/**
 *
 */
package com.logient.api.services;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;

import org.json.me.JSONException;
import org.json.me.JSONObject;

import com.logient.api.JSONObjectMapper;
import com.logient.api.Response;

import android.net.ConnectivityManager;
import android.net.ConnectivityManager.NetworkCallback;
import android.net.Network;
import android.net.NetworkCapabilities;
import ej.components.dependencyinjection.ServiceLoaderFactory;
import ej.net.util.NtpUtil;
import ej.rest.web.JSONResource;
import ej.rest.web.Resty;

/**
 *
 */
public class RestService {
	public static final Logger LOGGER = Logger.getLogger("RestService"); //$NON-NLS-1$

	private final String certificateType;
	private final String tlsVersion;
	private final String baseUrl;
	private final String certificatePath;
	private final Map<String, JSONObjectMapper> mappers = new HashMap<String, JSONObjectMapper>();

	/**
	 * @param baseUrl
	 * @param certificatePath
	 */
	public RestService(String baseUrl, String certificatePath) throws Exception {
		this("X509", "TLSv1.2", baseUrl, certificatePath);
	}

	public RestService(String certificateType, String tlsVersion, String baseUrl, String certificatePath)
			throws Exception {
		this.certificateType = certificateType;
		this.tlsVersion = tlsVersion;
		this.baseUrl = baseUrl;
		this.certificatePath = certificatePath;
		waitForConnectivity();
		updateTime();
		initRestyHttpsContext();
	}

	public <T> void addMapper(JSONObjectMapper<T> mapper) {
		this.mappers.put(mapper.getOutputClassSimpleName(), mapper);
	}

	private void waitForConnectivity() {
		LOGGER.info("=========== Waiting for connectivity ==========="); //$NON-NLS-1$
		final Object mutex = new Object();
		final ConnectivityManager service = ServiceLoaderFactory.getServiceLoader()
				.getService(ConnectivityManager.class);
		if (service != null) {
			NetworkCallback callback = new NetworkCallback() {
				@Override
				public void onCapabilitiesChanged(Network network, NetworkCapabilities networkCapabilities) {
					if (networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)) {
						synchronized (mutex) {
							mutex.notify();
						}
					}
				}
			};
			service.registerDefaultNetworkCallback(callback);
			NetworkCapabilities capabilities = service.getNetworkCapabilities(service.getActiveNetwork());
			if (capabilities == null || !capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)) {
				synchronized (mutex) {
					try {
						mutex.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
			service.unregisterNetworkCallback(callback);
		}
		LOGGER.info("Connected"); //$NON-NLS-1$
	}

	public static void updateTime() {
		LOGGER.info("=========== Updating time ==========="); //$NON-NLS-1$

		try {
			NtpUtil.updateLocalTime();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		LOGGER.info("Time updated"); //$NON-NLS-1$
	}

	private void initRestyHttpsContext() throws Exception {
		/*
		 * Create and initialize the SSLContext which will be used to connect to the secure Server. The followings steps
		 * show how to create and setup the SSLContext for Resty Https connection.
		 */

		/*
		 * Step 1 : Create an input stream with the server certificate file
		 */
		try (InputStream in = RestService.class.getResourceAsStream(this.certificatePath)) {
			/*
			 * Step 2 : Generate the server certificate
			 */
			CertificateFactory certificateFactory = CertificateFactory.getInstance(this.certificateType);
			Certificate myServerCert = certificateFactory.generateCertificate(in);

			/*
			 * Step 3 : Create and setup the KeyStore with the server certificate
			 */

			// create a default KeyStore
			KeyStore store = KeyStore.getInstance(KeyStore.getDefaultType());
			// our default KeyStore can not be loaded from an InputStream; so just load as empty KeyStore with null
			// parameters
			store.load(null, null);
			// add the server certificate to our created KeyStore
			store.setCertificateEntry("myServer", myServerCert); //$NON-NLS-1$

			/*
			 * Step 4: Create and initialize the trust manager with our KeyStore
			 */
			TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(this.certificateType);
			trustManagerFactory.init(store);
			TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();

			/*
			 * Step 5 : Create and and initialize the SSLContext with our trust managers
			 */
			SSLContext sslContext = SSLContext.getInstance(this.tlsVersion);
			sslContext.init(null, trustManagers, null);

			/*
			 * Step 6 : Finally, tell Https to use by default our sslContext's SocketFactory for SSLSocket creation. All
			 * Https Resty connection will use this SSLContext to connect to a secure server and the SSL handshake will
			 * be done with the defined trust store and TLS algorithm v1.2.
			 */
			HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());
		}
	}

	@SuppressWarnings("unchecked")
	public <T> Response<T> get(String requestUrl, Class<T> clazz) throws Exception {
		String requestPath = this.baseUrl + requestUrl;

		LOGGER.info("get: " + requestPath); //$NON-NLS-1$

		Resty resty = new Resty();
		JSONResource resource = resty.json(requestPath);
		HttpURLConnection conn = resource.http();
		try {
			int responseCode = conn.getResponseCode();
			// check the connection response code
			if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
				return new Response(conn.getResponseCode(), transform(resource.object(), clazz));

			} else {
				return new Response(conn.getResponseCode());
			}

		} catch (JSONException e) {
			LOGGER.severe(e.getLocalizedMessage());
			return new Response(conn.getResponseCode());
		} finally {
			conn.disconnect();
		}
	}

	private <T> T transform(JSONObject input, Class<T> clazz) throws JSONException {
		return (T) this.mappers.get(clazz.getSimpleName()).transform(input);
	}
}

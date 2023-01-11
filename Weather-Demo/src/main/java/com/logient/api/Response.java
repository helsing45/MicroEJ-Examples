/**
 *
 */
package com.logient.api;

/**
 *
 */
public class Response<T> {
	public int code;
	public T body;

	public Response(int code) {
		this(code, null);
	}

	public Response(int code, T body) {
		this.code = code;
		this.body = body;
	}
}

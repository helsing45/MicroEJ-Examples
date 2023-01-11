package com.logient.api;

import org.json.me.JSONException;
import org.json.me.JSONObject;

public interface JSONObjectMapper<Output> {

	public String getOutputClassSimpleName();

	Output transform(JSONObject input) throws JSONException;

}
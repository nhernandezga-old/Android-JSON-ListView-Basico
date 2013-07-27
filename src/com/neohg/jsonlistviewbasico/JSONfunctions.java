package com.neohg.jsonlistviewbasico;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class JSONfunctions {
	public static JSONObject getJSONfromURL(String url){
		//initialize
		InputStream Input_Stream = null;
		String result = "";
		JSONObject jArray = null;

		//http post
		try{
			
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost(url);
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
			Input_Stream = entity.getContent();
			
		}catch (Exception e) {
			Log.e("log_tag","Error in Http Connection" + e.toString());
		}

		//convert response to string

		try {

			BufferedReader reader = new BufferedReader(new InputStreamReader(Input_Stream, "iso-8859-1"), 8);
			StringBuilder String_builder = new StringBuilder();
			String line = null;

			while ((line = reader.readLine()) != null) {
				String_builder.append(line+ "\n");
			}
		
			Input_Stream.close();
			
			result = String_builder.toString();

		} catch (Exception e) {
			Log.e("Log_tag","Error Converting result" + e.toString());
		}
		
		try {
			jArray = new JSONObject(result);

		} catch (JSONException e) {
			Log.e("Log_tag", "Error Parsing Data" + e.toString());
		}
		return jArray;
	}
}
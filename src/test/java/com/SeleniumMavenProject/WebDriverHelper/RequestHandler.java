package com.SeleniumMavenProject.WebDriverHelper;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.SeleniumMavenProject.Common.CustomLogger;

public class RequestHandler {

	public String sendGetRequest(String apiUrl) {
		try {
			CloseableHttpClient client = HttpClients.createDefault();
			HttpGet getRequest = new HttpGet(apiUrl);
			HttpResponse response = client.execute(getRequest);

			HttpEntity entity = response.getEntity();
			return EntityUtils.toString(entity);
		} catch (Exception e) {
			CustomLogger.logError("sendGetRequest: " + e.getMessage());
			return null;
		}
	}

	public String sendPostRequest(String apiUrl, String[][] params) {
		try {
			CloseableHttpClient client = HttpClients.createDefault();
			HttpPost postRequest = new HttpPost(apiUrl);
			List<BasicNameValuePair> pairs = new ArrayList<BasicNameValuePair>();

			for (int i = 0; i < params.length; i++) {
				pairs.add(new BasicNameValuePair(params[i][0], params[i][1]));
			}

			postRequest.setEntity(new UrlEncodedFormEntity(pairs));
			HttpResponse response = client.execute(postRequest);

			HttpEntity entity = response.getEntity();
			return EntityUtils.toString(entity);
		} catch (Exception e) {
			CustomLogger.logError("sendPostRequest: " + e.getMessage());
			return null;
		}
	}
}

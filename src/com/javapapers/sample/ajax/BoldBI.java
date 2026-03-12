package com.javapapers.sample.ajax;

import java.io.PrintWriter;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class BoldBI extends HttpServlet {

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws java.io.IOException {
		JSONObject embedDetails = new JSONObject();
		embedDetails.put("email", GlobalAppSettings.EmbedDetails.UserEmail);
		embedDetails.put("serverurl", GlobalAppSettings.EmbedDetails.ServerUrl);
		embedDetails.put("siteidentifier", GlobalAppSettings.EmbedDetails.SiteIdentifier);
		embedDetails.put("embedsecret", GlobalAppSettings.EmbedDetails.EmbedSecret);
		embedDetails.put("dashboard", new JSONObject() {{
			put("id", GlobalAppSettings.EmbedDetails.DashboardId);
		}});

		String requestUrl = embedDetails.get("serverurl") + "/api/" + embedDetails.get("siteidentifier")
				+ "/embed/authorize";
		String jsonPayload = embedDetails.toJSONString();
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(requestUrl))
				.header("Content-Type", "application/json")
				.POST(HttpRequest.BodyPublishers.ofString(jsonPayload, StandardCharsets.UTF_8)).build();
		HttpResponse<String> response = null;
		try {
			response = client.send(request, HttpResponse.BodyHandlers.ofString());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String resultContent = response.body();
		String token = null;
		try {
			JSONParser parser = new JSONParser();
			JSONObject root = (JSONObject) parser.parse(resultContent);
			JSONObject data = (JSONObject) root.get("Data");
			if (data != null) {
				token = (String) data.get("access_token");
			}
		} catch (org.json.simple.parser.ParseException e) {
			e.printStackTrace();
		}
		// Write back to HTTP response
		res.setContentType("application/json");
		res.setCharacterEncoding("UTF-8");
		PrintWriter out = res.getWriter();
		if (token != null) {
			out.print(token);
		} else {
			out.print("{\"error\":\"Failed to generate embed token\"}");
		}
		out.flush();
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws java.io.IOException {
		doPost(req, res);
	}
}

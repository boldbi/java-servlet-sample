package com.javapapers.sample.ajax;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class EmbedConfigServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();

        try {
            // Prefer in-memory global settings if available
            if (GlobalAppSettings.EmbedDetails != null) {
                GlobalAppSettings.EmbedDetails g = GlobalAppSettings.EmbedDetails;
                JSONObject obj = new JSONObject();
                if (g.DashboardId != null) obj.put("DashboardId", g.DashboardId);
                if (g.ServerUrl != null) obj.put("ServerUrl", g.ServerUrl);
                if (g.UserEmail != null) obj.put("UserEmail", g.UserEmail);
                if (g.EmbedSecret != null) obj.put("EmbedSecret", g.EmbedSecret);
                if (g.EmbedType != null) obj.put("EmbedType", g.EmbedType);
                if (g.Environment != null) obj.put("Environment", g.Environment);
                if (g.ExpirationTime != null) obj.put("ExpirationTime", g.ExpirationTime);
                if (g.SiteIdentifier != null) obj.put("SiteIdentifier", g.SiteIdentifier);
                out.print(obj.toJSONString());
                out.flush();
                return;
            }

            // Fallback: attempt to stream the static file from webapp root
            InputStream is = getServletContext().getResourceAsStream("/embedConfig.json");
            if (is == null) {
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                JSONObject err = new JSONObject();
                err.put("error", "embedConfig.json not found in webapp root");
                out.print(err.toJSONString());
                out.flush();
                return;
            }

            // Validate JSON by parsing then re-emit (ensures we send valid JSON and avoid HTML 404 pages)
            JSONParser parser = new JSONParser();
            Object parsed = parser.parse(new InputStreamReader(is, java.nio.charset.StandardCharsets.UTF_8));
            if (parsed instanceof JSONObject) {
                out.print(((JSONObject) parsed).toJSONString());
            } else {
                out.print(parsed.toString());
            }
            out.flush();
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            JSONObject err = new JSONObject();
            err.put("error", "Failed to read embedConfig.json");
            err.put("details", e.getMessage());
            out.print(err.toJSONString());
            out.flush();
        }
    }
}

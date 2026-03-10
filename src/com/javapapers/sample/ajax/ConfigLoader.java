package com.javapapers.sample.ajax;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.InputStreamReader;

public class ConfigLoader implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try (java.io.InputStream is = sce.getServletContext().getResourceAsStream("/embedConfig.json")) {
            if (is == null) {
                System.err.println("embedConfig.json not found in webapp root");
                return;
            }
            JSONParser parser = new JSONParser();
            JSONObject cfg = (JSONObject) parser.parse(new InputStreamReader(is, java.nio.charset.StandardCharsets.UTF_8));
            GlobalAppSettings.EmbedDetails d = new GlobalAppSettings.EmbedDetails();
            d.DashboardId = (String) cfg.get("DashboardId");
            d.ServerUrl = (String) cfg.get("ServerUrl");
            d.UserEmail = (String) cfg.get("UserEmail");
            d.EmbedSecret = (String) cfg.get("EmbedSecret");
            d.EmbedType = (String) cfg.get("EmbedType");
            d.Environment = (String) cfg.get("Environment");
            d.ExpirationTime = (String) cfg.get("ExpirationTime");
            d.SiteIdentifier = (String) cfg.get("SiteIdentifier");
            GlobalAppSettings.EmbedDetails = d;
            System.out.println("Loaded embedConfig.json into GlobalAppSettings");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // no-op
    }
}

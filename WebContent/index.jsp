<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>BoldBI Embed - JAVA Servlet</title>
<script type="text/javascript" src="ajax.js"></script>
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script type="text/javascript"
	src="https://cdn.boldbi.com/embedded-sdk/latest/boldbi-embed.js"></script>
</head>
<body onload="embedSample()">
    <div id="dashboard"></div>
    <script>
        function embedSample() {
            // load configuration from webroot
            fetch('embedConfig.json')
                .then(r => r.json())
                .then(cfg => {
                    getEmbedToken().then(accessToken => {
                        var serverUrl = cfg.ServerUrl;
                        if (cfg.SiteIdentifier) {
                            serverUrl = serverUrl.replace(/\/$/, '') + '/' + cfg.SiteIdentifier.replace(/^\//, '');
                        }
                        var boldbiEmbedInstance = BoldBI.create({
                            serverUrl: serverUrl,
                            dashboardId: cfg.DashboardId,
                            embedContainerId: "dashboard",
                            embedToken: accessToken,
                        });
                        boldbiEmbedInstance.loadDashboard();
                    }).catch(error => {
                        console.error("Failed to get embed token:", error);
                    });
                })
                .catch(err => console.error('Failed to load embedConfig.json', err));
        }

        function getEmbedToken() {
            return fetch('BoldBI.do', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({})
            })
                .then(response => response.text())
                .then(data => data);
        }
    </script>
</body>
</html>
# BoldBI Embedding Java Servlet Sample

 This Bold BI Java Servlet sample repository contains the Dashboard embedding sample. This sample demonstrates how to embed the dashboard which is available in your Bold BI server.

This section guides you in using the Bold BI dashboard in your Java Servlet sample application.

 * [Requirements to run the demo](#requirements-to-run-the-demo)
 * [Using the Java Servet sample](#using-the-java-servlet-sample)
 * [Online Demos](#online-demos)
 * [Documentation](#documentation)

 ## Requirements to run the demo

The samples require the following requirements to run.

 * [Eclipse IDE](https://www.eclipse.org/downloads/)

 ## Using the Java Servlet sample
 
 * Open the file `index.jsp` of the sample in Eclipse. 

 * Please change the following properties in the `index.jsp` file as per your Bold BI Server.

<meta charset="utf-8"/>
<table>
  <tbody>
    <tr>
        <td align="left">RootUrl</td>
        <td align="left">Dashboard Server URL (Eg: http://localhost:5000/bi, http://demo.boldbi.com/bi).</td>
    </tr>
    <tr>
        <td align="left">SiteIdentifier</td>
        <td align="left">For the Bold BI Enterprise edition, it should be like `site/site1`. For Bold BI Cloud, it should be an empty string.</td>
    </tr>
    <tr>
        <td align="left">Environment</td>
        <td align="left">Your Bold BI application environment. (If Cloud, you should use cloud, if Enterprise, you should use onpremise)</td>
    </tr>
    <tr>
        <td align="left">DashboardId</td>
        <td align="left">Set the item id of the dashboard to embed from BI server.</td>
    </tr>
    <tr>
        <td align="left">EmbedSecret</td>
        <td align="left">Get your EmbedSecret key from the Embed tab by enabling the `Enable embed authentication` on the Administration page https://help.boldbi.com/embedded-bi/site-administration/embed-settings/.</td>
    </tr>
    <tr>
        <td align="left">UserEmail</td>
        <td align="left">UserEmail of the Admin in your Bold BI, which would be used to get the dashboard list.</td>
    </tr>
  </tbody>
</table>


Please refer to the [help documentation](https://help.boldbi.com/embedded-bi/javascript-based/samples/v3.3.40-or-later/other-platform-samples/#java-servlet-sample-to-embed-dashboard) to know how to run the sample.

## Online Demos

Look at the Bold BI Embedding sample to live demo [here](https://samples.boldbi.com/embed).


## Documentation

A complete Bold BI Embedding documentation can be found on [Bold BI Embedding Help](https://help.boldbi.com/embedded-bi/javascript-based/).

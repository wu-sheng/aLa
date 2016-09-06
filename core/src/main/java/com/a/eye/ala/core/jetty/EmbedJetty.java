package com.a.eye.ala.core.jetty;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

import java.net.URL;

public class EmbedJetty {
    public void start(int port) throws Exception {
        Server server = new Server(port);
        WebAppContext root = new WebAppContext();

        root.setContextPath("/");
        root.setDescriptor("webroot/WEB-INF/web.xml");
        URL webAppDir = Thread.currentThread().getContextClassLoader().getResource("webroot");
        if (webAppDir == null) {
            throw new RuntimeException(String.format("No %s directory was found into the JAR file", "webroot"));
        }
        root.setResourceBase(webAppDir.toURI().toString());
        root.setParentLoaderPriority(true);
        server.setHandler(root);
        server.start();
    }

}

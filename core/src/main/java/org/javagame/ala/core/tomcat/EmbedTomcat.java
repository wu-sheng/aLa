package org.javagame.ala.core.tomcat;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

import javax.servlet.ServletException;

/**
 * Created by wusheng on 16/8/1.
 */
public class EmbedTomcat {
    private final Tomcat tomcat;

    public EmbedTomcat(int port) throws ServletException {
        String TOMCAT_PATH = EmbedTomcat.class.getResource("/").getPath();
        String WEB_APP_PATH = TOMCAT_PATH + "webroot/";
        tomcat = new Tomcat();
        tomcat.setBaseDir(TOMCAT_PATH);
        tomcat.setHostname("localhost");
        tomcat.enableNaming();
        tomcat.addWebapp("/aLa", WEB_APP_PATH);
        tomcat.getConnector().setURIEncoding("UTF-8");
        tomcat.setPort(port);
    }

    public void start() throws LifecycleException {
        tomcat.start();
    }
}

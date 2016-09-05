package com.a.eye.ala.core;

import org.apache.catalina.LifecycleException;
import com.a.eye.ala.core.tomcat.EmbedTomcat;

import javax.servlet.ServletException;

/**
 * Created by wusheng on 16/8/1.
 */
public class Bootstrap {
    public static void boot(int port) throws ServletException, LifecycleException {
        EmbedTomcat tomcat = new EmbedTomcat(port);
        tomcat.start();

        while(true){
            try {
                Thread.sleep(60 * 1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws ServletException, LifecycleException {
        Bootstrap.boot(19780);
    }
}

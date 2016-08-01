package org.javagame.ala.core.tomcat;

import org.apache.catalina.LifecycleException;
import org.junit.Test;

import javax.servlet.ServletException;

/**
 * Created by wusheng on 16/8/1.
 */
public class EmbedTomcatTest {
    @Test
    public void testStart() throws LifecycleException, ServletException {
        EmbedTomcat tomcat = new EmbedTomcat(8080);
        tomcat.start();
    }

}

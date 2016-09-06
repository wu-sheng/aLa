package com.a.eye.ala.core;

import com.a.eye.ala.core.context.spring.SpringContextAware;
import com.a.eye.ala.core.jetty.EmbedJetty;

/**
 * Created by wusheng on 16/8/1.
 */
public class Bootstrap {
    public static void boot(int port) throws Exception {
        new EmbedJetty().start(port);
    }

    public void defaultBoot() throws Exception {
        Bootstrap.boot(19780);


    }

    public static void main(String[] args) throws Exception {
        Bootstrap.boot(19780);
    }
}

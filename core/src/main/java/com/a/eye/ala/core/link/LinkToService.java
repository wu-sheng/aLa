package com.a.eye.ala.core.link;

import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyCodeSource;
import groovy.lang.GroovyObject;
import com.a.eye.ala.core.uti.ExceptionToResponse;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by wusheng on 16/9/5.
 */
public class LinkToService {
    private static final Map<String, GroovyObject> SERVICE_CACHE = new ConcurrentHashMap<String, GroovyObject>();

    public String doService(String serviceName, String params, boolean isReload) {
        if (isReload) {
            SERVICE_CACHE.remove(serviceName);
        }

        if (!SERVICE_CACHE.containsKey(serviceName)) {
            GroovyClassLoader classLoader = new GroovyClassLoader(Thread.currentThread().getContextClassLoader());

            String basePath = LinkToService.class.getResource("/").getPath();
            File sourceFile = new File(basePath, serviceName + ".groovy");
            try {
                Class dynamicClass = classLoader.parseClass(new GroovyCodeSource(sourceFile));

                GroovyObject dynamicInstance = (GroovyObject) dynamicClass.newInstance();//proxy

                SERVICE_CACHE.put(serviceName, dynamicInstance);
            } catch (IOException e) {
                return ExceptionToResponse.getResponse("load groovy for service[" + serviceName + "] failure", e);
            } catch (InstantiationException e) {
                return ExceptionToResponse.getResponse("create service[" + serviceName + "] failure", e);
            } catch (IllegalAccessException e) {
                return ExceptionToResponse.getResponse("create service[" + serviceName + "] failure", e);
            }
        }

        GroovyObject dynamicInstance = SERVICE_CACHE.get(serviceName);

        return (String) dynamicInstance.invokeMethod("doService", params);
    }
}

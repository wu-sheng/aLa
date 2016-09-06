package com.a.eye.ala.core.servlet;

import com.a.eye.ala.core.link.LinkToService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by wusheng on 16/9/5.
 */
public class OpenService extends HttpServlet {
    private static final String SERVICE_BASE_PATH = "/aLaService/";

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        this.doPost(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        String serviceName = getServiceName(request.getRequestURI());
        LinkToService service = new LinkToService();
        String responseBody = service.doService(serviceName, request.getParameter("args"), true);
        PrintWriter writer = response.getWriter();
        writer.print(responseBody);
        writer.flush();
    }

    private String getServiceName(String serviceUri){
        if(serviceUri.startsWith(SERVICE_BASE_PATH)){
            return serviceUri.substring(SERVICE_BASE_PATH.length());
        }
        return "";
    }
}

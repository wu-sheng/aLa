package com.a.eye.ala.core.uti;

import com.google.gson.JsonObject;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Created by wusheng on 16/9/5.
 */
public class ExceptionToResponse {
    public static String getResponse(String msg, Exception e) {
        JsonObject exceptionMsg = new JsonObject();
        exceptionMsg.addProperty("code", 500);
        exceptionMsg.addProperty("msg", msg);
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        exceptionMsg.addProperty("exception", sw.toString());

        return exceptionMsg.toString();
    }
}

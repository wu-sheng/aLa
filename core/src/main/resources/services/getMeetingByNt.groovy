package services

import com.a.eye.ala.core.context.spring.SpringContextAware
import com.ai.meet.core.api.IMeetingApi;

public class test {
    public String doService(String args) throws Exception {
        Class beanClass = Class.forName("com.ai.meet.core.api.IMeetingApi");
        IMeetingApi meetingApi = SpringContextAware.getApplicationContext().getBean(beanClass);
        return meetingApi.getMeetingbyNt(args);
    }
}

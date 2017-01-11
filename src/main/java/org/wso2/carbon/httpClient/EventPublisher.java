package org.wso2.carbon.httpClient;

import org.wso2.carbon.httpClient.httpClientCore.HttpClient;

public class EventPublisher {
    String eventstr;

    public EventPublisher(){

    }
    public void publishEvents(String srt){
        this.eventstr = srt;
        HttpClient httpclient = new HttpClient(eventstr);
        httpclient.postEvent();
    }
}

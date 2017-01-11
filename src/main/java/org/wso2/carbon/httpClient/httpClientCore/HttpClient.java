package org.wso2.carbon.httpClient.httpClientCore;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.SystemDefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class HttpClient {

    private static final String URL = "http://localhost:9764/endpoints/pattern_matching_for_wso2_log_analyzer";

    // Create an HTTP client.
    org.apache.http.client.HttpClient httpClient = new SystemDefaultHttpClient();

    // Create a POST method using the receiver URL.
    HttpPost method = new HttpPost(URL);

    String eventString;

    public HttpClient(String str){
        this.eventString = str;
    }

    public void postEvent(){
        try {
            // Create an entity and add it to the method.
            StringEntity entity = new StringEntity(eventString);
            method.setEntity(entity);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        try {
            // Execute the method and retrieve the response.
            HttpResponse response = httpClient.execute(method);

            // Get the entity out of the response.
            HttpEntity httpEntity = response.getEntity();

            int status = response.getStatusLine().getStatusCode();

            // Check the status code for successful completion.
            if (status == 200) {
                System.out.println("Published");
            } else {
                System.out.println("Failed to publish");
            }

            // Close the connection and release the resources.
            try {
                EntityUtils.consume(httpEntity);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}

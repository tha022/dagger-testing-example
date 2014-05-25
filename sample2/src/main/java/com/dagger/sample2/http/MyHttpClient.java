package com.dagger.sample2.http;

import com.dagger.sample2.models.MyModel;
import com.dagger.sample2.exceptions.MyHttpException;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Thomas on 5/21/14.
 */
@Singleton
public class MyHttpClient {

    private HttpClient mClient;

    @Inject
    public MyHttpClient() {
        mClient = new DefaultHttpClient();
    }


    public MyModel executeRequest(HttpUriRequest get) throws IOException, MyHttpException {
        HttpResponse httpResponse = mClient.execute(get);

        int statusCode = httpResponse.getStatusLine().getStatusCode();
        if(statusCode != 200) {
            throw new MyHttpException(statusCode);
        }
        String content = responseToString(httpResponse);

        return new MyModel(statusCode, content);
    }

    public String responseToString(HttpResponse httpResponse) throws IllegalStateException, IOException {
        StringBuilder response = new StringBuilder();
        String aLine = new String();

        InputStream is = httpResponse.getEntity().getContent();
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));

        while ((aLine = reader.readLine()) != null) {
            response.append(aLine);
        }

        reader.close();
        return response.toString();
    }
}

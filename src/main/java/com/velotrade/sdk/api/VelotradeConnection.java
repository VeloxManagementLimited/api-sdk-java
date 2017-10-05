package com.velotrade.sdk.api;

import com.velotrade.sdk.entity.RequestMethod;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.*;
import java.util.List;
import java.util.Map;

public abstract class VelotradeConnection {

    protected String baseUrl;
    protected String userName;
    protected String password;
    protected String token;
    protected String entityId;

    protected String loginRequest = null;

    public VelotradeConnection(String baseUrl, String userName, String password, String loginRequest) throws Exception {
        this.baseUrl = baseUrl;
        this.userName = userName;
        this.password = password;
        this.loginRequest = loginRequest;
        this.token = getAuthToken();

    }

    public String getBaseUrl() {
        return baseUrl;
    }

    abstract protected String getAuthToken() throws IOException;

    public String getEntityId() {
        return entityId;
    }

    /**
     *
     * @param method GET, POST ....
     * @param request path
     * @param params list of parameters, if using jsonParams put with format name:"json", value:"yourJsonString"
     * @param contentTypes list of contentType for header
     * @param isWithJson true if want to send request with jsonParam
     * @return return Json format
     * @throws IOException
     */
    public String query(String method, String request, List<NameValuePair> params, Map<String, String> contentTypes, boolean isWithJson) throws IOException {
        String result = null;
        CloseableHttpClient client = HttpClients.createDefault();
        ResponseHandler<String> handler = new BasicResponseHandler();
        CloseableHttpResponse response;
        switch (method){
            case RequestMethod.GET:
                HttpGet httpGet = new HttpGet(baseUrl + request);
                for (Map.Entry<String, String> entry: contentTypes.entrySet()) {
                        httpGet.addHeader(entry.getKey(), entry.getValue());
                }
                httpGet.addHeader("Authorization", this.token);
                response = client.execute(httpGet);
                result = handler.handleResponse(response);
                client.close();
                break;
            case RequestMethod.POST:
                HttpPost httpPost = new HttpPost(baseUrl + request);
                for (Map.Entry<String, String> entry: contentTypes.entrySet()) {
                    httpPost.addHeader(entry.getKey(), entry.getValue());
                }
                httpPost.addHeader("Authorization", this.token);

                if(isWithJson){
                    httpPost.setHeader("Accept", "application/json");
                    StringEntity entity = new StringEntity(params.get(0).getValue());
                    httpPost.setEntity(entity);
                }else{
                    httpPost.setEntity(new UrlEncodedFormEntity(params));
                }

                response = client.execute(httpPost);
                result = handler.handleResponse(response);
                client.close();
                break;
        }

        return result;

    }

    /**
     * use for uploading file
     * @param filePath
     * @param request
     * @return id and name of file in json format
     * @throws IOException
     */
    public String uploadFile(String filePath, String request) throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(baseUrl+request);
        httpPost.addHeader("Authorization", this.token);

        File file = new File(filePath);
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder.addBinaryBody("file", file,
                ContentType.APPLICATION_OCTET_STREAM,
                file.getAbsolutePath().substring(file.getAbsolutePath().lastIndexOf("/")+1));
        HttpEntity multipart = builder.build();

        httpPost.setEntity(multipart);

        CloseableHttpResponse response = client.execute(httpPost);


        ResponseHandler<String> handler = new BasicResponseHandler();
        String result = handler.handleResponse(response);

        client.close();
        return result;
    }

}

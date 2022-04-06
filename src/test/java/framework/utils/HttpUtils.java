package framework.utils;

import framework.model.HttpModel;
import framework.urls.Urls;
import io.restassured.response.Response;

public class HttpUtils {

    private HttpModel httpModel;

    public HttpUtils() {
        this.httpModel = new HttpModel(Urls.BASE_URL);
    }

    public Response sendGetRequest(String path, String endpoint) {
        httpModel.setBaseUri(endpoint);
        return httpModel.httpGet(path);
    }

    public Response sendPostRequest(String path, String endpoint, String body) {
        httpModel.setBaseUri(endpoint);
        return httpModel.httpPost(path, body);
    }
}

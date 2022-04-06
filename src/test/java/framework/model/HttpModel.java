package framework.model;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.http.Cookies;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.nio.charset.StandardCharsets;
import java.util.Map;

public class HttpModel {

    private Cookies cookies;

    private String baseUri;

    private boolean urlEncodingEnabled;

    public HttpModel(final String uri) {
        this.baseUri = uri;
    }

    public RequestSpecification getRequestSpecification() {
        return new RequestSpecBuilder().setBaseUri(baseUri)
                .setUrlEncodingEnabled(urlEncodingEnabled)
                .setRelaxedHTTPSValidation()
                .setContentType(ContentType.JSON.withCharset(StandardCharsets.UTF_8))
                .addCookie(String.valueOf(cookies != null ? cookies : new Cookies()))
                .build();
    }

    public Response httpGet(final String path) {
        return RestAssured.given()
                .spec(getRequestSpecification())
                .when().get(path);
    }

    public Response httpGet(final String path, final Map<String, String> param) {
        return RestAssured.given()
                .spec(getRequestSpecification())
                .queryParams(param)
                .when().get(path);
    }

    public Response httpPost(final String path, final String body) {
        return RestAssured.given()
                .spec(getRequestSpecification())
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post(path);
    }

    public Cookies getCookies() {
        return cookies;
    }

    public void setCookies(Cookies cookies) {
        this.cookies = cookies;
    }

    public String getBaseUri() {
        return baseUri;
    }

    public void setBaseUri(String baseUri) {
        this.baseUri = baseUri;
    }

    public boolean isUrlEncodingEnabled() {
        return urlEncodingEnabled;
    }

    public void setUrlEncodingEnabled(boolean urlEncodingEnabled) {
        this.urlEncodingEnabled = urlEncodingEnabled;
    }
}

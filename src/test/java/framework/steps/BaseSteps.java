package framework.steps;

import framework.urls.Urls;
import framework.utils.HttpUtils;
import io.qameta.allure.Step;
import io.restassured.response.Response;


import static framework.utils.GenerateJSONUtils.generateCreateUserJSON;
import static framework.utils.GenerateJSONUtils.generateUnsuccessfullJSON;

public class BaseSteps extends HttpUtils {


    @Step("userListResponse")
    public Response userListResponse(String pageNumber) {
        return sendGetRequest(Urls.LIST_USERS_URL + pageNumber, Urls.BASE_URL);
    }

    @Step("isPageFound")
    public boolean isUserFound(String userId) {
        Response response = sendGetRequest(Urls.USER_URL + userId, Urls.BASE_URL);
        return response.getStatusCode() != 404 ? true : false;
    }

    @Step("pageStatusCode")
    public Integer pageStatusCode(String path) {
        Response response = sendGetRequest(path, Urls.BASE_URL);
        System.out.println(response.getStatusCode());
        return response.getStatusCode();
    }

    @Step("userResponse")
    public Response userResponse(String userId) {
        return sendGetRequest(Urls.USER_URL + userId, Urls.BASE_URL);
    }

    @Step("createUser")
    public Response createUser(String name, String job) {
        String body = generateCreateUserJSON(name, job);
        return sendPostRequest(Urls.USER_URL, Urls.BASE_URL, body);
    }

    @Step("unsuccessfullLogin")
    public Response unsuccessfullLogin(String email) {
        String body = generateUnsuccessfullJSON(email);
        return sendPostRequest(Urls.USER_LOGIN, Urls.BASE_URL, body);
    }


}

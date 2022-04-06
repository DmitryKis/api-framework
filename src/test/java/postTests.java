import framework.steps.BaseSteps;
import framework.urls.Urls;
import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pojoModels.Data;
import pojoModels.ListUsersPojo;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class postTests {

    BaseSteps baseSteps;

    @BeforeClass
    public void before() {
        baseSteps = new BaseSteps();
    }


    @Test
    @Description("Проверка успешного создания профиля клиента")
    public void createUserIsValidRequestTest() {
        Response response = baseSteps.createUser("Vasiliy", "Haker");
        response.then().assertThat().statusCode(201);
        response.then().assertThat().body("name", equalTo("Vasiliy"));
        response.then().assertThat().body("job", equalTo("Haker"));
        response.then().assertThat().body("id", notNullValue());
        response.then().assertThat().body("createdAt", notNullValue());
    }

    @Test
    @Description("Проверка неправильного создания профиля клиента")
    public void createUserIsNotValidRequestTest() {
        Response response = baseSteps.unsuccessfullLogin("peter@klaven");
        response.then().assertThat().statusCode(400);
        response.then().assertThat().body("error", equalTo("Missing password"));
    }

}

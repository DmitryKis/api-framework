import framework.steps.BaseSteps;
import framework.urls.Urls;
import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pojoModels.Data;
import pojoModels.ListUsersPojo;
import pojoModels.UserPojo;

import java.util.Optional;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class getTests {

    BaseSteps baseSteps;

    @BeforeClass
    public void before() {
        baseSteps = new BaseSteps();
    }

    @Test
    @Description("Проверка на соответствие ID клиента и названия аватара профиля")
    public void listUsersAvatarNumberEqualsIdTest() {
        ListUsersPojo listUsersPojo = baseSteps.userListResponse("1").as(ListUsersPojo.class);
        for (Data user : listUsersPojo.getData()) {
            Assert.assertTrue(user.getAvatar().contains(user.getId().toString()));
        }
    }

    @Test
    @Description("Проверка отсутствия профиля несуществующего клиента")
    public void userPageIsNotFoundTest() {
        Assert.assertFalse(baseSteps.isUserFound("23"));
    }

    @Test
    @Description("Проверка заполнености данныйх о клиенте")
    public void userResponseValidatonTest() {
        UserPojo userPojo = baseSteps.userResponse("2").as(UserPojo.class);
        Assert.assertEquals(userPojo.getData().getId(), (Integer) 2);
        Assert.assertTrue(userPojo.getData().getAvatar().contains(userPojo.getData().getId().toString()));
        Assert.assertNotNull(userPojo.getData().getEmail());
        Assert.assertNotNull(userPojo.getData().getFirst_name());
        Assert.assertNotNull(userPojo.getData().getLast_name());

    }

}

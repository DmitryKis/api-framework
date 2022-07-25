import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.response.ResponseBody;
import org.apache.commons.logging.Log;
import org.joda.time.DateTime;
import org.joda.time.LocalDateTime;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import pojoModels.UserPojo;

import java.util.LinkedHashSet;
import java.util.Optional;

public class newTests {

    @Test
    @Description("Проверка на соответствие ID клиента и названия аватара профиля")
    public void checkStatusCodeResponseIsTrueTest(){
        int statusCode = RestAssured.get("https://reqres.in/api/users?page=1").statusCode();
        Assert.assertEquals(statusCode, 200);
    }

    @Test
    @Description("Проверка на соответствие ID клиента и названия аватара профиля")
    public void checkCountListOfUsersTest(){
        ResponseBody body = RestAssured.get("https://reqres.in/api/users?page=1").getBody();
        int actualPages = body.jsonPath().get("total_pages");
        Assert.assertEquals(actualPages, 2);
    }

    @Test
    @Description("Проверка на соответствие ID клиента и названия аватара профиля")
    public void createUserTest(){
        JSONObject json = new JSONObject();
        json.put("name", "Vasia");
        json.put("job", "HR");
        ResponseBody body = RestAssured.given().body(json).post("https://reqres.in/api/users").getBody();
        String dateBuffer = body.jsonPath().get("createdAt").toString();
        LocalDateTime dateTime = LocalDateTime.parse(dateBuffer.substring(0, dateBuffer.length()-1));
        LocalDateTime actualDate = new LocalDateTime();
        Assert.assertTrue(dateTime.plusMinutes(5).isBefore(actualDate));
    }

    @Test
    @Description("Проверка на соответствие ID клиента и названия аватара профиля")
    public void checkIdEqualsAvatarIdTest(){
        ResponseBody body = RestAssured.get("https://reqres.in/api/users?page=1").getBody();
        int totalUsers = body.jsonPath().get("total");
        LinkedHashSet<UserPojo> userPojos = new LinkedHashSet<>();
        for (int i = 1; i <= totalUsers; i++){
            UserPojo userPojo = RestAssured.get("https://reqres.in/api/users/" + i).getBody().as(UserPojo.class);
            userPojos.add(userPojo);
        }
        userPojos.stream().forEach(userPojo -> {
            System.out.println(userPojo.toString());
            Assert.assertTrue(userPojo.getData().getAvatar().contains(userPojo.getData().getId().toString()));
        });

    }
}

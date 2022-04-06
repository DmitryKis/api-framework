package framework.utils;

import org.json.JSONObject;

public class GenerateJSONUtils {

    public static String generateCreateUserJSON(String name, String job) {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("job", job);
        return json.toString();
    }

    public static String generateUnsuccessfullJSON(String email) {
        JSONObject json = new JSONObject();
        json.put("email", email);
        return json.toString();
    }

}

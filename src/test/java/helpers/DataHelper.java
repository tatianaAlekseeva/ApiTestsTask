package helpers;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.InputUserData;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.List;

public class DataHelper {

    public static List<InputUserData> readUsersFromFile(String filePath) {
        Gson gson = new Gson();
        List<InputUserData> users = null;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            Type userListType = new TypeToken<List<InputUserData>>() {
            }.getType();
            users = gson.fromJson(reader, userListType);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return users;
    }

    public static InputUserData getUserdataForRequest(String username, String email, String password) {
        InputUserData userData = new InputUserData().setUsername(username)
                .setEmail(email)
                .setPassword(password);
        return userData;
    }
}

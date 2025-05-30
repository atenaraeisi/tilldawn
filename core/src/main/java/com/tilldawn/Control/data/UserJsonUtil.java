package com.tilldawn.Control.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tilldawn.Model.User;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class UserJsonUtil {
    public static void saveUsersToJson(List<User> users, String filePath) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try (FileWriter writer = new FileWriter(filePath)) {
            gson.toJson(users, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

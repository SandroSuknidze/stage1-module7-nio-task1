package com.epam.mjc.nio;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;


public class FileReader {

    public Profile getDataFromFile(File file) {
        try (BufferedReader reader = Files.newBufferedReader(file.toPath())) {
            Map<String, String> dataMap = new HashMap<>();
            String line;

            while ((line = reader.readLine()) != null) {
                String[] keyValue = line.split(": ");
                if (keyValue.length == 2) {
                    dataMap.put(keyValue[0].trim(), keyValue[1].trim());
                }
            }

            String name = dataMap.get("Name");
            Integer age = dataMap.containsKey("Age") ? Integer.parseInt(dataMap.get("Age")) : null;
            String email = dataMap.get("Email");
            Long phone = dataMap.containsKey("Phone") ? Long.parseLong(dataMap.get("Phone")) : null;

            return new Profile(name, age, email, phone);

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}

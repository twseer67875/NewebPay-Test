package com.cocobeen.Utils;

import com.cocobeen.Utils.struct.ConfigData;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileManager {

    public static ConfigData initConfigFile(){
        String filePath = "/Users/twseer67875/IdeaProjects/NewebPay/src/main/resources/config.json";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            StringBuilder content = new StringBuilder();

            char[] buffer = new char[1024];
            int bytesRead = 0;

            while ((bytesRead = reader.read(buffer)) != -1) {
                content.append(buffer, 0, bytesRead);
            }

            String allText = content.toString();
            reader.close();

            JSONObject json = new JSONObject(allText);
            return new ConfigData(json);
        }
        catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }

}

package com.kaifei.util.json;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kaifei.util.logger.LoggerUtil;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;

public class JsonDemo {

    String fileName = "conf/players.json";

    @Test
    public void testReadJsonFile() throws Exception {

        File file = new File(fileName);
        FileInputStream fis = new FileInputStream(file);
        byte[] bytes = new byte[fis.available()];
        fis.read(bytes);

        fis.close();

        String str = new String(bytes);
        System.out.println(str);

    }


    @Test
    public void testJacksonReadJsonFile() throws Exception {

        File file = new File(fileName);
        FileInputStream fis = new FileInputStream(file);
        byte[] bytes = new byte[fis.available()];
        fis.read(bytes);

        fis.close();

        String str = new String(bytes);
        System.out.println(str);

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(str);
            int retCode = jsonNode.get("retCode").asInt();
            System.out.println(retCode);

            // read data
            JsonNode userArray = jsonNode.get("data");
            Iterator<JsonNode> iterator = userArray.iterator();
            while (iterator.hasNext()){
                JsonNode user = iterator.next();
                System.out.println(user.get("id"));
                System.out.println(user.get("name"));
            }


        } catch ( Exception e) {
            LoggerUtil.error("parse json failed. error message is : " +  e.getMessage());
        }catch (Throwable e){

        }

    }
}

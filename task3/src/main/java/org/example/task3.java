package org.example;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class task3 {
    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("Неверное количество аргументов, необходимо передать три файла!");
            return;
        }

        String valuesPath = args[0];
        String testsPath = args[1];
        String reportPath = args[2];

        JSONObject valuesJson = readJsonFile(valuesPath);
        JSONObject testsJson = readJsonFile(testsPath);

        var values = (JSONArray) valuesJson.get("values");
        var tests = (JSONArray) testsJson.get("tests");

        makeReport(tests, values);

        writeJsonFile(testsJson, reportPath);
    }

    private static JSONObject readJsonFile(String filePath) {
        var parser = new JSONParser();
        try (var reader = new FileReader(filePath)) {
            return (JSONObject) parser.parse(reader);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static void writeJsonFile(JSONObject jsonObject, String filePath) {
        try (var file = new FileWriter(filePath)) {
            file.write(jsonObject.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void makeReport(JSONArray tests, JSONArray values) {
        for (Object testObj : tests) {
            var test = (JSONObject) testObj;
            long testId = (long) test.get("id");
            String testValue = searchValue(testId, values);
            if (testValue != null) {
                test.put("value", testValue);
            }

            if (test.containsKey("values")) {
                JSONArray innerTests = (JSONArray) test.get("values");
                makeReport(innerTests, values);
            }
        }
    }

    private static String searchValue(long testId, JSONArray values) {
        for (Object valueObj : values) {
            JSONObject value = (JSONObject) valueObj;
            long valueId = (long) value.get("id");
            if (valueId == testId) {
                return (String) value.get("value");
            }
        }
        return null;
    }
}
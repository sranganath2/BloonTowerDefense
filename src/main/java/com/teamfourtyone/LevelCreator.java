package com.teamfourtyone;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.Scanner;

public class LevelCreator {
    private static JSONObject getJSON(String id) {
        InputStream is = App.class.getResourceAsStream("levels/" + id + ".json");
        Scanner s = new Scanner(is).useDelimiter("\\A");
        String result = s.hasNext() ? s.next() : "";
        return new JSONObject(result);
    }

    public static int[][] loadPath(String levelID) {
        JSONObject obj = getJSON(levelID);
        JSONArray arr = obj.getJSONArray("path");

        int[][] path = new int[arr.length()][2];
        for (int i = 0; i < arr.length(); i++) {
            path[i][0] = arr.getJSONArray(i).getInt(0);
            path[i][1] = arr.getJSONArray(i).getInt(1);
        }
        return path;
    }

    public static int[] loadMonument(String levelID) {
        JSONObject obj = getJSON(levelID);
        JSONArray arr = obj.getJSONArray("monument");
        int[] location = new int[2];
        location[0] = arr.getInt(0);
        location[1] = arr.getInt(1);
        return location;
    }

    public static String loadPathColor(String levelID) {
        JSONObject obj = getJSON(levelID);
        return obj.getString("path_color");
    }

    public static String loadLevelName(String levelID) {
        JSONObject obj = getJSON(levelID);
        return obj.getString("level_name");
    }
}

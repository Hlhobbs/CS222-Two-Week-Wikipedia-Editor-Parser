package edu.bsu.cs222;


import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Map;

public class RevisionParser {
    JsonParser RevisionParser = new JsonParser();
    InputStream inputStream = getClass().getClassLoader().getResourceAsStream("sample.json");
    Reader reader = new InputStreamReader(inputStream);
    JsonElement rootElement = RevisionParser.parse(reader);
    JsonObject rootObject = rootElement.getAsJsonObject();
    JsonObject pages = rootObject.getAsJsonObject("query").getAsJsonObject("pages");
    JsonArray array = null;
}

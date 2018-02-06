package edu.bsu.cs222;


import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.junit.Assert;
import org.junit.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Map;

public class jsontests {

    @Test
    public void testReadRevisions(){
        JsonParser parser = new JsonParser();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("sample.json");
        Reader reader = new InputStreamReader(inputStream);
        JsonElement rootElement = parser.parse(reader);
        JsonObject rootObject = rootElement.getAsJsonObject();
        JsonObject pages = rootObject.getAsJsonObject("query").getAsJsonObject("pages");
        JsonArray array = null;
        for (Map.Entry<String,JsonElement> entry : pages.entrySet()) {
            JsonObject entryObject = entry.getValue().getAsJsonObject();
            array = entryObject.getAsJsonArray("revisions");
        }
        //JsonParser arrayParser = new JsonParser();
        //InputStream arrayStream = getClass().getClassLoader().getResourceAsStream("user");
        //Reader arrayReader = new InputStreamReader(arrayStream);
        //JsonElement userName = arrayParser.parse(arrayReader);

        //array.get(0).toString();

        JsonElement revisionAuthor = ((JsonObject) array.get(0)).get("user");
         String expected = "Kind Tennis Fan";
        Assert.assertEquals(expected, revisionAuthor);
    }
}

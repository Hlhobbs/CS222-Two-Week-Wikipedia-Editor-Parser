package edu.bsu.cs222;


import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class jsontests {

    @Test
    public void testReadRevisionAuthor() {
        JsonParser parser = new JsonParser();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("sample.json");
        Reader reader = new InputStreamReader(inputStream);
        JsonElement rootElement = parser.parse(reader);
        JsonObject rootObject = rootElement.getAsJsonObject();
        JsonObject pages = rootObject.getAsJsonObject("query").getAsJsonObject("pages");
        JsonArray array = null;
        for (Map.Entry<String, JsonElement> entry : pages.entrySet()) {
            JsonObject entryObject = entry.getValue().getAsJsonObject();
            array = entryObject.getAsJsonArray("revisions");

        }
        String FirstAuthor = array.get(0).getAsJsonObject().get("user").getAsString();
        Assert.assertEquals("Kind Tennis Fan", FirstAuthor);
    }

    @Test
    public void testRevisionParser() throws IOException {
        RevisionParser parser = new RevisionParser();
        InputStream input = getClass().getClassLoader().getResourceAsStream("sample.json");
        List<Revisions> revisions = parser.parse(input);
        System.out.println(revisions);
        Assert.assertEquals(4, revisions.size());
    }

    @Test
    public void testRevisionsAsObject() throws IOException {
        RevisionParser parser = new RevisionParser();
        InputStream input = getClass().getClassLoader().getResourceAsStream("sample.json");
        List<Revisions> revisionsList = parser.parse(input);
        Assert.assertNotNull(revisionsList);

    }

}

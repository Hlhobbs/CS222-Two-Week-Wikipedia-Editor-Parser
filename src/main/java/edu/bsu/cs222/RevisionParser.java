package edu.bsu.cs222;


import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.*;


public class RevisionParser {

    private List<Revisions> result = new ArrayList<Revisions>();

    @SuppressWarnings("RedundantThrows")
    public List<Revisions> parseByMostEdits(InputStream input) throws IOException {
        JsonParser RevisionParser = new JsonParser();
        Reader reader = new InputStreamReader(input);
        JsonElement rootElement = RevisionParser.parse(reader);
        JsonObject rootObject = rootElement.getAsJsonObject();
        JsonObject pages = rootObject.getAsJsonObject("query").getAsJsonObject("pages");
        JsonArray array = null;
        for (Map.Entry<String, JsonElement> entry : pages.entrySet()) {
            JsonObject entryObject = entry.getValue().getAsJsonObject();
            array = entryObject.getAsJsonArray("revisions");
        }


        assert array != null;
        for (int i = 0; i < array.size(); i++) {
            Revisions revisions = new Revisions();
            revisions.setId(array.get(i).getAsJsonObject().get("user").getAsString());
            revisions.setTimestamp(array.get(i).getAsJsonObject().get("timestamp").getAsString());
            result.add(revisions);
        }
       return SortByMostEdits(result);
    }

    @SuppressWarnings("RedundantThrows")
    public List<Revisions> parseByRecentEdits(InputStream input) throws IOException {
        JsonParser RevisionParser = new JsonParser();
        Reader reader = new InputStreamReader(input);
        JsonElement rootElement = RevisionParser.parse(reader);
        JsonObject rootObject = rootElement.getAsJsonObject();
        JsonObject pages = rootObject.getAsJsonObject("query").getAsJsonObject("pages");
        JsonArray array = null;
        for (Map.Entry<String, JsonElement> entry : pages.entrySet()) {
            JsonObject entryObject = entry.getValue().getAsJsonObject();
            array = entryObject.getAsJsonArray("revisions");
        }
        assert array != null;
        for (int i = 0; i < array.size(); i++) {
            Revisions revisions = new Revisions();
            revisions.setId(array.get(i).getAsJsonObject().get("user").getAsString());
            revisions.setTimestamp(array.get(i).getAsJsonObject().get("timestamp").getAsString());
            result.add(revisions);
        }
        return result;
    }


    private List<Revisions> SortByMostEdits(List<Revisions> result) {
        // This gets the count for each revision object
        for (int i = 0; i < result.size(); i++) {
            for (Revisions aResult : result) {
                if (result.get(i).getId().equals(aResult.getId())) {
                    result.get(i).addCount(1);
                }
            }

        }

        for (int i = 0; i < result.size()-1; i++) {
            for (int x = 0; x < result.size()-1; x++) {
                if (result.get(i).getId().equals(result.get(x).getId())) {
                    result.remove(x);
                }
            }



        }
        Comparator<Revisions> COMPARATOR = new Comparator<Revisions>()
        {
            // This is where the sorting happens.
            public int compare(Revisions o1, Revisions o2)
            {
                return o2.getCount() - o1.getCount();
            }
        };
        Collections.sort(result, COMPARATOR);



        return result;
        }
    }

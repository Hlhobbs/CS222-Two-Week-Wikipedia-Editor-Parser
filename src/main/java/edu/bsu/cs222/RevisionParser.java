package edu.bsu.cs222;


import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RevisionParser {
    JsonArray array;

    public List<Revision> parse (InputStream input)throws IOException{
        JsonParser RevisionParser = new JsonParser();
        Reader reader = new InputStreamReader(input);
        JsonElement rootElement = RevisionParser.parse(reader);
        JsonObject rootObject = rootElement.getAsJsonObject();
        JsonObject pages = rootObject.getAsJsonObject("query").getAsJsonObject("pages");
        List<Revision> result = new ArrayList<Revision>();
        JsonArray array = null;
        for (Map.Entry<String,JsonElement> entry : pages.entrySet()) {
            JsonObject entryObject = entry.getValue().getAsJsonObject();
            array = entryObject.getAsJsonArray("revisions");
        }
        for(int i= 0; i<array.size(); i++){
            result.add(new Revision());
        }
        return result;
    }


     //JsonObject Revision (){
        //array.;
     //}

}

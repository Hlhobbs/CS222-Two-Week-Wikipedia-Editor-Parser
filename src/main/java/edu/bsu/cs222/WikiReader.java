package edu.bsu.cs222;

import jdk.internal.util.xml.impl.Input;
import java.net.*;
import java.io.*;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;


public class WikiReader {

    public static void InternetConnectionTest() throws UnknownHostException, IOException
    {
        try
        {
            try
            {
                URL url = new URL("http://www.google.com");
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.connect();
            }
            catch (Exception exception)
            {
                System.out.println("No Internet Connection");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws Exception
    {

        InternetConnectionTest();

        String SearchTerm = "Soup";
        URL wikiurl = new URL("https://en.wikipedia.org/w/api.php?action=query&format=json&prop=revisions&titles="+SearchTerm+"&r%20vprop=timestamp|user&rvlimit=30&redirects");
        URLConnection connection = wikiurl.openConnection();
        connection.setRequestProperty("User-Agent","Revision Tracker/0.1 (me@bsu.edu)");
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        RevisionParser read = new RevisionParser();
        InputStream input = connection.getInputStream();

        System.out.println(read.parse(input));
        in.close();
    }
}
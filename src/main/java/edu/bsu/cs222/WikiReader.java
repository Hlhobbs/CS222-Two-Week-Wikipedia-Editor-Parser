package edu.bsu.cs222;

import java.net.*;
import java.io.*;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;
//import java.util.Scanner;


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
        /*
        Scanner scan = new Scanner(System.in);
        System.out.println("What do you want to search? ");
        String search = scan.nextLine();
        */
        InternetConnectionTest();

        URL wikiurl = new URL("http://en.wikipedia.org");
        URLConnection connection = wikiurl.openConnection();
        connection.setRequestProperty("User-Agent","Revision Tracker/0.1 (me@bsu.edu)");
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        String inputLine;
        while ((inputLine = in.readLine()) != null)
            System.out.println(inputLine);
        in.close();

    }
}

package edu.bsu.cs222;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;


@SuppressWarnings("ALL")
public class WikiReader {

    protected static void InternetConnectionTest() throws UnknownHostException, IOException
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


}
import java.net.*;
import java.io.*;
//import java.util.Scanner;


public class WikiReader {

    public static void main(String[] args) throws Exception
    {
        /*
        Scanner scan = new Scanner(System.in);
        System.out.println("What do you want to search? ");
        String search = scan.nextLine();
        */

        URL wikiurl = new URL("http://en.wikipedia.org");
        URLConnection connection = wikiurl.openConnection();
        connection.setRequestProperty("User-Agent","Revision Tracker/0.1 (mktaylor3@bsu.edu)");
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        String inputLine;
        while ((inputLine = in.readLine()) != null)
            System.out.println(inputLine);
        in.close();

    }


}

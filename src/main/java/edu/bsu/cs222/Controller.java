package edu.bsu.cs222;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import jdk.internal.util.xml.impl.Input;
import java.net.*;
import java.io.*;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


public class Controller {

    @FXML
    public TextArea ResultsField;
    @FXML
    private TextField SearchTerm;
    @FXML
    private Button RecentButton;
    @FXML
    private Button EditsButton;

    //private String WikiURL = "https://en.wikipedia.org/w/api.php?action=query&format=json&prop=revisions&titles=" + SearchTerm.getText() + "&r%20vprop=timestamp|user&rvlimit=30&redirects";
    private final Executor executor = Executors.newSingleThreadExecutor();

    @SuppressWarnings("unused")
    @FXML
    public void WikiSearchByRecentEdits(final ActionEvent actionEvent) {
        RecentButton.setDisable(true);
        EditsButton.setDisable(true);
      if(SearchTerm.getText().contains(" ")){

        }
        try {
            final URL url = new URL("https://en.wikipedia.org/w/api.php?action=query&format=json&prop=revisions&titles=" + SearchTerm.getText() + "&r%20vprop=timestamp|user&rvlimit=30&redirects");
            executor.execute(new Runnable() {
                public void run() {
                    try{
                        URLConnection connection = url.openConnection();
                        connection.setRequestProperty("User-Agent","Revision Tracker/0.1 (me@bsu.edu)");
                        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                        RevisionParser read = new RevisionParser();
                        InputStream input = connection.getInputStream();
                        read.parse(input);
                        System.out.println(read.parse(input));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            });

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        RecentButton.setDisable(false);
        EditsButton.setDisable(false);
    }

    @SuppressWarnings("unused")
    @FXML
    public void WikiSearchByMostEdits(final ActionEvent actionEvent) {
        RecentButton.setDisable(true);
        EditsButton.setDisable(true);
       if(SearchTerm.getText().contains(" ")){

        }
        try {
            final URL url = new URL("https://en.wikipedia.org/w/api.php?action=query&format=json&prop=revisions&titles=" + SearchTerm.getText() + "&r%20vprop=timestamp|user&rvlimit=30&redirects");
            executor.execute(new Runnable() {
                public void run() {
                    try{
                        URLConnection connection = url.openConnection();
                        connection.setRequestProperty("User-Agent","Revision Tracker/0.1 (me@bsu.edu)");
                        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                        RevisionParser read = new RevisionParser();
                        InputStream input = connection.getInputStream();
                        read.parse(input);
                        System.out.println(read.parse(input));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            });

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        RecentButton.setDisable(false);
        EditsButton.setDisable(false);
    }
}

package edu.bsu.cs222;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.net.*;
import java.io.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


public class Controller {

    @FXML
    private TextField SearchTerm;
    @FXML
    private RadioButton RecentButton;
    @FXML
    private RadioButton EditsButton;
    @FXML
    private TextArea ResultsField;
    @FXML
    private Button SearchButton;

    private List<Revisions> MostRecentEdits = new ArrayList<Revisions>();
    private List<Revisions> MostEditsMade = new ArrayList<Revisions>();
    private ToggleGroup SearchParameters = new ToggleGroup();
    private final Executor executor = Executors.newSingleThreadExecutor();

    @SuppressWarnings("unused")
    @FXML
    public void WikiSearch() throws IOException {
        EditsButton.setToggleGroup(SearchParameters);
        RecentButton.setToggleGroup(SearchParameters);
        ResultsField.setEditable(false);
        SearchButton.setDisable(true);
        WikiConnect();
        RadioButtonSelection();
        SearchButton.setDisable(false);
    }

    @FXML
    public void RadioButtonSelection(){

        StringBuilder text = new StringBuilder();

        if (RecentButton.isSelected()) {
            for (Revisions MostRecentEdit : MostRecentEdits) {
                text.append(MostRecentEdit.getId()).append("\n").append(MostRecentEdit.getTimestamp()).append("\n").append("\n");
            }
        }

        else if (EditsButton.isSelected()) {

            for (Revisions aMostEditsMade : MostEditsMade) {
                text.append(aMostEditsMade.getId()).append("\n").append(aMostEditsMade.getTimestamp()).append("\n").append("Times edited: ").append(aMostEditsMade.getCount()).append("\n").append("\n");
            }
        }
        ResultsField.setText(text.toString());
        MostRecentEdits.clear();
        MostEditsMade.clear();
    }

    private void WikiConnect() throws IOException, NullPointerException{
        try {
            WikiReader.InternetConnectionTest();
            @SuppressWarnings("deprecation") final URL WikiURL = new URL("https://en.wikipedia.org/w/api.php?action=query&format=json&prop=revisions&titles=" + URLEncoder.encode(SearchTerm.getText() ) + "&r%20vprop=timestamp|user&rvlimit=30&redirects");
            executor.execute(new Runnable() {
                public void run() {
                    try{
                        URLConnection connection = WikiURL.openConnection();
                        connection.setRequestProperty("User-Agent","Revision Tracker/0.1 (me@bsu.edu)");
                        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                        RevisionParser read = new RevisionParser();
                        InputStream input = connection.getInputStream();
                        if(EditsButton.isSelected()) {
                            EditsButton.setSelected(true);
                            MostEditsMade = read.parseByMostEdits(input);
                        }
                        else if(RecentButton.isSelected()){
                            RecentButton.setSelected(true);
                            MostRecentEdits = read.parseByRecentEdits(input);
                        }
                        in.close();
                    } catch (IOException e) {
                        throw new RuntimeException();
                    }
                }
            });
        } catch (MalformedURLException text) {
            throw new MalformedURLException("This is not acceptable URL formatting!");
        }

    }

}

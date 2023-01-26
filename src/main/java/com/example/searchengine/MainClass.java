package com.example.searchengine;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class MainClass extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainClass.class.getResource("search-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Searh Engine!");
        stage.setScene(scene);
        stage.show();
    }

    static DatabaseConnection connect;
    @FXML
    TextField text_field;
    @FXML
    Button search_button;
    @FXML
    Button search_history;
    @FXML
    TextArea text_area;

//    static Connection connection;
//    static String URL = "jdbc:mysql://localhost:3306/search_engine?useSSL=false";
//    static String username = "root";
//    static String password = ".Hskpsql02.";

    public static void main(String[] args) throws SQLException {

        connect = new DatabaseConnection();                                                 // Create an object of DatabaseConnection class
//        ResultSet iterator = connect.executeQuery("select * from search_history");          // use a ResultSet, from the object execute query
//
//        while (iterator.next()) {
//            String name = iterator.getString(2);
//        }

        launch();
    }
//  Button on click Function to get the Text from the TextField and insert into history table
    public void searchButton(ActionEvent actionEvent) throws SQLException {
        String keyword = text_field.getText();
        Timestamp date_time = new Timestamp(System.currentTimeMillis());
        int response = connect.executeUpdate("Insert into history values ('" + keyword + "', '" + date_time + "')");
        System.out.println(response);
        assert(response == 1);

//        Implementing Search button and displaying the top link with the keyword
        String query = "select link, linkname, time_stamp, " +
                "(length(link) - length(Replace(link, '"+ keyword +"', '')))/length('" + keyword + "') as countofkeyword from search_history order by countofkeyword desc limit 10;";
        ResultSet iterator = connect.executeQuery(query);

        ArrayList<SearchResult> searchResults = new ArrayList<>();

        while (iterator.next()) {
            String link = iterator.getString("link");
            String link_name = iterator.getString("linkname");
            Timestamp time = iterator.getTimestamp("time_stamp");
            int count = iterator.getInt("countofkeyword");

            System.out.println(link + " === " + link_name + " === " + count);
            searchResults.add(new SearchResult(link, link_name, time));
        }

        StringBuilder st = new StringBuilder();
        String space = "               ";
        String newLine = "\n";
        st.append("Link Name").append(space).append("Link").append(newLine).append(newLine);

        for (SearchResult s : searchResults) {
            st.append(s.getLinkname()).append(space).append(s.getLink()).append(newLine);
        }

        text_area.setText(st.toString());

    }
//  Button on click Function to display search history from the history table
    public void searchHistory(ActionEvent actionEvent) throws SQLException {
        ResultSet iterator = connect.executeQuery("Select * from history order by time_stamp desc limit 10");

        ArrayList<HistoryResult> historyResults = new ArrayList<>();
        while (iterator.next()) {
            String search = iterator.getString(1);
            String time_stamp = iterator.getString(2);
            System.out.println(search + " === " + time_stamp);
            historyResults.add(new HistoryResult(search, time_stamp));
        }
        System.out.println(historyResults);

        StringBuilder show = new StringBuilder();
        String space = "               ";
        String nextLine = "\n";
        show.append("keyword").append(space).append("Time").append(nextLine).append(nextLine);

        for (HistoryResult current: historyResults) {
            show.append(current.getKeyword()).append(space).append(current.getTimestamp()).append(nextLine);
        }
        text_area.setText(show.toString());
    }
}
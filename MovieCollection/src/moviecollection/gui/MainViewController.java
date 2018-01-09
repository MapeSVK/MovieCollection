/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moviecollection.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import moviecollection.be.Movie;

/**
 * FXML Controller class
 *
 * @author Pepe15224
 */
public class MainViewController implements Initializable {

    @FXML
    private TableView<?> categoryMoviesTableView;
    @FXML
    private TableView<?> allMoviesTableView;
    @FXML
    private ListView<?> categoryListView;
    @FXML
    private TextField searchTextField;
    @FXML
    private Button searchButton;
    @FXML
    private Button addCategoryButton;
    @FXML
    private Button deleteCategoryButton;
    @FXML
    private Button addMovieButton;
    @FXML
    private Button editMovieButton;
    @FXML
    private Button deleteMovieButton;
    
    
    private moviecollectionModel model;
    private Movie movie;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
    
    private void loadData() {
        model.loadDataFromDB();
    }

    @FXML
    private void addNewCategoryClick(ActionEvent event) {
    }

    @FXML
    private void deleteCategoryClick(ActionEvent event) {
    }

    @FXML
    private void addMovieClick(ActionEvent event) throws IOException {
       
           
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/moviecollection/gui/NewMovie.fxml"));

            Parent root1 = (Parent) fxmlLoader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.setTitle("Add/Edit song");
            stage.show();
        
        
    }

    @FXML
    private void editMovieClick(ActionEvent event) {
    }

    @FXML
    private void deleteMovieClick(ActionEvent event) {
    }
    
}

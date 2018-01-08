/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moviecollection;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Pepe15224
 */
public class MainView implements Initializable {

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void addNewCategoryClick(ActionEvent event) {
    }

    @FXML
    private void deleteCategoryClick(ActionEvent event) {
    }

    @FXML
    private void addMovieClick(ActionEvent event) {
    }

    @FXML
    private void editMovieClick(ActionEvent event) {
    }

    @FXML
    private void deleteMovieClick(ActionEvent event) {
    }
    
}

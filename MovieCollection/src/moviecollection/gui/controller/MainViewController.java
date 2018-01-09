/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moviecollection.gui.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

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
    private Button editMovieButton;
    @FXML
    private Button deleteMovieButton;
    @FXML
    private VBox vBox;
    @FXML
    private Label addM;
    @FXML
    private AnchorPane mainWindow;

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
    private void editMovieClick(ActionEvent event) {
    }

    @FXML
    private void deleteMovieClick(ActionEvent event) {
    }

    @FXML
    private void movieTableClick(MouseEvent event) {
        if(event.getButton()==MouseButton.SECONDARY)
        {
           // allMoviesTableView.getLayoutX();
           // allMoviesTableView.getLayoutY();
            vBox.setLayoutX(allMoviesTableView.getLayoutX()+event.getX());
            vBox.setLayoutY(allMoviesTableView.getLayoutY()+event.getY());
            vBox.setVisible(true);
            vBox.setDisable(false);
        }
        else if(event.getButton()==MouseButton.PRIMARY)
        {
          vBox.setVisible(false);
          vBox.setDisable(true);  
        }
        else
        {}

    }

    @FXML
    private void exitAddM(MouseEvent event) {
        addM.setStyle("-fx-background-color: 0");
    }

    @FXML
    private void enterAddM(MouseEvent event) {
        addM.setStyle("-fx-background-color: #66c3ff");
    }

    @FXML
    private void vBoxClicked(MouseEvent event) {
    }

    @FXML
    private void myWindowClick(MouseEvent event) {
    }
    
}

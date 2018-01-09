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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Mape
 */
public class NewMovieController implements Initializable {

    @FXML
    private ComboBox<?> firstCategoryComboBox;
    @FXML
    private ComboBox<?> secondCategoryComboBox;
    @FXML
    private ComboBox<?> thirdCategoryComboBox;
    @FXML
    private TextField TitleTextField;
    @FXML
    private TextField personalRatingComboBox;
    @FXML
    private TextField imdbRatingComboBox;
    @FXML
    private TextField filePathComboBox;
    @FXML
    private Button chooseFileButton;
    @FXML
    private Button saveNewMovieButton;
    @FXML
    private Button closeNewMovieButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void chooseFileButtonClick(ActionEvent event) {
    }

    @FXML
    private void saveNewMovieButtonClick(ActionEvent event) {
    }

    @FXML
    private void closeNewMovieButtonClick(ActionEvent event) {
    }
    
}

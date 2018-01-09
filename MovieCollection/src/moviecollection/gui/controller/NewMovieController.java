/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moviecollection.gui.controller;

<<<<<<< HEAD:MovieCollection/src/moviecollection/gui/NewMovieController.java
import java.net.URI;
=======
import java.io.File;
>>>>>>> e2eeeb98f91f716a88cbd06a12632e827ac8a796:MovieCollection/src/moviecollection/gui/controller/NewMovieController.java
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.TextField;
<<<<<<< HEAD:MovieCollection/src/moviecollection/gui/NewMovieController.java
import javafx.scene.media.Media;
import javafx.stage.FileChooser;
import moviecollection.be.Movie;
=======
import javafx.stage.FileChooser;
>>>>>>> e2eeeb98f91f716a88cbd06a12632e827ac8a796:MovieCollection/src/moviecollection/gui/controller/NewMovieController.java

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
<<<<<<< HEAD:MovieCollection/src/moviecollection/gui/NewMovieController.java
    
    
   private Movie movie;
   private moviecollectionModel model;
=======
>>>>>>> e2eeeb98f91f716a88cbd06a12632e827ac8a796:MovieCollection/src/moviecollection/gui/controller/NewMovieController.java

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void chooseFileButtonClick(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter filtermP4 = new FileChooser.ExtensionFilter("select MP4","*MP4");
        fileChooser.getExtensionFilters().add(filtermP4);
        File file = fileChooser.showOpenDialog(null);
        String filePath = file.toString();
        
        filePathComboBox.setText(filePath);
    }

    @FXML
    private void saveNewMovieButtonClick(ActionEvent event) {
    }

    @FXML
    private void closeNewMovieButtonClick(ActionEvent event) {
    }

    @FXML
    private void saveNewMovieButtonClick(ActionEvent event) {
    }

    @FXML
    private void closeNewMovieButtonClick(ActionEvent event) {
    }
    
    
    
}

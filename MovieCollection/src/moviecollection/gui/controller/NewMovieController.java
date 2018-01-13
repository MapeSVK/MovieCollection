/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moviecollection.gui.controller;

import java.io.File;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import moviecollection.be.Category;
import moviecollection.be.Movie;
import moviecollection.gui.model.MovieModel;



/**
 * FXML Controller class
 *
 * @author Mape
 */
public class NewMovieController implements Initializable {

    @FXML
    private Button SaveButton;
    @FXML
    private Button CloseButton;
    @FXML
    private TextField TitleTextField;
    @FXML
    private TextField PRatingTextField;
    @FXML
    private TextField IMDBRatingTextField;
    @FXML
    private TextField FileTextField;
    @FXML
    private Button ChooseButton;
    private MovieModel model;
    private Movie selectedMovie;
    @FXML
    private ComboBox<Category> firstCat;
    @FXML
    private ComboBox<Category> secCat;
    @FXML
    private ComboBox<Category> thirdCat;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
    public void setModelAndMovie(MovieModel model, Movie selectedMovie) {
        this.model=model;
        this.selectedMovie=selectedMovie;
        firstCat.setItems(model.getAllCategories());
        firstCat.getItems().add(new Category(-1, "none"));
        secCat.setItems(model.getAllCategories());
        thirdCat.setItems(model.getAllCategories());
        fill();
    }
 
    @FXML
    private void ChooseButtonClick(ActionEvent event) {
 
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter filtermP4 = new FileChooser.ExtensionFilter("select mp4","*.mp4");
        fileChooser.getExtensionFilters().add(filtermP4);
        File file = fileChooser.showOpenDialog(null);
        String filePath = file.toString();
        
        FileTextField.setText(filePath); //insert path of the file into the textField        
     }
    
    
    
    /************* SAVE AND CLOSE METHODS *************/
    
    @FXML
    private void SaveButtonClick(ActionEvent event) {
        if (firstCat.getValue() != secCat.getValue() && 
                secCat.getValue() != thirdCat.getValue() &&
                thirdCat.getValue() != firstCat.getValue()) {
                Save();
                
            
        }
        else {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("CATEGORY PROBLEM");
            alert.setContentText("You cannot add movie to two same categories");
            alert.showAndWait();
        }
    }
    
    private void Save(){
        if(selectedMovie==null)
        {
            model.addMovie(new Movie(-1,
            TitleTextField.getText(),
            Double.valueOf(PRatingTextField.getText()),
            Double.valueOf(IMDBRatingTextField.getText()),
            FileTextField.getText(),
            null));       
        }
        else if(selectedMovie!=null)
        {
            selectedMovie.setName(TitleTextField.getText());
            selectedMovie.setRating(Double.valueOf(IMDBRatingTextField.getText()));
            selectedMovie.setPersonalrating(Double.valueOf(PRatingTextField.getText()));
            selectedMovie.setFilelink(FileTextField.getText());
            model.editMovies(selectedMovie);
        }
        closeWindow();
    }
 
    /*********** OTHER METHODS *************/
    private void closeWindow()
    {
        Stage stage = (Stage) CloseButton.getScene().getWindow();
        stage.close();
    }
    @FXML
    private void CloseButtonClick(ActionEvent event) {
        closeWindow();
    }
    private void fill()
    {
        if(selectedMovie!=null)
        {
            TitleTextField.setText(selectedMovie.getName());
            PRatingTextField.setText(""+selectedMovie.getPersonalrating());
            IMDBRatingTextField.setText(""+selectedMovie.getRating());
            FileTextField.setText(selectedMovie.getFilelink());       
        }
    }

    


}

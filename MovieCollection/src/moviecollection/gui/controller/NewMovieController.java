/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moviecollection.gui.controller;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import moviecollection.be.Category;
import moviecollection.be.Movie;
import moviecollection.be.MovieInCategory;
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
    private MovieInCategory selectedMovieinC;
    @FXML
    private ComboBox<Category> firstCat;
    @FXML
    private ComboBox<Category> secCat;
    @FXML
    private ComboBox<Category> thirdCat;
    private Category none = new Category(-1, "None");
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
    public void setModelAndMovie(MovieModel model, MovieInCategory selectedMovieinC) {
        this.model=model;
        this.selectedMovieinC=selectedMovieinC;
        fillCombo();
        fill();
        
    }
 public void setModelAndMovie(MovieModel model)
 {
     this.model=model;
     this.selectedMovieinC=null;
     fillCombo();
     
 }
    @FXML
    private void ChooseButtonClick(ActionEvent event) {
 
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter filtermP4 = new FileChooser.ExtensionFilter("select mp4","*.mp4");
        FileChooser.ExtensionFilter filterMpeg4 = new FileChooser.ExtensionFilter("select mpeg4","*.mpeg4");
        fileChooser.getExtensionFilters().addAll(filtermP4,filterMpeg4);
        File file = fileChooser.showOpenDialog(null);
        String filePath = file.toString();
        
        FileTextField.setText(filePath); //insert path of the file into the textField        
     }
    
    
    
    /************* SAVE AND CLOSE METHODS *************/
    
    @FXML
    private void SaveButtonClick(ActionEvent event) {

        boolean exist=false;
        boolean isFilled = false;
        
        model.loadAllMovies();
        for(Movie movie : model.getAllMovies()){
           
               if(movie.getName().equals(TitleTextField.getText()))
               {
                Alert("Invalid name","This movie already exists!");   
                exist=true;
               }
           }
        if (!TitleTextField.getText().isEmpty() && 
            !PRatingTextField.getText().isEmpty() && 
            !IMDBRatingTextField.getText().isEmpty() &&
            !FileTextField.getText().isEmpty()){
            isFilled = true;
        }
        else {
            Alert("Invalid fields","Fields must be filled!");   
        }
        
        if(exist==false && isFilled ==true)
        {
           Save();
        }
        


    }
    
    private void Save(){
        if(selectedMovieinC==null)
        {
            
            if (firstCat.getValue() != secCat.getValue() && 
            secCat.getValue() != thirdCat.getValue() &&
            thirdCat.getValue() != firstCat.getValue() 
            || firstCat.getValue() == none && secCat.getValue()== none && thirdCat.getValue() != none
            || secCat.getValue() == none && thirdCat.getValue()== none && firstCat.getValue() != none
            || thirdCat.getValue() == none && firstCat.getValue() == none && secCat.getValue() != none) {
                Movie myMovie = new Movie(-1,
            TitleTextField.getText(),
            Double.valueOf(PRatingTextField.getText()),
            Double.valueOf(IMDBRatingTextField.getText()),
            FileTextField.getText(),
            null);
            model.addMovie(myMovie); 
            addMovieToCat(myMovie);
            }
             else {
            Alert("CATEGORY PROBLEM","You cannot add movie to categories");
            }
   
        }
        else if(selectedMovieinC!=null)
        {
            selectedMovieinC.setName(TitleTextField.getText());
            selectedMovieinC.setRating(Double.valueOf(IMDBRatingTextField.getText()));
            selectedMovieinC.setPersonalrating(Double.valueOf(PRatingTextField.getText()));
            selectedMovieinC.setFilelink(FileTextField.getText());
            model.editMovies(selectedMovieinC);
        }
        closeWindow();
    }
 private void addMovieToCat(Movie movie)
 {
     if(firstCat.getValue()!=none )
     model.addMovieToCategory(firstCat.getValue(), movie);
     if(secCat.getValue()!=none )
     model.addMovieToCategory(secCat.getValue(), movie);
     if(thirdCat.getValue()!=none )
     model.addMovieToCategory(thirdCat.getValue(), movie);
     
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
        
        
            TitleTextField.setText(selectedMovieinC.getName());
            PRatingTextField.setText(""+selectedMovieinC.getPersonalrating());
            IMDBRatingTextField.setText(""+selectedMovieinC.getRating());
            FileTextField.setText(selectedMovieinC.getFilelink());       
        
    }
private void fillCombo()
{
    firstCat.getItems().add(none);
        firstCat.getItems().addAll(model.getAllCategories());
        secCat.getItems().add(none);
        secCat.getItems().addAll(model.getAllCategories());
        thirdCat.getItems().add(none);
        thirdCat.getItems().addAll(model.getAllCategories());
        firstCat.getSelectionModel().selectFirst();
        secCat.getSelectionModel().selectFirst();
        thirdCat.getSelectionModel().selectFirst();
}
private void Alert(String title,String text)
{
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(title);
            alert.setContentText(text);
            alert.showAndWait();
}
}

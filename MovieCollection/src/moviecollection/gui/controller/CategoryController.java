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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import moviecollection.be.Category;
import moviecollection.gui.model.MovieModel;

/**
 * FXML Controller class
 *
 * @author 1
 */
public class CategoryController implements Initializable {

    @FXML
    private ComboBox<String> categoryBox;
    
    private MovieModel model;
    @FXML
    private Button closeButton;  
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
    categoryBox.getItems().addAll("Action", "Adventure", "Animation", "Biography", "Comedy", "Crime", "Documentary", "Drama", "Family", "Fantasy", "Film-Noir", "Game-Show", "History", "Horror", "Music", "Musical", "Mystery", "News", "Reality-TV", "Romance", "Sci-Fi", "Sport", "Talk-Show", "Thriller", "War", "Western");
    
    }    
   
    public void setModel(MovieModel model) 
    {
        this.model=model;
    }
   
    @FXML
    private void addCategory(ActionEvent event) {
        boolean exist=false;
        System.out.println(""+categoryBox.getValue());
        if(categoryBox.getValue()==null)
        {
            Alert("Category problem","You need to choose category");
        }
        else
       {
           for(Category cat : model.getAllCategories())
           {
               cat.getName();
               if(cat.getName().equals(categoryBox.getValue()))
               {
                Alert("Category problem","This category exists");   
                exist=true;
               }
           }
            if(exist==false)
                   {
           model.addCategory(new Category(-1, categoryBox.getValue()));
           closeWindow();
                   }
        } 
    }

    @FXML
    private void closeButtonClick(ActionEvent event) {
        closeWindow();
    }
    
    private void closeWindow()
    {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
 
    private void Alert(String title,String text)
{
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(title);
            alert.setContentText(text);
            alert.showAndWait();
}
}

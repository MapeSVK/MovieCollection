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
    @FXML
    private Button addC;
    private MovieModel model = new MovieModel();

    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
    categoryBox.getItems().addAll("Action", "Adventure", "Animation", "Biography", "Comedy", "Crime", "Documentary", "Drama", "Family", "Fantasy", "Film-Noir", "Game-Show", "History", "Horror", "Music", "Musical", "Mystery", "News", "Reality-TV", "Romance", "Sci-Fi", "Sport", "Talk-Show", "Thriller", "War", "Western");
    
    }    

    @FXML
    private void addCategory(ActionEvent event) {
        model.addCategory(new Category(-1, categoryBox.getValue()));
    }
    
}

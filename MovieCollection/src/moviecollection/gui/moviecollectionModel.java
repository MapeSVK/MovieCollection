/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moviecollection.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import moviecollection.be.Category;
import moviecollection.be.Movie;
import moviecollection.bll.BLLManager;

/**
 *
 * @author Mape
 */
public class moviecollectionModel {
    
    private ObservableList<Movie> allMovies = FXCollections.observableArrayList();  
    private ObservableList<Movie> filteredMovies = FXCollections.observableArrayList();   //Contains all filtered movies
    private ObservableList<Category> categories = FXCollections.observableArrayList();
    
    private final BLLManager bllManager = new BLLManager();
    
    public void loadDataFromDB(){
        allMovies.addAll(bllManager.loadMovies());       //Load the movies
        categories.addAll(bllManager.loadCategories());   //Load the categories
        
    }
    
    
}

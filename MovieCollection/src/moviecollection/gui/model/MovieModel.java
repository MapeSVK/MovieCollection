/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moviecollection.gui.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import moviecollection.be.Category;
import moviecollection.be.Movie;
import moviecollection.bll.BllManager;

/**
 *
 * @author Pepe15224
 */
public class MovieModel {
    
    BllManager manager = new BllManager();
    
    private ObservableList<Movie> allMovies = FXCollections.observableArrayList();
    private ObservableList<Category> allCategories = FXCollections.observableArrayList();

    
    public void loadAllMovies()
    {
        allMovies.clear();
        allMovies.addAll(manager.getAllMovies());
    }
    
    public ObservableList<Movie> getAllMovies()
    {
        return allMovies;
    }
    
    
    
    public void loadAllCategories(){
        allCategories.clear();
        allCategories.addAll(manager.getAllCategories());
    }
    
    public ObservableList<Category> getAllCategories() {
        return allCategories;
    }

    
     public void addCategory(Category category)
   {
       manager.addCategory(category);
   }
        public void addMovie(Movie movie)
{
    allMovies.add(movie);
    manager.addNewMovie(movie);
    
}
        public void deleteMovie(Movie movie)
{
    allMovies.remove(movie);
    manager.deleteMovies(movie);
}
  public void deleteCategory(Category selectedCategory) 
  {
      allCategories.remove(selectedCategory);
      manager.deleteCategory(selectedCategory);
  }
    public void updateDate(Movie movie)
    {
        manager.updateDate(movie);
        loadAllMovies();
    }
}

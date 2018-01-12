/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moviecollection.bll;

import java.util.List;
import moviecollection.be.Category;
import moviecollection.be.Movie;
import moviecollection.dal.ConnectionModel;


/**
 *
 * @author Pepe15224
 */
public class BllManager {
    
   ConnectionModel connectionModel = new ConnectionModel(); 
  
  public void addNewMovie(Movie movie)
  {
      connectionModel.addMovie(movie);
   }
   
   public List<Movie> getAllMovies()
   {
       return connectionModel.getAllMovies();
   }
   
    public void addCategory(Category category)
   {
       connectionModel.addCategory(category);
   }
       public void deleteMovies(Movie selectedMovie)
   {
       connectionModel.deleteMovies(selectedMovie);
   }
       
   public List<Category> getAllCategories() {
       return connectionModel.getAllCategories();
   }
   public void deleteCategory(Category selectedCategory) 
   {
     connectionModel.deleteCategory(selectedCategory);
   }
 
   public void addMovieToCategory(Category category, Movie movie) {
      connectionModel.addMovieToCategory(category, movie);
    }

   public void updateDate(Movie movie)
   {
       connectionModel.updateDate(movie);
   }
   public void editMovies(Movie movie)
   {
       connectionModel.editMovies(movie);
   }
}

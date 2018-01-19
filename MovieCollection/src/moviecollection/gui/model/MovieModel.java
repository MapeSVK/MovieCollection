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
import moviecollection.be.MovieInCategory;
import moviecollection.bll.BllManager;

/**
 *
 * @author Pepe15224
 */
public class MovieModel {
    
    BllManager manager = new BllManager();
    
    private ObservableList<Movie> allMovies = FXCollections.observableArrayList();
    private ObservableList<Category> allCategories = FXCollections.observableArrayList();
    private final ObservableList<MovieInCategory> moviesInC = FXCollections.observableArrayList();
  // Adds all movies from the Database to allMovies list
    public void loadAllMovies()
    {
        allMovies.clear();
        allMovies.addAll(manager.getAllMovies());
    }
   // Returns allMovies
    public ObservableList<Movie> getAllMovies()
    {
        return allMovies;
    }
   // Adds all categories from the Database to allCategories list
    public void loadAllCategories()
    {
        allCategories.clear();
        allCategories.addAll(manager.getAllCategories());
    }
   //Returns allCategories
    public ObservableList<Category> getAllCategories()
    {
        return allCategories;
    }
   // Adds Category to allCategory list and Database
    public void addCategory(Category category)
    {
        manager.addCategory(category);
        allCategories.add(category);
    }
   // Adds Movie to allMovies list and Database
    public void addMovie(Movie movie)
    {
        allMovies.add(movie);
        manager.addNewMovie(movie);
    }
   // Deletes Category from list and Database
    public void deleteCategory(Category selectedCategory) 
    {
        allCategories.remove(selectedCategory);
        manager.deleteCategory(selectedCategory);
        moviesInC.clear();
    }
   // Adds CatMovie to Database and to CatMovies list 
    public void addMovieToCategory(Category category, Movie movie) {
        manager.addMovieToCategory(category, movie);  
        moviesInC.add(new MovieInCategory(category.getId(), movie.getId(), -1));
        getMoviesById(category.getId());
    }
   // Updates Movie inside Database
    public void editMovies(MovieInCategory movieinC)
    {
        manager.editMovies(movieinC);
        moviesInC.setAll(getNameEtc());
    }
   // Updates Category inside Database
    public void editDate(MovieInCategory movieinC)
    {
        manager.editDate(movieinC);
        moviesInC.setAll(getNameEtc());
    }
   // Fills CatMovie list by the CategoryId
    public ObservableList<MovieInCategory> getMoviesById(int id)
    {
        moviesInC.setAll(manager.getMoviesById(id));   
        moviesInC.setAll(getNameEtc());
        return moviesInC; 
    }
   // Fills CatMovie list by the multiple CategoryId
    public ObservableList<MovieInCategory> getMultipleMoviesById(ObservableList<Category> multipleId)
    {
        moviesInC.setAll(manager.getMoviesById(-1));
        for(Category categoryId : multipleId)
    {
        moviesInC.addAll(manager.getMoviesById(categoryId.getId()));
    }
        moviesInC.setAll(getNameEtc());
        return moviesInC; 
    }
   // (Overloaded to avoid using NULL) Returns CatMovies that contains query
    public ObservableList<MovieInCategory> getTest(String part)
    {
        ObservableList<MovieInCategory> IlikeToSing = FXCollections.observableArrayList();
         
            for(MovieInCategory catMovie : moviesInC)
            {
               catMovie.getName();
               if(catMovie.getName().contains(part.toLowerCase()) || catMovie.getName().contains(part.toUpperCase()))
               {                  
                   IlikeToSing.add(catMovie);     
               }
            }  
        return IlikeToSing;
    }
   // Returns CatMovies that contains query
    public ObservableList<MovieInCategory> getTest(String part,Double score)
    {
        ObservableList<MovieInCategory> IlikeToDance = FXCollections.observableArrayList();
         
            for(MovieInCategory catMovie : moviesInC)
            {
               catMovie.getName();
               if(catMovie.getName().contains(part.toLowerCase()) && catMovie.getRating()>=score || catMovie.getName().contains(part.toUpperCase()) && catMovie.getRating()>=score)
               {                  
                   IlikeToDance.add(catMovie);                
               }
            }               
        return IlikeToDance;
    }
   // Gives CatMovie name, score.. etc from Movie
    public ObservableList<MovieInCategory> getNameEtc()
    {
        loadAllMovies();
        ObservableList<MovieInCategory> IlikeToSing = FXCollections.observableArrayList();
        
            for(Movie movie : allMovies)
            {
            
              for(MovieInCategory catMovie : moviesInC)
              {
                movie.getId();
                catMovie.getMovieId();
                if(movie.getId()==catMovie.getMovieId())
                {
                   catMovie.setName(movie.getName());
                   catMovie.setRating(movie.getRating());
                   catMovie.setPersonalrating(movie.getPersonalrating());
                   catMovie.setFilelink(movie.getFilelink());
                   catMovie.setLastview(movie.getLastview());
                   IlikeToSing.add(catMovie);    
                }
              }
            }
        return IlikeToSing;
    }
  // 
    public void deleteMovie(MovieInCategory movieinC)
    {
    manager.deleteMovie(movieinC);
    moviesInC.setAll(getNameEtc());
    }
}

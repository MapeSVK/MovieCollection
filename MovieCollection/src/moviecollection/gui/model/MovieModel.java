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
  
    public void loadAllMovies()
    {
        allMovies.clear();
        allMovies.addAll(manager.getAllMovies());
    }
    
    public ObservableList<Movie> getAllMovies()
    {
        return allMovies;
    }

    public void loadAllCategories()
    {
        allCategories.clear();
        allCategories.addAll(manager.getAllCategories());
    }
    
    public ObservableList<Category> getAllCategories()
    {
        return allCategories;
    }

    public void addCategory(Category category)
    {
        manager.addCategory(category);
        allCategories.add(category);
    }
   
    public void addMovie(Movie movie)
    {
        allMovies.add(movie);
        manager.addNewMovie(movie);
    }
   
    public void deleteCategory(Category selectedCategory) 
    {
        allCategories.remove(selectedCategory);
        manager.deleteCategory(selectedCategory);
        moviesInC.clear();
    }
    
    public void addMovieToCategory(Category category, Movie movie) {
        manager.addMovieToCategory(category, movie);  
        moviesInC.add(new MovieInCategory(category.getId(), movie.getId(), -1));
        getMoviesById(category.getId());
    }
   
    public void editMovies(MovieInCategory movieinC)
    {
        manager.editMovies(movieinC);
        moviesInC.setAll(getNameEtc());
    }
   
    public void editDate(MovieInCategory movieinC)
    {
        manager.editDate(movieinC);
        moviesInC.setAll(getNameEtc());
    }

    public ObservableList<MovieInCategory> getMoviesById(int id)
    {
        moviesInC.setAll(manager.getMoviesById(id));   
        moviesInC.setAll(getNameEtc());
        return moviesInC; 
    }

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

    public ObservableList<MovieInCategory> getTest(String part)
    {
        ObservableList<MovieInCategory> IlikeToSing = FXCollections.observableArrayList();
         
            for(MovieInCategory catMovie : moviesInC)
            {
               catMovie.getName();
               if(catMovie.getName().contains(part))
               {                  
                   IlikeToSing.add(catMovie);     
               }
            }  
        return IlikeToSing;
    }
  
    public ObservableList<MovieInCategory> getTest(String part,Double score)
    {
        ObservableList<MovieInCategory> IlikeToDance = FXCollections.observableArrayList();
         
            for(MovieInCategory catMovie : moviesInC)
            {
               catMovie.getName();
               if(catMovie.getName().contains(part) && catMovie.getRating()>=score)
               {                  
                   IlikeToDance.add(catMovie);                
               }
            }               
        return IlikeToDance;
    }

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

    public void deleteMovie(MovieInCategory movieinC)
    {
    manager.deleteMovie(movieinC);
    moviesInC.setAll(getNameEtc());
    }
}

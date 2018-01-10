/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moviecollection.gui.model;

<<<<<<< HEAD
=======
import java.sql.SQLException;
>>>>>>> 5a4299dcd0225f45e7ae6225c9b4a500bffc451b
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import moviecollection.be.Movie;
import moviecollection.bll.BllManager;

/**
 *
 * @author Pepe15224
 */
public class MovieModel {
    BllManager manager = new BllManager();
    private ObservableList<Movie> allMovies = FXCollections.observableArrayList();
    
<<<<<<< HEAD

    
    
    public void addNewMovie(Movie movie)  {
        
            manager.addNewMovie(movie);
            allMovies.add(movie);
            
            
}
=======
    private ObservableList<Movie> allMovies = FXCollections.observableArrayList();
    
    public void addMovie(Movie movie)
    {
        manager.addMovie(movie);
    }
    public void loadAllMovies()
    {
        allMovies.clear();
        allMovies.addAll(manager.getAllMovies());
    }
 
    public ObservableList<Movie> getAllSongs()
    {
        return allMovies;
    }
>>>>>>> 5a4299dcd0225f45e7ae6225c9b4a500bffc451b
}

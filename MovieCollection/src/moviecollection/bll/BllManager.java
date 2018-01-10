/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moviecollection.bll;

import java.util.List;
import moviecollection.be.Movie;
import moviecollection.dal.ConnectionModel;

/**
 *
 * @author Pepe15224
 */
public class BllManager {
   ConnectionModel model = new ConnectionModel(); 
   
   public void addMovie(Movie movie)
   {
       model.addMovie(movie);
   }
   
   public List<Movie> getAllMovies()
   {
       return model.getAllMovies();
   }
}

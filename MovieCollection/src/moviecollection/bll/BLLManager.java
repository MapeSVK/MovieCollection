/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moviecollection.bll;

import java.util.List;
import moviecollection.be.Category;
import moviecollection.be.Movie;
import moviecollection.dal.DALManager;

/**
 *
 * @author Pepe15224
 */
public class BLLManager {
    DALManager manager = new DALManager();
    
    
    public void addMovie(Movie movie)
    {
        manager.addMovie(movie);
    }
    
     public List<Movie> loadMovies(){
         return null;
     }
     
     public List<Category> loadCategories() {
         return null;
     }
     
     
    
}

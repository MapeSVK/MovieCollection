/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moviecollection.bll;

import moviecollection.be.Movie;
import moviecollection.dal.ConnectionModel;


/**
 *
 * @author Pepe15224
 */
public class BllManager {
   ConnectionModel connectionModel = new ConnectionModel(); 
   
   
   
  public void addNewMovie(Movie movie){
       connectionModel.addMovie(movie);
   }
}
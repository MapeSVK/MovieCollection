/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moviecollection.be;

/**
 *
 * @author Mape
 */
public class MovieInCategory {
    
    private String CategoryId;
    private String MovieId;
    private int id;
    private String name;

    public MovieInCategory(String CategoryId, String MovieId, int id) {
        this.CategoryId = CategoryId;
        this.MovieId = MovieId;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    } 
    
    public String getCategoryId() {
        return CategoryId;
    }

    public void setCategoryId(String CategoryId) {
        this.CategoryId = CategoryId;
    }
    
    public String getMovieId() {
        return MovieId;
    }

    public void setMovieId(String MovieId) {
        this.MovieId = MovieId;
    }

  
}

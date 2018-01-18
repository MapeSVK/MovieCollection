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
    
    private int CategoryId;
    private int MovieId;
    private int id;
    private String name;
    private Double rating;
    private Double personalrating;
    private String filelink;
    private String lastview;

    public String getLastview() {
        return lastview;
    }

    public void setLastview(String lastview) {
        this.lastview = lastview;
    }

    public String getFilelink() {
        return filelink;
    }

    public void setFilelink(String filelink) {
        this.filelink = filelink;
    }

    public Double getPersonalrating() {
        return personalrating;
    }

    public void setPersonalrating(Double personalrating) {
        this.personalrating = personalrating;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public MovieInCategory(int CategoryId, int MovieId, int id) {
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
    
    public int getCategoryId() {
        return CategoryId;
    }

    public void setCategoryId(int CategoryId) {
        this.CategoryId = CategoryId;
    }
    
    public int getMovieId() {
        return MovieId;
    }

    public void setMovieId(int MovieId) {
        this.MovieId = MovieId;
    }
}

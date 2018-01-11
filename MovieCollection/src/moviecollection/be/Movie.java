/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moviecollection.be;

/**
 *
 * @author Pepe15224
 */
public class Movie {
    
        private int id;
        private String name;
        private Double rating;
        private Double personalrating;
        private String filelink;
        private String lastview;

    public Movie(int id, String name, Double rating, Double personalrating, String filelink, String lastview) {
        this.id = id;
        this.name = name;
        this.rating = rating;
        this.personalrating = personalrating;
        this.filelink = filelink;
        this.lastview = lastview;
    }
  
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

}

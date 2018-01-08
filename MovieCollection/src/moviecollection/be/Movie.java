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
        private double rating;
        private double personalrating;
        private String filelink;
        private double lastview;
        
    public double getLastview() {
        return lastview;
    }

    public void setLastview(double lastview) {
        this.lastview = lastview;
    }


    public String getFilelink() {
        return filelink;
    }

    public void setFilelink(String filelink) {
        this.filelink = filelink;
    }


    public double getPersonalrating() {
        return personalrating;
    }

    public void setPersonalrating(double personalrating) {
        this.personalrating = personalrating;
    }


    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
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

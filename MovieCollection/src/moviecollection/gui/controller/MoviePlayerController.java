/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moviecollection.gui.controller;

import java.io.File;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import moviecollection.be.Movie;
import moviecollection.be.MovieInCategory;
import moviecollection.gui.model.MovieModel;

/**
 * FXML Controller class
 *
 * @author Pepe15224
 */
public class MoviePlayerController implements Initializable {

    @FXML
    private MediaView mediaView;
    private MediaPlayer mp;
    private Media me;
    private String filePath="";
    private MovieModel model;
    private MovieInCategory selectedMovieinC;
    

    /**
     * Initializes the controller class.
     */
  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }    
  public void setModelAndMovie(MovieModel model, MovieInCategory selectedMovieinC) {
        this.model=model;
        this.selectedMovieinC=selectedMovieinC;
        playMovie();
        setDate();
    }
  public void playMovie()
  {
      File file = new File(selectedMovieinC.getFilelink());
        filePath = file.toURI().toString();
        Media media = new Media(filePath);
        mp = new MediaPlayer(media);
        mediaView.setMediaPlayer(mp);
        mp.setAutoPlay(true);
        DoubleProperty width = mediaView.fitWidthProperty();
        DoubleProperty height = mediaView.fitHeightProperty();
        width.bind(Bindings.selectDouble(mediaView.sceneProperty(), "width"));
        height.bind(Bindings.selectDouble(mediaView.sceneProperty(), "height"));
  }
  public void setDate()
  {
      DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        
  }
}

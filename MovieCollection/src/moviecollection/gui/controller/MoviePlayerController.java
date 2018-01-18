package moviecollection.gui.controller;

import java.io.File;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import moviecollection.be.MovieInCategory;
import moviecollection.gui.model.MovieModel;


public class MoviePlayerController implements Initializable {

    @FXML
    private MediaView mediaView;
    private MediaPlayer mp;
    private String filePath="";
    private MovieModel model;
    private MovieInCategory selectedMovieinC;
    @FXML
    private Button exitM;
    private Double speed=1d;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }   
    
    public void setModelAndMovie(MovieModel model, MovieInCategory selectedMovieinC) {
        this.model=model;
        this.selectedMovieinC=selectedMovieinC;
        playMovie();
        setDate();
    }
    
    public void playMovie(){
        
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
    
  //Updates lastView date after double click on Movie and open Movie
    public void setDate(){
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        selectedMovieinC.setLastview(dateFormat.format(date));
        model.editDate(selectedMovieinC);
    }

    @FXML
    private void stop(ActionEvent event) {
        mp.stop();
    }

    @FXML
    private void pause(ActionEvent event) {
        mp.pause();
    }

    @FXML
    private void play(ActionEvent event) {
        mp.play();
    }

    @FXML
    private void fast(ActionEvent event) {
        speed=speed+0.5;
        mp.setRate(speed);
    }

    @FXML
    private void slow(ActionEvent event) {
        speed=speed-0.5;
        mp.setRate(speed);
    }

    @FXML
    private void exit(ActionEvent event) {
        mp.stop();
        Stage stage = (Stage) exitM.getScene().getWindow();
        stage.close();
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moviecollection.gui.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import moviecollection.be.Movie;
import moviecollection.gui.model.MovieModel;


/**
 * FXML Controller class
 *
 * @author Pepe15224
 */
public class MainViewController implements Initializable {

    @FXML
    private TableView<?> categoryMoviesTableView;
    @FXML
    private TableView<Movie> allMoviesTableView;
    @FXML
    private ListView<?> categoryListView;
    @FXML
    private Button searchButton;
    @FXML
    private AnchorPane mainWindow;
    @FXML
    private VBox vBox;
    @FXML
    private Label addM;
    @FXML
    private VBox vBoxCat;
    @FXML
    private Label addC;
    @FXML
    private TableColumn<Movie, String> columnTitle;
    @FXML
    private TableColumn<Movie, Double> columnMyRating;
    @FXML
    private TableColumn<Movie, Double> columnImdbRating;
    @FXML
    private TableColumn<Movie, Double> columnView;
    MovieModel model = new MovieModel();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       columnTitle.setCellValueFactory(new PropertyValueFactory("name"));
       columnMyRating.setCellValueFactory(new PropertyValueFactory("personalrating"));
       columnImdbRating.setCellValueFactory(new PropertyValueFactory("rating"));
       columnView.setCellValueFactory(new PropertyValueFactory("lastview"));  

       model.loadAllMovies();
       allMoviesTableView.setItems(model.getAllSongs());
    }    

    @FXML
    private void mICClick(MouseEvent event) {
    }

    @FXML
    private void mClick(MouseEvent event) {
        switch(event.getButton())
        {
            case SECONDARY:
            vBoxCat.setVisible(false);
            vBoxCat.setDisable(true);
            vBox.setVisible(true);
            vBox.setDisable(false);
            vBox.setLayoutX(allMoviesTableView.getLayoutX()+event.getX());
            vBox.setLayoutY(allMoviesTableView.getLayoutY()+event.getY());
             break;
            case PRIMARY:
            vBox.setVisible(false);
            vBox.setDisable(true);
            vBoxCat.setVisible(false);
            vBoxCat.setDisable(true);
            break;
        }
    }

    @FXML
    private void categoryClick(MouseEvent event) {
        switch(event.getButton())
        {
            case SECONDARY:
            vBox.setVisible(false);
            vBox.setDisable(true);
            vBoxCat.setVisible(true);
            vBoxCat.setDisable(false);
            vBoxCat.setLayoutX(categoryListView.getLayoutX()+event.getX());
            vBoxCat.setLayoutY(categoryListView.getLayoutY()+event.getY());
             break;
            case PRIMARY:
            vBoxCat.setVisible(false);
            vBoxCat.setDisable(true);
            vBox.setVisible(false);
            vBox.setDisable(true);
            break;
        }
    }

    @FXML
    private void exitAddM(MouseEvent event) {
         addM.setStyle("-fx-background-color: 0");
    }

    @FXML
    private void enterAddM(MouseEvent event) {
         addM.setStyle("-fx-background-color: #66c3ff");
    }

    @FXML
    private void mainWindowClick(MouseEvent event) {
    }

    @FXML
    private void clickAddM(MouseEvent event) throws IOException {
            vBox.setVisible(false);
            vBox.setDisable(true);
           /* 
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/moviecollection/gui/view/NewMovie.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();          
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.setTitle("Add/Edit song");
            stage.show();
           */ 
                    
    }
       
 private void editMovieClick(ActionEvent event) throws IOException {   
            
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/MovieCollection/gui/View/NewSong.fxml"));

            Parent root1 = (Parent) fxmlLoader.load();

            Stage stage = new Stage();
            stage.setTitle("Add/Edit media");
            stage.setScene(new Scene(root1));
            stage.showAndWait();
            
            allMoviesTableView.refresh();   //Udate the movies table view
            categoryListView.refresh();
           
   }

    @FXML
    private void exitAddC(MouseEvent event) {
        addC.setStyle("-fx-background-color: 0");

    }

    @FXML
    private void enterAddC(MouseEvent event) {
        addC.setStyle("-fx-background-color: #66c3ff");
    }

    @FXML
    private void clickAddC(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/moviecollection/gui/view/Category.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();          
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.setTitle("Add a category");
            stage.show();
            vBoxCat.setVisible(false);
            vBoxCat.setDisable(true);
    }
    
}

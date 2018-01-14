/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moviecollection.gui.controller;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import moviecollection.be.Category;
import moviecollection.be.Movie;
import moviecollection.gui.model.MovieModel;


/**
 * FXML Controller class
 *
 * @author Pepe15224
 */
public class MainViewController implements Initializable {

    @FXML
    private TableView<Movie> categoryMoviesTableView;
    @FXML
    private TableView<Movie> allMoviesTableView;
    @FXML
    private ListView<Category> categoryListView;
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
    private MovieModel model = new MovieModel();
    @FXML
    private Label editM;
    @FXML
    private Label DeleteM;

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
       allMoviesTableView.setItems(model.getAllMovies());
       
       
       model.loadAllCategories();
       categoryListView.setItems(model.getAllCategories());
    }    

    @FXML
    private void mICClick(MouseEvent event) {
        disableVbox(vBox);
        disableVbox(vBoxCat);
    }

    @FXML
    private void mClick(MouseEvent event) {
        
        switch(event.getButton())
        {
            case SECONDARY:  
                if(categoryMoviesTableView.getSelectionModel().getSelectedItem()!=null)
                {
                    editM.setDisable(false);
                    DeleteM.setDisable(false);
                }
             disableVbox(vBoxCat);
             showVbox(vBox);
            vBox.setLayoutX(categoryMoviesTableView.getLayoutX()+event.getX());
            vBox.setLayoutY(categoryMoviesTableView.getLayoutY()+event.getY());
             break;
            case PRIMARY:
                    editM.setDisable(true);
                    DeleteM.setDisable(true);
                disableVbox(vBox);
                disableVbox(vBoxCat);
            break;
        }
    }

    @FXML
    private void categoryClick(MouseEvent event) {
        switch(event.getButton())
        {
            case SECONDARY:
                
                disableVbox(vBox);
                showVbox(vBoxCat);
            vBoxCat.setLayoutX(categoryListView.getLayoutX()+event.getX());
            vBoxCat.setLayoutY(categoryListView.getLayoutY()+event.getY());
             break;
            case PRIMARY:
            disableVbox(vBoxCat);
            disableVbox(vBox);
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
        disableVbox(vBox);
        disableVbox(vBoxCat);
    }

    @FXML
    private void clickAddM(MouseEvent event) throws IOException {
            disableVbox(vBox);
           
            Parent root;
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/moviecollection/gui/view/NewMovie.fxml"));
            root = loader.load();
            NewMovieController controller = loader.getController();
            controller.setModelAndMovie(model, null);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("New/Edit Movie");
            stage.setScene(new Scene(root));
            stage.showAndWait();
    }
 
    @FXML
    private void exitAddC(MouseEvent event) {
        addC.setStyle("-fx-background-color: 0");
    }
    
    @FXML
    private void enterAddC(MouseEvent event) {
        addC.setStyle("-fx-background-color: #66c3ff");
    }  

    private void cliclEditM(MouseEvent event) {
        disableVbox(vBox);
    }

    @FXML
    private void clickDeleteM(MouseEvent event) {
        disableVbox(vBox);
        editM.setDisable(true);
        DeleteM.setDisable(true);
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setContentText("Are you sure?");
        
        Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
            model.deleteMovie(categoryMoviesTableView.getSelectionModel().getSelectedItem());
            } else {
            }
    }

    @FXML
    private void exitEditM(MouseEvent event) {
        editM.setStyle("-fx-background-color: 0");
    }

    @FXML
    private void enterEditM(MouseEvent event) {
        editM.setStyle("-fx-background-color: #66c3ff");
    }

    @FXML
    private void exitDeleteM(MouseEvent event) {
        DeleteM.setStyle("-fx-background-color: 0");
    }

    @FXML
    private void enterDeleteM(MouseEvent event) {
        DeleteM.setStyle("-fx-background-color: #66c3ff");
    }
    private void disableVbox(VBox vBox)
    {
        vBox.setVisible(false);
        vBox.setDisable(true);
    }
    private void showVbox(VBox vBox)
    {
        vBox.setVisible(true);
        vBox.setDisable(false);
    }
    @FXML
    private void clickAddC(MouseEvent event) throws IOException {
        Parent root;
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/moviecollection/gui/view/Category.fxml"));
            root = loader.load();
            CategoryController controller = loader.getController();
            controller.setModel(model);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Add Category");
            stage.setScene(new Scene(root));
            stage.showAndWait();
    }

    @FXML
    private void deleteCClick(MouseEvent event) {
        
        model.deleteCategory(categoryListView.getSelectionModel().getSelectedItem());
    }

    @FXML
    private void playMovie(ActionEvent event) throws IOException {
        Parent root;
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/moviecollection/gui/view/MoviePlayer.fxml"));
            root = loader.load();
            MoviePlayerController controller = loader.getController();
            Movie selectedMovie = categoryMoviesTableView.getSelectionModel().getSelectedItem();
            controller.setModelAndMovie(model, selectedMovie);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("New/Edit Playlist");
            stage.setScene(new Scene(root));
            stage.showAndWait();
    }

    @FXML
    private void ClickEditM(MouseEvent event) throws IOException {
                Parent root;
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/moviecollection/gui/view/NewMovie.fxml"));
            root = loader.load();
            NewMovieController controller = loader.getController();
            Movie selectedMovie = categoryMoviesTableView.getSelectionModel().getSelectedItem();
            controller.setModelAndMovie(model, selectedMovie);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("New/Edit Movie");
            stage.setScene(new Scene(root));
            stage.showAndWait();
    }
    
    
}

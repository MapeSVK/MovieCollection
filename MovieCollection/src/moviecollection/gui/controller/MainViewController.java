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
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import moviecollection.be.Category;
import moviecollection.be.MovieInCategory;
import moviecollection.gui.model.MovieModel;


/**
 * FXML Controller class
 *
 * @author Pepe15224
 */
public class MainViewController implements Initializable {

    @FXML
    private TableView<MovieInCategory> categoryMoviesTableView;
    @FXML
    private ListView<Category> categoryListView;
    @FXML
    private TableColumn<MovieInCategory, String> columnTitle;
    @FXML
    private TableColumn<MovieInCategory, Double> columnMyRating;
    @FXML
    private TableColumn<MovieInCategory, Double> columnImdbRating;
    @FXML
    private TableColumn<MovieInCategory, Double> columnView;
    
    private MovieModel model = new MovieModel();
    @FXML
    private MenuItem editM;
    @FXML
    private MenuItem deleteM;
    @FXML
    private MenuItem addC;
    @FXML
    private MenuItem deleteC;
    @FXML
    private MenuItem addM;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       columnTitle.setCellValueFactory(new PropertyValueFactory("name"));
       columnMyRating.setCellValueFactory(new PropertyValueFactory("personalrating"));
       columnImdbRating.setCellValueFactory(new PropertyValueFactory("rating"));
       columnView.setCellValueFactory(new PropertyValueFactory("lastview")); 
 
       model.loadAllCategories();
       categoryListView.setItems(model.getAllCategories());
       
      
    }    

@FXML
private void mICClick(MouseEvent event) {
    MovieInCategory selectedMovieinC = categoryMoviesTableView.getSelectionModel().getSelectedItem();
      if (event.getClickCount() == 2 && !event.isConsumed() && selectedMovieinC!=null) {
          try {
              event.consume();
              Parent root;
              Stage stage = new Stage();
              FXMLLoader loader = new FXMLLoader(getClass().getResource("/moviecollection/gui/view/MoviePlayer.fxml"));
              root = loader.load();
              MoviePlayerController controller = loader.getController();
              controller.setModelAndMovie(model, selectedMovieinC);
              stage.initModality(Modality.APPLICATION_MODAL);
              stage.setTitle("Movie Player");
              stage.setScene(new Scene(root));
              stage.showAndWait();
          } catch (IOException ex) {
              Logger.getLogger(MainViewController.class.getName()).log(Level.SEVERE, null, ex);
          }
        }
      if (selectedMovieinC!=null) 
        {           
            editM.setDisable(false);
            deleteM.setDisable(false);
        }
      else {
            editM.setDisable(true);
            deleteM.setDisable(true);      
      }
      
   
  }

   
    @FXML
    private void categoryClick(MouseEvent event) {
        Category selectedCategory = categoryListView.getSelectionModel().getSelectedItem();
        if(selectedCategory!=null)
        {
            categoryMoviesTableView.setItems(model.getMoviesById(selectedCategory.getId()));
            deleteC.setDisable(false);
        }
        else {
            deleteC.setDisable(true);
        }
        
    }
    
    


    @FXML
    private void clickAddM(ActionEvent event) {
        try {
            
           
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
        } catch (IOException ex) {
            Logger.getLogger(MainViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    @FXML
    private void clickEditM(ActionEvent event) {
        try {
            MovieInCategory movieinC = categoryMoviesTableView.getSelectionModel().getSelectedItem();
            Parent root;
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/moviecollection/gui/view/NewMovie.fxml"));
            root = loader.load();
            NewMovieController controller = loader.getController();
            controller.setModelAndMovie(model, movieinC);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("New/Edit Movie");
            stage.setScene(new Scene(root));
            stage.showAndWait();
        } catch (IOException ex) {
            Logger.getLogger(MainViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    

    @FXML
    private void clickDeleteM(ActionEvent event) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setContentText("Are you sure?");
        
        Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
            model.deleteMovie(categoryMoviesTableView.getSelectionModel().getSelectedItem());
            } else {
            }
    }

    
   
    @FXML
    private void clickAddC(ActionEvent event) {
        try {
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
        } catch (IOException ex) {
            Logger.getLogger(MainViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void clickDeleteC(ActionEvent event) {
        
        model.deleteCategory(categoryListView.getSelectionModel().getSelectedItem());
        
    }
    
    
   

 
}

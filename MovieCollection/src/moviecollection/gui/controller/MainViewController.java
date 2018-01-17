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
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import moviecollection.be.Category;
import moviecollection.be.MovieInCategory;
import moviecollection.gui.model.MovieModel;


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
    private MenuItem deleteC;  
    @FXML
    private TextField filterText;
    private ToggleButton filterButton;
    @FXML
    private TextField minFilter;
    @FXML
    private Circle statusDot;
    private ComboBox<Category> firstCombo;
    private ComboBox<Category> secondCombo;
    private ComboBox<Category> thirdCombo;
    private Category none = new Category(-1, "None");
    @FXML
    private TitledPane filterPane;
    @FXML
    private TableView<Category> selectedCategoriesTable;
    @FXML
    private TableColumn<Category, String> selectedCategoriesColumn;
    @FXML
    private TableView<Category> allCategoriesTable;
    @FXML
    private TableColumn<Category, String> allCategoriesColumn;
    /* INITIALIZE */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       columnTitle.setCellValueFactory(new PropertyValueFactory("name"));
       columnMyRating.setCellValueFactory(new PropertyValueFactory("personalrating"));
       columnImdbRating.setCellValueFactory(new PropertyValueFactory("rating"));
       columnView.setCellValueFactory(new PropertyValueFactory("lastview"));
       allCategoriesColumn.setCellValueFactory(new PropertyValueFactory("name"));
       selectedCategoriesColumn.setCellValueFactory(new PropertyValueFactory("name"));
 
       fillCat();
       model.loadAllCategories();
       categoryListView.setItems(model.getAllCategories());
    
    }    

    /* 1. MediaPlayer appears when you double-click on Movie
       2. Edit and Delete functions appears when you click on Movie */
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

    /* 1. If category is selected it shows up movies inside of the category 
       2. Delete function appears when you click on Category 
       3. Filter is still active when you click on different category */
    @FXML
    private void categoryClick(MouseEvent event) {
        Category selectedCategory = categoryListView.getSelectionModel().getSelectedItem();
        if(selectedCategory!=null)
        {
            fillCat();
            selectedCategoriesTable.getItems().clear();
            categoryMoviesTableView.setItems(model.getMoviesById(selectedCategory.getId()));
            deleteC.setDisable(false);
            
            if(minFilter.getText().equals(""))
        {          
            categoryMoviesTableView.setItems(model.getTest(filterText.getText()));
        }
        else if(!minFilter.getText().equals(""))
        {       
            categoryMoviesTableView.setItems(model.getTest(filterText.getText(),Double.valueOf(minFilter.getText())));
        }
        }
        else {
            deleteC.setDisable(true);
        }
        }

    /* Opens new FXML - NewMovie */
    @FXML
    private void clickAddM(ActionEvent event) {
        try {
            Parent root;
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/moviecollection/gui/view/NewMovie.fxml"));
            root = loader.load();
            NewMovieController controller = loader.getController();
            controller.setModelAndMovie(model);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("New/Edit Movie");
            stage.setScene(new Scene(root));
            stage.showAndWait();
        } catch (IOException ex) {
            Logger.getLogger(MainViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    /* Opens new FXML - NewMovie with pre-filled textfields */
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


    /* Deletes Movie but firstly it asks if you rly want to delete */
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

    
   /* Opens new FXML - Category */
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

    /* Deletes selected Category */
    @FXML
    private void clickDeleteC(ActionEvent event) { 
        model.deleteCategory(categoryListView.getSelectionModel().getSelectedItem());       
    }
    
   
    /************* FILTER SETTINGS **************/

    private void filterButt(ActionEvent event) {
//         if(firstCombo.getValue()!=none || secondCombo.getValue()!=none || thirdCombo.getValue()!=none)
//        {
//            categoryMoviesTableView.setItems(model.getMultipleMoviesById(firstCombo.getValue().getId(), secondCombo.getValue().getId(), thirdCombo.getValue().getId()));   
//            statusDot.setFill(Color.valueOf("#58ff21")); 
//            if(filterButton.isSelected()==true && minFilter.getText().equals(""))
//        {
//            categoryMoviesTableView.setItems(model.getTest(filterText.getText()));
//        }
//            else if(filterButton.isSelected()==true && !minFilter.getText().equals(""))
//        {       
//            
//            categoryMoviesTableView.setItems(model.getTest(filterText.getText(),Double.valueOf(minFilter.getText())));
//        }
//        categoryListView.getSelectionModel().select(-1);
//        }
//         else if(filterButton.isSelected()==true && minFilter.getText().equals(""))
//        {
//            statusDot.setFill(Color.valueOf("#58ff21"));
//            categoryMoviesTableView.setItems(model.getTest(filterText.getText()));
//        }
//        else if(filterButton.isSelected()==true && !minFilter.getText().equals(""))
//        {       
//            statusDot.setFill(Color.valueOf("#58ff21"));
//            categoryMoviesTableView.setItems(model.getTest(filterText.getText(),Double.valueOf(minFilter.getText())));
//        }
//         else if(categoryListView.getSelectionModel().getSelectedItem()!=null)
//        {
//            statusDot.setFill(Color.valueOf("#ff2121"));
//            categoryMoviesTableView.setItems(model.getMoviesById(categoryListView.getSelectionModel().getSelectedItem().getId())); 
//        }
//        
//        else 
//        {
//            statusDot.setFill(Color.valueOf("#ff2121"));
//        }
        }
    @FXML
    private void ClickFilterPane(MouseEvent event) {
        if(filterPane.isExpanded()==true)
        {
            filterPane.setPrefHeight(235);
        }
        else
            filterPane.setPrefHeight(20);
    }
    @FXML
    private void clickFilterText(MouseEvent event) {
        filterText.textProperty().addListener((observable, oldValue, newValue) -> {
    if(minFilter.getText().equals(""))
        {          
            categoryMoviesTableView.setItems(model.getTest(filterText.getText()));
        }
        else if(!minFilter.getText().equals(""))
        {       
            categoryMoviesTableView.setItems(model.getTest(filterText.getText(),Double.valueOf(minFilter.getText())));
        }
});
    }
    @FXML
    private void clickFilterMin(MouseEvent event) {
        minFilter.textProperty().addListener((observable, oldValue, newValue) -> {
        if(!minFilter.getText().equals(""))
        {       
            categoryMoviesTableView.setItems(model.getTest(filterText.getText(),Double.valueOf(minFilter.getText())));
        }
        else if(minFilter.getText().equals(""))
        {          
            categoryMoviesTableView.setItems(model.getTest(filterText.getText()));
        }
         });
    }
private void fillCat()
{
    model.loadAllCategories();
    allCategoriesTable.getItems().clear();
    allCategoriesTable.getItems().addAll(model.getAllCategories());
}

    @FXML
    private void removeCategoryFilter(ActionEvent event) {
        Category selectedCat = selectedCategoriesTable.getSelectionModel().getSelectedItem();
        if(selectedCat!=null)
        {
           selectedCategoriesTable.getItems().remove(selectedCat);
           allCategoriesTable.getItems().add(selectedCat);
           categoryMoviesTableView.setItems(model.getMultipleMoviesById(selectedCategoriesTable.getItems()));
           if(!minFilter.getText().equals(""))
        {       
            categoryMoviesTableView.setItems(model.getTest(filterText.getText(),Double.valueOf(minFilter.getText())));
        }
        else if(minFilter.getText().equals(""))
        {          
            categoryMoviesTableView.setItems(model.getTest(filterText.getText()));
        }
        }
    }

    @FXML
    private void addCategoryFilter(ActionEvent event) {
        Category selectedCat = allCategoriesTable.getSelectionModel().getSelectedItem();
        if(selectedCat!=null)
        {
           allCategoriesTable.getItems().remove(selectedCat);
           selectedCategoriesTable.getItems().add(selectedCat);
         categoryMoviesTableView.setItems(model.getMultipleMoviesById(selectedCategoriesTable.getItems())); 
         if(!minFilter.getText().equals(""))
        {       
            categoryMoviesTableView.setItems(model.getTest(filterText.getText(),Double.valueOf(minFilter.getText())));
        }
        else if(minFilter.getText().equals(""))
        {          
            categoryMoviesTableView.setItems(model.getTest(filterText.getText()));
        }
        }
    }
   
}

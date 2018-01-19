package moviecollection.gui.controller;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
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
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.MediaException;
import javafx.stage.Modality;
import javafx.stage.Stage;
import moviecollection.be.Category;
import moviecollection.be.Movie;
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
    @FXML
    private MenuItem editM;
    @FXML
    private MenuItem deleteM; 
    @FXML
    private TextField filterText;    
    @FXML
    private TextField minFilter;   
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
    
    private MovieModel model = new MovieModel();
    @FXML
    private MenuItem deleteC;

    /* INITIALIZE */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       columnTitle.setCellValueFactory(new PropertyValueFactory("name"));
       columnMyRating.setCellValueFactory(new PropertyValueFactory("personalrating"));
       columnImdbRating.setCellValueFactory(new PropertyValueFactory("rating"));
       columnView.setCellValueFactory(new PropertyValueFactory("lastview"));
       allCategoriesColumn.setCellValueFactory(new PropertyValueFactory("name"));
       selectedCategoriesColumn.setCellValueFactory(new PropertyValueFactory("name"));
       
       
       model.loadAllCategories();
       fillCat();
       categoryListView.setItems(model.getAllCategories()); 
       if(categoryListView.getItems().size()!=0)
       {
           categoryListView.getSelectionModel().selectFirst();
           categoryMoviesTableView.setItems(model.getMoviesById(
           categoryListView.getSelectionModel().getSelectedItem().getId()));
       }
    }
   
    /* 1. MediaPlayer appears when you double-click on the Movie. Double-click on the Movie also updates date.
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
                   Alert("Movie Player error", "Movie cannot be played. Movie player is not working or is missing.");
              }
              catch(MediaException me){
                  Alert("ERROR", "Movie cannot be played because of the bad link to the file.");
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
    
    /* 1. If category is selected it shows movies inside of the category 
       2. Delete function appears when you click on Category 
       3. Filter is still active when you click on different category */   
    @FXML
    private void categoryClick(MouseEvent event) {
        deleteC.setDisable(true);
        Category selectedCategory = categoryListView.getSelectionModel().getSelectedItem();
        if(selectedCategory!=null)
        {           
           fillCat();
            selectedCategoriesTable.getItems().clear();
            categoryMoviesTableView.setItems(model.getMoviesById(selectedCategory.getId()));
            deleteC.setDisable(false);
        }
        if(selectedCategory!=null && !minFilter.getText().equals(""))
        {       
            categoryMoviesTableView.setItems(model.getTest(filterText.getText(),Double.valueOf(minFilter.getText())));
        }
        else if(selectedCategory!=null && !filterText.getText().equals(""))
        {          
            categoryMoviesTableView.setItems(model.getTest(filterText.getText()));
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
            Alert("ERROR", "Movie cannot be added. Window is missing.");
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
            Alert("ERROR", "Movie cannot be edited. Window is missing.");
        }
    }

    /* Deletes Movie but before that it asks you if you rly want to delete */
    @FXML
    private void clickDeleteM(ActionEvent event) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setContentText("Are you sure?");
        
        Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                model.deleteMovie(categoryMoviesTableView.getSelectionModel().getSelectedItem());
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
            Alert("ERROR", "Category cannot be added. Window is missing.");
        }
    }

    /* Deletes selected Category */
    @FXML
    private void clickDeleteC(ActionEvent event) {         
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setContentText("Are you sure?");
        
        Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                model.deleteCategory(categoryListView.getSelectionModel().getSelectedItem());
            }             
    } 
   
    /************* FILTER SETTINGS **************/
    private boolean alreadyExecutedListener1;
    private boolean alreadyExecutedListener2;

   //Chandes the size of FilterPane
    @FXML
    private void ClickFilterPane(MouseEvent event) {
        if(filterPane.isExpanded()==true){
            filterPane.setPrefHeight(300);
            
        }
        else
            filterPane.setPrefHeight(20);
    }
   // Starts listener that is responsible for text filter
    @FXML
    private void clickFilterText(MouseEvent event) {
        if(!alreadyExecutedListener1){
            filterText.textProperty().addListener((observable, oldValue, newValue) -> {
                try{ 
                    if(minFilter.getText().equals(""))
                    {   
                        categoryMoviesTableView.setItems(model.getTest(filterText.getText()));
                        
                    }
                    else if(!minFilter.getText().equals(""))
                    {
                        categoryMoviesTableView.setItems(model.getTest(filterText.getText(),Double.valueOf(minFilter.getText())));
                    }
                }
                catch (NumberFormatException e) {
                    Alert("ERROR", "Minimal IMDB rating should be number, not text!");
                }
            });
        alreadyExecutedListener1 = true;
      }
    }
   // Starts listener that is responsible for rating filter 
    @FXML
    private void clickFilterMin(MouseEvent event) {
      if (!alreadyExecutedListener2) {
        minFilter.textProperty().addListener((observable, oldValue, newValue) -> {       
            try{ 
                if(!minFilter.getText().equals(""))
                {       
                    categoryMoviesTableView.setItems(model.getTest(filterText.getText(),Double.valueOf(minFilter.getText())));
                    
                }
                else if(minFilter.getText().equals(""))
                {      
                    categoryMoviesTableView.setItems(model.getTest(filterText.getText()));
                }
            }
            catch (NumberFormatException e) {
                Alert("ERROR", "Minimal IMDB rating should be number, not text!");
            }       
        });
        alreadyExecutedListener2 = true;
      }
    }
   // Removes Category Filter
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
   // Adds Category Filter
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

    /************ DATE NOTIFICATION ***************/
    
    // Alert appears if there is a Movie, which was last seen 2 years ago   
    public void twoYearsNotification() {
    model.loadAllMovies();
       
        for (Movie movie : model.getAllMovies()){
           
           String dateString = movie.getLastview(); //get date in string form
           
           try {
               
              if (dateString != null){
                  DateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy"); //date formator
                  DateTimeFormatter dateFormatter2 = DateTimeFormatter.ofPattern("dd/MM/yyyy"); //actual date formator
                  
                  LocalDate localDate = LocalDate.now();
                  
                  String actualDateString = localDate.format(dateFormatter2);
 
                  Date actualDate = dateFormatter.parse(actualDateString);
                  dateFormatter.format(actualDate);
                  
                  Date date = dateFormatter.parse(dateString); //get date from string
                  dateFormatter.format(date);
                  
                  
                  if (getDateDifference(date,actualDate,TimeUnit.DAYS)>712.5 && movie.getPersonalrating()<6) { 
                      Alert("DELETE OLD MOVIES", "There are old and bad movies in your collection");
                  }
              }            
           } 
           catch (ParseException ex) {
               Logger.getLogger(MainViewController.class.getName()).log(Level.SEVERE, null, ex);
           }           
        }
    }
    
   /****************** HELPER METHODS ******************/
    
    // Calculation between actualDate and date (lastView)
    public long getDateDifference(Date date1, Date date2, TimeUnit timeUnit) {
                    long diffInMillies = date2.getTime() - date1.getTime();
                    return timeUnit.convert(diffInMillies,TimeUnit.MILLISECONDS);
    }
    
    // Will show basic alert pop-up window
    private void Alert(String title,String text)
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle(title);
                alert.setContentText(text);
                alert.showAndWait();
    }
 
    /* Show notification when there is old and bad movie. 
    Notification appears after mouse enter application and 
    this method is executed only once (trick with boolean) */
    private boolean alreadyExecutedNotification;
    @FXML
    private void showNotification(MouseEvent event) {
        if(!alreadyExecutedNotification) {
             twoYearsNotification();
         alreadyExecutedNotification = true;
        }
    }
  
    private void fillCat()
    {
        allCategoriesTable.getItems().clear();
        allCategoriesTable.getItems().addAll(model.getAllCategories());
    }
}

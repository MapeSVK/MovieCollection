package moviecollection.gui.controller;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import moviecollection.be.Category;
import moviecollection.be.Movie;
import moviecollection.be.MovieInCategory;
import moviecollection.gui.model.MovieModel;


public class NewMovieController implements Initializable {

    @FXML
    private Button CloseButton;
    @FXML
    private TextField TitleTextField;
    @FXML
    private TextField PRatingTextField;
    @FXML
    private TextField IMDBRatingTextField;
    @FXML
    private TableView<Category> allCategory;
    @FXML
    private TableColumn<Category, String> allCategoryCollumn;
    @FXML
    private TableView<Category> addToCategory;
    @FXML
    private TableColumn<Category, String> addToCategoriesColumn;
    @FXML
    private TextField FileTextField;
    private MovieModel model;
    private MovieInCategory selectedMovieinC;
  
    /**
     * Initializes the controller class.
     */

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        allCategoryCollumn.setCellValueFactory(new PropertyValueFactory("name"));
        addToCategoriesColumn.setCellValueFactory(new PropertyValueFactory("name"));
    }   
    
    public void setModelAndMovie(MovieModel model, MovieInCategory selectedMovieinC) {
        this.model=model;
        this.selectedMovieinC=selectedMovieinC;
        fillCombo();
        fill();      
    }
    
    public void setModelAndMovie(MovieModel model)
    {
         this.model=model;
         this.selectedMovieinC=null;
         fillCombo();
    }
     
    @FXML
    private void ChooseButtonClick(ActionEvent event) {
            FileChooser fileChooser = new FileChooser();
            FileChooser.ExtensionFilter filtermP4 = new FileChooser.ExtensionFilter("select mp4","*.mp4");
            FileChooser.ExtensionFilter filterMpeg4 = new FileChooser.ExtensionFilter("select mpeg4","*.mpeg4");
            fileChooser.getExtensionFilters().addAll(filtermP4,filterMpeg4);
            File file = fileChooser.showOpenDialog(null);
            
            if (file!=null){ //if statement only to avoid nullPointException after pressing "cancel" in filechooser
            String filePath = file.toString();
            FileTextField.setText(filePath); //insert path of the file into the textField
            }
            else {
                System.out.println("File was not choosen.");
            }              
     }
 
    @FXML
    private void SaveButtonClick(ActionEvent event) {

        boolean exist=false;
        boolean isFilled = false;
        boolean isMaxDouble = false;
        boolean isNotNumber = false;
        
        model.loadAllMovies();
        for(Movie movie : model.getAllMovies()){
            
        /*********** Prevent adding 2 same movies ***************/
           
               if(movie.getName().equals(TitleTextField.getText()) && selectedMovieinC==null)
               {
                Alert("Invalid name","This movie already exists!");   
                exist=true;
               }
           }
        
        /*********** Fields must be filled  ***************/
        if (!TitleTextField.getText().isEmpty() && 
            !PRatingTextField.getText().isEmpty() && 
            !IMDBRatingTextField.getText().isEmpty() &&
            !FileTextField.getText().isEmpty()){
            isFilled = true;
        }
        else {
            Alert("Invalid fields","Fields must be filled!");   
        }
        
        /*********** Rating must be NUMBER from 0.1 - 10.0 ***************/
        try {
        Double PRatingDouble = Double.parseDouble(PRatingTextField.getText());
        Double IMDBRatingDouble = Double.parseDouble(IMDBRatingTextField.getText());
            if (PRatingDouble <= 10 && PRatingDouble >=0.1 && IMDBRatingDouble <= 10 && IMDBRatingDouble >=0.1) {
                isMaxDouble = true;
            }
            else {
                Alert("Rating error", "Rating must be number from 0.1 - 10.0");
            }
        }
        catch(NumberFormatException e){
            isNotNumber = true;
            Alert("Rating error", "Rating must be number from 0.1 - 10.0");
        }

        /*********** Final  ***************/
        if(exist==false && isFilled ==true && isNotNumber ==false && isMaxDouble==true)
        {
           Save();  
        }
    }
    
    private void Save(){  
        if(selectedMovieinC==null)
        {
            if(addToCategory.getItems().size()!=0)
            {
                Movie myMovie = new Movie(-1,
                TitleTextField.getText(),
                Double.valueOf(PRatingTextField.getText()),
                Double.valueOf(IMDBRatingTextField.getText()),
                FileTextField.getText(),
                null);
                model.addMovie(myMovie); 
                addMovieToCat(myMovie);
                closeWindow();
            }
            else
            {
                Alert("No Category", "You need to add category to 'Add To' list");
            }
        }
        else if(selectedMovieinC!=null)
        {
            selectedMovieinC.setName(TitleTextField.getText());
            selectedMovieinC.setRating(Double.valueOf(IMDBRatingTextField.getText()));
            selectedMovieinC.setPersonalrating(Double.valueOf(PRatingTextField.getText()));
            selectedMovieinC.setFilelink(FileTextField.getText());
            model.editMovies(selectedMovieinC);
            closeWindow();
        }  
    }
    
    @FXML
    private void addCategory(ActionEvent event) {
        Category selectedCat = allCategory.getSelectionModel().getSelectedItem();
        if(selectedCat!=null)
        {
            addToCategory.getItems().add(selectedCat);
            allCategory.getItems().remove(selectedCat);
        }
    }

    @FXML
    private void removeCategory(ActionEvent event) {
        Category selectedCat = addToCategory.getSelectionModel().getSelectedItem();
        if(selectedCat!=null)
        {
            addToCategory.getItems().remove(selectedCat);
            allCategory.getItems().add(selectedCat);
        }
    }
    
    private void addMovieToCat(Movie movie)
    {
         for(int i =0; i<addToCategory.getItems().size();i++)
         {
             model.addMovieToCategory(addToCategory.getItems().get(i), movie);
         }  
    }
    
    @FXML
    private void CloseButtonClick(ActionEvent event) {
        closeWindow();
    }
    
    private void closeWindow()
    {
        Stage stage = (Stage) CloseButton.getScene().getWindow();
        stage.close();
    }
    
    private void fill()
    {
            TitleTextField.setText(selectedMovieinC.getName());
            PRatingTextField.setText(""+selectedMovieinC.getPersonalrating());
            IMDBRatingTextField.setText(""+selectedMovieinC.getRating());
            FileTextField.setText(selectedMovieinC.getFilelink());       
    }
    
    private void fillCombo()
    {
       allCategory.getItems().addAll(model.getAllCategories());
    }
    
    private void Alert(String title,String text)
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle(title);
                alert.setContentText(text);
                alert.showAndWait();
    }
}

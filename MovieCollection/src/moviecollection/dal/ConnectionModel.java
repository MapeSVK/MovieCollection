/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moviecollection.dal;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import moviecollection.be.Movie;

/**
 *
 * @author Pepe15224
 */
public class ConnectionModel {
    
    private ConnectionManager cm = new ConnectionManager();
    
    
    /********** ADD, DELETE AND EDIT MOVIES ************/
    
    public void addMovie(Movie movie) {
        try (Connection con = cm.getConnection()) {
            String sql
                    = "INSERT INTO Movie"
                    + "(name, rating, personalrating, filelink, lastview) "
                    + "VALUES(?,?,?,?,?)";
            PreparedStatement pstmt
                    = con.prepareStatement(
                            sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, movie.getName());
            pstmt.setDouble(2, movie.getRating());
            pstmt.setDouble(3, movie.getPersonalrating());
            pstmt.setString(4, movie.getFilelink());
            pstmt.setDouble(5, movie.getLastview());

            

            // Database generated ID
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                movie.setId(rs.getInt(1));
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(ConnectionModel.class.getName()).log(
                    Level.SEVERE, null, ex);
        }
    }
    
    public void deleteMovie(Movie movie) throws SQLServerException, SQLException  {
            Connection con = cm.getConnection();
            PreparedStatement pstmt = con.prepareStatement("DELETE FROM Movie WHERE id=?");
            pstmt.setInt(1, movie.getId());     
    }
    
    public void editMovie(Movie movie) throws SQLException {
            Connection con = cm.getConnection();
            PreparedStatement pstmt = con.prepareStatement("UPDATE Movie SET name=?, rating=?, personalrating=?, filelink=? WHERE id=?");
            pstmt.setString(1, movie.getName());
            pstmt.setDouble(2, movie.getRating());
            pstmt.setDouble(3, movie.getPersonalrating());
            pstmt.setString(4, movie.getFilelink());
            pstmt.setDouble(5, movie.getId());  
    }
    
    /*******************************************/
    
    
    public List<Movie> getAllMovies()
    {
        List<Movie> allMovies = new ArrayList();
        try (Connection con = cm.getConnection())
        {
         PreparedStatement pstmt = con.prepareCall("SELECT * FROM Movie");
         ResultSet rs = pstmt.executeQuery();
            while(rs.next())
            {
                Movie m = new Movie(rs.getInt("id"),
                       rs.getString("name"),
                        rs.getDouble("rating"),
                        rs.getDouble("personalrating"),
                        rs.getString("filelink"), rs.getDouble("lastview"));
      
                allMovies.add(m);
            }
         }
         catch (SQLException ex) {
            Logger.getLogger(ConnectionModel.class.getName()).log(
                    Level.SEVERE, null, ex);
          }
        return allMovies;
}
}

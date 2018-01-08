/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moviecollection.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import moviecollection.be.Movie;

/**
 *
 * @author Pepe15224
 */
public class DALManager {
    
    ConnectionManager cm = new ConnectionManager();
    
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

            int affected = pstmt.executeUpdate();
            if (affected<1)
                throw new SQLException("Movie could not be added");

            // Get database generated id
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                movie.setId(rs.getInt(1));
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(DALManager.class.getName()).log(
                    Level.SEVERE, null, ex);
        }
    }
    
}

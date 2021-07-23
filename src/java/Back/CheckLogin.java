/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Back;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author ASUS
 */
public class CheckLogin {
 
    public User checkLogin(String username, String password) throws SQLException,
        ClassNotFoundException {
          
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/vLGgz5ywmj","vLGgz5ywmj", "RrG1VumVxC");
        String sql = "SELECT username, password  FROM vLGgz5ywmj.user WHERE username=? and password=?";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setString(1, username);
        statement.setString(2, password);
        ResultSet result = statement.executeQuery();
        
        User user = null;
        
        if (result.next()) {
            user = new User();
            user.setUsername(username);
        }

        con.close();
        return user;
        
    }
}

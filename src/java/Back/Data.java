/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Back;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

/**
 *
 * @author ASUS
 */
@WebServlet("/dataServlet")
public class Data extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String name = request.getParameter("name");
            String age = request.getParameter("age");
            String email = request.getParameter("email");
            int nIdentify=-1;
            ArrayList<String> data = new ArrayList();
            
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/vLGgz5ywmj","vLGgz5ywmj", "RrG1VumVxC");
            
            Cookie[] cookies = request.getCookies();
                if (cookies != null) {
                    for (Cookie cookie : cookies) {
                        String sql = "SELECT n_identify FROM vLGgz5ywmj.user WHERE username = '"+cookie.getValue()+"'";
                        Statement stat;
                          try {
                              stat = con.createStatement();
                              ResultSet r= stat.executeQuery(sql);
                               while(r.next()){
                                   nIdentify=r.getInt(1);
                               }
                               stat.close();
                          } catch (SQLException ex) {
                              Logger.getLogger(Data.class.getName()).log(Level.SEVERE, null, ex);
                          }
                       }
                }
            
            try {
                String sql = "UPDATE vLGgz5ywmj.user SET username = ?, password = ?, email = ?, idade = ?, nome = ? WHERE n_identify = '"+nIdentify+"'";
                PreparedStatement statement = con.prepareStatement(sql);
                statement.setString(1, username);
                statement.setString(2, password);
                statement.setString(3, email);
                statement.setString(4, age);
                statement.setString(5, name);
                statement.executeUpdate();
                statement.close();
                
                String json = "{\"Username\": \""+username+"\", \"E-mail\": \""+email+"\", \"Idade\": \""+age+"\", \"Nome\": \""+name+"\"}";
                data.add(json);
                
                User user = null;
                user = new User();
                user.setUsername(username);
                user.setPassword(password);
                user.setName(name);
                user.setAge(age);
                user.setEmail(email);
               
                con.close();
                String destPage = "index.html";
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
                dispatcher.forward(request, response);
            
            } catch (SQLException ex) {
                Logger.getLogger(Data.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            } catch (ClassNotFoundException ex) {
            Logger.getLogger(Data.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
            Logger.getLogger(Data.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}


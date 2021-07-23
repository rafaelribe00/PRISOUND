/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Back;

import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.swing.JOptionPane;

/**
 *
 * @author ASUS
 */
@WebServlet("/loginServlet")
public class Login extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        int num_logins=-1;
        int tipo=-1;
        
        CheckLogin checklogin = new CheckLogin();
        
        try {
            User user;
            user=checklogin.checkLogin(username, password);
            String destPage = "";
            
            if (user != null) {
                
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection con = DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/vLGgz5ywmj","vLGgz5ywmj", "RrG1VumVxC");
                    String sql = "SELECT num_logins, tipo_user FROM vLGgz5ywmj.user WHERE username='"+username+"'";
                    Statement statement = con.createStatement();
                    ResultSet rs= statement.executeQuery(sql);
                     if(rs.next()){
                        num_logins = rs.getInt(1);
                        tipo = rs.getInt(2);
                    }
                   
                    if(num_logins==0){
                        String sql1 ="UPDATE vLGgz5ywmj.user SET num_logins=1 WHERE username ='"+username+"'";
                        Statement stat = con.createStatement();
                        int result = stat.executeUpdate(sql1);
                        HttpSession session = request.getSession();
                        session.setAttribute("user", user);
                        destPage = "firstlogin.html";
                    }
                    else if(num_logins==1){
                        HttpSession session = request.getSession();
                        session.setAttribute("user", user);
                        if(tipo==0){
                            destPage = "dashboard_a.html";
                        }
                        else{ destPage = "dashboard.html";
                        }
                    }

                    con.close();
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                }
               
            } else {
                request.setAttribute("message", "Invalid email/password");
                RequestDispatcher dispatcher = request.getRequestDispatcher("index.html");
                dispatcher.forward( request, response );
                destPage = "index.html";
            }
             
            RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
            dispatcher.forward(request, response);
            
        } catch (Exception e) {
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
        System.out.println("login");
            doPost(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            doPost(request, response);
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

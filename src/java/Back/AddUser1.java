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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;



/**
 *
 * @author ASUS
 */
@WebServlet("/addUserServlet")
public class AddUser1 extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8"); 
        try (PrintWriter out = response.getWriter()) {
             
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String email = request.getParameter("email");
            String nome = request.getParameter("name");
            String tipoUser = request.getParameter("tipoUser");
            String nIdentify = request.getParameter("nIdentify");
            int tipo=-1;
        
             
             try{
               Class.forName("com.mysql.jdbc.Driver");
               Connection con = DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/vLGgz5ywmj","vLGgz5ywmj", "RrG1VumVxC");
               String sql = "INSERT INTO vLGgz5ywmj.user (username, password, email, tipo_user, n_identify, nome) VALUES (?, ?, ?, ?, ?, ?)";
                       
               PreparedStatement statement = con.prepareStatement(sql);
               statement.setString(1, username);
               statement.setString(2, password);
               statement.setString(3, email);
               statement.setString(4, tipoUser);
               statement.setString(5, nIdentify);
               statement.setString(6, nome);
               statement.executeUpdate();
               statement.close(); 
               
                User user = null;
                user = new User();
                user.setUsername(username);
                user.setPassword(password);
                user.setName(nome);
                user.setNIdentify(nIdentify);
                user.setEmail(email);
                
                String sql1 = "SELECT tipo_user FROM vLGgz5ywmj.user";
                Statement stat = con.createStatement();
                ResultSet rs= stat.executeQuery(sql1);
                    if(rs.next()){
                        tipo = rs.getInt(1);
                    }
                String destPage="";
                if(tipo ==0){
                    destPage = "user_a.html";
                }else{
                    destPage = "user.html";
                }
                con.close();
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
                dispatcher.forward(request, response);
               
           } catch (SQLException ex) {
                Logger.getLogger(AddUser1.class.getName()).log(Level.SEVERE, null, ex);
                
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(AddUser1.class.getName()).log(Level.SEVERE, null, ex);
            }
               
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

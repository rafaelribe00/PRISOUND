/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Dashboard;

import History.History;
import SerialComm.DadosSensor;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ASUS
 */
@WebServlet("/tableServlet")
public class Table extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            double decibeis, deci;
            String nome, name;
            ArrayList<String> array1 = new ArrayList();
            double db=0;
           
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/vLGgz5ywmj","vLGgz5ywmj", "RrG1VumVxC");
            String sql1 = "SELECT nome, db FROM vLGgz5ywmj.sensor WHERE data=(SELECT MAX(data) FROM vLGgz5ywmj.sensor WHERE data NOT IN (SELECT MAX(data) FROM vLGgz5ywmj.sensor))";
            Statement stat = con.createStatement();
            ResultSet rs= stat.executeQuery(sql1);
            while(rs.next()){
                nome = rs.getString(1);
                decibeis= rs.getInt(2);
                System.out.println(decibeis);
                
                String sql = "SELECT db FROM vLGgz5ywmj.sensor WHERE data=(SELECT MAX(data) FROM vLGgz5ywmj.sensor ) and nome='"+nome+"'";
                Statement stmt = con.createStatement();
                ResultSet r= stmt.executeQuery(sql);
                while(r.next()){
                    deci=r.getInt(1);
                    db=(((decibeis-deci)/deci)*100);
                    
                    System.out.println(db);
                    String json = "{\"Db\": \""+db+"\", \"Nome\": \""+nome+"\"}";
                    array1.add(json);
                }
                    
                } 
            System.out.println(array1);
            response.getWriter().println(array1);
            
            
            } catch (ClassNotFoundException ex) {
            Logger.getLogger(Table.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Table.class.getName()).log(Level.SEVERE, null, ex);
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

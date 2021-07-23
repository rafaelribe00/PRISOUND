/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Back;

import SerialComm.DadosSensor;
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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ASUS
 */
@WebServlet("/alertServlet")
public class Alert extends HttpServlet {

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
            
            int decibeis, dbMax, nSensor, alerta, alert;
            String nome, name;
            ArrayList<String> array1 = new ArrayList();
            
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/vLGgz5ywmj","vLGgz5ywmj", "RrG1VumVxC");
            String sql = "SELECT max_ruido, db, nome, n_sensor, alerta FROM vLGgz5ywmj.sensor WHERE data=(SELECT MAX(data) FROM vLGgz5ywmj.sensor WHERE local='A')";
            Statement stmt = con.createStatement();
            ResultSet r= stmt.executeQuery(sql);
                while(r.next()){
                    dbMax = r.getInt(1);
                    decibeis=r.getInt(2);
                    nome = r.getString(3);
                    nSensor = r.getInt(4);
                    alerta = r.getInt(5);
                    
                    if(decibeis>dbMax){
                        String sql1 = "UPDATE vLGgz5ywmj.sensor SET alerta='1' WHERE nome='"+nome+"' and n_sensor='"+nSensor+"'";
                        PreparedStatement statement = con.prepareStatement(sql1);
                        statement.executeUpdate();
                        statement.close();
                    }
                    
                    if(decibeis<=0){
                        String sql1 = "UPDATE vLGgz5ywmj.sensor SET alerta='2' WHERE nome='"+nome+"' and n_sensor='"+nSensor+"'";
                        PreparedStatement stat = con.prepareStatement(sql1);
                        stat.executeUpdate();
                        stat.close();
                    }
                    
                    String sql2 = "SELECT nome, alerta FROM vLGgz5ywmj.sensor WHERE n_sensor='"+nSensor+"'";
                    Statement stat = con.createStatement();
                    ResultSet rs= stat.executeQuery(sql2);
                    while(rs.next()){
                        name = rs.getString(1);
                        alert= rs.getInt(2);
                        String json = "{\"Alerta\": \""+alert+"\", \"Nome\": \""+name+"\"}";
                        array1.add(json);
                    }
                }  
                
                
             response.getWriter().println(array1);
             
        } catch (SQLException ex) {
            Logger.getLogger(Alert.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Alert.class.getName()).log(Level.SEVERE, null, ex);
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

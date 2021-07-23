/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package SerialComm;

import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import gnu.io.UnsupportedCommOperationException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rafael Ribeiro
 */
public class SerialDataInput implements SerialPortEventListener{
    
    //int iteradorBD = 25;
    
    SerialPort serialPort = null;
    
    private DadosSensor dados = new DadosSensor();
    private String appName;
    
    private BufferedReader input;
    
    private static final int TIME_OUT = 500;
    private static final int DATA_RATE = 9600;
    
    private String serialPortName = "COM3";
    
    public boolean initSerial(){
        
        boolean status = false;
        
        try {
            //Vai obter as serial ports do sistema e encontrar a COM3
            CommPortIdentifier portId = null;
            Enumeration portEnum = CommPortIdentifier.getPortIdentifiers();
            
            while(portId == null && portEnum.hasMoreElements()){
                CommPortIdentifier currPortId = (CommPortIdentifier) portEnum.nextElement();
                
                if(currPortId.getName().equals(serialPortName) || currPortId.getName().startsWith(serialPortName)){
                    serialPort = (SerialPort) currPortId.open(appName, TIME_OUT);
                    portId = currPortId;
                    System.out.println("Successfully opened the port");
                    break;
                }
            }
            
            if(portId == null || serialPort == null){
                return false;
            }
            
            serialPort.setSerialPortParams(DATA_RATE, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
            
            try{
              serialPort.enableReceiveTimeout(1000);
              serialPort.enableReceiveThreshold(0);
            } catch (UnsupportedCommOperationException e){
              // TODO Auto-generated catch block
              e.printStackTrace();
            }
            
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
            serialPort.addEventListener(this);
            serialPort.notifyOnDataAvailable(true);
            status = true;
           
        } catch (Exception e) {
            e.printStackTrace();
            status = false;
        }
        
        return status;
    }
    
    public synchronized void close(){
        if(serialPort != null){
            serialPort.removeEventListener();
            serialPort.close();
        }
    }
    
    @Override
    public void serialEvent(SerialPortEvent spe){
            
            //Este método lida com a chegada de dados através do serial
            try {
                switch(spe.getEventType()){
                    case SerialPortEvent.DATA_AVAILABLE:
                        if(input == null){
                            input = new BufferedReader(new InputStreamReader(serialPort.getInputStream()));
                        }
                        
                        if(input.ready()){
                            if(input.readLine().isEmpty()){
                                dados.setSensorData("1");
                                System.out.println(dados.getSensorData());
                            } else {
                                dados.setSensorData(input.readLine());
                                System.out.println(dados.getdB());
                                //if (iteradorBD == 30){
                                    Class.forName("com.mysql.jdbc.Driver");
                                    Connection con = DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/vLGgz5ywmj","vLGgz5ywmj", "RrG1VumVxC");
                                    String sql = "INSERT INTO vLGgz5ywmj.sensor (local, max_ruido, data, db, nome) VALUES ('"+dados.getLocal()+"', '"+dados.getdBMax()+"', '"+dados.getDate()+"', '"+dados.getdB()+"', '"+dados.getNome()+"')";
                                    PreparedStatement stat = con.prepareStatement(sql);
                                    stat.executeUpdate();
                                    stat.close();
                                    con.close();
                                    //iteradorBD = 0;
                                //}
                                //iteradorBD++;
                            }
                        }
                        
                        break;
                        
                    default:
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
    }  

    public DadosSensor getDadosSensor() {
        return dados;
    }

    public void setDadosSensor(DadosSensor dados) {
        this.dados = dados;
    }

    public String getSpName() {
        return serialPortName;
    }

    public void setSpName(String spName) {
        this.serialPortName = spName;
    }
    
}

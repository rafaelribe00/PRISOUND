/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package SerialComm;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rafael Ribeiro
 */
public class DadosSensor {
    private LocalDateTime date;
    private int dB;
    private String local = "A";
    private int dBMax = 60;
    private String nome = "AN1";
    
    private String sensorData;
    
    private void criaDadosSensor(){
        int valueSensorData = Integer.parseInt(sensorData);
        dB = valueSensorData;
        date = LocalDateTime.now();
    }
    
    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public int getdB() {
        return dB;
    }

    public void setdB(int dB) {
        this.dB = dB;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public int getdBMax() {
        return dBMax;
    }

    public void setdBMax(int dBMax) {
        this.dBMax = dBMax;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String getSensorData() {
        return sensorData;
    }

    public void setSensorData(String sensorData) {
        this.sensorData = sensorData;
        this.criaDadosSensor();
    }
}

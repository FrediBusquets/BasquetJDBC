/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author USER
 */
public class GrupoPosiciones {
  /*  : la media, el máximo y el mínimo
de canastas, asistencias y rebotes .*/
    private String position;
    private Double medCanastas;
    private int maxCanastas;
    private int minCanastas;   
    private Double medAsistencias;
    private int maxAsistencias;
    private int minAsistencias;  
    private Double medRebotes;
    private int maxRebotes;
    private int minRebotes;

    public GrupoPosiciones(String position, Double medCanastas, int maxCanastas, int minCanastas, Double medAsistencias, int maxAsistencias, int minAsistencias, Double medRebotes, int maxRebotes, int minRebotes) {
        this.position = position;
        this.medCanastas = medCanastas;
        this.maxCanastas = maxCanastas;
        this.minCanastas = minCanastas;
        this.medAsistencias = medAsistencias;
        this.maxAsistencias = maxAsistencias;
        this.minAsistencias = minAsistencias;
        this.medRebotes = medRebotes;
        this.maxRebotes = maxRebotes;
        this.minRebotes = minRebotes;
    }
    
    public GrupoPosiciones(){
    }
    @Override
    public String toString() {
        return "GrupoPosiciones{" + "position=" + position + ", medCanastas=" + medCanastas + ", maxCanastas=" + maxCanastas + ", minCanastas=" + minCanastas + ", medAsistencias=" + medAsistencias + ", maxAsistencias=" + maxAsistencias + ", minAsistencias=" + minAsistencias + ", medRebotes=" + medRebotes + ", maxRebotes=" + maxRebotes + ", minRebotes=" + minRebotes + '}';
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Double getMedCanastas() {
        return medCanastas;
    }

    public void setMedCanastas(Double medCanastas) {
        this.medCanastas = medCanastas;
    }

    public int getMaxCanastas() {
        return maxCanastas;
    }

    public void setMaxCanastas(int maxCanastas) {
        this.maxCanastas = maxCanastas;
    }

    public int getMinCanastas() {
        return minCanastas;
    }

    public void setMinCanastas(int minCanastas) {
        this.minCanastas = minCanastas;
    }

    public Double getMedAsistencias() {
        return medAsistencias;
    }

    public void setMedAsistencias(Double medAsistencias) {
        this.medAsistencias = medAsistencias;
    }

    public int getMaxAsistencias() {
        return maxAsistencias;
    }

    public void setMaxAsistencias(int maxAsistencias) {
        this.maxAsistencias = maxAsistencias;
    }

    public int getMinAsistencias() {
        return minAsistencias;
    }

    public void setMinAsistencias(int minAsistencias) {
        this.minAsistencias = minAsistencias;
    }

    public Double getMedRebotes() {
        return medRebotes;
    }

    public void setMedRebotes(Double medRebotes) {
        this.medRebotes = medRebotes;
    }

    public int getMaxRebotes() {
        return maxRebotes;
    }

    public void setMaxRebotes(int maxRebotes) {
        this.maxRebotes = maxRebotes;
    }

    public int getMinRebotes() {
        return minRebotes;
    }

    public void setMinRebotes(int minRebotes) {
        this.minRebotes = minRebotes;
    }
    
    
}

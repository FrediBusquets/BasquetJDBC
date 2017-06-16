/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.time.LocalDate;

/**
 *
 * @author USER
 */
public class Jugador {
    private String nombre;
    private LocalDate birthday;
    private int canastas;
    private int rebotes;
    private int asistencias;
    private String posicion;
    private Equipo equipo;
    
    public Jugador(){}

    public Jugador(String nombre, LocalDate birthday, int canastas, int rebotes, int asistencias, String posicion, Equipo equipo) {
        this.nombre = nombre;
        this.birthday = birthday;
        this.canastas = canastas;
        this.rebotes = rebotes;
        this.asistencias = asistencias;
        this.posicion = posicion;
        this.equipo = equipo;
    }

    public Jugador(Equipo equipo) {
        this.equipo = equipo;
    }

    public Jugador(int canastas, int rebotes, int asistencias) {
        this.canastas = canastas;
        this.rebotes = rebotes;
        this.asistencias = asistencias;
    }

    @Override
    public String toString() {
        return "Jugador{" + "nombre=" + nombre + ", birthday=" + birthday + ", canastas=" + canastas + ", rebotes=" + rebotes + ", asistencias=" + asistencias + ", posicion=" + posicion + ", equipo=" + equipo + '}';
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }


    public int getAsistencias() {
        return asistencias;
    }

    public void setAsistencias(int asistencias) {
        this.asistencias = asistencias;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCanastas() {
        return canastas;
    }

    public void setCanastas(int canastas) {
        this.canastas = canastas;
    }

    public int getRebotes() {
        return rebotes;
    }

    public void setRebotes(int rebotes) {
        this.rebotes = rebotes;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }
    
}

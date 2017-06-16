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
public class Equipo {
    private String nombre;
    private LocalDate fecha;
    private String localidad;
    
    public Equipo (){}

    public Equipo(String nombre, LocalDate fecha, String localidad) {
        this.nombre = nombre;
        this.fecha = fecha;
        this.localidad = localidad;
    }

    public Equipo(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    @Override
    public String toString() {
        return "Equipo{" + "nombre=" + nombre + ", fechan=" + fecha + ", localidad=" + localidad + '}';
    }
    
    
}

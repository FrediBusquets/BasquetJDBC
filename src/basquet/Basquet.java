/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basquet;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import model.Equipo;
import model.GrupoPosiciones;
import model.Jugador;
import model.Ranking;
import persistence.BasquetJDBC;

/**
 *
 * @author USER
 */
public class Basquet {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        BasquetJDBC basquetjdbc = new BasquetJDBC();
        try{
        basquetjdbc.conectar();
            System.out.println("Estableciendo conexion con el servidor");
            System.out.println(" ");
            Equipo barcelona = new Equipo("Barcelona",LocalDate.of(1973,5,17),"Barcelona");
            Equipo madrid = new Equipo("Real Madrid",LocalDate.of(1951,7,21),"Madrid");
            Equipo sevilla = new Equipo("Sevilla",LocalDate.of(1965,9,26),"Sevilla");
            Equipo atletico = new Equipo("Atletico de Madrid",LocalDate.of(1977,8,14),"Madrid");
            
            System.out.println("Añadiendo equipos");
            System.out.println(" ");
            basquetjdbc.insertarEquipo(barcelona);
            System.out.println("barcelona");
            basquetjdbc.insertarEquipo(madrid);
            System.out.println("madrid");
            basquetjdbc.insertarEquipo(sevilla);
            System.out.println("sevilla");
            basquetjdbc.insertarEquipo(atletico);
            System.out.println("atletico");
            System.out.println("Equipos Añadidos");
            System.out.println(" ");
            
            Jugador j1 = new Jugador("Alberto Perez",LocalDate.of(1989,3,6),120,43,55,"Pivot",madrid);
            Jugador j2 = new Jugador("Cristian Gomez",LocalDate.of(1985,5,1),50,55,42,"Base",barcelona);
            Jugador j3 = new Jugador("Alejandro Castro",LocalDate.of(1986,9,13),90,30,17,"Base",sevilla);
            Jugador j4 = new Jugador("Jorge Garcia",LocalDate.of(1991,11,20),70,65,31,"Pivot",atletico);
            Jugador j5 = new Jugador("Pedro Casas",LocalDate.of(1987,10,4),124,23,61,"Escolta",sevilla);
            Jugador j6 = new Jugador("Gerard Esteve",LocalDate.of(1986,4,7),46,102,48,"Alero",atletico);
            Jugador j7 = new Jugador("Fernando Rodriguez",LocalDate.of(1993,7,12),84,60,45,"Pivot",madrid);
            Jugador j8 = new Jugador("Javier Gomez",LocalDate.of(1989,7,18),132,16,35,"Base",barcelona);
            
            System.out.println("Añadiendo jugadores");
            System.out.println(" ");
           basquetjdbc.insertarJugador(j1);
            basquetjdbc.insertarJugador(j2);
            basquetjdbc.insertarJugador(j3);
            basquetjdbc.insertarJugador(j4);
            basquetjdbc.insertarJugador(j5);
            basquetjdbc.insertarJugador(j6);
            basquetjdbc.insertarJugador(j7);
            basquetjdbc.insertarJugador(j8);
            System.out.println("Jugadores Añadidos");
            
            System.out.println(" ");
            System.out.println("Modificar Jugador cansatas, asistencias y rebotes del jugador Alejandro Castro");
            
            System.out.println(" ");
            j3 = new Jugador("Alejandro Castro", LocalDate.of(1992,10, 25), 125, 80, 60, "Alero", madrid);
            basquetjdbc.modificarPlayer(j3);
            System.out.println("Datos del Jugador modificados"+j3.getNombre()+" Canastas:"+" Asistencias: "+j3.getAsistencias()+" Rebotes: "+j3.getRebotes());
            
            System.out.println(" ");
            System.out.println("Modificar Equipo del Jugador Pedro Casas");
            j5 = new Jugador("Pedro Casas",LocalDate.of(1987,10,4),124,23,61,"Escolta",madrid);           
            basquetjdbc.modificarEquipoJugador(madrid,"Pedro Casas");
            
            System.out.println(" ");
            System.out.println("Eliminar el Jugador Pedro Casas de la Base de datos ");
            basquetjdbc.borrarJugador("Pedro Casas");
            System.out.println("El Jugador a sido eliminado de la BBDD ");
            
            System.out.println(" ");
            System.out.println("Devolver Jugador Alejandro Castro ");
            j3 = basquetjdbc.buscarJugadorXNombre("Alejandro Castro");
            System.out.println(j3);
            
            System.out.println(" ");
            System.out.println("Devolver los Jugadores que tengan una a ");
            List<Jugador> jugadores = basquetjdbc.buscarJugadorXNombreIncompleto("a");
            for(Jugador r: jugadores){
            System.out.println(r);
            }
            
            System.out.println(" ");
            System.out.println("Listado de los jugadores con 90 canastas o mas: ");;
            jugadores = basquetjdbc.mayorOigualQue(90);
            for(Jugador r: jugadores){
            System.out.println(r);
            }
            System.out.println(" ");
            System.out.println("Listado de los jugadores con asistencias entre 35 y 60: ");
            jugadores = basquetjdbc.jugadorAsistencias(35, 60);
            for(Jugador r: jugadores){
            System.out.println(r);
            }
            
            System.out.println(" ");
            System.out.println("Listado de los jugadores que tienen la posición de Base:");
            jugadores = basquetjdbc.buscarJugadorXPosicion("Base");
            for(Jugador r: jugadores){
            System.out.println(r);
            }
            
            System.out.println(" ");
            System.out.println("Listado de los jugadores que han nacido antes del 16/03/1988:");
            jugadores = basquetjdbc.cumplenAntes(LocalDate.of(1988,3,16));
            for(Jugador r: jugadores){
            System.out.println(r);
            }
            
            System.out.println(" ");
            System.out.println("Devolver media, max y min de canastas, asistencias y rebotes agrupados por posición:");
            List<GrupoPosiciones> grupo = basquetjdbc.devolverJugadoresAgrupadosPorPosicion();
            for(GrupoPosiciones r: grupo){
            System.out.println(r);
            }
            
            System.out.println(" ");
            System.out.println("Mostrar ranking de canastas:");
            List<String> ranking = basquetjdbc.rankingDscCanastas();
            for(String r: ranking){
            System.out.println(r);
            }
            System.out.println(" ");
            System.out.println("Devolver la posición en el ranking de Alejandro Castro");
            int p = basquetjdbc.posicionJugadorRanking("Alejandro Castro");
            System.out.println("Posicion de Alejandro Castro en el ranking:  "+p);
            
            System.out.println(" ");        
            System.out.println("Listados de los equipos de Madrid:");
            List<Equipo> equipos = basquetjdbc.buscarEquiposPorLocalidad("Madrid");
            for(Equipo r: equipos){
            System.out.println(r);
            }
            
            System.out.println(" ");
            System.out.println("Listado de jugadores del equipo Madrid:");
            jugadores = basquetjdbc.mostrarJugadoresDeUnEquipo("Real Madrid");
            for(Jugador r: jugadores){
            System.out.println(r);
            }
            
            System.out.println(" ");
            System.out.println("Listado de jugadores del equipo Madrid que sean Pivot:");
            jugadores = basquetjdbc.mostrarJugadoresDeUnEquipoYMismaPosicion("Real Madrid", "Pivot");
            for(Jugador x: jugadores){
            System.out.println(x);
            }
            
            System.out.println(" ");
            System.out.println("Jugador del equipo Barcelona con más canástas:");
            j1 = basquetjdbc.maxCanstasEquipoDeterminado("Barcelona");
            System.out.println(j1);
            System.out.println(" ");       
            
        }catch (SQLException ex){
            System.out.println("Error con la BBDD: "+ ex.getMessage());
        }finally {
            try {
                basquetjdbc.desconectar();
                System.out.println("Conexión cerrada.");
            } catch (SQLException ex) {
                System.out.println("Error al desconectar "+ex.getMessage());
            }
        }
    }
    
}

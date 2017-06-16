/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Equipo;
import model.GrupoPosiciones;
import model.Jugador;
import model.Ranking;

/**
 *
 * @author USER
 */
public class BasquetJDBC {
    
    private Connection conexion;
        public void conectar() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/basket";
        String usr = "root";
        String pass = "";
        conexion = DriverManager.getConnection(url, usr, pass);
    }

    public void desconectar() throws SQLException {
        if (conexion != null) {
            conexion.close();
        }
    }
    public BasquetJDBC(){}
    // Metodo que recibe un Jugador y lo inserta a la BBDD
    public void insertarJugador(Jugador j) throws SQLException{
    String insert = "insert into player values (?,?,?,?,?,?,?)";
    PreparedStatement ps = conexion.prepareStatement (insert);
    ps.setString(1, j.getNombre());
    ps.setDate(2, java.sql.Date.valueOf(j.getBirthday()));
    ps.setInt(3, j.getCanastas());
    ps.setInt(4, j.getRebotes());
    ps.setInt(5, j.getAsistencias());
    ps.setString(6, j.getPosicion());
    ps.setString(7, j.getEquipo().getNombre());
    ps.executeUpdate();
    ps.close();
    }
    // Metodo que recibe un equipo y lo inserta a la BBDD
    public void insertarEquipo(Equipo e) throws SQLException{
    String insert = "insert into team values (?,?,?)";
    PreparedStatement ps = conexion.prepareStatement (insert);
    ps.setString(1, e.getNombre());    
    ps.setString(2, e.getLocalidad());
    ps.setDate(3, java.sql.Date.valueOf(e.getFecha()));
    ps.executeUpdate();
    ps.close();
    }
    //Metodo para modificar canastas, asistencias y rebotes de un jugador
    public void modificarPlayer(Jugador j)throws SQLException{
    String update = "update player set name=?, birth=?, nbaskets=?, nassists=?, nrebounds=?, position=?, team=? where name = '" + j.getNombre()+"';";
    PreparedStatement ps = conexion.prepareStatement(update);
    ps.setString(1, j.getNombre());
    ps.setDate(2, java.sql.Date.valueOf(j.getBirthday()));
    ps.setInt(3, j.getCanastas());
    ps.setInt(4, j.getRebotes());
    ps.setInt(5, j.getAsistencias());
    ps.setString(6, j.getPosicion());
    ps.setString(7, j.getEquipo().getNombre());
    ps.executeUpdate();
    ps.close();
    }
    //Metodo para modificar el Equipo de un jugador
    public void modificarEquipoJugador(Equipo e, String nombre) throws SQLException{
    String update = "update player set team=? where name= '"+nombre+"';";
    PreparedStatement ps = conexion.prepareStatement(update);
    ps.setString(1, e.getNombre());
    ps.executeUpdate();
    ps.close();
    }
    //Metodo para borrar un jugador
    public void borrarJugador(String nombre)throws SQLException{
        String delete = "delete from player where name='"+nombre+"';";
        Statement st = conexion.createStatement();
        st.executeUpdate(delete);
        st.close();
    }
    //Metodo para buscar jugador por el nombre
    public Jugador buscarJugadorXNombre(String nombre) throws SQLException{
    Jugador j = new Jugador();
    String query = "select * from player where name ='"+nombre+"';";
    Statement st = conexion.createStatement();    
    ResultSet rs = st.executeQuery(query);
    while(rs.next()){
    j.setNombre(rs.getString("name"));
    j.setBirthday(rs.getDate("birth").toLocalDate());
    j.setCanastas(rs.getInt("nbaskets"));
    j.setRebotes(rs.getInt("nrebounds"));
    j.setAsistencias(rs.getInt("nassists"));
    j.setPosicion(rs.getString("position"));
    Equipo e = new Equipo(rs.getString("team"));
    j.setEquipo(e);
    }
    st.close();
    rs.close();
    return j;
    }
    //Metodo para buscar al jugador por una parte del nombre
    public List<Jugador> buscarJugadorXNombreIncompleto(String nombre)throws SQLException{
            List<Jugador> jugadores = new ArrayList<>();
        String query = "select * from player where name LIKE '%"+nombre+"%';";
        Statement st = conexion.createStatement();
        ResultSet rs = st.executeQuery(query);
        while(rs.next()){
            Jugador j = new Jugador();
            Equipo e = new Equipo();
            j.setNombre(rs.getString("name"));
            j.setBirthday(rs.getDate("birth").toLocalDate());
            j.setCanastas(rs.getInt("nbaskets"));
            j.setAsistencias(rs.getInt("nassists"));
            j.setRebotes(rs.getInt("nrebounds"));
            j.setPosicion(rs.getString("position"));
            e.setNombre(rs.getString("team"));
            j.setEquipo(e);
            jugadores.add(j);
        }
        st.close();
        rs.close();
        return jugadores;
    }
      public List<Jugador> mayorOigualQue(int canastas) throws SQLException{
    List<Jugador> jugadores = new ArrayList<>();
    String query = "select * from player where nbaskets>="+canastas+";";
    PreparedStatement ps = conexion.prepareStatement(query);
    ResultSet rs = ps.executeQuery();
    while(rs.next()){
    Jugador j= new Jugador();       
    j.setNombre("name");
    j.setBirthday(rs.getDate("birth").toLocalDate());
    j.setCanastas(rs.getInt("nbaskets"));
    j.setRebotes(rs.getInt("nrebounds"));
    j.setAsistencias(rs.getInt("nassists"));
    j.setPosicion(rs.getString("position"));
    Equipo e = new Equipo(rs.getString("team"));
            j.setEquipo(e);
            jugadores.add(j);
    }
    ps.close();
    rs.close();
    return jugadores;
    }
 
    public List<Jugador> jugadorAsistencias(int asistencias1, int asistencias2) throws SQLException{
    List<Jugador> jugadores = new ArrayList<>();
    String query =  "select * from player where nassists>="+asistencias1+" && nassists<="+asistencias2+";";
    PreparedStatement ps = conexion.prepareStatement(query);
    ResultSet rs = ps.executeQuery();
    while(rs.next()){
    Jugador j= new Jugador();       
    j.setNombre("name");
    j.setBirthday(rs.getDate("birth").toLocalDate());
    j.setCanastas(rs.getInt("nbaskets"));
    j.setRebotes(rs.getInt("nrebounds"));
    j.setAsistencias(rs.getInt("nassists"));
    j.setPosicion(rs.getString("position"));
    Equipo e = new Equipo(rs.getString("team"));
            j.setEquipo(e);
            jugadores.add(j);
    }
    ps.close();
    rs.close();
    return jugadores;
    }
   public List<Jugador> buscarJugadorXPosicion(String posicion1) throws SQLException{
    List<Jugador> jugadores = new ArrayList<>();
    Jugador j = new Jugador();
    String query = "select * from player where position ='"+posicion1+"';";
    Statement st = conexion.createStatement();    
    ResultSet rs = st.executeQuery(query);
    while(rs.next()){
    j.setNombre(rs.getString("name"));
    j.setBirthday(rs.getDate("birth").toLocalDate());
    j.setCanastas(rs.getInt("nbaskets"));
    j.setRebotes(rs.getInt("nrebounds"));
    j.setAsistencias(rs.getInt("nassists"));
    j.setPosicion(rs.getString("position"));
    Equipo e = new Equipo(rs.getString("team"));
    j.setEquipo(e);
    jugadores.add(j);
    }
    st.close();
    rs.close();
    return jugadores;
    }
   
    public List<Jugador> cumplenAntes(LocalDate fechacumple) throws SQLException{
    List<Jugador> jugadores = new ArrayList<>();
    Jugador j = new Jugador();
    String query = "select * from player where birth<'"+java.sql.Date.valueOf(fechacumple)+"';";
    Statement st = conexion.createStatement();
    ResultSet rs = st.executeQuery(query);
    while(rs.next()){
    j.setNombre(rs.getString("name"));
    j.setBirthday(rs.getDate("birth").toLocalDate());
    j.setCanastas(rs.getInt("nbaskets"));
    j.setRebotes(rs.getInt("nrebounds"));
    j.setAsistencias(rs.getInt("nassists"));
    j.setPosicion(rs.getString("position"));
    Equipo e = new Equipo(rs.getString("team"));
    j.setEquipo(e);
    jugadores.add(j);
    }
    st.close();
    rs.close();
    return jugadores;
    }
    
    
    public List<GrupoPosiciones> devolverJugadoresAgrupadosPorPosicion() throws SQLException{
    List<GrupoPosiciones> grupos = new ArrayList<>();
        String query = "select position, max(nbaskets) as 'max1', min(nbaskets) as 'min1', avg(nbaskets) as "
                + "'avg1', max(nassists) as 'max2', min(nassists) as 'min2', avg(nassists) as 'avg2', max(nrebounds) as 'max3', "
                + "min(nrebounds) as 'min3', avg(nrebounds) as 'avg3' from player group by position;";
        Statement st = conexion.createStatement();
        ResultSet rs = st.executeQuery(query);
        while(rs.next()){
           GrupoPosiciones g = new GrupoPosiciones();
           g.setPosition(rs.getString("position"));
           g.setMaxCanastas(rs.getInt("max1"));
           g.setMinCanastas(rs.getInt("min1"));
           g.setMedCanastas(rs.getDouble("avg1"));
           g.setMaxAsistencias(rs.getInt("max2"));
           g.setMinAsistencias(rs.getInt("min2"));
           g.setMedAsistencias(rs.getDouble("avg2"));
           g.setMaxRebotes(rs.getInt("max3"));
           g.setMinRebotes(rs.getInt("min3"));
           g.setMedRebotes(rs.getDouble("avg3"));
           grupos.add(g);
        }
    st.close();
    rs.close();
    return grupos;
    }
        public List<String> rankingDscCanastas() throws SQLException {
        String query = "select name, nbaskets  from player order by nbaskets desc;";
        Statement st = conexion.createStatement();
        ResultSet rs = st.executeQuery(query);
        List<String> ranking = new ArrayList<>();
        int posicion = 1;
        while (rs.next()) {
        ranking.add(posicion+" "+rs.getString("name")+" "+rs.getInt("nbaskets"));
        posicion++;
        }
        rs.close();
        st.close();
        return ranking;
    }
    public int posicionJugadorRanking(String nombre) throws SQLException {
     int numeroR = 0;
     List<String> ranking = rankingDscCanastas();
     Map<String, String> jugadores = new HashMap<>();
        for(int i=0; i<ranking.size(); i++){
            String[] j = ranking.get(i).split(" ");
            jugadores.put(j[1], j[0]);
        }
        if(jugadores.containsKey(nombre)){
            numeroR = Integer.parseInt(jugadores.get(nombre));
        }
    return numeroR+1;
   }
    public List<Equipo> buscarEquiposPorLocalidad(String ciudad) throws SQLException{
    List<Equipo> equipos = new ArrayList();
    String query = "select * from team where city='"+ciudad+"';";
    Statement st = conexion.createStatement();    
    ResultSet rs = st.executeQuery(query);
    while(rs.next()){
        Equipo e = new Equipo();
    e.setNombre(rs.getString("name"));
    e.setLocalidad(rs.getString("city"));
    e.setFecha( rs.getDate("creation").toLocalDate());
    equipos.add(e);
    }
    st.close();
    rs.close();
    return equipos;
    }
    public List<Jugador> mostrarJugadoresDeUnEquipo(String equipo) throws SQLException{
    List<Jugador> jugadores = new ArrayList<>();
    Jugador j = new Jugador();
    String query = "select * from player where team ='"+equipo+"';";
    Statement st = conexion.createStatement();    
    ResultSet rs = st.executeQuery(query);
    while(rs.next()){
    j.setNombre(rs.getString("name"));
    j.setBirthday(rs.getDate("birth").toLocalDate());
    j.setCanastas(rs.getInt("nbaskets"));
    j.setRebotes(rs.getInt("nrebounds"));
    j.setAsistencias(rs.getInt("nassists"));
    j.setPosicion(rs.getString("position"));
    Equipo e = new Equipo(rs.getString("team"));
    j.setEquipo(e);
    jugadores.add(j);
    }
    st.close();
    rs.close();
    return jugadores;
    }
    public List<Jugador> mostrarJugadoresDeUnEquipoYMismaPosicion(String equipo,String posicion2) throws SQLException{
    List<Jugador> jugadores = new ArrayList<>();
    Jugador j = new Jugador();
    String query = "select * from player where team='"+equipo+"'and position='"+posicion2+"';";
    Statement st = conexion.createStatement();    
    ResultSet rs = st.executeQuery(query);
    while(rs.next()){
    j.setNombre(rs.getString("name"));
    j.setBirthday(rs.getDate("birth").toLocalDate());
    j.setCanastas(rs.getInt("nbaskets"));
    j.setRebotes(rs.getInt("nrebounds"));
    j.setAsistencias(rs.getInt("nassists"));
    j.setPosicion(rs.getString("position"));
    Equipo e = new Equipo(rs.getString("team"));
    j.setEquipo(e);
    jugadores.add(j);
    }
    st.close();
    rs.close();
    return jugadores;
    }
    public Jugador maxCanstasEquipoDeterminado(String equipo) throws SQLException{
    Jugador j = new Jugador();
    String query = "select * from player where team='"+equipo+"'and nbaskets=(select max(nbaskets) from player where team='"+equipo+"');";
    Statement st = conexion.createStatement();    
    ResultSet rs = st.executeQuery(query);
    while(rs.next()){
    j.setNombre(rs.getString("name"));
    j.setBirthday(rs.getDate("birth").toLocalDate());
    j.setCanastas(rs.getInt("nbaskets"));
    j.setRebotes(rs.getInt("nrebounds"));
    j.setAsistencias(rs.getInt("nassists"));
    j.setPosicion(rs.getString("position"));
    Equipo e = new Equipo(rs.getString("team"));
    j.setEquipo(e);
    }
    st.close();
    rs.close();
    return j;
    }
    
}

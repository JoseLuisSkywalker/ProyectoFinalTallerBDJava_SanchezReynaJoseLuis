/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import conexion.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelo.Paciente;

/**
 
 */

public class PacienteDAO {
    public static PacienteDAO instancia; 
    private ConexionBD conexionBD; 
    
    public PacienteDAO(){
        conexionBD = new ConexionBD(); 
    }
    
    public static PacienteDAO getInstancia(){
        
        if(instancia == null){
            instancia = new PacienteDAO();
            
        }
        
        return instancia; 
    }

    public boolean existePaciente(int idPaciente){
        String sql = "SELECT id_paciente FROM pacientes WHERE id_paciente = ?";
        ResultSet rs = null; 
        try{
            rs = conexionBD.ejecutarConsultaSQL(sql, idPaciente);
            return rs != null && rs.next(); 
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        } finally {
            try {
                if(rs != null) rs.close();
            }catch(SQLException e){
                
            }
        }
    }
 
    //==================== ALTAS DE PACIENTES ======================================= 
    public boolean agregarPaciente(Paciente paciente) throws SQLException{
        if(paciente == null){
            return false;
        }
        
        if(!hayMedicos()){
            return false; 
        }
        
        conexionBD.abrirConexion();
        
        try{
            conexionBD.getConexion().setAutoCommit(false);  // TRANSACCION: aqui se apaga el autocommit ************** 
            if(existePaciente(paciente.getIdPaciente())){
                conexionBD.getConexion().rollback(); 
                return false;
            }
            
            String sql = "INSERT INTO pacientes (id_paciente, nombre, apellido, telefono, fecha_nacimiento, sexo, estado_civil, fecha_registro, id_medico) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)"; 
           
        
            
            boolean resultado = conexionBD.ejecutarInstruccionLMD(sql, paciente.getIdPaciente(), paciente.getNombre(), paciente.getApellido(), paciente.getTelefono(),
            paciente.getFechaNacimiento(), paciente.getSexo(), paciente.getEstadoCivil(), paciente.getFechaRegistro(), paciente.getIdMedico());
                   
            if(!resultado){
                conexionBD.getConexion().rollback(); 
                return false; 
            }
                                                                        // -------------------------------------------------
            conexionBD.getConexion().commit();                          // TRANSACCION ****:  aqui se hace el commit
            return true;                                                // y en las condiciones estan los rollback para regresar
            
        } catch(Exception ex){
            try { conexionBD.getConexion().rollback(); } catch (Exception e){}
            return false;
        } finally {
            try { conexionBD.getConexion().setAutoCommit(true); } catch (Exception e){} // TRANSACCION: aqui se prende el autocommit ****** 
            conexionBD.cerrarConexion();
        }
    }
    
    
    public int obtenerTotalPacientes(){
        conexionBD.abrirConexion(); 
        ResultSet rs = null;
        try{
            String sql = "SELECT total_pacientes FROM estadisticas WHERE id = 1";           //********************
            rs = conexionBD.ejecutarConsultaSQL(sql);                                       // METODO QUE UTILIZO PARA LA TRANSACCION
                                                                                            // lo que hace es agarra la consulta de sql
            if(rs != null && rs.next()){                                                    //de la tabla estadisticas (la hice para esto)
            return rs.getInt("total_pacientes");                                            // y retorna el numero de sql en un int en java.
            }
        } catch (SQLException e) {
            e.printStackTrace();
            
        } finally {
            try {
                if (rs != null) rs.close();
                
            } catch (SQLException e){}
            conexionBD.cerrarConexion();
        }
        return 0; 
    }
    
    
    public int obtenerEdadPaciente(int idPaciente){
        conexionBD.abrirConexion();
        ResultSet rs = null;
        int edad = 0; 
        try{                                                                //************** metodo para la segunda FUNCION
            String sql = "SELECT edad_paciente(?) AS edad";                 //obtiene los daos de el nacimiento del paciente en sql
            rs = conexionBD.ejecutarConsultaSQL(sql, idPaciente);           //y aqui el metodo en java convierte el valor 
            if(rs != null && rs.next()){                                    //retornado de la funcion a un int de java
            edad = rs.getInt("edad");                                       //para poder plasmarlo en cuando se agrega un paciente
                                                                            //en altas.
                                                                                
        }
        } catch(SQLException e){
            e.printStackTrace();
        } finally {
            try { 
                if(rs != null){
                    rs.close();
                }
            } catch(SQLException e){
                e.printStackTrace();
            }
            conexionBD.cerrarConexion();
        }
        return edad;
    }
    //--------------------------- fin de altas ------------------------- 
    
    
    //=================== BAJAS =================================== 
    public boolean eliminarPaciente(int idPaciente){
        if(idPaciente <= 0){
            return false;
        }
        
        conexionBD.abrirConexion();
        try {
            if(!existePaciente(idPaciente)){
                return false; 
            }
        
            String sql = "CALL eliminar_paciente_proc(?)";                         //PROCEDIMIENTO 2: en sql el proc tiene el comando DELETE *****************
            boolean resultado = conexionBD.ejecutarInstruccionLMD(sql, idPaciente); //nomas hacemos el CALL para el procedimento
                                                                                   //aqui en el DAO
                                                                                   
            return resultado;
        } finally {  
            conexionBD.cerrarConexion();
        }
    }
    //---------------------- fin de bajas ------------------------ 
    
    //======================= MODIFICACIONES =========================== 
    
    public boolean modificarPaciente(Paciente paciente) {
        if (paciente == null) {
            return false;
        }

        conexionBD.abrirConexion();
        try {

            if (!existePaciente(paciente.getIdPaciente())) {
                return false;
            }

            String sql = "UPDATE pacientes SET nombre = ?, apellido = ?, telefono = ?, fecha_nacimiento = ?, "
                + "sexo = ?, estado_civil = ?, fecha_registro = ?, id_medico = ? "
                + "WHERE id_paciente = ?";

            boolean resultado = conexionBD.ejecutarInstruccionLMD(sql,paciente.getNombre(), paciente.getApellido(), paciente.getTelefono(), paciente.getFechaNacimiento(), paciente.getSexo(),
                    paciente.getEstadoCivil(), paciente.getFechaRegistro(), paciente.getIdMedico(), paciente.getIdPaciente());

            return resultado;

        } finally {
            conexionBD.cerrarConexion();
        }
    }   
    //-------------------- fin de modificaciones -----------------------
    
    
    public boolean hayMedicos() {
        conexionBD.abrirConexion();
        try {
            String sql = "SELECT COUNT(*) FROM medicos_cabecera";
            ResultSet rs = conexionBD.ejecutarConsultaSQL(sql);

            if(rs.next()){
                int total = rs.getInt(1);
                return total > 0;
            }
            return false;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        } finally {
            conexionBD.cerrarConexion();
        }
    }
    
    
    
    
    
}

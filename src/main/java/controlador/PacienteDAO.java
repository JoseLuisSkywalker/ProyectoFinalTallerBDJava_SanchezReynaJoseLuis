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
        
        conexionBD.abrirConexion();
        
        try{
            conexionBD.getConexion().setAutoCommit(false);  //parte de la transacción
            if(existePaciente(paciente.getIdPaciente())){
                conexionBD.getConexion().rollback(); // 1 rollback de la transacción
                return false;
            }
            
            String sql = "INSERT INTO pacientes (id_paciente, nombre, apellido, telefono, fecha_nacimiento, sexo, estado_civil, fecha_registro, id_medico) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)"; 
            
            boolean resultado = conexionBD.ejecutarInstruccionLMD(sql, paciente.getIdPaciente(), paciente.getNombre(), paciente.getApellido(), paciente.getTelefono(),
            paciente.getFechaNacimiento(), paciente.getSexo(), paciente.getEstadoCivil(), paciente.getFechaRegistro(), paciente.getIdMedico());
                   
            if(!resultado){
                conexionBD.getConexion().rollback(); // otro rollback de la transaccion
                return false; 
            }
            
            conexionBD.getConexion().commit(); //commit de la transaccion para el insert 
            return true; 
            
        } catch(Exception ex){
            try { conexionBD.getConexion().rollback(); } catch (Exception e){}
            return false;
        } finally {
            try { conexionBD.getConexion().setAutoCommit(true); } catch (Exception e){} // autocommits a la normalidad
            conexionBD.cerrarConexion();
        }
    }
    
    
    public int obtenerTotalPacientes(){
        conexionBD.abrirConexion(); 
        ResultSet rs = null;
        try{
            String sql = "SELECT total_pacientes FROM estadisticas WHERE id = 1";
            rs = conexionBD.ejecutarConsultaSQL(sql);
            
            if(rs != null && rs.next()){
            return rs.getInt("total_pacientes"); 
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
    //--------------------------- fin de altas ------------------------- 
    
    
    //=================== BAJAS =================================== 
    public boolean eliminarPaciente(int idPaciente){
        String sql = "CALL eliminar_paciente_proc(?)"; 
        conexionBD.abrirConexion(); 
        
        try{
            boolean resultado = conexionBD.ejecutarInstruccionLMD(sql, idPaciente);
            return resultado; 
            
        }finally{
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
    
    
    
}

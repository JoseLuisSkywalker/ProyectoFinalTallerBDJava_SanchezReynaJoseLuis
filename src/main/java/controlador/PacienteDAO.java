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
        
        try{
            ResultSet rs = conexionBD.ejecutarConsultaSQL(sql, idPaciente);
            return rs != null && rs.next(); 
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }
 
    //==================== ALTAS DE PACIENTES ======================================= 
    public boolean agregarPaciente(Paciente paciente){
        if(paciente == null){
            return false;
        }
        
        conexionBD.abrirConexion();
        
        try{
            if(existePaciente(paciente.getIdPaciente())){
                return false;
            }
            
            String sql = "INSERT INTO pacientes (id_paciente, nombre, apellido, telefono, fecha_nacimiento, sexo, estado_civil, fecha_registro, id_medico) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)"; 
            
            boolean resultado = conexionBD.ejecutarInstruccionLMD(sql, paciente.getIdPaciente(), paciente.getNombre(), paciente.getApellido(), paciente.getTelefono(),
            paciente.getFechaNacimiento(), paciente.getSexo(), paciente.getEstadoCivil(), paciente.getFechaRegistro(), paciente.getIdMedico());
                   
            return resultado;
            
        }finally{
            conexionBD.cerrarConexion();
        }
    }
    //--------------------------- fin de altas ------------------------- 
    
    
    //=================== BAJAS =================================== 
    public boolean eliminarPaciente(int idPaciente){
        String sql = "DELETE FROM pacientes WHERE id_paciente = ?"; 
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

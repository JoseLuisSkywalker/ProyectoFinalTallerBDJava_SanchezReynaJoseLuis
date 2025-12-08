/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import conexion.ConexionBD;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelo.Medico;



/**
 *
 * @author josesanchez
 */
public class MedicoDAO {

    public static MedicoDAO instancia; 
    private ConexionBD conexionBD; 
    
    public MedicoDAO(){
        conexionBD = new ConexionBD(); 
    }
    
    public static MedicoDAO getInstancia(){
        if(instancia == null){
           instancia = new MedicoDAO(); 
        }
        return instancia; 
    }
    //para verificar que exista el id del doc antes de agregar. 
    public boolean existeMedico(int idMedico) {
        String sql = "SELECT id_medico FROM medicos_cabecera WHERE id_medico = ?";
        
        ResultSet rs = conexionBD.ejecutarConsultaSQL(sql, idMedico); 
        
        try{
            return rs != null && rs.next();
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }
    
    //para insertar al doc en la tabla.
    
    public boolean agregarMedico(Medico medico){
        
        if (medico == null) return false; 
        
        conexionBD.abrirConexion();
        try{
            if(existeMedico(medico.getIdMedico())){
                return false;
            }
            
            String sql = "INSERT INTO medicos_cabecera (id_medico, nombre, apellido, numero_departamento, direccion, telefono)"
                    + "VALUES (?, ?, ?, ?, ?, ?)";
            
            boolean resultado = conexionBD.ejecutarInstruccionLMD(sql, medico.getIdMedico(), medico.getNombre(), medico.getApellido(), medico.getNumeroDepartamento(), medico.getDireccion(), medico.getTelefono());
            
            return resultado;
        } finally{
            conexionBD.cerrarConexion();
        }
        
    }
    
    public boolean eliminarMedico(int idMedico){
        if(idMedico <= 0){
            return false; 
        }
        
        conexionBD.abrirConexion(); 
        
        try{
            if(!existeMedico(idMedico)){
                return false; 
            }
            
            String sql = "CALL eliminar_medico_proc(?)"; 
            boolean resultado = conexionBD.ejecutarInstruccionLMD(sql, idMedico); 
            
            return resultado;
        }finally{
            conexionBD.cerrarConexion();
        }
    }
    
    public boolean modiifcarMedico(Medico medico){
        if (medico == null){
            return false; 
        }
        
        conexionBD.abrirConexion(); 
        try{
            if(!existeMedico(medico.getIdMedico())){
                return false; 
            }
            
            String sql = "UPDATE medicos_cabecera SET nombre = ?, apellido = ?, numero_departamento = ?, direccion = ?, telefono = ?"
                    + "WHERE id_medico = ?"; 
            
            boolean resultado = conexionBD.ejecutarInstruccionLMD(sql, medico.getNombre(), medico.getApellido(), medico.getNumeroDepartamento(),
                    medico.getDireccion(), medico.getTelefono(), medico.getIdMedico()); 
            
            return resultado;
            
        }finally{
            conexionBD.cerrarConexion();
        }
        
        
    }
    
    
    
    
    
    
}

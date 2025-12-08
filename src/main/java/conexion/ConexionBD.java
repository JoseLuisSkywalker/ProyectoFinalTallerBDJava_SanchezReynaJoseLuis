/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conexion;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConexionBD {

    private Connection conexion;
    
    // Datos de conexión
    private final String URL = "jdbc:postgresql://localhost:5432/wellmeadows_hospital";
    private final String USUARIO = "josesanchez";
    private final String PASSWORD = "boomboom";

    // Método para abrir la conexión
    public Connection abrirConexion() {
        try {
            // Cargar el driver manualmente (forma tradicional)
            Class.forName("org.postgresql.Driver");

            // Establecer conexión
            conexion = DriverManager.getConnection(URL, USUARIO, PASSWORD);
            System.out.println("Conexión exitosa a PostgreSQL.");

        } catch (ClassNotFoundException e) {
            System.out.println("Error: No se encontró el driver JDBC de PostgreSQL.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Error al conectar con la base de datos.");
            e.printStackTrace();
        }
        return conexion;
    }

    // Método para cerrar la conexión
    public void cerrarConexion() {
        if (conexion != null) {
            try {
                conexion.close();
                System.out.println("Conexión cerrada correctamente.");
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexión.");
                e.printStackTrace();
            }
        }
    }
    
    //Ejecuta las instrucciones
    public boolean ejecutarInstruccionLMD(String sql, Object... datos) {
        try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            for (int i = 0; i < datos.length; i++) {
                pstmt.setObject(i + 1, datos[i]);
            }
            return pstmt.executeUpdate() >= 1;
        } catch (SQLException e) {
            System.out.println("Error al ejecutar la instrucción LMD");
            e.printStackTrace();
            return false;
        }
    }
    
    
    //Resgresan un valor boolean. 
    public ResultSet ejecutarConsultaSQL(String sql, Object... datos) {
        try {
            PreparedStatement pstmt = conexion.prepareStatement(sql);
            for (int i = 0; i < datos.length; i++) {
                pstmt.setObject(i + 1, datos[i]);
            }
            return pstmt.executeQuery();
        } catch (SQLException e) {
            System.out.println("Error al ejecutar la consulta SQL");
            e.printStackTrace();
            return null;
        }
    }
    
    public Connection getConexion() {
    return conexion;
}

  
}

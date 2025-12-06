package modelo;

import javax.swing.table.AbstractTableModel;
import java.sql.*;

/*
 * Clase personalizada para el proyecto Hospital Wellmeadows.
 * Construida para funcionar con PostgreSQL en NetBeans y JTables.
 * Permite consultas normales y consultas con parámetros (filtros).
 */
public class ResultSetTableModel extends AbstractTableModel {

    private Connection conexion;
    private Statement instruccion;
    private ResultSet conjuntoResultados;
    private ResultSetMetaData metaDatos;
    private int numeroDeFilas;

    private boolean conectadoALaBaseDeDatos = false;

    // Constructor para consultas normales
    public ResultSetTableModel(String controlador, String url, String consulta)
            throws SQLException, ClassNotFoundException {

        Class.forName(controlador);

        conexion = DriverManager.getConnection(
                url,
                "josesanchez",          // USUARIO POSTGRES CORRECTO
                "boomboom"
        );

        instruccion = conexion.createStatement(
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY
        );

        conectadoALaBaseDeDatos = true;

        establecerConsulta(consulta);
    }

    // Constructor para consultas con parámetros (filtros dinámicos)
    public ResultSetTableModel(String controlador, String url, String consulta, Object... params)
            throws SQLException, ClassNotFoundException {

        Class.forName(controlador);

        conexion = DriverManager.getConnection(
                url,
                "josesanchez",
                "boomboom"
        );

        conectadoALaBaseDeDatos = true;

        establecerConsultaConParametros(consulta, params);
    }

    @Override
    public Class<?> getColumnClass(int columna) {
        if (!conectadoALaBaseDeDatos) {
            throw new IllegalStateException("No hay conexión a la base de datos");
        }

        try {
            String nombreClase = metaDatos.getColumnClassName(columna + 1);
            return Class.forName(nombreClase);
        } catch (Exception e) {
            e.printStackTrace();
            return Object.class;
        }
    }

    @Override
    public int getColumnCount() {
        if (!conectadoALaBaseDeDatos) {
            throw new IllegalStateException("No hay conexión a la base de datos");
        }

        try {
            return metaDatos.getColumnCount();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public String getColumnName(int columna) {
        if (!conectadoALaBaseDeDatos) {
            throw new IllegalStateException("No hay conexión a la base de datos");
        }

        try {
            return metaDatos.getColumnName(columna + 1);
        } catch (SQLException e) {
            e.printStackTrace();
            return "";
        }
    }

    @Override
    public int getRowCount() {
        if (!conectadoALaBaseDeDatos) {
            throw new IllegalStateException("No hay conexión a la base de datos");
        }

        return numeroDeFilas;
    }

    @Override
    public Object getValueAt(int fila, int columna) {
        if (!conectadoALaBaseDeDatos) {
            throw new IllegalStateException("No hay conexión a la base de datos");
        }

        try {
            conjuntoResultados.absolute(fila + 1);
            return conjuntoResultados.getObject(columna + 1);
        } catch (SQLException e) {
            e.printStackTrace();
            return "";
        }
    }

    // Consulta normal
    public void establecerConsulta(String consulta) throws SQLException {
        if (!conectadoALaBaseDeDatos) {
            throw new IllegalStateException("No hay conexión a la base de datos");
        }

        conjuntoResultados = instruccion.executeQuery(consulta);

        metaDatos = conjuntoResultados.getMetaData();

        conjuntoResultados.last();
        numeroDeFilas = conjuntoResultados.getRow();
        conjuntoResultados.beforeFirst();

        fireTableStructureChanged();
    }

    // Consulta con parámetros
    public void establecerConsultaConParametros(String consulta, Object... params)
            throws SQLException {

        if (!conectadoALaBaseDeDatos) {
            throw new IllegalStateException("No hay conexión a la base de datos");
        }

        if (conjuntoResultados != null) conjuntoResultados.close();
        if (instruccion != null) instruccion.close();

        PreparedStatement ps = conexion.prepareStatement(
                consulta,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY
        );

        for (int i = 0; i < params.length; i++) {
            ps.setObject(i + 1, params[i]);
        }

        conjuntoResultados = ps.executeQuery();

        metaDatos = conjuntoResultados.getMetaData();

        conjuntoResultados.last();
        numeroDeFilas = conjuntoResultados.getRow();
        conjuntoResultados.beforeFirst();

        fireTableStructureChanged();

        instruccion = ps;
    }

    // Cerrar conexión
    public void desconectarDeLaBaseDeDatos() {
        try {
            if (instruccion != null) instruccion.close();
            if (conexion != null) conexion.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conectadoALaBaseDeDatos = false;
        }
    }
}
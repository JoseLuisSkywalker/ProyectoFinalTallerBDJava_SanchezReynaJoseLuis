/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package vista;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import javax.swing.Timer;
import modelo.ResultSetTableModel;


import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import conexion.ConexionBD;
import java.io.FileOutputStream;
import java.sql.ResultSet;
import javax.swing.table.TableModel;



import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
/**
 *
 * @author josesanchez
 */
public class InternalVistas1 extends javax.swing.JInternalFrame {
    private final String driver = "org.postgresql.Driver";
    private final String url = "jdbc:postgresql://localhost:5432/wellmeadows_hospital";
    /**
     * Creates new form internalMedicos
     */
    public InternalVistas1() {
   
        
        initComponents();
        setSize(700, 500);        
        setResizable(false);
         
        cargarVista();
        
        
    }
    
    
    public void cargarVista(){
        try { 
            String consulta = "Select * FROM vista_medico_pacientes"; 
            ResultSetTableModel modelo = new ResultSetTableModel(driver, url, consulta);
            tablaVista.setModel(modelo);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error, no se cargo la vista correctamente");
            e.printStackTrace();
        }
    }
    
     
    //GENERADOR DE REPORTE el que use es el iText 5, que porque sabe que de que es de maven
    //y esta aqui mismo en Netbeans para descargar luego luego
    
    public void generarReportePDF() {

        Document documento = new Document();

        try {
            String rutaPDF = System.getProperty("user.home") + "/Desktop/ReporteMedicos.pdf";
            PdfWriter.getInstance(documento, new FileOutputStream(rutaPDF));
            documento.open();

            documento.add(new Paragraph("REPORTE VISTA MEDICO-PACIENTES"));
            documento.add(new Paragraph("------------------------------------------------------"));
            documento.add(new Paragraph("Fecha de generación: " + java.time.LocalDateTime.now()));
            documento.add(new Paragraph("\n"));

    
            TableModel modelo = tablaVista.getModel();

            StringBuilder encabezados = new StringBuilder();
            for (int i = 0; i < modelo.getColumnCount(); i++) {
                encabezados.append(modelo.getColumnName(i)).append(" | ");
            }
            documento.add(new Paragraph(encabezados.toString()));
            documento.add(new Paragraph("------------------------------------------------------"));

        
            for (int fila = 0; fila < modelo.getRowCount(); fila++) {

                StringBuilder filaTexto = new StringBuilder();

                for (int col = 0; col < modelo.getColumnCount(); col++) {
                    Object valor = modelo.getValueAt(fila, col);

                    if (valor == null) {
                        filaTexto.append("NULL");
                    } else {
                        filaTexto.append(valor.toString());
                    }

                    filaTexto.append(" | ");
                }

                documento.add(new Paragraph(filaTexto.toString()));
            }

            documento.close();
            JOptionPane.showMessageDialog(this, "PDF generado");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "ERROR: ");
            e.printStackTrace();
        }
    }
    
    
    public void generarGraficaTop2() {
        ConexionBD conexionBD = new ConexionBD();
        conexionBD.abrirConexion();

        ResultSet rs = null;

        try {
            String sql = "SELECT id_medico, apellido_medico, COUNT(*) AS total FROM vista_medico_pacientes GROUP BY id_medico, apellido_medico ORDER BY total DESC LIMIT 2";

            rs = conexionBD.ejecutarConsultaSQL(sql);

            DefaultCategoryDataset dataset = new DefaultCategoryDataset();

            while (rs.next()) {
                String nombreMedico = rs.getString("apellido_medico");
                int totalPac = rs.getInt("total");
                dataset.addValue(totalPac, "Pacientes", nombreMedico);
            }

            JFreeChart grafica = ChartFactory.createBarChart("Top 2 Medicos con más Pacientes", "Medico", "Numero de Pacientes", dataset);

            ChartPanel panel = new ChartPanel(grafica);

            javax.swing.JFrame ventana = new javax.swing.JFrame("Gráfica Médicos");
            ventana.setSize(700, 500);
            ventana.add(panel);
            ventana.setVisible(true);

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al generar la gráfica");
        } finally {
        
            try {
                if (rs != null) rs.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            conexionBD.cerrarConexion();
        }
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scroll = new javax.swing.JScrollPane();
        tablaVista = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(120, 0, 0));
        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setTitle("Vista 1");
        setToolTipText("");
        setMaximumSize(new java.awt.Dimension(700, 700));
        setMinimumSize(new java.awt.Dimension(700, 700));
        setNormalBounds(new java.awt.Rectangle(0, 0, 700, 700));
        setPreferredSize(new java.awt.Dimension(700, 700));
        setSize(new java.awt.Dimension(700, 700));
        setVisible(true);
        getContentPane().setLayout(null);

        scroll.setBackground(new java.awt.Color(51, 0, 0));
        scroll.setBorder(null);
        scroll.setForeground(new java.awt.Color(51, 51, 51));

        tablaVista.setBackground(new java.awt.Color(100, 0, 0));
        tablaVista.setForeground(new java.awt.Color(255, 255, 255));
        tablaVista.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Id Médico", "Apellido (médico)", "Id paciente", "Nombre", "Apellido"
            }
        ));
        scroll.setViewportView(tablaVista);

        getContentPane().add(scroll);
        scroll.setBounds(30, 90, 610, 310);

        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Vista: Cada Paciente que tiene un médico de cabecera");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(30, 30, 630, 40);

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane scroll;
    private javax.swing.JTable tablaVista;
    // End of variables declaration//GEN-END:variables
}

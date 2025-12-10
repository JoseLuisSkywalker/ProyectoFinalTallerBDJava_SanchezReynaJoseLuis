/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package vista;

import java.awt.Frame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

/**
 *
 * @author josesanchez
 */
public class Progreso extends javax.swing.JDialog {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Progreso.class.getName());

    /**
     * Creates new form Progreso
     */
    public Progreso(Frame parent, boolean modal) {
        
        super(parent, modal);
        initComponents();
        configurarDialogo(); 
        setSize(500, 120);
    }
    
    private void configurarDialogo() {
        setLocationRelativeTo(null);   // Centrar
        jProgressBar1.setStringPainted(true);   // Mostrar porcentaje
        jProgressBar1.setMinimum(0);
        jProgressBar1.setMaximum(100);
    }
    
    public void setProgreso(int valor) {
        jProgressBar1.setValue(valor);
    }

    public void iniciar() {
        setVisible(true);
    }

    public void finalizar() {
        setVisible(false);
        dispose();
    }

    public JProgressBar getBarra() {
        return jProgressBar1;
    }
   
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jProgressBar1 = new javax.swing.JProgressBar();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Procesando...");
        setBackground(new java.awt.Color(0, 30, 0));
        getContentPane().setLayout(null);

        jProgressBar1.setForeground(new java.awt.Color(0, 130, 0));
        getContentPane().add(jProgressBar1);
        jProgressBar1.setBounds(20, 40, 470, 70);

        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jLabel1.setText("Buscando todos los pacientes del médico...");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(20, 0, 300, 40);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     
    */
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JProgressBar jProgressBar1;
    // End of variables declaration//GEN-END:variables
}

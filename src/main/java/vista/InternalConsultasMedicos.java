package vista;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import modelo.ResultSetTableModel;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */

/**
 *
 * @author josesanchez
 */
public class InternalConsultasMedicos extends javax.swing.JInternalFrame {
    
    
    
    //[[[[[[[[[[[[[[[[[[[[ codigo nuevo (instancias) en proceso
    private ResultSetTableModel modelo;
    private String driver = "org.postgresql.Driver";
    private String url = "jdbc:postgresql://localhost:5432/wellmeadows_hospital";
    
    ButtonGroup group;
   
    private String campoSeleccionado = "id_medico"; //este es el campo para hacer la consulta segun el radio button
    //[[[[[[[[[[[[[[[[[[[[ fin de codigo nuevo (instancias) fin

    //constructor
    public InternalConsultasMedicos() {
        initComponents();
        setSize(700, 700);        
        setResizable(false);
        agregarListenerDeBusqueda();
        cargarTodosLosMedicos(tablaConsultasMedicos); 
        habilitarCampos();
        
        
        group = new ButtonGroup();
        group.add(rbtnID); 
        group.add(rbtnNombre); 
        group.add(rbtnApellido);
        group.add(rbtnDepartamento); 
        group.add(rbtnDireccion);
        group.add(rbtnTelefono);
        
        rbtnID.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
            habilitarCampos();
            }  
        }); 
        rbtnNombre.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
            habilitarCampos();
            }  
        });
        rbtnApellido.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
            habilitarCampos();
            }  
        });
        rbtnDireccion.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
            habilitarCampos();
            }  
        });
        rbtnTelefono.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
            habilitarCampos();
            }  
        });
        rbtnDepartamento.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
            habilitarCampos();
            }  
        });
        habilitarCampos();
    }
    //fin del constructor 
    
    
    //[[[[[[[[[[[[[[[[[[ codigo nuevo en proceso (métodos)
    
    private void cargarTodosLosMedicos(javax.swing.JTable tablaMedicos) {
        try {
            String sql = "SELECT id_medico, nombre, apellido, numero_departamento, direccion, telefono FROM medicos_cabecera";
            modelo = new ResultSetTableModel(driver, url, sql);
            tablaMedicos.setModel(modelo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
  
    
    public void filtrarMedicos(String campo, String valor, javax.swing.JTable tablaMedicos) {
        try {
            String sql = "SELECT id_medico, nombre, apellido, numero_departamento, direccion, telefono FROM medicos_cabecera WHERE "
                         + campo + " ILIKE '%" + valor + "%'";
            modelo = new ResultSetTableModel(driver, url, sql);
            tablaMedicos.setModel(modelo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void habilitarCampos() {

        campoIdMedicoConsultas.setEnabled(false);
        campoNombreMedicoConsultas.setEnabled(false);
        campoApellidoMedicoConsultas.setEnabled(false);
        campoTelefonoMedicoConsultas.setEnabled(false);
        campoDireccionMedicoConsultas.setEnabled(false);
        comboMedicoDepartamentoConsultas.setEnabled(false);

        if (rbtnID.isSelected()){
            campoIdMedicoConsultas.setEnabled(true);
        } else if (rbtnNombre.isSelected()){
            campoNombreMedicoConsultas.setEnabled(true);
        } else if (rbtnApellido.isSelected()){
            campoApellidoMedicoConsultas.setEnabled(true);
        } else if (rbtnDireccion.isSelected()){
            campoDireccionMedicoConsultas.setEnabled(true);
        } else if (rbtnTelefono.isSelected()){
            campoTelefonoMedicoConsultas.setEnabled(true);
        } else if (rbtnDepartamento.isSelected()){
            comboMedicoDepartamentoConsultas.setEnabled(true);
        }
    
    }
    //
    private void buscarMedicos(){
        try{
            String sqlBase = "SELECT id_medico, nombre, apellido, numero_departamento, direccion, telefono FROM medicos_cabecera";
            String where = "";
            String valor = ""; 
            
            if(rbtnID.isSelected()){
                valor = campoIdMedicoConsultas.getText().trim(); 
                if(!valor.isEmpty()){
                    where = " WHERE CAST(id_medico AS TEXT) LIKE '%" + valor + "%'";
                    
                }
            }else if(rbtnNombre.isSelected()){
                valor = campoNombreMedicoConsultas.getText().trim(); 
                if(!valor.isEmpty()){
                    where = " WHERE nombre ILIKE '%" + valor + "%'"; 
                    
                }
            }else if(rbtnApellido.isSelected()){
                valor = campoApellidoMedicoConsultas.getText().trim(); 
                if(!valor.isEmpty()){
                    where = " WHERE apellido ILIKE '%" + valor + "%'"; 
                    
                }
            }else if(rbtnDepartamento.isSelected()){
                valor = comboMedicoDepartamentoConsultas.getSelectedItem().toString(); 
                if(!valor.equals("Seleccionar...")){
                    where = " WHERE CAST(numero_departamento AS TEXT) LIKE '%" + valor + "%'"; 
                }
            }else if(rbtnDireccion.isSelected()){
                valor = campoDireccionMedicoConsultas.getText().trim();
                if(!valor.isEmpty()){
                    where = " WHERE direccion ILIKE '%" + valor + "%'"; 
                }
            }else if(rbtnTelefono.isSelected()){
                valor = campoTelefonoMedicoConsultas.getText().trim(); 
                if(!valor.isEmpty()){
                    where = " WHERE CAST(telefono AS TEXT) LIKE '%" + campoTelefonoMedicoConsultas.getText().trim() + "%'";
                }
            }
            
            String sqlFinal = sqlBase + where; 
            modelo = new ResultSetTableModel(driver, url, sqlFinal); 
            tablaConsultasMedicos.setModel(modelo); 
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
        
    
    
    private void agregarListenerDeBusqueda() {

        campoIdMedicoConsultas.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent e) {
                buscarMedicos();
            }
        });

        campoNombreMedicoConsultas.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent e) {
                buscarMedicos();
            }   
        });

        campoApellidoMedicoConsultas.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent e) {
                buscarMedicos();
            }
        });

        campoDireccionMedicoConsultas.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent e) {
                buscarMedicos();
            }
        });

        campoTelefonoMedicoConsultas.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent e) {
                buscarMedicos();
            }
        });

        comboMedicoDepartamentoConsultas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarMedicos();
            }
        });
    }
    
    
    //metodo restablecer
    
    private void restablecerCampos() {
        try {
       
            campoIdMedicoConsultas.setText("");
            campoNombreMedicoConsultas.setText("");
            campoApellidoMedicoConsultas.setText("");
            campoDireccionMedicoConsultas.setText("");
            campoTelefonoMedicoConsultas.setText("");
            comboMedicoDepartamentoConsultas.setSelectedIndex(0);
            group.clearSelection();
            campoIdMedicoConsultas.setEnabled(false);
            campoNombreMedicoConsultas.setEnabled(false);
            campoApellidoMedicoConsultas.setEnabled(false);
            campoDireccionMedicoConsultas.setEnabled(false);
            campoTelefonoMedicoConsultas.setEnabled(false);
            comboMedicoDepartamentoConsultas.setEnabled(false); 
            cargarTodosLosMedicos(tablaConsultasMedicos);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void refrescarTabla(){
        cargarTodosLosMedicos(tablaConsultasMedicos);
    }
       
    
    
    public void soloNumeros(java.awt.event.KeyEvent evt) {
        char c = evt.getKeyChar();
            if (!Character.isDigit(c) && c != '\b') {
            evt.consume();
            JOptionPane.showMessageDialog(this, "Solo se permiten números!");
        }
    }

    public void soloLetras(java.awt.event.KeyEvent evt) {
        char c = evt.getKeyChar();
        if (!Character.isLetter(c) && !Character.isWhitespace(c) && c != '\b') {
            evt.consume();
            JOptionPane.showMessageDialog(this, "Solo se permiten letras.");
        }
    }
    
    public void limitarCaracteres(java.awt.event.KeyEvent evt, javax.swing.JTextField campo, int max) {
        if (campo.getText().length() >= max) {
            evt.consume();
            JOptionPane.showMessageDialog(this, "Máximo permitido: " + max + " caracteres.");
        }
    }
    
    public void limitarSoloDiez(javax.swing.JTextField campo, java.awt.event.KeyEvent evt) {
        if (campo.getText().length() >= 10) {
            evt.consume();
            JOptionPane.showMessageDialog(this, "10 digitos para el telefono, no mas, no menos!");
        }
    }
     
    //[[[[[[[[[[[[[[[[[ fin codigo nuevo en proceso (métodos) 

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel17 = new javax.swing.JLabel();
        campoIdMedicoConsultas = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        campoNombreMedicoConsultas = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        campoApellidoMedicoConsultas = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        comboMedicoDepartamentoConsultas = new javax.swing.JComboBox<>();
        jLabel21 = new javax.swing.JLabel();
        campoDireccionMedicoConsultas = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        campoTelefonoMedicoConsultas = new javax.swing.JTextField();
        btnRestablecerMedicosConsultas = new javax.swing.JButton();
        rbtnID = new javax.swing.JRadioButton();
        rbtnNombre = new javax.swing.JRadioButton();
        rbtnApellido = new javax.swing.JRadioButton();
        rbtnDepartamento = new javax.swing.JRadioButton();
        rbtnDireccion = new javax.swing.JRadioButton();
        rbtnTelefono = new javax.swing.JRadioButton();
        jScrollMedicos = new javax.swing.JScrollPane();
        tablaConsultasMedicos = new javax.swing.JTable();

        setBackground(new java.awt.Color(120, 0, 0));
        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setBounds(new java.awt.Rectangle(0, 0, 700, 350));
        setMaximumSize(new java.awt.Dimension(700, 350));
        setMinimumSize(new java.awt.Dimension(700, 350));
        setPreferredSize(new java.awt.Dimension(700, 350));
        getContentPane().setLayout(null);

        jLabel17.setBackground(new java.awt.Color(110, 46, 46));
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Id del Médico");
        getContentPane().add(jLabel17);
        jLabel17.setBounds(70, 10, 90, 20);

        campoIdMedicoConsultas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoIdMedicoConsultasActionPerformed(evt);
            }
        });
        campoIdMedicoConsultas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                campoIdMedicoConsultasKeyTyped(evt);
            }
        });
        getContentPane().add(campoIdMedicoConsultas);
        campoIdMedicoConsultas.setBounds(190, 10, 290, 20);

        jLabel18.setBackground(new java.awt.Color(110, 46, 46));
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Nombre");
        getContentPane().add(jLabel18);
        jLabel18.setBounds(70, 50, 60, 20);

        campoNombreMedicoConsultas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoNombreMedicoConsultasActionPerformed(evt);
            }
        });
        campoNombreMedicoConsultas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                campoNombreMedicoConsultasKeyTyped(evt);
            }
        });
        getContentPane().add(campoNombreMedicoConsultas);
        campoNombreMedicoConsultas.setBounds(150, 50, 330, 20);

        jLabel19.setBackground(new java.awt.Color(110, 46, 46));
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Apellido");
        getContentPane().add(jLabel19);
        jLabel19.setBounds(70, 90, 60, 20);

        campoApellidoMedicoConsultas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoApellidoMedicoConsultasActionPerformed(evt);
            }
        });
        campoApellidoMedicoConsultas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                campoApellidoMedicoConsultasKeyTyped(evt);
            }
        });
        getContentPane().add(campoApellidoMedicoConsultas);
        campoApellidoMedicoConsultas.setBounds(150, 90, 330, 20);

        jLabel20.setBackground(new java.awt.Color(110, 46, 46));
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("Número del Departamento");
        getContentPane().add(jLabel20);
        jLabel20.setBounds(70, 130, 180, 20);

        comboMedicoDepartamentoConsultas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17" }));
        comboMedicoDepartamentoConsultas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboMedicoDepartamentoConsultasActionPerformed(evt);
            }
        });
        getContentPane().add(comboMedicoDepartamentoConsultas);
        comboMedicoDepartamentoConsultas.setBounds(260, 130, 120, 23);

        jLabel21.setBackground(new java.awt.Color(110, 46, 46));
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("Dirección");
        getContentPane().add(jLabel21);
        jLabel21.setBounds(70, 170, 70, 20);

        campoDireccionMedicoConsultas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoDireccionMedicoConsultasActionPerformed(evt);
            }
        });
        campoDireccionMedicoConsultas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                campoDireccionMedicoConsultasKeyTyped(evt);
            }
        });
        getContentPane().add(campoDireccionMedicoConsultas);
        campoDireccionMedicoConsultas.setBounds(160, 170, 370, 20);

        jLabel22.setBackground(new java.awt.Color(110, 46, 46));
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("Telefóno");
        getContentPane().add(jLabel22);
        jLabel22.setBounds(70, 210, 70, 20);

        campoTelefonoMedicoConsultas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoTelefonoMedicoConsultasActionPerformed(evt);
            }
        });
        campoTelefonoMedicoConsultas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                campoTelefonoMedicoConsultasKeyTyped(evt);
            }
        });
        getContentPane().add(campoTelefonoMedicoConsultas);
        campoTelefonoMedicoConsultas.setBounds(150, 210, 380, 20);

        btnRestablecerMedicosConsultas.setText("Restablecer");
        btnRestablecerMedicosConsultas.setToolTipText("Restablecer");
        btnRestablecerMedicosConsultas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRestablecerMedicosConsultasActionPerformed(evt);
            }
        });
        getContentPane().add(btnRestablecerMedicosConsultas);
        btnRestablecerMedicosConsultas.setBounds(550, 30, 110, 30);

        rbtnID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtnIDActionPerformed(evt);
            }
        });
        getContentPane().add(rbtnID);
        rbtnID.setBounds(30, 10, 30, 20);

        rbtnNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtnNombreActionPerformed(evt);
            }
        });
        getContentPane().add(rbtnNombre);
        rbtnNombre.setBounds(30, 50, 30, 19);

        rbtnApellido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtnApellidoActionPerformed(evt);
            }
        });
        getContentPane().add(rbtnApellido);
        rbtnApellido.setBounds(30, 90, 30, 20);

        rbtnDepartamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtnDepartamentoActionPerformed(evt);
            }
        });
        getContentPane().add(rbtnDepartamento);
        rbtnDepartamento.setBounds(30, 130, 30, 19);

        rbtnDireccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtnDireccionActionPerformed(evt);
            }
        });
        getContentPane().add(rbtnDireccion);
        rbtnDireccion.setBounds(30, 170, 30, 20);

        rbtnTelefono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtnTelefonoActionPerformed(evt);
            }
        });
        getContentPane().add(rbtnTelefono);
        rbtnTelefono.setBounds(30, 210, 30, 20);

        jScrollMedicos.setBackground(new java.awt.Color(51, 0, 0));
        jScrollMedicos.setBorder(null);
        jScrollMedicos.setForeground(new java.awt.Color(51, 51, 51));

        tablaConsultasMedicos.setBackground(new java.awt.Color(100, 0, 0));
        tablaConsultasMedicos.setForeground(new java.awt.Color(255, 255, 255));
        tablaConsultasMedicos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID Médico", "Nombre", "Apellido", "Departamento", "Dirección", "Teléfono"
            }
        ));
        jScrollMedicos.setViewportView(tablaConsultasMedicos);

        getContentPane().add(jScrollMedicos);
        jScrollMedicos.setBounds(30, 290, 610, 310);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void campoIdMedicoConsultasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoIdMedicoConsultasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoIdMedicoConsultasActionPerformed

    private void campoNombreMedicoConsultasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoNombreMedicoConsultasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoNombreMedicoConsultasActionPerformed

    private void campoApellidoMedicoConsultasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoApellidoMedicoConsultasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoApellidoMedicoConsultasActionPerformed

    private void comboMedicoDepartamentoConsultasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboMedicoDepartamentoConsultasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboMedicoDepartamentoConsultasActionPerformed

    private void campoDireccionMedicoConsultasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoDireccionMedicoConsultasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoDireccionMedicoConsultasActionPerformed

    private void campoTelefonoMedicoConsultasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoTelefonoMedicoConsultasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoTelefonoMedicoConsultasActionPerformed

    private void btnRestablecerMedicosConsultasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRestablecerMedicosConsultasActionPerformed
        // TODO add your handling code here:
       restablecerCampos();
    }//GEN-LAST:event_btnRestablecerMedicosConsultasActionPerformed

    private void rbtnIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtnIDActionPerformed
        // TODO add your handling code here:
        campoSeleccionado = "id_medico"; 
        
        
    }//GEN-LAST:event_rbtnIDActionPerformed

    private void rbtnNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtnNombreActionPerformed
        // TODO add your handling code here:
        campoSeleccionado = "nombre";
    }//GEN-LAST:event_rbtnNombreActionPerformed

    private void rbtnApellidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtnApellidoActionPerformed
        // TODO add your handling code here:
        campoSeleccionado = "apellido"; 
    }//GEN-LAST:event_rbtnApellidoActionPerformed

    private void rbtnDepartamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtnDepartamentoActionPerformed
        // TODO add your handling code here:
        campoSeleccionado = "numero_departamento";
    }//GEN-LAST:event_rbtnDepartamentoActionPerformed

    private void rbtnDireccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtnDireccionActionPerformed
        // TODO add your handling code here:
        campoSeleccionado = "direccion"; 
    }//GEN-LAST:event_rbtnDireccionActionPerformed

    private void rbtnTelefonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtnTelefonoActionPerformed
        // TODO add your handling code here:
        campoSeleccionado = "telefono"; 
    }//GEN-LAST:event_rbtnTelefonoActionPerformed

    private void campoIdMedicoConsultasKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoIdMedicoConsultasKeyTyped
        // TODO add your handling code here:
        soloNumeros(evt);
    }//GEN-LAST:event_campoIdMedicoConsultasKeyTyped

    private void campoNombreMedicoConsultasKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoNombreMedicoConsultasKeyTyped
        // TODO add your handling code here:
        soloLetras(evt);
        limitarCaracteres(evt, campoNombreMedicoConsultas, 45);
        
    }//GEN-LAST:event_campoNombreMedicoConsultasKeyTyped

    private void campoApellidoMedicoConsultasKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoApellidoMedicoConsultasKeyTyped
        // TODO add your handling code here:
        soloLetras(evt);
        limitarCaracteres(evt, campoApellidoMedicoConsultas, 45);
        
    }//GEN-LAST:event_campoApellidoMedicoConsultasKeyTyped

    private void campoDireccionMedicoConsultasKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoDireccionMedicoConsultasKeyTyped
        limitarCaracteres(evt, campoDireccionMedicoConsultas, 45);
    }//GEN-LAST:event_campoDireccionMedicoConsultasKeyTyped

    private void campoTelefonoMedicoConsultasKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoTelefonoMedicoConsultasKeyTyped
        // TODO add your handling code here:
        soloNumeros(evt);
        limitarSoloDiez(campoTelefonoMedicoConsultas, evt);
    }//GEN-LAST:event_campoTelefonoMedicoConsultasKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRestablecerMedicosConsultas;
    private javax.swing.JTextField campoApellidoMedicoConsultas;
    private javax.swing.JTextField campoDireccionMedicoConsultas;
    private javax.swing.JTextField campoIdMedicoConsultas;
    private javax.swing.JTextField campoNombreMedicoConsultas;
    private javax.swing.JTextField campoTelefonoMedicoConsultas;
    private javax.swing.JComboBox<String> comboMedicoDepartamentoConsultas;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JScrollPane jScrollMedicos;
    private javax.swing.JRadioButton rbtnApellido;
    private javax.swing.JRadioButton rbtnDepartamento;
    private javax.swing.JRadioButton rbtnDireccion;
    private javax.swing.JRadioButton rbtnID;
    private javax.swing.JRadioButton rbtnNombre;
    private javax.swing.JRadioButton rbtnTelefono;
    private javax.swing.JTable tablaConsultasMedicos;
    // End of variables declaration//GEN-END:variables
}

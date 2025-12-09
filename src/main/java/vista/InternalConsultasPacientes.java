/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import modelo.ResultSetTableModel;

/**
 *
 * @author josesanchez
 */
public class InternalConsultasPacientes extends javax.swing.JInternalFrame {
    
    private ResultSetTableModel modelo;
    private String driver = "org.postgresql.Driver";
    private String url = "jdbc:postgresql://localhost:5432/wellmeadows_hospital";
    
    private String campoSeleccionado = "id_paceinte";
    
    ButtonGroup group;
    /**
     * Creates new form InternalAltasPacientes
     */
    public InternalConsultasPacientes() {
        initComponents();
        setSize(700, 700);        
        setResizable(false);
        agregarListenerDeBusqueda();
        cargarTodosLosPacientes(tablaConsultasPacientes);
        habilitarCampos(); 
        
        group = new ButtonGroup();
        group.add(rbtnID); 
        group.add(rbtnNombre); 
        group.add(rbtnApellido);
        group.add(rbtnTelefono); 
        group.add(rbtnFechaNac);
        group.add(rbtnSexo);
        group.add(rbtnEstadoCivil);
        group.add(rbtnFechaRegistro);
        group.add(rbtnIdMedico);
        
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
        rbtnTelefono.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
            habilitarCampos();
            }  
        });
        rbtnFechaNac.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
            habilitarCampos();
            }  
        });
        rbtnSexo.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
            habilitarCampos();
            }  
        });
        rbtnEstadoCivil.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
            habilitarCampos();
            }  
        });
        rbtnFechaRegistro.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
            habilitarCampos();
            }  
        });
        rbtnIdMedico.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
            habilitarCampos();
            }  
        });
        
    }
    
    
    
    private void buscarPacientes(){
        
        
        try{
            String sqlBase = "SELECT id_paciente, nombre, apellido, telefono, fecha_nacimiento, sexo, estado_civil, fecha_registro, id_medico FROM pacientes"; 
            String where = ""; 
            String valor = ""; 
            
            if(rbtnID.isSelected()){
                valor = campoIDPacienteConsultas.getText().trim();
                if(!valor.isEmpty()){
                    where = " WHERE CAST(id_paciente AS TEXT) LIKE '%" + valor + "%'";
                    
            }
        }else if(rbtnNombre.isSelected()){
            valor = campoNombrePacientesConsultas.getText().trim(); 
            if(!valor.isEmpty()){
                where = " WHERE nombre ILIKE '%" + valor + "%'"; 
                
            }
        }else if(rbtnApellido.isSelected()){
            valor = campoApellidoPacientesConsultas.getText().trim(); 
            if(!valor.isEmpty()){
                where = " WHERE apellido ILIKE '%" + valor + "%'"; 
            }
        }else if(rbtnTelefono.isSelected()){
            valor = campoTelefonoPacientesConsultas.getText().trim(); 
            if(!valor.isEmpty()){
                where = " WHERE TRIM(telefono) LIKE '%" + valor + "%'"; 
            }
        }else if(rbtnFechaNac.isSelected()){
            String dia = comboDiaNacConsultas.getSelectedItem().toString(); 
            String mes = comboMesNacConsultas.getSelectedItem().toString(); 
            String anio = comboAnoNacConsultas.getSelectedItem().toString(); 
            
            if(!dia.equals("Día") && !mes.equals("Mes") && !anio.equals("Año")){
                String fecha = anio + "-" + mes + "-" + dia; 
                where = " WHERE fecha_nacimiento = '" + fecha + "'";
            }
        }else if(rbtnSexo.isSelected()){
            valor = comboSexoConsultas.getSelectedItem().toString().trim(); 
            if(!valor.equals("Seleccionar...")){
                where = " WHERE sexo ILIKE '%" + valor + "%'"; 
            }
        }else if(rbtnEstadoCivil.isSelected()){
            valor = comboCivilConsultas.getSelectedItem().toString().trim();
            if(!valor.isEmpty()){
                where = " WHERE fecha_registro LIKE '%" + valor + "%'"; 
            }
        }else if(rbtnFechaRegistro.isSelected()){
            String dia = comboDiaRegistroConsultas.getSelectedItem().toString(); 
            String mes = comboMesRegistroConsultas.getSelectedItem().toString(); 
            String anio = comboAnoRegistroConsultas.getSelectedItem().toString();
            if(!dia.equals("Día") && !mes.equals("Mes") && !anio.equals("Año")){
                String fecha = anio + "-" + mes + "-" + dia; 
                where = " WHERE fecha_registro = '" + fecha + "'"; 
            }
        }else if(rbtnIdMedico.isSelected()){
            
            valor = campoIdMedicoConsultas.getText().trim(); 
            if(!valor.isEmpty()){
                where = " WHERE CAST(id_medico AS TEXT) LIKE '%" + valor + "%'";
            }
        }
            
        String sqlFinal = sqlBase + where;
        modelo = new ResultSetTableModel(driver, url, sqlFinal); 
        tablaConsultasPacientes.setModel(modelo);
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
        
        
    }
    
    
    private void habilitarCampos(){
        
        campoIDPacienteConsultas.setEnabled(false); 
        campoNombrePacientesConsultas.setEnabled(false);
        campoApellidoPacientesConsultas.setEnabled(false); 
        campoTelefonoPacientesConsultas.setEnabled(false); 
        comboDiaNacConsultas.setEnabled(false);
        comboMesNacConsultas.setEnabled(false); 
        comboAnoNacConsultas.setEnabled(false); 
        comboSexoConsultas.setEnabled(false); 
        comboCivilConsultas.setEnabled(false); 
        comboDiaRegistroConsultas.setEnabled(false); 
        comboMesRegistroConsultas.setEnabled(false); 
        comboAnoRegistroConsultas.setEnabled(false); 
        campoIdMedicoConsultas.setEnabled(false);
        
        
        if(rbtnID.isSelected()){
            campoIDPacienteConsultas.setEnabled(true); 
        }else if(rbtnNombre.isSelected()){
            campoNombrePacientesConsultas.setEnabled(true); 
        }else if(rbtnApellido.isSelected()){
            campoApellidoPacientesConsultas.setEnabled(true);
        }else if(rbtnTelefono.isSelected()){
            campoTelefonoPacientesConsultas.setEnabled(true);
        }else if(rbtnFechaNac.isSelected()){
            comboDiaNacConsultas.setEnabled(true);
            comboMesNacConsultas.setEnabled(true);
            comboAnoNacConsultas.setEnabled(true);
        }else if(rbtnSexo.isSelected()){
            comboSexoConsultas.setEnabled(true); 
        }else if(rbtnEstadoCivil.isSelected()){
            comboCivilConsultas.setEnabled(true);
        }else if(rbtnFechaRegistro.isSelected()){
            comboDiaRegistroConsultas.setEnabled(true); 
            comboMesRegistroConsultas.setEnabled(true);
            comboAnoRegistroConsultas.setEnabled(true);
        }else if(rbtnIdMedico.isSelected()){
            campoIdMedicoConsultas.setEnabled(true); 
        }
    }
    
    private void cargarTodosLosPacientes(javax.swing.JTable tablaPacientes){
        try {
            String sql = "SELECT * FROM pacientes"; 
            modelo = new ResultSetTableModel(driver, url, sql);
            tablaPacientes.setModel(modelo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void filtrarPacientes(String campo, String valor, javax.swing.JTable tablaPacientes){
        try {
            String sql = "SELECT * FROM pacientes WHERE " + campo + " ILIKE '%" + valor + "%'"; 
            modelo = new ResultSetTableModel(driver, url, sql); 
            tablaPacientes.setModel(modelo);
            
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    
    
    private void agregarListenerDeBusqueda(){
         
        campoIDPacienteConsultas.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent e) {
                buscarPacientes();
            }
        });

         campoNombrePacientesConsultas.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent e) {
                buscarPacientes();
            }
        });

        campoApellidoPacientesConsultas.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent e) {
                buscarPacientes();
            }
        });

         campoTelefonoPacientesConsultas.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent e) {
                buscarPacientes();
            }
        });
        
        comboDiaNacConsultas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarPacientes();
            }
        });

        comboMesNacConsultas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarPacientes();
            }
        });

        comboAnoNacConsultas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarPacientes();
            }
        });

        comboSexoConsultas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarPacientes();
            }
        });
        
        comboCivilConsultas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarPacientes();
            }
        });

        comboDiaRegistroConsultas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarPacientes();
            }
        });
        
        comboMesRegistroConsultas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
        });

        comboAnoRegistroConsultas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarPacientes();
            }
        });
    }
    
    private void restablecerCampos() {
        try {

            campoIDPacienteConsultas.setText("");
            campoNombrePacientesConsultas.setText("");
            campoApellidoPacientesConsultas.setText("");
            campoTelefonoPacientesConsultas.setText("");
            comboDiaNacConsultas.setSelectedIndex(0);
            comboMesNacConsultas.setSelectedIndex(0);
            comboAnoNacConsultas.setSelectedIndex(0);
            comboSexoConsultas.setSelectedIndex(0);
            comboCivilConsultas.setSelectedIndex(0);
            comboDiaRegistroConsultas.setSelectedIndex(0);
            comboMesRegistroConsultas.setSelectedIndex(0);
            comboAnoRegistroConsultas.setSelectedIndex(0);
            campoIdMedicoConsultas.setText(""); 

            if (group != null) {
                group.clearSelection();
            }
            campoIDPacienteConsultas.setEnabled(false);
            campoNombrePacientesConsultas.setEnabled(false);
            campoApellidoPacientesConsultas.setEnabled(false);
            campoTelefonoPacientesConsultas.setEnabled(false);
            comboDiaNacConsultas.setEnabled(false);
            comboMesNacConsultas.setEnabled(false);
            comboAnoNacConsultas.setEnabled(false);
            comboSexoConsultas.setEnabled(false);
            comboCivilConsultas.setEnabled(false);
            comboDiaRegistroConsultas.setEnabled(false);
            comboMesRegistroConsultas.setEnabled(false);
            comboAnoRegistroConsultas.setEnabled(false);
            campoIdMedicoConsultas.setEnabled(false); 

            cargarTodosLosPacientes(tablaConsultasPacientes);

        } catch (Exception ex) {
        ex.printStackTrace();
        }
    }
    
    public void refrescarTabla(){
        cargarTodosLosPacientes(tablaConsultasPacientes);
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        campoNombrePacientesConsultas = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        campoIDPacienteConsultas = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        campoTelefonoPacientesConsultas = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        campoApellidoPacientesConsultas = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        comboDiaRegistroConsultas = new javax.swing.JComboBox<>();
        comboMesRegistroConsultas = new javax.swing.JComboBox<>();
        comboAnoRegistroConsultas = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        comboSexoConsultas = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        comboCivilConsultas = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        comboDiaNacConsultas = new javax.swing.JComboBox<>();
        comboMesNacConsultas = new javax.swing.JComboBox<>();
        comboAnoNacConsultas = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        campoIdMedicoConsultas = new javax.swing.JTextField();
        btnRestablecerPacientesConsultas = new javax.swing.JButton();
        rbtnID = new javax.swing.JRadioButton();
        rbtnNombre = new javax.swing.JRadioButton();
        rbtnApellido = new javax.swing.JRadioButton();
        rbtnTelefono = new javax.swing.JRadioButton();
        rbtnFechaNac = new javax.swing.JRadioButton();
        rbtnSexo = new javax.swing.JRadioButton();
        rbtnEstadoCivil = new javax.swing.JRadioButton();
        rbtnFechaRegistro = new javax.swing.JRadioButton();
        rbtnIdMedico = new javax.swing.JRadioButton();
        jScrollMedicos = new javax.swing.JScrollPane();
        tablaConsultasPacientes = new javax.swing.JTable();

        setBackground(new java.awt.Color(102, 0, 0));
        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setTitle("Consultar Pacientes");
        getContentPane().setLayout(null);

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Id Paciente");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(90, 10, 80, 20);

        campoNombrePacientesConsultas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoNombrePacientesConsultasActionPerformed(evt);
            }
        });
        campoNombrePacientesConsultas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                campoNombrePacientesConsultasKeyTyped(evt);
            }
        });
        getContentPane().add(campoNombrePacientesConsultas);
        campoNombrePacientesConsultas.setBounds(170, 40, 380, 23);

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Nombre");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(90, 40, 70, 20);

        campoIDPacienteConsultas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoIDPacienteConsultasActionPerformed(evt);
            }
        });
        campoIDPacienteConsultas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                campoIDPacienteConsultasKeyTyped(evt);
            }
        });
        getContentPane().add(campoIDPacienteConsultas);
        campoIDPacienteConsultas.setBounds(170, 10, 380, 23);

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Apellido");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(90, 70, 70, 17);

        campoTelefonoPacientesConsultas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoTelefonoPacientesConsultasActionPerformed(evt);
            }
        });
        campoTelefonoPacientesConsultas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                campoTelefonoPacientesConsultasKeyTyped(evt);
            }
        });
        getContentPane().add(campoTelefonoPacientesConsultas);
        campoTelefonoPacientesConsultas.setBounds(170, 100, 380, 23);

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Telefóno");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(90, 100, 60, 20);

        campoApellidoPacientesConsultas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                campoApellidoPacientesConsultasKeyTyped(evt);
            }
        });
        getContentPane().add(campoApellidoPacientesConsultas);
        campoApellidoPacientesConsultas.setBounds(170, 70, 380, 23);

        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Fecha de Nacimiento");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(90, 130, 160, 20);

        comboDiaRegistroConsultas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Día", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30" }));
        comboDiaRegistroConsultas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboDiaRegistroConsultasActionPerformed(evt);
            }
        });
        getContentPane().add(comboDiaRegistroConsultas);
        comboDiaRegistroConsultas.setBounds(210, 220, 72, 23);

        comboMesRegistroConsultas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mes", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));
        getContentPane().add(comboMesRegistroConsultas);
        comboMesRegistroConsultas.setBounds(300, 220, 72, 23);

        comboAnoRegistroConsultas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Año", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030" }));
        getContentPane().add(comboAnoRegistroConsultas);
        comboAnoRegistroConsultas.setBounds(390, 220, 130, 23);

        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Sexo");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(90, 160, 40, 20);

        comboSexoConsultas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "M", "F" }));
        getContentPane().add(comboSexoConsultas);
        comboSexoConsultas.setBounds(140, 160, 72, 23);

        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Estado Civil");
        getContentPane().add(jLabel7);
        jLabel7.setBounds(90, 190, 90, 20);

        comboCivilConsultas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Soltero", "Casado", "Divorciado", "Separado", "Viudo" }));
        getContentPane().add(comboCivilConsultas);
        comboCivilConsultas.setBounds(180, 190, 97, 23);

        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Fecha de Registro");
        getContentPane().add(jLabel8);
        jLabel8.setBounds(90, 220, 120, 20);

        comboDiaNacConsultas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Día", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30" }));
        getContentPane().add(comboDiaNacConsultas);
        comboDiaNacConsultas.setBounds(240, 130, 72, 23);

        comboMesNacConsultas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mes", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));
        getContentPane().add(comboMesNacConsultas);
        comboMesNacConsultas.setBounds(330, 130, 72, 23);

        comboAnoNacConsultas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Año", "1930", "1931", "1932", "1933", "1934", "1935", "1936", "1937", "1938", "1939", "1940", "1941", "1942", "1943", "1944", "1945", "1946", "1947", "1948", "1949", "1950", "1951", "1952", "1953", "1954", "1955", "1956", "1957", "1958", "1959", "1960", "1961", "1962", "1963", "1964", "1965", "1966", "1967", "1968", "1969", "1970", "1971", "1972", "1973", "1974", "1975", "1976", "1977", "1978", "1979", "1980", "1981", "1982", "1983", "1984", "1985", "1986", "1987", "1988", "1989", "1990", "1991", "1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025" }));
        getContentPane().add(comboAnoNacConsultas);
        comboAnoNacConsultas.setBounds(420, 130, 130, 23);

        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("ID del Médico de Cabecera");
        getContentPane().add(jLabel9);
        jLabel9.setBounds(90, 250, 180, 17);

        campoIdMedicoConsultas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                campoIdMedicoConsultasKeyTyped(evt);
            }
        });
        getContentPane().add(campoIdMedicoConsultas);
        campoIdMedicoConsultas.setBounds(260, 250, 290, 23);

        btnRestablecerPacientesConsultas.setText("Restablecer");
        btnRestablecerPacientesConsultas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRestablecerPacientesConsultasActionPerformed(evt);
            }
        });
        getContentPane().add(btnRestablecerPacientesConsultas);
        btnRestablecerPacientesConsultas.setBounds(570, 40, 100, 23);

        rbtnID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtnIDActionPerformed(evt);
            }
        });
        getContentPane().add(rbtnID);
        rbtnID.setBounds(40, 10, 30, 21);

        rbtnNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtnNombreActionPerformed(evt);
            }
        });
        getContentPane().add(rbtnNombre);
        rbtnNombre.setBounds(40, 40, 30, 21);

        rbtnApellido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtnApellidoActionPerformed(evt);
            }
        });
        getContentPane().add(rbtnApellido);
        rbtnApellido.setBounds(40, 70, 30, 21);

        rbtnTelefono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtnTelefonoActionPerformed(evt);
            }
        });
        getContentPane().add(rbtnTelefono);
        rbtnTelefono.setBounds(40, 100, 30, 21);

        rbtnFechaNac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtnFechaNacActionPerformed(evt);
            }
        });
        getContentPane().add(rbtnFechaNac);
        rbtnFechaNac.setBounds(40, 130, 30, 21);

        rbtnSexo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtnSexoActionPerformed(evt);
            }
        });
        getContentPane().add(rbtnSexo);
        rbtnSexo.setBounds(40, 160, 30, 21);

        rbtnEstadoCivil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtnEstadoCivilActionPerformed(evt);
            }
        });
        getContentPane().add(rbtnEstadoCivil);
        rbtnEstadoCivil.setBounds(40, 190, 30, 21);

        rbtnFechaRegistro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtnFechaRegistroActionPerformed(evt);
            }
        });
        getContentPane().add(rbtnFechaRegistro);
        rbtnFechaRegistro.setBounds(40, 220, 30, 21);

        rbtnIdMedico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtnIdMedicoActionPerformed(evt);
            }
        });
        getContentPane().add(rbtnIdMedico);
        rbtnIdMedico.setBounds(40, 250, 30, 21);

        jScrollMedicos.setBackground(new java.awt.Color(51, 0, 0));
        jScrollMedicos.setBorder(null);
        jScrollMedicos.setForeground(new java.awt.Color(51, 51, 51));

        tablaConsultasPacientes.setBackground(new java.awt.Color(100, 0, 0));
        tablaConsultasPacientes.setForeground(new java.awt.Color(255, 255, 255));
        tablaConsultasPacientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID Paciente", "Nombre", "Apellido", "Teléfono", "Fecha de Nacimiento", "Sexo", "Estado Civil", "Fecha de Registro", "Médico de Cabecera"
            }
        ));
        jScrollMedicos.setViewportView(tablaConsultasPacientes);

        getContentPane().add(jScrollMedicos);
        jScrollMedicos.setBounds(0, 310, 680, 310);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void campoNombrePacientesConsultasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoNombrePacientesConsultasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoNombrePacientesConsultasActionPerformed

    private void campoIDPacienteConsultasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoIDPacienteConsultasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoIDPacienteConsultasActionPerformed

    private void campoTelefonoPacientesConsultasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoTelefonoPacientesConsultasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoTelefonoPacientesConsultasActionPerformed

    private void rbtnApellidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtnApellidoActionPerformed
        campoSeleccionado = "apellido"; 
    }//GEN-LAST:event_rbtnApellidoActionPerformed

    private void comboDiaRegistroConsultasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboDiaRegistroConsultasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboDiaRegistroConsultasActionPerformed

    private void btnRestablecerPacientesConsultasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRestablecerPacientesConsultasActionPerformed
        // TODO add your handling code here:
        
        restablecerCampos();
    }//GEN-LAST:event_btnRestablecerPacientesConsultasActionPerformed

    private void rbtnIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtnIDActionPerformed
        // TODO add your handling code here:
        campoSeleccionado = "id_paciente";
    }//GEN-LAST:event_rbtnIDActionPerformed

    private void rbtnNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtnNombreActionPerformed
        // TODO add your handling code here:
        campoSeleccionado = "nombre"; 
    }//GEN-LAST:event_rbtnNombreActionPerformed

    private void rbtnTelefonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtnTelefonoActionPerformed
        // TODO add your handling code here:
        campoSeleccionado = "telefono"; 
    }//GEN-LAST:event_rbtnTelefonoActionPerformed

    private void rbtnFechaNacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtnFechaNacActionPerformed
        // TODO add your handling code here:
        campoSeleccionado = "fecha_nacimiento";
    }//GEN-LAST:event_rbtnFechaNacActionPerformed

    private void rbtnSexoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtnSexoActionPerformed
        // TODO add your handling code here:
        campoSeleccionado = "sexo";
    }//GEN-LAST:event_rbtnSexoActionPerformed

    private void rbtnEstadoCivilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtnEstadoCivilActionPerformed
        // TODO add your handling code here:
        campoSeleccionado = "estado_civil"; 
    }//GEN-LAST:event_rbtnEstadoCivilActionPerformed

    private void rbtnFechaRegistroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtnFechaRegistroActionPerformed
        // TODO add your handling code here:
        campoSeleccionado = "fecha_registro";
    }//GEN-LAST:event_rbtnFechaRegistroActionPerformed

    private void rbtnIdMedicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtnIdMedicoActionPerformed
        // TODO add your handling code here:
        campoSeleccionado = "id_medico";
    }//GEN-LAST:event_rbtnIdMedicoActionPerformed

    private void campoIDPacienteConsultasKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoIDPacienteConsultasKeyTyped
        // TODO add your handling code here:
        soloNumeros(evt);
    }//GEN-LAST:event_campoIDPacienteConsultasKeyTyped

    private void campoNombrePacientesConsultasKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoNombrePacientesConsultasKeyTyped
        // TODO add your handling code here:
        soloLetras(evt);
        limitarCaracteres(evt, campoNombrePacientesConsultas, 45);
        
    }//GEN-LAST:event_campoNombrePacientesConsultasKeyTyped

    private void campoApellidoPacientesConsultasKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoApellidoPacientesConsultasKeyTyped
        // TODO add your handling code here:
        soloLetras(evt);
        limitarCaracteres(evt, campoApellidoPacientesConsultas, 45);
    }//GEN-LAST:event_campoApellidoPacientesConsultasKeyTyped

    private void campoTelefonoPacientesConsultasKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoTelefonoPacientesConsultasKeyTyped
        // TODO add your handling code here:
        soloNumeros(evt);
        limitarSoloDiez(campoTelefonoPacientesConsultas, evt);
    }//GEN-LAST:event_campoTelefonoPacientesConsultasKeyTyped

    private void campoIdMedicoConsultasKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoIdMedicoConsultasKeyTyped
        // TODO add your handling code here:
        soloNumeros(evt);
    }//GEN-LAST:event_campoIdMedicoConsultasKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRestablecerPacientesConsultas;
    private javax.swing.JTextField campoApellidoPacientesConsultas;
    private javax.swing.JTextField campoIDPacienteConsultas;
    private javax.swing.JTextField campoIdMedicoConsultas;
    private javax.swing.JTextField campoNombrePacientesConsultas;
    private javax.swing.JTextField campoTelefonoPacientesConsultas;
    private javax.swing.JComboBox<String> comboAnoNacConsultas;
    private javax.swing.JComboBox<String> comboAnoRegistroConsultas;
    private javax.swing.JComboBox<String> comboCivilConsultas;
    private javax.swing.JComboBox<String> comboDiaNacConsultas;
    private javax.swing.JComboBox<String> comboDiaRegistroConsultas;
    private javax.swing.JComboBox<String> comboMesNacConsultas;
    private javax.swing.JComboBox<String> comboMesRegistroConsultas;
    private javax.swing.JComboBox<String> comboSexoConsultas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollMedicos;
    private javax.swing.JRadioButton rbtnApellido;
    private javax.swing.JRadioButton rbtnEstadoCivil;
    private javax.swing.JRadioButton rbtnFechaNac;
    private javax.swing.JRadioButton rbtnFechaRegistro;
    private javax.swing.JRadioButton rbtnID;
    private javax.swing.JRadioButton rbtnIdMedico;
    private javax.swing.JRadioButton rbtnNombre;
    private javax.swing.JRadioButton rbtnSexo;
    private javax.swing.JRadioButton rbtnTelefono;
    private javax.swing.JTable tablaConsultasPacientes;
    // End of variables declaration//GEN-END:variables

    
}

package vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaLogin extends JFrame {

    private JTextField campoUsuario;
    private JPasswordField campoPassword;
    private JButton botonLogin;

    public VentanaLogin() {
        setTitle("Ventana de Login");
        setSize(300, 380); 
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 15));
        getContentPane().setBackground(new Color(117, 31, 31));

        ImageIcon iconoOriginal = new ImageIcon("/Imagenes/logo.png");

        Image imagenAjustada = iconoOriginal.getImage().getScaledInstance(
                160, 160, Image.SCALE_SMOOTH
        );

        JLabel etiquetaLogo = new JLabel(new ImageIcon(imagenAjustada));

      

        JLabel txtUsuario = new JLabel("Usuario:");
        txtUsuario.setForeground(Color.WHITE);

        campoUsuario = new JTextField(12);

        JLabel txtPassword = new JLabel("Contraseña:");
        txtPassword.setForeground(Color.WHITE);

        campoPassword = new JPasswordField(12);

        botonLogin = new JButton("Iniciar sesión");

       
        add(etiquetaLogo);
        add(txtUsuario);
        add(campoUsuario);
        add(txtPassword);
        add(campoPassword);
        add(botonLogin);

     
        botonLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuario = campoUsuario.getText();
                String password = campoPassword.getText();

                if(usuario.equals("joseluis") && password.equals("boom")) {
                    dispose();
                    new VentanaInicio();
                } else {
                    JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos.");
                }
            }
        });

        setVisible(true);
    }
}
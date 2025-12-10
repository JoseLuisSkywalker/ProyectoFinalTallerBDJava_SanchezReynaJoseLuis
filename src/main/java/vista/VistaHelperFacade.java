/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

/**
 *
 * @author josesanchez
 */
/*
PATRON DE DISENIO 2: PATRON ESTRUCTURAL
basicamente para que estos metodos visuales no se vuelvan repetitivos en la clases 
se puede hacer la fachada visual (FACADE).


*/

public class VistaHelperFacade {

    public void centrarVentana(javax.swing.JInternalFrame frame) {
        frame.setLocation(
            (frame.getDesktopPane().getWidth() - frame.getWidth()) / 2,
            (frame.getDesktopPane().getHeight() - frame.getHeight()) / 2
        );
    }

    public void limpiarCampos(javax.swing.JTextField... campos) {
        for (javax.swing.JTextField c : campos) {
            c.setText("");
        }
    }

    public void mensaje(String texto) {
        javax.swing.JOptionPane.showMessageDialog(null, texto);
    }
}
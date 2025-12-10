/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package modelo;

import javax.swing.SwingUtilities;
import vista.VentanaInicio;
import vista.VentanaLogin;


/**
 *
 * @author josesanchez
 */
public class WellmeadowsHospitalProyectoFinal {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

                new VentanaLogin(); 

            }
        });
    }
}
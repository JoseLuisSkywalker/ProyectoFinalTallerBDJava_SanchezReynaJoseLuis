/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import javax.swing.JTextField;

/**
 *
 * @author josesanchez
 */
//
//PATRON DE DISENIO 3: COMPORTAMIENTO
public class SoloNumerosStrategy implements ValidarTexto{

    @Override
    public boolean validar(char c, JTextField campo) {
        return Character.isDigit(c) || c == '\b' || c == 127;  
                                    //no me dejaba borrar lol.
    }
}

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
//PATRON DE DISENIO 3: comportamiento
//esta clase implementa describe como se va hacer la validacion
//

public class SoloLetrasStrategy implements ValidarTexto{
    @Override
    public boolean validar(char c, JTextField campo){
        return Character.isLetter(c) || Character.isWhitespace(c); 
    }
}

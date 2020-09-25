/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tareacesar;

import java.util.*;

/**
 *
 * @author pauli
 */
public class TareaCesar {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Scanner sc=new Scanner(System.in);
        ArrayQueue<Integer> lectura = new ArrayQueue<Integer>();
        String mensaje;
        int n;
        String opcion;
        
        System.out.println("Ingrese el mensaje");
        mensaje = sc.next();
        System.out.println("¿Cuántos números tiene la clave?");
        n = sc.nextInt();
        System.out.println("Introduce el código:");
        for (int i = 0; i < n; i++) {
            lectura.enqueue(sc.nextInt());
        }
        System.out.println("(C) cifrar o (D) descifrar?:");
        opcion = sc.next();
        if(opcion == "C")
            System.out.println(codificar(mensaje,lectura));
        else
            System.out.println(decodificar(mensaje,lectura));
    }
    
    public static String codificar(String mensaje, ArrayQueue<Integer> clave){
        StringBuilder res = new StringBuilder("");
        Integer aux, mov;
        //65 en ascii es la A letra-64 me da posición original
        for (int i = 0; i < mensaje.length(); i++) {
            if(mensaje.charAt(i)==' '){//los espacios los dejo igual
                res.append(" ");
            }else{
                mov=clave.dequeue(); //saco el siguiente movimiento
                clave.enqueue(mov);
                aux=(((int)mensaje.charAt(i)-64)+mov)%26;//posicion original con movimiento
                if(aux<=0)
                    aux+=26;
                aux+=64; //lo regreso a ascii
                res.append((char)aux.intValue());
            }
        } 
        return res.toString();
    }
    
    public static String decodificar(String mensaje, ArrayQueue<Integer> clave){
        StringBuilder res = new StringBuilder("");
        Integer aux, mov;
        char x;
        //65 en ascii es la A letra-64 me da posición original
        for (int i = 0; i < mensaje.length(); i++) {
            if(mensaje.charAt(i)==' '){
                res.append(" ");
            }else{
                mov=clave.dequeue();
                clave.enqueue(mov);
                aux=(((int)mensaje.charAt(i)-64)-mov)%26;//posicion original
                if(aux<=0)
                    aux+=26;
                aux+=64;
                res.append((char)aux.intValue());
            }
        } 
        return res.toString();
    }
    
}

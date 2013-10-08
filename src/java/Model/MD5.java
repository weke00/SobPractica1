/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author gerardca
 */
public class  MD5 {
    
    //Método para generar la huella MD5
    public static String generateMD5Signature(String input) throws NoSuchAlgorithmException {
        //Cambiando MD5 por SHA-1 podríamos obtener la huella usando este otro algoritmo
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] huella = md.digest(input.getBytes());
        return transformaAHexadecimal(huella);

    }

    //Método para transformar el array de bytes en una cadena hexadecimal
    private static String transformaAHexadecimal(byte buf[]) {
        StringBuilder strbuf = new StringBuilder(buf.length * 2);
        for (int i = 0; i < buf.length; i++) {
            if (((int) buf[i] & 0xff) < 0x10) {
                strbuf.append("0");
            }
            strbuf.append(Long.toString((int) buf[i] & 0xff, 16));
        }
        return strbuf.toString();
    }
    
}

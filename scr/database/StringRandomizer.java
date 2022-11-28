/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import java.util.Random;

/**
 *
 * @author HMBAO
 */
public class StringRandomizer {

    public static String GetStringWithPrefix(String prefix, int length) {
        String POOL = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        String numPOOL = "1234567890";
        StringBuilder rngStr = new StringBuilder();
        Random rnd = new Random();

        int numOfDigits = rnd.nextInt(length / 5, length / 3);

        while (rngStr.length() < numOfDigits) {
            int index = (int) (rnd.nextFloat() * numPOOL.length());
            rngStr.append(numPOOL.charAt(index));
        }

        while (rngStr.length() < length - prefix.length()) {
            int index = (int) (rnd.nextFloat() * POOL.length());
            rngStr.append(POOL.charAt(index));
        }
        String result = prefix + rngStr.toString();
        System.out.println(result);
        return result;
    }

    public static void main(String[] args) {
        GetStringWithPrefix("msg", 10);
    }
}

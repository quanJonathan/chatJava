/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import database.StringRandomizer;

/**
 *
 * @author HMBAO
 */
public class IDPrefix {

    public static final String IDTinNhan = "msg";
    public static final String IDNhomChat = "grp";
    public static final int length = 10;
    
    public static String getIDTinNhan() {
        return StringRandomizer.GetStringWithPrefix(IDTinNhan, IDPrefix.length);
    }
    
    public static String getIDNhomChat() {
        return StringRandomizer.GetStringWithPrefix(IDNhomChat, IDPrefix.length - 3);
    }
}

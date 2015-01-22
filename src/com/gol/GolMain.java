/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gol;

import com.gol.gui.GolUI;
import java.awt.EventQueue;

/**
 *
 * @author PetchAdmin
 */
public class GolMain {
    
    public static void main(String[] args){
        Runnable appExecutor = new Runnable() {

            @Override
            public void run() {
                new GolUI();
            }
        };
        
        EventQueue.invokeLater(appExecutor);
    }
    
}

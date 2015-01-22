/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gol;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.BorderFactory;
import javax.swing.border.Border;

/**
 *
 * @author PetchAdmin
 */
public class GolUtils {
    
    public static Dimension getScreenDimension(){
        // Get screen dimensions
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        
        return screenSize;
    }
    
    public static Border buildTitledBorder(String title){
        Border etched = BorderFactory.createEtchedBorder();
        Border titled = BorderFactory.createTitledBorder(etched, title);
        
        return titled;
    }
    
}

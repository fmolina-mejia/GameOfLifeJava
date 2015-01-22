/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gol.gui;

import com.gol.GolUtils;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JFrame;

/**
 *
 * @author PetchAdmin
 */
public class GolUI extends JFrame {
    
    public GolUI(){
        
        super("Game of Life Application");        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        this.configureDimension();
        
        pack();        
        setVisible(true);
    }
    
    private void configureDimension(){
        
        Dimension screenSize = GolUtils.getScreenDimension();        
        
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        
        
        // Set frame width, height and let platform pick screen location
        //setSize(screenWidth/2, screenHeight/2);
        setLocationByPlatform(true);
        
        DisplayPanel displayPanel = new DisplayPanel();
        displayPanel.buildComponent();
        displayPanel.setMainFrame(this);
        
        LoaderPanel loaderPanel = new LoaderPanel();
        loaderPanel.setDisplayPanel(displayPanel);
        loaderPanel.buildComponent();
        
        getContentPane().setLayout(new FlowLayout());
        getContentPane().add(loaderPanel);
        getContentPane().add(displayPanel);
    }
    
}

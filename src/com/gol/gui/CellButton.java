/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gol.gui;

import java.awt.Color;
import javax.swing.JToggleButton;

/**
 *
 * @author PetchAdmin
 */
public class CellButton extends JToggleButton {
    
    private int state;
    
    public CellButton(){
        this.state = 0;
        setBackground(Color.red);
        setSize(20, 20);
        repaint();
    }
    
    public void setState(int state){
        this.state = state;
        if(this.state == 1){
            doClick();
        }
    }
    
    public int getState(){
        return this.state;
    }
    
    public void configureState(boolean state){
        if(state && !isSelected()){
            doClick();
        }
        else if(!state && isSelected()){
            doClick();
        }
    }
    
}

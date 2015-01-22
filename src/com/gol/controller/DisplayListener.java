/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gol.controller;

import com.gol.gui.CellButton;
import com.gol.gui.DisplayPanel;
import com.gol.logic.GameOfLife;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author PetchAdmin
 */
public class DisplayListener implements ActionListener {
    
    private Map<String, Object> resources;
    private DisplayPanel displayPanel;
    private int rows;
    private int columns;
    
    public DisplayListener(DisplayPanel displayPanel, Map<String, Object> resources){
        this.displayPanel = displayPanel;
        this.resources = resources;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(resources != null &&
                !resources.isEmpty() &&
                resources.containsKey("generations")){
            
            CellButton[][] cells = displayPanel.getCells();
            int rows = cells.length;
            int columns = cells[0].length;
            String generationStr = ((JTextField)resources.get("generations")).getText().trim();
            int generation = 0;
            boolean status = false;
            
            if(generationStr != null && !generationStr.isEmpty()){
                try{
                    generation = Integer.parseInt(generationStr);
                }
                catch(NumberFormatException nfe){
                    displayPanel.showMessage("Please verify that generation must be a number");
                }
                
                for (int i=0; i<generation; i++){  // loops through the generations
                    for (int j=0; j<rows; j++){     // loops through x-axis for computing the next generation
                        for (int k=0; k<columns; k++){ // loops through y-axis
                            status = GameOfLife.getDot(cells, j, k, rows, columns);
                            cells[j][k].configureState(status);
                        }
                    }
                    
                }
            }
            else{
                displayPanel.showMessage("Please verify that generation must be a number");
            }
//            
//            
//            System.out.println("Cells rows "+cells.length);
//            System.out.println("Cells columns "+cells[0].length);
//            System.out.println("Change some elements ");
//            
//            cells[1][1].doClick();
            
        }
    }
    
}

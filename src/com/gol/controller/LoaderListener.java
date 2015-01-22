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
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author PetchAdmin
 */
public class LoaderListener implements ActionListener {
    
    private Map<String, Object> resources;
    private DisplayPanel displayPanel;
    private int rows;
    private int columns;
    private File fileLoaded;    
    
    public LoaderListener(DisplayPanel displayPanel, Map<String, Object> resources){
        this.displayPanel = displayPanel;
        this.resources = resources;
        this.fileLoaded = null;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        rows = 0;
        columns = 0;
        
        if(resources != null &&
                !resources.isEmpty() && 
                resources.containsKey("rows") &&
                resources.containsKey("columns")){
            // Manual configuration
            String str_rows = ((JTextField) resources.get("rows")).getText().trim();
            String str_columns = ((JTextField) resources.get("columns")).getText().trim();
            
            try{
                rows = Integer.parseInt(str_rows);
                columns = Integer.parseInt(str_columns);
                
                if(rows < 3){
                    displayPanel.showMessage("Please verify that rows cannot set least than 3");
                    return;
                }
                if(columns < 3){
                    displayPanel.showMessage("Please verify that rows cannot set least than 3");
                    return;
                }
                
                displayPanel.rebuildCellsPanel(rows, columns);
            }
            catch(NumberFormatException nfe){
                displayPanel.showMessage("Please verify that rows or columns must be numbers");
                ((JTextField) resources.get("rows")).setText("");
                ((JTextField) resources.get("columns")).setText("");
            }
            
        }        
        else if(resources != null &&
                !resources.isEmpty() &&
                resources.containsKey("chooser") &&
                resources.containsKey("chooserDisplay") &&
                resources.containsKey("chooserParent")){
            // File chooser configuration
            this.fileLoaded = null;
            ((JTextField)resources.get("chooserDisplay")).setText("");
            
            JFileChooser chooser = (JFileChooser)resources.get("chooser");
            chooser.setCurrentDirectory(new File("."));
            int result = chooser.showOpenDialog((JPanel)resources.get("chooserParent"));
            
            if(result == JFileChooser.APPROVE_OPTION){
                fileLoaded = chooser.getSelectedFile();
                ((JTextField)resources.get("chooserDisplay")).setText(fileLoaded.getAbsolutePath());
            }
        }
        else{
            // File processing
            String filePath = ((JTextField)resources.get("chooserDisplay")).getText();
            
            try{
                FileReader fr = new FileReader(filePath);
                BufferedReader br= new BufferedReader(fr);
                StringBuilder sb = new StringBuilder();
                String line = br.readLine();
                while(line != null){ // reading file
                    System.out.println(line);
                    rows++;
                    sb.append(line);
                    sb.append("::");
                    line = br.readLine();
                }
                
                String s = sb.toString();
                columns = GameOfLife.calculateColumns(s);
                
                if(rows < 3){
                    displayPanel.showMessage("Please verify that rows cannot set least than 3");
                    return;
                }
                if(columns < 3){
                    displayPanel.showMessage("Please verify that rows cannot set least than 3");
                    return;
                }
                
                CellButton[][] board = GameOfLife.strToCellMatrix(s, rows, columns);
                displayPanel.rebuildCellsPanel(board);
            }
            catch(FileNotFoundException fnfe){
                displayPanel.showMessage("File selected cannot be loaded, please verify.");
            }
            catch(IOException ioe){
                displayPanel.showMessage("Information cannot be read, please verify the file.");
            }
            
        }
        
    }
    
}

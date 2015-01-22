/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gol.gui;

import com.gol.GolUtils;
import com.gol.controller.DisplayListener;
import java.awt.GridLayout;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author PetchAdmin
 */
public class DisplayPanel extends JPanel {
    
    private int rows;
    private int columns;
    private JPanel cellsPanel;
    private JPanel configurationPanel;
    private GridLayout displayCells;
    private CellButton[][] cells;
    private JFrame mainFrame;
    
    public DisplayPanel(){
        this.rows = 3;
        this.columns = 3;
        
        cellsPanel = new JPanel();
        configurationPanel = new JPanel();
                
    }
    
    public DisplayPanel(int rows, int columns){
        this.rows = rows;
        this.columns = columns;
        
        cellsPanel = new JPanel();
        configurationPanel = new JPanel();
        
    }
    
    public void buildComponent(){
        this.configurePanel();
        this.buildCellsPanel();
        this.buildConfigurationPanel();
    }
    
    private void configurePanel(){
        GridLayout display = new GridLayout(2, 1);
        setLayout(display);
         
        add(configurationPanel);
        add(cellsPanel);  
    }
        
    private void buildCellsPanel(){
        displayCells = new GridLayout(this.rows, this.columns);
        cells = new CellButton[this.rows][this.columns];
        
        cellsPanel.setBorder(GolUtils.buildTitledBorder("Cells"));
        cellsPanel.setLayout(displayCells);
        
        CellButton cell = null;
        
        for(int i = 0; i < this.rows; i++){
            for(int j = 0; j < this.columns; j++){
                cell = new CellButton();
                cells[i][j] = cell;
                cellsPanel.add(cell);
            }
        }
    }
    
    public void buildConfigurationPanel(){
        
        JLabel jl_generation = new JLabel("Generation:");
        JTextField tf_generation = new JTextField();
        JButton jb_execute = new JButton("Go to");
        
        configurationPanel.setLayout(new GridLayout(2,2));
        configurationPanel.add(jl_generation);
        configurationPanel.add(new JLabel());
        configurationPanel.add(tf_generation);
        configurationPanel.add(jb_execute);
        
        Map<String, Object> resources = new HashMap<String, Object>();
        resources.put("generations", tf_generation);
        jb_execute.addActionListener(new DisplayListener(this, resources));
                
    }
    
    public void rebuildCellsPanel(int rows, int columns){
        this.rows = rows;
        this.columns = columns;
        
        this.remove(cellsPanel);
        this.updateUI();
        this.repaint();
        
        cellsPanel = new JPanel();
                
        displayCells = new GridLayout(this.rows, this.columns);
        cells = new CellButton[this.rows][this.columns];
        
        cellsPanel.setBorder(GolUtils.buildTitledBorder("Cells"));
        cellsPanel.setLayout(displayCells);
        
        CellButton cell = null;
        
        for(int i = 0; i < this.rows; i++){
            for(int j = 0; j < this.columns; j++){                
                cell = new CellButton();
                cells[i][j] = cell;
                cellsPanel.add(cell);
            }
        }
        
        //cellsPanel.setVisible(true);
        this.add(cellsPanel);
        this.updateUI();
        this.repaint();
        
        this.mainFrame.pack();
    }
    
    public void rebuildCellsPanel(CellButton[][] cellsMatrix){
        this.rows = cellsMatrix.length;
        this.columns = cellsMatrix[0].length;
                
        this.remove(cellsPanel);
        this.updateUI();
        this.repaint();
        
        cellsPanel = new JPanel();
                
        displayCells = new GridLayout(this.rows, this.columns);
        cells = new CellButton[this.rows][this.columns];
        
        cellsPanel.setBorder(GolUtils.buildTitledBorder("Cells"));
        cellsPanel.setLayout(displayCells);
        
        for(int i = 0; i < this.rows; i++){
            for(int j = 0; j < this.columns; j++){
                cells[i][j] = cellsMatrix[i][j];
                cellsPanel.add(cells[i][j]);
            }
        }
        
        //cellsPanel.setVisible(true);
        this.add(cellsPanel);
        this.updateUI();
        this.repaint();
        
        this.mainFrame.pack();
    }
    
    public void setMainFrame(JFrame mainFrame){
        this.mainFrame = mainFrame;
    }
    
    public void showMessage(String message){
        JOptionPane.showMessageDialog(this, 
                message,
                "Error information",
                JOptionPane.ERROR_MESSAGE);
    }
    
    public CellButton[][] getCells(){
        return this.cells;
    }
    
}

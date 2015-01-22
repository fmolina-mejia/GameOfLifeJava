/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gol.gui;

import com.gol.controller.LoaderListener;
import com.gol.GolUtils;
import java.awt.GridLayout;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author PetchAdmin
 */
public class LoaderPanel extends JPanel {
            
    private JPanel filePanel;
    private JPanel manualPanel;
    private DisplayPanel displayPanel;
    private JFileChooser chooser;
    
    public LoaderPanel(){
        filePanel = new JPanel();
        manualPanel = new JPanel();
        chooser = new JFileChooser();
        
        //this.configurePanel();
        //this.buildFilePanel();
        //this.buildManualPanel();
    }
    
    public void buildComponent(){
        this.configurePanel();
        this.configureChooser();
        this.buildFilePanel();
        this.buildManualPanel();
    }
    
    private void configurePanel(){
        
        GridLayout displayLoader = new GridLayout(2, 1);
        setLayout(displayLoader);
        
        add(filePanel);
        add(manualPanel);
        
    }
    
    private void configureChooser(){
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Accepted files", "txt");
        chooser.setFileFilter(filter);
        
    }
    
    private void buildFilePanel(){        
        filePanel.setBorder(GolUtils.buildTitledBorder("File configuration"));
        JTextField tf_file = new JTextField();
        tf_file.setEditable(false);
        JButton jb_file = new JButton("Select file");
        JButton jb_file_configure = new JButton("Configure");
        Map<String, Object> fileResources;
        Map<String, Object> fileConfigurationResources;
        
        GridLayout displayFile = new GridLayout(2, 2);
        filePanel.setLayout(displayFile);
        
        filePanel.add(tf_file);
        filePanel.add(jb_file);
        filePanel.add(new JLabel());
        filePanel.add(jb_file_configure);
        
        fileResources = new HashMap<String, Object>();
        fileResources.put("chooser", chooser);
        fileResources.put("chooserDisplay", tf_file);
        fileResources.put("chooserParent", this);
        
        fileConfigurationResources = new HashMap<String, Object>();
        fileConfigurationResources.put("chooserDisplay", tf_file);
        
        jb_file.addActionListener(new LoaderListener(displayPanel, fileResources));        
        jb_file_configure.addActionListener(new LoaderListener(displayPanel, fileConfigurationResources));
        
        
    }
    
    private void buildManualPanel(){
        manualPanel.setBorder(GolUtils.buildTitledBorder("Manual configuration"));
        JLabel jl_rows = new JLabel("Rows");
        JLabel jl_columns = new JLabel("Columns");
        JTextField tf_rows = new JTextField();
        JTextField tf_columns = new JTextField();
        JButton jb_manual_configure = new JButton("Configure");
        
        GridLayout displayManual = new GridLayout(3, 2);
        manualPanel.setLayout(displayManual);
        
        manualPanel.add(jl_rows);
        manualPanel.add(tf_rows);
        manualPanel.add(jl_columns);
        manualPanel.add(tf_columns);
        manualPanel.add(new JLabel());
        manualPanel.add(jb_manual_configure);
        
        Map<String, Object> resources = new HashMap<String, Object>();
        resources.put("rows", tf_rows);
        resources.put("columns", tf_columns);
        
        jb_manual_configure.addActionListener(new LoaderListener(displayPanel, resources));
        
    }
    
    public void setDisplayPanel(DisplayPanel displayPanel){
        this.displayPanel = displayPanel;
    }
    
    
}

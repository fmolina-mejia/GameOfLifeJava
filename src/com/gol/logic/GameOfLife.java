/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gol.logic;

import com.gol.gui.CellButton;

/**
 *
 * @author PetchAdmin
 */
public class GameOfLife {
    
    public static boolean[][] strToBoolMatrix (String str, int xsize, int ysize){
        boolean [][] board = new boolean[xsize][ysize];  // converts an input string into the
        for (int i=0;i<xsize; i++){                      // boolean[][] used internally
                for (int j=0; j<ysize; j++){
                        board[i][j] = str.charAt((i*xsize)+j) != '0';
                }
        }
        return board;
    }
    
    public static int mod (int x, int m){ // deals with java's % returning negative vals for 
        m = Math.abs(m);                   // negative inputs
        return (x % m + m) % m;
    } 
 
    public static int getNeighborCount(boolean[][] board, int x, int y, int xsize, int ysize){
        int nc = 0;   // this function rather messily counts up the neighbors

        if (board[mod(x+1,xsize)][y]){
                nc++;
        }
        if (board[mod(x+1,xsize)][mod(y+1, ysize)]){
                nc++;
        }
        if (board[x][mod(y+1,ysize)]){
                nc++;
        }
        if (board[x][mod(y-1,ysize)]){
                nc++;
        }
        if (board[mod(x+1,xsize)][mod(y-1,ysize)]){
                nc++;
        }
        if (board[mod(x-1,xsize)][y]){
                nc ++;
        }
        if (board[mod(x-1,xsize)][mod(y-1,ysize)]){
                nc ++;
        }
        if (board[mod(x-1,xsize)][mod(y+1,ysize)]){
                nc ++;
        }
        return nc;
    }

    public static boolean getDot(boolean[][] board, int x, int y, int xsize, int ysize){
        // this function applies the rules of the game on one square

        return board[x][y] && getNeighborCount(board, x,y,xsize,ysize) == 2
                || getNeighborCount(board,x,y,xsize,ysize) == 3;
    }
    
    public static CellButton[][] strToCellMatrix(String str, int rows, int columns){
        CellButton[][] board = new CellButton[rows][columns];
        int i = 0;
        int j = 0;
        CellButton cell = null;
        
        if(str != null && !str.isEmpty()){
            for(String rowStr : str.split("::")){
                j = 0;
                for(char cellChar : rowStr.toCharArray()){
                    cell = new CellButton();
                    cell.setState(Integer.parseInt(String.valueOf(cellChar)));
                    board[i][j++] = cell;
                }
                i++;
            }
        }        
        
        return board;
    }
    
    public static int calculateColumns(String str){
        int columns = 0;
        
        if(str != null && !str.isEmpty()){
            for(String rowStr : str.split("::")){
                columns = rowStr.toCharArray().length;
                break;
            }
        }
        
        return columns;
    }
    
    public static boolean getDot(CellButton[][] board, int x, int y, int xsize, int ysize){
        // this function applies the rules of the game on one square

        return (((CellButton)board[x][y]).getState() == 1) && getNeighborCount(board, x,y,xsize,ysize) == 2
                || getNeighborCount(board,x,y,xsize,ysize) == 3;
    }
    
    public static int getNeighborCount(CellButton[][] board, int x, int y, int xsize, int ysize){
        int nc = 0;   // this function rather messily counts up the neighbors

        if (((CellButton)board[mod(x+1,xsize)][y]).getState() == 1){
                nc++;
        }
        if (((CellButton)board[mod(x+1,xsize)][mod(y+1, ysize)]).getState() == 1){
                nc++;
        }
        if (((CellButton)board[x][mod(y+1,ysize)]).getState() == 1){
                nc++;
        }
        if (((CellButton)board[x][mod(y-1,ysize)]).getState() == 1){
                nc++;
        }
        if (((CellButton)board[mod(x+1,xsize)][mod(y-1,ysize)]).getState() == 1){
                nc++;
        }
        if (((CellButton)board[mod(x-1,xsize)][y]).getState() == 1){
                nc ++;
        }
        if (((CellButton)board[mod(x-1,xsize)][mod(y-1,ysize)]).getState() == 1){
                nc ++;
        }
        if (((CellButton)board[mod(x-1,xsize)][mod(y+1,ysize)]).getState() == 1){
                nc ++;
        }
        return nc;
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author adoni
 */
public class KeyHandler implements KeyListener {
    private MainPanel mainPanel;
    private boolean checkStats;
    
    public KeyHandler(MainPanel mainPanel) {
        this.mainPanel = mainPanel;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    
    }
        
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        
        // CHECK STATS
        if (code == KeyEvent.VK_ENTER) {
            checkStats = !checkStats;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public boolean isCheckStats() {
        return checkStats;
    }

    public void setCheckStats(boolean checkStats) {
        this.checkStats = checkStats;
    }

}

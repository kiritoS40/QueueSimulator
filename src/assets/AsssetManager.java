/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assets;

import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import main.MainPanel;

/**
 *
 * @author adoni
 */
public class AsssetManager {
    private final MainPanel mainPanel;
    private Asset asset;
    
    public AsssetManager(MainPanel mainPanel) {
        this.mainPanel = mainPanel;
        asset = new Asset();
        
        getMapImage();
        getTVImage();
        getCatImage();
        getEmployeeImage();
        
    }
    
    public final void getMapImage() {
        try {
            asset.setMapImage(ImageIO.read(getClass().getResourceAsStream("/maps/map06.png")));
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public final void getTVImage() {
        asset.setTvImageGif(new ImageIcon(getClass().getResource("/objects/tv.gif")).getImage());
    }
    
    public final void getCatImage() {
        asset.setCatImageGif(new ImageIcon(getClass().getResource("/objects/cat.gif")).getImage());
    }
    
    public final void getEmployeeImage() {
        asset.setEmployeeImages(new Image[3]);
        asset.getEmployeeImages()[0] = new ImageIcon(getClass().getResource("/employees/employee1.gif")).getImage();
        asset.getEmployeeImages()[1] = new ImageIcon(getClass().getResource("/employees/empolyee2.gif")).getImage();
        asset.getEmployeeImages()[2] = new ImageIcon(getClass().getResource("/employees/cleaning_man.gif")).getImage();
    }
    
    public void draw(Graphics2D g2) {
        g2.drawImage(asset.getMapImage(), 0, 0, mainPanel.getScreenWidth(), mainPanel.getScreenHeight(), null);
        g2.drawImage(asset.getEmployeeImages()[1], 4 * mainPanel.getTileSize() + mainPanel.getTileSize() / 2, 3 * mainPanel.getTileSize() - mainPanel.getTileSize() / 40, 48, 75, null);
        g2.drawImage(asset.getEmployeeImages()[0] , 9 * mainPanel.getTileSize() + mainPanel.getTileSize() / 2, 3 * mainPanel.getTileSize() - mainPanel.getTileSize() / 40, 48, 75, null);
        g2.drawImage(asset.getEmployeeImages()[0], 14 * mainPanel.getTileSize() + mainPanel.getTileSize() / 2, 3 * mainPanel.getTileSize() - mainPanel.getTileSize() / 40, 48, 75, null);
        g2.drawImage(asset.getTvImageGif(), 8 * mainPanel.getTileSize() + mainPanel.getTileSize() / 2, mainPanel.getTileSize() - mainPanel.getTileSize() / 4, 144, 96, null);
        g2.drawImage(asset.getCatImageGif(), mainPanel.getTileSize() / 10, 4 * mainPanel.getTileSize() + mainPanel.getTileSize() / 4, 96, 36, null);
        g2.drawImage(asset.getEmployeeImages()[2], 18 * mainPanel.getTileSize(), 7 * mainPanel.getTileSize() + mainPanel.getTileSize() / 4, 48, 144, null);
    }
}

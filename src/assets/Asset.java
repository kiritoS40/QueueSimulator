/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assets;

import java.awt.Image;
import java.awt.image.BufferedImage;

/**
 *
 * @author adoni
 */
public class Asset {
    private BufferedImage mapImage;
    private Image[] employeeImages;
    private Image tvImageGif;
    private Image catImageGif;

    public BufferedImage getMapImage() {
        return mapImage;
    }

    public void setMapImage(BufferedImage mapImage) {
        this.mapImage = mapImage;
    }

    public Image[] getEmployeeImages() {
        return employeeImages;
    }

    public void setEmployeeImages(Image[] employeeImages) {
        this.employeeImages = employeeImages;
    }

    public Image getTvImageGif() {
        return tvImageGif;
    }

    public void setTvImageGif(Image tvImageGif) {
        this.tvImageGif = tvImageGif;
    }

    public Image getCatImageGif() {
        return catImageGif;
    }

    public void setCatImageGif(Image catImageGif) {
        this.catImageGif = catImageGif;
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Custom;

import java.awt.Image;
import static java.awt.Image.SCALE_SMOOTH;
import javax.swing.ImageIcon;

/**
 *
 * @author nguye
 */
public class ScaleImage {
    public static ImageIcon scaleImage(String image,int width, int height){
        ImageIcon imageIcon = new ImageIcon(image);
        Image img = imageIcon.getImage();
        Image newImg = img.getScaledInstance(width, height, SCALE_SMOOTH);
        return new ImageIcon(newImg);
    }
}

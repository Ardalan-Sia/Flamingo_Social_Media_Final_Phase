package project.tools;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageResizer {
    public static ImageIcon reSizeImage(int width, int height, BufferedImage img){

        Image dimg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(dimg);
    }
}

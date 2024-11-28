package UI;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.GamePanel;

public class MyButton {

    int x, y, width, height;
    Rectangle bounds;
    BufferedImage image;

    public MyButton(Rectangle bounds, BufferedImage image){
        this.bounds = bounds;
        this.image = image;

    }

    public void draw(Graphics2D g2){


        g2.drawImage(image, (int)bounds.getX(), (int)bounds.getY(), (int)bounds.getWidth(), (int)bounds.getHeight(), null);
    }

}

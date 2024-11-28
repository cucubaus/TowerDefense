package UI;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import entity.*;
import main.GamePanel;

public class EntityUI {

    GamePanel gp;
    Entity entity;
    int x, y, range;
    Rectangle bounds;

    public EntityUI(Entity entity){
        this.entity = entity;
        createBounds();
    }

    public void createBounds(){
        bounds = new Rectangle(entity.x, entity.y, GamePanel.tileSize, GamePanel.tileSize);
        entity.setBounds(bounds);

    }

    

    public Rectangle getBounds() {
        return bounds;
    }

    public void draw(Graphics2D g2){
        if (entity.isSelect()){

            drawRange(g2, entity.range);
            drawStats(g2);
        }

    }

    public void drawRange(Graphics2D g2, int r){

        int circleX = entity.x + (GamePanel.tileSize / 2) - r;
        int circleY = entity.y + (GamePanel.tileSize / 2) - r;

        float opacity = 0.25f;
        Color transparentColor = new Color(0.9f, 0.9f, 0.9f, opacity);

        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
        g2.setColor(transparentColor);
        g2.fillOval(circleX, circleY, r*2, r*2);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));

        opacity = 0.6f;
        transparentColor = new Color(0f, 0f, 0f, opacity);

        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
        g2.setColor(transparentColor);
        g2.drawOval(circleX, circleY, r*2, r*2);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
    }

    public void drawStats(Graphics2D g2){

    }


}

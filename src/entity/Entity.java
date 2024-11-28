package entity;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import UI.EntityUI;
import events.eventManager;
import main.GamePanel;
import tiles.TileManager;

public abstract class Entity {

    GamePanel gp;
    EntityUI entityUI;  
    Rectangle bounds;

    public int action;
    public int aniTick, aniSpeed = 11, aniIndex;
    
    public int x, y, speed, points, moneyEscape, moneyKill, health, maxHealth, range, price;
    public float healthBar;
    public String type;
    public boolean isAlive = true;
    public boolean select = false;
    public boolean stats = false;
    public String direction;

    public void setDefaultValues(){}


    public void setStats() {
        stats = !stats;
    }


    public void setBounds(Rectangle bounds) {
        this.bounds = bounds;
    }




    public Rectangle getBounds() {
        return bounds;
    }




    public boolean isSelect() {
        return select;
    }




    public void setSelect() {
        select = !select;
    }

    public GamePanel getGp() {
        return gp;
    }



    public void findDirection(){
        switch (direction) {
            case "right":
                if(TileManager.mapTileNum[x/48][y/48 + 1] == 1){
                    direction = "down";
                    return;
                }
                if(TileManager.mapTileNum[x/48][y/48 - 1] == 1){
                    direction = "up";
                    return;
                }
                direction = "left";
                return;
                
            case "left":
                if(TileManager.mapTileNum[x/48][y/48 + 1] == 1){
                    direction = "down";
                    return;
                }
                if(TileManager.mapTileNum[x/48][y/48 - 1] == 1){
                    direction = "up";
                    return;
                }
                direction = "right";
                return;
                
            case "up":
                if(TileManager.mapTileNum[x/48 + 1][y/48] == 1){
                    direction = "right";
                    return;
                }
                if(TileManager.mapTileNum[x/48 - 1][y/48] == 1){
                    direction = "left";
                    return;
                }
                direction = "down";
                return;
                
            case "down":
                if(TileManager.mapTileNum[x/48 + 1][y/48] == 1){
                    direction = "right";
                    return;
                }
                if(TileManager.mapTileNum[x/48 - 1][y/48] == 1){
                    direction = "left";
                    return;
                }
                direction = "up";
                return;
        }
    }

    public void calculatePath(){

        if(x>0){
            switch (direction) {
                case "right":
                    if(TileManager.mapTileNum[(x + GamePanel.tileSize) / GamePanel.tileSize][y/GamePanel.tileSize] != 1){
                        findDirection();
                    }
                    break;
                    
                case "left":
                    if(TileManager.mapTileNum[(x - speed) / GamePanel.tileSize][y/GamePanel.tileSize] != 1){
                        findDirection();
                    }
                    break;
                    
                case "up":
                    if(TileManager.mapTileNum[x/GamePanel.tileSize][(y - speed) / GamePanel.tileSize] != 1){
                        findDirection();
                    }
                    break;
                    
                case "down":
                    if(TileManager.mapTileNum[x/GamePanel.tileSize][(y + GamePanel.tileSize) / GamePanel.tileSize] != 1){
                        findDirection();
                    }
                    

                    break;
            }
        }
        if (x<-100 && y!= 336) {
            System.out.println("SO BAD LOL");
            GamePanel.score -= points*2;
            eventManager.money -= 69;
            eventManager.escape(this);
            isAlive = false;
            return;
        }

        if(direction == "right"){
            x += speed;
        }
        if (direction == "down") {
            y += speed;
        }
        if (direction == "left") {
            x -= speed;
        }
        if (direction == "up") {
            y -= speed;
        }
    }

    public void calculateHealth(){
        healthBar = (float) health / maxHealth;
        if (health <= 0) {
            eventManager.kill(this);
            isAlive = false;
        }
    }

    public void attackIfInRange(){}

    public int getHypoDistance(int x1, int y1, int x2, int y2){
        int xDiff = Math.abs(x1 - x2);
        int YDiff = Math.abs(y1 - y2);

        return (int) Math.hypot(xDiff, YDiff);
    }

    public BufferedImage flipImage(BufferedImage img) {
        BufferedImage flippedImage = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_ARGB);
        AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
        tx.translate(-img.getWidth(null), 0);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        return op.filter(img, flippedImage);
    }

    public void update(){}

    public void draw(Graphics2D g2){}

    public void drawRange(Graphics2D g2, int r){

        int circleX = x + (GamePanel.tileSize / 2) - r;
        int circleY = y + (GamePanel.tileSize / 2) - r;

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

}


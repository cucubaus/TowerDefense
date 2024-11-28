package entity;

import static main.Constants.RedTowerConstants.*;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;

import UI.EntityUI;
import main.GamePanel;


public class RedTower extends Entity {

      
    
    int dmg, cooldown, lastAction;
    Point beam = null;
    long lastAttackTime;
    String type = new String("redTower");

    BufferedImage sheetArray[][];
    BufferedImage img;
    


    public RedTower (GamePanel gp, int x, int y){
        this.gp = gp;
        this.x = x;
        this.y = y;

        setDefaultValues();

        this.sheetArray = GamePanel.sheetMap.get(type);
        this.entityUI = new EntityUI(this);


    }

    public void setDefaultValues() {
        aniSpeed = 7;
        action = IDLE;
        lastAction = action;
        range = RANGE;
        dmg = DMG;
        cooldown = COOLDOWN; //200 ms - 0.2s
        lastAttackTime = 0;
        price = PRICE;
    }

    public void attackIfInRange(){

        long currentTime = System.currentTimeMillis();


        if (GamePanel.enemyEntities.isEmpty()) {
            action = IDLE;
            beam = null;
        } else {
            for(Entity e : GamePanel.enemyEntities){

                if(isEnemyInRange(e)){
                    action = ATTACK;
                    beam = new Point(e.x, e.y);
                    if (currentTime - lastAttackTime >= cooldown) {
                        e.health -= dmg;
                        lastAttackTime = currentTime;
                        return;
                    }
                    return;
                } 
                
            }
            beam = null;
            action = IDLE;
        }

    }

    public boolean isEnemyInRange(Entity enemy){
        
        int distance = getHypoDistance(x, y, enemy.x, enemy.y);
        return distance < range;
    }

    public void update(){

        attackIfInRange();
        updateTick();
    }

    

    public void draw(Graphics2D g2){

        img = sheetArray[action][aniIndex];

        g2.drawImage(img, x, y, GamePanel.tileSize, GamePanel.tileSize, null);

        if (beam != null) {
            g2.setColor(Color.RED);
            g2.drawLine(x + 20, y + 20, (int) beam.getX() + 24, (int) beam.getY() + 24);
        }

        entityUI.draw(g2);


    }

    public void updateTick(){

        if (lastAction != action) {
            aniIndex = 0;
            lastAction = action;
        }

        aniTick++;
        if (aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= getSpriteAmount(action)) {
                aniIndex = 0;
            }
        }
        
    }

    

}

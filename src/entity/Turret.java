package entity;

import static main.Constants.TurretConstants.*;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import UI.EntityUI;
import main.GamePanel;


public class Turret extends Entity {

    int dmg, cooldown, lastAction;
    long lastAttackTime;
    String type = new String("turret");

    BufferedImage sheetArray[][];
    
    public Turret (GamePanel gp, int x, int y){
        this.gp = gp;
        this.x = x;
        this.y = y;


        setDefaultValues();

        this.sheetArray = GamePanel.sheetMap.get(type);
        this.entityUI = new EntityUI(this);

    }

    public void setDefaultValues() {
        aniSpeed = 5;
        action = IDLE;
        lastAction = action;
        range = RANGE;
        dmg = DMG;
        cooldown = COOLDOWN;
        lastAttackTime = 0;
        price = PRICE;
    }

    public void attackIfInRange(){

        long currentTime = System.currentTimeMillis();

        if (GamePanel.enemyEntities.isEmpty()) {
            action = IDLE;
        }

        for(Entity e : GamePanel.enemyEntities){

            if(e.isAlive && isEnemyInRange(e)){
                action = ATTACK;
                //determineDirection(e);
                if (currentTime - lastAttackTime >= cooldown) {
                    e.health -= dmg;
                    lastAttackTime = currentTime;
                    break;
                }
                break;
            }
            action = IDLE;
        }
    }

    public Entity getNearestEnemy(){

        if (GamePanel.enemyEntities.isEmpty()) {
            return null;
        }

        for(Entity e : GamePanel.enemyEntities){

            if(e.isAlive && isEnemyInRange(e)){
                return e;
            }
        }
        return null;
    }

    public boolean isEnemyInRange(Entity enemy){
        
        int distance = getHypoDistance(x, y, enemy.x, enemy.y);
        
        return distance < RANGE;
    }

    public void update(){
        attackIfInRange();
        updateTick();
    }

    public void draw(Graphics2D g2){

        BufferedImage img = sheetArray[action][aniIndex];

        Entity enemy = getNearestEnemy();
        if (enemy != null){

            double deltaX = enemy.x - this.x;
            double deltaY = enemy.y - this.y;
            double angle = Math.atan2(deltaY, deltaX);
            
            AffineTransform oldTransform = g2.getTransform();
            AffineTransform transform = new AffineTransform();
            transform.rotate(angle, x + (GamePanel.tileSize / 2), y + (GamePanel.tileSize / 2));
            g2.setTransform(transform);

            g2.drawImage(img, x, y, GamePanel.tileSize, GamePanel.tileSize, null);
            
            g2.setTransform(oldTransform);
        } else {
            g2.drawImage(img, x, y, GamePanel.tileSize, GamePanel.tileSize, null);
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

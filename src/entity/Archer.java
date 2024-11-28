package entity;

import static main.Constants.ArcherConstants.*;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import UI.EntityUI;
import main.GamePanel;


public class Archer extends Entity {

      
    
    int dmg, cooldown, lastAction;
    boolean flip = false;
    long lastAttackTime;
    String type = new String("archer");

    BufferedImage sheetArray[][];
    BufferedImage img;
    


    public Archer (GamePanel gp, int x, int y){
        this.gp = gp;
        this.x = x;
        this.y = y;

        setDefaultValues();

        this.sheetArray = GamePanel.sheetMap.get(type);
        this.entityUI = new EntityUI(this);


    }

    public void setDefaultValues() {
        aniSpeed = 7;
        action = IDLE_D;
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
            action = IDLE_D;
        } else {
            for(Entity e : GamePanel.enemyEntities){

                if(isEnemyInRange(e)){
                    determineDirection(e);
                    if (currentTime - lastAttackTime >= cooldown) {
                        e.health -= dmg;
                        lastAttackTime = currentTime;
                        return;
                    }
                    return;
                } 
                
            }
            action = IDLE_D;
        }

    }

    public boolean isEnemyInRange(Entity enemy){
        
        int distance = getHypoDistance(x, y, enemy.x, enemy.y);
        return distance < range;
    }

    public void determineDirection(Entity enemy) {
        double deltaX = enemy.x - this.x;
        double deltaY = enemy.y - this.y;
        double angle = Math.atan2(deltaY, deltaX);
        
        // Convert angle to degrees
        double angleDegrees = Math.toDegrees(angle);
        
        // Determine direction based on angle
        if (angleDegrees >= -45 && angleDegrees < 45) {
            // Facing right
            flip = true;
            action = ATTACK_S;
        } else if (angleDegrees >= 45 && angleDegrees < 135) {
            // Facing down
            action = ATTACK_D;
            flip = false;
        } else if (angleDegrees >= 135 || angleDegrees < -135) {
            // Facing left
            action = ATTACK_S;
            flip = false;
        } else {
            // Facing up
            action = ATTACK_U;
            flip = false;
        }
    }

    public void update(){

        attackIfInRange();
        updateTick();
    }

    

    public void draw(Graphics2D g2){

        img = sheetArray[action][aniIndex];

        if (flip) {
            img = flipImage(img);
        }


        g2.drawImage(img, x, y, GamePanel.tileSize, GamePanel.tileSize, null);

        entityUI.draw(g2);

        // drawRange(g2, range);


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

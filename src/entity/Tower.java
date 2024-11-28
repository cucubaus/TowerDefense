package entity;

import static main.Constants.TowerConstants.*;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import UI.EntityUI;
import events.eventManager;
import main.GamePanel;


public class Tower extends Entity {

      
    
    int cooldown, lastAction, printAmount;
    long lastMoneyPrint;
    String type = new String("tower");

    BufferedImage sheetArray[][];
    BufferedImage img;
    


    public Tower (GamePanel gp, int x, int y){
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
        cooldown = COOLDOWN; //2000 ms - 2.0s
        lastMoneyPrint = 0;
        printAmount = 1;
        price = PRICE;
    }

    public void printMoney(){

        long currentTime = System.currentTimeMillis();


        if (currentTime - lastMoneyPrint >= cooldown) {

            eventManager.money += printAmount;
            lastMoneyPrint = currentTime;
        }

    }

    public void update(){

        if (GamePanel.enemies - GamePanel.enemiesKilled != 0) {
            printMoney();
        }
        
        updateTick();
    }

    

    public void draw(Graphics2D g2){

        img = sheetArray[action][aniIndex];


        g2.drawImage(img, x, y-41, GamePanel.tileSize, GamePanel.tileSize+41, null);

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

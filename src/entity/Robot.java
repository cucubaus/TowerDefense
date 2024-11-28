package entity;

import static main.Constants.RobotConstants.*;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import main.GamePanel;

public class Robot extends Entity {


    BufferedImage sheetArray[][];

    public Robot (GamePanel gp, int x, int y){
        this.gp = gp;
        this.x = x;
        this.y = y;

        setDefaultValues();

        this.sheetArray = GamePanel.sheetMap.get(TYPE);
    }

    public Robot (GamePanel gp){
        this.gp = gp;
        this.x = X_START;
        this.y = Y_START;

        setDefaultValues();

        this.sheetArray = GamePanel.sheetMap.get(TYPE);
    }

    public void setDefaultValues() {
        direction = "right";
        speed = SPEED;
        action = MOVE_S;
        health = MAX_HEALH;
        maxHealth = MAX_HEALH;
        healthBar = health / maxHealth;
        points = POINTS;
        moneyEscape = MONEY_ESCAPE;
        moneyKill = MONEY_KILL;
    }

    public void setAnimation(){
        switch (direction) {
            case "right":
                action = MOVE_S;
                break;
            case "left":
                action = MOVE_S;
                break;
            case "up":
                action = MOVE_U;
                break;
            case "down":
                action = MOVE_D;
                break;
            default:
                action = MOVE_S;
                break;
        }
    }

    public void update(){

        calculatePath();
        calculateHealth();

        setAnimation();
        updateTick();

    }

    public void draw(Graphics2D g2){

        BufferedImage img = sheetArray[action][aniIndex];

        if (direction == "left") {
            img = flipImage(img);
        }

        g2.drawImage(img, x, y, GamePanel.tileSize, GamePanel.tileSize, null);

        g2.setColor(Color.red);
        g2.fillRect(x, y, (int) (GamePanel.tileSize * healthBar), 3);

    }

    public void updateTick(){

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

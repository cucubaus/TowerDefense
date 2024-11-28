package entity;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import events.eventManager;
import main.*;

public class EntityImages {

    public BufferedImage tank1, tank2;
    public BufferedImage tower1, tower2;
    public BufferedImage archer1, archer2;
    public BufferedImage zombie1, zombie2;
    public BufferedImage turret1, turret2, turret3, turret4, turret5, turret6;
    public BufferedImage live,live2,live3,empty1,empty2,empty3;

    public BufferedImage beeSheet, bloopSheet, cheeseSheet, orcSheet, robotSheet, wolfSheet, archerSheet, turretSheet, banner, towerSheet, redTowerSheet;

    BufferedImage sheetArray[][];

    public void load(){
        loadImages();
        loadSheets();
        loadImagesBaner();
        loadFont();
        loadIcons();
    }

    public void loadImages(){

        try {

            live = ImageIO.read(getClass().getResourceAsStream("/res/lives/1.png"));
            eventManager.livesFull[0] = live;

            live = ImageIO.read(getClass().getResourceAsStream("/res/lives/2.png"));
            for (int i = 1; i < eventManager.lives - 1; i++) {
                eventManager.livesFull[i] = live;
            }

            live = ImageIO.read(getClass().getResourceAsStream("/res/lives/3.png"));
            eventManager.livesFull[eventManager.lives -1] = live;

            live = ImageIO.read(getClass().getResourceAsStream("/res/lives/7.png"));
            eventManager.livesEmpty[0] = live;

            live = ImageIO.read(getClass().getResourceAsStream("/res/lives/8.png"));
            for (int i = 1; i < eventManager.lives - 1; i++) {
                eventManager.livesEmpty[i] = live;
            }

            live = ImageIO.read(getClass().getResourceAsStream("/res/lives/9.png"));
            eventManager.livesEmpty[eventManager.lives -1] = live;

            
        } catch (IOException e) {
            e.printStackTrace();
        }

        
    }

    public void loadIcons(){

        try {
            BufferedImage img;

            img = ImageIO.read(getClass().getResourceAsStream("/res/icons/towerIcon.png"));
            eventManager.iconMap.put("towerIcon", img);

            img = ImageIO.read(getClass().getResourceAsStream("/res/icons/tankIcon.png"));
            eventManager.iconMap.put("tankIcon", img);

            img = ImageIO.read(getClass().getResourceAsStream("/res/icons/archerIcon.png"));
            eventManager.iconMap.put("archerIcon", img);

            img = ImageIO.read(getClass().getResourceAsStream("/res/icons/turretIcon.png"));
            eventManager.iconMap.put("turretIcon", img);

            img = ImageIO.read(getClass().getResourceAsStream("/res/icons/redTowerIcon.png"));
            eventManager.iconMap.put("redTowerIcon", img);
            
            img = ImageIO.read(getClass().getResourceAsStream("/res/icons/xIcon.png"));
            eventManager.iconMap.put("xIcon", img);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadImagesBaner(){

        try {
            BufferedImage img;
            img = ImageIO.read(getClass().getResourceAsStream("/res/banner/banner1.png"));
            eventManager.bannerMap.put("1", img);

            img = ImageIO.read(getClass().getResourceAsStream("/res/banner/banner2.png"));
            eventManager.bannerMap.put("2", img);

            img = ImageIO.read(getClass().getResourceAsStream("/res/banner/banner3.png"));
            eventManager.bannerMap.put("3", img);

            img = ImageIO.read(getClass().getResourceAsStream("/res/banner/banner4.png"));
            eventManager.bannerMap.put("4", img);

            img = ImageIO.read(getClass().getResourceAsStream("/res/banner/banner5.png"));
            eventManager.bannerMap.put("5", img);

            img = ImageIO.read(getClass().getResourceAsStream("/res/banner/banner6.png"));
            eventManager.bannerMap.put("6", img);

            img = ImageIO.read(getClass().getResourceAsStream("/res/banner/banner7.png"));
            eventManager.bannerMap.put("7", img);

            img = ImageIO.read(getClass().getResourceAsStream("/res/banner/banner8.png"));
            eventManager.bannerMap.put("8", img);

            img = ImageIO.read(getClass().getResourceAsStream("/res/banner/price.png"));
            eventManager.bannerMap.put("price", img);

            img = ImageIO.read(getClass().getResourceAsStream("/res/banner/bannerInfo.png"));
            eventManager.bannerMap.put("bannerBottom", img);

            img = ImageIO.read(getClass().getResourceAsStream("/res/banner/infoBar.png"));
            eventManager.bannerMap.put("infoBar", img);

            img = ImageIO.read(getClass().getResourceAsStream("/res/banner/archerIcon.png"));
            eventManager.bannerMap.put("archerIcon", img);

            img = ImageIO.read(getClass().getResourceAsStream("/res/banner/stat.png"));
            eventManager.bannerMap.put("stat", img);

            img = ImageIO.read(getClass().getResourceAsStream("/res/banner/cash.png"));
            eventManager.bannerMap.put("cash", img);

            img = ImageIO.read(getClass().getResourceAsStream("/res/banner/menuBar.png"));
            eventManager.bannerMap.put("menuBar", img);

            img = ImageIO.read(getClass().getResourceAsStream("/res/banner/levelInfo.png"));
            eventManager.bannerMap.put("levelInfo", img);

            img = ImageIO.read(getClass().getResourceAsStream("/res/banner/button.png"));
            eventManager.bannerMap.put("button", img);

            img = ImageIO.read(getClass().getResourceAsStream("/res/banner/count.png"));
            eventManager.bannerMap.put("count", img);

            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }





    public void loadSheets(){

        try {

            archerSheet = ImageIO.read(getClass().getResourceAsStream("/res/sheets/archerSheet.png"));  // 6x6
            beeSheet = ImageIO.read(getClass().getResourceAsStream("/res/sheets/beeSheet.png"));        // 3x6
            bloopSheet = ImageIO.read(getClass().getResourceAsStream("/res/sheets/bloopSheet.png"));    // 3x6
            cheeseSheet = ImageIO.read(getClass().getResourceAsStream("/res/sheets/cheeseSheet.png"));  // 4x3
            orcSheet = ImageIO.read(getClass().getResourceAsStream("/res/sheets/orcSheet.png"));        // 3x6
            robotSheet = ImageIO.read(getClass().getResourceAsStream("/res/sheets/robotSheet.png"));    // 4x3
            wolfSheet = ImageIO.read(getClass().getResourceAsStream("/res/sheets/wolfSheet.png"));      // 3x6
            turretSheet = ImageIO.read(getClass().getResourceAsStream("/res/sheets/turretSheet.png"));  // 3x6
            towerSheet = ImageIO.read(getClass().getResourceAsStream("/res/sheets/towerSheet.png"));    // 1x6
            redTowerSheet = ImageIO.read(getClass().getResourceAsStream("/res/sheets/redTowerSheet.png")); // 2x10
            
        } catch (IOException e) {
            e.printStackTrace();
        }

        sheetArray = new BufferedImage[6][6];
        for (int j = 0; j < sheetArray.length; j++){
            for (int i = 0; i < sheetArray[j].length; i++) {
                sheetArray[j][i] = archerSheet.getSubimage(i*48, j*48, 48, 48);
            }
        }
        GamePanel.sheetMap.put("archer", sheetArray);

        sheetArray = new BufferedImage[3][6];
        for (int j = 0; j < sheetArray.length; j++){
            for (int i = 0; i < sheetArray[j].length; i++) {
                sheetArray[j][i] = beeSheet.getSubimage(i*48, j*48, 48, 48);
            }
        }
        GamePanel.sheetMap.put("bee", sheetArray);

        sheetArray = new BufferedImage[3][6];
        for (int j = 0; j < sheetArray.length; j++){
            for (int i = 0; i < sheetArray[j].length; i++) {
                sheetArray[j][i] = bloopSheet.getSubimage(i*48, j*48, 48, 48);
            }
        }
        GamePanel.sheetMap.put("bloop", sheetArray);

        sheetArray = new BufferedImage[4][3];
        for (int j = 0; j < sheetArray.length; j++){
            for (int i = 0; i < sheetArray[j].length; i++) {
                sheetArray[j][i] = cheeseSheet.getSubimage(i*48, j*48, 48, 48);
            }
        }
        GamePanel.sheetMap.put("cheese", sheetArray);

        sheetArray = new BufferedImage[3][6];
        for (int j = 0; j < sheetArray.length; j++){
            for (int i = 0; i < sheetArray[j].length; i++) {
                sheetArray[j][i] = orcSheet.getSubimage(i*48, j*48, 48, 48);
            }
        }
        GamePanel.sheetMap.put("orc", sheetArray);

        sheetArray = new BufferedImage[4][3];
        for (int j = 0; j < sheetArray.length; j++){
            for (int i = 0; i < sheetArray[j].length; i++) {
                sheetArray[j][i] = robotSheet.getSubimage(i*48, j*48, 48, 48);
            }
        }
        GamePanel.sheetMap.put("robot", sheetArray);

        sheetArray = new BufferedImage[3][6];
        for (int j = 0; j < sheetArray.length; j++){
            for (int i = 0; i < sheetArray[j].length; i++) {
                sheetArray[j][i] = wolfSheet.getSubimage(i*48, j*48, 48, 48);
            }
        }
        GamePanel.sheetMap.put("wolf", sheetArray);

        sheetArray = new BufferedImage[2][9];
        for (int j = 0; j < sheetArray.length; j++){
            for (int i = 0; i < sheetArray[j].length; i++) {
                sheetArray[j][i] = turretSheet.getSubimage(i*32, j*32, 32, 32);
            }
        }
        GamePanel.sheetMap.put("turret", sheetArray);

        sheetArray = new BufferedImage[1][6];
        for (int j = 0; j < sheetArray.length; j++){
            for (int i = 0; i < sheetArray[j].length; i++) {
                sheetArray[j][i] = towerSheet.getSubimage(i*48, j*48, 48, 89);
            }
        }
        GamePanel.sheetMap.put("tower", sheetArray);

        sheetArray = new BufferedImage[2][10];
        for (int j = 0; j < sheetArray.length; j++){
            for (int i = 0; i < sheetArray[j].length; i++) {
                sheetArray[j][i] = redTowerSheet.getSubimage(i*48, j*67, 48, 67);
            }
        }
        GamePanel.sheetMap.put("redTower", sheetArray);

    }

    public void loadFont() {
        try {
            // Load custom font
            eventManager.customFont = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/res/ThaleahFat.ttf"));
            // You might need to adjust the font size and style here
            eventManager.customFont = eventManager.customFont.deriveFont(Font.BOLD, 28);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
            // If the font loading fails, fallback to a default font
            eventManager.customFont = new Font("Arial", Font.BOLD, 20);
        }
    }

}

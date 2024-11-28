package events;

import java.awt.Font;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import entity.Entity;
import entity.EntityInfo;
import main.GamePanel;

public class eventManager {

    public static int money = 10010;
    public static int points = 0;
    public static int lives = 20;
    public static int currentLives = lives;
    public static Font customFont;
    public static boolean waitPlacement = false;
    public static String waitPlacementEntity = null;

    public static boolean running = true;
    

    public static String info_dmg = new String("");
    public static String info_range = new String("");
    public static String info_atkspeed = new String("");

    public static BufferedImage[] livesFull = new BufferedImage[lives];
    public static BufferedImage[] livesEmpty = new BufferedImage[lives];
    public static Map<String, BufferedImage> bannerMap = new HashMap<>();
    public static Map<String, BufferedImage> iconMap = new HashMap<>();
    public static Map<String, EntityInfo> entityInfo = new HashMap<>();
    public static EntityInfo[] entityInfoArray = new EntityInfo[8];

    public static EntityInfo selectedEntityInfo = new EntityInfo("", 0, 0, 0, 0);

    public static void escape(Entity e){
        currentLives--;
        livesFull[currentLives] = livesEmpty[currentLives];
        money += e.moneyEscape;
        points -= e.points*2;
        GamePanel.enemiesKilled++;

    }

    public static void kill(Entity e){
        money += e.moneyKill;
        points += e.points;
        GamePanel.enemiesKilled++;

    }

    public static void checkButton(){

    }

    public static void initItems(){
        EntityInfo e = new EntityInfo("archer", 100, 15, 144, 25);
        entityInfo.put("archer", e);
        entityInfoArray[0] = e;

        e = new EntityInfo("turret", 225, 50, 200, 75);
        entityInfo.put("turret", e);
        entityInfoArray[1] = e;

        e = new EntityInfo("tower", 1000, 0, 0, 0);
        entityInfo.put("tower", e);
        entityInfoArray[3] = e;

        e = new EntityInfo("redTower", 800, 1, 200, 5);
        entityInfo.put("redTower", e);
        entityInfoArray[2] = e;

    }

	public static void setSelectedEntityInfo(String type) {
		selectedEntityInfo = entityInfo.get(type);
	}

    public static void waitPlacement(String type){
        waitPlacement = true;
        waitPlacementEntity = type;
        
    }

    

    

    
    

}

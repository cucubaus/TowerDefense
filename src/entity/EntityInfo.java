package entity;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import events.eventManager;
import main.GamePanel;

public class EntityInfo {

    String type;
    int price, dmg, range, atk_speed;
    BufferedImage icon;

	public EntityInfo(String type, int price, int dmg, int range, int atk_speed) {
		this.type = type;
		this.price = price;
		this.dmg = dmg;
		this.range = range;
		this.atk_speed = atk_speed;
        icon = getImage(type);

	}


    public BufferedImage getImage(String type){
        switch (type) {
            case "tank":
                return eventManager.iconMap.get("tankIcon");
            case "tower":
                return eventManager.iconMap.get("towerIcon");
            case "archer":
                return eventManager.iconMap.get("archerIcon");
            case "turret":
                return eventManager.iconMap.get("turretIcon");
			case "redTower":
                return eventManager.iconMap.get("redTowerIcon");
            default:
                break;
        }

        return eventManager.iconMap.get("xIcon");

    }

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getDmg() {
		return dmg;
	}

	public void setDmg(int dmg) {
		this.dmg = dmg;
	}

	public int getRange() {
		return range;
	}

	public void setRange(int range) {
		this.range = range;
	}

	public int getAtk_speed() {
		return atk_speed;
	}

	public void setAtk_speed(int atk_speed) {
		this.atk_speed = atk_speed;
	}

    

    

}

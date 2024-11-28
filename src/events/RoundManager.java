package events;

import java.util.ArrayList;
import java.util.Arrays;

import entity.*;
import main.GamePanel;

public class RoundManager {

    GamePanel gp;
    private long lastSpawnTime = 0;
    int i = 0;
    Round round1,round2,round3;
    ArrayList<Round> rounds = new ArrayList<Round>();
    ArrayList<Integer> currentRound = new ArrayList<Integer>();

    public static boolean newRound = false;


    public RoundManager(GamePanel gp){
        this.gp = gp;
        initRounds();
        currentRound = rounds.get(GamePanel.round-1).getEnemyList();
    }

	public void initRounds() {
		round1 = new Round(new ArrayList<Integer>(Arrays.asList(1,2,3,4,5,6,1,2,3,4,5,6)));
        rounds.add(round1);
        round2 = new Round(new ArrayList<Integer>(Arrays.asList(1,2,2,1,2,2,1,2,1,1,2,2,1)));
        rounds.add(round2);
        round3 = new Round(new ArrayList<Integer>(Arrays.asList(2,1,1,1,2,2,1,2,1,1,2,2,1,2,1,2,2,2,1,1,1,2,1)));
        rounds.add(round3);
        
	}

    public void spawn(){
        long currentTime = System.nanoTime();

        if (newRound) {
            newRound();
            newRound = false;
        }

        if (eventManager.running) {
            GamePanel.enemies = currentRound.size();


            if ((currentTime - lastSpawnTime) >= 5e9){
                switch (currentRound.get(i)) {
                    case 1:
                        Entity e = new Bee(gp);
                        GamePanel.enemyEntities.add(e);
                        break;
                    case 2:
                        e = new Robot(gp);
                        GamePanel.enemyEntities.add(e);
                        break;
                    case 3:
                        e = new Bloop(gp);
                        GamePanel.enemyEntities.add(e);
                        break;
                    case 4:
                        e = new Wolf(gp);
                        GamePanel.enemyEntities.add(e);
                        break;
                    case 5:
                        e = new Orc(gp);
                        GamePanel.enemyEntities.add(e);
                        break;
                    case 6:
                        e = new Cheese(gp);
                        GamePanel.enemyEntities.add(e);
                        break;
                }
                i++;
                
                
                if(currentRound.size() == i){
                    eventManager.running = false;
                }

                lastSpawnTime = currentTime;

            }
            
        }

        

    }

    public void newRound(){
        i=0;
        GamePanel.round++;
        eventManager.running = true;
        currentRound = rounds.get(GamePanel.round-1).getEnemyList();

    }


}

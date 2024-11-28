package main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.swing.JPanel;

import UI.PanelEast;
import entity.*;
import events.RoundManager;
import events.eventManager;
import inputs.*;
import tiles.*;

public class GamePanel extends JPanel implements Runnable{

    //SCREEN SETTINGS
    final static int originalTileSize = 16; // 16x16 tile
    final static int scale = 3;

    public static final int tileSize = originalTileSize * scale;             // 48x48 tile
    public static final int maxScreenCol = 20;
    public static final int tempMaxScreenRow = 15;                  
    public static final int maxScreenRow = 18;
    public static final int gameScreenHeight = tempMaxScreenRow * tileSize;  // 720 PX
    public static final int screenWidth = tileSize * maxScreenCol;           // 960 px
    public static final int screenHeight = tileSize * maxScreenRow;          // 864 px

    private static int FPS = 60;
    public static boolean gamePaused = false;

    public static boolean noEnemiesLeft = false;
    public static int round = 1;
    public static int enemies = 0;
    public static int enemiesKilled = 0;
    public static boolean cursor_state = false;
    public static String itemSelected = null;
    public static Point selectedTile = null;
    public static boolean itemPlaced = true;
    public static int score = 0;

    public MyMouseListener myMouseListener;
    private KeyboardListener keyboardListener;

    public TileManager tileM;
    public RoundManager roundManager;

    public static ArrayList<Point> entityPosition = new ArrayList<Point>();

    public static Map<String, BufferedImage[][]> sheetMap = new HashMap<>();

    public static ArrayList<Entity> enemyEntities = new ArrayList<Entity>();
    public static ArrayList<Entity> allyEntities = new ArrayList<Entity>();

    Thread gameThread;

    public GamePanel(){


        setPreferredSize(new Dimension(screenWidth, gameScreenHeight));
        setDoubleBuffered(true);
        setFocusable(true);

        initInputs();
        initComponents();

        
    }




    public void initComponents(){
        tileM = new TileManager(this);

        EntityImages entityImg = new EntityImages();
        entityImg.load();

        roundManager = new RoundManager(this);
        eventManager.initItems();
        

    }

    public void initInputs(){

        myMouseListener = new MyMouseListener();
        keyboardListener = new KeyboardListener();
        this.addMouseListener(myMouseListener);
        this.addMouseMotionListener(myMouseListener);
        this.addKeyListener(keyboardListener);

        requestFocus();

    }



    public void initEntity(String type){

        

        switch (type) {
            case "archer":
                Entity entity = new Archer(this, (int)selectedTile.getX() * tileSize, (int)selectedTile.getY() * tileSize);
                allyEntities.add(entity);
                entityPosition.add(selectedTile);
                eventManager.money -= entity.price;
                break;
            case "turret":
                entity = new Turret(this, (int)selectedTile.getX() * tileSize, (int)selectedTile.getY() * tileSize);
                allyEntities.add(entity);
                entityPosition.add(selectedTile);
                eventManager.money -= entity.price;
                break;
            case "tower":
                entity = new Tower(this, (int)selectedTile.getX() * tileSize, (int)selectedTile.getY() * tileSize);
                allyEntities.add(entity);
                entityPosition.add(selectedTile);
                eventManager.money -= entity.price;
                break;
            case "redTower":
                entity = new RedTower(this, (int)selectedTile.getX() * tileSize, (int)selectedTile.getY() * tileSize);
                allyEntities.add(entity);
                entityPosition.add(selectedTile);
                eventManager.money -= entity.price;
                break;
                
            default:
                break;
        }

    }

    public void startGameThread(){

        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        double drawInterval = 1_000_000_000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        
        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if(delta >= 1){
                update();
                repaint();
                delta--;
            }
            
        }
    }
    
    public void update() {

        if (gamePaused) {
            return;
        }


        roundManager.spawn();

        if( selectedTile != null && eventManager.waitPlacement && !entityPosition.contains(selectedTile)){

            initEntity(itemSelected);
            
            cursor_state = !cursor_state;
            itemPlaced = !itemPlaced;
            selectedTile = null;
            eventManager.waitPlacement = false;
            eventManager.waitPlacementEntity = null;
        }

        for(Entity e : enemyEntities){
            e.update();
        }

        for(Entity e : allyEntities){
            e.update();
        }
        

        Iterator<Entity> iterator = enemyEntities.iterator();
        while (iterator.hasNext()) {
            Entity e = iterator.next();
            if (!e.isAlive) {
                iterator.remove();
            }
        }

        if(enemies - enemiesKilled == 0 && PanelEast.resumeButton.getText().equals("ROUND IN PROGRESS")){
            noEnemiesLeft = true;
            PanelEast.resumeButtonUpdate();
        }



    }

    public void paintComponent (Graphics g){

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        tileM.draw(g2);

        for(Entity e : enemyEntities){
            if(e.isAlive){
                e.draw(g2);
            }
        }

        for(Entity e : allyEntities){
            e.draw(g2);
        }

        g2.dispose();

    }


    

    
}

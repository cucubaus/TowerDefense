package tiles;
import java.awt.Graphics2D;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;

import events.eventManager;
import inputs.*;
import main.*;

public class TileManager{

    MyMouseListener mouseH;
    GamePanel gp;
    Tile[] tile;
    public static int mapTileNum[][];


    public TileManager(GamePanel gp){
        
        this.gp = gp;
        mouseH = gp.myMouseListener;

        tile = new Tile[20];
        mapTileNum = new int[GamePanel.maxScreenCol][GamePanel.tempMaxScreenRow];
        getTileImage();
        loadMap();
        
        
    }

    public void getTileImage(){

        try {
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/res/grass00.png"));
            
            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/res/road01.png"));

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/res/road02.png"));

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/res/road03.png"));

            tile[4] = new Tile();
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("/res/road04.png"));

            tile[5] = new Tile();
            tile[5].image = ImageIO.read(getClass().getResourceAsStream("/res/road05.png"));

            tile[6] = new Tile();
            tile[6].image = ImageIO.read(getClass().getResourceAsStream("/res/road06.png"));

            tile[7] = new Tile();
            tile[7].image = ImageIO.read(getClass().getResourceAsStream("/res/road07.png"));

            tile[8] = new Tile();
            tile[8].image = ImageIO.read(getClass().getResourceAsStream("/res/road08.png"));

            tile[9] = new Tile();
            tile[9].image = ImageIO.read(getClass().getResourceAsStream("/res/road09.png"));

            tile[10] = new Tile();
            tile[10].image = ImageIO.read(getClass().getResourceAsStream("/res/cursor.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void loadMap(){

        try {
            InputStream is = getClass().getResourceAsStream("/res/map.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
        
            int row = 0;
        
            for (row = 0; row < gp.tempMaxScreenRow; row++) {
                String line = br.readLine();
                if (line == null) break;
                
                String numbers[] = line.split(" ");
                for (int col = 0; col < gp.maxScreenCol; col++) {
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                }
            }
            br.close();
        } catch (Exception e) {
            System.out.println("zzzzzzzzzzzzzzz####");
        }

    }

    public void draw(Graphics2D g2){

        
        int x = 0;
        int y = 0;

        for (int row = 0; row < gp.tempMaxScreenRow; row++) {
            for (int col = 0; col < gp.maxScreenCol; col++) {
                if (row >= gp.tempMaxScreenRow || col >= gp.maxScreenCol) {
                    break; // Exit the loop if we exceed the bounds
                }
                
                int tileNum = mapTileNum[col][row];
                g2.drawImage(tile[tileNum].image, col * gp.tileSize, row * gp.tileSize, gp.tileSize, gp.tileSize, null);
            }
        }

        if(mouseH.getCurrentTilePosition() != null && eventManager.waitPlacement == true) {
            x = mouseH.getCurrentTilePosition().x * GamePanel.tileSize;
            y = mouseH.getCurrentTilePosition().y * GamePanel.tileSize;
            
            if (mouseH.getCurrentMousePos().getY() < GamePanel.tempMaxScreenRow) {
                g2.drawImage(tile[10].image, x, y, GamePanel.tileSize, GamePanel.tileSize, null);
            }

            
        }

        

    }


}

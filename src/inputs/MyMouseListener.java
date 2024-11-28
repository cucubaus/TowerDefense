package inputs;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import entity.Entity;
import events.eventManager;
import main.GamePanel;

public class MyMouseListener implements MouseListener, MouseMotionListener {

    private Point currentMousePos = null;
    private Point currentTilePosition = null;
    private Point currentTileClicked = null;
    

    public Point getCurrentTilePosition() {
        return currentTilePosition;
    }

    public Point getCurrentTileClicked() {
        return currentTileClicked;
    }

    public Point getSelectedTile() {
        return GamePanel.selectedTile;
    }
    public Point getCurrentMousePos(){
        return currentMousePos;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int x_e = e.getX();
        int y_e = e.getY();
        int x = x_e / GamePanel.tileSize;
        int y = y_e / GamePanel.tileSize;
        // if(e.getButton() == MouseEvent.BUTTON1){
        //     if (x_e < GamePanel.screenWidth && y_e < GamePanel.maxScreenRow * GamePanel.tileSize && y_e > 720) {
        //         for(MenuItem item : GamePanel.menuItems){
        //             Rectangle bound = item.bounds;
        //             if(bound.contains(x_e, y_e) && item.buyPrice <= eventManager.money){
        //                 GamePanel.cursor_state = !GamePanel.cursor_state;
        //                 GamePanel.itemSelected = item.type;
        //                 System.out.println(item.type);
        //             } 
        //         }
        //     }
        //     if(x_e < GamePanel.screenWidth && y_e < 720 && y_e > 0 && GamePanel.itemSelected!= null && GamePanel.cursor_state){
        //         GamePanel.selectedTile = new Point(x, y);
        //     }
        // }
        if (x_e < GamePanel.screenWidth && y_e < 720 && e.getButton() == MouseEvent.BUTTON1) {
            for (Entity entity : GamePanel.allyEntities){
                Rectangle bound = entity.getBounds();
                if(bound.contains(x_e, y_e)){
                    entity.setSelect();
                    System.out.println("dsasadasd");

                }
            }
            
        }
        // if (e.getButton() == MouseEvent.BUTTON1){
        //     System.out.println(x_e + " " + y_e);
        // }

        if(x_e < GamePanel.screenWidth && y_e < 720 && y_e > 0 && eventManager.waitPlacementEntity!= null){
            GamePanel.selectedTile = new Point(x, y);
            GamePanel.itemSelected = eventManager.waitPlacementEntity;
            //System.out.println(GamePanel.itemSelected);

        }

        
        

    }

    @Override
    public void mousePressed(MouseEvent e) {
        // int x = e.getX() / GamePanel.tileSize;
        // int y = e.getY() / GamePanel.tileSize;
        // if (x < GamePanel.maxScreenCol && y < GamePanel.maxScreenRow) {
        //     currentTileClicked = new Point(x, y);
        // }
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // currentTileClicked = null;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // Handle mouse entered event
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // Handle mouse exited event
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // Handle mouse dragged event
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        int x = e.getX() / GamePanel.tileSize;
        int y = e.getY() / GamePanel.tileSize;
        currentTilePosition = new Point(x, y);
        currentMousePos = new Point(x, y);
    }
}
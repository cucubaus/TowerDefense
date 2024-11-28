package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;

import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import UI.BottomPanelCenter;
import UI.MyButton;
import UI.PanelEast;
import events.eventManager;

public class SecondPanel extends JPanel {

    private final GamePanel gp;

    ArrayList<MyButton> iconButtons = new ArrayList<MyButton>();

    

    public SecondPanel(GamePanel gp){
        this.gp = gp;


        setPreferredSize(new Dimension(960, 144));
        setDoubleBuffered(true);
        setFocusable(true);
        
        setLayout(new BorderLayout());

        JPanel panelWest = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;

                g2.drawImage(eventManager.bannerMap.get("infoBar"), 0, 0, null);
                g2.drawImage(eventManager.selectedEntityInfo.getImage(eventManager.selectedEntityInfo.getType()), 0, 0, null);
                g2.drawImage(eventManager.bannerMap.get("stat"), 70, 5, null);
                g2.drawImage(eventManager.bannerMap.get("stat"), 70, 35, null);
                g2.drawImage(eventManager.bannerMap.get("stat"), 70, 65, null);
                g2.drawImage(eventManager.bannerMap.get("stat"), 70, 95, null);

                String text_dps = new String("DPS:" + eventManager.selectedEntityInfo.getDmg());
                String text_range = new String("RANGE:" + eventManager.selectedEntityInfo.getRange());
                String text_atkspeed = new String("A. SPEED:" + eventManager.selectedEntityInfo.getAtk_speed());
                g2.setFont(eventManager.customFont);
                g2.setColor(Color.black);
                Font tempFont = eventManager.customFont.deriveFont(Font.PLAIN, 16);
                g2.setFont(tempFont);

                g2.drawString(text_dps, 88, 25);
                g2.drawString(text_range, 88, 55);
                g2.drawString(text_atkspeed, 88, 85);
                if (eventManager.selectedEntityInfo.getType().equals("tower")) {
                    tempFont = eventManager.customFont.deriveFont(Font.ROMAN_BASELINE, 16);
                    g2.setColor(Color.black);
                    g2.drawString("$10 / SEC", 88, 115);
                }

                repaint();

                g2.dispose();
            }
        };
        panelWest.setPreferredSize(new Dimension(197, 144));
        add(panelWest, BorderLayout.WEST);

        BottomPanelCenter panelCenter = new BottomPanelCenter();
        panelCenter.setPreferredSize(new Dimension(576, 144));
        add(panelCenter, BorderLayout.CENTER);

        PanelEast panelEast = new PanelEast();
        add(panelEast, BorderLayout.EAST);

        
    }

    public void loadIcons(){

        int xIcon = 144;

        for (int i = 1; i <= 8; i++) {
            Rectangle bounds = new Rectangle(xIcon, 720, 72, 72);
            MyButton button = new MyButton(bounds, eventManager.bannerMap.get(String.valueOf(i)));
            iconButtons.add(button);
            xIcon += 72;
        }
        
    }



    private static ImageIcon resizeIcon(ImageIcon icon, int resizedWidth, int resizedHeight) {
        Image img = icon.getImage();  
        Image resizedImage = img.getScaledInstance(resizedWidth, resizedHeight,  java.awt.Image.SCALE_SMOOTH);  
        return new ImageIcon(resizedImage);
    }


    

}

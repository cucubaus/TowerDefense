package UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import entity.EntityInfo;
import events.eventManager;
import main.GamePanel;

public class BottomPanelCenter extends JPanel {

    Map<String, BufferedImage> bannerMap = new HashMap<>();

    JPanel icons, info, price;

    public BottomPanelCenter() {

        setLayout(new BorderLayout());
        

        setPreferredSize(new Dimension(576, 144));
        setBackground(Color.YELLOW);
        setDoubleBuffered(true);
        setFocusable(true);

        icons = new JPanel();
        icons.setBackground(Color.GREEN);
        icons.setPreferredSize(new Dimension(576, 72));
        add(icons, BorderLayout.NORTH);
        innitIcons();

        price = new JPanel();
        price.setBackground(Color.GRAY);
        price.setPreferredSize(new Dimension(576, 32));
        add(price, BorderLayout.CENTER);
        innitPrice();

        info = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;

                g2.drawImage(eventManager.bannerMap.get("bannerBottom"), 0, 0, null);

                int x = 16;
                for (int i = 0; i < eventManager.lives; i++) {
                    g2.drawImage(eventManager.livesFull[i], x + i*16, 5, 16, 32, null);
                }

                g2.setColor(Color.BLUE);
                int containerWidth = getWidth() / 6;
                int containerHeight = getHeight() / 2;
                int containerX = getWidth() - (getWidth()/8) * 3 + 32;
                int containerY = (getHeight() - containerHeight) / 3 - 2;


                g2.drawImage(eventManager.bannerMap.get("cash"), containerX, containerY, null);

                String text = new String(eventManager.money + "$");


                // Draw text
                
                g2.setFont(eventManager.customFont);
                g2.setColor(Color.black);
                

                FontMetrics fm = g2.getFontMetrics();
                int textWidth = fm.stringWidth(text);
                int textHeight = fm.getHeight();
                int textX = 460;
                int textY = (getHeight() - textHeight) / 2 + fm.getAscent() + 4;
                g2.drawString(text, textX, textY);

                int[] offsets = {-1, 1};
                for (int xo : offsets) {
                    for (int yo : offsets) {
                        g2.drawString(text, textX + xo, textY + yo);
                    }
                }
                g2.setColor(Color.GREEN);
                g2.drawString(text, textX, textY);

                repaint();
            }
        };
        info.setBackground(Color.PINK);
        info.setPreferredSize(new Dimension(576, 40));
        add(info, BorderLayout.SOUTH);
        //initInfo();



        


        // for (int i = 1; i <= 12; i++) {
        //     JButton button = new JButton("Button" + i);
        //     BufferedImage img;
        //     img = GamePanel.imagesMapTest.get("banner");
        //     button.setIcon(new ImageIcon(img));
        //     add(button);
        // }
        
    }

    public void innitIcons(){

        icons.setLayout(new GridLayout(1,8));
        EntityInfo entityInfo;
        
        for (int i = 0; i < 8; i++) {
            entityInfo = eventManager.entityInfoArray[i];

            JButton button = new JButton("Button" + i);
            button.setContentAreaFilled(false); // Make button transparent
            button.setFocusPainted(false); // Remove focus border
            button.setBorderPainted(false); // Remove border
            button.setText(null);

            ImageIcon imgIcon = new ImageIcon(eventManager.bannerMap.get(String.valueOf(i+1)));
            imgIcon = resizeIcon(imgIcon, 72, 72);
            button.setIcon(imgIcon);

            if (i < 4) {
                entityInfo = eventManager.entityInfoArray[i];
                final String type = entityInfo.getType();
    
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        eventManager.setSelectedEntityInfo(type);
                    }
                });
            }

            icons.add(button);
        }
    }

    public void innitPrice(){
        price.setLayout(new GridLayout(1,8));
        EntityInfo entityInfo;
        
        for (int j = 0; j < 2; j++){
            for (int i = 0; i < 4; i++) {
                entityInfo = eventManager.entityInfoArray[i];
                ImageIcon imgIcon = new ImageIcon(eventManager.bannerMap.get("price"));
                imgIcon = resizeIcon(imgIcon, 72, 32);
                
                JButton button = new JButton(imgIcon);
                
                if (j == 0) {
                    Font tempFont = eventManager.customFont.deriveFont(Font.PLAIN, 16);
                
                    button.setFont(tempFont);
                    button.setText("$" + entityInfo.getPrice());
                    button.setForeground(Color.black);
                    
                    button.setHorizontalTextPosition(SwingConstants.CENTER); // Set text position horizontally
                    button.setVerticalTextPosition(SwingConstants.CENTER); // Set text position vertically

                    final String type = entityInfo.getType();
                    final int entity_price = entityInfo.getPrice();
        
                    button.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if(eventManager.money >= entity_price){
                                //eventManager.setSelectedEntityInfo(type);
                                eventManager.waitPlacement(type);
                            }
                        }
                    });
                }
    
                price.add(button);

                
                
            }
        }

    }


    // public void innitPrice(){
    //     price.setLayout(new GridLayout(1,8));
        
    //     for (int i = 1; i <= 8; i++) {
    //         ImageIcon imgIcon = new ImageIcon(bannerMap.get("price"));
    //         imgIcon = resizeIcon(imgIcon, 72, 32);
            
    //         MyButton button = new MyButton(imgIcon, "text123");
    //         price.add(button);
    //     }
    // }

    public void initInfo(){
        info.setLayout(new GridLayout(1,8));
        
        
        JButton button = new JButton("Button");
        button.setContentAreaFilled(false); // Make button transparent
        button.setFocusPainted(false); // Remove focus border
        button.setBorderPainted(false); // Remove border
        button.setText(null);

        ImageIcon imgIcon = new ImageIcon(eventManager.bannerMap.get("bannerBottom"));
        imgIcon = resizeIcon(imgIcon, 576, 40);
        button.setIcon(imgIcon);
        info.add(button);
        
    }


    private static ImageIcon resizeIcon(ImageIcon icon, int resizedWidth, int resizedHeight) {
        Image img = icon.getImage();  
        Image resizedImage = img.getScaledInstance(resizedWidth, resizedHeight,  java.awt.Image.SCALE_SMOOTH);  
        return new ImageIcon(resizedImage);
    }


    
}

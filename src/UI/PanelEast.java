package UI;

import static events.Constants.ButtonConstants.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import events.RoundManager;
import events.eventManager;
import main.GamePanel;

public class PanelEast extends JPanel {

    public static JButton resumeButton;
    public static JButton pauseButton;

    public PanelEast(){
        setLayout(new BorderLayout());

        setPreferredSize(new Dimension(187, 144));
        setBackground(Color.YELLOW);
        setDoubleBuffered(true);
        setFocusable(true);

        JPanel infoPanel = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;

                g2.drawImage(eventManager.bannerMap.get("menuBar"), 0, -60, null);
                g2.drawImage(eventManager.bannerMap.get("levelInfo"), -12, -25, 210, 120, null);

                g2.drawImage(eventManager.bannerMap.get("count"), 20, 15, 27, 27, null);
                g2.drawImage(eventManager.bannerMap.get("count"), 140, 27, 27, 27, null);

                // Draw text
                
                g2.setColor(Color.GRAY);
                Font tempFont = eventManager.customFont.deriveFont(Font.PLAIN, 20);
                g2.setFont(tempFont);

                String text = new String("ROUND");

                int textX = 55;
                int textY = 32;
                g2.drawString(text, textX, textY);

                int[] offsets = {-1, 1};
                for (int xo : offsets) {
                    for (int yo : offsets) {
                        g2.drawString(text, textX + xo, textY + yo);
                    }
                }
                g2.setColor(Color.WHITE);
                g2.drawString(text, textX, textY);


                
                text = new String("ENEMIES");
                g2.setColor(Color.RED);
                
                textX = 69;
                textY = 49;
                g2.drawString(text, textX, textY);

                for (int xo : offsets) {
                    for (int yo : offsets) {
                        g2.drawString(text, textX + xo, textY + yo);
                    }
                }
                g2.setColor(Color.WHITE);
                g2.drawString(text, textX, textY);



                text = new String(String.valueOf(GamePanel.round));
                g2.setColor(Color.BLACK);
                
                textX = 30;
                textY = 32;
                g2.drawString(text, textX, textY);

                for (int xo : offsets) {
                    for (int yo : offsets) {
                        g2.drawString(text, textX + xo, textY + yo);
                    }
                }
                g2.setColor(Color.WHITE);
                g2.drawString(text, textX, textY);



                text = new String(String.valueOf(GamePanel.enemies - GamePanel.enemiesKilled));
                g2.setColor(Color.BLACK);
                
                textX = 148;
                textY = 46;
                g2.drawString(text, textX, textY);

                for (int xo : offsets) {
                    for (int yo : offsets) {
                        g2.drawString(text, textX + xo, textY + yo);
                    }
                }
                g2.setColor(Color.WHITE);
                g2.drawString(text, textX, textY);

                
                

                repaint();
                g2.dispose();
            }
        };
        infoPanel.setBackground(Color.GREEN);
        infoPanel.setPreferredSize(new Dimension(187, 84));
        add(infoPanel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.RED);
        buttonPanel.setPreferredSize(new Dimension(187, 60));
        add(buttonPanel, BorderLayout.SOUTH);
        buttonPanel.setLayout(null);

        Font tempFont = eventManager.customFont.deriveFont(Font.PLAIN, 18);

        resumeButton = new JButton();

        resumeButton.setBounds(0, 0, 187, 30);
        resumeButton.setFont(tempFont);
        resumeButton.setText(RESUME_BUTTON_1);
        resumeButton.setForeground(Color.black);
        resumeButton.setBackground(new Color(236,221,192));
        resumeButton.setFocusPainted(false);

        resumeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (resumeButton.getText().equals(RESUME_BUTTON_2)) {
                    resumeButtonUpdate();
                    RoundManager.newRound = true;
                }
                
            }
        });
        

        buttonPanel.add(resumeButton);

        pauseButton = new JButton();
        pauseButton.setBounds(0, 30, 93, 30);

        pauseButton.setFont(tempFont);
        pauseButton.setText(PAUSE_BUTTON_1);
        pauseButton.setForeground(Color.black);
        pauseButton.setBackground(new Color(236,221,192));
        pauseButton.setFocusPainted(false);

        pauseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(GamePanel.gamePaused == true){
                    pauseButton.setText(PAUSE_BUTTON_2);
                    GamePanel.gamePaused = false;
                } else {
                    GamePanel.gamePaused = true;
                    pauseButton.setText(PAUSE_BUTTON_1);
                }

            }
        });

        buttonPanel.add(pauseButton);

        JButton exitButton = new JButton();
        exitButton.setBounds(93, 30, 94, 30);

        exitButton.setFont(tempFont);
        exitButton.setText("EXIT");
        exitButton.setForeground(Color.black);
        exitButton.setBackground(new Color(236,221,192));
        exitButton.setFocusPainted(false);

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int choice = JOptionPane.showConfirmDialog(null, "EXIT GAME?", "", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });

        buttonPanel.add(exitButton);

    }

    public static void resumeButtonUpdate(){
        if (resumeButton.getText().equals(RESUME_BUTTON_1)) {
            resumeButton.setText(RESUME_BUTTON_2);
        } else {
            resumeButton.setText(RESUME_BUTTON_1);
        }
    }
}

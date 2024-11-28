package main;


import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;


public class Main {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            JFrame window = new JFrame();
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            window.setResizable(false);
            window.setTitle("TowerDefense");
            

            GamePanel gamePanel = new GamePanel();
            SecondPanel secondPanel = new SecondPanel(gamePanel);

            window.setLayout(new BoxLayout(window.getContentPane(), BoxLayout.Y_AXIS));
            

            window.add(gamePanel);
            window.add(secondPanel);
            window.pack();

            window.setLocationRelativeTo(null);
            window.setVisible(true);
            
            gamePanel.startGameThread();
        });

    }
}

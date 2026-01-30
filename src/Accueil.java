import javax.swing.*;
import java.awt.*;

public class Accueil extends JFrame {

    public Accueil() {
        super("Tic Tac Toe - Bienvenue");
        setSize(400, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel contain = (JPanel) getContentPane();
        contain.setBackground(new Color(18,18,18));

        JLabel title = new JLabel("TIC TAC TOE");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Segoe UI", Font.BOLD, 32));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setBorder(BorderFactory.createEmptyBorder(50,0,50,0));
        contain.add(title, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(18,18,18));
        buttonPanel.setLayout(new GridLayout(2,1,0,30));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(50,50,50,50));

        JButton vsComputer = new JButton("Contre l'ordinateur");
        JButton vsPlayer = new JButton("1 vs 1");

        JButton[] buttons = {vsComputer, vsPlayer};
        for(JButton b : buttons){
            b.setFont(new Font("Segoe UI", Font.BOLD, 20));
            b.setBackground(new Color(66,135,245));
            b.setForeground(Color.WHITE);
            b.setFocusPainted(false);
            b.setBorder(BorderFactory.createEmptyBorder(15,0,15,0));
            // Hover
            b.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent e) {
                    b.setBackground(new Color(90,160,255));
                }
                public void mouseExited(java.awt.event.MouseEvent e) {
                    b.setBackground(new Color(66,135,245));
                }
            });
            buttonPanel.add(b);
        }

        contain.add(buttonPanel, BorderLayout.CENTER);

        vsComputer.addActionListener(e -> {
            GraphTictac game = new GraphTictac();
            game.setVisible(true);
            this.dispose();
        });

        vsPlayer.addActionListener(e -> {
            Graph1v1 game = new Graph1v1();
            game.setVisible(true);
            this.dispose();
        });
    }


}


package View;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class Graph1v1 extends JFrame {
    static String pion = "";
    static boolean gameOver = false;
    static JButton[][] boxes = new JButton[3][3];
    static JButton replay = new JButton();
    static JButton retour = new JButton();
    static final String EMPTY = " ";
    static final String PLAYER_X = "X";
    static final String PLAYER_O = "O";
    static String[][] board = {
            {EMPTY, EMPTY, EMPTY},
            {EMPTY, EMPTY, EMPTY},
            {EMPTY, EMPTY, EMPTY}
    };

    public Graph1v1(){
        super("TIC TAC TOE");
        this.setSize(420,560);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        JPanel contain = (JPanel) this.getContentPane();
        contain.setBackground(new Color(18,18,18));

        Border padding = BorderFactory.createEmptyBorder(10,10,10,10);

        JLabel label = new JLabel("TIC TAC TOE");
        label.setForeground(Color.white);
        label.setFont(new Font("Segoe UI", Font.BOLD, 26));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setBorder(BorderFactory.createEmptyBorder(15,0,10,0));
        contain.add(label, BorderLayout.NORTH);

        JPanel boxGrid = new JPanel();
        boxGrid.setBackground(new Color(35,35,35));
        boxGrid.setPreferredSize(new Dimension(320,320));
        boxGrid.setBorder(padding);
        boxGrid.setLayout(new GridLayout(3,3,8,8));

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                JButton box = new JButton();
                boxes[i][j] = box;
                box.setBackground(new Color(28,28,28));
                box.setForeground(Color.WHITE);
                box.setPreferredSize(new Dimension(100,100));
                box.setFont(new Font("Segoe UI",Font.BOLD,48));
                box.setFocusPainted(false);
                box.setBorder(BorderFactory.createLineBorder(new Color(70,70,70),2));

                int finalI = i;
                int finalJ = j;

                box.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseEntered(java.awt.event.MouseEvent e) {
                        if(board[finalI][finalJ].equals(EMPTY) && !gameOver)
                            box.setBackground(new Color(50,50,50));
                    }
                    public void mouseExited(java.awt.event.MouseEvent e) {
                        if(board[finalI][finalJ].equals(EMPTY))
                            box.setBackground(new Color(28,28,28));
                    }
                });

                box.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (gameOver) return;

                        if(board[finalI][finalJ].equals(EMPTY)){
                            pion = Objects.equals(pion, PLAYER_X)?PLAYER_O:PLAYER_X;
                            board[finalI][finalJ] = pion;
                            box.setText(pion);
                            if(pion == PLAYER_X) box.setForeground(new Color(66,135,245));
                            else box.setForeground(new Color(240,80,80));
                        }

                        if(isWin(pion)){
                            label.setText("Joueur " + pion + " a gagné");
                        } else if (isBoardFull()) {
                            label.setText("Match null");
                        }

                        if(isWin(pion) || isBoardFull()) {
                            gameOver = true;
                            replay.setText("Replay");
                            replay.setVisible(true);
                            retour.setText("Retour");
                            retour.setVisible(true);
                            JPanel bottomPanel = new JPanel();
                            bottomPanel.setBackground(new Color(18,18,18));
                            bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
                            bottomPanel.add(replay);
                            bottomPanel.add(retour);

                            contain.add(bottomPanel, BorderLayout.SOUTH);
                            contain.revalidate();
                            contain.repaint();
                            replay.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    resetGame();
                                    label.setText("TIC TAC TOE");
                                }
                            });
                            retour.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    resetGame();
                                    label.setText("TIC TAC TOE");
                                    Accueil home = new Accueil();
                                    home.setVisible(true);
                                    Graph1v1.this.dispose();
                                }
                            });
                        }
                    }
                });
                boxGrid.add(box);
            }
        }
        contain.add(boxGrid, BorderLayout.CENTER);

        replay.setFont(new Font("Segoe UI", Font.BOLD, 18));
        replay.setBackground(new Color(66,135,245));
        replay.setForeground(Color.WHITE);
        replay.setFocusPainted(false);
        replay.setBorder(BorderFactory.createEmptyBorder(8,25,8,25));
        retour.setFont(new Font("Segoe UI", Font.BOLD, 18));
        retour.setBackground(new Color(66,135,245));
        retour.setForeground(Color.WHITE);
        retour.setFocusPainted(false);
        retour.setBorder(BorderFactory.createEmptyBorder(8,25,8,25));

        replay.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent e) {
                replay.setBackground(new Color(90,160,255));
            }
            public void mouseExited(java.awt.event.MouseEvent e) {
                replay.setBackground(new Color(66,135,245));
            }
        });
    }

    static void resetGame() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = EMPTY;
                boxes[i][j].setText("");
                boxes[i][j].setBackground(new Color(28,28,28));
            }
        }
        replay.setVisible(false);
        retour.setVisible(false);
        gameOver = false;
    }

    private static Boolean isWin(String player){
        for (int i = 0; i < 3; i++) {
            if ((board[i][0] == player && board[i][1] == player && board[i][2] == player) ||
                    (board[0][i] == player && board[1][i] == player && board[2][i] == player)) {
                return true;
            }
        }
        return (board[0][0] == player && board[1][1] == player && board[2][2] == player) ||
                (board[0][2] == player && board[1][1] == player && board[2][0] == player);
    }

    private static boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (Objects.equals(board[i][j], EMPTY)) {
                    return false;
                }
            }
        }
        return true;
    }


}

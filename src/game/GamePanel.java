package game;

import controller.GamePanelController;
import controller.observer.Observer;
import ui.widgets.DefaultButton;
import util.Utils;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class GamePanel extends JPanel implements Observer {

    private JLabel score1;
    private JLabel score2;
    private JLabel tscore1;
    private JLabel tscore2;
    private JLabel winner;
    private JPanel reversiBoard;
    private GamePanelController gamePanelController;
    private JLabel arrowLeft, arrowRight;

    private    JLabel labelPlayer1, labelPlayer2;

    public GamePanel() {

        this.setBackground(Color.WHITE);
        this.setLayout(new BorderLayout());

        JPanel leftPanel = new JPanel();
        leftPanel.setPreferredSize(new Dimension(150, 10));
        leftPanel.setMinimumSize(new Dimension(50, 10));
        add(leftPanel, BorderLayout.WEST);
        GridBagLayout gbl_leftPanel = new GridBagLayout();
        gbl_leftPanel.columnWidths = new int[]{0, 0, 0};
        gbl_leftPanel.rowHeights = new int[]{0, 0, 0, 0, 0};
        gbl_leftPanel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
        gbl_leftPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
        leftPanel.setLayout(gbl_leftPanel);

        score1 = new JLabel("Player 1");
        GridBagConstraints gbc_score1 = new GridBagConstraints();
        gbc_score1.gridwidth = 3;
        gbc_score1.insets = new Insets(0, 0, 5, 0);
        gbc_score1.gridx = 0;
        gbc_score1.gridy = 2;
        leftPanel.add(score1, gbc_score1);

        JPanel panel = new JPanel() {
            @Override
            public void paint(Graphics g) {
                super.paint(g);
                g.setColor(Color.BLACK);
                g.fillOval(35, 50, 80, 80);
                g.drawOval(35, 50, 80, 80);
            }
        };
        panel.setPreferredSize(new Dimension(100, 100));
        panel.setSize(new Dimension(100, 100));
        GridBagConstraints gbc_panel = new GridBagConstraints();
        gbc_panel.fill = GridBagConstraints.BOTH;
        gbc_panel.gridx = 1;
        gbc_panel.gridy = 3;
        leftPanel.add(panel, gbc_panel);

        arrowLeft = new JLabel("Your Turn");
        arrowLeft.setForeground(Color.BLACK);
        GridBagConstraints gbcArrowLeft = new GridBagConstraints();
        gbcArrowLeft.fill = GridBagConstraints.BOTH;
        gbcArrowLeft.gridx = 1;
        gbcArrowLeft.gridy = 4;
        leftPanel.add(arrowLeft, gbcArrowLeft);

        JPanel rightPanel = new JPanel();
        rightPanel.setPreferredSize(new Dimension(150, 10));
        add(rightPanel, BorderLayout.EAST);
        GridBagLayout gbl_rightPanel = new GridBagLayout();
        gbl_rightPanel.columnWidths = new int[]{0, 0, 0};
        gbl_rightPanel.rowHeights = new int[]{0, 0, 0, 0, 0};
        gbl_rightPanel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
        gbl_rightPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
        rightPanel.setLayout(gbl_rightPanel);

        score2 = new JLabel("Player 2");
        GridBagConstraints gbc_score2 = new GridBagConstraints();
        gbc_score2.gridwidth = 3;
        gbc_score2.insets = new Insets(0, 0, 5, 0);
        gbc_score2.gridx = 0;
        gbc_score2.gridy = 2;
        rightPanel.add(score2, gbc_score2);

        JPanel panel_1 = new JPanel() {
            @Override
            public void paint(Graphics g) {
                super.paint(g);
                g.setColor(Color.WHITE);
                g.fillOval(35, 50, 80, 80);
                g.drawOval(35, 50, 80, 80);
            }
        };
        panel_1.setPreferredSize(new Dimension(100, 100));
        GridBagConstraints gbc_panel_1 = new GridBagConstraints();
        gbc_panel_1.fill = GridBagConstraints.BOTH;
        gbc_panel_1.gridx = 1;
        gbc_panel_1.gridy = 3;
        rightPanel.add(panel_1, gbc_panel_1);

        arrowRight = new JLabel("Your Turn");
        arrowRight.setForeground(Color.BLACK);
        GridBagConstraints gbcArrowRight = new GridBagConstraints();
        gbcArrowRight.fill = GridBagConstraints.BOTH;
        gbcArrowRight.gridx = 1;
        gbcArrowRight.gridy = 4;
        rightPanel.add(arrowRight, gbcArrowRight);


        JPanel topPanel = new JPanel();
        topPanel.setPreferredSize(new Dimension(10, 60));
        add(topPanel, BorderLayout.NORTH);
        GridBagLayout gbl_topPanel = new GridBagLayout();
        gbl_topPanel.columnWidths = new int[]{30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30};
        gbl_topPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
        gbl_topPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        gbl_topPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        topPanel.setLayout(gbl_topPanel);

        labelPlayer2 = new JLabel("Player 2");
        GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
        gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 0);
        gbc_lblNewLabel_3.gridx = 9;
        gbc_lblNewLabel_3.gridy = 2;
        topPanel.add(labelPlayer2, gbc_lblNewLabel_3);

          labelPlayer1 = new JLabel("Palyer 1");
        GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
        gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_2.gridx = 7;
        gbc_lblNewLabel_2.gridy = 2;
        topPanel.add(labelPlayer1, gbc_lblNewLabel_2);

        tscore1 = new JLabel("0");
        GridBagConstraints gbc_tscore1 = new GridBagConstraints();
        gbc_tscore1.insets = new Insets(0, 0, 5, 5);
        gbc_tscore1.gridx = 7;
        gbc_tscore1.gridy = 3;
        topPanel.add(tscore1, gbc_tscore1);

        tscore2 = new JLabel("0");
        GridBagConstraints gbc_tscore2 = new GridBagConstraints();
        gbc_tscore2.insets = new Insets(0, 0, 5, 0);
        gbc_tscore2.gridx = 9;
        gbc_tscore2.gridy = 3;
        topPanel.add(tscore2, gbc_tscore2);

        winner=new JLabel("");
        GridBagConstraints gbc_winner=new GridBagConstraints();
        gbc_tscore2.insets = new Insets(0, 0, 5, 0);
        gbc_tscore2.gridx = 8;
        gbc_tscore2.gridy = 1;
        topPanel.add(winner, gbc_winner);


        JPanel bottomPanel = new JPanel();
        bottomPanel.setPreferredSize(new Dimension(10, 50));
        add(bottomPanel, BorderLayout.SOUTH);
        GridBagLayout gbl_bottomPanel = new GridBagLayout();
        gbl_bottomPanel.columnWidths = new int[]{30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30};
        gbl_bottomPanel.rowHeights = new int[]{30, 30, 30};
        gbl_bottomPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        gbl_bottomPanel.rowWeights = new double[]{0.0, 0.0};
        bottomPanel.setLayout(gbl_bottomPanel);


        JButton btnNewButton = new DefaultButton("Start");
        GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
        gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
        gbc_btnNewButton.gridx = 2;
        gbc_btnNewButton.gridy = 1;
        bottomPanel.add(btnNewButton, gbc_btnNewButton);

        JButton btnNewButton_1 = new DefaultButton("Pause");
        GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
        gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 0);
        gbc_btnNewButton_1.gridx = 7;
        gbc_btnNewButton_1.gridy = 1;
        bottomPanel.add(btnNewButton_1, gbc_btnNewButton_1);

        JButton btnNewButton_2 = new DefaultButton("Quit");
        GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
        gbc_btnNewButton_2.insets = new Insets(0, 0, 5, 5);
        gbc_btnNewButton_2.gridx = 10;
        gbc_btnNewButton_2.gridy = 1;
        bottomPanel.add(btnNewButton_2, gbc_btnNewButton_2);

        reversiBoard = new JPanel();
        reversiBoard.setLayout(new GridLayout(8, 8));
        reversiBoard.setBackground(new Color(41, 100, 59));
        add(reversiBoard, BorderLayout.CENTER);

        gamePanelController = new GamePanelController(this);
    }

    public JPanel getReversiBoard() {
        return reversiBoard;
    }

    public JLabel getScore1() {
        return this.score1;
    }

    public JLabel getScore2() {
        return this.score2;
    }

    public JLabel getTscore1() {
        return this.tscore1;
    }

    public JLabel getTscore2() {
        return this.tscore2;
    }

    public JLabel getWinner() {
        return winner;
    }

    public void setWinner(JLabel winner) {
        this.winner = winner;
    }

    public JLabel getLabelPlayer1() {
        return this.labelPlayer1;
    }

    public JLabel getLabelPlayer2() {
        return this.labelPlayer2;
    }

    public JLabel getArrowLeft(){
        return this.arrowLeft;
    }

    public JLabel getArrowRight(){
        return this.arrowRight;
    }

    @Override
    public void update(String p1score, String p2score) {
        this.getScore1().setText(p1score);
        this.getScore2().setText(p2score);

    }

    //    @Override
//    public void update(String s1,String s2) {
//        name.setItems(FXCollections.observableArrayList(names));
//           this.getScore1().setText(s1);
//        this.getScore2().setText(s2);
//    }
    public GamePanelController getController() {
        return gamePanelController;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Image img = Toolkit.getDefaultToolkit().getImage(Utils.getResoursePath("Board.jpg"));
        g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
    }
}

package game;

import controller.GamePanelController;
import controller.observer.Observer;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class GamePanel extends JPanel implements Observer {

    private JLabel score1;
    private JLabel score2;
    private JLabel tscore1;
    private JLabel tscore2;
    private JPanel reversiBoard;
    private GamePanelController gamePanelController;

    public GamePanel(){

        this.setBackground(Color.WHITE);
        this.setLayout(new BorderLayout());

        reversiBoard = new JPanel();
        reversiBoard.setLayout(new GridLayout(8,8));
        reversiBoard.setPreferredSize(new Dimension(500,500));
        reversiBoard.setBackground(new Color(41,100, 59));

        JPanel sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar,BoxLayout.Y_AXIS));
        sidebar.setPreferredSize(new Dimension(200,0));

        score1 = new JLabel("Score 1");
        score2 = new JLabel("Score 2");

        tscore1 = new JLabel("Total Score 1");
        tscore2 = new JLabel("Total Score 2");

        sidebar.add(score1);
        sidebar.add(score2);

        sidebar.add(new JLabel("-----------"));

        sidebar.add(tscore1);
        sidebar.add(tscore2);

        this.add(sidebar,BorderLayout.WEST);
        this.add(reversiBoard);

        new GamePanelController(this,this);
    }

    public JPanel getReversiBoard(){
        return reversiBoard;
    }

    public JLabel getScore1(){
        return this.score1;
    }

    public JLabel getScore2(){
        return this.score2;
    }

    public JLabel getTscore1(){
        return this.tscore1;
    }

    public JLabel getTscore2(){
        return  this.tscore2;
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
    public GamePanelController getController(){
        return gamePanelController;
    }


}

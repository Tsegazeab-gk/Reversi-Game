package ui;

import models.Score;
import services.dao.IScoreService;
import services.dao.ScoreService;
import ui.viewmodel.ScoreTableModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;


public class UserScoreScreen extends JPanel {
	private JTable table;

	// Column Names
	String[] columnNames = { "Name", "Moves", "Score" };
	IScoreService service = new ScoreService();

	List<Score> list;

	JFrame f;

	private final JButton buttonScore;

	private final JButton buttonMove;

	public UserScoreScreen() {

		list = service.getByBestScore(); // new ArrayList<Score>();

		// Frame initiallization
		f = new JFrame();

		ScoreTableModel tbl = new ScoreTableModel(list); // service.getByBestScore()
		// tbl.setColumnNames(columnNames);

		// Initializing the JTable
		table = new JTable(tbl);

		buttonScore = new JButton("Get Best Scorer");
		buttonScore.setBounds(10, 0, 210, 70);
		buttonScore.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				list = service.getByBestScore();
				tbl.setTableData(list);
			}
		});
		buttonMove = new JButton("Get Best Move");
		buttonMove.setBounds(250, 0, 210, 70);
		buttonMove.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				list = service.getByShortMoves(); // new ArrayList<Score>();
				tbl.setTableData(list);
			}
		});

		add(buttonScore);
		add(buttonMove);


		table.setBounds(30, 100, 200, 300);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		add(scrollPane);

		f.getContentPane().add(this);
		// Frame Size
		f.setSize(500, 200);
		// Frame Visible = true
		f.setVisible(true);

	}

	public static void main(String[] args) {

		// IScoreService service=new ScoreService();
		// service.getByBestScore().stream().forEach(s->System.out.println(s.getPlayerName()+
		// " "+s.getScore() +" "+s.getNumberOfMove()));
		UserScoreScreen user = new UserScoreScreen();
	}

}

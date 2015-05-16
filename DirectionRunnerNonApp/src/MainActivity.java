
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JApplet;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * This class is the main class that handles everything
 * @author Jacob Valdiviez
 *
 */
public class MainActivity extends JApplet{
	JPanel panel = new JPanel();
	BoardView boardview;
	DirectionBoardView directionBoardView;
	public void update(){
		panel.remove(boardview);
		panel.remove(directionBoardView);
		remove(panel);
		add(panel);
		//panel.setLayout(new BoxLayout());
		panel.add(boardview);
		panel.add(directionBoardView);
		setVisible(true);
		
	}
	public void init(){
		
		JLabel label = new JLabel("Hello");
		DirectionRunner directionRunner = new DirectionRunner("Jacob");
		//directionRunner.playGame();
		boardview = new BoardView(directionRunner.getGame());
		boardview.initialize();
		directionBoardView = new DirectionBoardView(directionRunner.getGame(),boardview,this);
		directionBoardView.initialize();
		add(panel);
		//panel.setLayout(new BoxLayout());
		panel.add(boardview);
		panel.add(directionBoardView);
	}
}
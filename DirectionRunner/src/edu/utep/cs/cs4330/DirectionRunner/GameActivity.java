package edu.utep.cs.cs4330.DirectionRunner;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
/**
 * This activity manages the game
 * @author Jacob Valdiviez
 *
 */
public class GameActivity extends ActionBarActivity{
	/**
	 * This field contains the board display
	 * @see BoardView
	 */
	BoardView board;
	/**
	 * This field contains the data of the game
	 * @see DirectionRunner
	 */
	DirectionRunner directionRunner;
	/**
	 * This field contains the text view used for the name of the user
	 */
	TextView textView;
	/**
	 * This field contains the text view used to display the score
	 */
	TextView scoreText;
	/**
	 * This field contains the text view used to display the clock
	 */
	TextView timer;
	/**
	 * This field contains the user
	 * @see User
	 */
	User user;
	/**
	 * This field contains the name of the user
	 */
	String username;
	/**
	 * This method initializes the game activity
	 * and also starts the timer
	 */
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        username = intent.getStringExtra("name");
        setContentView(R.layout.game_activity);
        board = (BoardView)findViewById(R.id.boardView);
        this.directionRunner = new DirectionRunner(username);
        this.board.setGame(directionRunner.getGame());
        textView = (TextView)findViewById(R.id.nameText);
        scoreText = (TextView)findViewById(R.id.scoreText);
        timer = (TextView)findViewById(R.id.clock);
        user = new User(username);
        user.setLevel(intent.getIntExtra("level", 1));
        user.setScore(intent.getIntExtra("score", 0));
        updateButtons();
        new CountDownTimer(20000, 1000) {

            public void onTick(long millisUntilFinished) {
                timer.setText("Time: " + millisUntilFinished / 1000);
            }

            public void onFinish() {
            	AlertDialog.Builder alert = new AlertDialog.Builder(GameActivity.this);
                alert.setTitle("You lose :(");
                alert.setMessage("Your score is " + user.getScore());
                alert.setPositiveButton("OK",
                		new DialogInterface.OnClickListener() {
                      	public void onClick(DialogInterface dialog, int id) {
                          	// Here actually start the GoLauncher
                      		onSaveData();
                      		Intent intent = new Intent(GameActivity.this,MainActivity.class);
                      		intent.putExtra("save", true);
                      		startActivity(intent);
                      	}
                });
                alert.show();
            }
        }.start();
    }
	/**
	 * This method updates the directional buttons with the correct pictures
	 * accordingly to what direction they are supposed to be
	 */
	public void updateButtons(){
		Direction[] directions = directionRunner.getGame().getDirectionBoard().getDirections();
		Button button1 = (Button)findViewById(R.id.button1);
		Button button2 = (Button)findViewById(R.id.button2);
		Button button3 = (Button)findViewById(R.id.button3);
		Button button4 = (Button)findViewById(R.id.button4);
		Button button5 = (Button)findViewById(R.id.button5);
		Button[] buttons = {button1,button2,button3,button4,button5};
		for(int i = 0; i < directions.length; i ++){
			if(directions[i].isDown())
				buttons[i].setBackgroundResource(R.drawable.arrow_down);
			else if(directions[i].isLeft())
				buttons[i].setBackgroundResource(R.drawable.arrow_left);
			else if(directions[i].isRight())
				buttons[i].setBackgroundResource(R.drawable.arrow_right);
			else if(directions[i].isUp())
				buttons[i].setBackgroundResource(R.drawable.arrow_up);
		}
	}
	/**
	 * This creates the options menu
	 */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    /**
     * This handles the selected items
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    /**
     * This method displays the name of the user along with his current score
     */
    public void onStart(){
    	super.onStart();
        //this.directionRunner = new DirectionRunner(username);
        textView.setText("Name: " + directionRunner.getGame().getBoard().getPlayer().getName() + " ");
        scoreText.setText("Score: " + user.getScore() + " ");
    }
    /**
     * This method handles the click of button1
     * @param view
     */
    public void button1(View view){
    	makeChanges(0);
    }
    /**
     * This method handles the click of button2
     * @param view
     */
    public void button2(View view){
    	makeChanges(1);
    }
    /**
     * This method handles the click of button3
     * @param view
     */
    public void button3(View view){
    	makeChanges(2);
    }
    /**
     * This method handles the click of button4
     * @param view
     */
    public void button4(View view){
    	makeChanges(3);
    }
    /**
     * This method handles the click of button5
     * @param view
     */
    public void button5(View view){
    	makeChanges(4);
    }
    /**
     * This asks the user if he wants to quit and if clicked ok,
     * the method takes the user back to the MainActivity
     * @see MainActivity
     * @param view
     */
    public void quit(View view){
    	AlertDialog.Builder alert = new AlertDialog.Builder(GameActivity.this);
        alert.setTitle("Are You Sure?");
        alert.setMessage("You are about to quit the game");
        alert.setPositiveButton("OK",
        		new DialogInterface.OnClickListener() {
              	public void onClick(DialogInterface dialog, int id) {
                  	// Here actually start the GoLauncher
              		onSaveData();
              		Intent intent = new Intent(GameActivity.this,MainActivity.class);
              		intent.putExtra("save", true);
              		startActivity(intent);
              	}
        });
        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
              	public void onClick(DialogInterface dialog, int id) {
              	}
        });
        alert.show();
    }
    /**
     * This method handles changes of the player moves and of the button displays
     * @param i
     */
    public void makeChanges(int i){
    	Direction direction = directionRunner.getGame().getDirectionBoard().getDirection(i);
    	if(direction.isDown())
			this.directionRunner.getGame().movePlayer(this.directionRunner.getGame().getPlayerX() - 1, this.directionRunner.getGame().getPlayerY(), i);
		else if(direction.isUp())
			this.directionRunner.getGame().movePlayer(this.directionRunner.getGame().getPlayerX() + 1, this.directionRunner.getGame().getPlayerY(), i);
		else if(direction.isRight())
			this.directionRunner.getGame().movePlayer(this.directionRunner.getGame().getPlayerX(), this.directionRunner.getGame().getPlayerY() + 1, i);
		else if(direction.isLeft())
			this.directionRunner.getGame().movePlayer(this.directionRunner.getGame().getPlayerX(), this.directionRunner.getGame().getPlayerY() - 1, i);
    	this.board.invalidate();
    	updateButtons();
    	if((directionRunner.getGame().getPlayerX() == 6)&&(directionRunner.getGame().getPlayerY() == 4)){
            AlertDialog.Builder alert = new AlertDialog.Builder(GameActivity.this);
            int prevScore = user.getScore();
            user.levelUp();
            alert.setTitle("Now in level " + user.getLevel() + "!");
            alert.setMessage("You earned " + (user.getScore() - prevScore) + "!");
            alert.setPositiveButton("OK",
            		new DialogInterface.OnClickListener() {
                  	public void onClick(DialogInterface dialog, int id) {
                      	// Here actually start the GoLauncher
                  		Intent intent = getIntent();
                  		intent.putExtra("name", username);
                  		intent.putExtra("level", user.getLevel());
                  		intent.putExtra("score", user.getScore());
                  		intent.putExtra("save", false);
                  		finish();
                  		startActivity(intent);
                  		
                  	}
            });
            alert.show();
    	}
    }
    /**
     * This resets the player back to the orginal position at the start of the game
     * @param view
     */
    public void reset(View view){
    	this.directionRunner.getGame().getBoard().getPlayer().setX(0);
    	this.directionRunner.getGame().getBoard().getPlayer().setY(0);
    	this.directionRunner.getGame().getDirectionBoard().setDirection(directionRunner.getGame().getBoard().getCell(0,0).getDirection(),0);
    	this.board.invalidate();
    	updateButtons();
    }
    /**
     * This method handles the data when its time to save
     */
    public void onSaveData(){
    	List<String> highscores = readHighscores();
    	if(highscores.size() < 1)
    		highscores.add(user.getUsername()+ ": " + user.getScore());
    	else if(highscores.size() < 5){
    		boolean add = true;
    		for(int i = 0; i < highscores.size(); i ++){
    			String[] parse = highscores.get(i).split(": ");    			
    			if(user.getScore() > Integer.parseInt(parse[1])){
    				int j = highscores.size();
    				highscores.add(highscores.get(j-1));
    				j --;
    				while(j > i){
    					highscores.set(j, highscores.get(j-1));
    					j--;
    				}
    				highscores.set(j,user.getUsername()+ ": " + user.getScore());
    				add = false;
    				break;
    			}
    		}
    		if(add)
    			highscores.add(user.getUsername()+ ": " + user.getScore());
    	}
    	else{
    		for(int i = 0; i < highscores.size(); i ++){
    			String[] parse = highscores.get(i).split(": "); 
    			if(user.getScore() > Integer.parseInt(parse[1])){
    				int j = highscores.size() - 1;
    				while(j > i){
    					highscores.set(j,highscores.get(j-1));
    					j--;
    				}
    				highscores.set(j, user.getUsername()+ ": " + user.getScore());
    				break;
    			}
    		}
    	}
    	saveHighscores(highscores);
    }
    /**
     * This method gets the list of highscores and puts it into a list of String
     * @return List<String>
     */
    public List<String> readHighscores(){
    	
    	List<String> highscores = new ArrayList<String>();
    	try{
    		FileInputStream foi = openFileInput("highscores.txt");
    		BufferedReader br = new BufferedReader(new InputStreamReader(foi));
    		String line;
    		while((line = br.readLine()) != null){
    			highscores.add(line);
    		}
    	}catch(Exception e){}
    	return highscores;
    }
    /**
     * This method gets a list of string and writes it into a text file
     * @param highscores
     */
    public void saveHighscores(List<String> highscores){
    	try {
			FileOutputStream fos = openFileOutput("highscores.txt",Context.MODE_PRIVATE);
			for(int i = 0; i < highscores.size(); i ++){
				String line = highscores.get(i) + "\n";
				fos.write(line.getBytes());
	    	}
			fos.close();
		} catch (Exception e) {}
 
    }
}

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * The board where a snake game takes place.
 * 
 * @author YOUR NAME
 *
 */
public class Board extends JPanel implements ActionListener {

    private final int BOARD_WIDTH = 300;	// board width (pixels)
    private final int BOARD_HEIGHT = 300;	// board height
    private final int SCORE_X = 20;			// x coordinate of score
    private final int SCORE_Y = BOARD_HEIGHT - 30;	// y coordinate of score
    private final int DOT_SIZE = 10;		// height & width of a dot (snake joint / apple)					
    private final int ALL_DOTS = 900;		// total number of possible dots on the board
    
    private final int x[] = new int[ALL_DOTS];	// x coordinates of the joints of the snake
    private final int y[] = new int[ALL_DOTS];  // y coordinates of the joints of the snake

    private int dots;	// number of joints in the snake body
    private int applePositionX;	// x coordinate of the current position of the apple
    private int applePositionY; // y coordinate of the current position of the apple

    // direction of the snake
    private boolean leftDirection = false;	
    private boolean rightDirection = true;
    private boolean upDirection = false;
    private boolean downDirection = false;
    
    // whether the game is in play
    private boolean inGame = true;

    private Timer timer; // timer used to animate the game
    
    private Image ball;  // image for the snake joint
    private Image apple; // image for the apple
    private Image head;  // image for the snake head
    private Image wall;	 // image for the obstacles
    
    private int score;	// player's score
    private String name;	// player's name
    private int delay; 	// speed of the game
    private Scoreboard scoreboard; // score board of highest scores

    /**
     * Default constructor.  Sets up the score board.  Attaches a {@link TAdapter}
     * object that listens for key strokes. Sets the background color and preferred
     * size of the board. Loads images required for the game and initializes game state.
     */
    public Board() {
    	// create a Scoreboard object to keep 15 highest scores
    	scoreboard = new Scoreboard(15);
    	
        addKeyListener(new TAdapter());
        setBackground(Color.black);
        setFocusable(true);

        setPreferredSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT));
        loadImages();
        initGame();
    }

    /**
     * Loads images needed for the board.
     */
    private void loadImages() {

    	
        // ImageIcon iid = new ImageIcon(SnakeGame.class.getResource("./dot.png")); // joint of the snake
        ImageIcon iid = new ImageIcon("./dot.png"); // joint of the snake
        ball = iid.getImage();

        // ImageIcon iia = new ImageIcon(SnakeGame.class.getResource("./apple.png")); // apple
        ImageIcon iia = new ImageIcon("./apple.png"); // apple
        apple = iia.getImage();

        // ImageIcon iih = new ImageIcon(SnakeGame.class.getResource("./head.png")); // head of the snake
        ImageIcon iih = new ImageIcon("./head.png"); // head of the snake
        head = iih.getImage();
        
        // ImageIcon iiw = new ImageIcon(SnakeGame.class.getResource("./wall.png")); // obstacle
        ImageIcon iiw = new ImageIcon("./wall.png"); // obstacle
        wall = iiw.getImage();
    }

    /**
     * Initializes game state. Prompts for the player's name. Initializes
     * the number of snake joints and their corresponding positions.
     * Initializes the score and speed of the game. Randomly places an apple
     * on the game board. Starts the game timer.
     */
    private void initGame() {
    	// prompt for player's name
    	name = (String)JOptionPane.showInputDialog(
    	                    this,
    	                    "What is your name?",
    	                    "What is your name?",
    	                    JOptionPane.PLAIN_MESSAGE,
    	                    null,
    	                    null,
    	                    "your name");

        dots = 3;	// the snake has three joints initially

        // computes (x, y) coordinates of each of the joints
        for (int i = 0; i < dots; i++) {
            x[i] = 50 - i * DOT_SIZE;
            y[i] = 50;
        }

        // randomly locates an apple on the board
        locateApple();

        // initializes score to 0
        score = 0;
        
        // initializes the speed of the game
        delay = 200;
        
        // creates a Timer object needed for animation
        // each DELAY ms, the timer will call the actionPerformed method
        timer = new Timer(delay, this);
        timer.start();
    }

    @Override
    /**
     * Paints the board by calling {@link doDrawing}.
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        doDrawing(g);
    }
    
    /**
     * Draws the 2-D graphics for a snake game.
     * 
     * @param g the graphics object on which the drawing is performed
     */
    private void doDrawing(Graphics g) {
    	// if game is in play, displays game interface
        if (inGame) { 
            g.drawImage(apple, applePositionX, applePositionY, this); // displays an apple
            
            // displays the snake
            for (int z = 0; z < dots; z++) {
                if (z == 0) {
                    g.drawImage(head, x[z], y[z], this);
                } else {
                    g.drawImage(ball, x[z], y[z], this);
                }
            }

            // displays score board (current score and player's name)
            String msg = "Score:  " + score;
            Font small = new Font("Helvetica", Font.BOLD, 14);
            
            g.setFont(small);
            g.setColor(Color.white);
            g.drawString(msg, SCORE_X, SCORE_Y);           
                       
            msg = "Player:  " + name;
            g.drawString(msg, SCORE_X, SCORE_Y + 15); 
            
            // synchronizes the painting on systems that buffer graphics events
            // in order to allow smooth animation
            Toolkit.getDefaultToolkit().sync();

        } 
        // game is over
        else {
            gameOver(g);
        }        
    }

    /**
     * Displays the game over screen. Displays game over message, player's last score,
     * and a list of highest scores.
     * 
     * @param g the graphics object on which the drawing is performed
     */
    private void gameOver(Graphics g) {
        // adds score to score board
    	scoreboard.add(new GameEntry(name, score));
    	
    	// displays game over message
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metr = getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        
        String msg = "Game Over";
        g.drawString(msg, (BOARD_WIDTH - metr.stringWidth(msg)) / 2, 20);
        
        msg = "Your score was " + score;
        g.drawString(msg, (BOARD_WIDTH - metr.stringWidth(msg)) / 2, 50);
        
        msg = "Press Enter to Play Again";
        g.setColor(Color.yellow);
        g.drawString(msg, (BOARD_WIDTH - metr.stringWidth(msg)) / 2, 80);
     
        // displays highest scores
        msg = "----------------------------------";
        g.setColor(Color.green);
        g.drawString(msg, (BOARD_WIDTH - metr.stringWidth(msg)) / 2, 120);
        
        msg = "Highest scores:";
        g.setColor(Color.green);
        g.drawString(msg, (BOARD_WIDTH - metr.stringWidth(msg)) / 2, 140);
        g.setColor(Color.gray);
        msg = scoreboard.toString();
        int x = 20;
        int y = 150;
        for (String line : msg.split("\n")) {
        	if (y > BOARD_HEIGHT) {
        		y = 150;
        		x = 150;
        	}
            g.drawString(line, x, y += g.getFontMetrics().getHeight());
        }
        
    }

    /**
     * Checks if the head of the snake collides with an apple.
     * 
     * If the head of the snake collides with the apple,
     * the game level is increased, and
     * an apple is placed in a new random location.
     */
    private void checkApple() {

        if ((x[0] == applePositionX) && (y[0] == applePositionY)) {
        	increaseLevel();
            locateApple();
        }
    }

    /**
     * Moves the snake for one time step. 
     * Changes the head of the snake based on the direction of movement
     * and shift the rest of the joint positions up the array.
     * (that is, the second joint moves where the first was, the 
     * third joint moves where the second was, etc.)
     */
    private void move() {
    	// shifts the joint positions, except the head, up the array
        for (int z = dots; z > 0; z--) {
            x[z] = x[(z - 1)];
            y[z] = y[(z - 1)];
        }

        // updates the head based on the current direction
        if (leftDirection) {
            x[0] -= DOT_SIZE;
        }

        if (rightDirection) {
            x[0] += DOT_SIZE;
        }

        if (upDirection) {
            y[0] -= DOT_SIZE;
        }

        if (downDirection) {
            y[0] += DOT_SIZE;
        }
    }

    /**
     * Checks if the snake has collided with itself or with one of the walls.
     * The game is over if a collision has occurred.
     */
    private void checkCollision() {
    	// checks if the snake has hit itself
        for (int z = dots; z > 0; z--) {
            if ((z > 4) && (x[0] == x[z]) && (y[0] == y[z])) {
                inGame = false;
            }
        }

        // checks if the snake has hit one of the walls
        if (y[0] >= BOARD_HEIGHT) { // top wall
            inGame = false;
        }
        else if (y[0] < 0) { // bottom wall
            inGame = false;
        }
        if (x[0] >= BOARD_WIDTH) { // right wall
            inGame = false;
        }
        else if (x[0] < 0) { // left wall
            inGame = false;
        }
        
        // stop the timer if game is over
        if(!inGame) {
            timer.stop();
        }
    }

    /**
     * Sets the position of the apple to a random location.
     */
    private void locateApple() {
        int r = (int) (Math.random() * BOARD_WIDTH);
        applePositionX = r / DOT_SIZE * DOT_SIZE;

        r = (int) (Math.random() * BOARD_HEIGHT);
        applePositionY = r / DOT_SIZE * DOT_SIZE;
    }

    /**
     * Increases the game level by increases the score, speed, and the number of joints
     * in the snake body.
     */
    private void increaseLevel() {
        dots++;
        score += dots * 5;
        delay -= 30;
        if (delay < 20)
        	delay = 20;
    }
    
    @Override
    /**
     * Updates the game state and graphics. 
     * This method is repeated called by the timer. 
     * Represents one time step of the game. If the 
     * game is on, the game state is updated based on whether 
     * the snake eats an apple or experiences a collision.
     * The score is decreased each time this method is called.
     */
    public void actionPerformed(ActionEvent e) {

        if (inGame) {
            checkApple();
            checkCollision();
            move();
            score--;
        }

        repaint();
    }

    /**
     * An adapter class for receiving keyboard events.
     * The direction keys are detected if the game is in play.
     * If the game is over, the enter key is detected.
     */
    private class TAdapter extends KeyAdapter {

        @Override
        /**
         * Updates the direction of the snake according to keystrokes
         */
        public void keyPressed(KeyEvent e) {

            int key = e.getKeyCode();
            if (inGame) { // game is in play
	            if ((key == KeyEvent.VK_LEFT) && (!rightDirection)) { // left
	                leftDirection = true;
	                upDirection = false;
	                downDirection = false;
	            }
	
	            if ((key == KeyEvent.VK_RIGHT) && (!leftDirection)) { // right
	                rightDirection = true;
	                upDirection = false;
	                downDirection = false;
	            }
	
	            if ((key == KeyEvent.VK_UP) && (!downDirection)) { // up
	                upDirection = true;
	                rightDirection = false;
	                leftDirection = false;
	            }
	
	            if ((key == KeyEvent.VK_DOWN) && (!upDirection)) { // down
	                downDirection = true;
	                rightDirection = false;
	                leftDirection = false;
	            }
            }
            else { // game over
            	if (key == KeyEvent.VK_ENTER) {
            		// restarts game
            		inGame = true;
            		initGame();
            	}
            }
            
        }
    }
}

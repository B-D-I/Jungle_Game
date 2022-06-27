package game;

import database.Database;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class GameWindow extends KeyWindow implements ActionListener {
    /**
     * This class contains the JPanel and game structure.
     */

    // ENEMY VARIABLES
    Enemy eagleOne = new Enemy();
    // enemy array
    private final int[] enemyXAxis = new int[34];
    private final int[] enemyYAxis = new int[34];
    private final int currentVertical = 50;
    // enemy projectile array
    private final int[] enemyWeaponXAxis = eagleOne.getProjectileXAxis();
    private final int[] enemyWeaponYAxis = eagleOne.getProjectileYAxis();
    // enemy movement variable
    private int enemyMovement;
    private final int enemyAmount = eagleOne.getEnemyAmount();
    // array to hold enemy dead / alive
    private final int[] isAlive = new int[enemyAmount];
    // will use the amount of repaints as a counter and when reached use a random enemy to shoot
    private byte enemyProjectileCount = 0;
    private byte currentEnemyProjectile = 0;
    private final short enemyWidth = 45;
    private final short enemyHeight = 45;


    // PLAYER VARIABLES
    Player playerOne;
    Player playerTwo;
    String playerName;
    // set the x-axis of players
    private int playerOneXAxis = 350;
    private int playerTwoXAxis = 250;
    // set the y-axis of players
    private final int playerYAxis = 450;
    // player projectile array
    private final int[] playerWeaponXAxis = new int[1000];
    private final int[] playerWeaponYAxis = new int[1000];
    private byte currentProjectile = 0;
    private final short objectDimensionHeight = 100;
    private final short objectDimensionWidth = 100;

    private final byte playerAmount;
    private JLabel score;
    private static int currentScore = 0;
    private boolean gameOver = false;
    private final Font fontGameOver = new Font("Arial", Font.BOLD,70);
    private final short fontGameOverXAxis = 90;
    private final short fontGameOverYAxis = 500;
    private final Database db;

    public GameWindow(String imageName, byte playerAmount, String playerName) {

        super(imageName);
        this.playerAmount = playerAmount;
        this.playerName = playerName;
        playerOne = new Player(playerName);
        playerTwo = new Player(playerName);
        init();
        db = new Database();
    }

    /**
     * Method to initiate the JPanel and paintComponent method
     */
    private void init() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // window dimensions
        setSize(630, 640);
        addKeyListener(this);
        // PLAYER PROJECTILE VARIABLES
        String color = playerOne.getProjectileColour();
        short width =  playerOne.getProjectileWidth();
        short speed = playerOne.getProjectileSpeed();

        JPanel gamePanel = new JPanel() {
            public void paintComponent(Graphics g) {

                BufferedImage image = null;

                if (gameOver) {
                    updateLeaderboard();
                    gameOverCloseWindow();
                    // reset game score
                    currentScore = 0;
                    soundEffect(arnold, 0);

                } else {
                    try {
                        //image = setBackgroundType();
                        image = ImageIO.read(new File("src\\main\\java\\images\\jungleBackground2.jpg"));
                        // exception if game background not work
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Graphics2D g2d = (Graphics2D) g;
                    // draw background
                    g2d.drawImage(image, 0, 0, 630, 600, this);
                    //draw player
                    g2d.drawImage(playerOne.getPlayerOne(), playerOneXAxis, playerYAxis, objectDimensionWidth, objectDimensionHeight, this);
                    // if multiplayer, draw player 2
                    if (playerAmount == 2) {
                        g2d.drawImage(playerTwo.getPlayerOne(), playerTwoXAxis, playerYAxis, objectDimensionWidth, objectDimensionHeight, this);
                    }
                    // increment player shot projectile
                    for (int j = 0; j < currentProjectile; j++) {
                        // set projectile color
                        g2d.setColor(Color.decode(color));
                        // projectile rectangle shape and dimensions
                        g2d.fill3DRect(playerWeaponXAxis[j], playerWeaponYAxis[j], width, 30, true);
                        playerWeaponYAxis[j] = playerWeaponYAxis[j] - speed;
                    }

                     //FOR LOOP TO CREATE AN ARRAY OF ENEMIES AND CONTROL MOVEMENT
                    for (int i = 0; i < enemyAmount; i++) {
                        // distribute the enemies
                        enemyXAxis[i + 1] = enemyXAxis[i] + 45;
                        enemyYAxis[i] = currentVertical;
                        // if less than or equal to the left of panel, go right
                        if (enemyXAxis[0] <= 10) {
                            enemyMovement = 10;
                        }
                        // move left once at the right of the panel
                        else if (enemyXAxis[0] >= 150) {
                            enemyMovement = -10;
                        }
                        // move the enemies
                        enemyXAxis[i] += enemyMovement;
                        if (isAlive[i] == 0) {
                            g2d.drawImage(eagleOne.getEnemy(), enemyXAxis[i], enemyYAxis[i], enemyWidth, enemyHeight, this);
                        }
                    }
                    // THIS SELECTS A RANDOM ENEMY TO SHOOT FROM
                    // if count reaches 12, generate a random number
                    if (enemyProjectileCount == 12) {
                        Random r = new Random();
                        // create a random index for enemy
                        int randomInvader = r.nextInt(10);
                        // check if enemy alive before shooting projectile from location
                        if (isAlive[randomInvader] == 0) {
                            // get the indexes coordinates and give it to the projectile
                            enemyWeaponXAxis[currentEnemyProjectile] = enemyXAxis[randomInvader];
                            enemyWeaponYAxis[currentEnemyProjectile] = enemyYAxis[randomInvader];
                        }
                        // increment current projectile
                        currentEnemyProjectile++;
                        // reset count
                        enemyProjectileCount = 0;
                    }
                    // DRAW ENEMY PROJECTILE
                    for (int k = 0; k < currentEnemyProjectile; k++) {
                        g2d.setColor(Color.white);
                        g2d.fillOval(enemyWeaponXAxis[k], enemyWeaponYAxis[k], 15, 20);
                        // to move projectile downwards must increment y-axis
                        enemyWeaponYAxis[k] += 10;
                    }
                    // CHECK IF ENEMY BEEN HIT
                    // for loop for each enemy
                    for (int y = 0; y < enemyAmount; y++) {
                        // for loop for user bullet
                        for (int z = 0; z < currentProjectile; z++) {
                            // if enemy is alive and within range of bullet at x and y coordinates
                            if (isAlive[y] == 0 && playerWeaponXAxis[z] >= enemyXAxis[y] && playerWeaponXAxis[z] <= enemyXAxis[y] + 20 && playerWeaponYAxis[z] >= enemyYAxis[y] && playerWeaponYAxis[z] <= enemyYAxis[y]) {
                                // enemy dies (0)
                                isAlive[y] = 1;
                                soundEffect(click, 0);
                                // remove projectile from screen
                                playerWeaponYAxis[z] = -20;
                                currentScore += 2;
                                if (y == 3){
                                    soundEffect(hail,0);
                                }
                                else if (y == 0 ){
                                    System.out.println("ALL ENEMY DEAD");
                                    currentScore += 5;
                                    soundEffect(birdCaw, 0);
                                    new GameWindow("gameBackground", playerAmount, playerName);
                                    closeWindow();
                                    }
                                updateScore();
                            }
                        }
                    }
                    // CHECK IF USER IS HIT
                    for (int w = 0; w < currentEnemyProjectile; w++) {
                        if (enemyWeaponXAxis[w] >= playerOneXAxis && enemyWeaponXAxis[w] <= playerOneXAxis + 60 && enemyWeaponYAxis[w] >= 420 && enemyWeaponYAxis[w] <= 440) {
                            playerOne.looseLife();
                            // enemy bullet is removed from screen
                            enemyWeaponYAxis[w] = -20;
                            if (!playerOne.isAlive()) {
                                g2d.setFont(fontGameOver);
                                g2d.drawString("GAME OVER", fontGameOverXAxis, fontGameOverYAxis);
                                // game over boolean becomes true
                                gameOver = true;
                            } // IF USER 2 IS HIT
                        } else if (playerAmount == 2 && enemyWeaponXAxis[w] >= playerTwoXAxis && enemyWeaponXAxis[w] <= playerTwoXAxis + 60 && enemyWeaponYAxis[w] >= 420 && enemyWeaponYAxis[w] <= 440) {
                            playerTwo.looseLife();
                            // enemy bullet is removed from screen
                            enemyWeaponYAxis[w] = -20;
                            if (!playerTwo.isAlive()) {
                                g2d.setFont(fontGameOver);
                                g2d.drawString("GAME OVER", fontGameOverXAxis, fontGameOverYAxis);
                                gameOver = true;
                            }
                        }
                    }
                    enemyProjectileCount++;
                    Toolkit.getDefaultToolkit().sync();
                    repaint();
                }
            }
        };      showScore();
                // add score to panel
                gamePanel.add(score);
           add(gamePanel);
        }

    //     method to provide movement and set the x coordinates of objects
    private void playerMove(String direction, byte player) {
        if (direction.equals("left") && player == 1) {
            playerOneXAxis -= playerOne.getPlayerMovementSpeed();
        } else if (direction.equals("left") && player == 2) {
            playerTwoXAxis -= playerOne.getPlayerMovementSpeed();
        } else if (direction.equals("right") && player == 1){
            playerOneXAxis += playerOne.getPlayerMovementSpeed();
        } else if (direction.equals("right") && player == 2){
            playerTwoXAxis += playerOne.getPlayerMovementSpeed();
        }
        repaint();
        // set the limits of movement to prevent screen exit.
        if (playerOneXAxis <= 0) {
            playerOneXAxis = 0;
        } else if (playerOneXAxis >= 560) {
            playerOneXAxis = 560;
        } else if (playerTwoXAxis <= 0) {
            playerTwoXAxis = 0;
        } else if (playerTwoXAxis >= 560) {
            playerTwoXAxis = 560;
        }
    }

    private void playerShoot(short player) {
        int axis = 0;
        // select the axis of the player
        if (player == 1){
            axis = playerOneXAxis;
        } else if (player ==2){
            axis = playerTwoXAxis;
        }
        // set where projectile starts centre above user
        playerWeaponXAxis[currentProjectile] = axis + 10;
        // set y coordinate where projectile starts, and increment
        playerWeaponYAxis[currentProjectile] = 400;
        soundEffect(baseball, 0);
        currentProjectile++;
    }

    private void showScore(){
        score = new JLabel("SCORE: " + currentScore);
        // customise score text
        score.setFont(new Font("Arial", Font.BOLD,35));
        score.setBounds(200, 5, 200, 50);
        score.setForeground(Color.decode("#ffe084"));
        score.setBorder(BorderFactory.createLineBorder(Color.black, 2, true));

    }
    // method to update score text
    private void updateScore(){
        score.setText("SCORE: " + currentScore);
    }

    private void gameOverCloseWindow(){
        soundEffect(gameOverSound, 0);
       try {
            Thread.sleep(4000);
            this.dispose();
        } catch (InterruptedException ex) {
           ex.printStackTrace();
       }
    }
    private void updateLeaderboard(){
        String playerTeam = playerOne.getTeamName();
        db.insertLeaderboard(playerAmount, playerName, playerTeam, currentScore);
    }

    private void closeWindow(){
        this.dispose();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
    @Override
    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == 37) {
            playerMove("left", (byte) 1);

        } else if (e.getKeyCode() == 39) {
            playerMove("right", (byte) 1);

        } else if (e.getKeyCode() == 38) {
            playerShoot((short) 1);

        } else if (e.getKeyCode() == 90){
            playerMove("left", (byte) 2);

        } else if (e.getKeyCode() == 88) {
            playerMove("right", (byte) 2);

        } else if (e.getKeyCode() == 83 && playerAmount == 2){
            playerShoot((short) 2);

        } else if (e.getKeyCode() == 27){
            soundEffect(arnold, 0);
            this.dispose();

        } else if (e.getKeyCode() == 32){
            String sound = playerOne.getCharacterSound();
            soundEffect(sound, 0);
    }
        repaint(); // repaint to change the image
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        }
    }

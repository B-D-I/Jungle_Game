package game;

import database.Database;

import javax.swing.*;
import java.awt.*;

public class Player extends GameObject {
    /**
     * This class contains all player methods and user customisation
     */

    private final String playerCharacter;
    private final String teamName;
    private final String difficulty;
    private int lives;
    private boolean alive;
    private String characterSound;
    private String projectileColour;
    private short projectileWidth;
    private short projectileSpeed;
    private short playerMovementSpeed;

    private Image playerOne;

    public Player(String username) {

        setAlive(true);
        Database db = new Database();
        playerCharacter = db.retrievePlayerInfo(username, "character");
        teamName = db.retrievePlayerInfo(username, "teamName");
        difficulty = db.retrievePlayerInfo(username, "difficulty");

        setProjectileType();
        setPlayerMovementSpeed();
        setCharacterSound();
        setLives();

        // confirm which character type belongs to player, then call relevant method to select image
        switch (playerCharacter) {
            case "Turbo-Lizard" -> loadLizardImage();
            case "Battle-Pig" -> loadPigImage();
            case "Dyno-Monkey" -> loadMonkeyImage();
        }
    }

    public String getTeamName() {
        return teamName;
    }

    public void loadLizardImage() {
        try {
            ImageIcon player =new ImageIcon("src\\main\\java\\images\\lizard.png");
            playerOne = player.getImage();
        } catch (Exception e){
            System.out.println(e);
        }
    }
    public void loadPigImage() {
        try {
            ImageIcon player =new ImageIcon("src\\main\\java\\images\\pig.png");
            playerOne = player.getImage();
        } catch (Exception e){
            System.out.println(e);
        }
    }
    public void loadMonkeyImage() {
        try {
            ImageIcon player =new ImageIcon("src\\main\\java\\images\\monkey.png");
            playerOne = player.getImage();
        } catch (Exception e){
            System.out.println(e);
        }
    }

    public Image getPlayerOne() {
        return playerOne;
    }

    public String getCharacterSound() {
        return characterSound;
    }
    // select the specific character sound
    public void setCharacterSound() {
        switch (playerCharacter) {
            case "Turbo-Lizard" -> this.characterSound = scarface;
            case "Battle-Pig" -> this.characterSound = pig;
            case "Dyno-Monkey" -> this.characterSound = monkey;
        }
    }
    // set player projectile based on character type
    public void setProjectileType() {
        switch (playerCharacter) {
            case "Turbo-Lizard" -> {
                this.projectileColour = "#bbff66";
                this.projectileWidth = 10;
                this.projectileSpeed = 10;
            }
            case "Battle-Pig" -> {
                this.projectileColour = "#ff1188";
                this.projectileWidth = 25;
                this.projectileSpeed = 5;
            }
            case "Dyno-Monkey" -> {
                this.projectileColour = "#f1c232";
                this.projectileWidth = 6;
                this.projectileSpeed = 10;
            }
        }
    }
    public String getProjectileColour() {
        return projectileColour;
    }

    public short getProjectileWidth() {
        return projectileWidth;
    }
    public short getProjectileSpeed() {
        return projectileSpeed;
    }

    public short getPlayerMovementSpeed() {
        return playerMovementSpeed;
    }
    // set the player character movement speed
    public void setPlayerMovementSpeed() {
        switch (playerCharacter) {
            case "Turbo-Lizard" -> this.playerMovementSpeed = 5;
            case "Battle-Pig" -> this.playerMovementSpeed = 3;
            case "Dyno-Monkey" -> this.playerMovementSpeed = 10;
        }
    }

    public void setLives() {
        switch (difficulty) {
            case "Easy" -> this.lives = 3;
            case "Medium" -> this.lives = 2;
            case "Hard" -> this.lives = 1;
        }
    }
    public int getLives(){
        return this.lives;
    }

    public void looseLife(){
        lives -= 1;
        if (lives <= 0) {
            alive = false;
            lives = 0;
        }
    }

    public void setAlive(boolean alive) {
        this.alive = true;
    }

    public boolean isAlive() {
        return alive;
    }

}

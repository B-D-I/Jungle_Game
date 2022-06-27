package game;

import javax.swing.*;
import java.awt.*;

public class Enemy extends GameObject{
    /**
     * This class contains enemy specifications such as the image type and amount of enemies
     */

    private Image enemyEagle;
    private short enemyAmount;

    public Enemy() {
        setAlive(true);
        setWeaponAxis();
        loadImage();
        setEnemyAmount();
    }

    /**
     * Method to act as an image setter of enemy
     */
    public void loadImage() {
        try {
            ImageIcon enemyImageOne =new ImageIcon(path+"images\\eagle.png");
            enemyEagle = enemyImageOne.getImage();
            enemyEagle = enemyEagle.getScaledInstance(150,150, Image.SCALE_SMOOTH);
        } catch (Exception e){
            System.out.println("e");
        }
    }
    // Enemy image getter method
    public Image getEnemy() {
        return enemyEagle;
    }

    public short getEnemyAmount() {
        return enemyAmount;
    }

    public void setEnemyAmount() {
        this.enemyAmount = 10;
    }

}

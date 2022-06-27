package game;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

abstract class KeyWindow extends View implements KeyListener {
    /**
     * This abstract class defines all subclass with background image, specified in the parameter upon initialisation
     */

    protected Controller controller;
    protected JPanel panel;
    private String backgroundType;

    public KeyWindow(String imageName) {
        setTitle("GAME WINDOW");
        init(imageName);
        this.addKeyListener(this);
    }

    /**
     * Method to initiate JPanel and set background image
     * @param imageName The specified background image
     */
    private void init(String imageName) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setImageName(imageName);
        // window dimensions
        setSize(630, 640);

        panel = new JPanel() {
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                BufferedImage image = null;
                try {
                    image = setBackgroundType();
                    // exception if game background not work
                } catch (IOException e) {
                    e.printStackTrace();
                }
                g.drawImage(image, 0, 0, 630, 600, this);
            }
        };
        add(panel);
    }

    // set the image background
    private void setImageName(String imageName) {
        backgroundType = imageName;
    }

    // throws exception to JPanel
    private BufferedImage setBackgroundType() throws IOException {
        BufferedImage image = switch (backgroundType) {
            case "loggedOutPage" -> ImageIO.read(new File(path+"images\\TitleScreen.jpg"));
            case "loggedInPage" -> ImageIO.read(new File(path+"images\\LoggedInScreen.jpg"));
            case "gameBackground" -> ImageIO.read(new File(path+"images\\jungleBackground2.jpg"));
            case "gameOver" -> ImageIO.read(new File(path+"images\\JGameOver2.jpg"));
            default -> null;
        };
        return image;
    }

    // Static method to enable sound effects to be called
    static void soundEffect(String audio, int repeats) {

        File localAudio;
            try {
                localAudio = new File(audio);
                // class to obtain audio stream
                AudioInputStream audioS = AudioSystem.getAudioInputStream(localAudio);
                // class to enable functionality such as looping
                Clip clip = AudioSystem.getClip();
                clip.open(audioS);
                clip.setFramePosition(2);
                clip.start();
                clip.loop(repeats);
            } catch (MalformedURLException e) {
                System.out.println(e);
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                e.printStackTrace();
            }
    }
}

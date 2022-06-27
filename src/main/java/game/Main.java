package game;

import javax.swing.*;

/**
 * The 'Jungle Attack' game provides an interactive solo or multiplayer GUI game, with database storage for user and leaderboard information.
 *
 * @author Nathan Hewett
 * @version 1.0
 * @since 04-12-2021
 */

public class Main extends JFrame {

    // SOUND EFFECTS
    String click = "src\\main\\java\\music\\click.wav";
    String arrow = "src\\main\\java\\music\\arrow2.wav";
    String baseball = "src\\main\\java\\music\\baseball_hit.wav";

    String birds = "src\\main\\java\\music\\bird_chirping2.wav";
    String gameOverSound = "src\\main\\java\\music\\game_over.wav";
    String guns = "src\\main\\java\\music\\gunshots2_x.wav";
    String monkey = "src\\main\\java\\music\\monkey2.wav";
    String pig = "src\\main\\java\\music\\pig.wav";
    String arnold = "src\\main\\java\\music\\arnold.wav";
    String scarface = "src\\main\\java\\music\\scarface.wav";
    String hail = "src\\main\\java\\music\\hail.wav";
    String birdCaw = "src\\main\\java\\music\\bird_caw2.wav";


    public static void main(String[] args) {
        // home page
        new LoggedOutHomeWindow("loggedOutPage");
    }
}


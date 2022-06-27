package game;

import javax.swing.*;

abstract class Effects extends JFrame {

    String path = "out\\artifacts\\Jungle_Game_jar\\resources\\";

    // if executing .jar file, use below path >>
//    String path = "resources\\";

    // SOUND EFFECTS
    String click = path+"music\\click.wav";
    String arrow = path+"music\\arrow2.wav";
    String baseball = path+"music\\baseball_hit.wav";
    String birds = path+"music\\bird_chirping2.wav";
    String gameOverSound = path+"music\\game_over.wav";
    String guns = path+"music\\gunshots2_x.wav";
    String monkey = path+"music\\monkey2.wav";
    String pig = path+"music\\pig.wav";
    String arnold = path+"music\\arnold.wav";
    String scarface = path+"music\\scarface.wav";
    String hail = path+"music\\hail.wav";
    String birdCaw = path+"music\\bird_caw2.wav";

}

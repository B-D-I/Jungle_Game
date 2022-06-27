package game;

import javax.swing.*;
import java.awt.*;

abstract class View extends Effects{

    // window icon on all windows
    private final Image icon = new ImageIcon("out\\artifacts\\Jungle_Game_jar\\resources\\images\\lizard.png").getImage();

    public View(){
        setVisible(true);
        // dispose or exit on close?
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        // place icon on all windows
        setIconImage(icon);
    }

}

package game;

import java.awt.event.KeyEvent;

public class LoggedOutHomeWindow extends KeyWindow {

    public LoggedOutHomeWindow(String imageName) {

        // set the background
        super(imageName);
        soundEffect(birds, 30);
        // key event listener
        controller = new Controller();
        this.addKeyListener(controller);
    }

    // KEY LISTENER MOVED TO CONTROLLER CLASS - however not removed from superclass yet
    @Override
    public void keyTyped(KeyEvent e) {
    }
    @Override
    public void keyPressed(KeyEvent e) {
    }
    @Override
    public void keyReleased(KeyEvent e) {
    }
}

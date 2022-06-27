package game;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class LoggedInHomeWindow extends KeyWindow {

  GameWindow game;
  CredentialsWindow credentials;
  private final String username;


  public LoggedInHomeWindow(String imageName, String username){
    super(imageName);
    this.username = username;
    soundEffect(guns, 1);
  }

  private String getPlayer() {
    return username;
  }

  private void logout(){
    this.dispose();
    soundEffect(arrow, 0);
  }

  /**
   * Method to initiate a single player game
   */
  private void onePlayerGame(){
    JOptionPane.showMessageDialog(this, """
                * MOVE: RIGHT & LEFT KEYS
                * SHOOT: UP KEY
                * WAR-CRY: SPACE BAR
                * QUIT: ESCAPE
                """);
    game = new GameWindow("gameBackground", (byte) 1, username);
    soundEffect(arrow, 0);
  }
  private void twoPlayerGame(){
    JOptionPane.showMessageDialog(this, """
                * P2 MOVE: Z (RIGHT) / X (LEFT)
                * P2 SHOOT: S
                * P1 MOVE: RIGHT & LEFT KEYS
                * P1 SHOOT: UP KEY
                * WAR-CRY: SPACE BAR
                * QUIT: ESCAPE
                """);
    game = new GameWindow("gameBackground", (byte) 2, username);
    soundEffect(arrow, 0);
  }

  private void openLeaderboard(){
    credentials = new LeaderboardWindow();
    soundEffect(arrow, 0);
  }

  private void openModifyWindow(){
    credentials = new ModifyUserWindow(getPlayer());
    soundEffect(arrow, 0);
  }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
      if (e.getKeyCode() == 49) {
        onePlayerGame();
      } else if (e.getKeyCode() == 50){
        twoPlayerGame();
      } else if (e.getKeyCode() == 76) {
        openLeaderboard();
      } else if (e.getKeyCode() == 77) {
        openModifyWindow();
      } else if (e.getKeyCode() == 88) {
        logout();
      }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
  }

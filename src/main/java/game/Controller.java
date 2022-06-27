package game;

import database.Database;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Controller extends Effects implements KeyListener, ActionListener {

    /**
     * This Controller class is designed to implement EventListener Interfaces, such as KeyListener, MouseListener and
     * ActionListener. All application event listening will eventually be held in this class.
     */

    Database db = new Database();
    protected CredentialsWindow credentials;
    protected LoginWindow login;

    public Controller(){
    }

    public void loggedOutWindowLogin(){
        credentials = new LoginWindow();
        KeyWindow.soundEffect(baseball, 0);
    }
    public void loggedOutWindowCreateUser(){
        credentials = new CreateUserWindow();
        KeyWindow.soundEffect(baseball, 0);
    }

    public void login(){
        /*
          Action event listener to confirm when 'Create User' button is pressed. If user is in database, the 'LoggedInHomeWindow'
          will open and the pop-up window will close. If not in database, a dialog box will prompt the user
          */
        String usernameText;
        usernameText = LoginWindow.usernameField.getText();
        // call the verifyUser database method to return a Boolean of if user in DB
        if (db.verifyUser(usernameText)) {
            JOptionPane.showMessageDialog(this, "Hi " + usernameText);
            new LoggedInHomeWindow("loggedInPage", usernameText);
            KeyWindow.soundEffect(monkey, 0);
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Username Not Recognised");
            KeyWindow.soundEffect(baseball, 0);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
//        if (e.getSource() == login.userButton) {
//            login();

    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        /*
          listen for 'l' and 'c' keyboard commands and call associated methods.
         */
        if (e.getKeyCode() == 76) {
            loggedOutWindowLogin();
        } else if (e.getKeyCode() == 67) {
            loggedOutWindowCreateUser();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }


}

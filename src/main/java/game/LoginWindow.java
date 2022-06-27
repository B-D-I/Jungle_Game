package game;

import database.Database;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class LoginWindow extends CredentialsWindow {

    private final Database db;
    private final JLabel gifLabel;
    //Controller ct = new Controller();
    public LoginWindow(){

        setTitle("LOGIN");
        userButton = new JButton("LOGIN");
        ImageIcon gif = new ImageIcon("src\\main\\java\\Images\\eye.gif");
        gifLabel = new JLabel(gif);

        setBounds(10, 10, 400, 280);
        addComponents();
        setLocationAndSize();
        setLayoutManager();
        addActionEvent();

        db = new Database();
    }

    @Override
    public void setLocationAndSize() {
        textUsername.setBounds(50,35,100,30);
        usernameField.setBounds(150,35,150,30);
        userButton.setBounds(165,100,100,30);
        gifLabel.setBounds(0, -120, 400, 500);
    }

    @Override
    public void addComponents() {
        container.add(textUsername);
        container.add(usernameField);
        container.add(userButton);
        container.add(gifLabel);
    }

    @Override
    public void addActionEvent() {
        //userButton.addActionListener(ct);
        userButton.addActionListener(this);
    }

    public void login(){
        String usernameText;
        usernameText = usernameField.getText();

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
        login();
    }
}
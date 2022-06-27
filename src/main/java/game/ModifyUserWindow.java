package game;

import database.Database;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ModifyUserWindow extends CredentialsWindow {
    /**
     * Class to enable user account modification
     */

    private final Database db;

    private final String username;
    private final JButton deleteOption = new JButton("DELETE ACCOUNT");
    private final JLabel deleteWarning = new JLabel("WARNING: ALL PLAYER INFO WILL BE DELETED IF PRESSED!");
    private final JLabel textUsername;

    public ModifyUserWindow(String user){

        this.username = user;

        deleteWarning.setFont(new Font("Arial", Font.BOLD,14));
        deleteWarning.setForeground(Color.decode("#cf4449"));
        deleteOption.setBackground(Color.decode("#f6546a"));

        //this.db = db;
        setTitle("MODIFY ACCOUNT");
        userButton = new JButton("MODIFY ACCOUNT");
        textUsername = new JLabel("PLAYER NAME:    " + user);
        textUsername.setFont(new Font("Arial", Font.BOLD,18));

        addComponents();
        setLocationAndSize();
        setLayoutManager();
        addActionEvent();

        db = new Database();
    }

    @Override
    public void setLocationAndSize() {
        //Setting location and Size of each component using setBounds() method.
        textUsername.setBounds(175,50,250,30);
        textTeamName.setBounds(50,150,250,30);
        teamNameField.setBounds(350,150,150,30);
        characterSelect.setBounds(320, 250, 200, 30);
        difficultySelect.setBounds(40, 250, 200, 30);
        userButton.setBounds(225, 360, 150, 30);
        userButton.setBounds(300, 350, 150, 30);
        deleteOption.setBounds(100, 350, 150, 30);
        deleteWarning.setBounds(75, 400, 500, 35);
    }

    @Override
    public void addComponents() {
        container.add(textUsername);
        container.add(textTeamName);
        container.add(teamNameField);
        container.add(userButton);
        container.add(characterSelect);
        container.add(difficultySelect);
        container.add(deleteOption);
        container.add(deleteWarning);
    }

    @Override
    public void addActionEvent() {
        userButton.addActionListener(this);
        characterSelect.addActionListener(this);
        difficultySelect.addActionListener(this);
        deleteOption.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String teamText;
        String characterType;
        String difficulty;

        teamText = teamNameField.getText();
        characterType = (String) characterSelect.getSelectedItem();
        difficulty = (String) difficultySelect.getSelectedItem();

        if (e.getSource() == userButton) {
            if (teamText.isEmpty() | teamText.length()>=15) {
                JOptionPane.showMessageDialog(this, "Fields Cannot Be Empty. Team Name must be under 15 characters");
            } else
                db.modifyUser(username, teamText, characterType, difficulty);
                JOptionPane.showMessageDialog(this, "User Details Modified");
        } else if (e.getSource() == deleteOption) {
                db.deleteUser(username);
                JOptionPane.showMessageDialog(this, "User Deleted");
                this.dispose();
        }
    }
}

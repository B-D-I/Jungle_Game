package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public abstract class CredentialsWindow extends View implements ActionListener{
    /**
     * Abstract class to contain the design layout for all credential window types.
     */

    Container container = getContentPane();
    // static fields to enable the controller class to perform action listener
    static JTextField usernameField = new JTextField();
    protected JLabel textUsername = new JLabel("USERNAME");
    protected JLabel textTeamName = new JLabel("TEAM NAME (FOR 2 PLAYER)");
    static JTextField teamNameField = new JTextField();
    static String[] characters = {"Turbo-Lizard", "Battle-Pig", "Dyno-Monkey"};
    protected JLabel characterSelection = new JLabel("CHARACTER: ");
    static JComboBox characterSelect = new JComboBox(characters);
    static String[] difficulty = {"Medium", "Hard", "Easy"};
    protected JLabel difficultySelection = new JLabel("DIFFICULTY: ");
    static JComboBox difficultySelect = new JComboBox(difficulty);
    protected JButton userButton;

    protected CredentialsWindow(){
        textUsername.setFont(new Font("Arial", Font.BOLD,15));
        textTeamName.setFont(new Font("Arial", Font.BOLD,15));
        characterSelection.setFont(new Font("Arial", Font.BOLD,15));
        difficultySelection.setFont(new Font("Arial", Font.BOLD,15));
        getContentPane().setBackground(Color.decode("#66bb99"));
        setBounds(10, 10, 580, 500);
    }

    protected void setLayoutManager() {
        container.setLayout(null);
    }

    abstract void setLocationAndSize();

    abstract void addComponents();

    abstract void addActionEvent();

    protected void setDefaultLocationAndSize() {
        //Setting the default location and size of each component using setBounds() method.
        textUsername.setBounds(50,40,200,30);
        usernameField.setBounds(300,40,200,30);
        textTeamName.setBounds(50,80,250,30);
        teamNameField.setBounds(300,80,200,30);
        difficultySelection.setBounds(50, 130, 200, 30);
        difficultySelect.setBounds(300, 130, 200, 30);
        characterSelection.setBounds(50, 200, 200, 30);
        characterSelect.setBounds(300, 200, 200, 30);
        userButton.setBounds(225, 360, 200, 30);
    }

    protected void setDefaultComponents(){
        container.add(textUsername);
        container.add(usernameField);
        container.add(textTeamName);
        container.add(teamNameField);
        container.add(userButton);
        container.add(characterSelect);
        container.add(characterSelection);
        container.add(difficultySelection);
        container.add(difficultySelect);
    }
}

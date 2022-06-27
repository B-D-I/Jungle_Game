package game;

import database.Database;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class CreateUserWindow extends CredentialsWindow {
    /**
     * This class enables a pop-up 'create user' window, utilising ActionListener to insert a new users details when the
     * associated button is pressed.
     */
    private final Database db;

    private final JLabel lizardInfo = new JLabel("* Turbo-Lizard: Average Speed & Average Projectile");
    private final JLabel pigInfo = new JLabel("* Battle-Pig: Slow Speed & Large Projectile");
    private final JLabel monkeyInfo = new JLabel("* Dyno-Monkey: Fast Speed & Small Projectile");

    //Controller ct = new Controller();

    public CreateUserWindow() {
        setTitle("CREATE ACCOUNT");
        userButton = new JButton("CREATE USER");
        db = new Database();
        addComponents();
        setLocationAndSize();
        setLayoutManager();
        addActionEvent();
        displayFeatures();
    }

    @Override
    public void setLocationAndSize() {
        setDefaultLocationAndSize();
        lizardInfo.setBounds(100,120,400,300);
        pigInfo.setBounds(100,150,400,300);
        monkeyInfo.setBounds(100,180,400,300);
        userButton.setBounds(225, 380, 150, 30);
    }

    @Override
    public void addComponents() {
        setDefaultComponents();
        container.add(lizardInfo);
        container.add(pigInfo);
        container.add(monkeyInfo);
    }

    @Override
    public void addActionEvent() {

//        userButton.addActionListener(ct);
//        characterSelect.addActionListener(ct);
//        difficultySelect.addActionListener(ct);
        userButton.addActionListener(this);
        characterSelect.addActionListener(this);
        difficultySelect.addActionListener(this);
    }

    private void displayFeatures(){
        lizardInfo.setFont(new Font("Arial", Font.BOLD,16));
        pigInfo.setFont(new Font("Arial", Font.BOLD,16));
        monkeyInfo.setFont(new Font("Arial", Font.BOLD,16));
        lizardInfo.setForeground(Color.decode("#0c2c63"));
        pigInfo.setForeground(Color.decode("#0c2c63"));
        monkeyInfo.setForeground(Color.decode("#0c2c63"));
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        /*
          Action event listener to confirm when 'Create User' button is pressed. If user is in database, the 'LoggedInHomeWindow'
          will open and the pop-up window will close. If not in database, a dialog box will prompt the user
          */
        String usernameText;
        String teamText;
        String characterType;
        String difficulty;
        // Retrieve user inputs
        usernameText = usernameField.getText();
        teamText = teamNameField.getText();
        characterType = (String) characterSelect.getSelectedItem();
        difficulty = (String) difficultySelect.getSelectedItem();

        // confirm 'userButton' is pressed
        if (e.getSource() == userButton) {
            assert difficulty != null;
            assert characterType != null;
            // Ensure fields are not empty
            if (usernameText.isEmpty() | teamText.isEmpty() | characterType.isEmpty() | difficulty.isEmpty() ){
                JOptionPane.showMessageDialog(this, "Complete All Fields!");
            // Ensure name characters are less than 15
            } else if (usernameText.length()>=15 | teamText.length()>=15) {
                JOptionPane.showMessageDialog(this, "Names Must Be Less Than 15 Characters");
            } else {
            // Create the new user account
                db.insert(usernameText, teamText, characterType, difficulty);
                JOptionPane.showMessageDialog(this, "User Created");
                KeyWindow.soundEffect(baseball, 0);
                this.dispose();
            }
        }
    }
}

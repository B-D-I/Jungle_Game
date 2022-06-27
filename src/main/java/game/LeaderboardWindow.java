package game;

import database.Database;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class LeaderboardWindow extends CredentialsWindow implements MouseListener {
    /**
     * Display single and multiplayer top 10 scores from database
     */

    private final Database db = new Database();
    private final JLabel leaderboard = new JLabel("LEADERBOARD");
    private final JLabel leaderTablesSingle = new JLabel("Single Player");
    private final JLabel leaderTablesMulti = new JLabel("Two Player");
    private final JTextArea textBox;
    private final JTextArea textBoxMulti;

    public LeaderboardWindow(){
        setTitle("LEADERBOARD");
        userButton = new JButton("CLOSE");
        leaderboard.setFont(new Font("Arial", Font.BOLD,50));

        textBox = new JTextArea();
        String lead = db.retrieveSingleUserLeaderboard();
        textBox.setText(lead);
        textBox.setFont(new Font("Arial", Font.BOLD, 14));

        textBoxMulti = new JTextArea();
        String teamLead = db.retrieveMultiUserLeaderboard();
        textBoxMulti.setText(teamLead);
        textBoxMulti.setFont(new Font("Arial", Font.BOLD, 14));

        addComponents();
        setLocationAndSize();
        setLayoutManager();
        addActionEvent();

        this.addMouseListener(this);
    }

    @Override
    public void setLocationAndSize() {
        leaderboard.setBounds(80,20,550,50);
        leaderTablesSingle.setBounds(20, 90, 200, 20);
        leaderTablesMulti.setBounds(300, 90, 200, 20);
        userButton.setBounds(500, 20, 50, 50);
        textBox.setBounds(20,110,250,340);
        textBoxMulti.setBounds(300,110,240,340);
    }

    @Override
    public void addComponents() {
        add(leaderboard);
        add(userButton);
        add(textBox);
        add(textBoxMulti);
        add(leaderTablesSingle);
        add(leaderTablesMulti);
    }

    @Override
    public void addActionEvent() {
        userButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        this.dispose();
        }

    @Override
    public void mousePressed(MouseEvent e) {
    }
    @Override
    public void mouseReleased(MouseEvent e) {
    }
    @Override
    public void mouseEntered(MouseEvent e) {
    }
    @Override
    public void mouseExited(MouseEvent e) {
    }
}

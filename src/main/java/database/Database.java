package database;

import java.sql.*;

public class Database {
    /**
     * This Database class contains all methods for SQLite database queries.
     */
    private Connection c = null;
    private Statement stmt = null;

    /**
     * This method provides a connection to the database, for subsequent methods to utilise. This Access Modifier
     * is set to private as there is no requirement for the database to be accessed outside this class.
     */
    private void openDB() {
        try {
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:src\\main\\java\\database\\JungleGameDB.db";
            c = DriverManager.getConnection(url);
            c.setAutoCommit(false);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            System.out.println("END");
        }
    }

    /**
     * This method is used only to test the database connection using JUnit.
     */
    public void testOpenDB() {
        try {
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:src\\main\\java\\database\\JungleGameDB.db";
            c = DriverManager.getConnection(url);
            c.setAutoCommit(false);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            System.out.println("Database TEST");
        }
    }

    /**
     * This method is used to close the database connection.
     */
    private void closeConnection() {
        try {
            c.close();
            System.out.println("Database Closed");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method inserts a new user information into the database.
     * @param username This is the username for the new account
     * @param teamName This is the team name for the new account
     * @param characterName This is the chosen character for the nwe account
     * @param difficulty The new user's difficulty level
     */
    public void insert(String username, String teamName, String characterName, String difficulty) {
        try {
            openDB();
            stmt = c.createStatement();
            String sql = "INSERT INTO Players (Username, TeamName, Character, Difficulty) VALUES ('" + username + "', '" + teamName + "', '" + characterName + "', '" + difficulty + "')";
            stmt.executeUpdate(sql);
            stmt.close();
            c.commit();
            closeConnection();
            System.out.println("Record created successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("END");
        closeConnection();
    }

    /**
     * Method for deleting a user from the database.
     * @param username This is the username for the user data to be deleted
     */
    public void deleteUser(String username) {
        try {
            openDB();
            stmt = c.createStatement();
            String sql = "DELETE FROM Players WHERE Username = '" + username + "'";
            stmt.executeUpdate(sql);
            stmt.close();
            c.commit();
            System.out.println(username + " Deleted");
            closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        } closeConnection();
    }

    /**
     * Method to verify a user in database.
     * @param username This is the player being verified
     * @return boolean to confirm if username in database
     */
    public boolean verifyUser(String username) {
        try {
            openDB();
            this.stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Players WHERE Username = '" + username + "'");
            // iterate through db. If username in db, return true
            if (rs.next()) {
                String user = rs.getString("Username");
                return username.equals(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return false;
    }

    /**
     * Method the retrieve specified user information.
     * @param username The player that requires information retrieval
     * @param retrieveType The type of data required
     * @return The specified data in parameter
     */
    public String retrievePlayerInfo(String username, String retrieveType) {
        openDB();
        try {
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Players WHERE Username = '" + username + "'");
            String characterType = rs.getString("Character");
            String teamName = rs.getString("teamName");
            String difficulty = rs.getString("Difficulty");
            switch (retrieveType) {
                case "character":
                    return characterType;
                case "teamName":
                    return teamName;
                case "difficulty":
                    return difficulty;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        } return null;
    }

    /**
     * Method to modify user information.
     * @param player The player being modified
     * @param newTeamName The modified team name
     * @param newCharacter The selected character
     * @param difficulty The selected difficulty
     */
    public void modifyUser(String player, String newTeamName, String newCharacter, String difficulty) {
        try {
            openDB();
            this.stmt = c.createStatement();
            String sql1 = "UPDATE Players Set TeamName = '"+ newTeamName +"' Where Username = '"+ player +"'";
            String sql2 = "UPDATE Players Set Character = '"+ newCharacter +"' Where Username = '"+ player +"'";
            String sql3 = "UPDATE Players Set Difficulty = '"+ difficulty +"' Where Username = '"+ player +"'";
            stmt.executeUpdate(sql1);
            stmt.executeUpdate(sql2);
            stmt.executeUpdate(sql3);
            c.commit();
            closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        } closeConnection();
    }

    /**
     * Method to insert score data into leaderboard.
     * @param playerAmount Confirm whether one or two player
     * @param username The username of player
     * @param teamName The team name of player
     * @param score The final score of the game to be saved
     */
    public void insertLeaderboard(byte playerAmount, String username, String teamName, int score) {
        openDB();
        if (playerAmount == 1) {
            try {
                stmt = c.createStatement();
                String sql = "INSERT INTO Leaderboard (Username, RecentScore) VALUES ('" + username + "', '" + score + "')";
                stmt.executeUpdate(sql);
                stmt.close();
                c.commit();
                closeConnection();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } else if (playerAmount == 2) {
            try {
                stmt = c.createStatement();
                String sql = "INSERT INTO Team_Leaderboard (TeamName, RecentScore) VALUES ('" + teamName + "', '" + score + "')";
                stmt.executeUpdate(sql);
                stmt.close();
                c.commit();
                closeConnection();
            } catch (SQLException ex) {
                ex.printStackTrace();
            } closeConnection();
        }
    }

    /**
     * Method to retrieve single player leader board score
     * @return all single player leaderboard scores
     */
    public String retrieveSingleUserLeaderboard(){
        openDB();
        try {
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Leaderboard ORDER BY RecentScore DESC LIMIT 10");
            StringBuilder result = new StringBuilder();
            // iterate until end of results loop
            while (rs.next()) {
                String username = rs.getString("Username");
                String RecentScore = rs.getString("RecentScore");

                result.append(username).append("\n").append("                  ->").append(RecentScore).append("\n ");
            }
            System.out.println("Leaderboard" + result);
            closeConnection();
            return result.toString();
        } catch (SQLException e) {
            e.printStackTrace();
        } closeConnection();
        return null;
    }
    /**
     * Method to retrieve multiplayer leader board score
     * @return all multiplayer leaderboard scores
     */
    public String retrieveMultiUserLeaderboard(){
        openDB();
        try {
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Team_Leaderboard ORDER BY RecentScore DESC LIMIT 10");
            StringBuilder result = new StringBuilder();
            while (rs.next()) {
                String teamName = rs.getString("TeamName");
                String teamRecentScore = rs.getString("RecentScore");

                result.append(teamName).append("\n").append("                  -> ").append(teamRecentScore).append("\n ");
            }
            System.out.println("Team Leaderboard" + result);
            return result.toString();
        } catch (SQLException e) {
            e.printStackTrace();
        } closeConnection();
        return null;
    }
}

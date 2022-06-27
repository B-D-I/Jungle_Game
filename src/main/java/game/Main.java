package game;

/**
 * The 'Jungle Attack' game provides an interactive solo or multiplayer GUI game, with database storage for user and leaderboard information.
 *
 * @author Nathan Hewett
 * @version 1.0
 * @since 04-12-2021
 */

public class Main {

    public static void main(String[] args) {
        // home page
        new LoggedOutHomeWindow("loggedOutPage");
    }
}


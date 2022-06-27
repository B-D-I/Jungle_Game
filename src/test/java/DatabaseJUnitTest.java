
import database.Database;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

public class DatabaseJUnitTest {

    Database dbTest = new Database();

    // test the connection time to database is under 3 seconds
    @BeforeEach
    @Timeout(3)
    public void testConnection(){
        dbTest.testOpenDB();
    }
    // test that verifying users from database is correct
    @Test
    public void verifyUserTest(){
        String name = "nathan";
        String name2 = "unknown";
        boolean test1 = dbTest.verifyUser(name);
        boolean test2 = dbTest.verifyUser(name2);

        Assertions.assertTrue(test1);
        Assertions.assertFalse(test2);
    }

    // test to confirm the correct credentials are returned from database
    @Test
    public void dbRetrievalTest(){
        String name = "nathan";
        String character = "Dyno-Monkey";
        String teamName = "A-Team";
        String difficulty = "Hard";

        Assertions.assertEquals(character, dbTest.retrievePlayerInfo(name, "character"));
        Assertions.assertEquals(teamName, dbTest.retrievePlayerInfo(name, "teamName"));
        Assertions.assertEquals(difficulty, dbTest.retrievePlayerInfo(name, "difficulty"));

        Assertions.assertNotSame("unknown", dbTest.retrievePlayerInfo(name, "character"));
        Assertions.assertNotSame("unknown", dbTest.retrievePlayerInfo(name, "teamName"));
        Assertions.assertNotSame("unknown", dbTest.retrievePlayerInfo(name, "difficulty"));
    }
    // test ensuring leaderboard information does not return empty
    @Test
    public void testLeaderboardRetrieval(){
        Assertions.assertNotNull(dbTest.retrieveSingleUserLeaderboard());
        Assertions.assertNotNull(dbTest.retrieveMultiUserLeaderboard());
    }
}

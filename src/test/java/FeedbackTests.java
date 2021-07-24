import DataBaseConnection.JDBCPostgreSQLConnector;
import Entity.Feedback;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FeedbackTests {

    private GregorianCalendar dateOfCreating;
    private Feedback feedback;

    @BeforeClass
    public static void init(){
        JDBCPostgreSQLConnector.initConnection();
    }

    @Before
    public void setUp(){
        dateOfCreating = new GregorianCalendar(2000, Calendar.APRIL, 24);
        feedback = new Feedback("All cool",dateOfCreating.getTime());
    }

    @AfterClass
    public static void destroy() throws SQLException {
        JDBCPostgreSQLConnector.closeConnection();
    }

    @Test
    public void testAddedNewFeedback(){
        feedback.addNewFeedback();
    }

    @Test
    public void testSelectFeedback(){
        feedback.selectFeedBack(2);
        assertEquals(2, feedback.getId());
    }

    @Test
    public void testDeleteFeedback(){
        feedback.addNewFeedback();
        feedback.getLast();
        feedback.deleteFeedback();
    }

    @Test
    public void tetsUpdateFeedback(){
        feedback.addNewFeedback();
        feedback.getLast();
        feedback.setDescription("All bad");
        feedback.updateFeedBack();
    }
}

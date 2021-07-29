import org.buldakov.employeeControl.Entity.Team;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.Assert.*;

public class FeedbackTests {

    private GregorianCalendar dateOfCreating;
    private Team feedback;
    List<Team> feedbackList;

    @BeforeClass
    public static void init(){
        JDBCPostgreSQLConnector.initConnection();
    }

    @Before
    public void setUp(){
        dateOfCreating = new GregorianCalendar(2000, Calendar.APRIL, 24);
        feedback = new Team("All cool",dateOfCreating.getTime());
        feedbackList = new ArrayList<>();
    }

    @AfterClass
    public static void destroy() throws SQLException {
        JDBCPostgreSQLConnector.closeConnection();
    }

    @Test
    public void testAddedNewFeedback(){
        feedback.addNewFeedback();
        Team feedbackLastAdded = new Team();
        feedbackLastAdded.getLast();
        assertEquals(feedbackLastAdded.getId(),feedback.getId());
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
        assertTrue(feedback.deleteFeedback());
    }

    @Test
    public void tetsUpdateFeedback(){
        feedback.addNewFeedback();
        feedback.getLast();
        feedback.setDescription("All bad");
        assertTrue(feedback.updateFeedBack());
    }

    @Test
    public void testSelectAllFeedback(){
        feedbackList = feedback.selectAll();
        assertNotNull(feedbackList);
    }
}

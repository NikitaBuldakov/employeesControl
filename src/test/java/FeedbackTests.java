import org.buldakov.employeeControl.Config.SpringConfig;
import org.buldakov.employeeControl.Entity.Feedback;
import org.buldakov.employeeControl.HibernateUtil.HibernateSessionFactoryUtil;
import org.buldakov.employeeControl.Service.FeedbackService;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

public class FeedbackTests {

    private Feedback feedback;
    List<Feedback> feedbackList;
    List<Feedback> modifiedList;
    private FeedbackService feedbackService;

    @Before
    public void setUp(){

        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(SpringConfig.class);
        ctx.scan("org.buldakov.employeeControl");
        ctx.refresh();
        feedback = ctx.getBean(Feedback.class);
        feedbackService = ctx.getBean(FeedbackService.class);
        feedbackList = new ArrayList<>();
        modifiedList = new ArrayList<>();
    }

    @AfterClass
    public static void destroy(){
        HibernateSessionFactoryUtil.shutdown();
    }

    @Test
    public void testAddedNewFeedback(){
        feedback = feedbackService.findFeedback(2);
        feedbackList = feedbackService.findAllFeedbacks();
        feedbackService.saveFeedback(feedback);
        modifiedList = feedbackService.findAllFeedbacks();
        assertTrue(modifiedList.size() > feedbackList.size());
    }


    @Test
    public void testSelectFeedback(){
        feedback = feedbackService.findFeedback(2);
        assertNotNull(feedback);
        assertEquals(2,feedback.getId());
    }

    @Test
    public void testDeleteFeedback(){
        feedback = feedbackService.findFeedback(2);
        feedbackService.saveFeedback(feedback);
        modifiedList = feedbackService.findAllFeedbacks();
        feedbackService.deleteFeedback(feedback);
        feedbackList = feedbackService.findAllFeedbacks();
        assertTrue(modifiedList.size() > feedbackList.size());
    }

    @Test
    public void testUpdateFeedback(){
        feedback = feedbackService.findFeedback(2);
        feedback.setDescription("Test cool tests");
        feedbackService.updateFeedback(feedback);
        Feedback modified = feedbackService.findFeedback(2);
        assertEquals("Test cool tests",modified.getDescription());
    }

    @Test
    public void testSelectAllFeedback(){
        feedbackList = feedbackService.findAllFeedbacks();
        assertTrue(feedbackList.size() > 0);
    }

}

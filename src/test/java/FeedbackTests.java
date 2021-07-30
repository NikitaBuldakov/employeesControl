
import Entity.Feedback;
import HibernateUtil.HibernateSessionFactoryUtil;
import Service.FeedbackService;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

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
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        feedback = (Feedback) applicationContext.getBean("feedback");
        feedbackService = (FeedbackService) applicationContext.getBean("feedbackService");
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

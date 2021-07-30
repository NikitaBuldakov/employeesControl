package org.buldakov.employeeControl.Service;

import org.buldakov.employeeControl.DAO.FeedbackDAOImpl;
import org.buldakov.employeeControl.Entity.Feedback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FeedbackService {

    @Autowired
    private final FeedbackDAOImpl feedbackDAO;

    public FeedbackService(FeedbackDAOImpl feedbackDAO){
        this.feedbackDAO = feedbackDAO;
    }

    public Feedback findFeedback(long id) {
        return feedbackDAO.findById(id);
    }

    public void saveFeedback(Feedback feedback) {
        feedbackDAO.save(feedback);
    }

    public void deleteFeedback(Feedback feedback) {
        feedbackDAO.delete(feedback);
    }

    public void updateFeedback(Feedback feedback) {
        feedbackDAO.update(feedback);
    }

    public List<Feedback> findAllFeedbacks() {
        return feedbackDAO.findAll();
    }
}

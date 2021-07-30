package Service;


import DAO.FeedbackDAOImpl;
import Entity.Feedback;

import java.util.List;


public class FeedbackService {

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

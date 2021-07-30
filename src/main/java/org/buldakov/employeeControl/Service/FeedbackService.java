package org.buldakov.employeeControl.Service;

import org.buldakov.employeeControl.DAO.FeedbackDAOImpl;
import org.buldakov.employeeControl.Entity.Feedback;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class FeedbackService {

    @Autowired
    private final FeedbackDAOImpl feedbackDAO;

    public FeedbackService(FeedbackDAOImpl feedbackDAO){
        this.feedbackDAO = feedbackDAO;
    }

    public Feedback findUser(int id) {
        return feedbackDAO.findById(id);
    }

    public void saveUser(Feedback feedback) {
        feedbackDAO.save(feedback);
    }

    public void deleteUser(Feedback feedback) {
        feedbackDAO.delete(feedback);
    }

    public void updateUser(Feedback feedback) {
        feedbackDAO.update(feedback);
    }

    public List<Feedback> findAllUsers() {
        return feedbackDAO.findAll();
    }
}

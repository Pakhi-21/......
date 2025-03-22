package com.company.ewt.service;

import com.company.ewt.entity.Employee;
import com.company.ewt.entity.Survey;
import com.company.ewt.repository.SurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class SurveyService {

    @Autowired
    private SurveyRepository surveyRepository;

    // Create a new survey (Admin only)
    public Survey createSurvey(Survey survey, Employee createdBy) {
        survey.setCreatedBy(createdBy);
        survey.setCreatedAt(LocalDateTime.now());
        survey.setUpdatedAt(LocalDateTime.now());
        return surveyRepository.save(survey);
    }

    // Fetch all surveys (Admins see all, employees see only active ones)
    public List<Survey> getAllSurveys(boolean isAdmin) {
        return isAdmin ? surveyRepository.findAll() : surveyRepository.findByStatusTrue();
    }

    // Get a single survey by ID
    public Survey getSurveyById(Long id) {
        return surveyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Survey not found"));
    }

    // Update survey (Admin only)
    public Survey updateSurvey(Long id, Survey updatedSurvey) {
        Survey survey = getSurveyById(id);
    
        if (updatedSurvey.getTitle() != null && !updatedSurvey.getTitle().isEmpty()) {
            survey.setTitle(updatedSurvey.getTitle());
        }
        if (updatedSurvey.getDescription() != null && !updatedSurvey.getDescription().isEmpty()) {
            survey.setDescription(updatedSurvey.getDescription());
        }
        if (updatedSurvey.getQuestions() != null && !updatedSurvey.getQuestions().isEmpty()) {
            survey.setQuestions(updatedSurvey.getQuestions());
        }
        if (updatedSurvey.getStartDate() != null) {
            survey.setStartDate(updatedSurvey.getStartDate());
        }
        if (updatedSurvey.getEndDate() != null) {
            survey.setEndDate(updatedSurvey.getEndDate());
        }
        survey.setStatus(updatedSurvey.isStatus());
        survey.setUpdatedAt(LocalDateTime.now()); 
        return surveyRepository.save(survey);
    }
    
    
    // Delete survey (Admin only)
    public void deleteSurvey(Long id) {
        surveyRepository.deleteById(id);
    }
}

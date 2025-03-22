package com.company.ewt.controller;

import com.company.ewt.entity.Employee;
import com.company.ewt.entity.Survey;
import com.company.ewt.service.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/surveys")
@CrossOrigin(origins = "http://localhost:3000")
public class AdminSurveyController {

    @Autowired
    private SurveyService surveyService;

    // Create a new survey (Admin only)
    @PostMapping
    public ResponseEntity<Survey> createSurvey(@RequestBody Survey survey, @RequestAttribute("admin") Employee admin) {
        return ResponseEntity.ok(surveyService.createSurvey(survey, admin));
    }

    // Get all surveys (Admins can see all surveys)
    @GetMapping
    public ResponseEntity<List<Survey>> getAllSurveys() {
        return ResponseEntity.ok(surveyService.getAllSurveys(true));
    }

    // Get a specific survey by ID
    @GetMapping("/{id}")
    public ResponseEntity<Survey> getSurveyById(@PathVariable Long id) {
        return ResponseEntity.ok(surveyService.getSurveyById(id));
    }

    // Update an existing survey (Admin only)
    @PutMapping("/{id}")
    public ResponseEntity<Survey> updateSurvey(@PathVariable Long id, @RequestBody Survey survey) {
        return ResponseEntity.ok(surveyService.updateSurvey(id, survey));
    }

    // Delete a survey (Admin only)
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSurvey(@PathVariable Long id) {
        surveyService.deleteSurvey(id);
        return ResponseEntity.ok("Survey deleted successfully");
    }
}

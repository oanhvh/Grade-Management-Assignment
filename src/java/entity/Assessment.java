/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

public class Assessment {
    private int assessmentId;
    private int courseId;
    private String category;
    private String type;
    private String part;
    private double weight;
    private String completionCriteria;
    private String duration;
    private String questionType;
    private String noQuestion;
    private String knowledgeAndSkill;
    private String gradingGuide;
    private String note;

    public Assessment() {
    }

    public Assessment(int assessmentId, int courseId, String category, String type, String part, double weight,
            String completionCriteria, String duration, String questionType, String noQuestion, String knowledgeAndSkill, String gradingGuide, String note) {
        this.assessmentId = assessmentId;
        this.courseId = courseId;
        this.category = category;
        this.type = type;
        this.part = part;
        this.weight = weight;
        this.completionCriteria = completionCriteria;
        this.duration = duration;
        this.questionType = questionType;
        this.noQuestion = noQuestion;
        this.knowledgeAndSkill = knowledgeAndSkill;
        this.gradingGuide = gradingGuide;
        this.note = note;
    }

    public int getAssessmentId() {
        return assessmentId;
    }

    public void setAssessmentId(int assessmentId) {
        this.assessmentId = assessmentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

   

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPart() {
        return part;
    }

    public void setPart(String part) {
        this.part = part;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getCompletionCriteria() {
        return completionCriteria;
    }

    public void setCompletionCriteria(String completionCriteria) {
        this.completionCriteria = completionCriteria;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

 

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    public String getNoQuestion() {
        return noQuestion;
    }

    public void setNoQuestion(String noQuestion) {
        this.noQuestion = noQuestion;
    }

    public String getKnowledgeAndSkill() {
        return knowledgeAndSkill;
    }

    public void setKnowledgeAndSkill(String knowledgeAndSkill) {
        this.knowledgeAndSkill = knowledgeAndSkill;
    }

    public String getGradingGuide() {
        return gradingGuide;
    }

    public void setGradingGuide(String gradingGuide) {
        this.gradingGuide = gradingGuide;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
    
    
}

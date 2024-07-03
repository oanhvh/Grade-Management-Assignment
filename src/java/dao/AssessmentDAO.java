/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.Assessment;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class AssessmentDAO {

    public List<Assessment> getAssessmentsByCourseId(int courseId) {
        List<Assessment> assessments = new ArrayList<>();
        String sql = "SELECT * FROM Assessments WHERE CourseId = ?";
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection1();
            if (conn != null) {
                ptm = conn.prepareStatement(sql);
                ptm.setInt(1, courseId);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    int assessmentId = rs.getInt("AssessmentId");
                    String category = rs.getString("Category");
                    String type = rs.getString("Type");
                    String part = rs.getString("Part");
                    double weight = rs.getDouble("Weight");
                    String completionCriteria = rs.getString("CompletionCriteria");
                    String duration = rs.getString("Duration");
                    String questionType = rs.getString("QuestionType");
                    String noQuestion = rs.getString("NoQuestion");
                    String knowledgeAndSkill = rs.getString("KnowledgeAndSkill");
                    String gradingGuide = rs.getString("GradingGuide");
                    String note = rs.getString("Note");

                    Assessment assessment = new Assessment(assessmentId, courseId, category, type, part, weight,
                            completionCriteria, duration, questionType, noQuestion, knowledgeAndSkill, gradingGuide, note);
                    assessments.add(assessment);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (ptm != null) {
                try {
                    ptm.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return assessments;
    }
}

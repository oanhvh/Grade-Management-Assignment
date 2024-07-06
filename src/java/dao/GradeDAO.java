/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.Grade;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;


public class GradeDAO {
    public void insertGrade(int studentClassId, int assessmentId, double score) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO Grades (StudentClassId, AssessmentId, Score) VALUES (?, ?, ?)";
        
        try (Connection conn = DBUtils.getConnection1();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, studentClassId);
            pstmt.setInt(2, assessmentId);
            pstmt.setDouble(3, score);
            pstmt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            throw e;
        }
    }
    
    public boolean updateGrade(int studentClassId, int assessmentId, double score) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE Grades SET Score = ? WHERE StudentClassId = ? AND AssessmentId = ?";
        
        try (Connection conn = DBUtils.getConnection1();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDouble(1, score);
            pstmt.setInt(2, studentClassId);
            pstmt.setInt(3, assessmentId);
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            throw e;
        }
    }
    
    public Map<String, Map<String, Double>> getGradesByClassAndCourse(int classId, int courseId) throws SQLException, ClassNotFoundException {
        Map<String, Map<String, Double>> studentGrades = new HashMap<>();
        String sql = "SELECT sc.StudentClassId, g.AssessmentId, g.Score " +
                     "FROM Grades g " +
                     "JOIN StudentClasses sc ON g.StudentClassId = sc.StudentClassId " +
                     "JOIN Classes c ON sc.ClassId = c.ClassId " +
                     "WHERE c.ClassId = ? AND c.CourseId = ?";

        try (Connection conn = DBUtils.getConnection1();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, classId);
            pstmt.setInt(2, courseId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    String studentClassId = rs.getString("StudentClassId");
                    String assessmentId = rs.getString("AssessmentId");
                    double score = rs.getDouble("Score");

                    studentGrades
                        .computeIfAbsent(studentClassId, k -> new HashMap<>())
                        .put(assessmentId, score);
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            throw e;
        }

        return studentGrades;
    }
    public static void main(String[] args) {
        GradeDAO d = new GradeDAO();
        try {
            System.out.println(d.getGradesByClassAndCourse(1, 1));
        } catch (SQLException ex) {
            Logger.getLogger(GradeDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GradeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public List<StudentCourseInfo> getStudentGrade(int studentId) throws SQLException, ClassNotFoundException {
        List<StudentCourseInfo> studentGrades = new ArrayList<>();
        String sql = "SELECT ROW_NUMBER() OVER (ORDER BY c.Semester, c.Year, co.CourseCode) AS NO, "
                + "CONCAT_WS(' ', c.Semester, c.Year) AS SEMESTER, "
                + "co.CourseCode AS SUBJECT_CODE, "
                + "co.CourseName AS SUBJECT_NAME, "
                + "g.Score AS GRADE, "
                + "CASE WHEN g.Score >= 5.0 THEN 'Passed' ELSE 'Not Passed' END AS STATUS "
                + "FROM Grades g "
                + "JOIN StudentClasses sc ON g.StudentClassId = sc.StudentClassId "
                + "JOIN Classes c ON sc.ClassId = c.ClassId "
                + "JOIN Courses co ON c.CourseId = co.CourseId "
                + "WHERE sc.StudentId = ? "
                + "ORDER BY c.Year, c.Semester, co.CourseCode";

        try (Connection conn = DBUtils.getConnection1(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, studentId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    int no = rs.getInt("NO");
                    String semester = rs.getString("SEMESTER");
                    String subjectCode = rs.getString("SUBJECT_CODE");
                    String subjectName = rs.getString("SUBJECT_NAME");
                    double grade = rs.getDouble("GRADE");
                    String status = rs.getString("STATUS");

                    StudentCourseInfo info = new StudentCourseInfo(no, semester, subjectCode, subjectName, grade, status);
                    studentGrades.add(info);
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            throw e;
        }

        return studentGrades;
    }
    
    public class StudentCourseInfo {
    private int no;
    private String semester;
    private String subjectCode;
    private String subjectName;
    private double grade;
    private String status;

    public StudentCourseInfo() {
    }

    public StudentCourseInfo(int no, String semester, String subjectCode, String subjectName, double grade, String status) {
        this.no = no;
        this.semester = semester;
        this.subjectCode = subjectCode;
        this.subjectName = subjectName;
        this.grade = grade;
        this.status = status;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
}

}

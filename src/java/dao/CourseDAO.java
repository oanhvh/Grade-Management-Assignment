/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.Course;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class CourseDAO {

    public List<Course> findCoursesByCodeAndInstructorId(String courseCode, int instructorId) throws SQLException {
        List<Course> courses = new ArrayList<>();
        String sql = "SELECT c.CourseId, CourseName, CourseCode, i.InstructorId FROM Courses c INNER JOIN InstructorCourse i"
                + " ON c.CourseId = i.CourseId WHERE CourseCode = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection1();
            if (conn != null) {
                if (instructorId != 0) {
                    sql += " AND i.InstructorId = ?";

                }
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, courseCode);
                if (instructorId != 0) {
                    pstmt.setInt(2, instructorId);
                }
                rs = pstmt.executeQuery();

                while (rs.next()) {
                    int courseId = rs.getInt("CourseId");
                    String courseName = rs.getString("CourseName");
                    String code = rs.getString("CourseCode");
                    int retrievedInstructorId = rs.getInt("InstructorId");
                    Course course = new Course(courseId, courseName, code, retrievedInstructorId);
                    courses.add(course);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CourseDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return courses;
    }

    public Course getCourseById(int courseId) throws SQLException {
        Course course = null;
        String sql = "SELECT CourseId, CourseName, CourseCode FROM Courses WHERE CourseId = ?";
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection1();
            if (conn != null) {
                ptm = conn.prepareStatement(sql);
                ptm.setInt(1, courseId);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    String courseName = rs.getString("CourseName");
                    String courseCode = rs.getString("CourseCode");
                    course = new Course(courseId, courseName, courseCode, 0);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return course;
    }
}

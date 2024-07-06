/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dto.ClassDTO;
import entity.Course;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClassDAO {

    public List<ClassDTO> findAllClassByInstructorId(int instructorId) throws SQLException {
        List<ClassDTO> classeses = new ArrayList<>();
        String sql = "SELECT c.ClassId, c.CourseId, c.Name, c.InstructorId, c.Semester, c.Year, cr.CourseName, cr.CourseCode\n"
                + "FROM Classes c\n"
                + "JOIN Courses cr ON c.CourseId = cr.CourseId\n"
                + "JOIN InstructorCourse ic ON c.CourseId = ic.CourseId\n"
                + "WHERE ic.InstructorId = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection1();
            if (conn != null) {
                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, instructorId);
                rs = pstmt.executeQuery();

                while (rs.next()) {
                    int classId = rs.getInt("ClassId");
                    int courseId = rs.getInt("CourseId");
                    int retrievedInstructorId = rs.getInt("InstructorId");
                    String semester = rs.getString("Semester");
                    int year = rs.getInt("Year");
                    String courseName = rs.getString("CourseName");
                    String name = rs.getString("Name");
                    String courseCode = rs.getString("CourseCode");

                    ClassDTO classes = new ClassDTO(classId, courseId,name, retrievedInstructorId, semester, year, courseName, courseCode);
                    classeses.add(classes);
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
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

        return classeses;
    }
    public static void main(String[] args) {
        try {
            ClassDAO d = new ClassDAO();
            System.out.println(d.findAllClassByInstructorId(2));
        } catch (SQLException ex) {
            Logger.getLogger(ClassDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

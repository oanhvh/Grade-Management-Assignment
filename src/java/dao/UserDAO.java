/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dto.StudentCourseInfo;
import entity.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class UserDAO {

    public User checkLogin(String username, String password) throws SQLException {
        User user = null;
        String sql = "SELECT UserId, Username, FullName, Email, RoleId FROM Users WHERE Username = ? AND Password = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection1();
            if (conn != null) {
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, username);
                pstmt.setString(2, password);
                rs = pstmt.executeQuery();

                if (rs.next()) {
                    int userId = rs.getInt("UserId");
                    String fullName = rs.getString("FullName");
                    String email = rs.getString("Email");
                    int roleId = rs.getInt("RoleId");

                    user = new User(userId, username, fullName, email, roleId);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
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

        return user;
    }

    public List<StudentCourseInfo> getStudentCourseInfo(int studentId) throws SQLException, ClassNotFoundException {
        List<StudentCourseInfo> studentCourseInfoList = new ArrayList<>();
        String sql = "SELECT ROW_NUMBER() OVER (ORDER BY c.Semester, c.Year, co.CourseCode) AS NO, "
                + "CONCAT(c.Semester, c.Year) AS SEMESTER, "
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
                    studentCourseInfoList.add(info);
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            throw e;
        }

        return studentCourseInfoList;
    }

}

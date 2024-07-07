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
        String sql = "SELECT \n"
                + "    ROW_NUMBER() OVER (ORDER BY c.Semester, c.Year, co.CourseCode) AS NO,\n"
                + "    CONCAT_WS(' ', c.Semester, c.Year) AS SEMESTER, \n"
                + "    co.CourseCode AS SUBJECT_CODE,\n"
                + "    co.CourseName AS SUBJECT_NAME,\n"
                + "    ROUND(AVG(g.Score * a.Weight) / AVG(a.Weight), 2) AS GRADE, \n"
                + "    CASE \n"
                + "        WHEN ROUND(AVG(g.Score * a.Weight) / AVG(a.Weight), 2) >= 5.0 THEN 'Passed' \n"
                + "        ELSE 'Not Passed' \n"
                + "    END AS STATUS \n"
                + "FROM \n"
                + "    Grades g \n"
                + "JOIN \n"
                + "    StudentClasses sc ON g.StudentClassId = sc.StudentClassId \n"
                + "JOIN \n"
                + "    Classes c ON sc.ClassId = c.ClassId \n"
                + "JOIN \n"
                + "    Courses co ON c.CourseId = co.CourseId \n"
                + "JOIN \n"
                + "    Assessments a ON g.AssessmentId = a.AssessmentId\n"
                + "WHERE \n"
                + "    sc.StudentId = ?\n"
                + "GROUP BY \n"
                + "    c.Semester, c.Year, co.CourseCode, co.CourseName\n"
                + "ORDER BY \n"
                + "    c.Year, c.Semester, co.CourseCode;";

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

    public List<User> getStudentsByClassId(int classId) throws SQLException, ClassNotFoundException {
        List<User> students = new ArrayList<>();
        String sql = "SELECT sc.StudentClassId, u.UserId, u.Username, u.RollNumber, u.FullName, u.Email, u.RoleId "
                   + "FROM StudentClasses sc "
                   + "JOIN Users u ON sc.StudentId = u.UserId "
                   + "WHERE sc.ClassId = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection1();
            if (conn != null) {
                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, classId);
                rs = pstmt.executeQuery();

                while (rs.next()) {
                    int userId = rs.getInt("UserId");
                    int studentClassId = rs.getInt("StudentClassId");
                    String username = rs.getString("Username");
                    String rollNumber = rs.getString("RollNumber");
                    String fullName = rs.getString("FullName");
                    String email = rs.getString("Email");
                    int roleId = rs.getInt("RoleId");

                    User user = new User(userId, studentClassId, username, rollNumber, fullName, email, roleId);
                    students.add(user);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
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

        return students;
    }
}

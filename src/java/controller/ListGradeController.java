/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.AssessmentDAO;
import dao.ClassDAO;
import dao.GradeDAO;
import dao.UserDAO;
import dto.ClassDTO;
import entity.Assessment;
import entity.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ListGradeController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String classCourse = request.getParameter("class");
        if (classCourse != null && !classCourse.isEmpty()) {
            String[] parts = classCourse.split("-");
            if (parts.length == 2) {
                try {
                    String classId = parts[0];
                    String courseId = parts[1];

                    UserDAO dao = new UserDAO();
                    List<User> students = dao.getStudentsByClassId(Integer.parseInt(classId));

                    AssessmentDAO adao = new AssessmentDAO();
                    List<Assessment> assessments = adao.getAssessmentsByCourseId(Integer.parseInt(courseId));

                    GradeDAO gdao = new GradeDAO();
                    Map<String, Map<String, Double>> studentGrades = gdao.getGradesByClassAndCourse(Integer.parseInt(classId), Integer.parseInt(courseId));
                    
                    ClassDAO cdao = new ClassDAO();
                    List<ClassDTO> classeses = new ArrayList<>();
                    HttpSession session = request.getSession();
                    User user = (User) session.getAttribute("account");
                    classeses = cdao.findAllClassByInstructorId(user.getUserId());
                    request.setAttribute("classes", classeses);
                    request.setAttribute("students", students);
                    request.setAttribute("assessments", assessments);
                    request.setAttribute("studentGrades", studentGrades);
                    request.getRequestDispatcher("add_grade.jsp").forward(request, response);
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(ListGradeController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                System.out.println("Invalid format for classCourse parameter.");
            }
        } else {
            System.out.println("classCourse parameter is missing or empty.");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

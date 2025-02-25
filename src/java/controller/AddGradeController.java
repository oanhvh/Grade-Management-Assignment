/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.ClassDAO;
import dao.GradeDAO;
import dto.ClassDTO;
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


public class AddGradeController extends HttpServlet {

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
        try {
            ClassDAO dao = new ClassDAO();
            List<ClassDTO> classeses = new ArrayList<>();
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("account");
            classeses = dao.findAllClassByInstructorId(user.getUserId());
            request.setAttribute("classes", classeses);
            request.getRequestDispatcher("add_grade.jsp").forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(AddGradeController.class.getName()).log(Level.SEVERE, null, ex);
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
        Map<String, String[]> parameterMap = request.getParameterMap();
        GradeDAO gradeDAO = new GradeDAO();

        for (String studentClassId : parameterMap.keySet()) {
            if (studentClassId.startsWith("grades[")) {
                int startIndex = studentClassId.indexOf("[") + 1;
                int endIndex = studentClassId.indexOf("]");

                int secondStartIndex = studentClassId.indexOf("[", endIndex) + 1;
                int secondEndIndex = studentClassId.indexOf("]", secondStartIndex);

                String studentId = studentClassId.substring(startIndex, endIndex);
                String assessmentId = studentClassId.substring(secondStartIndex, secondEndIndex);

                String scoreStr = request.getParameter(studentClassId);
                if (scoreStr != null && !scoreStr.trim().isEmpty()) {
                    double score = Double.parseDouble(scoreStr.trim());

                    try {
                        int studentIdInt = Integer.parseInt(studentId);
                        int assessmentIdInt = Integer.parseInt(assessmentId);

                        if (!gradeDAO.updateGrade(studentIdInt, assessmentIdInt, score)) {
                            gradeDAO.insertGrade(studentIdInt, assessmentIdInt, score);

                        }
                    } catch (NumberFormatException | SQLException | ClassNotFoundException ex) {
                        Logger.getLogger(AddGradeController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }

        response.sendRedirect("list_course.jsp");
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

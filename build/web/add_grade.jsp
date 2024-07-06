
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Syllabus Management</title>
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
        <link href="styles.css" rel="stylesheet">
    </head>
    <body>
        <!-- Header -->
        <header class="bg-dark text-white py-3">
            <div class="container">
                <div class="row align-items-center">
                    <div class="col-md-6">
                        <h1 class="h2">Student Grade Management System</h1>
                    </div>
                    <div class="col-md-6 text-md-right">
                        <nav>
                            <ul class="list-inline mb-0">
                                <li class="list-inline-item"><a href="#" class="text-white">Home</a></li>
                                    <c:if test="${sessionScope['account']!=null}">
                                        <c:if test="${sessionScope['account'].roleId == 3}">
                                        <li class="list-inline-item"><a href="studentGrade" class="text-white">Grade</a></li>
                                        </c:if>
                                        <c:if test="${sessionScope['account'].roleId == 2}">
                                        <li class="list-inline-item"><a href="AddGrade" class="text-white">Grade</a></li>
                                        </c:if>
                                    <li class="list-inline-item"><a href="list_course.jsp" class="text-white">Course</a></li>

                                    <li class="list-inline-item"><a href="#" class="text-white">Hello, ${sessionScope['account'].fullName}</a></li>

                                    <li class="list-inline-item"><a href="logout" class="text-white">Logout</a></li>

                                </c:if>
                            </ul>
                        </nav>
                    </div>
                </div>
            </div>
        </header>
        <!-- Main Content -->
        <div class="container mt-5">

            <div class="container">
                <form action="ListGrade" method="get">
                    <select id="classSelect" name="class" onchange="this.form.submit()">
                        <option selected disabled>Select Class</option>
                        <c:forEach var="o" items="${classes}">
                            <option value="${o.classId}-${o.courseId}">${o.name} - ${o.courseCode}</option>
                        </c:forEach>
                    </select>
                </form>

                <c:if test="${not empty students and not empty assessments}">
                    <form action="addgrade" method="post">
                        <div class="scrollable-table">
                            <table>
                                <thead>
                                    <tr>
                                        <th>Roll</th>
                                        <th>Name</th>
                                            <c:forEach var="assessment" items="${assessments}">
                                            <th>${assessment.category}</th>
                                            </c:forEach>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="student" items="${students}">
                                        <tr>
                                            <td>${student.rollNumber}</td>
                                            <td class="name">${student.fullName}</td>
                                            <c:forEach var="assessment" items="${assessments}">
                                                <td>
                                                    <c:set var="studentClassId" value="${student.studentClassId}" />
                                                    <c:set var="assessmentId" value="${assessment.assessmentId}" />

                                                    <c:set var="score" value="" />
                                                    <c:forEach var="entry" items="${studentGrades}">
                                                        <c:if test="${entry.key == studentClassId}">
                                                            <c:set var="studentGradesMap" value="${entry.value}" />
                                                        </c:if>
                                                    </c:forEach>

                                                    <c:forEach var="entry" items="${studentGradesMap}">
                                                        <c:if test="${entry.key == assessmentId}">
                                                            <c:set var="score" value="${entry.value}" />
                                                        </c:if>
                                                    </c:forEach>

                                                    <input style="width: 8.5rem" min="0" max="10" type="number" name="grades[${studentClassId}][${assessmentId}]" value="${score}" required>

                                                </td>
                                            </c:forEach>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                        <div class="form-container">
                            <input type="submit" value="Submit">
                        </div>
                    </form>

                </c:if>
                <c:if test="${empty students or empty assessments}">
                    <p>Không tìm thấy kết quả nào</p>
                </c:if>
            </div>
        </div>
        <!-- Footer -->
        <footer class="bg-dark text-white py-3">
            <div class="container text-center">
                <p class="mb-0">&copy; 2024 Syllabus Management System. All rights reserved.</p>
            </div>
        </footer>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </body>
</html>
<style>
    select {
        padding: 0.5rem;
        border: 1px solid #ccc;
        border-radius: 4px;
        font-size: 1rem;
        background-color: #fff;
        width: 200px;
    }
    table {
        width: 100%;
        margin: 20px 0;
        border-collapse: collapse;
        table-layout: fixed;
    }
    table, th, td {
        border: 1px solid #ddd;
    }
    th, td {
        padding: 8px;
        text-align: left;
    }
    th {
        background-color: #333;
        color: white;
        position: sticky;
        top: 0;
        z-index: 1;
    }
    tr:nth-child(even) {
        background-color: #f2f2f2;
    }
    .form-container {
        display: flex;
        justify-content: center;
        align-items: center;
        flex-direction: column;
        padding: 20px;
    }
    .form-container input[type="submit"] {
        background-color: #4CAF50;
        color: white;
        border: none;
        padding: 10px 20px;
        cursor: pointer;
        border-radius: 4px;
        font-size: 16px;
    }
    .form-container input[type="submit"]:hover {
        background-color: #45a049;
    }
    .scrollable-table {
        max-height: 400px;
        overflow-y: auto;
    }
    th, td {
        white-space: nowrap;
        overflow: hidden;
        text-overflow: ellipsis;
    }
    td.name {
        max-width: 200px;
    }
    body {
        background-color: #f8f9fa;
    }

    h2 {
        color: #343a40;
    }

    .form-inline {
        justify-content: center;
    }

    .table {
        margin-top: 20px;
    }

    header {
        margin-bottom: 20px;
    }

    footer {
        margin-top: 20px;
    }
</style>

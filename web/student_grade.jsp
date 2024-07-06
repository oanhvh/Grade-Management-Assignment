
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Student Courses</title>
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
        <link href="styles.css" rel="stylesheet">
    </head>
    <body>
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
        <div class="container mt-5">
            <h2>Student Grade</h2>
            <table class="table table-bordered">
                <thead class="thead-light">
                    <tr>
                        <th>NO</th>
                        <th>SEMESTER</th>
                        <th>SUBJECT CODE</th>
                        <th>SUBJECT NAME</th>
                        <th>GRADE</th>
                        <th>STATUS</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="o" items="${grades}">
                        <tr>
                            <td>${o.no}</td>
                            <td>${o.semester}</td>
                            <td>${o.subjectCode}</td>
                            <td>${o.subjectName}</td>
                            <td>${o.grade}</td>
                            <td>
                                <span class="badge ${o.status == 'Passed' ? 'badge-success' : 'badge-danger'}">${o.status}</span>
                            </td>
                        </tr>
                    </c:forEach>


                </tbody>
            </table>
        </div>
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
    body {
        background-color: #f8f9fa;
    }
    .table tbody tr:nth-child(odd) {
        background-color: #f2f2f2;
    }
    h2 {
        color: #343a40;
        margin-bottom: 20px;
    }

    .table th, .table td {
        vertical-align: middle !important;
    }

    .badge-success {
        background-color: #28a745;
    }

</style>

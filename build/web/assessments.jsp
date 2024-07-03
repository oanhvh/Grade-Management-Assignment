
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Assessment Details</title>
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
            <h2>Assessment Details</h2>
            <table class="table table-bordered mt-3">
                <tbody>
                    <tr>
                        <th>Syllabus ID:</th>
                        <td>${course.courseId}</td>
                    </tr>
                    <tr>
                        <th>Syllabus Code:</th>
                        <td>${course.courseCode}</td>
                    </tr>
                    <tr>
                        <th>Syllabus Name:</th>
                        <td>${course.courseName}</td>
                    </tr>
                </tbody>
            </table>

            <div class="alert alert-info">
                <strong>${total} assessment(s) found</strong>
            </div>
            <table class="table table-bordered">
                <thead class="thead-light">
                    <tr>
                        <th>Category</th>
                        <th>Type</th>
                        <th>Part</th>
                        <th>Weight</th>
                        <th>Completion Criteria</th>
                        <th>Duration</th>
                        <th>Question Type</th>
                        <th>No Question</th>
                        <th>Knowledge and Skill</th>
                        <th>Grading Guide</th>
                        <th>Note</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="o" items="${assessments}">
                        <tr>
                            <td>${o.category}</td>
                            <td>${o.type}</td>
                            <td>${o.part}</td>
                            <td>${o.weight} %</td>
                            <td>${o.completionCriteria}</td>
                            <td>${o.duration}</td>
                            <td>${o.questionType}</td>
                            <td>${o.noQuestion}</td>
                            <td>${o.knowledgeAndSkill}</td>
                            <td>${o.gradingGuide}</td>
                            <td>${o.note}</td>
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
    }

    .alert-info {
        margin-top: 20px;
    }

    .table {
        margin-top: 20px;
    }

    thead.thead-light th {
        background-color: #f8f9fa;
    }

    table th, table td {
        vertical-align: middle !important;
    }

</style>
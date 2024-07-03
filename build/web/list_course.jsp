
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
            <h2>Syllabus Management</h2>
            <form action="course" method="GET" class="form-inline my-3">
                <label for="subjectCode" class="mr-2">Search by subject:</label>
                <select class="form-control mr-2" id="subjectCode">
                    <option>Code</option>
                </select>
                <input name="code" type="text" class="form-control mr-2" placeholder="Input the code" aria-label="Search">
                <button type="submit" class="btn btn-success">Search</button>
            </form>
            <div class="alert alert-info">
                <strong>${total} syllabus(es) found</strong>
            </div>
            <table class="table table-bordered">
                <thead class="thead-light">
                    <tr>
                        <th>Syllabus ID</th>
                        <th>Subject Code</th>
                        <th>Subject Name</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="o" items="${courses}">
                        <tr>
                            <td>${o.courseId}</td>
                            <td>${o.courseCode}</td>
                            <td><a href="assessment?courseId=${o.courseId}">${o.courseName}</a></td>
                        </tr>
                    </c:forEach>

                </tbody>
            </table>
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

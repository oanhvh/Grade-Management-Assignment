
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

            <div class="container">
                <form action="submit_grades.php" method="post">
                    <div class="scrollable-table">
                        <table>
                            <thead>
                                <tr>
                                    <th>Roll</th>
                                    <th>Name</th>
                                    <th>Final Project Presentation Rest</th>
                                    <th>Implementation (Report 7)</th>
                                    <th>Project Introduction</th>
                                    <th>Project Management Plan</th>
                                    <th>Software Design (Report 4)</th>
                                    <th>Software Requirement</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>HE161216</td>
                                    <td class="name">Trịnh Anh Đức</td>
                                    <td><input type="number" name="grades[HE161216][final_project]" required></td>
                                    <td><input type="number" name="grades[HE161216][implementation]" required></td>
                                    <td><input type="number" name="grades[HE161216][project_intro]" required></td>
                                    <td><input type="number" name="grades[HE161216][project_management]" required></td>
                                    <td><input type="number" name="grades[HE161216][software_design]" required></td>
                                    <td><input type="number" name="grades[HE161216][software_requirement]" required></td>
                                </tr>

                            </tbody>
                        </table>
                    </div>
                    <div class="form-container">
                        <input type="submit" value="Submit Grades">
                    </div>
                </form>
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

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
  <title>Resume list</title>
</head>
<body>
<table>
  <thead>
  <tr>
    <td>position</td>
    <td>salary</td>
    <td>aboutMe</td>
  </tr>
  </thead>
  <tbody>
  <c:forEach var="resume" items="${resumes}">
    <tr>
      <td><c:out value="${resume.position}"/></td>
      <td><c:out value="${resume.salary}"/></td>
      <td><c:out value="${resume.aboutMe}"/></td>
    </tr>
  </c:forEach>
  </tbody>
</table>
</body>
</html>

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
    <td>photo</td>
    <td>position</td>
    <td>salary</td>
  </tr>
  </thead>
  <tbody>
  <c:forEach var="resume" items="${resumes}">
    <tr>
      <td><img src="<c:out value="${resume.photoSrc}"/>" style="height: 50px; width: auto"></td>
      <td><c:out value="${resume.position}"/></td>
      <td><c:out value="${resume.salary}"/></td>
    </tr>
  </c:forEach>
  </tbody>
</table>
</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
  <title>Резюме</title>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
</head>
<body>
<div class="container">
  <div class="row">
    <div class="col-md-12">
      <div class="well well-sm">
        <div class="row">
          <div class="col-md-4">
            <img src="${resume.photoSrc}" alt="" class="img-rounded img-responsive"/>
          </div>
          <div class="col-md-8">
            <h4>${resume.position}</h4>
            <small>${resume.gender}</small>
            <br/>
            <p>
              <fmt:setLocale value="ru-RU" scope="session"/>
              <i class="glyphicon glyphicon-gift"></i><fmt:formatDate value = "${resume.birthday}" dateStyle="full"/>
            </p>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>

</body>
</html>

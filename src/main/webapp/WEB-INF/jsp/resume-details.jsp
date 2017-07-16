<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title>Резюме</title>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
  <link rel="icon" type="image/png" href="/img/favicon.ico"/>
</head>
<body>
<div class="container">
  <div class="row">
    <div class="col-md-12">
      <div class="well well-sm">
        <div class="row">
          <div class="col-md-3" align="center">
            <img src="${resume.photoSrc}" alt="" class="img-rounded img-responsive"/>
          </div>
          <div class="col-md-9">
            <h3>${resume.position}</h3>
            <small>${resume.gender}</small>
            <br/>
            <p>
              <fmt:setLocale value="ru-RU" scope="session"/>
              <i class="glyphicon glyphicon-gift"></i> <fmt:formatDate value="${resume.birthday}" dateStyle="full"/>
              <br>
              ${resume.address}
            </p>

            <h2>${resume.salary}</h2>
          </div>
        </div>
        <div class="row">
          <div class="col-md-12">
            <h4>Обо мне</h4>
            <div class="col-md-12">
              ${resume.aboutMe}
            </div>
          </div>
        </div>
        <div class="row">
          <div class="col-md-12">
            <h4>Обыт работы</h4>
            <c:forEach var="experience" items="${resume.experienceList}">
              <div class="col-md-2">
                  ${experience.duration}
              </div>
              <div class="col-md-10">
                <p>
                  <b>${experience.company}</b>
                  <br>${experience.address} <a href="${experience.url}">${experience.url}</a>
                  <br>
                  <b>${experience.position}</b>
                  <br>${experience.description}
                </p>
              </div>
            </c:forEach>
          </div>
        </div>
        <div class="row">
          <div class="col-md-12">
            <h4>Высшее образование</h4>
            <c:forEach var="education" items="${resume.educationList}">
              <div class="col-md-2">
                  ${education.year}
              </div>
              <div class="col-md-10">
                <p>
                  <b>${education.name}</b>
                  <br>${education.type}
                </p>
              </div>
            </c:forEach>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>

</body>
</html>

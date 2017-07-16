<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
  <title>Список резюме</title>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
  <link href="<c:url value='/static/css/app.css' />" rel="stylesheet">
</head>
<body ng-app="myApp" class="ng-cloak" ng-controller="ResumeController as ctrl">

<nav class="navbar navbar-default">
  <div class="container">

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <form ng-submit="ctrl.search()" class="navbar-form navbar-left">
        <div class="form-group">
          <input type="text" ng-model="ctrl.position_search" class="form-control" placeholder="Поиск" size="100">
        </div>
        <button type="submit" class="btn btn-primary">
          <span class="glyphicon glyphicon-search" aria-hidden="true"></span>
        </button>
      </form>
      <form ng-submit="ctrl.refreshDb()" class="navbar-form navbar-right">
        <button type="submit" class="btn btn-primary">
        <span class="glyphicon glyphicon-refresh" aria-hidden="true"></span> Обновить
      </button>
      </form>
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>


<div class="container">
  <div class="panel panel-default">
    <!-- Default panel contents -->
    <div class="panel-heading"><span class="lead">Список Резюме </span></div>

    <div class="spinner" ng-show="loading" align="center">
      <img src="img/loading.gif"/>
    </div>

    <div class="tablecontainer">
      <table class="table table-hover">
        <tbody>
        <tr ng-repeat="resume in ctrl.resumes">
          <td align="center">
              <img ng-src="{{resume.photoSrc ? resume.photoSrc : 'img/default_image.png'}}"
                   style="max-height: 50px; width: auto"/>
          </td>
          <td>
            <a href="/resume/{{resume.id}}">
              <span ng-bind="resume.position"></span>
            </a>
          </td>
          <td><span ng-bind="resume.salary"></span></td>
        </tr>
        </tbody>
      </table>
    </div>
  </div>
</div>


<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular.js"></script>
<script src="<c:url value='/static/js/app.js' />"></script>
<script src="<c:url value='/static/js/service/resume_service.js' />"></script>
<script src="<c:url value='/static/js/controller/resume_controller.js' />"></script>
</body>
</html>

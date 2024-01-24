<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그아웃</title>
<link href="./css/index.css" rel="stylesheet" />
<link href="./css/menu.css" rel="stylesheet" />
<script type="text/javascript" src="./js/menu.js"></script>
</head>
<body>
   <div class="container">
      <header>
         <jsp:include page="menu.jsp"></jsp:include>
      </header>
      <div class="main">
         <div class="mainStyle">
            <article>
               로그아웃하셨소
            </article>
         </div>
      </div>
      <footer>
      <c:import url="footer.jsp"/>
      </footer>
   </div>
</body>
</html>
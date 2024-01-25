<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>음료 선택</title>
<link href="./css/menu.css" rel="stylesheet">
<link href="./css/index.css" rel="stylesheet">
<script type="text/javascript" src="./js/menu.js"></script>
</head>
<body>
	<header>
		<%@ include file="menu.jsp" %>
	</header>
	 <div>
		<div>
		 <article>
			<h1>음료 투표</h1>
				<ul>
					<li>상세보기</li>
					<li>각각 게시판 서블릿, jsp</li>
					<li>글쓰기</li>
					<li>삭제하기</li>
					<li>수정하기</li>
				</ul>				
			</article>
		</div>
	</div>
	<footer>
		<c:import url="footer.jsp"/>
	</footer>
</body>
</html>
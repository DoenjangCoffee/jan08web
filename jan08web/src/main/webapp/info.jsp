<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="./css/menu.css" rel="stylesheet">
<link href="./css/index.css" rel="stylesheet">
<script type="text/javascript" src="./js/menu.js"></script>
</head>
<body>
		<header>
			<%@ include file="menu.jsp" %>
			<jsp:include page="menu.jsp"></jsp:include>
			<!-- jsp:은 출력 결과만 화면에 나온다. / 포함 되어서 컴파일 된다. -->
		</header>
		<div>
			<div>
			<article>
			<h1>index 제목입니다..</h1>
				<h1>info</h1>
				<h2>2024-01-10</h2>
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
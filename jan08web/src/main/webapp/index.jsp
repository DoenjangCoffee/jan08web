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
			<jsp:include page="menu.jsp"></jsp:include><!-- menu.jsp 결과가 다 나온후 저장? -->
			<!-- jsp:은 출력 결과만 화면에 나온다. / 포함 되어서 컴파일 된다. -->
		</header>
		<div>
			<div>
			<article>
				<h1>index 제목이비니다ㅣ이이.</h1>
				
				<c:set var="number" value="105"/>
				<c:out value="${number }"/>
				<br>
				<c:forEach begin="1" end="9" var="row" step="2">
					2 x ${row } = ${row * 2 }<br>
				</c:forEach>
				
				<c:if test="${number eq 105 }">
					number는 105입니다.<br>
				</c:if>
				<hr>
				1~45까지 짝수만 출력하기<br>
				<c:forEach begin="1" end="45" var="a">
					<c:if test="${a % 2 eq 0 }">
					${a }<br>
					</c:if>
				</c:forEach>
				
			</article>
			
			<article>
				<c:import url="menu.jsp"></c:import>
				<br>
				<c:forEach begin="1" end="10" var="row" varStatus="s">
					${s.begin }/${s.end }
					<!-- varStatus은 현재 상황을 볼 수 있다. -->
				</c:forEach>
			</article>
			
			<article>
			</article>
		</div>
	</div>
	<footer>
	<c:import url="footer.jsp"/>
	</footer>
</body>
</html>
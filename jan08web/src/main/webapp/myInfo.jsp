<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- 위에 처럼 써야지 jstl을 사용 할 수 있다. -->

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
<link href="./css/menu.css" rel="stylesheet">
<link href="./css/index.css" rel="stylesheet">
<script type="text/javascript" src="./js/menu.js"></script>
</head>
<body>
<%
if(session.getAttribute("mid") == null){
	response.sendRedirect("./login");
}
%>
	<div class="container1">
		<header><%@ include file="menu.jsp" %><!-- 먼저 menu.jsp로 가져와서 jstl실행? -->
		</header>
		<div>
			<div class="mainStyle">
				<article>
					${sessionScope.mname }님의 회원 정보입니다.
					<div>
						<form action="./myInfo" method="post" onsubmit="return check()">
							<input type="password" name="newPW" id="newPW" placeholder="변경할 암호를 입력해주세요" >
							<button type="submit">변경하기</button>
						</form>
					</div>
				</article>
				<article>
					<h2>방문흔적 찾아가기</h2>
					
					<table>
						<thead>
							<tr>
								<td>글제목</td>
								<td>읽은날짜</td>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${readData }" var="d">
							<tr>
								<td onclick ="location.href='./detail?no=${d.board_no}'">${d.board_title }</td>
								<td>
								<fmt:parseDate value="${d.vdate }"  var = "date" pattern="yyyy-MM-dd HH:mm:ss"/>
								<fmt:formatDate value="${date }" pattern="yyyy년 MM월 dd일 HH시 mm분 ss초"/>
								</td>
							</tr>
							</c:forEach>
						</tbody>
					</table>
				</article>
			</div>
		</div>
	<footer>
		<c:import url="footer.jsp"/>
	</footer>
		
		
	</div>
		<script type="text/javascript">
			function check(){
				var pw = document.querySelector("#newPW");
				
				if (pw.value.length < 5) {
					alert("비밀번호는 5글자 이상이어야 합니다.");
					return false;
				}
			}
		</script>
	
</body>
</html>
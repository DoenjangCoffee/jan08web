<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 하시오</title>
<link href="./css/index.css" rel="stylesheet" />
<link href="./css/menu.css" rel="stylesheet" />
<script type="text/javascript" src="./js/menu.js"></script>
<style type="text/css">
.login{
	margin: 0 auto;
	padding: 0;
	border: none;
	background-color: red;
	width: 300px;
	text-align: center;
	box-sizing: border-box;
}
.login input{
	width: 50%;
	height: 30px;
	text-align: center;
	margin: 10px;

}
.login button{
	width: 50%;
	height: 30px;
	color: yellow;
	font-size: large;
	margin: 5px
}
</style>
<script type="text/javascript">
function err(){
	let errBox = document.querySelector("#errorMSG");
	errBox.innerHTML = "<marquee>올바른 아아디와 비밀번호를 입력해주시오.</marquee>";
	errBox.style.color = 'yellow';
	errBox.style.fontSize = '8pt';
}
</script>
</head>
<body>
	<div class="container">
		<header>
			<jsp:include page="menu.jsp"></jsp:include>
		</header>
		<div class="main">
			<div class="mainStyle">
				<article>
					<h1>로그인 하시오</h1>
					
					<c:if test="${param.error ne null}">
						<script type="text/javascript">
							alert("올바른 아이디와 암호를 입력해주시겠소.");
						</script>
					</c:if>
					
					<div class = "login">
						<form action = "./login" method="post">
							
							<input type="text" name="id" placeholder="아이디를 입력하세요"><br>
							<input type="password" name="pw" placeholder="암호를 입력하세요"><br><br>
							<button type="submit">로그인</button>
							<button type="reset">지우기</button>
							<div id="errorMSG"></div>
						</form>
						<a href="./join">회원가입</a>
					</div>
				</article>
			</div>
		</div>
		<footer>
			<c:import url="footer.jsp"/>
		</footer>
	</div>
	<c:if test="${param.error ne null }">
		<script type="text/javascript">
			err();/* 여기서 에러 실행.[함수 호출], 입력이 다 끝난 후에 실행을 한다. */
		</script>
	</c:if>
</body>
</html>
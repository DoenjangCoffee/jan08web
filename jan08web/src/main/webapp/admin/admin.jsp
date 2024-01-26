<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 페이지</title>
<link href="../css/admin.css" rel="stylesheet"/>
<link rel="stylesheet" href="//cdn.jsdelivr.net/npm/xeicon@2.3.3/xeicon.min.css">
<script type="text/javascript" src="../js/menu.js"></script>
<!-- admin폴더에 있기 때문에 ..을 붙였다 -->
</head>
<body>
	<h1>관리자 페이지</h1>
	<div class="wrap">
		<div class="menu">
			<!-- 메뉴 -->
			<nav>
				<ul>
					<li onclick="url('./members')"><i class="xi-profile xi-2x"></i>회원관리</li>
					<li onclick="url('./board')"><i class="xi-document xi-2x"></i>게시글 관리</li>
					<li onclick="url('./comment')"><i class="xi-forum-o xi-2x"></i>댓글 관리</li>
					<li onclick="url('./info')"><i class="xi-home-o xi-1x"> monwo 님</i></li>
					<li></li>
				</ul>
			</nav>
		</div>
		<div class="main">
			<!-- 본문 -->
			<!-- 이 페이지에 오는 모든 사람은 관리자인지 검사 합니다.
			관리자인 경우 이 페이지를 보여주고 로그인 하지 않았거나 일반 사용자는 로그인 페이지로 이동한다. -->
			<article>
				<div class="info">
					왼쪽 메뉴를 선택하세요.
				</div>
			</article>
		</div>
	</div>
</body>
</html>
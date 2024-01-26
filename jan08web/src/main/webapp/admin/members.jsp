<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 페이지</title>
<link href="../css/member.css" rel="stylesheet"/>
<link rel="stylesheet" href="//cdn.jsdelivr.net/npm/xeicon@2.3.3/xeicon.min.css">
<script type="text/javascript" src="../js/menu.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js" integrity="sha512-v2CJ7UaYy4JwqLDIrZUI/4hqeoQieOmAZNXBeQyjo21dadnwR+8ZaIJVT8EE2iyI61OV8e6M8PP2/4hpQINQ/g==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<!-- admin폴더에 있기 때문에 ..을 붙였다 -->
<script type="text/javascript">
$(function(){
	$("select[name=grade]").on("change",function(){
	let val = $(this).val();
	let mno = $(this).closest("tr").children().first().text();
	// 두 값을 잡아서 가상form에 담아서 전송한다.
	let form = $('<form></form>');
	form.attr('method', 'post');
	form.attr('action', './members');
	form.append($('<input/>',{type : 'hidden', name : 'mno', value : mno}));
	form.append($('<input/>', {type : 'hidden', name : 'grade', value : val}));
	<c:if test="${param.grade ne null}">
	form.append($('<input/>', {type : 'hidden', name : 'currentgrade', value : ${param.grade}}));
	</c:if>
	form.appendTo('body');
	form.submit();
	});
});//jQuery 끝
</script>
</head>
<body>
	<h1>관리자 페이지</h1>
	<div class="wrap">
		<div class="menu">
			<!-- 메뉴 -->
			<nav>
				<div class="info">
					<ul><!-- 옆에 나오는 메뉴 -->
						<li onclick="url('./members')"><i class="xi-profile xi-2x"></i>회원관리</li>
						<li onclick="url('./board')"><i class="xi-document xi-2x"></i>게시글 관리</li>
						<li onclick="url('./comment')"><i class="xi-forum-o xi-2x"></i>댓글 관리</li>
						<li onclick="url('./info')"><i class="xi-home-o xi-1x"> monwo 님</i></li>
				</ul>
			</div>
		</nav>
	</div>
		<div class="main">
			<!-- 본문 -->
			<!-- 이 페지이에 오는 모든 사름은 관리자인지 검사 합니다.
			관리자인 경우 이 페이지를 보여주고 로그인 하지 않았거나 일반 사용자는 로그인 페이지로 이동한다. -->
			<article>
				<h2>회원관리</h2>
					<div class="member-lists">
						<ul>
							<li onclick="url('./members?grade=0')"><i class="xi-close-circle-o"></i> 강퇴</li>
							<li onclick="url('./members?grade=1')"><i class="xi-minus-circle-o"></i> 탈퇴</li>
							<li onclick="url('./members?grade=2')"><i class="xi-check-circle-o"></i> 정지</li>
							<li onclick="url('./members?grade=5')"><i class="xi-label-o"></i> 정상</li>
							<li onclick="url('./members?grade=9')"><i class="xi-plus-square-o"></i> 관리자</li>               
						 </ul>
					  </div>
							<table>
								<thead>
									<tr>
										<td>번호</td>
										<td>아이디</td>
										<td>이름</td>
										<td>가입일</td>
										<td>등급</td>
									</tr>
							</thead>
							<tbody>
								<c:forEach items="${list }" var="row">
								<tr class="row${row.mgrade}">
									<td class="d1">${row.mno }</td>
									<td class="title">${row.mid }</td>
									<td class="d2">${row.mname }</td>
									<td class="d1">${row.mdate }</td>
									<td>
										<select name="grade">
											<optgroup label="정지">
												<option <c:if test="${row.mgrade eq 0}">selected="selected"</c:if> value="0">0 강퇴</option>
												<option <c:if test="${row.mgrade eq 1}">selected="selected"</c:if> value="1">1 탈퇴</option>
												<option <c:if test="${row.mgrade eq 2}">selected="selected"</c:if> value="2">2 정지</option>
											</optgroup>
											<optgroup label="정상">
												<option <c:if test="${row.mgrade eq 5}">selected="selected"</c:if>value="5">5 일반</option>
											</optgroup>
											<optgroup label="관리자">
												<option <c:if test="${row.mgrade eq 9}">selected="selected"</c:if> value="9">9 관리자</option>
											</optgroup>
										</select>
									</td>
									</tr>
							</c:forEach>
						</tbody>
					</table>
			</article>
		</div>
	</div>
</body>
</html>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 위에 처럼 써야지 jstl을 사용 할 수 있다. -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
<link href="./css/menu.css" rel="stylesheet">
<link href="./css/index.css" rel="stylesheet">
<script type="text/javascript" src="./js/menu.js"></script>
<style type="text/css">
table{
	width: 1000px;
	height: 400px;
	border-collapse: collapse;
}
tr:hover{
	background-color: yellow;
}
th{
	background-color: red;
	border-bottom: 2px solid green;
}
td{
	border-bottom: 1px solid black;
	text-align: center;
}
.title{
	text-align: left;
	width: 50%;
}
.w1{
	width: 10%;
}
.w2{
	width:20%;
}

.title a{
	text-decoration: none;
}
.title a:visited {
	color:black;
}
.title a:link {
	color:black;
}
.title a:hover{
	color: silver;
}
.paging{
	margin: 20px auto;
	width: 600px;
	height: 30px;
	margin-top: 10px;
	line-height: 30px;
	text-align: center;
	 
}
.currentBtn{
	background-color: blue;
	color: red;
}
span{
	color: blue;
	font-size: small;
	font-weight: bolder;
	
}
</style>
</head>
<body>
	<div class="container">
		<header><%@ include file="menu.jsp" %><!-- 먼저 menu.jsp로 가져와서 jstl실행? -->
		</header>
		<div class="main">
			<div style="width: 1000px; margin: 0 auto; padding-top: 10px;" >
				<article>
					<%-- for문 연습해보기<br>
					<c:forEach items="${list }" var="e" varStatus="s">
						${e.no }/ ${s.first }/${s.last }/${s.index}/${s.count }/${s.step }<br>
					</c:forEach> --%>
				</article>
				
				<article>
					<table>
						<c:choose><c:when test="${fn:length(list) gt 0 }">
							<tr>
								<th>번호</th>
								<th>제목</th>
								<th>글쓴이</th>
								<th>날짜</th>
								<th>읽음</th>
							</tr><c:forEach items="${list }" var="row">
							<tr>
								<td class="w1">${row.no }</td>
								<td class="title">
								<a href="./detail?page=${page}&no=${row.no }">${row.title }<span>&nbsp;&nbsp;<c:if test="${row.comment gt 0}">[${row.comment }]</c:if></span></a></td>
								<td class="w2">${row.write }</td>
								<td class="w1">${row.date }</td>
								<td class="w1">${row.count }</td>
							</tr>
					</c:forEach>
						</c:when>
							<c:otherwise>
								<h1>출력할 값이 없습니다.</h1>
							</c:otherwise>
					</c:choose>
					</table>
					전체 글 수 : ${totalCount }개 글이 있습니다 /<c:set var="totalPage" value="${totalCount/10 }"/>
					<fmt:parseNumber integerOnly="true" value="${totalPage }" var="totalPage"/>
					<c:if test="${totalCount % 10 gt 0}">
						<c:set var="totalPage" value="${totalPage +1 }"/>
					</c:if>
					전체 페이지 수:<c:out value="${totalPage }"/>
					<c:set var ="startPage" value="1"/>${startPage }
					<c:if test="${page gt 5}">
						<c:set var="startPage" value="${page-5 }"/>
					</c:if>
					<c:set var = "endPage" value="${startPage +9 }"/>
					<c:if test="${endPage gt totalPage }">
						<c:set var="startPage" value="${totalPage - 9 }"/>
						<c:set var="endPage" value="${totalPage }"/>
					</c:if>
					<!-- param은 url에 적혀있는 파리미터에 있는 page 보여준다. -->
						<div class="paging">
							<button onclick="paging(1)">⏮</button>
							<button
							<c:if test="${page - 10 lt 1 }"> disabled="disabled"</c:if> 
							onclick="paging(${page-10})">◁</button>
							
							
							<c:forEach begin="${startPage }" end="${endPage }" var="p">
								<button <c:if test="${page eq p }">class="currentBtn"</c:if> onclick = "paging(${p})">${p }</button>
							</c:forEach>
							<button <c:if test="${page + 10 gt totalPage }">disabled="disabled"</c:if> onclick="paging(${page+10})">▷</button>
							<button onclick="paging(${totalPage})">⏭</button>
							
						</div>
						<c:if test="${sessionScope.mname ne null }"><!-- mname의 값이 null이 아니면 참, null이면 거짓. -->
						<button onclick="url('./write')">글쓰기</button>
						${sessionScope.mname }님 어서오시오.
						</c:if>
				</article>
				<article>
					<%-- <fmt:requestEncoding value="UTF-8"/>
					<fmt:setLocale value="ko_kr"/>
					<fmt:formatNumber value="3.14" type="number"/><br>
					<fmt:parseNumber value = "3.14" integerOnly="true"/><br>
					
					<c:set var="nowDate" value="<%= new Date() %>"/>
					${nowDate }
					<br>
					<fmt:formatDate type="time" value="${nowDate }"/><br>
					<!-- 위에서 만든 nowDate에서 시간만 뽑아오기 -->
					<fmt:formatDate type="date" value="${nowDate }"/><br>
					<fmt:formatDate type="both" value="${nowDate }"/><br>
					<fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="${nowDate }"/><br>
					<fmt:formatDate type="both" dateStyle="medium" timeStyle="medium" value="${nowDate }"/><br>
					<fmt:formatDate type="both" dateStyle="long" timeStyle="long" value="${nowDate }"/><br>
					<fmt:formatDate pattern="yyyy-MM-dd" value="${nowDate }"/> --%>
					
					
					
				</article>
				<article>
					fn이용해서 자료형 데이터 길이 뽑아내기
					${fn:length(list) }<br><br>
					
					
					
				</article>
			</div>
		</div>
		<footer>
			<c:import url="footer.jsp"/>
		</footer>
		
		
	</div>
		<script type="text/javascript">
			function paging(no){
				location.href="board?page="+no;
			}
		</script>
</body>
</html>
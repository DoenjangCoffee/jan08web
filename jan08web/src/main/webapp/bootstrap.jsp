<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>New Age - Start Bootstrap Theme</title>
        <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
        <!-- Bootstrap icons-->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
        <!-- Google fonts-->
        <link rel="preconnect" href="https://fonts.gstatic.com" />
        <link href="https://fonts.googleapis.com/css2?family=Newsreader:ital,wght@0,600;1,600&amp;display=swap" rel="stylesheet" />
        <link href="https://fonts.googleapis.com/css2?family=Mulish:ital,wght@0,300;0,500;0,600;0,700;1,300;1,500;1,600;1,700&amp;display=swap" rel="stylesheet" />
        <link href="https://fonts.googleapis.com/css2?family=Kanit:ital,wght@0,400;1,400&amp;display=swap" rel="stylesheet" />
        <!-- Core theme CSS (includes Bootstrap)-->
        <link href="css/styles.css" rel="stylesheet" />
    </head>
    <body id="page-top">
        <!-- Navigation-->
        <nav class="navbar navbar-expand-lg navbar-light fixed-top shadow-sm" id="mainNav">
            <div class="container px-5">
                <a class="navbar-brand fw-bold" href="#page-top">웹사이트</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
                    Menu
                    <i class="bi-list"></i>
                </button>
                <div class="collapse navbar-collapse" id="navbarResponsive">
                    <ul class="navbar-nav ms-auto me-4 my-3 my-lg-0">
                        <li class="nav-item"><a class="nav-link me-lg-3" href="./board">보드</a></li>
                        <li class="nav-item"><a class="nav-link me-lg-3" href="./qna">문의게시판</a></li>
                        <li class="nav-item"><a class="nav-link me-lg-3" href="./notice">공지사항</a></li>
                        <li class="nav-item"><a class="nav-link me-lg-3" href="./login">로그인</a></li>
                    </ul>
                </div>
            </div>
        </nav>
       
        <section>
			<br><br><br><br><br>     
           <div class="m-5 p-5 border-bottom">
            <table class=" table table-hover">
            	<thead>
            		<tr>
            			<th>번호</th>
            			<th>제목</th>
            			<th>글쓴이</th>
            			<th>날짜</th>
            			<th>조회수</th>
            		</tr>
            	</thead>
            	<tbody>
            		<c:forEach items="${list }" var="row">
            		<tr>
            			<td>${row.no }</td>
            			<td>${row.title }</td>
            			<td>${row.write }</td>
            			<td>${row.date }</td>
            			<td>${row.count }</td>
            		</tr>
            		</c:forEach>
            	</tbody>
            </table>
            </div>
          
            <button type="button" class="mx-5 btn btn-outline-primary">글쓰기</button>
            
            <div class="container bg-info text-white">구역 나누기
            	<div class="m-5 mb-0 bg-primary">구역2</div>
            	<div class="mx-2 bg-secondary">구역3</div>
            	<div class="my-4 bg-primary w-25">구역4</div>
            	<div class="my-4 bg-primary w-50">구역5</div>
            	<div class="my-4 bg-primary w-75">구역6</div>
            </div>
        </section>
        <!-- Footer-->
        <footer class="bg-black text-center py-5">
            <div class="container px-5">
                <div class="text-white-50 small">
                    <div class="mb-2">&copy; monwo 2024. All Rights Reserved.</div>
                    <a href="#!">Privacy</a>
                    <span class="mx-1">&middot;</span>
                    <a href="#!">Terms</a>
                    <span class="mx-1">&middot;</span>
                    <a href="#!">FAQ</a>
                </div>
            </div>
        </footer>
        <!-- Bootstrap core JS-->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
        <!-- Core theme JS-->
        <script src="js/scripts.js"></script>
        <!-- * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *-->
        <!-- * *                               SB Forms JS                               * *-->
        <!-- * * Activate your form at https://startbootstrap.com/solution/contact-forms * *-->
        <!-- * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *-->
        <script src="https://cdn.startbootstrap.com/sb-forms-latest.js"></script>
    </body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상세보기</title>
<link href="./css/index.css" rel="stylesheet" />
<link href="./css/menu.css" rel="stylesheet" />
<link href="./css/detail.css" rel="stylesheet" />
<link rel="stylesheet" href="//cdn.jsdelivr.net/npm/xeicon@2.3.3/xeicon.min.css">
<script type="text/javascript" src="./js/menu.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js" integrity="sha512-v2CJ7UaYy4JwqLDIrZUI/4hqeoQieOmAZNXBeQyjo21dadnwR+8ZaIJVT8EE2iyI61OV8e6M8PP2/4hpQINQ/g==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script type="text/javascript">
	$(document).ready(function(){
		//댓글 바로 수정하기
		$(".commentUpdate").click(function(){
			if (confirm("댓글을 수정하시겠소??")) {
				//필요한 값 cno값 잡기 / 수정한 내용 + 로그인 ==> 서블릿에서 정리
				let cno = $(this).siblings(".cno").val(); // cno값
				let comment = $(this).parents(".comment").children(".ccomment"); // ccomment값
				let commentChange = comment.html().replaceAll("<br>","\r\n");
				//alert(cno + " : " + comment);
				
				comment.css('height','110px');
				
				
				$(this).hide();
				
				let recommentBox = '<div class="recommentBox">';
				//recommentBox += '<form action="./cedit" method="post">';
				recommentBox += '<textarea class="commentcontent1" name="comment">' + commentChange + '</textarea>';
				recommentBox += '<input type="hidden" name="cno" value="'+ cno +'">';
				recommentBox += '<button class="comment-btn1">댓글 수정</button>';
				//recommentBox += '</form>';
				recommentBox += '</div>';
				
				comment.html(recommentBox);
			}
		});
		
		// 댓글수정 01-25 .comment-btn1버튼 눌렀을 때 .cno값, .commentcontent값 가져오는 명령 만들기
		$(document).on('click',".comment-btn1",function(){
			let cno=$(".cno").val();
			let recomment= $(".commentcontent1").val();
			let comment=$(this).parents(".ccomment");//댓글 위치
			//alert(cno+ " : "+recomment);
			$.ajax({
				url : './recomment',
				type : 'post',
				dataType : 'text',
				data : {'cno' : cno,'comment':recomment},
				success : function(result){
					//alert("통신성공 :"+ result);
					if (result == 1) {
						//수정된 데이터를 화면에 보여주기
						$(this).parent(".recommentBox").remove();
						comment.css('height','auto');
						comment.css('padding','auto');
						comment.html(recomment.replace(/(?:\r\n|\r|\n)/g, '<br>'));
						$(".commentUpdate").show();
						
					} else {
						// 실패 화면 재로드
						alert("문제가 발생했습니다. 화면 갱신")
						//location.href='./detail?page=${param.page}$no=$(param.no)'; //아래 방법도 같은 내용의 코드
						location.href='./detail?page=${param.page}$no=${detail.no}';

					}
				},
				error : function(error){
					alert("문제가 발생했다."+error);
				}
				
			});//ajax end
		});
		
		
		//댓글 삭제 (ajax로 만들기)
		$(".commentDelete").click(function(){
			//부모객체 찾아가기(삭제번호 찾아가기) = ajax = this(나) ==> this(), super()
			//let text = $(this).parent(".cname").text();// partent    partents= 부모전체중에 하나
			//부모요소 아래 자식요소 찾기 children()
			//let cno = $(this).parent().children(".cno").val(); // 부모의 이름이 없어도 잡히긴 한다.
			//let cno = $(this).parent(".cname").children(".cno").val(); 
			//alert(cno);
			// 형제요소 이름으로 찾기 .siblings(), .prev() => 바로 이전 .next() => 바로 다음
			//let cno = $(this).siblings(".cno").val();
			
			//ajax 만들어서 post로 전송하기
			if (confirm("댓글을 삭제하시겠소??")) {
				let cno = $(this).prev().val();
				let point = $(this).parents(".comment");
			$.ajax({
				url : './commentDel', // 어디로 갈 주소
				type : 'post', 		// get방식, post방식
				dataType : 'text', // 수신타입  [나중에는 json으로 한다고 한다]
				data : {no:cno}, 	// 보낼 값
				success : function(result){ // 서버에서 날아오는 0 또는 1이 온다.
					if (result == 1) {
					// 정상 삭제 : this의 부모(.comment)를 찾아서 remove한다.
					point.remove();
					}else{
						// 삭제가 안됐을 때
						alert("삭제 할 수 없습니다. 관리자 문의 요망");
					}
				},
				error : function(request, status, error){ // 통신 오류 발생시 
					alert("문제가 발생");
					}
				});//ajax end
			}//if confirm end
		});//commentDelete.click end
		
		
		//댓글쓰기 버튼을 누르면 댓글창 나오게 하기 01-24
		$(".comment-write").hide(); // 맨 위에 두는게 좋다.
		//$(".comment-write").show(5000);
		
		$(".xi-comment-o").click(function(){
			$(".xi-comment-o").hide(); // 댓글창이 뜨면 버튼은 사라지고
			$(".comment-write").slideToggle('slow');
			//$(".comment-write").show();// 저 위에 버튼을 누르면 댓글창 뜨게한다.
		});
		
		$("#comment-btn").click(function(){
			let content = $("#commentcontent").val(); // textarea내용 가져오기
			let bno = ${detail.no}; // 글 번호 가져오기
			//alert("content : "+content "no : "+no);
			
			//전송 --> content 5글자 이상인 경우 실행 하겠습니다.
			//가상 form 만들기
			if (content.length < 2) {
				alert("댓글은 2글자 이상으로 적어주세요");
				$("#commentcontent").focus();
				//return false;
			}else{//댓글의 길이가 5이상이면 아래 코드 실행
				let form = $('<form></form>');
				form.attr('name', 'form');
				form.attr('method','post');
				form.attr('action', './comment');
				
				form.append($('<input/>',{type: 'hidden', name:'commentcontent', value: content})); // json으로 map이다
				form.append($('<input/>', {type:'hidden', name:'bno', value:bno}));
				
				form.appendTo("body");
				form.submit();
				/* let form = document.createElement('form'); //가상form 만들기
				form.name='form'; // 가상form의 이름은 form
				form.method='post'; // 가상 from의 메소드는 post
				form.action='./comment'; // 가상 form을 보내는 곳 comment 서블릿
				
				//붙이기
				let text = document.createElement('input');
				text.setAttribute("type","hidden");
				text.setAttribute("name","commentcontent");
				text.setAttribute("value", content);
				
				// 붙이기
				let no = document.createElement('input');
				no.setAttribute("type","hidden");
				no.setAttribute("name","bno");
				no.setAttribute("value", ${detail.no});
				
				//form에다가 붙이기
				form.appendChild(text);
				form.appendChild(no);
				
				
				//전송하기
				document.body.appendChild(form);
				form.submit(); */
			}
		});
		
	});
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
					<div class="detailDIV">
						<div class = "detailTITLE">
							${detail.title }
						</div>
						<div class = "detailWRITECOUNT">
							<div class="detailWRITE">
							${detail.write }
							<c:if test="${sessionScope.mname ne null && detail.mid eq sessionScope.mid }">
							<img alt="사진 없어" src="./img/edit_icon.png " onclick="update()">
							<img alt="이것 없어" src="./img/delet.png" onclick="del()">
							</c:if>
							</div>
							<div class="detailCOUNT">${detail.ip} / ${detail.count }</div>
						</div>
							<div class= "detailCONTENT">${detail.content }</div>
					</div>
						<!-- 여기에 댓글창 만들기[댓글내용, 누가썻는지, 어느글에 썻는지 ]-->
						<c:if test="${sessionScope.mid ne null }">
						<button class = "xi-comment-o">댓글쓰기</button>
						<div class="comment-write">
							<div class="comment-form">
								<textarea class="commentcontent" id="commentcontent" name="commentcontent"></textarea>
								<button id="comment-btn">댓글쓰기</button>
							</div>
						</div>
						</c:if>
						<!-- 댓글 출력창 -->
						<div class="comments">
							<c:forEach items="${commentList }" var="co">
								<div class = "comment">
									<div class="chead">
										<div class="cname">${co.mname }&nbsp;님 / ${co.cno }
										<input type = "hidden" class="cno" value="${co.cno }">
										<c:if test="${sessionScope.mname ne null && co.mid eq sessionScope.mid }">
										<img alt="삭제" src="./img/cdelete.png" class = "commentDelete"> <!-- 댓글은 여러개 이기 때문에 class로 준다. id는 하나만 일때[유일객체이다.] -->
										<img alt="수정" src="./img/change.png" class = "commentUpdate">
										</c:if>
									<div class="cdate">${co.ip} / ${co.cdate }</div>
									</div>
								</div>
							<div class="ccomment">${co.comment }</div>
						</div>
					</c:forEach>
				</div>
			<article>
			<button onclick="url('./board?page=${param.page}')">게시판 돌아가기</button>
			</article>
		</article>
	<article>
	푸터바이
	</article>
		</div>
			</div>
				<footer>
					<c:import url="footer.jsp"/>
		 		</footer>
					</div>
	<script type="text/javascript">
		function del(){
			//alert("정말 삭제 할거에요?");
			var ch = confirm("글을 삭제 것이오");/* 사용자가 예를 누르면 참 아니오를 누르면 거짓 */
			if (ch) {
			location.href = "./delete?no=${detail.no}";
			}
			/* 자바나 jstl을 먼저 실행되어서 문자열로 들어가도 숫자로 읽어준다. */
		}
		
		function update(){
			var ch = confirm("수정 하시겠소?")
			if (ch) {
				location.href = "./update?no=${detail.no}";
			}
		}
 		function commentDel(cno){
			if (confirm("댓글을 삭제하시겠소?")) {
				location.href="./commentDel?no=${detail.no}&cno="+cno;
			}
		} 
		function commentUpdate(cno){
			if (confirm("댓글을 수정하시겠소?")) {
				location.href="./commentUpdate?no=${detail.no}&cno="+cno;
			}
		}
	</script>
</body>
</html>
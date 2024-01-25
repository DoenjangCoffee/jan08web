<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>음료 선택</title>
<link href="./css/menu.css" rel="stylesheet">
<link href="./css/index.css" rel="stylesheet">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js" integrity="sha512-v2CJ7UaYy4JwqLDIrZUI/4hqeoQieOmAZNXBeQyjo21dadnwR+8ZaIJVT8EE2iyI61OV8e6M8PP2/4hpQINQ/g==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script type="text/javascript" src="./js/menu.js"></script>
<script type="text/javascript">
	$(function(){
		$(".btn").click(function(){
			let selectRaido = $('input[name="listGroupRadio"]:checked');
			let menu = selectRaido.val();
			$.ajax({
				url : './coffee',
				type : 'post',
				dataType:'text',
				data : {'menu': menu},
				success : function(result){
					if(result==1){
						alert("주문이 완료되었습니다.");
					}
				},
				error: function(error){
					alert("문제가 발생했습니다."+error);
				}
			});//ajax end
		});//btn end

	});// funciton end
</script>
</head>
<body>
	<header>
		<%@ include file="menu.jsp" %>
	</header>
	 <div>
		<div>
		 <article>
			<h1>음료 투표</h1>

<div class="accordion accordion-flush" id="accordionFlushExample">
  <div class="accordion-item">
    <h2 class="accordion-header" id="flush-headingOne">
      <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#flush-collapseOne" aria-expanded="false" aria-controls="flush-collapseOne">
       Coffee
      </button>
    </h2>
    <div id="flush-collapseOne" class="accordion-collapse collapse" aria-labelledby="flush-headingOne" data-bs-parent="#accordionFlushExample">
      <div class="accordion-body">
		<div>
		<h4>COOL</h4>
			<ul class="list-group">
 			<li class="list-group-item">
    			<input class="form-check-input me-1" type="radio" name="listGroupRadio" value="아이스아메리카노" id="firstRadio" checked>
    			<label class="form-check-label" for="firstRadio">아메리카노</label>
			</li>
  			<li class="list-group-item">
    			<input class="form-check-input me-1" type="radio" name="listGroupRadio" value="아이스카페라떼" id="secondRadio">
    			<label class="form-check-label" for="secondRadio">카페라떼</label>
  			</li>
  			<li class="list-group-item">
    			<input class="form-check-input me-1" type="radio" name="listGroupRadio" value="아이스루왁커피" id="thirdRadio">
    			<label class="form-check-label" for="thirdRadio">루왁커피</label>
  			</li>
			</ul>

		</div>
		<div>
		<h4>HOT</h4>
			<ul class="list-group">
 			<li class="list-group-item">
    			<input class="form-check-input me-1" type="radio" name="listGroupRadio" value="따뜻한아메리카노" id="firstRadio" checked>
    			<label class="form-check-label" for="firstRadio">아메리카노</label>
			</li>
  			<li class="list-group-item">
    			<input class="form-check-input me-1" type="radio" name="listGroupRadio" value="따뜻한카페라떼" id="secondRadio">
    			<label class="form-check-label" for="secondRadio">카페라떼</label>
  			</li>
  			<li class="list-group-item">
    			<input class="form-check-input me-1" type="radio" name="listGroupRadio" value="따뜻한루왁커피" id="thirdRadio">
    			<label class="form-check-label" for="thirdRadio">루왁커피</label>
  			</li>
			</ul>
		</div>
      </div>
    </div>
  </div>
  <div class="accordion-item">
    <h2 class="accordion-header" id="flush-headingTwo">
      <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#flush-collapseTwo" aria-expanded="false" aria-controls="flush-collapseTwo">
        Tea
      </button>
    </h2>
    <div id="flush-collapseTwo" class="accordion-collapse collapse" aria-labelledby="flush-headingTwo" data-bs-parent="#accordionFlushExample">
      <div class="accordion-body">
       <div>
       	<h4>COOL</h4>
       		<ul class="list-group">
 			<li class="list-group-item">
    			<input class="form-check-input me-1" type="radio" name="listGroupRadio" value="아복티" id="firstRadio" checked>
    			<label class="form-check-label" for="firstRadio">복숭아티</label>
			</li>
  			<li class="list-group-item">
    			<input class="form-check-input me-1" type="radio" name="listGroupRadio" value="아누티" id="secondRadio">
    			<label class="form-check-label" for="secondRadio">누룽지티</label>
  			</li>
  			<li class="list-group-item">
    			<input class="form-check-input me-1" type="radio" name="listGroupRadio" value="아페티" id="thirdRadio">
    			<label class="form-check-label" for="thirdRadio">페퍼민트티</label>
  			</li>
			</ul>
       </div>
       <div>
       	<h4>HOT</h4>
       	    <ul class="list-group">
 			<li class="list-group-item">
    			<input class="form-check-input me-1" type="radio" name="listGroupRadio" value="뜨복티" id="firstRadio" checked>
    			<label class="form-check-label" for="firstRadio">복숭아티</label>
			</li>
  			<li class="list-group-item">
    			<input class="form-check-input me-1" type="radio" name="listGroupRadio" value="뜨룽티" id="secondRadio">
    			<label class="form-check-label" for="secondRadio">누룽지티</label>
  			</li>
  			<li class="list-group-item">
    			<input class="form-check-input me-1" type="radio" name="listGroupRadio" value="뜨페티" id="thirdRadio">
    			<label class="form-check-label" for="thirdRadio">페퍼민트티</label>
  			</li>
			</ul>
       </div>
      </div>
    </div>
  </div>
  <div class="accordion-item">
    <h2 class="accordion-header" id="flush-headingThree">
      <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#flush-collapseThree" aria-expanded="false" aria-controls="flush-collapseThree">
        Null
      </button>
    </h2>
    <div id="flush-collapseThree" class="accordion-collapse collapse" aria-labelledby="flush-headingThree" data-bs-parent="#accordionFlushExample">
      <div class="accordion-body">
       <ul class="list-group">
 			<li class="list-group-item">
    			<input class="form-check-input me-1" type="radio" name="listGroupRadio" value="안 먹기" id="firstRadio" checked>
    			<label class="form-check-label" for="firstRadio">안 먹기</label>
    	</ul>
      </div>
    </div>
  </div>
</div>
<div class="d-grid gap-2">
	<button class="btn btn-primary">제출하기</button>
</div>

		</article>

		</div>
	</div>
	<footer>
		<c:import url="footer.jsp"/>
	</footer>
</body>
</html>
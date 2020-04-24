<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<!-- jQuery -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
	integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
	crossorigin="anonymous"></script>
<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
	integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
	crossorigin="anonymous">

<title>board</title>
<script>
	$(document).on('click', '#btnSave', function(e) {
		e.preventDefault();
		$("#form").submit();
	});
	$(document).on('click','#btnList',function(e) {
		e.preventDefault();
		location.href = "${pageContext.request.contextPath}/board/getBoardList";
	});

	$(document).ready(
		function() {
			var mode = '<c:out value="${mode}"/>';

			if (mode == 'edit') {
				//입력 폼 셋팅
				$("#boardWriter").prop('readonly', true);

				$("input:hidden[name='boardNumber']").val(<c:out value="${boardContent.boardNumber}"/>);

				$("input:hidden[name='mode']").val('<c:out value="${mode}"/>');

				$("#boardWriter").val('<c:out value="${boardContent.boardWriter}"/>');

				$("#boardTitle").val('<c:out value="${boardContent.boardTitle}"/>');

				$("#boardContent").val('<c:out value="${boardContent.boardContent}"/>');

				$("#boardTag").val('<c:out value="${boardContent.boardTag}"/>');
			} 
		});
</script>
<style>
body {
	padding-top: 70px;
	padding-bottom: 30px;
}
</style>
</head>
<body>
	<article>
		<div class="container" role="main">
			<h2>board Form</h2>
			<form:form name="form" id="form" role="form" modelAttribute="boardVO" method="post" action="${pageContext.request.contextPath}/board/saveBoard">
				<form:hidden path="boardNumber" />
				<input type="hidden" name="mode" />
				<div class="mb-3">
					<label for="title">제목</label>
					<form:input path="boardTitle" id="boardTitle" class="form-control" placeholder="제목을 입력해 주세요" />
				</div>
				<div class="mb-3">
					<label for="reg_id">작성자</label>
					<form:input path="boardWriter" id="boardWriter" class="form-control" placeholder="제목을 입력해 주세요" />
				</div>
				<div class="mb-3">
					<label for="content">내용</label>
					<form:textarea path="boardContent" id="boardContent" class="form-control" rows="5" placeholder="내용을 입력해 주세요" />
				</div>
				<div class="mb-3">
					<label for="tag">TAG</label>
					<form:input path="boardTag" id="boardTag" class="form-control" placeholder="태그를 입력해 주세요" />
				</div>
			</form:form>
			<div>
				<button type="button" class="btn btn-sm btn-primary" id="btnSave">저장</button>
				<button type="button" class="btn btn-sm btn-primary" id="btnList">목록</button>
			</div>
		</div>
	</article>
</body>
</html>
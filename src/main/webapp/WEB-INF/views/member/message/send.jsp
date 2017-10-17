<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/popup.css'/>?ver=1">
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/default.css'/>?ver=1">
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/jquery-ui.min.css'/>?ver=1">
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/jquery-ui.theme.min.css'/>?ver=1">
<script type="text/javascript" src="<c:url value='/resources/js/jquery-3.2.1.min.js'/>"></script>
<script>
	$(document).ready(function(){
		$("#reg").click(function(event){
			event.preventDefault();
			var s_id="${sessionScope.id}";
			var recv_id=$("#recv_id").val();
			var msg_title=$("#msg_title").val();
			var content=$("#content").val();
			$("#form1").submit();
		});
		$("#reset").click(function(event){
			event.preventDefault();
			self.close();
		});
	});
</script>
<div class="popup" style="width:388px;">
	<!-- title -->
	<div class="pop_tit_bg"><div class="pop_tit_l"><div class="pop_tit_r">
		<h3 class="pop_tit">쪽지보내기</h3>
	</div></div>
	<form method="post" action='<c:url value="/message/send"/>' id="form1" style="margin:0px;">
		<input type="hidden" value="${sessionScope.id }" id="s_id" name="s_id">
	<div class="sendMemo">
		<dl class="listType1">
		<dt>아이디</dt>
		<dd><input type="text" class="oid" id="recv_id" name="recv_id" value=""></dd>
		</dl>
		<dl class="listType1">
		<dt>제목</dt>
		<dd><input type="text" class="oid" id="msg_title" name="msg_title" value="" ></dd>
		</dl>
		
		<textarea id="content" name="content" class="memo_cnt_area2"></textarea>
		<ul class="btn_buttom1">
			<li><a href="#" id="reg"><img src="http://res.afreecatv.com/images/popup/button/btn_send.gif" alt="보내기"></a></li>
			<li><a href="#" id="reset"><img src="http://res.afreecatv.com/images/popup/button/btn_close4.gif" alt="닫기"></a></li>
		</ul>
	</div>
	</form>
	</div>
</div>


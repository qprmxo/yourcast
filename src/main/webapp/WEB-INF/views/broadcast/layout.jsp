<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link
	href='https://fonts.googleapis.com/css?family=Open+Sans:400,300,700'
	rel='stylesheet' type='text/css'>
<link href='https://fonts.googleapis.com/css?family=Sofia'
	rel='stylesheet' type='text/css'>
<script type="text/javascript"
	src="<c:url value='/resources/js/jquery-3.2.1.min.js'/>"></script>
<script type="text/javascript"
	src="<c:url value='/resources/js/sockjs.min.js'/>"></script>
<script type="text/javascript"
	src="<c:url value='/resources/jwplayer/jwplayer.js?ver=1.1'/>"></script>
</head>
<!-- 메인페이지 레이아웃 -->
<body>
	<div id="wrap">
		<div id="header">
			<tiles:insertAttribute name="header" />
		</div>

		<div id="main">
			<tiles:insertAttribute name="content" />
		</div>
	</div>
</body>
</html>
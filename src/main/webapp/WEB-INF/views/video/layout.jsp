<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script type="text/javascript"
	src="<c:url value='/resources/js/jquery-3.2.1.min.js'/>"></script>
<style type="text/css">
@import url(http://fonts.googleapis.com/earlyaccess/hanna.css);

* {
font-family: 'Hanna', serif;
}
</style>
</head>
<!-- 메인페이지 레이아웃 -->
<body id="body">
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
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/list.css'/>?ver=4">
<body class="w3-light-grey w3-content" style="max-width: 1600px">
	<!-- !PAGE CONTENT! -->
	<div class="w3-main" style="margin-left: 300px">

		<!-- Header -->
		<header id="portfolio">
			<span class="w3-button w3-hide-large w3-xxlarge w3-hover-text-grey"
				onclick="w3_open()"><i class="fa fa-bars"></i></span>
			<div class="w3-container">
				<h1>
					<b>${voMP.profile_title }</b>
				</h1>
				<div class="w3-section w3-bottombar w3-padding-16"  style="width: 1000px;">
				</div>
			</div>
			
		</header>
		<!-- 여기작성 -->
		<c:choose>
			<c:when test="${not empty board_name }">
				<h2 style="margin-left:30px;">${board_name }</h2>
			</c:when>
			<c:otherwise>
				<h2 style="margin-left:30px;">전체 글</h2>
			</c:otherwise>
		</c:choose>
		
		<p style="margin-left:30px;margin-top:-10px;color:#9A9A9A">board List</p>
		
		<br>
		
		<c:forEach var="vo" items="${blist }">
		<ul>
			<li class="notice" id="list_item_26185726">
				<div class="wrap_view">
					<div class="img_profile">
						<img src="<c:url value='/resources/upload/${vo.profile_savefilename }'/>"
							onerror="this.src='http://www.afreecatv.com/images/afmain/img_thumb_profile.gif';" style="width: 50px;height: 50px;border-radius: 25px;">
					</div>
					<div class="v_article">
						<div class="name">
							<div class="bj_name"><span style="color: #1559ff;font-weight: bold;">${vo.name}</span>&nbsp;<span style="color: #4B4B4B;">(${vo.id })&nbsp;&nbsp;&nbsp;${vo.b_regdate }</span><span
								class="u_id"></span>
							</div><span class="time"></span>
						</div>
						<div class="v_title">
						<a href='<c:url value="/${requestScope.id }/board/getInfo?b_num=${vo.b_num }&category_num=${vo.category_num }"/>' style="text-decoration: none;">${vo.b_title }</a>
							<span class="noti"></span>
						</div>
						<div class="view">${vo.b_content }</div>
					</div>
					<div class="watch_area">
						<span class="w3-button w3-light-grey w3-border w3-round-large" id="likeT_26185493"><i class="fa fa-thumbs-o-up" aria-hidden="true"></i>&nbsp;&nbsp;<b>${vo.bucnt }</b></span>
						<span class="w3-button w3-light-grey w3-border w3-round-large"><i class="fa fa-comment-o" aria-hidden="true"></i>&nbsp;&nbsp;<b>${vo.brcnt }</b></span>
					</div>			
				</div>
			</li>
		</ul>
		</c:forEach>
		</div>
		<!-- 페이징 -->
		<div class="w3-bar-all w3-center">
			<!-- 이전 -->
			<c:choose>
				<c:when test="${pu.startPageNum>5 }">
					<c:choose>
						<c:when test="${not empty category_num}">
							<a href='<c:url value="/${requestScope.id }/board/list?pageNum=${pu.startPageNum-1 }&category_num=${category_num }"/>'class="w3-bar-item w3-button w3-hover-black">이전</a>
						</c:when>
						<c:otherwise>
							<a href='<c:url value="/${requestScope.id }/board/list?pageNum=${pu.startPageNum-1 }"/>'class="w3-bar-item w3-button w3-hover-black">이전</a>
						</c:otherwise>
					</c:choose>
				</c:when>
				<c:otherwise>
					<a class="w3-bar-item w3-button w3-hover-black">이전</a>
				</c:otherwise>
			</c:choose>
			<!-- 숫자 부분 -->
			<c:forEach var="i" begin="${pu.startPageNum }" end="${pu.endPageNum }">
				<c:choose>
					<c:when test="${not empty category_num }"><!-- 전체보기가 아닌 경우 -->
						<c:choose>
							<c:when test="${i==pu.pageNum }"><!-- 현재페이지인 경우 -->
								<a href='<c:url value="/${requestScope.id }/board/list?pageNum=${i}&category_num=${category_num }"/>'class="w3-bar-item w3-button w3-hover-black"><span style="color:red">${i }</span></a>
							</c:when>
							<c:otherwise>
								<a href='<c:url value="/${requestScope.id }/board/list?pageNum=${i}&category_num=${category_num }"/>'class="w3-bar-item w3-button w3-hover-black"><span>${i }</span></a>
							</c:otherwise>
						</c:choose>
					</c:when>
					<c:otherwise><!-- 전체보기인 경우 -->
						<c:choose>
							<c:when test="${i==pu.pageNum }"><!-- 현재페이지인 경우 -->
								<a href='<c:url value="/${requestScope.id }/board/list?pageNum=${i}"/>'class="w3-bar-item w3-button w3-hover-black"><span style="color:red">${i }</span></a>
							</c:when>
							<c:otherwise>
								<a href='<c:url value="/${requestScope.id }/board/list?pageNum=${i}"/>'class="w3-bar-item w3-button w3-hover-black"><span>${i }</span></a>
							</c:otherwise>
						</c:choose>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			<!-- 다음 -->
			<c:choose>
				<c:when test="${pu.endPageNum<pu.totalPageCount }">
					<c:choose>
						<c:when test="${not empty category_num}">
							<a href='<c:url value="/${requestScope.id }/board/list?pageNum=${pu.endPageNum+1 }&category_num=${category_num }"/>'class="w3-bar-item w3-button w3-hover-black">다음</a>
						</c:when>
						<c:otherwise>
							<a href='<c:url value="/${requestScope.id }/board/list?pageNum=${pu.endPageNum+1 }"/>'class="w3-bar-item w3-button w3-hover-black">다음</a>
						</c:otherwise>
					</c:choose>
				
					
				</c:when>
				<c:otherwise>
					<a class="w3-bar-item w3-button w3-hover-black">다음</a>
				</c:otherwise>
			</c:choose>
		</div>
		<div>
		<!-- 작성END -->

			<footer class="w3-container w3-padding-32 w3-dark-grey">
				<div class="w3-row-padding">
					<div class="w3-third">
						<h3>FOOTER</h3>
						<p>Praesent tincidunt sed tellus ut rutrum. Sed vitae justo
							condimentum, porta lectus vitae, ultricies congue gravida diam
							non fringilla.</p>
						<p>
							Powered by <a href="https://www.w3schools.com/w3css/default.asp"
								target="_blank">w3.css</a>
						</p>
					</div>

					<div class="w3-third">
						<h3>BLOG POSTS</h3>
						<ul class="w3-ul w3-hoverable">
							<li class="w3-padding-16"><img src="/w3images/workshop.jpg"
								class="w3-left w3-margin-right" style="width: 50px"> <span
								class="w3-large">Lorem</span><br> <span>Sed mattis
									nunc</span></li>
							<li class="w3-padding-16"><img src="/w3images/gondol.jpg"
								class="w3-left w3-margin-right" style="width: 50px"> <span
								class="w3-large">Ipsum</span><br> <span>Praes tinci
									sed</span></li>
						</ul>
					</div>

					<div class="w3-third">
						<h3>POPULAR TAGS</h3>
						<p>
							<span class="w3-tag w3-black w3-margin-bottom">Travel</span> <span
								class="w3-tag w3-grey w3-small w3-margin-bottom">New York</span>
							<span class="w3-tag w3-grey w3-small w3-margin-bottom">London</span>
							<span class="w3-tag w3-grey w3-small w3-margin-bottom">IKEA</span>
							<span class="w3-tag w3-grey w3-small w3-margin-bottom">NORWAY</span>
							<span class="w3-tag w3-grey w3-small w3-margin-bottom">DIY</span>
							<span class="w3-tag w3-grey w3-small w3-margin-bottom">Ideas</span>
							<span class="w3-tag w3-grey w3-small w3-margin-bottom">Baby</span>
							<span class="w3-tag w3-grey w3-small w3-margin-bottom">Family</span>
							<span class="w3-tag w3-grey w3-small w3-margin-bottom">News</span>
							<span class="w3-tag w3-grey w3-small w3-margin-bottom">Clothing</span>
							<span class="w3-tag w3-grey w3-small w3-margin-bottom">Shopping</span>
							<span class="w3-tag w3-grey w3-small w3-margin-bottom">Sports</span>
							<span class="w3-tag w3-grey w3-small w3-margin-bottom">Games</span>
						</p>
					</div>

				</div>
			</footer>

			<div class="w3-black w3-center w3-padding-24">
				Powered by <a href="https://www.w3schools.com/w3css/default.asp"
					title="W3.CSS" target="_blank" class="w3-hover-opacity">w3.css</a>
			</div>

		</div>

	</div>
</body>
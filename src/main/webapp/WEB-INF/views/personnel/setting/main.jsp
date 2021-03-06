<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript"
	src="<c:url value='/resources/se2/js/HuskyEZCreator.js'/>"></script>
<body class="w3-light-grey w3-content" style="max-width: 1600px">
	<!-- !PAGE CONTENT! -->
	<div class="w3-main" style="margin-left: 300px">

		<!-- Header -->
		<header id="portfolio">
			<span class="w3-button w3-hide-large w3-xxlarge w3-hover-text-grey"
				onclick="w3_open()"><i class="fa fa-bars"></i></span>
			<div class="w3-container">
				<div class="link-2">
				<h1>
					<a href="<c:url value='/${requestScope.id }'/>" class="maintitle">
					<b>${voMP.profile_title }</b></a>
				</h1>
			</div>
				<div class="w3-section w3-bottombar w3-padding-16"  style="width: 1000px;">
				</div>
			</div>
			
		</header>
		<script type="text/javascript">
			$(function() {
				$("#imgfile").change(function() {
					if (this.files && this.files[0]) {
						$("#br").empty();
						var reader = new FileReader();
						$("#br").append("<br>");
						reader.onload = function(e) {
							$('#imgLogo').attr('src', e.target.result);
						}
						reader.readAsDataURL(this.files[0]);
					}
				});
			});
		</script>
		<!-- 여기작성 -->
		<div>
			<a href="<c:url value='/${requestScope.id}/setting/info'/>"
				class="w3-bar-item w3-button" style="text-decoration: none;">기본정보관리</a>
			<a href="<c:url value='/${requestScope.id}/setting/category'/>"
				class="w3-bar-item w3-button" style="text-decoration: none;">게시판관리</a>
			<a href="<c:url value='/${requestScope.id}/setting/main'/>"
				class="w3-bar-item w3-button" style="text-decoration: none;">
				<span style="color: #337AB7; font-weight: bold;">대문관리</span></a>
			<a href="<c:url value='/${requestScope.id}/setting/fanlist'/>"
				class="w3-bar-item w3-button" style="text-decoration: none;">팬
				목록</a> <a href="<c:url value='/${requestScope.id}/setting/blacklist'/>"
				class="w3-bar-item w3-button" style="text-decoration: none;">블랙리스트</a>
		</div>

		<br>

		<h2 style="margin-left: 30px;">대문 관리</h2>

		<p style="margin-left: 30px; margin-top: -10px; color: #9A9A9A">메인페이지의
			내용을 수정할 수 있습니다.</p>

		<br>

		<div style="margin-left: 30px;">
			<form method="post"
				action='<c:url value="/${requestScope.id}/setting/main"/>'
				id="target">
				<textarea name="content" id="ir1" rows="10" cols="100"
					style="width: 1000px; height: 600px; display: none;">${voMP.profile_content }</textarea>
			</form>
			<!-- 작성END -->
			<div class="w3-bar-all">
				<button type="submit" form="target" value="Submit"
				class="w3-button w3-border w3-round-large" style="background-color: #4C5870; float: left;"><i class="fa fa-check" style="color: white;"></i>
				<span style="color: #EBEDEC;">&nbsp;확인</span></button>
			</div>
		</div>
		<footer>
			<div class="w3-light-grey w3-center w3-padding-24"
				style="width: 1000px; font-size: 11px; border-top: 1px solid #CFCFCF; margin-top: 100px; margin-left: 30px;">
				All Contents Copyright ©Your Cast...Co.,Ltd. All Rights Reserved.</div>
		</footer>

		
	</div>
</body>
<script type="text/javascript">
	var oEditors = [];

	// 추가 글꼴 목록
	//var aAdditionalFontSet = [["MS UI Gothic", "MS UI Gothic"], ["Comic Sans MS", "Comic Sans MS"],["TEST","TEST"]];

	nhn.husky.EZCreator.createInIFrame({
		oAppRef : oEditors,
		elPlaceHolder : "ir1",
		sSkinURI : "/app/resources/se2/SmartEditor2Skin.html",
		htParams : {
			bUseToolbar : true, // 툴바 사용 여부 (true:사용/ false:사용하지 않음)
			bUseVerticalResizer : true, // 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음)
			bUseModeChanger : true, // 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음)
			//aAdditionalFontList : aAdditionalFontSet,		// 추가 글꼴 목록
			fOnBeforeUnload : function() {
				//alert("완료!");
			}
		}, //boolean
		fOnAppLoad : function() {
			//예제 코드
			//oEditors.getById["ir1"].exec("PASTE_HTML", ["로딩이 완료된 후에 본문에 삽입되는 text입니다."]);
		},
		fCreator : "createSEditor2"
	});

	function pasteHTML() {
		var sHTML = "<span style='color:#FF0000;'>이미지도 같은 방식으로 삽입합니다.<\/span>";
		oEditors.getById["ir1"].exec("PASTE_HTML", [ sHTML ]);
	}

	function showHTML() {
		var sHTML = oEditors.getById["ir1"].getIR();
		alert(sHTML);
	}

	//글자 바이트
	String.prototype.byteLength = function() {
		var l = 0;
		for (var idx = 0; idx < this.length; idx++) {
			var c = escape(this.charAt(idx));
			if (c.length == 1)
				l++;
			else if (c.indexOf("%u") != -1)
				l += 3;
			else if (c.indexOf("%") != -1)
				l += c.length / 3;
		}
		return l;
	};

	$('#target').submit(function(event) {

		oEditors.getById["ir1"].exec("UPDATE_CONTENTS_FIELD", []); // 에디터의 내용이 textarea에 적용됩니다.
		// 에디터의 내용에 대한 값 검증은 이곳에서 document.getElementById("ir1").value를 이용해서 처리하면 됩니다.
		var content = document.getElementById("ir1").value;
		var title = document.getElementById("title").value;
		console.log("내용 : " + content);
		if (content == null || content == "<p>&nbsp;</p>") {
			alert("내용을 입력하세요!");
			event.preventDefault();
		} else if (title == null || title == "") {
			alert("제목을 입력하세요!");
			event.preventDefault();
		} else if (title.byteLength() > 100) {
			alert("제목이 너무 깁니다!");
			event.preventDefault();
		} else {
			try {
				alert("대문설정이 완료되었습니다.");
				elClickedObj.form.submit();
			} catch (e) {
			}
		}
	});

	function setDefaultFont() {
		var sDefaultFont = '궁서';
		var nFontSize = 24;
		oEditors.getById["ir1"].setDefaultFont(sDefaultFont, nFontSize);
	}
</script>
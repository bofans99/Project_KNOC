<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- ㅡㅡㅡㅡㅡㅡㅡㅡmyPageㅡㅡㅡㅡㅡㅡㅡㅡㅡ -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="<%=request.getContextPath() %>/resource/style/myPage.css">
</head>
<body style="padding-top: 70px;">

	<div class="mp-wrapper">
		<div class="mp-fsfc">마이페이지</div>
		<div class="mp-bor-bot"></div>


	
	
		<div style="margin: 50px 0">
			<div class="mp-lcontainer">
			
				<div class="mp-thumbnail">
					<div class="mp-cdtitle">나의 프로필</div>
					<div class="mp-profile2">
						<div class="mp-profimg"> <img src="${imgage}" onerror="this.src='<%=request.getContextPath() %>/resource/image/profile.png'"> </div>
						<div class="mp-procomt">xxx님 <br> 안녕하세요!</div>
					</div>
					<div class="mp-just">
						<a class="mp-bottom_right" href="<%=request.getContextPath() %>/view/member/memberDelete.jsp">회원탈퇴</a>	
						|
						<a class="mp-bottom_right" href="<%=request.getContextPath() %>/view/member/memberUpdate.jsp">프로필 수정하기</a>					
					</div>
				</div>
				
				<div class="mp-thumbnail">
					<div class="mp-cdtitle">나의 클래스</div>
					<div class="mp-cljust">
						<div class="mp-clbox">
							<ul>
								<li><a href="#">클래스리스트1......</a></li>
								<li><a href="#">클래스리스트2......</a></li>
								<li><a href="#">클래스리스트3......</a></li>

							</ul>
						</div>	
						<div class="mp-justf">
							<%-- <div onclick="'#'">수정</div>
							|
							<div>삭제</div> --%>	
							<div class="cur" onclick="#">관리</div>
						</div>					
					</div>				
				</div>
				
				<div class="mp-thumbnail">
					<div class="mp-cdtitle">수강중인 클래스</div>	
					<div class="mp-cljust">
						<div class="mp-clbox">
							<ul>
								<li><a href="#">클래스리스트1......</a></li>
								<li><a href="#">클래스리스트2......</a></li>
								<li><a href="#">클래스리스트3......</a></li>
								<li><a href="#">클래스리스트4....................</a></li>
								<li><a href="#">클래스리스트5......</a></li>
							</ul>
						</div>	
						<div class="mp-justf">
							<div class="cur" onclick="#">바로가기</div>
						</div>					
					</div>									
				</div>
			</div>
			
			
			<div class="mp"></div>
			
			
			<div class="mp-lcontainer">

				<div class="mp-thumbnail">
					<div class="mp-cdtitle">관심 클래스</div>		
					<div class="mp-cljust">
						<div class="mp-clbox">
							<ul>
								<li><a href="#">클래스리스트1......</a></li>
								<li><a href="#">클래스리스트2......</a></li>
								<li><a href="#">클래스리스트3......</a></li>
								<li><a href="#">클래스리스트4....................</a></li>
								<li><a href="#">클래스리스트5......</a></li>
								<li><a href="#">클래스리스트6......</a></li>
							</ul>
						</div>					
					</div>		
				</div>

				<div class="mp-thumbnail">
					<div class="mp-cdtitle">멘토링 현황</div>	
					<div class="mp-cljust">
						<div class="mp-clbox">
							<ul>
								<li><a href="#">멘토링리스트1......</a></li>
								<li><a href="#">멘토링리스트2......</a></li>
								<li><a href="#">멘토링리스트3......</a></li>
								<li><a href="#">멘토링리스트4....................</a></li>
							</ul>
						</div>					
					</div>			
				</div>

				<div class="mp-thumbnail">
					<div class="mp-cdtitle">스터디 현황</div>			
					<div class="mp-cljust">
						<div class="mp-clbox">
							<ul>
								<li><a href="#">스터디리스트1......</a></li>
							</ul>
						</div>					
					</div>	
				</div>


			</div>
		</div>
	</div>
</body>
</html>
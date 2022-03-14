package controller;

import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Knoc_Member;
import model.Study;
import model.Study_Comment;
import service.Knoc_MemberDao;
import service.StudyDao;
import service.Study_CommentDao;

//@WebServlet("/study/*")
public class StudyController extends MskimRequestMapping {
	static String msg ="";
	static String url ="";
	
	//스터디 게시글 작성 view
	@RequestMapping("studyWrite")
	public String studyWrite(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(request.getSession().getAttribute("memid")==null) {
			msg = "로그인을 해야 이용 가능합니다.";
			url = request.getContextPath()+"/study/studyList";
			request.setAttribute("msg", msg);
			request.setAttribute("url", url);
			return "/view/alert.jsp";
		}
		String id = (String)request.getSession().getAttribute("memid");
		StudyDao sd = new StudyDao();
		String profile = sd.callProfile(id);
		
		request.setAttribute("profile", profile);
		request.setAttribute("id", id);
		return "/view/study/studyWrite.jsp";
	}
	
	//스터디 게시글 작성 process
	@RequestMapping("studyWritePro")
	public String studyWritePro(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Study s = new Study();
		StudyDao sd = new StudyDao();
		String writerId = (String) request.getSession().getAttribute("memid");
		String id = "study"+sd.nextNum();
		s.setStudy_Id(id);
		s.setLeader_Id(writerId);
		s.setTitle(request.getParameter("title"));
		s.setContent(request.getParameter("text"));
		int num = sd.insertStudy(s);
		if(num>0) { //게시글 작성 성공
			msg = "게시글이 등록되었습니다.";
			url = request.getContextPath()+"/study/studyList";
		}else{	//게시글 등록 실패
			msg = "게시글 등록 실패";
			url = request.getContextPath()+"/study/studyWirte";
		}
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		return "/view/alert.jsp";
	}
	
	//스터디 게시판 view @@@@@@@@@@@@@@@@@@@작성중
	@RequestMapping("studyList")
	public String studyList(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int process = 3;  //process = 1 :모집중, process = 2 : 모집완료, process = 3 : 전체
		
		if(request.getParameter("process")!=null) {
			process = Integer.parseInt(request.getParameter("process"));
		}
		
		int limit = 10; //한 페이지에 보이는 게시글 수
		
		String pageNum="";
		if(request.getParameter("pageNum")!=null) {
			pageNum = request.getParameter("pageNum");
		}else {
			pageNum = "1";
		}
		int pageInt = Integer.parseInt(pageNum);
		
		String keyword = null;
		if(request.getParameter("keyword")!=null) {
			keyword = request.getParameter("keyword");
		}
		
		StudyDao sd = new StudyDao();
		List<Study> list;
		List<String> profileList;
		int studyCount;
		if(process==3) {	//전체 선택되면 모든 게시글 출력
			studyCount = sd.studyAllCount();
			list = sd.studyAllList(pageInt,limit,keyword);
			profileList = sd.callProfileList(pageInt,limit,keyword);
		}else {				//선택된 게시글만 출력
			studyCount = sd.studyCount(process);
			list = sd.studyList(pageInt,limit,process,keyword); 
			profileList = sd.callProfileList(pageInt,limit,process,keyword);
		}
		
		
		int bottomLine = 5; //최대 페이징 수
		
		
		int startPage = (pageInt - 1)/bottomLine * bottomLine + 1;
		int endPage = startPage + bottomLine -1;
		int maxPage = (studyCount/limit)+(studyCount%limit == 0?0:1);
		if(endPage>maxPage) endPage = maxPage;
		
		for (String q : profileList) {
			System.out.println(q);
		}
		
		request.setAttribute("profileList", profileList);
		request.setAttribute("pageInt", pageInt);
		request.setAttribute("studyCount", studyCount);
		request.setAttribute("startPage", startPage);
		request.setAttribute("bottomLine", bottomLine);
		request.setAttribute("endPage", endPage);
		request.setAttribute("maxPage", maxPage);
		request.setAttribute("process", process);
		request.setAttribute("list", list);
		return "/view/study/studyList.jsp";
	}
	
	//스터디 게시글 정보 view
	@RequestMapping("studyInfo")
	public String studyInfo(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//session에 info위치 저장
		String studyId = request.getParameter("study_Id");
		if(studyId == null) {
			studyId = (String) request.getSession().getAttribute("study_Id");
		}
		request.getSession().setAttribute("study_Id", studyId);
		
		StudyDao sd = new StudyDao();
		Study_CommentDao scd = new Study_CommentDao();
		Study s = new Study();
		Study_Comment sc = new Study_Comment();
		
		//게시글과 답글 불러오기
		s = sd.selectOne(studyId);
		int refNum = Integer.parseInt(studyId.substring(5));
		List<Study_Comment> commentlist = scd.selectComment(refNum);
		
		//댓글 갯수
		int count = scd.count(refNum);
		
		//작성자 프로필 사진
		String leaderProfile = sd.callProfile(s.getLeader_Id());
		
		//댓글 작성자 프로필 사진
		List<String> commentProfileList = scd.callProfile(refNum);
		
		request.setAttribute("commentProfileList", commentProfileList);
		request.setAttribute("leaderProfile", leaderProfile);
		request.setAttribute("count", count);
		request.setAttribute("commentList", commentlist);
		request.setAttribute("s", s);
		return "/view/study/studyInfo.jsp";
	}
	
	@RequestMapping("writeStudyCommentPro")
	public String writeStudyComment(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Study_CommentDao scd = new Study_CommentDao();
		Study_Comment sc = new Study_Comment();
		
		String id = (String) request.getSession().getAttribute("memid");
		String study_Id = request.getParameter("study_Id");
		int refNum = Integer.parseInt(study_Id.substring(5));
		
		sc.setComment_Id(id);
		sc.setContent(request.getParameter("text"));
		sc.setRefNum(refNum);
		
		int num = scd.insert(sc);
		msg = "답글이 등록되었습니다.";
		url = request.getContextPath()+"/study/studyInfo";
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);

		return "/view/alert.jsp";
	}
}
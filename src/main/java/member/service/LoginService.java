package member.service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.control.CommandProcess;

import member.dao.MemberDAO;

public class LoginService implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		//데이터
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		
		//DB
		MemberDAO memberDAO = MemberDAO.getInstance();
		String name = memberDAO.login(id, pwd);
		
		//응답
		if(name==null) {
			return "/member/loginFail.jsp";
		}else {
			//쿠키
			/*
			Cookie cookie = new Cookie("memName", name); //쿠키 생성
			cookie.setMaxAge(30*60);//초 단위 - 30분
			
			//setPath()를 지정 안해도 시간을 3000으로 늘리니깐 되넹??!!
			//cookie.setPath("/"); - 만약 URL를 /member/로 지정하면 member폴더로 쿠키 전송해라
			response.addCookie(cookie); //클라이언트로 보내기
			
			Cookie cookie2 = new Cookie("memId", id); //쿠키 생성
			cookie2.setMaxAge(30*60);//초 단위
			//cookie2.setPath("/");
			response.addCookie(cookie2); //클라이언트로 보내기
			*/
			
			//세션
			HttpSession session = request.getSession(); //세션 생성
			session.setAttribute("memName", name);
			session.setAttribute("memId", id);
			
			return "/member/loginOk.jsp";
		}
	}

}



























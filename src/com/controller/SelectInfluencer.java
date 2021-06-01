package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.AdvertiserDAO;
import com.model.AdvertiserDTO;
import com.model.CampaginDAO;
import com.model.CampaginDTO;
import com.model.MatchingDAO;
import com.model.MatchingDTO;


@WebServlet("/SelectInfluencer")
public class SelectInfluencer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 request.setCharacterEncoding("EUC-KR"); 
		 
		 PrintWriter out = response.getWriter();
		 String [] sizes = request.getParameterValues("ck");

		  
		 MatchingDAO matDao = new MatchingDAO();
		 MatchingDTO matDto = new MatchingDTO();
		 

		
		 
		 
		 

		 for(int x=0;x<sizes.length;x++) {
			 matDao.MachingUpdate(sizes[x]);
			 System.out.println("SelectInfluencer 페이지 인플루언서 업데이트: ");
		 }
		
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 System.out.println("광고주 마이페이로 이동");

			HttpSession session = request.getSession(); // 세션 가져오기

			AdvertiserDTO info = (AdvertiserDTO) session.getAttribute("info"); // 회원 세션 가져오기

			AdvertiserDAO mdao = new AdvertiserDAO();
			AdvertiserDTO list = new AdvertiserDTO();
			CampaginDAO cdao = new CampaginDAO();
			CampaginDTO cdto = new CampaginDTO();

			ArrayList<MatchingDTO> matList = new ArrayList<MatchingDTO>();
			ArrayList<CampaginDTO> arrList = new ArrayList<CampaginDTO>();

			// 로그인 세션 정보가 없으면 로그인 페이지로 이동
			if (info == null) {
				out.println(
						"<script>alert('Try To Login!'); location.href='./klorofil-free-dashboard-template-v2.0/page-login.jsp?value=adver'; </script>");
				out.flush();
			} else {

				list = mdao.myPage(info.getAdver_mbr());// info.getMem_id()
				matList = matDao.MachingInfluencerSuccess(2);
				arrList = cdao.campaign_adver_Select(info.getAdver_mbr());

				System.out.println("확인 : ============== " + matList.get(0).getInflu_id());
				if (list != null) {
					System.out.println("확인 : 0000 ");
					session.setAttribute("matchingadv12345", "matching"); 
					session.setAttribute("adverMypage", list);
					session.setAttribute("adverMypageMatching2", matList);
					session.setAttribute("adverCampaignSelect", arrList);
					response.sendRedirect("./klorofil-free-dashboard-template-v2.0/mypage_ad.jsp");

				} else {
					System.out.println("전송실패!");
				}

			}
			
			
			

		 
		 
	}

}

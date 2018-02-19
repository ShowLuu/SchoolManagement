package com.uu.show.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.uu.show.entity.Score;
import com.uu.show.service.ScoreService;

@WebServlet(name ="score", urlPatterns = "/score")
public class ScoreController extends HttpServlet{

	private static final long serialVersionUID = 1L;

	private ScoreService ScoreService = new ScoreService();

	private String insert="";
	private String delete="";
	private String update="";
	private String query="";
	private String byID="";
	private String operator="";
	
	private String scoreID="";
	private String scoreStuID="";
	private String scoreStuName="";
	private String scoreSubID="";
	private String scoreSubName="";
	private String scoreSubScore="";
	private String scoreTime="";
	private String scoreRemark="";
	
	private String back="";
	private String likeQuery="";
	private String button="";
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		scoreID = req.getParameter("scoreID");
		scoreStuID = req.getParameter("scoreStuID");
		scoreStuName = req.getParameter("scoreStuName");
		scoreSubID = req.getParameter("scoreSubID");
		scoreSubName = req.getParameter("scoreSubName");
		scoreSubScore = req.getParameter("scoreSubScore");
		scoreTime = req.getParameter("scoreTime");
		scoreRemark = req.getParameter("scoreRemark");
		
		operator=req.getParameter("operator");
		byID=req.getParameter("byID");
		
		insert=req.getParameter("insert");
		delete=req.getParameter("delete");
		update=req.getParameter("update");
		query=req.getParameter("query");
		back=req.getParameter("back");
		likeQuery=req.getParameter("likeQuery");
		
		 if(insert!=null){
		     button=insert;
		 }else if(delete!=null){
			 button=delete;
		 }else if(update!=null){
			 button=update;
		 }else if(query!=null){
			 button=query;
		 }else if(back!=null){
			 button=back;
		 }else if(likeQuery!=null){
			 button=likeQuery;
		 }else if(operator!=null){
			 button=operator;
		 }else if(byID!=null){
			 button=byID;
		 }
		 
		switch (button) {
		case "新增":
			ClsInsert(req, resp);
			break;
		case "删除":
			ClsDelete(req, resp);
			break;
		case "修改":
			ClsUpdate(req, resp);
			break;
		case "模糊查询":
			ClsLikeQuery(req, resp);
			break;
		case "返回":
			initLogin(req, resp);
			break;
		case "查找":
			ClsLikeQueryID(req, resp);
			break;
		case "init":
			init(req, resp);
			break;

		default:
			break;
		}

	}

	private void initLogin(HttpServletRequest req, HttpServletResponse resp) {
		try {
			List<Score> cls = ScoreService.findAll();
			req.setAttribute("score", cls);
			req.getRequestDispatcher("/WEB-INF/views/score/init.jsp").forward(req, resp);
			return;
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	private void init(HttpServletRequest req, HttpServletResponse resp) {
		try {
			List<Score> cls = ScoreService.findAll();
			req.setAttribute("score", cls);
			req.getRequestDispatcher("/WEB-INF/views/score/init.jsp").forward(req, resp);
			return;
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void ClsInsert(HttpServletRequest req, HttpServletResponse resp) {
		String scoreIDs = scoreID.replace(" ", "");
		String scoreStuIDs = scoreStuID.replace(" ", "");
		String scoreStuNames = scoreStuName.replace(" ", "");
		String scoreSubIDs = scoreSubID.replace(" ", "");
		String scoreSubNames = scoreSubName.replace(" ", "");
		String scoreSubScores = scoreSubScore.replace(" ", "");
		String scoreTimes = scoreTime.replace(" ", "");
		String scoreRemarks = scoreRemark.replace(" ", "");
		
		String erroMsgscore = "";
		boolean flag = false;

		Score cls = ScoreService.findByScoreID(scoreID);

		if (!scoreIDs.equals("")) {
			if (cls==null) {
				flag=true;
			} else {
				erroMsgscore = "学生已存在";
			}
		} else {
			erroMsgscore = "请填入学生学号新增";
		}
		if (flag) {
			Score clss=new Score();
			clss.setScoreID(scoreIDs);
			clss.setScoreStuID(scoreStuIDs);
			clss.setScoreStuName(scoreStuNames);
			clss.setScoreSubID(scoreSubIDs);
			clss.setScoreSubName(scoreSubNames);
			clss.setScoreSubScore(scoreSubScores);
			clss.setScoreTime(scoreTimes);
			clss.setScoreRemark(scoreRemarks);
			System.out.println(scoreIDs + "--1");
			ScoreService.insert(clss);
			initLogin(req, resp);
		} else {
			req.setAttribute("erroMsgscore", erroMsgscore);
			initLogin(req, resp);
		}
	}

	private void ClsDelete(HttpServletRequest req, HttpServletResponse resp) {
		String scoreIDs = scoreID.replace(" ", "");
		Score cls = ScoreService.findByScoreID(scoreIDs);
		String erroMsgscore = "";
		if (cls!=null) {
		ScoreService.deletes(scoreIDs);
		initLogin(req, resp);
		}else {
			erroMsgscore = "学生不存在";
			req.setAttribute("erroMsgscore", erroMsgscore);
			initLogin(req, resp);
		}
	}

	private void ClsUpdate(HttpServletRequest req, HttpServletResponse resp) {
		String scoreIDs = scoreID.replace(" ", "");
		String scoreStuIDs = scoreStuID.replace(" ", "");
		String scoreStuNames = scoreStuName.replace(" ", "");
		String scoreSubIDs = scoreSubID.replace(" ", "");
		String scoreSubNames = scoreSubName.replace(" ", "");
		String scoreSubScores = scoreSubScore.replace(" ", "");
		String scoreTimes = scoreTime.replace(" ", "");
		String scoreRemarks = scoreRemark.replace(" ", "");
		String erroMsgscore = "";
		Score clsx = ScoreService.findByScoreID(scoreIDs);
		if (!scoreIDs.equals("")) {
			if (clsx!=null) {
				clsx.setScoreID(scoreIDs);
				clsx.setScoreStuID(scoreStuIDs);
				clsx.setScoreStuName(scoreStuNames);
				clsx.setScoreSubID(scoreSubIDs);
				clsx.setScoreSubName(scoreSubNames);
				clsx.setScoreSubScore(scoreSubScores);
				clsx.setScoreTime(scoreTimes);
				clsx.setScoreRemark(scoreRemarks);
				ScoreService.update(clsx);
				initLogin(req, resp);
			}else {
				erroMsgscore = "学生不存在";
				req.setAttribute("erroMsgscore", erroMsgscore);
				initLogin(req, resp);
			}
		}else {
			erroMsgscore = "请填入学生学号修改";
			req.setAttribute("erroMsgscore", erroMsgscore);
			initLogin(req, resp);
		}
	}

	private void ClsLikeQuery(HttpServletRequest req, HttpServletResponse resp) {
		String scoreStuIDs = scoreStuID.replace(" ", "");
		try {
			if(!scoreStuIDs.equals("")){
				Score clsx = new Score();
				clsx.setScoreStuID(scoreStuIDs);
				List<Score> cls=ScoreService.preQuery(clsx);
				if(cls.isEmpty()){
					String erroMsgscore = "信息无匹配";
					req.setAttribute("erroMsgscore", erroMsgscore);
					initLogin(req, resp);
				}
				req.setAttribute("score", cls);
				req.getRequestDispatcher("/WEB-INF/views/score/likeQuery.jsp").forward(req, resp);
				return;
			}
		} catch (ServletException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			if(!scoreStuName.equals("")){
				Score clsx = new Score();
				clsx.setScoreStuName(scoreStuName);
				List<Score> cls=ScoreService.preQuery(clsx);
				if(cls.isEmpty()){
					String erroMsgscore = "信息无匹配";
					req.setAttribute("erroMsgscore", erroMsgscore);
					initLogin(req, resp);
				}
				req.setAttribute("score", cls);
				req.getRequestDispatcher("/WEB-INF/views/score/likeQuery.jsp").forward(req, resp);
			}
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}try {
			if(!scoreSubID.equals("")){
				Score clsx = new Score();
				clsx.setScoreStuName(scoreStuName);
				List<Score> cls=ScoreService.preQuery(clsx);
				if(cls.isEmpty()){
					String erroMsgscore = "信息无匹配";
					req.setAttribute("erroMsgscore", erroMsgscore);
					initLogin(req, resp);
				}
				req.setAttribute("score", cls);
				req.getRequestDispatcher("/WEB-INF/views/score/likeQuery.jsp").forward(req, resp);
			}
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}try {
			if(!scoreSubName.equals("")){
				Score clsx = new Score();
				clsx.setScoreStuName(scoreStuName);
				List<Score> cls=ScoreService.preQuery(clsx);
				if(cls.isEmpty()){
					String erroMsgscore = "信息无匹配";
					req.setAttribute("erroMsgscore", erroMsgscore);
					initLogin(req, resp);
				}
				req.setAttribute("score", cls);
				req.getRequestDispatcher("/WEB-INF/views/score/likeQuery.jsp").forward(req, resp);
			}
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}try {
			if(!scoreSubScore.equals("")){
				Score clsx = new Score();
				clsx.setScoreStuName(scoreStuName);
				List<Score> cls=ScoreService.preQuery(clsx);
				if(cls.isEmpty()){
					String erroMsgscore = "信息无匹配";
					req.setAttribute("erroMsgscore", erroMsgscore);
					initLogin(req, resp);
				}
				req.setAttribute("score", cls);
				req.getRequestDispatcher("/WEB-INF/views/score/likeQuery.jsp").forward(req, resp);
			}
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}try {
			if(!scoreTime.equals("")){
				Score clsx = new Score();
				clsx.setScoreStuName(scoreStuName);
				List<Score> cls=ScoreService.preQuery(clsx);
				if(cls.isEmpty()){
					String erroMsgscore = "信息无匹配";
					req.setAttribute("erroMsgscore", erroMsgscore);
					initLogin(req, resp);
				}
				req.setAttribute("score", cls);
				req.getRequestDispatcher("/WEB-INF/views/score/likeQuery.jsp").forward(req, resp);
			}
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}try {
			if(!scoreRemark.equals("")){
				Score clsx = new Score();
				clsx.setScoreStuName(scoreStuName);
				List<Score> cls=ScoreService.preQuery(clsx);
				if(cls.isEmpty()){
					String erroMsgscore = "信息无匹配";
					req.setAttribute("erroMsgscore", erroMsgscore);
					initLogin(req, resp);
				}
				req.setAttribute("score", cls);
				req.getRequestDispatcher("/WEB-INF/views/score/likeQuery.jsp").forward(req, resp);
			}
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void ClsLikeQueryID(HttpServletRequest req, HttpServletResponse resp) {
		Score yuanxix = new Score();
		yuanxix.setScoreID(scoreID);
		List<Score> yuanxi=ScoreService.preQuery(yuanxix);
		if(yuanxi.isEmpty()){
			String erroMsgInsert = "信息无匹配";
			req.setAttribute("erroMsgscore", erroMsgInsert);
			initLogin(req, resp);
		}
		req.setAttribute("score", yuanxi);
		try {
			req.getRequestDispatcher("/WEB-INF/views/score/likeQuery.jsp").forward(req, resp);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}

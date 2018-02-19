package com.uu.show.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.uu.show.entity.Subject;
import com.uu.show.entity.Yuanxi;
import com.uu.show.service.SubjectService;

@WebServlet(name ="subject", urlPatterns = "/subject")
public class SubjectController extends HttpServlet{
	
	private static final long serialVersionUID = 1L;

	private SubjectService subjectService = new SubjectService();

	private String insert="";
	private String delete="";
	private String update="";
	private String query="";
	private String byID="";
	private String operator="";
	
	private String subID="";
	private String subName="";
	private String subRemark="";
	private String back="";
	private String likeQuery="";
	private String button="";
	

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		subID = req.getParameter("subID");
		subName = req.getParameter("subName");
		subRemark = req.getParameter("subRemark");
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
			SubjectInsert(req, resp);
			break;
		case "删除":
			SubjectDelete(req, resp);
			break;
		case "修改":
			SubjectUpdate(req, resp);
			break;
		case "查询":
			SubjectQuery(req, resp);
			break;
		case "模糊查询":
			SubjectLikeQuery(req, resp);
			break;
		case "返回":
			initLogin(req, resp);
			break;
		case "查找":
			SubjectLikeQueryID(req, resp);
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
			List<Subject> Subject = subjectService.findAll();
			req.setAttribute("subject", Subject);
			req.getRequestDispatcher("/WEB-INF/views/subject/init.jsp").forward(req, resp);
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
			List<Subject> Subject = subjectService.findAll();
			req.setAttribute("subject", Subject);
			req.getRequestDispatcher("/WEB-INF/views/subject/init.jsp").forward(req, resp);
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

	private void SubjectInsert(HttpServletRequest req, HttpServletResponse resp) {
		String subIDs = subID.replace(" ", "");
		String subNames = subName.replace(" ", "");
		String subRemarks = subRemark.replace(" ", "");
		String erroMsgSubject = "";
		boolean flag = false;

		Subject Subject = subjectService.findBySubjectID(subID);

		if (!subIDs.equals("")) {
			if (Subject==null) {
				flag = true;
			} else {
				erroMsgSubject = "课程已存在";
			}
		} else {
			erroMsgSubject = "请填入课程编号新增";
		}
		if (flag) {
			Subject Subjects=new Subject();
			Subjects.setSubID(subIDs);
			Subjects.setSubName(subNames);
			Subjects.setSubRemark(subRemarks);
			System.out.println(subIDs + "--1");
			subjectService.insert(Subjects);
			initLogin(req, resp);
		} else {
			req.setAttribute("erroMsgSubject", erroMsgSubject);
			initLogin(req, resp);
		}
	}

	private void SubjectDelete(HttpServletRequest req, HttpServletResponse resp) {
		String subIDs = subID.replace(" ", "");
		Subject Subject = subjectService.findBySubjectID(subIDs);
		String erroMsgSubject = "";
		if (Subject!=null) {
		subjectService.deletes(subIDs);
		initLogin(req, resp);
		}else {
			erroMsgSubject = "课程不存在";
			req.setAttribute("erroMsgSubject", erroMsgSubject);
			initLogin(req, resp);
		}
	}

	private void SubjectUpdate(HttpServletRequest req, HttpServletResponse resp) {
		String subIDs = subID.replace(" ", "");
		String subNames = subName.replace(" ", "");
		String subRemarks = subRemark.replace(" ", "");
		String erroMsgSubject = "";
		Subject Subjectx = subjectService.findBySubjectID(subIDs);
		if (!subIDs.equals("")) {
			if (Subjectx!=null) {
				Subjectx.setSubID(subIDs);
				Subjectx.setSubName(subNames);
				Subjectx.setSubRemark(subRemarks);
				subjectService.update(Subjectx);
				initLogin(req, resp);
			}else {
				erroMsgSubject = "课程不存在";
				req.setAttribute("erroMsgSubject", erroMsgSubject);
				initLogin(req, resp);
			}
		}else {
			erroMsgSubject = "请填入课程编号修改";
			req.setAttribute("erroMsgSubject", erroMsgSubject);
			initLogin(req, resp);
		}
	}

	private void SubjectQuery(HttpServletRequest req, HttpServletResponse resp) {
		String subIDs = subID.replace(" ", "");
		String subNames = subName.replace(" ", "");
		String subRemarks = subRemark.replace(" ", "");
		String erroMsgSubject = "";
		boolean flag = false;


		if (subIDs.equals("")) {
				if (!subNames.equals("")) {
					if (!subRemarks.equals("")) {
						flag = true;

					} else {
						flag = true;
					}
				} else {
					erroMsgSubject = "请填入课程名称查询";
				}
		} else {
			erroMsgSubject = "不可填入课程编号查询";
		}
		if (flag) {
			Subject Subjectx=new Subject();
			Subjectx.setSubName(subNames);
			Subjectx.setSubRemark(subRemarks);
			List<Subject> Subject = subjectService.query(Subjectx);
			if(Subject.isEmpty()){
				erroMsgSubject = "信息无匹配";
				req.setAttribute("erroMsgSubject", erroMsgSubject);
				initLogin(req, resp);
			}
			try {
				req.setAttribute("subject", Subject);
				req.getRequestDispatcher("/WEB-INF/views/subject/init.jsp").forward(req, resp);
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
		} else {
			req.setAttribute("erroMsgSubject", erroMsgSubject);
			initLogin(req, resp);
		}
		
	}

	private void SubjectLikeQuery(HttpServletRequest req, HttpServletResponse resp) {
		String subNames = subName.replace(" ", "");
		try {
			if(!subNames.equals("")){
				Subject Subjectx = new Subject();
				Subjectx.setSubName(subNames);
				List<Subject> Subject=subjectService.preQuery(Subjectx);
				if(Subject.isEmpty()){
					String erroMsgSubject = "信息无匹配";
					req.setAttribute("erroMsgSubject", erroMsgSubject);
					initLogin(req, resp);
				}
				System.out.println("q23r4wds");
				req.setAttribute("subject", Subject);
				req.getRequestDispatcher("/WEB-INF/views/subject/likeQuery.jsp").forward(req, resp);
			}
		} catch (ServletException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			if(!subRemark.equals("")){
				Subject Subjectx = new Subject();
				Subjectx.setSubRemark(subRemark);
				List<Subject> Subject=subjectService.preQuery(Subjectx);
				if(Subject.isEmpty()){
					String erroMsgSubject = "信息无匹配";
					req.setAttribute("erroMsgSubject", erroMsgSubject);
					initLogin(req, resp);
				}
				req.setAttribute("subject", Subject);
				req.getRequestDispatcher("/WEB-INF/views/subject/likeQuery.jsp").forward(req, resp);
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
	
	private void SubjectLikeQueryID(HttpServletRequest req, HttpServletResponse resp) {
		Subject yuanxix = new Subject();
		yuanxix.setSubID(subID);
		List<Subject> yuanxi=subjectService.preQuery(yuanxix);
		if(yuanxi.isEmpty()){
			String erroMsgInsert = "信息无匹配";
			req.setAttribute("erroMsgSubject", erroMsgInsert);
			initLogin(req, resp);
		}
		req.setAttribute("subject", yuanxi);
		try {
			req.getRequestDispatcher("/WEB-INF/views/subject/likeQuery.jsp").forward(req, resp);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}

package com.hzu.feirty.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.mail.Store;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.hzu.feirty.contorl.MailReceive;
import com.hzu.feirty.contorl.MailSenter;
import com.hzu.feirty.dao.TeacherDaoImpl;
import com.hzu.feirty.entity.Email;
import com.hzu.feirty.entity.Teacher;
import com.hzu.feirty.utils.ConnUtil;
import com.sun.org.apache.bcel.internal.generic.POP;

public class DoGetTeacher extends HttpServlet {
	private List<Email> maillist;
	
	public DoGetTeacher() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		String action = request.getParameter("action");
		PrintWriter out = response.getWriter();
		JSONObject array = new JSONObject();
		if(action.equals("SAVESET")){
			String name = request.getParameter("name");
			String school = request.getParameter("school");
			String workmail = request.getParameter("workmail");
			String mailpwd = request.getParameter("pwd");
			String user = request.getParameter("user");
			String peasonmail = request.getParameter("peasonmail");
			try {
				Teacher teacher = new Teacher(name,workmail,mailpwd,school,peasonmail,user);
				new TeacherDaoImpl().add(teacher);
				if(MailReceive.getAllMailByNumber(user)){
					array.put("code", "success");
					array.put("msg", "发送成功");
					array.put("data", "");
					System.out.println("教师信息收集完成！");			
				}else{
					array.put("code", "false");
					array.put("msg", "发送失败");
					array.put("data", "");
					System.out.println("学号信息收集失败！");					
				}		
			} catch (Exception e) {
				e.printStackTrace();
				array.put("code", "succ");
				array.put("msg", "发送失败");
				array.put("data", "");
				System.out.println("收集异常失败！");
			}
			
		}
		out.print(array);
		out.flush();
		out.close();
	}

}

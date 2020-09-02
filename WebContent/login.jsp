<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.sql.*" %>
<%@ page import="java.io.*" %>
<html>
 <head>
  <title> jsp page </title>
 </head>
     
     
	<% 
	int flag=0;
	String username=request.getParameter("name");
	String password=request.getParameter("pwd");
	try
	{

	 Class.forName("org.postgresql.Driver");
	 
    Connection con=DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","yashu");
	

    Statement ps=con.createStatement();
	 

	ResultSet rs=ps.executeQuery("select  name,pwd from registeruser");
	
	while(rs.next())
	{
		if(rs.getString(1)!=null && rs.getString(2)!=null)
		{
		if((rs.getString(1).equals(username)) && (rs.getString(2).equals(password)))
		{
			flag=1;
		}
		}
		
	} 
	if(flag==1)
	{
		
		out.println("<meta http-equiv='refresh' content='0; URL=success.html'>");
		//response.sendRedirect("success.html");
        /*out.println("valid user");
        out.println("<h2><a href=\"cop.html\">HOME_PAGE</a></h2>");*/
	}
	else
	{	
		request.setAttribute("error", "<b >User not Found.. Try Again<b>");
		request.getRequestDispatcher("l.jsp").forward(request, response);
		//out.println("Invalid User");
	
	}
	 }
	catch(Exception e)
	{
	    out.println(e);
	    e.printStackTrace();
	}
out.close();
%>
  
</html>

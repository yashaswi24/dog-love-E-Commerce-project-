import java.beans.Statement;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class servlet
 */
@WebServlet("/register")
  
public class register extends HttpServlet {  
public void doPost(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException {  
  
response.setContentType("text/html");  
PrintWriter out = response.getWriter();  
          
String n=request.getParameter("name");  
String a=request.getParameter("age");  
String e=request.getParameter("email");  
String p=request.getParameter("pwd");  
       
Connection con=null;
try{  
Class.forName("org.postgresql.Driver");
con=DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","yashu");	

  System.out.println("Conn established");
 

PreparedStatement ps=con.prepareStatement(  
"insert into registeruser values(?,?,?,?)");  
  
ps.setString(1,n);  
ps.setString(2,a);  
ps.setString(3,e);  
ps.setString(4,p);  
out.print("Inserted");
           System.out.println("Insertded..");
int i=ps.executeUpdate();
if(i>0){ 
out.print("<body bgcolor=green>");
out.print("<br><br><br><br><br>");
out.print(con +" ");
out.print("<h2 align=center>You are successfully registered...</h2>");  
out.print("<br><h2 align=center> <a href=\"l.jsp\" >Click here to login</a></h2>");
out.print("</body>");
}   
          
}catch (Exception e2) {
out.print("<body bgcolor=rgb(0,0,0,0.3)>");
out.print("<br><br><br><br><br>");

out.print("<h2 align=center>Regiatration failed...</h2>");  
out.print("<br><h2 align=center>Click here to <a href=\"reg.html\" >Try again?</a></h2>");
out.print("<body bgcolor=rgb(0,0,0,0.3)>");
}  
          
out.close();  
}  
  
}  
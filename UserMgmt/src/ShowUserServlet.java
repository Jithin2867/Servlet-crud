import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/showdata")
public class ShowUserServlet extends HttpServlet {
	
	public static final String query = "select id,name,email,mobile,dob,city,gender from usermgmt";
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		PrintWriter pw=res.getWriter();
		
		res.setContentType("text/html");
		
		pw.println("<link rel='stylesheet' href='css/bootstrap.css'></link>");
		pw.println("<marquee><h2 class='text-primary'>User Data</h2></marquee>");
		
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(Exception e) {
			e.printStackTrace();
		}
	
		try (Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/newuser","root","root");
				java.sql.PreparedStatement ps = con.prepareStatement(query);){
			
			ResultSet rs=ps.executeQuery();
			pw.println("<div style='margin:auto;width:1000px; margin-top:100px'>");
			pw.println("<table class='table table-hover table-striped table-center'>");
			pw.println("<tr>");
			pw.println("<th>ID</th>");
			pw.println("<th>Name</th>");
			pw.println("<th>Email</th>");
			pw.println("<th>Mobile</th>");
			pw.println("<th>DOB</th>");
			pw.println("<th>City</th>");
			pw.println("<th>Gender</th>");
			pw.println("<th> </th>");
			pw.println("<th> </th>");
			pw.println("</tr>");
			while(rs.next()) {
				
				pw.println("<tr>");
				pw.println("<td>"+rs.getInt(1)+"</td>");
				pw.println("<td>"+rs.getString(2)+"</td>");
				pw.println("<td>"+rs.getString(3)+"</td>");
				pw.println("<td>"+rs.getString(4)+"</td>");
				pw.println("<td>"+rs.getString(5)+"</td>");
				pw.println("<td>"+rs.getString(6)+"</td>");
				pw.println("<td>"+rs.getString(7)+"</td>");
				pw.println("<td><a href='editurl?id="+rs.getInt(1)+"'>Edit</td>");
				pw.println("<td><a href='deleteurl?id="+rs.getInt(1)+"'>Delete</td>");
				pw.println("</tr>");
			}
			pw.println("</table>");
			
	}catch(SQLException se) {
		pw.println("<h2 class='bg-danger text-light text-center'>"+se.getMessage()+"</h2>");
	}catch(Exception e) {
		e.printStackTrace();
	}
		pw.println("<a href='index.html'><button class='btn btn-outline-success'>Home</button></a>");
		pw.println("</div>");
		pw.close();
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	doGet(req,res);
	}
	

}

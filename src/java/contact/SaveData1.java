/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contact;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author harmeet singh
 */
@WebServlet(name = "SaveData1", urlPatterns = {"/SaveData1"})
public class SaveData1 extends HttpServlet {
public void doGet(HttpServletRequest req, HttpServletResponse res)throws IOException, ServletException{
        res.setContentType("text/html");
        PrintWriter out=res.getWriter();
        
        RequestDispatcher rd1=req.getRequestDispatcher("contact.html");
        
       
        String name=req.getParameter("name");
        String email=req.getParameter("email");
        String phone=req.getParameter("phone");
        String course=req.getParameter("course");
        String mode=req.getParameter("mode");
        String qualification=req.getParameter("qualification");
        String message=req.getParameter("message");
        
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/naurupa_technologies", "root", "root");
            Statement stat=conn.createStatement();
            stat.execute("insert into enroll(name, email, phone, course, mode, qualification, message) values('"+name+"', '"+email+"', '"+phone+"', '"+course+"', '"+mode+"', '"+qualification+"', '"+message+"')");
            rd1.include(req, res);
            out.println("<br> <h1>User Registered Successfully!!</h1>");
        }
        catch(Exception e){
            out.println("Not connected to database!!"+e);
        }
    }
}

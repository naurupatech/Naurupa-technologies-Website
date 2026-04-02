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
@WebServlet(name = "SaveData", urlPatterns = {"/SaveData"})
public class SaveData extends HttpServlet {
public void doGet(HttpServletRequest req, HttpServletResponse res)throws IOException, ServletException{
        res.setContentType("text/html");
        PrintWriter out=res.getWriter();
        
        RequestDispatcher rd1=req.getRequestDispatcher("contact.html");
        
       
        String name=req.getParameter("n1");
        String email=req.getParameter("n2");
        String phone=req.getParameter("n3");
        String msg=req.getParameter("n4");
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/naurupa_technologies", "root", "root");
            Statement stat=conn.createStatement();
            stat.execute("insert into contact(name, email, phone, message) values('"+name+"', '"+email+"', '"+phone+"', '"+msg+"')");
            rd1.include(req, res);
            out.println("<br> <h1>User Registered Successfully!!</h1>");
        }
        catch(Exception e){
            out.println("Not connected to database!!"+e);
        }
    }
}

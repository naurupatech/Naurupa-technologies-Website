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
import java.sql.PreparedStatement;
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
@WebServlet(name = "SaveData1", urlPatterns = { "/SaveData1" })
public class SaveData1 extends HttpServlet {
    // public void doGet(HttpServletRequest req,
    // Changed to doPost Accordingly for privacy over data
public void doPost(HttpServletRequest req,
HttpServletResponse res)throws IOException, ServletException{

        res.setContentType("text/html");
        PrintWriter out=res.getWriter();
        
        RequestDispatcher rd1= req.getRequestDispatcher("enroll.html"); //

        
       
        String name=req.getParameter("name");
        String email=req.getParameter("email");
        String phone=req.getParameter("phone");
        String course=req.getParameter("course");
        String mode=req.getParameter("mode");
        String qualification=req.getParameter("qualification");
        String message=req.getParameter("message");
        

        
        try(

            // Class.forName("com.mysql.jdbc.Driver"); //from JDBC 4.0 on, this is not required

            Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/naurupa_technologies", "root", "root");


            //  Statement stat=conn.createStatement(); //Not recommended for security reasons and optimization concerns, use PreparedStatement instead to prevent SQL injection

            // stat.execute("insert into enroll(name, email, phone, course, mode, qualification, message) values('"+name+"', '"+email+"', '"+phone+"', '"+course+"', '"+mode+"', '"+qualification+"', '"+message+"')");


            PreparedStatement stat = conn.prepareStatement("insert into enroll(name, email, phone, course, mode, qualification, message) values(?, ?, ?, ?, ?, ?, ?)"); // Added the prepared statement with placeholders for parameters inorder to prevent SQL injection and improve performance by allowing the database to optimize the query execution plan.

        ){
            

            stat.setString(1, name);
            stat.setString(2, email);
            stat.setString(3, phone);
            stat.setString(4, course);
            stat.setString(5, mode);
            stat.setString(6, qualification);
            stat.setString(7, message);

            
           int rowsUpdates =  stat.executeUpdate(); // Changed from execute to executeUpdate for better clarity and to indicate that this 
            // is an update operation (inserting data) rather than a query execution.

           rd1.include(req, res); // This line includes the content of "enroll.html" in the response, allowing the user to see the form again after submission. It is used to provide feedback to the user and allow them to submit another entry if needed.

           
            if(rowsUpdates > 0){

              out.println("<br> <h1>User Registered Successfully!!</h1>");
        
            } else {
                out.println("<br> <h1>Failed to register user. Please try again.</h1>");
            }
        }

        catch(Exception e){
            out.println("An Error Occured! "+e);
            e.printStackTrace();


        }
    }
}

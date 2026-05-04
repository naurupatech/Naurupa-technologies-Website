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
@WebServlet(name = "SaveData", urlPatterns = {"/SaveData"})
public class SaveData extends HttpServlet {

  private static final String DB_URL = "jdbc:mysql://localhost:3306/naurupa_technologies";
  private static final String DB_USER = "root";
  private static final String DB_PASS = "root";


public void doPost(HttpServletRequest req, HttpServletResponse res)throws IOException, ServletException{

        res.setContentType("text/html");
        PrintWriter out=res.getWriter();
        
        RequestDispatcher rd1 = req.getRequestDispatcher("contact.html");
        
       
        String name=req.getParameter("name");
        String email=req.getParameter("email");
        String phone=req.getParameter("phone");
        String msg=req.getParameter("msg");
        
        try(
            Connection conn=DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            PreparedStatement statement =conn.prepareStatement("insert into contact(name, email, phone, message) values(?, ?, ?, ?)");

        ){
            statement.setString(1, name);
            statement.setString(2, email);
            statement.setString(3, phone);
            statement.setString(4, msg);


            int rowsUpdates =  statement.executeUpdate();

            rd1.include(req, res);

              if(rowsUpdates>0){
            out.println("<br> <h1>User Registered Successfully!!</h1>");
              }else{
            out.println("<br> <h1>Failed to Register User!!</h1>");
              }


        }
        catch(Exception e){
            out.println("An Error Occured! "+e);
            e.printStackTrace();
        }
    }
}

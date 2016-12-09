package project;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Auth extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            HttpSession session = request.getSession();
            String cap1,uname,pass,ans;
            uname=(String) request.getParameter("user");
            pass=(String) request.getParameter("pass");
            cap1 = (String) request.getParameter("cap");
            ans = (String) session.getAttribute("ans1");
            Class.forName("com.mysql.jdbc.Driver");              
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/quiz","root","");  
            PreparedStatement pstmt=con.prepareStatement("select username,password from user1 where username=? and password=?");
            pstmt.setString(1,uname);
            pstmt.setString(2,pass);
            ResultSet rs=pstmt.executeQuery();            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Auth</title>");            
            out.println("</head>");
            out.println("<body>");
            if(!ans.equals(cap1))
            {
               out.println("<div class=\"alert alert-warning\">Please Enter correct Captcha</div>");
               RequestDispatcher requestdispatcher=request.getRequestDispatcher("LoginCaptcha");
               requestdispatcher.include(request,response);
                
            }
            else if("".equals(uname)&&"".equals(pass))
            {
                out.println("<div class=\"alert alert-warning\">Please Enter Username and Password, fields cannot be left blank.</div>");
                RequestDispatcher requestdispatcher=request.getRequestDispatcher("LoginCaptcha");
                requestdispatcher.include(request,response);
            }
            else if(rs.next())
            {
                response.sendRedirect("./Project_Web/QuizSelection.html");
            }
            else
            {
                out.println("<div class=\"alert alert-warning\">Please Enter correct Username and password or register..</div>");
                RequestDispatcher requestdispatcher=request.getRequestDispatcher("LoginCaptcha");
                requestdispatcher.include(request,response);
            }
            out.println("</body>");
            out.println("</html>");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Auth.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Auth.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}


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

public class Register extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String usrname=request.getParameter("username");
            String pass=request.getParameter("password");
            String cnf=request.getParameter("confirm");
            out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n"+
                    "<link rel=\"stylesheet\" type=\"text/css\" href=\"./Project_Web/bootstrap/css/bootstrap.css\">\n"+
                    "<link rel=\"stylesheet\" type=\"text/css\" href=\"./Project_Web/bootstrap/css/bootstrap.min.css\">\n" +
                    "<link rel=\"stylesheet\" type=\"text/css\" href=\"./Project_Web/bootstrap/css/main.css\">\n");
            if(!(pass.equals(cnf)))
            {
               out.println("<div class=\"alert alert-warning\">Please re-confirm your password.</div>");
               RequestDispatcher requestdispatcher=request.getRequestDispatcher("./Project_Web/Registration.html");
               requestdispatcher.include(request,response);
            }
            else
            {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/quiz", "root", "");
            PreparedStatement stmt;
            stmt = con.prepareStatement("insert into user1 values (?,?,?,?)");
            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.setString(3, usrname);
            stmt.setString(4, pass);
            int result=stmt.executeUpdate();
            if(result==1)
            {
               out.println("<div class=\"alert alert-warning\">Registration Successfull, Please Login in using corrcect credentials</div>");
               RequestDispatcher requestdispatcher=request.getRequestDispatcher("LoginCaptcha");
               requestdispatcher.include(request,response);
            }
            else
            {
                out.println("<div class=\"alert alert-warning\">Registration Failed, please try again</div>");
               RequestDispatcher requestdispatcher=request.getRequestDispatcher("LoginCaptcha");
               requestdispatcher.include(request,response);
            }
            stmt.close();
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
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


import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginURLParamSet extends HttpServlet {
 
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            String username=request.getParameter("username1");
            String pwd=request.getParameter("password1");
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {         
           out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"Css.css\">");         
           if(username==null||username.equals("")||pwd==null||pwd.equals(""))
           {
               out.println("<h2>Please enter Username and Password</h2>");
               RequestDispatcher requestdispatcher=request.getRequestDispatcher("/LoginURLParamSet.html");
               requestdispatcher.include(request,response);
               
           }
           else if(username.equals("MCA")&& pwd.equals("mca"))
           {
                out.println("<div id=\"box\">");
                out.print("<center>Logged In Successfully</center>");
                out.print("<br>Clicked below to get Username And Password");
                out.print("<a href='LoginURLParamGet?username="+username+"&password="+pwd+"'> Click Here</a>");
                out.println("</div>");
             
           }
           else
           {
               out.println("<h2>Wrong UserId And Password</h2>");
               RequestDispatcher requestdispatcher=request.getRequestDispatcher("/LoginURLParamSet.html");
               requestdispatcher.include(request,response);
           }
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

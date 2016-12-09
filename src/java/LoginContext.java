
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginContext extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            ServletContext cont;
            cont=getServletContext();
            String login = cont.getInitParameter("User-name");
            String password = cont.getInitParameter("Password");
            String usrnm=request.getParameter("usrname");
            String pass=request.getParameter("pass");
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"Css.css\">");
            out.println("<title>Servlet LoginContext</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginContext at " + request.getContextPath() + "</h1>");
            out.println(" <div id=\"box\">");
            if((usrnm.equals(login))&&(pass.equals(password)))
            {
                out.println("Welcome!</br>Login Successfull");
            }
            else if(("".equals(usrnm))&&("".equals(pass)))
            {
                out.println("Please input Your Login Details");
            }
            else
            {
                out.println("Incorrect Username or Password");
            }
            out.println("<br>");
            out.println("<a href=index.html> Go Back to Index </a>");
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
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

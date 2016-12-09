
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TourServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet tourservlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet tourservlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      String name=request.getParameter("username");
      response.setContentType("text/html");
        try (PrintWriter out = response.getWriter()) {
            out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"Css.css\">");
            out.println(" <div id=\"box\">");
            out.println("<h2>Hidden Field</h2>");
            out.println("Sorry, "+name);
            out.println("Site is down for routine maintainance please visit again later");
            out.println(" </div>");
        }
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

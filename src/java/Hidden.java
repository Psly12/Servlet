
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Hidden extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet hidden</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h2>Servlet hidden at " + request.getContextPath() + "</h2>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name=request.getParameter("Username");
        response.setContentType("text/html");
        try (PrintWriter out = response.getWriter()) {
            out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"Css.css\">");
            out.println(" <div id=\"box\">");
            out.println("<h2>Hidden Field</h2>");
            out.println("<form action=TourServlet>");
            out.println("<center>");
            out.println("Welcome "+name);
            out.println("<input type=hidden name=username value=\""+name+"\">");
            out.println("<br><br><input type=submit value=\"Take a Tour\">");
            out.println("</center>");
            out.println("</form>");
            out.println("</div>");
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


import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Session extends HttpServlet {
    static Integer count;
    public void init()
    {
        count=null;
    }    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            HttpSession session=request.getSession(true);
            String head="";
            if(session.isNew())
            {
                head="This is the new session";
                count=1;
                session.setAttribute("count", count);
            }
            else
            {
                head="This is the old session";
               
                count=(Integer)session.getAttribute("count");
                count++;
                session.setAttribute("count", count);
            }
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Session</title>");
            out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"Css.css\">");            
            out.println("</head>");
            out.println("<body>");
            out.println(" <div id=\"box\">");
            out.println("<h2>"+head+"</h2>");
            out.println("<table border = 1>");
            out.println("<tr><th>Information type</th>");
            out.println("<th>Session ID</th>");
            out.println("<th>Session Count</th></tr>");
            out.println("<tr><th>Total Session Accession</th>");
            out.println("<th>"+session.getId()+"</th>");
            out.println("<th>"+count+"</th></tr>");
            out.println("<table>");
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

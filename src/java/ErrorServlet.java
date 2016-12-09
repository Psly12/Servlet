
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ErrorServlet extends HttpServlet {
final String EXC="javax.servlet.error.exception";
final String MSG="javax.servlet.error.message";
final String ST="javax.servlet.error.status_code";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            doGet(request,response);
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ErrorServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>hello</h1>");
            out.println("<h1>Servlet ErrorServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        ServletContext sc=getServletContext();
        PrintWriter pw=response.getWriter();
        Exception exc=(Exception)request.getAttribute(EXC);
        Integer st_cd=(Integer)request.getAttribute(ST);
        String msg=(String)request.getAttribute(MSG);
        String str=exc.toString()+st_cd.toString()+msg;
        sc.log("Exception occured",exc); 
        pw.println("<html>");
        pw.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"Css.css\">"); 
        pw.println("<body>");
        pw.println("<div id=\"box\">");
        pw.println("<h2>Sorry an error has occured that has prevented the sever from servicing your request</h2>");
        pw.println("<table align=center>");
        pw.println("<tr bgcolor=lightgrey>");
        pw.println("<td>Status Code : </td><td>"+st_cd+"</td>");
        pw.println("</tr>");
        pw.println("<tr>");
        pw.println("<td>Type of Exception : </td><td>"+exc.getClass()+"</td>");
        pw.println("</tr>");
        pw.println("<tr bgcolor=lightgrey");
        pw.println("<br><td>Message Description : </td><td>"+msg+"</td>");        
        pw.print("</tr>");
        pw.println("</table>");
        pw.print("<h2>Please try again</h2><br><a href=\"index.html\">BACK</a>");
        pw.println("</div>");
        pw.println("</body>");
        pw.println("</html>");        
             
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

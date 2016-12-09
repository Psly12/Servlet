import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/Header"})
public class Header extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"Css.css\">");
            out.println("<title>Servlet Header</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h3>Request method : \""+request.getMethod()+"\"</h3>");
            out.println("<h3>Request URI : \""+request.getRequestURL()+"\"</h3>");
            out.println("<h3>Request protocol : \""+request.getProtocol()+"\"</h3>");
            out.println("<center>");
            out.println("<table border=\"1\" style=\"font-weight:bold\">");
            out.println("<tr><th>Header Name</th>");
            out.println("<th>Header Value</th></tr>");
            
            Enumeration headerNames=request.getHeaderNames();
            while(headerNames.hasMoreElements())
            {
                String headerName=(String)headerNames.nextElement();
                out.println("<tr><td>"+headerName+"</td>");
                out.println("<td>"+request.getHeader(headerName)+"</td></tr>");
            }            
            out.println("<tr><td>Content Length</td>");
            out.println("<td>"+request.getContentLength()+"</td></tr>");          
            out.println("<tr><td>Document Root</td>");
            out.println("<td>"+getServletContext().getRealPath("/")+"</td></tr>");            
            out.println("<tr><td>Remote Address</td>");
            out.println("<td>"+request.getRemoteAddr()+"</td></tr>");
            out.println("<tr><td>Remote Host</td>");
            out.println("<td>"+request.getRemoteHost()+"</td></tr>");           
            out.println("<tr><td>Request Method</td>");
            out.println("<td>"+request.getMethod()+"</td></tr>");
            out.println("<tr><td>Script Name</td>");
            out.println("<td>"+request.getServletPath()+"</td></tr>");
            out.println("<tr><td>Server Name</td>");
            out.println("<td>"+request.getServerName()+"</td></tr>");
            out.println("<tr><td>Server Port</td>");
            out.println("<td>"+String.valueOf(request.getServerPort())+"</td></tr>");
            out.println("<tr><td>Server Protocol</td>");
            out.println("<td>"+request.getProtocol()+"</td></tr>");
            out.println("<tr><td>Server Software</td>");
            out.println("<td>"+getServletContext().getServerInfo()+"</td></tr>");
            out.println("</table>");       
            out.println("<br>");
            out.println("<a href=index.html> Go Back to Index </a>");
            out.println("</center>");
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

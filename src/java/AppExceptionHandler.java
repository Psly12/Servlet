
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AppExceptionHandler extends HttpServlet {
 
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Throwable throwable=(Throwable)request.getAttribute("javax.servlet.error.exception");
        Integer statusCode=(Integer)request.getAttribute("javax.servlet.error.status_code");
        String servletName=(String)request.getAttribute("javax.Servlet.error.Servlet_name");
        if(servletName==null){
            servletName="Unknown";
        }
        String requestUri=(String)request.getAttribute("javax.servlet.error.request_uri");
        if(requestUri==null){
            requestUri="Unknown";
        }
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Exception/ErrorDetails</title>");
            out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"Css.css\">");            
            out.println("</head>");
            out.println("<body>");
            out.println(" <div id=\"box\">");
            if(statusCode!=500){
                out.write("<h3>Error Details</h3>");
                out.write("<strong>StatusCode</strong>:"+statusCode+"<br>");
                out.write("<strong>Request URI</strong>:"+requestUri);
            }else{
                out.write("<h3>Exception Details</h3>");
                out.write("<ul><li>Servlet Name:"+servletName+"</li>");
                out.write("<li>Exception Name:"+throwable.getClass().getName()+"</li>");
                out.write("<li>Requested URI:"+requestUri+"</li>");
                out.write("<li>Exception Message:"+throwable.getMessage()+"</li>");
                out.write("</ul>");
            }
            out.write("");
            out.write("<a href=\"index.html\">Back</a>");
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

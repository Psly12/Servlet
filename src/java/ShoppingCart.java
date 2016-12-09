
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShoppingCart extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String str1=request.getParameter("item");
        String str2=request.getParameter("qty");
        String str3=request.getParameter("Add");
        String str4=request.getParameter("List");
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"Css.css\">");
            if(str3!=null)
            {
                Cookie c1=new Cookie(str1,str2);
                response.addCookie(c1);
                response.sendRedirect("ShoppingCart.html");
            }
            else if(str4!=null)
            {
                Cookie clientCookie[]=request.getCookies();
                out.println("<div style=\"width:300px\" id=\"box\">");
                out.println("<h2>Cart</h2>");
                out.println("<center>");
                out.println("<table border=1 style=\"font-size: 25px;\">");
                out.println("<tr><th>Item</th><th>Item Quantity</th></tr>");
                for(int i=0;i<clientCookie.length;i++)
                {
                     out.print("<tr><td><b>"+clientCookie[i].getName()+"<b></td><td>"+clientCookie[i].getValue()+"</td></tr>");
                }
                out.println("</table>");
                out.println("</center>");
                out.println("<br>");
                out.println("<a href=index.html> Go Back to Index </a>");
                out.println("</div>");
            }
            out.close();
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


import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CalExp extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        int firstnum=0,secnum=0,sum=0,sub=0,mul=0;
        float div=0;
        String opr;
        firstnum=Integer.parseInt(request.getParameter("num1"));
        secnum=Integer.parseInt(request.getParameter("num2"));
        opr=request.getParameter("op");          
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet calculator</title>"); 
            out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"Css.css\">");
            out.println("</head>");
            out.println("<body>");
            out.println(" <div id=\"box\">");
            switch (opr) {
                case "+":
                    sum=firstnum+secnum;
                    out.println("<p>Sum of : "+firstnum+" and "+secnum+" is "+sum+"</p>");
                    break;
                case "-":
                    if(firstnum<secnum)
                    {
                        out.println("Please give proper input");
                    }
                    else
                    {
                        sub=firstnum-secnum;
                        out.println("<p>Subtraction of : "+firstnum+" and "+secnum+" is "+sub+"</p>");
                    }   
                    break;
                case "*":
                    mul=firstnum*secnum;
                    out.println("<p>Product of : "+firstnum+" and "+secnum+" is "+mul+"</p>");
                    break;
                case "/":
                    if(secnum==0)
                    {
                        out.println("Divide By Zero Exception");
                    }
                    div=firstnum/secnum;                  
                    out.println("<p>Division of : "+firstnum+" and "+secnum+" is "+div+"</p>");
                    break;
                default:
                    break;
            }
            out.println("<a href=\"Calculator.html\"><input type=\"submit\" value=\"Back\"></a>");
            out.println("</div");
            out.println("</body>");
            out.println("</html>");       
        }
    }
}



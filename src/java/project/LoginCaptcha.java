package project;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
public class LoginCaptcha extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            HttpSession session = request.getSession(true);
            int x = 1 + (int)(Math.random() * ((10 - 1) + 1));
            int y = 1 + (int)(Math.random() * ((10 - 1) + 1));
            int opr = 1 + (int)(Math.random() * ((3 - 1) + 1));
            String operand = null;
            Integer ans=new Integer(0);
            switch(opr)
            {
                case 1:
                {
                   operand="+";
                   ans=x+y;
                   break;
                }
                case 2:
                {
                   operand="*";
                   ans=x*y;
                   break;
                }
                case 3:
                {
                   operand="-";
                   ans=x-y;
                   break;
                }     
            }
            String ansS=String.valueOf(ans);
            out.println("<!DOCTYPE html>");
            out.println("<html>\n" +
                    "<head>\n" +
                        "<title>Login</title>\n" +
                        "<meta charset=\"UTF-8\">\n" +
                        "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
                        "<link rel=\"stylesheet\" type=\"text/css\" href=\"./Project_Web/bootstrap/css/bootstrap.css\">\n" +
                        "<link rel=\"stylesheet\" type=\"text/css\" href=\"./Project_Web/bootstrap/css/bootstrap.min.css\">\n" +
                        "<link rel=\"stylesheet\" type=\"text/css\" href=\"./Project_Web/bootstrap/css/main.css\">\n" +
                    "</head>\n" +
                    "<body>\n" + 
                        "<div class=\"panel-heading\">\n" +
                            "<div class=\"panel-title text-center\">\n" +
                                "<h1>Login Quiz</h1>\n" +
                                "<hr />\n" +
                            "</div>\n" +
                        "</div>" +
                        "<div class=\"modal-dialog\">\n" +
                        "<div class=\"loginmodal-container\">\n" +
                            "<h2>Login</h2>\n" +
                            "<form method=\"post\" action=\"Auth\">\n" +
                                "<input type=\"text\" name=\"user\" placeholder=\"Username\">\n" +
                                "<input type=\"password\" name=\"pass\" placeholder=\"Password\">\n" +
                                "Please Solve This : "+
                                x+" "+operand+" "+y+"\n" +
                                "<input type=\"text\" name=\"cap\" placeholder=\"Captcha\">\n" +
                                "<input type=\"submit\" name=\"login\" class=\"btn btn-primary btn-lg btn-block login-button\" value=\"Login\">\n");
                        session.setAttribute("ans1",ansS);                    
                        out.println("</form>\n" + 
                                " <div class=\"login-help\">\n" +
                                    "<a href=\"../Servlet/Project_Web/Registration.html\">Register</a>\n" +
                        "</div>" +
                        "</div>\n" +
                        "</div>\n");
                      out.println("<script src=\"./Project_Web/bootstrap/js/jquery.js\"></script>");
                       out.println("<script type=\"text/javascript\" src=\"./Project_Web/bootstrap/js/bootstrap.js\"></script>"+
                       "</body>\n" +
                "</html>");
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
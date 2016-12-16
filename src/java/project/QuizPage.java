package project;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class QuizPage extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String subj = null;
        String subject = null;        
        if(null != request.getParameter("j1"))
        {
            subj="j1";
            subject="Java";
        }
        else if(null != request.getParameter("c1"))
        { 
            subj="c1";
            subject="C";
        }
        else if(null != request.getParameter("c2"))
        {
            subj="c2";
            subject="C++";
        }       
        Integer arr[]={1,2,3,4,5,6,7,8,9,10};
        Collections.shuffle(Arrays.asList(arr));
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            Class.forName("com.mysql.jdbc.Driver");              
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/quiz","root","");  
            PreparedStatement pstmtq=con.prepareStatement("select * from submast where sid=?");
            pstmtq.setString(1,subj);
            ResultSet rsq=pstmtq.executeQuery();
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>"+subject+"</title>\n" +
                        "<meta charset=\"UTF-8\">\n" +
                        "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
                        "<link rel=\"stylesheet\" type=\"text/css\" href=\"./Project_Web/bootstrap/css/bootstrap.css\">\n" +
                        "<link rel=\"stylesheet\" type=\"text/css\" href=\"./Project_Web/bootstrap/css/bootstrap.min.css\">\n" +
                        "<link rel=\"stylesheet\" type=\"text/css\" href=\"./Project_Web/bootstrap/css/main.css\">\n");        
            out.println("</head>");
            out.println("<body>");
            out.println("<a href=\"../Servlet/LoginCaptcha\"><button class='btn btn-primary pull-right col-lg-1'>Logout</button></a>");
            out.println("<div class=\"container\">");
            out.println("<div class=\"panel-heading\">\n" +
                            "<div class=\"panel-title text-center\">\n" +
                                "<h1>"+subject+" Quiz</h1>\n" +
                                "<hr />\n" +
                            "</div>\n" +
                        "</div> ");
            out.println("<div class=\"main-login main-quiz\" style=\"padding:40px 40px\">");
            out.println("<form method=\"post\" action=\"./ResultS\" class=\"form-horizontal text-center\">");
            out.println("<input type=\"hidden\" name=\"sub\" value=\""+subj+"\">");
            for(int i=0;i<5;i++)
            {
                out.println("<div class=\"clearfix\">");
                int j=0;
                rsq.absolute(arr[i]);
                PreparedStatement pstmtqn=con.prepareStatement("select * from questmast where qid=?");
                pstmtqn.setString(1,rsq.getString("qid"));
                ResultSet rsqn=pstmtqn.executeQuery();
                while(rsqn.next())
                {
                    out.println("<div class = \"panel panel-primary\">\n" +
                                "<div class = \"panel-heading\">");
                    out.println("<h3 class = \"panel-title\">Q"+(i+1)+") "+rsqn.getString("question")+"</h3></div>");
                    out.println("<div class = \"panel-body\" style=\"text-align:left\">");
                    PreparedStatement pstmtopt=con.prepareStatement("select * from optmast where qid=?");
                    pstmtopt.setString(1,rsq.getString("qid"));
                    ResultSet rsopt=pstmtopt.executeQuery();

                    out.println("<input type=\"hidden\" name=\"qn"+i+"\" value=\""+rsq.getString("qid")+"\">");
                    while(rsopt.next())
                    {                       
                        out.println("<label class=\"check\" for=\"q"+(j+(i*10))+"\" style=\"font-weight: normal\"><input type=\"radio\" id=\"q"+(j+(i*10))+"\" name=\"op"+i+"\" value=\""+rsopt.getString("opid")+"\">");
                        out.println("<span class=\"label-text\">"+rsopt.getString("options")+"</span></label><br>");                        
                        j++;
                    }
                    out.println("</div></div>");
                }
                out.println("</div>");
            }
            out.println("<center><input type=\"submit\" value=\"Submit\" class=\"btn btn-primary btn-lg\" style=\"width:45%\">");
            out.println("<input type=\"reset\" value=\"Reset\" class=\"btn btn-primary btn-lg\" style=\"width:45%\"></center>");
            out.println("</form>");
            out.println("</div>");
            out.println("</div>");
            out.println("<script type=\"text/javascript\" src=\"bootstrap/js/bootstrap.js\"></script>");
            con.close();
            out.println("</body>");
            out.println("</html>");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(QuizPage.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(QuizPage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

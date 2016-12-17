
package project;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ResultS extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String scode=request.getParameter("sub");
        String QnAns[][];
        QnAns=new String[5][2];
        int score = 0;
        int attempt = 5;
        for(int i=0;i<5;i++)
        {   
            String qn=request.getParameter("qn"+i);
            String opn=request.getParameter("op"+i);
            QnAns[i][0]=qn;
            QnAns[i][1]=opn;
            if(opn!=null)
                QnAns[i][1]=opn;
            else
                QnAns[i][1]="";
        }
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            HttpSession se=request.getSession(false);  
            if(se==null || !request.isRequestedSessionIdValid() )
            {   
                out.println("<div class=\"alert alert-warning\">Invalid Session. Please Login First</div>");
                RequestDispatcher requestdispatcher=request.getRequestDispatcher("LoginCaptcha");
                requestdispatcher.include(request,response);
            } 
            Class.forName("com.mysql.jdbc.Driver");              
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/quiz","root","");
            for(int i=0;i<5;i++)
            {
                if(QnAns[i][1].equals(""))
                {
                    attempt--;
                }
                PreparedStatement pstmtoc=con.prepareStatement("select * from ans where qid=? and opid=?");
                pstmtoc.setString(1,QnAns[i][0]);
                pstmtoc.setString(2,QnAns[i][1]);
                ResultSet rsoc=pstmtoc.executeQuery();
                if(rsoc.next())
                    score++;
            }
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<meta charset=\"UTF-8\">\n" +
                        "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
                        "<link rel=\"stylesheet\" type=\"text/css\" href=\"./Project_Web/bootstrap/css/bootstrap.css\">\n" +
                        "<link rel=\"stylesheet\" type=\"text/css\" href=\"./Project_Web/bootstrap/css/main.css\">");                        
            out.println("<title>Result</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<a href=\"../Servlet/Logout\"><button class='btn btn-primary pull-right col-lg-1'>Logout</button></a>");
            out.println("<div class=\"container\">");
            out.println("<div class=\"panel-heading\">\n" +
                            "<div class=\"panel-title text-center\">\n" +
                                "<h1>Results</h1>\n" +
                                "<hr />\n" +
                            "</div>\n" +
                        "</div> ");
            out.println("<div class=\"main-login main-quiz\" style=\"padding:40px 40px\">");
            out.println("<table class=\"table table-striped\">\n" +
                        "<thead>\n" +
                        "<tr>\n" +
                        "<td>Score</td>\n" +
                        "<td>Attempted</td>\n" +
                        "</tr>\n" +
                        "</thead>\n" +
                        "<tbody>\n" +
                        "<tr>\n" +
                        "<td>"+score+"</td>\n" +
                        "<td>"+attempt+"/5</td>\n" +
                        "</tr>\n" +
                        "</tbody>\n" +
                        "</table>");
            out.println("<div class = \"panel-group \" id = \"accordion\">\n" +
                            "<div class = \"panel panel-primary \">\n" +
                                "<div class = \"panel-heading\">\n" +
                                "<h4 class = \"panel-title\">");
            out.println("<button data-toggle = \"collapse\" data-parent = \"#accordion\" href = \"#collapseOne\" class=\"text-center btn btn-primary btn-block\" id=\"more\">\n" +
                        "<span class=\"glyphicon glyphicon-chevron-down\"></span>Show Details</button></h4>\n" +
                        "</div>");
            out.println("<div id = \"collapseOne\" class = \"panel-collapse collapse\" id=\"details\">\n" +
                            " <div class = \"panel-body\">");
            for(int i=0;i<5;i++)
            {
                out.println("<div class=\"clearfix\">");              
                PreparedStatement pstmtqn=con.prepareStatement("select * from questmast where qid=?");
                pstmtqn.setString(1,QnAns[i][0]);
                ResultSet rsqn=pstmtqn.executeQuery();
                while(rsqn.next())
                {
                    out.println("<div class = \"panel panel-primary\">\n" +
                                "<div class = \"panel-heading\">");
                    out.println("<h3 class = \"panel-title\">Q"+(i+1)+") "+rsqn.getString("question")+"</h3></div>");
                    out.println("<div class = \"panel-body\" style=\"text-align:left\">");
                    PreparedStatement pstmtopt=con.prepareStatement("select * from optmast where qid=?");
                    pstmtopt.setString(1,QnAns[i][0]);
                    ResultSet rsopt=pstmtopt.executeQuery();                   
                    while(rsopt.next())
                    {   
                        String status;
                        if(QnAns[i][1].equals(rsopt.getString("opid")))
                            status="checked";
                        else
                            status="disabled";
                        PreparedStatement pstmta=con.prepareStatement("select * from ans where qid=? and opid=?");
                        if(QnAns[i][1].equals(rsopt.getString("opid")))
                        {
                            pstmta.setString(1,QnAns[i][0]);
                            pstmta.setString(2,rsopt.getString("opid"));
                            ResultSet rsa=pstmta.executeQuery();
                            if(rsa.next())
                            {
                                out.println("<label class=\"check\" style=\"font-weight: normal\"><input type=\"radio\" name=\"op"+i+"\" value=\""+rsopt.getString("opid")+"\" "+status+">");
                                out.println("<span class=\"label-text\" style=\"color:green\">"+rsopt.getString("options")+" - <b>Correct!<b></span></label><br>");
                            }
                            else
                            {
                                out.println("<label class=\"check\" style=\"font-weight: normal\"><input type=\"radio\" name=\"op"+i+"\" value=\""+rsopt.getString("opid")+"\" "+status+">");
                                out.println("<span class=\"label-text\" style=\"color:red\">"+rsopt.getString("options")+" - <b>Incorrect!<b></span></label><br>");
                            }
                        }
                        else
                        {
                            pstmta.setString(1,QnAns[i][0]);
                            pstmta.setString(2,rsopt.getString("opid"));
                            ResultSet rsa=pstmta.executeQuery();
                            if(rsa.next())
                            {
                                out.println("<label class=\"check\" style=\"font-weight: normal\"><input type=\"radio\" name=\"op"+i+"\" value=\""+rsopt.getString("opid")+"\" "+status+">");
                                out.println("<span class=\"label-text\" style=\"color:blue\">"+rsopt.getString("options")+" - <b>Correct Option!<b></span></label><br>");
                            }
                            else
                            {
                                out.println("<label class=\"check\" style=\"font-weight: normal\"><input type=\"radio\" name=\"op"+i+"\" value=\""+rsopt.getString("opid")+"\" "+status+">");
                                out.println("<span class=\"label-text\">"+rsopt.getString("options")+"</span></label><br>");
                            }
                        }
                    }
                    out.println("</div></div>");
                }
                out.println("</div>");
            }
            out.println("</div></div></div></div>");//detail end
            out.println("<button class=\"btn btn-primary btn-lg btn-block\" data-toggle=\"modal\" data-target=\"#myModal\">Retake Quiz</button>"+
                        "<div class=\"modal fade\" id=\"myModal\" tabindex=\"-1\" role=\"dialog\" aria-labelledby=\"myModalLabel\" aria-hidden=\"true\">\n" +
                        "<div class=\"vertical-alignment-helper\">\n" +
                        "<div class=\"modal-dialog vertical-align-center\">\n" +
                        "<div class=\"modal-content\">\n" +
                        "<div class=\"modal-header\">\n" +
                        "<button type=\"button\" class=\"close\" data-dismiss=\"modal\"><span aria-hidden=\"true\">&times;</span><span class=\"sr-only\">Close</span>\n" +
                        "</button>\n" +
                        "<h4 class=\"modal-title\" id=\"myModalLabel\">Retake Quiz</h4>\n" +
                        "</div>\n" +
                        "<div class=\"modal-body\">\n" +
                        "<form method=\"post\" action=\"../Servlet/QuizPage\"><input type=\"submit\" class=\"btn btn-primary btn-lg btn-block\" name=\""+scode+"\" value=\"Same Subject\"></a>" +
                        "<a href=\"../Servlet/QuizSelection\"><input type=\"button\" class=\"btn btn-primary btn-lg btn-block\" value=\"Diffrent Subject\"></a>" +                        
                        "</div>\n" +
                        "<div class=\"modal-footer\">\n" +
                        "<button type=\"button\" class=\"btn btn-default\" data-dismiss=\"modal\">Close</button>\n" +
                        "</div>\n" +
                        "</div>\n" +
                        "</div>\n" +
                        "</div>\n" +
                        "</div>");           
            out.println("</div>");
            out.println("</div>");
            con.close();
            out.println("<script src=\"./Project_Web/bootstrap/js/collapsebtn.js\"></script>");
            out.println("<script src=\"./Project_Web/bootstrap/js/jquery.js\"></script>");
            out.println("<script type=\"text/javascript\" src=\"./Project_Web/bootstrap/js/bootstrap.js\"></script>");
            out.println("</body>");
            out.println("</html>");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ResultS.class.getName()).log(Level.SEVERE, null, ex);
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

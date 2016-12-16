
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
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ResultS extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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
            Class.forName("com.mysql.jdbc.Driver");              
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/quiz","root","");
            for(int i=0;i<5;i++)
            {
                if(QnAns[i][1].equals(""))
                {
                    attempt--;
                }                
            }
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<meta charset=\"UTF-8\">\n" +
                        "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
                        "<link rel=\"stylesheet\" type=\"text/css\" href=\"./Project_Web/bootstrap/css/bootstrap.css\">\n" +
                        "<link rel=\"stylesheet\" type=\"text/css\" href=\"./Project_Web/bootstrap/css/bootstrap.min.css\">\n" +
                        "<link rel=\"stylesheet\" type=\"text/css\" href=\"./Project_Web/bootstrap/css/main.css\">\n");
            out.println("<title>Result</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<a href=\"../Servlet/LoginCaptcha\"><button class='btn btn-primary pull-right col-lg-1'>Logout</button></a>");
            out.println("<div class=\"container\">");
            out.println("<div class=\"panel-heading\">\n" +
                            "<div class=\"panel-title text-center\">\n" +
                                "<h1>Results</h1>\n" +
                                "<hr />\n" +
                            "</div>\n" +
                        "</div> ");
            out.println("<div class=\"main-login main-quiz\" style=\"padding:40px 40px\">");
            out.println("<form method=\"post\" action=\"./ResultS\" class=\"form-horizontal text-center\">");
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
            out.println("<center><input type=\"submit\" value=\"Submit\" class=\"btn btn-primary btn-lg\" style=\"width:45%\">");
            out.println("<input type=\"reset\" value=\"Reset\" class=\"btn btn-primary btn-lg\" style=\"width:45%\"></center>");
            out.println("</form>");
            out.println("</div>");
            out.println("</div>");
            out.println("<script type=\"text/javascript\" src=\"bootstrap/js/bootstrap.js\"></script>");
            con.close();
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

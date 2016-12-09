/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author mca1
 */
public class Calc extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet servlet2</title>");
            out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"Css.css\">");
            out.println("</head>");
            out.println("<body>");
          
            String n1 = request.getParameter("num1");
                String n2 = request.getParameter("num2");
                String opt = request.getParameter("op");
                if(n2.matches("^[A-Za-z]$")||n1.matches("^[A-Za-z]$"))
                {
                    out.println("NumberFormatException");
                    out.println("<br>");
                    out.println("<a href=index.html> Go Back to Index </a>");
                } 
                int a=Integer.parseInt(n1);
                int b=Integer.parseInt(n2);
                if(b==0&&opt.equals("/"))
                {
                    out.println("DivideByZeroException");
                    out.println("<br>");
                    out.println("<a href=index.html> Go Back to Index </a>");
                }
                if(opt.equals("*"))
                        out.println(Integer.parseInt(n1) * Integer.parseInt(n2));
                if(opt.equals("/"))
                        out.println(Integer.parseInt(n1) / Integer.parseInt(n2));
                if(opt.equals("+"))
                        out.println(Integer.parseInt(n1) + Integer.parseInt(n2));
                else if(opt.equals("-"))
                       out.println(Integer.parseInt(n1) - Integer.parseInt(n2));
            out.println("<br>");
            out.println("<a href=index.html> Go Back to Index </a>");
            out.println("</body>");
            out.println("</html>");
            
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

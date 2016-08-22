/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lue.Instagram;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 *
 * @author Lue Infoservices
 */
@WebServlet(name = "FullPostServlet", urlPatterns = {"/fullpost"})
public class FullPostServlet extends HttpServlet {

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
        CloseableHttpClient httpclient = HttpClients.createDefault();
        StringBuffer bufferResponse;
        CloseableHttpResponse response2;
        String fullpostURL = request.getParameter("fullposturl");
        String imgeurl=request.getParameter("imgeurl");
        String postDesc=request.getParameter("postdesc");
        try {
            HttpGet httpGet = new HttpGet(fullpostURL);
            response2 = httpclient.execute(httpGet);
            System.out.println(response2.getStatusLine());
            if (response2.getStatusLine().getStatusCode() != 200) {
                
            }
            System.out.println("POST Response Status:: " + response2.getStatusLine().getStatusCode());
            BufferedReader reader = new BufferedReader(new InputStreamReader(response2.getEntity().getContent()));
            String inputLine;
            bufferResponse = new StringBuffer();

            while ((inputLine = reader.readLine()) != null) {
                bufferResponse.append(inputLine);
            }
            reader.close();
            Document doc = Jsoup.parse(bufferResponse.toString());
           // System.out.println("doc==="+doc);
             //getListOfElements(doc);
             HttpEntity entity2 = response2.getEntity();
            EntityUtils.consume(entity2);
            request.getSession().setAttribute("imgeurl", imgeurl);
            request.getSession().setAttribute("postDesc", postDesc);
            response.sendRedirect("postDetail.jsp");
        } catch (Exception ex) {
            ex.printStackTrace();
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

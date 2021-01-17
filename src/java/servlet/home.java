/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import testdata.DataInfoGet;
import testdata.DataVersion;
import testdata.DataIndex;
import testdata.CmdItem;
import testdata.CmdInfo;
import testdata.NormalCommandParse;
import testdata.DataKeyInfo;
import testdata.ProjectName;
import testdata.VariantName;

import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author yhx
 */
@WebServlet(name = "home", urlPatterns = {"/home"})
public class home extends HttpServlet {

    protected void getTestDataKeyInfo(String tableName, HttpServletRequest request){
        DataInfoGet dataInfo = new DataInfoGet();
        DataKeyInfo pKey = new DataKeyInfo(tableName);

        List<ProjectName> projectName = dataInfo.getProjectName(pKey);
        if (projectName.size() > 0)
        {
            pKey.setProjectName(projectName.get(0).getName());
            List<VariantName> variantName = dataInfo.getVariantName(pKey);

            if (variantName.size() > 0)
            {
                pKey.setVariantName(variantName.get(0).getName());
                List<DataVersion> version = dataInfo.getDataVersion(pKey);

                if (version.size() > 0)
                {
                    pKey.setVersion(version.get(0).getVersion());
                    List<DataIndex> dataIndex = dataInfo.getDataIndex(pKey);

                    int testDataIndexSize = dataIndex.size();
                    if (testDataIndexSize > 0)
                    {
                        if ((testDataIndexSize > 1) && (tableName.equals("bootcpumem_ext"))){
                            request.setAttribute("EnableCalcAvg", "true");
                        }
                        request.setAttribute("ProjectName", projectName);
                        request.setAttribute("VariantName", variantName);
                        request.setAttribute("DataVersion", version);
                        request.setAttribute("DataIndex", dataIndex);
                    }
                }
            }
        }
    }
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
        
        int index = 0;
        String selectParam = request.getParameter("select");
        
        if (selectParam != null)
        {
            index = Integer.valueOf(selectParam);
        }
                       
        String [] jspHome = new String[7];
        
        jspHome[0] = "/WEB-INF/views/boottime.jsp";
        jspHome[1] = "/WEB-INF/views/bootcpumem.jsp";
        jspHome[2] = "/WEB-INF/views/emmcusage.jsp";
        jspHome[3] = "/WEB-INF/views/appprio.jsp";
        jspHome[4] = "/WEB-INF/views/normalcommand.jsp";
        jspHome[5] = "/WEB-INF/views/navcpuusage.jsp";
        
        switch(index)
        {
            case 0:
            {
                getTestDataKeyInfo("boottime_ext", request);                
                break;
            }
            case 1:
            {
                getTestDataKeyInfo("bootcpumem_ext", request);
                break;
            }
            case 2:
            {
                getTestDataKeyInfo("emmcusage_ext", request);
                break;
            }
            case 3:
            {
                getTestDataKeyInfo("appprio_ext", request);
                break;
            }
            case 4:
            {
                NormalCommandParse normalCmd = new NormalCommandParse();
                
                List<CmdInfo> cmdInfos = normalCmd.GetNormalCommands();
                request.setAttribute("CmdInfos", cmdInfos);
                break;
            }
            case 5:
            {
                getTestDataKeyInfo("navcpuusage_ext", request);
                break;
            }
            default:
                break;
        }
        
        if (index < 0 || index > 5)
        {
            index = 0;
        }
        
        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(jspHome[index]);
        
        dispatcher.forward(request, response);
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import testdata.DataKeyInfo;
import testdata.DataIndexHeader;
import testdata.BootTimerParse;
import testdata.DataIndexParse;
import testdata.BootCpuMemParse;
import testdata.EmmcUsageParse;
import testdata.AppPrioParse;
import testdata.NavCpuUsageParser;

/**
 *
 * @author yhx
 */
@WebServlet(name = "TestDataGet", urlPatterns = {"/TestDataGet"})
public class TestDataGet extends HttpServlet {

    protected void commSelectedIndexParse(PrintWriter out, DataKeyInfo indexKey, String tableName, int indexClass){
        DataIndexParse dataIndex = new DataIndexParse();
        String outDataMsg = "error";
        
        switch (tableName) {
            case "boottime":
                indexKey.setTableName("boottime_ext");
                out.println(dataIndex.getTestDataIndex(indexKey, indexClass));
                break;
            case "bootcpumem":
                indexKey.setTableName("bootcpumem_ext");
                out.println(dataIndex.getTestDataIndex(indexKey, indexClass));
                break;
            case "emmcusage":
                indexKey.setTableName("emmcusage_ext");
                out.println(dataIndex.getTestDataIndex(indexKey, indexClass));
                break;
            case "appprio":
                indexKey.setTableName("appprio_ext");
                out.println(dataIndex.getTestDataIndex(indexKey, indexClass));
                break;
            case "navcpuusage":
                indexKey.setTableName("navcpuusage_ext");
                out.println(dataIndex.getTestDataIndex(indexKey, indexClass));
                break;
            default:
                out.println(outDataMsg);
                break;
        }
    }
    protected DataIndexHeader getRequestInputParam(HttpServletRequest request){
        return new DataIndexHeader(request.getParameter("projectname"),
                request.getParameter("projectvariant"),
                request.getParameter("dataversion"),
                request.getParameter("dataindex")
        );
    }
    
    protected DataIndexHeader getRequestInputParamCmp(HttpServletRequest request){
        return new DataIndexHeader(request.getParameter("projectname"),
                request.getParameter("projectvariant"),
                request.getParameter("dataversion"),
                request.getParameter("dataindex"),
                request.getParameter("cmpprojectname"),
                request.getParameter("cmpprojectvariant"),
                request.getParameter("cmpdataversion"),
                request.getParameter("dataCmpIndex"));
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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            
            String dataType = request.getParameter("dataType");
            String outDataMsg = "error";
            
            if (dataType != null){
                // Tools index 
                if (dataType.equals("VariantName")){
                    String indexType   = request.getParameter("indexType");
                    String projectName = request.getParameter("projectname");
                    
                    if (null != indexType && null != projectName){
                        DataKeyInfo indexKey = new DataKeyInfo();

                        indexKey.setProjectName(projectName);
                        commSelectedIndexParse(out, indexKey, indexType, 1); //1 : variant name index
                    }                    
                }
                else if (dataType.equals("Version")){
                    String indexType   = request.getParameter("indexType");
                    String projectName = request.getParameter("projectname");
                    String variantName = request.getParameter("projectvariant");
                    
                    if (null != indexType && null != projectName && null != variantName){
                        DataKeyInfo indexKey = new DataKeyInfo();
                        
                        indexKey.setProjectName(projectName);
                        indexKey.setVariantName(variantName);
                        
                        commSelectedIndexParse(out, indexKey, indexType, 2); //2 : version  index
                    }
                }
                else if (dataType.equals("DataIndex")){
                    String indexType     = request.getParameter("indexType");
                    String projectName   = request.getParameter("projectname");
                    String variantName   = request.getParameter("projectvariant");
                    String version       = request.getParameter("version");
                    
                    if (null != indexType && null != projectName && null != variantName && null != version){
                        DataKeyInfo indexKey = new DataKeyInfo();

                        indexKey.setProjectName(projectName);
                        indexKey.setVariantName(variantName);
                        indexKey.setVersion(version);

                        commSelectedIndexParse(out, indexKey, indexType, 3); //3 : testdata index
                    }
                }
                //System BootTime
                else if (dataType.equals("BootTimeOne")){
                    DataIndexHeader indexHeader = getRequestInputParam(request);
                    if (indexHeader.isVaildParam()){
                        BootTimerParse dataParse = new BootTimerParse();
                        DataKeyInfo key = new DataKeyInfo();
                        key.setTableName("boottime_ext");
                        if (indexHeader.saveToDataKeyInfo(key)){    
                            out.println(dataParse.getDataMsg(key));
                        }
                    }
                }
                else if (dataType.equals("BootTimeTwo")){
                    DataIndexHeader indexHeader = getRequestInputParamCmp(request);
                    if (indexHeader.isVaildParam()){
                        BootTimerParse dataParse = new BootTimerParse();
                        DataKeyInfo key = new DataKeyInfo();
                        key.setTableName("boottime_ext");
                        
                        DataKeyInfo keyCmp = new DataKeyInfo();
                        keyCmp.setTableName("boottime_ext");
                        
                        if (indexHeader.saveToDataKeyInfoCmp(key, keyCmp)){
                            out.println(dataParse.getCmpDataMsg(key, keyCmp));
                        }
                    }
                }
                //System BootCpuMem
                else if (dataType.equals("BootCpuMemOne")){
                    DataIndexHeader indexHeader = getRequestInputParam(request);
                    if (indexHeader.isVaildParam()){
                        BootCpuMemParse dataParse = new BootCpuMemParse();
                        DataKeyInfo key = new DataKeyInfo();
                        key.setTableName("bootcpumem_ext");
                        if (indexHeader.saveToDataKeyInfo(key)){    
                            out.println(dataParse.getDataMsg(key));
                        }
                    }
                }
                else if (dataType.equals("BootCpuMemTwo")){
                    DataIndexHeader indexHeader = getRequestInputParamCmp(request);
                    if (indexHeader.isVaildParam()){
                        BootCpuMemParse dataParse = new BootCpuMemParse();
                        DataKeyInfo key = new DataKeyInfo();
                        key.setTableName("bootcpumem_ext");
                        
                        DataKeyInfo keyCmp = new DataKeyInfo();
                        keyCmp.setTableName("bootcpumem_ext");
                        
                        if (indexHeader.saveToDataKeyInfoCmp(key, keyCmp)){
                            out.println(dataParse.getCmpDataMsg(key, keyCmp));
                        }
                    }
                }
                //System EmmcUsage
                else if (dataType.equals("EmmcUsageOne")){
                    DataIndexHeader indexHeader = getRequestInputParam(request);
                    if (indexHeader.isVaildParam()){
                        EmmcUsageParse dataParse = new EmmcUsageParse();
                        DataKeyInfo key = new DataKeyInfo();
                        key.setTableName("emmcusage_ext");
                        if (indexHeader.saveToDataKeyInfo(key)){    
                            out.println(dataParse.getDataMsg(key));
                        }
                    }
                }
                else if (dataType.equals("EmmcUsageTwo")){
                    DataIndexHeader indexHeader = getRequestInputParamCmp(request);
                    if (indexHeader.isVaildParam()){
                        EmmcUsageParse dataParse = new EmmcUsageParse();
                        DataKeyInfo key = new DataKeyInfo();
                        key.setTableName("emmcusage_ext");
                        
                        DataKeyInfo keyCmp = new DataKeyInfo();
                        keyCmp.setTableName("emmcusage_ext");
                        
                        if (indexHeader.saveToDataKeyInfoCmp(key, keyCmp)){
                            out.println(dataParse.getCmpDataMsg(key, keyCmp));
                        }
                    }                       
                }
                //System appPrio
                else if (dataType.equals("AppPrioOne")){
                    String isAllData = request.getParameter("isAllData");
                    DataIndexHeader indexHeader = getRequestInputParam(request);
                    //System.out.println(indexHeader);
                    //System.out.println("Get appprio: " + isAllData);
                    
                    if (indexHeader.isVaildParam()){
                        //.out.println("Get appprio param ok");
                        AppPrioParse dataParse = new AppPrioParse();
                        DataKeyInfo key = new DataKeyInfo();
                        key.setTableName("appprio_ext");
                        if (indexHeader.saveToDataKeyInfo(key)){
                            out.println(dataParse.getDataMsg(key, isAllData));
                        }
                    }else{
                        //System.out.println("Get appprio param invaild");
                    }
                }
                else if (dataType.equals("AppPrioOneSub")){
                    String processId = request.getParameter("ProcessId");
                    DataIndexHeader indexHeader = getRequestInputParam(request);
                    
                    if ((null != processId) && indexHeader.isVaildParam()){
                        AppPrioParse dataParse = new AppPrioParse();
                        DataKeyInfo key = new DataKeyInfo();
                        key.setTableName("appprio_ext");
                        if (indexHeader.saveToDataKeyInfo(key)){
                            out.println(dataParse.getSubDataMsg(key, processId));
                        }
                    }
                }
                //System NavCpuUsage
                else if (dataType.equals("NavCpuUsageOne")){
                    DataIndexHeader indexHeader = getRequestInputParam(request);
                    if (indexHeader.isVaildParam()){
                        NavCpuUsageParser dataParse = new NavCpuUsageParser();
                        DataKeyInfo key = new DataKeyInfo();
                        key.setTableName("navcpuusage_ext");
                        if (indexHeader.saveToDataKeyInfo(key)){    
                            out.println(dataParse.getDataMsg(key));
                        }
                    }
                }
                else if (dataType.equals("NavCpuUsageTwo")){
                    DataIndexHeader indexHeader = getRequestInputParamCmp(request);
                    if (indexHeader.isVaildParam()){
                        NavCpuUsageParser dataParse = new NavCpuUsageParser();
                        DataKeyInfo key = new DataKeyInfo();
                        key.setTableName("navcpuusage_ext");
                        
                        DataKeyInfo keyCmp = new DataKeyInfo();
                        keyCmp.setTableName("navcpuusage_ext");
                        
                        if (indexHeader.saveToDataKeyInfoCmp(key, keyCmp)){
                            out.println(dataParse.getCmpDataMsg(key, keyCmp));
                        }
                    }
                }
                else {
                    out.println(outDataMsg);
                }
            }
            else{
                out.println(outDataMsg);
            }
            out.flush();
            out.close();
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

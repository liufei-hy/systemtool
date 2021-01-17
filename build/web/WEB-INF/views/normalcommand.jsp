<%-- 
    Document   : normalcommand
    Created on : Dec 6, 2018, 11:20:17 AM
    Author     : HaYang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="public/publicheader.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Normal Command List</title>
        <%@include file="public/publiccss.jsp" %>
    </head>
    <body>
        <div class="container">
            <c:set value="class='active'" var="navCommandStyle"></c:set>
            <%@include file="public/nav.jsp" %>
            <div class="row">
                <div class="col-md-12">
                <c:forEach items="${CmdInfos}" var="cinfo">
                    <h3><b>${cinfo.number}.${cinfo.projectName}</b></h3>
                    <table class="table table-bordered">
                        <tr> <th>Option</th><th>CommandName</th> <th>CommandValue</th> </tr>
                        <c:forEach items="${cinfo.cmdItem}" var="item">
                                <tr>
                                <td><button class="mybtn" data-clipboard-action="copy" data-clipboard-target="#${item.id}"}><img src="<c:url value="/image/copy.png" />" height="20"/> </button> </td>
                                <td>${item.key} </td>
                                <td><div id="${item.id}">${item.value} </div> </td>
                                </tr>
                        </c:forEach>
                    </table>
                </c:forEach>
                </div>
            </div>
        </div>
        <%@include file="public/publicjs.jsp" %>
        <script type="text/javascript" src="clipboard/clipboard.min.js"> </script>
               
        <script type="text/javascript">
            var clipboard = new ClipboardJS('.mybtn');
            
            clipboard.on('sucess', function(e){
                console.log(e);
            });
            
            clipboard.on('error', function(e){
                console.log(e);
            });
        </script>
    </body>
</html>

<%-- 
    Document   : commselected
    Created on : 2019-3-24, 14:59:16
    Author     : Administrator
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="row" >
    <form id="form1" onsubmit="return false" action="##" method="post">
        <div class="col-md-1">
        <select class="combobox form-control" name="projectname" id="dProjectName"  >
            <option value="" selected="selected">Project</option>
            <c:forEach items="${ProjectName}" var="vProjectName">
                <option value="${vProjectName.name}">${vProjectName.name}</option>
            </c:forEach>
        </select>
        </div>

        <div class="col-md-1">
        <select class="combobox form-control" name="projectvariant" id="dPorjectVariant"  >
            <option value="" selected="selected">Variant</option>
            <c:forEach items="${VariantName}" var="vVariantName">
                <option value="${vVariantName.name}">${vVariantName.name}</option>
            </c:forEach>
        </select>
        </div>

        <div class="col-md-3">
        <select class="combobox form-control" name="dataversion" id="dversionSelect"  >
            <option value="" selected="selected">Version</option>
            <c:forEach items="${DataVersion}" var="vVersion">
                <option value="${vVersion.version}">${vVersion.version}</option>
            </c:forEach>
        </select>
        </div>
        <div class="col-md-1">

        <select class="combobox form-control" name="dataindex" id="dindexSelect"  >
            <option value="" selected="selected">Test</option>
            <c:if test="${not empty EnableCalcAvg}">
                <option value="*">AVG(*)</option>
            </c:if>
            <c:forEach items="${DataIndex}" var="vIndex">
                <option value="${vIndex.index}">Test${vIndex.index}</option>
            </c:forEach>
        </select>
        </div>

        <div class="col-md-1">
        <select class="combobox form-control" name="cmpprojectname" id="dcProjectName"  >
            <option value="" selected="selected">cProject</option>
            <c:forEach items="${ProjectName}" var="vProjectName">
                <option value="${vProjectName.name}">${vProjectName.name}</option>
            </c:forEach>
        </select>
        </div>

        <div class="col-md-1">
        <select class="combobox form-control" name="cmpprojectvariant" id="dcPorjectVariant"  >
            <option value="" selected="selected">cVariant</option>
            <c:forEach items="${VariantName}" var="vVariantName">
                <option value="${vVariantName.name}">${vVariantName.name}</option>
            </c:forEach>
        </select>
        </div>

        <div class="col-md-3">
        <select class="combobox form-control" name="cmpdataversion" id="dcVersionSelect"  >
            <option value="" selected="selected">cVersion</option>
            <c:forEach items="${DataVersion}" var="vVersion">
                <option value="${vVersion.version}">${vVersion.version}</option>
            </c:forEach>
        </select>
        </div>

        <div class="col-md-1">
        <select class="combobox form-control" name="dataCmpIndex" id="dindexCmpSelect"  >
            <option value="" selected="selected">cTest</option>
            <c:if test="${not empty EnableCalcAvg}">
                <option value="*">AVG(*)</option>
            </c:if>
            <c:forEach items="${DataIndex}" var="vIndex">
                <option value="${vIndex.index}">Test${vIndex.index}</option>
            </c:forEach>
        </select>
        </div>

        <div class="col-md-6">
            <button type="button" class="btn btn-default" value="Show" onclick="loadData()">Check Data</button>
            <button type="button" class="btn btn-default" value="Compare" onclick="loadDataCompare()">Comp Data</button>
        </div>
    </form>
</div>

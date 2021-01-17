<%-- 
    Document   : nav
    Created on : Nov 29, 2018, 2:47:21 PM
    Author     : HaYang
--%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h1> System Tools </h1>
<nav class="navbar navbar-inverse">
    <ul class="nav navbar-nav">
        <li ${navCommandStyle}>
            <a href="${pageContext.request.contextPath}/home?select=4">Commands</a>
        </li>
        <li ${navBootTimeStyle}>
            <a href="${pageContext.request.contextPath}/home?select=0">BootTime</a>
        </li>
        <li ${navBootCpuMemStyle}>
            <a href="${pageContext.request.contextPath}/home?select=1">BootCpuMem</a>
        </li>
        <li ${navAppPrioStyle}>
            <a href="${pageContext.request.contextPath}/home?select=3">Proc Prio</a>
        </li>
        <li ${navEmmcUsageStyle}>
            <a href="${pageContext.request.contextPath}/home?select=2">EMMC Using</a>
        </li>
        <li ${navNavCpuUsageStyle}>
            <a href="${pageContext.request.contextPath}/home?select=5">Nav Cpu</a>
        </li>
        <li>
            <a href="#">More TestCase</a>
        </li>
    </ul>
</nav>

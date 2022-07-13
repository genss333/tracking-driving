<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
    <nav class="navbar navbar-dark align-items-start sidebar sidebar-dark accordion bg-gradient-primary p-0" style="background: #2d9c74;"><!-- #2d9c74-->
        <div class="container-fluid d-flex flex-column p-0">
            <a class="navbar-brand d-flex justify-content-center align-items-center sidebar-brand m-0" href="#">
                <div class="sidebar-brand-icon"><i class="fas fa-dolly-flatbed"></i></div>
                <div class="sidebar-brand-text mx-3"><span>Live Tracking</span></div>
            </a>
            <hr class="sidebar-divider my-0">
            <ul class="nav navbar-nav text-light" id="accordionSidebar">
                <li class="nav-item"><a class="nav-link active" href="${pageContext.request.contextPath}"><i class="fas fa-chart-line"></i><span>    แดชบอร์ด</span></a></li>
                <li class="nav-item"><a class="nav-link active" href="${pageContext.request.contextPath}/tracking"><i class="fas fa-clipboard-list"></i></i><span>    ติดตามรถส่งพัสดุ</span></a></li>
                <li class="nav-item"><a class="nav-link active" href="${pageContext.request.contextPath}/assignwork"><i class="fas fa-boxes"></i><span>    จัดการพัสดุ</span></a></li>
                <li class="nav-item"><a class="nav-link active" href="${pageContext.request.contextPath}/vehicle"><i class="fas fa-truck"></i><span>    จัดการรถส่งพัสดุ</span></a></li>
                <li class="nav-item"><a class="nav-link active" href="${pageContext.request.contextPath}/driver"><i class="fas fa-users"></i><span>    จัดการพนักงานขับรถ</span></a></li>
                <li class="nav-item"><a class="nav-link active" href="${pageContext.request.contextPath}/customer"><i class="fas fa-users"></i><span>    จัดการผู้รับพัสดุ</span></a></li>
                <li class="nav-item"><a class="nav-link active" href="${pageContext.request.contextPath}/seller"><i class="fas fa-border-all"></i><span>    Seller</span></a></li>
                <li class="nav-item"><a class="nav-link active" href="${pageContext.request.contextPath}/report"><i class="fas fa-vote-yea"></i><span>    Report</span></a></li>
            </ul>
            <div class="text-center d-none d-md-inline"><button class="btn rounded-circle border-0" id="sidebarToggle" type="button"></button></div>
        </div>
    </nav>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
    <nav class="navbar navbar-dark align-items-start sidebar sidebar-dark accordion bg-gradient-primary p-0" style="background: #28231D;"><!-- #2d499c-->
        <div class="container-fluid d-flex flex-column p-0">
            <a class="navbar-brand d-flex justify-content-center align-items-center sidebar-brand m-0" href="${pageContext.request.contextPath}/">
                <div class="sidebar-brand-icon"><i class="fas fa-dolly-flatbed"></i></div>
                <div class="sidebar-brand-text mx-3"><span>Live Tracking</span></div>
            </a>
            <hr class="sidebar-divider my-0">
            <ul class="nav navbar-nav text-light" id="accordionSidebar">
                <li class="nav-item"><a class="nav-link active" href="${pageContext.request.contextPath}/admin"><i class="fas fa-boxes"></i><span>    อนุมัติบริษัทขนส่ง</span></a></li>
            <div class="text-center d-none d-md-inline"><button class="btn rounded-circle border-0" id="sidebarToggle" type="button"></button></div>
        </div>
    </nav>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
    <%@page import="java.util.ArrayList"%>
	<%@page import="misl.spring.model.DriverModel"%>
    <%@page import="misl.spring.model.VehicleModel"%>
    <%
        ArrayList<DriverModel> driverList = (ArrayList<DriverModel>) request.getAttribute("driverList");
        ArrayList<VehicleModel> vehicleList = (ArrayList<VehicleModel>) request.getAttribute("vehicleList");
    %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Dashboard</title>
    <%@ include file="/include/homemapcss.jsp"%>
    <%@ include file="/include/css.jsp"%>
</head>

<body id="page-top">
    <div id="wrapper">
        <!-- import Side Bar -->
        <%@ include file="/include/sidebar1/sidebar.jsp"%>

        <div class="d-flex flex-column" id="content-wrapper">
            <div id="content">
              <!-- import Side Bar -->
            <%@ include file="/include/headerbar.jsp"%>

            <div class="container-fluid">
                <!-- Page Name-->
                <h3 class="text-dark mb-4">Web - Console - Scen1</h3>
                <div class="row">
                    <div class="col">
                        <table style="text-align: center; table-layout: fixed;"
											id="driverData">
											<thead>
												<tr>
                                                    <th>id</th>
                                                    <th>username</th>
                                                    <th>password</th>
                                                    <th>FirstName</th>
                                                    <th>LastName</th>
                                                    <th>Phone</th>
												</tr>
											</thead>
											<tbody>
                                                <%
														for(DriverModel val :driverList){	
														%>
                                                <tr>
                                                    <td><%=val.getDriverId() %></td>
                                                    <td><%=val.getUsername() %></td>
                                                    <td><%=val.getPassword() %></td>
                                                    <td><%=val.getFirstname() %></td>
                                                    <td><%=val.getLastname() %></td>
                                                    <td><%=val.getPhonenumber() %></td>
                                                </tr>
                                                <%} %>
											</tbody>
										</table>
                    </div>
                    <div class="col">
                        <table style="text-align: center; table-layout: fixed;"
											id="driverData">
											<thead>
												<tr>
                                                    <th>id</th>
                                                    <th>ชื่อ</th>
                                                    <th>รุ่น</th>

												</tr>
											</thead>
											<tbody>
                                                <%
														for(VehicleModel val :vehicleList){	
														%>
                                                <tr>
                                                    <td><%=val.getVehicleId()%></td>
                                                    <td><%=val.getName()%></td>
                                                    <td><%=val.getBrand()%></td>
                                                </tr>
                                                <%} %>
											</tbody>
										</table>
                    </div>
                </div>
            </div>
        </div>
        <footer class="bg-white sticky-footer">
            <div class="container my-auto">
                <div class="text-center my-auto copyright"><span>Live Tracking Logistics 2021</span></div>
            </div>
        </footer>
    </div><a class="border rounded d-inline scroll-to-top" href="#page-top"><i class="fas fa-angle-up"></i></a></div>
    <%@ include file="/include/js.jsp"%>
    <%@ include file="/include/homemapfunc.jsp"%>
    <script>
     
    </script>
    
</body>

</html>
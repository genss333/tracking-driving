<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<title>ติดตามรถส่งพัสดุ</title>
<%@ include file="/include/homemapcss.jsp"%>
<%@ include file="/include/css.jsp"%>
</head>

<body id="page-top">
	<div id="wrapper">
		<!-- import Side Bar -->
		<%@ include file="/include/sidebar1/sidebarseller.jsp"%>

		<div class="d-flex flex-column" id="content-wrapper">
			<div id="content">
				<!-- import Side Bar -->
				<%@ include file="/include/headerbar.jsp"%>

				<div class="container-fluid">
					<!-- Page Name-->
					<h3 class="text-dark mb-4">ติดตามรถส่งพัสดุที่ทำงานอยู่ ณ
						ขณะนี้</h3>
					<div class="row">
						<div class="col-md-8">
							<div class="shadow p-3 mb-5 bg-white rounded" id="map"
								style="height: 80vh;"></div>
						</div>
						<div class="col-md-4">
							<div class="card">
								<div class="card-header">Vehicle</div>
								<div class="card-body">
									<div class="table-responsive">
										<table id="datatable-vehicles">
											<thead>
												<tr>
													<th>ID</th>
													<th width="5%"><i class="fas fa-truck"></i> ทะเบียนรถ</th>
													<th><i class="fas fa-truck"></i> รถ</th>
													<th><i class="fas fa-user"></i> คนขับ</th>
													<th><i class="fas fa-map-marker-alt"></i> จุดหมาย</th>
												</tr>
											</thead>
											<tbody>

											</tbody>
										</table>
									</div>
								</div>
							</div>

						</div>

					</div>

				</div>
			</div>
			<footer class="bg-white sticky-footer">
				<div class="container my-auto">
					<div class="text-center my-auto copyright">
						<span>Live Tracking Logistics 2021</span>
					</div>
				</div>
			</footer>
		</div>
		<a class="border rounded d-inline scroll-to-top" href="#page-top"><i
			class="fas fa-angle-up"></i></a>
	</div>
	<%@ include file="/include/js.jsp"%>
	<%@ include file="/include/homemapfunc.jsp"%>
	<script>
        // icon car
		var Iconvehicle = L.icon({ 
			iconUrl: '${pageContext.request.contextPath}/contents/img/mapmarker.png',
			iconSize: [50,50],
			iconAnchor: [25,45],
		});

        var map = L.map('map').setView([16.250149, 103.240739], 15);
		L.tileLayer('http://{s}.tile.osm.org/{z}/{x}/{y}.png', {
			attribution: '&copy; <a href="http://osm.org/copyright">OpenStreetMap</a> contributors'
		}).addTo(map);

        const Tracking = async()=>{
            let vehicle;
            let vehicles=[];
            let j = 0;
			let tempSize = -1;
			let status = 3;
            setInterval( async () => {
                const {data} = await axios.post('${pageContext.request.contextPath}/tracking/maptracking',
				{
					starttime: getTime(-5),
					endtime: getTime(0),
					status:status
				})
				// Check data size
				if(tempSize !== data.length){
						tempSize = data.length
						// reset old data
						if(vehicles[0]){
							console.log("Data Reset !");
							vehicles.map((val,i)=>{
								val.remove()
							})
						}
					}
				// plot all marker
                data.map((val, i)=>{
					//remove old marker
                    if(!vehicles[i]){
                        vehicles[i].remove()
                    }
					console.log(val.locations);
					// plot
						vehicle = L.marker(L.latLng(val.locations[0].latitude,val.locations[0].longitude), { icon: Iconvehicle })
						.bindPopup(val.driver.firstname+" "+val.driver.lastname)
						.openPopup()
						.addTo(map)
						vehicles.splice(i, 1,vehicle)
                })
                console.log("====================")
            }, 5000);
            


            
        }
        const getTime=(sec)=>{
        	var today = new Date();
        		today.setSeconds( today.getSeconds() + sec );
            var date = today.getFullYear()+'-'+(today.getMonth()+1)+'-'+today.getDate()
            var time = today.getHours() + ":" + today.getMinutes() + ":" + today.getSeconds()
            var dateTime = date+' '+time
            return dateTime
        }
        setInterval(() => {
            //console.log(getTime(-5)+" --- "+getTime(0))
        }, 5000);

    
        setTimeout(() => {
            Tracking()
        }, 100);

    </script>

</body>

</html>
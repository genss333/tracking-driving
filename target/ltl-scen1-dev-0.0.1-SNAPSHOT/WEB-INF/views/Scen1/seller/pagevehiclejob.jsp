<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<title>จัดการพัสดุ</title>
<%@ include file="/include/homemapcss.jsp"%>
<%@ include file="/include/css.jsp"%>
<style type="text/css">
.card-counter {
	box-shadow: 2px 2px 10px #DADADA;
	margin: 5px;
	padding: 20px 10px;
	background-color: #fff;
	height: 100px;
	border-radius: 5px;
	transition: .3s linear all;
}

.card-counter:hover {
	box-shadow: 4px 4px 20px #DADADA;
	transition: .3s linear all;
}

.card-counter.primary {
	background-color: #007bff;
	color: #FFF;
}

.card-counter.danger {
	background-color: #ef5350;
	color: #FFF;
}

.card-counter.success {
	background-color: #66bb6a;
	color: #FFF;
}

.card-counter.info {
	background-color: #26c6da;
	color: #FFF;
}

.card-counter i {
	font-size: 5em;
	opacity: 0.2;
}

.card-counter .count-numbers {
	position: absolute;
	right: 35px;
	top: 20px;
	font-size: 32px;
	display: block;
}

.card-counter .count-name {
	position: absolute;
	right: 35px;
	top: 65px;
	font-style: italic;
	text-transform: capitalize;
	opacity: 0.5;
	display: block;
	font-size: 18px;
}
/* Style the tab */
.tab {
	overflow: hidden;
	border: 1px solid #ccc;
	background-color: #f1f1f1;
}

/* Style the buttons inside the tab */
.tab button {
	background-color: inherit;
	float: right;
	border: none;
	outline: none;
	cursor: pointer;
	padding: 14px 16px;
	transition: 0.3s;
	font-size: 17px;
}

/* Change background color of buttons on hover */
.tab button:hover {
	background-color: #ddd;
}

/* Create an active/current tablink class */
.tab button.active {
	background-color: #ccc;
}

/* Style the tab content */
.tabcontent {
	display: none;
	padding: 6px 12px;
	-webkit-animation: fadeEffect 1s;
	animation: fadeEffect 1s;
}

/* Fade in tabs */
@
-webkit-keyframes fadeEffect {
	from {opacity: 0;
}

to {
	opacity: 1;
}

}
@
keyframes fadeEffect {
	from {opacity: 0;
}

to {
	opacity: 1;
}
}
</style>
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
					<h3 class="text-dark mb-4" id="vehicle"></h3>
					<div class="row" style="text-align: left; margin-bottom: 4%">
						<div class="col-md-6 col-xl-4 mb-3">
							<div class="card shadow border-left-success py-2">
								<div class="card-body">
									<div class="row align-items-center no-gutters">
										<div class="col mr-2">
											<div
												class="text-uppercase text-success font-weight-bold text-xs mb-1">
												<span style="font-size: 16px;">งานที่ส่งแล้ว
													(ออเดอร์)</span>
											</div>
											<div class="text-dark font-weight-bold h5 mb-0">
												<span style="font-size: 22px;" id="jobsussecc"></span>
											</div>
										</div>
										<div class="col-auto">
											<i class="fas fa-house-user fa-2x text-gray-300"></i>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-6 col-xl-4 mb-3">
							<div class="card shadow border-left-primary py-2">
								<div class="card-body">
									<div class="row align-items-center no-gutters">
										<div class="col mr-2">
											<div
												class="text-uppercase text-primary font-weight-bold text-xs mb-1">
												<span style="font-size: 16px;">งานทั้งหมด (ออเดอร์)</span>
											</div>
											<div class="text-dark font-weight-bold h5 mb-0">
												<span style="font-size: 22px;" id="joball"></span>
											</div>
										</div>
										<div class="col-auto">
											<i class="fas fa-house-user fa-2x text-gray-300"></i>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-6 col-xl-4 mb-3">
							<div class="card shadow border-left-warning py-2">
								<div class="card-body">
									<div class="row align-items-center no-gutters">
										<div class="col mr-2">
											<div
												class="text-uppercase text-warning font-weight-bold text-xs mb-1">
												<span style="font-size: 16px;">งานที่ยังไม่เสร็จ
													(ออเดอร์)</span>
											</div>
											<div class="text-dark font-weight-bold h5 mb-0">
												<span style="font-size: 22px;" id="jobfault"></span>
											</div>
										</div>
										<div class="col-auto">
											<i class="fas fa-house-user fa-2x text-gray-300"></i>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-6 col-xl-4 mb-3">
							<div class="card shadow border-left-success py-2">
								<div class="card-body">
									<div class="row align-items-center no-gutters">
										<div class="col mr-2">
											<div
												class="text-uppercase text-success font-weight-bold text-xs mb-1">
												<span style="font-size: 16px;">ระยะทางที่วิ่ง
													(กิโลเมตร)</span>
											</div>
											<div class="text-dark font-weight-bold h5 mb-0">
												<span style="font-size: 22px;" id="distancesussecc"></span>
											</div>
										</div>
										<div class="col-auto">
											<i class="fas fa-ruler-combined fa-2x text-gray-300"></i>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-6 col-xl-4 mb-3">
							<div class="card shadow border-left-primary py-2">
								<div class="card-body">
									<div class="row align-items-center no-gutters">
										<div class="col mr-2">
											<div
												class="text-uppercase text-primary font-weight-bold text-xs mb-1">
												<span style="font-size: 16px;">ระยะทางที่คำนวณ
													(กิโลเมตร)</span>
											</div>
											<div class="text-dark font-weight-bold h5 mb-0">
												<span style="font-size: 22px;" id="distanceall"></span>
											</div>
										</div>
										<div class="col-auto">
											<i class="fas fa-ruler-combined fa-2x text-gray-300"></i>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-6 col-xl-4 mb-3">
							<div class="card shadow border-left-warning py-2">
								<div class="card-body">
									<div class="row align-items-center no-gutters">
										<div class="col mr-2">
											<div
												class="text-uppercase text-warning font-weight-bold text-xs mb-1">
												<span style="font-size: 16px;">ระยะทางที่เกินจากการคำนวณ
													(กิโลเมตร)</span>
											</div>
											<div class="text-dark font-weight-bold h5 mb-0">
												<span style="font-size: 22px;" id="distancefault"></span>
											</div>
										</div>
										<div class="col-auto">
											<i class="fas fa-ruler-combined fa-2x text-gray-300"></i>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>

					<ul class="nav nav-tabs" id="myTab" role="tablist">
						<li class="nav-item"><a class="nav-link active" id="home-tab"
							data-toggle="tab" href="#mainmap" role="tab" aria-controls="home"
							aria-selected="true">Polyline</a></li>
						<li class="nav-item"><a class="nav-link" id="profile-tab"
							data-toggle="tab" href="#waypointmap" role="tab"
							aria-controls="profile" aria-selected="false">Waypoint</a></li>

					</ul>

					<div class="tab-content shadow bg-white rounded " id="myTabContent"
						style="height: 70vh; overflow: hidden; margin-bottom: 2%;">
						<div class="tab-pane shadow bg-white rounded  fade show active"
							style="height: 70vh; margin-bottom: 2%" " id="mainmap"
							role="tabpanel"></div>
						<div class="tab-pane shadow  bg-white rounded  fade active"
							style="height: 70vh; margin-bottom: 2%" " id="waypointmap"
							role="tabpanel"></div>
					</div>



					<!-- <div class="row">
						<div class="col-md-12 col-xl-6 mb-2">
							<div class="shadow p-3 mb-5 bg-white rounded " id="mainmap"
								style="height: 50vh;"></div>
						</div>
						<div class="col-md-12 col-xl-6 mb-2">
							<div class="shadow p-3 mb-5 bg-white rounded" id="waypointmap"
								style="height: 50vh;"></div>
						</div>
					</div> -->


					<div class="row">
						<div class="col">
							<div class="card">
								<div class="card-header ">
									<div class="row">
										<div class="col">พัสดุเตรียมนำจ่าย</div>
									</div>
								</div>
								<div class="card-body">
									<div class="table-responsive">
										<table id="datatable-order" style="table-layout: fixed;">
											<thead>
												<tr>
													<th width="5">ลำดับ</th>
													<th width="40">เลขออเดอร์</th>
													<th width="50">ชื่อ</th>
													<th width="50">นามสกุล</th>
													<th width="50">ที่อยู่</th>
													<th width="40">พิกัด</th>
													<th width="10">เบอร์โทร</th>
													<th width="10">สถานะ</th>

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

			<div class="modal fade" id="addLocation" tabindex="-1"
				aria-labelledby="addLocationLabel" aria-hidden="true">
				<div class="modal-dialog modal-lg">
					<div class="modal-content">
						<!-- contance Here -->
						<div class="modal-header">
							<h5 class="modal-title" id="addLocationLabel">กำหนดพิกัด</h5>
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
						</div>

						<div class="modal-body">
							<div class="shadow p-3 mb-5 bg-white rounded" id="maplatlng"
								style="height: 40vh;"></div>
							<div class="row">
								<div class="col text-left">
									<form>
										<div class="row">
											<div class="col-md-6">
												<div class="form-group">
													<label for="swal-name">Latitude </label> <input type="text"
														class="form-control" id="swal-name"
														aria-describedby="swal-carcode" placeholder="lat">
												</div>
											</div>
											<div class="col-md-6">
												<div class="form-group">
													<label for="swal-surname">Longitude </label> <input
														type="text" class="form-control" id="swal-surname"
														aria-describedby="swal-surname" placeholder="lng">
												</div>
											</div>
										</div>

									</form>
								</div>
							</div>
						</div>

						<div class="modal-footer">
							<button type="button" class="btn btn-secondary"
								data-dismiss="modal">ปิด</button>
							<button type="button" class="btn btn-primary">กำหนดพิกัด</button>
						</div>

					</div>
				</div>

			</div>


			<div class="modal fade" id="exampleModal" tabindex="-1"
				aria-labelledby="exampleModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">

						<div class="modal-header">
							<h5 class="modal-title" id="exampleModalLabel">New message</h5>
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
						</div>
						<div class="modal-body">
							<form>
								<div class="form-group">
									<label for="recipient-name" class="col-form-label">Recipient:</label>
									<input type="text" class="form-control" id="recipient-name">
								</div>
								<div class="form-group">
									<label for="message-text" class="col-form-label">Message:</label>
									<textarea class="form-control" id="message-text"></textarea>
								</div>
							</form>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-secondary"
								data-dismiss="modal">ปิด</button>
							<button type="button" class="btn btn-primary">กำหนดพิกัด</button>
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
	
        const colors= [
"#63b598", "#ce7d78", "#ea9e70", "#a48a9e", "#f50422", "#648177" ,"#0d5ac1" ,
"#f205e6" ,"#1c0365" ,"#14a9ad" ,"#4ca2f9" ,"#a4e43f" ,"#d298e2" ,"#6119d0",
"#d2737d" ,"#c0a43c" ,"#f2510e" ,"#651be6" ,"#79806e" ,"#61da5e" ,"#cd2f00" ,
"#9348af" ,"#01ac53" ,"#c5a4fb" ,"#996635","#b11573" ,"#4bb473" ,"#75d89e" ,
"#2f3f94" ,"#2f7b99" ,"#da967d" ,"#34891f" ,"#b0d87b" ,"#ca4751" ,"#7e50a8" ,
"#c4d647" ,"#e0eeb8" ,"#11dec1" ,"#289812" ,"#566ca0" ,"#ffdbe1" ,"#2f1179" ,
"#935b6d" ,"#916988" ,"#513d98" ,"#aead3a", "#9e6d71", "#4b5bdc", "#0cd36d",
"#250662", "#cb5bea", "#228916", "#ac3e1b", "#df514a", "#539397", "#880977",
"#f697c1", "#ba96ce", "#679c9d", "#c6c42c", "#5d2c52", "#48b41b", "#e1cf3b",
"#5be4f0", "#57c4d8", "#a4d17a", "#225b8", "#be608b", "#96b00c", "#088baf",
"#f158bf", "#e145ba", "#ee91e3", "#05d371", "#5426e0", "#4834d0", "#802234",
"#6749e8", "#0971f0", "#8fb413", "#b2b4f0", "#c3c89d", "#c9a941", "#41d158",
"#fb21a3", "#51aed9", "#5bb32d", "#807fb", "#21538e", "#89d534", "#d36647",
"#7fb411", "#0023b8", "#3b8c2a", "#986b53", "#c6e1e8", "#983f7a", "#ea24a3",
"#79352c", "#521250", "#c79ed2", "#d6dd92", "#e33e52", "#b2be57", "#fa06ec",
"#1bb699", "#6b2e5f", "#64820f", "#1c271", "#21538e", "#89d534", "#d36647",
"#7fb411", "#0023b8", "#3b8c2a", "#986b53", "#f50422", "#983f7a", "#ea24a3",
"#79352c", "#521250", "#c79ed2", "#d6dd92", "#e33e52", "#b2be57", "#fa06ec",
"#1bb699", "#6b2e5f", "#64820f", "#1c271", "#9cb64a", "#996c48", "#9ab9b7",
"#06e052", "#e3a481", "#0eb621", "#fc458e", "#b2db15", "#aa226d", "#792ed8",
"#73872a", "#520d3a", "#cefcb8", "#a5b3d9", "#7d1d85", "#c4fd57", "#f1ae16",
"#8fe22a", "#ef6e3c", "#243eeb", "#1dc18", "#dd93fd", "#3f8473", "#e7dbce",
"#421f79", "#7a3d93", "#635f6d", "#93f2d7", "#9b5c2a", "#15b9ee", "#0f5997",
"#409188", "#911e20", "#1350ce", "#10e5b1", "#fff4d7", "#cb2582", "#ce00be",
"#32d5d6", "#17232", "#608572", "#c79bc2", "#00f87c", "#77772a", "#6995ba",
"#fc6b57", "#f07815", "#8fd883", "#060e27", "#96e591", "#21d52e", "#d00043",
"#b47162", "#1ec227", "#4f0f6f", "#1d1d58", "#947002", "#bde052", "#e08c56",
"#28fcfd", "#bb09b", "#36486a", "#d02e29", "#1ae6db", "#3e464c", "#a84a8f",
"#911e7e", "#3f16d9", "#0f525f", "#ac7c0a", "#b4c086", "#c9d730", "#30cc49",
"#3d6751", "#fb4c03", "#640fc1", "#62c03e", "#d3493a", "#88aa0b", "#406df9",
"#615af0", "#4be47", "#2a3434", "#4a543f", "#79bca0", "#a8b8d4", "#00efd4",
"#7ad236", "#7260d8", "#1deaa7", "#06f43a", "#823c59", "#e3d94c", "#dc1c06",
"#f53b2a", "#b46238", "#2dfff6", "#a82b89", "#1a8011", "#436a9f", "#1a806a",
"#4cf09d", "#c188a2", "#67eb4b", "#b308d3", "#fc7e41", "#af3101", "#ff065",
"#71b1f4", "#a2f8a5", "#e23dd0", "#d3486d", "#00f7f9", "#474893", "#3cec35",
"#1c65cb", "#5d1d0c", "#2d7d2a", "#ff3420", "#5cdd87", "#a259a4", "#e4ac44",
"#1bede6", "#8798a4", "#d7790f", "#b2c24f", "#de73c2", "#d70a9c", "#25b67",
"#88e9b8", "#c2b0e2", "#86e98f", "#ae90e2", "#1a806b", "#436a9e", "#0ec0ff",
"#f812b3", "#b17fc9", "#8d6c2f", "#d3277a", "#2ca1ae", "#9685eb", "#8a96c6",
"#dba2e6", "#76fc1b", "#608fa4", "#20f6ba", "#07d7f6", "#dce77a", "#77ecca"]
/* console.log(RGB(255,0,0)); */
                // icon car
		var Iconvehicle = L.icon({ 
			iconUrl: '${pageContext.request.contextPath}/contents/img/mapmarker.png',
			iconSize: [50,50],
			iconAnchor: [25,45],
		});
		var IconHouse = L.icon({ 
			iconUrl: '${pageContext.request.contextPath}/contents/img/house.png',
			iconSize: [30,30],
			//iconAnchor: [20,25],
		});
		var IconWarehouse = L.icon({ 
			iconUrl: '${pageContext.request.contextPath}/contents/img/warehouse.png',
			iconSize: [30,30],
			iconAnchor: [20,25],
		});
        let order;
        
        
        	var mainmap = L.map('mainmap').setView([16.25075881174496, 103.25044379070209], 15);
			L.tileLayer('http://{s}.tile.osm.org/{z}/{x}/{y}.png', {
				attribution: '&copy; <a href="http://osm.org/copyright">OpenStreetMap</a> contributors'
				}).addTo(mainmap);
			
			var waypointmap = L.map('waypointmap').setView([16.25075881174496, 103.25044379070209], 15);
			L.tileLayer('http://{s}.tile.osm.org/{z}/{x}/{y}.png', {
				attribution: '&copy; <a href="http://osm.org/copyright">OpenStreetMap</a> contributors'
				}).addTo(waypointmap);
        $(document).ready( function () {
            order = $('#datatable-order').DataTable();
        })
     
        
        const getorder= async()=>{
        const {data} = await axios.get('${pageContext.request.contextPath}/provider/findroute/${jobsectionId}')
         $(document).ready( function () {
            $("#jobsussecc").html(data[0].countsussessjob);
            $("#joball").html(data[0].countjob);
            $("#jobfault").html(data[0].countjob-data[0].countsussessjob);
            $("#vehicle").html(data[0].vehicle.name);
        })
        let latlngs=[]
        for (let i = 0; i < data[0].order.length-1; i++) {	
            latlngs=[]
            order.row.add([
                `<mark style="background-color:`+colors[i]+`; color:black;">`+data[0].order[i].index+'</mark>',
                data[0].order[i].orderNumber,
                data[0].order[i].firstname,
                data[0].order[i].lastname,
                data[0].order[i].address+", "+data[0].order[i].tambon+", "+data[0].order[i].amphur+", "+data[0].order[i].province,
                data[0].order[i].lat+","+data[0].order[i].lng,
                data[0].order[i].phoneNumber,
                data[0].order[i].status
            ]).draw(false)
            L.marker(L.latLng(data[0].order[i].lat, data[0].order[i].lng), { icon: IconHouse, title: 'Hover Text' }).addTo(mainmap).bindPopup(data[0].order[i].index+": "+data[0].order[i].firstname+" "+data[0].order[i].lastname)
						.openPopup();
						
            latlngs.push(L.latLng(data[0].order[i+1].lat, data[0].order[i+1].lng),L.latLng(data[0].order[i].lat, data[0].order[i].lng))
            L.polyline(latlngs, {color: colors[i+1], dashArray: '10, 10', dashOffset: '0'}).addTo(mainmap)
        }
        latlngs=[]
        latlngs.push(L.latLng(data[0].order[data[0].order.length-1].lat, data[0].order[data[0].order.length-1].lng),L.latLng(data[0].order[0].lat, data[0].order[0].lng))
        L.polyline(latlngs, {color: colors[0], dashArray: '10, 10', dashOffset: '0'}).addTo(mainmap)
        
        L.marker(L.latLng(data[0].order[data[0].order.length-1].lat, data[0].order[data[0].order.length-1].lng), { icon: IconWarehouse, title: 'Hover Text' }).addTo(mainmap).bindPopup(data[0].order[data[0].order.length-1].firstname)
						.openPopup();
						
    }
    const getwaypoint = async()=>{
        const {data} = await axios.get('${pageContext.request.contextPath}/provider/findordermoc/${jobsectionId}')
         $(document).ready( function () {
            $("#distancesussecc").html("0");
            $("#distanceall").html(Math.round(data.result.distance * 1000)/1000);	
            $("#distancefault").html("0");
        })
        data.result.order.map((val,i)=>{
            let latlng=[]
            val.location.map((val,j)=>{
                latlng.push(L.latLng(val[1],val[0]))
            })
            L.marker(L.latLng(val.lat, val.lng), { icon: IconHouse, title: 'Hover Text' }).addTo(waypointmap).bindPopup(val.firstname)
						.openPopup();
            L.polyline(latlng, {color: colors[i], dashArray: '10, 10', dashOffset: '0'}).addTo(waypointmap)
        console.log(latlng);
        })
    }
    setTimeout(() => {
    	order.clear()
    	 	getorder()
    	    getwaypoint()
      }, 1000);
   

    </script>


</body>

</html>
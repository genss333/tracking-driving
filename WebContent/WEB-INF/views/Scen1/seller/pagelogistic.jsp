<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<title>รถขนส่งสินค้า</title>
<%@ include file="/include/homemapcss.jsp"%>
<%@ include file="/include/css.jsp"%>
<style type="text/css">
table {
	color: black;
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
					<h3 class="text-dark mb-4">รถขนพัสดุ</h3>
					<div class="row">
						<div class="col-sm-10" style="position: relative">
							<div class="shadow p-3 mb-5 bg-white rounded" id="mainmap"
								style="height: 70vh;"></div>
						</div>
						<div class="col-sm-2" style="position: relative">
							<div class="shadow p-3 mb-5 bg-white rounded" id="mainmap"
								style="height: 70vh;"></div>
						</div>
					</div>
					<div class="row">
						<div class="col">
							<div class="card">
								<div class="card-header ">
									<div class="row">
										<div class="col">รถนำจ่าย</div>
									</div>
								</div>
								<div class="card-body">
									<div class="table-responsive">
										<table style="text-align: center; table-layout: fixed;"
											id="datatable-jobs">
											<thead>
												<tr>
													<th width="1%">#</th>
													<th width="9">วันที่</th>
													<th width="10">ชื่อรถ</th>
													<th width="10">คนขับ</th>
													<th style="color: green;" width="10">งานที่เสร็จ</th>
													<th style="color: blue;" width="10">งานทั้งหมด</th>
													<th style="color: red;" width="10">งานที่เหลือ</th>
													<th style="color: green;" width="10">ระยะทางที่วิ่ง</th>
													<th style="color: blue;" width="10">ระยะทางที่คำนวณ</th>
													<th style="color: red;" width="10">ระยะทางที่เกิน</th>
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
					<!--   <div id="vehiclejob">
                    <br>
                    <h3 class="text-dark mb-4">งานของรถส่งพัสดุ</h3>
                    <div id="addAllJobs"></div>
                </div> -->
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
						<span>Live Tracking Logistics 2021 </span>
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
        let jobs;
    	$(document).ready( function () {
            jobs = $('#datatable-jobs').DataTable();
        })
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
    				iconUrl: '${pageContext.request.contextPath}/contents/img/truck-moving-solid.svg',
    				iconSize: [30,30],
    				iconAnchor: [20,25],
    			});
    			var IconTruck = L.icon({ 
    				iconUrl: '${pageContext.request.contextPath}/contents/img/truck-R.png',
    				iconSize: [30,30],
    				iconAnchor: [20,25],
    			});
		const markerColor=(color)=>{
			const icon = L.divIcon({
				className: 'custom-div-icon',
				html: `<i class="fas fa-map-marker-alt fa-3x" style="color: `+color+`; text-shadow: 0 0 3px #000;"></i>`,
				iconSize: [100, 100],
				iconAnchor: [15, 35],
				popupAnchor: [0,-40],
    		});
			return icon 
		}
    			
    	let BestExpress1 = [
    		    L.latLng(16.253516, 103.240826),
    		    L.latLng(16.250920, 103.246965),
    		    L.latLng(16.251013, 103.247459),
    		    ]
    	let BestExpress2 = [
		    L.latLng(16.258289472526545, 103.23022783063934),
		    L.latLng(16.264057560537083, 103.24719109303754),
		    L.latLng(16.26511102106275, 103.22965043272329),
		    L.latLng(16.258424808493142, 103.22990304395876),
		    L.latLng(16.250456485926815, 103.21774160454476),
		    L.latLng(16.230468841343225, 103.23175435378366),
		    L.latLng(16.24755834536859, 103.27382282997945),
		    L.latLng(16.25257503925033, 103.23663658342753),
		    L.latLng(16.27138063088057, 103.25507995576814),
		    L.latLng(16.239367395222377, 103.20284441149346),
		    ]
    	let BestExpress3 = [
		    L.latLng(16.244813514907253, 103.24896365764468),
		    L.latLng(16.241987478894337, 103.25576110082044),
		    L.latLng(16.24417256184668, 103.24213586874048),
		    L.latLng(16.24458044130877, 103.25554868072119),
		    L.latLng(16.249970197594813, 103.2515733902925),
		    L.latLng(16.245512734045136, 103.26152678922843),
		    L.latLng(16.250932290411146, 103.23952603545254),
		    L.latLng(16.254544783441325, 103.25081464669717),
		    L.latLng(16.233001108942055, 103.25999699187363),
		    L.latLng(16.23072849319561, 103.24889045439103),
		    ]
    	
    	var mainmap = L.map('mainmap').setView([16.250149, 103.240739], 14);
		L.tileLayer('http://{s}.tile.osm.org/{z}/{x}/{y}.png', {
			attribution: '&copy; <a href="http://osm.org/copyright">OpenStreetMap</a> contributors'
		}).addTo(mainmap);
		let order;
		let latlngs =[];

		const getorder= async()=>{
			
			 
			BestExpress1.map((val,i) => { 
				 var marker =  L.marker(val, { icon: Iconvehicle }).addTo(mainmap),
				 	 p = new L.Popup({ autoClose: false, closeOnClick: false })
	                .setContent("BestExpress1")
	                .setLatLng(val);
					 latlngs.push(val)
					 marker.bindPopup(p);
			})
		}
		getorder()
        $('#exampleModal').on('show.bs.modal', function (event) {
            var button = $(event.relatedTarget) // Button that triggered the modal
            var recipient = button.data('whatever') // Extract info from data-* attributes
            // If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
            // Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
            var modal = $(this)
            modal.find('.modal-title').text('New message to ' + recipient)
            modal.find('.modal-body input').val(recipient)
        })

        $('#addLocation').on('show.bs.modal', function (event) {
            var button = $(event.relatedTarget)
            var recipient = button.data('jobid')
            var modal = $(this)
            
        })


        // Init Open Street Map.

      
        const initmap = ()=>{
            map = L.map('map').setView([16.250149, 103.240739], 15);
		    L.tileLayer('http://{s}.tile.osm.org/{z}/{x}/{y}.png', {
			attribution: '&copy; <a href="http://osm.org/copyright">OpenStreetMap</a> contributors'
		}).addTo(map);
       }
         const getjob = async()=>{
            const {data} = await axios.get('${pageContext.request.contextPath}/provider/findvehiclemoc/${providerId}/${date}')
            console.log('${pageContext.request.contextPath}/provider/findvehiclemoc/${providerId}/${date}')
            data.map((val,i)=>{
                jobs.row.add([
                    i +1,
                    val.date,
                    "<a href = '${pageContext.request.contextPath}/seller/vehiclejob/"+val.jobsectionId+"'>" + val.vehicle + "</a>",
                    val.driver,
                    val.jobSuccessful,
                    val.jobAll,
                    val.jobFault,
                    val.distanceSuccessful,
                    Math.round(val.distanceAll * 1000)/1000,
                    val.distanceFault,
                ]).draw( false );
            })
         }
           
           
        
        setTimeout(() => {
            //initmap()
            getjob()
        }, 0);
    </script>

</body>

</html>
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

		


        var map = L.map('map').setView([16.250149, 103.240739], 15);
		L.tileLayer('http://{s}.tile.osm.org/{z}/{x}/{y}.png', {
			attribution: '&copy; <a href="http://osm.org/copyright">OpenStreetMap</a> contributors'
		}).addTo(map);

		const getOldLine = async()=>{
			let polyLine = []
			let dashedLine = []
			const {data} = await axios.get('${pageContext.request.contextPath}/tracking/getoldline/125')
			console.log(data);
			data.result.polyLine.map((val,i)=>{
				polyLine.push(L.latLng(val[1],val[0]))
			})

			data.result.dashedLine.map((val,i)=>{
				dashedLine=[]
				console.log(val);
				val.map((val,i)=>{
					dashedLine.push(L.latLng(val[1],val[0]))
			})
				L.polyline(dashedLine, {color: colors[i], dashArray: '10, 10', dashOffset: '0'}).addTo(map)
				L.marker(dashedLine[dashedLine.length-1], { icon: IconHouse, title: 'Hover Text' }).addTo(map).bindPopup("data").openPopup();
			})
			L.polyline(polyLine, {color: "#FF0000"}).addTo(map)
			L.marker(polyLine[polyLine.length-1], { icon: Iconvehicle, title: 'Hover Text' }).addTo(map).bindPopup("Car")
						.openPopup();

		}
		
		const getbug = async()=>{
			let dashedLine = []
			const {data} = await axios.get('${pageContext.request.contextPath}/tracking/getoldline/128')
			// data.result.dashedLine.map((val,i)=>{
			// 	console.log(val)
			// 	val.map((val,i)=>{
			// 		console.log(val[1]+","+val[0])
			// 	})
			// })

			data.result.dashedLine[0].map((val,i)=>{
				console.log(val[1]+","+val[0])
				dashedLine.push(L.latLng(val[1],val[0]))
			})
			L.polyline(dashedLine, {color: "#FF0000"}).addTo(map)

		}
		// getOldLine()
		getbug()
		
    </script>

</body>

</html>
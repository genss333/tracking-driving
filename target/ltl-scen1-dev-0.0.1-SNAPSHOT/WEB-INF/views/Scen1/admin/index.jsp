<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<title>Admin</title>
<%@ include file="/include/homemapcss.jsp"%>
<%@ include file="/include/css.jsp"%>
</head>
<body> 
	<div id="wrapper">
		<!-- import Side Bar -->
		<%@ include file="/include/sidebar1/sidebaradmin.jsp"%>
  
		<div class="d-flex flex-column" id="content-wrapper">
		  <div id="content">
			<!-- import Side Bar -->
			<%@ include file="/include/headerbar.jsp"%>
  
			<div class="container-fluid">
			  <!-- Page Name-->
			  <h3 class="text-dark mb-4">บริษัทขนส่งที่รอการอนุมัติ</h3>
				<div class="row">
					<div class="col-md-7">
					
					</div>
					<div class="col-md-5">
					
					</div>
				</div>
				<div class="row">
					<div class="col">
						<div class="card">
							<div class="card-header">
							  <div class="row">
								<div class="col">บริษัทขนส่งที่รอการอนุมัติ</div>
							  </div>
							</div>
							<div class="card-body">
							  <div class="table-responsive">
								<table
								  style="text-align: center; table-layout: fixed"
								  id="datatable-comp"
								>
								  <thead>
									<tr>
									  <th width="5px">#</th>
									  <th></th>
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
				<br>
				<div class="row">
					<div class="col">
						<div class="card">
							<div class="card-header">
							  <div class="row">
								<div class="col">บริษัทขนส่งที่อยู่ในระบบ</div>
							  </div>
							</div>
							<div class="card-body">
							  <div class="table-responsive">
								<table
								  style="text-align: center; table-layout: fixed"
								  id="datatable-compin"
								>
								  <thead>
									<tr>
									  <th width="5px">#</th>
									  <th>ชื่อบริษัทขนส่ง</th>
									  <th>ที่อยู่</th>
									  <th>ตำบล</th>
									  <th>อำเภอ</th>
									  <th>จังหวัด</th>
									  <th>ละติจูด</th>
									  <th>ลองจิจูด</th>
									  <th>เบอร์โทร</th>
									  <th>อีเมล</th>
									  <th>admin username</th>
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
				<span>Live Tracking Logistics 2021 </span>
			  </div>
			</div>
		  </footer>
		</div>
		<a class="border rounded d-inline scroll-to-top" href="#page-top"
		  ><i class="fas fa-angle-up"></i
		></a>
	  </div>
	<%@ include file="/include/js.jsp"%>
	<%@ include file="/include/homemapfunc.jsp"%>
	<script>
		let comp,compin
		let mapcomp = []
		let ping = []
		var IconWarehouse = L.icon({
        iconUrl:
          "${pageContext.request.contextPath}/contents/img/warehouse.png",
        iconSize: [30, 30],
        iconAnchor: [20, 25],
      });
		$(document).ready( async()=> {
			comp = await $("#datatable-comp").DataTable();
			compin = await $("#datatable-compin").DataTable();
			getprovider();
			getproviderin();
      });
	  const getprovider = async ()=>{
		comp.clear().draw();
		mapcomp = []
		ping = []
		  const {data} = await axios.get('${pageContext.request.contextPath}/admin/findcompany/0')
			data.result.map(async(val,i)=>{
				await comp.row.add([
					val.providerId,
					`
					<div class="card text-left">
					<div class="card-body">
						<div class="row">
							<div class="col-md-7">
								<div class="row">
									<div class="col">
										<h5 class="text-dark mb-4">วันที่ข้ออนุมัติ \${val.createdAt}</h5>
									</div>
								</div>
								<div class="row">
									<div class="col">
										<h5 class="text-dark mb-4">ชื่อบริษัทขนส่ง : \${val.name}</h5>
									</div>
								</div><hr>
								<div class="row">
									<div class="col">
										<h5 class="text-dark mb-4">ข้อมูลติดต่อ</h5>
									</div>
								</div>
								<div class="row">
									<div class="col">
										<h5 class="text-dark mb-4">อีเมลล์ : \${val.email}</h5>
									</div>
									<div class="col">
										<h5 class="text-dark mb-4">เบอร์โทร : \${val.phone}</h5>
									</div>
								</div>
								<div class="row">
									<div class="col">
										<h5 class="text-dark mb-4">ที่อยู่ : \${val.address}, \${val.tambon}, \${val.amphur}, \${val.province}</h5>
									</div>
								</div><hr>
								<button type="button" class="btn btn-success" onclick="approved(\${val.providerId},'\${val.name}')">อนุมัติ</button>
								<button type="button" class="btn btn-danger" onclick="rejected(\${val.providerId},'\${val.name}')">ไม่อนุมัติ</button>
							</div>
							<div class="col-md-5">
								<div class="shadow p-3 mb-5 bg-white rounded" id="map\${i}" style="height: 35vh"></div>
								<h5 class="text-dark mb-4">ละติจูด: \${val.latitude}	ลองจิจูด: \${val.longitude}</h5>
							</div>
						</div>

					</div>
				  </div>
					`,

				]).draw( false );
				mapcomp[i] = await L.map("map"+i).setView([val.latitude, val.longitude], 14);
				await L.tileLayer("http://{s}.tile.osm.org/{z}/{x}/{y}.png", {
	  			attribution:'&copy; <a href="http://osm.org/copyright">OpenStreetMap</a> contributors',
				}).addTo(mapcomp[i]);
				ping[i] = await L.marker([val.latitude, val.longitude], { icon: IconWarehouse }).addTo(mapcomp[i]);
			})
	  }
	  const getproviderin = async()=> {
		compin.clear().draw();
		const {data} = await axios.get('${pageContext.request.contextPath}/admin/findcompanyin')
		console.log(data)
		data.result.map((val,i)=>{
			compin.row.add([
				i,
				val.providerModel.name,
				val.providerModel.address,
				val.providerModel.tambon,
				val.providerModel.amphur,
				val.providerModel.province,
				val.providerModel.latitude,
				val.providerModel.longitude,
				val.providerModel.email == null ? "-" : val.providerModel.email,
				val.providerModel.phone  == null ? "-" : val.providerModel.phone,
				val.employeeModel.username
		]).draw( false );

		})
	  }
	  const approved = async(id,name)=>{
			Swal.fire({
				title: 'แน่ใจนะ ?',
				text: "อนุมัติให้บริษัท ID:"+id +" "+name,
				icon: 'warning',
				showCancelButton: true,
				confirmButtonColor: '#3085d6',
				cancelButtonColor: '#d33',
				confirmButtonText: 'อนุมัติ'
				}).then(async (result) => {
					if (result.isConfirmed) {
						// approved API down here
						const {data} = await axios.get('${pageContext.request.contextPath}/admin/approved/'+id)
						console.log(data);
						Swal.fire(
						'เรียบร้อย',
						"คุณได้เพิ่มบริษัท ID:"+id +" "+name,
						'success'
						)
						getprovider()
						getproviderin()
					}
				})
	  	}
		  const rejected = async(id,name)=>{
			Swal.fire({
				title: 'แน่ใจนะ ?',
				text: "ไม่อนุมัติให้บริษัท ID:"+id +" "+name,
				icon: 'warning',
				showCancelButton: true,
				confirmButtonColor: '#3085d6',
				cancelButtonColor: '#d33',
				confirmButtonText: 'ไม่อนุมัติ'
				}).then(async (result) => {
					if (result.isConfirmed) {
						// approved API down here
						const {data} = await axios.get('${pageContext.request.contextPath}/admin/rejected/'+id)
						console.log(data);
						Swal.fire(
						'เรียบร้อย',
						"คุณได้ปฏิเสธบริษัท ID:"+id +" "+name,
						'success'
						)
						getprovider()
						getproviderin()
					}
				})
	  	}
	</script>
</body>
</html>
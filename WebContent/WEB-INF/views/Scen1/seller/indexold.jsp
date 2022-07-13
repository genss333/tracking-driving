<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no" />
<title>บริษัทขนส่ง</title>
<%@ include file="/include/homemapcss.jsp"%>
<%@ include file="/include/css.jsp"%>
<style type="text/css">
.btnn {
	display: inline-block;
	background-color: #00b531;
	color: white;
	padding: 8px 12px;
	border-radius: 4px;
	font-size: 16px;
	cursor: pointer;
}

#pictureFromCamera {
	width: 100%;
	height: auto;
	margin-top: 16px;
}

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
					<h3 class="text-dark mb-4">บริษัทขนส่ง</h3>
					<div class="row">
						<div class="col">
							<div class="card">
								<div class="card-header">
									<a href="#" class="btn btn-success" data-toggle="modal"
										data-target="#addLocation" data-jobid="1" onclick="showmap()">เพิ่ม
										Order</a>
								</div>
								<div class="card-body">
									<div class="table-responsive">
										<table id="datatable-order" style="table-layout: fixed; text-align: center;">
											<thead >
												<tr>
													<th width="1%">#</th>
													<th width="9">วันที่</th>
													<th width="10">ชื่อบริษัท</th>
													<th style="color: green;" width="12">งานที่เสร็จ</th>
													<th style="color: blue;" width="12">งานทั้งหมด</th>
													<th style="color: red;" width="11">งานที่เหลือ</th>
													<th style="color: green;" width="15">ระยะทางที่วิ่ง</th>
													<th style="color: blue;" width="15">ระยะทางที่คำนวณ</th>
													<th style="color: red;" width="15">ระยะทางเกิน</th>
												</tr>
											</thead>
											<tbody></tbody>
										</table>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>

			<!--========================Modal========================-->

			<div class="modal fade" id="addLocation" tabindex="-1"
				aria-labelledby="addLocationLabel" aria-hidden="true">
				<div class="modal-dialog modal-lg">
					<div class="modal-content">
						<!-- contance Here -->
						<div class="modal-header">
							<h5 class="modal-title" id="addLocationLabel">เพิ่มออเดอร์</h5>
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
						</div>
						<div class="modal-body">
							<form>
								<div class="row">
									<div class="col-md-8">
										<label for="order_number">เลขออเดอร์</label> <input
											type="text" class="form-control" id="order_number"
											aria-describedby="name" placeholder="" />
									</div>
									<div class="col-md-4">
										<label for="name">อัปโหลดรูป</label><br>
										<label for="cameraFileInput">
											<span class="btnn">Open camera</span>
									  
											<!-- The hidden file `input` for opening the native camera -->
											<input
											  id="cameraFileInput"
											  type="file"
											  accept="image/*"
											  capture="environment"
											  style="display: none;"
											/>
										  </label>
									</div>
								</div>
								<div class="row">
									<div class="col">
										<img id="pictureFromCamera" />
									</div>
								</div>


								<div class="row">
									<div class="col">
										<div class="form-group">
											<label for="phone_number">เบอร์โทร</label> <input type="text"
												class="form-control" id="phone_number"
												aria-describedby="name" placeholder="" />
										</div>
									</div>
									<div class="col">
										<div class="form-group">
											<label for="sub_phone_number">เบอร์โทรสำรอง</label> <input
												type="text" class="form-control" id="sub_phone_number"
												aria-describedby="name" placeholder="" />
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col">
										<div class="form-group">
											<label for="firstname">ชือ</label> <input type="text"
												class="form-control" id="firstname" aria-describedby="name"
												placeholder="" />
										</div>
									</div>
									<div class="col">
										<div class="form-group">
											<label for="lastname">นามสกุล</label> <input type="text"
												class="form-control" id="lastname" aria-describedby="name"
												placeholder="" />
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col">
										<div class="form-group">
											<label for="address">ที่อยู่</label> <input type="text"
												class="form-control" id="address" aria-describedby="name"
												placeholder="บ้านเลขที่, หมู่บ้าน, ซอย, ถนน" />
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col">
										<div class="form-group">
											<label for="name">จังหวัด</label> <select
												class="custom-select" id="province">
												<option selected>จังหวัด</option>
												<option value="มหาสารคาม">มหาสารคาม</option>
											</select>
										</div>
									</div>
									<div class="col">
										<div class="form-group">
											<label for="name">อำเภอ</label> <select class="custom-select"
												id="AMPHUR">
												<option selected>อำเภอ</option>
												<option value="กันทรวิชัย">กันทรวิชัย</option>
											</select>
										</div>
									</div>
									<div class="col">
										<div class="form-group">
											<label for="name">ตำบล</label> <select class="custom-select"
												id="TAMBON">
												<option selected>ตำบล</option>
												<option value="ท่าขอนยาง">ท่าขอนยาง</option>
												<option value="ขามเรียง">ขามเรียง</option>
												<option value="เขวาใหญ่">เขวาใหญ่</option>
												<option value="กุดใส้จ่อ">กุดใส้จ่อ</option>
											</select>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col">
										<div class="form-group">
											<label for="zipcode">รหัสไปรษณีย์</label> <input type="text"
												class="form-control" id="zipcode" aria-describedby="name"
												placeholder="" />
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col">
										<div class="form-group">
											<label for="latitude">latitude</label> <input type="text"
												class="form-control" id="latitude" aria-describedby="name"
												placeholder="" />
										</div>
									</div>
									<div class="col">
										<div class="form-group">
											<label for="longitude">longitude</label> <input type="text"
												class="form-control" id="longitude" aria-describedby="name"
												placeholder="" />
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col">
										<label for="map">พิกัดผู้รับสินค้า</label>
										<div class="shadow p-3 mb-5 bg-white rounded" id="map"
											style="height: 50vh"></div>
									</div>
								</div>
							</form>
						</div>

						<div class="modal-footer">
							<button type="button" class="btn btn-secondary"
								data-dismiss="modal">ปิด</button>
							<button type="button" data-dismiss="modal"
								class="btn btn-primary" onclick="addorder()">ยืนยัน</button>
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

    document
      .getElementById("cameraFileInput")
      .addEventListener("change", function () {
        document
          .getElementById("pictureFromCamera")
          .setAttribute("src", window.URL.createObjectURL(this.files[0]));
      });
    
    let map = L.map("map").setView([16.255598155019182, 103.235214941831], 18);
    L.tileLayer("http://{s}.tile.osm.org/{z}/{x}/{y}.png", {
        attribution:
          '&copy; <a href="http://osm.org/copyright">OpenStreetMap</a> contributors',
      }).addTo(map);
   
    const addorder = async() => {
		payload ={
				orderNumber: document.getElementById("order_number").value,
				latitude: document.getElementById("latitude").value,
				longitude: document.getElementById("longitude").value,
				tambon: document.getElementById("TAMBON").value,
				subPhoneNumber: document.getElementById("sub_phone_number").value,
				phoneNumber: document.getElementById("phone_number").value,
				firstname: document.getElementById("firstname").value,
				lastname: document.getElementById("lastname").value,
				address: document.getElementById("address").value,
				province: document.getElementById("province").value,
				amphur: document.getElementById("AMPHUR").value,
				zipcode: document.getElementById("zipcode").value,
				sellerId :5
			}
	console.log(payload);
		const {data} = await axios.post('${pageContext.request.contextPath}/order/setindex',payload)
		
		/* getOrderSeller() */
	}
      let order;
      var IconHouse = L.icon({
        iconUrl: "${pageContext.request.contextPath}/contents/img/house.png",
        iconSize: [30, 30],
        //iconAnchor: [20,25],
      });
      $(document).ready(function () {
        order = $("#datatable-order").DataTable({
          columnDefs: [
            {
              orderable: true,
              className: "select-checkbox",
              targets: 0,
            },
          ],
        });
      });
      flatpickr(".timeDelivery", {
        locale: "th",
        enableTime: true,
        dateFormat: "Y-m-d H:i:S",
        defaultDate: "today",
        altInput: "true",
        altFormat: "j F Y H:i น.",
      });
      const initmap = () => {
        var map = L.map("map").setView([16.255598155019182, 103.235214941831], 18);
        L.tileLayer("http://{s}.tile.osm.org/{z}/{x}/{y}.png", {
          attribution:
            '&copy; <a href="http://osm.org/copyright">OpenStreetMap</a> contributors',
        }).addTo(map);
        L.marker([16.255598155019182, 103.235214941831], { icon: IconHouse }).addTo(map);
        map.on('click', function(e) {
        	  str = "lat:"+e.latlng.lat+", lng:"+e.latlng.lng;
        	  
        	  $("#latitude").val(e.latlng.lat);
        	  $("#longitude").val(e.latlng.lng);
        	  console.log(str);
        	})
      };

      const showcard = () => {
        $("#userdetail").show();
      };
      // hide() and show()
      const showdata = async () => {
        await $("#userdata").show();
        
        await initmap();
      };
      const showmap =()=>{
        setTimeout(async() => {
        	await map.remove();
            await initmap();
        }, 500);
      }
      const notuser = () => {
        Swal.fire({
          title: "Customer App",
          text: "โหลดเพื่อกำหนดที่รับสินค้า",
          imageUrl:
            "${pageContext.request.contextPath}/contents/img/LoadCustomerApp.png",
          imageHeight: "150%",
          imageWidth: "150%",
          imageAlt: "A tall image",
        });
      };
      //==========================================new=====================================

      const getOrderSeller = async()=>{
        //order.clear().draw();
        const {data} = await axios.get('${pageContext.request.contextPath}/seller/findproductmoc')
        console.log(data);
        data.map((val,i)=>{
          order.row.add([
              i+1,
              val.date,
              "<a href = '${pageContext.request.contextPath}/seller/"+val.providerId+"/"+val.date+"' style='color: green;'>" + val.logisticName +"</a>",
              val.jobSuccessful,
              val.jobAll,
              val.jobFault,
              val.distanceSuccessful,
              val.distanceAll,
              val.distanceFault,
            ])
            .draw(false);
          
        })
      }

      setTimeout(() => {
        getOrderSeller()
      }, 100);
      const getmap = async()=>{
    	  const {data} = await axios.get('')
      }
    </script>
</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8" />
    <meta
      name="viewport"
      content="width=device-width, initial-scale=1.0, shrink-to-fit=no"
    />
    <title>รถขนส่งสินค้า</title>
    <%@ include file="/include/homemapcss.jsp"%> <%@ include
    file="/include/css.jsp"%>
    <style type="text/css">
      table {
        color: black;
      }
      body {
        color: black;
      }
    </style>
  </head>

  <body id="page-top">
    <div id="wrapper">
      <!-- import Side Bar -->
      <%@ include file="/include/sidebar1/sidebarlogistics.jsp"%>

      <div class="d-flex flex-column" id="content-wrapper">
        <div id="content">
          <!-- import Side Bar -->
          <%@ include file="/include/headerbar.jsp"%>

          <div class="container-fluid">
            <!-- Page Name-->

            <h3 class="text-dark mb-4">รถขนพัสดุ</h3>
            <div class="row">
              <div class="col-sm-10" style="position: relative">
                <div
                  class="shadow p-3 mb-5 bg-white rounded"
                  id="mainmap"
                  style="height: 70vh"
                ></div>
              </div>
              <div class="col-sm-2" style="position: relative">
                <div
                  class="shadow p-3 mb-5 bg-white rounded"
                  id="mainmap"
                  style="height: 70vh"
                ></div>
              </div>
              <br>
            </div>
			<div class="row">
				<div class="col">
				  <div class="card">
					<div class="card-header">
					  <div class="row">
						<div class="col">เพิ่มออเดอร์</div>
					  </div>
					</div>
					<div class="card-body">
					  <a
                      href="#"
                      class="btn btn-success btn-lg btn-block"
                      data-toggle="modal"
                      data-target="#addLocation"
                      data-jobid="1"
                      onclick="showmap()"
                      >เพิ่ม Order</a>
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
                      <div class="col">รถนำจ่าย</div>
                    </div>
                  </div>
                  <div class="card-body">
                    <div class="table-responsive">
                      <table
                        style="text-align: center; table-layout: fixed"
                        id="datatable-jobs"
                      >
                        <thead>
                          <tr>
                            <th width="1%">#</th>
                            <th width="9">วันที่</th>
                            <th width="10">ชื่อรถ</th>
                            <th width="10">คนขับ</th>
                            <th style="color: green" width="10">งานที่เสร็จ</th>
                            <th style="color: blue" width="10">งานทั้งหมด</th>
                            <th style="color: red" width="10">งานที่เหลือ</th>
                            <th style="color: green" width="10">
                              ระยะทางที่วิ่ง
                            </th>
                            <th style="color: blue" width="10">
                              ระยะทางที่คำนวณ
                            </th>
                            <th style="color: red" width="10">
                              ระยะทางที่เกิน
                            </th>
                          </tr>
                        </thead>
                        <tbody></tbody>
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

        <!-- ===================== Modal =================-->
        <div
          class="modal fade"
          id="addLocation"
          tabindex="-1"
          aria-labelledby="addLocationLabel"
          aria-hidden="true"
        >
          <div class="modal-dialog modal-lg">
            <div class="modal-content">
              <!-- contance Here -->
              <div class="modal-header">
                <h5 class="modal-title" id="addLocationLabel">เพิ่มออเดอร์</h5>
                <button
                  type="button"
                  class="close"
                  data-dismiss="modal"
                  aria-label="Close"
                >
                  <span aria-hidden="true">&times;</span>
                </button>
              </div>
              <div class="modal-body">
                <form>
                  <div class="row">
                    <div class="col-md-8">
                      <label for="order_number">เลขออเดอร์</label>
                      <input
                        type="text"
                        class="form-control"
                        id="order_number"
                        aria-describedby="name"
                        placeholder=""
                      />
                    </div>
                    <div class="col-md-4">
                      <label for="name">อัปโหลดรูป</label><br />
                      <label for="cameraFileInput">
                        <span class="btnn">Open camera</span>

                        <!-- The hidden file `input` for opening the native camera -->
                        <input
                          id="cameraFileInput"
                          type="file"
                          accept="image/*"
                          capture="environment"
                          style="display: none"
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
                        <label for="phone_number">เบอร์โทร</label>
                        <input
                          type="text"
                          class="form-control"
                          id="phone_number"
                          aria-describedby="name"
                          placeholder=""
                          onchange="onchangeTel()"
                        />
                      </div>
                    </div>
                    <div class="col">
                      <div class="form-group">
                        <label for="sub_phone_number">เบอร์โทรสำรอง</label>
                        <input
                          type="text"
                          class="form-control"
                          id="sub_phone_number"
                          aria-describedby="name"
                          placeholder=""
                        />
                      </div>
                    </div>
                  </div>
                  <div class="row">
                    <div class="col">
                      <div class="form-group">
                        <label for="firstname">ชือ</label>
                        <input
                          type="text"
                          class="form-control"
                          id="firstname"
                          aria-describedby="name"
                          placeholder=""
                        />
                      </div>
                    </div>
                    <div class="col">
                      <div class="form-group">
                        <label for="lastname">นามสกุล</label>
                        <input
                          type="text"
                          class="form-control"
                          id="lastname"
                          aria-describedby="name"
                          placeholder=""
                        />
                      </div>
                    </div>
                  </div>
                  <div class="row">
                    <div class="col">
                      <div class="form-group">
                        <label for="address">ที่อยู่</label>
                        <input
                          type="text"
                          class="form-control"
                          id="address"
                          aria-describedby="name"
                          placeholder="บ้านเลขที่, หมู่บ้าน, ซอย, ถนน"
                        />
                      </div>
                    </div>
                  </div>
                  <div class="row">
                    <div class="col">
                      <div class="form-group">
                        <label for="name">จังหวัด</label>
                        <select
                          class="custom-select"
                          id="province"
                          onchange="getamphur()"
                        >
                          <option selected>จังหวัด</option>
                        </select>
                      </div>
                    </div>
                    <div class="col">
                      <div class="form-group">
                        <label for="name">อำเภอ</label>
                        <select
                          class="custom-select"
                          id="AMPHUR"
                          onchange="gettambon()"
                        >
                          <option selected>อำเภอ</option>
                        </select>
                      </div>
                    </div>
                    <div class="col">
                      <div class="form-group">
                        <label for="name">ตำบล</label>
                        <select
                          class="custom-select"
                          id="TAMBON"
                          onchange="setmap()"
                        >
                          <option selected>ตำบล</option>
                        </select>
                      </div>
                    </div>
                  </div>
                  <div class="row">
                    <div class="col">
                      <div class="form-group">
                        <label for="zipcode">รหัสไปรษณีย์</label>
                        <input
                          type="text"
                          class="form-control"
                          id="zipcode"
                          aria-describedby="name"
                          placeholder=""
                        />
                      </div>
                    </div>
                  </div>
                  <div class="row">
                    <div class="col">
                      <div class="form-group">
                        <label for="latitude">latitude</label>
                        <input
                          type="text"
                          class="form-control"
                          id="latitude"
                          aria-describedby="name"
                          placeholder=""
                        />
                      </div>
                    </div>
                    <div class="col">
                      <div class="form-group">
                        <label for="longitude">longitude</label>
                        <input
                          type="text"
                          class="form-control"
                          id="longitude"
                          aria-describedby="name"
                          placeholder=""
                        />
                      </div>
                    </div>
                  </div>
                  <div class="row">
                    <div class="col">
                      <label for="map">พิกัดผู้รับสินค้า</label>
                      <div
                        class="shadow p-3 mb-5 bg-white rounded"
                        id="map"
                        style="height: 50vh"
                      ></div>
                    </div>
                  </div>
                </form>
              </div>

              <div class="modal-footer">
                <button
                  type="button"
                  class="btn btn-secondary"
                  data-dismiss="modal"
                >
                  ปิด
                </button>
                <button
                  type="button"
                  data-dismiss="modal"
                  class="btn btn-primary"
                  onclick="addorder()"
                >
                  ยืนยัน
                </button>
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
    <%@ include file="/include/js.jsp"%> <%@ include
    file="/include/homemapfunc.jsp"%>
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
      $(document).ready(function () {
        jobs = $("#datatable-jobs").DataTable();
      });
      // icon car
      var Iconvehicle = L.icon({
        iconUrl:
          "${pageContext.request.contextPath}/contents/img/mapmarker.png",
        iconSize: [50, 50],
        iconAnchor: [25, 45],
      });
      var IconHouse = L.icon({
        iconUrl: "${pageContext.request.contextPath}/contents/img/house.png",
        iconSize: [30, 30],
        //iconAnchor: [20,25],
      });
      var IconWarehouse = L.icon({
        iconUrl:
          "${pageContext.request.contextPath}/contents/img/truck-moving-solid.svg",
        iconSize: [30, 30],
        iconAnchor: [20, 25],
      });
      var IconTruck = L.icon({
        iconUrl: "${pageContext.request.contextPath}/contents/img/truck-R.png",
        iconSize: [30, 30],
        iconAnchor: [20, 25],
      });
      const markerColor = (color) => {
        const icon = L.divIcon({
          className: "custom-div-icon",
          html:
            `<i class="fas fa-map-marker-alt fa-3x" style="color: ` +
            color +
            `; text-shadow: 0 0 3px #000;"></i>`,
          iconSize: [100, 100],
          iconAnchor: [15, 35],
          popupAnchor: [0, -40],
        });
        return icon;
      };
      var mapadd;
      var map = L.map("mainmap").setView([16.250149, 103.240739], 14);
      L.tileLayer("http://{s}.tile.osm.org/{z}/{x}/{y}.png", {
        attribution:
          '&copy; <a href="http://osm.org/copyright">OpenStreetMap</a> contributors',
      }).addTo(map);
      let order;
      let latlngs = [];

      $("#exampleModal").on("show.bs.modal", function (event) {
        var button = $(event.relatedTarget); // Button that triggered the modal
        var recipient = button.data("whatever"); // Extract info from data-* attributes
        // If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
        // Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
        var modal = $(this);
        modal.find(".modal-title").text("New message to " + recipient);
        modal.find(".modal-body input").val(recipient);
      });

      $("#addLocation").on("show.bs.modal", function (event) {
        var button = $(event.relatedTarget);
        var recipient = button.data("jobid");
        var modal = $(this);
      });
      const getjob = async () => {
        const { data } = await axios.get(
          "${pageContext.request.contextPath}/provider/findAllvehicle"
        );
        data.map((val, i) => {
          jobs.row
            .add([
              i + 1,
              val.date,
              "<a href = '${pageContext.request.contextPath}/provider/vehiclejob/" +
                val.jobsectionId +
                "'>" +
                val.vehicle +
                "</a>",
              val.driver,
              val.jobSuccessful,
              val.jobAll,
              val.jobFault,
              val.distanceSuccessful,
              (val.distanceAll * 0.001).toFixed(2) + " Km.",
              val.distanceFault,
            ])
            .draw(false);
        });
      };

      const Tracking = async () => {
        let vehicle;
        let vehicles = [];
        let j = 0;
        let tempSize = -1;
        let status = 2;
        setInterval(async () => {
          const { data } = await axios.post(
            "${pageContext.request.contextPath}/tracking/maptracking",
            {
              starttime: getTime(-50),
              endtime: getTime(0),
              status: status,
            }
          );
          console.log(data);
          // Check data size
          if (tempSize !== data.length) {
            tempSize = data.length;
            // reset old data
            if (vehicles[0]) {
              console.log("Data Reset !");
              vehicles.map((val, i) => {
                val.remove();
              });
            }
          }
          // plot all marker
          data.map((val, i) => {
            //remove old marker
            if (vehicles[i] && val.locations.length !== 0) {
              vehicles[i].remove();
            }
            console.log(val.locations);
            // plot
            if (val.locations.length !== 0) {
              vehicle = L.marker(
                L.latLng(val.locations[0].latitude, val.locations[0].longitude),
                { icon: Iconvehicle }
              )
                .bindPopup(val.driver.firstname + " " + val.driver.lastname)
                .openPopup()
                .addTo(map);
              vehicles.splice(i, 1, vehicle);
            }
          });
          console.log("====================");
        }, 5000);
      };
      const getTime = (sec) => {
        var today = new Date();
        today.setSeconds(today.getSeconds() + sec);
        var date =
          today.getFullYear() +
          "-" +
          (today.getMonth() + 1) +
          "-" +
          today.getDate();
        var time =
          today.getHours() +
          ":" +
          today.getMinutes() +
          ":" +
          today.getSeconds();
        var dateTime = date + " " + time;
        return dateTime;
      };

      setTimeout(() => {
        initmap()
        getjob();
        Tracking();
      }, 0);

      let ping;
      // Function add Order
      const onchangeTel = async()=>{
        const { data } = await axios.get("${pageContext.request.contextPath}/order/findUser/"+$("#phone_number").val());
        console.log(data)
        if(data.result.userid > 0){
          $("#latitude").val(data.result.lat)
          $("#longitude").val(data.result.lng)
          $("#firstname").val(data.result.firstname)
          $("#lastname").val(data.result.lastname)
          $("#address").val(data.result.address)         
          $("#zipcode").val(data.result.zipcode)
          try {
        	  var province = document.getElementById("province");
              setSelected(province,data.result.province)
              await getamphur()
              var amphur = document.getElementById("AMPHUR");
              setSelected(amphur,data.result.amphur)
              
              await gettambon()
              var tambon = document.getElementById("TAMBON");
              setSelected(tambon,data.result.tambon)
          }catch(err){
        	  console.log(err)
          }

          if (ping) {
            ping.remove();
          }
          ping = L.marker([data.result.lat,data.result.lng], { icon: IconHouse }).addTo(mapadd);
          mapadd.flyTo([data.result.lat,data.result.lng], 12);
          
         
        }
      }
      const setSelected =(selectObj, valueToSet)=>{
        for (var i = 0; i < selectObj.options.length; i++) {
          console.log(selectObj.options[i].text);
          if (selectObj.options[i].text == valueToSet) {
              selectObj.options[i].selected = true;
              return;
          }
        }
      }
      const initmap = () => {
        mapadd = L.map("map").setView(
          [16.255598155019182, 103.235214941831],
          8
        );
        L.tileLayer("http://{s}.tile.osm.org/{z}/{x}/{y}.png", {
          attribution:
            '&copy; <a href="http://osm.org/copyright">OpenStreetMap</a> contributors',
        }).addTo(mapadd);
        //L.marker([16.255598155019182, 103.235214941831], { icon: IconHouse }).addTo(map);
        mapadd.on("click", function (e) {
          str = "lat:" + e.latlng.lat + ", lng:" + e.latlng.lng;

          $("#latitude").val(e.latlng.lat);
          $("#longitude").val(e.latlng.lng);
          if (ping) {
            ping.remove();
          }
          ping = L.marker(e.latlng, { icon: IconHouse }).addTo(mapadd);
          console.log(str);
        });
      };

      const addorder = async () => {
        payload = {
          orderNumber: document.getElementById("order_number").value,
          latitude: document.getElementById("latitude").value,
          longitude: document.getElementById("longitude").value,
          tambon: JSON.parse($("#TAMBON").val()).tambon,
          subPhoneNumber: document.getElementById("sub_phone_number").value,
          phoneNumber: document.getElementById("phone_number").value,
          firstname: document.getElementById("firstname").value,
          lastname: document.getElementById("lastname").value,
          address: document.getElementById("address").value,
          province: JSON.parse($("#province").val()).province,
          amphur: JSON.parse($("#AMPHUR").val()).amphur,
          zipcode: document.getElementById("zipcode").value,
          sellerId: 5,
        };
        console.log(payload);
        const { data } = await axios.post(
          "${pageContext.request.contextPath}/order/setindex",
          payload
        );

        /* getOrderSeller() */
      };
      const showmap = () => {
        setTimeout(async () => {
          await mapadd.remove();
          await initmap();
        }, 500);
      };
	//   getprovince();
	
	let thmap;
	const getUniqueList = (arr, key)=> {
		return [...new Map(arr.map(item => [item[key], item])).values()]
	}
	const getthmap = async() => {
		let select = document.getElementById("province");
		 const {data}  = await axios.get("${pageContext.request.contextPath}/order/getthmap");
		 thmap = data.result
		 let unique = getUniqueList(thmap,"province")
		 unique.map((val, i) => {
          var opt = document.createElement("option");
          opt.value = JSON.stringify({
            province: val.province,
            lat: val.lat,
            lng: val.lng,
          });
          opt.innerHTML = val.province;
          select.appendChild(opt);
        });
	}
    const getamphur = async () => {
        var province = JSON.parse($("#province").val());
        console.log("Province:" + JSON.parse($("#province").val()).province);
        console.log(province.lat + "," + province.lng);
        let option = "<option disabled selected >กรุณาเลือก</option>";
		let data = thmap.filter(val => val.province === province.province)
		let unique = getUniqueList(data,"amphur")
        unique.map((val, i) => {
          let obj = JSON.stringify({
            amphur: val.amphur,
            lat: val.lat,
            lng: val.lng,
          });
          option += "<option value='" + obj + "'>" + val.amphur + "</option>";
        });
        $("#AMPHUR").html(option);
        mapadd.flyTo([province.lat, province.lng], 12);
      };
    const gettambon = async () => {
          var amphur = JSON.parse($("#AMPHUR").val());
          console.log(amphur.lat + "," + amphur.lng + "," + amphur.amphur);
          let option = "<option disabled selected >กรุณาเลือก</option>";
		  let data = thmap.filter(val => val.amphur === amphur.amphur)
          data.map((val, i) => {
            let obj = JSON.stringify({
              tambon: val.tambon,
              lat: val.lat,
              lng: val.lng,
            });
            option += "<option value='" + obj + "'>" + val.tambon + "</option>";
          });
          $("#TAMBON").html(option);
          mapadd.flyTo([amphur.lat, amphur.lng], 12);
        };
    const setmap = () => {
        var tambon = JSON.parse($("#TAMBON").val());
        console.log(tambon.lat + "," + tambon.lng + "," + tambon.tambon);
        mapadd.flyTo([tambon.lat, tambon.lng], 12);
      };
	getthmap()
    </script>
  </body>
</html>

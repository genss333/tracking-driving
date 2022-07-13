<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>จัดการคนขับรถ</title>
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
                <h3 class="text-dark mb-4">Report</h3>
                <div class="row">
                    <div class="col">
                        <div class="card">
                            <div class="card-header ">
                                <div class="row">
                                    <div class="col">รายงานการส่งสินค้า</div>
                                </div>
                            </div>
                            <div class="card-body">
                                <div class ="table-responsive">
                                    <table id ="datatable-report">
                                        <thead>
                                        <tr>
                                            <th>#</th>
                                            <th>TrackingID</th>
                                            <th>เบอร์โทร</th>
                                            <th>วันที่จัดส่ง</th>
											<th>สถานะการจัดส่ง</th>
                                            <th>รายละเอียดผู้รับ</th>
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

        <div class="modal fade" id="info" tabindex="-1" aria-labelledby="addLocationLabel" aria-hidden="true">
            <div class="modal-dialog modal-lg">
                <div class="modal-content">
                    <!-- contance Here -->
                    <div class="modal-header text-white bg-success">
                        <h5 class="modal-title" id="addLocationLabel">รายงานการส่งสินค้า</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                          <span aria-hidden="true">&times;</span>
                        </button>
                      </div>
        
                      <div class="modal-body">
                        <div class="card shadow">
                            <div class="card-header text-white bg-success">
                                ข้อมูล
                            </div>
                            <div class="card-body">
                                <div class="row">
                                    <div class="col">
                                        <h5 class="card-title text-success"> <i class="fas fa-box-open"></i> LTL00000042TH</h5>
                                        <p class="card-text">ส่งจาก: จังหวัดเชียงใหม่  ถึง: จังหวัดมหาสารคาม</p>
                                    </div>
                                    <div class="col text-right">
                                        <h5 class="card-title">02มิ.ย.2564 11:39 น.</h5>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col">
                                        <br>
                                        <h5 class="card-title">ชื่อคนนำส่ง</h5>
                                        <p class="card-text">นายเคอร์รี่ ขนส่งเร็วดี</p>
                                    </div>
                                    <div class="col">
                                        <br>
                                        <h5 class="card-title">เบอร์ติดต่อ</h5>
                                        <p class="card-text">0883555290</p>
                                    </div>
                                </div>
                                
                            </div>
                          </div>
                          <br>
                          <div class="card shadow">
                            <div class="card-header text-white bg-success">
                                พิกัดผู้รับสินค้า
                            </div>
                            <div class="card-body">
                                <div class="row">
                                    <div class="col">
                                            <div
                                                class="shadow p-3 mb-5 bg-white rounded"
                                                id="map"
                                                style="height: 50vh"
                                            ></div>
                                    </div>
                                </div>
                                
                            </div>
                          </div>
                          <br>
                        <div class="card shadow">
                            <div class="card-header text-white bg-success">
                                หลักฐานการจัดส่ง
                            </div>
                            <div class="card-body">
                                <div class="row">
                                    <div class="col">
                                        <h5 class="card-title">ลายเซ็นของผู้รับ</h5>
                                        <img src="https://placeholder.pics/svg/300x300">
                                    </div>
                                    <div class="col">
                                        <h5 class="card-title">รูปถ่ายการส่งมอบ</h5>
                                        <img src="https://placeholder.pics/svg/300x300">
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col">
                                    <br>
                                        <h5 class="card-title">QR Code เพื่อรับพัสดุ </h5>
                                        <p class="card-text text-success">(สแกนเรียบร้อย)</p>
                                        <img src="${pageContext.request.contextPath}/contents/img/scanqr.png" width="300" height="300">
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col">
                                        <br>
                                        <h5 class="card-title">ชื่อผู้รับ</h5>
                                        <p class="card-text">นายจิรเมธ แสนทอง</p>
                                    </div>
                                </div>
                            </div>
                          </div>

                    </div>
        
                    <div class="modal-footer">
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
        let latlng = [
			L.latLng(16.25472076420446,103.23512434959413), // start -> D1
			L.latLng(16.254710464234098,103.23556423187257),
			L.latLng(16.25472076420446,103.23635816574098),
			L.latLng(16.254741364143577,103.23696970939638),
			L.latLng(16.254525064675153,103.23735594749452),
			L.latLng(16.253896764869044,103.23736667633058),
			L.latLng(16.253319963278553,103.23737740516664),
			L.latLng(16.25277406021399,103.2371735572815),
			L.latLng(16.252485657982845,103.23705554008485),
			L.latLng(16.252104554385433,103.23706626892091),
			L.latLng(16.25191915236815,103.23755979537965),
			L.latLng(16.251713149921613,103.23795676231386),
			L.latLng(16.251888252014943,103.23836445808412),
			]
        let tablereport;
        let json=[
            {
                "trackid":"LTL00000042TH",
                "tel":"0883555290",
                "date":"12/06/2564",
            },
            {
                "trackid":"LTL00000073TH",
                "tel":"0915338125",
                "date":"12/06/2564",
            },
            {
                "trackid":"LTL00000043TH",
                "tel":"0874568215",
                "date":"12/06/2564",
            },
            {
                "trackid":"LTL00000001TH",
                "tel":"0846727489",
                "date":"12/06/2564",
            },
            {
                "trackid":"LTL00000058TH",
                "tel":"0846721488",
                "date":"12/06/2564",
            }
        ]
        const getrandomNumber = (max)=>{
            return Math.floor(Math.random() * max);
         }
        $(document).ready( function () {
            tablereport = $('#datatable-report').DataTable();
            json.map((val,i)=>{
                tablereport.row.add([
                    i+1,
                    val.trackid,
                    val.tel,
                    val.date,
                    getrandomNumber(2) == 1 ? `<span class="badge bg-warning text-dark">ดำเนินการจัดส่ง</span>`:`<span class="badge bg-success" style="color:#FFF;">จัดส่งเรียบร้อย</span>`,
                    `<a class="btn btn-primary" role="button" data-toggle="modal" data-target="#info" data-jobid="1" onclick="showmap()"><i class="fas fa-info-circle"></i></a>`
                ]).draw(false);
            })
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
			iconUrl: '${pageContext.request.contextPath}/contents/img/warehouse.png',
			iconSize: [30,30],
			iconAnchor: [20,25],
		});
        const initmap = () => {
        var map = L.map("map").setView([16.253896764869044,103.23736667633058], 15);
        L.tileLayer("http://{s}.tile.osm.org/{z}/{x}/{y}.png", {
          attribution:
            '&copy; <a href="http://osm.org/copyright">OpenStreetMap</a> contributors',
        }).addTo(map);
        L.polyline(latlng, {color: 'red'}).addTo(map)
        L.marker([16.251888252014943,103.23836445808412], { icon: IconHouse }).addTo(map);
        L.marker([16.25472076420446,103.23512434959413], { icon: Iconvehicle }).addTo(map);
      };
        const showmap =()=>{
        setTimeout(() => {
          initmap();
        }, 500);
      }
    </script>
</body>
</html>`
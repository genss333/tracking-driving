<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>จัดการพัสดุ</title>
    <%@ include file="/include/homemapcss.jsp"%>
    <%@ include file="/include/css.jsp"%>
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
                <h3 class="text-dark mb-4">จัดการพัสดุ</h3>
                <div class="row">
                    <div class="col">
                        <div class="card">
                            <div class="card-header ">
                                <div class="row">
                                    <div class="col">พัสดุเตรียมนำจ่าย</div>
                                    <div class="col text-right"><button type="button" id="hide" class="btn btn-success" onclick="NewCut()"><i class="fas fa-dolly"></i>   ตัดรอบ</button></div>
                                </div>
                            </div>
                            <div class="card-body">
                                <div class ="table-responsive">
                                    <table id ="datatable-jobs">
                                        <thead>
                                        <tr>
                                            <th><input name="select_all" value="1" id="example-select-all" type="checkbox" /></th>
                                            <th>กำหนดส่ง</th>
                                            <th>TrackID</th>
                                            <th>เบอร์โทร</th>
                                            <th>ที่อยู่</th>
                                            <th>พิกัด</th>
                                            <th>กำหนดงาน</th>
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

                <div id="vehiclejob">
                    <br>
                    <h3 class="text-dark mb-4">งานของรถส่งพัสดุ</h3>
                    <div id="addAllJobs"></div>
                </div>
            </div>
        </div>

<div class="modal fade" id="addLocation" tabindex="-1" aria-labelledby="addLocationLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <!-- contance Here -->
            <div class="modal-header">
                <h5 class="modal-title" id="addLocationLabel">กำหนดพิกัด</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                  <span aria-hidden="true">&times;</span>
                </button>
              </div>

              <div class="modal-body">
                <div class="shadow p-3 mb-5 bg-white rounded" id = "maplatlng" style="height: 40vh;"></div>
                <div class="row">
                    <div class="col text-left">
                        <form>
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label for="swal-name">Latitude </label>
                                        <input type="text" class="form-control" id="swal-name" aria-describedby="swal-carcode" placeholder="lat">
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label for="swal-surname">Longitude </label>
                                        <input type="text" class="form-control" id="swal-surname" aria-describedby="swal-surname" placeholder="lng">
                                    </div>
                                </div>
                            </div>
                            
                        </form>
                    </div>
                </div>
            </div>

            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">ปิด</button>
                <button type="button" class="btn btn-primary">กำหนดพิกัด</button>
            </div>

        </div>
    </div>

</div>


<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">

      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">New message</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
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
        <button type="button" class="btn btn-secondary" data-dismiss="modal">ปิด</button>
        <button type="button" class="btn btn-primary">กำหนดพิกัด</button>
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






        let jobphonenumber = [    {        "PhoneNumber": "+66-955-536-116"    },    {        "PhoneNumber": "+66-855-585-449"    },    {        "PhoneNumber": "+66-655-507-116"    },    {        "PhoneNumber": "+66-955-508-990"    },    {        "PhoneNumber": "+66-655-578-931"    },    {        "PhoneNumber": "+66-955-568-038"    },    {        "PhoneNumber": "+66-655-540-242"    },    {        "PhoneNumber": "+66-955-564-110"    },    {        "PhoneNumber": "+66-655-556-876"    },    {        "PhoneNumber": "+66-655-537-116"    },    {        "PhoneNumber": "+66-855-536-340"    },    {        "PhoneNumber": "+66-955-597-408"    },    {        "PhoneNumber": "+66-955-569-277"    },    {        "PhoneNumber": "+66-955-559-420"    },    {        "PhoneNumber": "+66-855-596-342"    },    {        "PhoneNumber": "+66-655-581-409"    },    {        "PhoneNumber": "+66-655-578-558"    },    {        "PhoneNumber": "+66-955-573-657"    },    {        "PhoneNumber": "+66-855-536-717"    },    {        "PhoneNumber": "+66-955-515-548"    }]
        let cusphonenumber = [    {        "PhoneNumber": "+66-855-585-449"    },    {        "PhoneNumber": "+66-655-578-931"    },    {        "PhoneNumber": "+66-955-568-038"    },    {        "PhoneNumber": "+66-655-556-876"    },    {        "PhoneNumber": "+66-655-537-116"    },    {        "PhoneNumber": "+66-855-536-340"    }]
        let jobs;
        let goodsin;
        let goodsout;
        let goodsTraveling;
        let wptTraveling=[];
        let traveltemp=[]
        let wpts=[];
        var map;
        let alljobs = [
            {
                "Home": "D0",
                "phone":"+66-855-585-449",
                "lat": "16.25490761752868",
                "lng": "103.2349811376614",
                "Index":""
            },
            {
                "Home": "D1",
                "phone":"+66-655-507-116",
                "lat": "16.250729443107833",
                "lng": "103.24684985359129",
                "Index":""
            },
            {
                "Home": "D2",
                "phone":"+66-655-578-931",
                "lat": "16.25460514375541",
                "lng": "103.23152970959005",
                "Index":""
            },
            {
                "Home": "D3",
                "phone":"+66-955-568-038",
                "lat": "16.251992850747765",
                "lng": "103.23765921668723",
                "Index":""
            },
            {
                "Home": "D4",
                "phone":"+66-655-540-242",
                "lat": "16.248933673920938",
                "lng": "103.239850372396",
                "Index":""
            },
            {
                "Home": "D5",
                "phone":"+66-655-556-876",
                "lat": "16.244210761470463",
                "lng": "103.24181954823428",
                "Index":""
            },
            {
                "Home": "D6",
                "phone":"+66-655-537-116",
                "lat": "16.247081481976746",
                "lng": "103.25322159507967",
                "Index":""
            },
            {
                "Home": "D7",
                "phone":"+66-855-536-340",
                "lat": "16.236881738060973",
                "lng": "103.25120316697544",
                "Index":""
            }
        ]

        let center = ["ศูนย์ A","ศูนย์ B","ศูนย์ C"];
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

        // Init Open Street Map.

        let datalatlng = [
            "16.253516, 103.240726",
            "16.250920, 103.246965",
            "16.251013, 103.247459",
            "16.238816, 103.259355",
            "16.236643, 103.261835",
            "16.251012, 103.247457",
    ]
        const initmap = ()=>{
            map = L.map('map').setView([16.250149, 103.240739], 15);
		    L.tileLayer('http://{s}.tile.osm.org/{z}/{x}/{y}.png', {
			attribution: '&copy; <a href="http://osm.org/copyright">OpenStreetMap</a> contributors'
		}).addTo(map);

        var map2 = L.map('map2').setView([16.236087, 103.263130], 15);
		    L.tileLayer('http://{s}.tile.osm.org/{z}/{x}/{y}.png', {
			attribution: '&copy; <a href="http://osm.org/copyright">OpenStreetMap</a> contributors'
		}).addTo(map2);

        L.marker(L.latLng(16.25485788250856,103.23514580726625), { icon: IconHouse }).addTo(map)
        L.marker(L.latLng(16.253516, 103.240726), { icon: IconHouse }).addTo(map)
        L.marker(L.latLng(16.250920, 103.246965), { icon: IconHouse }).addTo(map)
        L.marker(L.latLng(16.251013, 103.247459), { icon: IconHouse }).addTo(map)
        
        
        L.marker(L.latLng(16.238816, 103.259355), { icon: IconHouse }).addTo(map2)
         L.marker(L.latLng(16.236643, 103.261835), { icon: IconHouse }).addTo(map2)
          L.marker(L.latLng(16.235654, 103.266177), { icon: IconHouse }).addTo(map2)
         



        }

        const initmapadd = ()=>{
            setTimeout(() => {
                var maplatlng = L.map('maplatlng').setView([16.236087, 103.263130], 15);
                L.tileLayer('http://{s}.tile.osm.org/{z}/{x}/{y}.png', {
                attribution: '&copy; <a href="http://osm.org/copyright">OpenStreetMap</a> contributors'
            }).addTo(maplatlng);
                L.marker(L.latLng(16.236087, 103.263130), { icon: Iconvehicle }).addTo(maplatlng)
            }, 500);
            
        }
         $(document).ready( function () {
            jobs = $('#datatable-jobs').DataTable();
            goodsin = $('#datatable-assign').DataTable();
            goodsout = $('#datatable-goodsout').DataTable();

            goodsTraveling = $('#datatable-goodsTraveling').DataTable();


            var v1job = $('#vehicle1job').DataTable( {
                            "paging":   false,
                            "ordering": false,
                            "info":     false,
                            "searching": false
                        } );
            var v2job = $('#vehicle2job').DataTable( {
                            "paging":   false,
                            "ordering": false,
                            "info":     false,
                            "searching": false
                        } );                        
         })
         let date =["15/06/2564",
         "15/06/2564",
         "15/06/2564",
         "15/06/2564",
         "15/06/2564",
         "15/06/2564",]
         const getjob = async()=>{
            const {data} = await axios.get('${pageContext.request.contextPath}/provider/findorder')
            data.map((val,i)=>{
                jobs.row.add([
                    `<input type="checkbox" id="vehicle1" name="vehicle1" value="Bike">`,
                    val.date,
                    val.orderNumber,
                    val.phoneNumber,
                    val.address,
                    val.lat+","+val.lng,
                    `<select class="custom-select" id="car`+val.orderId+`">
                        <option selected value="`+val.sender.vehicleModel.vehicleId+`">`+val.sender.vehicleModel.brand+" "+val.sender.vehicleModel.name+`</option>
                    </select>`,
                ]).draw( false );
            })
            }
            const getRoute = async()=>{
                const {data} = await axios.get('${pageContext.request.contextPath}/provider/findroute')
                console.log(data)
                let strHTML = "";
                let datatables=[]
                let maps=[]
                data.map((val,i)=>{
                    let strTable = "<tr><td>"+data[i].order[data[i].order.length-1].orderId+"</td><td>"+data[i].order[0].orderId+"</td></tr>";
                    for (let j = 1; j < val.order.length; j++) {
                        strTable +=
                        "<tr><td>"+data[i].order[j-1].orderId+"</td><td>"+data[i].order[j].orderId+"</td></tr>"
                        
                    }
                    strHTML += `
                    <div class="card">
                        <div class="card-body">
                          <div class="row">
                            <div class="col-md-8">
                                <div class="row">
                                    <div class="col">
                                        <h5 class="text-dark mb-4">Vehicle#`+val.vehicle.vehicleId+`-`+val.vehicle.name+`</h5>
                                    </div>
                                    <div class="col text-right">
                                        <h5 class="text-dark mb-4">Date:`+val.data+`</h5>
                                    </div>
                                </div>
                                <p>เขต: `+"ต."+val.zone.tambon+", อ."+val.zone.amphur+", จ."+val.zone.province+`</p>
                                <div class="row">
                                    <div class="col-md-6">
                                        <div class ="table-responsive">
                                            <table id ="vehicle`+i+`job">
                                                <thead>
                                                <tr>
                                                    <th>From</th>
                                                    <th>To</th>
                                                  </tr>
                                                </thead>
                                                <tbody>
                                                    `+
                                                    strTable
                                                    +`
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                              </div>
                              <div class="col-md-4">
                                <div class="shadow p-3 mb-5 bg-white rounded" id = "map`+i+`" style="height: 45vh;"></div>
                            </div>
                            </div>
                        </div>
                    </div><br>
                    `
                })
                document.getElementById("addAllJobs").innerHTML = strHTML
                data.map((val,i)=>{
                        maps.push(L.map('map'+i).setView([val.order[val.order.length-1].lat, val.order[val.order.length-1].lng], 13));
                        L.tileLayer('http://{s}.tile.osm.org/{z}/{x}/{y}.png', {
                        attribution: '&copy; <a href="http://osm.org/copyright">OpenStreetMap</a> contributors'
                    }).addTo(maps[i]);
                    let latlng=[L.latLng(val.order[val.order.length-1].lat, val.order[val.order.length-1].lng)]
                    val.order.map((val,j)=>{
                        L.marker(L.latLng(val.lat, val.lng), { icon: IconHouse }).bindPopup(val.index+": "+val.phoneNumber)
						.openPopup()
						.addTo(maps[i])
                        latlng.push(L.latLng(val.lat, val.lng))
                    })
                    L.polyline(latlng, {color: "#FF0000"}).addTo(maps[i])
                    datatables.push($('#vehicle'+i+'job').DataTable( {
                            "paging":   false,
                            "ordering": false,
                            "info":     false,
                            "searching": false
                        } ))
                    })

            }
            const test=()=>{
                console.log("Hello from swal");
            }
            const setlocation = ()=>{
                Swal.fire({
            title: 'กำหนดพิกัด',
            imageUrl: 'https://placeholder.pics/svg/500x500',
            html: `
            <div class="row">
                    <div class="col text-left">
                        <form>
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label for="swal-name">Latitude </label>
                                        <input type="text" class="form-control" id="swal-name" aria-describedby="swal-carcode" placeholder="lat">
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label for="swal-surname">Longitude </label>
                                        <input type="text" class="form-control" id="swal-surname" aria-describedby="swal-surname" placeholder="lng">
                                    </div>
                                </div>
                            </div>
                            
                        </form>
                    </div>
                </div>
            `,
            confirmButtonText: 'ยืนยัน',
            focusConfirm: false,
            preConfirm: () => {
                test()
                const rbs = document.querySelectorAll('input[name="gender"]');
                let gender;
                    for (const rb of rbs) {
                        if (rb.checked) {
                            gender = rb.value;
                            break;
                        }
                    }
                const name = Swal.getPopup().querySelector('#swal-name').value
                const surname = Swal.getPopup().querySelector('#swal-surname').value
                const address = Swal.getPopup().querySelector('#swal-address').value
                const tel = Swal.getPopup().querySelector('#swal-tel').value
                const username = Swal.getPopup().querySelector('#swal-username').value
                const password = Swal.getPopup().querySelector('#swal-password').value
                const cpassword = Swal.getPopup().querySelector('#swal-cpassword').value
                if (!gender || !name || !surname || !address || !tel || !username || !password || !cpassword) {
                    Swal.showValidationMessage(`กรุณากรอกข้อมูลให้ครบ`)
                }else if(password !== cpassword){
                    Swal.showValidationMessage(`พาสเวิร์ด ไม่ตรงกัน !`)
                }else{
                    Swal.fire({
                    title: 'เเจ้งเตือน !',
                    text: "คุณต้องการเพิ่มพนักงานขับรถหรือไม่ ?",
                    icon: 'info',
                    showCancelButton: true,
                    confirmButtonColor: '#3085d6',
                    cancelButtonColor: '#d33',
                    confirmButtonText: 'ยืนยัน',
                    cancelButtonText:'ยกเลิก'
                }).then((result) => {
                    if (result.value) {
                        console.log("Add!")
                        Swal.fire('เพิ่มข้อมูลเรียบร้อย !', '', 'success')
                    }else{
                        Swal.fire('ยกเลิกการเพิ่มข้อมูล', '', 'info')
                    }
                });
                }
            }
            })
            }
            const cutaround = async()=>{
                const {data} = await axios.post('${pageContext.request.contextPath}/assignwork/cutaround')
                data.goodsin.map((val,i)=>{
                    goodsin.row.add([
                        i+1,
                        val.trackingid,
                        val.tel,
                        val.address
                    ]).draw( false );
                })
                data.goodsout.map((val,i)=>{
                    goodsout.row.add([
                        i+1,
                        val.trackingid,
                        val.tel,
                        val.address
                    ]).draw( false );
                })
                for (let i = 1; i < data.tsp.length; i++) {
                    goodsTraveling.row.add([
                        i,
                        data.tsp[i].job.trackingid,
                        data.tsp[i].job.tel,
                        data.tsp[i].job.address
                    ]).draw( false );
                    L.marker(L.latLng(data.tsp[i].taskUserLocation.lat,data.tsp[i].taskUserLocation.lng), { icon: IconHouse }).bindPopup(data.tsp[i].job.address).openPopup().addTo(map)
                }
                L.marker(L.latLng(data.tsp[0].taskUserLocation.lat,data.tsp[0].taskUserLocation.lng), { icon: IconWarehouse }).bindPopup(data.tsp[0].job.address).openPopup().addTo(map)
            
                data.latlng.map((val,i)=>{
                wpts.push(L.latLng(val[1],val[0]))
                var polyline1 = L.polyline(wpts, {color: "#FF0000"}).addTo(map);
            })
            }


            let vehicle1 = ["D1","D2"]
            let vehicle2 = ["D3", "D4", "D5"]


            const NewCut= async()=>{
                await $("#vehiclejob").show();
                await initmap();
            }
            const playback = () =>{

            }
            const getTSP = async(id)=>{
                const {data} = await axios.post('${pageContext.request.contextPath}/assignwork/gettsp/'+id)

            }
        setTimeout(() => {
            //initmap()
            getjob()
            getRoute()
        }, 0);
    </script>
    
</body>

</html>
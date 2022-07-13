<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
    <%
        String id = (String)request.getAttribute("id");
    %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>รายละเอียดจุดหมาย</title>
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
                <h3 class="text-dark mb-4">รายละเอียดจุดหมาย</h3>
                <p>รถหมายเลข: <%=id%></p>
                <div class="row mb-3">
                    <div class="col-lg-8">
                        <div class="row">
                            <div class="col">
                                <div class="shadow p-3 mb-5 bg-white rounded" id = "map" style="height: 80vh;"></div>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-4">
                        <div class="row md-3">
                            <div class="col">
                                <div class="card text-white bg-primary shadow">
                                    <div class="card-body">
                                        <div class="row mb-2">
                                            <div class="col">
                                                <p class="m-0">กำลังนำส่ง</p>
                                                <p class="m-0"><strong>65.2%</strong></p>
                                            </div>
                                            <div class="col-auto"><i class="fas fa-rocket fa-2x"></i></div>
                                        </div>
                                        <p class="text-white-50 small m-0"><i class="fas fa-arrow-up"></i>&nbsp;5% since last month</p>
                                    </div>
                                </div>
                            </div>
                            <div class="col">
                                <div class="card text-white bg-success shadow">
                                    <div class="card-body">
                                        <div class="row mb-2">
                                            <div class="col">
                                                <p class="m-0">จัดส่งเรียบร้อย</p>
                                                <p class="m-0"><strong>65.2%</strong></p>
                                            </div>
                                            <div class="col-auto"><i class="fas fa-rocket fa-2x"></i></div>
                                        </div>
                                        <p class="text-white-50 small m-0"><i class="fas fa-arrow-up"></i>&nbsp;5% since last month</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <br>
                        <div class="row">
                            <div class="col">
                                <div class="card shadow mb-4">
                                    <div class="card-header py-3">
                                        <h6 class="text-primary font-weight-bold m-0">รายการพัสดุที่จัดส่ง</h6>
                                    </div>
                                    <div class="card-body">
                                        <div class ="table-responsive">
                                            <table id ="datatable-jobs">
                                                <thead>
                                                <tr>
                                                    <th>ID</th>
                                                    <th>Tel</th>
                                                    <th>To</th>
                                                    <th>Status</th>
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
         // variable
		let data =[
			[
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
			],
			[
			L.latLng(16.251874894048246,103.2376402616501), // D1 -> D2
			L.latLng(16.25180794326172,103.23782265186311),
			L.latLng(16.25170494200716,103.23807477951051),
			L.latLng(16.25159679063178,103.23827862739564),
			L.latLng(16.251493789266572,103.23851466178895),
			L.latLng(16.25139078784737,103.23870241641998),
			L.latLng(16.251251735845845,103.23891162872314),
			L.latLng(16.251164184535163,103.23905646800995),
			L.latLng(16.25090668045418,103.2394587993622),
			L.latLng(16.250798528639557,103.23962509632112),
			L.latLng(16.2507006769465,103.239786028862),
			L.latLng(16.2506388758521,103.23989868164064),
			L.latLng(16.250520423700177,103.23987722396852),
			],
			[
			L.latLng(16.250602825204716,103.23993623256685), // D2 -> D3
			L.latLng(16.25049982331865,103.24010789394379),
			L.latLng(16.25041742177093,103.24023127555849),
			L.latLng(16.2503041195864,103.24041903018953),
			L.latLng(16.250185667232767,103.24059605598451),
			L.latLng(16.250092965341004,103.24076771736146),
			L.latLng(16.249964212641014,103.24095010757448),
			L.latLng(16.24970155687156,103.24136316776277),
			L.latLng(16.249577954035086,103.24159383773805),
			L.latLng(16.249402849883698,103.24186742305756),
			L.latLng(16.249253496219573,103.24210345745088),
			L.latLng(16.24905779124653,103.24237167835237),
			L.latLng(16.248913587457515,103.24264526367189),
			L.latLng(16.24860248673546,103.24305295944215),
			L.latLng(16.24837588020897,103.2434928417206),
			L.latLng(16.248077171206788,103.24393272399904),
			L.latLng(16.247768161416584,103.24440479278566),
			L.latLng(16.247098638538304,103.244286775589),
			L.latLng(16.246593919784413,103.24416875839233),
			L.latLng(16.246068598889,103.24410438537599),
			L.latLng(16.24552267568702,103.24406147003175),
			L.latLng(16.245079755749067,103.24410438537599),
			],
			[
			L.latLng(16.245102448982344,103.24407756328584), //D3 -> D4
			L.latLng(16.24488613890757,103.24409365653993),
			L.latLng(16.244700730082656,103.24412047863008),
			L.latLng(16.244520471335285,103.24415802955627),
			L.latLng(16.244283559587423,103.24421167373659),
			L.latLng(16.244062098347637,103.24425995349885),
			L.latLng(16.243794284422144,103.2443243265152),
			L.latLng(16.2435419209663,103.24441015720369),
			L.latLng(16.243222603476383,103.2445389032364),
			L.latLng(16.24295993870086,103.2446676492691),
			L.latLng(16.242779678357817,103.24474811553956),
			L.latLng(16.24252216329541,103.24487686157227),
			L.latLng(16.2422800988293,103.24503242969513),
			L.latLng(16.241852622555136,103.24535429477692),
			L.latLng(16.24161570759339,103.24555814266206),
			L.latLng(16.241394243349337,103.24577271938325),
			L.latLng(16.241162478175745,103.24601948261262),
			L.latLng(16.240910111342778,103.24630916118623),
			L.latLng(16.240750450526125,103.24652373790742),
			L.latLng(16.24055988744627,103.24678659439088),
			L.latLng(16.24039507598507,103.24706554412843),
			L.latLng(16.240199362195543,103.24741423130037),
			L.latLng(16.2398954903996,103.24808478355409),
			L.latLng(16.23979763328063,103.24840664863588),
			L.latLng(16.239710076869763,103.24868559837341),
			L.latLng(16.239581317371222,103.24912548065187),
			L.latLng(16.239545264696538,103.24944198131563),
			L.latLng(16.239529813548206,103.24989259243011),
			L.latLng(16.239498911247917,103.25034856796266),
			L.latLng(16.23951436239868,103.25070798397066),
			L.latLng(16.239555565461394,103.25118005275728),
			L.latLng(16.23962252041993,103.25156629085542),
			L.latLng(16.239668873839403,103.25185060501099),
			L.latLng(16.239746129514256,103.25208663940431),
			L.latLng(16.239596768515494,103.25211346149446),
			L.latLng(16.239298046177755,103.25202763080597),
			L.latLng(16.23895812020714,103.25192034244539),
			L.latLng(16.238731502567067,103.25185596942903),
			L.latLng(16.238340071482924,103.25172185897829),
			L.latLng(16.23802074554796,103.25161457061769),
			L.latLng(16.237809578112703,103.25157165527345),
			L.latLng(16.23754175567364,103.25149655342102),
			L.latLng(16.237191525780123,103.25141072273256),
			L.latLng(16.23701126015069,103.25105130672456),
			L.latLng(16.237026711496842,103.2506436109543),
			L.latLng(16.237037012393575,103.25030028820039),
			L.latLng(16.2370473132898,103.2500696182251),
			],
			[
			L.latLng(16.237073065527962,103.2500696182251), //D4 -> start
			L.latLng(16.237042162841757,103.25024664402008),
			L.latLng(16.237026711496842,103.25055778026582),
			L.latLng(16.237016410599548,103.25078845024109),
			L.latLng(16.23701126015069,103.25112104415894),
			L.latLng(16.23701126015069,103.25133562088014),
			L.latLng(16.237243030215318,103.25143218040468),
			L.latLng(16.237516003496825,103.25148582458496),
			L.latLng(16.237752923396382,103.25155556201936),
			L.latLng(16.238010444702713,103.2516199350357),
			L.latLng(16.23827826650372,103.25169503688814),
			L.latLng(16.238551238348748,103.25179696083069),
			L.latLng(16.238808758610052,103.25186669826509),
			L.latLng(16.23905597774374,103.25195252895357),
			L.latLng(16.239442257018133,103.25207591056825),
			L.latLng(16.239740979136872,103.2521402835846),
			L.latLng(16.23984913703352,103.25240314006807),
			L.latLng(16.240008798581787,103.25286984443665),
			L.latLng(16.240127257066106,103.2531648874283),
			L.latLng(16.24030751984015,103.25350284576417),
			L.latLng(16.24051868459392,103.25380325317384),
			L.latLng(16.240626842062934,103.25398027896883),
			L.latLng(16.240755600877073,103.25415730476381),
			L.latLng(16.240884359606927,103.25424313545227),
			L.latLng(16.241126425790863,103.25407683849335),
			L.latLng(16.241656910215927,103.253470659256),
			L.latLng(16.24217709258315,103.25277864933015),
			L.latLng(16.242645770567442,103.25212955474855),
			L.latLng(16.243026892500488,103.25158774852754),
			L.latLng(16.243392562850076,103.25111567974092),
			L.latLng(16.24392819143048,103.25033247470857),
			L.latLng(16.244443217535824,103.24961364269258),
			L.latLng(16.2449994442145,103.2487714290619),
			L.latLng(16.24529300766059,103.24818670749664),
			L.latLng(16.245648373351038,103.24762344360352),
			L.latLng(16.24597798733038,103.24700117111207),
			L.latLng(16.246539359867025,103.2460141181946),
			L.latLng(16.24702347801516,103.2453328371048),
			L.latLng(16.2473427893328,103.24483931064607),
			L.latLng(16.247641499450776,103.24443697929384),
			L.latLng(16.247986560573853,103.24386835098268),
			L.latLng(16.248357371851878,103.24339091777803),
			L.latLng(16.24870758186114,103.2428812980652),
			L.latLng(16.24901144004016,103.2423985004425),
			L.latLng(16.24924834609119,103.24204981327058),
			L.latLng(16.24949555210093,103.24163138866426),
			L.latLng(16.249747907915197,103.24122905731203),
			L.latLng(16.25002601394753,103.24079990386964),
			L.latLng(16.25035047048797,103.24030101299287),
			L.latLng(16.2506285756678,103.23983430862428),
			L.latLng(16.250942731045814,103.23938906192781),
			L.latLng(16.251164184535163,103.2389920949936),
			L.latLng(16.25138048770248,103.23863267898561),
			L.latLng(16.25161739089834,103.23821961879732),
			L.latLng(16.251818243384207,103.23767781257631),
			L.latLng(16.25204999599747,103.23711454868318),
			L.latLng(16.252132396860773,103.23689997196199),
			L.latLng(16.252384749289746,103.23698580265045),
			L.latLng(16.252616501235032,103.23708772659303),
			L.latLng(16.252910053306746,103.23722183704378),
			L.latLng(16.253213904989355,103.23732912540436),
			L.latLng(16.253466256029686,103.23737740516664),
			L.latLng(16.253888557045794,103.23735594749452),
			L.latLng(16.25407395720524,103.23735058307648),
			L.latLng(16.25435318367779,103.23725938796998),
			L.latLng(16.25434288368815,103.23702335357667),
			L.latLng(16.25434288368815,103.23675513267519),
			L.latLng(16.254337733693145,103.23637425899507),
			L.latLng(16.254384083643462,103.23620796203615),
			L.latLng(16.254626133206404,103.23619723320009),
			L.latLng(16.25474458288387,103.23610067367554),
			L.latLng(16.25471883296007,103.2357305288315),
			L.latLng(16.25471883296007,103.2353925704956),
			L.latLng(16.254723982945098,103.23515117168428),
			L.latLng(16.25485788250856,103.23514580726625),
			]
		]
		let ownserver ='http://192.168.1.33:5055/route/v1';
		let hostserver='https://api.zyanwoa.com/osrm/v1/route/v1';
		let i = 0;
		let j = 1;
		let timeinterval = 3000;
		let marker;
		let workdetail;
		let arrtime;
		let next = true;
		let house;
        // Init Open Street Map.
		var map = L.map('map').setView([16.250149, 103.240739], 15);
		L.tileLayer('http://{s}.tile.osm.org/{z}/{x}/{y}.png', {
			attribution: '&copy; <a href="http://osm.org/copyright">OpenStreetMap</a> contributors'
		}).addTo(map);

        let datatablejobs;
        $(document).ready( function () {
            workdetail = $('#datatable-workdetail').DataTable();
			arrtime = $('#datatable-arrtime').DataTable();
			datatablejobs = $('#datatable-jobs').DataTable();
			for (let i = 0; i < 5; i++) {
				datatablejobs.row.add([
                        "THP00000"+getRandomInt(10,99),
                        "08888888"+getRandomInt(10,99),
                        "D"+i,
                        i != 0 ? `<span style="color:DodgerBlue;">กำลังจัดส่ง...</span>`:`<span style="color:SpringGreen;">เสร็จสิ้น</span>`
                    ]).draw( false );
				
			}


            } );
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

        const setmark = ()=>{
			for (let i = 0; i < data.length; i++) {
				if(i >= data.length-1){
					L.marker(data[i][data[i].length-1], { icon: IconWarehouse }).addTo(map)
				}else{
					L.marker(data[i][data[i].length-1], { icon: IconHouse }).addTo(map)
				}	
			}
		}
		// wayPointDrawer
		const wayPointDrawer = (waypts,color)=>{
			if(marker){
				marker.remove()
			}
			control = L.Routing.control(L.extend({serviceUrl: ownserver}, {
				waypoints: waypts,
				createMarker: function() { return null; },
				show: false,
			routeDragInterval: 500,
				routeWhileDragging: true,
				reverseWaypoints: false,
				showAlternatives: false, // แสดงทางเลือกของเส้นทาง
			fitSelectedRoutes: false,
				lineOptions: {
			addWaypoints:false,
				styles: [{color: color, opacity: 0.7, weight: 5}]
			}
			}))
			control.addTo(map);
			marker = L.marker(waypts[waypts.length-1], { icon: Iconvehicle }).addTo(map)
			//L.Routing.errorControl(control).addTo(map); // เำื่อโฟกัสที่เส้น
		}
        
		// variable to server
		let deiverworkid;
		let workid;
		const getworkdetail = async() =>{
			const {data} = await axios.get('${pageContext.request.contextPath}/getworkdetail')
            
			await workdetail.clear().draw()

			deiverworkid = data.length != 0 ? data[0].distanceid : null 
			workid = data.length != 0 ? data[0].workid.workid : null 
			console.log("ID:"+deiverworkid);
			data.map((val,i)=>{
				workdetail.row.add([
                        i+1,
                        val.from,
                        val.to,
                        val.distance+" Km.",
						`<button type="button" class="btn btn-success" onclick="updatestatus(`+val.distanceid+`)">Done</button>`
                    ]).draw( false );
			})
            
			//console.log(workdetail.row);
		}

        const getdashboard = async() =>{
			
        }

		function getRandomInt(min, max) {
			min = Math.ceil(min);
			max = Math.floor(max);
			return Math.floor(Math.random() * (max - min) + min); //The maximum is exclusive and the minimum is inclusive
}

		const getarrivetime = async() =>{
			const {data} = await axios.get('${pageContext.request.contextPath}/getarrivetime')
			await arrtime.clear().draw()
			data.map((val,i)=>{
				arrtime.row.add([
                        i+1,
                        val.point,
                        val.distance +" กิโลเมตร",
                        val.arrivetime
                    ]).draw( false );
			})
			//console.log(workdetail.row);
		}
		const updatestatus = async(id) =>{
			Swal.fire({
			title: 'กรุณารอสักครู่',
			html: 'กำลังดึงข้อมูลงานคนขับ<br>อัพเดท...<b></b><br>ดึงข้อมูล...<d></d>',
			didOpen: async() => {
				await Swal.showLoading()
				const content = Swal.getContent()
				const {data} = await axios.get('${pageContext.request.contextPath}/updatestatus/'+id)
				if (content) {
					if(data.status == "success"){
						const b = content.querySelector('b')
					b.textContent = "Done!"
					}
				}
				await getworkdetail()
                await getdashboard()
				await swal.close();
			}
			})
			next = false
		}
		const updateLocation = async(lat,lng)=>{
			payload ={
				lat:lat,
				lng:lng,
				deiverworkid:deiverworkid,
				workid:workid
			}
			const {data} = await axios.post('${pageContext.request.contextPath}/calculateroute',payload)
			await getarrivetime()
		}
		//playblck
		setInterval(()=>{
			let wpts = [];
			
			if(data[i] && j < data[i].length){
				wpts.push(data[i][j-1])
				wpts.push(data[i][j])
				console.log(data[i][j].lng);
				updateLocation(data[i][j].lat,data[i][j].lng)
				wayPointDrawer(wpts,"#FF0000")
				j++
			}else if (next){
				console.log("End");
				
			} else {
				i++ 
				j = 0
				next = true
			}
			
		},timeinterval)
		//getworkdetail()
        getdashboard()
		//getarrivetime()
		setmark()
		//house = L.marker(data[0][0], { icon: IconWarehouse }).addTo(map)
            
    </script>
    
</body>

</html>
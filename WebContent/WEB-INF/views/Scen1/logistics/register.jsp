<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<title>Register</title>
<%@ include file="/include/homemapcss.jsp"%>
<%@ include file="/include/css.jsp"%>
</head>
<body style="background-color: aliceblue;">
    <div class="container">
        <div class="card shadow-lg o-hidden border-0 my-5">
            <div class="card-body p-0">
                <div class="row">
                    <div class="col">
                        <div class="p-5">
                            <div class="text-center">
                                <h4 class="text-dark mb-4">สมัครสมาชิก</h4>
                            </div>
                            <form class="user needs-validation" novalidate>
                                <div class="form-group row">
                                    <div class="col-sm-6 mb-3 mb-sm-0"><input class="form-control" type="text" id="nameInput" placeholder="ชื่อ" name="first_name" required>
                                      <div class="invalid-tooltip">กรุณากรอกชื่อผู้สมัคร</div>
                                    </div>
                                    <div class="col-sm-6"><input class="form-control" type="text" id="surnameInput" placeholder="นามสกุล" name="last_name" required>
                                      <div class="invalid-tooltip">กรุณากรอกนามสกุลผู้สมัคร</div>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <div class="col-sm-8 mb-3 mb-sm-0"><input class="form-control" type="text" id="emailInput" placeholder="อีเมล" name="email"></div>
                                    <div class="col-sm-4"><input class="form-control" type="text" id="telInput" placeholder="เบอร์โทร" name="tel"></div>
                                </div>
                                
                                <div class="form-group">
                                    <input class="form-control" type="text" id="companynameInput" placeholder="ชื่อบริษัท" name="company_name">
                                </div>
                                    <div class="form-group"><div class="custom-file">
                                        <input type="file" class="custom-file-input" id="companyFile">
                                        <label class="custom-file-label" for="customFile">ทะเบียนบริษัท / หจก.</label>
                                      </div>
                                    </div>
                                    <div class="form-group">
                                      <input class="form-control" type="text" id="addressInput" placeholder="ที่อยู่" name="address">
                                    </div>
                                <div class="form-group">
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
				                </div>
				                <div class="shadow p-3 mb-5 bg-white rounded" id="map" style="height: 40vh"></div>
                                <button type="button" class="btn btn-primary btn-lg btn-block" onclick="register()">สมัครสมาชิก</button>
                                <hr>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
<%@ include file="/include/js.jsp"%> 
<%@ include file="/include/homemapfunc.jsp"%>
<script>
	var mapadd = L.map("map").setView([16.250149, 103.240739], 14);
	L.tileLayer("http://{s}.tile.osm.org/{z}/{x}/{y}.png", {
	  attribution:
	    '&copy; <a href="http://osm.org/copyright">OpenStreetMap</a> contributors',
	}).addTo(mapadd);

    //   getprovince();
          var IconHouse = L.icon({
        iconUrl: "${pageContext.request.contextPath}/contents/img/house.png",
        iconSize: [30, 30],
        //iconAnchor: [20,25],
      });
      var IconWarehouse = L.icon({
        iconUrl:
          "${pageContext.request.contextPath}/contents/img/warehouse.png",
        iconSize: [30, 30],
        iconAnchor: [20, 25],
      });
      
      var Iconmarker = L.icon({
          iconUrl:
            "${pageContext.request.contextPath}/contents/img/maps-and-flags.png",
          iconSize: [30, 30],
          iconAnchor: [20, 25],
        });
	let lat;
    let lng;
    let ping;
    let mapping;
    mapadd.on("click", function (e) {
          str = "lat:" + e.latlng.lat + ", lng:" + e.latlng.lng;

          lat = e.latlng.lat;
          lng = e.latlng.lng;
          if (ping) {
            ping.remove();
          }
          if(mapping){
        		mapping.remove()
        	}
          ping = L.marker(e.latlng, { icon: IconHouse }).addTo(mapadd);
          console.log(str);
        });
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
        if(mapping){
    		mapping.remove()
    	}
        mapping = L.marker([province.lat, province.lng], { icon: Iconmarker }).addTo(mapadd);
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
          if(mapping){
      		mapping.remove()
      	}
          mapping = L.marker([amphur.lat, amphur.lng], { icon: Iconmarker }).addTo(mapadd);
          mapadd.flyTo([amphur.lat, amphur.lng], 12);
        };
    const setmap = () => {
        var tambon = JSON.parse($("#TAMBON").val());
        console.log(tambon.lat + "," + tambon.lng + "," + tambon.tambon);
        if(mapping){
      		mapping.remove()
      	}
          mapping = L.marker([tambon.lat, tambon.lng], { icon: Iconmarker }).addTo(mapadd);
        mapadd.flyTo([tambon.lat, tambon.lng], 12);
      };
	getthmap()
  const register = async() =>{
    console.log(typeof $("#province").val() + " : " + $("#province").val() );
    if(typeof $("#province").val() === 'object'){
      console.log(JSON.parse($("#province").val()).province);
    }else{
      console.log("ไม่มีข้อมูล");
    }
		let nameInput = $("#nameInput").val()
		let surnameInput = $("#surnameInput").val()
		let emailInput = $("#emailInput").val()
		let telInput = $("#telInput").val()
		let passwordInput = $("#passwordInput").val()
		let conpasswordInput = $("#conpasswordInput").val()
		let companynameInput = $("#companynameInput").val()
		//let companyFile = $("#companyFile").val()
    let addressInput = $("#addressInput").val()
		let province = $("#province").val().charAt(0) === '{' ? JSON.parse($("#province").val()).province : ''  
		let AMPHUR = $("#AMPHUR").val().charAt(0) === '{' ? JSON.parse($("#AMPHUR").val()).amphur : '' 
		let TAMBON = $("#TAMBON").val().charAt(0) === '{' ? JSON.parse($("#TAMBON").val()).tambon : '' 

    // console.log(
    //   "nameInput: " + nameInput + "\n",
    //   "surnameInput: " + surnameInput + "\n",
    //   "emailInput: " + emailInput + "\n",
    //   "telInput: " + telInput + "\n",
    //   "companynameInput: " + companynameInput + "\n",
    //   "addressInput: " + addressInput + "\n",
    //   "province: " + province + "\n",
    //   "AMPHUR: " + AMPHUR + "\n",
    //   "TAMBON: " + TAMBON + "\n",
    //   "lat: " + lat + "\n",
    //   "lng: " + lng + "\n",
    //   );
		if(nameInput != '' &&
		surnameInput != '' && 
		emailInput != '' && 
		telInput != '' &&
		companynameInput != '' &&
		province != '' &&
		AMPHUR != '' &&
		TAMBON != '' &&
	    lat &&
	    lng &&
    	addressInput != ''
    ) {
      console.log("ครบ");
       let payload = await {
          name:nameInput,
          surname:surnameInput,
          email:emailInput,
          phone:telInput,
          //password:passwordInput,
          providername:companynameInput,
          province:province,
          tambon:TAMBON,
          amphur:AMPHUR,
          latitude:lat,
          longitude:lng,
          address:addressInput
        }
        console.log(payload);
        const {data} = await axios.post('${pageContext.request.contextPath}/provider/register/add',payload)
        if(data.status == "ok"){
          Swal.fire({
            icon: 'success',
            title: 'การสมัครการสมัครสมาชิกเสร็จสิ้น รอการยืนยัน',
            showConfirmButton: false,
            timer: 1500
          })
        }else{
          Swal.fire({
            icon: 'error',
            title: 'การสมัครสมาชิกผิดพลาด',
            showConfirmButton: false,
            timer: 1500
          })
        }
		} else{
      Swal.fire({
            icon: 'error',
            title: 'ข้อมูลไม่ครบ',
            showConfirmButton: false,
            timer: 1500
          })
		}
	}
</script>
</body>
</html>
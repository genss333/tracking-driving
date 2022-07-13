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
        <%@ include file="/include/sidebar1/sidebarlogistics.jsp"%>

        <div class="d-flex flex-column" id="content-wrapper">
            <div id="content">
              <!-- import Side Bar -->
            <%@ include file="/include/headerbar.jsp"%>

            <div class="container-fluid">
                <!-- Page Name-->
                <h3 class="text-dark mb-4">จัดการพนักงานขับรถ</h3>
                <div class="row">
                    <div class="col">
                        <div class="card">
                            <div class="card-header ">
                                <div class="row">
                                    <div class="col">พนักงานขับรถ</div>
                                    <div class="col text-right"><button type="button" class="btn btn-success" onclick="addriver()"><i class="fas fa-plus-circle"></i>   เพิ่มพนักงานขับรถ</button></div>
                                </div>
                            </div>
                            <div class="card-body">
                                <div class ="table-responsive">
                                    <table id ="datatable-drivers">
                                        <thead>
                                        <tr>
                                            <th>#</th>
                                            <th>ชื่อ</th>
                                            <th>นามสกุล</th>
                                            <th>ที่อยู่</th>
                                            <th>เบอร์โทร</th>
											<th>เพศ</th>
                                            <th>Username</th>
											<th>Password</th>
											<th>Action</th>
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
                <div class="text-center my-auto copyright"><span>Live Tracking Logistics 2021</span></div>
            </div>
        </footer>
    </div><a class="border rounded d-inline scroll-to-top" href="#page-top"><i class="fas fa-angle-up"></i></a></div>
    <%@ include file="/include/js.jsp"%>
    <%@ include file="/include/homemapfunc.jsp"%>
    <script>
		let driver;
		let driverdatas = [
				{
					"firstname":"Driver1",
					"surname":"Driver11",
					"address":"1-1-1-1",
					"gender":"M",
					"username":"driver1",
					"password":"1234"
					
				},
				{
					"firstname":"Driver2",
					"surname":"Driver22",
					"address":"2-2-2-2",
					"gender":"M",
					"username":"driver2",
					"password":"1234"
					
				},
				{
					"firstname":"Driver3",
					"surname":"Driver33",
					"address":"3-3-3-3",
					"gender":"F",
					"username":"driver3",
					"password":"1234"
					
				},
				{
					"firstname":"Driver4",
					"surname":"Driver44",
					"address":"4-4-4-4",
					"gender":"M",
					"username":"driver4",
					"password":"1234"
					
				},
				{
					"firstname":"Driver5",
					"surname":"Driver55",
					"address":"5-5-5-5",
					"gender":"F",
					"username":"driver5",
					"password":"1234"
					
				},
				{
					"firstname":"Driver6",
					"surname":"Driver66",
					"address":"6-6-6-6",
					"gender":"M",
					"username":"driver6",
					"password":"1234"
					
				},
				{
					"firstname":"Driver7",
					"surname":"Driver77",
					"address":"7-7-7-7",
					"gender":"M",
					"username":"driver7",
					"password":"1234"
					
				},
				{
					"firstname":"Driver8",
					"surname":"Driver88",
					"address":"8-8-8-8",
					"gender":"M",
					"username":"driver8",
					"password":"1234"
					
				},
				{
					"firstname":"Driver9",
					"surname":"Driver99",
					"address":"9-9-9-9",
					"gender":"F",
					"username":"driver9",
					"password":"1234"
					
				},
				{
					"firstname":"Driver10",
					"surname":"Driver10",
					"address":"10-10-10-10",
					"gender":"M",
					"username":"driver10",
					"password":"1234"
					
				}
				]
         $(document).ready( function () {
            driver = $('#datatable-drivers').DataTable();

			/*
											<th>#</th>
                                            <th>ชื่อ</th>
                                            <th>นามสกุล</th>
                                            <th>ที่อยู่</th>
                                            <th>เบอร์โทร</th>
											<th>เพศ</th>
                                            <th>Username</th>
											<th>Password</th>
											<th>Action</th>
			*/
            for (let i = 0; i < driverdatas.length; i++) {
				driver.row.add([
                        i+1,
                        driverdatas[i].firstname,
                        driverdatas[i].surname,
                        driverdatas[i].address,
                        "0888888"+getrandomNumber(100),
						driverdatas[i].gender =="M" ? "ชาย":"หญิง",
						driverdatas[i].username,
						driverdatas[i].password,
						`
                        <button type="button" class="btn btn-warning" onclick="update(`+i+`)"><i class="fas fa-cog"></i>    เเก้ไข</button>
                        <button type="button" class="btn btn-danger" onclick="deletevehicle(`+i+`)"><i class="far fa-trash-alt">    ลบ</i></button>
                        `
                    ]).draw( false );
         }
         });
         
         const getrandomNumber = (max)=>{
            return Math.floor(Math.random() * max);
         }


//value="`+driver.row(val).data()[1]+`"
        const clickupdate = (val) =>{
            console.log(driver.row(val).data()[5]);
            switch (driver.row(val).data()[5]) {
                case 'ชาย':
                    document.getElementById("male").checked = true;
                    break;
                case 'หญิง':
                    document.getElementById("female").checked = true;
                    break;
                default:
                    break;
            }
        }
         const update = (val) =>{
         Swal.fire({
            title: 'แก้ไข้ข้อมูลพนักงานขับรถ',
            html: `
            <body>
                <div class="row">
                    <div class="col text-left">
                        <form>
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label for="swal-name">ชื่อ</label>
                                        <input type="text" class="form-control" id="swal-name" aria-describedby="swal-carcode" placeholder="ชื่อ" value="`+driver.row(val).data()[1]+`">
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label for="swal-surname">นามสกุล</label>
                                        <input type="text" class="form-control" id="swal-surname" aria-describedby="swal-surname" placeholder="นามสกุล" value="`+driver.row(val).data()[2]+`">
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-8">
                                    <div class="form-group">
                                        <label for="swal-address">ที่อยู่</label>
                                        <input type="text" class="form-control" id="swal-address" aria-describedby="swal-address" placeholder="ที่อยู่" value="`+driver.row(val).data()[3]+`">
                                    </div>
                                </div>
                                <div class="col-md-4">
                                    <div class="form-group">
                                        <label for="swal-tel">เบอร์โทร</label>
                                        <input type="text" class="form-control" id="swal-tel" aria-describedby="swal-tel" placeholder="เบอร์โทร" value="`+driver.row(val).data()[4]+`">
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col">
                                    <div class="form-group">
                                        <label for="swal-tel">เพศ:</label>
                                        <div class="form-check form-check-inline">
                                            <input class="form-check-input" type="radio" name="gender" id="male" value="male">
                                            <label class="form-check-label" for="male">
                                                Male
                                            </label>
                                            </div>
                                            <div class="form-check form-check-inline">
                                            <input class="form-check-input" type="radio" name="gender" id="female" value="female">
                                            <label class="form-check-label" for="female">
                                                Female
                                            </label>
                                            </div>
                                            <div class="form-check form-check-inline">
                                            <input class="form-check-input" type="radio" name="gender" id="other" value="other">
                                            <label class="form-check-label" for="other">
                                                Other
                                            </label>
                                            </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col">
                                    <div class="form-group">
                                        <label for="swal-username">Username</label>
                                        <input type="text" class="form-control" id="swal-username" aria-describedby="swal-username" placeholder="username" value="`+driver.row(val).data()[6]+`">
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col">
                                    <div class="form-group">
                                        <label for="swal-password">Password</label>
                                        <input type="text" class="form-control" id="swal-password" aria-describedby="swal-password" placeholder="password" value="`+driver.row(val).data()[7]+`">
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col">
                                    <div class="form-group">
                                        <label for="swal-cpassword">Confirm Password</label> 
                                        <input type="text" class="form-control" id="swal-cpassword" aria-describedby="swal-cpassword" placeholder="confirm password" value="`+driver.row(val).data()[7]+`">
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
                </body>
            

            `,
            confirmButtonText: 'ยืนยัน',
            focusConfirm: false,
            didOpen:()=>{
                console.log(driver.row(val).data()[5]);
                switch (driver.row(val).data()[5]) {
                    case 'ชาย':
                        document.getElementById("male").checked = true;
                        break;
                    case 'หญิง':
                        document.getElementById("female").checked = true;
                        break;
                    default:
                        break;
                }
            },
            preConfirm: () => {
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
                }else if(driver.row(val).data()[1] == name &&
                        driver.row(val).data()[2] == surname &&
                        driver.row(val).data()[3] == address &&
                        driver.row(val).data()[4] == tel &&
                        gender == gender &&
                        driver.row(val).data()[6] == username &&
                        driver.row(val).data()[7] == password &&
                        driver.row(val).data()[7] == cpassword){
                        Swal.fire({
                        position: 'center',
                        icon: 'success',
                        title: 'ไม่มีการเปลี่ยนแปลง',
                        showConfirmButton: false,
                        timer: 1500
                        })
                }else if(password !== cpassword){
                    Swal.showValidationMessage(`พาสเวิร์ด ไม่ตรงกัน !`)
                }else{
                    Swal.fire({
                    title: 'เเจ้งเตือน !',
                    text: "คุณต้องการบันทึกการเเก้ไขหรือไม่ ?",
                    icon: 'info',
                    showCancelButton: true,
                    confirmButtonColor: '#3085d6',
                    cancelButtonColor: '#d33',
                    confirmButtonText: 'ยืนยัน',
                    cancelButtonText:'ยกเลิก'
                }).then((result) => {
                    if (result.value) {
                        console.log("Update!")
                        Swal.fire('เเก้ไขเรียบร้อย !', '', 'success')
                    }else{
                        Swal.fire('ยกเลิกการเเก้ไขข้อมูล', '', 'info')
                    }
                });
                }
            }
            })
         }
         const addriver = () =>{
         Swal.fire({
            title: 'เพิ่มพนักงานขับรถ',
            html: `

            <div class="row">
                    <div class="col text-left">
                        <form>
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label for="swal-name">ชื่อ</label>
                                        <input type="text" class="form-control" id="swal-name" aria-describedby="swal-carcode" placeholder="ชื่อ">
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label for="swal-surname">นามสกุล</label>
                                        <input type="text" class="form-control" id="swal-surname" aria-describedby="swal-surname" placeholder="นามสกุล">
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-8">
                                    <div class="form-group">
                                        <label for="swal-address">ที่อยู่</label>
                                        <input type="text" class="form-control" id="swal-address" aria-describedby="swal-address" placeholder="ที่อยู่">
                                    </div>
                                </div>
                                <div class="col-md-4">
                                    <div class="form-group">
                                        <label for="swal-tel">เบอร์โทร</label>
                                        <input type="text" class="form-control" id="swal-tel" aria-describedby="swal-tel" placeholder="เบอร์โทร">
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col">
                                    <div class="form-group">
                                        <label for="swal-tel">เพศ:</label>
                                        <div class="form-check form-check-inline">
                                            <input class="form-check-input" type="radio" name="gender" id="male" value="male">
                                            <label class="form-check-label" for="male">
                                                Male
                                            </label>
                                            </div>
                                            <div class="form-check form-check-inline">
                                            <input class="form-check-input" type="radio" name="gender" id="female" value="female">
                                            <label class="form-check-label" for="female">
                                                Female
                                            </label>
                                            </div>
                                            <div class="form-check form-check-inline">
                                            <input class="form-check-input" type="radio" name="gender" id="other" value="other">
                                            <label class="form-check-label" for="other">
                                                Other
                                            </label>
                                            </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col">
                                    <div class="form-group">
                                        <label for="swal-username">Username</label>
                                        <input type="text" class="form-control" id="swal-username" aria-describedby="swal-username" placeholder="username">
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col">
                                    <div class="form-group">
                                        <label for="swal-password">Password</label>
                                        <input type="text" class="form-control" id="swal-password" aria-describedby="swal-password" placeholder="password">
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col">
                                    <div class="form-group">
                                        <label for="swal-cpassword">Confirm Password</label> 
                                        <input type="text" class="form-control" id="swal-cpassword" aria-describedby="swal-cpassword" placeholder="confirm password">
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
         const deletevehicle = (val) =>{
            Swal.fire({
            title: 'แจ้งเตือน ?',
            text: "ต้องการลบข้อมูล "+driver.row(val).data()[1]+" หรือไม่ !",
            icon: 'info',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'ยืนยัน',
            cancelButtonText:'ยกเลิก'
        }).then((result) => {
            if (result.value) {
                console.log("Deleted!");
            }else{
                console.log("not Delete!");
            }
        });
        }
         //setTimeout(()=>{deletevehicle()},100)
    </script>
</body>
</html>
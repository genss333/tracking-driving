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
    <title>จัดการรถส่งพัสดุ</title>
    <%@ include file="/include/homemapcss.jsp"%> <%@ include
    file="/include/css.jsp"%>
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
            <h3 class="text-dark mb-4">จัดการรถส่งพัสดุ</h3>
            <div class="row">
              <div class="col">
                <div class="card">
                  <div class="card-header">
                    <div class="row">
                      <div class="col">รถส่งพัสดุ</div>
                      <div class="col text-right">
                        <button
                          type="button"
                          class="btn btn-success"
                          onclick="addvehicle()"
                        >
                          <i class="fas fa-plus-circle"></i> เพิ่มรถส่งพัสดุ
                        </button>
                      </div>
                    </div>
                  </div>
                  <div class="card-body">
                    <div class="table-responsive">
                      <table id="datatable-vehicles2">
                        <thead>
                          <tr>
                            <th>#</th>
                            <th>ทะเบียนรถ</th>
                            <th>ประเภทรถ</th>
                            <th>ยี่ห้อรถ</th>
                            <th>เลขตัวรถ</th>
                            <th>เลขเครื่องยนต์</th>
                            <th>สี</th>
                            <th>เชื้อเพลิง</th>
                            <th>Action</th>
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
        <footer class="bg-white sticky-footer">
          <div class="container my-auto">
            <div class="text-center my-auto copyright">
              <span>Live Tracking Logistics 2021</span>
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
      let vehicles;
      let center = ["ศูนย์ A", "ศูนย์ B", "ศูนย์ C"];
      let vehicletype = [
        "รถยนต์บรรทุกส่วนบุคคล",
        "รถจักรยานยนต์",
        "รถยนต์นั่งส่วนบุคคล",
      ];
      let vehiclebrand = ["Nissan", "Toyota", "Honda"];
      let vehiclecolor = ["ดำ", "เเดง", "ขาว"];
      let vehiclefuel = ["แก๊สโซฮอล์", "ดีเซล"];
      $(document).ready(function () {
        vehicles = $("#datatable-vehicles2").DataTable();
        for (let i = 0; i < 20; i++) {
          vehicles.row
            .add([
              i + 1,
              "LTL " + getrandomNumber(3000),
              vehicletype[getrandomNumber(vehicletype.length)],
              vehiclebrand[getrandomNumber(vehiclebrand.length)],
              getrandomNumber(999999999999999),
              getrandomNumber(999999999999999),
              vehiclecolor[getrandomNumber(vehiclecolor.length)],
              vehiclefuel[getrandomNumber(vehiclefuel.length)],
              `
                        <button type="button" class="btn btn-warning" onclick="update(` +
                i +
                `)"><i class="fas fa-cog"></i></button>
                        <button type="button" class="btn btn-danger" onclick="deletevehicle(` +i +`)">
                <i class="far fa-trash-alt"></i></button>
                        `,
            ])
            .draw(false);
        }
      });

      const getrandomNumber = (max) => {
        return Math.floor(Math.random() * max);
      };

      const update = (val) => {
        Swal.fire({
          title: "แก้ไข้ข้อมูลรถส่งพัสดุ",
          html:
            `
            <div class="row">
                    <div class="col text-left">
                        <form>
                            <div class="row">
                                <div class="col-md-3">
                                    <div class="form-group">
                                        <label for="swal-carcode">ทะเบียนรถ</label>
                                        <input type="text" class="form-control" id="swal-carcode" aria-describedby="swal-carcode" placeholder="ทะเบียนรถ" value="` +
            vehicles.row(val).data()[1] +
            `">
                                    </div>
                                </div>
                                <div class="col-md-9">
                                    <div class="form-group">
                                        <label for="swal-cartype">ประเภทรถ</label>
                                        <input type="text" class="form-control" id="swal-cartype" aria-describedby="swal-cartype" placeholder="ประเภทรถ" value="` +
            vehicles.row(val).data()[2] +
            `">
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label for="swal-carbrand">ยี่ห้อรถ</label>
                                        <input type="text" class="form-control" id="swal-carbrand" aria-describedby="swal-carbrand" placeholder="ยี่ห้อรถ" value="` +
            vehicles.row(val).data()[3] +
            `">
                                    </div>
                                </div>
                                <div class="col-md-4">
                                    <div class="form-group">
                                        <label for="swal-carfuel">เชื้อเพลง</label>
                                        <input type="text" class="form-control" id="swal-carfuel" aria-describedby="swal-carfuel" placeholder="เชื้อเพลง" value="` +
            vehicles.row(val).data()[7] +
            `">
                                    </div>
                                </div>
                                <div class="col-md-2">
                                    <div class="form-group">
                                        <label for="swal-carcolor">สี</label>
                                        <input type="text" class="form-control" id="swal-carcolor" aria-describedby="swal-carcolor" placeholder="สี" value="` +
            vehicles.row(val).data()[6] +
            `">
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col">
                                    <div class="form-group">
                                        <label for="swal-carnumber">เลขตัวรถ</label>
                                        <input type="text" class="form-control" id="swal-carnumber" aria-describedby="swal-carnumber" placeholder="เลขตัวรถ" value="` +
            vehicles.row(val).data()[4] +
            `">
                                    </div>
                                </div>
                                <div class="col">
                                    <div class="form-group">
                                        <label for="swal-carenginenumber">เลขเครื่องยนต์</label>
                                        <input type="text" class="form-control" id="swal-carenginenumber" aria-describedby="swal-carenginenumber" placeholder="เลขเครื่องยนต์" value="` +
            vehicles.row(val).data()[5] +
            `">
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            `,
          width: "80%",
          confirmButtonText: "ยืนยัน",
          focusConfirm: false,
          preConfirm: () => {
            const carcode =
              Swal.getPopup().querySelector("#swal-carcode").value;
            const cartype =
              Swal.getPopup().querySelector("#swal-cartype").value;
            const carbrand =
              Swal.getPopup().querySelector("#swal-carbrand").value;
            const carfuel =
              Swal.getPopup().querySelector("#swal-carfuel").value;
            const carcolor =
              Swal.getPopup().querySelector("#swal-carcolor").value;
            const carnumber =
              Swal.getPopup().querySelector("#swal-carnumber").value;
            const carenginenumber = Swal.getPopup().querySelector(
              "#swal-carenginenumber"
            ).value;
            if (
              !carcode ||
              !cartype ||
              !carbrand ||
              !carfuel ||
              !carcolor ||
              !carnumber ||
              !carenginenumber
            ) {
              Swal.showValidationMessage(`กรุณากรอกข้อมูลให้ครบ`);
            } else if (
              vehicles.row(val).data()[1] == carcode &&
              vehicles.row(val).data()[2] == cartype &&
              vehicles.row(val).data()[3] == carbrand &&
              vehicles.row(val).data()[4] == carnumber &&
              vehicles.row(val).data()[5] == carenginenumber &&
              vehicles.row(val).data()[6] == carcolor &&
              vehicles.row(val).data()[7] == carfuel
            ) {
              Swal.fire({
                position: "center",
                icon: "success",
                title: "ไม่มีการเปลี่ยนแปลง",
                showConfirmButton: false,
                timer: 1500,
              });
            } else {
              Swal.fire({
                title: "เเจ้งเตือน !",
                text: "คุณต้องการบันทึกการเเก้ไขหรือไม่ ?",
                icon: "info",
                showCancelButton: true,
                confirmButtonColor: "#3085d6",
                cancelButtonColor: "#d33",
                confirmButtonText: "ยืนยัน",
                cancelButtonText: "ยกเลิก",
              }).then((result) => {
                if (result.value) {
                  console.log("Update!");
                  Swal.fire("เเก้ไขเรียบร้อย !", "", "success");
                } else {
                  Swal.fire("ยกเลิกการเเก้ไขข้อมูล", "", "info");
                }
              });
            }
          },
        });
      };
      const addvehicle = () => {
        Swal.fire({
          title: "เพิ่มรถส่งพัสดุ",
          html: `
            <div class="row">
                    <div class="col text-left">
                        <form>
                            <div class="row">
                                <div class="col-md-3">
                                    <div class="form-group">
                                        <label for="swal-carcode">ทะเบียนรถ</label>
                                        <input type="text" class="form-control" id="swal-carcode" aria-describedby="swal-carcode" placeholder="ทะเบียนรถ">
                                    </div>
                                </div>
                                <div class="col-md-9">
                                    <div class="form-group">
                                        <label for="swal-cartype">ประเภทรถ</label>
                                        <input type="text" class="form-control" id="swal-cartype" aria-describedby="swal-cartype" placeholder="ประเภทรถ">
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label for="swal-carbrand">ยี่ห้อรถ</label>
                                        <input type="text" class="form-control" id="swal-carbrand" aria-describedby="swal-carbrand" placeholder="ยี่ห้อรถ">
                                    </div>
                                </div>
                                <div class="col-md-4">
                                    <div class="form-group">
                                        <label for="swal-carfuel">เชื้อเพลง</label>
                                        <input type="text" class="form-control" id="swal-carfuel" aria-describedby="swal-carfuel" placeholder="เชื้อเพลง">
                                    </div>
                                </div>
                                <div class="col-md-2">
                                    <div class="form-group">
                                        <label for="swal-carcolor">สี</label>
                                        <input type="text" class="form-control" id="swal-carcolor" aria-describedby="swal-carcolor" placeholder="สี">
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col">
                                    <div class="form-group">
                                        <label for="swal-carnumber">เลขตัวรถ</label>
                                        <input type="text" class="form-control" id="swal-carnumber" aria-describedby="swal-carnumber" placeholder="เลขตัวรถ">
                                    </div>
                                </div>
                                <div class="col">
                                    <div class="form-group">
                                        <label for="swal-carenginenumber">เลขเครื่องยนต์</label>
                                        <input type="text" class="form-control" id="swal-carenginenumber" aria-describedby="swal-carenginenumber" placeholder="เลขเครื่องยนต์">
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            `,
          width: "80%",
          confirmButtonText: "ยืนยัน",
          focusConfirm: false,
          preConfirm: () => {
            const carcode =
              Swal.getPopup().querySelector("#swal-carcode").value;
            const cartype =
              Swal.getPopup().querySelector("#swal-cartype").value;
            const carbrand =
              Swal.getPopup().querySelector("#swal-carbrand").value;
            const carfuel =
              Swal.getPopup().querySelector("#swal-carfuel").value;
            const carcolor =
              Swal.getPopup().querySelector("#swal-carcolor").value;
            const carnumber =
              Swal.getPopup().querySelector("#swal-carnumber").value;
            const carenginenumber = Swal.getPopup().querySelector(
              "#swal-carenginenumber"
            ).value;
            if (
              !carcode ||
              !cartype ||
              !carbrand ||
              !carfuel ||
              !carcolor ||
              !carnumber ||
              !carenginenumber
            ) {
              Swal.showValidationMessage(`กรุณากรอกข้อมูลให้ครบ`);
            } else {
              Swal.fire({
                title: "เเจ้งเตือน !",
                text: "คุณต้องการเพิ่มรถส่งพัสดุหรือไม่ ?",
                icon: "info",
                showCancelButton: true,
                confirmButtonColor: "#3085d6",
                cancelButtonColor: "#d33",
                confirmButtonText: "ยืนยัน",
                cancelButtonText: "ยกเลิก",
              }).then((result) => {
                if (result.value) {
                  console.log("Add!");
                  Swal.fire("เพิ่มข้อมูลเรียบร้อย !", "", "success");
                } else {
                  Swal.fire("ยกเลิกการเพิ่มข้อมูล", "", "info");
                }
              });
            }
          },
        });
      };
      const deletevehicle = (val) => {
        Swal.fire({
          title: "แจ้งเตือน ?",
          text: "ต้องการลบข้อมูล " + vehicles.row(val).data()[1] + " หรือไม่ !",
          icon: "info",
          showCancelButton: true,
          confirmButtonColor: "#3085d6",
          cancelButtonColor: "#d33",
          confirmButtonText: "ยืนยัน",
          cancelButtonText: "ยกเลิก",
        }).then((result) => {
          if (result.value) {
            console.log("Deleted!");
          } else {
            console.log("not Delete!");
          }
        });
      };
      //setTimeout(()=>{deletevehicle()},100)
    </script>
  </body>
</html>

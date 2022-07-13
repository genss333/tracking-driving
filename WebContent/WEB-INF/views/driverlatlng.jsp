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
						<div class="col">
							<button id = "find-me">Show my location</button><br/>
<p id = "status"></p>
<a id = "map-link" target="_blank"></a>
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
	function geoFindMe() {

		  const status = document.querySelector('#status');
		  const mapLink = document.querySelector('#map-link');

		  mapLink.href = '';
		  mapLink.textContent = '';

		  function success(position) {
		    const latitude  = position.coords.latitude;
		    const longitude = position.coords.longitude;

		    status.textContent = '';
		    mapLink.href = `https://www.openstreetmap.org/#map=18/${latitude}/${longitude}`;
		    mapLink.textContent = `Latitude: ${latitude} °, Longitude: ${longitude} °`;
		  }

		  function error() {
		    status.textContent = 'Unable to retrieve your location';
		  }

		  if(!navigator.geolocation) {
		    status.textContent = 'Geolocation is not supported by your browser';
		  } else {
		    status.textContent = 'Locating…';
		    navigator.geolocation.getCurrentPosition(success, error);
		  }

		}

		document.querySelector('#find-me').addEventListener('click', geoFindMe);
    </script>

</body>

</html>
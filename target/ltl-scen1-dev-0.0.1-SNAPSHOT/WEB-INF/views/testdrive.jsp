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
	let latlng =[
		L.latLng(16.254784656196094,103.23514580726625),
		 L.latLng(16.25471770640082,103.23523163795473),
		 L.latLng(16.254702256444826,103.23536038398744),
		 L.latLng(16.254697106459226,103.2355320453644),
		 L.latLng(16.254702256444826,103.23573589324951),
		 L.latLng(16.254702256444826,103.23594510555267),
		 L.latLng(16.254712556415626,103.236181139946),
		 L.latLng(16.25470740643029,103.23643863201143),
		 L.latLng(16.254712556415626,103.23671221733095),
		 L.latLng(16.25470740643029,103.23697507381439),
		 L.latLng(16.254712556415626,103.23721647262575),
		 L.latLng(16.254645606595787,103.23734521865846),
		 L.latLng(16.254403557056825,103.23735594749452),
		 L.latLng(16.254202707213057,103.23736667633058),
		 L.latLng(16.254007007167836,103.23736667633058),
		 L.latLng(16.25386280700981,103.23736667633058),
		 L.latLng(16.25365165658753,103.23736131191254),
		 L.latLng(16.25347140604754,103.23737740516664),
		 L.latLng(16.253270555251106,103.23736667633058),
		 L.latLng(16.253080004305755,103.23729157447816),
		 L.latLng(16.252853402941078,103.2371896505356),
		 L.latLng(16.25266285159129,103.23710918426515),
		 L.latLng(16.252425949655542,103.23702335357667),
		 L.latLng(16.252250848041268,103.2369375228882),
		 L.latLng(16.25210149654109,103.23698043823244),
		 L.latLng(16.252024245720605,103.23718428611755),
		 L.latLng(16.251931544695953,103.23737740516664),
		 L.latLng(16.251859443868764,103.2375866174698),
		 L.latLng(16.25183884362756,103.23768317699432),
		 L.latLng(16.251746142515454,103.23789775371553),
		 L.latLng(16.251627691030812,103.23815524578096),
		 L.latLng(16.251534989819127,103.2383805513382),
		 L.latLng(16.25142683835023,103.23858439922334),
		 L.latLng(16.25129808652395,103.23878824710847),
		 L.latLng(16.251122983904875,103.23904037475587),
		 L.latLng(16.250989081796792,103.2392978668213),
		 L.latLng(16.250850029511078,103.23952317237855),
		 L.latLng(16.25095818129736,103.23963046073914),
		 L.latLng(16.251122983904875,103.2397484779358),
		 L.latLng(16.25131868682182,103.23986113071443),
		 L.latLng(16.25147318898705,103.23998987674715),
		 L.latLng(16.251643141228513,103.24010789394379),
		 L.latLng(16.251813093323033,103.24024736881258),
		 L.latLng(16.25198819532732,103.24037075042726),
		 L.latLng(16.252132396860773,103.2404673099518),
		 L.latLng(16.252297198483756,103.24059069156648),
		 L.latLng(16.252456849924233,103.24065506458284),
		 L.latLng(16.25262165127515,103.24067652225496),
		 L.latLng(16.252879153109312,103.2406657934189),
		 L.latLng(16.25307485427765,103.2406657934189),
		 L.latLng(16.25303365404788,103.24084281921388)
	]
	const getTime=(sec)=>{
        	var today = new Date();
        		today.setSeconds( today.getSeconds() + sec );
            var date = today.getFullYear()+'-'+(today.getMonth()+1)+'-'+today.getDate()
            var time = today.getHours() + ":" + today.getMinutes() + ":" + today.getSeconds()
            var dateTime = date+' '+time
            return dateTime
        }
		let i =0;
	setInterval(async() => {
		let payload={
			lat:latlng[i].lat,
			lng:latlng[i].lng,
			datetimeRecord:getTime(0),
			orderRouteId:245
		}
		const {data} = await axios.post('${pageContext.request.contextPath}/driver/addLocation',payload)
		i++
	}, 5000);
    </script>

</body>

</html>
var colors = [
"#00FF00", "#0000FF", "#ab47bc", "#7e57c2", "#5c6bc0",
"#42a5f5", "#29b6f6", "#26c6da", "#26a69a", "#66bb6a", "#9ccc65",
"#d4e157", "#ffee58", "#ffca28", "#ffa726", "#ff7043", "#8d6e63",
"#bdbdbd", "#78909c", "#ff5252", "#ff4081", "#e040fb", "#7c4dff",
"#536dfe", "#448aff", "#40c4ff", "#18ffff", "#64ffda", "#69f0ae",
"#b2ff59", "#eeff41", "#ffff00", "#ffd740", "#ffab40", "#ff6e40",
"#d50000", "#c51162", "#aa00ff", "#6200ea", "#304ffe", "#2962ff",
"#0091ea", "#00b8d4", "#00bfa5", "#00c853", "#64dd17", "#aeea00",
"#ffd600", "#ffab00", "#ff6d00", "#dd2c00", "#e53935", "#d81b60",
"#8e24aa", "#5e35b1", "#3949ab", "#1e88e5", "#039be5", "#00acc1",
"#00897b", "#43a047", "#7cb342", "#c0ca33", "#fdd835", "#ffb300",
"#fb8c00", "#f4511e", "#6d4c41", "#757575", "#546e7a", "#c62828",
"#ad1457", "#6a1b9a", "#4527a0", "#283593", "#1565c0", "#0277bd",
"#00838f", "#00695c", "#2e7d32", "#558b2f", "#9e9d24", "#f9a825",
"#ff8f00", "#ef6c00", "#d84315", "#4e342e", "#424242", "#37474f",
"#b71c1c", "#880e4f", "#4a148c", "#311b92", "#1a237e", "#0d47a1",
"#01579b", "#006064", "#004d40", "#1b5e20", "#33691e", "#827717",
"#f57f17", "#ff6f00", "#e65100", "#bf360c", "#3e2723", "#212121",
"#263238", "#ef9a9a", "#f48fb1", "#ce93d8", "#b39ddb", "#9fa8da",
"#90caf9", "#81d4fa", "#80deea", "#80cbc4", "#a5d6a7", "#c5e1a5",
"#e6ee9c", "#fff59d", "#ffe082", "#ffcc80", "#ffab91", "#bcaaa4",
"#eeeeee", "#b0bec5"
]

// init map
var map = L.map('map').setView([16.250149, 103.240739], 16);
L.tileLayer('http://{s}.tile.osm.org/{z}/{x}/{y}.png', {
	attribution: '&copy; <a href="http://osm.org/copyright">OpenStreetMap</a> contributors'
}).addTo(map);
var control;
let timelast = [];
let wpts = [
	L.latLng(16.247131, 103.245395),
	L.latLng(16.249225, 103.247184),
	L.latLng(16.246825, 103.250059),
	L.latLng(16.244034, 103.250617),
	L.latLng(16.246524, 103.253417),
];
let wpts2 = [];
console.log(wpts)
const drawCurrentLine = ()=>{
  for (var i = 0; i < obj.length; i++) {
    wpts =[]
    obj[i].location.map((val,index)=>{timelast.splice(i, 1,val.record_time)}) // collect last time
    for(var j = 1; j < obj[i].location.length; j++){
			wpts.push(L.latLng(parseFloat(obj[i].location[j-1].lat),parseFloat(obj[i].location[j-1].lng)))
			wpts.push(L.latLng(parseFloat(obj[i].location[j].lat),parseFloat(obj[i].location[j].lng)))

    }
		console.log(wpts)
		drawLine(wpts,colors[i])
  }
  drawNextLine()
}

const drawNextLine = () =>{
  setInterval(()=>{
    /*item = obj.map((val,index)=>{
      return val.location.filter(location => location.employee_route_id>13)
    })*/
    for (var i = 0; i < obj.length; i++) {
      console.log("i:"+i)
      wpts =[]
      const location = obj[i].location.filter(location => location.record_time>=timelast[i])// ไม่เอาข้อมูลเดิม
      location.map((val,index)=>{timelast.splice(i, 1,val.record_time)}) // collect last time
      for (var j = 1; j < location.length; j++) {
        wpts.push(L.latLng(parseFloat(location[j-1].lat),parseFloat(location[j-1].lng)))
        wpts.push(L.latLng(parseFloat(location[j].lat),parseFloat(location[j].lng)))
      }
      console.log("Done:"+i)
      drawLine(wpts,colors[i])
    }
  },5000)
}
const drawLine = (waypts,color)=>{
	control = L.Routing.control(L.extend(window.lrmConfig, {
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
	//L.Routing.errorControl(control).addTo(map); // เำื่อโฟกัสที่เส้น
}
setTimeout(function(){drawCurrentLine()}, 5000);
setInterval(function(){},5000)
setInterval(()=>{},5000)

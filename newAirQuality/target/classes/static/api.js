function init( tokenId, inputId, outputId ) {

	token.id = tokenId;

	var input = $(inputId);
	var timer = null;
	var output = $(outputId);

	input.on("keyup",function(){

		/* Debounce */
		if (timer) clearTimeout(timer);
		timer = setTimeout(function(){
			search(input.val(), output);
		},250);

	})

}

function search(keyword, output) {

	get_data_from_all_stations(keyword);

	var info = "";
	output.html("<h2 style='color: white'>Search results "+info+":</h2>")
	output.append($("<div style='color: white'/>").html("Loading..."))
	output.append($("<div style='color: white'/>").addClass("cp-spinner cp-meter"))

	$.getJSON("//api.waqi.info/search/?token="+token()+"&keyword="+keyword,function(result){

		var info = "";
		output.html("<h2  style='color: white'>Search results "+info+":</h2>")
		if (!result || (result.status!="ok")) {
			output.append("Sorry, something wrong happend: ")
			if (result.data) output.append($("<code>").html(result.data))
			return
		}

		if (result.data.length==0) {
			output.append("Sorry, there is no result for your query!")
			return
		}

		var table = $("<table class='table table-dark'/>").addClass("result")
		output.append(table)

		output.append($("<div style='color: white'/>").html("Click on any of the station to see the detailled AQI"))

		var stationInfo = $("<div style='color: white'/>")
		output.append(stationInfo)
		var j = 0;
		result.data.forEach(function(station,i){
			var tr = $("<tr style='color: white'>");
			tr.append($("<td>").html(station.station.name))
			tr.append($("<td>").html(colorize(station.aqi)))
			tr.append($("<td>").html(station.time.stime))
			tr.append($("<td><a href='CityInfo/"+station.uid+"'> "+station.uid+"</a></td> "));

			tr.on("click",function(){
				console.log("stationInfo: "+stationInfo);
				showStation(station,stationInfo)
			})

			/*
			var a = $("<a href='/CityInfo/'>")
			a.append(tr)
			table.append(a)
			*/
			table.append(tr);
			if (i==0)
				console.log("stationInfo: "+stationInfo);
				showStation(station,stationInfo)
		})
		//console.log(scope);

	});
}

function showStation(station, output) {
	//console.log("station: "+station);
	//console.log("output: "+output);
	output.html("<h2>Pollutants & Weather conditions:</h2>")
	output.append($("<div/>").html("Loading..."))
	output.append($("<div/>").addClass("cp-spinner cp-meter"))

	$.getJSON("//api.waqi.info/feed/@"+station.uid+"/?token="+token(),function(result){
		console.log(result);
		console.log(station.uid);
		output.html("<h2>Pollutants & Weather conditions:</h2>")
		if (!result || (result.status!="ok")) {
			output.append("Sorry, something wrong happend: ")
			if (result.data) output.append($("<code>").html(result.data))
			return
		}

		var names = {
			pm25: "PM<sub>2.5</sub>",
			pm10: "PM<sub>10</sub>",
			o3: "Ozone",
			no2: "Nitrogen Dioxide",
			so2: "Sulphur Dioxide",
			co: "Carbon Monoxyde",
			t: "Temperature",
			w: "Wind",
			r: "Rain (precipitation)",
			h: "Relative Humidity",
			d: "Dew",
			p: "Atmostpheric Pressure"
		}

		output.append($("<div>").html("Station: "+result.data.city.name+" on "+result.data.time.s))

		var table = $("<table class='table table-dark'/>").addClass("result")
		output.append(table)

		for (var specie in result.data.iaqi) {
			var aqi = result.data.iaqi[specie].v
			var tr = $("<tr>");
			tr.append($("<td>").html(names[specie]||specie))
			tr.append($("<td>").html(colorize(aqi,specie)))
			table.append(tr)
		}
	})
}

function token() {
	return "72efba96064c6901c4cb6d7330ec55f94b553947";
}

function colorize(aqi, specie ) {
	specie = specie||"aqi"
	if (["pm25","pm10","no2","so2","co","o3","aqi"].indexOf(specie)<0) return aqi;

	var spectrum = [
		{a:0,  b:"#cccccc",f:"#ffffff"},
		{a:50, b:"#009966",f:"#ffffff"},
		{a:100,b:"#ffde33",f:"#000000"},
		{a:150,b:"#ff9933",f:"#000000"},
		{a:200,b:"#cc0033",f:"#ffffff"},
		{a:300,b:"#660099",f:"#ffffff"},
		{a:500,b:"#7e0023",f:"#ffffff"}
		];


	var i = 0;
	for (i=0;i<spectrum.length-2;i++) {
		if (aqi=="-"||aqi<=spectrum[i].a) break;
	};	
	return $("<div/>")
		.html(aqi)
		.css("font-size","120%")
		.css("min-width","30px")
		.css("text-align","center")
		.css("background-color",spectrum[i].b)
		.css("color",spectrum[i].f)

}
function postData(id, name, co, h, dew, no2, o3, p, pm10, pm25, so2, t, w, wg, r){
	fetch('http://localhost:8084/CityInfo', {
		method: 'POST',
		body:JSON.stringify({"id":id,
			"name":name,
			"co":co,
			"h":h,
			"d":dew,
			"no2":no2,
			"o3":o3,
			"p":p,
			"pm10":pm10,
			"pm25":pm25,
			"so2":so2,
			"t":t,
			"w":w,
			"wg":wg,
			"r":r,
		}),
		headers: {
			'Content-Type': 'application/json'
		}
	}).then((res) => res.text())
		.then((text)=>console.log("text:"+ text))
		.catch((err)=>console.log("err:" + err))
}
function get_specific_data_from_station(station_name, uid){
	let string = 'https://api.waqi.info/feed/@'+uid+'/?token='+token()
	let request_specific = new XMLHttpRequest()
	request_specific.open('GET', string, true)
	request_specific.onload = function() {
		let dataa_specific = JSON.parse(this.response)
		//console.log(dataa_specific);
		if (request_specific.status == 200 ) {
			let co_value;
			let dew_value;
			let h_value;
			let no2_value;
			let o3_value;
			let p_value;
			let p10_value;
			let p25_value;
			let so2_value;
			let t_value;
			let w_value;
			let wg_value;
			let r_value;



			if (dataa_specific.data.iaqi) {

				if (dataa_specific.data.iaqi.co && dataa_specific.data.iaqi.co['v']) {
					co_value=dataa_specific.data.iaqi.co["v"];
				}
				if (dataa_specific.data.iaqi.r && dataa_specific.data.iaqi.r['v']) {
					r_value=dataa_specific.data.iaqi.r["v"];
				}
				if (dataa_specific.data.iaqi.d && dataa_specific.data.iaqi.d['v']) {
					dew_value=dataa_specific.data.iaqi.d["v"];
				}
				if (dataa_specific.data.iaqi.h && dataa_specific.data.iaqi.h['v']) {
					h_value=dataa_specific.data.iaqi.h["v"];
				}
				if (dataa_specific.data.iaqi.no2 && dataa_specific.data.iaqi.no2['v']) {
					no2_value=dataa_specific.data.iaqi.no2["v"];
				}
				if (dataa_specific.data.iaqi.o3 && dataa_specific.data.iaqi.o3['v']) {
					o3_value=dataa_specific.data.iaqi.o3["v"];
				}
				if (dataa_specific.data.iaqi.p && dataa_specific.data.iaqi.p['v']) {
					p_value=dataa_specific.data.iaqi.p["v"];
				}
				if (dataa_specific.data.iaqi.pm10 && dataa_specific.data.iaqi.pm10['v']) {
					p10_value=dataa_specific.data.iaqi.pm10["v"];
				}
				if (dataa_specific.data.iaqi.pm25 && dataa_specific.data.iaqi.pm25['v']) {
					p25_value=dataa_specific.data.iaqi.pm25["v"];
				}
				if (dataa_specific.data.iaqi.so2 && dataa_specific.data.iaqi.so2['v']) {
					so2_value=dataa_specific.data.iaqi.so2["v"];
				}
				if (dataa_specific.data.iaqi.t && dataa_specific.data.iaqi.t['v']) {
					t_value=dataa_specific.data.iaqi.t["v"];
				}
				if (dataa_specific.data.iaqi.w && dataa_specific.data.iaqi.w['v']) {
					w_value=dataa_specific.data.iaqi.w["v"];
				}
				if (dataa_specific.data.iaqi.wg && dataa_specific.data.iaqi.wg['v']) {
					wg_value=dataa_specific.data.iaqi.wg["v"];
				}
				postData(uid, dataa_specific.data.city.name, co_value, h_value, dew_value, no2_value, o3_value, p_value, p10_value, p25_value, so2_value, t_value,  w_value, wg_value, r_value)
			}

		}else {
			console.log(`It's not working! -_-`);


		}
	}
	request_specific.send()

}
function get_data_from_all_stations(keyword) {
	let string = 'https://api.waqi.info/search/?token='+token()+'&keyword='+keyword
	let request = new XMLHttpRequest()
	request.open('GET', string, true)
	request.onload = function() {
		// Begin accessing JSON data here
		let dataa = JSON.parse(this.response)
		console.log(dataa);

		if (request.status == 200 ) {
			//console.log(request.responseText);
			dataa.data.forEach(ci => {
				//console.log(ci.station.name)
				//console.log(ci.uid)
				get_specific_data_from_station(ci.station.name, ci.uid);
			})
		} else {
			console.log(`It's not working! -_-`);

		}
	}
	request.send()
}
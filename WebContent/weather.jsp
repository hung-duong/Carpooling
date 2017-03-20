<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<title>Insert title here</title>

<link
	href="${pageContext.request.servletContext.contextPath}/resources/css/weather.css"
	type="text/css" rel="stylesheet" />
<script type="text/javascript"
	src="${pageContext.request.servletContext.contextPath}/resources/js/weather.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"
	type="text/javascript"></script>

<!-- <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCRPzdQe_b24l8lC0mYu1Hb6_WcpPWmfUo" defer="defer" type="text/javascript"></script> -->
<script type="text/javascript"
	src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCRPzdQe_b24l8lC0mYu1Hb6_WcpPWmfUo"></script>
<style>
html, body {
	width: 100%;
	height: 100%;
	margin: 0;
	padding: 0;
}

#map-canvas {
	width: 100%;
	height: 100%;
}

.gm-style-iw {
	text-align: center;
}
</style>
 <style type="text/css">

fieldset.radiogroup  { 
  margin: 0; 
  padding: 0; 
  margin-bottom: 1.25em; 
  padding: .125em; 
} 

fieldset.radiogroup legend { 
  margin: 0; 
  padding: 0; 
  font-weight: bold; 
  margin-left: 20px; 
  font-size: 100%; 
  color: black; 
} 


ul.radio  { 
  margin: 0; 
  padding: 0; 
  margin-left: 20px; 
  list-style: none; 
} 

ul.radio li { 
  border: 1px transparent solid; 
} 

ul.radio li:hover, 
ul.radio li.focus  { 
  background-color: lightyellow; 
  border: 1px gray solid; 
  width: 10em; 
} 

  </style>
<script>
	let URL = "http://api.openweathermap.org/data/2.5/";
	let CITY_FROM = "forecast?q="+ "${user.city}" + "," + "${user.state}" + ",us";
	let ZIP_CODE_FROM = "forecast?zip=" + ${user.zipCode}
	let APPID = "&APPID=";
	let OPEN_WEATHER_MAP_KEY = "a30be688bf0b959ec440996cd755e890";

	var map;
	var geoJSON = {
		type : "FeatureCollection",
		features : []
	};
	var request;
	var gettingData = false;
	var openWeatherMapKey = "a30be688bf0b959ec440996cd755e890"
	function initialize() {
		var mapOptions = {
			zoom : 4,
			center : new google.maps.LatLng(50, -50)
		};
		map = new google.maps.Map(document.getElementById('map-canvas'),
				mapOptions);
		// Add interaction listeners to make weather requests
		google.maps.event.addListener(map, 'idle', checkIfDataRequested);
		// Sets up and populates the info window with details
		map.data.addListener('click', function(event) {
			infowindow.setContent("<img src="
					+ event.feature.getProperty("icon") + ">"
					+ "<br /><strong>" + event.feature.getProperty("city")
					+ "</strong>" + "<br />"
					+ event.feature.getProperty("main") + "<br />"
					+ event.feature.getProperty("description"));
			infowindow.setOptions({
				position : {
					lat : event.latLng.lat(),
					lng : event.latLng.lng()
				},
				pixelOffset : {
					width : 0,
					height : -15
				}
			});
			infowindow.open(map);
		});
	}
	var checkIfDataRequested = function() {
		// Stop extra requests being sent
		while (gettingData === true) {
			request.abort();
			gettingData = false;
		}
		GoSearchDesCityFunc();
	};

	//$("#btnSearchDesCity").click(GoSearchDesCityFunc);

	function GoSearchDesCityFunc() {
		//example url: http://api.openweathermap.org/data/2.5/forecast?q=fairfield,us&appid=a30be688bf0b959ec440996cd755e890
		/* var requestStringFromCity = URL + CITY_NAME + $("#desCity").val()
				+ COUNTRY_CODE + APPID + OPEN_WEATHER_MAP_KEY;  */

		//for city		
		//var requestStringFromCity = "http://api.openweathermap.org/data/2.5/forecast?q=fairfield,ia,us&appid=a30be688bf0b959ec440996cd755e890"
		//for Zip code
		//var requestStringFromCity = "http://api.openweathermap.org/data/2.5/forecast?zip=52556&appid=a30be688bf0b959ec440996cd755e890";

		//get By location
		//lat=41.0086lon=-91.9627
		//chinh format
		//var requestStringFromCity = "http://api.openweathermap.org/data/2.5/weather?lat=41.0086&lon=139&appid=a30be688bf0b959ec440996cd755e890"
		var requestString= "";
		if(${ empty user.city}){
			//_CityFrom 
			requestString = URL + CITY_FROM + APPID + OPEN_WEATHER_MAP_KEY; 
		} else if(${not empty user.zipCode}){
			//_ZipcodeFrom
			requestString = URL + ZIP_CODE_FROM + APPID + OPEN_WEATHER_MAP_KEY; 
		}
		console.log(requestString);
		$.get(requestString).done(function(results) {
			ForecastFromCitySuccess(results);
		}).fail(ajaxError);
	}

	function ForecastFromCitySuccess(results) {

		/* for (var i = 0; i < results.list.length; i++) {
		    console.log(results.list[i].weather[0].main);
		    console.log(results.list[i].weather[0].description);
		    console.log(results.list[i].weather[0].icon);
		}	 */
		console.log("lat=" + results.city.coord.lat + "lon="
				+ results.city.coord.lon);
		var json = jsonToGeoJson(results);
		geoJSON.features.push(json);
		drawIcons(geoJSON);
	}

	function ajaxError(xhr, status, exception) {
		console.log(xhr, status, exception);
	}

	var infowindow = new google.maps.InfoWindow();
	// For each result that comes back, convert the data to geoJSON
	var jsonToGeoJson = function(results) {
		var feature = {
			type : "Feature",
			properties : {
				city : results.city.name,
				main : results.list[1].weather[0].main,
				description : results.list[1].weather[0].description,
				icon : "http://openweathermap.org/img/w/"
						+ results.list[1].weather[0].icon + ".png",
				coordinates : [ results.city.coord.lon, results.city.coord.lat ]
			},
			geometry : {
				type : "Point",
				coordinates : [ results.city.coord.lon, results.city.coord.lat ]
			}
		};
		// Set the custom marker icon
		map.data.setStyle(function(feature) {
			return {
				icon : {
					url : feature.getProperty('icon'),
					anchor : new google.maps.Point(25, 25)
				}
			};
		});
		// returns object
		return feature;
	};
	// Add the markers to the map
	var drawIcons = function(weather) {
		map.data.addGeoJson(geoJSON);
		// Set the flag to finished
		gettingData = false;
	};
	// Clear data layer and geoJSON
	var resetData = function() {
		geoJSON = {
			type : "FeatureCollection",
			features : []
		};
		map.data.forEach(function(feature) {
			map.data.remove(feature);
		});
	};
	google.maps.event.addDomListener(window, 'load', initialize);
</script>
</head>
<body>
	<form>
		<fieldset class="radiogroup"> 
			<legend>Search Weather by</legend>
			  <div>
			   <input type="text" name="txtSearch" id="txtSearch" /> 
				<input type="submit" name="btnSearchDes" value="Search" />
			</div>
			  <ul class="radio"> 
			    <li><input type="radio" name="crust" id="cityDes" value="cityDes" checked/><label for="cityDes">City Destination</label></li> 
			    <li><input type="radio" name="crust" id="zipCodeDes" value="zipCodeDes" /><label for="zipCodeDes">Zip Code Destination</label></li> 
			    <li><input type="radio" name="crust" id="currentLocation" value="currentLocation" /><label for="currentLocation">Current Location</label></li> 
			  </ul> 
		</fieldset>		
	</form>
	<div id="map-canvas"></div>
</body>
</html>
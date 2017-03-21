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
<%-- <script type="text/javascript"
	src="${pageContext.request.servletContext.contextPath}/resources/js/weather.js"></script> --%>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"
	type="text/javascript"></script>

<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>

<script type="text/javascript"
	src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCRPzdQe_b24l8lC0mYu1Hb6_WcpPWmfUo"></script>

<script>
	let URL = "http://api.openweathermap.org/data/2.5/";
	let CITY_FROM = "forecast?q="+ "${user.city}" + "," + "${user.state}" + ",us";
	let ZIP_CODE_FROM = "forecast?zip=" + ${user.zipCode} + "";
	let APPID = "&APPID=";
	let OPEN_WEATHER_MAP_KEY = "a30be688bf0b959ec440996cd755e890";
	var firstLoad = true;
	console.log("Set first load");
	var URL_ICON = "http://openweathermap.org/img/w/";
	var map;
	var geoJSON = {
		type : "FeatureCollection",
		features : []
	};
	var request;
	var gettingData = false;
	var openWeatherMapKey = "a30be688bf0b959ec440996cd755e890"
	
	var currentLocation = {
		lat: 0,
		lon: 0
	};
	
	
	getLocation();
	
	function getLocation() {
	     if (navigator.geolocation) {
	         navigator.geolocation.getCurrentPosition(showPosition);
	     }
	 }

	 function showPosition(position) {
		 currentLocation.lat = position.coords.latitude;
		 currentLocation.lon = position.coords.longitude;
		 console.log("Current location: ", currentLocation);
		 map.setCenter(new google.maps.LatLng(currentLocation.lat, currentLocation.lon));
	 } 
	
	
	function initialize() {
		var mapOptions = {
			zoom : 8,
			center : new google.maps.LatLng(currentLocation.lat, currentLocation.lon)
		};
		map = new google.maps.Map(document.getElementById('map-canvas'),
				mapOptions);
		// Add interaction listeners to make weather requests
		google.maps.event.addListener(map, 'idle', checkIfDataRequested);
		// Sets up and populates the info window with details
		
		map.data.addListener('click', function(event) {
			/* /* main : results.list[1].weather[0].main,
			description : results.list[1].weather[0].description, 
			icon : "http://openweathermap.org/img/w/"
					+ results.list[1].weather[0].icon + ".png", */
			var contentHTML = "<table>";
            for(var i = 0; i <event.feature.getProperty("list").length; i++) {
          
                contentHTML += "<td>";
                contentHTML += "<img src=" + URL_ICON + event.feature.getProperty("list")[i].weather[0].icon + ".png"  + ">"
                contentHTML += "<br /><strong>" + event.feature.getProperty("list")[i].dt_txt + "</strong>";
                contentHTML += "<br />" + event.feature.getProperty("list")[i].weather[0].main;
                contentHTML += "<br />" + event.feature.getProperty("list")[i].main.temp;
                /* contentHTML += "<br />" + event.feature.getProperty("list")[i].weather[0].description;  */
        
                contentHTML += "</td>";
            }
            contentHTML += "</table>";
			
			infowindow.setContent("<img src="
					+ event.feature.getProperty("icon") + ">"
					+ "<br /><strong>" + event.feature.getProperty("city")
					+ "</strong>" + "<br />"
					+ "<br /><strong>" + event.feature.getProperty("list")[0].dt_txt
					+ "</strong>" + "<br />"
				    + event.feature.getProperty("list")[0].weather[0].main + "<br />"
					+ event.feature.getProperty("list")[0].weather[0].description + "<br />"
					+ contentHTML
					+ "<a href='javascript:doSomething();'>click for detail</a>"
					);
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
		//GoSearchDesCityFunc();
		if(firstLoad == true){
			initialWeatherMap();
			firstLoad = false;
			console.log("Inner firstLoad = "+firstLoad);
		}		
		console.log("firstLoad = "+firstLoad);
	};
	
	function initialWeatherMap() {
		var requestString= "";
		if(${empty user.city}){
			//_CityFrom 
			requestString = URL + CITY_FROM + APPID + OPEN_WEATHER_MAP_KEY; 
		} else if(${not empty user.zipCode}){
			//_ZipcodeFrom
			requestString = URL + ZIP_CODE_FROM + APPID + OPEN_WEATHER_MAP_KEY; 
		}
		//console.log(requestString);
		$.get(requestString).done(function(results) {
			ForecastFromCitySuccess(results);
		}).fail(ajaxError);
	}
	function ForecastFromCitySuccess(results) {
		resetData();
		var json = jsonToGeoJson(results);
		console.log(json.properties.list);
		geoJSON.features.push(json);
		drawIcons(geoJSON);
		map.setCenter(new google.maps.LatLng(results.city.coord.lat, results.city.coord.lon));
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
				list: results.list,
				/* main : results.list[1].weather[0].main,
				description : results.list[1].weather[0].description, */
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
	
	 $(function() {
		 $("#btnSearchDes").click(GoSearchDesFunc);
		 
		 function GoSearchDesFunc() {
				var requestString= "";
				if($("#txtSearch").val().trim() != "" && $('input:radio[name=des]:checked').val() == "cityDes"){
					//_CityFrom 
					requestString = URL + "forecast?q="+ $("#txtSearch").val()  + "," + $("#txtState").val() + ",us" + APPID + OPEN_WEATHER_MAP_KEY; 
				} else {
					//_ZipcodeFrom
					requestString = URL + "forecast?zip=" + $("#txtSearch").val() + APPID + OPEN_WEATHER_MAP_KEY; 
				}
				//console.log(requestString);
				$.get(requestString).done(function(results) {
					ForecastFromCitySuccess(results);
				}).fail(ajaxError);
		}
	 });
	//$("#btnSearchDesCity").click(GoSearchDesCityFunc);
</script>

</head>
<body>
	<form method='post' action='WeatherController'>
		<fieldset class="radiogroup">
			<legend>Search Weather by</legend>
			<div>
				Search: <input type="text" name="txtSearch" id="txtSearch" />
				State: <input class="form-control"
					placeholder="Eg.IA (Must be 2 characters)" name="state" type="text"
					value="${user.getState()}" id="txtState" />

				<input type="submit" name="btnSearchDes" id="btnSearchDes"
					value="Search" />
			</div>
			<ul class="radio">
				<li><input type="radio" name="des" value="cityDes"
					${cityDesChecked} checked /><label for="cityDes">City
						Destination</label></li>
				<li><input type="radio" name="des" value="zipCodeDes"
					${zipCodeDesChecked} /><label for="zipCodeDes">Zip Code
						Destination</label></li>
				<li><input type="radio" name="des" value="currentLocation"
					${currentLocationChecked} /><label for="currentLocation">Current
						Location</label></li>
			</ul>
		</fieldset>
	</form>
	<div id="map-canvas"></div>
	<p id="demo"></p>
</body>
</html>
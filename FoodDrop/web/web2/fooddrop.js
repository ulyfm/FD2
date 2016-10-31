var map;
var pos = {lat: 0, lng: 0};

if(navigator.geolocation){
	navigator.geolocation.getCurrentPosition(setLocation, locError);
}else{
	
}

function initMap() {
  map = new google.maps.Map(document.getElementById('map'), {
    zoom: 12,
    center: {lat: 0, lng: 0},
    mapTypeControlOptions: {
              style: google.maps.MapTypeControlStyle.HORIZONTAL_BAR,
              position: google.maps.ControlPosition.TOP_RIGHT
          }

  });
  geocoder = new google.maps.Geocoder();
}

function setLocation(position){
	loc = {lat: position.coords.latitude, lng: position.coords.longitude};
	sl();
}

var lastwindow;
var personalloc = null;

function sl(){
  if(personalloc != null){
    personalloc.setMap(null);
  }
  map.setCenter(loc);
  personalloc = new google.maps.Marker({
    position: loc,
    map: map,
    title: "Your Location"
  });
  personalloc.addListener('click', function(){
    if(lastwindow != null)
      lastwindow.close();
    lastwindow = new google.maps.InfoWindow({
      content: "<h3>Your Location</h3>"
    });
    lastwindow.open(map, personalloc);
  });
  getJSON("/getposition?1&" + getType() + "&" + loc.lat + "&" + loc.lng, updateMarkers);
}

function locError(){
	loc = {lat: 0, lng: 0}
}

function getJSON(url, callback) {
	var xhr = new XMLHttpRequest();
	xhr.timeout = 5000;
	xhr.ontimeout = function(){
		alert("Search timed out");
    }
    xhr.onload = function() {
      var status = xhr.status;
      if (status == 200) {
        callback(xhr.responseText);
      } else {
        callback("zero results");
      }
    };
    xhr.open("GET", url, true);
    xhr.send();
};

var res;

function submitAddress(){
	document.getElementById('resultsdisp').innerHTML = '';
	document.getElementById('address').blur();
	geocoder.geocode({'address': document.getElementById('address').value}, function(results, status) {
		if (status === 'OK') {
			if(results.length == 0){
				loc = {lat: results[0].geometry.location.lat(), lng: results[0].geometry.location.lng()};
				sl();
			}else{
				/* TODO display all results */
				var box = document.getElementById('resultsdisp');
				var inht = "";
				for(var i = 0; i < results.length; ++i){
					inht += "<a href='javascript: selectAddr(" + results[i].geometry.location.lat() + ", " + results[i].geometry.location.lng() + ", " + i + ");'>" + results[i].formatted_address + "</a><br>"
				}
				box.innerHTML = inht;
				res = results;
			}
		} else {
			var box = document.getElementById('resultsdisp');
			box.innerHTML = "no results";
		}
	});
}

function selectAddr(lat, lng, addr){
	loc = {lat: lat, lng: lng};
	document.getElementById('search').style.display = 'none';
	var a = document.getElementById('currentlocation');
	a.innerHTML = "Current Location: <br>" + res[addr].formatted_address;
	a.style.display = 'block';
	document.getElementById('cloc').style.display = 'block';
	sl();
}
function enableSearch(){
	document.getElementById('resultsdisp').innerHTML = '';
	document.getElementById('address').value = '';
	document.getElementById('search').style.display = 'block';
	var a = document.getElementById('currentlocation');
	a.innerHTML = '';
	a.style.display = 'none';
	document.getElementById('cloc').style.display = 'none';
	document.getElementById('address').focus();
}
var map;
var loc = {lat: 0, lng: 0};
var lkey = document.cookie;
var hkey = document.cookie.split(";")[0].substring(2); /* Cookie: u=login key;expires=... */

if(navigator.geolocation){
	navigator.geolocation.getCurrentPosition(setLocation, locError);
}else{
	
}

function updateCookie(fullstring){
	document.cookie = fullstring;
	lkey = fullstring;
	hkey = fullstring.split(";")[0].substring(2);
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
	geocoder.geocode({'location': loc}, function(results, status) {
          if (status === 'OK') {
            
			res = results;
            selectAddr(loc.lat, loc.lng, 0);
            
          } else {
            
          }
        });
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
  getJSON("/getposition?1&" + hkey + "&" + loc.lat + "&" + loc.lng, updateMarkers);
}

function locError(){
	loc = {lat: 0, lng: 0}
}

function getJSON(url, callback) {
	var xhr = new XMLHttpRequest();
	xhr.timeout = 5000;
	xhr.ontimeout = function(){
		console.log("Search timed out");
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
			if(results.length == 1){
				res = results;
				selectAddr(results[0].geometry.location.lat(), results[0].geometry.location.lng(), 0);
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

function updateMarkers(data){
  var list = JSON.parse(data).locations;
  for(var i = 0; i < list.length; ++i){
    addMarker({lat: list[i].lat, lng: list[i].lng}, map, list[i].desc);
  }
}

function toggleLogin(){
	if(document.getElementById('foverlay').style.display == "none" || document.getElementById('foverlay').style.display == ""){
		document.getElementById('foverlay').style.display = 'block';
	}else{
		document.getElementById('foverlay').style.display = 'none';
	}
}
function elogin(event){
	if(event.target === document.getElementById('foverlay')){
		toggleLogin();
	}
}
function toggleDoverlay(){
	if(document.getElementById('doverlay').style.display == "none" || document.getElementById('doverlay').style.display == ""){
		document.getElementById('doverlay').style.display = 'block';
	}else{
		document.getElementById('doverlay').style.display = 'none';
	}
}
function edoverlay(event){
	if(event.target === document.getElementById('doverlay')){
		toggleDoverlay();
	}
}

function addMarker(position, map, desc){
  var marker = new google.maps.Marker({
      position: position,
      map: map,
      title: desc
    });
    google.maps.event.addListener(marker, 'click', function(event){
      if(lastwindow != null)
        lastwindow.close();
      lastwindow = new google.maps.InfoWindow({
        content: desc
      });
      lastwindow.open(map, marker);
    });
}

function updateDisp(){
	
}
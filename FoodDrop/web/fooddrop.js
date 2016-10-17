var loc;
var map;
if(navigator.geolocation){

}else{
  document.getElementById('autoloc').style.display = none;
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
function getLocation(){
  navigator.geolocation.getCurrentPosition(setLocation, locError);
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
  toggleOverlay();
  getJSON("/getposition?1&" + getType() + "&" + loc.lat + "&" + loc.lng, updateMarkers);
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
        alert("error getting data: " + status);
      }
    };
    xhr.open("GET", url, true);
    xhr.send();
};
function reqListener () {
  console.log(this.responseText);
}
function updateMarkers(data){
  var list = JSON.parse(data).locations;
  for(var i = 0; i < list.length; ++i){
    addMarker({lat: list[i].lat, lng: list[i].lng}, map, list[i].desc);
  }
}
function setLocation(position){
  loc = {lat: position.coords.latitude, lng: position.coords.longitude};
  sl();
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
function locError(){
  alert("Error getting location");
}
function toggleOverlay(){
  if(document.getElementById('hiddencontent').style.display === "none"){
    document.getElementById('hiddencontent').style.display = "block";
    document.getElementById('toggle').innerHTML = "close";
    document.getElementById('toggle').style.backgroundColor = "#e74c3c";
    document.getElementById('toggle').style.border = "2px solid #c0392b";
  }else{
    document.getElementById('hiddencontent').style.display = "none";
    document.getElementById('toggle').innerHTML = "open";
    document.getElementById('toggle').style.backgroundColor = "#2ecc71";
    document.getElementById('toggle').style.border = "2px solid #27ae60";
  }
}
function enterLocation(){
  document.getElementById('hiddenfields').style.display = "block";
}
var geocoder;
function geoloc(addr){
  geocoder.geocode({'address': document.getElementById('address').value}, function(results, status) {
          if (status === 'OK') {
            return {lat: results[0].geometry.location.lat(), lng: results[0].geometry.location.lng()};
          } else {
            //HELP
          }
        });
}
function submitAddress(){
  geocoder.geocode({'address': document.getElementById('address').value}, function(results, status) {
          if (status === 'OK') {
            loc = {lat: results[0].geometry.location.lat(), lng: results[0].geometry.location.lng()};
            sl();
          } else {
            alert('Invalid address request: ' + status);
          }
        });

}
function getType(){
  if(document.getElementById('check').checked){
    return 2;
  }else{
    return 1;
  }
}
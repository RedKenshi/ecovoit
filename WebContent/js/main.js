var key = "AIzaSyDZe0varc34YAvx57ffHuWKq0V1EParir0";
var map;

function initMap(){
  var mapOptions = {
    center: {lat: 43.59, lng: 1.455},
    zoom: 12,
    scrollwheel: true
  };
  directionsDisplay = new google.maps.DirectionsRenderer();
  map = new google.maps.Map(document.getElementById('map'),mapOptions);
  var start = /** @type {HTMLInputElement} */(document.getElementById('searchOnMap'));
  var autocomplete_search = new google.maps.places.Autocomplete(start);
  autocomplete_search.bindTo('bounds', map);
  var infowindow = new google.maps.InfoWindow();
  var marker = new google.maps.Marker({map: map});
  google.maps.event.addListener(marker, 'click', function() {infowindow.open(map, marker);});
  google.maps.event.addListener(autocomplete_search, 'place_changed', function() {
    infowindow.close();
    var place = autocomplete_search.getPlace();
    if (!place.geometry) {
      return;
    }
    if (place.geometry.viewport) {
      map.fitBounds(place.geometry.viewport);
    } else {
      map.setCenter(place.geometry.location);
      map.setZoom(17);
    }
    marker.setPlace(/** @type {!google.maps.Place} */ ({placeId: place.place_id,location: place.geometry.location}));
    marker.setVisible(true);
    infowindow.setContent('<div><strong>'+place.name +'</strong><br>'+'Place ID: '+place.place_id+'<br>'+place.formatted_address+'</div>');
    infowindow.open(map, marker);
  });
  google.maps.event.addListener(map, "rightclick",function(event){
	  setMenuXY(event.latLng);
  });
  google.maps.event.addListener(map, "click",function(event){
	$('#contextWrapper').removeClass("contextWrapperToggled");
	$('#stepCreationModal').removeClass("stepCreationModalToggled");
  });
}

function init_index(){
	$('#btn-log-wrapper').on('click',function(){
        $('#log-wrapper').toggleClass('hidden');
    });
	$('#btn-createtravel').on('click',function(){
        $('#createtravel-wrapper').toggleClass('hidden');
        return false;
    });
	$('#btn_login').on('click',function(){
		console.log($('#login_wrapper').css('display'));
		if($('#login_wrapper').css('display') == 'none'){
			$('#login_wrapper').fadeIn().css('display', 'inline-block');
			$('#register_wrapper').fadeIn().css('display', 'none');
		}else{
			$('#login_wrapper').fadeIn().css('display', 'none');
		}
	});
	$('#btn_register').on('click',function(){
		if($('#register_wrapper').css('display') == 'none'){
			$('#register_wrapper').fadeIn().css('display', 'inline-block');
			$('#login_wrapper').fadeIn().css('display', 'none');
		}else{
			$('#register_wrapper').fadeIn().css('display', 'none');
		}
	});
	$('#btn-travel-launch').on('click',function(){
		var depart = document.getElementById('searchOnMapDepart').value;
		var arrivee = document.getElementById('searchOnMapArrive').value;
		var directionsService = new google.maps.DirectionsService();
	    var choixMode =  "DRIVING";  //document.getElementById("mode").value;
	    directionsDisplay.setMap(map);
		if (depart && arrivee){
		    var request = {
		        origin:depart, 
		        destination:arrivee,
		        travelMode: google.maps.DirectionsTravelMode[choixMode]
		    };
		    directionsService.route(request, function(response, status) {
		      if (status == google.maps.DirectionsStatus.OK) {
		        directionsDisplay.setDirections(response);
		        alert("trouvé ");	
		      }
		    });
		}
	});

}

function getCanvasXY(caurrentLatLng){
      var scale = Math.pow(2, map.getZoom());
     var nw = new google.maps.LatLng(
         map.getBounds().getNorthEast().lat(),
         map.getBounds().getSouthWest().lng()
     );
     var worldCoordinateNW = map.getProjection().fromLatLngToPoint(nw);
     var worldCoordinate = map.getProjection().fromLatLngToPoint(caurrentLatLng);
     var currentLatLngOffset = new google.maps.Point(
         Math.floor((worldCoordinate.x - worldCoordinateNW.x) * scale),
         Math.floor((worldCoordinate.y - worldCoordinateNW.y) * scale)
     );
     return currentLatLngOffset;
  }

function setMenuXY(currentLatLng){
    var mapWidth = $('#map_canvas').width();
    var mapHeight = $('#map_canvas').height();
    var menuWidth = $('.contextmenu').width();
    var menuHeight = $('.contextmenu').height();
		$('#clickprop').attr("lat",currentLatLng.lat());
		$('#clickprop').attr("lng",currentLatLng.lng());
    var clickedPosition = getCanvasXY(currentLatLng);
    var x = clickedPosition.x;
    var y = clickedPosition.y;
     if((mapWidth - x ) < menuWidth)
         x = x - menuWidth;
    if((mapHeight - y ) < menuHeight)
        y = y - menuHeight;
		$('#contextWrapper').addClass("contextWrapperToggled");
    $('#contextWrapper').css('left',x+250);
    $('#contextWrapper').css('top',y);
};

function Travel (id, map, colorMarket){
  	var pinImage = new google.maps.MarkerImage("http://maps.google.com/mapfiles/ms/icons/"+colorMarket+"-dot.png");
	  var param = id;


		  var mapOptions = {
		    center: {lat: 43.59, lng: 1.455},
		    zoom: 12,
		    scrollwheel: true
		  };
	 // map = new google.maps.Map(document.getElementById('map'),mapOptions);
	  var start = /** @type {HTMLInputElement} */(document.getElementById(id.toString()));
	  var autocomplete_search = new google.maps.places.Autocomplete(start);
	  autocomplete_search.bindTo('bounds', map);
	  var infowindow = new google.maps.InfoWindow();
	  var marker = new google.maps.Marker({map: map, icon: pinImage});
	  google.maps.event.addListener(marker, 'click', function() {infowindow.open(map, marker);});
	  google.maps.event.addListener(autocomplete_search, 'place_changed', function() {
	    infowindow.close();
	    var place = autocomplete_search.getPlace();
	    if (!place.geometry) {
	      return;
	    }
	    if (place.geometry.viewport) {
	      map.fitBounds(place.geometry.viewport);
	    } else {
	      map.setCenter(place.geometry.location);
	      map.setZoom(17);
	    }
	    marker.setPlace(/** @type {!google.maps.Place} */ ({placeId: place.place_id,location: place.geometry.location}));
	    marker.setVisible(true);
	    infowindow.setContent('<div><strong>'+place.name +'</strong><br>'+'Place ID: '+place.place_id+'<br>'+place.formatted_address+'</div>');
	    infowindow.open(map, marker);
	  });
	  google.maps.event.addListener(map, "rightclick",function(event){
	    setMenuXY(event.latLng);
	  });
	  google.maps.event.addListener(map, "click",function(event){
		$('#contextWrapper').removeClass("contextWrapperToggled");
		$('#stepCreationModal').removeClass("stepCreationModalToggled");
	  });
}

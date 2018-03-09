var key = "AIzaSyDZe0varc34YAvx57ffHuWKq0V1EParir0";

function initMap(){
  var mapOptions = {
    center: {lat: 43.59, lng: 1.455},
    zoom: 12,
    scrollwheel: true
  };
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
}
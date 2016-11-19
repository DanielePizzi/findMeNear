angular.module('findMeNearApp.Home')
.controller('HomeController', ['$scope', '$location', function ($scope, $location) {
	   $scope.currentUser = null;
	   
	// Posizione corrente al momento dell'apertura della macchina
	   $scope.loc = { lat: 41.9, lon: 12.416667 };
	   $scope.gotoCurrentLocation = function () {
	       if ("geolocation" in navigator) {
	           navigator.geolocation.getCurrentPosition(function (position) {
	               var c = position.coords;
	               $scope.gotoLocation(c.latitude, c.longitude);
	           });
	           return true;
	       }
	       return false;
	   };
	   $scope.gotoLocation = function (lat, lon) {
	       if ($scope.lat != lat || $scope.lon != lon) {
	           $scope.loc = { lat: lat, lon: lon };
	           if (!$scope.$$phase) $scope.$apply("loc");
	       }
	   };

	   // geolocalizzazione per chiamate asincrone
	   $scope.search = "";
	   $scope.geoCode = function () {
	       if ($scope.search && $scope.search.length > 0) {
	           if (!this.geocoder) this.geocoder = new google.maps.Geocoder();
	           this.geocoder.geocode({ 'address': $scope.search }, function (results, status) {
	               if (status == google.maps.GeocoderStatus.OK) {
	                   var loc = results[0].geometry.location;
	                   $scope.search = results[0].formatted_address;
	                   $scope.gotoLocation(loc.lat(), loc.lng());
	               } else {
	                   alert("Mi dispiace ma quello che hai cercato non ha prodotto risultati");
	               }
	           });
	       }
	   };

	   // punti di interesse mokkati per farli visualizzare sulla mappa
	   // la formattazzione del json è tramite "lat", "lon", e "name" 
	   $scope.pointOfInterest = [
	       { "name": "Casa Pizzi", "code": "RM", "city": "Roma", "state": "IT", "lat": 41.8566992, "lon": 12.6056415, "vol2011": 44414121 },
	       { "name": "Colosseo", "code": "RM", "city": "Roma", "state": "IT", "lat": 41.8902142, "lon": 12.4900369, "vol2011": 44414121 },
	       { "name": "Tor Vergata", "code": "RM", "city": "Roma", "state": "IT", "lat": 41.850146, "lon": 12.5956788, "vol2011": 44414121 },
	   ];
	}])
.filter('lat', function () {
	//Formattazione dei numeri per la latitudine (40.46... => "40°27'44"N")
   return function (input, decimals) {
       if (!decimals) decimals = 0;
       input = input * 1;
       var ns = input > 0 ? "N" : "S";
       input = Math.abs(input);
       var deg = Math.floor(input);
       var min = Math.floor((input - deg) * 60);
       var sec = ((input - deg - min / 60) * 3600).toFixed(decimals);
       return deg + "°" + min + "'" + sec + '"' + ns;
   }
})

.filter('lon', function () {
	//Formattazione dei numeri per la longitudine (-80.02... => "80°1'24"W")
	return function (input, decimals) {
       if (!decimals) decimals = 0;
       input = input * 1;
       var ew = input > 0 ? "E" : "W";
       input = Math.abs(input);
       var deg = Math.floor(input);
       var min = Math.floor((input - deg) * 60);
       var sec = ((input - deg - min / 60) * 3600).toFixed(decimals);
       return deg + "°" + min + "'" + sec + '"' + ew;
   }
})

.directive("appMap", function () {
	//- Documentazione per utilizzare google maps: https://developers.google.com/maps/documentation/

	return {
       restrict: "E",
       replace: true,
       template: "<div></div>",
       scope: {
           center: "=",        // Center point on the map (e.g. <code>{ latitude: 10, longitude: 10 }</code>).
           markers: "=",       // Array of map markers (e.g. <code>[{ lat: 10, lon: 10, name: "hello" }]</code>).
           width: "@",         // Map width in pixels.
           height: "@",        // Map height in pixels.
           zoom: "@",          // Zoom level (one is totally zoomed out, 25 is very much zoomed in).
           mapTypeId: "@",     // Type of tile to show on the map (roadmap, satellite, hybrid, terrain).
           panControl: "@",    // Whether to show a pan control on the map.
           zoomControl: "@",   // Whether to show a zoom control on the map.
           scaleControl: "@"   // Whether to show scale control on the map.
       },
       link: function (scope, element, attrs) {
           var toResize, toCenter;
           var map;
           var currentMarkers;

           // cambia lo scoope dell'aplicazione per cambiare la mappa
           var arr = ["width", "height", "markers", "mapTypeId", "panControl", "zoomControl", "scaleControl"];
           for (var i = 0, cnt = arr.length; i < arr.length; i++) {
               scope.$watch(arr[i], function () {
                   cnt--;
                   if (cnt <= 0) {
                       updateControl();
                   }
               });
           }

           // aggiornamento dello zoom e centramento, con ricreazione della mappa
           scope.$watch("zoom", function () {
               if (map && scope.zoom)
                   map.setZoom(scope.zoom * 1);
           });
           scope.$watch("center", function () {
               if (map && scope.center)
                   map.setCenter(getLocation(scope.center));
           });

           function updateControl() {

               // Aggiornamento altezza e lunghezza
               if (scope.width) element.width(scope.width);
               if (scope.height) element.height(scope.height);

               // opzioni mappa
               var options =
               {
                   center: new google.maps.LatLng(41.9, -12.416667),
                   zoom: 11,
                   mapTypeId: "roadmap"
               };
               if (scope.center) options.center = getLocation(scope.center);
               if (scope.zoom) options.zoom = scope.zoom * 1;
               if (scope.mapTypeId) options.mapTypeId = scope.mapTypeId;
               if (scope.panControl) options.panControl = scope.panControl;
               if (scope.zoomControl) options.zoomControl = scope.zoomControl;
               if (scope.scaleControl) options.scaleControl = scope.scaleControl;

               // Creazione mappa
               map = new google.maps.Map(element[0], options);

               // creazione markers
               updateMarkers();

               // aggiornamento dello scoope
               google.maps.event.addListener(map, 'center_changed', function () {

                   // non aggiornare se l'utente non zoomma o allarga la mappa
                   if (toCenter) clearTimeout(toCenter);
                   toCenter = setTimeout(function () {
                       if (scope.center) {

                           // controlla se il centro è veramente cambiato
                           if (map.center.lat() != scope.center.lat ||
                               map.center.lng() != scope.center.lon) {

                               // aggiorna lo scoope e applica i cambiamenti
                               scope.center = { lat: map.center.lat(), lon: map.center.lng() };
                               if (!scope.$$phase) scope.$apply("center");
                           }
                       }
                   }, 500);
               });
           }

           // aggiornamento dei markers
           function updateMarkers() {
               if (map && scope.markers) {

                   // cancella i vecchi markers
                   if (currentMarkers != null) {
                       for (var i = 0; i < currentMarkers.length; i++) {
                           currentMarkers[i] = m.setMap(null);
                       }
                   }

                   // creazione di un nuovo markers
                   currentMarkers = [];
                   var markers = scope.markers;
                   if (angular.isString(markers)) markers = scope.$eval(scope.markers);
                   for (var i = 0; i < markers.length; i++) {
                       var m = markers[i];
                       var loc = new google.maps.LatLng(m.lat, m.lon);
                       var mm = new google.maps.Marker({ position: loc, map: map, title: m.name });
                       currentMarkers.push(mm);
                   }
               }
           }

           // converzione della posizione corrente per google maps
           function getLocation(loc) {
               if (loc == null) return new google.maps.LatLng(41.9, 12.416667);
               if (angular.isString(loc)) loc = scope.$eval(loc);
               return new google.maps.LatLng(loc.lat, loc.lon);
           }
       }
   };
});
   
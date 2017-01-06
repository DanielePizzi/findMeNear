angular.module('findMeNearApp.templateComuneModule')

/*DIRETTIVA PER FAR DISEGNARE LA MAPPA A SCHERMO*/
.directive("appMap", [ '$location', function (location) {
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
           
           console.log(location.url())

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
               
               if (location.url() == "/maps") {
            	   google.maps.event.addListener(map, 'click', function(event) {
                       addMarker(event.latLng, map);
                     });
			}
               
           }
           
           /*aggiunta marker dinamicamente*/
           function addMarker(location, map) {
        	   var markers = [];
        	   var labels = 'A';
        	   var labelIndex = 0;
          		  // Add the marker at the clicked location, and add the next-available label
          		  // from the array of alphabetical characters.
          		  var marker = new google.maps.Marker({
          			draggable: true,
          		    position: location,
          		    label: labels[labelIndex++ % labels.length],
          		    animation: google.maps.Animation.DROP,
          		    map: map
          		  });
          		  markers.push(marker);
          		  console.log(markers);
          	}

           // aggiornamento dei markers
           function updateMarkers() {
               if (map && scope.markers) {

                   // cancella i vecchi markers
                   if (currentMarkers != null) {
                	   currentMarkers = null;
                   }

                   // creazione di un nuovo markers
                   currentMarkers = [];
                   var markers = scope.markers;
                   if (angular.isString(markers)) markers = scope.$eval(scope.markers);
                   for (var i = 0; i < markers.length; i++) {
                       var m = markers[i];
                       var loc = new google.maps.LatLng(m.geometry.location.lat, m.geometry.location.lng);
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
}])

/*DIRETTIVA PER COMPARARE SE DUE CAMPI SONO UGUALI*/  

.directive("compareTo", function () {
	return {
		        require: "ngModel",
		        scope: {
		            otherModelValue: "=compareTo"
		        },
		        link: function(scope, element, attributes, ngModel) {
		             
		            ngModel.$validators.compareTo = function(modelValue) {
		                return modelValue == scope.otherModelValue;
		            };
		 
		            scope.$watch("otherModelValue", function() {
		                ngModel.$validate();
		            });
		        }
		    };
});


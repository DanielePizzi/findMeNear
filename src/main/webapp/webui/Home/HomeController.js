angular.module('findMeNearApp.HomeModule')
.controller('HomeController', ['$scope', '$location', function ($scope, $location) {
	
	$scope.home = {};
	
	$scope.utenteLoggato = JSON.parse(JSON.stringify(eval('(' + sessionStorage.getItem('utenteLoggato')+')')));
	
	console.log($scope.utenteLoggato);
	
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

   
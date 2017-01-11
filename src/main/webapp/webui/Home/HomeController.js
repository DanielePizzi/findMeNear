angular.module('findMeNearApp.HomeModule')
.controller('HomeController', ['$scope', '$http', '$location', '$anchorScroll', function ($scope, $http, $location, $anchorScroll) {
	
	$scope.home = {
		categorie : ["ristoranti","aereoporti","bar","atm","cafe","museo","dottore","palestra","ospedale","farmacia","parcheggio","scuole","università","stazioni del treno"],
	};
	
	
	$scope.utenteLoggato = JSON.parse(JSON.stringify(eval('(' + sessionStorage.getItem('utenteLoggato')+')')));
	$scope.pointOfInterest = [];
	
	
	// Posizione corrente al momento dell'apertura della macchina
	   $scope.loc = { lat: 41.9, lon: 12.416667 };
	   $scope.gotoCurrentLocation = function () {
		   $scope.pointOfInterest = [];
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
		   $anchorScroll();
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
	   
	   function distance(latCurrent, lonCurrent, latPosition, lonPosition, unit) {
	        var radlat1 = Math.PI * latCurrent/180
	        var radlat2 = Math.PI * latPosition/180
	        var radlon1 = Math.PI * lonCurrent/180
	        var radlon2 = Math.PI * lonPosition/180
	        var theta = lonCurrent-lonPosition
	        var radtheta = Math.PI * theta/180
	        var dist = Math.sin(radlat1) * Math.sin(radlat2) + Math.cos(radlat1) * Math.cos(radlat2) * Math.cos(radtheta);
	        dist = Math.acos(dist)
	        dist = dist * 180/Math.PI
	        dist = dist * 60 * 1.1515
	        if (unit=="K") { dist = dist * 1.609344 }
	        if (unit=="N") { dist = dist * 0.8684 }
	        return dist
	}
	   
	   $scope.home.ricercaPerCategoria = function(){
		   if ($scope.home.categoria == "ristoranti") {
			   $http.get('https://maps.googleapis.com/maps/api/place/nearbysearch/json?location='+$scope.loc.lat+','+$scope.loc.lon+'&rankby=distance&type=restaurant&key=AIzaSyD_jIwf9f9vry_xpbal9RrsV-eIoz1p6ks').then(function(response){
					 $scope.pointOfInterest = response.data.results;
					 console.log( $scope.pointOfInterest);
					 for (var i = 0; i < $scope.pointOfInterest.length; i++) { 
						$scope.pointOfInterest[i].distance = distance($scope.loc.lat, $scope.loc.lon, $scope.pointOfInterest[i].geometry.location.lat, $scope.pointOfInterest[i].geometry.location.lng, 'K');
					 	}
					 $scope.pointOfInterest.sort(function(a,b){
						 return parseFloat(a.distance) - parseFloat(b.distance);
					 })
			   		});
		   }
		   if ($scope.home.categoria == "aereoporti") {
			   $http.get('https://maps.googleapis.com/maps/api/place/nearbysearch/json?location='+$scope.loc.lat+','+$scope.loc.lon+'&rankby=distance&type=bar&key=AIzaSyD_jIwf9f9vry_xpbal9RrsV-eIoz1p6ks').then(function(response){
					 $scope.pointOfInterest = response.data.results;
					 console.log($scope.pointOfInterest);
					 for (var i = 0; i < $scope.pointOfInterest.length; i++) {
							$scope.pointOfInterest[i].distance = distance($scope.loc.lat, $scope.loc.lon, $scope.pointOfInterest[i].geometry.location.lat, $scope.pointOfInterest[i].geometry.location.lng, 'K');
						}
					 $scope.pointOfInterest.sort(function(a,b){
						 return parseFloat(a.distance) - parseFloat(b.distance);
					 })
				   });
		   }
		   if ($scope.home.categoria == "bar") {
			   $http.get('https://maps.googleapis.com/maps/api/place/nearbysearch/json?location='+$scope.loc.lat+','+$scope.loc.lon+'&rankby=distance&type=airport&key=AIzaSyD_jIwf9f9vry_xpbal9RrsV-eIoz1p6ks').then(function(response){
					 $scope.pointOfInterest = response.data.results;
					 console.log($scope.pointOfInterest);
					 for (var i = 0; i < $scope.pointOfInterest.length; i++) {
							$scope.pointOfInterest[i].distance = distance($scope.loc.lat, $scope.loc.lon, $scope.pointOfInterest[i].geometry.location.lat, $scope.pointOfInterest[i].geometry.location.lng, 'K');
						}
					 $scope.pointOfInterest.sort(function(a,b){
						 return parseFloat(a.distance) - parseFloat(b.distance);
					 })
				   });
		   }
		   if ($scope.home.categoria == "università") {
			   $http.get('https://maps.googleapis.com/maps/api/place/nearbysearch/json?location='+$scope.loc.lat+','+$scope.loc.lon+'&rankby=distance&type=university&key=AIzaSyD_jIwf9f9vry_xpbal9RrsV-eIoz1p6ks').then(function(response){
					 $scope.pointOfInterest = response.data.results;
					 console.log($scope.pointOfInterest);
					 for (var i = 0; i < $scope.pointOfInterest.length; i++) {
							$scope.pointOfInterest[i].distance = distance($scope.loc.lat, $scope.loc.lon, $scope.pointOfInterest[i].geometry.location.lat, $scope.pointOfInterest[i].geometry.location.lng, 'K');
						}
					 $scope.pointOfInterest.sort(function(a,b){
						 return parseFloat(a.distance) - parseFloat(b.distance);
					 })
				   });
		   }
		   if ($scope.home.categoria == "atm") {
			   $http.get('https://maps.googleapis.com/maps/api/place/nearbysearch/json?location='+$scope.loc.lat+','+$scope.loc.lon+'&rankby=distance&type=atm&key=AIzaSyD_jIwf9f9vry_xpbal9RrsV-eIoz1p6ks').then(function(response){
					 $scope.pointOfInterest = response.data.results;
					 console.log($scope.pointOfInterest);
					 for (var i = 0; i < $scope.pointOfInterest.length; i++) {
							$scope.pointOfInterest[i].distance = distance($scope.loc.lat, $scope.loc.lon, $scope.pointOfInterest[i].geometry.location.lat, $scope.pointOfInterest[i].geometry.location.lng, 'K');
						}
					 $scope.pointOfInterest.sort(function(a,b){
						 return parseFloat(a.distance) - parseFloat(b.distance);
					 })
				   });
		   }
		   if ($scope.home.categoria == "cafe") {
			   $http.get('https://maps.googleapis.com/maps/api/place/nearbysearch/json?location='+$scope.loc.lat+','+$scope.loc.lon+'&rankby=distance&type=cafe&key=AIzaSyD_jIwf9f9vry_xpbal9RrsV-eIoz1p6ks').then(function(response){
					 $scope.pointOfInterest = response.data.results;
					 console.log($scope.pointOfInterest);
					 for (var i = 0; i < $scope.pointOfInterest.length; i++) {
							$scope.pointOfInterest[i].distance = distance($scope.loc.lat, $scope.loc.lon, $scope.pointOfInterest[i].geometry.location.lat, $scope.pointOfInterest[i].geometry.location.lng, 'K');
						}
					 $scope.pointOfInterest.sort(function(a,b){
						 return parseFloat(a.distance) - parseFloat(b.distance);
					 })
				   });
		   }
		   if ($scope.home.categoria == "museo") {
			   $http.get('https://maps.googleapis.com/maps/api/place/nearbysearch/json?location='+$scope.loc.lat+','+$scope.loc.lon+'&rankby=distance&type=museum&key=AIzaSyD_jIwf9f9vry_xpbal9RrsV-eIoz1p6ks').then(function(response){
					 $scope.pointOfInterest = response.data.results;
					 console.log($scope.pointOfInterest);
					 for (var i = 0; i < $scope.pointOfInterest.length; i++) {
							$scope.pointOfInterest[i].distance = distance($scope.loc.lat, $scope.loc.lon, $scope.pointOfInterest[i].geometry.location.lat, $scope.pointOfInterest[i].geometry.location.lng, 'K');
						}
					 $scope.pointOfInterest.sort(function(a,b){
						 return parseFloat(a.distance) - parseFloat(b.distance);
					 })
				   });
		   }
		   if ($scope.home.categoria == "dottore") {
			   $http.get('https://maps.googleapis.com/maps/api/place/nearbysearch/json?location='+$scope.loc.lat+','+$scope.loc.lon+'&rankby=distance&type=doctor&key=AIzaSyD_jIwf9f9vry_xpbal9RrsV-eIoz1p6ks').then(function(response){
					 $scope.pointOfInterest = response.data.results;
					 console.log($scope.pointOfInterest);
					 for (var i = 0; i < $scope.pointOfInterest.length; i++) {
							$scope.pointOfInterest[i].distance = distance($scope.loc.lat, $scope.loc.lon, $scope.pointOfInterest[i].geometry.location.lat, $scope.pointOfInterest[i].geometry.location.lng, 'K');
						}
					 $scope.pointOfInterest.sort(function(a,b){
						 return parseFloat(a.distance) - parseFloat(b.distance);
					 })
				   });
		   }
		   if ($scope.home.categoria == "palestra") {
			   $http.get('https://maps.googleapis.com/maps/api/place/nearbysearch/json?location='+$scope.loc.lat+','+$scope.loc.lon+'&rankby=distance&type=gym&key=AIzaSyD_jIwf9f9vry_xpbal9RrsV-eIoz1p6ks').then(function(response){
					 $scope.pointOfInterest = response.data.results;
					 console.log($scope.pointOfInterest);
					 for (var i = 0; i < $scope.pointOfInterest.length; i++) {
							$scope.pointOfInterest[i].distance = distance($scope.loc.lat, $scope.loc.lon, $scope.pointOfInterest[i].geometry.location.lat, $scope.pointOfInterest[i].geometry.location.lng, 'K');
						}
					 $scope.pointOfInterest.sort(function(a,b){
						 return parseFloat(a.distance) - parseFloat(b.distance);
					 })
				   });
		   }
		   if ($scope.home.categoria == "ospedale") {
			   $http.get('https://maps.googleapis.com/maps/api/place/nearbysearch/json?location='+$scope.loc.lat+','+$scope.loc.lon+'&rankby=distance&type=hospital&key=AIzaSyD_jIwf9f9vry_xpbal9RrsV-eIoz1p6ks').then(function(response){
					 $scope.pointOfInterest = response.data.results;
					 console.log($scope.pointOfInterest);
					 for (var i = 0; i < $scope.pointOfInterest.length; i++) {
							$scope.pointOfInterest[i].distance = distance($scope.loc.lat, $scope.loc.lon, $scope.pointOfInterest[i].geometry.location.lat, $scope.pointOfInterest[i].geometry.location.lng, 'K');
						}
					 $scope.pointOfInterest.sort(function(a,b){
						 return parseFloat(a.distance) - parseFloat(b.distance);
					 })
				   });
		   }
		   if ($scope.home.categoria == "farmacia") {
			   $http.get('https://maps.googleapis.com/maps/api/place/nearbysearch/json?location='+$scope.loc.lat+','+$scope.loc.lon+'&rankby=distance&type=pharmacy&key=AIzaSyD_jIwf9f9vry_xpbal9RrsV-eIoz1p6ks').then(function(response){
					 $scope.pointOfInterest = response.data.results;
					 console.log($scope.pointOfInterest);
					 for (var i = 0; i < $scope.pointOfInterest.length; i++) {
							$scope.pointOfInterest[i].distance = distance($scope.loc.lat, $scope.loc.lon, $scope.pointOfInterest[i].geometry.location.lat, $scope.pointOfInterest[i].geometry.location.lng, 'K');
						}
					 $scope.pointOfInterest.sort(function(a,b){
						 return parseFloat(a.distance) - parseFloat(b.distance);
					 })
				   });
		   }
		   if ($scope.home.categoria == "parcheggio") {
			   $http.get('https://maps.googleapis.com/maps/api/place/nearbysearch/json?location='+$scope.loc.lat+','+$scope.loc.lon+'&rankby=distance&type=parking&key=AIzaSyD_jIwf9f9vry_xpbal9RrsV-eIoz1p6ks').then(function(response){
					 $scope.pointOfInterest = response.data.results;
					 console.log($scope.pointOfInterest);
					 for (var i = 0; i < $scope.pointOfInterest.length; i++) {
							$scope.pointOfInterest[i].distance = distance($scope.loc.lat, $scope.loc.lon, $scope.pointOfInterest[i].geometry.location.lat, $scope.pointOfInterest[i].geometry.location.lng, 'K');
						}
					 $scope.pointOfInterest.sort(function(a,b){
						 return parseFloat(a.distance) - parseFloat(b.distance);
					 })
				   });
		   }
		   if ($scope.home.categoria == "scuole") {
			   $http.get('https://maps.googleapis.com/maps/api/place/nearbysearch/json?location='+$scope.loc.lat+','+$scope.loc.lon+'&rankby=distance&type=school&key=AIzaSyD_jIwf9f9vry_xpbal9RrsV-eIoz1p6ks').then(function(response){
					 $scope.pointOfInterest = response.data.results;
					 console.log($scope.pointOfInterest);
					 for (var i = 0; i < $scope.pointOfInterest.length; i++) {
							$scope.pointOfInterest[i].distance = distance($scope.loc.lat, $scope.loc.lon, $scope.pointOfInterest[i].geometry.location.lat, $scope.pointOfInterest[i].geometry.location.lng, 'K');
						}
					 $scope.pointOfInterest.sort(function(a,b){
						 return parseFloat(a.distance) - parseFloat(b.distance);
					 })
				   });
		   }
		   if ($scope.home.categoria == "stazioni del treno") {
			   $http.get('https://maps.googleapis.com/maps/api/place/nearbysearch/json?location='+$scope.loc.lat+','+$scope.loc.lon+'&rankby=distance&type=train_station&key=AIzaSyD_jIwf9f9vry_xpbal9RrsV-eIoz1p6ks').then(function(response){
					 $scope.pointOfInterest = response.data.results;
					 console.log($scope.pointOfInterest);
					 for (var i = 0; i < $scope.pointOfInterest.length; i++) {
							$scope.pointOfInterest[i].distance = distance($scope.loc.lat, $scope.loc.lon, $scope.pointOfInterest[i].geometry.location.lat, $scope.pointOfInterest[i].geometry.location.lng, 'K');
						}
					 $scope.pointOfInterest.sort(function(a,b){
						 return parseFloat(a.distance) - parseFloat(b.distance);
					 })
				   });
		   }
	   }
	   	   
	}])

   
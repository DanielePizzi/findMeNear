angular.module('findMeNearApp.HomeModule')
.controller('HomeController', ['$scope', '$http', '$location', function ($scope, $http, $location) {
	
	$scope.home = {
		categorie : ["ristoranti","aereoporti","bar","atm","cafe","museo","dottore","palestra","ospedale","farmacia","parcheggio","scuole","università","stazioni del treno"],
	};
	
	
	$scope.utenteLoggato = JSON.parse(JSON.stringify(eval('(' + sessionStorage.getItem('utenteLoggato')+')')));
	
	
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
	   
	   $scope.home.ricercaPerCategoria = function(){
		   if ($scope.home.categoria == "ristoranti") {
			   $http.get('https://maps.googleapis.com/maps/api/place/nearbysearch/json?location='+$scope.loc.lat+','+$scope.loc.lon+'radius=6000&type=restaurant&keyword=cruise&key=AIzaSyD_jIwf9f9vry_xpbal9RrsV-eIoz1p6ks').then(function(response){
					 $scope.pointOfInterest = response.data.results;
				   });
		   }
		   if ($scope.home.categoria == "aereoporti") {
			   $http.get('https://maps.googleapis.com/maps/api/place/nearbysearch/json?location='+$scope.loc.lat+','+$scope.loc.lon+'&radius=6000&type=bar&keyword=cruise&key=AIzaSyD_jIwf9f9vry_xpbal9RrsV-eIoz1p6ks').then(function(response){
					 $scope.pointOfInterest = response.data.results;
				   });
		   }
		   if ($scope.home.categoria == "bar") {
			   $http.get('https://maps.googleapis.com/maps/api/place/nearbysearch/json?location='+$scope.loc.lat+','+$scope.loc.lon+'&radius=6000&type=airport&keyword=cruise&key=AIzaSyD_jIwf9f9vry_xpbal9RrsV-eIoz1p6ks').then(function(response){
					 $scope.pointOfInterest = response.data.results;
				   });
		   }
		   if ($scope.home.categoria == "università") {
			   $http.get('https://maps.googleapis.com/maps/api/place/nearbysearch/json?location='+$scope.loc.lat+','+$scope.loc.lon+'&radius=6000&type=university&keyword=cruise&key=AIzaSyD_jIwf9f9vry_xpbal9RrsV-eIoz1p6ks').then(function(response){
					 $scope.pointOfInterest = response.data.results;
				   });
		   }
		   if ($scope.home.categoria == "atm") {
			   $http.get('https://maps.googleapis.com/maps/api/place/nearbysearch/json?location='+$scope.loc.lat+','+$scope.loc.lon+'&radius=6000&type=atm&keyword=cruise&key=AIzaSyD_jIwf9f9vry_xpbal9RrsV-eIoz1p6ks').then(function(response){
					 $scope.pointOfInterest = response.data.results;
				   });
		   }
		   if ($scope.home.categoria == "cafe") {
			   $http.get('https://maps.googleapis.com/maps/api/place/nearbysearch/json?location='+$scope.loc.lat+','+$scope.loc.lon+'&radius=6000&type=cafe&keyword=cruise&key=AIzaSyD_jIwf9f9vry_xpbal9RrsV-eIoz1p6ks').then(function(response){
					 $scope.pointOfInterest = response.data.results;
				   });
		   }
		   if ($scope.home.categoria == "museo") {
			   $http.get('https://maps.googleapis.com/maps/api/place/nearbysearch/json?location='+$scope.loc.lat+','+$scope.loc.lon+'&radius=6000&type=museum&keyword=cruise&key=AIzaSyD_jIwf9f9vry_xpbal9RrsV-eIoz1p6ks').then(function(response){
					 $scope.pointOfInterest = response.data.results;
					 console.log($scope.pointOfInterest);
				   });
		   }
		   if ($scope.home.categoria == "dottore") {
			   $http.get('https://maps.googleapis.com/maps/api/place/nearbysearch/json?location='+$scope.loc.lat+','+$scope.loc.lon+'&radius=6000&type=doctor&keyword=cruise&key=AIzaSyD_jIwf9f9vry_xpbal9RrsV-eIoz1p6ks').then(function(response){
					 $scope.pointOfInterest = response.data.results;
				   });
		   }
		   if ($scope.home.categoria == "palestra") {
			   $http.get('https://maps.googleapis.com/maps/api/place/nearbysearch/json?location='+$scope.loc.lat+','+$scope.loc.lon+'&radius=6000&type=gym&keyword=cruise&key=AIzaSyD_jIwf9f9vry_xpbal9RrsV-eIoz1p6ks').then(function(response){
					 $scope.pointOfInterest = response.data.results;
				   });
		   }
		   if ($scope.home.categoria == "ospedale") {
			   $http.get('https://maps.googleapis.com/maps/api/place/nearbysearch/json?location='+$scope.loc.lat+','+$scope.loc.lon+'&radius=6000&type=hospital&keyword=cruise&key=AIzaSyD_jIwf9f9vry_xpbal9RrsV-eIoz1p6ks').then(function(response){
					 $scope.pointOfInterest = response.data.results;
				   });
		   }
		   if ($scope.home.categoria == "farmacia") {
			   $http.get('https://maps.googleapis.com/maps/api/place/nearbysearch/json?location='+$scope.loc.lat+','+$scope.loc.lon+'&radius=6000&type=pharmacy&keyword=cruise&key=AIzaSyD_jIwf9f9vry_xpbal9RrsV-eIoz1p6ks').then(function(response){
					 $scope.pointOfInterest = response.data.results;
					 console.log(response.data);
					 console.log($scope.pointOfInterest);
				   });
		   }
		   if ($scope.home.categoria == "parcheggio") {
			   $http.get('https://maps.googleapis.com/maps/api/place/nearbysearch/json?location='+$scope.loc.lat+','+$scope.loc.lon+'&radius=6000&type=parking&keyword=cruise&key=AIzaSyD_jIwf9f9vry_xpbal9RrsV-eIoz1p6ks').then(function(response){
					 $scope.pointOfInterest = response.data.results;
				   });
		   }
		   if ($scope.home.categoria == "scuole") {
			   $http.get('https://maps.googleapis.com/maps/api/place/nearbysearch/json?location='+$scope.loc.lat+','+$scope.loc.lon+'&radius=6000&type=school&keyword=cruise&key=AIzaSyD_jIwf9f9vry_xpbal9RrsV-eIoz1p6ks').then(function(response){
					 $scope.pointOfInterest = response.data.results;
				   });
		   }
		   if ($scope.home.categoria == "stazioni del treno") {
			   $http.get('https://maps.googleapis.com/maps/api/place/nearbysearch/json?location='+$scope.loc.lat+','+$scope.loc.lon+'&radius=6000&type=train_station&keyword=cruise&key=AIzaSyD_jIwf9f9vry_xpbal9RrsV-eIoz1p6ks').then(function(response){
					 $scope.pointOfInterest = response.data.results;
				   });
		   }
	   }
	   	   
	}])

   
angular.module('findMeNearApp.MapsModule')
    .controller('MapsController', ['$scope','$rootScope', '$location', '$http', 'MapsService', '$window', '$anchorScroll', '$q', function ($scope,  $rootScope, $location, $http, MapsService, $window, $anchorScroll, $q) {
    	
    	$scope.utenteLoggato = JSON.parse(JSON.stringify(eval('(' + sessionStorage.getItem('utenteLoggato')+')')));
    	
    	$scope.maps = {
    			loc: { lat: 41.8709227, lon: 12.5916256 },
    			categorie : ["ristoranti","aereoporti","bar","atm","cafe","museo","dottore","palestra","ospedale","farmacia","parcheggio","scuole","università","stazioni del treno"],
    			pointOfInterestPersonal: []
    	}
    	
    	var position = $scope.$on('markers',function(event, args){
    		$scope.coordinate = args[0];
    	})
    	
    	$scope.aggiungiPuntoDiInteresse = function (){
    		var puntoDiInteresse = {
    			username: $scope.utenteLoggato.nome,
    			pointOfInterest : {
    				nome: $scope.maps.nome,
    				citta: $scope.maps.citta,
    				stato: $scope.maps.stato,
    				tipo: $scope.maps.categoria,
    				descrizione: $scope.maps.descrizione,
    				geometry: {
    					location: {
    						lat: $scope.coordinate.position.lat().toString(),
    						lng: $scope.coordinate.position.lng().toString(),
    					}
    				}
    			},
    		}
    		MapsService.savePoint(puntoDiInteresse).then(function(response){
    			if (response.data.esito == false) {
    				$window.alert(response.data.descrizione);
				} else {
					$window.alert(response.data.descrizione);
					$scope.maps.nome = null;
					$scope.maps.citta = null;
					$scope.maps.stato = null;
					$scope.maps.categoria = null;
					$scope.maps.descrizione = null;
					$anchorScroll();
				}
        	})
    	}
    	
    	// Posizione corrente al momento dell'apertura della macchina
 	   $scope.gotoCurrentLocation = function () {
 		   var deferred = $q.defer();
 	       if ("geolocation" in navigator) {
 	           navigator.geolocation.getCurrentPosition(function (position) {
 	               var c = position.coords;
 	               $scope.coordinate = position;
 	               $scope.gotoLocation(c.latitude, c.longitude);
 	               $rootScope.$broadcast('position',position)
 	              deferred.resolve(true);
 	           });
 	       } else {
 	    	  deferred.reject(false);
 	       }
 	      return deferred.promise;
 	   };
 	   $scope.gotoLocation = function (lat, lon) {
 	       if ($scope.lat != lat || $scope.lon != lon) {
 	           $scope.maps.loc = { lat: lat, lon: lon };
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
 	   
 	   $scope.maps.ricercaPuntoPiuVicino = function(){
 		   var puntoDiInteresse = {
 				   username: $scope.utenteLoggato.nome,
 				   categoria: $scope.maps.categoriaPuntiSalvati,
 				   latitudine: $scope.maps.loc.lat,
 				   longitudine: $scope.maps.loc.lon
 		   }
 		  $scope.gotoCurrentLocation().then(function(){
 			 MapsService.getPoint(puntoDiInteresse).then(function(response){
 	 			 if (response.data.esito == false) {
 	 				$window.alert(response.data.descrizione);
 					}
 	 			 $scope.maps.pointOfInterestPersonal = response.data.pointOfInterest
 	 			 $scope.gotoCurrentLocation();
 	 		  })  
 		  })
 	   }
 	   
 	   $scope.eliminaPuntoInteresse = function(){
 		  var puntoDiInteresse = {
				   idPoint: $scope.maps.pointOfInterestPersonal[0].id,
		   }
 		 MapsService.removePoint(puntoDiInteresse).then(function(response){
 			 if (response.data.esito == false) {
 				 	$window.alert(response.data.descrizione);
				} else {
					$window.alert(response.data.descrizione);
		 			$scope.maps.pointOfInterestPersonal = response.data.pointOfInterest
		 			$scope.maps.pointOfInterestPersonal = [];
				}
 		  })
 	   }
    	
    }]);

angular.module('findMeNearApp.MapsModule')
    .controller('MapsController', ['$scope','$rootScope', '$location', '$http', 'MapsService', '$window', '$anchorScroll',function ($scope, $rootScope, $location, $http,MapsService, $window, $anchorScroll) {
    	
    	$scope.utenteLoggato = JSON.parse(JSON.stringify(eval('(' + sessionStorage.getItem('utenteLoggato')+')')));
    	
    	$scope.maps = {
    			loc: { lat: 41.8709227, lon: 12.5916256 },
    			categorie : ["ristoranti","aereoporti","bar","atm","cafe","museo","dottore","palestra","ospedale","farmacia","parcheggio","scuole","università","stazioni del treno"],
    			pointOfInterestPersonal: []
    	}
    	
    	// punti di interesse mokkati per farli visualizzare sulla mappa
 	   // la formattazzione del json è tramite "lat", "lon", e "name" 
 	  
    	
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
 		  $scope.maps.pointOfInterestPersonal = [];
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
 		  MapsService.getPoint(puntoDiInteresse).then(function(response){
 			 if (response.data.esito == false) {
 				$window.alert(response.data.descrizione);
				}
 			 $scope.maps.pointOfInterestPersonal = response.data.pointOfInterest
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

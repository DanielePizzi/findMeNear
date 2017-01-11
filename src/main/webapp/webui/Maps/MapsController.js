angular.module('findMeNearApp.MapsModule')
    .controller('MapsController', ['$scope','$rootScope', '$location', 'MapsService', function ($scope,$rootScope, $location, MapsService) {
    	
    	console.log(MapsService);
    	
    	$scope.utenteLoggato = JSON.parse(JSON.stringify(eval('(' + sessionStorage.getItem('utenteLoggato')+')')));
    	
    	$scope.maps = {
    			loc: { lat: 41.9, lon: 12.416667 },
    			categorie : ["ristoranti","aereoporti","bar","atm","cafe","museo","dottore","palestra","ospedale","farmacia","parcheggio","scuole","università","stazioni del treno"],
    	}
    	
    	// punti di interesse mokkati per farli visualizzare sulla mappa
 	   // la formattazzione del json è tramite "lat", "lon", e "name" 
 	   $scope.maps.pointOfInterestPersonal = [
 	                                          
 	       { nome: "Casa Pizzi", citta: "Roma", stato: "IT", geometry : {location : {lat: 41.8566992, lng: 12.6056415}}, tipo: "personale" , descrizione :"mi piace tanto roma"},
 	   ];
    	
    	var position = $scope.$on('markers',function(event, args){
    		$scope.coordinate = args[0];
    		console.log($scope.coordinate);
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
        		console.log(response.data)
        	})
    	}
    	
    	
    }]);

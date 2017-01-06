angular.module('findMeNearApp.MapsModule')
    .controller('MapsController', ['$rootScope', '$location', function ($scope,$rootScope, $location) {
    	
    	$scope.maps = {
    			loc: { lat: 41.9, lon: 12.416667 }
    	}
    	
    	// punti di interesse mokkati per farli visualizzare sulla mappa
 	   // la formattazzione del json è tramite "lat", "lon", e "name" 
 	   $scope.maps.pointOfInterestPersonal = [
 	                                          
 	       { nome: "Casa Pizzi", citta: "Roma", stato: "IT", geometry : {location : {lat: 41.8566992, lng: 12.6056415}}, tipo: "personale" , descrizione :"mi piace tanto roma"},
 	   ];
    	
    	
    }]);

'use strict';
angular.module('findMeNearApp.MapsModule')
    .service('MapsService', function ($http) {
    	
    	return {
    		
    		savePoint: function(puntoDiInteresse){
    			return $http.post('findMeNear/maps/savePoint' ,puntoDiInteresse);
    		}
    		
    	}
           
    });
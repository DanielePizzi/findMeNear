
'use strict';
angular.module('findMeNearApp.RegisterModule')
    .service('RegisterService', function ($http) {
    	
    	return {
    		
    		datiRegistrazione: function(nuovoUtente){
    			return $http.post('findMeNear/register' ,nuovoUtente);
    		}
    		
    	}
           
    });
    
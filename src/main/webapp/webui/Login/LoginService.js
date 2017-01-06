'use strict';
angular.module('findMeNearApp.LoginModule')
    .service('LoginService', function ($http) {
    	
    	return {
    		
    		login: function(utente){
    			return $http.post('findMeNear/login' ,utente);
    		}
    		
    	}
           
    });
    
       
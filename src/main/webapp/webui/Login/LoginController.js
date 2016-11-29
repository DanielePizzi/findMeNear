angular.module('findMeNearApp.LoginModule')
    .controller('LoginController', ['$scope', '$rootScope', '$location','LoginService', function ($scope, $rootScope, $location,LoginService) {
    	  
    	$scope.login = {};

        $scope.login = function () {
        	var utente = {
    	    		email: $scope.login.email,
    	    		password: $scope.login.password,
    	    	}
          console.log(utente);
        	LoginService.login(utente).then(function(response){
    			console.log(response.data);
    		})
        };
    }]);
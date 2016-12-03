angular.module('findMeNearApp.LoginModule')
    .controller('LoginController', ['$scope', '$rootScope', '$location','LoginService', function ($scope, $rootScope, $location,LoginService) {
    	  
    	$scope.login = {};

        $scope.login = function () {
        	var utente = {
    	    		email: $scope.login.email,
    	    		password: $scope.login.password,
    	    	}
        	LoginService.login(utente).then(function(response){
        		if (response.data.esito == true) {
        			sessionStorage.setItem('utenteLoggato',JSON.stringify(response.data));
        			$location.path('/home');
				}else {
					alert("qualcosa è andato storto");
				}
        	})
        };
    }]);
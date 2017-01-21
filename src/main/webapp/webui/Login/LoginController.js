angular.module('findMeNearApp.LoginModule')
    .controller('LoginController', ['$scope', '$rootScope', '$location','LoginService', '$window',function ($scope, $rootScope, $location,LoginService, $window) {
    	  
    	$scope.login = {};

        $scope.login = function () {
        	var utente = {
    	    		email: $scope.login.email,
    	    		password: $scope.login.password,
    	    	}
        	LoginService.login(utente).then(function(response){
        		if (response.data.esito == true) {
        			sessionStorage.setItem('utenteLoggato',JSON.stringify(response.data));
        			$window.alert(response.data.descrizione);
        			$location.path('/home');
				}else {
					$window.alert(response.data.descrizione);
				}
        	})
        };
    }]);
angular.module('findMeNearApp.RegisterModule')
    .controller('RegisterController', ['$location', '$scope', 'RegisterService', '$window',function ($location, $scope, RegisterService, $window) {

    	$scope.register = {};
    	
    	$scope.registrati = function(){
    		
    		var nuovoUtente = {
    	    		name: $scope.register.name,
    	    		email: $scope.register.email,
    	    		password: $scope.register.password,
    	    	}
    		
    		RegisterService.datiRegistrazione(nuovoUtente).then(function(response){
    			if (response.data.esito == true) {
    				sessionStorage.setItem('utenteLoggato',JSON.stringify(response.data));
    				$window.alert(response.data.descrizione);
        			$location.path('/home');
				}else {
					$window.alert(response.data.descrizione);
				}
    		})
    	}
     
}]);

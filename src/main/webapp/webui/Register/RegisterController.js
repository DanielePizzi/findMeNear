angular.module('findMeNearApp.RegisterModule')
    .controller('RegisterController', ['$location', '$scope', 'RegisterService', function ($location, $scope, RegisterService) {

    	$scope.register = {};
    	console.log(RegisterService);
    	
    	$scope.registrati = function(){
    		
    		var nuovoUtente = {
    	    		name: $scope.register.name,
    	    		email: $scope.register.email,
    	    		password: $scope.register.password,
    	    	}
    		
    		console.log(nuovoUtente);
    		RegisterService.datiRegistrazione(nuovoUtente).then(function(response){
    			console.log(response.data);
    		})
    	}
     
}]);

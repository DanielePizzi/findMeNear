angular.module('findMeNearApp.templateComuneModule')
.controller('templateComuneController', [ '$rootScope', '$scope','$location','$route', function ($rootScope,$scope,$location,$route) {
		
	/*recupera l'utente loggato al momento della registrazione salvata nelle session-storage*/
	if (sessionStorage.length != 0) {
		$scope.utenteLoggato = JSON.parse(JSON.stringify(eval('(' + sessionStorage.getItem('utenteLoggato')+')')));
	}
	
	$rootScope.esci = function(){
		sessionStorage.removeItem('utenteLoggato');
		$route.reload();
	};
	
}])
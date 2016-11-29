angular.module('findMeNearApp.templateComuneModule')
.controller('templateComuneController', ['$rootScope','$scope', function ($rootScope,$scope) {
	
	/*recupera l'utente loggato al momento della registrazione salvata nelle session-storage*/
	$scope.utenteLoggato = JSON.parse(JSON.stringify(eval('(' + sessionStorage.getItem('utenteLoggato')+')')));
	
	console.log('ciao');
	
	$rootScope.esci = function(){
		console.log('ciao');
		sessionStorage.removeItem('utenteLoggato');
	};
	
}])
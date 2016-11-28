angular.module('findMeNearApp.templateComuneModule')
.controller('templateComuneController', ['$scope', function ($scope) {
	
	$scope.utenteLoggato = JSON.parse(JSON.stringify(eval('(' + sessionStorage.getItem('utenteLoggato')+')')));
	
}])
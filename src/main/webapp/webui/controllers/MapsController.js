var Maps = angular.module('Maps')
    .controller('MapsController', ['$rootScope', '$location', MapsController]);

function MapsController($scope,$rootScope, $location) {
	alert("mi prendo questo controller");
	
	var stringMap = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?";
	console.log(stringMap);
	
};

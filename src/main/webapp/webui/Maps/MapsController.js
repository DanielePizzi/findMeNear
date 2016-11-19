angular.module('findMeNearApp.Maps')
    .controller('MapsController', ['$rootScope', '$location', function ($scope,$rootScope, $location) {
    	alert("mi prendo questo controller");
    	
    	var stringMap = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?";
    	console.log(stringMap);
    	
    }]);

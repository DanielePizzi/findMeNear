angular.module('findMeNearApp.templateComuneModule')

.filter('lat', function () {
	//Formattazione dei numeri per la latitudine (40.46... => "40°27'44"N")
   return function (input, decimals) {
       if (!decimals) decimals = 0;
       input = input * 1;
       var ns = input > 0 ? "N" : "S";
       input = Math.abs(input);
       var deg = Math.floor(input);
       var min = Math.floor((input - deg) * 60);
       var sec = ((input - deg - min / 60) * 3600).toFixed(decimals);
       return deg + "°" + min + "'" + sec + '"' + ns;
   }
})

.filter('lon', function () {
	//Formattazione dei numeri per la longitudine (-80.02... => "80°1'24"W")
	return function (input, decimals) {
       if (!decimals) decimals = 0;
       input = input * 1;
       var ew = input > 0 ? "E" : "W";
       input = Math.abs(input);
       var deg = Math.floor(input);
       var min = Math.floor((input - deg) * 60);
       var sec = ((input - deg - min / 60) * 3600).toFixed(decimals);
       return deg + "°" + min + "'" + sec + '"' + ew;
   }
})

.filter('distance', function () {
	return function (input) {
		if (input >= 1) {
			return input.toFixed(2) + 'km';
		} else {
			return (input*1000).toFixed(2) + ' m';
		}
	}
})

.filter('open_closed', function () {
	return function (input) {
		if (input == false) {
			return 'Chiuso';
		} else {
			return 'Aperto';
		}
	}
})


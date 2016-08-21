angular.module('App.Admin')
    .controller('AdminController', ['$rootScope', '$location', AdminController]);

function AdminController($rootScope, $location) {
   
    $scope.currentUser = null;
};
angular.module('App.Auth')
    .controller('RegisterController', RegisterController);

RegisterController.$inject = ['$location', '$scope', '$rootScope', 'AuthService', 'FlashMessage'];
function RegisterController($location, $scope, $rootScope, AuthService, FlashMessage) {
//    var rc = this;
    console.log('register controller');
    $scope.register = function (admin) {
        console.log('received the register event for user: '+$scope.user.username);
        $rootScope.isSubmitted = true;
        $scope.dataLoading = true;
        $scope.user.admin = admin;
        AuthService.register($scope.user, function (response) {
            if (response.code==200) {
                AuthService.createJWTToken(response.result.user, response.result.token);
                AuthService.setCredentials();
                $location.path('/app');
            } else {
                $scope.error = response.result;
                $scope.details = response.details;
                $scope.dataLoading = false;
                $rootScope.isSubmitted = false;
            }
        });
    };
};
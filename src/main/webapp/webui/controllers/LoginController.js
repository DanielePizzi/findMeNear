angular.module('App.Auth')
    .controller('LoginController', ['$scope', '$rootScope', '$location', 'AuthService', LoginController]);

function LoginController($scope, $rootScope, $location, AuthService) {
   //var lc = this;

    (function initController() {
        // Reset della login
        AuthService.clearCredentials();
    })();

    $scope.login = function () {
        console.log('received the login event for user: '+$scope.user.email);
        $scope.dataLoading = true;
        $rootScope.isSubmitted = true;
        AuthService.login($scope.user.email, $scope.user.password, function (response) {
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
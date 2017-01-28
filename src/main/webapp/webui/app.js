
angular.module('findMeNearApp',
                                    [
                                        'ngAnimate',
                                        'ngMessages',
                                        'ngRoute',
                                        'ngCookies',
                                        'findMeNearApp.templateComuneModule',
                                        'findMeNearApp.HomeModule',
                                        'findMeNearApp.LoginModule',
                                        'findMeNearApp.RegisterModule',
                                        'findMeNearApp.MapsModule',
                                    ])
.config(['$routeProvider',
    function ($routeProvider){
        $routeProvider
            .when('/home', {
                controller: 'HomeController',
                templateUrl: 'webui/Home/home.html',
            })

            .when('/login', {
                controller: 'LoginController',
                templateUrl: 'webui/Login/login.html',
            })

            .when('/register', {
                controller: 'RegisterController',
                templateUrl: 'webui/Register/register.html',
            })

            .when('/access-denied', {
                controller: 'LoginController',
                templateUrl: 'webui/Login/access-denied.html',
            })

            .when('/maps', {
                controller: 'MapsController',
                templateUrl: 'webui/Maps/maps.html',
                })

            .otherwise({ redirectTo: '/home' });
    }
])

.config(function($httpProvider) {
	  $httpProvider.defaults.useXDomain = true;
      $httpProvider.defaults.headers.common['X-Requested-With'];
})

.constant('BackendCfg',  {
    url: '/findMeNear',
    setupHttp: function(http) {
        http.defaults.useXDomain = true;
        http.defaults.withCredentials = true;
    }
})
.run(['$rootScope', '$location', '$cookieStore', '$http',
    function ($rootScope, $location, $cookieStore, $http) {
        // keep user logged in after page refresh
        $rootScope.globals = $cookieStore.get('globals') || {};
        if ($rootScope.globals.currentUser) {
            $http.defaults.headers.common['Authorization'] = 'Bearer ' + $rootScope.globals.token;
            $rootScope.currentUser = $rootScope.globals.currentUser;
        }

        $rootScope.isSubmitted = false;

        $rootScope.$on('$locationChangeStart', function (event, next, current) {
            console.log('received event: ' + event + ' from: ' + current + ' to go to next: ' + next);
            // redirect to login page if not logged in and trying to access a restricted page
            var restrictedPage = $.inArray($location.path(), ['/login', '/register', '/admin.login', '/adm.register', '/admin', '/app', '/dashboard','/maps']) === -1;
            var loggedIn = $rootScope.globals.currentUser;
            $rootScope.currentUser = $rootScope.globals.currentUser;
            if (restrictedPage && !loggedIn) {
                if($location.path().indexOf('admin') > -1) {
                    $location.path('/admin.login');
                } else if($location.path().indexOf('app') > -1) {
                    $location.path('/login');
                } else {
                    $location.path('/home');
                }
            }
        });
    }
]);
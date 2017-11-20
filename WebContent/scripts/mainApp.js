var mainApp = angular.module('mainApp', ['ngRoute']);
    // create the controller and inject Angular's $scope
	mainApp.config(function($routeProvider, $locationProvider) {
	    $routeProvider
	        // route for the signup page
	        .when('/register', {
	            templateUrl : 'views/signup.html',
	            controller  : 'signUpController'
	        })
	
	        // route for the login page
	        .when('/', {
	            templateUrl : 'views/login.html',
	            controller  : 'loginController'
	        })
	        .when('/trucklocate', {
	            templateUrl : 'views/locateTruck.html',
	            controller  : 'locateTruckController'
	        })
	        .when('/home', {
	            templateUrl : 'views/homePage.html',
	            controller  : 'homeController'
	        })
	        .otherwise({ redirectTo: '/' });
	    /*$locationProvider.html5Mode(true);*/
	
	       /* // route for the contact page
	        .when('/contact', {
	            templateUrl : 'pages/contact.html',
	            controller  : 'contactController'
	        });*/
	});
	
    mainApp.controller('mainController', function($scope, $location) {
        // create a message to display in our view
    	$scope.rootUrl = $location.$$absUrl;
    });
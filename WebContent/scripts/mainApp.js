var mainApp = angular.module('mainApp', ['ngRoute']);
    // create the controller and inject Angular's $scope
	mainApp.config(function($routeProvider, $locationProvider) {
	    $routeProvider
	        // route for the signup page
	        .when('/register', {
	            templateUrl : 'views/signupCustomer.html',
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
	        .when('/manageAlerts', {
	            templateUrl : 'views/manageAlerts.html',
	            controller  : 'manageAlertsController'
	        })
	        .when('/vendorRegister', {
	            templateUrl : 'views/signupVendor.html',
	            controller  : 'signUpController'
	        })
	        .when('/rateFoodTruck', {
	            templateUrl : 'views/rateFoodTruck.html',
	            controller  : 'rateFoodTruckController'
	        })
	        .when('/reserveLocation', {
	            templateUrl : 'views/reserveLocation.html',
	            controller  : 'reserveLocationController'
	        })
	        .when('/locateTrucks', {
	            templateUrl : 'views/locateTrucks.html',
	            controller  : 'locateTruckController'
	        })
	        .when('/uploadMenu', {
	            templateUrl : 'views/uploadMenu.html',
	            controller  : 'uploadMenuController'
	        })
	        .when('/feedbacks', {
	            templateUrl : 'views/viewFeedBacks.html',
	            controller  : 'viewFeedBackController'
	        })
	        .otherwise({ redirectTo: '/' });
	});
	
    mainApp.controller('mainController', function($scope, $location) {
        // create a message to display in our view
    	$scope.rootUrl = $location.$$absUrl;
    });
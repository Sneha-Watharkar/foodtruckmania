mainApp.controller('loginController', function($scope, $location, $rootScope, $window) {
        // create a message to display in our view
        $scope.rootUrl = $location.$$absUrl;
        $scope.loadHomePage = function() {
        	console.log("Load Home page",$scope.rootUrl+'home'); 
        	$window.location.assign($scope.rootUrl+'home');
        }    
    });
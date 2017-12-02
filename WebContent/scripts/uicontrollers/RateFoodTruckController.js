mainApp.controller('rateFoodTruckController', function($scope, $location, $rootScope, $window, UserService) {
        // create a message to display in our view
		$scope.currentPage = 'Rate Food Truck';
		$scope.rootUrl = $location.$$absUrl.substring(0,$scope.rootUrl.lastIndexOf('/')+1);
		$scope.allFoodTrucks = [{
        	truckId: 1,
        	name: "Asian Eatery"
        },{
        	truckId: 2,
        	name: "Maki of Japan"
        },
        {
        	truckId: 3,
        	name: "Dominos"
        },
        {
        	truckId: 4,
        	name: "Chinese Gourmet"
        },
        {
        	truckId: 5,
        	name: "Subz"
        }];
});
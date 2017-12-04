mainApp.controller('adminController', function($scope, $location, FoodTruckService, $sce, $window, UserService) {
        // create a message to display in our view
        $scope.currentPage = 'Manage Trucks';
        $scope.rootUrl = $location.$$absUrl.substring(0,$scope.rootUrl.lastIndexOf('/')+1);
        $scope.setAsActive = function (path) {
			return ($location.path().substr(0, path.length) == path) ? 'active' : '';
		}
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
mainApp.controller('homeController', function($scope, $location, FoodTruckService) {
        // create a message to display in our view
        $scope.currentPage = 'Home';
        $scope.rootUrl = $location.$$absUrl.substring(0,$scope.rootUrl.lastIndexOf('/')+1);
        console.log("Home controller loads");
        $scope.favFoodTrucks;
       // $scope.allFoodTrucks = FoodTruckService.getAllFoodtrucks();
        //$scope.favFoodTrucks = FoodTruckService.getFavoriteFoodtrucks();
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
        
        $scope.favFoodTrucks = [{
        	truckId: 3,
        },{
        	truckId: 5
        }];
        
        $scope.setAsActive = function (path) {
			return ($location.path().substr(0, path.length) == path) ? 'active' : '';
		}
});
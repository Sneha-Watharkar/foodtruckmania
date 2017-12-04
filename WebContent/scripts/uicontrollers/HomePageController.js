mainApp.controller('homeController', function($scope, $location, FoodTruckService, $sce, $window, UserService) {
        // create a message to display in our view
        $scope.currentPage = 'Home';
        $scope.rootUrl = $location.$$absUrl.substring(0,$scope.rootUrl.lastIndexOf('/')+1);
        console.log("Home controller loads");
        $scope.favFoodTrucks;
        $scope.currentUser = UserService.getCurrentUser();
        console.log("In Home", $scope.currentUser);
        if($scope.currentUser.userType == "customer") {
        	FoodTruckService.getAllFoodtrucks().then(function(res){
            	console.log("All food trucks",  res);
            	var truckString = res.data.trucks;
            	$scope.allFoodTrucks = JSON.parse(truckString);
            }, function(err){
            	
            });
        	FoodTruckService.getFavoriteFoodtrucks().then(function(res){
            	console.log("All fav food trucks",  res);
            	/*var truckString = res.data.trucks;
            	$scope.allFoodTrucks = JSON.parse(truckString);*/
            }, function(err){
            	
            });
        	/*FoodTruckService.getFavoriteFoodtrucks();*/
        }
        
        $scope.favFoodTrucks = [{
        	truckId: 3,
        },{
        	truckId: 5
        }];
        
        $scope.setAsActive = function (path) {
			return ($location.path().substr(0, path.length) == path) ? 'active' : '';
		}
});
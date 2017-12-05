mainApp.controller('homeController', function($scope, $location, FoodTruckService, $sce, $window, UserService, $filter) {
        // create a message to display in our view
        $scope.currentPage = 'Home';
        $scope.rootUrl = $location.$$absUrl.substring(0,$scope.rootUrl.lastIndexOf('/')+1);
        console.log("Home controller loads");
        $scope.currentUser = UserService.getCurrentUser();
        console.log("In Home", $scope.currentUser);
        if($scope.currentUser.userType == "customer") {
        	FoodTruckService.getFavoriteFoodtrucks().then(function(res){
            	console.log("All fav food trucks",  res);
            	$scope.favFoodTrucks = res.data.trucks;
            	$scope.favFoodTrucks = ["20"];
            	/*var truckString = res.data.trucks;
            	$scope.allFoodTrucks = JSON.parse(truckString);*/
            }, function(err){
            	
            });
        	FoodTruckService.getAllFoodtrucks().then(function(res){
            	console.log("All food trucks",  res);
            	var truckString = res.data.trucks;
            	$scope.allFoodTrucks = JSON.parse(truckString);
            }, function(err){
            	
            });
        	
//        	for(var i=0;i<)
        	
        }
        
        $scope.setAsActive = function (path) {
			return ($location.path().substr(0, path.length) == path) ? 'active' : '';
		}
        
        $scope.updateUserFav = function(truck){
        	/*var favoriteObj = {
        			truck: truck
        	};*/
        	
        	UserService.updateFavTrucks(truck).then(function(res) {
        		console.log("Res of fav", res);
        	}, function(err){
        		
        	});
        };
});
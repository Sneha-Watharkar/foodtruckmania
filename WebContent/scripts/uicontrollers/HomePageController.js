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
            }, function(err){
            	
            });
        	FoodTruckService.getAllFoodtrucks().then(function(res){
            	console.log("All food trucks",  res);
            	var truckString = res.data.trucks;
            	$scope.allFoodTrucks = JSON.parse(truckString);
            }, function(err){
            	
            });
        }
        
        $scope.setAsActive = function (path) {
			return ($location.path().substr(0, path.length) == path) ? 'active' : '';
		}
        
        $scope.updateUserFav = function(truck){
        	console.log("Truck obj is ",truck);
        	UserService.updateFavTrucks(truck).then(function(res) {
        		console.log("Res of fav hello", res);
        		$scope.displayNotification('show', "Added to favorites", 'success');
        		FoodTruckService.getFavoriteFoodtrucks().then(function(res){
                	console.log("All fav food trucks",  res);
                	$scope.favFoodTrucks = res.data.trucks;
                }, function(err){
                	
                });
        	}, function(err){
        		
        	});
        };
        
        $scope.loadFoodMenu = function(truck) {
        	FoodTruckService.viewMenu(truck).then(function(menu){
        		console.log("menu is ",menu);
        		/*var file = new Blob([$scope.menuFile], {type: 'application/pdf'});
 	  	       var fileURL = URL.createObjectURL(file);
 	        $scope.foodMenuFile = $sce.trustAsResourceUrl(fileURL);
 	        $window.open($scope.foodMenuFile);*/
        	}, function(err){
        		
        	});
        };
        
        $scope.displayNotification = function(status, message, type) {
        	$scope.notification = {};
	        $scope.notification.status = status; 
	        $scope.notification.message = message;
	        $scope.notification.type = type;
        };
        
        $scope.logOut = function() {
        	
        	UserService.logout().then(function(res){
        		console.log("clicked correctly",res);
        		$window.location.assign($scope.rootUrl);
        	}, function(err){
        		
        	});
        };
		
});
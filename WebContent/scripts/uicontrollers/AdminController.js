mainApp.controller('adminController', function($scope, $location, FoodTruckService, $sce, $window, UserService) {
        // create a message to display in our view
        $scope.currentPage = 'Manage Trucks';
        $scope.rootUrl = $location.$$absUrl.substring(0,$scope.rootUrl.lastIndexOf('/')+1);
        $scope.setAsActive = function (path) {
			return ($location.path().substr(0, path.length) == path) ? 'active' : '';
		}
        
        FoodTruckService.fetchPendingApprovals().then(function(res){
        	$scope.allPendingFoodTrucks = res.data.results;
        	console.log("Pending trucks are",$scope.allPendingFoodTrucks );
        },
        function(err){
        	
        });
       
        $scope.acceptFoodTruck = function(truck) {
        	var truckObj = {
        			truck: truck,
        			foodTruckStatus: 'approved'
        	};
        	FoodTruckService.approveRejectFoodTruck(truckObj).then(function(res){
        		console.log(res,"App Status");
        		var index = $scope.allPendingFoodTrucks.indexOf(truck);
        		$scope.allPendingFoodTrucks.splice(index, 1);
            	/*$scope.allPendingFoodTrucks = res.data.results;
            	console.log("Pending trucks are",$scope.allPendingFoodTrucks );*/
            },
            function(err){
            	
            });
        }
        $scope.rejectFoodTruck = function(truck) {
        	var truckObj = {
        			truck: truck,
        			foodTruckStatus: 'reject'
        	};
        	FoodTruckService.approveRejectFoodTruck(truckObj).then(function(res){
        		console.log(res,"App Status");
        		var index = $scope.allPendingFoodTrucks.indexOf(truck);
        		$scope.allPendingFoodTrucks.splice(index, 1);
            	/*$scope.allPendingFoodTrucks = res.data.results;
            	console.log("Pending trucks are",$scope.allPendingFoodTrucks );*/
            },
            function(err){
            	
            });
        }
});
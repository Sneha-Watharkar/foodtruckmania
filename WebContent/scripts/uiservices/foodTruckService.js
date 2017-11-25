mainApp.service('FoodTruckService', function($http) {
	this.allFoodTrucks = null;
	this.errors = null;
	this.favFoodTrucks = null;
    this.getAllFoodtrucks = function () {
    	$http({
            method : 'GET',
            url : 'javaAngularJS'
		    }).success(function(data, status, headers, config) {
		           this.allFoodTrucks = data;
		           return this.allFoodTrucks;
		    }).error(function(data, status, headers, config) {
		           this.errors = data;
		    });
    }
    
    this.getFavoriteFoodtrucks = function (userId) {
    	$http({
            method : 'GET',
            url : 'javaAngularJS'
		    }).success(function(data, status, headers, config) {
		           this.favFoodTrucks = data;
		           return this.favFoodTrucks;
		    }).error(function(data, status, headers, config) {
		           this.errors = data;
		    });
    }
});
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
    
    this.rateFoodTruck = function (ratingObj) {
    	$http({
            method: 'POST',
            url: 'rateTruck',
            headers: {
                'Content-Type': 'application/json'
            },
            data:	JSON.stringify({data: ratingObj, action:'rating'})
        })
        .success(function (data) {
        	console.log("Success", data);
        	return data;
        })
        .error(function (data, status) {
        	console.log("Failure "+ JSON.stringify({data: data}));
        	return data;
        });
    }
});
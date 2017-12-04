mainApp.service('FoodTruckService', function($http) {
	this.allFoodTrucks = null;
	this.errors = null;
	this.favFoodTrucks = null;
    this.getAllFoodtrucks = function () {
    	console.log("getAllFoodtrucks service");
    	return $http({
            method : 'GET',
            url : 'getAllFoodTrucks',
            data: {'action': 'getAllFoodTrucks'}
		    });
    }
    this.fetchPendingApprovals = function () {
    	console.log("fetchPendingApprovals service");
    	return $http({
            method : 'POST',
            url : 'fetchPendingApprovals',
            data: {data: {},'action': 'fetchPendingApprovals'}
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
    
    this.reserveLocation =  function (locationObj) {
    	$http({
            method: 'POST',
            url: 'reserveLocation',
            headers: {
                'Content-Type': 'application/json'
            },
            data:	JSON.stringify({data: locationObj, action:'reserveLocation'})
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
    
    this.getFoodMenu = function (truckId) {
      $http.post('/loadMenu',{id: truckId}, {responseType:'arraybuffer'})
  	  .success(function (response) {
  	       var file = new Blob([response], {type: 'application/pdf'});
  	       var fileURL = URL.createObjectURL(file);
  	       return fileURL;
  	  }).error(function (data) {
	      	console.log("Failure "+ JSON.stringify({data: data}));
	    	return data;
  	  });
    }
    
    this.getAllLocations = function () {
    	console.log("getAllFoodtrucks service");
    	return $http({
            method : 'GET',
            url : 'getAllLocations',
            data: {'action': 'getAllLocations'}
		    });
    }
    
    this.approveRejectFoodTruck = function(truckObj) {
    	return $http({
            method: 'POST',
            url: 'approveFoodTrucks',
            headers: {
                'Content-Type': 'application/json'
            },
            data:JSON.stringify({data: truckObj, action:'approveFoodTrucks'})
        });
    }
});
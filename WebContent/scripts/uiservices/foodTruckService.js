mainApp.service('FoodTruckService', function($http) {
	this.allFoodTrucks = null;
	this.errors = null;
	this.favFoodTrucks = null;
    this.getAllFoodtrucks = function () {
    	console.log("getAllFoodtrucks service");
    	return $http({
            method : 'POST',
            url : 'getAllFoodTrucks',
            data: {data: {},'action': 'getAllFoodTrucks'}
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
    	return $http({
            method : 'POST',
            url : 'getFavFoodTrucks',
            data: {data: {},'action': 'getFavFoodTrucks'}
    	});
    }
    
    this.rateFoodTruck = function (ratingObj) {
    	return $http({
            method: 'POST',
            url: 'rateTruck',
            headers: {
                'Content-Type': 'application/json'
            },
            data:	JSON.stringify({data: ratingObj, action:'rating'})
        });
    }
    
    this.reserveLocation =  function (locationObj) {
    	return $http({
            method: 'POST',
            url: 'reserveLocation',
            headers: {
                'Content-Type': 'application/json'
            },
            data:	JSON.stringify({data: locationObj, action:'reserveLocation'})
        });
    }
    
    this.viewMenu = function (truck) {
    	console.log("View menu service");
      return $http.post('viewMenu',{data:truck,action:'viewMenu'}, {responseType:'arraybuffer'});
  	  /*.success(function (response) {
  	       var file = new Blob([response], {type: 'application/pdf'});
  	       var fileURL = URL.createObjectURL(file);
  	       return fileURL;
  	  }).error(function (data) {
	      	console.log("Failure "+ JSON.stringify({data: data}));
	    	return data;
  	  });*/
    }
    
    this.getAllLocations = function () {
    	console.log("getAllFoodtrucks service");
    	return $http({
            method : 'POST',
            url : 'getAllLocations',
            data: {data: {},'action': 'getAllLocations'}
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
    
    this.getFeedBacks = function() {
    	return $http({
            method: 'POST',
            url: 'getFeedback',
            headers: {
                'Content-Type': 'application/json'
            },
            data:JSON.stringify({data: {}, action:'getFeedback'})
        });	
    }
    
    this.sendMail = function() {
    	return $http({
            method: 'POST',
            url: 'sendMail',
            headers: {
                'Content-Type': 'application/json'
            },
            data:JSON.stringify({data: {}, action:'sendMail'})
        });	
    }
});
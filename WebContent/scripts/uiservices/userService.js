mainApp.service('UserService', function($http) {
	this.currentUser = {};
    this.checkForUserExistence = function (user) {
    	return $http({
            method: 'POST',
            url: 'loginUser',
            headers: {
                'Content-Type': 'application/json'
            },
            data:JSON.stringify({data: user, action:'login'})
        });
    }
    
    this.updateUserAlerts = function (userAlerts) {
    		return $http.post('updateUserAlerts',{data:userAlerts,'action':'setAlerts'});
    }
    
    this.setCurrentUser = function (currentUser) {
    	this.currentUser = currentUser;
    	console.log("Current user in service", this.currentUser);
    	return this.currentUser;
    }
    this.getCurrentUser = function () {
    	return this.currentUser;
    }
    
    this.getAlerts = function () {
		return $http.post('updateUserAlerts',{data:{},'action':'getAlerts'});
    }
    
    this.updateFavTrucks = function(truck) {
    	return $http.post('setUserFavorites',{data:{'truck':truck},'action':'setUserFavorites'});
    }
    
    this.logout = function(){
    	return $http.post('logout',{data:{},'action':'logout'});
    }
});
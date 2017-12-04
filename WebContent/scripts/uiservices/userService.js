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
});
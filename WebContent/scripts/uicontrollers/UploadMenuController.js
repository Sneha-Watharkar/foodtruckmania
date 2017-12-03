mainApp.controller('uploadMenuController', function($scope, $location, $window, $sce) {
        // create a message to display in our view
        $scope.currentPage = 'Upload Menu';
        $scope.rootUrl = $location.$$absUrl.substring(0,$scope.rootUrl.lastIndexOf('/')+1);;
        console.log("Root url",$scope.rootUrl); 
        $scope.setAsActive = function (path) {
			return ($location.path().substr(0, path.length) == path) ? 'active' : '';
		}
        
        
        $scope.uploadMenu = function () {
        	var fd = new FormData();
	        fd.append('file', $scope.menuFile);
	        console.log("File here is ",$scope.menuFile);
	        $scope.displayNotification('show', 'Menu Uploaded successfully!', 'success');
	        /*var file = new Blob([$scope.menuFile], {type: 'application/pdf'});
	  	       var fileURL = URL.createObjectURL(file);
	        $scope.foodMenuFile = $sce.trustAsResourceUrl(fileURL);
	        $window.open($scope.foodMenuFile);*/
	        /*$http.post('uploadMenu', fd, {
	             transformRequest: angular.identity,
	             headers: {'Content-Type': undefined}
	        });*/
        }
        
        $scope.displayNotification = function(status, message, type) {
        	$scope.notification = {
					/*status: 'hide',
					type: '',
					message: ''*/
			};
	        $scope.notification.status = status; 
	        $scope.notification.message = message;
	        $scope.notification.type = type;
        };
        
});
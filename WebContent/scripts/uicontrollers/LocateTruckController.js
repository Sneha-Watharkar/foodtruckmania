mainApp.controller('locateTruckController', function($scope, $location, FoodTruckService, UserService) {
        // create a message to display in our view
        $scope.currentPage = 'Truck Locations';
        $scope.rootUrl = $location.$$absUrl.substring(0,$scope.rootUrl.lastIndexOf('/')+1);;
        console.log("Root url",$scope.rootUrl); 
        $scope.currentUser = UserService.getCurrentUser();
        $scope.setAsActive = function (path) {
			return ($location.path().substr(0, path.length) == path) ? 'active' : '';
		}
        
        FoodTruckService.getAllLocations().then(function(res){
        	console.log("Res of locations",res);
        	var resultString = res.data.trucks;
        	$scope.locationData = JSON.parse(resultString);
        	console.log("Location Data", $scope.locationData);
        	
        	for (i = 0; i < $scope.locationData.length; i++){
                createMarker($scope.locationData[i]);
            }
        }, function(err){
        	
        });
        /*$scope.locations = [
            {
                location : 'Library',
                desc : 'UNCC Campus',
                lat : 35.30809,
                long : -80.73270000000002
            }
         ];*/
        
        var mapOptions = {
                zoom: 14,
                center: new google.maps.LatLng(35.30809,-80.73270000000002),
                /*mapTypeId: google.maps.MapTypeId.TERRAIN*/
            }
        $scope.map = new google.maps.Map(document.getElementById('map'), mapOptions);
        $scope.markers = [];
        
        var infoWindow = new google.maps.InfoWindow();
        
        var createMarker = function (info){
            
            var marker = new google.maps.Marker({
                map: $scope.map,
                position: new google.maps.LatLng(info.latitude, info.longitude),
                title: info.foodTruckName
            });
            marker.content = '<div class="infoWindowContent">' + info.foodTruckLocation + '</div>';
            
            google.maps.event.addListener(marker, 'click', function(){
                infoWindow.setContent('<h5>' + marker.title + '</h5>' + marker.content);
                infoWindow.open($scope.map, marker);
            });
            
            $scope.markers.push(marker);     
        }

        $scope.openInfoWindow = function(e, selectedMarker){
            e.preventDefault();
            google.maps.event.trigger(selectedMarker, 'click');
        }
        
});
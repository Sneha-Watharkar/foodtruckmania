mainApp.controller('locateTruckController', function($scope, $location) {
        // create a message to display in our view
        $scope.currentPage = 'Truck Locations';
        $scope.rootUrl = $location.$$absUrl.substring(0,$scope.rootUrl.lastIndexOf('/')+1);;
        console.log("Root url",$scope.rootUrl); 
        $scope.setAsActive = function (path) {
			return ($location.path().substr(0, path.length) == path) ? 'active' : '';
		}
        
        $scope.locations = [
            {
                location : 'Library',
                desc : 'UNCC Campus',
                lat : 35.30809,
                long : -80.73270000000002
            }
         ];
        
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
                position: new google.maps.LatLng(info.lat, info.long),
                title: info.city
            });
            marker.content = '<div class="infoWindowContent">' + info.desc + '</div>';
            
            google.maps.event.addListener(marker, 'click', function(){
                infoWindow.setContent('<h2>' + marker.title + '</h2>' + marker.content);
                infoWindow.open($scope.map, marker);
            });
            
            $scope.markers.push(marker);     
        }
        
        for (i = 0; i < $scope.locations.length; i++){
            createMarker($scope.locations[i]);
        }

        $scope.openInfoWindow = function(e, selectedMarker){
            e.preventDefault();
            google.maps.event.trigger(selectedMarker, 'click');
        }
        
});
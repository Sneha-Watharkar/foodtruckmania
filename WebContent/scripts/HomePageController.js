mainApp.controller('homeController', function($scope, $location) {
        // create a message to display in our view
        $scope.currentPage = 'Home';
        $scope.rootUrl = $location.$$absUrl;
        console.log("Home controller loads");  
});
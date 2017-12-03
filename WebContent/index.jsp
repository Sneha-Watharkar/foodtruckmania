<!DOCTYPE html>
<html ng-app="mainApp">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

<title>Food truck mania</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css" integrity="sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ" crossorigin="anonymous">
<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
<!-- <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" /> -->
<!-- <script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="bootstrap/js/jquery-3.2.1.min.js"></script> -->
<!-- <script src="http://maps.googleapis.com/maps/api/js?sensor=false&language=en"></script> -->
<link href="dashboard.css" rel="stylesheet">
<link href="styles.css" rel="stylesheet" type="text/css" />
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.2.25/angular.min.js"></script>
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/angularjs/1.2.25/angular-route.js"></script>
<script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDT-MCqOgy_1XXeZAVCUysmoFt6mjFtKuE"
  type="text/javascript"></script>
<script src="scripts/mainApp.js"></script>


<!-- Including all js files -->
<script src="scripts/uicontrollers/LoginController.js"></script>
<script src="scripts/uicontrollers/SignUpController.js"></script>
<script src="scripts/uicontrollers/HomePageController.js"></script>
<script src="scripts/uicontrollers/LocateTruckController.js"></script>
<script src="scripts/uicontrollers/ManageAlertsController.js"></script>
<script src="scripts/uicontrollers/RateFoodTruckController.js"></script>
<script src="scripts/uicontrollers/ReserveLocationController.js"></script>
<script src="scripts/uicontrollers/UploadMenuController.js"></script>
<script src="scripts/uiservices/userService.js"></script>
<script src="scripts/uiservices/foodTruckService.js"></script>
<script src="scripts/directives/fileUpload.js"></script>
<script src="scripts/directives/starRating.js"></script>
<script src="scripts/directives/notifications.js"></script>
</head>
<body ng-controller="mainController">
	<div ng-view></div>
<script src="https://code.jquery.com/jquery-3.1.1.slim.min.js" integrity="sha384-A7FZj7v+d/sdmMqp/nOQwliLvUsJfDHW+k9Omg/a/EheAdgtzNs3hpfag6Ed950n" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js" integrity="sha384-DztdAPBWPRXSA/3eYEEUWrWCy7G5KFbe8fFjk5JAIxUYHKkDx6Qin1DkWx51bBrb" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js" integrity="sha384-vBWWzlZJ8ea9aCX4pEW3rVHjgjt7zpkNpZk+02D9phzyeVkE+jo0ieGizqPLForn" crossorigin="anonymous"></script>
</body>
</html>
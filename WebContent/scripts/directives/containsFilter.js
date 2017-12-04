mainApp.filter('contains', function() {
  return function (array, needle) {
	  if(array & array.length) {
		  return array.indexOf(needle) >= 0;
	  } else {
		  return null;
	  }
  };
});
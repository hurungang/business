var dataObjectServices = angular.module('runtech-data-object', []);

dataObjectServices.factory('RuntechDataObject', ['$http',
  function($http){
	var factory = {};
	factory.get = function(objectType,objectId){
		return $http.get("../json/"+objectType+"/"+objectId+".do");
	}
	return factory;
  }]);
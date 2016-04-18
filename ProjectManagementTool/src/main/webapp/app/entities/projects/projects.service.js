(function() {
    'use strict';

    angular
        .module('projectManagementToolApp')
        .factory('Projects', Projects);

    Projects.$inject = ['$rootScope', '$resource'];

    function Projects ($rootScope, $resource) {
        
        var service = $resource('/api/projects', {}, {
            'query': {method: 'GET', isArray: true},
        	'get': {
        		method: 'GET',
        		transformResponse: function(data){
        			data = angular.fromJson(data);
        			return data;
        		}
        	}
        });
        
        return service;
    }
})()

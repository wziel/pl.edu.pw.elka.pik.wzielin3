(function () {
    'use strict';

    angular
        .module('projectManagementToolApp')
        .factory('Project', Project);

    Project.$inject = ['$resource'];

    function Project ($resource) {
        var service = $resource('api/projects/:name', {}, {
            'query': {method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    data = angular.fromJson(data);
                    return data;
                }
            },
            'save': { method:'POST' },
            'update': { method:'PUT' },
            'delete':{ method:'DELETE'}
        });

        return service;
    }
})();

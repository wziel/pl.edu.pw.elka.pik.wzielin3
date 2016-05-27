(function () {
    'use strict';

    angular
        .module('projectManagementToolApp')
        .factory('Task', Task);

    Task.$inject = ['$resource'];

    function Task($resource) {
        var service = $resource('api/tasks/:taskId', {}, {
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

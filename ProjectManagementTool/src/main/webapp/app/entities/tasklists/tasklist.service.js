(function () {
    'use strict';

    angular
        .module('projectManagementToolApp')
        .factory('TaskList', TaskList);

    TaskList.$inject = ['$resource'];

    function TaskList ($resource) {
        var service = $resource('api/tasklists/:taskListId', {}, {
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

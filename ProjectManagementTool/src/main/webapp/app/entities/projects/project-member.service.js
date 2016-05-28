(function () {
    'use strict';

    angular
        .module('projectManagementToolApp')
        .factory('ProjectMember', ProjectMember);

    ProjectMember.$inject = ['$resource'];

    function ProjectMember ($resource) {
        var service = $resource('api/projects/:projectId/:login', {
        	projectId: "@projectId",
            login: "@login"
        }, {
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

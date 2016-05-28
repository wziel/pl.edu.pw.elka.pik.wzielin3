(function () {
    'use strict';

    angular
        .module('projectManagementToolApp')
        .factory('Board', Board);

    Board.$inject = ['$resource'];

    function Board ($resource) {
        var service = $resource('api/boards/:boardId', {}, {
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

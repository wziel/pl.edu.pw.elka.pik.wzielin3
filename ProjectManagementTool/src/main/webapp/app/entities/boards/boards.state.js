(function() {
    'use strict';

    angular
        .module('projectManagementToolApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider.state('board-detail', {
            parent: 'project-detail',
            url: '/boards/:id',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'Board details'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/boards/board-detail.html',
                    controller: 'BoardController',
                    controllerAs: 'vm'
                }
            }
        });
    }
})();

(function() {
    'use strict';

    angular
        .module('projectManagementToolApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider.state('projects', {
            parent: 'entity',
            url: '/projects',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'Your Projects'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/projects/projects.html',
                    controller: 'ProjectsController',
                    controllerAs: 'vm'
                }
            }
        });
    }
})();

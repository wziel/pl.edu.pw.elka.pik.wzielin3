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
        })
            .state('project-detail', {
                parent: 'entity',
                url: '/projects/:name',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'Project Details'
                },
                views: {
                    'content@': {
                        templateUrl: 'app/entities/projects/project-detail.html',
                        controller: 'ProjectController',
                        controllerAs: 'vm'
                    }
                }
            })
            .state('projects.new', {
                parent: 'projects',
                url: '/new',
                data: {
                    authorities: ['ROLE_ADMIN']
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'app/entities/projects/projects-new-project-dialog.html',
                        controller: 'ProjectsNewProjectDialogController',
                        controllerAs: 'vm',
                        backdrop: 'static',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {
                                    name: null, membersCount: null
                                };
                            }
                        }
                    }).result.then(function() {
                        $state.go('projects', null, { reload: true });
                    }, function() {
                        $state.go('projects');
                    });
                }]
            });
    }
})();

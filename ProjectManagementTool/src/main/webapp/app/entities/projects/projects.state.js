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
            url: '/projects/{projectId}',
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
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/projects/project-edit.html',
                    controller: 'ProjectEditController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                name: null, membersCount: null, description: null, id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('projects', null, { reload: true });
                }, function() {
                    $state.go('projects');
                });
            }]
        })
        .state('projects.edit', {
            parent: 'projects',
            url: '/{projectId}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/projects/project-edit.html',
                    controller: 'ProjectEditController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Project', function(Project) {
                            return Project.get({projectId : $stateParams.projectId});
                        }]
                    }
                }).result.then(function() {
                    $state.go('projects', null, { reload: true });
                }, function() {
                    $state.go('^');
                });
            }]
        })
            .state('projects.newUser', {
                parent: 'projects',
                url: '{projectId}/adduser',
                data: {
                    authorities: ['ROLE_USER']
                },
                onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                    $uibModal.open({
                        templateUrl: 'app/entities/projects/project-add-user-dialog.html',
                        controller: 'ProjectAddUserDialogController',
                        controllerAs: 'vm',
                        backdrop: 'static',
                        size: 'lg',
                        resolve: {
                            entity: ['Project', function(Project) {
                                return Project.get({projectId : $stateParams.projectId});
                            }]
                        }
                    }).result.then(function() {
                        $state.go('projects.edit', null, { reload: true });
                    }, function() {
                        $state.go('projects.edit');
                    });
                }]
            });
        ;
    }
})();

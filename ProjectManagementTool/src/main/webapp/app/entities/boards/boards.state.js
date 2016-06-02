(function() {
    'use strict';

    angular
        .module('projectManagementToolApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider.state('board-edit', {
            parent: 'project-detail',
            url: '/boards/:boardId/edit',
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/boards/board-edit.html',
                    controller: 'BoardEditController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: [ 'Board', function(Board) {
                            return Board.get({boardId : $stateParams.boardId});
                        }]
                    }
                }).result.then(function() {
                    $state.go('project-detail', null, { reload: true });
                }, function() {
                    $state.go('project-detail');
                });
            }]
        }).state('board-new', {
            parent: 'project-detail',
            url: '/boards/new',
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/boards/board-edit.html',
                    controller: 'BoardEditController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return { 
                            	id: null, 
                            	name: null,
                            	projectId: $stateParams.projectId
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('project-detail', null, { reload: true });
                }, function() {
                    $state.go('project-detail');
                });
            }]
        }).state('board-detail', {
            parent: 'project-detail',
            url: '/boards/:boardId',
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

(function() {
    'use strict';

    angular
        .module('projectManagementToolApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider.state('board-detail.task-new', {
            parent: 'board-detail',
            url: '/tasks/new',
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/tasks/task-edit.html',
                    controller: 'TaskEditController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return { id: null, name: null };
                        }
                    }
                }).result.then(function() {
                    $state.go('board-detail', null, { reload: true });
                }, function() {
                    $state.go('board-detail');
                });
            }]
        }).state('board-detail.task-edit', {
            parent: 'board-detail',
            url: '/tasks/:taskId/edit',
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/tasks/task-edit.html',
                    controller: 'TaskEditController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: [ 'Task', function(Task) {
                            return Task.get({taskId: $stateParams.taskId});
                        }]
                    }
                }).result.then(function() {
                    $state.go('board-detail', null, { reload: true });
                }, function() {
                    $state.go('board-detail');
                });
            }]
        });
    }
})();

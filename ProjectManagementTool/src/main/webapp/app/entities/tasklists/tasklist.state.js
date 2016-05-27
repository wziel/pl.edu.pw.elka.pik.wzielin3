(function() {
    'use strict';

    angular
        .module('projectManagementToolApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider.state('board-detail.list-new', {
            parent: 'board-detail',
            url: '/lists/new',
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/tasklists/tasklist-edit.html',
                    controller: 'TaskListEditController',
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
        }).state('board-detail.list-edit', {
            parent: 'board-detail',
            url: '/lists/{taskListId}/edit',
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/tasklists/tasklist-edit.html',
                    controller: 'TaskListEditController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: [ 'TaskList', function(TaskList) {
                            return TaskList.get({taskListId : $stateParams.taskListId});
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

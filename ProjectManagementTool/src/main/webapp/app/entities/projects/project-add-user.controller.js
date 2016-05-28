/**
 * Created by mmudel on 28.05.2016.
 */

(function() {
    'use strict';

    angular
        .module('projectManagementToolApp')
        .controller('ProjectAddUserDialogController', ProjectAddUserDialogController);

    ProjectAddUserDialogController.$inject = ['$stateParams', '$uibModalInstance', 'entity', 'ProjectMember', 'User', 'paginationConstants'];

    function ProjectAddUserDialogController ($stateParams, $uibModalInstance, entity, ProjectMember, User, paginationConstants) {
        var vm = this;

        vm.clear = clear;
        vm.addUser = addUser;
        vm.project = entity;
        vm.page = 1;
        vm.users = [];

        vm.loadAll();

        function loadAll () {
            User.query({page: vm.page - 1, size: paginationConstants.itemsPerPage}, function (result, headers) {
                vm.users = result;
            });
        }

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function onSaveSuccess (result) {
            vm.isSaving = false;
            $uibModalInstance.close(result);
        }

        function onSaveError () {
            vm.isSaving = false;
        }

        function addUser (login) {
            vm.isSaving = true;
            ProjectMember.save({name: vm.project.name, login: login});
        }
    }
})();

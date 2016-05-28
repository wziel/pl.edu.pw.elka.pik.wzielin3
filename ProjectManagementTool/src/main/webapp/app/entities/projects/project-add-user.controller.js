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
        vm.loadAll = loadAll;
        vm.userLogin = "";
        vm.invalidUser = false;
        vm.userAlreadyBelong = false;

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

        function addUser () {
            vm.isSaving = true;
            vm.invalidUser = false;
            vm.userAlreadyBelong = false;
            for(var i=0; i<vm.project.users.length; i++){
                if(vm.project.users[i] == vm.userLogin){
                    onSaveError();
                    vm.userAlreadyBelong = true;
                    return;
                }
            }
            for(var i =0; i<vm.users.length; i++){
                if(vm.users[i].login == vm.userLogin) {
                    ProjectMember.save({projectId: vm.project.id, login: vm.userLogin}, onSaveSuccess, onSaveError);
                    return;
                }
            }
            onSaveError();
            vm.invalidUser = true;
        }
    }
})();

/**
 * Created by mmudel on 29.04.2016.
 */

(function() {
    'use strict';

    angular
        .module('projectManagementToolApp')
        .controller('TaskListEditController', TaskListEditController);

    TaskListEditController.$inject = ['$stateParams', '$uibModalInstance', 'entity', 'TaskList'];

    function TaskListEditController ($stateParams, $uibModalInstance, entity, TaskList) {
        var vm = this;
        vm.clear = clear;
        vm.save = save;
        vm.taskList = entity;
        vm.save = save;

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

        function save () {
            vm.isSaving = true;
            if(vm.taskList.id) {
                TaskList.update(vm.taskList, onSaveSuccess, onSaveError);
            }
            else {
            	TaskList.save(vm.taskList, onSaveSuccess, onSaveError);	
            }
        }
    }
})();

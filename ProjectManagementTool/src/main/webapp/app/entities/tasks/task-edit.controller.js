/**
 * Created by mmudel on 29.04.2016.
 */

(function() {
    'use strict';

    angular
        .module('projectManagementToolApp')
        .controller('TaskEditController', TaskEditController);

    TaskEditController.$inject = ['$stateParams', '$uibModalInstance', 'entity', 'Task'];

    function TaskEditController($stateParams, $uibModalInstance, entity, Task) {
        var vm = this;
        vm.clear = clear;
        vm.save = save;
        vm.task = entity;
        vm.save = save;
        vm.isEdit = isEdit();

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
            if(isEdit()) {
                Task.update(vm.task, onSaveSuccess, onSaveError);
            }
            else {
            	Task.save(vm.task, onSaveSuccess, onSaveError);	
            }
        }
        
        function isEdit() {
        	return vm.task.id !== null;
        }
    }
})();

/**
 * Created by mmudel on 29.04.2016.
 */

(function() {
    'use strict';

    angular
        .module('projectManagementToolApp')
        .controller('ProjectEditController', ProjectEditController);

    ProjectEditController.$inject = ['$stateParams', '$uibModalInstance', 'entity', 'Project'];

    function ProjectEditController ($stateParams, $uibModalInstance, entity, Project) {
        var vm = this;

        vm.clear = clear;
        vm.languages = null;
        vm.save = save;
        vm.project = entity;
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
            	Project.update(vm.project, onSaveSuccess, onSaveError);
            }
            else {
            	Project.save(vm.project, onSaveSuccess, onSaveError);	
            }
        }
        
        function isEdit() {
        	return vm.project.id !== null;
        }
    }
})();

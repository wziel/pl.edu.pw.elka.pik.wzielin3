/**
 * Created by mmudel on 29.04.2016.
 */

(function() {
    'use strict';

    angular
        .module('projectManagementToolApp')
        .controller('ProjectsNewProjectDialogController', ProjectsNewProjectDialogController);

    ProjectsNewProjectDialogController.$inject = ['$stateParams', '$uibModalInstance', 'entity', 'Project'];

    function ProjectsNewProjectDialogController ($stateParams, $uibModalInstance, entity, Project) {
        var vm = this;

        vm.clear = clear;
        vm.languages = null;
        vm.save = save;
        vm.project = entity;



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
            Project.save(vm.project, onSaveSuccess, onSaveError);
        }
    }
})();

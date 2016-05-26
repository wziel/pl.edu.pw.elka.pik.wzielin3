/**
 * Created by mmudel on 26.05.2016.
 */
(function() {
    'use strict';

    angular
        .module('projectManagementToolApp')
        .controller('ProjectModifyProjectDialogController', ProjectModifyProjectDialogController);

    ProjectModifyProjectDialogController.$inject = ['$stateParams', '$uibModalInstance', 'entity', 'Project'];

    function ProjectModifyProjectDialogController ($stateParams, $uibModalInstance, entity, Project) {
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
            Project.update(vm.project, onSaveSuccess, onSaveError);
        }
    }
})();

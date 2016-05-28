/**
 * Created by mmudel on 29.04.2016.
 */

(function() {
    'use strict';

    angular
        .module('projectManagementToolApp')
        .controller('BoardEditController', BoardEditController);

    BoardEditController.$inject = ['$stateParams', '$uibModalInstance', 'entity', 'Board'];

    function BoardEditController ($stateParams, $uibModalInstance, entity, Board) {
        var vm = this;
        vm.clear = clear;
        vm.save = save;
        vm.board = entity;
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
                Board.update(vm.board, onSaveSuccess, onSaveError);
            }
            else {
            	Board.save(vm.board, onSaveSuccess, onSaveError);	
            }
        }
        
        function isEdit() {
        	return vm.board.id !== null;
        }
    }
})();

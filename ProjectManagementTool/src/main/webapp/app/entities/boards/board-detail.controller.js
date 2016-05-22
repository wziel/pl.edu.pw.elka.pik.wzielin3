(function() {
    'use strict';

    angular
        .module('projectManagementToolApp')
        .controller('BoardController', ProjectController);

    ProjectController.$inject = ['$stateParams', 'Board'];

    function ProjectController ($stateParams, Board) {
        var vm = this;

        vm.load = load;
        vm.board = {};
        vm.projectName = $stateParams.name;

        vm.load($stateParams.id);

        function load (boardId) {
        	Board.get({id: boardId}, function(result) {
        		vm.board = result;
        	})
        }
    }
})();

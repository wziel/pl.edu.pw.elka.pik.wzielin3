(function() {
    'use strict';

    angular
        .module('projectManagementToolApp')
        .controller('BoardController', BoardController);

    BoardController.$inject = ['$stateParams', 'Board'];

    function BoardController ($stateParams, Board) {
        var vm = this;

        vm.load = load;
        vm.board = {};
        vm.projectName = $stateParams.name;

        vm.load($stateParams.boardId);

        function load (boardId) {
        	Board.get({boardId: boardId}, function(result) {
        		vm.board = result;
        	})
        }
    }
})();

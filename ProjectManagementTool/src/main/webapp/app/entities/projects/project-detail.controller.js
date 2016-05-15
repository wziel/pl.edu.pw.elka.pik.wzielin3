/**
 * Created by mmudel on 23.04.2016.
 */
(function() {
    'use strict';

    angular
        .module('projectManagementToolApp')
        .controller('ProjectController', ProjectController);

    ProjectController.$inject = ['$stateParams', 'Project'];

    function ProjectController ($stateParams, Project) {
        var vm = this;

        vm.showUsers;
        vm.load = load;
        vm.project = {};

        vm.load($stateParams.name);

        function load (name) {
            Project.get({name: name}, function(result) {
                vm.project = result;
                //mock boards for now
                vm.project.boards = ["Test 1", "Test board 2", "Pretty awesome board"]
            });
        }
    }
})();

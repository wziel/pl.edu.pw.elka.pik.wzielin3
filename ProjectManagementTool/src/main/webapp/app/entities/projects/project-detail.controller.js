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

        vm.load($stateParams.projectId);

        function load (projectId) {
            Project.get({id: projectId}, function(result) {
                vm.project = result;
            });
        }
    }
})();

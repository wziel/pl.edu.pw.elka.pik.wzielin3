(function() {
    'use strict';

    angular
        .module('projectManagementToolApp')
        .controller('ProjectController', ProjectController);

    ProjectController.$inject = ['$state', '$stateParams', 'Project', 'ProjectMember'];

    function ProjectController ($state, $stateParams, Project, ProjectMember) {
        var vm = this;

        vm.showUsers;
        vm.load = load;
        vm.project = {};
        vm.deleteUser = deleteUser;

        vm.load($stateParams.projectId);

        function load (projectId) {
            Project.get({id: projectId}, function(result) {
                vm.project = result;
            });
        }

        function deleteUser(login) {
            ProjectMember.delete({projectId: vm.project.id, login: login},
            function(){
                $state.reload();
            });
        }
    }
})();

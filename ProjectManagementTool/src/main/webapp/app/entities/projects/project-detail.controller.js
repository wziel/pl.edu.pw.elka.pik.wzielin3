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
                //delete this after endpoint returns users with project
                vm.project.users = [{name:"Michał Mudel"},{name:"Joanna Raczyńska"}]
                //
            });
        }
    }
})();

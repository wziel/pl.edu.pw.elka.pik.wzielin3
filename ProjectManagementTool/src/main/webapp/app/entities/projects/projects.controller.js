(function() {
    'use strict';

    angular
        .module('projectManagementToolApp')
        .controller('ProjectsController', ProjectsController);

    ProjectsController.$inject = ['Project', 'paginationConstants'];

    function ProjectsController (Project, paginationConstants) {
        var vm = this;

        vm.loadAll = loadAll;
        vm.loadPage = loadPage;
        vm.page = 1;
        vm.projects = [];

        vm.loadAll();

        function loadAll () {
            Project.query({page: vm.page - 1, size: paginationConstants.itemsPerPage}, function (result, headers) {
                vm.projects = result;
            });
        }

        function loadPage (page) {
            vm.page = page;
            vm.loadAll();
        }
        
    }
})();

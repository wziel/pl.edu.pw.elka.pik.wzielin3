(function () {
    'use strict';

    angular
        .module('projectManagementToolApp')
        .factory('Register', Register);

    Register.$inject = ['$resource'];

    function Register ($resource) {
        return $resource('api/register', {}, {});
    }
})();

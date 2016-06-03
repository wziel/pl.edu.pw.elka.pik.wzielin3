(function() {
    'use strict';

    angular
        .module('projectManagementToolApp')
        .directive('usernotinproject', usernotinproject)
        .directive('userexists', userexists);

    function usernotinproject () {
    	return {
    		require: 'ngModel',
    		link: function(scope, elm, attrs, ctrl) {
    			var vm = scope.vm;
    			ctrl.$validators.usernotinproject = function(modelValue, viewValue) {
        			if(vm.project.users) {
        				for(var i = 0; i < vm.project.users.length; ++i) {
        					if(vm.project.users[i] == modelValue) {
        						return false;
        					}
        				}	
        			}
    				return true;
    			}
    		}
    	}
    }

    function userexists () {
    	return {
    		require: 'ngModel',
    		link: function(scope, elm, attrs, ctrl) {
    			var vm = scope.vm;
    			ctrl.$validators.userexists = function(modelValue, viewValue) {
    				var x = ctrl;
        			if(vm.project.users) {
	    			    for(var i = 0; i < vm.users.length; i++){
	    			        if(vm.users[i].login == modelValue) {
	    			        	return true;
	    			        }
	    			    }
	    			    return false;
        			}
    				return true;
    			}
    		}
    	}
    }
})();
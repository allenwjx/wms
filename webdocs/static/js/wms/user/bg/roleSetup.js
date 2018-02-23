var role_setup_vm;
$(document).ready(function () {
    debugger;
    role_setup_vm = new Vue({
        el: '#modalForm',
        data: {
            roles: [],
            model: {}
        },
        ready: function () {
            this.init();
        },
        methods: {
            init: function () {
                var self = this;
                $.ajax({
                    type: 'GET',
                    url: __ctx + "/combo/roles"
                }).done(function (resp) {
                    self.roles = resp;
                });

                $.ajax({
                    type: 'GET',
                    url: __ctx + "/user/bg/role",
                    data: {id: $("#id").val()}
                }).done(function (result) {
                    self.model = result;
                });
            },
            submitData: function () {
                var me = this.model;
                var method = "POST";
                var url = __ctx + "/user/bg/roleSetup";
                $.ajax({
                    type: method,
                    url: url,
                    data: JSON.stringify(me),
                    dataType: "json",
                    contentType: "application/json",
                    //成功返回之后调用的函数
                    success: function (data) {
                        if (data && data.success) {
                            $('#formModal').modal('hide');
                            toastr.success('操作成功', {timeOut: 1500, positionClass: "toast-top-center"});
                            userBg_vm.preQuery();
                        } else {
                            toastr.error(data.errorMessage, {timeOut: 1500, positionClass: "toast-top-center"});
                        }
                    },
                    error: function (err) {
                        toastr.error(err, {timeOut: 1500, positionClass: "toast-top-center"});
                    }
                });
            }
        }
    });

});
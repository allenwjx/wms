var role_edit_vm;
$(document).ready(function () {
    debugger;
    role_edit_vm = new Vue({
        el: '#modalForm',
        data: {
            applyTransferConfig: {
                infoText: "",
                preserveSelectionOnMove: 'moved',
                moveOnSelect: false
            },
            authorizations: [],
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
                    url: __ctx + "/combo/authorizations"
                }).done(function (resp) {
                    self.authorizations = resp;
                });
                $.ajax({
                    type: 'GET',
                    url: __ctx + "/basic/role/edit",
                    data: {id: $("#id").val()}
                }).done(function (result) {
                    self.model = result;
                });
            },
            submitData: function () {
                $("#modalForm").data('bootstrapValidator', null);
                this.validator();
                var me = this.model;
                var url = __ctx + "/basic/role/save";
                var method = "POST";
                if (this.model.id) {
                    method = "PUT";
                    url = __ctx + "/basic/role/update";
                }
                $("#modalForm").data('bootstrapValidator').validate();
                if ($("#modalForm").data('bootstrapValidator').isValid()) {
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
                                role_vm.preQuery();
                            } else {
                                toastr.error(data.errorMessage, {timeOut: 1500, positionClass: "toast-top-center"});
                            }
                        },
                        error: function (err) {
                            toastr.error(err, {timeOut: 1500, positionClass: "toast-top-center"});
                        }
                    });
                }
            },
            validator: function () {
                $('#modalForm').bootstrapValidator({
                    message: 'This value is not valid',
                    fields: {
                        name: {
                            validators: {
                                notEmpty: {
                                    message: '请输入角色名称'
                                }
                            }
                        }
                    }
                });
            }
            ,
        }
    });

});
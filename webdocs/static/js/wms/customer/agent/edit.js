var agent_edit_vm;
$(document).ready(function () {
    debugger;
    agent_edit_vm = new Vue({
        el: '#modalForm',
        data: {
            model: formData
        },
        ready: function () {
            this.init();
        },
        methods: {
            init: function () {
            },
            submitData: function () {
                $("#modalForm").data('bootstrapValidator', null);
                this.validator();
                var me = this.model;
                var url = __ctx + "/customer/agent/save";
                var method = "POST";
                if (this.model.id != 0) {
                    method = "PUT";
                    url = __ctx + "/customer/agent/update";
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
                                agent_vm.preQuery();
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
                                    message: '请输入代理商姓名'
                                }
                            }
                        },
                        mobile: {
                            validators: {
                                notEmpty: {
                                    message: '请输入代理商联系电话'
                                }
                            }
                        },
                        address: {
                            validators: {
                                notEmpty: {
                                    message: '请输入代理商联系地址'
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
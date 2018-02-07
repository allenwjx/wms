var manufacturer_edit_vm;
$(document).ready(function () {
    debugger;
    manufacturer_edit_vm = new Vue({
        el: '#modalForm',
        data: {
            model: formData,
            settleTypes: settleTypes,
            expresses: expresses
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
                var url = __ctx + "/customer/manufacturer/save";
                var method = "POST";
                if (this.model.id != 0) {
                    method = "PUT";
                    url = __ctx + "/customer/manufacturer/update";
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
                                manufacturer_vm.preQuery();
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
                                    message: '请输入厂商名称'
                                }
                            }
                        },
                        settleType: {
                            validators: {
                                notEmpty: {
                                    message: '请选择结算类型'
                                }
                            }
                        },
                        express: {
                            validators: {
                                notEmpty: {
                                    message: '请选择派件公司'
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
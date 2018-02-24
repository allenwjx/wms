/**
 * Created by hzy24985 on 2018/2/8.
 */

$(document).ready(function () {
    debugger;
    new Vue({
        el: '#modalForm',
        data: {
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
                    url: __ctx + "/qr/code/one",
                    data: {id: formData.id}
                }).done(function (result) {
                    self.model = result;
                });
                console.log ('- init model is ' + self.model);
            },

            // 提交数据
            submitData: function () {
                $("#modalForm").data('bootstrapValidator', null);
                this.validator();
                var me = this.model;
                var url = __ctx + "/qr/code/doBind";
                var method = "POST";
                $("#modalForm").data('bootstrapValidator').validate();
                if ($("#modalForm").data('bootstrapValidator').isValid()) {
                    $.ajax({
                        type: method,
                        url: url,
                        data: JSON.stringify (me),
                        dataType: "json",
                        contentType: "application/json",
                        //成功返回之后调用的函数
                        success: function (data) {
                            if (data && data.success) {
                                $('#formModal').modal('hide');
                                toastr.success('操作成功', {timeOut: 1500, positionClass: "toast-top-center"});
                                qrCode_vm.preQuery();
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

            // 校验数据
            validator: function () {
                $('#modalForm').bootstrapValidator ({
                    message: 'This value is not valid',
                    fields: {
                        commodityId: {
                            validators: {
                                notEmpty: {
                                    message: '请选择商品ID'
                                }
                            }
                        }
                    }
                });
            }
        }
    });

});
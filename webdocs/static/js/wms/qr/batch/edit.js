$(document).ready(function () {
    debugger;
    var qrCodeBatch_edit_vm = new Vue({
        el: '#modalForm',
        data: {
            commodities: [],
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
                    url: __ctx + "/combo/commodities"
                }).done(function (resp) {
                    self.commodities = resp;
                });
            },
            submitData: function () {
                $("#modalForm").data('bootstrapValidator', null);
                this.validator();
                var me = this.model;
                var url = __ctx + "/qr/batch/save";
                var method = "POST";
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
                                qrCodeBatch_vm.preQuery();
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
                        commodityId: {
                            validators: {
                                notEmpty: {
                                    message: '请选择商品'
                                }
                            }
                        },
                        amount: {
                            validators: {
                                notEmpty: {
                                    message: '请输入生成二维码数量'
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
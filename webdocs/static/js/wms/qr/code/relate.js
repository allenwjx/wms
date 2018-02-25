// TODO
var commodity_edit_vm;
$(document).ready(function () {
    debugger;
    commodity_edit_vm = new Vue({
        el: '#modalForm',
        data: {
            manufacturers: [],
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
                    url: __ctx + "/sale/commodity/edit",
                    data: {id: $("#id").val()}
                }).done(function (result) {
                    self.model = result;
                });
                $.ajax({
                    type: 'GET',
                    url: __ctx + "/combo/loadManus"
                }).done(function (resp) {
                    self.manufacturers = resp;
                });
            },
            submitData: function () {
                $("#modalForm").data('bootstrapValidator', null);
                this.validator();
                var me = this.model;
                var url = __ctx + "/sale/commodity/save";
                var method = "POST";
                if (this.model.id) {
                    method = "PUT";
                    url = __ctx + "/sale/commodity/update";
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
                                commidty_vm.preQuery();
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
                        manufacturerId: {
                            validators: {
                                notEmpty: {
                                    message: '请选择厂商'
                                }
                            }
                        },
                        name: {
                            validators: {
                                notEmpty: {
                                    message: '请输入商品名称'
                                }
                            }
                        },
                        price: {
                            validators: {
                                notEmpty: {
                                    message: '请输入商品单价，单位：元'
                                },
                                greaterThan: {
                                    value: 0,
                                    message: '该值只能包含数字'
                                }
                            }
                        },
                        unit: {
                            validators: {
                                notEmpty: {
                                    message: '请输入商品单位'
                                }
                            }
                        },
                        weight: {
                            validators: {
                                notEmpty: {
                                    message: '请输入商品重量，单位：KG'
                                },
                                greaterThan: {
                                    value: 0,
                                    message: '该值只能包含数字'
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
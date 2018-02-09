var auxiliary_edit_vm;
$(document).ready(function () {
    debugger;
    auxiliary_edit_vm = new Vue({
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
                    url: __ctx + "/sale/auxiliary/edit",
                    data: {id: $("#id").val()}
                }).done(function (result) {
                    self.model = result;
                });
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
                var url = __ctx + "/sale/auxiliary/save";
                var method = "POST";
                if (this.model.id) {
                    method = "PUT";
                    url = __ctx + "/sale/auxiliary/update";
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
                                auxiliary_vm.preQuery();
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
                                    message: '请选择归属商品'
                                }
                            }
                        },
                        name: {
                            validators: {
                                notEmpty: {
                                    message: '请输入辅材名称'
                                }
                            }
                        },
                        price: {
                            validators: {
                                notEmpty: {
                                    message: '请输入辅材单价，单位：元'
                                },
                                greaterThan: {
                                    value: 0,
                                    message: '该值只能包含数字'
                                }
                            }
                        },
                        quantity: {
                            validators: {
                                notEmpty: {
                                    message: '请输入满足多少件商品收取辅材费用'
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
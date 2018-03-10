var commodity_edit_vm;
$(document).ready(function () {
    debugger;
    commodity_edit_vm = new Vue({
        el: '#modalForm',
        data: {
            provinces: [],
            model: {},
            expresses: freight_vm.expresses
        },
        ready: function () {
            this.init();
        },
        methods: {
            init: function () {
                var self = this;
                $.ajax({
                    type: 'GET',
                    url: __ctx + "/sale/freight/edit",
                    data: {id: $("#id").val()}
                }).done(function (result) {
                    self.model = result;
                });
                $.ajax({
                    type: 'GET',
                    url: __ctx + "/combo/provinces"
                }).done(function (resp) {
                    self.provinces = resp;
                });
            },
            submitData: function () {
                $("#modalForm").data('bootstrapValidator', null);
                this.validator();
                var me = this.model;
                var url = __ctx + "/sale/freight/save";
                var method = "POST";
                if (this.model.id) {
                    method = "PUT";
                    url = __ctx + "/sale/freight/update";
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
                                freight_vm.preQuery();
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
                        provinceCode: {
                            validators: {
                                notEmpty: {
                                    message: '请选择省份'
                                }
                            }
                        },
                        firstWeight: {
                            validators: {
                                notEmpty: {
                                    message: '请输入首重'
                                },
                                greaterThan: {
                                    value: 0,
                                    message: '该值只能包含数字'
                                }
                            }
                        },
                        firstOriginalPrice: {
                            validators: {
                                notEmpty: {
                                    message: '请输入原始首重价格，单位：元'
                                },
                                greaterThan: {
                                    value: 0,
                                    message: '该值只能包含数字'
                                }
                            }
                        },
                        additionalOriginalPrice: {
                            validators: {
                                notEmpty: {
                                    message: '请输入原始续重价格，单位：元'
                                },
                                greaterThan: {
                                    value: 0,
                                    message: '该值只能包含数字'
                                }
                            }
                        },
                        firstCostPrice: {
                            validators: {
                                notEmpty: {
                                    message: '请输入成本首重价格，单位：元'
                                },
                                greaterThan: {
                                    value: 0,
                                    message: '该值只能包含数字'
                                }
                            }
                        },
                        additionalCostPrice: {
                            validators: {
                                notEmpty: {
                                    message: '请输入成本续重价格，单位：元'
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

        }
    });

});
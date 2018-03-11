var userFront_vm;
$(document).ready(function () {
    userFront_vm = new Vue({
        el: '#user-front-root',
        data: {
            pageSize: 10,
            pageResult: {
                data: [],
                paginator: {
                    currentPage: 0,
                    totalCount: 0,
                    totalPage: 0
                }
            },
            queryForm: {},
            userTypes: [],
            manus:[],
            agents:[],
            pwdUser: {},
            linkVo: {},
            formData: {type: '', newPassword: '', confirmPassword: '', agentCode: '', manuCode: ''},
            discounts:[],
            expresses: [],
            discountForm: {userId: 0, expressCode: "", discount: 1.0}
        },
        ready: function () {
            this.init();
            this.queryForm = {};
            this.query();
        },
        methods: {
            /**
             * 初始化combo数据。
             */
            init: function () {
                var self = this;
                $.ajax({
                    type: 'GET',
                    url: __ctx + "/combo/fromBizEnum",
                    data: {className: 'UserLinkTypeEnum'}
                }).done(function (resp) {
                    self.userTypes = resp;
                });


                $.ajax({
                    type: 'GET',
                    url: __ctx + "/combo/express"
                }).done(function (resp) {
                    self.expresses = resp;
                });
            },
            /**
             * 重置分页数据，然后执行列表查询
             */
            preQuery: function () {
                $.resetCurrentPage(this.pageResult.paginator);
                this.query();
            },
            /**
             * 执行分页查询
             */
            query: function () {
                var self = this;
                var pageInfo = {
                    "currentPage": self.pageResult.paginator.currentPage,
                    "pageSize": self.pageSize,
                    "totalCount": self.pageResult.paginator.totalCount
                };
                $.ajax({
                    type: 'GET',
                    url: __ctx + "/user/front/list",
                    data: $.extend(self.queryForm, pageInfo)
                }).done(function (result) {
                    self.pageResult = result;
                });
            },
            /**
             * 重置查询表单
             */
            reset: function () {
                this.queryForm = {};
            },

            /**
             * 打开修改密码的model
             * @param item user一条记录
             */
            openPwdModel: function (item) {
                this.pwdUser = item;
                $("#pwdModal").modal({
                    show: true,
                    backdrop: 'static'
                });
            },

            /**
             * 打开修改用户折扣信息的model
             * @param item user的一条记录
             */
            openDiscountModel: function (item) {
                var self = this;
                self.typeUser = item;
                self.queryDiscount(self.typeUser.id);

            },
            queryDiscount: function (userId) {
                var self = this;
                self.discountForm.userId = userId;
                $.ajax({
                    type: 'GET',
                    url: __ctx + "/user/front/discounts",
                    data: {userId: userId}
                }).done(function (result) {
                    if (!result.success) {
                        toastr.error(result.errorMessage, '', {
                            timeOut: 3000,
                            positionClass: "toast-top-center"
                        });
                        return false;
                    }
                    //初识化界面
                    self.discounts = result.data;

                    //弹框
                    $("#discountModal").modal({
                        show: true,
                        backdrop: 'static'
                    });
                });
            },
            /**
             * 修改密码
             * @returns {boolean}
             */
            changePassword: function () {
                var self = this;
                if ($.trim(self.formData.newPassword).length <= 0 || $.trim(self.formData.newPassword).length > 20) {
                    toastr.error("用户登录密码不能为空,且不能超出规定长度", '', {
                        timeOut: 3000,
                        positionClass: "toast-top-center"
                    });
                    return false;
                }
                if (self.formData.newPassword !== self.formData.confirmPassword) {
                    toastr.error("确认密码与输入的密码不一致", '', {
                        timeOut: 3000,
                        positionClass: "toast-top-center"
                    });
                    return false;
                }
                $.ajax({
                    type: 'POST',
                    url: __ctx + '/user/front/pwd',
                    data: {id: self.pwdUser.id, password: self.formData.newPassword, confirmPassword: self.formData.confirmPassword}
                }).done(function (resp) {
                    if (resp.success) {
                        toastr.success('操作成功', "", {
                            timeOut: 3000,
                            positionClass: "toast-top-center"
                        });
                        $('#pwdModal').modal('hide');
                    } else {
                        toastr.error(resp.errorMessage, resp.errorCode, {
                            timeOut: 3000,
                            positionClass: "toast-top-center"
                        });
                    }
                });
            },

            addDiscount: function () {
                var self = this;
                if (!self.discountForm.expressCode) {
                    toastr.error("参数校验", "请选择快递公司", {timeOut: 1500, positionClass: "toast-top-center"});
                    return;
                }
                $("#discountModalForm").data('bootstrapValidator', null);
                this.validatorDiscount();

                $("#discountModalForm").data('bootstrapValidator').validate();
                if ($("#discountModalForm").data('bootstrapValidator').isValid()) {
                    $.ajax({
                        type: 'POST',
                        url: __ctx + "/user/front/addDiscounts",
                        data: self.discountForm
                    }).done(function (data) {
                        if (data.success) {
                            toastr.success('操作成功', {timeOut: 1500, positionClass: "toast-top-center"});
                            self.queryDiscount(self.discountForm.userId);
                        } else {
                            toastr.error(data.errorMessage, {timeOut: 1500, positionClass: "toast-top-center"});
                        }
                    });
                }
            },
            delDiscount: function (item) {
                var self = this;
                $.ajax({
                    type: 'POST',
                    url: __ctx + "/user/front/delDis",
                    data: {id: item.id}
                }).done(function (data) {
                    if (data.success) {
                        toastr.success('操作成功', {timeOut: 1500, positionClass: "toast-top-center"});
                        self.queryDiscount(self.discountForm.userId);
                    } else {
                        toastr.error(data.errorMessage, {timeOut: 1500, positionClass: "toast-top-center"});
                    }
                });
            },
            validatorDiscount: function() {
                $('#discountModalForm').bootstrapValidator({
                    message: 'This value is not valid',
                    fields: {
                        expressCode: {
                            validators: {
                                notEmpty: {
                                    message: '请选择快递公司'
                                }
                            }
                        },
                        discount: {
                            validators: {
                                numeric: {
                                    message: '请输入数字'
                                },
                                notEmpty: {
                                    message: '请输入折扣'
                                }
                            }
                        }
                    }
                });
            }
        }
    });

    $("#pwdModal").on("hidden.bs.modal", function () {
        $(this).removeData("bs.modal");
    });
    $("#discountModal").on("hidden.bs.modal", function () {
        $(this).removeData("bs.modal");
    });
});
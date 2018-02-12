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
            typeUser: {},
            linkVo: {},
            formData: {type: '', newPassword: '', confirmPassword: '', agentCode: '', manuCode: ''}
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
                    url: __ctx + "/combo/allManus"
                }).done(function (resp) {
                    self.manus = resp;
                });

                $.ajax({
                    type: 'GET',
                    url: __ctx + "/combo/allAgents"
                }).done(function (resp) {
                    self.agents = resp;
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
             * 打款修改用户类型model
             * @param item user的一条记录
             */
            openTypeModel: function (item) {
                var self = this;
                self.typeUser = item;
                $.ajax({
                    type: 'GET',
                    url: __ctx + "/user/front/link",
                    data: {id: self.typeUser.id}
                }).done(function (result) {
                    if (!result.success) {
                        toastr.error(result.errorMessage, '', {
                            timeOut: 3000,
                            positionClass: "toast-top-center"
                        });
                        return false;
                    }
                    //初识化界面
                    self.linkVo = result.data;
                    self.formData.type = self.typeUser.type;
                    if (self.formData.type == 'A') {
                        self.formData.agentCode = self.linkVo.code;
                    } else if (self.formData.type == 'B') {
                        self.formData.manuCode = self.linkVo.code;
                    }
                    //弹框
                    $("#typeModal").modal({
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
            /**
             * 修改用户类型.
             */
            changeType: function () {
                var self = this;
                var code = '';
                if (self.formData.type === 'A') {
                    code = self.formData.agentCode;
                } else if (self.formData.type === 'B'){
                    code = self.formData.manuCode;
                }

                $.ajax({
                    type: 'POST',
                    url: __ctx + '/user/front/type',
                    data: {userId: self.typeUser.id, type: self.formData.type, code: code}
                }).done(function (resp) {
                    if (resp.success) {
                        toastr.success('操作成功', "", {
                            timeOut: 3000,
                            positionClass: "toast-top-center"
                        });
                        $('#typeModal').modal('hide');
                    } else {
                        toastr.error(resp.message, resp.errorCode, {
                            timeOut: 3000,
                            positionClass: "toast-top-center"
                        });
                    }
                });
            },
            delete: function (id, enabled) {
                var self = this;
                var p = "";
                var _enabled = 0;
                if (enabled == 0) {
                    p = "启用";
                    _enabled = 1;
                } else {
                    p = "禁用";
                    _enabled = 0;
                }
                alertify.confirm("确定" + p + "该记录么？", function (result) {
                    if (result) {
                        $.ajax({
                            type: 'POST',
                            url: __ctx + "/user/bg/state/" + id + "/" + _enabled
                        }).done(function (data) {
                            if (data.success) {
                                toastr.success('操作成功', {timeOut: 1500, positionClass: "toast-top-center"});
                                self.preQuery();
                            } else {
                                toastr.error(data.errorMessage, {timeOut: 1500, positionClass: "toast-top-center"});
                            }
                        });
                    }
                });
            }
        }
    });

    $("#pwdModal").on("hidden.bs.modal", function () {
        $(this).removeData("bs.modal");
    });
    $("#typeModal").on("hidden.bs.modal", function () {
        $(this).removeData("bs.modal");
    });
});
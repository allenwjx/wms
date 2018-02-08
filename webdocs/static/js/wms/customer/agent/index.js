var agent_vm;
$(document).ready(function () {
    agent_vm = new Vue({
        el: '#agent',
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
            states: states
        },
        ready: function () {
            this.init();
            this.queryForm = {};
            this.query();
        },
        methods: {
            init: function () {
            },
            preQuery: function () {
                $.resetCurrentPage(this.pageResult.paginator);
                this.query();
            },
            query: function () {
                var self = this;
                var pageInfo = {
                    "currentPage": self.pageResult.paginator.currentPage,
                    "pageSize": self.pageSize,
                    "totalCount": self.pageResult.paginator.totalCount
                };
                $.ajax({
                    type: 'GET',
                    url: __ctx + "/customer/agent/list",
                    data: $.extend(self.queryForm, pageInfo)
                }).done(function (result) {
                    self.pageResult = result;
                });
            },
            reset: function () {
                this.queryForm = {};
            },
            create: function () {
                $("#formModal").modal({
                    show: true,
                    remote: __ctx + "/customer/agent/edit",
                    backdrop: 'static'
                });
            },
            edit: function (id) {
                $("#formModal").modal({
                    show: true,
                    remote: __ctx + "/customer/agent/edit?id=" + id,
                    backdrop: 'static'
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
                            url: __ctx + "/customer/agent/state/" + id + "/" + _enabled
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

    $("#formModal").on("hidden.bs.modal", function () {
        $(this).removeData("bs.modal");
    });
});
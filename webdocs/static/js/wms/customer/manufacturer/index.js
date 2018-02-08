var manufacturer_vm;
$(document).ready(function () {
    manufacturer_vm = new Vue({
        el: '#manufacturer',
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
            settleTypes: settleTypes,
            expresses: expresses
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
                    url: __ctx + "/customer/manufacturer/list",
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
                    remote: __ctx + "/customer/manufacturer/edit",
                    backdrop: 'static'
                });
            },
            edit: function (id) {
                $("#formModal").modal({
                    show: true,
                    remote: __ctx + "/customer/manufacturer/edit?id=" + id,
                    backdrop: 'static'
                });
            },
            delete: function (id) {
                var self = this;
                alertify.confirm("该操作不可恢复，确定删除该记录么？", function (result) {
                    if (result) {
                        $.ajax({
                            type: 'GET',
                            url: __ctx + "/customer/manufacturer/delete/" + id
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
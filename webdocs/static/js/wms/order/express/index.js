var express_vm;
$(document).ready(function () {
    express_vm = new Vue({
        el: '#express-root',
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
            settleTypes: [],
            expresses: []
        },
        ready: function () {
            this.init();
            this.queryForm = {};
            this.query();
        },
        methods: {
            init: function () {
                var vm = this;
            },
            preQuery: function () {
                $.resetCurrentPage(this.pageResult.paginator);
                this.query();
            },
            /** 查询方法 */
            query: function () {
                var self = this;
                var pageInfo = {
                    "currentPage": self.pageResult.paginator.currentPage,
                    "pageSize": self.pageSize,
                    "totalCount": self.pageResult.paginator.totalCount
                };
                $.ajax({
                    type: 'GET',
                    url: __ctx + "/order/express/list",
                    data: $.extend(self.queryForm, pageInfo)
                }).done(function (resp) {
                    self.pageResult = resp;
                });
            },
            reset: function () {
                this.queryForm = {};
            }
        }
    });

    $("#formModal").on("hidden.bs.modal", function () {
        $(this).removeData("bs.modal");
    });

    $('.date-picker').datepicker({
        format : 'yyyy-mm-dd',
        language : "zh-CN",
        orientation : "top 0",
        autoclose : true
    });
});
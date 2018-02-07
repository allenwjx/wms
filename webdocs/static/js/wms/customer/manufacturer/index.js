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
                    url: __ctx + "/customer/manufacturer/list",
                    data: $.extend(self.queryForm, pageInfo)
                }).done(function (response) {
                    self.pageResult = response;
                });
            },
            reset: function () {
                this.queryForm = {};
            },
            // create: function () {
            //     $("#formModal").modal({
            //         show: true,
            //         remote: __ctx + "/airlinefilterrule/edit",
            //         backdrop: 'static'
            //     });
            // },
            // edit: function (id) {
            //     $("#formModal").modal({
            //         show: true,
            //         remote: __ctx + "/airlinefilterrule/edit?id=" + id,
            //         backdrop: 'static'
            //     });
            // }
        }
    });

    $("#formModal").on("hidden.bs.modal", function () {
        $(this).removeData("bs.modal");
    });
});
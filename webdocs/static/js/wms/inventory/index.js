var inventory_vm;
$(document).ready(function () {
    inventory_vm = new Vue({
        el: '#inventory-root',
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
            commodities: [],
            impCommodityId: 0
        },
        ready: function () {
            this.init();
            this.queryForm = {};
            this.query();
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
                    url: __ctx + "/inventory/list",
                    data: $.extend(self.queryForm, pageInfo)
                }).done(function (result) {
                    self.pageResult = result;
                });
            },
            reset: function () {
                this.queryForm = {};
            },

            importModal: function () {
                $("#importModal").modal({
                    show: true,
                    remote: __ctx + "/page/inventory/import",
                    backdrop: 'static'
                });
            }
        }
    });

    $("#importModal").on("hidden.bs.modal", function () {
        $(this).removeData("bs.modal");
    });


});
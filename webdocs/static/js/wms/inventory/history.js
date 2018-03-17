var inventory_history_vm;
debugger;
$(document).ready(function () {
    inventory_history_vm = new Vue({
        el: '#inventory-history-root',
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
                    "page": self.pageResult.paginator.currentPage,
                    "pageSize": self.pageSize,
                    "totalCount": self.pageResult.paginator.totalCount,
                    mobile: $("#historyMobile").val(),
                    commodityId: $("#historyCommodityId").val()
                };
                $.ajax({
                    type: 'GET',
                    url: __ctx + "/inventory/history",
                    data: $.extend(self.queryForm, pageInfo)
                }).done(function (result) {
                    self.pageResult = result;
                });
            },
            reset: function () {
                this.queryForm = {};
            }
        }
    });
});
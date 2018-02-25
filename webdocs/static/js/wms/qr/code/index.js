var qrCode_vm;
$(document).ready(function () {
    debugger;
    qrCode_vm = new Vue({
        el: '#qrCode',
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
            states: []
        },
        ready: function () {
            this.init();
            this.preQuery();
        },
        methods: {
            init: function () {
                var self = this;
                self.queryForm.batchId = $("#batchId").val();
                $.ajax({
                    type: 'GET',
                    url: __ctx + "/combo/commodities"
                }).done(function (resp) {
                    self.commodities = resp;
                });

                $.ajax({
                    type: 'GET',
                    url: __ctx + "/combo/fromBizEnum",
                    data: {className: 'StateEnum'}
                }).done(function (resp) {
                    if (resp.length > 0) {
                        for (var i = 0; i < resp.length; i++) {
                            if (resp[i].value == 0) {
                                resp[i].text = "未关联";
                            } else {
                                resp[i].text = "已关联";
                            }
                        }
                    }
                    self.states = resp;
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
                    url: __ctx + "/qr/code/list",
                    data: $.extend(self.queryForm, pageInfo)
                }).done(function (result) {
                    self.pageResult = result;
                });
            },
            reset: function () {
                this.queryForm = {};
            },
            details: function (serialNo) {
                $("#formModal").modal({
                    show: true,
                    remote: __ctx + "/page/qr/code/details?serialNo=" + serialNo,
                    backdrop: 'static'
                });
            },
            viewCode: function(id) {
                $("#formModal").modal({
                    show: true,
                    remote: __ctx + "/page/qr/code/viewCode?id=" + id,
                    backdrop: 'static'
                });
            }

        }
    });
});
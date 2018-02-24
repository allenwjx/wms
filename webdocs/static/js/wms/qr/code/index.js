var qrCode_vm;
$(document).ready(function () {
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
            states: []
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
                    url: __ctx + "/combo/fromBizEnum",
                    data: {className: 'StateEnum'}
                }).done(function (resp) {
                    if (resp.length > 0) {
                        for (var i = 0; i < resp.length; i++) {
                            if (resp[i].value == 0) {
                                resp[i].text = "未使用";
                            } else {
                                resp[i].text = "已使用";
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
            bind: function (id) {
                $("#formModal").modal({
                    show: true,
                    remote: __ctx + "/qr/code/bind?id=" + id,
                    backdrop: 'static'
                });
            }
        }
    });
});
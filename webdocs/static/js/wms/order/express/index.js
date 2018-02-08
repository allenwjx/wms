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
            expressTypes: [],
            agents: []
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
                    data: {className:'ExpressTypeEnum'}
                }).done(function (resp) {
                    self.expressTypes = resp;
                });

                $.ajax({
                    type: 'GET',
                    url: __ctx + "/combo/allAgentsAndManus"
                }).done(function (resp) {
                    self.agents = resp;
                });
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
                }).done(function (result) {
                    self.pageResult = result;
                });
            },
            reset: function () {
                this.queryForm = {};
            },
            edit: function (id) {
                $("#formModal").modal({
                    show: true,
                    remote: __ctx + "/page/order/express/view?id=" + id,
                    backdrop: 'static'
                });
            },
            /** 导出Excel */
            exportExcel : function(){
                var start= this.queryForm.fromDate;
                var end = this.queryForm.toDate;
                if (start == '' || end == ''){
                    toastr.error("请选择日期范围");
                    return
                }
                var des = new Date(end) - new Date(start);
                if (des/86400000 > 60){
                    toastr.error("日期范围大于60天，请缩小时间范围");
                    return
                }
                window.location.href = __ctx + "/order/express/export?" + $.param(this.queryForm);
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
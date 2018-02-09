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
            },
            importModal: function () {
                $("#importModal").modal({
                    show: true,
                    backdrop: 'static'
                });
            }
        }
    });

    $("#formModal").on("hidden.bs.modal", function () {
        $(this).removeData("bs.modal");
    });

    $("#angentFile").fileinput({
        language: 'zh', // 设置语言
        uploadUrl: __ctx + "/customer/agent/import", // 上传的地址
        allowedFileExtensions : ['xls', 'xlsx'],// 接收的文件后缀
        showUpload: true, // 是否显示上传按钮
        showCaption: true,// 是否显示标题
        browseClass: "btn green-meadow", // 按钮样式
        dropZoneEnabled: false,// 是否显示拖拽区域
        maxFileCount: 1,
        showUploadedThumbs:false,
        autoReplace : true,
        showPreview:false
    });

    $("#angentFile").on("fileuploaded",function(event, data, previewId, index){
        if(data.response.result){
            toastr.success(data.response, '', {
                timeOut: 3000,
                positionClass: "toast-top-center"
            });
        }
        //上传完成后清空文件选择框
        setTimeout(function(){
            $('#angentFile').fileinput('clear');
        }, 0);
        $("#importModal").modal("hide");
        agent_vm.preQuery();
    });

    $("#angentFile").on("fileerror",function(event, data, msg){
        console.log("fileerror");
    });
});
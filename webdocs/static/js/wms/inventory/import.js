var importVue;

$(document).ready(function () {
    debugger;
    var fileUpload;
    importVue = new Vue({
        el: '#inventory-import-root',
        data: {
            commodities: inventory_vm.commodities,
            impCommodityId: 0
        },
        watch: {
            impCommodityId: function (newValue, oldValue) {
                $("#inventoryFile").fileinput('url', __ctx + "/inventory/import/" + newValue);
            }
        },
        ready: function () {
            this.init();
        },
        methods: {
            init: function () {
            }
        }
    });

    fileUpload = $("#inventoryFile").fileinput({
        language: 'zh', // 设置语言
        uploadUrl: __ctx + "/inventory/import", // 上传的地址
        allowedFileExtensions : ['xls', 'xlsx'],// 接收的文件后缀
        showUpload: true, // 是否显示上传按钮
        showCaption: true,// 是否显示标题
        browseClass: "btn green-meadow", // 按钮样式
        dropZoneEnabled: false,// 是否显示拖拽区域
        maxFileCount: 1,
        showUploadedThumbs:false,
        autoReplace : true,
        showPreview:false,
        uploadExtraData: function (previewId, index) {
            var data = {commodityId: importVue.impCommodityId};
            return data;
        }
    });

    $("#inventoryFile").on("fileuploadsubmit",function(event, data, previewId, index){
        console.log(data);
    });

    $("#inventoryFile").on("fileuploaded",function(event, data, previewId, index){
        if(data.response.result){
            toastr.success(data.response, '', {
                timeOut: 3000,
                positionClass: "toast-top-center"
            });
        }
        //上传完成后清空文件选择框
        setTimeout(function(){
            $('#inventoryFile').fileinput('clear');
        }, 0);
        $("#importModal").modal("hide");
        inventory_vm.preQuery();
    });

    $("#inventoryFile").on("fileerror",function(event, data, msg){
        console.log("fileerror");
    });

});
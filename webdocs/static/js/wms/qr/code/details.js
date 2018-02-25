$(document).ready(function () {
    debugger;
    new Vue({
        el: '#shipRecordDetails',
        data: {
            model: {}
        },
        ready: function () {
            this.init();
        },
        methods: {
            init: function () {
                var self = this;
                $.ajax({
                    type: 'GET',
                    url: __ctx + "/api/qrcode?action=view",
                    data: {serialNo: $('#serialNo').val(), commodityId: 0}
                }).done(function (result) {
                    self.model = result;
                });
            }
        }
    });

});
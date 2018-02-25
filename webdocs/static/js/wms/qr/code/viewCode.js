$(document).ready(function () {
    debugger;
    var view_code_vm = new Vue({
        el: '#viewCode',
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
                    url: __ctx + "/qr/code/viewCode",
                    data: {id: $("#id").val()}
                }).done(function (result) {
                    self.model = result;
                });
            }
        }
    });

});
/**
 * Created by hzy24985 on 2018/2/8.
 */

$(document).ready(function () {
    debugger;
    new Vue({
        el: '#express-order-root',
        data: {
            model: {},
            agent: express_vm.agents
        },
        ready: function () {
            this.init();
        },
        methods: {
            init: function () {
                var self = this;
                $.ajax({
                    type: 'GET',
                    url: __ctx + "/order/express/one",
                    data: {id: $("#objId").val()}
                }).done(function (result) {
                    self.model = result;
                });
            }
        }
    });

});
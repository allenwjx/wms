$(document).ready(function () {
    debugger;
    var login_vm = new Vue({
        el: '#login',
        data: {
            model: {}
        },
        methods: {
            login: function () {
                $("#loginForm").data('bootstrapValidator', null);
                this.validator();
                var me = this.model;
                var url = "/wms/login";
                var method = "POST";
                $("#loginForm").data('bootstrapValidator').validate();
                if ($("#loginForm").data('bootstrapValidator').isValid()) {
                    $.ajax({
                        type: method,
                        url: url,
                        data: JSON.stringify(me),
                        dataType: "json",
                        contentType: "application/json",
                        //成功返回之后调用的函数
                        success: function (data) {
                            if (data.success) {
                                window.location.replace("/wms/index");
                            } else {
                                $('#loginModal .modal-dialog').addClass('shake');
                                $('.error').addClass('alert alert-danger').html(data.errorMessage);
                                $('input[type="password"]').val('');
                                setTimeout(function () {
                                    $('#loginModal .modal-dialog').removeClass('shake');
                                }, 1000);
                            }
                        },
                        error: function (err) {
                            toastr.error(err, {timeOut: 1500, positionClass: "toast-top-center"});
                        }
                    });
                }
            },
            validator: function () {
                $('#loginForm').bootstrapValidator({
                    message: 'This value is not valid',
                    fields: {
                        username: {
                            validators: {
                                notEmpty: {
                                    message: '请输入账号'
                                }
                            }
                        },
                        password: {
                            validators: {
                                notEmpty: {
                                    message: '请输入密码'
                                }
                            }
                        }
                    }
                });
            }
        }
    });
});
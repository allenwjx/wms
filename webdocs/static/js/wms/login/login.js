$(document).ready(function () {
    debugger;
    var login_vm = new Vue({
        el: '#login',
        data: {
            model: {}
        },
        ready: function () {
            this.handleLogin();
        },
        methods: {
            handleLogin: function () {
                var self = this;
                $('.login-form').validate({
                    errorElement: 'span', //default input error message container
                    errorClass: 'help-block', // default input error message class
                    focusInvalid: false, // do not focus the last invalid input
                    rules: {
                        username: {
                            required: true
                        },
                        password: {
                            required: true
                        },
                        remember: {
                            required: false
                        }
                    },

                    messages: {
                        username: {
                            required: "Username is required."
                        },
                        password: {
                            required: "Password is required."
                        }
                    },

                    invalidHandler: function (event, validator) { //display error alert on form submit
                        $('#err').html("请输入用户名和密码.");
                        $('.alert-danger', $('.login-form')).show(500);
                    },

                    highlight: function (element) { // hightlight error inputs
                        $(element).closest('.form-group').addClass('has-error'); // set error class to the control group
                    },

                    success: function (label) {
                        label.closest('.form-group').removeClass('has-error');
                        label.remove();
                    },

                    errorPlacement: function (error, element) {
                        error.insertAfter(element.closest('.input-icon'));
                    }
                });

                $('.login-form input').keypress(function (e) {
                    if (e.which == 13) {
                        if ($('.login-form').validate().form()) {
                            self.doLogin();
                        }
                        return false;
                    }
                });
            },
            login: function () {
                if ($('.login-form').validate().form()) {
                    this.doLogin();
                }
            },
            doLogin: function () {
                var me = this.model;
                var url = "/wms/login";
                var method = "POST";
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
                            $('#err').html(data.errorMessage);
                            $('.alert-danger', $('.login-form')).show(500);
                            $('input[type="password"]').val('');
                        }
                    },
                    error: function (err) {
                        toastr.error(err, {timeOut: 1500, positionClass: "toast-top-center"});
                    }
                });
            }
        }
    });
});
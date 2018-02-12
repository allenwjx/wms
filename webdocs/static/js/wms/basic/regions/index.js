var regions_vm;
$(document).ready(function () {
    regions_vm = new Vue({
        el: '#regions',
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
            provinces: [],
            cities: [],
            districts: []
        },
        ready: function () {
            this.init();
            this.queryForm = {};
            this.query();
        },
        watch: {
            "queryForm.provinceId" : function(nValue, oValue) {
                if(nValue) {
                    this.onProvinceChange(nValue);
                }
            },
            "queryForm.cityId" : function(nValue, oValue) {
                if(nValue) {
                    this.onCityChange(nValue);
                }
            }
        },
        methods: {
            init: function () {
                var self = this;
                $.ajax({
                    type: 'GET',
                    url: __ctx + "/combo/provinces"
                }).done(function (resp) {
                    self.provinces = resp;
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
                    url: __ctx + "/basic/regions/list",
                    data: $.extend(self.queryForm, pageInfo)
                }).done(function (result) {
                    self.pageResult = result;
                });
            },
            reset: function () {
                this.queryForm = {};
            },
            onProvinceChange: function (provinceId) {
                var self = this;
                $.ajax({
                    type: 'GET',
                    url: __ctx + "/combo/cities/" + provinceId
                }).done(function (resp) {
                    self.cities = resp;
                });
            },
            onCityChange: function (cityId) {
                var self = this;
                $.ajax({
                    type: 'GET',
                    url: __ctx + "/combo/districts/" + cityId
                }).done(function (resp) {
                    self.districts = resp;
                });
            }
        }
    });
});
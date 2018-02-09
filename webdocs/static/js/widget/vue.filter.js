/**
 * Created by hzy24985 on 2016/4/25.
 */

/**
 * 将后台传来的数字型日期转成日期字符串, 格式按照momentjs类库做转换.
 * eg. data | toDate 'YYYY-MM-DD'
 *
 * @param format {String} 日期格式, 参照momentjs.
 * @author hzy24985
 */
Vue.filter('toDate', {
    read: function (value, format) {
        if (value) {
            return moment(value).format(format);
        } else {
            return value;
        }
    },
    write: function (value, format) {
        return value;
    }
});

/**
 * 人民币分转成元
 */
Vue.filter('toMoney', {
    read: function (value) {
        if (value) {
            return "￥" + parseFloat(value)/100;
        } else {
            return value;
        }
    },
    write: function (value, format) {
        if (value && value.length > 1) {
            var num = value.substr(0, value.length - 1);
            return parseFloat(num)*100;
        } else {
            return value;
        }
    }
});

/**
 * 克转千克
 */
Vue.filter('toKG', {
    read: function (value) {
        if (value) {
            return "KG" + parseFloat(value)/1000;
        } else {
            return value;
        }
    },
    write: function (value, format) {
        if (value && value.length > 1) {
            var num = value.substr(0, value.length - 2);
            return parseFloat(num)*1000;
        } else {
            return value;
        }
    }
});

/**
 * 克转500克
 */
Vue.filter('toJin', {
    read: function (value) {
        if (value) {
            return parseFloat(value)/500 + " * (500克)";
        } else {
            return value;
        }
    },
    write: function (value, format) {
        if (value && value.length > 1) {
            var num = value.substr(0, value.length - 2);
            return parseFloat(num)*500;
        } else {
            return value;
        }
    }
});

/**
 * datas arr
 * 将 datas 里面对应的value显示为对应的text 页面过滤
 */
Vue.filter('toViewStr', {
    read: function (value, datas) {
        var text;
        $(datas).each(function (i, e) {
            if (value == e.value) {
                text = e.text;
            }
        });
        if (!text) {
            text = value;
        }
        return text;
    }
});
/**
 * 枚举值转换, 单项绑定，不会回写值.
 * @param value     {String}        传过来的值
 * @param all       {Array}         列表数据
 * @param feild     {String}  可选  列表中的属性名称, default: value
 * @param showFeild {String}  可选  显示成列表中的属性名称  default: text
 * @author hzy24985
 */
Vue.filter('enumFormat', function (value, all, feild, showFeild) {
    var text, feildName = feild, showName = showFeild;
    if (!feild) {
        feildName = 'value';
    }
    if (!showFeild) {
        showName = 'text';
    }
    $(all).each(function (index, item) {
        if (item[feildName] == value) {
            text = item[showName];
        }
    });
    return text;
});

//过滤指定字符传串
Vue.filter('replaceFilter', function (content, mark) {
    if (content != null && content != undefined) {
        return content.replace(mark, "");
    }
    return content;
});
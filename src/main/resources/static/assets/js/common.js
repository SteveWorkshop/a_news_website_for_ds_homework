function notify(title, content) {
    if (!('Notification' in window)) {
        //改成用对话框实现通知
        alert(title + ' : ' + content)
    }
    //检查是否拥有通知权限；有就通知，没有请求；
    else if (Notification.permission == 'granted') {
        var options = {
            icon: 'assets/images/notification.png',
            body: content
        }
        var notification = new Notification(title, options);

    } else if (Notification.permission !== 'denied') {
        Notification.requestPermission().then(function (result) {
            if (result == 'granted') {
                var notification = new Notification(title, options);
            }
        })
    }
}

function dateFtt(fmt, date) { //author: meizz   
    var o = {
        "M+": date.getMonth() + 1,                 //月份   
        "d+": date.getDate(),                    //日   
        "h+": date.getHours(),                   //小时   
        "m+": date.getMinutes(),                 //分   
        "s+": date.getSeconds(),                 //秒   
        "q+": Math.floor((date.getMonth() + 3) / 3), //季度   
        "S": date.getMilliseconds()             //毫秒   
    };
    if (/(y+)/.test(fmt))
        fmt = fmt.replace(RegExp.$1, (date.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt))
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
} 
function createWebSocket(userId,questionId) {
    var websocket = null;

//判断当前浏览器是否支持WebSocket
    if ('WebSocket' in window) {
        websocket = new WebSocket("ws://localhost:8887/questionWebSocket/"+userId+"/"+questionId);
    } else {
        alert('Not support websocket')
    }

    //监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
    window.onbeforeunload = function () {
        websocket.close();
    }

    return websocket;
}
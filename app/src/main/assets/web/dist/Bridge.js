/**
* Bridge框架JS功能实现
*/

function getRandomId() {
    return (Math.round(Math.random() * Date.now() * 1000)) % Math.pow(10, 7) * 1000;
}

let callbackId = getRandomId();
//调用natvie功能后的回调
let callbacks = {};

function call(request){
    WebViewBridge.callFunc(request);
}

//js调用native,js->native
function callToNative(funcId,data,callback) {
    let request = {}
    request.funcId = funcId
    if(data){
        request.data = data
    }
    if(callback){
        //记录调用事件
        let callbackKey = funcId + callbackId++;
        callbacks[callbackKey] = callback;
        request.callbackKey = callbackKey
    }
    console.log("request" + JSON.stringify(request))
    call(JSON.stringify(request))
}

//native调用js。natvie->js
function callbackFromNative(response){
    let msgJson = JSON.parse(response);
    if(msgJson.callbackKey){
        let data = msgJson.data === "null" ? null : msgJson.data;
        let callback = callbacks[msgJson.callbackKey]
        if(typeof callback === "function"){
            callback(data);
        }
        if (callback) {
            delete callback[msgJson.callbackKey];
        }
    }
}

WebViewBridge.callToNative = callToNative
WebViewBridge.callbackFromNative = callbackFromNative

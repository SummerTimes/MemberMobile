 (function() {

if(typeof(window.CTJSBridge) == 'undefined'){

    window.CTCallBackList = {};

    window.CTMethodList=new Array();

    var URL="";
    var callBack;

    window.BLAppType="bailian";


        String.prototype.hashCode = function() {
          var hash = 0;
          if (this.length == 0) return hash;
          for (var index = 0; index < this.length; index++) {
            var charactor = this.charCodeAt(index);
            hash = ((hash << 5) - hash) + charactor;
            hash = hash & hash;
          }
          return hash;
        };

        var messagingIframe;

        function LoadAPI(apiName, data, callback){

            if(!apiName){
                return;
            }
            if(!data){
                return;
            }

            var dataString = encodeURIComponent(JSON.stringify(data));
            var timestamp = Date.parse(new Date());
            var identifier = (apiName + dataString + timestamp).hashCode().toString();

            console.log("调用API"+identifier);

            window.CTCallBackList[identifier] = callback;
            var methodName=apiName;

            var url = "blbridge://api?callbackIdentifier=" +
                identifier + "&data=" + dataString + "&apiName=" + apiName+"&methodName="+methodName;

            URL+=url+"::::";
            console.log("本地"+apiName+URL);

            setTimeout(function(){
                    if(URL==""){
                        return;
                    }
                    LoadNative(URL);
                    URL="";
            },600);
        }


        var buriedPoint;


        function fetchUserInfo(){
            return BuriedPoint;
        }


        function CallJava(){
            LoadMethod("setBuriedPoint","setBuriedPoint","",setBuriedPoint(data))
        }



        function setBuriedPoint(data){
            buriedPoint=data;
        }





         function LoadMethod(targetName, actionName, data, callback){
                var dataString = encodeURIComponent(JSON.stringify(data));
                 var timestamp = Date.parse(new Date());
                var identifier = (targetName + actionName + dataString+timestamp).hashCode().toString();


                window.CTCallBackList[identifier] = callback;
                var methodName=targetName+"#"+actionName;
    //            alert("调用本地");
                var url = "blbridge://component?callbackIdentifier=" + identifier + "&data=" + dataString + "&targetName=" + targetName + "&actionName=" + actionName+"&methodName="+methodName;

                console.log("方法调用"+targetName+actionName);

                if(window.CTMethodList.length==0){
                     setTimeout(function(){
                           console.log("初始方法");
                           var startUrl="blbridge://component?callbackIdentifier="+"&startMethod";
                           LoadNative(startUrl);
                        },30);
                 }

                 console.log("方法压入"+targetName+actionName);
                 console.log("方法栈压入前"+ window.CTMethodList);
                 window.CTMethodList.push(url);
                 console.log("方法栈压入后"+ window.CTMethodList);
         }



        function flatMethod() {

              console.log("方法栈push准备"+window.CTMethodList);
              if(window.CTMethodList.length>0){
                console.log("方法栈push前"+window.CTMethodList);
                LoadNative(window.CTMethodList[0]);

               console.log("方法栈push后"+window.CTMethodList[0]);


                window.CTMethodList.shift();
              }
        }



        function CallJS() {
            console.log("本地JS被JAVA调用");
            console.log(window.commShare);
            window.commShare();
        }

        function CallBack(identifier, resultStatus, resultData) {

              console.log("====回调JS===="+resultStatus+"====="+resultData);

              callBackDict = window.CTCallBackList[identifier];

              if (callBackDict) {
                isFinished = true;
console.log("====回调JS===="+resultStatus+"====="+resultData);

                if (resultStatus == "success") {
                  callBackDict.success(resultData);
                }
                if (resultStatus == "fail") {
                  callBackDict.fail(resultData);
                }
                if (resultStatus == "progress") {
                  isFinished = false;
                  callBackDict.progress(resultData);
                }

                if (isFinished) {
                   console.log("====移除===="+identifier);
                  window.CTCallBackList[identifier] = null;
                  delete window.CTCallBackList[identifier];
                }
              }
            }


        function LoadNative(url) {

            if(!messagingIframe){
                messagingIframe = doc.createElement('iframe');
                messagingIframe.style.display = 'none';
                window.document.documentElement.appendChild(messagingIframe);
            }


            messagingIframe.src = url;
            console.log("调用"+document.getElementsByTagName('iframe').length);
        }

        function _setNativeTitle(title){
              var methodName="title";
    //           var url = "blbridge://api?callbackIdentifier=" +
    //                      identifier + "&data=" + dataString  +"&apiName=" + methodName+"&methodName="+methodName;
    console.log("====设置头===="+title);

               LoadMethod(methodName,methodName,title);
        }




        function _createQueueReadyIframe(doc) {



        }


        var CTJSBridge = window.CTJSBridge = {
            LoadAPI:LoadAPI,
            LoadMethod:LoadMethod,
            CallBack:CallBack,
            _setNativeTitle:_setNativeTitle,
            CallJS:CallJS,
            fetchUserInfo:fetchUserInfo,
            CTMethodList:CTMethodList,
            flatMethod:flatMethod,
            CTCallBackList:CTCallBackList
        };

        var doc = document;
        _createQueueReadyIframe(doc);
        var readyEvent = doc.createEvent('Events');
        readyEvent.initEvent('BLBridgeReady');
        readyEvent.bridge = CTJSBridge;
        doc.dispatchEvent(readyEvent);
}






})();
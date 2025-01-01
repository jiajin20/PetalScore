/*
 * @Author: error: error: git config user.name & please set dead value or install git && error: git config user.email & please set dead value or install git & please set dead value or install git
 * @Date: 2024-10-29 10:40:46
 * @LastEditors: jiajin20 3196117119@qq.com
 * @LastEditTime: 2024-11-11 10:58:41
 * @FilePath: \WebContentc:\JAVAprogram\innerExpress2.0\WebContent\statics\js\request.js
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
 */
var request = Object.create({
    req: function (url, method, data, params, resolve, reject) {
        // 参数处理：根据传入参数的数量调整
        let args = Array.from(arguments).filter(i => i !== undefined);
        
        // 确定 resolve 和 reject 的位置
        switch (args.length) {
            case 3:
                resolve = data;
                data = undefined;
                break;
            case 4:
                resolve = params;
                params = undefined;
                break;
            case 5:
                if (args.filter(i => typeof i === 'function').length === 2) {
                    reject = resolve;
                    resolve = params;
                    params = undefined;
                }
                break;
        }

        // 设置默认的 reject 函数
        reject = reject || function (error) {
            console.error("返回的错误是：" + error);
        };

        // 初始化请求参数
        let init = {
            method,
            headers: {
                "Content-Type": "application/json",  // 设置请求头参数为json格式
                'token': window.sessionStorage.getItem('token')
            }
        };

        // URL 参数拼接函数
        function changeParamsToUrl(pa) {
            let paramString = Object.keys(pa)
                .map(key => encodeURIComponent(key) + '=' + encodeURIComponent(pa[key]))
                .join('&');
            return url + (url.includes('?') ? '&' : '?') + paramString;
        }

        // 拼接参数到 URL 或设置请求体
        switch (method.toLowerCase()) {
            case "get":
            case "delete":
                if (params !== undefined) {
                    url = changeParamsToUrl(params);
                }
                if (data !== undefined) {
                    url = changeParamsToUrl(data);
                }
                break;
            case "post":
            case "put":
                if (params !== undefined) {
                    url = changeParamsToUrl(params);
                }
                if (data !== undefined) {
                    init.body = JSON.stringify(data);
                }
                break;
        }





        // 发起请求
        fetch(url, init)
            .then(res => {
                if (res.status !== 200) {
                    return res.text().then(text => {
                        throw new Error(text || `Request failed with status ${res.status}`);
                    });
                }
                return res.json();  // 返回 JSON 数据
            })
            .then(resolve)  // 成功时调用 resolve
            .catch(reject);  // 失败时调用 reject
    }
});

// 各种 HTTP 请求方法封装
request.post = function (url, data, params, resolve, reject) {
    request.req(url, "POST", data, params, resolve, reject);
};

request.get = function (url, data, params, resolve, reject) {
    request.req(url, "GET", data, params, resolve, reject);
};

request.put = function (url, data, params, resolve, reject) {
    request.req(url, "PUT", data, params, resolve, reject);
};

request.delete = function (url, data, params, resolve, reject) {
    request.req(url, "DELETE", data, params, resolve, reject);
};

var exec = require('cordova/exec');

module.exports = {
    startMLKit:(data,successCallback,errorCallback)=>{
        exec(successCallback,errorCallback,"MLKitPlugin","openMLKit",[data]);
    }
};

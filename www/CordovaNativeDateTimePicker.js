var exec = require('cordova/exec');

exports.showDateTimePicker = function (arg0, success, error) {
    exec(success, error, 'CordovaNativeDateTimePicker', 'datetimePicker', [arg0]);
};

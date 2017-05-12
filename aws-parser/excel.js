const
	xlsxj = require('xlsx-to-json'),
	xlsx  = require('xlsx');

module.exports.parseXlsx = function(file, cb) {
	xlsxj({
		input: file, 
		output:  file.slice(0, -5) + '.json'
	}, function(err, result) {
		if(err) {
			cb(err, null);
		}else {
			cb(null, result);
		}
	});
}

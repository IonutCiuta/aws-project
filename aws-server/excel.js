const
	xlsxj = require('xlsx-to-json'),
	xlsx  = require('xlsx');

module.exports.parseXlsx = function(file, cb) {
	xlsxj({
		input: file, 
		output:  file + 'out.json'
	}, function(err, result) {
		if(err) {
			cb(err, null);
		}else {
			cb(null, result);
		}
	});
}

const
	path    	= require("path");
	express 	= require('express'),
	moment 		= require('moment'),
	mongoose 	= require('mongoose'),
	log 		= require('winston'),
	bodyParser 	= require('body-parser');
	excel		= require('./excel.js');

const 
	DATE_FORMAT = "DD.MM.YY HH:mm:ss";

const
	PRIVATE_SECTOR_DATA = [
		//'codes.xlsx'
		//'2014_1.xlsx'
		//'2014_2.xlsx'
		//'2016_1.xlsx'
		//'2016_2.xlsx'
	];

var app = express();
app.use(bodyParser.json());
app.use('/', express.static(path.join(__dirname, '../aws-web')));
app.use('/bower_components', express.static(path.join(__dirname, '../bower_components')));

app.get('/', function(req, res) {
	res.sendFile(path.join(__dirname, '../aws-web/index.html'));	
});

var server = app.listen(process.env.PORT || 3000, function() {
	log.info(
    	'Application server started on port %s @ %s', 
    	server.address().port,
    	moment().format(DATE_FORMAT)
    );

	/*
	PRIVATE_SECTOR_DATA.forEach(function(file) {
		log.info('Parsing %s at %s', file, moment().format(DATE_FORMAT));
	    excel.parseXlsx(path.join(__dirname, '../aws-db/xlsx/private_sector_data', file), function(err, data) {
	    	if(err) {
	    		log.error(JSON.stringify(err));
	    	} else {
	    		log.info('Done parsing %s at %s', file, moment().format(DATE_FORMAT));
	    	};
	    });
	});
	*/
});
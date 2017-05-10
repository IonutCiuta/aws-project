const
	path    	= require("path");
	express 	= require('express'),
	moment 		= require('moment'),
	mongoose 	= require('mongoose'),
	log 		= require('winston'),
	bodyParser 	= require('body-parser'),
	excel		= require('./excel.js'),
	fs			= require('fs');

const 
	DATE_FORMAT = "DD.MM.YY HH:mm:ss";

const
	PRIVATE_SECTOR_DATA = '../aws-project/aws-db/xlsx/private_sector_data/'

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


	fs.readdir(PRIVATE_SECTOR_DATA, (err, files) => {
		if(err) {
			log.error(JSON.stringify(err));
		}

		files.forEach(function(file) {
			log.info('Parsing %s at %s', file, moment().format(DATE_FORMAT));

			var extIdx = file.lastIndexOf('.');
			var ext = file.substring(extIdx + 1, extIdx + 5);

			if(ext === "xlsx") {
			    excel.parseXlsx(path.join(__dirname, '../aws-db/xlsx/private_sector_data', file), function(err, data) {
			    	if(err) {
			    		log.error(JSON.stringify(err));
			    	} else {
			    		log.info('Done parsing %s at %s', file, moment().format(DATE_FORMAT));
			    	};
			    });
			} else {
				log.info("Skipping file.");
			}
		})
	});
});
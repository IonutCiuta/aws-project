const
	path    	= require("path");
	express 	= require('express'),
	moment 		= require('moment'),
	mongoose 	= require('mongoose'),
	log 		= require('winston'),
	bodyParser 	= require('body-parser');


const 
	DATE_FORMAT = "DD.MM.YY HH:mm:ss";

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

    console.log(path.join(__dirname, '../aws-web/'));
});
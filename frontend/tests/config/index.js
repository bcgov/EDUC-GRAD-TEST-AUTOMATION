'use strict';
const nconf = require('nconf');
const dotenv = require('dotenv');
const path = require('path');
dotenv.config();

//switch to local if running locally
const env = process.env.NODE_ENV;
//const env = 'local'

nconf.argv()
  .env()
  .file({ file: path.join(__dirname, `${env}.json`) });

nconf.defaults({
    url: {
        base_url: process.env.BASE_URL
      },
    adminCredential: {
        user: process.env.TEST_ADMIN_USERNAME,
        pass: process.env.TEST_ADMIN_PASSWORD
    },
    test: {
        pen: '126187343'
    }
});

module.exports = nconf;
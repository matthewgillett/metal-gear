import feed from './feed.js';
import fetch from 'isomorphic-fetch'

module.exports = function(parameter) {
  	return dispatch => {
  		var url = 'http://localhost:8080//twitter/tweets/' + parameter;
		return fetch(url)
		.then(function(response) {
			return response.json();
		})
		.then(function(json) {
			dispatch(feed(json));
		});
	}
};

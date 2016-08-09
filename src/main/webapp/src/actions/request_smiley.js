import fetch from 'isomorphic-fetch';
import emotion from './emotion'
import title from './title';

module.exports = function(parameter) {
	return dispatch => {
		var url = 'http://localhost:8080//twitter/sentiment/' + parameter;
		return fetch(url)
		.then(function(response) {
			return response.json();
		})
		.then(function(json) {
			dispatch(emotion(json.sentiment));
			dispatch(title(json.firmName));
		})
	}
};

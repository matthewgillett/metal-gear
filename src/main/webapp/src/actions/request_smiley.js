import fetch from 'isomorphic-fetch';
import emotion from './emotion'

module.exports = function(parameter) {
	return dispatch => {
		var url = 'http://localhost:8080//twitter/sentiment/' + parameter;
		return fetch(url)
		.then(function(response) {
			return response.json();
		})
		.then(function(json) {
			console.log(json);
			dispatch(emotion(json.sentiment));
		})
	}
};

import fetch from 'isomorphic-fetch';
import emotion from './emotion'

module.exports = function(parameter) {
	return dispatch => {
		var url = 'http://localhost:8080/twiter/sentiment/' + parameter;
		console.log(url);
		return fetch(url)
		.then(function(response) {
			return response.json();
		})
		.then(function(json) {
			dispatch(emotion(json.key));
		})
	}
};

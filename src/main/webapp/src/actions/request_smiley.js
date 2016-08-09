import fetch from 'isomorphic-fetch';
import emotion from './emotion'

module.exports = function(parameter) {
	return dispatch => {
		return fetch('http://localhost:8080/sentiment')
		.then(function(response) {
			return response.json();
		})
		.then(function(json) {
			dispatch(emotion(json.key));
		})
	}
};

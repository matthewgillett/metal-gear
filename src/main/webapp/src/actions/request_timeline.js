
import timeline from './timeline.js';

module.exports = function(parameter) {
	return dispatch => {
		dispatch(timeline(['title1', 'Some text', 'title2', 'other text']));
		/*return fetch('http://localhost:8080/sentiment')
		.then(function(response) {
			return response.json();
		})
		.then(function(json) {

		});*/
	}
};

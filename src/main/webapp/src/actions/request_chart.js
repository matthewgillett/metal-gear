import chart from './chart.js';

module.exports = function(parameter) {
	return dispatch => {
		dispatch(chart(['0,0','10','10']));
		/*return fetch('http://localhost:8080/sentiment')
		.then(function(response) {
			return response.json();
		})
		.then(function(json) {

		});*/
	}
};

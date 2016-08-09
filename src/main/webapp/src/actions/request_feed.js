import feed from './feed';

module.exports = function(parameter) {
  	return dispatch => {
  		dispatch(feed([Math.random(), 'aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa', 'sad']));
		/*return fetch('http://localhost:8080/sentiment')
		.then(function(response) {
			return response.json();
		})
		.then(function(json) {

		});*/
	}
};

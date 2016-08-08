/* Define your initial state here.
 *
 * If you change the type from object to something else, do not forget to update
 * src/container/App.js accordingly.
 */
const initialState = {};

import fetch from 'isomorphic-fetch'

module.exports = function(state = initialState, action) {
  switch(action.type) {
    case 'SEARCH': {
      // Modify next state depending on the action and return it
      return Object.assign({}, state, {
        //Set the search param
        searchParam: action.parameter
      });
    }
    case 'EMOTION': {
      //Create the new state object
      var curr = Object.assign({}, state, {});
      if (curr.searchParam === undefined) return curr;
      fetch('http://www.reddit.com/r/programming.json')
      .then(response => {
        return response.json();
      })
      .then(json => {
        return json.data.children.map(child => child.data);
      }).then(function(info) {
        console.log(info);
        return curr;
      });
    }
    default: {
      /* Return original state if no actions were consumed. */
      return state;
    }
  }
}

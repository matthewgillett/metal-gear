/* Define your initial state here.
 *
 * If you change the type from object to something else, do not forget to update
 * src/container/App.js accordingly.
 */
const initialState = {};

module.exports = function(state = initialState, action) {
  switch(action.type) {
    case 'SEARCH': {
      // Modify next state depending on the action and return it
      return Object.assign({}, state, {
        searchParam: action.parameter
      });
    }
    default: {
      /* Return original state if no actions were consumed. */
      return state;
    }
  }
}

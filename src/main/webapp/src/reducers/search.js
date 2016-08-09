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
        //Set the search param
        searchParam: action.parameter
      });
    }
    case 'EMOTION': {
      //Create the new state object
      console.log(action.parameter)
      return Object.assign({}, state, {
        smiley: action.parameter
      });
    }
    case 'FEED': {
      var curr = Object.assign({}, state, {});
      if (curr.feed !== undefined && curr.feed.length > 10) {
        curr.feed = [];
        return curr;
      }
      if (curr.feed === undefined) {
        curr.feed = [];
      }
      curr.feed.push({
          key: curr.feed.length,
          name: Math.random(),
          text: 'hello',
          sentiment: 4
      });
      return curr;
    }
    case 'CLEAR' : {
      return Object.assign({}, state, {
        feed: []
      });
    }
    default: {
      /* Return original state if no actions were consumed. */
      return state;
    }
  }
}

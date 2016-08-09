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
      return Object.assign({}, state, {
        smiley: action.parameter
      });
    }

    case 'FEED': {
      var curr = Object.assign({}, state, {});
      if (curr.feed !== undefined && curr.feed.length > 10) {
        curr.feed.shift();
      }
        curr.feed = [];

      action.parameter.forEach(function(param) {
        curr.feed.push({
            key: curr.feed.length,
            name: param[0],
            text: param[1],
            sentiment: param[2]
        });
      });

      return curr;
    }

    case 'CLEAR' : {
      return Object.assign({}, state, {
        feed: []
      });
    }

    case 'CHART' : {
      return Object.assign({}, state, {
        chart: {}
      });
    }

    case 'TIMELINE' : {
      return Object.assign({}, state, {
        timeline: {}
      });
    }

    default: {
      /* Return original state if no actions were consumed. */
      return state;
    }
  }
}

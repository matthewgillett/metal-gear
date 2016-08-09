/* CAUTION: When using the generators, this file is modified in some places.
 *          This is done via AST traversal - Some of your formatting may be lost
 *          in the process - no functionality should be broken though.
 *          This modifications only run once when the generator is invoked - if
 *          you edit them, they are not updated again.
 */
import React, {
  Component,
  PropTypes
} from 'react';
import { bindActionCreators } from 'redux';
import { connect } from 'react-redux';
import Main from '../components/Main';
/* Populated by react-webpack-redux:reducer */
class App extends Component {
  render() {
    const {actions, search, emotion} = this.props;
    return <Main actions={actions} search={search} emotion={emotion}/>;
  }
}
/* Populated by react-webpack-redux:reducer
 *
 * HINT: if you adjust the initial type of your reducer, you will also have to
 *       adjust it here.
 */
App.propTypes = {
  actions: PropTypes.object.isRequired,
  search: PropTypes.object.isRequired
};
function mapStateToProps(state) {
  /* Populated by react-webpack-redux:reducer */
  const props = {
    search: state.search,
    emotion: state.emotion
  };
  return props;
}
function mapDispatchToProps(dispatch) {
  /* Populated by react-webpack-redux:action */
  const actions = {
    search: require('../actions/search.js'),
    emotion: require('../actions/emotion.js'),
    typeahead: require('../actions/typeahead.js'),
    feed: require('../actions/feed.js'),
    clear: require('../actions/clear.js'),
    request_smiley: require('../actions/request_smiley.js'),
    request_feed: require('../actions/request_feed.js'),
    chart: require('../actions/chart.js'),
    request_chart: require('../actions/request_chart.js'),
    timeline: require('../actions/timeline.js'),
    request_timeline: require('../actions/request_timeline.js'),
    title: require('../actions/title.js')
  };
  const actionMap = { actions: bindActionCreators(actions, dispatch) };
  return actionMap;
}
export default connect(mapStateToProps, mapDispatchToProps)(App);

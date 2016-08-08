require('normalize.css/normalize.css');
require('styles/bootstrap.min.css');

import React from 'react';
import Search from './SearchComponent'

class AppComponent extends React.Component {
  render() {
    return (
      <div>
      	<Search searchParam={this.props.search.searchParam} searchFunc={this.props.actions.search}/>
      </div>
    );
  }
}

AppComponent.defaultProps = {
};

export default AppComponent;

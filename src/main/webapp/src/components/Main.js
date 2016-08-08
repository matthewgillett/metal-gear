require('normalize.css/normalize.css');
require('styles/bootstrap.min.css');

import React from 'react';
import Search from './SearchComponent'
import Smiley from './SmileyComponent'

class AppComponent extends React.Component {
  render() {
  	var smiley;
  	if (this.props.search.searchParam !== undefined) {
  		smiley = <Smiley smiley={this.props.search.smiley}/>
  	}
    return (
	    <div>
	      <Search searchParam={this.props.search.searchParam} searchFunc={this.props.actions.search} emoteFunc={this.props.actions.emotion}/>
	      <div className="container">
	      	<div className="row">
	      		<div className="col-md-4">
	      			{smiley}
	      		</div>
	      	</div>
	      </div>
	   	</div>
    );
  }
}

AppComponent.defaultProps = {
};

export default AppComponent;

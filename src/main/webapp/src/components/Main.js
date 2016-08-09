require('normalize.css/normalize.css');
require('styles/bootstrap.min.css');

import React from 'react';
import Search from './SearchComponent'
import Smiley from './SmileyComponent'
import Feed from './FeedComponent';

class AppComponent extends React.Component {
  render() {
  	var smiley;
  	var feed;
  	if (this.props.search.searchParam !== undefined) {
  		smiley = <Smiley smiley={this.props.search.smiley}/>
  		feed = <Feed tweets= {this.props.search.feed} feedFunc={this.props.actions.feed}/>
  	}
    return (
	    <div>
	      <Search searchParam={this.props.search.searchParam} searchFunc={this.props.actions.search} requestSmiley={this.props.actions.request_smiley} feedFunc={this.props.actions.feed} clearFunc={this.props.actions.clear}/>
	      <div className="container">
	      	<div className="row">
	      		<div className="col-md-4">
	      			{smiley}
	      		</div>
	      		<div className="col-md-8">
	      			{feed}
	      		</div>
	      	</div>
	      	<div className="row">
	      	</div>
	      </div>
	   	</div>
    );
  }
}

AppComponent.defaultProps = {
};

export default AppComponent;

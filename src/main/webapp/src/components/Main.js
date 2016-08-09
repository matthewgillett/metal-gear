require('normalize.css/normalize.css');
require('styles/bootstrap.min.css');
require('styles/Main.css');

import React from 'react';
import Search from './SearchComponent'
import Smiley from './SmileyComponent'
import Feed from './FeedComponent';
import Graph from './GraphComponent';
import Timeline from './TimelineComponent';

class AppComponent extends React.Component {
  render() {
  	var smiley;
  	var feed;
  	var graph
  	var timeline;
  	if (this.props.search.searchParam !== undefined) {
  		feed = <Feed tweets= {this.props.search.feed} feedFunc={this.props.actions.request_feed}/>
  		graph = <Graph  tweets= {this.props.search.feed} searchParam = {this.props.search.searchParam}/>
  		timeline = <Timeline/>
  	}
    return (
	    <div>
	      <div className="container-fluid">
	      <Search firmName={this.props.search.firmName} smiley={this.props.search.smiley} searchParam={this.props.search.searchParam} searchFunc={this.props.actions.search} requestSmiley={this.props.actions.request_smiley} requestFeed={this.props.actions.request_feed} clearFunc={this.props.actions.clear}/>
	      </div>
	      <div className="container-fluid">
	      	<div className="row sent-row">
	      		<div className="col-md-6 text-center">
	      			{graph}
	      		</div>
	      		<div className="col-md-2">
	      			{feed}
	      		</div>
	      	</div>
	      	<div className="row bottom-row">
  	      		<div className="col-md-6 text-center">
      				{timeline}
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

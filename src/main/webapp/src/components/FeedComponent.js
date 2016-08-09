'use strict';

import React from 'react';

require('styles//Feed.css');

let error = require('./../images/error.png');
let very_sad = require('./../images/very-sad.png');
let sad = require('./../images/sad.png');
let neutral = require('./../images/neutral.png');
let happy = require('./../images/happy.png');
let very_happy = require('./../images/very-happy.png');



class FeedComponent extends React.Component {
  render() {
  	var update = this.props.feedFunc;
  	function getSmiley(val) {
  		if (val == 0) return very_sad;
  		if (val == 1) return sad;
  		if (val == 2) return neutral;
  		if (val == 3) return happy;
  		if (val == 4) return very_happy;
  		return error;
  	};
    return (
      <div className="container feed-container">
      <table className="table table-responsive">
	    <thead>
	      <tr>
	        <th>Username</th>
	        <th>Text</th>
	        <th>Sentiment</th>
	      </tr>
	    </thead>
	    <tbody>
	    {this.props.tweets.map(tweet => {
	      return <tr key={this.key}>
	        <td>{tweet.name}</td>
	        <td className="wrap-td">{tweet.text}</td>
	        <td className="smile-icon"><img src={getSmiley(tweet.sentiment)} id="feed-smile"/></td>
		  </tr>
	    })}
	    </tbody>
	  </table>
	</div>
    );
  }
}

FeedComponent.displayName = 'FeedComponent';

// Uncomment properties you need
// FeedComponent.propTypes = {};
// FeedComponent.defaultProps = {};

export default FeedComponent;

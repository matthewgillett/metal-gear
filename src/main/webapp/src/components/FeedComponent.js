'use strict';

import React from 'react';

require('styles//Feed.css');

class FeedComponent extends React.Component {
  render() {
  	var update = this.props.feedFunc;
  	setInterval(function() {
    	update();
	}, 1000 * 60);
    return (
      <div className="container feed-container">
      <table className="table">
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
	        <td>{tweet.sentiment}</td>
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

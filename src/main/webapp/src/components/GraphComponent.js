'use strict';

import React from 'react';
import {PieChart} from 'react-d3';

require('styles//Graph.css');

class GraphComponent extends React.Component {
  render() {
  	var denom = this.props.tweets.length;
  	var builder = [];

  	function mapSentimentToEmotion(sentiment) {
  		switch(sentiment) {
  			case '0': return 'Very Sad';
  			case '1': return 'Sad';
  			case '2': return 'Neutral';
  			case '3': return 'Happy';
  			case '4': return 'Very Happy';
  			default: return 'No value';
  		}

  	}

  	var sentMap = {};
	this.props.tweets.forEach(function(tweet) {
		var key = mapSentimentToEmotion(tweet.sentiment);
		if (sentMap[key] === undefined) {
			sentMap[key] = 1;
		} else {
			sentMap[key] += 1;
		}
	});

	var pieData = [];
	for (var key in sentMap) {
		builder.push({
			label: key,
			value: Math.round(sentMap[key]/denom * 100)
		});
	}

	console.log(builder);

  	var pieData = builder;
    return (
      <div className="graph-component">
		<PieChart
		  data={pieData}
		  width={400}
		  height={400}
		  radius={100}
		  innerRadius={20}
		  title="Percentage for sentiment"
		/>
      </div>
    );
  }
}

GraphComponent.displayName = 'GraphComponent';

// Uncomment properties you need
// GraphComponent.propTypes = {};
// GraphComponent.defaultProps = {};

export default GraphComponent;

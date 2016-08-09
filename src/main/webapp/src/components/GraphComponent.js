'use strict';

import React from 'react';
import {LineChart} from 'react-d3';

require('styles//Graph.css');

class GraphComponent extends React.Component {
  render() {
	var lineData = [
	  {
	    values: [ { x: 0, y: 20 },{ x: 24, y: 10 }]
	  }
	];
	var chartSeries= [{
		color: '#ff7f0e'
	}];
    return (
      <div className="graph-component">
      <LineChart
		  legend={true}
		  data={lineData}
		  width={650}
		  height={250}
		  title="Line Chart"
		  chartSeries={chartSeries}
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

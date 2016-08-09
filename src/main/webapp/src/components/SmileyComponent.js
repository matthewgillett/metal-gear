'use strict';

import React from 'react';

require('styles//Smiley.css');

let error = require('./../images/error.png');
let very_sad = require('./../images/very-sad.png');
let sad = require('./../images/sad.png');
let neutral = require('./../images/neutral.png');
let happy = require('./../images/happy.png');
let very_happy = require('./../images/very-happy.png');

class SmileyComponent extends React.Component {
  render() {
  	var emoticon = (function(val) {
  		switch(val) {
  			case 0: return very_sad;
  			case 1: return sad;
  			case 2: return neutral;
  			case 3: return happy;
  			case 4: return very_happy;
  			default: {
  				return error;
  			}
  		}
  	}(this.props.smiley));

    return (
      <div className="smiley-component container">
      	<img src={emoticon} id="feel-image"/>
      </div>
    );
  }
}

SmileyComponent.displayName = 'SmileyComponent';

// Uncomment properties you need
// SmileyComponent.propTypes = {};
// SmileyComponent.defaultProps = {};

export default SmileyComponent;

'use strict';

import React from 'react';

require('styles//Search.css');

class SearchComponent extends React.Component {
  render() {
  	var val;
  	var oldVal;
  	var submit = e => {
		e.preventDefault();
		if (val === '') {
			this.props.searchFunc(undefined);
			return;
		}
		if (val !== oldVal) {
			this.props.searchFunc(val);
			this.props.emoteFunc();
			oldVal = val;
		}
	}
    return (
	    <div className="jumbotron text-center">
	      <h1>{this.props.searchParam}</h1>
	       <form onSubmit={submit}>
	         <div className="col-md-6 col-md-offset-3">
	        	<div className="input-group input-group-lg">
	            <input type="text" className="form-control" placeholder="Search a firm" value={val} onChange={e => {
	            	val = e.target.value;
	            }}/>
	            <submit className="btn btn-primary input-group-addon" onSubmit={submit} onClick={submit}>Search</submit>
	          </div>
	        </div>
	      </form>
	    </div>
    );
  }
}

SearchComponent.displayName = 'SearchComponent';

// Uncomment properties you need
SearchComponent.propTypes = {
	searchParam: React.PropTypes.string,
	searchFunc: React.PropTypes.func
};
// SearchComponent.defaultProps = {};

export default SearchComponent;

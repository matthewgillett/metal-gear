import {SEARCH} from './const';

module.exports = function(searchParam) {
  return { type: SEARCH, parameter: searchParam};
};

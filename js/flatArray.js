/**
Write some code that will flatten an array of arbitrarily nested arrays of integers into a flat array of integers. e.g. [[1,2,[3]],4] -> [1,2,3,4].
 */
'use strict';

// exam if the array is already flattened; returns true/false
const isArrayFlattened = function (array) {
  if (!array) {
    return true;
  }
  for (const item of array) {
    if (item instanceof Array) {
      return false;
    }
  }
  return true;
};

/*  main function to flatten the arrays. The use of Array.prototype.reduce is so that each iteration a (potential) layer of square brackets is removed.
 This is ensured by that [1].concat(2).concat(3) returns the same value as [1].concat([2, 3]), which is [1, 2, 3] for both
 ALTERNATIVELY to reduce we can use Array.prototype.flat() or Array.prototype.flatMap() */

const flatten = function (array) {
  while (!isArrayFlattened(array)) {
    array = array.reduce((previous, item) => {
      return previous.concat(item);
    }, []);
  }
  return array;
};

const cases = [
  [],
  [1, [2], 3],
  [[1, 2], 3],
  [[[1], 2], 3],
  [[[[1]], 2], 3],
  [[1], [2], [3]],
  [[[[1], [2]], [3]]],
  [[1, 2, [3]], 4]
];

cases.forEach((item, index) => {
  let el = JSON.stringify(item)
  console.log(index + 1 + ")", el, " -> ", flatten(item))
});
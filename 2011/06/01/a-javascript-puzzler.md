[sourcecode lang="javascript" gist="1002886"]var M = function() { this.m = function() { return 42 } };

var inst = new M();

inst.f = function() { return 42 };

// How to tell that f is a function in a field and 
// m is a method in an arbitrary object?
// 
// example:

is_method(inst.f);
//=> false

is_method(inst.m);
//=> true

// Is it possible to write is_method?
[/sourcecode]

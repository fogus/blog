Using [Underscore](http://underscorejs.org) and [Lemonad](http://www.functionaljs.org), how could you write a run-length encoder/decoder without ever referencing a function parameter?

One way:

    var S = ['a', 'a', 'a', 'a', 
             'b', 'c', 'c', 
             'a', 'a', 'd', 
             'e', 'e', 'e', 'e'];
    
    var pack = L.partial1(L.partitionBy, _.identity);
    
    pack(S);
    //=> [['a', 'a', 'a', 'a'], ['b'] ...]
    
    var rle = _.compose(
      L.partial1(L.flip(_.map), 
                 L.juxt(L.plucker('length'), 
                        _.first)), 
      pack);
    
    rle(S);
    //=> [[4, 'a'], [1, 'b'], [2, 'c'], 
          [2, 'a'], [1, 'd'], [4, 'e']]
    
    var rld = _.compose(
      _.flatten, 
      L.partial1(L.flip(_.map), 
                 L.splat(L.repeat)));
    
    L.pipeline(S, rle, rld);
    
    //=> ["a", "a", "a", "a", "b", ...]

The above style is called "point-free" style where "points" refer to function parameters like `a` and `b` in the function `function(a, b) { ... }`.  The functions `rle` and `rld` are built entirely via the composition functions in Lemonad and Underscore[^comp_].  Fun fun.js.

I'll cover point-free style to a small degree in my [upcoming book on functional JavaScript](http://www.functionaljavascript.com), but it's a fun way to think about building programs and I encourage you to explore.

*I have [a gist with the same code](https://gist.github.com/fogus/5354028) for syntax highlighting and different spacing.*

[^comp_]: Underscore has for a while had the `_.compose` function, but is only now incorporating other composition functions like `_.partial` and (maybe) `_.flip`.
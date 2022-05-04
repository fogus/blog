Yesterday I [demonstrated how to transform a mundane recursive function into a tail recursive function](http://blog.fogus.me/2011/03/08/transforming-an-accumulating-mundane-recursive-fn-to-a-tail-recursive-fn-with-clojure/).  However, I received some flak for that post because there are better ways to implement run-length encoding with Clojure.  While certainly true, I think some of my critics[^kritic] missed the point of the exercise.  ;-)  

Having said that; their points are valid because in Clojure (and likely most, if not all functional languages) **recursion should be seen as a low-level[^low] operation and avoided if at all possible**.  The better path is to take a survey of the available higher-order functions and plug them together to create new functions.  We go through great pains to stress this in [Joy of Clojure](http://joyofclojure.com), so I will not retrace well-trodden ground.  Instead, observe the following:

    (def S '[a a a a b c c a a d e e e e])
    
    (def pack (partial partition-by identity))
    
    (pack S)
    ;;=> ((a a a a) (b) (c c) (a a) (d) (e e e e))
    
    (def rle (comp (partial map (juxt count first)) pack))
    
    (rle S)
    ;=> ([4 a] [1 b] [2 c] [2 a] [1 d] [4 e])
    
    (def rld (partial mapcat (partial apply repeat)))
    
    (-> S rle rld)
    ;;=> (a a a a b c c a a d e e e e)

As a nice bonus, the definitions[^creds] above are point-free.  

:f

[^creds]: Much thanks to [Christophe Grand](http://clj-me.cgrand.net/), [Alan Malloy](https://github.com/amalloy), and [Alan Dipert](http://alan.dipert.org/) for the ideas motivating[^cough] this post.

[^cough]: That is, I stole their ideas and made them point-free. cough. cough.

[^kritic]: Critic is probably not a fair statement.  A better term would be kritik.

[^low]: Low in respect to levels of abstraction and also in much the same sense as hedonism is low.

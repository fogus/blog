Thanks to some nice work by [Bobby Calderwood](http://twitter.com/bobbycalderwood) ClojureScript now has Atom validators and watchers.  Some commentary below:

### Setting a validator

Placing a validator on an Atom is as easy as:

    (def A (atom 0 :validator 
                   (fn [new-val] 
                     (== 0 new-val))))
    @A
    ;=> 0
    
    (swap! A inc)
    ;; AssertionError
    
    @A
    ;=> 0

As you see, failing validation means that the value in the Atom is not updated.

### Setting a watch

Setting a watch function is just a easy:

    (def A (atom 0))
    @A
    ;=> 0
    
    (add-watch A :watch-change (fn [key a old-val new-val] 
                                 (assert (== 0 new-val))))
    
    (swap! A inc)
    ;; AssertionError
    ;=> 1
    
    @A
    ;=> 1

As you see, a failure in a watch function does not prevent the Atom value from changing.  You can also remove a watch function from an Atom by referring to its provided key (in this case `:watch-change`):

    (remove-watch A :watch-change)
    (swap! A inc)
    ;=> 3

The cool part about this new ClojureScript functionality is that it has already existed in Clojure for *every* reference type.  Since ClojureScript only has Atoms, they are the only things providing a watch/validator capability.

:F

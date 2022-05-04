For various reasons I've taken it upon myself to learn Ruby and I thought I would share a humdinger.

Implementing functions checking for odd-ness and even-ness is often held as an example for illustrating mutual function recursion, and the same can be illustrated with Ruby:

    def e?(n)
      if n.zero?
        true
      else
        o?(n.abs - 1)
      end
    end
    
    def o?(n)
      if n.zero?
        false
      else
        e?(n.abs - 1)
      end
    end

And these functions will work mostly as expected:

    o? 201
    #=> true
    
    e? 991
    #=> false

However, you run into stack issues when attempting to check larger values:

    e? 20000
    # SystemStackError: stack level too deep

The simplest way to ensure that the stack is maintained is by avoiding the stack altogether.  We can do this by re-writing `o?` and `e?` to wrap the mutually recursive calls in a proc taking no args:

    def e?(n)
      if n.zero?
        true
      else
        lambda {| | o?(n.abs - 1) }
      end
    end
    
    def o?(n)
      if n.zero?
        false
      else
        lambda {| | e?(n.abs - 1) }
      end
    end

Making the new call as you might expect returns a `Proc`:

    o? 3
    #=> #<Proc:0x00000001011be868>

So what do we do with this beast?  Well, the simple thing to do is to manually check for a "callable"[^call] thing and call it iteratively; otherwise, we use the first "non-callable" thing as the final result.  Or, we can write something called a trampoline[^tramp] to do those steps for us:

    class Tramp
      def self.new
        raise "Cannot create instances of #{self.inspect}"
      end
        
      def self.oline(fun, *args)
        ret = fun.call(*args)
    
        while (ret.is_a? Proc) or (ret.is_a? Method)
          ret = ret.call
        end
    
        ret
      end
    end

And now, we can check larger numbers without blowing the stack:

    Tramp.oline(method(:e?), 20000)
    #=> true
    
    Tramp.oline(lambda {| | o? 200000 })
    #=> false

Fun, fun, fun.

:f

[^call]: I'm new to Ruby, so bear with me when I use incorrect terms.  Better yet, post a comment to let me know.

[^tramp]: I implemented in this way for various reasons, but mostly because I wanted to test out some orthogonal features of Ruby.

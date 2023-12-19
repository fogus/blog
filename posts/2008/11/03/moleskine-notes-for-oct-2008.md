fogus.jaka
- Jaka
- Closure
- Continuation
- Environment
- Function
- Type
- Kernel
- Repl
- Pair
- Lambda


fogus.jaka.meta
- Mop
 
fogus.jaka.types
- Atom
- Integer
- Float
- Symbol

fogus.jaka.comp
- Compiler


> Originally you choose one of three doors which have equally randomized probabilities. You have on average a 1/3 chance of being right. Consequently, the chance that you are wrong is 2/3. If you stick with that original choice, then nothing that happens afterward changes those probabilities. In other words, the chance of the winning door being ONE OF THE OTHER TWO is 2/3 - regardless of what happens next.


> When Monty Hall shows you a losing door from these other two, he is helping you enormously. He is effectively saying, "Look, the chance of the prize being behind one of these two doors (of the three) is 2/3, and I'm going to help you by showing you one of them which ISN'T the winner." After he has done this, the probability of your original door being the right one doesn't suddenly jump to 50%, when it was only 33.3% beforehand. Why on earth should it? Your original choice was one out of three. If you played the game 100 times and stuck with the original choice you'd end up being right about 33 times, REGARDLESS of whether Monty Hall showed you what was behind any one, two or all of the doors. To repeat, you chose one of three doors originally, and if you stick with that choice you have a one in three chance of being right. End of story.

~~~
With 3 doors, each door starts with a 1/3 chance.
p(DoorA) = 33%, p(DoorB) = 33%, p(DoorC) = 33%.

Or put another way... 
If you pick A: 
    p(DoorA) = 33%, p(DoorB or DoorC) = 67%.
Remove DoorB and the goat behind it and you're left with: 
    p(DoorA) = 33%, p(DoorC) = 67%.
~~~

> In the present problem Monty Hall is giving you additional information about the distribution of probabilities by showing you a "losing" door. 


> That's why the million door analogy is so helpful in clarifying. You started with a freakin huge probability that you picked the wrong door And only a 1/1,000,000 chance that you picked the right door. If Monty then removes doors with goats behind them until their are only two doors left, the one you originally picked and one other (the other being the one door he's been avoiding picking). The odds that you originally picked the right door are still 1 in a million, the odds that the one other door is the correct door are 999,999/1,000,000. So given those odds you should probably switch doors when given the opportunity. I certainly would.



Clojure Classifier (from somewhere on pastebin)
================================================
<pre lang="lisp">
(def good "c:/users/xxxx/desktop/datasets/goodemails.txt")
(def spam "c:/users/xxxx/desktop/datasets/spam.txt")
(def spam? "c:/users/xxxx/desktop/datasets/spamtest.txt")

(defn count-words [file]
  (reduce 
    (fn [occ w] 
      (if (> (count w) 4) 
        (assoc occ w 1) 
        (merge-with + occ {w 1}))) {} (split file " ")))

(defn most-common [n tuples]
    (keys (take n (sort #(compare (val %2) (val %1)) tuples))))

(defn count-word [x xs]
    (count (filter (fn [y] (= y x)) xs)))

(defn count-all-words [set lst]
    (map #(count-word % lst) set))

(defn classify [good spam test accuracy]
    (let [a (most-common accuracy (count-words (slurp good)))
	  b (most-common accuracy (count-words (slurp spam)))
	  c (splt (slurp test) " ")]
        (let [goodCount (sum (count-all-words a c)) 
	      badCount  (sum (count-all-words b c))]
	    (/ badCount (+ badCount goodCount)))))
</pre>

TODO
====
1.  Object-literal syntax for arrays and hashes
2. Array slicing and other intelligent collection operators
3. Perl 5 compatible regular expression literals
4. Destructuring bind (e.g. x, y = returnTwoValues())
5. Function literals and first-class, non-broken closures
6. Standard OOP with classes, instances, interfaces, polymorphism, etc.
7. Visibility quantifiers (public/private/protected)
8. Iterators and generators
9. List comprehensions
10. Namespaces and packages
11. Cross-platform GUI
12. Operator overloading
13. Keyword and rest parameters
14. First-class parser and AST support
15. Static typing and duck typing
16. Type expressions and statically checkable semantics
17. Solid string and collection libraries
18. Strings and streams act like collections
19. Modify comment headers
20. Add .hash function to strings
21. @idx() command busted (maybe use ix_list_progn_index_get_cmd)
22. Rename type_atom to type_symbol
23. Move ix_type_cmd somewhere else
24. Drawn the memory map (indexes, etc...)
25. Make things static that should be static
26. inst#class -> ix_oo_inst_class_get
27. Create mindmap ala CLojure
28. Implement the [Thrush permuting combinator](http://github.com/raganwald/homoiconic/tree/master/2008-10-30/thrush.markdown)
29. Create rudimentary ns hack to load things like ext math
30. Make min/max work on lists also
31. Change after, around, before, primary to be decls in func_messages.[ch]

> The most specific event can serve as a general example of a class of events.

Potential Clojure Projects
===========================
1.  Version Spaces
2.  Workflow engine
3.  Simple oo system

> Ix should be (more) orthogonal

~~~
for([?i in range(1 10)]
     if([odd?( ?i)]
         out("Odd " ?i crlf)
     else
         out("Even " ?i crlf)))
~~~

~~~
(wf/wf
  (wf/act
     (wf/put foo 2)

     (if (= (wf/get bar) 3)
       (wf/end)
       (wf/conc
        (dosomething (wf/get baz))
        (if (something02 (wf/get fiz))
          (something03))))))
~~~

<pre lang="prolog">
% Unprogramming a permutation machine

stack([],[],[]) --> [].
stack([push|Instrs],[I|IS],SS) --> stack(Instrs,IS,[I|SS]).
stack([pop|Instrs],IS,[S|SS]) --> [S], stack(Instrs,IS,SS).

% ?- phrase(stack(Instructions, [1,2,3,4,5], []), [1,2,4,3,5], []).
% Instructions = [push, pop, push, pop, push, push, pop, pop, push, pop] .
</pre>

<http://rapidshare.com/files/158735881/clips6.3_CERN_Linux4.tar.gz.html>

-m

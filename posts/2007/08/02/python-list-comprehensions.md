In a nutshell...<br/>
<code><pre>
>>> firstTen = [x for x in range(1,10)]
>>> firstTen
 [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]

>>> evens = [x for x in firstTen if x % 2 == 0]
 [2, 4, 6, 8, 10]

>>> squares = [[x,x*x] for x in evens]
 [[2, 4], [4, 16], [6, 36], [8, 64], [10, 100]]

>>> table = [[x,y] for x in range(0,1) for y in range(0,1)]
 [[0, 0], [0, 1], [1, 0], [1, 1]]
</pre></code>
-m
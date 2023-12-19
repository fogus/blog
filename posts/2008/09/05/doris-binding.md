I have been playing around with the parsing routines for the [Doris Project](/projects/doris) and have touched on something 
that I like.  To bind variables in the Doris environment, one can do the following:

~~~
# Bind _num to a number
var _num <- 1
_num
1

# Bind _expr to the results of an expression
var _expr <- cat("first part" " and " "second part")
_expr
"first part and second part"

# Bind _lst to a list
var _lst <- 1 2 3
_lst
(1 2 3)

# Bind _lst2 to a list
var _lst2 <- list(1 2 3)
_lst2
(1 2 3)

#Bind _lst3 to a nested list
var _lst3 <- 1 2 list(3 4) int(5.0)
_lst3
(1 2 (3 4) 5)

~~~

-m

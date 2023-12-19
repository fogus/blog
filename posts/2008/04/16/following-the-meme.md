<pre>
~/ $ history|awk '{a[$2]++} END{for(i in a){printf "%5d\t%s \n",a[i],i}}'|sort -rn|head
   95   webgen 
   63   cd 
   44   svn 
   42   ls 
   37   clear 
   31   make 
   27   emacs 
   25   ./broccoli 
   22   scp 
   19   portal 
</pre>

webgen generates <a href="/projects/">my projects site</a>, Subversion is my source repository of choice, make is used to build <a href="/projects/broccoli/">Broccoli</a>, emacs is my editor of choice, broccoli is the Broccoli interpreter, scp is how my website gets updated, portal is a script to log me into my company project web server.  

-m

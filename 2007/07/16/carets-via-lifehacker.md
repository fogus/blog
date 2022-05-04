<a href="http://lifehacker.com/software/version-control/use-the-caret--to-manage-file-versions-278587.php">Use the caret (^) to manage file versions</a><br/>
<span style="font-style:italic;">What is wrong with this picture?</span><br/>
Here is what it looks like on my system:<pre>
foo^1
foo^10
foo^11
...
foo^2
foo^20
foo^21
...
foo^3
...
</pre>
<span style="font-weight:bold;">YUCK!</span><br/>
Instead, try pre-pending zeros after the carets... but stay consistent.<br/>
foo^001, foo^002, ..., foo^010, ..., foo^020, ...<br/>
-m
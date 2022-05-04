At the command prompt type:
<br />ssh -L [local port]:127.0.0.1:[remote port] [user]@[remote.host]
<br />
<br /><i>Example: ssh -L 5900:127.0.0.1:5900 modus@70.255.0.100</i>
<br />
<br />Next, simply point your VNC client to 127.0.0.1:5900
<br />
<br />The -g switch on the ssh command will allow other machines to log in remotely through your machine.
<br />
<br />-m
<br />
<B>How to install nVidia drivers in Linux with the Geforce video card:</B><BR><BR>From your preferred shell run:<BR><CODE>wget http://download.nvidia.com/XFree86/Linux-x86/1.0-4363/NVIDIA-Linux-x86-1 .0-4363.run</CODE><BR><BR>Type <CODE>su</CODE> and type your root password.<BR><BR>Run <CODE>sh NVIDIA-Linux-x86-1.0-4363.run</CODE> and follow any prompts.<BR><BR>Modify /etc/X11/XF86Config in the following ways:<BR>&nbsp; 1) Change <CODE>Driver "nv"</CODE> to <CODE>Driver "nvidia"</CODE><BR>&nbsp; 2) Remove the lines <CODE>Load "dri"</CODE> and <CODE>Load "GLcore"</CODE> if they are present<BR><BR>Type <CODE>exit</CODE> to get out of the root account.<BR><BR>Run <CODE>startx</CODE> and X should work perfectly.<br/> -m
For those of you unfamiliar with my habits, I am in the process of developing my own operating system during my hobby hours (or minutes recently).  The preliminary results have been promising as I have been able to implement a base kernel with kprint, kmalloc and kfree (with flat mm), pic init, a rudimentary HAL, and some entropy/random routines.  The real problem so far has not been the progress but rather the lack of vision, therefore I will take a few minutes to list out some goals and cool features to provide via my OS.

1. I grew up on the Commodore 64 and loved every minute of it.  Among my favorite features were the peek and poke commands which allowed you to set and get values of memory locations from within BASIC programs.  These functions were of course completely unprotected, thus allowing you to put any byte into any <a href="http://www.tkk.fi/Misc/cbm/docs/c64-memorymap.html">memory location</a> (including video memory which would cause interesting environment changes to occur).  As a kid these were invaluable learning tools as I could peek and poke around, mess thing up, and then switch on and off and be back to square one in 3 seconds.  Which leads me to my next goal&#8230;

2. The C64 was an instant-on machine.  As soon as the swich went down the BASIC shell was up waiting for input.  I would love to get this type of response, but it is probably pie in the sky.  However, with the help of a fast loading kernel and some fast firmware to help out (<a href="http://www.linuxbios.org/index.php/Main_Page">LinuxBIOS</a> perhaps).

3. I would love to create an OS able to pool high quality entropy for use in crypto functions.  Currently, I have a few hooks in the HAL layer to pull entropy from hardware sources (e.g. the Via C3 and C7 chips) where appropriate and to also supplemet that pool with very good random number generators.  An ultimate goal of the OS is to feed as much non-deterministic entropy into the pool as possible.  Some possibilities might be: user key click timings, realtime clock and reference oscillator jitter, cpu temp. etc&#8230;)

4. HAL.  I love hardware abstraction layers and the problems they prsent in design and implmentation.  I am less interested in providing portability as I am for figuring out how to provide portability.  The journey is the reward.

	That should provide a nice start to getting a fairly complete goal set under wraps.  I will go now and try to noodle my way through these points and expand on them.

	-m
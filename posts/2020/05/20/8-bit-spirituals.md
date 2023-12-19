An 8-bit Spiritual (8BS) is a modern program that hearkens back to a time when applications provided a robust experience but fit onto a single floppy disk. Modern apps are bloated by comparison, but there are applications that fit the spirit of the 8-bit days.

That said, a modern 8BS should provide a minimal set of modern features: unicode support and Internet and/or WWW support (where appropriate) seem like a minimal set.

I don't want to limit the size of the eventual binary or linked libs because limiting to 8-bit era floppy sizes is not necessary at this point. I think modern-system-relative "small" is acceptable. Also, 8BSes are programming language agnostic and both VM-bound and direct hardware applications are fair game. 8BS is a state of mind so to speak.

For now, an example of the kinds of applications that I would be interested in WRT 8-bit Spirituals are as follows...

# Programs of Yore

Before I point out a few examples of modern programs that fit the 8BS mold I'd like to take a moment to talk about a few interesting examples. Certainly, ANY program from the 8-bit era fits the literal definition of a constrained environment program by default but only a small subset could be representational of the type of program that I'm after for this post. The programs below were all constrained -- but they were never constraining.

## KAMAS

KAMAS was ground-breaking outliner software originally designed for the [Kaypro II ](http://oldcomputers.net/kayproii.html) (eventually finding its way onto other systems) that provided a full-fledged editor, BBS system, and internal programming language interpreter -- all on a 191K floppy disk.

<a href="http://blog.fogus.me/wp-content/uploads/2020/05/kamas.png"><img src="http://blog.fogus.me/wp-content/uploads/2020/05/kamas-300x213.png" alt="" width="300" height="213" class="aligncenter size-medium wp-image-6665" /></a>

I'm somewhat of a fan of the KAMAS software, even though I'm only able to run a [truncated](https://www.pcorner.com/list/WORDP/KAMAS25.ZIP/INFO/) [^kp2] version on the [DOSBox emulator](https://www.dosbox.com). Even minus the more advanced features found on the Kaypro versions, KAMAS for DOS is a solid application; however with those advanced features the program was a [powerful digital workspace](https://archive.org/search.php?query=subject%3A%22kamas%22&and[]=mediatype%3A%22texts%22&and[]=year%3A%22-1%22).

[^kp2]: If I could find a workable copy of the original Kaypro II disks then I would immediately grab an old dusty system to run it on. Alas, the KAMAS software has turned out more difficult to find than the computer to run it on.

## Electric Pencil

Electric Pencil was the first program to implement word processing-like features and ran on the MITS Altair on 8K of memory. 

<a href="http://blog.fogus.me/wp-content/uploads/2020/05/ep.jpg"><img src="http://blog.fogus.me/wp-content/uploads/2020/05/ep.jpg" alt="" width="280" height="210" class="aligncenter size-full wp-image-6666" /></a>

While certainly not at the level of many word processors that came after it, Electric Pencil set the pace for many of its successors. Indeed, many retrocomputerists consider the now defunct Word 5.1 for the Macintosh the pinnacle of word processing.

<a href="http://blog.fogus.me/wp-content/uploads/2020/05/pce-mac-word51.png"><img src="http://blog.fogus.me/wp-content/uploads/2020/05/pce-mac-word51-300x200.png" alt="" width="300" height="200" class="aligncenter size-medium wp-image-6675" /></a>

I was never fortunate enough to use [Word 5.1](https://winworldpc.com/product/microsoft-word/5x-mac) but it certainly seems to shine as an example of an application that an 8BS should shoot for.

## Turbo Pascal

Turbo Pascal was originally released on the 8-bit CP/M OS and contained an IDE and compiler that could run programs in memory or compile them blazingly fast -- all for $35.[^e35]

<a href="http://blog.fogus.me/wp-content/uploads/2020/05/tp.jpg"><img src="http://blog.fogus.me/wp-content/uploads/2020/05/tp-300x188.jpg" alt="" width="300" height="188" class="aligncenter size-medium wp-image-6667" /></a>

Pascal was a great language to the 8-bit era and amenable to fast compilation but the Turbo Pascal software is famous for being a shining example of Anders Hejlsberg's programming prowess.[^pas]

[^e35]: It was eventually sold for $35 but was released for $49.99.

[^pas]: Pascal of the time could be scanned, parsed, optimazed, and code-gen'd in a single compiler pass.

# Modern 8BSes

Having shown a few examples that act as goalposts for the modern 8BSes, I'll offer a few examples of modern programs that hit the mark set in the beginning of this post.

## Redis

Something like pre-clustering Redis is a decent candidate. Certainly it's not a floppy-sized program but it did a lot given its small footprint. While it's grown in scope over time, the original vision was a sleek data-structures database that performed well, was quite stable, and was even easy to understand. 

## Frink

[Frink](https://frinklang.org) is a programming language unlike any other. It is generally known for its ability to handle and convert between a bevy of units of measure consistently throughout calculations. For anyone who has created a complex system (or even a simple one for that matter) that needed to juggle varying units of measure, the complexity of the problem that Frink handles seamlessly is staggering.

    // Levenshtein distance is a built-in
    println[editDistance["kitten","sitting"]]
    //=> 3
    
    // Convert feet to meters
    38 feet -> meters
    //=> 7239/625 (exactly 11.5824)

Frink requires a JVM, which is as far from an 8BS as you can get. However, VM aside "the Frink programming language including all its reference data, GUI, and graphics, can ship in less than 500 kilobytes (pack200 compression)."[^ae]

[^ae]: As [explained to me](https://twitter.com/aeliasen/status/1261031751431761920?s=20) by Alan Eliasen - Frink's creator.

## Dwarf Fortress

[Dwarf Fortress](http://bay12games.com/dwarves/) is another interesting candidate.

<a href="http://blog.fogus.me/wp-content/uploads/2020/05/EYAGN1XWoAUl_ss.png"><img src="http://blog.fogus.me/wp-content/uploads/2020/05/EYAGN1XWoAUl_ss-291x300.png" alt="" width="291" height="300" class="aligncenter size-medium wp-image-6679" /></a>

It's a world-builder / rougelike / simulation game that is obsessive in its approach to detail -- despite its textual graphics. The game has maintained a strict vision of providing strong-simulative gameplay by its designers Tarn and Zach Adams. 

## PuTTY

The final program that I'll nominate is the workhorse terminal emulator [PuTTY](https://www.chiark.greenend.org.uk/~sgtatham/putty/). After 20+ years of development, PuTTY is still a 3 MB install and provides a useful set of functionality. When you need the functionality that PuTTY provides you REALLY need it to run well and right away. PuTTY has been meeting this need year after year and has been rock-solid all along the way.


## 8BS systems

Before I end I should point out the existence of hardware 8BSes. I suspect that the Pi, Arduino, ESP32, etc. communities have a ton of examples of viable 8-bit Spirituals to learn from. More exploration needed on my part and perhaps a future post will tackle them in more detail -- though pointers and info about them is always welcomed in the comments below.

# Finis

That's all that I have for now. The 8-bit era has passed us by and is not likely to ever return in the realm of the general-purpose user-centric application. However, the *spirit* of the 8-bit system is still something that we can strive for and sing the praises of. The question is of course -- should we?

:F

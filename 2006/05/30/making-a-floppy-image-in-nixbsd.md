<p>If you want to create a filesystem in a floppy image, using FAT, and you have a copy of a UNIX-like system around (such as Linux), you can actually do this quite easily:</p>
<pre>$ dd if=/dev/zero of=test_floppy_image bs=512 count=2880
2880+0 records in
2880+0 records out
1474560 bytes (1.5 MB) copied, 0.022472 seconds, 65.6 MB/s
$ mkfs.vfat -n 'Test Floppy' test_floppy_image
mkfs.vfat 2.11 (12 Mar 2005)
$ file test_floppy_image
test_floppy_image: x86 boot sector, mkdosfs boot message display, code offset 0x3c, OEM-ID " mkdosfs", root entries 224, sectors 2880 (volumes < =32 MB) , sectors/FAT 9, serial number 0x447b86f7, label: "Test Floppy", FAT (12 bit)
$</pre>
<p>You are then able to mount that and use it directly with the system, and read/write it as if it were a real floppy. No drivers, no special programs required &#8212; and then, when you are ready, you can move the image to a<br />

computer with a floppy drive and then use &#8216;dd&#8217; (or &#8216;cat&#8217;, even) to write the image to a floppy disk.</p>
<p>Note that this example is for a 1.44 MB floppy, which has 2,880 512-byte sectors. If you are using a different floppy format, adjust accordingly.<br />
-m</p>

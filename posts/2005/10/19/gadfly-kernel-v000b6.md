<p>Over the past week I have found some insane motivation for working on my OS kernel. In the past, I have developed my bootloader and a simple kernel entry point, but progress since that point has been scarce. Over the past few weeks however, I have managed to incorporate a hardware abstraction layer (HAL), kprint functions, hardware assisted random number generation, and a partial port to PowerPC. Specifically, the progress timeline looks something like this:</p>
<p><strong>Version 0.00a0 (x86)</strong><br />
Created bootloader hook into kmain<br />
Created memcpy and memset functions<br />
Created Ant build script</p>

<p><strong>Version 0.00a6 (x86)</strong><br />
Created preliminary HAL hooks for text video access including hadrware cursor control, clear screen, and putc<br />
Built kernel-level putc calling HAL_putc and test calls<br />
Created std_args macros</p>
<p><strong>Version 0.00a9 (x86)</strong><br />
Created preliminary kprintf handling %s, %c, and %% flags<br />
Put together the first cut HAL prototypes for interrupts, memory, cpu, and crypt</p>
<p><strong>Version 0.00b4 (x86)</strong><br />
Added kprintf handling for %d, %i, %u, and %x<br />

Added HAL_get_entropy method to the HAL_crypt interface to retrieve hardware entropy<br />
Started on first-cut memory allocator</p>
<p><strong>Version 0.00b6 (x86/ppc)</strong><br />
Created first-cut at ppc bootloading using an explicit load from OpenFirmware. The base kernel runs, but I have not filled in the video HAL operations, so it is a bit of a hack at the moment</p>
<p><strong>Upcomming Version Todo</strong><br />
Finish kmalloc and kfree<br />
Implement a kernel-level entropy pool and access functions<br />
Get the full HAL ported to ppc</p>
<p>If anyone is interested in checking out the latest version, then email me and I will send a disc image.</p>

<p>-m
</p>
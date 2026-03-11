# Garbage Collection

books
* Richard Jones, Eliot Moss, Antony Hosking. **垃圾回收算法手册：自动内存管理的艺术** (The Garbage Collection Handbook: the Art of Automatic Memory Management). 机械工业出版社, 2016.
* [HotSpot Virtual Machine Garbage Collection Tuning Guide](https://docs.oracle.com/javase/8/docs/technotes/guides/vm/gctuning/title.html): Java SE 8 HotSpot虚拟机垃圾收集调优指南
* [Memory Management in the Java HotSpot™ Virtual Machine](https://www.oracle.com/technetwork/java/javase/tech/memorymanagement-whitepaper-1-150020.pdf): HotSpot内存管理白皮书
* CMS: Printezis, T. and Detlefs, D. 2000. **A generational mostly-concurrent garbage collector**. In Proceedings of the 2nd international Symposium on Memory Management (Minneapolis, Minnesota, United States, October 15 - 16, 2000)
* G1: Detlefs, D., Flood, C., Heller, S., and Printezis, T. 2004. **Garbage-first garbage collection**. In Proceedings of the 4th international Symposium on Memory Management (Vancouver, BC, Canada, October 24 - 25, 2004).<br>

# Implementations

## G1
* [The Garbage First Garbage Collector](https://www.oracle.com/java/technologies/javase/hotspot-garbage-collection.html)
  * [JEP 248: Make G1 the Default Garbage Collector](https://openjdk.java.net/jeps/248)

> HotSpot Virtual Machine > Garbage Collection Tuning > 9 Garbage-First Garbage Collector


### Heap Layout

G1 partitions the heap into a set of equally sized heap **regions**, each a contiguous range of virtual memory as shown in Figure 9-1. 
A region is the unit of memory allocation and memory reclamation. 
At any given time, each of these regions can be empty (light gray), or assigned to a particular generation, young or old. 
As requests for memory comes in, the memory manager hands out free regions. 
The memory manager assigns them to a generation and then returns them to the application as free space into which it can allocate itself.

![Figure 9-1 G1 Garbage Collector Heap Layout](https://docs.oracle.com/en/java/javase/11/gctuning/img/jsgct_dt_004_grbg_frst_hp.png)

The young generation contains eden regions (red) and survivor regions (red with "S"). These regions provide the same function as the respective contiguous spaces in other collectors, with the difference that in G1 these regions are typically laid out in a noncontiguous pattern in memory. 
Old regions (light blue) make up the old generation. Old generation regions may be humongous (light blue with "H") for objects that span multiple regions.

An application always allocates into a young generation, that is, eden regions, with the exception of humongous objects that are directly allocated as belonging to the old generation.

G1 garbage collection pauses can *reclaim space in the young generation as a whole*, and *any additional set of old generation regions at any collection pause*.
During the pause G1 **copies** objects from this collection set to one or more different regions in the heap. 
The destination region for an object depends on the source region of that object: the entire young generation is copied into either survivor or old regions, and objects from old regions to other, different old regions using aging.

### Garbage Collection Cycle

On a high level, the G1 collector alternates between two phases. 

- The **young-only** phase contains garbage collections that fill up the currently available memory with objects in the old generation gradually. 
- The **space-reclamation** phase is where G1 reclaims space in the old generation incrementally, in addition to handling the young generation. Then the cycle restarts with a young-only phase.

Figure 9-2 gives an overview about this cycle with an example of the sequence of garbage collection pauses that could occur:

![Figure 9-2 Garbage Collection Cycle Overview](https://docs.oracle.com/en/java/javase/11/gctuning/img/jsgct_dt_001_grbgcltncyl.png)

The following list describes the phases, their pauses and the transition between the phases of the G1 garbage collection cycle in detail:

- (1) Young-only phase
This phase starts with a few **Normal** young collections that promote objects into the old generation. 
The *transition* between the young-only phase and the space-reclamation phase starts *when the old generation occupancy reaches a certain threshold*, the **Initiating Heap Occupancy threshold**. At this time, G1 schedules a **Concurrent Start** young collection instead of a **Normal** young collection. 

- (1.1) Concurrent Start
This type of collection starts the *marking process* in addition to performing a **Normal** young collection. 
Concurrent marking determines all currently reachable (live) objects *in the old generation regions* to be kept for the following space-reclamation phase. 
While collection marking hasn’t completely finished, **Normal** young collections may occur. 
Marking finishes with two special *stop-the-world pauses*: Remark and Cleanup. 

- (1.2) Remark
This pause finalizes the marking itself, performs *global reference processing* and *class unloading*, *reclaims completely empty regions* and *cleans up internal data structures*. 
Between Remark and Cleanup G1 *calculates information* to later be able to reclaim free space in selected old generation regions concurrently, which will be finalized in the Cleanup pause.

- (1.3) Cleanup
This pause *determines whether a space-reclamation phase will actually follow*. 
If a space-reclamation phase follows, the young-only phase completes with a single **Prepare Mixed** young collection. 

- (2) Space-reclamation phase
This phase consists of multiple *Mixed collections* that in addition to young generation regions, also *evacuate live objects of sets of old generation regions*. 
The space-reclamation phase *ends when* G1 determines that evacuating more old generation regions wouldn't yield enough free space worth the effort.


After space-reclamation, the collection cycle *restarts with another young-only phase*. 
As backup, if the application runs out of memory while gathering liveness information, G1 performs an in-place stop-the-world **full heap compaction (Full GC)** like other collectors.

### Young-Only Phase Generation Sizing

During the young-only phase, the set of regions to collect (*collection set*), consists only of young generation regions. 
G1 always sizes the young generation at the end of a normal young collection for the next mutator phase. 
This way, G1 can meet the pause time goals that were set using `-XX:MaxGCPauseTimeMillis` and `-XX:PauseTimeIntervalMillis` based on long-term observations of actual pause time. 
It takes into account how long it took young generations of similar size to evacuate. 
This includes information like how many objects had to be copied during collection, and how interconnected these objects had been.

If not otherwise constrained, then G1 adaptively sizes the young generation size between the values that `-XX:G1NewSizePercent` and `-XX:G1MaxNewSizePercent` determine to meet pause-time. 
See Garbage-First Garbage Collector Tuning  for more information about how to fix long pauses.

### Space-Reclamation Phase Generation Sizing

During the space-reclamation phase, G1 tries to *maximize the amount of space that's reclaimed in the old generation in a single garbage collection pause*. 
The size of the young generation is set to minimum allowed, typically as determined by `-XX:G1NewSizePercent`, and any old generation regions to reclaim space are added until G1 determines that adding further regions will exceed the pause time goal. 
In a particular garbage collection pause, G1 adds old generation regions *in order of their reclamation efficiency, highest first*, and the remaining available time to get the final collection set.

The number of old generation regions to take per garbage collection is bounded at the lower end by the number of potential candidate old generation regions (*collection set candidate regions*) to collect, divided by the length of the space-reclamation phase as determined by `-XX:G1MixedGCCountTarget`. 
The collection set candidate regions are all old generation regions that have an occupancy that's lower than `-XX:G1MixedGCLiveThresholdPercent` at the start of the phase.

The phase *ends when* the remaining amount of space that can be reclaimed in the collection set candidate regions is less than the percentage set by `-XX:G1HeapWastePercent`.

See Garbage-First Garbage Collector Tuning for more information about how many old generation regions G1 will use and how to avoid long mixed collection pauses.



## C4
* C4: Gil Tene, Balaji Iyengar, and Michael Wolf. 2011. **C4: the continuously concurrent compacting collector**. In Proceedings of the international symposium on Memory management (ISMM '11). Association for Computing Machinery, New York, NY, USA, 79–88. https://doi.org/10.1145/1993478.1993491
## ZGC
* ZGC: Albert Mingkun Yang and Tobias Wrigstad. 2022. **Deep Dive into ZGC: A Modern Garbage Collector in OpenJDK**. ACM Trans. Program. Lang. Syst. Just Accepted (May 2022). https://doi.org/10.1145/3538532
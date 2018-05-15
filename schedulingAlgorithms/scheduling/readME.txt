README

This program was created in my operating systems course at cal poly pomona. Was to mimic how operating systems complete 
different tasks or jobs using different scheduling algorithms, such as Round Robin, or Shortest Job first.



The program was created on the ECLIPSE IDE using java.

To RUN---> In ECLIPSE just click the run button
 
Program will display Gantt charts for each algorithm against the given input data.

EXAMPLE RESULT:

"0 [Job201 ] 14" ---- Job201 starts at 0 seconds and finished at 14 seconds. 

*Note for Round Robin
Example for Round Robin output

0 [Job201] 3[Job202] 5 [Job203] 8 [Job204] 11 [Job205] 14[Job206] 15 [Job207] 18 [Job208] 21[Job209] 24 [Job210] 27 [Job211] 30
30 [Job201] 33 [Job203] 36 [Job204] 39 [Job205] 42 [Job207] 45 [Job208] 48 [Job210] 51 [Job211] 54

A new line represents a new round for in the round robin algorithm.
So any jobs that still have a burstime > 0 will be interated thru, otherwise they will be skipped.

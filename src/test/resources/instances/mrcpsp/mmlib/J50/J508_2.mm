jobs  (incl. supersource/sink ):	52
RESOURCES
- renewable                 : 2 R
- nonrenewable              : 2 N
- doubly constrained        : 0 D
************************************************************************
PRECEDENCE RELATIONS:
jobnr.    #modes  #successors   successors
1	1	20		2 3 4 5 6 7 8 10 11 12 13 14 15 16 20 21 24 30 31 33 
2	3	9		51 48 47 44 29 27 23 22 18 
3	3	5		49 48 27 19 9 
4	3	5		47 32 29 18 17 
5	3	6		51 44 41 40 23 18 
6	3	10		45 44 43 42 41 40 34 29 26 25 
7	3	9		47 46 45 44 42 40 29 28 25 
8	3	4		41 27 23 18 
9	3	11		47 46 44 43 42 41 40 37 36 34 29 
10	3	9		50 49 48 44 43 42 39 37 23 
11	3	6		47 46 44 41 40 18 
12	3	10		50 48 46 45 43 42 41 40 37 29 
13	3	7		45 43 42 41 37 29 27 
14	3	5		44 41 40 38 18 
15	3	10		49 48 43 41 40 39 38 37 36 35 
16	3	9		45 44 41 40 39 38 37 36 34 
17	3	4		41 37 27 23 
18	3	7		45 43 42 39 37 36 34 
19	3	7		46 45 44 42 39 38 35 
20	3	6		46 42 41 40 39 34 
21	3	6		46 44 43 40 37 35 
22	3	5		43 41 38 37 35 
23	3	4		46 38 36 34 
24	3	4		44 42 38 34 
25	3	4		38 37 36 35 
26	3	4		39 37 36 35 
27	3	3		40 36 34 
28	3	3		38 36 34 
29	3	3		39 38 35 
30	3	3		41 37 35 
31	3	2		45 34 
32	3	2		44 36 
33	3	2		40 36 
34	3	1		35 
35	3	1		52 
36	3	1		52 
37	3	1		52 
38	3	1		52 
39	3	1		52 
40	3	1		52 
41	3	1		52 
42	3	1		52 
43	3	1		52 
44	3	1		52 
45	3	1		52 
46	3	1		52 
47	3	1		52 
48	3	1		52 
49	3	1		52 
50	3	1		52 
51	3	1		52 
52	1	0		
************************************************************************
REQUESTS/DURATIONS
jobnr.	mode	dur	R1	R2	N1	N2	
------------------------------------------------------------------------
1	1	0	0	0	0	0	
2	1	2	1	8	9	4	
	2	4	1	6	8	4	
	3	8	1	4	8	2	
3	1	1	8	5	5	5	
	2	2	8	5	4	3	
	3	10	8	4	4	3	
4	1	7	5	10	7	5	
	2	8	4	6	6	4	
	3	9	2	4	6	2	
5	1	3	9	4	5	3	
	2	4	7	2	4	3	
	3	7	5	2	3	3	
6	1	1	6	7	6	9	
	2	2	6	4	4	8	
	3	9	1	4	1	7	
7	1	3	9	7	7	10	
	2	4	8	3	5	7	
	3	9	8	2	5	7	
8	1	1	7	8	9	8	
	2	2	6	5	9	8	
	3	5	5	2	9	8	
9	1	3	4	10	5	7	
	2	6	2	9	5	5	
	3	10	2	7	4	5	
10	1	4	8	6	5	3	
	2	8	7	6	4	2	
	3	10	7	5	4	2	
11	1	5	7	3	3	6	
	2	6	6	2	2	6	
	3	8	5	1	2	5	
12	1	4	9	9	9	5	
	2	7	8	5	8	4	
	3	8	8	3	6	3	
13	1	1	6	3	6	4	
	2	2	4	3	6	3	
	3	10	3	2	5	3	
14	1	5	8	8	6	7	
	2	8	7	7	6	6	
	3	9	2	6	6	4	
15	1	4	4	5	9	3	
	2	6	4	5	7	3	
	3	10	4	2	7	2	
16	1	3	9	6	8	9	
	2	4	4	5	7	8	
	3	7	2	5	5	8	
17	1	1	9	9	6	3	
	2	3	9	9	5	2	
	3	8	7	8	3	2	
18	1	1	5	4	6	5	
	2	6	3	4	5	4	
	3	8	3	3	4	3	
19	1	3	10	4	10	6	
	2	5	8	4	10	5	
	3	6	6	2	10	2	
20	1	3	3	8	7	7	
	2	4	3	6	5	5	
	3	7	2	6	1	1	
21	1	2	1	5	9	6	
	2	4	1	4	9	5	
	3	6	1	4	9	4	
22	1	2	9	5	7	2	
	2	7	9	5	7	1	
	3	9	8	5	6	2	
23	1	2	8	8	7	9	
	2	8	6	5	7	7	
	3	10	6	3	6	7	
24	1	1	8	7	4	8	
	2	4	5	7	3	6	
	3	5	5	3	3	5	
25	1	4	7	8	5	7	
	2	6	5	7	5	7	
	3	7	4	7	3	6	
26	1	4	7	7	7	4	
	2	7	6	4	6	3	
	3	9	5	2	6	2	
27	1	1	5	4	7	9	
	2	6	5	4	5	6	
	3	10	3	3	5	5	
28	1	5	7	8	3	7	
	2	6	6	7	2	3	
	3	7	5	5	2	2	
29	1	6	5	3	7	6	
	2	8	4	3	5	5	
	3	9	4	1	2	4	
30	1	1	1	3	3	7	
	2	3	1	2	2	5	
	3	10	1	2	1	1	
31	1	1	7	10	8	10	
	2	7	7	5	7	6	
	3	9	6	4	5	4	
32	1	1	9	8	9	7	
	2	2	6	7	5	7	
	3	3	6	7	2	5	
33	1	6	7	10	3	10	
	2	8	4	8	2	8	
	3	10	3	6	1	8	
34	1	1	6	5	5	9	
	2	6	4	4	2	7	
	3	9	4	3	1	7	
35	1	1	3	7	3	3	
	2	7	3	4	2	2	
	3	8	1	1	1	2	
36	1	3	4	8	9	5	
	2	8	3	8	6	5	
	3	9	2	8	5	5	
37	1	6	2	8	5	7	
	2	9	2	5	2	5	
	3	10	1	4	2	4	
38	1	4	6	5	6	5	
	2	5	5	3	3	5	
	3	6	5	3	1	5	
39	1	1	10	3	8	5	
	2	5	5	2	3	5	
	3	9	1	1	2	5	
40	1	3	4	10	5	8	
	2	4	3	10	5	5	
	3	9	3	10	4	2	
41	1	7	6	4	9	7	
	2	8	4	3	8	6	
	3	10	3	2	8	6	
42	1	1	4	9	7	2	
	2	2	2	7	6	1	
	3	6	1	6	6	1	
43	1	2	9	10	4	9	
	2	5	5	9	3	8	
	3	8	2	7	1	5	
44	1	3	5	7	7	6	
	2	7	5	4	7	6	
	3	10	4	3	6	2	
45	1	5	7	10	5	6	
	2	6	7	9	3	6	
	3	7	6	9	3	5	
46	1	1	7	9	9	1	
	2	2	6	7	7	1	
	3	7	6	5	7	1	
47	1	3	2	9	6	7	
	2	5	2	9	3	5	
	3	7	2	7	3	4	
48	1	1	6	3	6	5	
	2	5	6	3	5	5	
	3	9	4	3	2	5	
49	1	5	2	10	6	5	
	2	6	2	10	4	4	
	3	9	2	10	4	3	
50	1	2	7	1	9	10	
	2	3	7	1	8	9	
	3	10	7	1	6	7	
51	1	4	9	4	7	8	
	2	6	8	4	5	7	
	3	7	7	4	5	5	
52	1	0	0	0	0	0	
************************************************************************

 RESOURCE AVAILABILITIES 
	R 1	R 2	N 1	N 2
	38	40	239	228

************************************************************************

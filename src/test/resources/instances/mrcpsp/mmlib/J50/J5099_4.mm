jobs  (incl. supersource/sink ):	52
RESOURCES
- renewable                 : 2 R
- nonrenewable              : 2 N
- doubly constrained        : 0 D
************************************************************************
PRECEDENCE RELATIONS:
jobnr.    #modes  #successors   successors
1	1	6		2 3 4 6 7 9 
2	3	3		16 12 5 
3	3	4		19 15 13 8 
4	3	7		27 19 16 14 13 12 11 
5	3	5		19 15 14 11 10 
6	3	6		27 22 19 13 12 11 
7	3	4		19 12 11 10 
8	3	4		27 14 12 11 
9	3	4		27 14 13 12 
10	3	5		27 24 22 20 17 
11	3	5		25 24 23 21 18 
12	3	4		26 23 18 17 
13	3	3		26 24 17 
14	3	3		29 22 21 
15	3	3		25 23 20 
16	3	3		29 24 21 
17	3	2		29 21 
18	3	2		28 20 
19	3	3		30 28 24 
20	3	5		40 32 31 30 29 
21	3	4		40 32 30 28 
22	3	3		32 28 26 
23	3	4		40 37 32 28 
24	3	5		40 38 37 32 31 
25	3	4		38 32 31 30 
26	3	5		40 38 37 34 31 
27	3	3		40 33 29 
28	3	3		38 34 31 
29	3	5		42 38 37 36 34 
30	3	4		47 42 37 34 
31	3	3		46 42 33 
32	3	2		35 34 
33	3	2		47 36 
34	3	3		46 43 39 
35	3	4		47 45 43 41 
36	3	2		43 39 
37	3	2		43 39 
38	3	3		47 45 41 
39	3	2		45 41 
40	3	3		47 45 44 
41	3	3		51 49 44 
42	3	4		51 50 49 48 
43	3	2		49 44 
44	3	2		50 48 
45	3	2		49 48 
46	3	2		50 48 
47	3	1		49 
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
2	1	5	4	0	0	6	
	2	6	2	0	0	2	
	3	7	0	4	2	0	
3	1	7	0	3	7	0	
	2	8	0	2	6	0	
	3	9	0	1	0	3	
4	1	1	0	10	0	8	
	2	7	5	0	5	0	
	3	8	0	5	0	7	
5	1	2	10	0	10	0	
	2	8	0	1	0	9	
	3	9	4	0	0	8	
6	1	5	4	0	0	8	
	2	7	0	6	0	6	
	3	10	0	5	0	2	
7	1	6	8	0	3	0	
	2	7	7	0	2	0	
	3	10	0	2	0	3	
8	1	4	0	5	6	0	
	2	6	3	0	0	3	
	3	9	0	3	0	2	
9	1	3	0	6	8	0	
	2	3	9	0	7	0	
	3	10	0	2	7	0	
10	1	1	5	0	0	4	
	2	3	3	0	7	0	
	3	4	0	8	7	0	
11	1	1	0	7	9	0	
	2	5	0	5	0	8	
	3	6	8	0	9	0	
12	1	1	0	7	6	0	
	2	5	8	0	5	0	
	3	10	8	0	0	3	
13	1	3	5	0	5	0	
	2	8	0	1	0	5	
	3	9	5	0	0	4	
14	1	1	0	6	0	10	
	2	3	7	0	3	0	
	3	5	7	0	0	7	
15	1	3	7	0	8	0	
	2	8	6	0	0	1	
	3	9	5	0	0	1	
16	1	1	5	0	0	7	
	2	4	0	4	0	3	
	3	8	3	0	0	2	
17	1	2	0	8	0	6	
	2	6	0	7	3	0	
	3	7	0	7	2	0	
18	1	4	4	0	5	0	
	2	6	0	5	4	0	
	3	10	2	0	3	0	
19	1	2	5	0	0	6	
	2	6	0	5	4	0	
	3	8	5	0	3	0	
20	1	4	0	5	0	8	
	2	7	0	4	5	0	
	3	9	2	0	4	0	
21	1	2	4	0	8	0	
	2	5	0	8	0	9	
	3	8	1	0	0	9	
22	1	2	6	0	10	0	
	2	3	0	5	5	0	
	3	8	0	2	0	9	
23	1	2	0	10	0	4	
	2	9	5	0	0	4	
	3	9	0	2	3	0	
24	1	4	4	0	0	6	
	2	5	0	8	9	0	
	3	10	3	0	0	4	
25	1	6	8	0	9	0	
	2	6	4	0	0	8	
	3	9	3	0	8	0	
26	1	5	0	1	7	0	
	2	6	0	1	6	0	
	3	9	0	1	4	0	
27	1	4	0	5	5	0	
	2	5	0	5	4	0	
	3	8	0	5	3	0	
28	1	3	0	4	0	6	
	2	8	0	3	0	4	
	3	9	3	0	0	3	
29	1	3	0	7	8	0	
	2	5	8	0	0	5	
	3	10	8	0	0	3	
30	1	1	6	0	6	0	
	2	9	5	0	4	0	
	3	10	5	0	0	6	
31	1	5	9	0	6	0	
	2	5	0	7	0	9	
	3	6	0	7	2	0	
32	1	3	6	0	0	3	
	2	7	0	4	0	3	
	3	8	0	3	4	0	
33	1	8	0	3	9	0	
	2	9	0	1	8	0	
	3	9	8	0	0	5	
34	1	1	7	0	0	7	
	2	3	0	3	0	5	
	3	6	0	1	2	0	
35	1	3	5	0	0	9	
	2	4	4	0	0	6	
	3	5	4	0	0	4	
36	1	2	3	0	0	9	
	2	6	0	5	0	8	
	3	6	2	0	6	0	
37	1	2	0	8	0	3	
	2	3	0	5	0	2	
	3	8	2	0	0	2	
38	1	1	5	0	0	8	
	2	6	3	0	6	0	
	3	10	0	5	4	0	
39	1	1	9	0	9	0	
	2	5	0	7	0	4	
	3	7	0	6	0	3	
40	1	2	0	8	5	0	
	2	5	0	4	5	0	
	3	8	0	3	0	5	
41	1	4	0	9	6	0	
	2	5	0	6	5	0	
	3	10	1	0	0	2	
42	1	2	0	5	3	0	
	2	3	0	4	3	0	
	3	10	0	4	0	5	
43	1	2	0	6	0	2	
	2	3	7	0	0	2	
	3	10	5	0	0	2	
44	1	3	6	0	0	7	
	2	4	4	0	0	4	
	3	10	3	0	2	0	
45	1	6	9	0	0	8	
	2	7	0	4	0	5	
	3	7	9	0	1	0	
46	1	1	8	0	5	0	
	2	1	0	6	2	0	
	3	9	0	6	1	0	
47	1	3	0	6	9	0	
	2	6	0	5	6	0	
	3	10	8	0	0	7	
48	1	2	9	0	5	0	
	2	5	8	0	0	8	
	3	9	0	2	5	0	
49	1	2	9	0	3	0	
	2	3	8	0	3	0	
	3	5	0	8	0	5	
50	1	1	0	1	0	3	
	2	1	0	1	5	0	
	3	8	2	0	5	0	
51	1	3	0	6	7	0	
	2	4	3	0	5	0	
	3	10	2	0	5	0	
52	1	0	0	0	0	0	
************************************************************************

 RESOURCE AVAILABILITIES 
	R 1	R 2	N 1	N 2
	25	31	140	137

************************************************************************

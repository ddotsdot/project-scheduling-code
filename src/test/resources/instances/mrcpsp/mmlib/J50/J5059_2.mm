jobs  (incl. supersource/sink ):	52
RESOURCES
- renewable                 : 2 R
- nonrenewable              : 2 N
- doubly constrained        : 0 D
************************************************************************
PRECEDENCE RELATIONS:
jobnr.    #modes  #successors   successors
1	1	10		2 3 4 5 6 7 10 14 15 20 
2	3	9		29 25 21 19 18 16 12 9 8 
3	3	8		29 24 23 22 19 18 13 11 
4	3	4		19 18 13 9 
5	3	5		29 24 22 19 11 
6	3	5		28 22 21 13 11 
7	3	3		28 16 9 
8	3	4		32 24 23 13 
9	3	2		22 11 
10	3	2		18 13 
11	3	7		51 39 38 36 32 27 17 
12	3	7		51 39 38 36 32 27 17 
13	3	6		51 39 38 36 27 17 
14	3	6		39 38 36 32 29 17 
15	3	6		39 38 36 32 27 17 
16	3	7		51 50 49 40 32 26 22 
17	3	8		50 49 40 37 34 31 30 26 
18	3	7		51 50 49 33 32 30 27 
19	3	5		47 38 31 28 27 
20	3	11		50 49 48 47 46 39 38 37 35 34 33 
21	3	6		50 49 38 35 30 27 
22	3	8		47 46 39 38 37 36 34 33 
23	3	5		47 45 36 34 31 
24	3	5		47 45 44 34 31 
25	3	8		51 49 47 45 44 43 41 38 
26	3	6		48 47 46 43 42 33 
27	3	6		48 46 45 43 37 34 
28	3	5		49 44 39 37 34 
29	3	5		49 44 37 35 34 
30	3	5		47 46 45 44 41 
31	3	3		46 41 35 
32	3	2		45 35 
33	3	3		45 44 41 
34	3	2		42 41 
35	3	2		43 42 
36	3	2		48 41 
37	3	1		41 
38	3	1		42 
39	3	1		43 
40	3	1		44 
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
2	1	2	3	4	9	0	
	2	3	1	3	6	0	
	3	9	1	3	5	0	
3	1	4	3	8	8	0	
	2	5	1	7	0	3	
	3	6	1	6	0	1	
4	1	1	6	9	6	0	
	2	5	5	7	0	3	
	3	10	5	7	2	0	
5	1	3	10	7	0	5	
	2	8	7	4	5	0	
	3	10	2	4	0	3	
6	1	3	3	6	9	0	
	2	4	3	5	5	0	
	3	5	1	5	4	0	
7	1	3	10	7	0	4	
	2	4	7	7	0	3	
	3	9	7	7	0	1	
8	1	3	8	8	5	0	
	2	4	6	6	0	1	
	3	7	6	6	2	0	
9	1	1	5	7	0	5	
	2	2	3	6	6	0	
	3	9	2	6	2	0	
10	1	5	4	3	0	7	
	2	6	3	2	0	6	
	3	10	3	2	0	5	
11	1	5	8	7	0	4	
	2	6	5	7	8	0	
	3	8	4	3	7	0	
12	1	1	5	6	10	0	
	2	3	5	4	0	6	
	3	9	1	2	8	0	
13	1	1	8	9	0	6	
	2	6	5	5	8	0	
	3	9	3	2	8	0	
14	1	1	4	8	8	0	
	2	3	3	7	0	4	
	3	4	3	7	0	3	
15	1	2	8	8	0	7	
	2	2	8	8	6	0	
	3	5	8	8	4	0	
16	1	3	7	9	0	8	
	2	5	6	7	0	6	
	3	7	5	5	3	0	
17	1	1	6	5	0	3	
	2	7	5	5	8	0	
	3	9	4	5	5	0	
18	1	2	6	4	5	0	
	2	5	6	3	0	4	
	3	8	5	2	0	3	
19	1	4	7	9	7	0	
	2	4	6	6	0	5	
	3	8	4	3	3	0	
20	1	4	10	6	0	8	
	2	6	8	3	2	0	
	3	8	7	1	2	0	
21	1	1	6	10	7	0	
	2	3	6	7	5	0	
	3	8	6	5	4	0	
22	1	6	7	4	6	0	
	2	9	4	3	5	0	
	3	10	2	3	3	0	
23	1	1	7	2	5	0	
	2	3	5	2	0	6	
	3	8	2	2	3	0	
24	1	1	8	8	0	4	
	2	4	6	7	6	0	
	3	10	4	7	6	0	
25	1	1	7	5	0	7	
	2	6	5	4	0	5	
	3	10	5	3	4	0	
26	1	7	6	7	0	8	
	2	8	3	4	0	7	
	3	9	1	3	0	4	
27	1	2	4	8	0	10	
	2	5	4	8	3	0	
	3	9	2	6	1	0	
28	1	3	6	3	2	0	
	2	7	5	2	2	0	
	3	8	4	1	0	2	
29	1	1	7	4	5	0	
	2	5	5	3	3	0	
	3	6	5	2	0	3	
30	1	1	8	8	0	9	
	2	2	7	8	3	0	
	3	5	5	6	0	6	
31	1	1	10	1	3	0	
	2	1	6	1	0	7	
	3	9	5	1	3	0	
32	1	3	8	3	0	6	
	2	3	6	3	7	0	
	3	7	5	2	7	0	
33	1	2	6	9	0	9	
	2	6	4	5	6	0	
	3	10	2	2	3	0	
34	1	2	10	7	0	6	
	2	6	7	7	0	3	
	3	10	2	7	5	0	
35	1	4	7	7	0	8	
	2	7	6	4	0	7	
	3	9	6	4	3	0	
36	1	7	3	8	0	7	
	2	9	3	7	7	0	
	3	10	2	6	0	5	
37	1	3	8	4	8	0	
	2	5	7	2	6	0	
	3	7	7	2	5	0	
38	1	2	6	4	2	0	
	2	5	4	4	0	8	
	3	9	3	3	2	0	
39	1	2	4	9	0	4	
	2	3	3	8	3	0	
	3	7	3	8	2	0	
40	1	2	10	4	0	8	
	2	5	9	4	0	6	
	3	9	9	4	8	0	
41	1	3	8	8	0	8	
	2	4	8	6	4	0	
	3	8	7	2	0	5	
42	1	2	6	6	0	7	
	2	4	6	5	0	5	
	3	7	4	5	2	0	
43	1	1	7	8	5	0	
	2	3	6	6	3	0	
	3	8	2	1	3	0	
44	1	1	9	6	9	0	
	2	2	6	5	7	0	
	3	3	4	4	0	1	
45	1	2	3	7	5	0	
	2	2	2	7	0	5	
	3	4	2	6	3	0	
46	1	1	6	5	0	7	
	2	4	4	4	4	0	
	3	10	3	2	4	0	
47	1	1	6	1	7	0	
	2	5	6	1	0	2	
	3	7	4	1	5	0	
48	1	3	7	7	6	0	
	2	5	7	5	0	7	
	3	7	6	5	4	0	
49	1	2	10	9	6	0	
	2	5	9	8	5	0	
	3	8	7	6	0	7	
50	1	1	6	3	5	0	
	2	8	6	3	0	4	
	3	10	6	3	3	0	
51	1	9	9	4	1	0	
	2	9	8	4	0	2	
	3	10	8	4	0	1	
52	1	0	0	0	0	0	
************************************************************************

 RESOURCE AVAILABILITIES 
	R 1	R 2	N 1	N 2
	42	38	201	186

************************************************************************

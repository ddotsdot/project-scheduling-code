jobs  (incl. supersource/sink ):	52
RESOURCES
- renewable                 : 2 R
- nonrenewable              : 2 N
- doubly constrained        : 0 D
************************************************************************
PRECEDENCE RELATIONS:
jobnr.    #modes  #successors   successors
1	1	9		2 3 4 5 6 7 8 11 15 
2	3	7		29 22 21 18 14 13 10 
3	3	8		30 29 27 22 21 18 17 14 
4	3	3		19 12 9 
5	3	12		33 32 29 27 25 24 23 22 20 19 18 17 
6	3	5		27 25 20 18 9 
7	3	3		20 19 9 
8	3	6		33 25 23 19 17 16 
9	3	12		41 36 33 32 31 30 29 28 26 24 23 22 
10	3	8		33 31 28 25 24 23 20 17 
11	3	8		51 37 33 32 27 24 20 18 
12	3	7		37 32 30 25 22 20 18 
13	3	6		41 33 32 30 28 17 
14	3	8		37 34 33 32 31 26 25 23 
15	3	7		51 37 36 33 31 28 20 
16	3	8		50 41 40 36 32 28 26 24 
17	3	8		51 50 40 37 36 35 34 26 
18	3	7		50 41 36 34 31 28 26 
19	3	10		51 50 49 48 41 40 37 36 34 31 
20	3	5		50 40 35 34 26 
21	3	5		50 40 39 36 24 
22	3	9		51 49 48 47 46 45 43 40 39 
23	3	8		48 47 46 45 43 40 39 38 
24	3	7		49 48 46 45 44 43 34 
25	3	6		47 45 44 41 36 35 
26	3	7		49 48 47 46 45 43 39 
27	3	7		50 49 48 47 45 42 39 
28	3	5		49 45 44 43 35 
29	3	6		48 47 46 45 40 38 
30	3	6		49 48 46 44 43 42 
31	3	4		46 45 44 42 
32	3	3		45 43 39 
33	3	3		50 43 42 
34	3	2		47 38 
35	3	2		46 38 
36	3	2		43 42 
37	3	2		43 42 
38	3	1		42 
39	3	1		44 
40	3	1		42 
41	3	1		42 
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
2	1	2	0	8	4	0	
	2	4	0	8	0	8	
	3	10	0	7	0	6	
3	1	4	0	9	8	0	
	2	6	3	0	7	0	
	3	7	0	7	6	0	
4	1	2	0	8	0	6	
	2	4	0	7	8	0	
	3	5	0	7	7	0	
5	1	1	2	0	6	0	
	2	5	0	3	0	4	
	3	6	2	0	0	3	
6	1	3	10	0	0	3	
	2	3	8	0	3	0	
	3	6	0	3	0	1	
7	1	3	6	0	10	0	
	2	4	0	7	0	8	
	3	6	0	6	3	0	
8	1	3	0	3	0	8	
	2	3	5	0	1	0	
	3	5	2	0	0	7	
9	1	2	0	5	0	3	
	2	6	9	0	0	2	
	3	7	9	0	4	0	
10	1	4	0	7	0	10	
	2	5	8	0	8	0	
	3	7	6	0	4	0	
11	1	3	0	5	7	0	
	2	9	7	0	3	0	
	3	10	0	4	1	0	
12	1	1	6	0	3	0	
	2	2	0	3	2	0	
	3	4	0	3	1	0	
13	1	1	0	6	0	10	
	2	3	0	6	0	6	
	3	9	3	0	0	4	
14	1	2	7	0	0	3	
	2	8	0	4	6	0	
	3	10	0	2	0	1	
15	1	3	8	0	0	8	
	2	7	0	8	6	0	
	3	8	0	7	0	4	
16	1	2	6	0	9	0	
	2	4	0	5	0	5	
	3	5	0	4	6	0	
17	1	2	0	7	5	0	
	2	5	0	7	4	0	
	3	8	0	7	3	0	
18	1	4	0	2	6	0	
	2	5	4	0	0	5	
	3	8	4	0	1	0	
19	1	3	8	0	0	10	
	2	4	8	0	0	7	
	3	8	7	0	3	0	
20	1	4	0	10	0	5	
	2	8	0	7	6	0	
	3	10	9	0	4	0	
21	1	1	0	5	0	6	
	2	1	2	0	5	0	
	3	8	0	5	0	2	
22	1	4	2	0	5	0	
	2	7	0	2	2	0	
	3	8	1	0	2	0	
23	1	5	0	6	0	7	
	2	6	3	0	7	0	
	3	10	0	5	0	2	
24	1	2	0	8	6	0	
	2	4	0	6	4	0	
	3	8	0	5	4	0	
25	1	5	0	8	8	0	
	2	7	0	8	7	0	
	3	9	4	0	5	0	
26	1	1	1	0	0	8	
	2	2	0	5	5	0	
	3	5	1	0	4	0	
27	1	1	0	2	0	7	
	2	3	0	1	8	0	
	3	9	7	0	0	3	
28	1	1	7	0	10	0	
	2	2	4	0	0	2	
	3	2	2	0	10	0	
29	1	5	0	9	2	0	
	2	7	0	3	2	0	
	3	8	0	1	2	0	
30	1	6	7	0	0	6	
	2	7	7	0	0	5	
	3	9	7	0	0	1	
31	1	6	0	3	8	0	
	2	7	0	3	5	0	
	3	10	0	3	0	6	
32	1	8	6	0	8	0	
	2	9	6	0	7	0	
	3	10	0	6	7	0	
33	1	5	0	9	9	0	
	2	8	0	9	0	3	
	3	9	8	0	0	3	
34	1	1	0	1	0	10	
	2	5	3	0	4	0	
	3	6	0	1	2	0	
35	1	3	8	0	3	0	
	2	5	0	6	2	0	
	3	8	3	0	2	0	
36	1	1	5	0	0	10	
	2	2	4	0	0	8	
	3	5	4	0	3	0	
37	1	2	9	0	0	6	
	2	3	6	0	0	6	
	3	7	5	0	0	4	
38	1	1	0	5	5	0	
	2	5	0	4	3	0	
	3	9	0	4	0	8	
39	1	3	3	0	0	3	
	2	4	3	0	0	2	
	3	4	3	0	9	0	
40	1	3	9	0	0	3	
	2	8	0	7	6	0	
	3	9	5	0	5	0	
41	1	4	7	0	3	0	
	2	6	5	0	0	2	
	3	9	0	5	2	0	
42	1	4	7	0	0	5	
	2	8	6	0	0	5	
	3	9	4	0	0	3	
43	1	2	5	0	10	0	
	2	8	0	5	6	0	
	3	9	0	5	0	3	
44	1	1	5	0	0	7	
	2	1	0	6	0	5	
	3	2	0	6	0	3	
45	1	1	10	0	0	7	
	2	1	0	7	0	5	
	3	10	4	0	0	2	
46	1	2	0	7	0	8	
	2	3	4	0	4	0	
	3	8	0	6	2	0	
47	1	2	6	0	0	5	
	2	6	6	0	0	4	
	3	10	6	0	2	0	
48	1	3	0	5	0	6	
	2	4	0	4	0	4	
	3	8	1	0	3	0	
49	1	3	10	0	0	8	
	2	4	0	6	5	0	
	3	10	5	0	0	4	
50	1	7	3	0	3	0	
	2	8	2	0	0	4	
	3	9	1	0	0	1	
51	1	1	6	0	7	0	
	2	3	5	0	0	4	
	3	10	4	0	5	0	
52	1	0	0	0	0	0	
************************************************************************

 RESOURCE AVAILABILITIES 
	R 1	R 2	N 1	N 2
	36	32	87	71

************************************************************************

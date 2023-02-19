jobs  (incl. supersource/sink ):	52
RESOURCES
- renewable                 : 2 R
- nonrenewable              : 2 N
- doubly constrained        : 0 D
************************************************************************
PRECEDENCE RELATIONS:
jobnr.    #modes  #successors   successors
1	1	11		2 3 4 5 6 7 8 10 11 12 13 
2	3	6		33 24 21 20 19 9 
3	3	7		34 28 24 22 20 17 16 
4	3	6		26 25 21 20 18 15 
5	3	6		34 32 29 27 23 17 
6	3	8		33 32 30 28 27 26 22 21 
7	3	6		32 29 28 27 20 17 
8	3	5		33 28 21 20 15 
9	3	4		26 25 22 14 
10	3	7		38 33 32 30 28 21 20 
11	3	4		40 32 28 17 
12	3	7		37 36 33 32 31 28 25 
13	3	4		30 28 22 20 
14	3	7		36 35 34 32 31 29 27 
15	3	6		37 36 32 30 29 27 
16	3	5		32 31 29 27 25 
17	3	6		39 38 37 33 30 21 
18	3	8		39 38 37 36 35 33 31 28 
19	3	7		40 38 36 35 34 31 28 
20	3	7		50 49 41 40 39 37 35 
21	3	5		48 41 36 35 31 
22	3	5		48 40 39 36 29 
23	3	5		51 50 38 35 28 
24	3	5		49 48 40 39 30 
25	3	4		51 39 38 30 
26	3	5		50 49 41 38 37 
27	3	6		49 48 46 43 40 38 
28	3	7		49 48 47 45 44 42 41 
29	3	5		51 45 43 41 38 
30	3	3		50 46 35 
31	3	7		51 50 47 45 44 43 42 
32	3	5		47 46 45 44 39 
33	3	6		48 47 46 45 43 42 
34	3	6		50 49 46 45 44 42 
35	3	5		47 45 44 43 42 
36	3	3		49 44 43 
37	3	3		48 43 42 
38	3	2		44 42 
39	3	2		43 42 
40	3	2		45 42 
41	3	1		46 
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
2	1	7	5	0	3	7	
	2	8	4	0	2	5	
	3	10	3	0	1	5	
3	1	2	7	0	4	9	
	2	7	0	3	4	4	
	3	10	5	0	3	2	
4	1	4	3	0	6	5	
	2	5	0	5	5	5	
	3	8	0	4	3	5	
5	1	3	0	9	8	7	
	2	4	0	8	7	3	
	3	7	0	6	6	3	
6	1	5	0	8	6	6	
	2	7	0	6	5	4	
	3	9	0	5	4	3	
7	1	1	3	0	6	9	
	2	7	1	0	4	8	
	3	9	0	4	2	7	
8	1	1	0	4	9	9	
	2	2	0	3	3	8	
	3	9	4	0	2	7	
9	1	4	0	9	9	8	
	2	5	0	6	7	8	
	3	10	3	0	7	8	
10	1	3	4	0	2	5	
	2	7	3	0	1	5	
	3	9	0	4	1	5	
11	1	1	0	9	1	4	
	2	4	0	5	1	2	
	3	9	6	0	1	2	
12	1	3	0	7	7	1	
	2	5	0	6	7	2	
	3	6	0	6	7	1	
13	1	1	0	3	5	5	
	2	2	0	2	4	3	
	3	3	0	2	2	3	
14	1	2	0	9	7	3	
	2	2	6	0	4	2	
	3	3	4	0	2	1	
15	1	2	0	7	8	4	
	2	3	0	5	6	3	
	3	7	0	5	6	1	
16	1	1	6	0	6	9	
	2	3	4	0	6	6	
	3	4	0	8	6	2	
17	1	5	8	0	10	7	
	2	7	0	8	9	5	
	3	10	8	0	8	3	
18	1	1	7	0	9	3	
	2	4	0	4	9	2	
	3	6	0	4	9	1	
19	1	9	9	0	5	2	
	2	10	6	0	5	1	
	3	10	0	6	4	1	
20	1	2	0	5	10	6	
	2	6	0	5	7	5	
	3	9	7	0	4	2	
21	1	4	0	10	9	2	
	2	6	5	0	5	1	
	3	7	0	10	3	1	
22	1	4	2	0	9	8	
	2	5	0	5	7	7	
	3	6	1	0	6	6	
23	1	1	8	0	9	2	
	2	4	3	0	9	2	
	3	5	0	7	7	2	
24	1	4	9	0	9	8	
	2	4	0	7	8	4	
	3	5	3	0	8	2	
25	1	2	0	3	9	2	
	2	6	3	0	7	2	
	3	7	2	0	7	2	
26	1	1	0	7	6	7	
	2	3	7	0	6	3	
	3	4	0	4	6	3	
27	1	3	0	5	5	8	
	2	6	6	0	4	7	
	3	9	0	2	4	6	
28	1	4	0	7	9	9	
	2	5	9	0	9	8	
	3	6	9	0	9	4	
29	1	1	0	10	3	7	
	2	2	0	10	1	7	
	3	7	0	10	1	6	
30	1	1	7	0	9	5	
	2	2	0	8	6	5	
	3	5	3	0	6	2	
31	1	1	0	5	7	7	
	2	2	8	0	6	4	
	3	7	0	4	4	3	
32	1	4	0	3	7	9	
	2	9	2	0	6	5	
	3	10	2	0	5	4	
33	1	3	0	10	3	4	
	2	3	4	0	2	4	
	3	6	3	0	2	4	
34	1	6	0	4	5	7	
	2	9	9	0	3	6	
	3	10	0	3	2	5	
35	1	5	5	0	5	6	
	2	7	0	5	5	5	
	3	8	2	0	5	2	
36	1	2	0	9	6	9	
	2	3	2	0	4	7	
	3	9	2	0	3	7	
37	1	1	7	0	9	8	
	2	2	0	10	7	8	
	3	10	4	0	7	7	
38	1	2	0	9	6	9	
	2	3	0	8	4	8	
	3	9	3	0	2	8	
39	1	1	0	6	10	8	
	2	3	7	0	5	6	
	3	4	5	0	4	6	
40	1	1	0	6	8	9	
	2	4	1	0	4	8	
	3	5	0	4	4	7	
41	1	4	0	4	8	7	
	2	5	0	4	8	6	
	3	6	0	4	8	5	
42	1	5	4	0	7	8	
	2	7	2	0	6	5	
	3	9	0	4	5	5	
43	1	5	9	0	6	5	
	2	8	0	7	6	2	
	3	9	0	7	3	2	
44	1	1	0	5	6	7	
	2	4	7	0	3	6	
	3	9	4	0	3	5	
45	1	3	0	10	10	3	
	2	5	0	7	9	2	
	3	5	4	0	7	2	
46	1	2	4	0	8	6	
	2	3	0	2	7	6	
	3	9	2	0	5	4	
47	1	2	3	0	7	4	
	2	6	3	0	7	3	
	3	10	2	0	6	2	
48	1	1	0	5	7	6	
	2	4	0	5	7	5	
	3	10	7	0	5	4	
49	1	1	4	0	9	4	
	2	2	4	0	7	2	
	3	7	0	6	5	2	
50	1	1	4	0	10	2	
	2	3	0	9	9	2	
	3	7	2	0	9	2	
51	1	2	4	0	3	8	
	2	5	0	7	3	8	
	3	7	3	0	1	8	
52	1	0	0	0	0	0	
************************************************************************

 RESOURCE AVAILABILITIES 
	R 1	R 2	N 1	N 2
	18	25	287	247

************************************************************************

jobs  (incl. supersource/sink ):	52
RESOURCES
- renewable                 : 2 R
- nonrenewable              : 2 N
- doubly constrained        : 0 D
************************************************************************
PRECEDENCE RELATIONS:
jobnr.    #modes  #successors   successors
1	1	18		2 3 4 5 6 7 8 9 10 11 13 14 15 17 18 20 23 24 
2	3	9		48 45 43 39 32 27 26 22 12 
3	3	12		51 50 46 44 43 41 39 31 30 27 22 21 
4	3	9		51 46 45 44 43 37 34 29 19 
5	3	8		47 45 44 42 37 36 22 16 
6	3	8		47 45 44 43 30 25 22 21 
7	3	7		47 45 44 37 36 26 16 
8	3	8		48 46 45 39 30 28 22 21 
9	3	4		45 39 22 12 
10	3	12		48 46 45 44 39 38 37 36 35 32 30 28 
11	3	10		49 48 46 45 44 43 39 34 30 21 
12	3	7		46 44 41 35 34 30 21 
13	3	11		49 45 44 43 42 39 37 36 35 33 31 
14	3	10		50 49 47 44 43 42 38 37 36 34 
15	3	9		50 48 43 39 37 35 34 33 30 
16	3	8		43 41 39 38 35 34 32 30 
17	3	4		43 41 39 21 
18	3	4		45 39 32 21 
19	3	6		48 41 40 38 33 27 
20	3	6		46 43 37 36 35 33 
21	3	5		42 38 37 36 33 
22	3	5		40 38 35 34 33 
23	3	5		49 46 43 42 36 
24	3	4		42 41 35 33 
25	3	3		39 35 31 
26	3	2		41 31 
27	3	2		47 35 
28	3	2		41 34 
29	3	2		39 36 
30	3	1		40 
31	3	1		34 
32	3	1		33 
33	3	1		52 
34	3	1		52 
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
2	1	8	5	0	6	9	
	2	9	3	0	2	8	
	3	10	0	9	2	7	
3	1	4	5	0	10	7	
	2	8	4	0	7	6	
	3	9	0	3	5	5	
4	1	6	7	0	6	8	
	2	8	7	0	4	7	
	3	9	0	6	3	4	
5	1	4	0	5	9	9	
	2	8	7	0	7	9	
	3	9	0	4	4	9	
6	1	1	0	4	4	4	
	2	2	2	0	3	2	
	3	6	2	0	3	1	
7	1	1	0	7	2	9	
	2	1	3	0	2	4	
	3	6	1	0	2	3	
8	1	6	7	0	4	5	
	2	8	0	4	4	5	
	3	8	7	0	4	4	
9	1	6	4	0	2	7	
	2	7	3	0	2	6	
	3	9	3	0	2	5	
10	1	1	3	0	4	7	
	2	6	1	0	3	5	
	3	9	0	1	3	4	
11	1	3	0	6	6	6	
	2	6	9	0	4	4	
	3	7	0	3	3	2	
12	1	3	0	9	6	4	
	2	3	6	0	3	4	
	3	9	5	0	3	2	
13	1	5	10	0	8	10	
	2	7	9	0	6	9	
	3	7	0	4	6	9	
14	1	8	0	9	8	4	
	2	9	0	7	6	3	
	3	10	0	3	4	2	
15	1	8	7	0	6	3	
	2	9	4	0	4	3	
	3	9	0	4	4	3	
16	1	2	6	0	9	9	
	2	8	0	6	4	8	
	3	9	3	0	2	7	
17	1	2	3	0	3	9	
	2	8	2	0	2	5	
	3	9	0	6	1	3	
18	1	5	0	8	7	3	
	2	6	0	6	7	2	
	3	6	4	0	7	1	
19	1	3	0	7	10	4	
	2	9	7	0	6	3	
	3	10	0	3	4	3	
20	1	5	0	10	9	9	
	2	10	5	0	8	6	
	3	10	0	9	7	2	
21	1	4	3	0	4	10	
	2	6	0	3	4	8	
	3	7	0	3	1	8	
22	1	4	8	0	7	7	
	2	7	7	0	5	7	
	3	8	7	0	3	7	
23	1	1	0	2	1	1	
	2	3	0	1	1	1	
	3	10	7	0	1	1	
24	1	5	0	8	8	6	
	2	8	0	8	6	6	
	3	10	0	7	4	6	
25	1	1	4	0	9	3	
	2	3	3	0	9	2	
	3	9	0	1	9	2	
26	1	5	1	0	9	10	
	2	7	1	0	8	8	
	3	8	1	0	7	8	
27	1	1	8	0	7	9	
	2	2	8	0	6	8	
	3	10	0	1	3	8	
28	1	1	0	3	9	9	
	2	7	4	0	8	7	
	3	10	0	3	8	6	
29	1	5	0	6	8	3	
	2	8	0	4	7	3	
	3	10	0	3	2	3	
30	1	1	0	5	8	4	
	2	1	6	0	5	4	
	3	5	0	5	2	4	
31	1	1	0	8	8	10	
	2	2	0	8	8	9	
	3	3	0	8	8	8	
32	1	1	0	7	6	8	
	2	3	0	5	3	4	
	3	8	5	0	2	1	
33	1	2	9	0	7	5	
	2	6	0	7	4	3	
	3	8	0	7	1	1	
34	1	1	6	0	9	2	
	2	7	0	5	6	2	
	3	10	5	0	5	2	
35	1	6	6	0	2	7	
	2	6	0	8	2	6	
	3	8	6	0	1	3	
36	1	4	0	9	7	5	
	2	5	8	0	6	5	
	3	8	7	0	4	3	
37	1	2	0	5	5	5	
	2	8	5	0	5	3	
	3	10	0	3	5	3	
38	1	5	0	6	5	7	
	2	10	7	0	4	6	
	3	10	0	4	1	5	
39	1	2	7	0	6	7	
	2	5	6	0	6	4	
	3	6	6	0	4	3	
40	1	1	0	2	5	5	
	2	1	1	0	5	4	
	3	6	1	0	5	3	
41	1	2	9	0	9	3	
	2	4	9	0	9	2	
	3	7	8	0	8	2	
42	1	6	0	8	7	7	
	2	8	4	0	3	7	
	3	9	4	0	2	7	
43	1	3	8	0	1	7	
	2	6	8	0	1	6	
	3	8	6	0	1	5	
44	1	5	5	0	8	4	
	2	6	0	5	6	3	
	3	7	1	0	6	2	
45	1	1	10	0	4	5	
	2	5	7	0	4	2	
	3	6	0	6	3	2	
46	1	2	0	6	3	9	
	2	4	5	0	3	9	
	3	9	0	2	3	9	
47	1	4	0	8	3	9	
	2	6	0	7	2	7	
	3	6	7	0	2	5	
48	1	1	7	0	4	6	
	2	2	7	0	3	4	
	3	7	0	2	2	2	
49	1	2	8	0	1	8	
	2	3	8	0	1	5	
	3	10	7	0	1	2	
50	1	1	0	9	4	5	
	2	2	8	0	4	4	
	3	7	8	0	3	2	
51	1	2	8	0	9	4	
	2	8	8	0	8	2	
	3	10	0	3	6	1	
52	1	0	0	0	0	0	
************************************************************************

 RESOURCE AVAILABILITIES 
	R 1	R 2	N 1	N 2
	29	31	272	286

************************************************************************

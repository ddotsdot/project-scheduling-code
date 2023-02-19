************************************************************************
file with basedata            : cm429_.bas
initial value random generator: 619368737
************************************************************************
projects                      :  1
jobs (incl. supersource/sink ):  18
horizon                       :  135
RESOURCES
  - renewable                 :  2   R
  - nonrenewable              :  2   N
  - doubly constrained        :  0   D
************************************************************************
PROJECT INFORMATION:
pronr.  #jobs rel.date duedate tardcost  MPM-Time
    1     16      0       21        1       21
************************************************************************
PRECEDENCE RELATIONS:
jobnr.    #modes  #successors   successors
   1        1          3           2   3   4
   2        4          3           5   7  11
   3        4          3           7  11  13
   4        4          1           6
   5        4          3           6   8  10
   6        4          3           9  12  13
   7        4          3           9  10  16
   8        4          1          14
   9        4          1          15
  10        4          2          15  17
  11        4          3          12  15  17
  12        4          1          14
  13        4          2          14  17
  14        4          1          16
  15        4          1          18
  16        4          1          18
  17        4          1          18
  18        1          0        
************************************************************************
REQUESTS/DURATIONS:
jobnr. mode duration  R 1  R 2  N 1  N 2
------------------------------------------------------------------------
  1      1     0       0    0    0    0
  2      1     3       7    2   10    0
         2     4       7    2    0    8
         3     5       6    1    0    6
         4    10       5    1   10    0
  3      1     4       7    6    6    0
         2     5       5    6    6    0
         3     8       5    6    5    0
         4    10       3    4    3    0
  4      1     3       6    8    0    7
         2     4       5    7    0    5
         3     8       4    6    0    4
         4     9       3    4    0    3
  5      1     3       9    9    8    0
         2     5       8    7    8    0
         3     5       5    7    0    3
         4     6       4    4    8    0
  6      1     1       2    4    0    3
         2     3       2    4    0    2
         3     8       2    4    5    0
         4     9       2    4    2    0
  7      1     1      10    9    7    0
         2     2      10    9    5    0
         3     3      10    7    0    7
         4    10       9    5    3    0
  8      1     1       8    8    0    6
         2     4       8    8    9    0
         3     6       7    6    0    4
         4     8       6    5    0    3
  9      1     1       6    2    9    0
         2     6       4    2    6    0
         3     8       1    1    0    9
         4     8       3    1    4    0
 10      1     2       3    6    0    8
         2     5       3    5    8    0
         3     7       3    5    7    0
         4     9       2    4    0    6
 11      1     5       4    7    1    0
         2     5       3    6    0    5
         3     5       3    9    1    0
         4     7       1    2    0    6
 12      1     1       9    8    9    0
         2     3       8    8    9    0
         3     5       6    7    8    0
         4     6       6    7    0    9
 13      1     5       8    3    8    0
         2     5       6    3    0    3
         3     8       5    2    0    2
         4     9       3    2    0    2
 14      1     5      10    6    6    0
         2     6      10    6    4    0
         3     6      10    5    0   10
         4    10      10    1    0    6
 15      1     1       1   10    0    8
         2     2       1   10    6    0
         3     7       1    9    0    4
         4     9       1    9    3    0
 16      1     4       6    8    0   10
         2     8       5    5    7    0
         3     8       4    4    0    8
         4     8       4    5    9    0
 17      1     1       6    7    0    6
         2     4       6    6    4    0
         3     4       6    5    0    6
         4     7       6    5    4    0
 18      1     0       0    0    0    0
************************************************************************
RESOURCEAVAILABILITIES:
  R 1  R 2  N 1  N 2
   13   14  105  103
************************************************************************

Sudoku solver
=
### A sudoku is a popular puzzle where the puzzler has to fill in numbers in a nine by nine grid. The goal of this exercise it to create a program that solves Sudokus.
#
There are a couple of rules:

The grid is divided in nine rows, nine columns and nine blocks. The blocks are three by three and are positioned in the grid as three rows with three blocks in a row.<br>
All numbers from 1 to 9 should occur at least once and at most once in each row, column and block.<br>

Example

**C:/> java Sudoku 000820090500000000308040007100000040006402503000090010093004000004035200000700900**

**Initial State:<br>
| | | |8|2| | |9| |<br>
|5| | | | | | | | |<br>
|3| |8| |4| | | |7|<br>
|1| | | | | | |4| |<br>
| | |6|4| |2|5| |3|<br>
| | | | |9| | |1| |<br>
| |9|3| | |4| | | |<br>
| | |4| |3|5|2| | |<br>
| | | |7| | |9| | |<br>**

**Solved:<br>
|6|7|1|8|2|3|4|9|5|<br>
|5|4|9|1|7|6|3|2|8|<br>
|3|2|8|5|4|9|1|6|7|<br>
|1|3|2|6|5|7|8|4|9|<br>
|9|8|6|4|1|2|5|7|3|<br>
|4|5|7|3|9|8|6|1|2|<br>
|8|9|3|2|6|4|7|5|1|<br>
|7|1|4|9|3|5|2|8|6|<br>
|2|6|5|7|8|1|9|3|4|<br>**

**Solved in 12.189 seconds.**

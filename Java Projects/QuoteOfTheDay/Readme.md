Quote of the day (required)
=
### Write a short program that, when started, prints a quote of the day on the screen.
#
**The Quotes:<br>
1: "galileo": "eppur si muove"<br>
2: "archimedes": "eureka!"<br>
3: "erasmus": "in regione caecorum rex est luscus"<br>
4: "socrates": "I know nothing except the fact of my ignorance"<br>
5: "rené descartes": "cogito, ergo sum"<br>
6: "sir isaac newton": "if I have seen further it is by standing on the shoulders of giants"<br>**

In the repository of your language, you will find a code file with the above quotes in it, assigned to a field or variable. Modify the program such that it outputs the quote of the dat in the following format:<br>

**1: C\> java Quote<br>
2: Quote for Monday the 1th of February:<br>
3: "Eureka!" -- Archimedes<br>**
A quote is printed between quotation marks, starts with a capital letter and ends with a full stop. If the quote ends with punctuation, do not add the full stop to the end of the sentence. For example eppur si muove becomes "Eppur si muove.", while eureka! becomes "Eureka!".<br>

Names always start with a capital letter.<br>

Select the quote by taking the day of the year and take the day as the index of the list. January 2nd is the second day of the year, so the quote should be "Eureka!" -- Archimedes, while January 3th gives the quote from Erasmus. If the list runs out of quotes, start again on the first quote. This means that January 1th, January 7th and January 13th all give the Galileo quote.<br>

Learning goals:
-
Immutable data<br>
String manipulation<br>
Date API<br>

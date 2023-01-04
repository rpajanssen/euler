Example project euler solutions

Project 19

https://projecteuler.net/problem=19

You are given the following information, but you may prefer to do some research for yourself.

1 Jan 1900 was a Monday.
Thirty days has September, April, June and November.
All the rest have thirty-one,
Saving February alone, which has twenty-eight, rain or shine. And on leap years, twenty-nine.
A leap year occurs on any year evenly divisible by 4, but not on a century unless
it is divisible by 400.
How many Sundays fell on the first of the month during the twentieth century
(1 Jan 1901 to 31 Dec 2000)?


-> first solve it simple/brute force not using any libs (not even java time)
-> then refactor and improve

Example solutions:
- 1st solution : brute force with three nested loops
- 2nd solution : get rid of the loop over each day in the month
- 3rd solution : get rid of another loop to decrease cyclomatic complexity further
- 4th solution : cleaning up code, extracting methods
- 5th solution : extracting logic to classes, cleanup code and main algorithm

- last solution : using local-date

Assessments

Criteria to watch:
- communication
- approach
- understanding of the problem
- framework/lib choice
- Java knowledge
- patterns
- clean code (vs over engineering - KISS)
- testing
- refactoring
- coding principles
- documentation
- tool skills (bv IDE)

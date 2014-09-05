CX4140
======
Homework 2 

1) Create a linked list of n nodes. Plot the normalizing running time per link (that is, total running time divided by n), for traversing your list as the list size n varies from 25 to 224 links in size (using powers of two).

2) Repeat the same experiment using a contiguous array of integers with array length n. Each element of the array contains the index of the next element, and a -1 value represents the end of the list.
Create three types of input (sequential, strided, and random, discussed next), to find the average access time per element of the array.

If the array is indexed from "0" to "n-1" then the inputs to test would be as follows, for an array with 16 elements:

Sequential Order: 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, -1

Stride-2 Order: 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 1, -1

Stride-4 Order: 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 1, 2, 3, -1

Random Order: 9, 6, 1, 10, 2, 11, 7, 15, 5, 8, 14, 12, 3, 4, 13, -1
(Make sure your random order starts with the "0" element, and touches every elment before reaching the end-of-list -1.)

(Hint: You may wish to test correctness by keeping a running sum of the visited indices and checking that the sum equals the sum of the integers from 1 to n-1.)

Describe your experimental methodology for getting accurate timings.
What is the resolution and accuracy of the clock you used?

What is the asymptotic complexity of each algorithm? O(n)

Create a single graph plot where the x-axis is log (base 2) of the input size (e.g. 5, 6, 7, ..., 24, 25, etc.) and the y-axis is the average access time per element of the array. Plot each series on the same graph (i.e. the linked list performance, then the consecutive array with sequential order accesses, strided accesses, and random accesses).

Compare and analyze these results. Does the access pattern affect performance? Why or why not? Does the list size affect performance? Why or why not?

Is the algorithmic model accurate? Explain.

Please turn in a printout of your code, your graph/plot, and analyses.

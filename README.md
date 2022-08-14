# exelsior-test-task

Thank you for your interest in Excelsior company.

You can find task description below:

Input data:
You can find file example.csv that contains unsorted information about stock price statistics. The file has following structure:
- date: trading day
- ticker: name of the stock ticker
- price_close: close price of the trading day (the price of the last transaction)
- price_high: max price of the trading day
- price_low: min price of the trading day
- price_open: open price of the trading day (the price of the first transaction)

Task conditions:
Write Java program that receives the input path to the csv file and the numbers N and M. Your program should output the following information to the console:
For each ticker:
- Print Ticker name
- Print days count
- Print maximum price for all days
- Print minimum price for all days
- For the last M days print moving average value of N elements (format - "01.01.2022: 1000.00")

Additional requirements:
1. Please use only standart Java libraries (Java 8 and higher)
2. Take into account corner cases and cases of incorrect N and M (suppose that file format is always correct)
3. Tests are optional
4. Try to make your solution scalable (let's assume that in a real situation, the data volumes can be much larger, as well as the numbers M and N).
5. Add some comments or ReadMe.md file which explains your algorithm.

Explanations:
Moving average (MA) of N elements calculates as sum of the close prices for previous N days divided by N.

	For instance,
	If N = 5, M = 3 and we have following data:

	day = 01.01.2022: price_close = 10.00
	day = 02.01.2022: price_close = 11.00
	day = 03.01.2022: price_close = 12.00
	day = 04.01.2022: price_close = 13.00
	day = 05.01.2022: price_close = 14.00
	day = 06.01.2022: price_close = 15.00
	day = 07.01.2022: price_close = 16.00
	day = 08.01.2022: price_close = 17.00
	day = 09.01.2022: price_close = 18.00
	day = 10.01.2022: price_close = 19.00

	Then we need to calculate MA for 08.01.2022 - 10.01.2022 and the resuls will be:
	08.01.2022: MA = (12.00 + 13.00 + 14.00 + 15.00 + 16.00) / 5 = 14.00
	09.01.2022: MA = (13.00 + 14.00 + 15.00 + 16.00 + 17.00) / 5 = 15.00
	10.01.2022: MA = (14.00 + 15.00 + 16.00 + 17.00 + 18.00) / 5 = 16.00

	So you should print:
	08.01.2022: 14.00
	09.01.2022: 15.00
	10.01.2022: 16.00

<h2>Solution</h2>

<h3>Running the program</h3>
For running the application you should setup  3 vararg
* ard[0] - the path to the csv file
* arg[1] - an integer of elements for computing moving average N in the task condition
* arg[2] - an integer of days for showing moving average M in the task condition 
<h3>Algorithm for creating a domain model</h3>
<p>
From data transfer object application doing mapping and collect data to a HashMap where the key is TikerName 
If the object doesn't exist then put the new object to a map
If the object exists then merge two objects and compute the maximum price and minimum price.
</p>
<h3>Algorithm of computing moving average</h3>
<p>In the solution for computing, the moving average used the Sliding Window algorithm.
We calculate the sum last  N elements Then for calculating the sum for the next day we should subtract the first element and add the N+1 element;
</p>
<p>
<b>Complexity Analysis</b><br>
Time complexity : O(n+m)<br>
Space complexity: O(1) Constant extra space is used.
</p>



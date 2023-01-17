# Take Home Test Solution 
### *By: James Lynch*

## How to use this program
1. Open the program in your desired IDE, and make sure Java 18 is installed.
2. Place your desired .csv data file in the `resources` folder. There should be a file called `testdata.csv` currently in there.
3. Run `Main.java` 
4. In the console, type out the name of the data file you placed in the `resources` folder. The name should not matter, just make sure to include **.csv**.
5. Wait for the magic...
6. And you're done! Access your results in the `output.csv` file located in the `Takehome` folder.

<br></br>
## Further information about my program

##My approach
Below are some of the approaches, design principles, and explanations, in no particular order, on how I decided to tackle this problem.
- Utilized the Multiton design pattern for my Customer object. This would allow me to maintain one customer per id along with a list of all customers.
- Divided transactions & balances by month to allow for more division between code, and minimize errors.
- Created a sorting function for transactions by two factors, date and then if it's a credit or debit. This would allow my program to process transactions in a linear order, regardless of the order the data is implemented in. 
- Created multiple objects within the program to allow for separation of code, loose coupling (intended, but could be better), and to make testing easier.
- Chose to utilize ArrayLists over LinkedLists to improve time complexity, although depending on data set size and order it may be a diminishable, or even negative, tradeoff.
  <br></br>
- Overall, I went into this task with three main goals for myself: simplicity, efficiency, and code legibility to another (you!).

##Microsoft Excel issues
Two key issues I ran into when using Excel for the .csv files were:
- special characters being added before the first line of data when saved as a **.csv**. 
  - To fix I suggest avoiding Excel altogether and using a basic editor such as `TextEdit`. If you already saved the **.csv** with Excel, simply open it in a program such as `TextEdit` and re-save the file.
- Date auto-formatting *(more of an annoyance than an issue).*
  - A `DateTimeParseException` would be thrown due to Excel auto-formatting the given `mm/dd/yyyy` date to a condensed format (such as 22 instead of 2022). To fix this, you need to re-format all the cells to the stated `mm/dd/yyyy` format.
  
#Thank you for taking the time to read through my solution, Happy new year!
<br></br>

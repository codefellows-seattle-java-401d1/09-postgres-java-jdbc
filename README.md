# ![CF](http://i.imgur.com/7v5ASc8.png) Java JDBC Connections

## Resources  
* [HTML Postgres Cheat Sheet](http://www.postgresonline.com/special_feature.php?sf_name=postgresql83_cheatsheet&outputformat=html)
* [Postgres CLI (Command Line Interface)](https://www.pgcli.com/)
* [Postico](https://eggerapps.at/postico/)

## Feature Tasks
Create a Java command line tool that interacts with the world database. Allow
users to search for countries, cities, populations, languages or whatever you
want.

You must construct at least these following types of queries:

* One query that returns all cities or countries.
* One query that returns cities or countries constrained by one parameter.
* One query that returns results constrained by at least two parameters.
* One query that returns results based on an SQL join.

Your program should accept input from `System.in` and read it with a Scanner.
Make something cool!

## Project Structure
Your project must have the following classes:

* `main.java.Main` - the bulk of the program. Deals with ALL input/output.
* `WorldDB` - creates methods used by main.java.Main that query the database.
* `City` - models the properties of a city
* `Country` - models the properties of a country

## Testing
Create unit tests to make sure your `WorldDB` class retrieves simple known
information property for each of your queries.

## Submission Instructions
* Work in a fork of this repository
* Work in a branch on your fork
* Write all of your code in a directory named `lab-` + `<your name>` **e.g.** `lab-susan`
* Open a pull request to this repository
* Submit on canvas a question and observation, how long you spent, and a link to
  your pull request

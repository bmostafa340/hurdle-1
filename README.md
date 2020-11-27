# hurdle-1
Queue scheduling model of SFO's September 2019 flight delays due to construction - accepted solution to Ph 11 2019 Hurdle 1

See the report PDF for the full project, as well as a more detailed explanation of the programs and their context.

Code overview:
  - Main constructs a day of flights based on data from a day in mid-September of 2018 using Reader. It then assigns
    flights which are due for arrival/departure to runways as they become available. It records flight delays
    hour by hour and prints the results.
  - Flight and Runway are data elements used by Main.
  - Reader reads a data file representing a day of flights and generates a corresponding list of flights.

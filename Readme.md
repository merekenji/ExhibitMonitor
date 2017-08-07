# Exhibit Monitor
## Java Practice Assignment
Exhibits are csv files which contains loan data of a bank. Exhibit can have multiple formats which should be documented into config.xml. Sample structure of the CSV would be
Serial Num, first name, Last name, date of birth, date of death, loan amount

CSV files are uploaded on "input" FTP server from various locations and it should be uploaded before a specific time that are specified in config.xml. If csv files arrived after the specified time, it should be considered as invalid file and get deleted. A valid file is a file that has not been processed on the day itself yet, exist in config.xml (Server is expecting file) and arrived on time. If a valid file is uploaded on the "input" FTP server, it should be parsed and move into the "process" FTP server.

The records parsed should then be transferred to the database.

Output files are generated at a specified time in config.xml and it should consist of the data in the files that are specified in config.xml. Once generated, the files should be archived in "archive" FTP server
## Assignment Versions
Version 1.0 - Uploaded Working XML Parser Code

Version 1.1 - Created "Task" Classes for Threads and Renaming Packages/Classes

Version 1.2 - Completed Poller Task

version 1.3 - Completed Worker Task

Version 1.4 - Completed Valid and Invalid Database Task

Version 1.5 - Created DAO classes

Version 1.6 - Renamed Files, Merged Similar Files

Version 1.7 - Completed Input Portion of Project
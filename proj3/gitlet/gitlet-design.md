# Gitlet Design Document

**Name**: Kelvin Le

## Classes and Data Structures

### Repo
Will create a repo

### History
This class will hold the logs and previous versions
Will also take care of metadata

### Log
This class keeps track of all commits that precede the current commit

### Global Log
This class keeps track of all commits

### Merge 
This class is responsible for merging two branches together

### Commit Class
This class will represent commit objects

### Blob
This class will represent blob objects

### Commands
This will be the parent class for all commands

### Remove
Will remove a commit

### Status
Will keep track of files changed

### Checkout
Puts files in the working directory

### Branch
Creates new branches

### Remove Branch
Removes a branch

### Reset
Checkout and changes the branch head

### Find
Finds commit or commits based on the inputted commit message




## Algorithms

### History Class 
This class will save the state of commits and store them for later use using
serialization and Utils methods.
When a file is committed, a new file is created and stored containing the SHA-1 id
as well as metadata.
This class will kep track of pointers between commits.
No idea how to store the files in an orderly manner as of now.

### Commit Class
This class will update commits using and implement the history class
to store them. This class will also keep track of pointers to blob objects.

### Blob Class
This class will implement the commit class and update files using Utils methods.

### Command Class
This class will be the parent class and blueprint of all other command classes
to implement DRY

### Remove Class
This class removes a file from a commit and implements the command class

### Status class
This class will keep track of files that have changed by implementing the blob class.
Will contain all changed files in an ArrayList for further action. Will 
implement the command class.

### Checkout Class
This class will implement the commit and history classes to bring chosen files
into the working directory. Also implements command.

### Branch Class
This class implements command and repo to create new branches.

### Remove Branch Class
Implements the command class. Removes a branch by deleting the pointer or
moving the pointer to another branch.

### Log Class
Uses linked list to keep track of all previous commits.

### Global Log
Uses a two dimension linked list to keep track of all commits.

### Reset Class
Implements branch and checkout class to reset.

### Find Class
Implements history. Iterates through history to find the commit that matches the
inputted commit message and prints its SHA-1 id. If multiple commits have the same 
commit message, prints out all of them on separate lines.



## Persistence
This will work with the history class to store all data in files. The files
will be stored in either a stack hash or linked list.
Most likely will use a two dimensional linked list because it is quick and easy
access the last element and add to the end of it. Using a two dimensional linked
list to keep track of multiple branches.  

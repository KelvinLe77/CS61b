# Gitlet Design Document

**Name**: Kelvin Le

## Classes and Data Structures

### History
This class will hold the logs and previous versions
Will also take care of metadata

### Merge 
This class is responsible for merging two branches together

### Commit Class
This class will represent commit objects

### Blob
This class will represent blob objects

### Commands
This will be the parent class for all commands


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


## Persistence
This will work with the history class to store all data in files. The files
will be stored in either a stack hash or linked list.

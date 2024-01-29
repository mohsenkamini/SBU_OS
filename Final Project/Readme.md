# Operating Systems Final Project

Solving the reader/writer problems in java.

# Use of Semaphores

Overall the following Semaphores have been made use of:

| Semaphore | Initial value | Use case |
| --------  | ------------- | -------- |
| wmutex | 1  | one writer at a time can access the CS |  
| rmutex | 1  | one reader at a time can access the CS |  
| full | 0  | readers wait on this semaphores when blocking untill there's something to be read |  




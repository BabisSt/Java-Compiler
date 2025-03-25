# Java-Compiler for MiniJava

First Project on Compilers  
Made a Compiler for MiniJava language complete with scanner, parser, and lexer  

---

**Stevis Charalampos - Antonios AM 1115201600278**  
**First Assignment on Compilers**

The project consists of two folders (part 1, 2) and a README file.  
The first part runs with the following commands:
- `javac Main.java` -> for compile  
- `java Main` -> for execution  

The second part runs with the following commands:
- `make compile` -> for compile  
- `make execute` -> for execution  
- `make clean` -> for cleanup  

In part 1, I have all the files that were provided in the course website examples, and I modified `TernaryEvaluator.java`.  
The grammar correctly solves all the examples provided as test cases on Piazza. The grammar is at the top of the file, and each rule has its own function. I had a lookup table in a file but did not include it.

In part 2, I have all the files provided from the course website examples, and I modified `scanner.flex` and `parser.cup`. The executable is placed in a folder named `product`, and the file name is `Main.java`.  
In `scanner.flex`, I added symbols that the lexer needs to read, such as letters, concatenation, if, else, etc. I'm not sure if numbers and operations were required, so I removed them from the lexer and parser.  
In the parser, I declared all the terminals and non-terminals of the lexer as well as the priorities (giving them to the right to avoid conflicts). Below is the grammar. The grammar works as follows: we have the `program` which is the goal of the grammar, and it accepts declarations and calls, like `name()` or `name()` with the function body. Function arguments can be empty, multiple variables separated by commas, or string literals like `"java"`. The function returns support the above along with concatenation. The grammar supports `if-else` conditions.

I ran several test cases from those given on Piazza, but in some cases, I didn't get the correct results.  
The program terminates with a double Ctrl + D.

**Reminder:** In case the mail filters the .jar files, their actual location is inside the `part_2` folder, outside the `lexer_parser` folder.

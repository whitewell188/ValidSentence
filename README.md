# Valid Sentence

The code in this repository implements two methods of validating that a string can be said to be a valid sentence according to a set of defined rules. One implementation is written in **Python** whilst the other is written in **Java**.  The Python implmentation is intentionally written in a tight, non-flexible fashion in one single script whilst the Java version separates the rule validators into individual Java files. The intention is to show that whilst the Java version could be more extensible and easier to patch in the field it is slower than its Python equivalent by a factor of 3 or 4. Often such a tradeoff is a consideration in architecting a solution.

The ruleset adhered to is as follows:

- String starts with a capital letter
- String has an even number of quotation marks
- String ends with a period character ."
- String has no period characters other than the last character 
- Numbers below 13 are spelled out (one, two, "three, etc)

## Pre-requisites

### Python
An installation of Python3 is required before usage.

### 
An installation of Java is required before usage. Also required are installations of:
- make
- Maven (3+)

## Usage

### Python

#### To run the script

... from top-level directory type the following:

#### # bin/ValidSentencePython

*usage: 
* -f,--file <arg>       path and filename containing a batch of sentences to parse
* -s,--sentence <arg>   individual sentence to parse*

A file of sample sentences are supplied in the file InputFile.txt so the following is an example:

#### # bin/ValidSentencePython --file InputFile.txt

### Java

#### To build the executable

... from top-level directory type the following:

#### # make clean all

#### To run the executable
... from top-level directory type the following:

#### # bin/ValidSentenceJava

*usage: 
* -f,--file <arg>       path and filename containing a batch of sentences to parse
* -s,--sentence <arg>   individual sentence to parse*

A file of sample sentences are supplied in the file InputFile.txt so the following is an example:

#### # bin/ValidSentenceJava--file InputFile.txt

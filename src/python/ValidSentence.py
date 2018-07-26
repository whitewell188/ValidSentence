#!/usr/bin/env python

#
# Script that parses a string to determine its validity given the following ruleset:
#
#   String starts with a capital letter
#   String has an even number of quotation marks
#   String ends with a period character ."
#   String has no period characters other than the last character
#   Numbers below 13 are spelled out ("one", "two", "three", etc...)
#
# The script can configurably parse a single string or a number of
# strings contained within a file
#
# The output will either state the string meets the validity rules or
# else prints out all the reasons why it is not valid, i.e. each rule
# that fails will be reported for each string
#
# Some metrics for the operation will be printed to screen also

import sys
import re
import argparse
from datetime import datetime

lowest_allowed_numeric = 13
input_file_name = ""

def validate_full_stop(sentence):
    # Last character must be a full stop
    if ( not sentence.endswith('.')):
        length = len(sentence)
        raise Exception("invalid character \'" + sentence[length-1] +"\' at position " + str(length) + " when \'.\' expected")

def validate_capital_letter(sentence):
    # First character must be uppercase
    if (not sentence[0].isupper()):
        raise Exception("sentence does not begin with a capital letter")

def validate_number_full_stops(sentence):
    # Only one full stop allowed, at end of string
    length = len(sentence)
    num_periods = sentence.count('.')
    if ( num_periods > 1 ):
        raise Exception("invalid number of periods [" + str(num_periods) + "] - only one is allowed, at end of sentence")

def validate_number_of_quotation_marks(sentence):
    # Quotation marks must be an even number
    number_qms = sentence.count('"')
    if (number_qms % 2 != 0):
        raise Exception("there are " + str(number_qms) + " quotation marks when only an even number is allowed")

def validate_number_usage(sentence):
    # Numbers below 13 are spelled out
    # Use regex
    numbers = re.findall(r'\d+', sentence)
    error_strings = []
    valid=True
    for number in numbers:
        if int(number) < lowest_allowed_numeric:
            valid=False
            position = sentence.find(number)
            error_strings.append("invalid content \'" + number + "\' at position " + str(position+1)
                                 + " - numbers less than " + str(lowest_allowed_numeric) + " must be spelled out")

    if valid is not True:
        raise Exception(": ".join(error_strings))

def validate( sentence ):

    error_strings = []
    valid = True
    try:
        validate_capital_letter(sentence)
    except Exception as e:
        valid = False
        error_strings.append(str(e))
    try:
        validate_full_stop(sentence)
    except Exception as e:
        valid = False
        error_strings.append(str(e))
    try:
        validate_number_full_stops(sentence)
    except Exception as e:
        valid = False
        error_strings.append(str(e))
    try:
        validate_number_of_quotation_marks(sentence)
    except Exception as e:
        valid = False
        error_strings.append(str(e))
    try:
        validate_number_usage(sentence)
    except Exception as e:
        valid = False
        error_strings.append(str(e))

    if valid is not True:
        error_strings.insert(0, "Invalid sentence")
        return valid, ": ".join(error_strings)
    else:
        return valid, "Valid sentence"


def main():
    num_valid_strings = 0
    lines = []

    # Parse command line arguments
    parser = argparse.ArgumentParser()
    parser.add_argument('--file', help="path and filename containing a batch of sentences to parse", type=str)
    parser.add_argument('--sentence', help="individual sentence to parse", type=str)
    args = parser.parse_args()

    if args.file:
        input_file_name = args.file

        # Open file containing sentences
        try:
            lines = [line.rstrip('\n') for line in open(input_file_name)]
        except Exception as e:
            print(e)
            sys.exit(1)

    if args.sentence:
        # Operate on one sentence supplied on cmd line
        lines.append(args.sentence)

    if not args.file and not args.sentence:
        parser.print_usage()
        sys.exit(1)

    time1 = datetime.now()

    num_valid_strings = len(lines)
    for line in lines:
        if (len(line) == 0):
            # skip empty lines
            continue

        # Prepare string by stripping whitespace
        stripped_input = line.lstrip(' ')
        stripped_input = stripped_input.rstrip()

        # Call validation functions, receive result and message
        valid, message = validate(stripped_input)

        if not valid:
            num_valid_strings -= 1

        print("Input  = [" + stripped_input + "]")
        print("Result = [" + message + "]")

    # gather metrics
    time2 = datetime.now()

    elapsed_time = time2-time1
    print("\nProcessed " + str(len(lines)) + " sentences in "
          + str(elapsed_time.microseconds) + " microseconds - "
          + str(num_valid_strings) + " valid, "
          + str(len(lines)-num_valid_strings) + " invalid")

if __name__ == '__main__':
    main()
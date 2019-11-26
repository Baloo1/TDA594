#!/usr/bin/env python

import pycosat
import sys

DEFAULT_PATH = "../dimacs/ecos_x86.dimacs"
DEAD_FEATURES_FILE = "dead-features.txt"

def parse(filename):
    with open(filename, 'r') as cnf:
        lines = cnf.readlines()
        
        info_line = next((line for line in lines if line[0] == 'p'), None).split()
        nvar, nclause = int(info_line[2]), int(info_line[3])

        clauses = [[int(x) for x in line.split()[:-1]] for line in lines if line[0] not in ['c', 'p']]
        int_to_name = dict([(int(x[1]), x[2]) for x in [line.split() for line in lines if line[0] == 'c']])

        return nvar, nclause, clauses, int_to_name


def check_dead(clauses):
    removed = False

    for sol in pycosat.itersolve(clauses):
        for var in sol:
            if var > 0 and var in clauses[-1]:
                print(" -> Removing feature n°{:<4} from potential dead features".format(var), end="\r")
                clauses[-1].remove(var)
                removed = True
        
        if removed:
            return True
    
    return False

def implicates(var1, var2, clauses):
    satisfiability = pycosat.solve(clauses + [[-var1, var2]])
    if satisfiability == "UNKNOWN":
        print(f"The solver could not check {var1} -> {var2}")
        return False
    elif satisfiability == "UNSAT":
        return False
    else:
        return True

def satisfiability(clauses):
        print("Checking satisfiability...")

        satisfiability = pycosat.solve(clauses)
        if satisfiability == "UNKNOWN":
            print(" -> PicoSAT could not determined this instance's satisfiability")
            print(" -> Exiting")
            exit()
        elif satisfiability == "UNSAT":
            print(" -> This instance is not satisfiable")
            print(" -> Exiting")
            exit()
        else:
            print(" -> This instance is satisfiable")

def dead_features(clauses, int_to_name):
    print("Finding dead features...")

    all_found = False
    extended = clauses + [list(int_to_name.keys())]
    while not all_found:
        all_found = not check_dead(extended)
    
    print()
    print(f" -> There are {len(extended[-1])} dead features")
    
    with open(DEAD_FEATURES_FILE, 'w') as dead_features_f:
        dead_features_f.write("\n".join([int_to_name[c] for c in extended[-1]]))
    
    print(f" -> You can find the list of dead features in {DEAD_FEATURES_FILE}")

def implication_graph(nvar, clauses, int_to_name):
    print("Generating implication graph")

    implications = []
    all_vars = list(int_to_name.keys())

    for i in range(0, nvar):
        for j in range(0, nvar):
            if i != j:
                var1, var2 = all_vars[i], all_vars[j]
                if implicates(var1, var2, clauses):
                    print(f" -> Implication found: ({var1},{var2})")
                    implications.append((var1, var2))
    
    print(implications)

if __name__ == "__main__":
    if len(sys.argv) < 2:
        print("No file specified, using default (../dimacs/ecos_x86.dimacs)")
        filepath = DEFAULT_PATH
    else:
        filepath = sys.argv[1]

    try:
        nvar, nclause, clauses, int_to_name = parse(filepath)
        all_vars = int_to_name.keys
    except FileNotFoundError:
        print("The file does not exist")
    except:
        print(f"Error while parsing: {sys.exc_info()[0]}")
        print("This is probably due to the file being incorrectly formatted")
    else:
        # Check for solvability
        satisfiability(clauses)

        # Search for dead features
        dead_features(clauses, int_to_name)
        
        # Create implication graph
        implication_graph(nvar, clauses, int_to_name)

        


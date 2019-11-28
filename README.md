# TDA594 Project

This repository hosts the SPLE project done in the context of Chalmers courses TDA594 - Software Engineering Principles for Complex Systems

## Assignment 3

### Reasoning

We decided it would be easier use python for this assignement, since there is a library, [pycosat](https://github.com/ContinuumIO/pycosat) that offers binding to Picosat, a SAT solver written in C. This library is simple and easy to understand, in contrast to SAT4J.

### Our solution

Run it with

```shell
cd assignement3/python-sat-solver
chmod +x main.py
./main.py {your dimacs file here}
```

You can also call main.py without arguments, in which case it will use *ecos_x86.dimacs*. The program outputs 2 files, *output.txt* (list of dead features) and *implications.txt* (implication graph).
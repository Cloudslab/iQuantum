# iQuantum Examples
This directory contains several examples that demonstrate the use of iQuantum. 
The examples are located in the `org.iquantum.examples` package. 

## Example 1
This example demonstrates how to create a simple quantum datacenter with a single quantum computer (`QNode)` and 
a single quantum task (`QTask`). The quantum task is submitted to the quantum computer for execution.

## Example 2
This example demonstrates how to create a quantum CDatacenter with 2 quantum computers (`QNode`) follow the configurations 
of IBM Oslo and IBM Perth nodes, 2 quantum tasks (`QTask`) with different topologies. 

## Example 3
This example demonstrates how to create a quantum CDatacenter with 2 quantum computers (`QNode`) and
4 quantum tasks (`QTask`). Some of quantum tasks require more qubits than the number of qubits 
available in the quantum computer, which results in the rejection of these tasks.

## Example 4
This example demonstrates how to create a quantum CDatacenter with 2 quantum computers (`QNode`) follow the configurations
of IBM Hanoi and IBM Geneva nodes automatically (imported from the CSV dataset), 4 quantum tasks (`QTask`) with different 
topologies. The quantum tasks are submitted to the quantum computers for execution.

More advanced examples will be added in the future.
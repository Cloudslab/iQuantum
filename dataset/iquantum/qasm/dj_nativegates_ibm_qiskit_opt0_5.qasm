// Benchmark was created by MQT Bench on 2022-12-14
// For more information about MQT Bench, please visit https://www.cda.cit.tum.de/mqtbench/
// MQT Bench version: 0.2.2
// Qiskit version: {'qiskit-terra': '0.22.3', 'qiskit-aer': '0.11.1', 'qiskit-ignis': None, 'qiskit-ibmq-provider': '0.19.2', 'qiskit': '0.39.3', 'qiskit-nature': '0.5.2', 'qiskit-finance': '0.3.4', 'qiskit-optimization': '0.4.0', 'qiskit-machine-learning': '0.5.0'}
// Used Gate Set: ['rz', 'sx', 'x', 'cx', 'measure']

OPENQASM 2.0;
include "qelib1.inc";
qreg q[5];
creg c[4];
rz(pi/2) q[0];
sx q[0];
rz(pi/2) q[0];
rz(pi/2) q[1];
sx q[1];
rz(pi/2) q[1];
x q[1];
rz(pi/2) q[2];
sx q[2];
rz(pi/2) q[2];
rz(pi/2) q[3];
sx q[3];
rz(pi/2) q[3];
x q[4];
rz(pi/2) q[4];
sx q[4];
rz(pi/2) q[4];
cx q[0],q[4];
rz(pi/2) q[0];
sx q[0];
rz(pi/2) q[0];
cx q[1],q[4];
x q[1];
rz(pi/2) q[1];
sx q[1];
rz(pi/2) q[1];
cx q[2],q[4];
rz(pi/2) q[2];
sx q[2];
rz(pi/2) q[2];
cx q[3],q[4];
rz(pi/2) q[3];
sx q[3];
rz(pi/2) q[3];
barrier q[0],q[1],q[2],q[3],q[4];
measure q[0] -> c[0];
measure q[1] -> c[1];
measure q[2] -> c[2];
measure q[3] -> c[3];

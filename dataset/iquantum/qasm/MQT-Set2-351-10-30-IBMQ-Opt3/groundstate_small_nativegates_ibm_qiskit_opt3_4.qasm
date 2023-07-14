// Benchmark was created by MQT Bench on 2023-06-29
// For more information about MQT Bench, please visit https://www.cda.cit.tum.de/mqtbench/
// MQT Bench version: v1.0.0
// Qiskit version: {'qiskit-terra': '0.24.1', 'qiskit-aer': '0.12.0', 'qiskit-ignis': None, 'qiskit-ibmq-provider': '0.20.2', 'qiskit': '0.43.1', 'qiskit-nature': '0.6.2', 'qiskit-finance': '0.3.4', 'qiskit-optimization': '0.5.0', 'qiskit-machine-learning': '0.6.1'}
// Used Gate Set: ['rz', 'sx', 'x', 'cx', 'measure']

OPENQASM 2.0;
include "qelib1.inc";
qreg q[4];
creg meas[4];
rz(-1.7325142602230543) q[0];
sx q[0];
rz(-pi/2) q[0];
rz(-2.1113072060070914) q[1];
cx q[0],q[1];
rz(-pi/2) q[1];
sx q[1];
rz(3.9239550032308586) q[1];
rz(1.8334560281581238) q[2];
cx q[0],q[2];
x q[0];
cx q[1],q[2];
rz(pi/2) q[2];
sx q[2];
rz(3.9333517438663517) q[2];
x q[3];
rz(-1.746960664467073) q[3];
cx q[0],q[3];
rz(1.007548850192678) q[0];
sx q[0];
rz(0.8933433677600986) q[0];
cx q[1],q[3];
cx q[0],q[1];
rz(pi/2) q[1];
sx q[1];
rz(4.513157617589647) q[1];
rz(-pi/2) q[3];
sx q[3];
rz(-1.6695183581866733) q[3];
sx q[3];
rz(pi/2) q[3];
cx q[2],q[3];
cx q[0],q[2];
cx q[1],q[2];
rz(pi/2) q[2];
sx q[2];
rz(1.6718426065189664) q[2];
rz(-pi/2) q[3];
sx q[3];
rz(0.7131310879756363) q[3];
cx q[0],q[3];
sx q[0];
rz(pi/2) q[0];
cx q[1],q[3];
sx q[1];
rz(pi/2) q[1];
cx q[2],q[3];
sx q[2];
rz(pi/2) q[2];
rz(-pi/2) q[3];
sx q[3];
rz(-2.6373670533283775) q[3];
sx q[3];
rz(-pi/2) q[3];
barrier q[0],q[1],q[2],q[3];
measure q[0] -> meas[0];
measure q[1] -> meas[1];
measure q[2] -> meas[2];
measure q[3] -> meas[3];

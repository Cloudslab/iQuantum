// Benchmark was created by MQT Bench on 2023-06-29
// For more information about MQT Bench, please visit https://www.cda.cit.tum.de/mqtbench/
// MQT Bench version: v1.0.0
// Qiskit version: {'qiskit-terra': '0.24.1', 'qiskit-aer': '0.12.0', 'qiskit-ignis': None, 'qiskit-ibmq-provider': '0.20.2', 'qiskit': '0.43.1', 'qiskit-nature': '0.6.2', 'qiskit-finance': '0.3.4', 'qiskit-optimization': '0.5.0', 'qiskit-machine-learning': '0.6.1'}
// Used Gate Set: ['rz', 'sx', 'x', 'cx', 'measure']

OPENQASM 2.0;
include "qelib1.inc";
qreg q[18];
creg c[17];
rz(-pi/2) q[0];
sx q[0];
rz(pi) q[0];
rz(-pi/2) q[1];
sx q[1];
rz(pi) q[1];
rz(pi/2) q[2];
sx q[2];
rz(pi) q[2];
rz(-pi/2) q[3];
sx q[3];
rz(pi) q[3];
rz(pi/2) q[4];
sx q[4];
rz(pi) q[4];
rz(-pi/2) q[5];
sx q[5];
rz(pi) q[5];
rz(-pi/2) q[6];
sx q[6];
rz(pi) q[6];
rz(pi/2) q[7];
sx q[7];
rz(pi) q[7];
rz(-pi/2) q[8];
sx q[8];
rz(pi) q[8];
rz(-pi/2) q[9];
sx q[9];
rz(pi) q[9];
rz(pi/2) q[10];
sx q[10];
rz(pi) q[10];
rz(-pi/2) q[11];
sx q[11];
rz(pi) q[11];
rz(-pi/2) q[12];
sx q[12];
rz(pi) q[12];
rz(pi/2) q[13];
sx q[13];
rz(pi) q[13];
rz(pi/2) q[14];
sx q[14];
rz(pi) q[14];
rz(-pi/2) q[15];
sx q[15];
rz(pi) q[15];
rz(pi/2) q[16];
sx q[16];
rz(pi) q[16];
rz(pi/2) q[17];
sx q[17];
rz(-pi/2) q[17];
cx q[0],q[17];
sx q[0];
rz(-pi/2) q[0];
cx q[1],q[17];
sx q[1];
rz(-pi/2) q[1];
cx q[2],q[17];
sx q[2];
rz(pi/2) q[2];
cx q[3],q[17];
sx q[3];
rz(-pi/2) q[3];
cx q[4],q[17];
sx q[4];
rz(pi/2) q[4];
cx q[5],q[17];
sx q[5];
rz(-pi/2) q[5];
cx q[6],q[17];
sx q[6];
rz(-pi/2) q[6];
cx q[7],q[17];
sx q[7];
rz(pi/2) q[7];
cx q[8],q[17];
sx q[8];
rz(-pi/2) q[8];
cx q[9],q[17];
cx q[10],q[17];
sx q[10];
rz(pi/2) q[10];
cx q[11],q[17];
sx q[11];
rz(-pi/2) q[11];
cx q[12],q[17];
sx q[12];
rz(-pi/2) q[12];
cx q[13],q[17];
sx q[13];
rz(pi/2) q[13];
cx q[14],q[17];
sx q[14];
rz(pi/2) q[14];
cx q[15],q[17];
sx q[15];
rz(-pi/2) q[15];
cx q[16],q[17];
sx q[16];
rz(pi/2) q[16];
sx q[9];
rz(-pi/2) q[9];
barrier q[0],q[1],q[2],q[3],q[4],q[5],q[6],q[7],q[8],q[9],q[10],q[11],q[12],q[13],q[14],q[15],q[16],q[17];
measure q[0] -> c[0];
measure q[1] -> c[1];
measure q[2] -> c[2];
measure q[3] -> c[3];
measure q[4] -> c[4];
measure q[5] -> c[5];
measure q[6] -> c[6];
measure q[7] -> c[7];
measure q[8] -> c[8];
measure q[9] -> c[9];
measure q[10] -> c[10];
measure q[11] -> c[11];
measure q[12] -> c[12];
measure q[13] -> c[13];
measure q[14] -> c[14];
measure q[15] -> c[15];
measure q[16] -> c[16];

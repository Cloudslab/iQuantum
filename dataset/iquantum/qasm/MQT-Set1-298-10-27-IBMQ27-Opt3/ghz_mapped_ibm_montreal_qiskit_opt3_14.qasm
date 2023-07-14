// Benchmark was created by MQT Bench on 2023-06-29
// For more information about MQT Bench, please visit https://www.cda.cit.tum.de/mqtbench/
// MQT Bench version: v1.0.0
// Qiskit version: {'qiskit-terra': '0.24.1', 'qiskit-aer': '0.12.0', 'qiskit-ignis': None, 'qiskit-ibmq-provider': '0.20.2', 'qiskit': '0.43.1', 'qiskit-nature': '0.6.2', 'qiskit-finance': '0.3.4', 'qiskit-optimization': '0.5.0', 'qiskit-machine-learning': '0.6.1'}
// Used Gate Set: ['rz', 'sx', 'x', 'cx', 'measure']
// Coupling List: [[0, 1], [1, 0], [1, 2], [1, 4], [2, 1], [2, 3], [3, 2], [3, 5], [4, 1], [4, 7], [5, 3], [5, 8], [6, 7], [7, 4], [7, 6], [7, 10], [8, 5], [8, 9], [8, 11], [9, 8], [10, 7], [10, 12], [11, 8], [11, 14], [12, 10], [12, 13], [12, 15], [13, 12], [13, 14], [14, 11], [14, 13], [14, 16], [15, 12], [15, 18], [16, 14], [16, 19], [17, 18], [18, 15], [18, 17], [18, 21], [19, 16], [19, 20], [19, 22], [20, 19], [21, 18], [21, 23], [22, 19], [22, 25], [23, 21], [23, 24], [24, 23], [24, 25], [25, 22], [25, 24], [25, 26], [26, 25]]

OPENQASM 2.0;
include "qelib1.inc";
qreg q[27];
creg meas[14];
rz(pi/2) q[26];
sx q[26];
rz(pi/2) q[26];
cx q[26],q[25];
cx q[25],q[24];
cx q[24],q[23];
cx q[23],q[21];
cx q[21],q[18];
cx q[18],q[15];
cx q[15],q[12];
cx q[12],q[13];
cx q[13],q[14];
cx q[14],q[11];
cx q[11],q[8];
cx q[8],q[5];
cx q[5],q[3];
barrier q[3],q[5],q[8],q[11],q[14],q[13],q[12],q[15],q[18],q[21],q[23],q[24],q[25],q[26];
measure q[3] -> meas[0];
measure q[5] -> meas[1];
measure q[8] -> meas[2];
measure q[11] -> meas[3];
measure q[14] -> meas[4];
measure q[13] -> meas[5];
measure q[12] -> meas[6];
measure q[15] -> meas[7];
measure q[18] -> meas[8];
measure q[21] -> meas[9];
measure q[23] -> meas[10];
measure q[24] -> meas[11];
measure q[25] -> meas[12];
measure q[26] -> meas[13];

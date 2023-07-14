// Benchmark was created by MQT Bench on 2023-06-29
// For more information about MQT Bench, please visit https://www.cda.cit.tum.de/mqtbench/
// MQT Bench version: v1.0.0
// Qiskit version: {'qiskit-terra': '0.24.1', 'qiskit-aer': '0.12.0', 'qiskit-ignis': None, 'qiskit-ibmq-provider': '0.20.2', 'qiskit': '0.43.1', 'qiskit-nature': '0.6.2', 'qiskit-finance': '0.3.4', 'qiskit-optimization': '0.5.0', 'qiskit-machine-learning': '0.6.1'}
// Used Gate Set: ['rz', 'sx', 'x', 'cx', 'measure']
// Coupling List: [[0, 1], [1, 0], [1, 2], [1, 4], [2, 1], [2, 3], [3, 2], [3, 5], [4, 1], [4, 7], [5, 3], [5, 8], [6, 7], [7, 4], [7, 6], [7, 10], [8, 5], [8, 9], [8, 11], [9, 8], [10, 7], [10, 12], [11, 8], [11, 14], [12, 10], [12, 13], [12, 15], [13, 12], [13, 14], [14, 11], [14, 13], [14, 16], [15, 12], [15, 18], [16, 14], [16, 19], [17, 18], [18, 15], [18, 17], [18, 21], [19, 16], [19, 20], [19, 22], [20, 19], [21, 18], [21, 23], [22, 19], [22, 25], [23, 21], [23, 24], [24, 23], [24, 25], [25, 22], [25, 24], [25, 26], [26, 25]]

OPENQASM 2.0;
include "qelib1.inc";
qreg q[27];
creg meas[13];
sx q[12];
rz(0.3875966866551801) q[12];
sx q[12];
sx q[13];
rz(0.420534335283965) q[13];
sx q[13];
sx q[14];
rz(0.46364760900080615) q[14];
sx q[14];
sx q[15];
rz(0.36136712390670755) q[15];
sx q[15];
sx q[16];
rz(pi/6) q[16];
sx q[16];
sx q[18];
rz(0.33983690945412137) q[18];
sx q[18];
sx q[19];
rz(0.6154797086703874) q[19];
sx q[19];
sx q[20];
rz(pi/4) q[20];
sx q[20];
sx q[21];
rz(0.32175055439664213) q[21];
sx q[21];
sx q[23];
rz(0.30627736916966963) q[23];
sx q[23];
sx q[24];
rz(0.29284277172857553) q[24];
sx q[24];
sx q[25];
rz(0.2810349015028133) q[25];
sx q[25];
x q[26];
cx q[26],q[25];
sx q[25];
rz(0.28103490150281374) q[25];
sx q[25];
cx q[25],q[24];
sx q[24];
rz(0.29284277172857553) q[24];
sx q[24];
cx q[24],q[23];
sx q[23];
rz(0.3062773691696692) q[23];
sx q[23];
cx q[23],q[21];
sx q[21];
rz(0.32175055439664213) q[21];
sx q[21];
cx q[21],q[18];
sx q[18];
rz(0.3398369094541218) q[18];
sx q[18];
cx q[18],q[15];
sx q[15];
rz(0.36136712390670755) q[15];
sx q[15];
cx q[15],q[12];
sx q[12];
rz(0.387596686655181) q[12];
sx q[12];
cx q[12],q[13];
sx q[13];
rz(0.420534335283965) q[13];
sx q[13];
cx q[13],q[14];
sx q[14];
rz(0.46364760900080615) q[14];
sx q[14];
cx q[14],q[16];
sx q[16];
rz(pi/6) q[16];
sx q[16];
cx q[16],q[19];
sx q[19];
rz(0.6154797086703869) q[19];
sx q[19];
cx q[19],q[20];
sx q[20];
rz(pi/4) q[20];
sx q[20];
cx q[25],q[26];
cx q[24],q[25];
cx q[23],q[24];
cx q[21],q[23];
cx q[18],q[21];
cx q[15],q[18];
cx q[12],q[15];
cx q[13],q[12];
cx q[14],q[13];
cx q[16],q[14];
cx q[19],q[16];
cx q[20],q[19];
barrier q[20],q[19],q[16],q[14],q[13],q[12],q[15],q[18],q[21],q[23],q[24],q[25],q[26];
measure q[20] -> meas[0];
measure q[19] -> meas[1];
measure q[16] -> meas[2];
measure q[14] -> meas[3];
measure q[13] -> meas[4];
measure q[12] -> meas[5];
measure q[15] -> meas[6];
measure q[18] -> meas[7];
measure q[21] -> meas[8];
measure q[23] -> meas[9];
measure q[24] -> meas[10];
measure q[25] -> meas[11];
measure q[26] -> meas[12];

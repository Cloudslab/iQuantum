// Benchmark was created by MQT Bench on 2023-06-29
// For more information about MQT Bench, please visit https://www.cda.cit.tum.de/mqtbench/
// MQT Bench version: v1.0.0
// Qiskit version: {'qiskit-terra': '0.24.1', 'qiskit-aer': '0.12.0', 'qiskit-ignis': None, 'qiskit-ibmq-provider': '0.20.2', 'qiskit': '0.43.1', 'qiskit-nature': '0.6.2', 'qiskit-finance': '0.3.4', 'qiskit-optimization': '0.5.0', 'qiskit-machine-learning': '0.6.1'}
// Used Gate Set: ['rz', 'sx', 'x', 'cx', 'measure']
// Coupling List: [[0, 1], [1, 0], [1, 2], [1, 4], [2, 1], [2, 3], [3, 2], [3, 5], [4, 1], [4, 7], [5, 3], [5, 8], [6, 7], [7, 4], [7, 6], [7, 10], [8, 5], [8, 9], [8, 11], [9, 8], [10, 7], [10, 12], [11, 8], [11, 14], [12, 10], [12, 13], [12, 15], [13, 12], [13, 14], [14, 11], [14, 13], [14, 16], [15, 12], [15, 18], [16, 14], [16, 19], [17, 18], [18, 15], [18, 17], [18, 21], [19, 16], [19, 20], [19, 22], [20, 19], [21, 18], [21, 23], [22, 19], [22, 25], [23, 21], [23, 24], [24, 23], [24, 25], [25, 22], [25, 24], [25, 26], [26, 25]]

OPENQASM 2.0;
include "qelib1.inc";
qreg q[27];
creg c[10];
creg meas[10];
rz(-pi/2) q[11];
sx q[11];
rz(-pi/4) q[12];
sx q[12];
rz(pi/4) q[15];
sx q[15];
rz(-pi/2) q[15];
rz(pi/2) q[16];
sx q[16];
rz(7*pi/8) q[16];
cx q[16],q[19];
rz(-pi/4) q[19];
cx q[16],q[19];
cx q[16],q[14];
rz(-pi/8) q[14];
cx q[16],q[14];
rz(pi/8) q[14];
rz(3*pi/4) q[19];
sx q[19];
rz(pi/2) q[19];
cx q[16],q[19];
cx q[19],q[16];
cx q[16],q[19];
rz(pi/4) q[16];
cx q[16],q[14];
rz(-pi/4) q[14];
cx q[16],q[14];
rz(3*pi/4) q[14];
sx q[14];
rz(pi/2) q[14];
rz(0.2945243112740431) q[19];
cx q[19],q[22];
rz(-pi/16) q[22];
cx q[19],q[22];
cx q[19],q[20];
rz(-pi/32) q[20];
cx q[19],q[20];
cx q[19],q[16];
cx q[16],q[19];
cx q[19],q[16];
cx q[14],q[16];
cx q[16],q[14];
cx q[14],q[16];
rz(0.07363107781851078) q[14];
cx q[14],q[13];
rz(-pi/64) q[13];
cx q[14],q[13];
rz(pi/64) q[13];
sx q[14];
rz(-pi/2) q[14];
cx q[14],q[11];
rz(-pi/2) q[11];
sx q[14];
cx q[14],q[11];
rz(1.5462526341887255) q[11];
sx q[14];
cx q[14],q[11];
rz(-3.1293208072867085) q[11];
cx q[11],q[8];
rz(-3.1170489609836225) q[14];
rz(3*pi/16) q[19];
rz(pi/32) q[20];
rz(pi/16) q[22];
cx q[19],q[22];
rz(-pi/8) q[22];
cx q[19],q[22];
cx q[19],q[20];
rz(-pi/16) q[20];
cx q[19],q[20];
cx q[16],q[19];
cx q[19],q[16];
cx q[16],q[19];
cx q[16],q[14];
cx q[14],q[16];
cx q[16],q[14];
rz(0.14726215563702155) q[14];
cx q[14],q[13];
rz(-pi/32) q[13];
cx q[14],q[13];
rz(pi/32) q[13];
cx q[14],q[16];
rz(-pi/64) q[16];
cx q[14],q[16];
rz(pi/64) q[16];
rz(3*pi/8) q[19];
rz(pi/16) q[20];
rz(pi/8) q[22];
cx q[19],q[22];
rz(-pi/4) q[22];
cx q[19],q[22];
cx q[19],q[20];
rz(-pi/8) q[20];
cx q[19],q[20];
cx q[19],q[16];
cx q[16],q[19];
cx q[19],q[16];
rz(0.2945243112740431) q[16];
rz(pi/8) q[20];
rz(3*pi/4) q[22];
sx q[22];
rz(pi/2) q[22];
rz(-pi/256) q[8];
cx q[11],q[8];
cx q[11],q[14];
cx q[14],q[11];
cx q[11],q[14];
rz(pi/128) q[11];
cx q[13],q[14];
cx q[14],q[13];
cx q[13],q[14];
rz(-0.7792622402459055) q[13];
sx q[13];
rz(-pi/2) q[13];
cx q[13],q[12];
rz(pi/2) q[12];
sx q[13];
cx q[13],q[12];
rz(1.5646604036433545) q[12];
sx q[13];
cx q[13],q[12];
rz(3.1385246920140224) q[12];
sx q[12];
rz(-pi) q[12];
rz(2.3500585670408025) q[13];
sx q[13];
rz(-pi) q[13];
cx q[15],q[12];
rz(-pi/2) q[12];
sx q[15];
cx q[15],q[12];
rz(1.5677283652191247) q[12];
sx q[15];
cx q[15],q[12];
rz(-2.3531265286165732) q[12];
sx q[12];
rz(-pi) q[12];
rz(3*pi/4) q[15];
cx q[16],q[14];
rz(-pi/16) q[14];
cx q[16],q[14];
rz(pi/16) q[14];
cx q[16],q[19];
rz(-pi/32) q[19];
cx q[16],q[19];
cx q[16],q[14];
cx q[14],q[16];
cx q[16],q[14];
rz(pi/32) q[19];
cx q[22],q[19];
cx q[19],q[22];
cx q[22],q[19];
rz(7*pi/16) q[19];
cx q[19],q[20];
rz(-pi/4) q[20];
cx q[19],q[20];
cx q[19],q[16];
rz(-pi/8) q[16];
cx q[19],q[16];
rz(pi/8) q[16];
cx q[19],q[22];
rz(3*pi/4) q[20];
sx q[20];
rz(7*pi/8) q[20];
rz(-pi/16) q[22];
cx q[19],q[22];
cx q[16],q[19];
cx q[19],q[16];
cx q[16],q[19];
cx q[20],q[19];
rz(-pi/4) q[19];
cx q[20],q[19];
rz(3*pi/4) q[19];
sx q[19];
rz(pi/2) q[19];
rz(pi/16) q[22];
cx q[22],q[19];
cx q[19],q[22];
cx q[22],q[19];
cx q[20],q[19];
rz(-pi/8) q[19];
cx q[20],q[19];
rz(pi/8) q[19];
rz(1.4726215563702154) q[22];
cx q[22],q[19];
rz(-pi/4) q[19];
cx q[22],q[19];
rz(3*pi/4) q[19];
sx q[19];
rz(pi/2) q[19];
cx q[20],q[19];
cx q[19],q[20];
cx q[20],q[19];
rz(7*pi/16) q[20];
rz(pi/256) q[8];
cx q[11],q[8];
rz(-pi/128) q[8];
cx q[11],q[8];
cx q[14],q[11];
cx q[11],q[14];
cx q[14],q[11];
rz(0.07363107781851078) q[11];
rz(pi/256) q[14];
sx q[14];
rz(-pi/2) q[14];
cx q[14],q[13];
rz(-pi/2) q[13];
sx q[14];
cx q[14],q[13];
rz(1.558524480491812) q[13];
sx q[14];
cx q[14],q[13];
rz(1.576932249946438) q[13];
sx q[13];
rz(pi/2) q[13];
cx q[13],q[12];
rz(-pi/2) q[12];
sx q[13];
cx q[13],q[12];
rz(1.5646604036433531) q[12];
sx q[13];
cx q[13],q[12];
x q[12];
rz(pi/2) q[12];
x q[13];
rz(0.006135923151541434) q[13];
rz(-3.1293208072867076) q[14];
rz(pi/128) q[8];
cx q[11],q[8];
rz(-pi/64) q[8];
cx q[11],q[8];
cx q[11],q[14];
rz(-pi/128) q[14];
cx q[11],q[14];
rz(pi/128) q[14];
cx q[16],q[14];
cx q[14],q[16];
cx q[16],q[14];
cx q[11],q[14];
cx q[14],q[11];
cx q[11],q[14];
rz(pi/32) q[11];
rz(pi/256) q[14];
cx q[14],q[13];
rz(-pi/256) q[13];
cx q[14],q[13];
rz(pi/256) q[13];
rz(pi/64) q[8];
cx q[11],q[8];
rz(-pi/32) q[8];
cx q[11],q[8];
cx q[11],q[14];
cx q[14],q[11];
cx q[11],q[14];
rz(0.07363107781851078) q[14];
cx q[14],q[16];
rz(-pi/64) q[16];
cx q[14],q[16];
cx q[14],q[13];
rz(-pi/128) q[13];
cx q[14],q[13];
rz(-1.546252634188726) q[13];
sx q[13];
rz(pi/64) q[16];
cx q[19],q[16];
cx q[16],q[19];
cx q[19],q[16];
rz(0.2945243112740431) q[16];
rz(pi/32) q[8];
cx q[8],q[11];
cx q[11],q[8];
cx q[8],q[11];
cx q[11],q[14];
cx q[14],q[11];
cx q[11],q[14];
cx q[16],q[14];
rz(-pi/16) q[14];
cx q[16],q[14];
rz(pi/16) q[14];
cx q[16],q[19];
rz(-pi/32) q[19];
cx q[16],q[19];
cx q[14],q[16];
cx q[16],q[14];
cx q[14],q[16];
rz(pi/64) q[14];
sx q[14];
rz(-pi/2) q[14];
cx q[14],q[13];
rz(-pi/2) q[13];
sx q[14];
cx q[14],q[13];
rz(1.5217089415825562) q[13];
sx q[14];
cx q[14],q[13];
rz(-pi) q[13];
rz(-3.092505268377453) q[14];
rz(pi/32) q[19];
cx q[16],q[19];
cx q[19],q[16];
cx q[16],q[19];
cx q[22],q[19];
rz(-pi/8) q[19];
cx q[22],q[19];
rz(pi/8) q[19];
cx q[20],q[19];
rz(-pi/4) q[19];
cx q[20],q[19];
rz(3*pi/4) q[19];
sx q[19];
rz(pi/2) q[19];
cx q[16],q[19];
cx q[19],q[16];
cx q[16],q[19];
rz(pi/4) q[16];
cx q[22],q[19];
rz(-pi/16) q[19];
cx q[22],q[19];
rz(pi/16) q[19];
cx q[20],q[19];
rz(-pi/8) q[19];
cx q[20],q[19];
rz(pi/8) q[19];
cx q[16],q[19];
rz(-pi/4) q[19];
cx q[16],q[19];
cx q[14],q[16];
cx q[16],q[14];
cx q[14],q[16];
rz(pi/8) q[14];
rz(3*pi/4) q[19];
sx q[19];
rz(pi/2) q[19];
cx q[16],q[19];
cx q[19],q[16];
cx q[16],q[19];
cx q[22],q[19];
rz(-pi/32) q[19];
cx q[22],q[19];
rz(pi/32) q[19];
cx q[20],q[19];
rz(-pi/16) q[19];
cx q[20],q[19];
rz(pi/16) q[19];
cx q[19],q[16];
cx q[16],q[19];
cx q[19],q[16];
cx q[14],q[16];
rz(-pi/8) q[16];
cx q[14],q[16];
cx q[14],q[11];
cx q[11],q[14];
cx q[14],q[11];
cx q[11],q[8];
rz(pi/8) q[16];
rz(pi/4) q[19];
cx q[19],q[16];
rz(-pi/4) q[16];
cx q[19],q[16];
rz(3*pi/4) q[16];
sx q[16];
rz(pi/2) q[16];
cx q[16],q[14];
cx q[14],q[16];
cx q[16],q[14];
cx q[14],q[13];
cx q[13],q[14];
cx q[14],q[13];
cx q[13],q[12];
cx q[12],q[13];
cx q[13],q[12];
cx q[12],q[15];
cx q[15],q[12];
cx q[12],q[15];
cx q[19],q[16];
cx q[16],q[19];
cx q[19],q[16];
cx q[16],q[14];
cx q[14],q[16];
cx q[16],q[14];
cx q[14],q[13];
cx q[13],q[14];
cx q[14],q[13];
cx q[20],q[19];
cx q[19],q[20];
cx q[20],q[19];
cx q[16],q[19];
cx q[19],q[16];
cx q[16],q[19];
cx q[22],q[19];
cx q[19],q[22];
cx q[22],q[19];
cx q[8],q[11];
cx q[11],q[8];
barrier q[12],q[14],q[11],q[20],q[22],q[19],q[16],q[8],q[13],q[15];
measure q[12] -> meas[0];
measure q[14] -> meas[1];
measure q[11] -> meas[2];
measure q[20] -> meas[3];
measure q[22] -> meas[4];
measure q[19] -> meas[5];
measure q[16] -> meas[6];
measure q[8] -> meas[7];
measure q[13] -> meas[8];
measure q[15] -> meas[9];

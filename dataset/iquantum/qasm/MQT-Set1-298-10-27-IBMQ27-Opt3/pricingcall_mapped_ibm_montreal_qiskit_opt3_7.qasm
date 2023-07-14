// Benchmark was created by MQT Bench on 2023-06-29
// For more information about MQT Bench, please visit https://www.cda.cit.tum.de/mqtbench/
// MQT Bench version: v1.0.0
// Qiskit version: {'qiskit-terra': '0.24.1', 'qiskit-aer': '0.12.0', 'qiskit-ignis': None, 'qiskit-ibmq-provider': '0.20.2', 'qiskit': '0.43.1', 'qiskit-nature': '0.6.2', 'qiskit-finance': '0.3.4', 'qiskit-optimization': '0.5.0', 'qiskit-machine-learning': '0.6.1'}
// Used Gate Set: ['rz', 'sx', 'x', 'cx', 'measure']
// Coupling List: [[0, 1], [1, 0], [1, 2], [1, 4], [2, 1], [2, 3], [3, 2], [3, 5], [4, 1], [4, 7], [5, 3], [5, 8], [6, 7], [7, 4], [7, 6], [7, 10], [8, 5], [8, 9], [8, 11], [9, 8], [10, 7], [10, 12], [11, 8], [11, 14], [12, 10], [12, 13], [12, 15], [13, 12], [13, 14], [14, 11], [14, 13], [14, 16], [15, 12], [15, 18], [16, 14], [16, 19], [17, 18], [18, 15], [18, 17], [18, 21], [19, 16], [19, 20], [19, 22], [20, 19], [21, 18], [21, 23], [22, 19], [22, 25], [23, 21], [23, 24], [24, 23], [24, 25], [25, 22], [25, 24], [25, 26], [26, 25]]

OPENQASM 2.0;
include "qelib1.inc";
qreg q[27];
creg meas[7];
rz(-pi) q[5];
sx q[5];
rz(5*pi/8) q[5];
sx q[5];
rz(-pi) q[8];
sx q[8];
rz(1.4323415210217458) q[8];
sx q[8];
rz(pi/2) q[9];
sx q[9];
rz(-pi/2) q[9];
rz(-pi) q[11];
sx q[11];
rz(1.458719605612079) q[11];
sx q[11];
rz(-0.7054916355003247) q[12];
sx q[12];
rz(pi/2) q[12];
rz(-pi) q[14];
sx q[14];
rz(1.630788126696773) q[14];
sx q[14];
cx q[14],q[11];
rz(-pi) q[11];
sx q[11];
rz(2.106141886115508) q[11];
sx q[11];
cx q[14],q[11];
cx q[11],q[8];
cx q[14],q[11];
cx q[11],q[14];
cx q[14],q[11];
sx q[11];
rz(-pi) q[8];
sx q[8];
rz(2.8238426747998497) q[8];
sx q[8];
cx q[8],q[11];
rz(pi/2) q[11];
sx q[8];
rz(-pi/2) q[8];
sx q[8];
cx q[8],q[11];
rz(1.4171072475062267) q[11];
sx q[11];
cx q[14],q[11];
rz(-pi) q[11];
sx q[11];
rz(2.460188037783457) q[11];
sx q[11];
rz(-pi/2) q[8];
sx q[8];
rz(-pi/2) q[8];
cx q[8],q[11];
cx q[11],q[14];
cx q[14],q[11];
cx q[11],q[14];
cx q[14],q[13];
cx q[11],q[14];
rz(-0.8375296682261526) q[13];
sx q[13];
rz(-pi) q[13];
cx q[12],q[13];
sx q[12];
rz(-pi/2) q[12];
sx q[12];
rz(pi/2) q[13];
cx q[12],q[13];
rz(pi/2) q[12];
sx q[12];
rz(2.408325995021049) q[12];
sx q[13];
rz(2.4361010180894684) q[13];
sx q[13];
rz(-3*pi/4) q[13];
cx q[14],q[11];
cx q[11],q[14];
cx q[14],q[13];
rz(pi/4) q[13];
cx q[12],q[13];
sx q[12];
rz(3*pi/4) q[13];
rz(-pi) q[14];
sx q[14];
rz(-pi) q[14];
cx q[13],q[14];
sx q[13];
rz(-pi/2) q[13];
sx q[13];
rz(pi/2) q[14];
cx q[13],q[14];
rz(-pi/2) q[13];
sx q[13];
rz(-3*pi/4) q[13];
sx q[13];
rz(pi/2) q[13];
cx q[13],q[12];
rz(-pi/2) q[12];
sx q[13];
cx q[13],q[12];
rz(pi/4) q[12];
sx q[13];
cx q[13],q[12];
rz(-1.582490104410196) q[12];
rz(-pi/4) q[13];
sx q[13];
rz(pi/2) q[13];
rz(-pi/2) q[14];
sx q[14];
rz(-pi/4) q[14];
sx q[14];
rz(pi/2) q[14];
cx q[14],q[11];
cx q[11],q[14];
cx q[14],q[11];
cx q[5],q[8];
cx q[8],q[5];
cx q[5],q[8];
x q[5];
cx q[9],q[8];
cx q[8],q[9];
cx q[9],q[8];
cx q[11],q[8];
rz(-pi/4) q[8];
cx q[5],q[8];
rz(pi/4) q[8];
cx q[11],q[8];
rz(pi/4) q[11];
rz(-pi/4) q[8];
cx q[5],q[8];
rz(-pi/4) q[8];
sx q[8];
rz(0.9896177343255683) q[8];
rz(-pi) q[9];
sx q[9];
rz(-pi/2) q[9];
cx q[8],q[9];
x q[8];
rz(0.24518440430752222) q[9];
cx q[8],q[9];
rz(-0.5811785924693282) q[8];
cx q[5],q[8];
cx q[8],q[5];
cx q[5],q[8];
cx q[8],q[11];
rz(-pi/4) q[11];
rz(pi/4) q[8];
cx q[8],q[11];
rz(pi/2) q[11];
sx q[11];
rz(-pi/2) q[11];
cx q[11],q[14];
cx q[14],q[11];
cx q[11],q[14];
x q[14];
rz(pi/2) q[14];
cx q[13],q[14];
sx q[13];
rz(-pi/2) q[13];
sx q[13];
rz(pi/2) q[14];
cx q[13],q[14];
sx q[13];
rz(-pi/4) q[13];
cx q[12],q[13];
sx q[12];
rz(pi/2) q[12];
rz(pi/4) q[13];
rz(-pi) q[14];
sx q[14];
rz(-pi/4) q[14];
cx q[14],q[13];
x q[13];
rz(-pi/4) q[13];
cx q[12],q[13];
sx q[12];
rz(-pi/2) q[12];
sx q[12];
rz(pi/2) q[13];
cx q[12],q[13];
sx q[12];
rz(pi/4) q[12];
rz(-pi) q[13];
sx q[13];
rz(-2.3445007125770463) q[13];
cx q[13],q[14];
rz(-pi/4) q[14];
cx q[11],q[14];
x q[8];
rz(2.8964082492822696) q[9];
sx q[9];
cx q[9],q[8];
cx q[8],q[9];
cx q[9],q[8];
cx q[11],q[8];
rz(-pi/4) q[8];
cx q[5],q[8];
rz(-pi) q[5];
sx q[5];
rz(-pi/2) q[5];
rz(pi/4) q[8];
cx q[11],q[8];
rz(pi/4) q[11];
x q[8];
rz(-pi/4) q[8];
cx q[5],q[8];
sx q[5];
rz(-pi/2) q[5];
sx q[5];
rz(pi/2) q[8];
cx q[5],q[8];
sx q[5];
rz(3*pi/4) q[5];
sx q[8];
rz(-3.1384746608363177) q[8];
cx q[8],q[11];
rz(-pi/4) q[11];
cx q[8],q[11];
sx q[8];
rz(-pi/2) q[8];
cx q[8],q[5];
rz(pi/2) q[5];
sx q[8];
cx q[8],q[5];
rz(1.5217283663328773) q[5];
sx q[8];
cx q[8],q[5];
x q[5];
rz(-0.7885161561509246) q[5];
rz(-1.6198642872569153) q[8];
sx q[8];
cx q[11],q[8];
rz(-pi/4) q[8];
cx q[5],q[8];
sx q[5];
rz(-pi/2) q[5];
rz(pi/4) q[8];
cx q[11],q[8];
rz(pi/4) q[11];
x q[8];
rz(-pi/4) q[8];
cx q[5],q[8];
sx q[5];
rz(-pi/2) q[5];
sx q[5];
rz(pi/2) q[8];
cx q[5],q[8];
rz(-pi) q[5];
sx q[5];
rz(pi/4) q[5];
sx q[8];
rz(0.6761607882299066) q[8];
cx q[8],q[11];
rz(-pi/4) q[11];
cx q[8],q[11];
cx q[11],q[14];
cx q[14],q[13];
cx q[13],q[14];
sx q[13];
cx q[12],q[13];
sx q[12];
rz(-pi/2) q[12];
sx q[12];
rz(pi/2) q[13];
cx q[12],q[13];
rz(-pi/2) q[12];
sx q[12];
rz(pi/2) q[12];
rz(pi/2) q[13];
sx q[13];
rz(3*pi/4) q[13];
cx q[14],q[11];
cx q[11],q[14];
cx q[14],q[11];
sx q[8];
rz(-pi/2) q[8];
cx q[8],q[5];
rz(pi/2) q[5];
sx q[8];
cx q[8],q[5];
rz(1.4726604058708586) q[5];
sx q[8];
cx q[8],q[5];
x q[5];
rz(-4.603151605217149) q[5];
rz(1.472660405870859) q[8];
sx q[8];
rz(-pi) q[8];
cx q[11],q[8];
rz(-pi/4) q[8];
cx q[5],q[8];
sx q[5];
rz(-pi/2) q[5];
rz(pi/4) q[8];
cx q[11],q[8];
rz(pi/4) q[11];
x q[8];
rz(-pi/4) q[8];
cx q[5],q[8];
sx q[5];
rz(-pi/2) q[5];
sx q[5];
rz(pi/2) q[8];
cx q[5],q[8];
rz(-pi) q[5];
sx q[5];
rz(pi/4) q[5];
sx q[8];
rz(-pi/4) q[8];
cx q[8],q[11];
rz(-pi/4) q[11];
cx q[8],q[11];
sx q[8];
rz(pi/2) q[8];
cx q[8],q[5];
rz(pi/2) q[5];
sx q[8];
cx q[8],q[5];
rz(1.4726604058708586) q[5];
sx q[8];
cx q[8],q[5];
rz(1.668932247718936) q[8];
sx q[8];
rz(-pi) q[8];
cx q[11],q[8];
rz(-pi/4) q[8];
cx q[5],q[8];
sx q[5];
rz(-pi/2) q[5];
rz(pi/4) q[8];
cx q[11],q[8];
rz(pi/4) q[11];
x q[8];
rz(-pi/4) q[8];
cx q[5],q[8];
sx q[5];
rz(-pi/2) q[5];
sx q[5];
rz(pi/2) q[8];
cx q[5],q[8];
sx q[5];
rz(-3*pi/4) q[5];
sx q[5];
rz(-pi/2) q[5];
sx q[8];
rz(-pi/4) q[8];
cx q[8],q[11];
rz(-pi/4) q[11];
cx q[8],q[11];
cx q[11],q[14];
cx q[14],q[11];
cx q[11],q[14];
cx q[14],q[13];
rz(pi/4) q[13];
cx q[12],q[13];
sx q[12];
rz(3*pi/4) q[13];
rz(-pi) q[14];
sx q[14];
rz(-pi) q[14];
cx q[13],q[14];
sx q[13];
rz(-pi/2) q[13];
sx q[13];
rz(pi/2) q[14];
cx q[13],q[14];
rz(-pi/2) q[13];
sx q[13];
rz(-3*pi/4) q[13];
sx q[13];
rz(pi/2) q[13];
cx q[13],q[12];
rz(-pi/2) q[12];
sx q[13];
cx q[13],q[12];
rz(pi/4) q[12];
sx q[13];
cx q[13],q[12];
rz(-pi/4) q[13];
sx q[13];
rz(pi/2) q[13];
rz(-pi/2) q[14];
sx q[14];
rz(-pi/4) q[14];
sx q[14];
rz(pi/2) q[14];
cx q[14],q[11];
cx q[11],q[14];
cx q[14],q[11];
cx q[8],q[5];
sx q[5];
rz(2.945320811741717) q[5];
sx q[5];
rz(-pi) q[5];
cx q[8],q[5];
sx q[5];
rz(1.7670681686429717) q[5];
sx q[5];
cx q[9],q[8];
cx q[8],q[9];
cx q[9],q[8];
cx q[8],q[5];
rz(-pi/4) q[5];
cx q[9],q[8];
cx q[8],q[9];
cx q[9],q[8];
cx q[8],q[5];
rz(pi/4) q[5];
cx q[9],q[8];
cx q[8],q[9];
cx q[9],q[8];
cx q[8],q[5];
rz(-pi/4) q[5];
rz(pi/4) q[8];
cx q[9],q[8];
cx q[8],q[9];
cx q[9],q[8];
cx q[8],q[5];
rz(3*pi/4) q[5];
sx q[5];
rz(pi/2) q[5];
cx q[8],q[9];
rz(pi/4) q[8];
rz(-pi/4) q[9];
cx q[8],q[9];
cx q[8],q[5];
rz(-pi) q[5];
sx q[5];
rz(2.945320811741717) q[5];
sx q[5];
cx q[8],q[5];
sx q[5];
rz(1.3745244849468197) q[5];
sx q[5];
cx q[9],q[8];
cx q[8],q[9];
cx q[9],q[8];
cx q[8],q[5];
rz(-pi/4) q[5];
cx q[9],q[8];
cx q[8],q[9];
cx q[9],q[8];
cx q[8],q[5];
rz(pi/4) q[5];
cx q[9],q[8];
cx q[8],q[9];
cx q[9],q[8];
cx q[8],q[5];
rz(-pi/4) q[5];
rz(pi/4) q[8];
cx q[9],q[8];
cx q[8],q[9];
cx q[9],q[8];
cx q[8],q[5];
rz(3*pi/4) q[5];
sx q[5];
rz(pi/2) q[5];
cx q[8],q[9];
rz(3*pi/4) q[8];
rz(-pi/4) q[9];
cx q[8],q[9];
sx q[8];
rz(pi/2) q[8];
cx q[11],q[8];
rz(-pi/4) q[8];
x q[9];
cx q[9],q[8];
rz(pi/4) q[8];
cx q[11],q[8];
rz(pi/4) q[11];
rz(3*pi/4) q[8];
rz(-pi) q[9];
sx q[9];
rz(-pi) q[9];
cx q[8],q[9];
sx q[8];
rz(-pi/2) q[8];
sx q[8];
rz(pi/2) q[9];
cx q[8],q[9];
rz(pi/2) q[8];
sx q[8];
rz(3*pi/4) q[8];
cx q[8],q[11];
rz(-pi/4) q[11];
cx q[8],q[11];
rz(pi/2) q[11];
sx q[11];
rz(-pi/2) q[11];
cx q[11],q[14];
cx q[14],q[11];
cx q[11],q[14];
x q[14];
rz(pi/2) q[14];
cx q[13],q[14];
sx q[13];
rz(-pi/2) q[13];
sx q[13];
rz(pi/2) q[14];
cx q[13],q[14];
sx q[13];
rz(-pi/4) q[13];
cx q[12],q[13];
sx q[12];
rz(-pi/2) q[12];
rz(pi/4) q[13];
rz(-pi) q[14];
sx q[14];
rz(-pi/4) q[14];
cx q[14],q[13];
x q[13];
rz(-pi/4) q[13];
cx q[12],q[13];
sx q[12];
rz(-pi/2) q[12];
sx q[12];
rz(pi/2) q[13];
cx q[12],q[13];
sx q[12];
rz(-3*pi/4) q[12];
sx q[12];
rz(-pi/2) q[12];
sx q[13];
rz(-pi/4) q[13];
cx q[13],q[14];
rz(-pi/4) q[14];
cx q[13],q[14];
cx q[11],q[14];
x q[8];
rz(-pi/2) q[9];
sx q[9];
rz(-pi/4) q[9];
sx q[9];
rz(pi/2) q[9];
barrier q[11],q[13],q[8],q[5],q[9],q[14],q[12];
measure q[11] -> meas[0];
measure q[13] -> meas[1];
measure q[8] -> meas[2];
measure q[5] -> meas[3];
measure q[9] -> meas[4];
measure q[14] -> meas[5];
measure q[12] -> meas[6];

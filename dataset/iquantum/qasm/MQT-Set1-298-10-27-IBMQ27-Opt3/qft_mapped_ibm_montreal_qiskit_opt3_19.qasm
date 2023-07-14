// Benchmark was created by MQT Bench on 2023-06-29
// For more information about MQT Bench, please visit https://www.cda.cit.tum.de/mqtbench/
// MQT Bench version: v1.0.0
// Qiskit version: {'qiskit-terra': '0.24.1', 'qiskit-aer': '0.12.0', 'qiskit-ignis': None, 'qiskit-ibmq-provider': '0.20.2', 'qiskit': '0.43.1', 'qiskit-nature': '0.6.2', 'qiskit-finance': '0.3.4', 'qiskit-optimization': '0.5.0', 'qiskit-machine-learning': '0.6.1'}
// Used Gate Set: ['rz', 'sx', 'x', 'cx', 'measure']
// Coupling List: [[0, 1], [1, 0], [1, 2], [1, 4], [2, 1], [2, 3], [3, 2], [3, 5], [4, 1], [4, 7], [5, 3], [5, 8], [6, 7], [7, 4], [7, 6], [7, 10], [8, 5], [8, 9], [8, 11], [9, 8], [10, 7], [10, 12], [11, 8], [11, 14], [12, 10], [12, 13], [12, 15], [13, 12], [13, 14], [14, 11], [14, 13], [14, 16], [15, 12], [15, 18], [16, 14], [16, 19], [17, 18], [18, 15], [18, 17], [18, 21], [19, 16], [19, 20], [19, 22], [20, 19], [21, 18], [21, 23], [22, 19], [22, 25], [23, 21], [23, 24], [24, 23], [24, 25], [25, 22], [25, 24], [25, 26], [26, 25]]

OPENQASM 2.0;
include "qelib1.inc";
qreg q[27];
creg c[19];
creg meas[19];
sx q[1];
rz(pi/2) q[3];
sx q[3];
rz(7*pi/8) q[3];
sx q[4];
cx q[3],q[5];
rz(-pi/4) q[5];
cx q[3],q[5];
cx q[3],q[2];
rz(-pi/8) q[2];
cx q[3],q[2];
rz(pi/8) q[2];
rz(3*pi/4) q[5];
sx q[5];
rz(pi/2) q[5];
cx q[3],q[5];
cx q[5],q[3];
cx q[3],q[5];
rz(pi/4) q[3];
cx q[3],q[2];
rz(-pi/4) q[2];
cx q[3],q[2];
rz(3*pi/4) q[2];
sx q[2];
rz(pi/2) q[2];
sx q[7];
cx q[5],q[8];
cx q[8],q[5];
cx q[5],q[8];
rz(0.2945243112740431) q[8];
cx q[8],q[11];
rz(-pi/16) q[11];
cx q[8],q[11];
rz(pi/16) q[11];
cx q[8],q[5];
rz(-pi/32) q[5];
cx q[8],q[5];
rz(pi/32) q[5];
cx q[3],q[5];
cx q[5],q[3];
cx q[3],q[5];
rz(3*pi/16) q[5];
cx q[8],q[11];
cx q[11],q[8];
cx q[8],q[11];
cx q[5],q[8];
rz(-pi/8) q[8];
cx q[5],q[8];
cx q[5],q[3];
rz(-pi/16) q[3];
cx q[5],q[3];
rz(pi/16) q[3];
cx q[2],q[3];
cx q[3],q[2];
cx q[2],q[3];
rz(3*pi/8) q[3];
rz(pi/8) q[8];
cx q[5],q[8];
cx q[8],q[5];
cx q[5],q[8];
cx q[3],q[5];
rz(-pi/4) q[5];
cx q[3],q[5];
cx q[3],q[2];
rz(-pi/8) q[2];
cx q[3],q[2];
rz(pi/8) q[2];
rz(3*pi/4) q[5];
sx q[5];
rz(pi/2) q[5];
cx q[3],q[5];
cx q[5],q[3];
cx q[3],q[5];
rz(pi/4) q[3];
cx q[3],q[2];
rz(-pi/4) q[2];
cx q[3],q[2];
rz(3*pi/4) q[2];
sx q[2];
rz(pi/2) q[2];
rz(pi/2) q[12];
sx q[12];
rz(-pi) q[12];
cx q[11],q[14];
cx q[14],q[11];
cx q[11],q[14];
rz(0.0859029241215959) q[14];
cx q[14],q[16];
rz(-pi/64) q[16];
cx q[14],q[16];
cx q[14],q[11];
rz(-pi/128) q[11];
cx q[14],q[11];
rz(pi/128) q[11];
cx q[14],q[13];
rz(-pi/256) q[13];
cx q[14],q[13];
rz(pi/256) q[13];
rz(pi/64) q[16];
cx q[14],q[16];
cx q[16],q[14];
cx q[14],q[16];
rz(-0.779262240245906) q[16];
sx q[16];
cx q[8],q[11];
cx q[11],q[8];
cx q[8],q[11];
rz(0.14726215563702155) q[11];
cx q[11],q[14];
rz(-pi/32) q[14];
cx q[11],q[14];
cx q[11],q[8];
rz(pi/32) q[14];
rz(-pi/64) q[8];
cx q[11],q[8];
cx q[11],q[14];
cx q[14],q[11];
cx q[11],q[14];
rz(-3.1047771146805374) q[14];
cx q[14],q[13];
rz(-pi/128) q[13];
cx q[14],q[13];
rz(pi/128) q[13];
sx q[14];
rz(-pi) q[14];
rz(pi/64) q[8];
cx q[5],q[8];
cx q[8],q[5];
cx q[5],q[8];
rz(0.2945243112740431) q[8];
cx q[8],q[11];
rz(-pi/16) q[11];
cx q[8],q[11];
rz(pi/16) q[11];
cx q[8],q[5];
rz(-pi/32) q[5];
cx q[8],q[5];
rz(pi/32) q[5];
cx q[3],q[5];
cx q[5],q[3];
cx q[3],q[5];
rz(3*pi/16) q[5];
cx q[8],q[11];
cx q[11],q[8];
cx q[8],q[11];
cx q[5],q[8];
rz(-pi/8) q[8];
cx q[5],q[8];
cx q[5],q[3];
rz(-pi/16) q[3];
cx q[5],q[3];
rz(pi/16) q[3];
cx q[2],q[3];
cx q[3],q[2];
cx q[2],q[3];
rz(3*pi/8) q[3];
rz(pi/8) q[8];
cx q[5],q[8];
cx q[8],q[5];
cx q[5],q[8];
cx q[3],q[5];
rz(-pi/4) q[5];
cx q[3],q[5];
cx q[3],q[2];
rz(-pi/8) q[2];
cx q[3],q[2];
rz(pi/8) q[2];
rz(3*pi/4) q[5];
sx q[5];
rz(pi/2) q[5];
cx q[3],q[5];
cx q[5],q[3];
cx q[3],q[5];
rz(pi/4) q[3];
cx q[3],q[2];
rz(-pi/4) q[2];
cx q[3],q[2];
rz(3*pi/4) q[2];
sx q[2];
rz(pi/2) q[2];
rz(-pi/4) q[19];
sx q[19];
rz(-pi/2) q[19];
cx q[19],q[16];
rz(pi/2) q[16];
sx q[19];
cx q[19],q[16];
rz(1.5646604036433545) q[16];
sx q[19];
cx q[19],q[16];
rz(2.3500585670408025) q[16];
sx q[16];
rz(-pi/2) q[16];
cx q[16],q[14];
rz(pi/2) q[14];
sx q[16];
cx q[16],q[14];
rz(1.558524480491812) q[14];
sx q[16];
cx q[16],q[14];
rz(-3.1293208072867085) q[14];
cx q[11],q[14];
cx q[14],q[11];
cx q[11],q[14];
rz(0.0859029241215959) q[14];
cx q[14],q[13];
rz(-pi/64) q[13];
cx q[14],q[13];
rz(pi/64) q[13];
cx q[14],q[11];
rz(-pi/128) q[11];
cx q[14],q[11];
rz(pi/128) q[11];
rz(0.0061359231515405455) q[16];
sx q[16];
rz(-pi) q[16];
rz(-0.7884661249732186) q[19];
sx q[19];
cx q[8],q[11];
cx q[11],q[8];
cx q[8],q[11];
rz(0.14726215563702155) q[11];
rz(-pi/2) q[22];
sx q[22];
rz(pi/2) q[22];
cx q[22],q[19];
rz(-pi/2) q[19];
sx q[22];
cx q[22],q[19];
rz(1.5677283652191247) q[19];
sx q[22];
cx q[22],q[19];
rz(3.1385246920140215) q[19];
sx q[19];
rz(-pi/2) q[19];
cx q[19],q[16];
rz(-pi/2) q[16];
sx q[19];
cx q[19],q[16];
rz(1.5646604036433531) q[16];
sx q[19];
cx q[19],q[16];
x q[16];
rz(1.5769322499464398) q[16];
cx q[14],q[16];
rz(-pi/256) q[16];
cx q[14],q[16];
rz(pi/256) q[16];
x q[19];
x q[22];
rz(0.0015339807878844702) q[22];
cx q[22],q[25];
rz(-pi/2048) q[25];
cx q[22],q[25];
cx q[22],q[19];
cx q[19],q[22];
cx q[22],q[19];
rz(pi/4096) q[19];
cx q[19],q[20];
rz(-pi/4096) q[20];
cx q[19],q[20];
cx q[19],q[16];
cx q[16],q[19];
cx q[19],q[16];
cx q[14],q[16];
cx q[16],q[14];
cx q[14],q[16];
cx q[14],q[13];
cx q[13],q[14];
cx q[14],q[13];
cx q[11],q[14];
rz(-1.5704128315979249) q[13];
sx q[13];
rz(pi/2) q[13];
cx q[13],q[12];
rz(-pi/2) q[12];
sx q[13];
cx q[13],q[12];
rz(1.5704128315979258) q[12];
sx q[13];
cx q[13],q[12];
x q[12];
rz(1.571131885092248) q[12];
cx q[12],q[10];
rz(-pi/16384) q[10];
cx q[12],q[10];
rz(pi/16384) q[10];
cx q[12],q[15];
rz(-0.0003834951969707845) q[13];
sx q[13];
rz(-pi/32) q[14];
cx q[11],q[14];
cx q[11],q[8];
rz(pi/32) q[14];
rz(-pi/32768) q[15];
cx q[12],q[15];
rz(pi/32768) q[15];
cx q[16],q[19];
cx q[18],q[15];
cx q[15],q[18];
cx q[18],q[15];
cx q[12],q[15];
rz(-pi/65536) q[15];
cx q[12],q[15];
cx q[12],q[10];
cx q[10],q[12];
cx q[12],q[10];
rz(pi/131072) q[10];
sx q[10];
rz(pi/2) q[10];
cx q[10],q[7];
sx q[10];
rz(pi/2) q[12];
sx q[12];
rz(-pi) q[12];
rz(pi/65536) q[15];
cx q[18],q[15];
cx q[15],q[18];
cx q[18],q[15];
cx q[19],q[16];
cx q[16],q[19];
rz(pi/4096) q[20];
rz(pi/1024) q[22];
rz(pi/2048) q[25];
cx q[22],q[25];
rz(-pi/1024) q[25];
cx q[22],q[25];
cx q[19],q[22];
cx q[22],q[19];
cx q[19],q[22];
rz(pi/2048) q[19];
cx q[19],q[20];
rz(-pi/2048) q[20];
cx q[19],q[20];
rz(pi/2048) q[20];
rz(pi/512) q[22];
rz(pi/1024) q[25];
cx q[22],q[25];
rz(-pi/512) q[25];
cx q[22],q[25];
rz(pi/512) q[25];
rz(-pi/2) q[7];
cx q[10],q[7];
sx q[10];
rz(pi/2) q[7];
cx q[10],q[7];
rz(1.5708202952447072) q[10];
sx q[10];
rz(3.1415806693648882) q[7];
sx q[7];
rz(-pi/2) q[7];
cx q[7],q[4];
rz(-pi/2) q[4];
sx q[7];
cx q[7],q[4];
rz(pi/2) q[4];
sx q[7];
cx q[7],q[4];
rz(3.1415866614773407) q[4];
sx q[4];
rz(-pi/2) q[4];
cx q[4],q[1];
rz(-pi/2) q[1];
sx q[4];
cx q[4],q[1];
rz(pi/2) q[1];
sx q[4];
cx q[4],q[1];
x q[1];
rz(1.570802318907349) q[4];
sx q[4];
rz(1.5708083110198023) q[7];
sx q[7];
rz(-pi/64) q[8];
cx q[11],q[8];
cx q[11],q[14];
cx q[14],q[11];
cx q[11],q[14];
rz(pi/128) q[14];
cx q[14],q[16];
rz(-pi/128) q[16];
cx q[14],q[16];
rz(pi/128) q[16];
cx q[19],q[16];
cx q[16],q[19];
cx q[19],q[16];
cx q[16],q[14];
cx q[14],q[16];
cx q[16],q[14];
rz(pi/4096) q[14];
sx q[14];
rz(pi/2) q[14];
cx q[14],q[13];
rz(pi/2) q[13];
sx q[14];
cx q[14],q[13];
rz(1.5700293364009539) q[13];
sx q[14];
cx q[14],q[13];
rz(1.5711798219918673) q[13];
sx q[13];
rz(pi/2) q[13];
cx q[13],q[12];
rz(-pi/2) q[12];
sx q[13];
cx q[13],q[12];
rz(1.5704128315979258) q[12];
sx q[13];
cx q[13],q[12];
x q[12];
rz(1.571131885092248) q[12];
cx q[12],q[15];
x q[13];
rz(0.0003834951969707845) q[13];
rz(-1.570029336400954) q[14];
rz(-pi/16384) q[15];
cx q[12],q[15];
rz(pi/16384) q[15];
cx q[15],q[18];
cx q[16],q[19];
cx q[18],q[15];
cx q[15],q[18];
cx q[12],q[15];
rz(-pi/32768) q[15];
cx q[12],q[15];
sx q[12];
rz(pi/2) q[12];
cx q[12],q[10];
rz(-pi/2) q[10];
sx q[12];
cx q[12],q[10];
rz(pi/2) q[10];
sx q[12];
cx q[12],q[10];
rz(3.1415686851399833) q[10];
sx q[10];
rz(-pi/2) q[10];
cx q[10],q[7];
sx q[10];
rz(0.7854461002970696) q[12];
sx q[12];
rz(-1.5707004529956536) q[15];
sx q[15];
rz(pi/2) q[18];
sx q[18];
rz(-pi) q[18];
cx q[19],q[16];
cx q[16],q[19];
cx q[14],q[16];
cx q[16],q[14];
cx q[14],q[16];
sx q[14];
rz(-pi/2) q[14];
cx q[22],q[19];
cx q[19],q[22];
cx q[22],q[19];
rz(0.0046019423636569235) q[19];
cx q[19],q[20];
rz(-pi/1024) q[20];
cx q[19],q[20];
cx q[19],q[16];
rz(-pi/2048) q[16];
cx q[19],q[16];
rz(pi/2048) q[16];
rz(pi/1024) q[20];
rz(pi/256) q[22];
cx q[22],q[25];
rz(-pi/256) q[25];
cx q[22],q[25];
cx q[22],q[19];
cx q[19],q[22];
cx q[22],q[19];
rz(0.009203884727313847) q[19];
cx q[19],q[20];
rz(-pi/512) q[20];
cx q[19],q[20];
cx q[19],q[16];
rz(-pi/1024) q[16];
cx q[19],q[16];
rz(pi/1024) q[16];
rz(pi/512) q[20];
rz(pi/256) q[25];
cx q[25],q[22];
cx q[22],q[25];
cx q[25],q[22];
cx q[22],q[19];
cx q[19],q[22];
cx q[22],q[19];
sx q[19];
rz(pi/2) q[19];
cx q[25],q[22];
cx q[22],q[25];
cx q[25],q[22];
rz(pi/4096) q[22];
rz(-pi/2) q[7];
cx q[10],q[7];
sx q[10];
rz(pi/2) q[7];
cx q[10],q[7];
rz(1.5708202952447072) q[10];
sx q[10];
rz(3.1415806693648882) q[7];
sx q[7];
rz(-pi/2) q[7];
cx q[7],q[4];
rz(-pi/2) q[4];
sx q[7];
cx q[7],q[4];
rz(pi/2) q[4];
sx q[7];
cx q[7],q[4];
x q[4];
rz(1.5708083110198015) q[7];
sx q[7];
rz(pi/64) q[8];
cx q[5],q[8];
cx q[8],q[5];
cx q[5],q[8];
rz(0.2945243112740431) q[8];
cx q[8],q[11];
rz(-pi/16) q[11];
cx q[8],q[11];
rz(pi/16) q[11];
cx q[8],q[5];
rz(-pi/32) q[5];
cx q[8],q[5];
rz(pi/32) q[5];
cx q[3],q[5];
cx q[5],q[3];
cx q[3],q[5];
rz(3*pi/16) q[5];
cx q[8],q[11];
cx q[11],q[8];
cx q[8],q[11];
rz(-1.5217089415825558) q[11];
sx q[11];
cx q[14],q[11];
rz(-pi/2) q[11];
sx q[14];
cx q[14],q[11];
rz(1.5217089415825562) q[11];
sx q[14];
cx q[14],q[11];
rz(-3.0925052683774528) q[11];
sx q[11];
rz(-pi/2) q[11];
rz(-pi) q[14];
cx q[14],q[16];
cx q[16],q[14];
cx q[14],q[16];
rz(-3.117048960983623) q[16];
sx q[16];
rz(-pi) q[16];
cx q[19],q[16];
rz(pi/2) q[16];
sx q[19];
cx q[19],q[16];
rz(1.5462526341887268) q[16];
sx q[19];
cx q[19],q[16];
rz(-3.117048960983624) q[16];
cx q[14],q[16];
cx q[16],q[14];
cx q[14],q[16];
sx q[14];
rz(-pi/2) q[14];
rz(-1.5523885573402685) q[19];
cx q[19],q[20];
rz(-pi/256) q[20];
cx q[19],q[20];
cx q[19],q[16];
rz(-pi/512) q[16];
cx q[19],q[16];
rz(pi/512) q[16];
rz(pi/256) q[20];
cx q[20],q[19];
cx q[19],q[20];
cx q[20],q[19];
rz(0.0046019423636569235) q[20];
cx q[5],q[8];
rz(-pi/8) q[8];
cx q[5],q[8];
cx q[5],q[3];
rz(-pi/16) q[3];
cx q[5],q[3];
rz(pi/16) q[3];
cx q[2],q[3];
cx q[3],q[2];
cx q[2],q[3];
rz(3*pi/8) q[3];
rz(pi/8) q[8];
cx q[8],q[5];
cx q[5],q[8];
cx q[8],q[5];
cx q[3],q[5];
rz(-pi/4) q[5];
cx q[3],q[5];
cx q[3],q[2];
rz(-pi/8) q[2];
cx q[3],q[2];
rz(pi/8) q[2];
rz(3*pi/4) q[5];
sx q[5];
rz(pi/2) q[5];
cx q[5],q[3];
cx q[3],q[5];
cx q[5],q[3];
rz(pi/8) q[3];
cx q[3],q[2];
rz(-pi/4) q[2];
cx q[3],q[2];
rz(3*pi/4) q[2];
sx q[2];
rz(7*pi/8) q[2];
sx q[3];
rz(9*pi/16) q[5];
sx q[5];
rz(-pi) q[5];
rz(-1.4726215563702154) q[8];
sx q[8];
cx q[11],q[8];
sx q[11];
rz(-pi/2) q[8];
cx q[11],q[8];
sx q[11];
rz(1.4726215563702159) q[8];
cx q[11],q[8];
rz(1.619883712007237) q[11];
sx q[11];
cx q[14],q[11];
rz(-pi/2) q[11];
sx q[14];
cx q[14],q[11];
rz(1.5217089415825562) q[11];
sx q[14];
cx q[14],q[11];
rz(-2.3071071049800045) q[11];
sx q[11];
rz(-pi/2) q[11];
rz(-pi) q[14];
cx q[14],q[16];
cx q[16],q[14];
cx q[14],q[16];
rz(0.03681553890925539) q[16];
cx q[16],q[19];
rz(-pi/128) q[19];
cx q[16],q[19];
cx q[16],q[14];
rz(-pi/256) q[14];
cx q[16],q[14];
rz(pi/256) q[14];
rz(pi/128) q[19];
cx q[19],q[16];
cx q[16],q[19];
cx q[19],q[16];
rz(1.6689710972195773) q[8];
sx q[8];
rz(pi/2) q[8];
cx q[8],q[5];
rz(-pi/2) q[5];
sx q[8];
cx q[8],q[5];
rz(7*pi/16) q[5];
sx q[8];
cx q[8],q[5];
rz(3*pi/16) q[5];
sx q[5];
rz(pi/2) q[5];
cx q[5],q[3];
rz(-pi/2) q[3];
sx q[5];
cx q[5],q[3];
rz(3*pi/8) q[3];
sx q[5];
cx q[5],q[3];
rz(7*pi/8) q[3];
cx q[2],q[3];
rz(-pi/4) q[3];
cx q[2],q[3];
rz(3*pi/4) q[3];
sx q[3];
rz(pi/2) q[3];
rz(-1.3910687423051178) q[5];
sx q[5];
rz(-pi) q[5];
rz(-2.4543692606170255) q[8];
sx q[8];
rz(-pi) q[8];
cx q[11],q[8];
sx q[11];
rz(-pi/2) q[8];
cx q[11],q[8];
sx q[11];
rz(1.4726215563702159) q[8];
cx q[11],q[8];
rz(3*pi/4) q[11];
cx q[11],q[14];
cx q[14],q[11];
cx q[11],q[14];
rz(0.07363107781851078) q[14];
cx q[14],q[16];
rz(-pi/64) q[16];
cx q[14],q[16];
cx q[14],q[11];
rz(-pi/128) q[11];
cx q[14],q[11];
rz(pi/128) q[11];
rz(pi/64) q[16];
cx q[16],q[14];
cx q[14],q[16];
cx q[16],q[14];
rz(1.652349140859994) q[8];
sx q[8];
rz(pi/2) q[8];
cx q[8],q[5];
rz(-pi/2) q[5];
sx q[8];
cx q[8],q[5];
rz(7*pi/16) q[5];
sx q[8];
cx q[8],q[5];
x q[5];
rz(0.9983696606063939) q[5];
cx q[5],q[3];
cx q[3],q[5];
cx q[5],q[3];
cx q[2],q[3];
rz(-pi/8) q[3];
cx q[2],q[3];
rz(pi/8) q[3];
rz(pi/4) q[5];
cx q[5],q[3];
rz(-pi/4) q[3];
cx q[5],q[3];
rz(3*pi/4) q[3];
sx q[3];
rz(pi/2) q[3];
cx q[2],q[3];
cx q[3],q[2];
cx q[2],q[3];
cx q[3],q[5];
cx q[5],q[3];
cx q[3],q[5];
x q[8];
rz(-0.7687762070378668) q[8];
cx q[8],q[11];
cx q[11],q[8];
cx q[8],q[11];
rz(0.14726215563702155) q[11];
cx q[11],q[14];
rz(-pi/32) q[14];
cx q[11],q[14];
cx q[11],q[8];
rz(pi/32) q[14];
cx q[13],q[14];
cx q[14],q[13];
cx q[13],q[14];
cx q[14],q[16];
cx q[16],q[14];
cx q[14],q[16];
cx q[13],q[14];
cx q[14],q[13];
cx q[13],q[14];
rz(0.018407769454627694) q[13];
cx q[16],q[19];
cx q[19],q[16];
cx q[16],q[19];
rz(1.5769322499464389) q[16];
sx q[16];
rz(-pi) q[16];
cx q[22],q[19];
rz(-pi/4096) q[19];
cx q[22],q[19];
rz(pi/4096) q[19];
cx q[22],q[25];
cx q[25],q[22];
cx q[22],q[25];
rz(-2.3538935190105157) q[22];
cx q[22],q[19];
rz(-pi/2048) q[19];
cx q[22],q[19];
rz(pi/2048) q[19];
cx q[20],q[19];
rz(-pi/1024) q[19];
cx q[20],q[19];
rz(-1.5677283652191252) q[19];
sx q[19];
rz(pi/2) q[19];
cx q[19],q[16];
rz(-pi/2) q[16];
sx q[19];
cx q[19],q[16];
rz(1.5646604036433531) q[16];
sx q[19];
cx q[19],q[16];
x q[16];
rz(1.5769322499464398) q[16];
x q[19];
sx q[22];
rz(-pi) q[22];
cx q[25],q[24];
cx q[24],q[25];
cx q[25],q[24];
cx q[24],q[23];
cx q[23],q[24];
cx q[24],q[23];
cx q[23],q[21];
cx q[21],q[23];
cx q[23],q[21];
rz(-1.5704128315979249) q[21];
sx q[21];
rz(pi/2) q[21];
cx q[21],q[18];
rz(-pi/2) q[18];
sx q[21];
cx q[21],q[18];
rz(1.5704128315979258) q[18];
sx q[21];
cx q[21],q[18];
rz(1.5706045791964094) q[18];
sx q[18];
rz(pi/2) q[18];
cx q[18],q[15];
rz(-pi/2) q[15];
sx q[18];
cx q[18],q[15];
rz(1.5706045791964112) q[15];
sx q[18];
cx q[18],q[15];
rz(-2.356098616393102) q[15];
sx q[15];
rz(-pi/2) q[15];
cx q[15],q[12];
rz(-pi/2) q[12];
sx q[15];
cx q[15],q[12];
rz(1.5707004529956539) q[12];
sx q[15];
cx q[15],q[12];
rz(2.356242427091966) q[12];
sx q[12];
rz(pi/2) q[12];
cx q[12],q[10];
rz(-pi/2) q[10];
sx q[12];
cx q[12],q[10];
rz(pi/2) q[10];
sx q[12];
cx q[12],q[10];
rz(3.1415686851399833) q[10];
sx q[10];
rz(-pi/2) q[10];
cx q[10],q[7];
sx q[10];
rz(1.570844263694518) q[12];
rz(2.3562903639915866) q[15];
rz(-1.5706045791964116) q[18];
sx q[18];
rz(-pi) q[18];
x q[21];
rz(0.0003834951969707845) q[21];
cx q[21],q[23];
cx q[23],q[21];
cx q[21],q[23];
cx q[23],q[24];
cx q[24],q[23];
cx q[23],q[24];
cx q[24],q[25];
cx q[25],q[24];
cx q[24],q[25];
rz(pi/4) q[25];
sx q[25];
rz(pi/2) q[25];
cx q[25],q[22];
rz(-pi/2) q[22];
sx q[25];
cx q[25],q[22];
rz(1.5700293364009532) q[22];
sx q[25];
cx q[25],q[22];
x q[22];
rz(-0.7846311730035058) q[22];
cx q[22],q[19];
cx q[19],q[22];
cx q[22],q[19];
cx q[20],q[19];
rz(-pi/2048) q[19];
cx q[20],q[19];
rz(pi/2048) q[19];
rz(pi/1024) q[22];
cx q[22],q[19];
rz(-pi/1024) q[19];
cx q[22],q[19];
rz(pi/1024) q[19];
x q[25];
rz(-3*pi/4) q[25];
cx q[25],q[24];
cx q[24],q[25];
cx q[25],q[24];
cx q[24],q[23];
cx q[23],q[24];
cx q[24],q[23];
cx q[23],q[21];
cx q[21],q[23];
cx q[23],q[21];
rz(-1.5704128315979249) q[21];
sx q[21];
rz(pi/2) q[21];
cx q[21],q[18];
rz(-pi/2) q[18];
sx q[21];
cx q[21],q[18];
rz(1.5704128315979258) q[18];
sx q[21];
cx q[21],q[18];
x q[18];
rz(1.571131885092248) q[18];
cx q[18],q[15];
rz(-pi/16384) q[15];
cx q[18],q[15];
rz(pi/16384) q[15];
cx q[12],q[15];
cx q[15],q[12];
cx q[12],q[15];
rz(pi/2) q[12];
sx q[12];
rz(-pi) q[12];
cx q[18],q[15];
rz(-pi/32768) q[15];
cx q[18],q[15];
rz(pi/32768) q[15];
x q[21];
rz(0.0003834951969707845) q[21];
cx q[21],q[23];
cx q[23],q[21];
cx q[21],q[23];
cx q[23],q[24];
cx q[24],q[23];
cx q[23],q[24];
cx q[24],q[25];
cx q[25],q[24];
cx q[24],q[25];
cx q[25],q[22];
cx q[22],q[25];
cx q[25],q[22];
rz(pi/2048) q[25];
rz(-pi/2) q[7];
cx q[10],q[7];
sx q[10];
rz(pi/2) q[7];
cx q[10],q[7];
rz(1.5708202952447072) q[10];
x q[7];
rz(-pi/64) q[8];
cx q[11],q[8];
cx q[14],q[11];
cx q[11],q[14];
cx q[14],q[11];
cx q[16],q[14];
cx q[14],q[16];
cx q[16],q[14];
cx q[13],q[14];
rz(-pi/256) q[14];
cx q[13],q[14];
rz(pi/256) q[14];
rz(pi/128) q[16];
cx q[16],q[14];
rz(-pi/128) q[14];
cx q[16],q[14];
rz(pi/128) q[14];
sx q[14];
rz(-pi/2) q[14];
cx q[19],q[16];
cx q[16],q[19];
cx q[19],q[16];
cx q[20],q[19];
cx q[19],q[20];
cx q[20],q[19];
rz(pi/4096) q[19];
cx q[19],q[22];
rz(-pi/4096) q[22];
cx q[19],q[22];
rz(pi/4096) q[22];
cx q[25],q[22];
rz(-pi/2048) q[22];
cx q[25],q[22];
rz(pi/2048) q[22];
rz(pi/64) q[8];
cx q[5],q[8];
cx q[8],q[5];
cx q[5],q[8];
rz(0.2945243112740431) q[8];
cx q[8],q[11];
rz(-pi/16) q[11];
cx q[8],q[11];
rz(pi/16) q[11];
cx q[8],q[5];
rz(-pi/32) q[5];
cx q[8],q[5];
rz(pi/32) q[5];
cx q[3],q[5];
cx q[5],q[3];
cx q[3],q[5];
rz(3*pi/16) q[5];
cx q[8],q[11];
cx q[11],q[8];
cx q[8],q[11];
rz(-1.5217089415825558) q[11];
sx q[11];
cx q[14],q[11];
rz(-pi/2) q[11];
sx q[14];
cx q[14],q[11];
rz(1.5217089415825562) q[11];
sx q[14];
cx q[14],q[11];
rz(-3.0925052683774528) q[11];
sx q[11];
rz(-pi/2) q[11];
rz(-pi) q[14];
cx q[16],q[14];
cx q[14],q[16];
cx q[16],q[14];
cx q[13],q[14];
rz(-pi/512) q[14];
cx q[13],q[14];
rz(pi/512) q[14];
cx q[19],q[16];
cx q[16],q[19];
cx q[19],q[16];
cx q[16],q[14];
cx q[14],q[16];
cx q[16],q[14];
cx q[14],q[13];
cx q[13],q[14];
cx q[14],q[13];
rz(-1.5704128315979249) q[13];
sx q[13];
rz(pi/2) q[13];
cx q[13],q[12];
rz(-pi/2) q[12];
sx q[13];
cx q[13],q[12];
rz(1.5704128315979258) q[12];
sx q[13];
cx q[13],q[12];
x q[12];
rz(1.5709880743933837) q[12];
cx q[12],q[15];
x q[13];
rz(0.0003834951969707845) q[13];
rz(pi/1024) q[14];
rz(-pi/16384) q[15];
cx q[12],q[15];
cx q[10],q[12];
cx q[12],q[10];
cx q[10],q[12];
rz(pi/32768) q[10];
rz(pi/16384) q[15];
cx q[12],q[15];
cx q[15],q[12];
cx q[12],q[15];
cx q[18],q[15];
rz(-pi/65536) q[15];
cx q[18],q[15];
rz(pi/65536) q[15];
cx q[20],q[19];
cx q[19],q[20];
cx q[20],q[19];
rz(pi/256) q[19];
cx q[19],q[16];
rz(-pi/256) q[16];
cx q[19],q[16];
rz(pi/256) q[16];
rz(0.047553404424454875) q[20];
cx q[22],q[19];
cx q[19],q[22];
cx q[22],q[19];
cx q[16],q[19];
cx q[19],q[16];
cx q[16],q[19];
cx q[14],q[16];
rz(-pi/1024) q[16];
cx q[14],q[16];
cx q[13],q[14];
cx q[14],q[13];
cx q[13],q[14];
rz(pi/1024) q[16];
cx q[20],q[19];
rz(-pi/128) q[19];
cx q[20],q[19];
rz(pi/128) q[19];
cx q[19],q[16];
cx q[16],q[19];
cx q[19],q[16];
cx q[14],q[16];
cx q[16],q[14];
cx q[14],q[16];
sx q[14];
rz(-pi/2) q[14];
rz(pi/512) q[22];
cx q[22],q[19];
rz(-pi/512) q[19];
cx q[22],q[19];
rz(pi/512) q[19];
cx q[20],q[19];
rz(-pi/256) q[19];
cx q[20],q[19];
rz(pi/256) q[19];
cx q[16],q[19];
cx q[19],q[16];
cx q[16],q[19];
sx q[16];
rz(-pi/2) q[16];
cx q[25],q[22];
cx q[22],q[25];
cx q[25],q[22];
rz(pi/4096) q[22];
cx q[22],q[19];
rz(-pi/4096) q[19];
cx q[22],q[19];
rz(pi/4096) q[19];
cx q[5],q[8];
rz(-pi/8) q[8];
cx q[5],q[8];
cx q[5],q[3];
rz(-pi/16) q[3];
cx q[5],q[3];
rz(pi/16) q[3];
cx q[2],q[3];
cx q[3],q[2];
cx q[2],q[3];
rz(3*pi/8) q[3];
rz(pi/8) q[8];
cx q[8],q[5];
cx q[5],q[8];
cx q[8],q[5];
cx q[3],q[5];
rz(-pi/4) q[5];
cx q[3],q[5];
cx q[3],q[2];
rz(-pi/8) q[2];
cx q[3],q[2];
rz(pi/8) q[2];
rz(3*pi/4) q[5];
sx q[5];
rz(pi/2) q[5];
cx q[5],q[3];
cx q[3],q[5];
cx q[5],q[3];
rz(pi/8) q[3];
cx q[3],q[2];
rz(-pi/4) q[2];
cx q[3],q[2];
rz(3*pi/4) q[2];
sx q[2];
rz(7*pi/8) q[2];
sx q[3];
rz(9*pi/16) q[5];
sx q[5];
rz(-pi) q[5];
rz(-1.4726215563702154) q[8];
sx q[8];
cx q[11],q[8];
sx q[11];
rz(-pi/2) q[8];
cx q[11],q[8];
sx q[11];
rz(1.4726215563702159) q[8];
cx q[11],q[8];
rz(1.619883712007237) q[11];
sx q[11];
cx q[14],q[11];
rz(-pi/2) q[11];
sx q[14];
cx q[14],q[11];
rz(1.5217089415825562) q[11];
sx q[14];
cx q[14],q[11];
rz(-3.092505268377453) q[11];
sx q[11];
rz(-pi/2) q[11];
rz(1.5953400194010676) q[14];
sx q[14];
cx q[16],q[14];
rz(-pi/2) q[14];
sx q[16];
cx q[16],q[14];
rz(1.546252634188727) q[14];
sx q[16];
cx q[16],q[14];
rz(-3.117048960983624) q[14];
sx q[14];
rz(-pi/2) q[14];
rz(-pi) q[16];
cx q[19],q[16];
cx q[16],q[19];
cx q[19],q[16];
cx q[22],q[19];
cx q[19],q[22];
cx q[22],q[19];
cx q[25],q[22];
cx q[22],q[25];
cx q[25],q[22];
rz(pi/1024) q[22];
rz(0.018407769454627694) q[25];
rz(1.6689710972195773) q[8];
sx q[8];
rz(pi/2) q[8];
cx q[8],q[5];
rz(-pi/2) q[5];
sx q[8];
cx q[8],q[5];
rz(7*pi/16) q[5];
sx q[8];
cx q[8],q[5];
rz(3*pi/16) q[5];
sx q[5];
rz(pi/2) q[5];
cx q[5],q[3];
rz(-pi/2) q[3];
sx q[5];
cx q[5],q[3];
rz(3*pi/8) q[3];
sx q[5];
cx q[5],q[3];
rz(7*pi/8) q[3];
cx q[2],q[3];
rz(-pi/4) q[3];
cx q[2],q[3];
rz(3*pi/4) q[3];
sx q[3];
rz(pi/2) q[3];
rz(-11*pi/16) q[5];
sx q[5];
rz(-pi) q[5];
rz(-1.6689710972195773) q[8];
sx q[8];
rz(-pi) q[8];
cx q[11],q[8];
sx q[11];
rz(-pi/2) q[8];
cx q[11],q[8];
sx q[11];
rz(1.4726215563702159) q[8];
cx q[11],q[8];
rz(1.619883712007237) q[11];
sx q[11];
cx q[14],q[11];
rz(-pi/2) q[11];
sx q[14];
cx q[14],q[11];
rz(1.5217089415825562) q[11];
sx q[14];
cx q[14],q[11];
rz(-3.0925052683774528) q[11];
sx q[11];
rz(-pi/2) q[11];
rz(-pi) q[14];
cx q[13],q[14];
cx q[14],q[13];
cx q[13],q[14];
cx q[12],q[13];
cx q[13],q[12];
cx q[12],q[13];
rz(pi/2048) q[14];
cx q[14],q[16];
cx q[15],q[12];
cx q[12],q[15];
cx q[15],q[12];
cx q[10],q[12];
rz(-pi/32768) q[12];
cx q[10],q[12];
rz(pi/32768) q[12];
rz(-pi/2048) q[16];
cx q[14],q[16];
cx q[13],q[14];
cx q[14],q[13];
cx q[13],q[14];
rz(pi/4096) q[13];
rz(pi/2048) q[16];
cx q[16],q[19];
cx q[19],q[16];
cx q[16],q[19];
rz(pi/8192) q[16];
cx q[16],q[14];
rz(-pi/8192) q[14];
cx q[16],q[14];
rz(pi/8192) q[14];
cx q[13],q[14];
rz(-pi/4096) q[14];
cx q[13],q[14];
cx q[12],q[13];
cx q[13],q[12];
cx q[12],q[13];
rz(pi/8192) q[12];
rz(pi/4096) q[14];
cx q[16],q[14];
cx q[14],q[16];
cx q[16],q[14];
rz(pi/16384) q[14];
cx q[14],q[13];
rz(-pi/16384) q[13];
cx q[14],q[13];
rz(pi/16384) q[13];
cx q[12],q[13];
rz(-pi/8192) q[13];
cx q[12],q[13];
rz(pi/8192) q[13];
cx q[13],q[14];
cx q[14],q[13];
cx q[13],q[14];
cx q[15],q[12];
cx q[12],q[15];
cx q[15],q[12];
cx q[12],q[13];
cx q[13],q[12];
cx q[12],q[13];
rz(0.04295146206079795) q[13];
rz(pi/2) q[16];
sx q[16];
rz(-pi) q[16];
cx q[22],q[19];
rz(-pi/1024) q[19];
cx q[22],q[19];
rz(pi/1024) q[19];
cx q[20],q[19];
rz(-pi/512) q[19];
cx q[20],q[19];
rz(pi/512) q[19];
cx q[19],q[22];
cx q[22],q[19];
cx q[19],q[22];
rz(pi/2048) q[19];
sx q[19];
rz(pi/2) q[19];
cx q[19],q[16];
rz(pi/2) q[16];
sx q[19];
cx q[19],q[16];
rz(1.5692623460070108) q[16];
sx q[19];
cx q[19],q[16];
rz(-3.1408256631958498) q[16];
cx q[16],q[14];
rz(-pi/4096) q[14];
cx q[16],q[14];
rz(pi/4096) q[14];
rz(0.0015339807878849143) q[19];
cx q[20],q[19];
rz(-pi/1024) q[19];
cx q[20],q[19];
rz(pi/1024) q[19];
cx q[25],q[22];
rz(-pi/256) q[22];
cx q[25],q[22];
rz(pi/256) q[22];
cx q[19],q[22];
cx q[22],q[19];
cx q[19],q[22];
cx q[19],q[16];
cx q[16],q[19];
cx q[19],q[16];
cx q[14],q[16];
cx q[16],q[14];
cx q[14],q[16];
cx q[13],q[14];
rz(-pi/128) q[14];
cx q[13],q[14];
rz(pi/128) q[14];
sx q[14];
rz(-pi/2) q[14];
cx q[25],q[22];
rz(-pi/512) q[22];
cx q[25],q[22];
rz(pi/512) q[22];
cx q[22],q[19];
cx q[19],q[22];
cx q[22],q[19];
cx q[16],q[19];
cx q[19],q[16];
cx q[16],q[19];
cx q[20],q[19];
rz(-pi/2048) q[19];
cx q[20],q[19];
rz(pi/2048) q[19];
cx q[25],q[22];
cx q[22],q[25];
cx q[25],q[22];
rz(pi/1024) q[22];
cx q[22],q[19];
rz(-pi/1024) q[19];
cx q[22],q[19];
rz(pi/1024) q[19];
rz(1.6689710972195773) q[8];
sx q[8];
rz(pi/2) q[8];
cx q[8],q[5];
rz(-pi/2) q[5];
sx q[8];
cx q[8],q[5];
rz(7*pi/16) q[5];
sx q[8];
cx q[8],q[5];
x q[5];
rz(9*pi/16) q[5];
cx q[5],q[3];
cx q[3],q[5];
cx q[5],q[3];
cx q[2],q[3];
rz(-pi/8) q[3];
cx q[2],q[3];
rz(pi/8) q[3];
rz(pi/4) q[5];
cx q[5],q[3];
rz(-pi/4) q[3];
cx q[5],q[3];
rz(3*pi/4) q[3];
sx q[3];
rz(pi/2) q[3];
cx q[2],q[3];
cx q[3],q[2];
cx q[2],q[3];
rz(3*pi/8) q[2];
rz(pi/16) q[3];
rz(-1.6689710972195773) q[8];
sx q[8];
rz(-pi) q[8];
cx q[11],q[8];
sx q[11];
rz(-pi/2) q[8];
cx q[11],q[8];
sx q[11];
rz(1.4726215563702159) q[8];
cx q[11],q[8];
rz(1.619883712007237) q[11];
sx q[11];
cx q[14],q[11];
rz(-pi/2) q[11];
sx q[14];
cx q[14],q[11];
rz(1.5217089415825562) q[11];
sx q[14];
cx q[14],q[11];
rz(-3.0925052683774528) q[11];
rz(-pi) q[14];
cx q[16],q[14];
cx q[14],q[16];
cx q[16],q[14];
cx q[13],q[14];
rz(-pi/256) q[14];
cx q[13],q[14];
rz(pi/256) q[14];
rz(pi/128) q[16];
cx q[16],q[14];
rz(-pi/128) q[14];
cx q[16],q[14];
rz(pi/128) q[14];
cx q[19],q[16];
cx q[16],q[19];
cx q[19],q[16];
rz(-3.0434178831651124) q[8];
cx q[8],q[5];
cx q[5],q[8];
cx q[8],q[5];
cx q[3],q[5];
rz(-pi/16) q[5];
cx q[3],q[5];
rz(pi/16) q[5];
rz(pi/8) q[8];
cx q[8],q[5];
rz(-pi/8) q[5];
cx q[8],q[5];
cx q[11],q[8];
rz(pi/8) q[5];
cx q[5],q[3];
cx q[3],q[5];
cx q[5],q[3];
cx q[2],q[3];
rz(-pi/4) q[3];
cx q[2],q[3];
rz(3*pi/4) q[3];
sx q[3];
rz(pi/2) q[3];
rz(pi/32) q[5];
cx q[8],q[11];
cx q[11],q[8];
rz(pi/16) q[11];
cx q[5],q[8];
rz(-pi/32) q[8];
cx q[5],q[8];
rz(pi/32) q[8];
cx q[11],q[8];
rz(-pi/16) q[8];
cx q[11],q[8];
cx q[14],q[11];
cx q[11],q[14];
cx q[14],q[11];
rz(pi/32) q[14];
rz(pi/16) q[8];
cx q[8],q[5];
cx q[5],q[8];
cx q[8],q[5];
cx q[5],q[3];
cx q[3],q[5];
cx q[5],q[3];
cx q[2],q[3];
rz(-pi/8) q[3];
cx q[2],q[3];
rz(pi/8) q[3];
rz(pi/4) q[5];
cx q[5],q[3];
rz(-pi/4) q[3];
cx q[5],q[3];
rz(3*pi/4) q[3];
sx q[3];
rz(pi/2) q[3];
cx q[2],q[3];
cx q[3],q[2];
cx q[2],q[3];
rz(pi/16) q[3];
rz(pi/64) q[8];
cx q[8],q[11];
rz(-pi/64) q[11];
cx q[8],q[11];
rz(pi/64) q[11];
cx q[14],q[11];
rz(-pi/32) q[11];
cx q[14],q[11];
rz(pi/32) q[11];
cx q[11],q[8];
cx q[16],q[14];
cx q[14],q[16];
cx q[16],q[14];
cx q[13],q[14];
rz(-pi/512) q[14];
cx q[13],q[14];
rz(pi/512) q[14];
cx q[19],q[16];
cx q[16],q[19];
cx q[19],q[16];
rz(pi/256) q[16];
cx q[16],q[14];
rz(-pi/256) q[14];
cx q[16],q[14];
rz(pi/256) q[14];
cx q[19],q[16];
cx q[16],q[19];
cx q[19],q[16];
rz(pi/64) q[16];
cx q[19],q[22];
cx q[22],q[19];
cx q[19],q[22];
cx q[20],q[19];
cx q[19],q[20];
cx q[20],q[19];
cx q[22],q[25];
cx q[25],q[22];
cx q[22],q[25];
cx q[8],q[11];
cx q[11],q[8];
rz(pi/128) q[11];
cx q[11],q[14];
rz(-pi/128) q[14];
cx q[11],q[14];
rz(pi/128) q[14];
cx q[16],q[14];
rz(-pi/64) q[14];
cx q[16],q[14];
rz(pi/64) q[14];
cx q[14],q[11];
cx q[11],q[14];
cx q[14],q[11];
cx q[14],q[13];
cx q[13],q[14];
cx q[14],q[13];
cx q[13],q[12];
cx q[12],q[13];
cx q[13],q[12];
cx q[12],q[15];
cx q[15],q[12];
cx q[12],q[15];
cx q[16],q[14];
cx q[14],q[16];
cx q[16],q[14];
cx q[14],q[13];
cx q[13],q[14];
cx q[14],q[13];
cx q[16],q[19];
cx q[18],q[15];
cx q[15],q[18];
cx q[18],q[15];
cx q[15],q[12];
cx q[12],q[15];
cx q[15],q[12];
cx q[19],q[16];
cx q[16],q[19];
cx q[8],q[5];
cx q[5],q[8];
cx q[8],q[5];
cx q[3],q[5];
rz(-pi/16) q[5];
cx q[3],q[5];
cx q[2],q[3];
cx q[3],q[2];
cx q[2],q[3];
rz(pi/4) q[3];
rz(pi/16) q[5];
rz(pi/8) q[8];
cx q[8],q[5];
rz(-pi/8) q[5];
cx q[8],q[5];
cx q[11],q[8];
rz(pi/8) q[5];
cx q[3],q[5];
rz(-pi/4) q[5];
cx q[3],q[5];
cx q[2],q[3];
cx q[3],q[2];
cx q[2],q[3];
rz(pi/32) q[3];
rz(3*pi/4) q[5];
sx q[5];
rz(pi/2) q[5];
cx q[8],q[11];
cx q[11],q[8];
rz(pi/16) q[11];
cx q[8],q[5];
cx q[5],q[8];
cx q[8],q[5];
cx q[3],q[5];
rz(-pi/32) q[5];
cx q[3],q[5];
cx q[3],q[2];
cx q[2],q[3];
cx q[3],q[2];
cx q[2],q[1];
cx q[1],q[2];
cx q[2],q[1];
cx q[1],q[4];
rz(pi/8) q[3];
cx q[4],q[1];
cx q[1],q[4];
cx q[4],q[7];
rz(pi/32) q[5];
cx q[5],q[8];
cx q[7],q[4];
cx q[4],q[7];
cx q[7],q[10];
cx q[10],q[7];
cx q[7],q[10];
cx q[8],q[5];
cx q[5],q[8];
cx q[11],q[8];
rz(-pi/16) q[8];
cx q[11],q[8];
cx q[11],q[14];
cx q[14],q[11];
cx q[11],q[14];
cx q[14],q[13];
cx q[13],q[14];
cx q[14],q[13];
cx q[13],q[12];
cx q[12],q[13];
cx q[13],q[12];
rz(pi/16) q[8];
cx q[8],q[5];
cx q[5],q[8];
cx q[8],q[5];
cx q[3],q[5];
rz(-pi/8) q[5];
cx q[3],q[5];
cx q[2],q[3];
cx q[3],q[2];
cx q[2],q[3];
cx q[1],q[2];
cx q[2],q[1];
cx q[1],q[2];
cx q[1],q[4];
cx q[4],q[1];
cx q[1],q[4];
rz(pi/8) q[5];
rz(pi/4) q[8];
cx q[8],q[5];
rz(-pi/4) q[5];
cx q[8],q[5];
rz(3*pi/4) q[5];
sx q[5];
rz(pi/2) q[5];
cx q[5],q[3];
cx q[3],q[5];
cx q[5],q[3];
cx q[2],q[3];
cx q[3],q[2];
cx q[2],q[3];
cx q[8],q[5];
cx q[5],q[8];
cx q[8],q[5];
cx q[5],q[3];
cx q[3],q[5];
cx q[5],q[3];
barrier q[8],q[5],q[1],q[13],q[7],q[11],q[15],q[22],q[16],q[20],q[19],q[25],q[18],q[14],q[10],q[12],q[4],q[3],q[2];
measure q[8] -> meas[0];
measure q[5] -> meas[1];
measure q[1] -> meas[2];
measure q[13] -> meas[3];
measure q[7] -> meas[4];
measure q[11] -> meas[5];
measure q[15] -> meas[6];
measure q[22] -> meas[7];
measure q[16] -> meas[8];
measure q[20] -> meas[9];
measure q[19] -> meas[10];
measure q[25] -> meas[11];
measure q[18] -> meas[12];
measure q[14] -> meas[13];
measure q[10] -> meas[14];
measure q[12] -> meas[15];
measure q[4] -> meas[16];
measure q[3] -> meas[17];
measure q[2] -> meas[18];

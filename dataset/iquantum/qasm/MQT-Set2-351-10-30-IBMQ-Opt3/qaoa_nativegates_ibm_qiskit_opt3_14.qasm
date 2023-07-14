// Benchmark was created by MQT Bench on 2023-06-30
// For more information about MQT Bench, please visit https://www.cda.cit.tum.de/mqtbench/
// MQT Bench version: v1.0.0
// Qiskit version: {'qiskit-terra': '0.24.1', 'qiskit-aer': '0.12.0', 'qiskit-ignis': None, 'qiskit-ibmq-provider': '0.20.2', 'qiskit': '0.43.1', 'qiskit-nature': '0.6.2', 'qiskit-finance': '0.3.4', 'qiskit-optimization': '0.5.0', 'qiskit-machine-learning': '0.6.1'}
// Used Gate Set: ['rz', 'sx', 'x', 'cx', 'measure']

OPENQASM 2.0;
include "qelib1.inc";
qreg q[14];
creg meas[14];
rz(pi/2) q[0];
sx q[0];
rz(pi) q[0];
rz(pi/2) q[1];
sx q[1];
rz(pi) q[1];
rz(pi/2) q[2];
sx q[2];
rz(pi) q[2];
rz(pi/2) q[3];
sx q[3];
rz(pi/2) q[3];
cx q[2],q[3];
rz(-4.555781533392214) q[3];
cx q[2],q[3];
rz(pi/2) q[4];
sx q[4];
rz(pi) q[4];
rz(pi/2) q[5];
sx q[5];
rz(pi/2) q[5];
cx q[4],q[5];
rz(-4.555781533392214) q[5];
cx q[4],q[5];
rz(pi/2) q[6];
sx q[6];
rz(pi/2) q[6];
cx q[5],q[6];
rz(-4.555781533392214) q[6];
cx q[5],q[6];
rz(pi/2) q[5];
sx q[5];
rz(11.85923767700637) q[5];
sx q[5];
rz(5*pi/2) q[5];
rz(pi/2) q[7];
sx q[7];
rz(pi/2) q[7];
cx q[4],q[7];
rz(-4.555781533392214) q[7];
cx q[4],q[7];
sx q[4];
rz(11.85923767700637) q[4];
sx q[4];
rz(3*pi) q[4];
cx q[4],q[5];
rz(-2.4344849387505407) q[5];
cx q[4],q[5];
cx q[6],q[7];
rz(-4.555781533392214) q[7];
cx q[6],q[7];
rz(pi/2) q[6];
sx q[6];
rz(11.85923767700637) q[6];
sx q[6];
rz(5*pi/2) q[6];
cx q[5],q[6];
rz(-2.4344849387505407) q[6];
cx q[5],q[6];
rz(pi/2) q[5];
sx q[5];
rz(10.839043842999246) q[5];
sx q[5];
rz(5*pi/2) q[5];
rz(pi/2) q[7];
sx q[7];
rz(11.85923767700637) q[7];
sx q[7];
rz(5*pi/2) q[7];
cx q[4],q[7];
rz(-2.4344849387505407) q[7];
cx q[4],q[7];
sx q[4];
rz(10.839043842999246) q[4];
sx q[4];
rz(5*pi/2) q[4];
cx q[6],q[7];
rz(-2.4344849387505407) q[7];
cx q[6],q[7];
rz(pi/2) q[6];
sx q[6];
rz(10.839043842999246) q[6];
sx q[6];
rz(5*pi/2) q[6];
rz(pi/2) q[7];
sx q[7];
rz(10.839043842999246) q[7];
sx q[7];
rz(5*pi/2) q[7];
rz(pi/2) q[8];
sx q[8];
rz(pi) q[8];
rz(pi/2) q[9];
sx q[9];
rz(pi/2) q[9];
cx q[1],q[9];
rz(-4.555781533392214) q[9];
cx q[1],q[9];
cx q[8],q[9];
rz(-4.555781533392214) q[9];
cx q[8],q[9];
rz(pi/2) q[9];
sx q[9];
rz(11.85923767700637) q[9];
sx q[9];
rz(5*pi/2) q[9];
rz(pi/2) q[10];
sx q[10];
rz(pi/2) q[10];
cx q[0],q[10];
rz(-4.555781533392214) q[10];
cx q[0],q[10];
rz(pi/2) q[11];
sx q[11];
rz(pi/2) q[11];
cx q[0],q[11];
rz(-4.555781533392214) q[11];
cx q[0],q[11];
sx q[0];
rz(11.85923767700637) q[0];
sx q[0];
rz(3*pi) q[0];
cx q[3],q[11];
rz(-4.555781533392214) q[11];
cx q[3],q[11];
rz(pi/2) q[11];
sx q[11];
rz(11.85923767700637) q[11];
sx q[11];
rz(5*pi/2) q[11];
rz(pi/2) q[3];
sx q[3];
rz(11.85923767700637) q[3];
sx q[3];
rz(5*pi/2) q[3];
rz(pi/2) q[12];
sx q[12];
rz(pi/2) q[12];
cx q[1],q[12];
rz(-4.555781533392214) q[12];
cx q[1],q[12];
sx q[1];
rz(11.85923767700637) q[1];
sx q[1];
rz(3*pi) q[1];
cx q[1],q[9];
cx q[8],q[12];
rz(-4.555781533392214) q[12];
cx q[8],q[12];
rz(pi/2) q[12];
sx q[12];
rz(11.85923767700637) q[12];
sx q[12];
rz(5*pi/2) q[12];
sx q[8];
rz(11.85923767700637) q[8];
sx q[8];
rz(3*pi) q[8];
rz(-2.4344849387505407) q[9];
cx q[1],q[9];
cx q[1],q[12];
rz(-2.4344849387505407) q[12];
cx q[1],q[12];
sx q[1];
rz(10.839043842999246) q[1];
sx q[1];
rz(5*pi/2) q[1];
cx q[8],q[9];
rz(-2.4344849387505407) q[9];
cx q[8],q[9];
cx q[8],q[12];
rz(-2.4344849387505407) q[12];
cx q[8],q[12];
rz(pi/2) q[12];
sx q[12];
rz(10.839043842999246) q[12];
sx q[12];
rz(5*pi/2) q[12];
sx q[8];
rz(10.839043842999246) q[8];
sx q[8];
rz(5*pi/2) q[8];
rz(pi/2) q[9];
sx q[9];
rz(10.839043842999246) q[9];
sx q[9];
rz(5*pi/2) q[9];
rz(pi/2) q[13];
sx q[13];
rz(pi/2) q[13];
cx q[2],q[13];
rz(-4.555781533392214) q[13];
cx q[2],q[13];
cx q[10],q[13];
rz(-4.555781533392214) q[13];
cx q[10],q[13];
rz(pi/2) q[10];
sx q[10];
rz(11.85923767700637) q[10];
sx q[10];
rz(5*pi/2) q[10];
cx q[0],q[10];
rz(-2.4344849387505407) q[10];
cx q[0],q[10];
cx q[0],q[11];
rz(-2.4344849387505407) q[11];
cx q[0],q[11];
sx q[0];
rz(10.839043842999246) q[0];
sx q[0];
rz(5*pi/2) q[0];
rz(pi/2) q[13];
sx q[13];
rz(11.85923767700637) q[13];
sx q[13];
rz(5*pi/2) q[13];
sx q[2];
rz(11.85923767700637) q[2];
sx q[2];
rz(3*pi) q[2];
cx q[2],q[3];
rz(-2.4344849387505407) q[3];
cx q[2],q[3];
cx q[2],q[13];
rz(-2.4344849387505407) q[13];
cx q[2],q[13];
cx q[10],q[13];
rz(-2.4344849387505407) q[13];
cx q[10],q[13];
rz(pi/2) q[10];
sx q[10];
rz(10.839043842999246) q[10];
sx q[10];
rz(5*pi/2) q[10];
rz(pi/2) q[13];
sx q[13];
rz(10.839043842999246) q[13];
sx q[13];
rz(5*pi/2) q[13];
sx q[2];
rz(10.839043842999246) q[2];
sx q[2];
rz(5*pi/2) q[2];
cx q[3],q[11];
rz(-2.4344849387505407) q[11];
cx q[3],q[11];
rz(pi/2) q[11];
sx q[11];
rz(10.839043842999246) q[11];
sx q[11];
rz(5*pi/2) q[11];
rz(pi/2) q[3];
sx q[3];
rz(10.839043842999246) q[3];
sx q[3];
rz(5*pi/2) q[3];
barrier q[0],q[1],q[2],q[3],q[4],q[5],q[6],q[7],q[8],q[9],q[10],q[11],q[12],q[13];
measure q[0] -> meas[0];
measure q[1] -> meas[1];
measure q[2] -> meas[2];
measure q[3] -> meas[3];
measure q[4] -> meas[4];
measure q[5] -> meas[5];
measure q[6] -> meas[6];
measure q[7] -> meas[7];
measure q[8] -> meas[8];
measure q[9] -> meas[9];
measure q[10] -> meas[10];
measure q[11] -> meas[11];
measure q[12] -> meas[12];
measure q[13] -> meas[13];

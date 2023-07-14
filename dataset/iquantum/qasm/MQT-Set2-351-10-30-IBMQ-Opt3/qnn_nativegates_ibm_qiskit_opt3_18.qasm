// Benchmark was created by MQT Bench on 2023-06-29
// For more information about MQT Bench, please visit https://www.cda.cit.tum.de/mqtbench/
// MQT Bench version: v1.0.0
// Qiskit version: {'qiskit-terra': '0.24.1', 'qiskit-aer': '0.12.0', 'qiskit-ignis': None, 'qiskit-ibmq-provider': '0.20.2', 'qiskit': '0.43.1', 'qiskit-nature': '0.6.2', 'qiskit-finance': '0.3.4', 'qiskit-optimization': '0.5.0', 'qiskit-machine-learning': '0.6.1'}
// Used Gate Set: ['rz', 'sx', 'x', 'cx', 'measure']

OPENQASM 2.0;
include "qelib1.inc";
qreg q[18];
creg meas[18];
rz(pi/2) q[0];
sx q[0];
rz(-1.1415926535897931) q[0];
rz(pi/2) q[1];
sx q[1];
rz(-2.7123889803846897) q[1];
cx q[0],q[1];
rz(9.172838187819544) q[1];
cx q[0],q[1];
rz(pi/2) q[2];
sx q[2];
rz(-2.7123889803846897) q[2];
cx q[0],q[2];
rz(9.172838187819544) q[2];
cx q[0],q[2];
cx q[1],q[2];
rz(9.172838187819544) q[2];
cx q[1],q[2];
rz(pi/2) q[3];
sx q[3];
rz(-2.7123889803846897) q[3];
cx q[0],q[3];
rz(9.172838187819544) q[3];
cx q[0],q[3];
cx q[1],q[3];
rz(9.172838187819544) q[3];
cx q[1],q[3];
cx q[2],q[3];
rz(9.172838187819544) q[3];
cx q[2],q[3];
rz(pi/2) q[4];
sx q[4];
rz(-2.7123889803846897) q[4];
cx q[0],q[4];
rz(9.172838187819544) q[4];
cx q[0],q[4];
cx q[1],q[4];
rz(9.172838187819544) q[4];
cx q[1],q[4];
cx q[2],q[4];
rz(9.172838187819544) q[4];
cx q[2],q[4];
cx q[3],q[4];
rz(9.172838187819544) q[4];
cx q[3],q[4];
rz(pi/2) q[5];
sx q[5];
rz(-2.7123889803846897) q[5];
cx q[0],q[5];
rz(9.172838187819544) q[5];
cx q[0],q[5];
cx q[1],q[5];
rz(9.172838187819544) q[5];
cx q[1],q[5];
cx q[2],q[5];
rz(9.172838187819544) q[5];
cx q[2],q[5];
cx q[3],q[5];
rz(9.172838187819544) q[5];
cx q[3],q[5];
cx q[4],q[5];
rz(9.172838187819544) q[5];
cx q[4],q[5];
rz(pi/2) q[6];
sx q[6];
rz(-2.7123889803846897) q[6];
cx q[0],q[6];
rz(9.172838187819544) q[6];
cx q[0],q[6];
cx q[1],q[6];
rz(9.172838187819544) q[6];
cx q[1],q[6];
cx q[2],q[6];
rz(9.172838187819544) q[6];
cx q[2],q[6];
cx q[3],q[6];
rz(9.172838187819544) q[6];
cx q[3],q[6];
cx q[4],q[6];
rz(9.172838187819544) q[6];
cx q[4],q[6];
cx q[5],q[6];
rz(9.172838187819544) q[6];
cx q[5],q[6];
rz(pi/2) q[7];
sx q[7];
rz(-2.7123889803846897) q[7];
cx q[0],q[7];
rz(9.172838187819544) q[7];
cx q[0],q[7];
cx q[1],q[7];
rz(9.172838187819544) q[7];
cx q[1],q[7];
cx q[2],q[7];
rz(9.172838187819544) q[7];
cx q[2],q[7];
cx q[3],q[7];
rz(9.172838187819544) q[7];
cx q[3],q[7];
cx q[4],q[7];
rz(9.172838187819544) q[7];
cx q[4],q[7];
cx q[5],q[7];
rz(9.172838187819544) q[7];
cx q[5],q[7];
cx q[6],q[7];
rz(9.172838187819544) q[7];
cx q[6],q[7];
rz(pi/2) q[8];
sx q[8];
rz(-2.7123889803846897) q[8];
cx q[0],q[8];
rz(9.172838187819544) q[8];
cx q[0],q[8];
cx q[1],q[8];
rz(9.172838187819544) q[8];
cx q[1],q[8];
cx q[2],q[8];
rz(9.172838187819544) q[8];
cx q[2],q[8];
cx q[3],q[8];
rz(9.172838187819544) q[8];
cx q[3],q[8];
cx q[4],q[8];
rz(9.172838187819544) q[8];
cx q[4],q[8];
cx q[5],q[8];
rz(9.172838187819544) q[8];
cx q[5],q[8];
cx q[6],q[8];
rz(9.172838187819544) q[8];
cx q[6],q[8];
cx q[7],q[8];
rz(9.172838187819544) q[8];
cx q[7],q[8];
rz(pi/2) q[9];
sx q[9];
rz(-2.7123889803846897) q[9];
cx q[0],q[9];
rz(9.172838187819544) q[9];
cx q[0],q[9];
cx q[1],q[9];
rz(9.172838187819544) q[9];
cx q[1],q[9];
cx q[2],q[9];
rz(9.172838187819544) q[9];
cx q[2],q[9];
cx q[3],q[9];
rz(9.172838187819544) q[9];
cx q[3],q[9];
cx q[4],q[9];
rz(9.172838187819544) q[9];
cx q[4],q[9];
cx q[5],q[9];
rz(9.172838187819544) q[9];
cx q[5],q[9];
cx q[6],q[9];
rz(9.172838187819544) q[9];
cx q[6],q[9];
cx q[7],q[9];
rz(9.172838187819544) q[9];
cx q[7],q[9];
cx q[8],q[9];
rz(9.172838187819544) q[9];
cx q[8],q[9];
rz(pi/2) q[10];
sx q[10];
rz(-2.7123889803846897) q[10];
cx q[0],q[10];
rz(9.172838187819544) q[10];
cx q[0],q[10];
cx q[1],q[10];
rz(9.172838187819544) q[10];
cx q[1],q[10];
cx q[2],q[10];
rz(9.172838187819544) q[10];
cx q[2],q[10];
cx q[3],q[10];
rz(9.172838187819544) q[10];
cx q[3],q[10];
cx q[4],q[10];
rz(9.172838187819544) q[10];
cx q[4],q[10];
cx q[5],q[10];
rz(9.172838187819544) q[10];
cx q[5],q[10];
cx q[6],q[10];
rz(9.172838187819544) q[10];
cx q[6],q[10];
cx q[7],q[10];
rz(9.172838187819544) q[10];
cx q[7],q[10];
cx q[8],q[10];
rz(9.172838187819544) q[10];
cx q[8],q[10];
cx q[9],q[10];
rz(9.172838187819544) q[10];
cx q[9],q[10];
rz(pi/2) q[11];
sx q[11];
rz(-2.7123889803846897) q[11];
cx q[0],q[11];
rz(9.172838187819544) q[11];
cx q[0],q[11];
cx q[1],q[11];
rz(9.172838187819544) q[11];
cx q[1],q[11];
cx q[2],q[11];
rz(9.172838187819544) q[11];
cx q[2],q[11];
cx q[3],q[11];
rz(9.172838187819544) q[11];
cx q[3],q[11];
cx q[4],q[11];
rz(9.172838187819544) q[11];
cx q[4],q[11];
cx q[5],q[11];
rz(9.172838187819544) q[11];
cx q[5],q[11];
cx q[6],q[11];
rz(9.172838187819544) q[11];
cx q[6],q[11];
cx q[7],q[11];
rz(9.172838187819544) q[11];
cx q[7],q[11];
cx q[8],q[11];
rz(9.172838187819544) q[11];
cx q[8],q[11];
cx q[9],q[11];
rz(9.172838187819544) q[11];
cx q[9],q[11];
cx q[10],q[11];
rz(9.172838187819544) q[11];
cx q[10],q[11];
rz(pi/2) q[12];
sx q[12];
rz(-2.7123889803846897) q[12];
cx q[0],q[12];
rz(9.172838187819544) q[12];
cx q[0],q[12];
cx q[1],q[12];
rz(9.172838187819544) q[12];
cx q[1],q[12];
cx q[2],q[12];
rz(9.172838187819544) q[12];
cx q[2],q[12];
cx q[3],q[12];
rz(9.172838187819544) q[12];
cx q[3],q[12];
cx q[4],q[12];
rz(9.172838187819544) q[12];
cx q[4],q[12];
cx q[5],q[12];
rz(9.172838187819544) q[12];
cx q[5],q[12];
cx q[6],q[12];
rz(9.172838187819544) q[12];
cx q[6],q[12];
cx q[7],q[12];
rz(9.172838187819544) q[12];
cx q[7],q[12];
cx q[8],q[12];
rz(9.172838187819544) q[12];
cx q[8],q[12];
cx q[9],q[12];
rz(9.172838187819544) q[12];
cx q[9],q[12];
cx q[10],q[12];
rz(9.172838187819544) q[12];
cx q[10],q[12];
cx q[11],q[12];
rz(9.172838187819544) q[12];
cx q[11],q[12];
rz(pi/2) q[13];
sx q[13];
rz(-2.7123889803846897) q[13];
cx q[0],q[13];
rz(9.172838187819544) q[13];
cx q[0],q[13];
cx q[1],q[13];
rz(9.172838187819544) q[13];
cx q[1],q[13];
cx q[2],q[13];
rz(9.172838187819544) q[13];
cx q[2],q[13];
cx q[3],q[13];
rz(9.172838187819544) q[13];
cx q[3],q[13];
cx q[4],q[13];
rz(9.172838187819544) q[13];
cx q[4],q[13];
cx q[5],q[13];
rz(9.172838187819544) q[13];
cx q[5],q[13];
cx q[6],q[13];
rz(9.172838187819544) q[13];
cx q[6],q[13];
cx q[7],q[13];
rz(9.172838187819544) q[13];
cx q[7],q[13];
cx q[8],q[13];
rz(9.172838187819544) q[13];
cx q[8],q[13];
cx q[9],q[13];
rz(9.172838187819544) q[13];
cx q[9],q[13];
cx q[10],q[13];
rz(9.172838187819544) q[13];
cx q[10],q[13];
cx q[11],q[13];
rz(9.172838187819544) q[13];
cx q[11],q[13];
cx q[12],q[13];
rz(9.172838187819544) q[13];
cx q[12],q[13];
rz(pi/2) q[14];
sx q[14];
rz(-2.7123889803846897) q[14];
cx q[0],q[14];
rz(9.172838187819544) q[14];
cx q[0],q[14];
cx q[1],q[14];
rz(9.172838187819544) q[14];
cx q[1],q[14];
cx q[2],q[14];
rz(9.172838187819544) q[14];
cx q[2],q[14];
cx q[3],q[14];
rz(9.172838187819544) q[14];
cx q[3],q[14];
cx q[4],q[14];
rz(9.172838187819544) q[14];
cx q[4],q[14];
cx q[5],q[14];
rz(9.172838187819544) q[14];
cx q[5],q[14];
cx q[6],q[14];
rz(9.172838187819544) q[14];
cx q[6],q[14];
cx q[7],q[14];
rz(9.172838187819544) q[14];
cx q[7],q[14];
cx q[8],q[14];
rz(9.172838187819544) q[14];
cx q[8],q[14];
cx q[9],q[14];
rz(9.172838187819544) q[14];
cx q[9],q[14];
cx q[10],q[14];
rz(9.172838187819544) q[14];
cx q[10],q[14];
cx q[11],q[14];
rz(9.172838187819544) q[14];
cx q[11],q[14];
cx q[12],q[14];
rz(9.172838187819544) q[14];
cx q[12],q[14];
cx q[13],q[14];
rz(9.172838187819544) q[14];
cx q[13],q[14];
rz(pi/2) q[15];
sx q[15];
rz(-2.7123889803846897) q[15];
cx q[0],q[15];
rz(9.172838187819544) q[15];
cx q[0],q[15];
cx q[1],q[15];
rz(9.172838187819544) q[15];
cx q[1],q[15];
cx q[2],q[15];
rz(9.172838187819544) q[15];
cx q[2],q[15];
cx q[3],q[15];
rz(9.172838187819544) q[15];
cx q[3],q[15];
cx q[4],q[15];
rz(9.172838187819544) q[15];
cx q[4],q[15];
cx q[5],q[15];
rz(9.172838187819544) q[15];
cx q[5],q[15];
cx q[6],q[15];
rz(9.172838187819544) q[15];
cx q[6],q[15];
cx q[7],q[15];
rz(9.172838187819544) q[15];
cx q[7],q[15];
cx q[8],q[15];
rz(9.172838187819544) q[15];
cx q[8],q[15];
cx q[9],q[15];
rz(9.172838187819544) q[15];
cx q[9],q[15];
cx q[10],q[15];
rz(9.172838187819544) q[15];
cx q[10],q[15];
cx q[11],q[15];
rz(9.172838187819544) q[15];
cx q[11],q[15];
cx q[12],q[15];
rz(9.172838187819544) q[15];
cx q[12],q[15];
cx q[13],q[15];
rz(9.172838187819544) q[15];
cx q[13],q[15];
cx q[14],q[15];
rz(9.172838187819544) q[15];
cx q[14],q[15];
rz(pi/2) q[16];
sx q[16];
rz(-2.7123889803846897) q[16];
cx q[0],q[16];
rz(9.172838187819544) q[16];
cx q[0],q[16];
cx q[1],q[16];
rz(9.172838187819544) q[16];
cx q[1],q[16];
cx q[2],q[16];
rz(9.172838187819544) q[16];
cx q[2],q[16];
cx q[3],q[16];
rz(9.172838187819544) q[16];
cx q[3],q[16];
cx q[4],q[16];
rz(9.172838187819544) q[16];
cx q[4],q[16];
cx q[5],q[16];
rz(9.172838187819544) q[16];
cx q[5],q[16];
cx q[6],q[16];
rz(9.172838187819544) q[16];
cx q[6],q[16];
cx q[7],q[16];
rz(9.172838187819544) q[16];
cx q[7],q[16];
cx q[8],q[16];
rz(9.172838187819544) q[16];
cx q[8],q[16];
cx q[9],q[16];
rz(9.172838187819544) q[16];
cx q[9],q[16];
cx q[10],q[16];
rz(9.172838187819544) q[16];
cx q[10],q[16];
cx q[11],q[16];
rz(9.172838187819544) q[16];
cx q[11],q[16];
cx q[12],q[16];
rz(9.172838187819544) q[16];
cx q[12],q[16];
cx q[13],q[16];
rz(9.172838187819544) q[16];
cx q[13],q[16];
cx q[14],q[16];
rz(9.172838187819544) q[16];
cx q[14],q[16];
cx q[15],q[16];
rz(9.172838187819544) q[16];
cx q[15],q[16];
rz(pi/2) q[17];
sx q[17];
rz(-2.7123889803846897) q[17];
cx q[0],q[17];
rz(9.172838187819544) q[17];
cx q[0],q[17];
sx q[0];
rz(-5.853981633974483) q[0];
cx q[1],q[17];
rz(9.172838187819544) q[17];
cx q[1],q[17];
rz(pi/2) q[1];
sx q[1];
rz(-2.7123889803846897) q[1];
cx q[0],q[1];
rz(9.172838187819544) q[1];
cx q[0],q[1];
cx q[2],q[17];
rz(9.172838187819544) q[17];
cx q[2],q[17];
rz(pi/2) q[2];
sx q[2];
rz(-2.7123889803846897) q[2];
cx q[0],q[2];
rz(9.172838187819544) q[2];
cx q[0],q[2];
cx q[1],q[2];
rz(9.172838187819544) q[2];
cx q[1],q[2];
cx q[3],q[17];
rz(9.172838187819544) q[17];
cx q[3],q[17];
rz(pi/2) q[3];
sx q[3];
rz(-2.7123889803846897) q[3];
cx q[0],q[3];
rz(9.172838187819544) q[3];
cx q[0],q[3];
cx q[1],q[3];
rz(9.172838187819544) q[3];
cx q[1],q[3];
cx q[2],q[3];
rz(9.172838187819544) q[3];
cx q[2],q[3];
cx q[4],q[17];
rz(9.172838187819544) q[17];
cx q[4],q[17];
rz(pi/2) q[4];
sx q[4];
rz(-2.7123889803846897) q[4];
cx q[0],q[4];
rz(9.172838187819544) q[4];
cx q[0],q[4];
cx q[1],q[4];
rz(9.172838187819544) q[4];
cx q[1],q[4];
cx q[2],q[4];
rz(9.172838187819544) q[4];
cx q[2],q[4];
cx q[3],q[4];
rz(9.172838187819544) q[4];
cx q[3],q[4];
cx q[5],q[17];
rz(9.172838187819544) q[17];
cx q[5],q[17];
rz(pi/2) q[5];
sx q[5];
rz(-2.7123889803846897) q[5];
cx q[0],q[5];
rz(9.172838187819544) q[5];
cx q[0],q[5];
cx q[1],q[5];
rz(9.172838187819544) q[5];
cx q[1],q[5];
cx q[2],q[5];
rz(9.172838187819544) q[5];
cx q[2],q[5];
cx q[3],q[5];
rz(9.172838187819544) q[5];
cx q[3],q[5];
cx q[4],q[5];
rz(9.172838187819544) q[5];
cx q[4],q[5];
cx q[6],q[17];
rz(9.172838187819544) q[17];
cx q[6],q[17];
rz(pi/2) q[6];
sx q[6];
rz(-2.7123889803846897) q[6];
cx q[0],q[6];
rz(9.172838187819544) q[6];
cx q[0],q[6];
cx q[1],q[6];
rz(9.172838187819544) q[6];
cx q[1],q[6];
cx q[2],q[6];
rz(9.172838187819544) q[6];
cx q[2],q[6];
cx q[3],q[6];
rz(9.172838187819544) q[6];
cx q[3],q[6];
cx q[4],q[6];
rz(9.172838187819544) q[6];
cx q[4],q[6];
cx q[5],q[6];
rz(9.172838187819544) q[6];
cx q[5],q[6];
cx q[7],q[17];
rz(9.172838187819544) q[17];
cx q[7],q[17];
rz(pi/2) q[7];
sx q[7];
rz(-2.7123889803846897) q[7];
cx q[0],q[7];
rz(9.172838187819544) q[7];
cx q[0],q[7];
cx q[1],q[7];
rz(9.172838187819544) q[7];
cx q[1],q[7];
cx q[2],q[7];
rz(9.172838187819544) q[7];
cx q[2],q[7];
cx q[3],q[7];
rz(9.172838187819544) q[7];
cx q[3],q[7];
cx q[4],q[7];
rz(9.172838187819544) q[7];
cx q[4],q[7];
cx q[5],q[7];
rz(9.172838187819544) q[7];
cx q[5],q[7];
cx q[6],q[7];
rz(9.172838187819544) q[7];
cx q[6],q[7];
cx q[8],q[17];
rz(9.172838187819544) q[17];
cx q[8],q[17];
rz(pi/2) q[8];
sx q[8];
rz(-2.7123889803846897) q[8];
cx q[0],q[8];
rz(9.172838187819544) q[8];
cx q[0],q[8];
cx q[1],q[8];
rz(9.172838187819544) q[8];
cx q[1],q[8];
cx q[2],q[8];
rz(9.172838187819544) q[8];
cx q[2],q[8];
cx q[3],q[8];
rz(9.172838187819544) q[8];
cx q[3],q[8];
cx q[4],q[8];
rz(9.172838187819544) q[8];
cx q[4],q[8];
cx q[5],q[8];
rz(9.172838187819544) q[8];
cx q[5],q[8];
cx q[6],q[8];
rz(9.172838187819544) q[8];
cx q[6],q[8];
cx q[7],q[8];
rz(9.172838187819544) q[8];
cx q[7],q[8];
cx q[9],q[17];
rz(9.172838187819544) q[17];
cx q[9],q[17];
cx q[10],q[17];
rz(9.172838187819544) q[17];
cx q[10],q[17];
rz(pi/2) q[10];
sx q[10];
rz(-2.7123889803846897) q[10];
cx q[11],q[17];
rz(9.172838187819544) q[17];
cx q[11],q[17];
rz(pi/2) q[11];
sx q[11];
rz(-2.7123889803846897) q[11];
cx q[12],q[17];
rz(9.172838187819544) q[17];
cx q[12],q[17];
rz(pi/2) q[12];
sx q[12];
rz(-2.7123889803846897) q[12];
cx q[13],q[17];
rz(9.172838187819544) q[17];
cx q[13],q[17];
rz(pi/2) q[13];
sx q[13];
rz(-2.7123889803846897) q[13];
cx q[14],q[17];
rz(9.172838187819544) q[17];
cx q[14],q[17];
rz(pi/2) q[14];
sx q[14];
rz(-2.7123889803846897) q[14];
cx q[15],q[17];
rz(9.172838187819544) q[17];
cx q[15],q[17];
rz(pi/2) q[15];
sx q[15];
rz(-2.7123889803846897) q[15];
cx q[16],q[17];
rz(9.172838187819544) q[17];
cx q[16],q[17];
rz(pi/2) q[16];
sx q[16];
rz(-2.7123889803846897) q[16];
rz(pi/2) q[17];
sx q[17];
rz(-2.7123889803846897) q[17];
rz(pi/2) q[9];
sx q[9];
rz(-2.7123889803846897) q[9];
cx q[0],q[9];
rz(9.172838187819544) q[9];
cx q[0],q[9];
cx q[0],q[10];
cx q[1],q[9];
rz(9.172838187819544) q[10];
cx q[0],q[10];
cx q[0],q[11];
rz(9.172838187819544) q[11];
cx q[0],q[11];
cx q[0],q[12];
rz(9.172838187819544) q[12];
cx q[0],q[12];
cx q[0],q[13];
rz(9.172838187819544) q[13];
cx q[0],q[13];
cx q[0],q[14];
rz(9.172838187819544) q[14];
cx q[0],q[14];
cx q[0],q[15];
rz(9.172838187819544) q[15];
cx q[0],q[15];
cx q[0],q[16];
rz(9.172838187819544) q[16];
cx q[0],q[16];
cx q[0],q[17];
rz(9.172838187819544) q[17];
cx q[0],q[17];
sx q[0];
rz(2.8034185009351313) q[0];
sx q[0];
rz(9.172838187819544) q[9];
cx q[1],q[9];
cx q[1],q[10];
rz(9.172838187819544) q[10];
cx q[1],q[10];
cx q[1],q[11];
rz(9.172838187819544) q[11];
cx q[1],q[11];
cx q[1],q[12];
rz(9.172838187819544) q[12];
cx q[1],q[12];
cx q[1],q[13];
rz(9.172838187819544) q[13];
cx q[1],q[13];
cx q[1],q[14];
rz(9.172838187819544) q[14];
cx q[1],q[14];
cx q[1],q[15];
rz(9.172838187819544) q[15];
cx q[1],q[15];
cx q[1],q[16];
rz(9.172838187819544) q[16];
cx q[1],q[16];
cx q[1],q[17];
rz(9.172838187819544) q[17];
cx q[1],q[17];
rz(-pi) q[1];
sx q[1];
rz(2.7376107993048926) q[1];
sx q[1];
cx q[2],q[9];
rz(9.172838187819544) q[9];
cx q[2],q[9];
cx q[2],q[10];
rz(9.172838187819544) q[10];
cx q[2],q[10];
cx q[2],q[11];
rz(9.172838187819544) q[11];
cx q[2],q[11];
cx q[2],q[12];
rz(9.172838187819544) q[12];
cx q[2],q[12];
cx q[2],q[13];
rz(9.172838187819544) q[13];
cx q[2],q[13];
cx q[2],q[14];
rz(9.172838187819544) q[14];
cx q[2],q[14];
cx q[2],q[15];
rz(9.172838187819544) q[15];
cx q[2],q[15];
cx q[2],q[16];
rz(9.172838187819544) q[16];
cx q[2],q[16];
cx q[2],q[17];
rz(9.172838187819544) q[17];
cx q[2],q[17];
rz(-pi) q[2];
sx q[2];
rz(2.149065958637041) q[2];
sx q[2];
cx q[3],q[9];
rz(9.172838187819544) q[9];
cx q[3],q[9];
cx q[3],q[10];
rz(9.172838187819544) q[10];
cx q[3],q[10];
cx q[3],q[11];
rz(9.172838187819544) q[11];
cx q[3],q[11];
cx q[3],q[12];
rz(9.172838187819544) q[12];
cx q[3],q[12];
cx q[3],q[13];
rz(9.172838187819544) q[13];
cx q[3],q[13];
cx q[3],q[14];
rz(9.172838187819544) q[14];
cx q[3],q[14];
cx q[3],q[15];
rz(9.172838187819544) q[15];
cx q[3],q[15];
cx q[3],q[16];
rz(9.172838187819544) q[16];
cx q[3],q[16];
cx q[3],q[17];
rz(9.172838187819544) q[17];
cx q[3],q[17];
rz(-pi) q[3];
sx q[3];
rz(2.570803715132561) q[3];
sx q[3];
cx q[4],q[9];
rz(9.172838187819544) q[9];
cx q[4],q[9];
cx q[4],q[10];
rz(9.172838187819544) q[10];
cx q[4],q[10];
cx q[4],q[11];
rz(9.172838187819544) q[11];
cx q[4],q[11];
cx q[4],q[12];
rz(9.172838187819544) q[12];
cx q[4],q[12];
cx q[4],q[13];
rz(9.172838187819544) q[13];
cx q[4],q[13];
cx q[4],q[14];
rz(9.172838187819544) q[14];
cx q[4],q[14];
cx q[4],q[15];
rz(9.172838187819544) q[15];
cx q[4],q[15];
cx q[4],q[16];
rz(9.172838187819544) q[16];
cx q[4],q[16];
cx q[4],q[17];
rz(9.172838187819544) q[17];
cx q[4],q[17];
rz(-pi) q[4];
sx q[4];
rz(2.7220517865869605) q[4];
sx q[4];
cx q[5],q[9];
rz(9.172838187819544) q[9];
cx q[5],q[9];
cx q[5],q[10];
rz(9.172838187819544) q[10];
cx q[5],q[10];
cx q[5],q[11];
rz(9.172838187819544) q[11];
cx q[5],q[11];
cx q[5],q[12];
rz(9.172838187819544) q[12];
cx q[5],q[12];
cx q[5],q[13];
rz(9.172838187819544) q[13];
cx q[5],q[13];
cx q[5],q[14];
rz(9.172838187819544) q[14];
cx q[5],q[14];
cx q[5],q[15];
rz(9.172838187819544) q[15];
cx q[5],q[15];
cx q[5],q[16];
rz(9.172838187819544) q[16];
cx q[5],q[16];
cx q[5],q[17];
rz(9.172838187819544) q[17];
cx q[5],q[17];
rz(-pi) q[5];
sx q[5];
rz(2.942722109840754) q[5];
sx q[5];
cx q[6],q[9];
rz(9.172838187819544) q[9];
cx q[6],q[9];
cx q[6],q[10];
rz(9.172838187819544) q[10];
cx q[6],q[10];
cx q[6],q[11];
rz(9.172838187819544) q[11];
cx q[6],q[11];
cx q[6],q[12];
rz(9.172838187819544) q[12];
cx q[6],q[12];
cx q[6],q[13];
rz(9.172838187819544) q[13];
cx q[6],q[13];
cx q[6],q[14];
rz(9.172838187819544) q[14];
cx q[6],q[14];
cx q[6],q[15];
rz(9.172838187819544) q[15];
cx q[6],q[15];
cx q[6],q[16];
rz(9.172838187819544) q[16];
cx q[6],q[16];
cx q[6],q[17];
rz(9.172838187819544) q[17];
cx q[6],q[17];
rz(-pi) q[6];
sx q[6];
rz(2.281279668424924) q[6];
sx q[6];
cx q[7],q[9];
rz(9.172838187819544) q[9];
cx q[7],q[9];
cx q[7],q[10];
rz(9.172838187819544) q[10];
cx q[7],q[10];
cx q[7],q[11];
rz(9.172838187819544) q[11];
cx q[7],q[11];
cx q[7],q[12];
rz(9.172838187819544) q[12];
cx q[7],q[12];
cx q[7],q[13];
rz(9.172838187819544) q[13];
cx q[7],q[13];
cx q[7],q[14];
rz(9.172838187819544) q[14];
cx q[7],q[14];
cx q[7],q[15];
rz(9.172838187819544) q[15];
cx q[7],q[15];
cx q[7],q[16];
rz(9.172838187819544) q[16];
cx q[7],q[16];
cx q[7],q[17];
rz(9.172838187819544) q[17];
cx q[7],q[17];
rz(-pi) q[7];
sx q[7];
rz(2.3171485842523154) q[7];
sx q[7];
cx q[8],q[9];
rz(9.172838187819544) q[9];
cx q[8],q[9];
cx q[8],q[10];
rz(9.172838187819544) q[10];
cx q[8],q[10];
cx q[8],q[11];
rz(9.172838187819544) q[11];
cx q[8],q[11];
cx q[8],q[12];
rz(9.172838187819544) q[12];
cx q[8],q[12];
cx q[8],q[13];
rz(9.172838187819544) q[13];
cx q[8],q[13];
cx q[8],q[14];
rz(9.172838187819544) q[14];
cx q[8],q[14];
cx q[8],q[15];
rz(9.172838187819544) q[15];
cx q[8],q[15];
cx q[8],q[16];
rz(9.172838187819544) q[16];
cx q[8],q[16];
cx q[8],q[17];
rz(9.172838187819544) q[17];
cx q[8],q[17];
rz(-pi) q[8];
sx q[8];
rz(2.343706497352886) q[8];
sx q[8];
cx q[9],q[10];
rz(9.172838187819544) q[10];
cx q[9],q[10];
cx q[9],q[11];
rz(9.172838187819544) q[11];
cx q[9],q[11];
cx q[10],q[11];
rz(9.172838187819544) q[11];
cx q[10],q[11];
cx q[9],q[12];
rz(9.172838187819544) q[12];
cx q[9],q[12];
cx q[10],q[12];
rz(9.172838187819544) q[12];
cx q[10],q[12];
cx q[11],q[12];
rz(9.172838187819544) q[12];
cx q[11],q[12];
cx q[9],q[13];
rz(9.172838187819544) q[13];
cx q[9],q[13];
cx q[10],q[13];
rz(9.172838187819544) q[13];
cx q[10],q[13];
cx q[11],q[13];
rz(9.172838187819544) q[13];
cx q[11],q[13];
cx q[12],q[13];
rz(9.172838187819544) q[13];
cx q[12],q[13];
cx q[9],q[14];
rz(9.172838187819544) q[14];
cx q[9],q[14];
cx q[10],q[14];
rz(9.172838187819544) q[14];
cx q[10],q[14];
cx q[11],q[14];
rz(9.172838187819544) q[14];
cx q[11],q[14];
cx q[12],q[14];
rz(9.172838187819544) q[14];
cx q[12],q[14];
cx q[13],q[14];
rz(9.172838187819544) q[14];
cx q[13],q[14];
cx q[9],q[15];
rz(9.172838187819544) q[15];
cx q[9],q[15];
cx q[10],q[15];
rz(9.172838187819544) q[15];
cx q[10],q[15];
cx q[11],q[15];
rz(9.172838187819544) q[15];
cx q[11],q[15];
cx q[12],q[15];
rz(9.172838187819544) q[15];
cx q[12],q[15];
cx q[13],q[15];
rz(9.172838187819544) q[15];
cx q[13],q[15];
cx q[14],q[15];
rz(9.172838187819544) q[15];
cx q[14],q[15];
cx q[9],q[16];
rz(9.172838187819544) q[16];
cx q[9],q[16];
cx q[10],q[16];
rz(9.172838187819544) q[16];
cx q[10],q[16];
cx q[11],q[16];
rz(9.172838187819544) q[16];
cx q[11],q[16];
cx q[12],q[16];
rz(9.172838187819544) q[16];
cx q[12],q[16];
cx q[13],q[16];
rz(9.172838187819544) q[16];
cx q[13],q[16];
cx q[14],q[16];
rz(9.172838187819544) q[16];
cx q[14],q[16];
cx q[15],q[16];
rz(9.172838187819544) q[16];
cx q[15],q[16];
sx q[16];
rz(2.871050923831863) q[16];
sx q[16];
rz(pi/2) q[16];
cx q[9],q[17];
rz(9.172838187819544) q[17];
cx q[9],q[17];
cx q[10],q[17];
rz(9.172838187819544) q[17];
cx q[10],q[17];
rz(-pi) q[10];
sx q[10];
rz(2.7895581094556565) q[10];
sx q[10];
cx q[11],q[17];
rz(9.172838187819544) q[17];
cx q[11],q[17];
rz(-pi) q[11];
sx q[11];
rz(2.5869102925023864) q[11];
sx q[11];
cx q[12],q[17];
rz(9.172838187819544) q[17];
cx q[12],q[17];
rz(-pi) q[12];
sx q[12];
rz(2.2444485898614985) q[12];
sx q[12];
cx q[13],q[17];
rz(9.172838187819544) q[17];
cx q[13],q[17];
rz(-pi) q[13];
sx q[13];
rz(2.635916643793408) q[13];
sx q[13];
cx q[14],q[17];
rz(9.172838187819544) q[17];
cx q[14],q[17];
rz(-pi) q[14];
sx q[14];
rz(2.597419444070934) q[14];
sx q[14];
cx q[15],q[17];
rz(9.172838187819544) q[17];
cx q[15],q[17];
rz(-pi) q[15];
sx q[15];
rz(2.4919201339768895) q[15];
sx q[15];
rz(-pi) q[17];
sx q[17];
rz(2.391500171921611) q[17];
sx q[17];
cx q[16],q[17];
sx q[16];
rz(-0.04537363230759084) q[16];
sx q[16];
rz(1.3916464014515786) q[17];
cx q[16],q[17];
rz(-pi/2) q[16];
sx q[16];
rz(-0.0494309676787168) q[16];
sx q[16];
rz(pi/2) q[16];
cx q[15],q[16];
cx q[14],q[15];
cx q[13],q[14];
cx q[12],q[13];
cx q[11],q[12];
cx q[10],q[11];
rz(-pi) q[11];
sx q[11];
rz(2.570470364220655) q[11];
sx q[11];
rz(-pi) q[12];
sx q[12];
rz(2.332200535359193) q[12];
sx q[12];
rz(-pi) q[13];
sx q[13];
rz(2.7653625530067636) q[13];
sx q[13];
rz(-pi) q[14];
sx q[14];
rz(3.13419976467703) q[14];
sx q[14];
rz(-pi) q[15];
sx q[15];
rz(2.6670242981200882) q[15];
sx q[15];
rz(-pi) q[16];
sx q[16];
rz(2.721325200595631) q[16];
sx q[16];
rz(-1.3549765880836313) q[17];
sx q[17];
rz(-2.4815795908589564) q[17];
sx q[17];
rz(-0.2707049080188453) q[17];
rz(-pi) q[9];
sx q[9];
rz(3.0489335969210796) q[9];
sx q[9];
cx q[9],q[10];
rz(-pi) q[10];
sx q[10];
rz(2.236084233354128) q[10];
sx q[10];
cx q[8],q[9];
cx q[7],q[8];
cx q[6],q[7];
cx q[5],q[6];
cx q[4],q[5];
cx q[3],q[4];
cx q[2],q[3];
cx q[1],q[2];
cx q[0],q[1];
rz(-pi) q[0];
sx q[0];
rz(2.290999477871571) q[0];
sx q[0];
rz(-pi) q[1];
sx q[1];
rz(2.215265048428165) q[1];
sx q[1];
rz(-pi) q[2];
sx q[2];
rz(2.4060169878075133) q[2];
sx q[2];
rz(-pi) q[3];
sx q[3];
rz(2.850784562920504) q[3];
sx q[3];
rz(-pi) q[4];
sx q[4];
rz(2.6603536532095813) q[4];
sx q[4];
rz(-pi) q[5];
sx q[5];
rz(3.1365506101615637) q[5];
sx q[5];
rz(-pi) q[6];
sx q[6];
rz(2.8124052371431265) q[6];
sx q[6];
rz(-pi) q[7];
sx q[7];
rz(3.0695257000346734) q[7];
sx q[7];
rz(-pi) q[8];
sx q[8];
rz(2.8152428079224574) q[8];
sx q[8];
rz(-pi) q[9];
sx q[9];
rz(2.151957664300472) q[9];
sx q[9];
barrier q[0],q[1],q[2],q[3],q[4],q[5],q[6],q[7],q[8],q[9],q[10],q[11],q[12],q[13],q[14],q[15],q[16],q[17];
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
measure q[14] -> meas[14];
measure q[15] -> meas[15];
measure q[16] -> meas[16];
measure q[17] -> meas[17];

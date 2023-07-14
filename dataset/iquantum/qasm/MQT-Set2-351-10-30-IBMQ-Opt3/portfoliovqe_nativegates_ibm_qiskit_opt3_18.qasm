// Benchmark was created by MQT Bench on 2023-06-29
// For more information about MQT Bench, please visit https://www.cda.cit.tum.de/mqtbench/
// MQT Bench version: v1.0.0
// Qiskit version: {'qiskit-terra': '0.24.1', 'qiskit-aer': '0.12.0', 'qiskit-ignis': None, 'qiskit-ibmq-provider': '0.20.2', 'qiskit': '0.43.1', 'qiskit-nature': '0.6.2', 'qiskit-finance': '0.3.4', 'qiskit-optimization': '0.5.0', 'qiskit-machine-learning': '0.6.1'}
// Used Gate Set: ['rz', 'sx', 'x', 'cx', 'measure']

OPENQASM 2.0;
include "qelib1.inc";
qreg q[18];
creg meas[18];
sx q[0];
rz(2.804542967947489) q[0];
sx q[0];
rz(-pi) q[0];
sx q[1];
rz(-2.719006923537421) q[1];
sx q[1];
cx q[0],q[1];
rz(pi/2) q[1];
sx q[1];
rz(pi/2) q[1];
sx q[2];
rz(0.46311936052260894) q[2];
sx q[2];
cx q[0],q[2];
cx q[1],q[2];
rz(pi/2) q[2];
sx q[2];
rz(pi/2) q[2];
sx q[3];
rz(0.35712337293306673) q[3];
sx q[3];
cx q[0],q[3];
cx q[1],q[3];
cx q[2],q[3];
rz(pi/2) q[3];
sx q[3];
rz(pi/2) q[3];
sx q[4];
rz(2.762288756299194) q[4];
sx q[4];
cx q[0],q[4];
cx q[1],q[4];
cx q[2],q[4];
cx q[3],q[4];
rz(pi/2) q[4];
sx q[4];
rz(pi/2) q[4];
sx q[5];
rz(1.9257488490648438) q[5];
sx q[5];
cx q[0],q[5];
cx q[1],q[5];
cx q[2],q[5];
cx q[3],q[5];
cx q[4],q[5];
rz(pi/2) q[5];
sx q[5];
rz(pi/2) q[5];
sx q[6];
rz(-1.2944971339125306) q[6];
sx q[6];
cx q[0],q[6];
cx q[1],q[6];
cx q[2],q[6];
cx q[3],q[6];
cx q[4],q[6];
cx q[5],q[6];
rz(pi/2) q[6];
sx q[6];
rz(pi/2) q[6];
sx q[7];
rz(-1.9160883147069292) q[7];
sx q[7];
cx q[0],q[7];
cx q[1],q[7];
cx q[2],q[7];
cx q[3],q[7];
cx q[4],q[7];
cx q[5],q[7];
cx q[6],q[7];
rz(pi/2) q[7];
sx q[7];
rz(pi/2) q[7];
sx q[8];
rz(-1.5412984000231038) q[8];
sx q[8];
cx q[0],q[8];
cx q[1],q[8];
cx q[2],q[8];
cx q[3],q[8];
cx q[4],q[8];
cx q[5],q[8];
cx q[6],q[8];
cx q[7],q[8];
rz(pi/2) q[8];
sx q[8];
rz(pi/2) q[8];
sx q[9];
rz(2.130804586707307) q[9];
sx q[9];
cx q[0],q[9];
cx q[1],q[9];
cx q[2],q[9];
cx q[3],q[9];
cx q[4],q[9];
cx q[5],q[9];
cx q[6],q[9];
cx q[7],q[9];
cx q[8],q[9];
rz(pi/2) q[9];
sx q[9];
rz(pi/2) q[9];
sx q[10];
rz(-2.5294496944400278) q[10];
sx q[10];
cx q[0],q[10];
cx q[1],q[10];
cx q[2],q[10];
cx q[3],q[10];
cx q[4],q[10];
cx q[5],q[10];
cx q[6],q[10];
cx q[7],q[10];
cx q[8],q[10];
cx q[9],q[10];
rz(pi/2) q[10];
sx q[10];
rz(pi/2) q[10];
sx q[11];
rz(-0.6989011847311666) q[11];
sx q[11];
cx q[0],q[11];
cx q[1],q[11];
cx q[2],q[11];
cx q[3],q[11];
cx q[4],q[11];
cx q[5],q[11];
cx q[6],q[11];
cx q[7],q[11];
cx q[8],q[11];
cx q[9],q[11];
cx q[10],q[11];
rz(pi/2) q[11];
sx q[11];
rz(pi/2) q[11];
sx q[12];
rz(0.20365040369339615) q[12];
sx q[12];
cx q[0],q[12];
cx q[1],q[12];
cx q[2],q[12];
cx q[3],q[12];
cx q[4],q[12];
cx q[5],q[12];
cx q[6],q[12];
cx q[7],q[12];
cx q[8],q[12];
cx q[9],q[12];
cx q[10],q[12];
cx q[11],q[12];
rz(pi/2) q[12];
sx q[12];
rz(pi/2) q[12];
sx q[13];
rz(-0.23058167215998004) q[13];
sx q[13];
cx q[0],q[13];
cx q[1],q[13];
cx q[2],q[13];
cx q[3],q[13];
cx q[4],q[13];
cx q[5],q[13];
cx q[6],q[13];
cx q[7],q[13];
cx q[8],q[13];
cx q[9],q[13];
cx q[10],q[13];
cx q[11],q[13];
cx q[12],q[13];
rz(pi/2) q[13];
sx q[13];
rz(pi/2) q[13];
sx q[14];
rz(0.7329865326615717) q[14];
sx q[14];
cx q[0],q[14];
cx q[1],q[14];
cx q[2],q[14];
cx q[3],q[14];
cx q[4],q[14];
cx q[5],q[14];
cx q[6],q[14];
cx q[7],q[14];
cx q[8],q[14];
cx q[9],q[14];
cx q[10],q[14];
cx q[11],q[14];
cx q[12],q[14];
cx q[13],q[14];
rz(pi/2) q[14];
sx q[14];
rz(pi/2) q[14];
sx q[15];
rz(-2.4939805238571537) q[15];
sx q[15];
cx q[0],q[15];
cx q[1],q[15];
cx q[2],q[15];
cx q[3],q[15];
cx q[4],q[15];
cx q[5],q[15];
cx q[6],q[15];
cx q[7],q[15];
cx q[8],q[15];
cx q[9],q[15];
cx q[10],q[15];
cx q[11],q[15];
cx q[12],q[15];
cx q[13],q[15];
cx q[14],q[15];
rz(pi/2) q[15];
sx q[15];
rz(pi/2) q[15];
sx q[16];
rz(-3.043378730133746) q[16];
sx q[16];
cx q[0],q[16];
cx q[1],q[16];
cx q[2],q[16];
cx q[3],q[16];
cx q[4],q[16];
cx q[5],q[16];
cx q[6],q[16];
cx q[7],q[16];
cx q[8],q[16];
cx q[9],q[16];
cx q[10],q[16];
cx q[11],q[16];
cx q[12],q[16];
cx q[13],q[16];
cx q[14],q[16];
cx q[15],q[16];
rz(pi/2) q[16];
sx q[16];
rz(pi/2) q[16];
sx q[17];
rz(2.7163702298208205) q[17];
sx q[17];
cx q[0],q[17];
sx q[0];
rz(1.1239940307323995) q[0];
sx q[0];
rz(-pi) q[0];
cx q[1],q[17];
sx q[1];
rz(-2.7111930923930068) q[1];
sx q[1];
cx q[0],q[1];
rz(pi/2) q[1];
sx q[1];
rz(pi/2) q[1];
cx q[2],q[17];
sx q[2];
rz(-0.352639452045338) q[2];
sx q[2];
cx q[0],q[2];
cx q[1],q[2];
rz(pi/2) q[2];
sx q[2];
rz(pi/2) q[2];
cx q[3],q[17];
sx q[3];
rz(-0.07200745118004548) q[3];
sx q[3];
cx q[0],q[3];
cx q[1],q[3];
cx q[2],q[3];
rz(pi/2) q[3];
sx q[3];
rz(pi/2) q[3];
cx q[4],q[17];
sx q[4];
rz(-0.06266637297114208) q[4];
sx q[4];
cx q[0],q[4];
cx q[1],q[4];
cx q[2],q[4];
cx q[3],q[4];
rz(pi/2) q[4];
sx q[4];
rz(pi/2) q[4];
cx q[5],q[17];
sx q[5];
rz(-0.3095979888950957) q[5];
sx q[5];
cx q[0],q[5];
cx q[1],q[5];
cx q[2],q[5];
cx q[3],q[5];
cx q[4],q[5];
rz(pi/2) q[5];
sx q[5];
rz(pi/2) q[5];
cx q[6],q[17];
sx q[6];
rz(0.13699763519090435) q[6];
sx q[6];
cx q[0],q[6];
cx q[1],q[6];
cx q[2],q[6];
cx q[3],q[6];
cx q[4],q[6];
cx q[5],q[6];
rz(pi/2) q[6];
sx q[6];
rz(pi/2) q[6];
cx q[7],q[17];
sx q[7];
rz(2.9053249563952503) q[7];
sx q[7];
cx q[0],q[7];
cx q[1],q[7];
cx q[2],q[7];
cx q[3],q[7];
cx q[4],q[7];
cx q[5],q[7];
cx q[6],q[7];
rz(pi/2) q[7];
sx q[7];
rz(pi/2) q[7];
cx q[8],q[17];
sx q[8];
rz(1.1436092750019817) q[8];
sx q[8];
cx q[0],q[8];
cx q[1],q[8];
cx q[2],q[8];
cx q[3],q[8];
cx q[4],q[8];
cx q[5],q[8];
cx q[6],q[8];
cx q[7],q[8];
rz(pi/2) q[8];
sx q[8];
rz(pi/2) q[8];
cx q[9],q[17];
cx q[10],q[17];
sx q[10];
rz(2.2663897117979994) q[10];
sx q[10];
cx q[11],q[17];
sx q[11];
rz(-3.000880106438469) q[11];
sx q[11];
cx q[12],q[17];
sx q[12];
rz(2.8245327178329855) q[12];
sx q[12];
cx q[13],q[17];
sx q[13];
rz(2.390894747770342) q[13];
sx q[13];
cx q[14],q[17];
sx q[14];
rz(1.6417312848495627) q[14];
sx q[14];
cx q[15],q[17];
sx q[15];
rz(-1.4610221534740688) q[15];
sx q[15];
cx q[16],q[17];
sx q[16];
rz(1.7981030529211255) q[16];
sx q[16];
rz(-pi) q[17];
sx q[17];
rz(2.7510468432797914) q[17];
sx q[17];
sx q[9];
rz(-0.2266724408662224) q[9];
sx q[9];
cx q[0],q[9];
cx q[0],q[10];
cx q[0],q[11];
cx q[0],q[12];
cx q[0],q[13];
cx q[0],q[14];
cx q[0],q[15];
cx q[0],q[16];
cx q[0],q[17];
sx q[0];
rz(1.4435558418894416) q[0];
sx q[0];
rz(-pi) q[0];
cx q[1],q[9];
cx q[1],q[10];
cx q[1],q[11];
cx q[1],q[12];
cx q[1],q[13];
cx q[1],q[14];
cx q[1],q[15];
cx q[1],q[16];
cx q[1],q[17];
sx q[1];
rz(0.504740529067671) q[1];
sx q[1];
cx q[0],q[1];
rz(pi/2) q[1];
sx q[1];
rz(-pi/2) q[1];
cx q[2],q[9];
cx q[2],q[10];
cx q[2],q[11];
cx q[2],q[12];
cx q[2],q[13];
cx q[2],q[14];
cx q[2],q[15];
cx q[2],q[16];
cx q[2],q[17];
sx q[2];
rz(-1.5893639704078968) q[2];
sx q[2];
cx q[0],q[2];
cx q[1],q[2];
rz(pi/2) q[2];
sx q[2];
rz(-pi/2) q[2];
cx q[3],q[9];
cx q[3],q[10];
cx q[3],q[11];
cx q[3],q[12];
cx q[3],q[13];
cx q[3],q[14];
cx q[3],q[15];
cx q[3],q[16];
cx q[3],q[17];
sx q[3];
rz(-3.1243564687891023) q[3];
sx q[3];
cx q[0],q[3];
cx q[1],q[3];
cx q[2],q[3];
rz(pi/2) q[3];
sx q[3];
rz(-pi/2) q[3];
cx q[4],q[9];
cx q[4],q[10];
cx q[4],q[11];
cx q[4],q[12];
cx q[4],q[13];
cx q[4],q[14];
cx q[4],q[15];
cx q[4],q[16];
cx q[4],q[17];
sx q[4];
rz(-2.2931058791417165) q[4];
sx q[4];
cx q[0],q[4];
cx q[1],q[4];
cx q[2],q[4];
cx q[3],q[4];
rz(pi/2) q[4];
sx q[4];
rz(-pi/2) q[4];
cx q[5],q[9];
cx q[5],q[10];
cx q[5],q[11];
cx q[5],q[12];
cx q[5],q[13];
cx q[5],q[14];
cx q[5],q[15];
cx q[5],q[16];
cx q[5],q[17];
sx q[5];
rz(-0.7589969911989876) q[5];
sx q[5];
cx q[0],q[5];
cx q[1],q[5];
cx q[2],q[5];
cx q[3],q[5];
cx q[4],q[5];
rz(pi/2) q[5];
sx q[5];
rz(pi/2) q[5];
cx q[6],q[9];
cx q[6],q[10];
cx q[6],q[11];
cx q[6],q[12];
cx q[6],q[13];
cx q[6],q[14];
cx q[6],q[15];
cx q[6],q[16];
cx q[6],q[17];
sx q[6];
rz(-2.0634266564901065) q[6];
sx q[6];
cx q[0],q[6];
cx q[1],q[6];
cx q[2],q[6];
cx q[3],q[6];
cx q[4],q[6];
cx q[5],q[6];
rz(pi/2) q[6];
sx q[6];
rz(-pi/2) q[6];
cx q[7],q[9];
cx q[7],q[10];
cx q[7],q[11];
cx q[7],q[12];
cx q[7],q[13];
cx q[7],q[14];
cx q[7],q[15];
cx q[7],q[16];
cx q[7],q[17];
sx q[7];
rz(2.745902023872331) q[7];
sx q[7];
cx q[0],q[7];
cx q[1],q[7];
cx q[2],q[7];
cx q[3],q[7];
cx q[4],q[7];
cx q[5],q[7];
cx q[6],q[7];
rz(pi/2) q[7];
sx q[7];
rz(-pi/2) q[7];
cx q[8],q[9];
cx q[8],q[10];
cx q[8],q[11];
cx q[8],q[12];
cx q[8],q[13];
cx q[8],q[14];
cx q[8],q[15];
cx q[8],q[16];
cx q[8],q[17];
sx q[8];
rz(-0.1742359191033973) q[8];
sx q[8];
cx q[0],q[8];
cx q[1],q[8];
cx q[2],q[8];
cx q[3],q[8];
cx q[4],q[8];
cx q[5],q[8];
cx q[6],q[8];
cx q[7],q[8];
rz(pi/2) q[8];
sx q[8];
rz(pi/2) q[8];
rz(pi/2) q[9];
sx q[9];
rz(pi/2) q[9];
cx q[9],q[10];
rz(pi/2) q[10];
sx q[10];
rz(pi/2) q[10];
cx q[9],q[11];
cx q[10],q[11];
rz(pi/2) q[11];
sx q[11];
rz(pi/2) q[11];
cx q[9],q[12];
cx q[10],q[12];
cx q[11],q[12];
rz(pi/2) q[12];
sx q[12];
rz(pi/2) q[12];
cx q[9],q[13];
cx q[10],q[13];
cx q[11],q[13];
cx q[12],q[13];
rz(pi/2) q[13];
sx q[13];
rz(pi/2) q[13];
cx q[9],q[14];
cx q[10],q[14];
cx q[11],q[14];
cx q[12],q[14];
cx q[13],q[14];
rz(pi/2) q[14];
sx q[14];
rz(pi/2) q[14];
cx q[9],q[15];
cx q[10],q[15];
cx q[11],q[15];
cx q[12],q[15];
cx q[13],q[15];
cx q[14],q[15];
rz(pi/2) q[15];
sx q[15];
rz(pi/2) q[15];
cx q[9],q[16];
cx q[10],q[16];
cx q[11],q[16];
cx q[12],q[16];
cx q[13],q[16];
cx q[14],q[16];
cx q[15],q[16];
rz(pi/2) q[16];
sx q[16];
rz(pi/2) q[16];
cx q[9],q[17];
cx q[10],q[17];
sx q[10];
rz(-2.8234789525460293) q[10];
sx q[10];
cx q[11],q[17];
sx q[11];
rz(-3.1334111895865346) q[11];
sx q[11];
cx q[12],q[17];
sx q[12];
rz(-0.015270951401415545) q[12];
sx q[12];
cx q[13],q[17];
sx q[13];
rz(-0.012104492504146691) q[13];
sx q[13];
cx q[14],q[17];
sx q[14];
rz(-0.4128155297643392) q[14];
sx q[14];
cx q[15],q[17];
sx q[15];
rz(2.483056942536612) q[15];
sx q[15];
cx q[16],q[17];
sx q[16];
rz(1.2935929527978338) q[16];
sx q[16];
rz(-pi) q[17];
sx q[17];
rz(1.3094309151633912) q[17];
sx q[17];
sx q[9];
rz(-0.9967904584641936) q[9];
sx q[9];
cx q[0],q[9];
cx q[0],q[10];
cx q[0],q[11];
cx q[0],q[12];
cx q[0],q[13];
cx q[0],q[14];
cx q[0],q[15];
cx q[0],q[16];
cx q[0],q[17];
sx q[0];
rz(0.6228028261533924) q[0];
sx q[0];
rz(-pi) q[0];
cx q[1],q[9];
cx q[1],q[10];
cx q[1],q[11];
cx q[1],q[12];
cx q[1],q[13];
cx q[1],q[14];
cx q[1],q[15];
cx q[1],q[16];
cx q[1],q[17];
sx q[1];
rz(2.5625024488911468) q[1];
sx q[1];
cx q[2],q[9];
cx q[2],q[10];
cx q[2],q[11];
cx q[2],q[12];
cx q[2],q[13];
cx q[2],q[14];
cx q[2],q[15];
cx q[2],q[16];
cx q[2],q[17];
sx q[2];
rz(2.5641162727818845) q[2];
sx q[2];
cx q[3],q[9];
cx q[3],q[10];
cx q[3],q[11];
cx q[3],q[12];
cx q[3],q[13];
cx q[3],q[14];
cx q[3],q[15];
cx q[3],q[16];
cx q[3],q[17];
sx q[3];
rz(0.36518255864478455) q[3];
sx q[3];
cx q[4],q[9];
cx q[4],q[10];
cx q[4],q[11];
cx q[4],q[12];
cx q[4],q[13];
cx q[4],q[14];
cx q[4],q[15];
cx q[4],q[16];
cx q[4],q[17];
sx q[4];
rz(1.9521270536000719) q[4];
sx q[4];
cx q[5],q[9];
cx q[5],q[10];
cx q[5],q[11];
cx q[5],q[12];
cx q[5],q[13];
cx q[5],q[14];
cx q[5],q[15];
cx q[5],q[16];
cx q[5],q[17];
sx q[5];
rz(1.0751214710385355) q[5];
sx q[5];
rz(-pi) q[5];
cx q[6],q[9];
cx q[6],q[10];
cx q[6],q[11];
cx q[6],q[12];
cx q[6],q[13];
cx q[6],q[14];
cx q[6],q[15];
cx q[6],q[16];
cx q[6],q[17];
sx q[6];
rz(2.0978956535042954) q[6];
sx q[6];
cx q[7],q[9];
cx q[7],q[10];
cx q[7],q[11];
cx q[7],q[12];
cx q[7],q[13];
cx q[7],q[14];
cx q[7],q[15];
cx q[7],q[16];
cx q[7],q[17];
sx q[7];
rz(1.5585736560278836) q[7];
sx q[7];
cx q[8],q[9];
cx q[8],q[10];
cx q[8],q[11];
cx q[8],q[12];
cx q[8],q[13];
cx q[8],q[14];
cx q[8],q[15];
cx q[8],q[16];
cx q[8],q[17];
sx q[8];
rz(2.7413053767650544) q[8];
sx q[8];
rz(-pi) q[8];
rz(pi/2) q[9];
sx q[9];
rz(pi/2) q[9];
cx q[9],q[10];
rz(pi/2) q[10];
sx q[10];
rz(pi/2) q[10];
cx q[9],q[11];
cx q[10],q[11];
rz(pi/2) q[11];
sx q[11];
rz(-pi/2) q[11];
cx q[9],q[12];
cx q[10],q[12];
cx q[11],q[12];
rz(pi/2) q[12];
sx q[12];
rz(pi/2) q[12];
cx q[9],q[13];
cx q[10],q[13];
cx q[11],q[13];
cx q[12],q[13];
rz(pi/2) q[13];
sx q[13];
rz(pi/2) q[13];
cx q[9],q[14];
cx q[10],q[14];
cx q[11],q[14];
cx q[12],q[14];
cx q[13],q[14];
rz(pi/2) q[14];
sx q[14];
rz(pi/2) q[14];
cx q[9],q[15];
cx q[10],q[15];
cx q[11],q[15];
cx q[12],q[15];
cx q[13],q[15];
cx q[14],q[15];
rz(pi/2) q[15];
sx q[15];
rz(pi/2) q[15];
cx q[9],q[16];
cx q[10],q[16];
cx q[11],q[16];
cx q[12],q[16];
cx q[13],q[16];
cx q[14],q[16];
cx q[15],q[16];
rz(pi/2) q[16];
sx q[16];
rz(-pi/2) q[16];
cx q[9],q[17];
cx q[10],q[17];
sx q[10];
rz(1.086264259592447) q[10];
sx q[10];
rz(-pi) q[10];
cx q[11],q[17];
sx q[11];
rz(1.7670692302914892) q[11];
sx q[11];
cx q[12],q[17];
sx q[12];
rz(2.4173091626862675) q[12];
sx q[12];
rz(-pi) q[12];
cx q[13],q[17];
sx q[13];
rz(0.600589947576903) q[13];
sx q[13];
rz(-pi) q[13];
cx q[14],q[17];
sx q[14];
rz(2.391494197453694) q[14];
sx q[14];
rz(-pi) q[14];
cx q[15],q[17];
sx q[15];
rz(1.3611658306387646) q[15];
sx q[15];
rz(-pi) q[15];
cx q[16],q[17];
sx q[16];
rz(2.336778572978216) q[16];
sx q[16];
sx q[17];
rz(-0.6904329452824953) q[17];
sx q[17];
sx q[9];
rz(2.079379880052305) q[9];
sx q[9];
rz(-pi) q[9];
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

// Benchmark was created by MQT Bench on 2023-06-29
// For more information about MQT Bench, please visit https://www.cda.cit.tum.de/mqtbench/
// MQT Bench version: v1.0.0
// Qiskit version: {'qiskit-terra': '0.24.1', 'qiskit-aer': '0.12.0', 'qiskit-ignis': None, 'qiskit-ibmq-provider': '0.20.2', 'qiskit': '0.43.1', 'qiskit-nature': '0.6.2', 'qiskit-finance': '0.3.4', 'qiskit-optimization': '0.5.0', 'qiskit-machine-learning': '0.6.1'}
// Used Gate Set: ['rz', 'sx', 'x', 'cx', 'measure']

OPENQASM 2.0;
include "qelib1.inc";
qreg q[12];
creg meas[12];
rz(-pi) q[0];
sx q[0];
rz(0.5401545924684257) q[0];
sx q[0];
rz(-pi) q[1];
sx q[1];
rz(3.125679499979901) q[1];
sx q[1];
rz(-pi) q[2];
sx q[2];
rz(0.43586571514590666) q[2];
sx q[2];
sx q[3];
rz(1.7980958779133918) q[3];
sx q[3];
rz(-pi) q[3];
sx q[4];
rz(2.8000253713039776) q[4];
sx q[4];
rz(-pi) q[4];
rz(-pi) q[5];
sx q[5];
rz(2.3097571603245157) q[5];
sx q[5];
rz(-pi) q[6];
sx q[6];
rz(0.48762009054512045) q[6];
sx q[6];
rz(-pi) q[7];
sx q[7];
rz(2.594909955836723) q[7];
sx q[7];
rz(-pi) q[8];
sx q[8];
rz(3.080697794903693) q[8];
sx q[8];
rz(-pi) q[9];
sx q[9];
rz(1.4676619350043136) q[9];
sx q[9];
rz(-pi) q[10];
sx q[10];
rz(3.129884039621282) q[10];
sx q[10];
rz(-pi) q[11];
sx q[11];
rz(1.930541056271065) q[11];
sx q[11];
cx q[10],q[11];
rz(-pi) q[11];
sx q[11];
rz(2.7322762863128247) q[11];
sx q[11];
cx q[9],q[10];
sx q[10];
rz(1.9863510804198592) q[10];
sx q[10];
rz(-pi) q[10];
cx q[10],q[11];
sx q[11];
rz(3.079410428006975) q[11];
sx q[11];
rz(-pi) q[11];
cx q[8],q[9];
cx q[7],q[8];
cx q[6],q[7];
cx q[5],q[6];
cx q[4],q[5];
cx q[3],q[4];
cx q[2],q[3];
cx q[1],q[2];
cx q[0],q[1];
sx q[0];
rz(2.933069317275871) q[0];
sx q[0];
rz(-pi) q[0];
sx q[1];
rz(2.8298025499163826) q[1];
sx q[1];
rz(-pi) q[1];
rz(-pi) q[2];
sx q[2];
rz(1.575661374861177) q[2];
sx q[2];
rz(-pi) q[3];
sx q[3];
rz(1.5034857722930397) q[3];
sx q[3];
sx q[4];
rz(1.6933824620677083) q[4];
sx q[4];
rz(-pi) q[4];
rz(-pi) q[5];
sx q[5];
rz(1.6411018997150055) q[5];
sx q[5];
sx q[6];
rz(1.6238652031650815) q[6];
sx q[6];
rz(-pi) q[6];
rz(-pi) q[7];
sx q[7];
rz(1.5264219458437527) q[7];
sx q[7];
rz(-pi) q[8];
sx q[8];
rz(1.698449623361176) q[8];
sx q[8];
rz(-pi) q[9];
sx q[9];
rz(1.1365429112014604) q[9];
sx q[9];
cx q[9],q[10];
sx q[10];
rz(1.7427249802045743) q[10];
sx q[10];
rz(-pi) q[10];
cx q[10],q[11];
sx q[11];
rz(1.5536074058873375) q[11];
sx q[11];
rz(-pi) q[11];
cx q[8],q[9];
cx q[7],q[8];
cx q[6],q[7];
cx q[5],q[6];
cx q[4],q[5];
cx q[3],q[4];
cx q[2],q[3];
cx q[1],q[2];
cx q[0],q[1];
sx q[0];
rz(2.6789722254098978) q[0];
sx q[0];
rz(-pi) q[0];
sx q[1];
rz(1.8197074570085139) q[1];
sx q[1];
rz(-pi) q[1];
sx q[2];
rz(3.076693370264037) q[2];
sx q[2];
rz(-pi) q[2];
rz(-pi) q[3];
sx q[3];
rz(2.663594859135193) q[3];
sx q[3];
sx q[4];
rz(1.8635782311451425) q[4];
sx q[4];
rz(-pi) q[4];
rz(-pi) q[5];
sx q[5];
rz(2.9348123867531832) q[5];
sx q[5];
sx q[6];
rz(2.0949710857434347) q[6];
sx q[6];
rz(-pi) q[6];
sx q[7];
rz(1.3087635434275011) q[7];
sx q[7];
rz(-pi) q[7];
rz(-pi) q[8];
sx q[8];
rz(1.7832423280620215) q[8];
sx q[8];
sx q[9];
rz(2.9604543567983868) q[9];
sx q[9];
rz(-pi) q[9];
cx q[9],q[10];
sx q[10];
rz(1.5429461630595807) q[10];
sx q[10];
rz(-pi) q[10];
cx q[8],q[9];
cx q[7],q[8];
cx q[6],q[7];
cx q[5],q[6];
cx q[4],q[5];
cx q[3],q[4];
cx q[2],q[3];
cx q[1],q[2];
cx q[0],q[1];
sx q[0];
rz(3.0715744308331354) q[0];
sx q[0];
rz(-pi) q[0];
sx q[1];
rz(1.6179415083092499) q[1];
sx q[1];
rz(-pi) q[1];
sx q[2];
rz(1.6316864344326625) q[2];
sx q[2];
rz(-pi) q[2];
sx q[3];
rz(1.6236593462177016) q[3];
sx q[3];
rz(-pi) q[3];
rz(-pi) q[4];
sx q[4];
rz(1.6347802144552883) q[4];
sx q[4];
rz(-pi) q[5];
sx q[5];
rz(1.6848653421315714) q[5];
sx q[5];
sx q[6];
rz(1.6013986122905575) q[6];
sx q[6];
rz(-pi) q[6];
rz(-pi) q[7];
sx q[7];
rz(1.826954518235432) q[7];
sx q[7];
rz(-pi) q[8];
sx q[8];
rz(2.8522560923218254) q[8];
sx q[8];
sx q[9];
rz(1.8099564541823403) q[9];
sx q[9];
rz(-pi) q[9];
barrier q[0],q[1],q[2],q[3],q[4],q[5],q[6],q[7],q[8],q[9],q[10],q[11];
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

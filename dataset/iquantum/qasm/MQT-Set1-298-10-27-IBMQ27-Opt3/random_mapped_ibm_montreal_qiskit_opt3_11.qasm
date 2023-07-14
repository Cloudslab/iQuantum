// Benchmark was created by MQT Bench on 2023-06-30
// For more information about MQT Bench, please visit https://www.cda.cit.tum.de/mqtbench/
// MQT Bench version: v1.0.0
// Qiskit version: {'qiskit-terra': '0.24.1', 'qiskit-aer': '0.12.0', 'qiskit-ignis': None, 'qiskit-ibmq-provider': '0.20.2', 'qiskit': '0.43.1', 'qiskit-nature': '0.6.2', 'qiskit-finance': '0.3.4', 'qiskit-optimization': '0.5.0', 'qiskit-machine-learning': '0.6.1'}
// Used Gate Set: ['rz', 'sx', 'x', 'cx', 'measure']
// Coupling List: [[0, 1], [1, 0], [1, 2], [1, 4], [2, 1], [2, 3], [3, 2], [3, 5], [4, 1], [4, 7], [5, 3], [5, 8], [6, 7], [7, 4], [7, 6], [7, 10], [8, 5], [8, 9], [8, 11], [9, 8], [10, 7], [10, 12], [11, 8], [11, 14], [12, 10], [12, 13], [12, 15], [13, 12], [13, 14], [14, 11], [14, 13], [14, 16], [15, 12], [15, 18], [16, 14], [16, 19], [17, 18], [18, 15], [18, 17], [18, 21], [19, 16], [19, 20], [19, 22], [20, 19], [21, 18], [21, 23], [22, 19], [22, 25], [23, 21], [23, 24], [24, 23], [24, 25], [25, 22], [25, 24], [25, 26], [26, 25]]

OPENQASM 2.0;
include "qelib1.inc";
qreg q[27];
creg meas[11];
rz(0.19070431485839112) q[8];
sx q[8];
rz(4.644744599946235) q[8];
sx q[8];
rz(6.372588395379228) q[8];
rz(pi/2) q[11];
rz(-3*pi/2) q[12];
sx q[12];
rz(3*pi/4) q[12];
cx q[13],q[12];
rz(-pi/4) q[12];
sx q[14];
cx q[11],q[14];
x q[11];
rz(pi/2) q[14];
sx q[14];
rz(pi/2) q[14];
cx q[15],q[12];
rz(pi/4) q[12];
cx q[13],q[12];
rz(pi/4) q[12];
sx q[12];
rz(0.9896177343255674) q[12];
cx q[13],q[14];
cx q[14],q[13];
cx q[13],q[14];
rz(-1.2895748532565854) q[13];
sx q[13];
rz(-0.8054304177673117) q[13];
sx q[13];
rz(0.19752481893727136) q[13];
cx q[12],q[13];
x q[12];
rz(1.3732715078576228) q[13];
cx q[12],q[13];
x q[12];
rz(-1.775015897723014) q[12];
rz(-0.19752481893727358) q[13];
sx q[13];
rz(-1.772291454538598) q[13];
sx q[13];
rz(3*pi/4) q[13];
rz(pi/2) q[15];
sx q[15];
rz(-1.2169407415517401) q[15];
sx q[15];
rz(-2.6748501968803566) q[15];
rz(0.5764679533660741) q[16];
rz(-pi/2) q[19];
sx q[19];
rz(pi/2) q[19];
cx q[19],q[16];
sx q[16];
rz(0.976920710654019) q[16];
sx q[16];
rz(-pi) q[16];
sx q[19];
rz(0.976920710654019) q[19];
sx q[19];
rz(-5*pi/2) q[19];
cx q[19],q[16];
rz(0.20893021003137502) q[16];
cx q[16],q[14];
rz(-pi/4) q[14];
cx q[11],q[14];
rz(pi/4) q[14];
cx q[16],q[14];
rz(-pi/4) q[14];
cx q[11],q[14];
rz(-3*pi/4) q[11];
sx q[11];
rz(-1.7097069466119796) q[11];
sx q[11];
rz(-2.014385463025575) q[11];
rz(0.7499642326161071) q[14];
sx q[14];
rz(-1.6980822945547756) q[14];
sx q[14];
rz(1.8431098909286678) q[14];
cx q[16],q[14];
cx q[14],q[16];
cx q[16],q[14];
x q[14];
rz(pi/2) q[14];
cx q[11],q[14];
x q[11];
rz(1.4332068705562455) q[14];
cx q[11],q[14];
rz(2.835592973597766) q[11];
sx q[11];
rz(-1.4318857069778126) q[11];
sx q[11];
rz(-0.5099804623877828) q[11];
rz(pi/2) q[14];
sx q[19];
rz(3.790267133313593) q[19];
rz(-pi/4) q[20];
x q[22];
rz(-0.58117859246933) q[22];
x q[25];
rz(pi/2) q[25];
cx q[22],q[25];
x q[22];
rz(pi/4) q[25];
cx q[22],q[25];
rz(-1.3665767558667774) q[22];
cx q[22],q[19];
cx q[19],q[22];
cx q[22],q[19];
cx q[20],q[19];
cx q[19],q[20];
cx q[20],q[19];
cx q[19],q[16];
cx q[16],q[19];
cx q[19],q[16];
cx q[16],q[14];
cx q[14],q[16];
cx q[16],q[14];
cx q[13],q[14];
cx q[14],q[13];
cx q[13],q[14];
cx q[11],q[14];
cx q[12],q[13];
cx q[13],q[12];
cx q[12],q[13];
rz(-0.5811785924693282) q[12];
cx q[12],q[15];
x q[12];
rz(pi/2) q[13];
sx q[13];
rz(-pi/2) q[13];
rz(pi/4) q[14];
rz(0.31865570668801046) q[15];
cx q[12],q[15];
x q[12];
rz(3.085738678382725) q[12];
rz(-pi) q[15];
cx q[16],q[14];
rz(-pi/4) q[14];
cx q[11],q[14];
sx q[11];
rz(3.725694093386519) q[11];
sx q[11];
rz(6.845052821839392) q[11];
rz(pi/4) q[14];
cx q[16],q[14];
rz(-3*pi/4) q[14];
sx q[14];
rz(-pi/4) q[14];
cx q[13],q[14];
sx q[13];
rz(-pi/2) q[13];
sx q[13];
rz(pi/2) q[14];
cx q[13],q[14];
rz(-pi) q[13];
sx q[13];
rz(-pi/4) q[13];
cx q[13],q[12];
cx q[12],q[13];
cx q[13],q[12];
rz(pi/4) q[12];
sx q[12];
rz(-2.1862760354652835) q[12];
sx q[12];
rz(1.5132165099238648) q[12];
cx q[12],q[15];
x q[12];
rz(pi/2) q[13];
sx q[13];
rz(1.7100050107244122) q[13];
rz(pi/2) q[14];
sx q[14];
rz(-pi) q[14];
rz(pi/3) q[15];
cx q[12],q[15];
rz(2.560414061120463) q[12];
sx q[12];
rz(-0.9553166181245096) q[12];
sx q[12];
rz(-pi/2) q[12];
rz(pi/2) q[15];
cx q[15],q[18];
cx q[18],q[15];
cx q[15],q[18];
cx q[18],q[21];
rz(7.415808924421599) q[20];
cx q[21],q[18];
cx q[18],q[21];
cx q[21],q[23];
cx q[22],q[19];
cx q[19],q[22];
cx q[22],q[19];
rz(-0.19330789184177855) q[19];
cx q[20],q[19];
rz(-1.9276677656101788) q[19];
sx q[19];
rz(-1.1024506576994728) q[19];
sx q[19];
cx q[20],q[19];
sx q[19];
rz(-1.1024506576994728) q[19];
sx q[19];
rz(2.9063738208494048) q[19];
cx q[23],q[21];
cx q[21],q[23];
cx q[23],q[24];
cx q[24],q[23];
cx q[23],q[24];
rz(-0.18956138720905713) q[25];
cx q[25],q[22];
cx q[22],q[25];
cx q[25],q[22];
cx q[8],q[11];
cx q[11],q[8];
cx q[8],q[11];
cx q[11],q[14];
cx q[14],q[11];
cx q[11],q[14];
cx q[14],q[16];
cx q[16],q[14];
cx q[14],q[16];
cx q[11],q[14];
cx q[14],q[11];
cx q[11],q[14];
cx q[19],q[16];
rz(2.832055090474391) q[16];
cx q[19],q[16];
rz(pi/2) q[16];
sx q[16];
rz(pi/2) q[16];
cx q[14],q[16];
cx q[16],q[14];
cx q[14],q[16];
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
rz(-0.13920868392951558) q[13];
cx q[14],q[13];
rz(pi/2) q[13];
sx q[13];
rz(-2.867723978430937) q[13];
sx q[13];
rz(-0.10247692477974901) q[13];
cx q[12],q[13];
cx q[13],q[12];
cx q[12],q[13];
rz(0.06047140188662414) q[12];
sx q[12];
rz(1.5956678467140382) q[12];
rz(-pi) q[14];
sx q[14];
rz(pi/2) q[14];
cx q[22],q[19];
cx q[19],q[22];
cx q[22],q[19];
cx q[20],q[19];
rz(-pi/4) q[19];
cx q[20],q[19];
rz(pi/4) q[19];
cx q[16],q[19];
cx q[19],q[16];
cx q[16],q[19];
cx q[14],q[16];
cx q[16],q[14];
cx q[14],q[16];
rz(-pi) q[14];
sx q[14];
rz(1.682723200684089) q[14];
sx q[14];
cx q[11],q[14];
sx q[14];
rz(1.682723200684089) q[14];
sx q[14];
rz(-pi) q[14];
cx q[11],q[14];
rz(-0.4270838211254939) q[11];
sx q[11];
rz(4.374665957334048) q[11];
sx q[11];
rz(4.734403657552383) q[11];
rz(-pi) q[14];
sx q[14];
rz(-pi) q[14];
rz(-0.4775333361308667) q[19];
cx q[20],q[19];
rz(1.5795771063356323) q[19];
sx q[19];
rz(-1.5436863927671922) q[19];
sx q[19];
cx q[20],q[19];
rz(-0.027624266635725814) q[19];
sx q[19];
rz(-1.6208077773912626) q[19];
sx q[19];
rz(-1.1017272028765195) q[19];
sx q[20];
rz(0.7911959679909875) q[20];
cx q[25],q[22];
cx q[22],q[25];
cx q[25],q[22];
cx q[22],q[19];
cx q[19],q[22];
cx q[22],q[19];
cx q[16],q[19];
cx q[19],q[16];
cx q[16],q[19];
rz(-pi) q[16];
sx q[16];
rz(pi/2) q[16];
cx q[14],q[16];
sx q[14];
x q[16];
rz(-pi/4) q[16];
cx q[19],q[20];
cx q[20],q[19];
x q[19];
rz(-2.9769629698968245) q[19];
cx q[19],q[16];
cx q[16],q[19];
cx q[19],q[16];
cx q[16],q[14];
cx q[14],q[16];
cx q[16],q[14];
cx q[14],q[13];
cx q[13],q[14];
cx q[14],q[13];
cx q[11],q[14];
rz(-1.2269689139090714) q[13];
sx q[13];
rz(pi/2) q[13];
cx q[13],q[12];
rz(-pi/2) q[12];
sx q[13];
cx q[13],q[12];
rz(0.023386227099056283) q[12];
sx q[13];
cx q[13],q[12];
rz(2.669395733060613) q[12];
sx q[12];
rz(-pi/2) q[12];
rz(-3.118206426490738) q[13];
sx q[13];
rz(-1.545924806875755) q[13];
sx q[13];
rz(-0.06047140188662503) q[13];
rz(-pi/4) q[14];
rz(pi/2) q[20];
sx q[20];
rz(-0.7008423226704128) q[20];
sx q[20];
rz(3*pi/4) q[20];
cx q[25],q[22];
cx q[22],q[25];
cx q[25],q[22];
cx q[22],q[19];
rz(pi/4) q[19];
cx q[16],q[19];
rz(pi/4) q[16];
rz(-pi/4) q[19];
sx q[22];
rz(-pi) q[22];
cx q[19],q[22];
sx q[19];
rz(-pi/2) q[19];
sx q[19];
rz(pi/2) q[22];
cx q[19],q[22];
rz(pi/2) q[19];
sx q[19];
rz(3*pi/4) q[19];
cx q[19],q[16];
rz(-pi/4) q[16];
cx q[19],q[16];
rz(-pi/2) q[22];
sx q[22];
rz(-pi/4) q[22];
sx q[22];
rz(pi/2) q[22];
cx q[22],q[19];
cx q[19],q[22];
cx q[22],q[19];
cx q[19],q[16];
cx q[16],q[14];
rz(pi/4) q[14];
cx q[11],q[14];
sx q[11];
rz(-pi) q[11];
rz(pi/4) q[14];
sx q[14];
rz(pi) q[14];
cx q[14],q[13];
cx q[13],q[14];
cx q[25],q[22];
rz(-pi/4) q[22];
cx q[19],q[22];
sx q[19];
rz(pi/2) q[19];
rz(pi/4) q[22];
cx q[25],q[22];
x q[22];
rz(-pi/4) q[22];
cx q[19],q[22];
sx q[19];
rz(-pi/2) q[19];
sx q[19];
rz(pi/2) q[22];
cx q[19],q[22];
sx q[19];
rz(-3*pi/4) q[19];
sx q[19];
rz(pi/2) q[19];
cx q[19],q[20];
sx q[19];
rz(-pi/2) q[19];
sx q[19];
rz(pi/2) q[20];
cx q[19],q[20];
sx q[19];
rz(-pi/4) q[19];
cx q[19],q[16];
cx q[16],q[19];
cx q[19],q[16];
cx q[16],q[14];
cx q[14],q[16];
cx q[16],q[14];
cx q[11],q[14];
cx q[14],q[11];
cx q[11],q[14];
cx q[14],q[16];
cx q[16],q[14];
cx q[14],q[16];
rz(-pi) q[20];
sx q[20];
rz(-pi/2) q[20];
rz(-pi) q[22];
sx q[22];
rz(-3.752590850930839) q[22];
rz(pi/4) q[25];
cx q[22],q[25];
rz(-pi/4) q[25];
cx q[22],q[25];
rz(-3*pi/2) q[25];
sx q[25];
rz(3*pi/4) q[25];
cx q[24],q[25];
rz(-pi/4) q[25];
cx q[22],q[25];
sx q[22];
rz(3.3817161945332095) q[22];
sx q[22];
rz(8.480310782014604) q[22];
cx q[19],q[22];
cx q[22],q[19];
cx q[19],q[22];
cx q[20],q[19];
cx q[19],q[20];
cx q[20],q[19];
cx q[16],q[19];
cx q[19],q[16];
cx q[16],q[19];
rz(pi/4) q[25];
cx q[24],q[25];
rz(-2.2917125381768306) q[24];
sx q[24];
rz(-2.12751441396977) q[24];
sx q[24];
rz(2.917645437737998) q[24];
rz(pi/4) q[25];
sx q[25];
rz(pi/2) q[25];
cx q[22],q[25];
cx q[25],q[22];
rz(-pi) q[22];
sx q[22];
rz(3.015511188868894) q[22];
sx q[22];
cx q[19],q[22];
cx q[22],q[19];
cx q[19],q[22];
rz(-pi) q[25];
sx q[25];
rz(pi/2) q[25];
cx q[24],q[25];
sx q[24];
x q[25];
rz(-pi/4) q[25];
cx q[22],q[25];
rz(-pi) q[22];
sx q[22];
rz(-pi/2) q[22];
rz(pi/4) q[25];
cx q[24],q[25];
rz(pi/4) q[24];
x q[25];
rz(-pi/4) q[25];
cx q[22],q[25];
sx q[22];
rz(-pi/2) q[22];
sx q[22];
rz(pi/2) q[25];
cx q[22],q[25];
sx q[22];
rz(-3*pi/4) q[22];
sx q[22];
rz(-pi/2) q[22];
sx q[25];
rz(-pi/4) q[25];
cx q[25],q[24];
rz(-pi/4) q[24];
cx q[25],q[24];
cx q[22],q[25];
rz(-pi/2) q[24];
sx q[24];
rz(-pi) q[24];
cx q[25],q[22];
cx q[22],q[25];
rz(0.7191456665618805) q[25];
sx q[25];
rz(-2.1348665871410395) q[25];
sx q[25];
rz(pi/2) q[25];
cx q[24],q[25];
sx q[24];
rz(-1.006726066448754) q[24];
sx q[24];
rz(pi/2) q[25];
cx q[24],q[25];
x q[24];
rz(pi/4) q[24];
rz(-1.0182237264750422) q[25];
sx q[25];
cx q[8],q[11];
rz(pi/4) q[11];
cx q[11],q[14];
cx q[14],q[11];
cx q[11],q[14];
sx q[11];
rz(-0.8955711562849213) q[11];
sx q[11];
cx q[16],q[14];
rz(pi/4) q[14];
sx q[14];
rz(1.1439190345014794) q[14];
cx q[14],q[13];
cx q[13],q[14];
cx q[14],q[13];
cx q[12],q[13];
sx q[12];
rz(2.7727035557156112) q[12];
sx q[12];
rz(-pi/2) q[12];
sx q[13];
rz(2.7727035557156112) q[13];
sx q[13];
rz(-pi) q[13];
cx q[12],q[13];
sx q[12];
rz(2.11510576954997) q[12];
rz(0.4268772922934172) q[13];
cx q[13],q[14];
cx q[14],q[13];
cx q[13],q[14];
rz(-0.7404047177025498) q[13];
cx q[12],q[13];
rz(1.026486884039823) q[13];
sx q[13];
rz(-1.5702357928061037) q[13];
sx q[13];
cx q[12],q[13];
sx q[12];
rz(-2.0121369495250754) q[12];
sx q[13];
rz(-1.5702357928061046) q[13];
sx q[13];
rz(-0.28608216633727324) q[13];
rz(pi/2) q[14];
sx q[14];
rz(-2.5957971382615685) q[14];
sx q[14];
rz(-pi/2) q[14];
cx q[11],q[14];
rz(-2.791298688709783) q[11];
sx q[11];
rz(-pi/2) q[11];
rz(-pi/2) q[14];
sx q[14];
rz(-0.5457955153282263) q[14];
sx q[14];
rz(pi/2) q[14];
rz(-pi/2) q[16];
rz(pi/2) q[8];
cx q[8],q[11];
cx q[11],q[8];
cx q[8],q[11];
cx q[11],q[14];
cx q[14],q[11];
cx q[11],q[14];
cx q[14],q[16];
cx q[16],q[14];
cx q[14],q[16];
rz(pi/2) q[14];
sx q[14];
rz(-1.6359946673168242) q[14];
cx q[19],q[16];
rz(2.357949790203161) q[16];
x q[19];
rz(2.0142025399601344) q[19];
cx q[22],q[19];
cx q[19],q[22];
cx q[22],q[19];
cx q[19],q[16];
cx q[16],q[19];
cx q[19],q[16];
cx q[16],q[14];
rz(-3.076394313067866) q[14];
cx q[16],q[14];
rz(pi/2) q[14];
sx q[14];
rz(pi/2) q[14];
cx q[13],q[14];
cx q[14],q[13];
rz(-pi/2) q[13];
sx q[13];
rz(pi/2) q[13];
sx q[14];
rz(-pi) q[16];
x q[16];
cx q[16],q[14];
cx q[14],q[16];
cx q[16],q[14];
cx q[14],q[13];
cx q[13],q[14];
cx q[14],q[13];
rz(-1.129455704064719) q[13];
sx q[13];
rz(-pi) q[13];
cx q[12],q[13];
sx q[12];
rz(-pi/2) q[12];
sx q[12];
rz(pi/2) q[13];
cx q[12],q[13];
rz(-0.7322450492854728) q[12];
sx q[12];
rz(-2.3889026441313366) q[12];
sx q[12];
rz(-0.27560664556104486) q[12];
rz(-pi/2) q[13];
sx q[13];
rz(0.1914073130797661) q[13];
cx q[19],q[16];
rz(pi/2) q[16];
sx q[16];
rz(pi/2) q[16];
cx q[14],q[16];
cx q[16],q[14];
cx q[14],q[16];
cx q[11],q[14];
cx q[14],q[11];
cx q[11],q[14];
rz(0.782469416862142) q[14];
sx q[14];
x q[19];
rz(3.0806758270694843) q[22];
sx q[22];
rz(-pi/2) q[22];
cx q[25],q[22];
rz(0.06642497305503524) q[22];
sx q[25];
cx q[25],q[22];
rz(0.06642497305503528) q[22];
sx q[25];
cx q[25],q[22];
rz(-pi/2) q[22];
sx q[22];
rz(-1.5098795002745904) q[22];
sx q[22];
rz(3*pi/4) q[22];
cx q[19],q[22];
cx q[20],q[19];
cx q[19],q[20];
cx q[20],q[19];
cx q[16],q[19];
sx q[16];
rz(2.719855579682376) q[16];
sx q[16];
rz(-pi/2) q[16];
sx q[19];
rz(2.719855579682376) q[19];
sx q[19];
rz(-pi) q[19];
cx q[16],q[19];
sx q[16];
rz(-2.7642739604243403) q[16];
sx q[16];
rz(-pi/2) q[16];
cx q[16],q[14];
rz(pi/2) q[14];
sx q[16];
cx q[16],q[14];
rz(pi/4) q[14];
sx q[16];
cx q[16],q[14];
x q[14];
rz(0.40807947023199453) q[14];
cx q[13],q[14];
rz(3.1323771420116753) q[14];
sx q[14];
rz(-0.899879943122837) q[14];
sx q[14];
rz(-0.09056860318643167) q[14];
cx q[11],q[14];
cx q[14],q[11];
cx q[11],q[14];
x q[16];
rz(1.573725073330201) q[16];
rz(-2.157692984453154) q[19];
rz(-1.3002439672064061) q[20];
sx q[20];
rz(-pi) q[20];
rz(pi/4) q[22];
sx q[22];
rz(pi/2) q[22];
cx q[19],q[22];
rz(pi/4) q[22];
rz(-pi) q[25];
sx q[25];
rz(1.1883069401499657) q[25];
cx q[25],q[22];
rz(-pi/4) q[22];
cx q[19],q[22];
cx q[19],q[16];
rz(-pi/4) q[16];
cx q[14],q[16];
rz(pi/4) q[16];
cx q[19],q[16];
rz(-pi/4) q[16];
cx q[14],q[16];
rz(3*pi/4) q[16];
sx q[16];
rz(pi/2) q[16];
cx q[19],q[16];
cx q[16],q[19];
cx q[19],q[16];
cx q[14],q[16];
rz(pi/4) q[14];
rz(-pi/4) q[16];
cx q[14],q[16];
cx q[13],q[14];
cx q[12],q[13];
rz(2.3556832399656393) q[13];
sx q[13];
rz(-1.7508969099362854) q[13];
sx q[13];
cx q[12],q[13];
rz(1.4650424601818184) q[13];
sx q[13];
rz(-1.0454005516295855) q[13];
sx q[13];
rz(-4.920952705503518) q[13];
rz(2.991912323865222) q[14];
cx q[13],q[14];
sx q[13];
rz(0.9859398947998486) q[13];
sx q[13];
sx q[14];
rz(0.9859398947998486) q[14];
sx q[14];
rz(-pi) q[14];
cx q[13],q[14];
rz(-3*pi/2) q[13];
sx q[13];
rz(-pi/2) q[13];
rz(1.9850849101278456) q[14];
cx q[11],q[14];
cx q[14],q[11];
cx q[11],q[14];
rz(pi/4) q[16];
cx q[16],q[14];
cx q[14],q[16];
rz(pi/4) q[22];
cx q[25],q[22];
rz(pi/4) q[22];
sx q[22];
rz(3*pi/4) q[22];
cx q[22],q[19];
cx q[19],q[22];
cx q[22],q[19];
rz(-pi) q[19];
cx q[19],q[20];
sx q[19];
rz(-pi/2) q[19];
sx q[19];
rz(pi/2) q[20];
cx q[19],q[20];
rz(-pi/2) q[19];
sx q[19];
rz(-1.3002439672064074) q[19];
sx q[19];
rz(-pi/4) q[19];
rz(-pi/2) q[20];
sx q[20];
rz(pi/4) q[20];
sx q[20];
rz(-pi) q[22];
sx q[22];
rz(pi/2) q[22];
sx q[25];
rz(pi/4) q[25];
cx q[24],q[25];
cx q[25],q[24];
cx q[24],q[25];
x q[25];
rz(pi/2) q[25];
cx q[22],q[25];
sx q[22];
rz(-pi/2) q[22];
sx q[22];
rz(pi/2) q[25];
cx q[22],q[25];
sx q[22];
rz(-pi/4) q[22];
rz(-pi) q[25];
sx q[25];
rz(0.7585572916572705) q[25];
cx q[8],q[11];
cx q[11],q[8];
cx q[8],q[11];
cx q[11],q[14];
cx q[14],q[11];
cx q[11],q[14];
cx q[14],q[16];
cx q[16],q[14];
cx q[14],q[16];
rz(-pi) q[14];
sx q[14];
cx q[16],q[19];
rz(-pi/2) q[16];
sx q[16];
rz(-pi/2) q[16];
rz(-pi/4) q[19];
cx q[20],q[19];
x q[19];
rz(pi/4) q[19];
cx q[16],q[19];
sx q[16];
rz(-pi/2) q[16];
sx q[16];
rz(pi/2) q[19];
cx q[16],q[19];
rz(2.0155884704633937) q[16];
sx q[16];
rz(-1.9164441209042309) q[16];
sx q[16];
rz(2.847609438674361) q[16];
sx q[19];
cx q[19],q[22];
cx q[16],q[19];
rz(-1.418074293577401) q[19];
cx q[16],q[19];
sx q[16];
rz(-pi/2) q[16];
cx q[14],q[16];
sx q[14];
rz(-2.0350953284362747) q[14];
cx q[11],q[14];
cx q[14],q[11];
cx q[11],q[14];
rz(3*pi/4) q[16];
cx q[14],q[16];
cx q[16],q[14];
cx q[14],q[16];
rz(pi/4) q[16];
rz(1.418074293577401) q[19];
sx q[20];
rz(-2.848583709013857) q[20];
sx q[20];
rz(-1.1213098420439458) q[20];
rz(pi/4) q[22];
cx q[25],q[22];
rz(pi/4) q[22];
sx q[22];
rz(pi/2) q[22];
cx q[19],q[22];
cx q[22],q[19];
cx q[19],q[22];
cx q[19],q[20];
rz(-0.4494864847509511) q[20];
cx q[19],q[20];
rz(pi/2) q[19];
sx q[19];
rz(pi/2) q[19];
cx q[16],q[19];
rz(-pi/4) q[19];
cx q[16],q[19];
cx q[16],q[14];
rz(pi/4) q[14];
cx q[11],q[14];
rz(pi/4) q[11];
rz(-pi/4) q[14];
cx q[16],q[14];
rz(3*pi/4) q[14];
sx q[14];
rz(pi/2) q[14];
cx q[13],q[14];
cx q[14],q[13];
cx q[13],q[14];
cx q[14],q[16];
cx q[16],q[14];
cx q[14],q[16];
cx q[14],q[11];
rz(-pi/4) q[11];
rz(pi/4) q[14];
cx q[14],q[11];
cx q[13],q[14];
cx q[14],q[13];
cx q[13],q[14];
cx q[14],q[11];
rz(-pi) q[11];
x q[11];
rz(-1.9107192835882616) q[14];
sx q[14];
cx q[13],q[14];
sx q[13];
rz(-pi/2) q[13];
sx q[13];
rz(pi/2) q[14];
cx q[13],q[14];
rz(-pi/2) q[13];
sx q[13];
rz(0.3399229567933646) q[13];
rz(pi/2) q[14];
sx q[14];
rz(-pi/4) q[14];
rz(3*pi/4) q[19];
sx q[19];
rz(pi/2) q[19];
rz(-pi/2) q[22];
sx q[22];
cx q[24],q[25];
cx q[25],q[24];
cx q[24],q[25];
cx q[24],q[23];
cx q[23],q[24];
cx q[24],q[23];
cx q[23],q[21];
cx q[21],q[23];
cx q[23],q[21];
cx q[21],q[18];
cx q[18],q[21];
cx q[21],q[18];
rz(pi/2) q[18];
sx q[18];
rz(pi/2) q[18];
rz(pi/4) q[25];
sx q[25];
rz(-pi/2) q[25];
cx q[25],q[22];
rz(-pi/2) q[22];
sx q[25];
cx q[25],q[22];
rz(pi/4) q[22];
sx q[25];
cx q[25],q[22];
rz(-pi) q[22];
cx q[22],q[19];
cx q[19],q[22];
cx q[22],q[19];
x q[19];
rz(-pi/2) q[19];
cx q[16],q[19];
sx q[16];
rz(pi/4) q[16];
x q[19];
rz(-pi/2) q[19];
cx q[22],q[19];
cx q[19],q[22];
cx q[22],q[19];
cx q[19],q[16];
rz(pi/4) q[16];
cx q[20],q[19];
cx q[19],q[20];
cx q[20],q[19];
cx q[19],q[16];
cx q[16],q[19];
cx q[19],q[16];
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
cx q[13],q[14];
cx q[14],q[13];
cx q[13],q[14];
rz(-3*pi/4) q[14];
cx q[15],q[12];
cx q[12],q[15];
cx q[15],q[18];
rz(pi/2) q[15];
cx q[15],q[12];
cx q[12],q[15];
cx q[15],q[12];
rz(pi/2) q[18];
sx q[18];
rz(-pi) q[18];
cx q[18],q[21];
cx q[21],q[18];
cx q[18],q[21];
cx q[21],q[23];
cx q[22],q[19];
rz(-pi/4) q[19];
cx q[20],q[19];
rz(3*pi/4) q[19];
sx q[19];
rz(pi/2) q[19];
rz(pi/4) q[22];
cx q[22],q[19];
cx q[19],q[22];
cx q[22],q[19];
cx q[20],q[19];
rz(-pi/4) q[19];
rz(pi/4) q[20];
cx q[20],q[19];
cx q[22],q[19];
cx q[19],q[20];
cx q[20],q[19];
rz(pi/2) q[19];
sx q[19];
x q[20];
rz(-2.030179244887462) q[20];
cx q[22],q[19];
cx q[19],q[22];
cx q[22],q[19];
rz(pi/4) q[19];
cx q[23],q[21];
cx q[21],q[23];
cx q[23],q[24];
cx q[24],q[23];
cx q[23],q[24];
rz(3.372331830377817) q[24];
rz(-1.2710580017878605) q[25];
cx q[24],q[25];
rz(0.5546589866094243) q[25];
sx q[25];
rz(-2.3524567206350033) q[25];
sx q[25];
cx q[24],q[25];
sx q[25];
rz(-2.3524567206350033) q[25];
sx q[25];
rz(2.3822704451709633) q[25];
cx q[8],q[11];
cx q[11],q[8];
cx q[8],q[11];
cx q[14],q[11];
rz(-pi/4) q[11];
cx q[14],q[11];
rz(3*pi/4) q[11];
sx q[11];
rz(pi/2) q[11];
sx q[14];
rz(pi/2) q[14];
cx q[14],q[13];
cx q[13],q[14];
cx q[14],q[13];
cx q[13],q[12];
cx q[12],q[13];
rz(-3*pi/2) q[12];
sx q[12];
rz(2.1664632933694206) q[12];
cx q[13],q[14];
cx q[14],q[13];
cx q[13],q[14];
cx q[14],q[16];
cx q[13],q[14];
cx q[14],q[13];
cx q[13],q[14];
rz(pi/4) q[16];
cx q[14],q[16];
rz(pi/4) q[14];
cx q[13],q[14];
cx q[14],q[13];
cx q[13],q[14];
rz(-pi/4) q[16];
cx q[14],q[16];
cx q[14],q[13];
rz(-pi/4) q[13];
rz(3*pi/4) q[14];
cx q[14],q[13];
sx q[14];
rz(pi/2) q[14];
rz(5*pi/16) q[16];
cx q[16],q[14];
rz(-pi/16) q[14];
cx q[16],q[14];
rz(pi/16) q[14];
cx q[13],q[14];
cx q[14],q[13];
cx q[13],q[14];
cx q[16],q[14];
rz(-pi/16) q[14];
cx q[14],q[13];
rz(pi/16) q[13];
cx q[14],q[13];
rz(-pi/16) q[13];
cx q[16],q[14];
rz(pi/16) q[14];
cx q[14],q[13];
rz(-pi/16) q[13];
cx q[14],q[13];
rz(pi/16) q[13];
cx q[12],q[13];
cx q[13],q[12];
cx q[12],q[13];
cx q[14],q[13];
rz(-pi/16) q[13];
cx q[13],q[12];
rz(pi/16) q[12];
cx q[13],q[12];
rz(-pi/16) q[12];
cx q[16],q[14];
cx q[14],q[16];
cx q[16],q[14];
cx q[14],q[13];
rz(pi/16) q[13];
cx q[13],q[12];
rz(-pi/16) q[12];
cx q[13],q[12];
rz(pi/16) q[12];
cx q[16],q[14];
cx q[14],q[16];
cx q[16],q[14];
cx q[14],q[13];
rz(-pi/16) q[13];
cx q[13],q[12];
rz(pi/16) q[12];
cx q[13],q[12];
rz(-pi/16) q[12];
cx q[16],q[14];
cx q[14],q[16];
cx q[16],q[14];
cx q[14],q[13];
cx q[11],q[14];
rz(pi/16) q[13];
cx q[13],q[12];
rz(-pi/16) q[12];
cx q[13],q[12];
rz(9*pi/16) q[12];
sx q[12];
rz(pi/2) q[12];
cx q[14],q[11];
cx q[11],q[14];
cx q[14],q[16];
cx q[16],q[14];
cx q[14],q[16];
cx q[19],q[16];
rz(-pi/4) q[16];
cx q[19],q[16];
rz(3*pi/4) q[16];
sx q[16];
rz(pi/2) q[16];
cx q[16],q[14];
cx q[14],q[16];
cx q[16],q[14];
x q[19];
rz(-0.5811785924693282) q[19];
cx q[19],q[20];
x q[19];
rz(pi/4) q[20];
cx q[19],q[20];
rz(-1.3665767558667774) q[19];
cx q[19],q[22];
rz(-2.81557740828491) q[20];
sx q[20];
cx q[22],q[19];
cx q[19],q[22];
cx q[20],q[19];
rz(5.057412206538706) q[19];
cx q[20],q[19];
rz(-pi) q[19];
sx q[19];
rz(-pi) q[19];
rz(-pi) q[20];
sx q[20];
rz(-pi) q[20];
cx q[22],q[25];
cx q[25],q[22];
cx q[22],q[25];
cx q[24],q[25];
rz(-pi/4) q[25];
cx q[24],q[25];
rz(pi/4) q[25];
cx q[8],q[11];
cx q[11],q[8];
cx q[8],q[11];
cx q[14],q[11];
cx q[11],q[14];
rz(1.41472006600941) q[11];
sx q[11];
rz(7.364368859255449) q[11];
sx q[14];
rz(1.0744553437070685) q[14];
sx q[14];
rz(-pi) q[14];
barrier q[12],q[16],q[24],q[11],q[19],q[14],q[22],q[25],q[20],q[8],q[13];
measure q[12] -> meas[0];
measure q[16] -> meas[1];
measure q[24] -> meas[2];
measure q[11] -> meas[3];
measure q[19] -> meas[4];
measure q[14] -> meas[5];
measure q[22] -> meas[6];
measure q[25] -> meas[7];
measure q[20] -> meas[8];
measure q[8] -> meas[9];
measure q[13] -> meas[10];

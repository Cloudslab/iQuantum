// Benchmark was created by MQT Bench on 2023-06-29
// For more information about MQT Bench, please visit https://www.cda.cit.tum.de/mqtbench/
// MQT Bench version: v1.0.0
// Qiskit version: {'qiskit-terra': '0.24.1', 'qiskit-aer': '0.12.0', 'qiskit-ignis': None, 'qiskit-ibmq-provider': '0.20.2', 'qiskit': '0.43.1', 'qiskit-nature': '0.6.2', 'qiskit-finance': '0.3.4', 'qiskit-optimization': '0.5.0', 'qiskit-machine-learning': '0.6.1'}
// Used Gate Set: ['rz', 'sx', 'x', 'cx', 'measure']

OPENQASM 2.0;
include "qelib1.inc";
qreg q[11];
creg meas[11];
rz(0.19070431485839112) q[0];
sx q[0];
rz(4.644744599946235) q[0];
sx q[0];
rz(6.372588395379228) q[0];
x q[1];
rz(-0.58117859246933) q[1];
rz(-pi/4) q[2];
x q[4];
rz(pi/2) q[4];
cx q[1],q[4];
x q[1];
rz(pi/4) q[4];
cx q[1],q[4];
rz(-1.3665767558667774) q[1];
cx q[2],q[1];
cx q[1],q[2];
cx q[2],q[1];
x q[1];
rz(-0.5811785924693282) q[1];
rz(7.415808924421599) q[2];
rz(-0.18956138720905713) q[4];
rz(1.7291450525092706) q[5];
sx q[5];
rz(-pi/2) q[5];
sx q[6];
rz(-2.4067759745093467) q[7];
sx q[7];
cx q[5],q[7];
sx q[5];
rz(-0.9769207106540194) q[5];
sx q[5];
rz(0.9769207106540186) q[7];
cx q[5],q[7];
rz(-pi/2) q[5];
sx q[5];
rz(0.2970178621676478) q[5];
cx q[2],q[5];
rz(-1.9276677656101788) q[5];
sx q[5];
rz(-1.1024506576994728) q[5];
sx q[5];
cx q[2],q[5];
cx q[2],q[4];
rz(-pi/4) q[4];
cx q[2],q[4];
rz(-3*pi/4) q[4];
sx q[4];
rz(1.682723200684089) q[4];
sx q[4];
sx q[5];
rz(-1.1024506576994728) q[5];
sx q[5];
rz(3.691771984246853) q[5];
cx q[5],q[0];
rz(2.832055090474391) q[0];
cx q[5],q[0];
rz(pi/2) q[0];
sx q[0];
rz(1.09326299066403) q[0];
cx q[2],q[0];
rz(1.5795771063356323) q[0];
sx q[0];
rz(-1.5436863927671922) q[0];
sx q[0];
cx q[2],q[0];
rz(-0.027624266635725814) q[0];
sx q[0];
rz(-1.6208077773912626) q[0];
sx q[0];
rz(-0.3163290394790712) q[0];
sx q[2];
rz(0.7911959679909875) q[2];
sx q[7];
rz(-0.7348166790804473) q[7];
rz(pi/2) q[9];
cx q[9],q[6];
rz(-3*pi/4) q[6];
sx q[6];
rz(-1.7722914545385962) q[6];
sx q[6];
rz(2.560414061120462) q[6];
x q[9];
rz(-3*pi/2) q[10];
sx q[10];
rz(3*pi/4) q[10];
cx q[3],q[10];
rz(-pi/4) q[10];
cx q[8],q[10];
rz(pi/4) q[10];
cx q[3],q[10];
rz(-3*pi/4) q[10];
sx q[10];
rz(-pi) q[10];
cx q[6],q[10];
rz(1.3732715078576228) q[10];
x q[6];
cx q[6],q[10];
rz(pi/4) q[10];
rz(1.1871425532628406) q[6];
sx q[6];
rz(-1.369301199051197) q[6];
sx q[6];
rz(-pi/4) q[6];
cx q[7],q[3];
rz(-pi/4) q[3];
rz(-pi/2) q[8];
sx q[8];
rz(-1.924651912038053) q[8];
sx q[8];
rz(-0.4667424567094365) q[8];
cx q[1],q[8];
x q[1];
rz(0.31865570668801046) q[8];
cx q[1],q[8];
rz(-2.7890074869406236) q[1];
sx q[1];
rz(1.7100050107244131) q[1];
x q[8];
cx q[9],q[3];
rz(pi/4) q[3];
cx q[7],q[3];
rz(-pi/4) q[3];
x q[7];
rz(-1.3665767558667783) q[7];
cx q[9],q[3];
rz(0.7499642326161071) q[3];
sx q[3];
rz(-1.6980822945547756) q[3];
sx q[3];
rz(1.8431098909286678) q[3];
rz(-3*pi/4) q[9];
sx q[9];
rz(-1.7097069466119805) q[9];
sx q[9];
rz(0.1375894562386506) q[9];
cx q[7],q[9];
x q[7];
rz(1.4332068705562455) q[9];
cx q[7],q[9];
rz(-1.008262413594823) q[7];
rz(-1.2956174143175945) q[9];
sx q[9];
rz(-1.4318857069778117) q[9];
sx q[9];
rz(-0.509980462387781) q[9];
cx q[9],q[6];
rz(pi/4) q[6];
cx q[7],q[6];
rz(-pi/4) q[6];
cx q[9],q[6];
rz(pi/4) q[6];
cx q[7],q[6];
rz(pi/4) q[6];
sx q[6];
rz(3*pi/4) q[6];
cx q[10],q[6];
sx q[10];
rz(pi/2) q[10];
cx q[5],q[10];
rz(-pi/4) q[10];
cx q[5],q[10];
rz(3*pi/4) q[10];
sx q[10];
rz(-pi/2) q[10];
cx q[10],q[1];
rz(-0.13920868392951558) q[1];
cx q[10],q[1];
rz(pi/2) q[1];
sx q[1];
rz(-2.867723978430937) q[1];
sx q[1];
rz(-1.6128018496880223) q[1];
sx q[10];
rz(pi/2) q[10];
cx q[10],q[2];
cx q[2],q[10];
x q[10];
rz(-0.5901423096869234) q[10];
cx q[10],q[1];
rz(-0.8160243334150046) q[1];
sx q[1];
rz(-1.604934213953018) q[1];
sx q[1];
cx q[10],q[1];
sx q[1];
rz(-1.604934213953018) q[1];
sx q[1];
rz(2.326349258323277) q[1];
sx q[10];
rz(pi/2) q[10];
rz(-pi/2) q[2];
sx q[2];
rz(3.842434976260206) q[2];
sx q[2];
rz(15*pi/4) q[2];
sx q[6];
rz(-2.1862760354652835) q[6];
sx q[6];
rz(1.5132165099238648) q[6];
cx q[6],q[8];
x q[6];
cx q[7],q[4];
sx q[4];
rz(1.682723200684089) q[4];
sx q[4];
rz(-pi) q[4];
cx q[7],q[4];
x q[4];
rz(-pi/2) q[4];
cx q[3],q[4];
sx q[3];
rz(pi/4) q[3];
x q[4];
rz(-pi/4) q[4];
cx q[5],q[3];
rz(pi/4) q[3];
cx q[4],q[3];
rz(-pi/4) q[3];
cx q[5],q[3];
rz(3*pi/4) q[3];
sx q[3];
rz(-0.6109981973410461) q[3];
cx q[5],q[4];
rz(-pi/4) q[4];
cx q[5],q[4];
cx q[0],q[5];
cx q[3],q[4];
rz(-pi/4) q[5];
cx q[3],q[5];
rz(pi/4) q[5];
cx q[0],q[5];
rz(-pi/4) q[5];
cx q[3],q[5];
cx q[3],q[0];
rz(-pi/4) q[0];
cx q[3],q[0];
rz(-3*pi/2) q[0];
sx q[0];
rz(3*pi/4) q[0];
rz(-3*pi/4) q[5];
cx q[5],q[2];
rz(-pi/4) q[2];
sx q[7];
rz(4.374665957334048) q[7];
sx q[7];
rz(4.734403657552383) q[7];
rz(pi/3) q[8];
cx q[6],q[8];
rz(2.560414061120463) q[6];
sx q[6];
rz(-0.9553166181245096) q[6];
sx q[6];
rz(-pi/2) q[6];
cx q[7],q[6];
rz(-pi/4) q[6];
cx q[4],q[6];
rz(pi/4) q[6];
cx q[7],q[6];
rz(pi/4) q[6];
sx q[6];
rz(pi) q[6];
cx q[6],q[1];
cx q[1],q[6];
rz(-0.7404047177025498) q[1];
sx q[6];
rz(-2.466367483079818) q[6];
sx q[7];
rz(-4.207501093408396) q[7];
rz(3.871695794452318) q[8];
cx q[8],q[0];
rz(-pi/4) q[0];
cx q[3],q[0];
rz(pi/4) q[0];
sx q[3];
rz(3.3817161945332095) q[3];
sx q[3];
rz(8.480310782014604) q[3];
cx q[8],q[0];
rz(pi/4) q[0];
sx q[0];
rz(pi/2) q[0];
cx q[4],q[0];
cx q[0],q[4];
rz(-pi) q[4];
sx q[4];
rz(3.015511188868894) q[4];
sx q[4];
sx q[8];
rz(-1.7604762279470343) q[8];
sx q[8];
rz(2.138921755524869) q[8];
cx q[0],q[8];
sx q[0];
rz(pi/4) q[0];
cx q[7],q[0];
rz(pi/4) q[0];
x q[8];
rz(-pi/4) q[8];
cx q[8],q[0];
rz(-pi/4) q[0];
cx q[7],q[0];
rz(1.5910107045699657) q[0];
sx q[0];
rz(-2.2598620600720913) q[0];
sx q[0];
rz(2.11898592705458) q[0];
cx q[7],q[8];
rz(-pi/4) q[8];
cx q[7],q[8];
rz(-pi/2) q[8];
sx q[8];
rz(-pi/2) q[8];
cx q[0],q[8];
sx q[0];
rz(-1.006726066448754) q[0];
sx q[0];
rz(pi/2) q[8];
cx q[0],q[8];
rz(2.6499368797902463) q[0];
sx q[0];
rz(pi/2) q[0];
x q[8];
rz(3*pi/4) q[8];
sx q[9];
rz(3.725694093386519) q[9];
sx q[9];
rz(8.415849148634289) q[9];
cx q[9],q[2];
rz(pi/4) q[2];
cx q[4],q[9];
x q[4];
rz(2.0142025399601344) q[4];
cx q[0],q[4];
sx q[0];
rz(1.6372212998499318) q[0];
sx q[0];
rz(-pi) q[0];
sx q[4];
rz(1.6372212998499318) q[4];
sx q[4];
rz(-pi) q[4];
cx q[0],q[4];
x q[0];
rz(pi/4) q[0];
rz(-0.443406213165237) q[4];
cx q[5],q[2];
rz(pi/4) q[2];
sx q[2];
rz(1.1439190345014794) q[2];
cx q[10],q[2];
sx q[10];
rz(2.7727035557156112) q[10];
sx q[10];
rz(-pi/2) q[10];
sx q[2];
rz(2.7727035557156112) q[2];
sx q[2];
rz(-pi) q[2];
cx q[10],q[2];
sx q[10];
rz(-1.026486884039823) q[10];
cx q[10],q[1];
rz(1.026486884039823) q[1];
sx q[1];
rz(-1.5702357928061037) q[1];
sx q[1];
cx q[10],q[1];
sx q[1];
rz(-1.5702357928061046) q[1];
sx q[1];
rz(-0.28608216633727324) q[1];
sx q[10];
rz(0.4413406227301788) q[10];
rz(-1.1439190345014802) q[2];
sx q[2];
cx q[2],q[6];
sx q[2];
rz(-pi/2) q[2];
sx q[5];
rz(-1.6359946673168242) q[5];
rz(pi/2) q[6];
sx q[6];
rz(-2.7912986887097837) q[6];
sx q[6];
rz(-pi/2) q[6];
cx q[7],q[5];
rz(-3.076394313067866) q[5];
cx q[7],q[5];
rz(pi/2) q[5];
sx q[5];
rz(pi/2) q[5];
cx q[1],q[5];
cx q[5],q[1];
rz(-pi/2) q[1];
sx q[1];
rz(pi/2) q[1];
cx q[1],q[3];
sx q[1];
rz(2.719855579682376) q[1];
sx q[1];
rz(-pi/2) q[1];
sx q[3];
rz(2.719855579682376) q[3];
sx q[3];
rz(-pi) q[3];
cx q[1],q[3];
sx q[1];
rz(-3*pi/4) q[1];
cx q[1],q[2];
rz(-pi/4) q[2];
cx q[1],q[2];
rz(pi/4) q[2];
rz(-2.157692984453154) q[3];
sx q[5];
sx q[7];
rz(-pi/2) q[7];
cx q[7],q[10];
rz(pi/2) q[10];
sx q[7];
rz(-pi/2) q[7];
sx q[7];
cx q[7],q[10];
rz(2.1524022951591846) q[10];
sx q[10];
rz(-0.6540612225422411) q[10];
sx q[10];
rz(2.668974761652774) q[10];
sx q[7];
rz(-2.9501853405100262) q[7];
cx q[7],q[1];
rz(3.1323771420116753) q[1];
sx q[1];
rz(-0.899879943122837) q[1];
sx q[1];
rz(-0.09056860318643167) q[1];
rz(2.357949790203161) q[9];
cx q[9],q[5];
rz(pi/2) q[5];
sx q[5];
rz(3*pi/4) q[5];
x q[9];
cx q[9],q[0];
rz(pi/4) q[0];
sx q[0];
rz(pi/2) q[0];
cx q[3],q[0];
rz(pi/4) q[0];
cx q[4],q[0];
rz(-pi/4) q[0];
cx q[3],q[0];
rz(pi/4) q[0];
cx q[3],q[2];
rz(-pi/4) q[2];
cx q[4],q[0];
rz(pi/4) q[0];
sx q[0];
rz(3*pi/4) q[0];
sx q[4];
rz(-pi/2) q[4];
cx q[5],q[2];
rz(pi/4) q[2];
cx q[3],q[2];
rz(-pi/4) q[2];
cx q[5],q[2];
rz(3*pi/4) q[2];
sx q[2];
rz(-0.812239035137627) q[2];
cx q[2],q[8];
cx q[5],q[3];
rz(-pi/4) q[3];
cx q[5],q[3];
rz(pi/4) q[3];
cx q[3],q[1];
cx q[1],q[3];
rz(pi/2) q[1];
rz(-pi) q[3];
sx q[3];
cx q[7],q[5];
cx q[10],q[7];
rz(2.991912323865222) q[5];
rz(2.3556832399656393) q[7];
sx q[7];
rz(-1.7508969099362854) q[7];
sx q[7];
cx q[10],q[7];
rz(1.4650424601818184) q[7];
sx q[7];
rz(-1.0454005516295855) q[7];
sx q[7];
rz(-4.920952705503518) q[7];
cx q[7],q[5];
sx q[5];
rz(0.9859398947998486) q[5];
sx q[5];
rz(-pi) q[5];
sx q[7];
rz(0.9859398947998486) q[7];
sx q[7];
cx q[7],q[5];
rz(1.9850849101278456) q[5];
rz(-pi) q[7];
sx q[7];
rz(-pi) q[7];
rz(-pi/4) q[8];
cx q[9],q[0];
rz(pi/4) q[0];
sx q[0];
rz(-3*pi/2) q[9];
sx q[9];
rz(3*pi/4) q[9];
cx q[6],q[9];
rz(-pi/4) q[9];
cx q[0],q[9];
sx q[0];
rz(-2.848583709013857) q[0];
sx q[0];
rz(-1.1213098420439458) q[0];
rz(pi/4) q[9];
cx q[6],q[9];
cx q[6],q[8];
rz(pi/4) q[8];
cx q[2],q[8];
sx q[2];
rz(pi/2) q[2];
rz(pi/4) q[8];
sx q[8];
rz(pi) q[8];
cx q[8],q[0];
rz(-0.4494864847509511) q[0];
cx q[8],q[0];
cx q[0],q[10];
cx q[10],q[0];
cx q[0],q[10];
rz(-3*pi/4) q[0];
cx q[0],q[5];
cx q[10],q[2];
rz(pi/2) q[10];
rz(pi/2) q[2];
sx q[2];
rz(0.23073917678802358) q[2];
rz(-pi/4) q[5];
cx q[0],q[5];
sx q[0];
rz(pi/2) q[0];
cx q[0],q[10];
cx q[10],q[0];
rz(-3*pi/2) q[10];
sx q[10];
rz(2.1664632933694206) q[10];
rz(3*pi/4) q[5];
sx q[5];
rz(pi/2) q[5];
sx q[8];
rz(pi/2) q[8];
cx q[1],q[8];
rz(-pi/4) q[8];
cx q[1],q[8];
rz(3*pi/4) q[8];
sx q[8];
rz(3*pi/4) q[8];
rz(2.444738128265027) q[9];
sx q[9];
rz(-0.5561176338379461) q[9];
sx q[9];
rz(-2.4827237893086513) q[9];
cx q[9],q[6];
rz(-1.418074293577401) q[6];
cx q[9],q[6];
rz(1.418074293577401) q[6];
cx q[4],q[6];
rz(-pi/4) q[6];
cx q[4],q[6];
sx q[4];
rz(-pi) q[4];
cx q[4],q[7];
sx q[4];
rz(1.8705346518019326) q[6];
cx q[2],q[6];
rz(0.5546589866094243) q[6];
sx q[6];
rz(-2.3524567206350033) q[6];
sx q[6];
cx q[2],q[6];
sx q[6];
rz(-2.3524567206350033) q[6];
sx q[6];
rz(2.3822704451709633) q[6];
x q[7];
rz(-pi/4) q[7];
cx q[8],q[7];
rz(pi/4) q[7];
cx q[4],q[7];
rz(pi/4) q[4];
rz(-pi/4) q[7];
cx q[8],q[7];
rz(3*pi/4) q[7];
sx q[7];
rz(3*pi/4) q[7];
cx q[8],q[4];
rz(-pi/4) q[4];
cx q[8],q[4];
cx q[7],q[4];
cx q[4],q[8];
cx q[7],q[5];
rz(-pi/4) q[5];
cx q[7],q[5];
rz(3*pi/4) q[5];
sx q[5];
rz(pi/2) q[5];
x q[7];
rz(-0.5811785924693282) q[7];
cx q[8],q[4];
rz(pi/2) q[4];
sx q[4];
x q[8];
rz(-2.030179244887462) q[8];
cx q[7],q[8];
x q[7];
rz(pi/4) q[8];
cx q[7],q[8];
rz(-1.3665767558667774) q[7];
cx q[2],q[7];
rz(-pi/4) q[7];
cx q[2],q[7];
rz(pi/4) q[7];
rz(-2.81557740828491) q[8];
sx q[8];
cx q[8],q[4];
rz(5.057412206538706) q[4];
cx q[8],q[4];
rz(-pi) q[4];
sx q[4];
rz(-pi) q[4];
rz(-pi) q[8];
sx q[8];
rz(-pi) q[8];
sx q[9];
rz(-pi/2) q[9];
cx q[3],q[9];
sx q[3];
rz(-1.2496971650388264) q[3];
rz(3*pi/4) q[9];
cx q[1],q[9];
rz(pi/4) q[9];
cx q[3],q[9];
rz(-pi/4) q[9];
cx q[1],q[9];
cx q[1],q[3];
rz(-pi/4) q[3];
cx q[1],q[3];
rz(3*pi/4) q[9];
sx q[9];
rz(-0.33992295679336504) q[9];
cx q[9],q[3];
rz(-pi) q[3];
x q[3];
cx q[5],q[3];
cx q[3],q[5];
rz(1.41472006600941) q[3];
sx q[3];
rz(7.364368859255449) q[3];
sx q[5];
rz(1.0744553437070685) q[5];
sx q[5];
rz(-pi) q[5];
sx q[9];
cx q[1],q[9];
sx q[1];
rz(-pi/2) q[1];
sx q[1];
rz(pi/2) q[9];
cx q[1],q[9];
rz(-pi/2) q[1];
sx q[1];
rz(1.1253211201908129) q[1];
rz(pi/2) q[9];
sx q[9];
rz(-pi/4) q[9];
cx q[0],q[9];
rz(pi/4) q[9];
cx q[1],q[9];
rz(-pi/4) q[9];
cx q[0],q[9];
cx q[0],q[1];
rz(3*pi/4) q[0];
rz(-pi/4) q[1];
cx q[0],q[1];
sx q[0];
rz(pi/2) q[0];
rz(5*pi/16) q[9];
cx q[9],q[0];
rz(-pi/16) q[0];
cx q[9],q[0];
rz(pi/16) q[0];
cx q[9],q[1];
rz(-pi/16) q[1];
cx q[1],q[0];
rz(pi/16) q[0];
cx q[1],q[0];
rz(-pi/16) q[0];
cx q[9],q[1];
rz(pi/16) q[1];
cx q[1],q[0];
rz(-pi/16) q[0];
cx q[1],q[0];
rz(pi/16) q[0];
cx q[1],q[10];
rz(-pi/16) q[10];
cx q[10],q[0];
rz(pi/16) q[0];
cx q[10],q[0];
rz(-pi/16) q[0];
cx q[9],q[10];
rz(pi/16) q[10];
cx q[10],q[0];
rz(-pi/16) q[0];
cx q[10],q[0];
rz(pi/16) q[0];
cx q[1],q[10];
rz(-pi/16) q[10];
cx q[10],q[0];
rz(pi/16) q[0];
cx q[10],q[0];
rz(-pi/16) q[0];
cx q[9],q[10];
rz(pi/16) q[10];
cx q[10],q[0];
rz(-pi/16) q[0];
cx q[10],q[0];
rz(9*pi/16) q[0];
sx q[0];
rz(pi/2) q[0];
barrier q[0],q[1],q[2],q[3],q[4],q[5],q[6],q[7],q[8],q[9],q[10];
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

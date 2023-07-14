// Benchmark was created by MQT Bench on 2023-06-29
// For more information about MQT Bench, please visit https://www.cda.cit.tum.de/mqtbench/
// MQT Bench version: v1.0.0
// Qiskit version: {'qiskit-terra': '0.24.1', 'qiskit-aer': '0.12.0', 'qiskit-ignis': None, 'qiskit-ibmq-provider': '0.20.2', 'qiskit': '0.43.1', 'qiskit-nature': '0.6.2', 'qiskit-finance': '0.3.4', 'qiskit-optimization': '0.5.0', 'qiskit-machine-learning': '0.6.1'}
// Used Gate Set: ['rz', 'sx', 'x', 'cx', 'measure']
// Coupling List: [[0, 1], [1, 0], [1, 2], [1, 4], [2, 1], [2, 3], [3, 2], [3, 5], [4, 1], [4, 7], [5, 3], [5, 8], [6, 7], [7, 4], [7, 6], [7, 10], [8, 5], [8, 9], [8, 11], [9, 8], [10, 7], [10, 12], [11, 8], [11, 14], [12, 10], [12, 13], [12, 15], [13, 12], [13, 14], [14, 11], [14, 13], [14, 16], [15, 12], [15, 18], [16, 14], [16, 19], [17, 18], [18, 15], [18, 17], [18, 21], [19, 16], [19, 20], [19, 22], [20, 19], [21, 18], [21, 23], [22, 19], [22, 25], [23, 21], [23, 24], [24, 23], [24, 25], [25, 22], [25, 24], [25, 26], [26, 25]]

OPENQASM 2.0;
include "qelib1.inc";
qreg q[27];
creg c[20];
x q[1];
rz(-1.3464786010726586) q[1];
rz(pi/2) q[2];
sx q[2];
rz(pi/2) q[2];
cx q[1],q[2];
rz(1.3464786010726586) q[2];
cx q[1],q[2];
rz(-1.3464786010726586) q[2];
x q[3];
rz(pi/2) q[3];
rz(pi/2) q[4];
sx q[4];
rz(pi/2) q[4];
cx q[1],q[4];
cx q[4],q[1];
cx q[1],q[4];
rz(1.3459063543334278) q[4];
rz(pi/2) q[6];
sx q[6];
rz(pi/2) q[6];
rz(pi/2) q[7];
sx q[7];
rz(pi/2) q[7];
cx q[4],q[7];
rz(-0.4486354514444759) q[7];
cx q[4],q[7];
cx q[4],q[1];
rz(-0.8972709028889518) q[1];
cx q[4],q[1];
rz(0.8972709028889518) q[1];
rz(0.4486354514444759) q[7];
cx q[4],q[7];
cx q[7],q[4];
cx q[4],q[7];
rz(pi/2) q[10];
sx q[10];
rz(pi/2) q[10];
cx q[7],q[10];
cx q[10],q[7];
cx q[7],q[10];
rz(-1.3470508478118894) q[10];
sx q[10];
rz(-pi) q[10];
rz(pi/2) q[11];
sx q[11];
rz(pi/2) q[11];
sx q[12];
rz(-pi) q[12];
cx q[12],q[10];
rz(pi/2) q[10];
sx q[12];
cx q[12],q[10];
rz(0.22374547898300715) q[10];
sx q[12];
cx q[12],q[10];
x q[10];
rz(1.7945418057779037) q[10];
rz(2.9132692006929397) q[12];
rz(pi/2) q[13];
sx q[13];
rz(pi/2) q[13];
rz(pi/2) q[14];
sx q[14];
rz(pi/2) q[14];
rz(pi/2) q[15];
sx q[15];
rz(pi/2) q[15];
cx q[12],q[15];
rz(-0.4474909579660144) q[15];
cx q[12],q[15];
rz(0.4474909579660144) q[15];
rz(pi/2) q[16];
sx q[16];
rz(pi/2) q[16];
rz(pi/2) q[18];
sx q[18];
rz(pi/2) q[18];
cx q[18],q[15];
cx q[15],q[18];
cx q[18],q[15];
cx q[12],q[15];
rz(-0.8949819159320288) q[15];
cx q[12],q[15];
cx q[12],q[13];
cx q[13],q[12];
cx q[12],q[13];
rz(-0.9132938115874135) q[13];
cx q[13],q[14];
rz(1.3516288217257355) q[14];
cx q[13],q[14];
cx q[13],q[12];
rz(-0.438335010138322) q[12];
cx q[13],q[12];
rz(0.438335010138322) q[12];
rz(-1.3516288217257355) q[14];
cx q[13],q[14];
cx q[14],q[13];
cx q[13],q[14];
rz(-2.5026896554354234) q[14];
cx q[14],q[16];
rz(0.8949819159320288) q[15];
rz(-0.876670020276644) q[16];
cx q[14],q[16];
cx q[14],q[11];
rz(1.3882526130365052) q[11];
cx q[14],q[11];
rz(-1.3882526130365052) q[11];
sx q[14];
rz(-pi) q[14];
rz(0.876670020276644) q[16];
rz(pi/2) q[19];
sx q[19];
rz(pi/2) q[19];
cx q[19],q[16];
cx q[16],q[19];
cx q[19],q[16];
rz(pi/4) q[16];
sx q[16];
rz(pi/2) q[16];
cx q[16],q[14];
rz(-pi/2) q[14];
sx q[16];
cx q[16],q[14];
rz(1.2057088992781146) q[14];
sx q[16];
cx q[16],q[14];
x q[14];
rz(-0.4203107358806655) q[14];
cx q[11],q[14];
cx q[14],q[11];
cx q[11],q[14];
rz(-0.021475731030398976) q[11];
cx q[13],q[14];
cx q[14],q[13];
cx q[13],q[14];
cx q[12],q[13];
cx q[13],q[12];
cx q[12],q[13];
x q[16];
rz(-3*pi/4) q[16];
cx q[16],q[19];
cx q[19],q[16];
cx q[16],q[19];
rz(2.371152061491741) q[19];
sx q[19];
rz(-pi) q[19];
rz(pi/2) q[20];
sx q[20];
rz(pi/2) q[20];
rz(pi/2) q[21];
sx q[21];
rz(pi/2) q[21];
rz(-pi) q[22];
sx q[22];
rz(-pi) q[22];
cx q[22],q[19];
rz(pi/2) q[19];
sx q[22];
cx q[22],q[19];
rz(0.8406214717613314) q[19];
sx q[22];
cx q[22],q[19];
rz(-2.4114177985562284) q[19];
cx q[16],q[19];
cx q[19],q[16];
cx q[16],q[19];
cx q[20],q[19];
cx q[19],q[20];
cx q[20],q[19];
rz(-0.011504855909142308) q[20];
rz(1.1692755969483184) q[22];
rz(pi/2) q[23];
sx q[23];
rz(pi/2) q[23];
cx q[21],q[23];
cx q[23],q[21];
cx q[21],q[23];
rz(pi/2) q[24];
sx q[24];
rz(pi/2) q[24];
rz(pi/2) q[25];
sx q[25];
rz(pi/2) q[25];
cx q[22],q[25];
rz(-1.4603497100671303) q[25];
cx q[22],q[25];
cx q[22],q[19];
rz(0.22089323345553233) q[19];
cx q[22],q[19];
rz(-0.22089323345553233) q[19];
cx q[19],q[16];
cx q[16],q[19];
cx q[19],q[16];
cx q[14],q[16];
cx q[16],q[14];
cx q[14],q[16];
cx q[13],q[14];
cx q[14],q[13];
cx q[13],q[14];
rz(1.4603497100671303) q[25];
cx q[22],q[25];
cx q[25],q[22];
cx q[22],q[25];
cx q[22],q[19];
cx q[19],q[22];
cx q[22],q[19];
cx q[19],q[16];
cx q[16],q[19];
cx q[19],q[16];
cx q[14],q[16];
cx q[16],q[14];
cx q[14],q[16];
cx q[25],q[24];
cx q[24],q[25];
cx q[25],q[24];
rz(pi/64) q[24];
cx q[24],q[23];
rz(0.44178646691106466) q[23];
cx q[24],q[23];
rz(-0.44178646691106466) q[23];
cx q[23],q[21];
cx q[21],q[23];
cx q[23],q[21];
cx q[18],q[21];
cx q[21],q[18];
cx q[18],q[21];
cx q[15],q[18];
cx q[18],q[15];
cx q[15],q[18];
cx q[24],q[23];
rz(0.8835729338221293) q[23];
cx q[24],q[23];
rz(-0.8835729338221293) q[23];
cx q[21],q[23];
cx q[23],q[21];
cx q[21],q[23];
cx q[24],q[25];
rz(-7*pi/16) q[25];
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
cx q[18],q[15];
cx q[15],q[18];
cx q[18],q[15];
cx q[23],q[21];
cx q[21],q[23];
cx q[23],q[21];
cx q[21],q[18];
cx q[18],q[21];
cx q[21],q[18];
rz(-pi/32768) q[23];
rz(7*pi/16) q[25];
cx q[25],q[22];
cx q[22],q[25];
cx q[25],q[22];
cx q[22],q[19];
cx q[19],q[22];
cx q[22],q[19];
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
cx q[10],q[12];
cx q[12],q[10];
cx q[10],q[12];
rz(-pi/128) q[14];
sx q[14];
rz(pi/2) q[14];
cx q[15],q[12];
cx q[12],q[15];
cx q[15],q[12];
rz(-pi/8) q[12];
rz(-0.7976700097005334) q[16];
sx q[16];
rz(-pi/2) q[16];
cx q[18],q[15];
cx q[15],q[18];
cx q[18],q[15];
rz(-0.8835729338221299) q[15];
sx q[15];
rz(pi/2) q[15];
cx q[21],q[18];
cx q[18],q[21];
cx q[21],q[18];
rz(-0.14726215563702155) q[18];
rz(-pi/131072) q[21];
sx q[21];
cx q[25],q[22];
cx q[22],q[25];
cx q[25],q[22];
cx q[22],q[19];
cx q[19],q[22];
cx q[22],q[19];
rz(-0.018407769454627694) q[19];
rz(-pi/8192) q[22];
sx q[22];
rz(pi/2) q[22];
rz(-pi/16384) q[25];
sx q[25];
rz(pi/2) q[25];
cx q[7],q[10];
cx q[10],q[7];
cx q[7],q[10];
cx q[12],q[10];
rz(pi/8) q[10];
cx q[12],q[10];
rz(-pi/8) q[10];
cx q[10],q[7];
cx q[7],q[10];
cx q[10],q[7];
cx q[12],q[10];
cx q[10],q[12];
cx q[12],q[10];
rz(0.6032214048645566) q[12];
sx q[12];
rz(-pi/2) q[12];
cx q[7],q[4];
cx q[4],q[7];
cx q[7],q[4];
cx q[1],q[4];
cx q[10],q[7];
cx q[4],q[1];
cx q[1],q[4];
cx q[7],q[10];
cx q[10],q[7];
rz(-pi/4) q[7];
cx q[7],q[6];
rz(pi/4) q[6];
cx q[7],q[6];
rz(-pi/4) q[6];
cx q[7],q[4];
cx q[4],q[7];
cx q[7],q[4];
cx q[4],q[1];
cx q[1],q[4];
cx q[4],q[1];
cx q[1],q[2];
cx q[2],q[1];
cx q[1],q[2];
rz(0.060585932751573424) q[2];
sx q[2];
rz(pi/2) q[2];
cx q[2],q[3];
sx q[2];
rz(-pi/2) q[2];
sx q[2];
rz(pi/2) q[3];
cx q[2],q[3];
rz(pi/2) q[2];
sx q[2];
rz(-pi) q[2];
cx q[1],q[2];
cx q[2],q[1];
cx q[1],q[2];
rz(pi/2) q[1];
sx q[1];
rz(pi/2) q[1];
cx q[1],q[4];
rz(-pi) q[3];
sx q[3];
rz(1.5102103940433222) q[3];
cx q[4],q[1];
cx q[1],q[4];
cx q[6],q[7];
cx q[7],q[6];
cx q[6],q[7];
cx q[10],q[7];
cx q[7],q[10];
cx q[10],q[7];
rz(pi/4) q[10];
cx q[4],q[7];
cx q[7],q[4];
cx q[4],q[7];
cx q[1],q[4];
cx q[10],q[7];
cx q[4],q[1];
cx q[1],q[4];
rz(pi/8) q[4];
rz(pi/4) q[7];
cx q[10],q[7];
sx q[10];
rz(pi/2) q[10];
rz(-pi/4) q[7];
cx q[4],q[7];
rz(pi/8) q[7];
cx q[4],q[7];
rz(-pi/8) q[7];
cx q[7],q[10];
cx q[10],q[7];
cx q[7],q[10];
rz(-2.370367272508816) q[10];
sx q[10];
rz(-pi) q[10];
cx q[12],q[10];
rz(-pi/2) q[10];
sx q[12];
cx q[12],q[10];
rz(7*pi/16) q[10];
sx q[12];
cx q[12],q[10];
rz(0.378526299382254) q[10];
sx q[10];
rz(pi/2) q[10];
rz(-1.388619568262006) q[12];
sx q[12];
rz(-pi) q[12];
cx q[15],q[12];
rz(-pi/2) q[12];
sx q[15];
cx q[15],q[12];
rz(1.4726215563702159) q[12];
sx q[15];
cx q[15],q[12];
rz(1.7837678240038404) q[12];
sx q[12];
rz(pi/2) q[12];
rz(0.6872233929727676) q[15];
cx q[18],q[15];
rz(pi/64) q[15];
cx q[18],q[15];
rz(-pi/64) q[15];
cx q[4],q[7];
rz(pi/4) q[7];
cx q[4],q[7];
sx q[4];
rz(pi/2) q[4];
rz(3*pi/4) q[7];
sx q[7];
cx q[10],q[7];
sx q[10];
rz(pi/2) q[7];
cx q[10],q[7];
sx q[10];
rz(3*pi/8) q[7];
cx q[10],q[7];
rz(-1.980117364853203) q[10];
sx q[10];
cx q[12],q[10];
rz(-pi/2) q[10];
sx q[12];
cx q[12],q[10];
rz(7*pi/16) q[10];
sx q[12];
cx q[12],q[10];
rz(2.3728164465519272) q[10];
x q[12];
rz(0.5724266661885027) q[12];
cx q[15],q[12];
cx q[12],q[15];
cx q[15],q[12];
cx q[12],q[13];
cx q[13],q[12];
cx q[12],q[13];
sx q[13];
cx q[14],q[13];
rz(-pi/2) q[13];
sx q[14];
cx q[14],q[13];
rz(1.546252634188727) q[13];
sx q[14];
cx q[14],q[13];
x q[13];
cx q[13],q[12];
cx q[12],q[13];
cx q[13],q[12];
rz(-pi/64) q[12];
sx q[12];
rz(-2.3807381827985146) q[14];
sx q[14];
rz(-pi) q[14];
cx q[16],q[14];
rz(-pi/2) q[14];
sx q[16];
cx q[16],q[14];
rz(1.558524480491812) q[14];
sx q[16];
cx q[16],q[14];
rz(-3*pi/4) q[14];
cx q[14],q[13];
cx q[13],q[14];
cx q[14],q[13];
rz(-pi/128) q[13];
sx q[13];
rz(pi/2) q[13];
x q[16];
rz(-0.7976700097005338) q[16];
cx q[18],q[15];
rz(pi/32) q[15];
cx q[18],q[15];
rz(-pi/32) q[15];
sx q[15];
rz(pi/2) q[15];
cx q[15],q[12];
rz(-pi/2) q[12];
sx q[15];
cx q[15],q[12];
rz(1.5217089415825562) q[12];
sx q[15];
cx q[15],q[12];
x q[12];
rz(-pi/64) q[12];
sx q[12];
cx q[13],q[12];
rz(-pi/2) q[12];
sx q[13];
cx q[13],q[12];
rz(1.546252634188727) q[12];
sx q[13];
cx q[13],q[12];
x q[12];
rz(1.5462526341887273) q[13];
cx q[13],q[14];
cx q[14],q[13];
cx q[13],q[14];
rz(-0.0046019423636569235) q[13];
rz(pi/2) q[15];
cx q[18],q[15];
cx q[15],q[18];
cx q[18],q[15];
rz(0.5724266661885036) q[15];
sx q[15];
rz(-pi/2) q[15];
rz(-0.2945243112740431) q[18];
cx q[19],q[16];
rz(pi/512) q[16];
cx q[19],q[16];
rz(-pi/512) q[16];
cx q[16],q[14];
cx q[14],q[16];
cx q[16],q[14];
cx q[11],q[14];
rz(pi/1024) q[14];
cx q[11],q[14];
rz(-pi/1024) q[14];
cx q[13],q[14];
rz(pi/2048) q[14];
cx q[13],q[14];
rz(-pi/2048) q[14];
cx q[19],q[16];
rz(pi/256) q[16];
cx q[19],q[16];
rz(-pi/256) q[16];
cx q[14],q[16];
cx q[16],q[14];
cx q[14],q[16];
cx q[11],q[14];
rz(pi/512) q[14];
cx q[11],q[14];
rz(-pi/512) q[14];
cx q[13],q[14];
rz(pi/1024) q[14];
cx q[13],q[14];
rz(-pi/1024) q[14];
cx q[16],q[19];
cx q[19],q[16];
cx q[16],q[19];
cx q[14],q[16];
cx q[16],q[14];
cx q[14],q[16];
rz(-pi/128) q[14];
sx q[14];
rz(pi/2) q[14];
cx q[20],q[19];
rz(pi/4096) q[19];
cx q[20],q[19];
rz(-pi/4096) q[19];
sx q[19];
cx q[22],q[19];
rz(-pi/2) q[19];
sx q[22];
cx q[22],q[19];
rz(1.5704128315979258) q[19];
sx q[22];
cx q[22],q[19];
x q[19];
cx q[16],q[19];
cx q[19],q[16];
cx q[16],q[19];
rz(-pi/4096) q[16];
cx q[20],q[19];
rz(pi/2048) q[19];
cx q[20],q[19];
rz(-pi/2048) q[19];
cx q[16],q[19];
rz(pi/4096) q[19];
cx q[16],q[19];
rz(-pi/4096) q[19];
sx q[19];
rz(1.5704128315979258) q[22];
sx q[22];
cx q[25],q[22];
rz(-pi/2) q[22];
sx q[25];
cx q[25],q[22];
rz(1.5706045791964112) q[22];
sx q[25];
cx q[25],q[22];
rz(-3.1412091583928214) q[22];
sx q[22];
rz(-pi/2) q[22];
cx q[22],q[19];
rz(-pi/2) q[19];
sx q[22];
cx q[22],q[19];
rz(1.5704128315979258) q[19];
sx q[22];
cx q[22],q[19];
x q[19];
rz(1.5704128315979258) q[22];
rz(1.5706045791964112) q[25];
cx q[25],q[24];
cx q[24],q[25];
cx q[25],q[24];
cx q[23],q[24];
rz(pi/32768) q[24];
cx q[23],q[24];
rz(-pi/32768) q[24];
rz(-pi/65536) q[25];
cx q[25],q[24];
rz(pi/65536) q[24];
cx q[25],q[24];
cx q[22],q[25];
rz(-pi/65536) q[24];
cx q[24],q[23];
cx q[23],q[24];
cx q[24],q[23];
sx q[23];
rz(pi/2) q[23];
cx q[23],q[21];
rz(-pi/2) q[21];
sx q[23];
cx q[23],q[21];
rz(pi/2) q[21];
sx q[23];
cx q[23],q[21];
x q[21];
rz(-2.396844981067403e-05) q[21];
rz(1.5707483898952752) q[23];
sx q[23];
rz(-pi/16384) q[24];
cx q[25],q[22];
cx q[22],q[25];
rz(-pi/32768) q[22];
cx q[24],q[25];
rz(pi/16384) q[25];
cx q[24],q[25];
rz(-pi/16384) q[25];
cx q[22],q[25];
rz(pi/32768) q[25];
cx q[22],q[25];
rz(-pi/32768) q[25];
cx q[25],q[24];
cx q[24],q[25];
cx q[25],q[24];
sx q[24];
rz(pi/2) q[24];
cx q[24],q[23];
rz(-pi/2) q[23];
sx q[24];
cx q[24],q[23];
rz(pi/2) q[23];
sx q[24];
cx q[24],q[23];
rz(-3.1415447166901718) q[23];
sx q[23];
rz(-pi/2) q[23];
rz(-1.5708922005941393) q[24];
sx q[24];
rz(-pi) q[24];
rz(-pi/8192) q[25];
rz(-pi/4) q[7];
cx q[7],q[4];
rz(pi/4) q[4];
cx q[7],q[4];
rz(-pi/4) q[4];
sx q[7];
rz(pi/2) q[7];
cx q[10],q[7];
cx q[7],q[10];
cx q[10],q[7];
rz(pi/8) q[7];
cx q[7],q[4];
rz(pi/8) q[4];
cx q[7],q[4];
rz(-pi/8) q[4];
cx q[7],q[10];
rz(pi/4) q[10];
cx q[7],q[10];
rz(-pi/4) q[10];
sx q[7];
rz(pi/2) q[7];
cx q[4],q[7];
cx q[7],q[4];
cx q[4],q[7];
rz(-1.7680700847793758) q[4];
sx q[4];
cx q[7],q[10];
cx q[10],q[7];
cx q[7],q[10];
cx q[10],q[12];
cx q[12],q[10];
cx q[10],q[12];
rz(0.8020201197570311) q[12];
sx q[12];
rz(-pi) q[12];
cx q[15],q[12];
rz(-pi/2) q[12];
sx q[15];
cx q[15],q[12];
rz(7*pi/16) q[12];
sx q[15];
cx q[15],q[12];
rz(2.3728164465519272) q[12];
cx q[10],q[12];
cx q[12],q[10];
cx q[10],q[12];
rz(-5*pi/8) q[10];
sx q[10];
rz(pi/2) q[10];
rz(-pi/64) q[12];
x q[15];
rz(0.5724266661885027) q[15];
cx q[18],q[15];
rz(pi/32) q[15];
cx q[18],q[15];
rz(-pi/32) q[15];
cx q[12],q[15];
rz(pi/64) q[15];
cx q[12],q[15];
rz(-pi/64) q[15];
rz(-pi) q[7];
sx q[7];
cx q[10],q[7];
sx q[10];
rz(pi/2) q[7];
cx q[10],q[7];
sx q[10];
rz(3*pi/8) q[7];
cx q[10],q[7];
x q[10];
rz(-5*pi/8) q[10];
cx q[10],q[12];
cx q[12],q[10];
cx q[10],q[12];
rz(-pi/32) q[10];
cx q[15],q[12];
cx q[12],q[15];
cx q[15],q[12];
cx q[12],q[13];
cx q[13],q[12];
cx q[12],q[13];
sx q[13];
cx q[14],q[13];
rz(-pi/2) q[13];
sx q[14];
cx q[14],q[13];
rz(1.546252634188727) q[13];
sx q[14];
cx q[14],q[13];
x q[13];
cx q[12],q[13];
cx q[13],q[12];
cx q[12],q[13];
rz(-pi/512) q[13];
rz(1.5462526341887273) q[14];
cx q[11],q[14];
rz(pi/256) q[14];
cx q[11],q[14];
rz(-pi/256) q[14];
cx q[13],q[14];
rz(pi/512) q[14];
cx q[13],q[14];
rz(-pi/512) q[14];
cx q[14],q[16];
cx q[16],q[14];
cx q[14],q[16];
cx q[11],q[14];
cx q[14],q[11];
cx q[11],q[14];
rz(-pi/128) q[14];
cx q[16],q[19];
cx q[18],q[15];
rz(pi/16) q[15];
cx q[18],q[15];
rz(-pi/16) q[15];
cx q[15],q[12];
cx q[12],q[15];
cx q[15],q[12];
cx q[10],q[12];
rz(pi/32) q[12];
cx q[10],q[12];
rz(-pi/32) q[12];
rz(-pi/64) q[15];
cx q[15],q[12];
rz(pi/64) q[12];
cx q[15],q[12];
rz(-pi/64) q[12];
cx q[12],q[13];
cx q[13],q[12];
cx q[12],q[13];
rz(-pi/256) q[12];
cx q[14],q[13];
rz(pi/128) q[13];
cx q[14],q[13];
rz(-pi/128) q[13];
cx q[12],q[13];
rz(pi/256) q[13];
cx q[12],q[13];
rz(-pi/256) q[13];
cx q[13],q[14];
cx q[14],q[13];
cx q[13],q[14];
rz(-0.14726215563702155) q[13];
cx q[18],q[15];
cx q[15],q[18];
cx q[18],q[15];
cx q[15],q[12];
cx q[12],q[15];
cx q[15],q[12];
rz(-pi/8) q[12];
cx q[18],q[15];
cx q[15],q[18];
cx q[18],q[15];
rz(-pi/32) q[15];
rz(-0.9572040116406406) q[18];
cx q[19],q[16];
cx q[16],q[19];
cx q[14],q[16];
cx q[16],q[14];
cx q[14],q[16];
cx q[11],q[14];
cx q[14],q[11];
cx q[11],q[14];
rz(-0.39193209130478135) q[11];
rz(-pi/2048) q[14];
sx q[14];
cx q[20],q[19];
rz(pi/1024) q[19];
cx q[20],q[19];
rz(-pi/1024) q[19];
cx q[19],q[16];
cx q[16],q[19];
cx q[19],q[16];
sx q[16];
rz(pi/2) q[16];
cx q[16],q[14];
rz(-pi/2) q[14];
sx q[16];
cx q[16],q[14];
rz(1.5692623460070116) q[14];
sx q[16];
cx q[16],q[14];
x q[14];
rz(-0.0015339807878858025) q[14];
cx q[11],q[14];
rz(pi/4096) q[14];
cx q[11],q[14];
rz(-pi/4096) q[14];
rz(1.567728365219125) q[16];
cx q[20],q[19];
rz(pi/512) q[19];
cx q[20],q[19];
rz(-pi/512) q[19];
cx q[16],q[19];
rz(pi/1024) q[19];
cx q[16],q[19];
cx q[14],q[16];
cx q[16],q[14];
cx q[14],q[16];
rz(-pi/1024) q[19];
cx q[16],q[19];
cx q[19],q[16];
cx q[16],q[19];
cx q[19],q[22];
cx q[22],q[19];
cx q[19],q[22];
rz(-pi/16384) q[19];
cx q[25],q[22];
rz(pi/8192) q[22];
cx q[25],q[22];
rz(-pi/8192) q[22];
cx q[19],q[22];
rz(pi/16384) q[22];
cx q[19],q[22];
cx q[20],q[19];
cx q[19],q[20];
cx q[20],q[19];
rz(-pi/256) q[19];
rz(-0.02416019740919885) q[20];
rz(-pi/16384) q[22];
cx q[22],q[25];
cx q[25],q[22];
cx q[22],q[25];
rz(-pi/4096) q[22];
rz(-pi/2) q[25];
sx q[25];
rz(-pi/2) q[25];
cx q[25],q[24];
rz(-pi/2) q[24];
sx q[25];
cx q[25],q[24];
rz(1.5707004529956539) q[24];
sx q[25];
cx q[25],q[24];
rz(-1.5708922005941393) q[24];
sx q[24];
rz(pi/2) q[24];
rz(-1.5706045791964114) q[25];
sx q[25];
rz(-pi/2) q[25];
rz(1.0783301308832849) q[7];
sx q[7];
rz(pi/2) q[7];
cx q[7],q[4];
rz(pi/2) q[4];
sx q[7];
cx q[7],q[4];
rz(pi/4) q[4];
sx q[7];
cx q[7],q[4];
rz(1.2778643593090582) q[4];
sx q[4];
rz(pi/2) q[4];
x q[7];
rz(2.5534682481768236) q[7];
cx q[7],q[10];
cx q[10],q[7];
cx q[7],q[10];
cx q[12],q[10];
rz(pi/8) q[10];
cx q[12],q[10];
rz(-pi/8) q[10];
rz(-pi/16) q[7];
cx q[7],q[10];
rz(pi/16) q[10];
cx q[7],q[10];
rz(-pi/16) q[10];
cx q[12],q[10];
cx q[10],q[12];
cx q[12],q[10];
rz(pi/4) q[10];
cx q[15],q[12];
rz(pi/32) q[12];
cx q[15],q[12];
rz(-pi/32) q[12];
cx q[13],q[12];
rz(pi/64) q[12];
cx q[13],q[12];
rz(-pi/64) q[12];
cx q[12],q[15];
cx q[15],q[12];
cx q[12],q[15];
rz(0.6032214048645566) q[12];
sx q[12];
rz(-pi/2) q[12];
cx q[18],q[15];
rz(pi/128) q[15];
cx q[18],q[15];
rz(-pi/128) q[15];
cx q[4],q[7];
cx q[7],q[4];
cx q[4],q[7];
cx q[10],q[7];
rz(pi/8) q[4];
rz(pi/4) q[7];
cx q[10],q[7];
sx q[10];
rz(pi/2) q[10];
rz(-pi/4) q[7];
cx q[4],q[7];
rz(pi/8) q[7];
cx q[4],q[7];
rz(-pi/8) q[7];
cx q[10],q[7];
cx q[7],q[10];
cx q[10],q[7];
rz(-2.370367272508816) q[10];
sx q[10];
rz(-pi) q[10];
cx q[12],q[10];
rz(-pi/2) q[10];
sx q[12];
cx q[12],q[10];
rz(7*pi/16) q[10];
sx q[12];
cx q[12],q[10];
rz(0.378526299382254) q[10];
sx q[10];
rz(pi/2) q[10];
x q[12];
rz(-2.5383712487252357) q[12];
cx q[13],q[12];
rz(pi/32) q[12];
cx q[13],q[12];
rz(-pi/32) q[12];
cx q[12],q[15];
cx q[15],q[12];
cx q[12],q[15];
cx q[13],q[12];
cx q[12],q[13];
cx q[13],q[12];
rz(0.5724266661885036) q[12];
sx q[12];
rz(-pi/2) q[12];
cx q[13],q[14];
cx q[14],q[13];
cx q[13],q[14];
rz(-pi/512) q[13];
cx q[16],q[14];
cx q[14],q[16];
cx q[16],q[14];
cx q[11],q[14];
rz(pi/2048) q[14];
cx q[11],q[14];
rz(-pi/2048) q[14];
cx q[18],q[15];
rz(pi/64) q[15];
cx q[18],q[15];
rz(-pi/64) q[15];
sx q[18];
rz(pi/2) q[18];
cx q[19],q[16];
rz(pi/256) q[16];
cx q[19],q[16];
rz(-pi/256) q[16];
cx q[14],q[16];
cx q[16],q[14];
cx q[14],q[16];
cx q[13],q[14];
rz(pi/512) q[14];
cx q[13],q[14];
rz(-pi/512) q[14];
cx q[11],q[14];
rz(pi/1024) q[14];
cx q[11],q[14];
rz(-pi/1024) q[14];
cx q[16],q[19];
cx q[19],q[16];
cx q[16],q[19];
cx q[14],q[16];
cx q[16],q[14];
cx q[14],q[16];
rz(-pi/128) q[14];
sx q[16];
cx q[22],q[19];
rz(pi/4096) q[19];
cx q[22],q[19];
rz(-pi/4096) q[19];
cx q[20],q[19];
rz(pi/8192) q[19];
cx q[20],q[19];
rz(-pi/8192) q[19];
cx q[19],q[22];
cx q[22],q[19];
cx q[19],q[22];
rz(-pi/2048) q[19];
sx q[19];
rz(pi/2) q[19];
cx q[19],q[16];
rz(-pi/2) q[16];
sx q[19];
cx q[19],q[16];
rz(1.5692623460070116) q[16];
sx q[19];
cx q[19],q[16];
rz(-2.353126528616573) q[16];
sx q[16];
rz(-pi/2) q[16];
rz(1.5692623460070108) q[19];
cx q[20],q[19];
rz(pi/4096) q[19];
cx q[20],q[19];
rz(-pi/4096) q[19];
sx q[22];
cx q[25],q[22];
rz(-pi/2) q[22];
sx q[25];
cx q[25],q[22];
rz(1.5706045791964112) q[22];
sx q[25];
cx q[25],q[22];
x q[22];
rz(-0.00038349519697167267) q[22];
cx q[22],q[19];
rz(pi/8192) q[19];
cx q[22],q[19];
rz(-pi/8192) q[19];
rz(-0.00019174759848450407) q[25];
sx q[25];
rz(-pi/2) q[25];
cx q[4],q[7];
rz(pi/4) q[7];
cx q[4],q[7];
sx q[4];
rz(pi/2) q[4];
rz(3*pi/4) q[7];
sx q[7];
cx q[10],q[7];
sx q[10];
rz(pi/2) q[7];
cx q[10],q[7];
sx q[10];
rz(3*pi/8) q[7];
cx q[10],q[7];
rz(-1.980117364853203) q[10];
sx q[10];
cx q[12],q[10];
rz(-pi/2) q[10];
sx q[12];
cx q[12],q[10];
rz(7*pi/16) q[10];
sx q[12];
cx q[12],q[10];
rz(2.3728164465519272) q[10];
x q[12];
rz(0.5724266661885027) q[12];
cx q[12],q[15];
cx q[15],q[12];
cx q[12],q[15];
cx q[12],q[13];
cx q[13],q[12];
cx q[12],q[13];
rz(-pi/256) q[12];
cx q[14],q[13];
rz(pi/128) q[13];
cx q[14],q[13];
rz(-pi/128) q[13];
cx q[12],q[13];
rz(pi/256) q[13];
cx q[12],q[13];
rz(-pi/256) q[13];
cx q[13],q[14];
cx q[14],q[13];
cx q[13],q[14];
cx q[11],q[14];
rz(pi/512) q[14];
cx q[11],q[14];
rz(0.779262240245906) q[14];
sx q[14];
rz(pi/4) q[15];
sx q[15];
cx q[16],q[14];
rz(-pi/2) q[14];
sx q[16];
cx q[16],q[14];
rz(1.5677283652191247) q[14];
sx q[16];
cx q[16],q[14];
x q[14];
rz(pi/4) q[14];
rz(0.7823302018216767) q[16];
cx q[18],q[15];
rz(-pi/2) q[15];
sx q[18];
cx q[18],q[15];
rz(1.4726215563702159) q[15];
sx q[18];
cx q[18],q[15];
x q[15];
rz(pi/4) q[15];
cx q[15],q[12];
cx q[12],q[15];
cx q[15],q[12];
rz(0.6872233929727676) q[18];
cx q[21],q[18];
cx q[18],q[21];
cx q[21],q[18];
cx q[18],q[15];
cx q[15],q[18];
cx q[18],q[15];
sx q[15];
rz(pi/2) q[15];
rz(-pi/4) q[7];
cx q[7],q[4];
rz(pi/4) q[4];
cx q[7],q[4];
rz(-pi/4) q[4];
sx q[7];
rz(pi/2) q[7];
cx q[10],q[7];
cx q[7],q[10];
cx q[10],q[7];
rz(pi/8) q[7];
cx q[7],q[4];
rz(pi/8) q[4];
cx q[7],q[4];
rz(-pi/8) q[4];
cx q[7],q[10];
rz(pi/4) q[10];
cx q[7],q[10];
rz(-pi/4) q[10];
cx q[12],q[10];
cx q[10],q[12];
cx q[12],q[10];
sx q[7];
rz(pi/2) q[7];
cx q[6],q[7];
cx q[7],q[6];
cx q[6],q[7];
cx q[7],q[10];
cx q[10],q[7];
cx q[7],q[10];
cx q[10],q[12];
cx q[12],q[10];
cx q[10],q[12];
rz(-pi/262144) q[12];
sx q[12];
cx q[15],q[12];
rz(-pi/2) q[12];
sx q[15];
cx q[15],q[12];
rz(pi/2) q[12];
sx q[15];
cx q[15],q[12];
x q[12];
rz(-1.1984224905781105e-05) q[12];
rz(pi/2) q[15];
cx q[15],q[18];
cx q[18],q[15];
cx q[15],q[18];
cx q[18],q[21];
cx q[21],q[18];
cx q[18],q[21];
cx q[18],q[15];
cx q[15],q[18];
cx q[18],q[15];
rz(-pi/128) q[18];
rz(-pi/131072) q[21];
sx q[21];
cx q[23],q[21];
rz(-pi/2) q[21];
sx q[23];
cx q[23],q[21];
rz(pi/2) q[21];
sx q[23];
cx q[23],q[21];
rz(-3.1415686851399824) q[21];
sx q[21];
rz(-pi/2) q[21];
rz(1.5707483898952752) q[23];
sx q[23];
cx q[24],q[23];
rz(-pi/2) q[23];
sx q[24];
cx q[24],q[23];
rz(pi/2) q[23];
sx q[24];
cx q[24],q[23];
rz(-3.1415447166901718) q[23];
sx q[23];
rz(-pi/2) q[23];
rz(-1.5708922005941393) q[24];
sx q[24];
rz(-pi) q[24];
cx q[25],q[24];
rz(-pi/2) q[24];
sx q[25];
cx q[25],q[24];
rz(1.5707004529956539) q[24];
sx q[25];
cx q[25],q[24];
rz(-1.5708922005941393) q[24];
sx q[24];
rz(pi/2) q[24];
x q[25];
rz(-pi/2) q[25];
cx q[25],q[22];
cx q[22],q[25];
cx q[25],q[22];
rz(-pi/16384) q[22];
cx q[22],q[19];
rz(pi/16384) q[19];
cx q[22],q[19];
rz(-pi/16384) q[19];
cx q[16],q[19];
cx q[19],q[16];
cx q[16],q[19];
cx q[20],q[19];
rz(pi/2048) q[19];
cx q[20],q[19];
rz(-pi/2048) q[19];
cx q[25],q[22];
cx q[22],q[25];
cx q[25],q[22];
rz(-pi/4096) q[22];
cx q[22],q[19];
rz(pi/4096) q[19];
cx q[22],q[19];
rz(-pi/4096) q[19];
cx q[25],q[22];
cx q[22],q[25];
cx q[25],q[22];
rz(-pi/8192) q[22];
cx q[22],q[19];
rz(pi/8192) q[19];
cx q[22],q[19];
rz(-pi/8192) q[19];
rz(pi/16) q[7];
cx q[7],q[4];
rz(pi/16) q[4];
cx q[7],q[4];
rz(-pi/16) q[4];
cx q[1],q[4];
cx q[4],q[1];
cx q[1],q[4];
cx q[2],q[1];
cx q[1],q[2];
cx q[2],q[1];
cx q[7],q[10];
rz(pi/8) q[10];
cx q[7],q[10];
rz(-pi/8) q[10];
cx q[12],q[10];
cx q[10],q[12];
cx q[12],q[10];
sx q[10];
rz(pi/2) q[10];
cx q[13],q[12];
cx q[12],q[13];
cx q[13],q[12];
rz(-pi/64) q[12];
cx q[12],q[15];
rz(pi/64) q[15];
cx q[12],q[15];
rz(-pi/64) q[15];
cx q[18],q[15];
rz(pi/128) q[15];
cx q[18],q[15];
rz(-pi/128) q[15];
cx q[7],q[6];
rz(pi/4) q[6];
cx q[7],q[6];
rz(-pi/4) q[6];
sx q[7];
rz(pi/2) q[7];
cx q[4],q[7];
cx q[7],q[4];
cx q[4],q[7];
cx q[1],q[4];
cx q[4],q[1];
cx q[1],q[4];
cx q[2],q[1];
cx q[1],q[2];
cx q[2],q[1];
rz(-pi/1048576) q[4];
sx q[4];
rz(-pi/524288) q[7];
sx q[7];
cx q[10],q[7];
sx q[10];
rz(-pi/2) q[7];
cx q[10],q[7];
sx q[10];
rz(pi/2) q[7];
cx q[10],q[7];
rz(pi/2) q[10];
cx q[10],q[12];
cx q[12],q[10];
cx q[10],q[12];
cx q[12],q[15];
cx q[15],q[12];
cx q[12],q[15];
cx q[12],q[13];
cx q[13],q[12];
cx q[12],q[13];
cx q[13],q[14];
cx q[14],q[13];
cx q[13],q[14];
cx q[11],q[14];
rz(-pi/512) q[13];
rz(pi/256) q[14];
cx q[11],q[14];
rz(-pi/256) q[14];
cx q[13],q[14];
rz(pi/512) q[14];
cx q[13],q[14];
rz(-pi/512) q[14];
cx q[15],q[18];
cx q[18],q[15];
cx q[15],q[18];
rz(-pi/262144) q[18];
sx q[18];
cx q[21],q[18];
rz(-pi/2) q[18];
sx q[21];
cx q[21],q[18];
rz(pi/2) q[18];
sx q[21];
cx q[21],q[18];
x q[18];
rz(-1.1984224904892926e-05) q[18];
rz(1.5707723583450868) q[21];
sx q[21];
cx q[23],q[21];
rz(-pi/2) q[21];
sx q[23];
cx q[23],q[21];
rz(pi/2) q[21];
sx q[23];
cx q[23],q[21];
x q[21];
rz(-2.396844981067403e-05) q[21];
cx q[21],q[18];
cx q[18],q[21];
cx q[21],q[18];
rz(1.5707483898952752) q[23];
sx q[23];
cx q[24],q[23];
rz(-pi/2) q[23];
sx q[24];
cx q[24],q[23];
rz(pi/2) q[23];
sx q[24];
cx q[24],q[23];
x q[23];
rz(-4.793689962134806e-05) q[23];
cx q[23],q[21];
cx q[21],q[23];
cx q[23],q[21];
rz(pi/2) q[24];
cx q[24],q[25];
cx q[25],q[24];
cx q[24],q[25];
rz(-pi/2048) q[24];
cx q[25],q[22];
cx q[22],q[25];
cx q[25],q[22];
cx q[22],q[19];
cx q[19],q[22];
cx q[22],q[19];
rz(-0.0002876213977285577) q[19];
cx q[19],q[16];
rz(pi/32768) q[16];
cx q[19],q[16];
rz(-pi/32768) q[16];
cx q[14],q[16];
cx q[16],q[14];
cx q[14],q[16];
cx q[19],q[22];
rz(pi/16384) q[22];
cx q[19],q[22];
cx q[16],q[19];
cx q[19],q[16];
cx q[16],q[19];
cx q[20],q[19];
rz(pi/1024) q[19];
cx q[20],q[19];
rz(-pi/1024) q[19];
rz(-pi/16384) q[22];
cx q[19],q[22];
cx q[22],q[19];
cx q[19],q[22];
cx q[16],q[19];
cx q[19],q[16];
cx q[16],q[19];
cx q[22],q[25];
cx q[25],q[22];
cx q[22],q[25];
rz(-pi/4096) q[22];
cx q[24],q[25];
rz(pi/2048) q[25];
cx q[24],q[25];
rz(-pi/2048) q[25];
cx q[22],q[25];
rz(pi/4096) q[25];
cx q[22],q[25];
cx q[19],q[22];
cx q[22],q[19];
cx q[19],q[22];
rz(-pi/8192) q[22];
rz(-pi/4096) q[25];
cx q[22],q[25];
rz(pi/8192) q[25];
cx q[22],q[25];
rz(-pi/8192) q[25];
cx q[25],q[24];
cx q[24],q[25];
cx q[25],q[24];
cx q[24],q[23];
cx q[23],q[24];
cx q[24],q[23];
cx q[25],q[22];
cx q[22],q[25];
cx q[25],q[22];
rz(-pi/1024) q[22];
rz(-pi/4096) q[25];
sx q[25];
rz(pi/2) q[25];
rz(-3.14158666147734) q[7];
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
rz(-2.9960562262232315e-06) q[4];
cx q[1],q[4];
cx q[4],q[1];
cx q[1],q[4];
cx q[2],q[1];
cx q[1],q[2];
cx q[2],q[1];
rz(pi/2) q[7];
cx q[7],q[10];
cx q[10],q[7];
cx q[7],q[10];
cx q[10],q[12];
cx q[12],q[10];
cx q[10],q[12];
cx q[12],q[15];
cx q[15],q[12];
cx q[12],q[15];
rz(-0.0007609982814901423) q[15];
cx q[15],q[18];
rz(pi/262144) q[18];
cx q[15],q[18];
rz(-pi/262144) q[18];
cx q[21],q[18];
cx q[18],q[21];
cx q[21],q[18];
cx q[15],q[18];
rz(pi/131072) q[18];
cx q[15],q[18];
rz(-pi/131072) q[18];
cx q[23],q[21];
cx q[21],q[23];
cx q[23],q[21];
cx q[21],q[18];
cx q[18],q[21];
cx q[21],q[18];
rz(-0.6872233929727672) q[7];
cx q[7],q[4];
rz(pi/32) q[4];
cx q[7],q[4];
rz(-pi/32) q[4];
cx q[7],q[10];
rz(pi/16) q[10];
cx q[7],q[10];
rz(-pi/16) q[10];
cx q[12],q[10];
cx q[10],q[12];
cx q[12],q[10];
rz(-0.14726215563702155) q[10];
cx q[7],q[6];
rz(pi/8) q[6];
cx q[7],q[6];
cx q[4],q[7];
rz(-pi/8) q[6];
cx q[7],q[4];
cx q[4],q[7];
cx q[10],q[7];
rz(pi/4) q[4];
cx q[4],q[1];
rz(pi/4) q[1];
cx q[4],q[1];
rz(-pi/4) q[1];
sx q[4];
rz(pi/2) q[4];
rz(pi/64) q[7];
cx q[10],q[7];
cx q[10],q[12];
rz(pi/32) q[12];
cx q[10],q[12];
rz(-pi/32) q[12];
rz(-pi/64) q[7];
cx q[7],q[10];
cx q[10],q[7];
cx q[7],q[10];
cx q[10],q[12];
cx q[12],q[10];
cx q[10],q[12];
cx q[12],q[13];
cx q[13],q[12];
cx q[12],q[13];
cx q[13],q[14];
cx q[14],q[13];
cx q[13],q[14];
cx q[11],q[14];
cx q[13],q[12];
cx q[12],q[13];
cx q[13],q[12];
rz(-pi/256) q[13];
rz(pi/128) q[14];
cx q[11],q[14];
rz(-pi/128) q[14];
cx q[13],q[14];
rz(pi/256) q[14];
cx q[13],q[14];
rz(-pi/256) q[14];
cx q[14],q[16];
cx q[15],q[12];
rz(pi/65536) q[12];
cx q[15],q[12];
rz(-pi/65536) q[12];
cx q[10],q[12];
cx q[12],q[10];
cx q[10],q[12];
cx q[12],q[13];
cx q[13],q[12];
cx q[12],q[13];
cx q[16],q[14];
cx q[14],q[16];
cx q[13],q[14];
cx q[14],q[13];
cx q[13],q[14];
cx q[11],q[14];
cx q[13],q[12];
cx q[12],q[13];
cx q[13],q[12];
rz(-pi/128) q[13];
rz(pi/64) q[14];
cx q[11],q[14];
rz(-pi/64) q[14];
cx q[13],q[14];
rz(pi/128) q[14];
cx q[13],q[14];
rz(-pi/128) q[14];
cx q[15],q[12];
rz(pi/32768) q[12];
cx q[15],q[12];
rz(-pi/32768) q[12];
cx q[15],q[18];
cx q[16],q[19];
rz(pi/16384) q[18];
cx q[15],q[18];
rz(-pi/16384) q[18];
cx q[19],q[16];
cx q[16],q[19];
rz(-pi/2048) q[16];
cx q[20],q[19];
rz(pi/512) q[19];
cx q[20],q[19];
rz(-pi/512) q[19];
cx q[22],q[19];
rz(pi/1024) q[19];
cx q[22],q[19];
rz(-pi/1024) q[19];
cx q[16],q[19];
rz(pi/2048) q[19];
cx q[16],q[19];
cx q[14],q[16];
cx q[16],q[14];
cx q[14],q[16];
rz(-pi/2048) q[19];
cx q[19],q[22];
cx q[22],q[19];
cx q[19],q[22];
cx q[16],q[19];
cx q[19],q[16];
cx q[16],q[19];
rz(-pi/512) q[16];
cx q[20],q[19];
rz(pi/256) q[19];
cx q[20],q[19];
rz(-pi/256) q[19];
cx q[16],q[19];
rz(pi/512) q[19];
cx q[16],q[19];
rz(-pi/512) q[19];
sx q[22];
cx q[25],q[22];
rz(-pi/2) q[22];
sx q[25];
cx q[25],q[22];
rz(1.5700293364009532) q[22];
sx q[25];
cx q[25],q[22];
x q[22];
rz(-0.7900001057611057) q[22];
rz(1.570029336400954) q[25];
cx q[25],q[24];
cx q[24],q[25];
cx q[25],q[24];
cx q[24],q[23];
cx q[23],q[24];
cx q[24],q[23];
cx q[23],q[21];
cx q[21],q[23];
cx q[23],q[21];
cx q[21],q[18];
cx q[18],q[21];
cx q[21],q[18];
cx q[15],q[18];
rz(pi/8192) q[18];
cx q[15],q[18];
rz(-pi/8192) q[18];
rz(-pi/16) q[7];
cx q[7],q[6];
rz(pi/16) q[6];
cx q[7],q[6];
rz(-pi/16) q[6];
cx q[7],q[4];
cx q[4],q[7];
cx q[7],q[4];
rz(pi/8) q[4];
cx q[4],q[1];
rz(pi/8) q[1];
cx q[4],q[1];
rz(-pi/8) q[1];
cx q[1],q[2];
cx q[2],q[1];
cx q[1],q[2];
cx q[2],q[3];
cx q[3],q[2];
cx q[2],q[3];
cx q[3],q[5];
cx q[4],q[7];
cx q[5],q[3];
cx q[3],q[5];
cx q[5],q[8];
rz(pi/4) q[7];
cx q[4],q[7];
sx q[4];
rz(pi/2) q[4];
rz(-pi/4) q[7];
cx q[6],q[7];
cx q[7],q[6];
cx q[6],q[7];
cx q[7],q[10];
cx q[10],q[7];
cx q[7],q[10];
cx q[10],q[12];
cx q[12],q[10];
cx q[10],q[12];
cx q[12],q[13];
cx q[13],q[12];
cx q[12],q[13];
rz(-pi/64) q[12];
cx q[13],q[14];
cx q[14],q[13];
cx q[13],q[14];
cx q[11],q[14];
rz(pi/32) q[14];
cx q[11],q[14];
rz(-pi/32) q[14];
cx q[13],q[14];
cx q[14],q[13];
cx q[13],q[14];
cx q[12],q[13];
rz(pi/64) q[13];
cx q[12],q[13];
rz(-pi/64) q[13];
cx q[14],q[16];
cx q[16],q[14];
cx q[14],q[16];
cx q[13],q[14];
cx q[14],q[13];
cx q[13],q[14];
rz(-pi/1024) q[16];
cx q[16],q[19];
rz(pi/1024) q[19];
cx q[16],q[19];
cx q[14],q[16];
cx q[16],q[14];
cx q[14],q[16];
rz(-pi/1024) q[19];
cx q[22],q[19];
rz(pi/2048) q[19];
cx q[22],q[19];
rz(-pi/2048) q[19];
cx q[20],q[19];
cx q[19],q[20];
cx q[20],q[19];
rz(-pi/128) q[19];
cx q[19],q[16];
rz(pi/128) q[16];
cx q[19],q[16];
rz(-pi/128) q[16];
sx q[22];
rz(pi/2) q[22];
cx q[6],q[7];
cx q[7],q[6];
cx q[6],q[7];
cx q[7],q[10];
cx q[10],q[7];
cx q[7],q[10];
rz(-pi) q[10];
sx q[10];
cx q[4],q[7];
cx q[7],q[4];
cx q[4],q[7];
cx q[8],q[5];
cx q[5],q[8];
cx q[11],q[8];
rz(pi/16) q[8];
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
rz(-5*pi/8) q[12];
sx q[12];
rz(pi/2) q[12];
cx q[12],q[10];
rz(pi/2) q[10];
sx q[12];
cx q[12],q[10];
rz(3*pi/8) q[10];
sx q[12];
cx q[12],q[10];
rz(-pi/4) q[10];
cx q[10],q[7];
rz(-1.980117364853203) q[12];
sx q[12];
rz(0.47425189576382254) q[13];
rz(-pi/256) q[14];
cx q[14],q[16];
rz(pi/256) q[16];
cx q[14],q[16];
cx q[11],q[14];
cx q[14],q[11];
cx q[11],q[14];
rz(-pi/512) q[14];
rz(-pi/256) q[16];
cx q[14],q[16];
rz(pi/512) q[16];
cx q[14],q[16];
rz(-pi/512) q[16];
cx q[16],q[19];
cx q[19],q[16];
cx q[16],q[19];
rz(-0.14726215563702155) q[16];
rz(pi/4) q[19];
sx q[19];
cx q[22],q[19];
rz(-pi/2) q[19];
sx q[22];
cx q[22],q[19];
rz(1.5677283652191247) q[19];
sx q[22];
cx q[22],q[19];
rz(-2.3500585670408034) q[19];
sx q[19];
rz(pi/2) q[19];
rz(0.7823302018216776) q[22];
rz(pi/4) q[7];
cx q[10],q[7];
sx q[10];
rz(pi/2) q[10];
rz(-pi/4) q[7];
rz(-pi/16) q[8];
cx q[8],q[11];
cx q[11],q[8];
cx q[8],q[11];
cx q[11],q[14];
cx q[14],q[11];
cx q[11],q[14];
cx q[13],q[14];
rz(pi/32) q[14];
cx q[13],q[14];
sx q[13];
rz(-pi/2) q[13];
cx q[13],q[12];
rz(-pi/2) q[12];
sx q[13];
cx q[13],q[12];
rz(7*pi/16) q[12];
sx q[13];
cx q[13],q[12];
rz(2.3728164465519272) q[12];
cx q[12],q[10];
cx q[10],q[12];
cx q[12],q[10];
rz(pi/8) q[10];
cx q[10],q[7];
x q[13];
rz(0.5724266661885027) q[13];
rz(-pi/32) q[14];
cx q[16],q[14];
rz(pi/64) q[14];
cx q[16],q[14];
rz(-pi/64) q[14];
cx q[14],q[11];
cx q[11],q[14];
cx q[14],q[11];
rz(-pi/256) q[14];
rz(pi/8) q[7];
cx q[10],q[7];
cx q[10],q[12];
rz(pi/4) q[12];
cx q[10],q[12];
sx q[10];
rz(pi/2) q[10];
rz(-pi/4) q[12];
rz(-pi/8) q[7];
cx q[7],q[10];
cx q[10],q[7];
cx q[7],q[10];
rz(-0.07363107781851078) q[8];
cx q[8],q[11];
rz(pi/128) q[11];
cx q[8],q[11];
rz(-pi/128) q[11];
cx q[14],q[11];
rz(pi/256) q[11];
cx q[14],q[11];
rz(-pi/256) q[11];
cx q[13],q[14];
cx q[14],q[13];
cx q[13],q[14];
cx q[16],q[14];
rz(pi/32) q[14];
cx q[16],q[14];
rz(-pi/32) q[14];
cx q[11],q[14];
cx q[14],q[11];
cx q[11],q[14];
cx q[14],q[16];
cx q[16],q[14];
cx q[14],q[16];
cx q[13],q[14];
cx q[14],q[13];
cx q[13],q[14];
cx q[13],q[12];
cx q[12],q[13];
cx q[13],q[12];
rz(-3*pi/16) q[12];
cx q[12],q[10];
rz(pi/16) q[10];
cx q[12],q[10];
rz(-pi/16) q[10];
cx q[12],q[13];
rz(pi/8) q[13];
cx q[12],q[13];
cx q[12],q[10];
cx q[10],q[12];
cx q[12],q[10];
rz(pi/4) q[10];
cx q[10],q[7];
rz(-pi/8) q[13];
cx q[12],q[13];
cx q[13],q[12];
cx q[12],q[13];
rz(-pi/128) q[14];
cx q[15],q[12];
cx q[12],q[15];
cx q[15],q[12];
rz(-pi) q[16];
sx q[16];
rz(-pi) q[16];
cx q[19],q[16];
rz(-pi/2) q[16];
sx q[19];
cx q[19],q[16];
rz(1.5646604036433531) q[16];
sx q[19];
cx q[19],q[16];
rz(-pi/2) q[16];
x q[19];
rz(-1.5769322499464389) q[19];
cx q[20],q[19];
cx q[19],q[20];
cx q[20],q[19];
rz(pi/4) q[7];
cx q[10],q[7];
sx q[10];
rz(pi/2) q[10];
rz(-pi/4) q[7];
cx q[8],q[11];
rz(pi/64) q[11];
cx q[8],q[11];
rz(-pi/64) q[11];
cx q[14],q[11];
rz(pi/128) q[11];
cx q[14],q[11];
rz(-pi/128) q[11];
cx q[16],q[14];
cx q[14],q[16];
cx q[16],q[14];
rz(-pi/256) q[14];
cx q[14],q[11];
rz(pi/256) q[11];
cx q[14],q[11];
rz(-pi/256) q[11];
cx q[13],q[14];
cx q[14],q[13];
cx q[13],q[14];
rz(-pi/128) q[13];
rz(-pi/64) q[16];
cx q[8],q[11];
cx q[11],q[8];
cx q[8],q[11];
rz(-pi/32) q[11];
cx q[11],q[14];
rz(pi/32) q[14];
cx q[11],q[14];
rz(-pi/32) q[14];
cx q[16],q[14];
rz(pi/64) q[14];
cx q[16],q[14];
rz(-pi/64) q[14];
cx q[13],q[14];
rz(pi/128) q[14];
cx q[13],q[14];
cx q[12],q[13];
cx q[13],q[12];
cx q[12],q[13];
rz(-pi/128) q[14];
cx q[11],q[14];
cx q[14],q[11];
cx q[11],q[14];
cx q[13],q[14];
cx q[14],q[13];
cx q[13],q[14];
cx q[13],q[12];
cx q[12],q[13];
cx q[13],q[12];
rz(-pi/16) q[12];
cx q[12],q[15];
cx q[14],q[16];
rz(pi/16) q[15];
cx q[12],q[15];
cx q[12],q[10];
cx q[10],q[12];
cx q[12],q[10];
rz(pi/8) q[10];
cx q[10],q[7];
rz(-pi/16) q[15];
cx q[16],q[14];
cx q[14],q[16];
rz(-0.005368932757599744) q[16];
cx q[16],q[19];
rz(pi/4096) q[19];
cx q[16],q[19];
rz(-pi/4096) q[19];
cx q[22],q[19];
cx q[19],q[22];
cx q[22],q[19];
cx q[16],q[19];
rz(pi/2048) q[19];
cx q[16],q[19];
rz(-pi/2048) q[19];
cx q[20],q[19];
cx q[19],q[20];
cx q[20],q[19];
cx q[16],q[19];
rz(pi/1024) q[19];
cx q[16],q[19];
cx q[16],q[14];
cx q[14],q[16];
cx q[16],q[14];
rz(-0.018407769454627694) q[14];
rz(-pi/1024) q[19];
rz(pi/8) q[7];
cx q[10],q[7];
cx q[10],q[12];
rz(pi/4) q[12];
cx q[10],q[12];
sx q[10];
rz(pi/2) q[10];
rz(-pi/4) q[12];
cx q[15],q[12];
cx q[12],q[15];
cx q[15],q[12];
cx q[12],q[13];
cx q[13],q[12];
cx q[12],q[13];
rz(-pi/64) q[12];
rz(-pi/8) q[7];
cx q[7],q[10];
cx q[10],q[7];
cx q[7],q[10];
cx q[8],q[11];
cx q[11],q[8];
cx q[8],q[11];
cx q[14],q[11];
rz(pi/512) q[11];
cx q[14],q[11];
rz(-pi/512) q[11];
cx q[8],q[11];
cx q[11],q[8];
cx q[8],q[11];
cx q[14],q[11];
rz(pi/256) q[11];
cx q[14],q[11];
rz(-pi/256) q[11];
cx q[16],q[14];
cx q[14],q[16];
cx q[16],q[14];
rz(-pi/32) q[14];
cx q[14],q[13];
rz(pi/32) q[13];
cx q[14],q[13];
rz(-pi/32) q[13];
cx q[12],q[13];
rz(pi/64) q[13];
cx q[12],q[13];
rz(-pi/64) q[13];
cx q[14],q[13];
cx q[13],q[14];
cx q[14],q[13];
cx q[13],q[12];
cx q[12],q[13];
cx q[13],q[12];
rz(-3*pi/16) q[12];
cx q[12],q[10];
rz(pi/16) q[10];
cx q[12],q[10];
rz(-pi/16) q[10];
cx q[12],q[15];
rz(-0.8835729338221299) q[13];
sx q[13];
rz(pi/2) q[13];
sx q[14];
rz(pi/8) q[15];
cx q[12],q[15];
cx q[12],q[10];
cx q[10],q[12];
cx q[12],q[10];
rz(pi/4) q[10];
cx q[10],q[7];
rz(pi/4) q[12];
sx q[12];
cx q[13],q[12];
rz(-pi/2) q[12];
sx q[13];
cx q[13],q[12];
rz(1.4726215563702159) q[12];
sx q[13];
cx q[13],q[12];
x q[12];
rz(3*pi/16) q[12];
rz(0.6872233929727676) q[13];
sx q[13];
rz(-pi/8) q[15];
cx q[12],q[15];
rz(pi/16) q[15];
cx q[12],q[15];
rz(-pi/16) q[15];
rz(-pi/128) q[16];
sx q[16];
rz(pi/2) q[16];
cx q[16],q[14];
rz(-pi/2) q[14];
sx q[16];
cx q[16],q[14];
rz(1.546252634188727) q[14];
sx q[16];
cx q[16],q[14];
rz(-3.0925052683774528) q[14];
sx q[14];
rz(-pi/2) q[14];
cx q[14],q[13];
rz(-pi/2) q[13];
sx q[14];
cx q[14],q[13];
rz(1.5217089415825562) q[13];
sx q[14];
cx q[14],q[13];
x q[13];
rz(1.5217089415825562) q[14];
rz(1.5462526341887264) q[16];
rz(pi/4) q[7];
cx q[10],q[7];
sx q[10];
rz(pi/2) q[10];
cx q[12],q[10];
cx q[10],q[12];
cx q[12],q[10];
rz(pi/8) q[10];
rz(-pi/4) q[7];
cx q[10],q[7];
rz(pi/8) q[7];
cx q[10],q[7];
cx q[10],q[12];
rz(pi/4) q[12];
cx q[10],q[12];
sx q[10];
rz(pi/2) q[10];
rz(-pi/4) q[12];
cx q[13],q[12];
cx q[12],q[13];
cx q[13],q[12];
rz(-0.6872233929727672) q[12];
cx q[12],q[15];
rz(pi/32) q[15];
cx q[12],q[15];
rz(-pi/32) q[15];
rz(-pi/8) q[7];
cx q[7],q[10];
cx q[10],q[7];
cx q[7],q[10];
cx q[12],q[10];
rz(pi/16) q[10];
cx q[12],q[10];
rz(-pi/16) q[10];
cx q[12],q[13];
rz(pi/8) q[13];
cx q[12],q[13];
cx q[12],q[10];
cx q[10],q[12];
cx q[12],q[10];
rz(pi/4) q[10];
cx q[10],q[7];
rz(-pi/8) q[13];
rz(pi/4) q[7];
cx q[10],q[7];
sx q[10];
rz(pi/2) q[10];
rz(-pi/4) q[7];
barrier q[1],q[25],q[24],q[23],q[6],q[4],q[21],q[18],q[22],q[20],q[19],q[8],q[11],q[16],q[14],q[15],q[12],q[13],q[7],q[10],q[2];
measure q[1] -> c[0];
measure q[25] -> c[1];
measure q[24] -> c[2];
measure q[23] -> c[3];
measure q[6] -> c[4];
measure q[4] -> c[5];
measure q[21] -> c[6];
measure q[18] -> c[7];
measure q[22] -> c[8];
measure q[20] -> c[9];
measure q[19] -> c[10];
measure q[8] -> c[11];
measure q[11] -> c[12];
measure q[16] -> c[13];
measure q[14] -> c[14];
measure q[15] -> c[15];
measure q[12] -> c[16];
measure q[13] -> c[17];
measure q[7] -> c[18];
measure q[10] -> c[19];

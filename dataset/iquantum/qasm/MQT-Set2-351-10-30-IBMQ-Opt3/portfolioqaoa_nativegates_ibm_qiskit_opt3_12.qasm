// Benchmark was created by MQT Bench on 2023-06-29
// For more information about MQT Bench, please visit https://www.cda.cit.tum.de/mqtbench/
// MQT Bench version: v1.0.0
// Qiskit version: {'qiskit-terra': '0.24.1', 'qiskit-aer': '0.12.0', 'qiskit-ignis': None, 'qiskit-ibmq-provider': '0.20.2', 'qiskit': '0.43.1', 'qiskit-nature': '0.6.2', 'qiskit-finance': '0.3.4', 'qiskit-optimization': '0.5.0', 'qiskit-machine-learning': '0.6.1'}
// Used Gate Set: ['rz', 'sx', 'x', 'cx', 'measure']

OPENQASM 2.0;
include "qelib1.inc";
qreg q[12];
creg meas[12];
creg meas69[12];
rz(pi/2) q[0];
sx q[0];
rz(3.142134015222707) q[0];
rz(pi/2) q[1];
sx q[1];
rz(1.5655145474657637) q[1];
cx q[0],q[1];
rz(0.07445966809789036) q[1];
cx q[0],q[1];
rz(pi/2) q[2];
sx q[2];
rz(1.5705197532850903) q[2];
cx q[0],q[2];
rz(0.07454966809857523) q[2];
cx q[0],q[2];
cx q[1],q[2];
rz(0.07459413552985703) q[2];
cx q[1],q[2];
rz(pi/2) q[3];
sx q[3];
rz(1.5709878132719357) q[3];
cx q[0],q[3];
rz(0.07456193322686651) q[3];
cx q[0],q[3];
cx q[1],q[3];
rz(0.0745466961834478) q[3];
cx q[1],q[3];
cx q[2],q[3];
rz(0.07455212639952179) q[3];
cx q[2],q[3];
rz(pi/2) q[4];
sx q[4];
rz(1.568019733558737) q[4];
cx q[0],q[4];
rz(0.07458296228787073) q[4];
cx q[0],q[4];
cx q[1],q[4];
rz(0.07475295426407998) q[4];
cx q[1],q[4];
cx q[2],q[4];
rz(0.07436135679667692) q[4];
cx q[2],q[4];
cx q[3],q[4];
rz(0.07456984436414955) q[4];
cx q[3],q[4];
rz(pi/2) q[5];
sx q[5];
rz(1.571737421879706) q[5];
cx q[0],q[5];
rz(0.07456932395383553) q[5];
cx q[0],q[5];
cx q[1],q[5];
rz(0.07450554340778216) q[5];
cx q[1],q[5];
cx q[2],q[5];
rz(0.07470961797356436) q[5];
cx q[2],q[5];
cx q[3],q[5];
rz(0.0745489718601384) q[5];
cx q[3],q[5];
cx q[4],q[5];
rz(0.07433314283170729) q[5];
cx q[4],q[5];
rz(pi/2) q[6];
sx q[6];
rz(1.5708179214776088) q[6];
cx q[0],q[6];
rz(0.0745565862955105) q[6];
cx q[0],q[6];
cx q[1],q[6];
rz(0.07459031460593217) q[6];
cx q[1],q[6];
cx q[2],q[6];
rz(0.07455754915325767) q[6];
cx q[2],q[6];
cx q[3],q[6];
rz(0.07455400292533569) q[6];
cx q[3],q[6];
cx q[4],q[6];
rz(0.07453930800175021) q[6];
cx q[4],q[6];
cx q[5],q[6];
rz(0.07455510465787246) q[6];
cx q[5],q[6];
rz(pi/2) q[7];
sx q[7];
rz(1.5710492386920736) q[7];
cx q[0],q[7];
rz(0.07455350333850727) q[7];
cx q[0],q[7];
cx q[1],q[7];
rz(0.0745467879879796) q[7];
cx q[1],q[7];
cx q[2],q[7];
rz(0.07454994473611372) q[7];
cx q[2],q[7];
cx q[3],q[7];
rz(0.07455374251615869) q[7];
cx q[3],q[7];
cx q[4],q[7];
rz(0.07451489231286537) q[7];
cx q[4],q[7];
cx q[5],q[7];
rz(0.0745532445819428) q[7];
cx q[5],q[7];
cx q[6],q[7];
rz(0.07455227859106485) q[7];
cx q[6],q[7];
rz(pi/2) q[8];
sx q[8];
rz(1.5706684843883076) q[8];
cx q[0],q[8];
rz(0.07455738947394036) q[8];
cx q[0],q[8];
cx q[1],q[8];
rz(0.07454516665790119) q[8];
cx q[1],q[8];
cx q[2],q[8];
rz(0.07456045447559821) q[8];
cx q[2],q[8];
cx q[3],q[8];
rz(0.07455445645939124) q[8];
cx q[3],q[8];
cx q[4],q[8];
rz(0.07452913156565495) q[8];
cx q[4],q[8];
cx q[5],q[8];
rz(0.074569415139454) q[8];
cx q[5],q[8];
cx q[6],q[8];
rz(0.07455457244520701) q[8];
cx q[6],q[8];
cx q[7],q[8];
rz(0.07455290456080359) q[8];
cx q[7],q[8];
rz(pi/2) q[9];
sx q[9];
rz(1.5709514955681048) q[9];
cx q[0],q[9];
rz(0.07454669487421457) q[9];
cx q[0],q[9];
cx q[1],q[9];
rz(0.07455427179597715) q[9];
cx q[1],q[9];
cx q[2],q[9];
rz(0.07455194966604753) q[9];
cx q[2],q[9];
cx q[3],q[9];
rz(0.07455349730899194) q[9];
cx q[3],q[9];
cx q[4],q[9];
rz(0.0745354986117159) q[9];
cx q[4],q[9];
cx q[5],q[9];
rz(0.07455598309704058) q[9];
cx q[5],q[9];
cx q[6],q[9];
rz(0.07455431777116195) q[9];
cx q[6],q[9];
cx q[7],q[9];
rz(0.07455402936317873) q[9];
cx q[7],q[9];
cx q[8],q[9];
rz(0.07455204470894852) q[9];
cx q[8],q[9];
rz(pi/2) q[10];
sx q[10];
rz(1.5705570991163205) q[10];
cx q[0],q[10];
rz(0.07456270329336802) q[10];
cx q[0],q[10];
cx q[1],q[10];
rz(0.07453966129959813) q[10];
cx q[1],q[10];
cx q[2],q[10];
rz(0.07455371954213161) q[10];
cx q[2],q[10];
cx q[3],q[10];
rz(0.07455453261124065) q[10];
cx q[3],q[10];
cx q[4],q[10];
rz(0.07460188796637607) q[10];
cx q[4],q[10];
cx q[5],q[10];
rz(0.07456218227621454) q[10];
cx q[5],q[10];
cx q[6],q[10];
rz(0.07455478200027268) q[10];
cx q[6],q[10];
cx q[7],q[10];
rz(0.07455232449215415) q[10];
cx q[7],q[10];
cx q[8],q[10];
rz(0.07455356841333385) q[10];
cx q[8],q[10];
cx q[9],q[10];
rz(0.07455383672722654) q[10];
cx q[9],q[10];
rz(pi/2) q[11];
sx q[11];
rz(1.5717799435555655) q[11];
cx q[0],q[11];
rz(0.0745126794704471) q[11];
cx q[0],q[11];
sx q[0];
rz(-0.6603933585274309) q[0];
sx q[0];
rz(3.0925791120686075) q[0];
cx q[1],q[11];
rz(0.07455283225191771) q[11];
cx q[1],q[11];
rz(pi/2) q[1];
sx q[1];
rz(-0.6603933585274309) q[1];
sx q[1];
rz(2.0489955465297864) q[1];
cx q[0],q[1];
rz(-6.7413939446003885) q[1];
cx q[0],q[1];
cx q[2],q[11];
rz(0.07458687408195439) q[11];
cx q[2],q[11];
rz(pi/2) q[2];
sx q[2];
rz(-0.6603933585274309) q[2];
sx q[2];
rz(1.5958366074175725) q[2];
cx q[0],q[2];
rz(-6.749542321770609) q[2];
cx q[0],q[2];
cx q[1],q[2];
rz(-6.7535682928719165) q[2];
cx q[1],q[2];
cx q[3],q[11];
rz(0.07455216915640077) q[11];
cx q[3],q[11];
rz(pi/2) q[3];
sx q[3];
rz(-0.6603933585274309) q[3];
sx q[3];
rz(1.5534596153944342) q[3];
cx q[0],q[3];
rz(-6.750652776110586) q[3];
cx q[0],q[3];
cx q[1],q[3];
rz(-6.749273251935143) q[3];
cx q[1],q[3];
cx q[2],q[3];
rz(-6.749764890250143) q[3];
cx q[2],q[3];
cx q[4],q[11];
rz(0.07436393072999399) q[11];
cx q[4],q[11];
rz(pi/2) q[4];
sx q[4];
rz(-0.6603933585274309) q[4];
sx q[4];
rz(1.8221822019542646) q[4];
cx q[0],q[4];
rz(-6.752556695213854) q[4];
cx q[0],q[4];
cx q[1],q[4];
rz(-6.767947347741892) q[4];
cx q[1],q[4];
cx q[2],q[4];
rz(-6.732493082863875) q[4];
cx q[2],q[4];
cx q[3],q[4];
rz(-6.751369030887665) q[4];
cx q[3],q[4];
cx q[5],q[11];
rz(0.07445876594378609) q[11];
cx q[5],q[11];
rz(pi/2) q[5];
sx q[5];
rz(-0.6603933585274309) q[5];
sx q[5];
rz(1.4855919085091838) q[5];
cx q[0],q[5];
rz(-6.751321914226669) q[5];
cx q[0],q[5];
cx q[1],q[5];
rz(-6.74554738154433) q[5];
cx q[1],q[5];
cx q[2],q[5];
rz(-6.764023787324199) q[5];
cx q[2],q[5];
cx q[3],q[5];
rz(-6.749479286066825) q[5];
cx q[3],q[5];
cx q[4],q[5];
rz(-6.729938660349537) q[5];
cx q[4],q[5];
cx q[6],q[11];
rz(0.07453524211125856) q[11];
cx q[6],q[11];
rz(pi/2) q[6];
sx q[6];
rz(-0.6603933585274309) q[6];
sx q[6];
rz(1.5688411977029695) q[6];
cx q[0],q[6];
rz(-6.750168678187692) q[6];
cx q[0],q[6];
cx q[1],q[6];
rz(-6.753222355882567) q[6];
cx q[1],q[6];
cx q[2],q[6];
rz(-6.750255852943532) q[6];
cx q[2],q[6];
cx q[3],q[6];
rz(-6.749934786249976) q[6];
cx q[3],q[6];
cx q[4],q[6];
rz(-6.748604344261634) q[6];
cx q[4],q[6];
cx q[5],q[6];
rz(-6.750034534385332) q[6];
cx q[5],q[6];
cx q[7],q[11];
rz(0.0745493375691875) q[11];
cx q[7],q[11];
rz(pi/2) q[7];
sx q[7];
rz(-0.6603933585274309) q[7];
sx q[7];
rz(1.5478983099798374) q[7];
cx q[0],q[7];
rz(-6.749889554895795) q[7];
cx q[0],q[7];
cx q[1],q[7];
rz(-6.749281563690093) q[7];
cx q[1],q[7];
cx q[2],q[7];
rz(-6.749567367848231) q[7];
cx q[2],q[7];
cx q[3],q[7];
rz(-6.749911209448013) q[7];
cx q[3],q[7];
cx q[4],q[7];
rz(-6.746393808257294) q[7];
cx q[4],q[7];
cx q[5],q[7];
rz(-6.7498661277172785) q[7];
cx q[5],q[7];
cx q[6],q[7];
rz(-6.749778669295537) q[7];
cx q[6],q[7];
cx q[8],q[11];
rz(0.07453982272287178) q[11];
cx q[8],q[11];
rz(pi/2) q[8];
sx q[8];
rz(-0.6603933585274309) q[8];
sx q[8];
rz(1.5823708616760674) q[8];
cx q[0],q[8];
rz(-6.7502413959736) q[8];
cx q[0],q[8];
cx q[1],q[8];
rz(-6.749134772480139) q[8];
cx q[1],q[8];
cx q[2],q[8];
rz(-6.750518893632994) q[8];
cx q[2],q[8];
cx q[3],q[8];
rz(-6.749975848100155) q[8];
cx q[3],q[8];
cx q[4],q[8];
rz(-6.74768299494028) q[8];
cx q[4],q[8];
cx q[5],q[8];
rz(-6.751330169946738) q[8];
cx q[5],q[8];
cx q[6],q[8];
rz(-6.749986349168665) q[8];
cx q[6],q[8];
cx q[7],q[8];
rz(-6.749835343045421) q[8];
cx q[7],q[8];
cx q[9],q[11];
rz(0.07455751460587987) q[11];
cx q[9],q[11];
cx q[10],q[11];
rz(0.07454247316935447) q[11];
cx q[10],q[11];
rz(pi/2) q[10];
sx q[10];
rz(-0.6603933585274309) q[10];
sx q[10];
rz(1.5924554083477584) q[10];
rz(pi/2) q[11];
sx q[11];
rz(-0.6603933585274309) q[11];
sx q[11];
rz(1.4817421012849827) q[11];
rz(pi/2) q[9];
sx q[9];
rz(-0.6603933585274309) q[9];
sx q[9];
rz(1.5567477303557222) q[9];
cx q[0],q[9];
rz(-6.749273133400409) q[9];
cx q[0],q[9];
cx q[0],q[10];
cx q[1],q[9];
rz(-6.750722496024506) q[10];
cx q[0],q[10];
cx q[0],q[11];
rz(-6.746193462985015) q[11];
cx q[0],q[11];
sx q[0];
rz(-2.681937084604808) q[0];
sx q[0];
rz(3.1413248110449707) q[0];
rz(-6.749959129131976) q[9];
cx q[1],q[9];
cx q[1],q[10];
rz(-6.748636330973816) q[10];
cx q[1],q[10];
cx q[1],q[11];
rz(-6.749828796377972) q[11];
cx q[1],q[11];
rz(pi/2) q[1];
sx q[1];
rz(-2.681937084604808) q[1];
sx q[1];
rz(1.5734095249432167) q[1];
cx q[0],q[1];
rz(-0.036839454031101025) q[1];
cx q[0],q[1];
cx q[2],q[9];
rz(-6.749748889239077) q[9];
cx q[2],q[9];
cx q[2],q[10];
rz(-6.749909129436499) q[10];
cx q[2],q[10];
cx q[2],q[11];
rz(-6.752910859362335) q[11];
cx q[2],q[11];
rz(pi/2) q[2];
sx q[2];
rz(-2.681937084604808) q[2];
sx q[2];
rz(1.5709331635195412) q[2];
cx q[0],q[2];
rz(-0.03688398217597095) q[2];
cx q[0],q[2];
cx q[1],q[2];
rz(-0.03690598275604919) q[2];
cx q[1],q[2];
cx q[3],q[9];
rz(-6.7498890089984105) q[9];
cx q[3],q[9];
cx q[3],q[10];
rz(-6.749982742700004) q[10];
cx q[3],q[10];
cx q[3],q[11];
rz(-6.749768761352075) q[11];
cx q[3],q[11];
rz(pi/2) q[3];
sx q[3];
rz(-2.681937084604808) q[3];
sx q[3];
rz(1.570701587489058) q[3];
cx q[0],q[3];
rz(-0.03689005043602924) q[3];
cx q[0],q[3];
cx q[1],q[3];
rz(-0.03688251179967841) q[3];
cx q[1],q[3];
cx q[2],q[3];
rz(-0.03688519843796927) q[3];
cx q[2],q[3];
cx q[4],q[9];
rz(-6.748259451522168) q[9];
cx q[4],q[9];
cx q[4],q[10];
rz(-6.754270179274856) q[10];
cx q[4],q[10];
cx q[4],q[11];
rz(-6.732726120411862) q[11];
cx q[4],q[11];
rz(pi/2) q[4];
sx q[4];
rz(-2.681937084604808) q[4];
sx q[4];
rz(1.572170066182938) q[4];
cx q[0],q[4];
rz(-0.036900454714559786) q[4];
cx q[0],q[4];
cx q[1],q[4];
rz(-0.03698455946217944) q[4];
cx q[1],q[4];
cx q[2],q[4];
rz(-0.03679081381077357) q[4];
cx q[2],q[4];
cx q[3],q[4];
rz(-0.036893964527855296) q[4];
cx q[3],q[4];
cx q[5],q[9];
rz(-6.750114066092091) q[9];
cx q[5],q[9];
cx q[5],q[10];
rz(-6.750675324421767) q[10];
cx q[5],q[10];
cx q[5],q[11];
rz(-6.741312265802041) q[11];
cx q[5],q[11];
rz(pi/2) q[5];
sx q[5];
rz(-2.681937084604808) q[5];
sx q[5];
rz(1.5703307132620754) q[5];
cx q[0],q[5];
rz(-0.03689370705112554) q[5];
cx q[0],q[5];
cx q[1],q[5];
rz(-0.03686215116920403) q[5];
cx q[1],q[5];
cx q[2],q[5];
rz(-0.036963118522095884) q[5];
cx q[2],q[5];
cx q[3],q[5];
rz(-0.036883637707018246) q[5];
cx q[3],q[5];
cx q[4],q[5];
rz(-0.03677685474955177) q[5];
cx q[4],q[5];
cx q[6],q[9];
rz(-6.749963291611346) q[9];
cx q[6],q[9];
cx q[6],q[10];
rz(-6.750005321765333) q[10];
cx q[6],q[10];
cx q[6],q[11];
rz(-6.748236228606003) q[11];
cx q[6],q[11];
rz(pi/2) q[6];
sx q[6];
rz(-2.681937084604808) q[6];
sx q[6];
rz(1.570785642670975) q[6];
cx q[0],q[6];
rz(-0.03688740500344904) q[6];
cx q[0],q[6];
cx q[1],q[6];
rz(-0.03690409232657399) q[6];
cx q[1],q[6];
cx q[2],q[6];
rz(-0.03688788138421492) q[6];
cx q[2],q[6];
cx q[3],q[6];
rz(-0.03688612686255436) q[6];
cx q[3],q[6];
cx q[4],q[6];
rz(-0.03687885644387339) q[6];
cx q[4],q[6];
cx q[5],q[6];
rz(-0.03688667195261694) q[6];
cx q[5],q[6];
cx q[7],q[9];
rz(-6.74993717986681) q[9];
cx q[7],q[9];
cx q[7],q[10];
rz(-6.749782825066486) q[10];
cx q[7],q[10];
cx q[7],q[11];
rz(-6.7495123964584245) q[11];
cx q[7],q[11];
rz(pi/2) q[7];
sx q[7];
rz(-2.681937084604808) q[7];
sx q[7];
rz(1.5706711968225502) q[7];
cx q[0],q[7];
rz(-0.03688587968839323) q[7];
cx q[0],q[7];
cx q[1],q[7];
rz(-0.03688255722062798) q[7];
cx q[1],q[7];
cx q[2],q[7];
rz(-0.03688411904437425) q[7];
cx q[2],q[7];
cx q[3],q[7];
rz(-0.03688599802324913) q[7];
cx q[3],q[7];
cx q[4],q[7];
rz(-0.03686677660694573) q[7];
cx q[4],q[7];
cx q[5],q[7];
rz(-0.03688575166672988) q[7];
cx q[5],q[7];
cx q[6],q[7];
rz(-0.03688527373582512) q[7];
cx q[6],q[7];
cx q[8],q[9];
rz(-6.749757494187953) q[9];
cx q[8],q[9];
cx q[8],q[10];
rz(-6.749895446609432) q[10];
cx q[8],q[10];
cx q[8],q[11];
rz(-6.748650945837235) q[11];
cx q[8],q[11];
rz(pi/2) q[8];
sx q[8];
rz(-2.681937084604808) q[8];
sx q[8];
rz(1.5708595777410936) q[8];
cx q[0],q[8];
rz(-0.03688780238172914) q[8];
cx q[0],q[8];
cx q[1],q[8];
rz(-0.0368817550559606) q[8];
cx q[1],q[8];
cx q[2],q[8];
rz(-0.036889318813249236) q[8];
cx q[2],q[8];
cx q[3],q[8];
rz(-0.036886351251776306) q[8];
cx q[3],q[8];
cx q[4],q[8];
rz(-0.03687382157923715) q[8];
cx q[4],q[8];
cx q[5],q[8];
rz(-0.0368937521658633) q[8];
cx q[5],q[8];
cx q[6],q[8];
rz(-0.036886408636589284) q[8];
cx q[6],q[8];
cx q[7],q[8];
rz(-0.03688558343883621) q[8];
cx q[7],q[8];
cx q[9],q[10];
rz(-6.749919739084776) q[10];
cx q[9],q[10];
cx q[9],q[11];
rz(-6.750252725109504) q[11];
cx q[9],q[11];
cx q[10],q[11];
rz(-6.748890910697746) q[11];
cx q[10],q[11];
rz(pi/2) q[10];
sx q[10];
rz(-2.681937084604808) q[10];
sx q[10];
rz(1.57091468640209) q[10];
rz(pi/2) q[11];
sx q[11];
rz(-2.681937084604808) q[11];
sx q[11];
rz(1.570309675358427) q[11];
rz(pi/2) q[9];
sx q[9];
rz(-2.681937084604808) q[9];
sx q[9];
rz(1.5707195559331169) q[9];
cx q[0],q[9];
rz(-0.036882511151925895) q[9];
cx q[0],q[9];
cx q[0],q[10];
cx q[1],q[9];
rz(-0.03689043143194566) q[10];
cx q[0],q[10];
cx q[0],q[11];
rz(-0.03686568178731211) q[11];
cx q[0],q[11];
sx q[0];
rz(2.6735912236292583) q[0];
sx q[0];
rz(5*pi/2) q[0];
rz(-0.03688625988822973) q[9];
cx q[1],q[9];
cx q[1],q[10];
rz(-0.036879031240513775) q[10];
cx q[1],q[10];
cx q[1],q[11];
rz(-0.03688554766349709) q[11];
cx q[1],q[11];
rz(pi/2) q[1];
sx q[1];
rz(2.6735912236292583) q[1];
sx q[1];
rz(5*pi/2) q[1];
cx q[2],q[9];
rz(-0.036885110997817216) q[9];
cx q[2],q[9];
cx q[2],q[10];
rz(-0.0368859866566847) q[10];
cx q[2],q[10];
cx q[2],q[11];
rz(-0.036902390102696817) q[11];
cx q[2],q[11];
rz(pi/2) q[2];
sx q[2];
rz(2.6735912236292583) q[2];
sx q[2];
rz(5*pi/2) q[2];
cx q[3],q[9];
rz(-0.036885876705247345) q[9];
cx q[3],q[9];
cx q[3],q[10];
rz(-0.03688638892844916) q[10];
cx q[3],q[10];
cx q[3],q[11];
rz(-0.03688521959224135) q[11];
cx q[3],q[11];
rz(pi/2) q[3];
sx q[3];
rz(2.6735912236292583) q[3];
sx q[3];
rz(5*pi/2) q[3];
cx q[4],q[9];
rz(-0.03687697172087313) q[9];
cx q[4],q[9];
cx q[4],q[10];
rz(-0.03690981832953572) q[10];
cx q[4],q[10];
cx q[4],q[11];
rz(-0.036792087282715304) q[11];
cx q[4],q[11];
rz(pi/2) q[4];
sx q[4];
rz(2.6735912236292583) q[4];
sx q[4];
rz(5*pi/2) q[4];
cx q[5],q[9];
rz(-0.03688710656668626) q[9];
cx q[5],q[9];
cx q[5],q[10];
rz(-0.03689017365497774) q[10];
cx q[5],q[10];
cx q[5],q[11];
rz(-0.036839007683897196) q[11];
cx q[5],q[11];
rz(pi/2) q[5];
sx q[5];
rz(2.6735912236292583) q[5];
sx q[5];
rz(5*pi/2) q[5];
cx q[6],q[9];
rz(-0.03688628263478166) q[9];
cx q[6],q[9];
cx q[6],q[10];
rz(-0.036886512315458746) q[10];
cx q[6],q[10];
cx q[6],q[11];
rz(-0.03687684481543495) q[11];
cx q[6],q[11];
rz(pi/2) q[6];
sx q[6];
rz(2.6735912236292583) q[6];
sx q[6];
rz(5*pi/2) q[6];
cx q[7],q[9];
rz(-0.03688613994286654) q[9];
cx q[7],q[9];
cx q[7],q[10];
rz(-0.03688529644571777) q[10];
cx q[7],q[10];
cx q[7],q[11];
rz(-0.0368838186441893) q[11];
cx q[7],q[11];
rz(pi/2) q[7];
sx q[7];
rz(2.6735912236292583) q[7];
sx q[7];
rz(5*pi/2) q[7];
cx q[8],q[9];
rz(-0.03688515802097313) q[9];
cx q[8],q[9];
cx q[8],q[10];
rz(-0.03688591188462971) q[10];
cx q[8],q[10];
cx q[8],q[11];
rz(-0.03687911110583444) q[11];
cx q[8],q[11];
rz(pi/2) q[8];
sx q[8];
rz(2.6735912236292583) q[8];
sx q[8];
rz(5*pi/2) q[8];
cx q[9],q[10];
rz(-0.03688604463484964) q[10];
cx q[9],q[10];
cx q[9],q[11];
rz(-0.036887864291652334) q[11];
cx q[9],q[11];
cx q[10],q[11];
rz(-0.03688042243321281) q[11];
cx q[10],q[11];
rz(pi/2) q[10];
sx q[10];
rz(2.6735912236292583) q[10];
sx q[10];
rz(5*pi/2) q[10];
rz(pi/2) q[11];
sx q[11];
rz(2.6735912236292583) q[11];
sx q[11];
rz(5*pi/2) q[11];
rz(pi/2) q[9];
sx q[9];
rz(2.6735912236292583) q[9];
sx q[9];
rz(5*pi/2) q[9];
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
barrier q[0],q[1],q[2],q[3],q[4],q[5],q[6],q[7],q[8],q[9],q[10],q[11];
measure q[0] -> meas69[0];
measure q[1] -> meas69[1];
measure q[2] -> meas69[2];
measure q[3] -> meas69[3];
measure q[4] -> meas69[4];
measure q[5] -> meas69[5];
measure q[6] -> meas69[6];
measure q[7] -> meas69[7];
measure q[8] -> meas69[8];
measure q[9] -> meas69[9];
measure q[10] -> meas69[10];
measure q[11] -> meas69[11];

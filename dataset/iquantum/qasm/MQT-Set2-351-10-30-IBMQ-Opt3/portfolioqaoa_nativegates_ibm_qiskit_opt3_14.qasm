// Benchmark was created by MQT Bench on 2023-06-29
// For more information about MQT Bench, please visit https://www.cda.cit.tum.de/mqtbench/
// MQT Bench version: v1.0.0
// Qiskit version: {'qiskit-terra': '0.24.1', 'qiskit-aer': '0.12.0', 'qiskit-ignis': None, 'qiskit-ibmq-provider': '0.20.2', 'qiskit': '0.43.1', 'qiskit-nature': '0.6.2', 'qiskit-finance': '0.3.4', 'qiskit-optimization': '0.5.0', 'qiskit-machine-learning': '0.6.1'}
// Used Gate Set: ['rz', 'sx', 'x', 'cx', 'measure']

OPENQASM 2.0;
include "qelib1.inc";
qreg q[14];
creg meas[14];
creg meas71[14];
rz(pi/2) q[0];
sx q[0];
rz(3.1116524906445147) q[0];
rz(pi/2) q[1];
sx q[1];
rz(1.5710600460377373) q[1];
cx q[0],q[1];
rz(-2.4883376940278428) q[1];
cx q[0],q[1];
rz(pi/2) q[2];
sx q[2];
rz(1.569140945174862) q[2];
cx q[0],q[2];
rz(-2.488189801079398) q[2];
cx q[0],q[2];
cx q[1],q[2];
rz(-2.4885757709207925) q[2];
cx q[1],q[2];
rz(pi/2) q[3];
sx q[3];
rz(1.5694053749137016) q[3];
cx q[0],q[3];
rz(-2.4882307294351533) q[3];
cx q[0],q[3];
cx q[1],q[3];
rz(-2.488451093009375) q[3];
cx q[1],q[3];
cx q[2],q[3];
rz(-2.4884347180169746) q[3];
cx q[2],q[3];
rz(pi/2) q[4];
sx q[4];
rz(1.5750822385495802) q[4];
cx q[0],q[4];
rz(-2.48811184868701) q[4];
cx q[0],q[4];
cx q[1],q[4];
rz(-2.4883613530672437) q[4];
cx q[1],q[4];
cx q[2],q[4];
rz(-2.48846102964374) q[4];
cx q[2],q[4];
cx q[3],q[4];
rz(-2.488472097001153) q[4];
cx q[3],q[4];
rz(pi/2) q[5];
sx q[5];
rz(1.581293110644502) q[5];
cx q[0],q[5];
rz(-2.48899431453761) q[5];
cx q[0],q[5];
cx q[1],q[5];
rz(-2.4884035379234266) q[5];
cx q[1],q[5];
cx q[2],q[5];
rz(-2.4883952801367166) q[5];
cx q[2],q[5];
cx q[3],q[5];
rz(-2.488386046082014) q[5];
cx q[3],q[5];
cx q[4],q[5];
rz(-2.488447533793457) q[5];
cx q[4],q[5];
rz(pi/2) q[6];
sx q[6];
rz(1.5718186774298797) q[6];
cx q[0],q[6];
rz(-2.488698702845494) q[6];
cx q[0],q[6];
cx q[1],q[6];
rz(-2.4883684093361174) q[6];
cx q[1],q[6];
cx q[2],q[6];
rz(-2.488352040200744) q[6];
cx q[2],q[6];
cx q[3],q[6];
rz(-2.488372423582016) q[6];
cx q[3],q[6];
cx q[4],q[6];
rz(-2.4884272548832715) q[6];
cx q[4],q[6];
cx q[5],q[6];
rz(-2.48855262234835) q[6];
cx q[5],q[6];
rz(pi/2) q[7];
sx q[7];
rz(1.5750743885651444) q[7];
cx q[0],q[7];
rz(-2.4885116939654863) q[7];
cx q[0],q[7];
cx q[1],q[7];
rz(-2.4884368301084376) q[7];
cx q[1],q[7];
cx q[2],q[7];
rz(-2.4884085449543205) q[7];
cx q[2],q[7];
cx q[3],q[7];
rz(-2.4884035912584306) q[7];
cx q[3],q[7];
cx q[4],q[7];
rz(-2.488372071893649) q[7];
cx q[4],q[7];
cx q[5],q[7];
rz(-2.4884202039318133) q[7];
cx q[5],q[7];
cx q[6],q[7];
rz(-2.488440788941867) q[7];
cx q[6],q[7];
rz(pi/2) q[8];
sx q[8];
rz(1.5639660281234642) q[8];
cx q[0],q[8];
rz(-2.487926214157582) q[8];
cx q[0],q[8];
cx q[1],q[8];
rz(-2.48838533819862) q[8];
cx q[1],q[8];
cx q[2],q[8];
rz(-2.4883797943936603) q[8];
cx q[2],q[8];
cx q[3],q[8];
rz(-2.4885135012328448) q[8];
cx q[3],q[8];
cx q[4],q[8];
rz(-2.4884408097077055) q[8];
cx q[4],q[8];
cx q[5],q[8];
rz(-2.4883970849371613) q[8];
cx q[5],q[8];
cx q[6],q[8];
rz(-2.4884779120392437) q[8];
cx q[6],q[8];
cx q[7],q[8];
rz(-2.4884397676798624) q[8];
cx q[7],q[8];
rz(pi/2) q[9];
sx q[9];
rz(1.6570781827091903) q[9];
cx q[0],q[9];
rz(-2.489431841700984) q[9];
cx q[0],q[9];
cx q[1],q[9];
rz(-2.488080665353674) q[9];
cx q[1],q[9];
cx q[2],q[9];
rz(-2.48823509092156) q[9];
cx q[2],q[9];
cx q[3],q[9];
rz(-2.4884060618294326) q[9];
cx q[3],q[9];
cx q[4],q[9];
rz(-2.4864444768870273) q[9];
cx q[4],q[9];
cx q[5],q[9];
rz(-2.4891733809230754) q[9];
cx q[5],q[9];
cx q[6],q[9];
rz(-2.4900368471418064) q[9];
cx q[6],q[9];
cx q[7],q[9];
rz(-2.489638294810158) q[9];
cx q[7],q[9];
cx q[8],q[9];
rz(-2.488746683352538) q[9];
cx q[8],q[9];
rz(pi/2) q[10];
sx q[10];
rz(1.6225228521971715) q[10];
cx q[0],q[10];
rz(-2.4894020371972916) q[10];
cx q[0],q[10];
cx q[1],q[10];
rz(-2.4879905677270164) q[10];
cx q[1],q[10];
cx q[2],q[10];
rz(-2.488384228308121) q[10];
cx q[2],q[10];
cx q[3],q[10];
rz(-2.4882326831423125) q[10];
cx q[3],q[10];
cx q[4],q[10];
rz(-2.4883041849960117) q[10];
cx q[4],q[10];
cx q[5],q[10];
rz(-2.488679356326208) q[10];
cx q[5],q[10];
cx q[6],q[10];
rz(-2.4889150375732414) q[10];
cx q[6],q[10];
cx q[7],q[10];
rz(-2.4886262342817997) q[10];
cx q[7],q[10];
cx q[8],q[10];
rz(-2.488305921660866) q[10];
cx q[8],q[10];
cx q[9],q[10];
rz(-2.492835334791843) q[10];
cx q[9],q[10];
rz(pi/2) q[11];
sx q[11];
rz(1.5711129208356827) q[11];
cx q[0],q[11];
rz(-2.4883819182802163) q[11];
cx q[0],q[11];
cx q[1],q[11];
rz(-2.488391245006035) q[11];
cx q[1],q[11];
cx q[2],q[11];
rz(-2.4884186189145527) q[11];
cx q[2],q[11];
cx q[3],q[11];
rz(-2.488453910001845) q[11];
cx q[3],q[11];
cx q[4],q[11];
rz(-2.4884470951691813) q[11];
cx q[4],q[11];
cx q[5],q[11];
rz(-2.4884556107769846) q[11];
cx q[5],q[11];
cx q[6],q[11];
rz(-2.488422051656259) q[11];
cx q[6],q[11];
cx q[7],q[11];
rz(-2.4884317078833904) q[11];
cx q[7],q[11];
cx q[8],q[11];
rz(-2.4884620144598077) q[11];
cx q[8],q[11];
cx q[9],q[11];
rz(-2.4885844344213353) q[11];
cx q[9],q[11];
cx q[10],q[11];
rz(-2.4887593822582352) q[11];
cx q[10],q[11];
rz(pi/2) q[12];
sx q[12];
rz(1.5797996063668567) q[12];
cx q[0],q[12];
rz(-2.4873162334035563) q[12];
cx q[0],q[12];
cx q[1],q[12];
rz(-2.4887501028569847) q[12];
cx q[1],q[12];
cx q[2],q[12];
rz(-2.488626833059739) q[12];
cx q[2],q[12];
cx q[3],q[12];
rz(-2.4885027601753387) q[12];
cx q[3],q[12];
cx q[4],q[12];
rz(-2.488514744926734) q[12];
cx q[4],q[12];
cx q[5],q[12];
rz(-2.4883086577393083) q[12];
cx q[5],q[12];
cx q[6],q[12];
rz(-2.488371011850344) q[12];
cx q[6],q[12];
cx q[7],q[12];
rz(-2.4884758927557793) q[12];
cx q[7],q[12];
cx q[8],q[12];
rz(-2.4886431782954923) q[12];
cx q[8],q[12];
cx q[9],q[12];
rz(-2.4894193173865724) q[12];
cx q[9],q[12];
cx q[10],q[12];
rz(-2.4888418820340727) q[12];
cx q[10],q[12];
cx q[11],q[12];
rz(-2.488547918816779) q[12];
cx q[11],q[12];
rz(pi/2) q[13];
sx q[13];
rz(1.5283639525297144) q[13];
cx q[0],q[13];
rz(-2.4899302742815013) q[13];
cx q[0],q[13];
sx q[0];
rz(-1.4057872380177692) q[0];
sx q[0];
rz(0.03760550313918021) q[0];
cx q[1],q[13];
rz(-2.4882441679027005) q[13];
cx q[1],q[13];
rz(pi/2) q[1];
sx q[1];
rz(-1.4057872380177692) q[1];
sx q[1];
rz(1.5704650896258592) q[1];
cx q[0],q[1];
rz(3.1254068702008464) q[1];
cx q[0],q[1];
cx q[2],q[13];
rz(-2.488536761178527) q[13];
cx q[2],q[13];
rz(pi/2) q[2];
sx q[2];
rz(-1.4057872380177692) q[2];
sx q[2];
rz(1.572875522516795) q[2];
cx q[0],q[2];
rz(3.1252211134049612) q[2];
cx q[0],q[2];
cx q[1],q[2];
rz(3.1257059000144634) q[2];
cx q[1],q[2];
cx q[3],q[13];
rz(-2.488245312154026) q[13];
cx q[3],q[13];
rz(pi/2) q[3];
sx q[3];
rz(-1.4057872380177692) q[3];
sx q[3];
rz(1.5725433929491555) q[3];
cx q[0],q[3];
rz(3.125272520319935) q[3];
cx q[0],q[3];
cx q[1],q[3];
rz(3.125549301815657) q[3];
cx q[1],q[3];
cx q[2],q[3];
rz(3.1255287344650635) q[3];
cx q[2],q[3];
cx q[4],q[13];
rz(-2.4888371618238354) q[13];
cx q[4],q[13];
rz(pi/2) q[4];
sx q[4];
rz(-1.4057872380177692) q[4];
sx q[4];
rz(1.5654131273701104) q[4];
cx q[0],q[4];
rz(3.125123203485699) q[4];
cx q[0],q[4];
cx q[1],q[4];
rz(3.1254365864746716) q[4];
cx q[1],q[4];
cx q[2],q[4];
rz(3.1255617824469577) q[4];
cx q[2],q[4];
cx q[3],q[4];
rz(3.125575683291275) q[4];
cx q[3],q[4];
cx q[5],q[13];
rz(-2.489227202895275) q[13];
cx q[5],q[13];
rz(pi/2) q[5];
sx q[5];
rz(-1.4057872380177692) q[5];
sx q[5];
rz(1.5576121354219534) q[5];
cx q[0],q[5];
rz(3.1262316000022983) q[5];
cx q[0],q[5];
cx q[1],q[5];
rz(3.1254895715818174) q[5];
cx q[1],q[5];
cx q[2],q[5];
rz(3.125479199620094) q[5];
cx q[2],q[5];
cx q[3],q[5];
rz(3.1254676014443015) q[5];
cx q[3],q[5];
cx q[4],q[5];
rz(3.125544831362185) q[5];
cx q[4],q[5];
cx q[6],q[13];
rz(-2.488934314271719) q[13];
cx q[6],q[13];
rz(pi/2) q[6];
sx q[6];
rz(-1.4057872380177692) q[6];
sx q[6];
rz(1.5695122319125971) q[6];
cx q[0],q[6];
rz(3.1258603052155545) q[6];
cx q[0],q[6];
cx q[1],q[6];
rz(3.1254454493035673) q[6];
cx q[1],q[6];
cx q[2],q[6];
rz(3.1254248893095284) q[6];
cx q[2],q[6];
cx q[3],q[6];
rz(3.125450491284702) q[6];
cx q[3],q[6];
cx q[4],q[6];
rz(3.125519360604994) q[6];
cx q[4],q[6];
cx q[5],q[6];
rz(3.125676824898364) q[6];
cx q[5],q[6];
cx q[7],q[13];
rz(-2.4882014205855203) q[13];
cx q[7],q[13];
rz(pi/2) q[7];
sx q[7];
rz(-1.4057872380177692) q[7];
sx q[7];
rz(1.565422987123208) q[7];
cx q[0],q[7];
rz(3.1256254179493417) q[7];
cx q[0],q[7];
cx q[1],q[7];
rz(3.125531387298393) q[7];
cx q[1],q[7];
cx q[2],q[7];
rz(3.125495860522741) q[7];
cx q[2],q[7];
cx q[3],q[7];
rz(3.1254896385717554) q[7];
cx q[3],q[7];
cx q[4],q[7];
rz(3.125450049556378) q[7];
cx q[4],q[7];
cx q[5],q[7];
rz(3.1255105044548896) q[7];
cx q[5],q[7];
cx q[6],q[7];
rz(3.1255363596802477) q[7];
cx q[6],q[7];
cx q[8],q[13];
rz(-2.4881803678088708) q[13];
cx q[8],q[13];
rz(pi/2) q[8];
sx q[8];
rz(-1.4057872380177692) q[8];
sx q[8];
rz(1.5793753321463218) q[8];
cx q[0],q[8];
rz(3.1248900424340413) q[8];
cx q[0],q[8];
cx q[1],q[8];
rz(3.12546671232719) q[8];
cx q[1],q[8];
cx q[2],q[8];
rz(3.125459749186235) q[8];
cx q[2],q[8];
cx q[3],q[8];
rz(3.125627687916891) q[8];
cx q[3],q[8];
cx q[4],q[8];
rz(3.1255363857625973) q[8];
cx q[4],q[8];
cx q[5],q[8];
rz(3.125481466489146) q[8];
cx q[5],q[8];
cx q[6],q[8];
rz(3.125582987107008) q[8];
cx q[6],q[8];
cx q[7],q[8];
rz(3.125535076952709) q[8];
cx q[7],q[8];
cx q[9],q[13];
rz(-2.4896483662290256) q[13];
cx q[9],q[13];
cx q[10],q[13];
rz(-2.490268841005899) q[13];
cx q[10],q[13];
rz(pi/2) q[10];
sx q[10];
rz(-1.4057872380177692) q[10];
sx q[10];
rz(1.5058266732579586) q[10];
cx q[11],q[13];
rz(-2.48822517053216) q[13];
cx q[11],q[13];
rz(pi/2) q[11];
sx q[11];
rz(-1.4057872380177692) q[11];
sx q[11];
rz(1.570398677716753) q[11];
cx q[12],q[13];
rz(-2.486552533810384) q[13];
cx q[12],q[13];
rz(pi/2) q[12];
sx q[12];
rz(-1.4057872380177692) q[12];
sx q[12];
rz(1.5594880096416102) q[12];
rz(pi/2) q[13];
sx q[13];
rz(-1.4057872380177692) q[13];
sx q[13];
rz(1.624092322095544) q[13];
rz(pi/2) q[9];
sx q[9];
rz(-1.4057872380177692) q[9];
sx q[9];
rz(1.4624244181526613) q[9];
cx q[0],q[9];
rz(3.1267811437421176) q[9];
cx q[0],q[9];
cx q[0],q[10];
cx q[1],q[9];
rz(3.1267437086299834) q[10];
cx q[0],q[10];
cx q[0],q[11];
rz(3.12546241683446) q[11];
cx q[0],q[11];
cx q[0],q[12];
rz(3.1241238931915576) q[12];
cx q[0],q[12];
cx q[0],q[13];
rz(3.127407186025373) q[13];
cx q[0],q[13];
sx q[0];
rz(-2.023532568259597) q[0];
sx q[0];
rz(-0.01915085718629239) q[0];
rz(3.125084036533208) q[9];
cx q[1],q[9];
cx q[1],q[10];
rz(3.1249708719325913) q[10];
cx q[1],q[10];
cx q[1],q[11];
rz(3.125474131407213) q[11];
cx q[1],q[11];
cx q[1],q[12];
rz(3.1259248647604374) q[12];
cx q[1],q[12];
cx q[1],q[13];
rz(3.1252893993307294) q[13];
cx q[1],q[13];
rz(-pi/2) q[1];
sx q[1];
rz(-2.023532568259597) q[1];
sx q[1];
rz(-1.5706276420230028) q[1];
cx q[0],q[1];
rz(-1.591634617910787) q[1];
cx q[0],q[1];
cx q[2],q[9];
rz(3.1252779984427845) q[9];
cx q[2],q[9];
cx q[2],q[10];
rz(3.125465318280313) q[10];
cx q[2],q[10];
cx q[2],q[11];
rz(3.125508513638351) q[11];
cx q[2],q[11];
cx q[2],q[12];
rz(3.125770035184) q[12];
cx q[2],q[12];
cx q[2],q[13];
rz(3.125656902920229) q[13];
cx q[2],q[13];
rz(-pi/2) q[2];
sx q[2];
rz(-2.023532568259597) q[2];
sx q[2];
rz(-1.5718551712992523) q[2];
cx q[0],q[2];
rz(-1.591540020004299) q[2];
cx q[0],q[2];
cx q[1],q[2];
rz(-1.5917869008687844) q[2];
cx q[1],q[2];
cx q[3],q[9];
rz(3.12549274166327) q[9];
cx q[3],q[9];
cx q[3],q[10];
rz(3.1252749742190953) q[10];
cx q[3],q[10];
cx q[3],q[11];
rz(3.1255528400201538) q[11];
cx q[3],q[11];
cx q[3],q[12];
rz(3.125614196912433) q[12];
cx q[3],q[12];
cx q[3],q[13];
rz(3.125290836535559) q[13];
cx q[3],q[13];
rz(-pi/2) q[3];
sx q[3];
rz(-2.023532568259597) q[3];
sx q[3];
rz(-1.5716860320673245) q[3];
cx q[0],q[3];
rz(-1.591566199323943) q[3];
cx q[0],q[3];
cx q[1],q[3];
rz(-1.5917071521753585) q[3];
cx q[1],q[3];
cx q[2],q[3];
rz(-1.5916966781127604) q[3];
cx q[2],q[3];
cx q[4],q[9];
rz(3.123028947834084) q[9];
cx q[4],q[9];
cx q[4],q[10];
rz(3.1253647821198958) q[10];
cx q[4],q[10];
cx q[4],q[11];
rz(3.1255442804404483) q[11];
cx q[4],q[11];
cx q[4],q[12];
rz(3.1256292500237692) q[12];
cx q[4],q[12];
cx q[4],q[13];
rz(3.126034212737507) q[13];
cx q[4],q[13];
rz(-pi/2) q[4];
sx q[4];
rz(-2.023532568259596) q[4];
sx q[4];
rz(-1.5680548960258998) q[4];
cx q[0],q[4];
rz(-1.5914901587147434) q[4];
cx q[0],q[4];
cx q[1],q[4];
rz(-1.5916497511245091) q[4];
cx q[1],q[4];
cx q[2],q[4];
rz(-1.5917135080213833) q[4];
cx q[2],q[4];
cx q[3],q[4];
rz(-1.5917205871205062) q[4];
cx q[3],q[4];
cx q[5],q[9];
rz(3.1264565113206855) q[9];
cx q[5],q[9];
cx q[5],q[10];
rz(3.1258360055618395) q[10];
cx q[5],q[10];
cx q[5],q[11];
rz(3.125554976231135) q[11];
cx q[5],q[11];
cx q[5],q[12];
rz(3.1253703999838462) q[12];
cx q[5],q[12];
cx q[5],q[13];
rz(3.126524112901485) q[13];
cx q[5],q[13];
rz(-pi/2) q[5];
sx q[5];
rz(-2.023532568259597) q[5];
sx q[5];
rz(-1.5640821880379852) q[5];
cx q[0],q[5];
rz(-1.5920546171483034) q[5];
cx q[0],q[5];
cx q[1],q[5];
rz(-1.591676734149206) q[5];
cx q[1],q[5];
cx q[2],q[5];
rz(-1.5916714521574462) q[5];
cx q[2],q[5];
cx q[3],q[5];
rz(-1.5916655457078661) q[5];
cx q[3],q[5];
cx q[4],q[5];
rz(-1.5917048755666483) q[5];
cx q[4],q[5];
cx q[6],q[9];
rz(3.127541043881794) q[9];
cx q[6],q[9];
cx q[6],q[10];
rz(3.1261320263914976) q[10];
cx q[6],q[10];
cx q[6],q[11];
rz(3.125512825237431) q[11];
cx q[6],q[11];
cx q[6],q[12];
rz(3.1254487181186734) q[12];
cx q[6],q[12];
cx q[6],q[13];
rz(3.1261562383487416) q[13];
cx q[6],q[13];
rz(-pi/2) q[6];
sx q[6];
rz(-2.023532568259597) q[6];
sx q[6];
rz(-1.5701423927785507) q[6];
cx q[0],q[6];
rz(-1.5918655327632698) q[6];
cx q[0],q[6];
cx q[1],q[6];
rz(-1.5916542645801544) q[6];
cx q[1],q[6];
cx q[2],q[6];
rz(-1.5916437942639317) q[6];
cx q[2],q[6];
cx q[3],q[6];
rz(-1.5916568322431983) q[6];
cx q[3],q[6];
cx q[4],q[6];
rz(-1.5916919044110283) q[6];
cx q[4],q[6];
cx q[5],q[6];
rz(-1.5917720941689768) q[6];
cx q[5],q[6];
cx q[7],q[9];
rz(3.127040453387804) q[9];
cx q[7],q[9];
cx q[7],q[10];
rz(3.1257692831057393) q[10];
cx q[7],q[10];
cx q[7],q[11];
rz(3.1255249536710794) q[11];
cx q[7],q[11];
cx q[7],q[12];
rz(3.1255804508425666) q[12];
cx q[7],q[12];
cx q[7],q[13];
rz(3.1252357077602) q[13];
cx q[7],q[13];
rz(-pi/2) q[7];
sx q[7];
rz(-2.023532568259597) q[7];
sx q[7];
rz(-1.5680599171719471) q[7];
cx q[0],q[7];
rz(-1.5917459148319935) q[7];
cx q[0],q[7];
cx q[1],q[7];
rz(-1.5916980290861018) q[7];
cx q[1],q[7];
cx q[2],q[7];
rz(-1.5916799368349681) q[7];
cx q[2],q[7];
cx q[3],q[7];
rz(-1.5916767682642856) q[7];
cx q[3],q[7];
cx q[4],q[7];
rz(-1.591656607290057) q[7];
cx q[4],q[7];
cx q[5],q[7];
rz(-1.5916873943566023) q[7];
cx q[5],q[7];
cx q[6],q[7];
rz(-1.5917005613052406) q[7];
cx q[6],q[7];
cx q[8],q[9];
rz(3.1259205697876467) q[9];
cx q[8],q[9];
cx q[8],q[10];
rz(3.125366963409147) q[10];
cx q[8],q[10];
cx q[8],q[11];
rz(3.1255630193975983) q[11];
cx q[8],q[11];
cx q[8],q[12];
rz(3.12579056515959) q[12];
cx q[8],q[12];
cx q[8],q[13];
rz(3.125209265009711) q[13];
cx q[8],q[13];
rz(-pi/2) q[8];
sx q[8];
rz(-2.023532568259597) q[8];
sx q[8];
rz(-1.5751652433781302) q[8];
cx q[0],q[8];
rz(-1.5913714198699214) q[8];
cx q[0],q[8];
cx q[1],q[8];
rz(-1.5916650929189549) q[8];
cx q[1],q[8];
cx q[2],q[8];
rz(-1.5916615468922601) q[8];
cx q[2],q[8];
cx q[3],q[8];
rz(-1.5917470708283432) q[8];
cx q[3],q[8];
cx q[4],q[8];
rz(-1.5917005745878539) q[8];
cx q[4],q[8];
cx q[5],q[8];
rz(-1.591672606575865) q[8];
cx q[5],q[8];
cx q[6],q[8];
rz(-1.591724306638139) q[8];
cx q[6],q[8];
cx q[7],q[8];
rz(-1.5916999080675531) q[8];
cx q[7],q[8];
cx q[9],q[10];
rz(3.1310560059179315) q[10];
cx q[9],q[10];
cx q[9],q[11];
rz(3.125716781561683) q[11];
cx q[9],q[11];
cx q[10],q[11];
rz(3.125936519892492) q[11];
cx q[10],q[11];
cx q[9],q[12];
rz(3.126765412927767) q[12];
cx q[9],q[12];
cx q[10],q[12];
rz(3.126040141425378) q[12];
cx q[10],q[12];
cx q[11],q[12];
rz(3.1256709171592654) q[12];
cx q[11],q[12];
cx q[9],q[13];
rz(3.127053103311404) q[13];
cx q[9],q[13];
cx q[10],q[13];
rz(3.1278324332773817) q[13];
cx q[10],q[13];
rz(-pi/2) q[10];
sx q[10];
rz(-2.023532568259597) q[10];
sx q[10];
rz(-1.5377100906734498) q[10];
cx q[11],q[13];
rz(3.1252655382155172) q[13];
cx q[11],q[13];
rz(-pi/2) q[11];
sx q[11];
rz(-2.023532568259597) q[11];
sx q[11];
rz(-1.5705938213084636) q[11];
cx q[12],q[13];
rz(3.123164669706337) q[13];
cx q[12],q[13];
rz(-pi/2) q[12];
sx q[12];
rz(-2.023532568259597) q[12];
sx q[12];
rz(-1.5650374896901695) q[12];
rz(-pi/2) q[13];
sx q[13];
rz(-2.023532568259596) q[13];
sx q[13];
rz(-1.5979376733905388) q[13];
rz(-pi/2) q[9];
sx q[9];
rz(-2.023532568259597) q[9];
sx q[9];
rz(-1.5156071982853785) q[9];
cx q[0],q[9];
rz(-1.5923344760200209) q[9];
cx q[0],q[9];
cx q[0],q[10];
cx q[1],q[9];
rz(-1.592315411935608) q[10];
cx q[0],q[10];
cx q[0],q[11];
rz(-1.5916629054101876) q[11];
cx q[0],q[11];
cx q[0],q[12];
rz(-1.5909812531788419) q[12];
cx q[0],q[12];
cx q[0],q[13];
rz(-1.5926532922931296) q[13];
cx q[0],q[13];
sx q[0];
rz(-3.2714080451010252) q[0];
sx q[0];
rz(5*pi/2) q[0];
rz(-1.5914702126788987) q[9];
cx q[1],q[9];
cx q[1],q[10];
rz(-1.591412582839539) q[10];
cx q[1],q[10];
cx q[1],q[11];
rz(-1.591668871135708) q[11];
cx q[1],q[11];
cx q[1],q[12];
rz(-1.5918984101487816) q[12];
cx q[1],q[12];
cx q[1],q[13];
rz(-1.5915747950745163) q[13];
cx q[1],q[13];
rz(pi/2) q[1];
sx q[1];
rz(-3.2714080451010252) q[1];
sx q[1];
rz(5*pi/2) q[1];
cx q[2],q[9];
rz(-1.591568989095109) q[9];
cx q[2],q[9];
cx q[2],q[10];
rz(-1.5916643829911405) q[10];
cx q[2],q[10];
cx q[2],q[11];
rz(-1.5916863805197956) q[11];
cx q[2],q[11];
cx q[2],q[12];
rz(-1.5918195621382762) q[12];
cx q[2],q[12];
cx q[2],q[13];
rz(-1.5917619487666743) q[13];
cx q[2],q[13];
rz(pi/2) q[2];
sx q[2];
rz(-3.2714080451010252) q[2];
sx q[2];
rz(5*pi/2) q[2];
cx q[3],q[9];
rz(-1.5916783485346575) q[9];
cx q[3],q[9];
cx q[3],q[10];
rz(-1.5915674489887113) q[10];
cx q[3],q[10];
cx q[3],q[11];
rz(-1.5917089540299638) q[11];
cx q[3],q[11];
cx q[3],q[12];
rz(-1.5917402004429446) q[12];
cx q[3],q[12];
cx q[3],q[13];
rz(-1.5915755269808105) q[13];
cx q[3],q[13];
rz(pi/2) q[3];
sx q[3];
rz(-3.2714080451010252) q[3];
sx q[3];
rz(5*pi/2) q[3];
cx q[4],q[9];
rz(-1.590423644839175) q[9];
cx q[4],q[9];
cx q[4],q[10];
rz(-1.5916131842704884) q[10];
cx q[4],q[10];
cx q[4],q[11];
rz(-1.5917045950060225) q[11];
cx q[4],q[11];
cx q[4],q[12];
rz(-1.5917478663418514) q[12];
cx q[4],q[12];
cx q[4],q[13];
rz(-1.591954096346749) q[13];
cx q[4],q[13];
rz(pi/2) q[4];
sx q[4];
rz(-3.2714080451010252) q[4];
sx q[4];
rz(5*pi/2) q[4];
cx q[5],q[9];
rz(-1.5921691547605796) q[9];
cx q[5],q[9];
cx q[5],q[10];
rz(-1.5918531580000272) q[10];
cx q[5],q[10];
cx q[5],q[11];
rz(-1.591710041909875) q[11];
cx q[5],q[11];
cx q[5],q[12];
rz(-1.5916160452057564) q[12];
cx q[5],q[12];
cx q[5],q[13];
rz(-1.5922035813234865) q[13];
cx q[5],q[13];
rz(pi/2) q[5];
sx q[5];
rz(-3.2714080451010252) q[5];
sx q[5];
rz(5*pi/2) q[5];
cx q[6],q[9];
rz(-1.5927214603131687) q[9];
cx q[6],q[9];
cx q[6],q[10];
rz(-1.592003908612563) q[10];
cx q[6],q[10];
cx q[6],q[11];
rz(-1.5916885762308308) q[11];
cx q[6],q[11];
cx q[6],q[12];
rz(-1.5916559292463879) q[12];
cx q[6],q[12];
cx q[6],q[13];
rz(-1.592016238715784) q[13];
cx q[6],q[13];
rz(pi/2) q[6];
sx q[6];
rz(-3.2714080451010252) q[6];
sx q[6];
rz(5*pi/2) q[6];
cx q[7],q[9];
rz(-1.5924665312134638) q[9];
cx q[7],q[9];
cx q[7],q[10];
rz(-1.5918191791373282) q[10];
cx q[7],q[10];
cx q[7],q[11];
rz(-1.5916947527178154) q[11];
cx q[7],q[11];
cx q[7],q[12];
rz(-1.5917230150282933) q[12];
cx q[7],q[12];
cx q[7],q[13];
rz(-1.5915474522785564) q[13];
cx q[7],q[13];
rz(pi/2) q[7];
sx q[7];
rz(-3.2714080451010252) q[7];
sx q[7];
rz(5*pi/2) q[7];
cx q[8],q[9];
rz(-1.5918962229047966) q[9];
cx q[8],q[9];
cx q[8],q[10];
rz(-1.5916142951068142) q[10];
cx q[8],q[10];
cx q[8],q[11];
rz(-1.591714137946875) q[11];
cx q[8],q[11];
cx q[8],q[12];
rz(-1.5918300171674011) q[12];
cx q[8],q[12];
cx q[8],q[13];
rz(-1.5915339861287654) q[13];
cx q[8],q[13];
rz(pi/2) q[8];
sx q[8];
rz(-3.2714080451010252) q[8];
sx q[8];
rz(5*pi/2) q[8];
cx q[9],q[10];
rz(-1.5945114785378998) q[10];
cx q[9],q[10];
cx q[9],q[11];
rz(-1.5917924423704097) q[11];
cx q[9],q[11];
cx q[10],q[11];
rz(-1.5919043456037238) q[11];
cx q[10],q[11];
cx q[9],q[12];
rz(-1.5923264649962634) q[12];
cx q[9],q[12];
cx q[10],q[12];
rz(-1.5919571155712036) q[12];
cx q[10],q[12];
cx q[11],q[12];
rz(-1.5917690856128262) q[12];
cx q[11],q[12];
cx q[9],q[13];
rz(-1.5924729732727387) q[13];
cx q[9],q[13];
cx q[10],q[13];
rz(-1.592869852336534) q[13];
cx q[10],q[13];
rz(pi/2) q[10];
sx q[10];
rz(-3.2714080451010252) q[10];
sx q[10];
rz(5*pi/2) q[10];
cx q[11],q[13];
rz(-1.5915626436399766) q[13];
cx q[11],q[13];
rz(pi/2) q[11];
sx q[11];
rz(-3.2714080451010252) q[11];
sx q[11];
rz(5*pi/2) q[11];
cx q[12],q[13];
rz(-1.5904927621219025) q[13];
cx q[12],q[13];
rz(pi/2) q[12];
sx q[12];
rz(-3.2714080451010252) q[12];
sx q[12];
rz(5*pi/2) q[12];
rz(pi/2) q[13];
sx q[13];
rz(-3.2714080451010252) q[13];
sx q[13];
rz(5*pi/2) q[13];
rz(pi/2) q[9];
sx q[9];
rz(-3.2714080451010252) q[9];
sx q[9];
rz(5*pi/2) q[9];
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
barrier q[0],q[1],q[2],q[3],q[4],q[5],q[6],q[7],q[8],q[9],q[10],q[11],q[12],q[13];
measure q[0] -> meas71[0];
measure q[1] -> meas71[1];
measure q[2] -> meas71[2];
measure q[3] -> meas71[3];
measure q[4] -> meas71[4];
measure q[5] -> meas71[5];
measure q[6] -> meas71[6];
measure q[7] -> meas71[7];
measure q[8] -> meas71[8];
measure q[9] -> meas71[9];
measure q[10] -> meas71[10];
measure q[11] -> meas71[11];
measure q[12] -> meas71[12];
measure q[13] -> meas71[13];

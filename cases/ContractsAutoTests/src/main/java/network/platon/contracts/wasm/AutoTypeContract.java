package network.platon.contracts.wasm;

import com.platon.rlp.datatypes.Int32;
import com.platon.rlp.datatypes.Uint8;
import java.math.BigInteger;
import java.util.Arrays;
import org.web3j.abi.WasmFunctionEncoder;
import org.web3j.abi.datatypes.WasmFunction;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.WasmContract;
import org.web3j.tx.gas.GasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://github.com/PlatONnetwork/client-sdk-java/releases">platon-web3j command line tools</a>,
 * or the org.web3j.codegen.WasmFunctionWrapperGenerator in the 
 * <a href="https://github.com/PlatONnetwork/client-sdk-java/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with platon-web3j version 0.9.1.2-SNAPSHOT.
 */
public class AutoTypeContract extends WasmContract {
    private static String BINARY_0 = "0x0061736d0100000001570f60027f7f0060017f017f60017f0060037f7f7f0060000060027f7f017f60037f7f7f017f60027f7e0060047f7f7f7f017f60047f7f7f7f0060077f7f7f7f7f7f7f0060037f7e7f006000017f60017e017f60017f017e02a9010703656e760c706c61746f6e5f70616e6963000403656e7617706c61746f6e5f6765745f696e7075745f6c656e677468000c03656e7610706c61746f6e5f6765745f696e707574000203656e760d706c61746f6e5f72657475726e000003656e7617706c61746f6e5f6765745f73746174655f6c656e677468000503656e7610706c61746f6e5f6765745f7374617465000803656e7610706c61746f6e5f7365745f73746174650009036b6a04060504010201010604040503030200030a020104020206010101020109060000010201010303000001000000000002000000030300070d0b05080003000201010101010500050800020406000e0202020102010702000702070002010000010203060000020100080204050170010a0a05030100020615037f0141b08b040b7f0041b08b040b7f0041ad0b0b075406066d656d6f72790200115f5f7761736d5f63616c6c5f63746f727300070b5f5f686561705f6261736503010a5f5f646174615f656e6403020f5f5f66756e63735f6f6e5f65786974001b06696e766f6b650051090f010041010b091d0c464748490c4a450ad7756a1800100a41a40a101a1a4101101c41b00a101a1a4109101c0ba20a010d7f2002410f6a210f410020026b21072002410e6a210a410120026b210e2002410d6a210d410220026b210c0340200020056a2103200120056a220441037145200220054672450440200320042d00003a0000200f417f6a210f200741016a2107200a417f6a210a200e41016a210e200d417f6a210d200c41016a210c200541016a21050c010b0b200220056b210602400240024002402003410371220b044020064120490d03200b4101460d01200b4102460d02200b4103470d032003200120056a280200220a3a0000200041016a210b200220056b417f6a210c200521030340200c4113494504402003200b6a2208200120036a220941046a2802002206411874200a41087672360200200841046a200941086a2802002204411874200641087672360200200841086a2009410c6a28020022064118742004410876723602002008410c6a200941106a280200220a411874200641087672360200200341106a2103200c41706a210c0c010b0b2002417f6a2007416d2007416d4b1b200f6a4170716b20056b2106200120036a41016a2104200020036a41016a21030c030b2006210403402004411049450440200020056a2203200120056a2202290200370200200341086a200241086a290200370200200541106a2105200441706a21040c010b0b027f2006410871450440200120056a2104200020056a0c010b200020056a2202200120056a2201290200370200200141086a2104200241086a0b21052006410471044020052004280200360200200441046a2104200541046a21050b20064102710440200520042f00003b0000200441026a2104200541026a21050b2006410171450d03200520042d00003a000020000f0b2003200120056a2206280200220a3a0000200341016a200641016a2f00003b0000200041036a210b200220056b417d6a210720052103034020074111494504402003200b6a2208200120036a220941046a2802002206410874200a41187672360200200841046a200941086a2802002204410874200641187672360200200841086a2009410c6a28020022064108742004411876723602002008410c6a200941106a280200220a410874200641187672360200200341106a2103200741706a21070c010b0b2002417d6a200c416f200c416f4b1b200d6a4170716b20056b2106200120036a41036a2104200020036a41036a21030c010b2003200120056a2206280200220d3a0000200341016a200641016a2d00003a0000200041026a210b200220056b417e6a210720052103034020074112494504402003200b6a2208200120036a220941046a2802002206411074200d41107672360200200841046a200941086a2802002204411074200641107672360200200841086a2009410c6a28020022064110742004411076723602002008410c6a200941106a280200220d411074200641107672360200200341106a2103200741706a21070c010b0b2002417e6a200e416e200e416e4b1b200a6a4170716b20056b2106200120036a41026a2104200020036a41026a21030b20064110710440200320042d00003a00002003200428000136000120032004290005370005200320042f000d3b000d200320042d000f3a000f200441106a2104200341106a21030b2006410871044020032004290000370000200441086a2104200341086a21030b2006410471044020032004280000360000200441046a2104200341046a21030b20064102710440200320042f00003b0000200441026a2104200341026a21030b2006410171450d00200320042d00003a00000b20000be10201027f02402001450d00200041003a0000200020016a2202417f6a41003a000020014103490d00200041003a0002200041003a00012002417d6a41003a00002002417e6a41003a000020014107490d00200041003a00032002417c6a41003a000020014109490d002000410020006b41037122036a220241003602002002200120036b417c7122036a2201417c6a410036020020034109490d002002410036020820024100360204200141786a4100360200200141746a410036020020034119490d002002410036021820024100360214200241003602102002410036020c200141706a41003602002001416c6a4100360200200141686a4100360200200141646a41003602002003200241047141187222036b2101200220036a2102034020014120490d0120024200370300200241186a4200370300200241106a4200370300200241086a4200370300200241206a2102200141606a21010c000b000b20000b3501017f230041106b220041b08b0436020c418408200028020c41076a41787122003602004180082000360200418c083f003602000b9f0101047f230041106b220224002002200036020c027f02400240024020000440418c08200041086a22014110762200418c082802006a2203360200418408200141840828020022016a41076a4178712204360200200341107420044d0d0120000d020c030b41000c030b418c08200341016a360200200041016a21000b200040000d0010000b20012002410c6a410410081a200141086a0b200241106a24000b0300010b2f01027f2000410120001b2100034002402000100b22010d004190082802002202450d0020021104000c010b0b20010b7801027f20002101024003402001410371044020012d0000450d02200141016a21010c010b0b2001417c6a21010340200141046a22012802002202417f73200241fffdfb776a7141808182847871450d000b0340200241ff0171450d01200141016a2d00002102200141016a21010c000b000b200120006b0bc10301067f024020002001460d00027f02400240200120006b20026b410020024101746b4b044020002001734103712103200020014f0d012003450d0220000c030b20002001200210080f0b024020030d002001417f6a21030340200020026a220441037104402002450d052004417f6a200220036a2d00003a00002002417f6a21020c010b0b2000417c6a21032001417c6a2104034020024104490d01200220036a200220046a2802003602002002417c6a21020c000b000b2001417f6a210103402002450d03200020026a417f6a200120026a2d00003a00002002417f6a21020c000b000b200241046a21062002417f73210503400240200120046a2107200020046a2208410371450d0020022004460d03200820072d00003a00002006417f6a2106200541016a2105200441016a21040c010b0b200220046b21014100210303402001410449450440200320086a200320076a280200360200200341046a21032001417c6a21010c010b0b200320076a210120022005417c2005417c4b1b20066a417c716b20046b2102200320086a0b210303402002450d01200320012d00003a00002002417f6a2102200341016a2103200141016a21010c000b000b20000b0a0041940841013602000b0a0041940841003602000b4d01017f20004200370200200041086a2202410036020020012d0000410171450440200020012902003702002002200141086a28020036020020000f0b200020012802082001280204101320000b6401027f2002417049044002402002410a4d0440200020024101743a0000200041016a21030c010b200241106a4170712204100d21032000200236020420002004410172360200200020033602080b2003200120021014200220036a41003a00000f0b000b13002002047f20002001200210080520000b1a0b130020002d0000410171044020002802081a0b0b3401017f2000200147044020002001280208200141016a20012d0000220041017122021b2001280204200041017620021b10170b0bab0101037f410a2103027f0240027f024020002d00002205410171220404402000280200417e71417f6a21030b2003200249044020040d0120054101760c020b20040d02200041016a0c030b20002802040b210420002003200220036b200420042002200110180f0b20002802080b220421032002047f200320012002100f0520030b1a200220046a41003a000020002d0000410171450440200020024101743a00000f0b200020023602040bb70101027f416e20016b20024f0440027f200041016a20002d0000410171450d001a20002802080b2108027f416f200141e6ffffff074b0d001a410b20014101742207200120026a220220022007491b2202410b490d001a200241106a4170710b2207100d21022005044020022006200510140b200320046b220322060440200220056a200420086a200610140b200020023602082000200320056a220136020420002007410172360200200120026a41003a00000f0b000b2301017f03402001410c46450440200020016a4100360200200141046a21010c010b0b0b190020004200370200200041086a41003602002000101920000b7601037f101041980828020021000340200004400340419c08419c082802002201417f6a22023602002001410148450440200020024102746a22004184016a280200200041046a2802001011110200101041980828020021000c010b0b419c084120360200419808200028020022003602000c010b0b0b940101027f1010419808280200220145044041980841a00836020041a00821010b0240419c0828020022024120460440418402100b22010440200141840210091a0b2001450d0120014198082802003602004198082001360200419c084100360200410021020b419c08200241016a360200200120024102746a22014184016a4100360200200141046a200036020010110f0b10110b070041a40a10150b780020004200370210200042ffffffff0f3702082000200129020037020002402002410871450d002000101f20012802044f0d002002410471450440200042003702000c010b10000b024002402002411071450d002000101f20012802044d0d0020024104710d01200042003702000b20000f0b100020000b290002402000280204044020002802002c0000417f4c0d0141010f0b41000f0b20001020200010216a0b240002402000280204450d0020002802002c0000417f4c0d0041000f0b2000102841016a0b8a0301047f0240024020002802040440200010294101210220002802002c00002201417f4c0d010c020b41000f0b200141ff0171220241b7014d0440200241807f6a0f0b02400240200141ff0171220141bf014d04400240200041046a22042802002201200241c97e6a22034d047f100020042802000520010b4102490d0020002802002d00010d0010000b200341054f044010000b20002802002d000145044010000b410021024100210103402001200346450440200028020020016a41016a2d00002002410874722102200141016a21010c010b0b200241384f0d010c020b200141f7014d0440200241c07e6a0f0b0240200041046a22042802002201200241897e6a22034d047f100020042802000520010b4102490d0020002802002d00010d0010000b200341054f044010000b20002802002d000145044010000b410021024100210103402001200346450440200028020020016a41016a2d00002002410874722102200141016a21010c010b0b20024138490d010b200241ff7d490d010b100020020f0b20020bd20102057f017e230041206b22022400200041046a210102402000280200220404402001280200220504402005200041086a2802006a21030b200041086a2004360200200041046a2204200336020020022004290200220637031020022006370308200241186a20014100200241086a102310242004200229031822063702002000200028020022002006422088a722012000200020014b1b6b3602000c010b2001280200220104402001200041086a2802006a21030b200041086a4100360200200041046a20033602000b200241206a24000b3902017f017e230041306b2201240020012000290200220237031020012002370308200141186a200141086a4114101e101f200141306a24000b5e01027f2000027f027f2001280200220504404100200220036a200128020422014b2001200249720d011a410020012003490d021a200220056a2104200120026b20032003417f461b0c020b41000b210441000b360204200020043602000b850202037f017e230041206b2203240020004200370200200041086a22054100360200200128020421040240027f2002044041002004450d011a200420012802002d000041c001490d011a200341186a2001102620032003290318220637031020032006370308200341086a10232101200041086a4100200328021c220220012001417f461b200328021822044520022001497222011b2205360200200041046a4100200420011b3602002000200220056b3602000c020b20040b210220012802002104200128020421012000410036020020054100200220016b20044520022001497222021b360200200041046a4100200120046a20021b3602000b200341206a240020000b2101017f20011021220220012802044b044010000b2000200120011020200210240b900302097f017e230041406a220224002001280208220341004b0440200241386a20011026200220022903383703182001200241186a102336020c200241306a20011026410021032001027f410020022802302205450d001a410020022802342207200128020c2204490d001a200720042004417f461b210820050b360210200141146a2008360200200141086a41003602000b200141106a2104200141146a21072001410c6a2105200141086a210803400240200341004f0d002007280200450d00200241306a2001102641002103027f20022802302209044041002002280234220a20052802002206490d011a200a20066b2103200620096a0c010b41000b210620072003360200200420063602002002200336022c2002200636022820022002290328370310200241306a20044100200241106a1023102420042002290330220b37020020052005280200200b422088a76a3602002008200828020041016a22033602000c010b0b20022004290200220b3703202002200b3703082000200241086a4114101e1a200241406b24000b4101017f02402000280204450d0020002802002d0000220041bf014d0440200041b801490d01200041c97e6a0f0b200041f801490d00200041897e6a21010b20010b4401017f200028020445044010000b0240200028020022012d0000418101470d00200041046a28020041014d047f100020002802000520010b2c00014100480d0010000b0b9f0101037f02402000280204044020001029200028020022022c000022014100480d0120014100470f0b41000f0b027f4101200141807f460d001a200141ff0171220341b7014d0440200041046a28020041014d047f100020002802000520020b2d00014100470f0b4100200341bf014b0d001a200041046a280200200141ff017141ca7e6a22014d047f100020002802000520020b20016a2d00004100470b0baf0102057f017e230041206b2201240002402000280204450d0020002802002d000041c001490d00200141186a20001026200128021c210003402000450d012001200129031822063703102001200637030841002100200141086a10232102027f2001280218220404404100200128021c22052002490d011a200520026b2100200220046a0c010b41000b21022001200036021c20012002360218200341016a21030c000b000b200141206a240020030b1d01017f200020012802002203200320012802046a102d20002002102e0b2c002000200220016b2202102f200028020020002802046a2001200210081a2000200028020420026a3602040b9e0201077f02402001450d002000410c6a2107200041106a2105200041046a21060340200528020022022007280200460d01200241786a28020020014904401000200528020021020b200241786a2203200328020020016b220136020020010d01200520033602002000410120062802002002417c6a28020022016b22021030220341016a20024138491b2204200628020022086a10312004200120002802006a22046a2004200820016b100f1a0240200241374d0440200028020020016a200241406a3a00000c010b200341f7016a220441ff014d0440200028020020016a20043a00002000280200200120036a6a210103402002450d02200120023a0000200241087621022001417f6a21010c000b000b10000b410121010c000b000b0b1b00200028020420016a220120002802084b04402000200110320b0b1e01017f03402000044020004108762100200141016a21010c010b0b20010b0f00200020011032200020013602040b3901017f200028020820014904402001100b22022000280200200028020410081a20002802001a200041086a2001360200200020023602000b0b5001017f230041106b2202240002402001044020022001360200200220002802043602042000410c6a200210340c010b2002410036020820024200370300200020021035200210360b200241106a24000b3701017f20002802042202200028020849044020022001290200370200200041046a2200200028020041086a3602000f0b2000200110370b3d01027f230041106b220224002002200128020022033602082002200128020420036b36020c20022002290308370300200020021038200241106a24000b1501017f200028020022010440200020013602040b0b7201027f230041206b22032400200341086a2000200028020420002802006b41037541016a1040200028020420002802006b410375200041086a1041220228020820012902003702002002200228020841086a36020820002002104220022002280204104420022802001a200341206a24000b5902027f017e230041106b2202240002402001280204220341374d04402000200341406a41ff017110390c010b2000200341f701103a0b20022001290200220437030820022004370300200020024101102c200241106a24000b250020004101102f200028020020002802046a20013a00002000200028020441016a3602040b2a01017f20022001103022026a22034180024e044010000b2000200341ff01711039200020012002103b0b3d002000200028020420026a1031200028020020002802046a417f6a2100034020010440200020013a0000200141087621012000417f6a21000c010b0b0b920101037f230041106b2202240020012802002103024002400240024020012802042201410146044020032c000022044100480d012000200441ff017110390c040b200141374b0d010b200020014180017341ff017110390c010b2000200141b701103a0b2002200136020c2002200336020820022002290308370300200020024100102c0b20004101102e200241106a24000b830101037f02400240200150450440200142ff00560d0120002001a741ff017110390c020b200041800110390c010b02402001103e220241374d0440200020024180017341ff017110390c010b20021030220341b7016a22044180024f044010000b2000200441ff01711039200020022003103b0b200020012002103f0b20004101102e0b3202017f017e034020002002845045044020024238862000420888842100200141016a2101200242088821020c010b0b20010b5101017e2000200028020420026a1031200028020020002802046a417f6a21000340200120038450450440200020013c0000200342388620014208888421012000417f6a2100200342088821030c010b0b0b40002001418080808002490440200028020820002802006b220041037541feffffff004d047f20012000410275220020002001491b0541ffffffff010b0f0b000b6401017f2000410036020c200041106a200336020020010440027f20014180808080024904402001410374100d0c010b000b21040b200020043602002000200420024103746a22023602082000410c6a200420014103746a3602002000200236020420000b6701017f20002802002000280204200141046a1043200028020021022000200128020436020020012002360204200028020421022000200128020836020420012002360208200028020821022000200128020c3602082001200236020c200120012802043602000b270020022002280200200120006b22016b2202360200200141014e044020022000200110081a0b0b2c01017f20002802082102200041086a2100034020012002464504402000200241786a22023602000c010b0b0b070041b00a10150b040041050b040041760b0400411e0b0400410a0b6101037f230041106b22022400200041186a2201200241bc0a104b2203104c200310152001200241bf0a104b2203104c200310152001200241c20a104b2201104c200110152000411c6a2802002000280218200241106a24006b410c6e41ff01710b1f0020004200370200200041086a4100360200200020012001100e101320000bc20101037f230041206b22032400024020002802042202200028020849044020022001290200370200200241086a200141086a28020036020020011019200041046a22002000280200410c6a3602000c010b200341086a2000200220002802006b410c6d41016a104d200041046a28020020002802006b410c6d200041086a104e220228020822042001290200370200200441086a200141086a2802003602002001101920022002280208410c6a36020820002002104f200210500b200341206a24000b4000200141d6aad5aa01490440200028020820002802006b410c6d220041a9d5aad5004d047f20012000410174220020002001491b0541d5aad5aa010b0f0b000b4f01017f2000410036020c200041106a2003360200200104402001106621040b20002004360200200020042002410c6c6a22023602082000410c6a20042001410c6c6a3602002000200236020420000bbb0101047f2000280204210320002802002104200141046a21020340200320044645044020022802002205417c6a2003417c6a280200360200200541746a200341746a2203290200370200200310192002200228020041746a3602000c010b0b200028020021022000200141046a220328020036020020032002360200200041046a220228020021042002200128020836020020012004360208200028020821022000200128020c3602082001200236020c200120032802003602000b3a01037f20002802042103200041086a210203402003200228020022014704402002200141746a2201360200200110150c010b0b20002802001a0bf40302037f017e230041b0016b22002400100710012201100b22021002200041f8006a20004190016a2002200110521027200041f8006a102902400240200041f8006a102a450d00200028027c450d0020002802782d000041c001490d010b10000b200041a8016a200041f8006a105320002802ac01220141094f044010000b20002802a80121020340200104402001417f6a210120023100002003420886842103200241016a21020c010b0b024002402003500d0041c50a10542003510440200041003602742000410236027020002000290370370308200041086a10550c020b41ca0a105420035104402000410036026c2000410336026820002000290368370310200041106a10560c020b41d70a10542003510440200041003602642000410436026020002000290360370318200041186a10560c020b41e60a105420035104402000410036025c2000410536025820002000290358370320200041206a10560c020b41f80a10542003510440200041003602542000410636025020002000290350370328200041286a10570c020b41890b105420035104402000410036024c2000410736024820002000290348370330200041306a10550c020b419b0b10542003520d00200041003602442000410836024020002000290340370338200041386a10570c010b10000b200041b0016a24000b3401017f230041106b220324002003200236020c200320013602082003200329030837030020002003411c101e200341106a24000be60101047f200110212204200128020422024b04401000200141046a28020021020b200128020021052000027f024002400240027f0240200204404100210120052c00002203417f4c0d012005450d030c040b41000c010b200341ff0171220141bf014d04404100200341ff017141b801490d011a200141c97e6a0c010b4100200341ff017141f801490d001a200141897e6a0b41016a210120050d010b410021030c010b410021032002200149200120046a20024b720d00410020022004490d011a200120056a2103200220016b20042004417f461b0c010b41000b360204200020033602000b3901027e42a5c688a1c89ca7f94b210103402000300000220250450440200041016a2100200142b383808080207e20028521010c010b0b20010b5101047f230041306b22012400200141086a200028020422034101756a210220002802002100200141086a105820022003410171047f200228020020006a2802000520000b1102001059200141306a24000bd90102047f017e230041e0006b220124002001200028020422034101756a2102200028020021002001105820022003410171047f200228020020006a2802000520000b1101002102200141286a105a2100200141d8006a4100360200200141d0006a4200370300200141c8006a420037030020014200370340200141406b2002ac22054201862005423f87852205105b20012802402102200141406b410472105c20002002105d20002005105e200028020c200041106a28020047044010000b2000280200200028020410032000105f1059200141e0006a24000bce0102047f017e230041e0006b220124002001200028020422034101756a2102200028020021002001105820022003410171047f200228020020006a2802000520000b1101002102200141286a105a2100200141d8006a4100360200200141d0006a4200370300200141c8006a420037030020014200370340200141406b2002ad2205106020012802402102200141406b410472105c20002002105d20002005103d200028020c200041106a28020047044010000b2000280200200028020410032000105f1059200141e0006a24000beb08020d7f017e230041b0016b22012400200042003702182000428debc585c3a7f9dbf7003703102000410036020820004200370200200041206a4100360200200141386a105a22072000290310105e200728020c200741106a28020047044010000b200041186a2108024002400240200728020022062007280204220410042205044020014100360230200142003703282005417f4c0d02200141306a2005100d20051009220320056a22093602002001200936022c2001200336022820062004200320051005417f47044002400240200141106a2001280228220241016a200128022c2002417f736a10522202280204450d0020022802002d000041c001490d002002102b2103200041206a2206280200200041186a28020022046b410c6d20034904402008200141e0006a20032000411c6a28020020046b410c6d2006104e2203104f200310500b20014188016a2002410110252109200141f8006a200241001025210b20014198016a41017221060340200941046a2202280200200b41046a280200460440200941086a280200200b41086a280200460d030b20012002290200220e370398012001200e370308200141e0006a200141086a411c101e2102200141d0006a101a210c0240024002400240024002402001280264450d0020012802602d000041c0014f0d00200141a8016a2002105320021021210320012802a8012202044020012802ac01220420034f0d020b41002102200141a0016a41003602002001420037039801410021044100210d0c020b20014198016a101a1a0c040b200141a0016a220a41003602002001420037039801200420032003417f461b220441704f0d09200220046a210d2004410a4b0d010b200120044101743a009801200621030c010b200a200441106a417071220a100d22033602002001200436029c012001200a410172360298010b03402002200d470440200320022d00003a0000200341016a2103200241016a21020c010b0b200341003a00000b024020012d0050410171450440200141003b01500c010b200141d8006a220228020041003a00002001410036025420012d0050410171450d0020022802001a200141003602500b200141d8006a200141a0016a280200360200200120012903980137035020014198016a101920014198016a10152008200c104c200c1015200910220c000b000b10000b200521020b200141286a10360b2007105f024020020d002000411c6a2103200041046a2802002206200028020022026b410c6d2204200041206a280200200041186a28020022056b410c6d4b04402005044020081067200041186a22052802001a200041206a4100360200200542003702000b20082004104d220541d6aad5aa014f0d04200041186a2005106622043602002000411c6a2004360200200041206a20042005410c6c6a36020020022006200310680c010b2004200328020020056b410c6d22044b0440200220022004410c6c6a2202200510691a20022006200310680c010b20082002200620051069106a0b200141b0016a240020000f0b000b000b000b9a07010d7f230041f0006b22012400200141206a105a2106200141d0006a22034100360200200141c8006a22024200370300200141406b2205420037030020014200370338200141386a2000290310105b20012802382104200141386a410472105c20062004105d20062000290310105e200628020c200641106a28020047044010000b2006280204210b2006280200200141086a105a210420034100360200200242003703002005420037030020014200370338027f200041186a2802002000411c6a2203280200470440200141386a4100106b20032802002107200041186a2802002103200141d8006a4101722108200141cc006a2109034020032007470440200141d8006a20031012410121050240200128025c20012d005822024101762002410171220d1b2202450d000240024020024101460440200141e0006a2802002008200d1b2c0000417f4a0d030c010b200241374b0d010b200241016a21050c010b2002103020026a41016a21050b027f200141d0006a28020022020440200141406b280200200220092802006a417f6a220241087641fcffff07716a280200200241ff07714102746a0c010b200141386a0b2202200228020020056a36020010152003410c6a21030c010b0b200141386a4101106b20012802380c010b2001410136023841010b2105200141386a410472105c4101100d220341fe013a0000200120033602582001200341016a22023602602001200236025c200428020c200441106a2802004704401000200128025c2102200128025821030b200220036b2202200428020422076a220820042802084b047f200420081061200441046a2802000520070b20042802006a2003200210081a200441046a2203200328020020026a3602002004200128025c20056a20012802586b105d20042000411c6a2203280200200041186a22022802006b410c6d10332003280200210520022802002103200141386a4101722102200141406b2107034020032005470440200141386a2003101220012007280200200220012d00382209410171220a1b3602682001200128023c2009410176200a1b36026c2001200129036837030020042001103c10152003410c6a21030c010b0b02402004410c6a2202280200200441106a220528020047044010002004280200210320022802002005280200460d0110000c010b200428020021030b200b2003200441046a2802001006200141d8006a10362004105f2006105f200041186a106c2000106c200141f0006a24000b29002000410036020820004200370200200041001061200041146a41003602002000420037020c20000b08002000200110600ba90201067f230041106b22042400200028020422012000280210220341087641fcffff07716a2102027f200120002802084704402002280200200341ff07714102746a0c010b41000b2101200441086a20001064200428020c2103034020012003470440200141046a220120022802006b418020470d0120022802042101200241046a21020c010b0b20004100360214200041046a22022802002101200041086a210503402005280200220620016b410275220341034f044020012802001a2002200228020041046a22013602000c010b0b0240200041106a027f2003410147044020034102470d024180080c010b4180040b3602000b03402001200647044020012802001a200141046a21010c010b0b2000200041046a280200106520002802001a200441106a24000b1300200028020820014904402000200110610b0b080020002001103d0b1c01017f200028020c22010440200041106a20013602000b200010620b7502027f017e4101210320014280015a0440034020012004845045044020044238862001420888842101200241016a2102200442088821040c010b0b200241384f047f2002103020026a0520020b41016a21030b200041186a2802000440200041046a106321000b2000200028020020036a3602000b3601017f200028020820014904402001100b200028020020002802041008210220001062200041086a2001360200200020023602000b0b080020002802001a0b2e002000280204200028021420002802106a417f6a220041087641fcffff07716a280200200041ff07714102746a0b5101037f20012802042203200128021020012802146a220441087641fcffff07716a21022000027f200320012802084704402002280200200441ff07714102746a0c010b41000b360204200020023602000b2c01017f20002802082102200041086a21000340200120024645044020002002417c6a22023602000c010b0b0b1700200041d6aad5aa014904402000410c6c100d0f0b000b0b0020002000280200106a0b2e00034020002001464504402002280200200010121a20022002280200410c6a3602002000410c6a21000c010b0b0b2600034020002001464504402002200010162002410c6a21022000410c6a21000c010b0b20020b2c01017f2000280204210203402001200246450440200241746a220210150c010b0b200041046a20013602000bed0e020b7f027e230041306b22062400200041046a2109024020014101460440200910632802002101200041186a22022002280200417f6a3602002009106d4180104f04402000410c6a2202280200417c6a2802001a20092002280200417c6a10650b200141384f047f2001103020016a0520010b41016a2101200041186a280200450d012009106321000c010b02402009106d0d00200041146a220128020022024180084f0440200120024180786a360200200041086a2201280200220228020021042001200241046a360200200620043602182009200641186a106e0c010b024002400240024002400240024002402000410c6a2802002202200041086a2802006b4102752204200041106a2203280200220520002802046b2201410275490440418020100d210820022005470d01200041086a22042802002202200041046a2802002205460d02200221010c080b20062001410175410120011b20042003106f2104418020100d210820042802082201200428020c2205470d0320042802042202200428020022034d0d02200120026b220141027521052002200220036b41027541016a417e6d41027422076a2103200441046a2001047f200320022001100f1a200441046a2802000520020b20076a360200200441086a200320054102746a22013602000c030b2000410c6a22042802002201200041106a2802002205470d04200041086a22072802002202200041046a28020022034d0d03200120026b220141027521052002200220036b41027541016a417e6d41027422076a2103200041086a2001047f200320022001100f1a200041086a2802000520020b20076a3602002000410c6a200320054102746a22013602000c040b2000410c6a22072802002203200041106a220a28020022014f0d042003200120036b41027541016a41026d41027422076a2101200320026b22050440200120056b220120022005100f1a2000410c6a28020021030b200041086a20013602002000410c6a200320076a3602000c050b200641186a200520036b2201410175410120011b22012001410276200441106a280200106f2102200441086a2802002105200441046a2802002101034020012005470440200241086a220328020020012802003602002003200328020041046a360200200141046a21010c010b0b2004290200210d200420022902003702002002200d370200200441086a2201290200210d2001200241086a22032902003702002003200d37020020021070200128020021010b20012008360200200441086a2208200828020041046a3602002000410c6a2802002105200441106a210b0340200041086a28020020054704402005417c6a21050240200441046a220728020022022004280200220a470440200221010c010b200828020022032004410c6a28020022014904402003200120036b41027541016a41026d410274220c6a2101200320026b220a04402001200a6b22012002200a100f1a200828020021030b2007200136020020082003200c6a3602000c010b200641186a2001200a6b2201410175410120011b2201200141036a410276200b280200106f2008280200210a2007280200210103402001200a470440200641206a220228020020012802003602002002200228020041046a360200200141046a21010c010b0b2004290200210d200420062903183702002008290200210e2008200641206a22012903003702002001200e3703002006200d3703181070200728020021010b2001417c6a200528020036020020072007280200417c6a3602000c010b0b200041046a220128020021022001200428020036020020042002360200200441046a2201280200210220012005360200200041086a20023602002000410c6a2201290200210d2001200441086a22012902003702002001200d370200200410700c040b200641186a200520036b2201410175410120011b22012001410276200041106a106f21022000410c6a280200210520072802002101034020012005470440200241086a220328020020012802003602002003200328020041046a360200200141046a21010c010b0b200041046a2201290200210d200120022902003702002002200d3702002000410c6a2201290200210d2001200241086a22032902003702002003200d37020020021070200128020021010b200120083602002004200428020041046a3602000c020b200641186a200120056b2201410175410120011b2201200141036a410276200a106f210220072802002105200041086a2802002101034020012005470440200241086a220328020020012802003602002003200328020041046a360200200141046a21010c010b0b200041046a2201290200210d200120022902003702002002200d3702002000410c6a2201290200210d2001200241086a22012902003702002001200d37020020021070200041086a28020021010b2001417c6a2008360200200420042802002201417c6a22023602002002280200210220042001360200200620023602182009200641186a106e0b200641186a20091064200628021c4100360200200041186a2100410121010b2000200028020020016a360200200641306a24000b1400200028020004402000106720002802001a0b0b2801017f200028020820002802046b2201410874417f6a410020011b200028021420002802106a6b0be50202067f017e230041206b22062400024020002802082202200028020c2205470d0020002802042203200028020022044b0440200220036b220241027521052003200320046b41027541016a417e6d41027422076a2104200041046a2002047f200420032002100f1a200041046a2802000520030b20076a360200200041086a200420054102746a22023602000c010b200641086a200520046b2202410175410120021b220220024102762000410c6a106f2103200041086a2802002105200041046a280200210203402002200546450440200341086a220428020020022802003602002004200428020041046a360200200241046a21020c010b0b200029020021082000200329020037020020032008370200200041086a220229020021082002200341086a22042902003702002004200837020020031070200228020021020b20022001280200360200200041086a2200200028020041046a360200200641206a24000b6201017f2000410036020c200041106a200336020002402001044020014180808080044f0d012001410274100d21040b200020043602002000200420024102746a22023602082000410c6a200420014102746a3602002000200236020420000f0b000b3801037f2000280208210120002802042102200041086a210303402001200247044020032001417c6a22013602000c010b0b20002802001a0b0b77010041bc0a0b70763100763200763300696e6974006765745f616e746f5f696e74006765745f616e746f5f696e743332006765745f616e746f5f6d756c7469706c65006765745f616e746f5f75696e74385f74007365745f616e746f5f636172655f6f6e65006765745f616e746f5f6974657261746f72";

    public static String BINARY = BINARY_0;

    public static final String FUNC_GET_ANTO_ITERATOR = "get_anto_iterator";

    public static final String FUNC_GET_ANTO_INT = "get_anto_int";

    public static final String FUNC_GET_ANTO_INT32 = "get_anto_int32";

    public static final String FUNC_GET_ANTO_MULTIPLE = "get_anto_multiple";

    public static final String FUNC_GET_ANTO_UINT8_T = "get_anto_uint8_t";

    public static final String FUNC_SET_ANTO_CARE_ONE = "set_anto_care_one";

    protected AutoTypeContract(String contractAddress, Web3j web3j, Credentials credentials, GasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    protected AutoTypeContract(String contractAddress, Web3j web3j, TransactionManager transactionManager, GasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteCall<Uint8> get_anto_iterator() {
        final WasmFunction function = new WasmFunction(FUNC_GET_ANTO_ITERATOR, Arrays.asList(), Uint8.class);
        return executeRemoteCall(function, Uint8.class);
    }

    public static RemoteCall<AutoTypeContract> deploy(Web3j web3j, Credentials credentials, GasProvider contractGasProvider) {
        String encodedConstructor = WasmFunctionEncoder.encodeConstructor(BINARY, Arrays.asList());
        return deployRemoteCall(AutoTypeContract.class, web3j, credentials, contractGasProvider, encodedConstructor);
    }

    public static RemoteCall<AutoTypeContract> deploy(Web3j web3j, TransactionManager transactionManager, GasProvider contractGasProvider) {
        String encodedConstructor = WasmFunctionEncoder.encodeConstructor(BINARY, Arrays.asList());
        return deployRemoteCall(AutoTypeContract.class, web3j, transactionManager, contractGasProvider, encodedConstructor);
    }

    public static RemoteCall<AutoTypeContract> deploy(Web3j web3j, Credentials credentials, GasProvider contractGasProvider, BigInteger initialVonValue) {
        String encodedConstructor = WasmFunctionEncoder.encodeConstructor(BINARY, Arrays.asList());
        return deployRemoteCall(AutoTypeContract.class, web3j, credentials, contractGasProvider, encodedConstructor, initialVonValue);
    }

    public static RemoteCall<AutoTypeContract> deploy(Web3j web3j, TransactionManager transactionManager, GasProvider contractGasProvider, BigInteger initialVonValue) {
        String encodedConstructor = WasmFunctionEncoder.encodeConstructor(BINARY, Arrays.asList());
        return deployRemoteCall(AutoTypeContract.class, web3j, transactionManager, contractGasProvider, encodedConstructor, initialVonValue);
    }

    public RemoteCall<Int32> get_anto_int() {
        final WasmFunction function = new WasmFunction(FUNC_GET_ANTO_INT, Arrays.asList(), Int32.class);
        return executeRemoteCall(function, Int32.class);
    }

    public RemoteCall<Int32> get_anto_int32() {
        final WasmFunction function = new WasmFunction(FUNC_GET_ANTO_INT32, Arrays.asList(), Int32.class);
        return executeRemoteCall(function, Int32.class);
    }

    public RemoteCall<Int32> get_anto_multiple() {
        final WasmFunction function = new WasmFunction(FUNC_GET_ANTO_MULTIPLE, Arrays.asList(), Int32.class);
        return executeRemoteCall(function, Int32.class);
    }

    public RemoteCall<Uint8> get_anto_uint8_t() {
        final WasmFunction function = new WasmFunction(FUNC_GET_ANTO_UINT8_T, Arrays.asList(), Uint8.class);
        return executeRemoteCall(function, Uint8.class);
    }

    public RemoteCall<TransactionReceipt> set_anto_care_one() {
        final WasmFunction function = new WasmFunction(FUNC_SET_ANTO_CARE_ONE, Arrays.asList(), Void.class);
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> set_anto_care_one(BigInteger vonValue) {
        final WasmFunction function = new WasmFunction(FUNC_SET_ANTO_CARE_ONE, Arrays.asList(), Void.class);
        return executeRemoteCallTransaction(function, vonValue);
    }

    public static AutoTypeContract load(String contractAddress, Web3j web3j, Credentials credentials, GasProvider contractGasProvider) {
        return new AutoTypeContract(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static AutoTypeContract load(String contractAddress, Web3j web3j, TransactionManager transactionManager, GasProvider contractGasProvider) {
        return new AutoTypeContract(contractAddress, web3j, transactionManager, contractGasProvider);
    }
}

package network.platon.contracts.wasm;

import com.platon.rlp.datatypes.Int64;
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
public class InsertSort extends WasmContract {
    private static String BINARY_0 = "0x0061736d0100000001530f60027f7f0060017f0060017f017f60000060037f7f7f0060027f7f017f60037f7f7f017f60047f7f7f7f017f60027f7e0060017f017e60047f7f7f7f0060037f7f7e0060037f7e7f006000017f60017e017f02a9010703656e760c706c61746f6e5f70616e6963000303656e7617706c61746f6e5f6765745f696e7075745f6c656e677468000d03656e7610706c61746f6e5f6765745f696e707574000103656e760d706c61746f6e5f72657475726e000003656e7617706c61746f6e5f6765745f73746174655f6c656e677468000503656e7610706c61746f6e5f6765745f7374617465000703656e7610706c61746f6e5f7365745f7374617465000a03585703060503020204030301030301010602020201020a06000402010202040100020000000000010000000004080e0c0507000400010b050100030609090201000902020000010001080802010000020700010002000701030405017001030305030100020615037f0141d08a040b7f0041d08a040b7f0041d00a0b075406066d656d6f72790200115f5f7761736d5f63616c6c5f63746f727300070b5f5f686561705f6261736503010a5f5f646174615f656e6403020f5f5f66756e63735f6f6e5f65786974001206696e766f6b65003f0908010041010b02143a0aa864571c00100a41a40a420037020041ac0a4100360200101141011013105d0ba20a010d7f2002410f6a210f410020026b21072002410e6a210a410120026b210e2002410d6a210d410220026b210c0340200020056a2103200120056a220441037145200220054672450440200320042d00003a0000200f417f6a210f200741016a2107200a417f6a210a200e41016a210e200d417f6a210d200c41016a210c200541016a21050c010b0b200220056b210602400240024002402003410371220b044020064120490d03200b4101460d01200b4102460d02200b4103470d032003200120056a280200220a3a0000200041016a210b200220056b417f6a210c200521030340200c4113494504402003200b6a2208200120036a220941046a2802002206411874200a41087672360200200841046a200941086a2802002204411874200641087672360200200841086a2009410c6a28020022064118742004410876723602002008410c6a200941106a280200220a411874200641087672360200200341106a2103200c41706a210c0c010b0b2002417f6a2007416d2007416d4b1b200f6a4170716b20056b2106200120036a41016a2104200020036a41016a21030c030b2006210403402004411049450440200020056a2203200120056a2202290200370200200341086a200241086a290200370200200541106a2105200441706a21040c010b0b027f2006410871450440200120056a2104200020056a0c010b200020056a2202200120056a2201290200370200200141086a2104200241086a0b21052006410471044020052004280200360200200441046a2104200541046a21050b20064102710440200520042f00003b0000200441026a2104200541026a21050b2006410171450d03200520042d00003a000020000f0b2003200120056a2206280200220a3a0000200341016a200641016a2f00003b0000200041036a210b200220056b417d6a210720052103034020074111494504402003200b6a2208200120036a220941046a2802002206410874200a41187672360200200841046a200941086a2802002204410874200641187672360200200841086a2009410c6a28020022064108742004411876723602002008410c6a200941106a280200220a410874200641187672360200200341106a2103200741706a21070c010b0b2002417d6a200c416f200c416f4b1b200d6a4170716b20056b2106200120036a41036a2104200020036a41036a21030c010b2003200120056a2206280200220d3a0000200341016a200641016a2d00003a0000200041026a210b200220056b417e6a210720052103034020074112494504402003200b6a2208200120036a220941046a2802002206411074200d41107672360200200841046a200941086a2802002204411074200641107672360200200841086a2009410c6a28020022064110742004411076723602002008410c6a200941106a280200220d411074200641107672360200200341106a2103200741706a21070c010b0b2002417e6a200e416e200e416e4b1b200a6a4170716b20056b2106200120036a41026a2104200020036a41026a21030b20064110710440200320042d00003a00002003200428000136000120032004290005370005200320042f000d3b000d200320042d000f3a000f200441106a2104200341106a21030b2006410871044020032004290000370000200441086a2104200341086a21030b2006410471044020032004280000360000200441046a2104200341046a21030b20064102710440200320042f00003b0000200441026a2104200341026a21030b2006410171450d00200320042d00003a00000b20000be10201027f02402001450d00200041003a0000200020016a2202417f6a41003a000020014103490d00200041003a0002200041003a00012002417d6a41003a00002002417e6a41003a000020014107490d00200041003a00032002417c6a41003a000020014109490d002000410020006b41037122036a220241003602002002200120036b417c7122036a2201417c6a410036020020034109490d002002410036020820024100360204200141786a4100360200200141746a410036020020034119490d002002410036021820024100360214200241003602102002410036020c200141706a41003602002001416c6a4100360200200141686a4100360200200141646a41003602002003200241047141187222036b2101200220036a2102034020014120490d0120024200370300200241186a4200370300200241106a4200370300200241086a4200370300200241206a2102200141606a21010c000b000b20000b3501017f230041106b220041d08a0436020c418408200028020c41076a41787122003602004180082000360200418c083f003602000b9f0101047f230041106b220224002002200036020c027f02400240024020000440418c08200041086a22014110762200418c082802006a2203360200418408200141840828020022016a41076a4178712204360200200341107420044d0d0120000d020c030b41000c030b418c08200341016a360200200041016a21000b200040000d0010000b20012002410c6a410410081a200141086a0b200241106a24000b2f01027f2000410120001b2100034002402000100b22010d004190082802002202450d0020021103000c010b0b20010bbf0301057f024020002001460d00027f02400240200120006b20026b410020024101746b4b044020002001734103712103200020014f0d012003450d0220000c030b20002001200210081a0f0b024020030d002001417f6a21040340200020026a220341037104402002450d052003417f6a200220046a2d00003a00002002417f6a21020c010b0b2000417c6a21042001417c6a2103034020024104490d01200220046a200220036a2802003602002002417c6a21020c000b000b2001417f6a210103402002450d03200020026a417f6a200120026a2d00003a00002002417f6a21020c000b000b200241046a21052002417f73210303400240200120046a2106200020046a2207410371450d0020022004460d03200720062d00003a00002005417f6a2105200341016a2103200441016a21040c010b0b200220046b2101410021000340200141044f0440200020076a200020066a280200360200200041046a21002001417c6a21010c010b0b200020066a210120022003417c2003417c4b1b20056a417c716b20046b2102200020076a0b210003402002450d01200020012d00003a00002002417f6a2102200041016a2100200141016a21010c000b000b0b0a0041940841013602000b0a0041940841003602000b130020002d0000410171044020002802081a0b0b2301017f03402000410c470440200041a40a6a4100360200200041046a21000c010b0b0b7601037f100e41980828020021000340200004400340419c08419c082802002201417f6a22023602002001410148450440200020024102746a22004184016a280200200041046a280200100f110100100e41980828020021000c010b0b419c084120360200419808200028020022003602000c010b0b0b940101027f100e419808280200220145044041980841a00836020041a00821010b0240419c0828020022024120460440418402100b22010440200141840210091a0b2001450d0120014198082802003602004198082001360200419c084100360200410021020b419c08200241016a360200200120024102746a22014184016a4100360200200141046a2000360200100f0f0b100f0b070041a40a10100b780020004200370210200042ffffffff0f3702082000200129020037020002402002410871450d002000101620012802044f0d002002410471450440200042003702000c010b10000b024002402002411071450d002000101620012802044d0d0020024104710d01200042003702000b20000f0b100020000b290002402000280204044020002802002c0000417f4c0d0141010f0b41000f0b20001017200010186a0b240002402000280204450d0020002802002c0000417f4c0d0041000f0b2000101f41016a0b8a0301047f0240024020002802040440200010204101210220002802002c00002201417f4c0d010c020b41000f0b200141ff0171220241b7014d0440200241807f6a0f0b02400240200141ff0171220141bf014d04400240200041046a22042802002201200241c97e6a22034d047f100020042802000520010b4102490d0020002802002d00010d0010000b200341054f044010000b20002802002d000145044010000b410021024100210103402001200346450440200028020020016a41016a2d00002002410874722102200141016a21010c010b0b200241384f0d010c020b200141f7014d0440200241c07e6a0f0b0240200041046a22042802002201200241897e6a22034d047f100020042802000520010b4102490d0020002802002d00010d0010000b200341054f044010000b20002802002d000145044010000b410021024100210103402001200346450440200028020020016a41016a2d00002002410874722102200141016a21010c010b0b20024138490d010b200241ff7d490d010b100020020f0b20020bd20102057f017e230041206b22022400200041046a210102402000280200220404402001280200220504402005200041086a2802006a21030b200041086a2004360200200041046a2204200336020020022004290200220637031020022006370308200241186a20014100200241086a101a101b2004200229031822063702002000200028020022002006422088a722012000200020014b1b6b3602000c010b2001280200220104402001200041086a2802006a21030b200041086a4100360200200041046a20033602000b200241206a24000b3902017f017e230041306b2201240020012000290200220237031020012002370308200141186a200141086a411410151016200141306a24000b5e01027f2000027f027f2001280200220504404100200220036a200128020422014b2001200249720d011a410020012003490d021a200220056a2104200120026b20032003417f461b0c020b41000b210441000b360204200020043602000b850202037f017e230041206b2203240020004200370200200041086a22054100360200200128020421040240027f2002044041002004450d011a200420012802002d000041c001490d011a200341186a2001101d20032003290318220637031020032006370308200341086a101a2101200041086a4100200328021c220220012001417f461b200328021822044520022001497222011b2205360200200041046a4100200420011b3602002000200220056b3602000c020b20040b210220012802002104200128020421012000410036020020054100200220016b20044520022001497222021b360200200041046a4100200120046a20021b3602000b200341206a240020000b2101017f20011018220220012802044b044010000b20002001200110172002101b0b900302097f017e230041406a220324002001280208220520024b0440200341386a2001101d200320032903383703182001200341186a101a36020c200341306a2001101d410021052001027f410020032802302206450d001a410020032802342208200128020c2207490d001a200820072007417f461b210420060b360210200141146a2004360200200141086a41003602000b200141106a2109200141146a21072001410c6a2106200141086a210803400240200520024f0d002007280200450d00200341306a2001101d41002105027f2003280230220a044041002003280234220b20062802002204490d011a200b20046b21052004200a6a0c010b41000b210420072005360200200920043602002003200536022c2003200436022820032003290328370310200341306a20094100200341106a101a101b20092003290330220c37020020062006280200200c422088a76a3602002008200828020041016a22053602000c010b0b20032009290200220c3703202003200c3703082000200341086a411410151a200341406b24000b4101017f02402000280204450d0020002802002d0000220041bf014d0440200041b801490d01200041c97e6a0f0b200041f801490d00200041897e6a21010b20010b4401017f200028020445044010000b0240200028020022012d0000418101470d00200041046a28020041014d047f100020002802000520010b2c00014100480d0010000b0b9f0101037f02402000280204044020001020200028020022022c000022014100480d0120014100470f0b41000f0b027f4101200141807f460d001a200141ff0171220341b7014d0440200041046a28020041014d047f100020002802000520020b2d00014100470f0b4100200341bf014b0d001a200041046a280200200141ff017141ca7e6a22014d047f100020002802000520020b20016a2d00004100470b0baf0102057f017e230041206b2201240002402000280204450d0020002802002d000041c001490d00200141186a2000101d200128021c210003402000450d012001200129031822063703102001200637030841002100200141086a101a2102027f2001280218220404404100200128021c22052002490d011a200520026b2100200220046a0c010b41000b21022001200036021c20012002360218200341016a21030c000b000b200141206a240020030b2c002000200220016b22021025200028020020002802046a2001200210081a2000200028020420026a3602040b940201097f02402000410c6a2107200041106a2104200041046a21060340200428020022012007280200460d01200141786a28020041014904401000200428020021010b200141786a2202200228020041016b220336020020030d01200420023602002000410120062802002001417c6a28020022026b22011026220341016a20014138491b2205200628020022086a10272005200220002802006a22096a2009200820026b100d0240200141374d0440200028020020026a200141406a3a00000c010b200341f7016a220541ff014d0440200028020020026a20053a00002000280200200220036a6a210203402001450d02200220013a0000200141087621012002417f6a21020c000b000b10000b0c000b000b0b1b00200028020420016a220120002802084b04402000200110280b0b1e01017f03402000044020004108762100200141016a21010c010b0b20010b0f00200020011028200020013602040b3901017f200028020820014904402001100b22022000280200200028020410081a20002802001a200041086a2001360200200020023602000b0b5001017f230041106b2202240002402001044020022001360200200220002802043602042000410c6a2002102a0c010b200241003602082002420037030020002002102b2002102c0b200241106a24000b3701017f20002802042202200028020849044020022001290200370200200041046a2200200028020041086a3602000f0b20002001102d0b3d01027f230041106b220224002002200128020022033602082002200128020420036b36020c2002200229030837030020002002102e200241106a24000b1501017f200028020022010440200020013602040b0b7201027f230041206b22032400200341086a2000200028020420002802006b41037541016a1035200028020420002802006b410375200041086a1036220228020820012902003702002002200228020841086a36020820002002103720022002280204103920022802001a200341206a24000b6502027f017e230041106b2202240002402001280204220341374d04402000200341406a41ff0171102f0c010b2000200310300b20022001290200220437030820022004370300200020022802002201200120022802046a102320001024200241106a24000b2500200041011025200028020020002802046a20013a00002000200028020441016a3602040b2b01027f20011026220241f7016a22034180024e044010000b2000200341ff0171102f20002001200210310b3d002000200028020420026a1027200028020020002802046a417f6a2100034020010440200020013a0000200141087621012000417f6a21000c010b0b0b810101037f02400240200150450440200142ff00560d0120002001a741ff0171102f0c020b2000418001102f0c010b024020011033220241374d0440200020024180017341ff0171102f0c010b20021026220341b7016a22044180024f044010000b2000200441ff0171102f20002002200310310b20002001200210340b200010240b3202017f017e034020002002845045044020024238862000420888842100200141016a2101200242088821020c010b0b20010b5101017e2000200028020420026a1027200028020020002802046a417f6a21000340200120038450450440200020013c0000200342388620014208888421012000417f6a2100200342088821030c010b0b0b40002001418080808002490440200028020820002802006b220041037541feffffff004d047f20012000410275220020002001491b0541ffffffff010b0f0b000b6401017f2000410036020c200041106a200336020020010440027f20014180808080024904402001410374100c0c010b000b21040b200020043602002000200420024103746a22023602082000410c6a200420014103746a3602002000200236020420000b6701017f20002802002000280204200141046a1038200028020021022000200128020436020020012002360204200028020421022000200128020836020420012002360208200028020821022000200128020c3602082001200236020c200120012802043602000b270020022002280200200120006b22016b2202360200200141014e044020022000200110081a0b0b2c01017f20002802082102200041086a2100034020012002464504402000200241786a22023602000c010b0b0b070041b00a10100bd10102087f017e230041106b220724002001280200220841086a21052002a72109410121030340200320094e450440200820034103746a340200210b20052104200321060340024020064101480d00200441786a220a2903002202200b570d002006417f6a210620042002370300200a21040c010b0b2004200b370300200541086a2105200341016a21030c010b0b20072001103c2101200041186a103d2000200128020036021820012902042102200141003602082000411c6a2002370200200142003702002001102c200741106a24000b6501027f20004200370200200041003602080240200128020420012802006b2202450d0020002002410375103e200141046a280200200128020022036b22014101480d00200041046a22022802002003200110081a2002200228020020016a3602000b20000b2301017f2000280200220104402000200136020420004100360208200042003702000b0b3101017f20014180808080024904402000200110542202360200200020023602042000200220014103746a3602080f0b000bc70202047f017e230041f0006b22002400100710012201100b22021002200041206a200041086a20022001104022014100101e02400240200041206a10412204500d0041bc0a10422004510440200041206a104310440c020b41c10a10422004510440200042003703682000410036026020004200370358200041206a20014101101e200041206a200041d8006a1045200041206a20014102101e2000200041206a1046370368200041206a1043200041206a200041c8006a200041d8006a103c22022000290368103b2002102c1044200041d8006a102c0c020b41c60a10422004520d00200041206a1043200041c8006a200041386a103c2102200041d8006a1047220120021048104920012002104a200128020c200141106a28020047044010000b2001280200200128020410032001104b2002102c10440c010b10000b200041f0006a24000b3401017f230041106b220324002003200236020c200320013602082003200329030837030020002003411c1015200341106a24000ba30202057f017e200010200240024020001021450d002000280204450d0020002802002d000041c001490d010b10000b200010182204200028020422014b04401000200041046a28020021010b2000280200210502400240027f027f024020010440410020052c00002200417f4a0d031a200041ff0171220241bf014b0d014100200041ff017141b801490d021a200241c97e6a0c020b410120050d021a410021000c030b4100200041ff017141f801490d001a200241897e6a0b41016a0b2102410021002001200449200220046a20014b720d0020012002490d01200220056a2103200120026b20042004417f461b22004109490d0110000c010b0b0340200004402000417f6a210020033100002006420886842106200341016a21030c010b0b20060b3901027e42a5c688a1c89ca7f94b210103402000300000220250450440200041016a2100200142b383808080207e20028521010c010b0b20010be50301097f230041406a22042400200042003702182000428debc585c3a7f9dbf7003703102000410036020820004200370200200041206a4100360200200441286a104722012000290310104e200128020c200141106a28020047044010000b200041186a210502400240200128020022072001280204220810042202044020044100360220200442003703182002417f4c0d01200441206a2002100c20021009220620026a22093602002004200936021c2004200636021820072008200620021005417f47044020042004280218220341016a200428021c2003417f736a104020051045200221030b200441186a102c0b2001104b20030d01200041046a2802002207200028020022026b22034103752201200041206a280200200041186a28020022066b4103754b04402005103d2005200520011035103e20034101480d022000411c6a22012802002002200310081a2001200128020020036a3602000c020b20022000411c6a28020020066b22036a20072001200341037522084b1b220320026b22050440200620022005100d0b200120084b0440200720036b22024101480d022000411c6a22012802002003200210081a2001200128020020026a3602000c020b2000411c6a200620054103754103746a3602000c010b000b200441406b240020000bb103010b7f230041d0006b22012400200141186a10472105200141c8006a4100360200200141406b4200370300200141386a420037030020014200370330200141306a2000290310104f20012802302102200141306a410472105120052002104920052000290310104e200528020c200541106a28020047044010000b200528020421072005280200200141306a10472102200041186a2206104821094101100c220341fe013a0000200120033602082001200341016a22043602102001200436020c200228020c200241106a2802004704401000200128020c2104200128020821030b200420036b22042002280204220a6a220b20022802084b047f2002200b104c200241046a28020005200a0b20022802006a2003200410081a200241046a2203200328020020046a3602002002200128020c20096a20012802086b104920022006104a02402002410c6a2204280200200241106a220628020047044010002002280200210320042802002006280200460d0110000c010b200228020021030b20072003200241046a2802001006200141086a102c2002104b2005104b200041186a102c2000102c200141d0006a24000beb0202067f017e230041e0006b22022400024002402000280204450d0020002802002d000041c001490d002000102221032001280208200128020022046b41037520034904402001200241106a2003200128020420046b410375200141086a105522031056200310570b200241386a20004101101c2103200141086a2104200241286a20004100101c21060340200341046a2200280200200641046a280200460440200341086a280200200641086a280200460d030b20022000290200220837034820022008370308200241106a200241086a411c1015104621080240200141046a220528020022002004280200490440200020083703002005200041086a3602000c010b200241c8006a2001200020012802006b41037541016a1035200528020020012802006b410375200410552100200241d0006a2205280200220720083703002005200741086a360200200120001056200010570b200310190c000b000b10000b200241e0006a24000b1601017e200010412201420188420020014201837d850b2900200041003602082000420037020020004100104c200041146a41003602002000420037020c20000ba90102027f017e230041206b22012400200141186a4100360200200141106a4200370300200141086a420037030020014200370300027f20002802002000280204470440200141001058200041046a280200210220002802002100034020002002464504402001200029030022034201862003423f8785104f200041086a21000c010b0b20014101105820012802000c010b2001410136020041010b20014104721051200141206a24000b13002000280208200149044020002001104c0b0b4f02017f017e2000200128020420012802006b41037510292001280204210220012802002101034020012002464504402000200129030022034201862003423f8785104e200141086a21010c010b0b0b1c01017f200028020c22010440200041106a20013602000b2000104d0b3601017f200028020820014904402001100b20002802002000280204100821022000104d200041086a2001360200200020023602000b0b080020002802001a0b08002000200110320b7902017f017e4101210220014280015a044041002102034020012003845045044020034238862001420888842101200241016a2102200342088821030c010b0b200241384f047f2002102620026a0520020b41016a21020b200041186a2802000440200041046a105021000b2000200028020020026a3602000b2e002000280204200028021420002802106a417f6a220041087641fcffff07716a280200200041ff07714102746a0ba90201067f230041106b22042400200028020422012000280210220341087641fcffff07716a2102027f200120002802084704402002280200200341ff07714102746a0c010b41000b2101200441086a20001052200428020c2103034020012003470440200141046a220120022802006b418020470d0120022802042101200241046a21020c010b0b20004100360214200041046a22022802002101200041086a210503402005280200220620016b410275220341034f044020012802001a2002200228020041046a22013602000c010b0b0240200041106a027f2003410147044020034102470d024180080c010b4180040b3602000b03402001200647044020012802001a200141046a21010c010b0b2000200041046a280200105320002802001a200441106a24000b5101037f20012802042203200128021020012802146a220441087641fcffff07716a21022000027f200320012802084704402002280200200441ff07714102746a0c010b41000b360204200020023602000b2c01017f20002802082102200041086a21000340200120024645044020002002417c6a22023602000c010b0b0b170020004180808080024904402000410374100c0f0b000b4f01017f2000410036020c200041106a2003360200200104402001105421040b200020043602002000200420024103746a22023602082000410c6a200420014103746a3602002000200236020420000b940101037f200120012802042000280204200028020022046b22026b2203360204200241004a044020032004200210081a200141046a28020021030b2000280200210220002003360200200141046a22032002360200200041046a220228020021042002200128020836020020012004360208200028020821022000200128020c3602082001200236020c200120032802003602000b3801037f2000280208210120002802042102200041086a21030340200120024704402003200141786a22013602000c010b0b20002802001a0be90e020b7f027e230041306b22062400200041046a2109024020014101460440200910502802002101200041186a22022002280200417f6a360200200910594180104f04402000410c6a2202280200417c6a2802001a20092002280200417c6a10530b200141384f047f2001102620016a0520010b41016a2101200041186a280200450d012009105021000c010b0240200910590d00200041146a220128020022024180084f0440200120024180786a360200200041086a2201280200220228020021042001200241046a360200200620043602182009200641186a105a0c010b024002400240024002400240024002402000410c6a2802002202200041086a2802006b4102752204200041106a2203280200220520002802046b2201410275490440418020100c210820022005470d01200041086a22042802002202200041046a2802002205460d02200221010c080b20062001410175410120011b20042003105b2104418020100c210820042802082201200428020c2205470d0320042802042202200428020022034d0d02200120026b220141027521052002200220036b41027541016a417e6d41027422076a2103200441046a2001047f200320022001100d200441046a2802000520020b20076a360200200441086a200320054102746a22013602000c030b2000410c6a22042802002201200041106a2802002205470d04200041086a22072802002202200041046a28020022034d0d03200120026b220141027521052002200220036b41027541016a417e6d41027422076a2103200041086a2001047f200320022001100d200041086a2802000520020b20076a3602002000410c6a200320054102746a22013602000c040b2000410c6a22072802002203200041106a220a28020022014f0d042003200120036b41027541016a41026d41027422076a2101200320026b22050440200120056b220120022005100d2000410c6a28020021030b200041086a20013602002000410c6a200320076a3602000c050b200641186a200520036b2201410175410120011b22012001410276200441106a280200105b2102200441086a2802002105200441046a2802002101034020012005470440200241086a220328020020012802003602002003200328020041046a360200200141046a21010c010b0b2004290200210d200420022902003702002002200d370200200441086a2201290200210d2001200241086a22032902003702002003200d3702002002105c200128020021010b20012008360200200441086a2208200828020041046a3602002000410c6a2802002105200441106a210b0340200041086a28020020054704402005417c6a21050240200441046a220728020022022004280200220a470440200221010c010b200828020022032004410c6a28020022014904402003200120036b41027541016a41026d410274220c6a2101200320026b220a04402001200a6b22012002200a100d200828020021030b2007200136020020082003200c6a3602000c010b200641186a2001200a6b2201410175410120011b2201200141036a410276200b280200105b2008280200210a2007280200210103402001200a470440200641206a220228020020012802003602002002200228020041046a360200200141046a21010c010b0b2004290200210d200420062903183702002008290200210e2008200641206a22012903003702002001200e3703002006200d370318105c200728020021010b2001417c6a200528020036020020072007280200417c6a3602000c010b0b200041046a220128020021022001200428020036020020042002360200200441046a2201280200210220012005360200200041086a20023602002000410c6a2201290200210d2001200441086a22012902003702002001200d3702002004105c0c040b200641186a200520036b2201410175410120011b22012001410276200041106a105b21022000410c6a280200210520072802002101034020012005470440200241086a220328020020012802003602002003200328020041046a360200200141046a21010c010b0b200041046a2201290200210d200120022902003702002002200d3702002000410c6a2201290200210d2001200241086a22032902003702002003200d3702002002105c200128020021010b200120083602002004200428020041046a3602000c020b200641186a200120056b2201410175410120011b2201200141036a410276200a105b210220072802002105200041086a2802002101034020012005470440200241086a220328020020012802003602002003200328020041046a360200200141046a21010c010b0b200041046a2201290200210d200120022902003702002002200d3702002000410c6a2201290200210d2001200241086a22012902003702002001200d3702002002105c200041086a28020021010b2001417c6a2008360200200420042802002201417c6a22023602002002280200210220042001360200200620023602182009200641186a105a0b200641186a20091052200628021c4100360200200041186a2100410121010b2000200028020020016a360200200641306a24000b2801017f200028020820002802046b2201410874417f6a410020011b200028021420002802106a6b0be40202067f017e230041206b22062400024020002802082202200028020c2205470d0020002802042203200028020022044b0440200220036b220241027521052003200320046b41027541016a417e6d41027422076a2104200041046a2002047f200420032002100d200041046a2802000520030b20076a360200200041086a200420054102746a22023602000c010b200641086a200520046b2202410175410120021b220220024102762000410c6a105b2103200041086a2802002105200041046a280200210203402002200546450440200341086a220428020020022802003602002004200428020041046a360200200241046a21020c010b0b200029020021082000200329020037020020032008370200200041086a220229020021082002200341086a2204290200370200200420083702002003105c200228020021020b20022001280200360200200041086a2200200028020041046a360200200641206a24000b6201017f2000410036020c200041106a200336020002402001044020014180808080044f0d012001410274100c21040b200020043602002000200420024102746a22023602082000410c6a200420014102746a3602002000200236020420000f0b000b3801037f2000280208210120002802042102200041086a210303402001200247044020032001417c6a22013602000c010b0b20002802001a0b3801017f41b00a420037020041b80a410036020041742100034020000440200041bc0a6a4100360200200041046a21000c010b0b410210130b0b1a010041bc0a0b13696e697400736f7274006765745f6172726179";

    public static String BINARY = BINARY_0;

    public static final String FUNC_GET_ARRAY = "get_array";

    public static final String FUNC_SORT = "sort";

    protected InsertSort(String contractAddress, Web3j web3j, Credentials credentials, GasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    protected InsertSort(String contractAddress, Web3j web3j, TransactionManager transactionManager, GasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteCall<Int64[]> get_array() {
        final WasmFunction function = new WasmFunction(FUNC_GET_ARRAY, Arrays.asList(), Int64[].class);
        return executeRemoteCall(function, Int64[].class);
    }

    public static RemoteCall<InsertSort> deploy(Web3j web3j, Credentials credentials, GasProvider contractGasProvider) {
        String encodedConstructor = WasmFunctionEncoder.encodeConstructor(BINARY, Arrays.asList());
        return deployRemoteCall(InsertSort.class, web3j, credentials, contractGasProvider, encodedConstructor);
    }

    public static RemoteCall<InsertSort> deploy(Web3j web3j, TransactionManager transactionManager, GasProvider contractGasProvider) {
        String encodedConstructor = WasmFunctionEncoder.encodeConstructor(BINARY, Arrays.asList());
        return deployRemoteCall(InsertSort.class, web3j, transactionManager, contractGasProvider, encodedConstructor);
    }

    public static RemoteCall<InsertSort> deploy(Web3j web3j, Credentials credentials, GasProvider contractGasProvider, BigInteger initialVonValue) {
        String encodedConstructor = WasmFunctionEncoder.encodeConstructor(BINARY, Arrays.asList());
        return deployRemoteCall(InsertSort.class, web3j, credentials, contractGasProvider, encodedConstructor, initialVonValue);
    }

    public static RemoteCall<InsertSort> deploy(Web3j web3j, TransactionManager transactionManager, GasProvider contractGasProvider, BigInteger initialVonValue) {
        String encodedConstructor = WasmFunctionEncoder.encodeConstructor(BINARY, Arrays.asList());
        return deployRemoteCall(InsertSort.class, web3j, transactionManager, contractGasProvider, encodedConstructor, initialVonValue);
    }

    public RemoteCall<TransactionReceipt> sort(Int64[] arr, Int64 length) {
        final WasmFunction function = new WasmFunction(FUNC_SORT, Arrays.asList(arr,length), Void.class);
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> sort(Int64[] arr, Int64 length, BigInteger vonValue) {
        final WasmFunction function = new WasmFunction(FUNC_SORT, Arrays.asList(arr,length), Void.class);
        return executeRemoteCallTransaction(function, vonValue);
    }

    public static InsertSort load(String contractAddress, Web3j web3j, Credentials credentials, GasProvider contractGasProvider) {
        return new InsertSort(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static InsertSort load(String contractAddress, Web3j web3j, TransactionManager transactionManager, GasProvider contractGasProvider) {
        return new InsertSort(contractAddress, web3j, transactionManager, contractGasProvider);
    }
}

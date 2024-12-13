package io.foxcapades.mc.bukkit.thimble.write

import com.google.gson.stream.JsonWriter
import io.foxcapades.mc.bukkit.thimble.types.DefaultTypeDefinitionRegistry
import org.bukkit.NamespacedKey
import org.bukkit.craftbukkit.v1_21_R1.persistence.CraftPersistentDataContainer
import org.bukkit.craftbukkit.v1_21_R1.persistence.CraftPersistentDataTypeRegistry
import org.bukkit.persistence.PersistentDataType
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.io.StringWriter
import java.math.BigDecimal
import java.math.BigInteger

@DisplayName("ValueWriterImpl")
class ValueWriterImplTest {
  private lateinit var buffer: StringWriter
  private lateinit var writer: ValueWriterImpl

  @BeforeEach
  fun setup() {
    buffer = StringWriter(4096)
    writer = ValueWriterImpl(DefaultTypeDefinitionRegistry, JsonWriter(buffer))
  }

  // region Binary

  @Test
  fun writeBinary() {
    writer.writeBinary("hello".toByteArray())
    assertEquals(""""aGVsbG8="""", buffer.toString())
  }

  @Test
  fun writeBinaryOrNull_NotNull() {
    writer.writeBinaryOrNull("hello".toByteArray())
    assertEquals(""""aGVsbG8="""", buffer.toString())
  }

  @Test
  fun writeBinaryOrNull_Null() {
    writer.writeBinaryOrNull(null)
    assertEquals("null", buffer.toString())
  }

  @Test
  fun writeBinaryList() {
    writer.writeBinaryList(listOf("hello".toByteArray(), "goodbye".toByteArray()))
    assertEquals("""["<bin>",1,2,"aGVsbG8=","Z29vZGJ5ZQ=="]""", buffer.toString())
  }

  @Test
  fun writeBinaryListOrNull_NotNull() {
    writer.writeBinaryListOrNull(listOf("hello".toByteArray(), "goodbye".toByteArray()))
    assertEquals("""["<bin>",1,2,"aGVsbG8=","Z29vZGJ5ZQ=="]""", buffer.toString())
  }

  @Test
  fun writeBinaryListOrNull_Null() {
    writer.writeBinaryListOrNull(null)
    assertEquals("null", buffer.toString())
  }

  // endregion Binary

  // region Boolean

  @Test
  fun writeBoolean() {
    writer.writeBoolean(true)
    assertEquals("true", buffer.toString())
  }

  @Test
  fun writeBooleanOrNull_NotNull() {
    writer.writeBooleanOrNull(false)
    assertEquals("false", buffer.toString())
  }

  @Test
  fun writeBooleanOrNull_Null() {
    writer.writeBooleanOrNull(null)
    assertEquals("null", buffer.toString())
  }

  @Test
  fun writeBooleanArray() {
    writer.writeBooleanArray(booleanArrayOf(true, false))
    assertEquals("""["[B]",1,2,true,false]""", buffer.toString())
  }

  @Test
  fun writeBooleanArrayOrNull_NotNull() {
    writer.writeBooleanArrayOrNull(booleanArrayOf(true, false))
    assertEquals("""["[B]",1,2,true,false]""", buffer.toString())
  }

  @Test
  fun writeBooleanArrayOrNull_Null() {
    writer.writeBooleanArrayOrNull(null)
    assertEquals("null", buffer.toString())
  }

  @Test
  fun writeBooleanList() {
    writer.writeBooleanList(listOf(true, false))
    assertEquals("""["<B>",1,2,true,false]""", buffer.toString())
  }

  @Test
  fun writeBooleanListOrNull_NotNull() {
    writer.writeBooleanListOrNull(listOf(true, false))
    assertEquals("""["<B>",1,2,true,false]""", buffer.toString())
  }

  @Test
  fun writeBooleanListOrNull_Null() {
    writer.writeBooleanListOrNull(null)
    assertEquals("null", buffer.toString())
  }

  // endregion Boolean

  // region Byte

  @Test
  fun writeByte() {
    writer.writeByte(1)
    assertEquals("1", buffer.toString())
  }

  @Test
  fun writeByteOrNull_NotNull() {
    writer.writeByteOrNull(2)
    assertEquals("2", buffer.toString())
  }

  @Test
  fun writeByteOrNull_Null() {
    writer.writeByteOrNull(null)
    assertEquals("null", buffer.toString())
  }

  @Test
  fun writeByteList() {
    writer.writeByteList(listOf(1, 2, 3))
    assertEquals("""["<b>",1,3,1,2,3]""", buffer.toString())
  }

  @Test
  fun writeByteListOrNull_NotNull() {
    writer.writeByteListOrNull(listOf(1, 2, 3))
    assertEquals("""["<b>",1,3,1,2,3]""", buffer.toString())
  }

  @Test
  fun writeByteListOrNull_Null() {
    writer.writeByteListOrNull(null)
    assertEquals("null", buffer.toString())
  }

  // endregion Byte

  // region Short

  @Test
  fun writeShort() {
    writer.writeShort(1)
    assertEquals("1", buffer.toString())
  }

  @Test
  fun writeShortOrNull_NotNull() {
    writer.writeShortOrNull(2)
    assertEquals("2", buffer.toString())
  }

  @Test
  fun writeShortOrNull_Null() {
    writer.writeShortOrNull(null)
    assertEquals("null", buffer.toString())
  }

  @Test
  fun writeShortArray() {
    writer.writeShortArray(shortArrayOf(1, 2, 3, 4))
    assertEquals("""["[s]",1,4,1,2,3,4]""", buffer.toString())
  }

  @Test
  fun writeShortArrayOrNull_NotNull() {
    writer.writeShortArrayOrNull(shortArrayOf())
    assertEquals("""["[s]",1,0]""", buffer.toString())
  }

  @Test
  fun writeShortArrayOrNull_Null() {
    writer.writeShortArrayOrNull(null)
    assertEquals("null", buffer.toString())
  }

  @Test
  fun writeShortList() {
    writer.writeShortList(listOf(1, 2))
    assertEquals("""["<s>",1,2,1,2]""", buffer.toString())
  }

  @Test
  fun writeShortListOrNull_NotNull() {
    writer.writeShortListOrNull(listOf(1))
    assertEquals("""["<s>",1,1,1]""", buffer.toString())
  }

  @Test
  fun writeShortListOrNull_Null() {
    writer.writeShortListOrNull(null)
    assertEquals("null", buffer.toString())
  }

  // endregion Short

  // region Int

  @Test
  fun writeInt() {
    writer.writeInt(5)
    assertEquals("5", buffer.toString())
  }

  @Test
  fun writeIntOrNull_NotNull() {
    writer.writeIntOrNull(3)
    assertEquals("3", buffer.toString())
  }

  @Test
  fun writeIntOrNull_Null() {
    writer.writeIntOrNull(null)
    assertEquals("null", buffer.toString())
  }

  @Test
  fun writeIntArray() {
    writer.writeIntArray(intArrayOf(4, 5, 6))
    assertEquals("""["[i]",1,3,4,5,6]""", buffer.toString())
  }

  @Test
  fun writeIntArrayOrNull_NotNull() {
    writer.writeIntArrayOrNull(intArrayOf(6))
    assertEquals("""["[i]",1,1,6]""", buffer.toString())
  }

  @Test
  fun writeIntArrayOrNull_Null() {
    writer.writeIntArrayOrNull(null)
    assertEquals("null", buffer.toString())
  }

  @Test
  fun writeIntList() {
    writer.writeIntList(listOf(666))
    assertEquals("""["<i>",1,1,666]""", buffer.toString())
  }

  @Test
  fun writeIntListOrNull() {
    writer.writeIntListOrNull(listOf(420))
    assertEquals("""["<i>",1,1,420]""", buffer.toString())
  }

  @Test
  fun writeIntArrayList() {
    writer.writeIntArrayList(listOf(intArrayOf(3, 4), intArrayOf(5)))
    assertEquals("""["<[i]>",1,2,["[i]",1,2,3,4],["[i]",1,1,5]]""", buffer.toString())
  }

  @Test
  fun writeIntArrayListOrNull_NotNull() {
    writer.writeIntArrayListOrNull(listOf(intArrayOf(3, 4), intArrayOf(5)))
    assertEquals("""["<[i]>",1,2,["[i]",1,2,3,4],["[i]",1,1,5]]""", buffer.toString())
  }

  @Test
  fun writeIntArrayListOrNull_Null() {
    writer.writeIntArrayListOrNull(null)
    assertEquals("""null""", buffer.toString())
  }

  // endregion Int

  // region Long

  @Test
  fun writeLong() {
    writer.writeLong(69)
    assertEquals("69", buffer.toString())
  }

  @Test
  fun writeLongOrNull_NotNull() {
    writer.writeLongOrNull(13)
    assertEquals("13", buffer.toString())
  }

  @Test
  fun writeLongOrNull_Null() {
    writer.writeLongOrNull(null)
    assertEquals("null", buffer.toString())
  }

  @Test
  fun writeLongArray() {
    writer.writeLongArray(longArrayOf(7, 8))
    assertEquals("""["[l]",1,2,7,8]""", buffer.toString())
  }

  @Test
  fun writeLongArrayOrNull_NotNull() {
    writer.writeLongArrayOrNull(longArrayOf(7, 8))
    assertEquals("""["[l]",1,2,7,8]""", buffer.toString())
  }

  @Test
  fun writeLongArrayOrNull_Null() {
    writer.writeLongArrayOrNull(null)
    assertEquals("null", buffer.toString())
  }

  @Test
  fun writeLongList() {
    writer.writeLongList(listOf(9))
    assertEquals("""["<l>",1,1,9]""", buffer.toString())
  }

  @Test
  fun writeLongListOrNull_NotNull() {
    writer.writeLongListOrNull(listOf(9))
    assertEquals("""["<l>",1,1,9]""", buffer.toString())
  }

  @Test
  fun writeLongListOrNull_Null() {
    writer.writeLongListOrNull(null)
    assertEquals("null", buffer.toString())
  }

  @Test
  fun writeLongArrayList() {
    writer.writeLongArrayList(listOf(longArrayOf(3), longArrayOf(2, 1)))
    assertEquals("""["<[l]>",1,2,["[l]",1,1,3],["[l]",1,2,2,1]]""", buffer.toString())
  }

  @Test
  fun writeLongArrayListOrNull_NotNull() {
    writer.writeLongArrayListOrNull(listOf(longArrayOf(3), longArrayOf(2, 1)))
    assertEquals("""["<[l]>",1,2,["[l]",1,1,3],["[l]",1,2,2,1]]""", buffer.toString())
  }

  @Test
  fun writeLongArrayListOrNull_Null() {
    writer.writeLongArrayListOrNull(null)
    assertEquals("null", buffer.toString())
  }

  // endregion Long

  // region BigInteger

  @Test
  fun writeBigInteger() {
    writer.writeBigInteger(BigInteger(
      "161803398874989484820458683436563811772030917980576286213544862270526046281890" +
      "244970720720418939113748475408807538689175212663386222353693179318006076672635" +
      "443338908659593958290563832266131992829026788067520876689250171169620703222104" +
      "321626954862629631361443814975870122034080588795445474924618569536486444924104" +
      "432077134494704956584678850987433944221254487706647809158846074998871240076521" +
      "705751797883416625624940758906970400028121042762177111777805315317141011704666" +
      "599146697987317613560067087480710131795236894275219484353056783002287856997829" +
      "778347845878228911097625003026961561700250464338243776486102838312683303724292" +
      "675263116533924731671112115881863851331620384005222165791286675294654906811317" +
      "159934323597349498509040947621322298101726107059611645629909816290555208524790" +
      "352406020172799747175342777592778625619432082750513121815628551222480939471234" +
      "145170223735805772786160086883829523045926478780178899219902707769038953219681" +
      "986151437803149974110692608867429622675756052317277752035361393621076738937645" +
      "560606059216589466759551900400555908950229530942312482355212212415444006470340" +
      "565734797663972394949946584578873039623090375033993856210242369025138680414577" +
      "995698122445747178034173126453220416397232134044449487302315417676893752103068" +
      "737880344170093954409627955898678723209512426893557309704509595684401755519881" +
      "921802064052905518934947592600734852282101088194644544222318891319294689622002" +
      "301443770269923007803085261180754519288770502109684249362713592518760777884665" +
      "836150238913493333122310533923213624319263728910670503399282265263556209029798" +
      "642472759772565508615487543574826471814145127000602389016207773224499435308899" +
      "909501680328112194320481964387675863314798571911397815397807476150772211750826" +
      "945863932045652098969855567814106968372884058746103378105444390943683583581381" +
      "131168993855576975484149144534150912954070050194775486163075422641729394680367" +
      "319805861833918328599130396072014455950449779212076124785645916160837059498786"
    ))
    assertEquals(
      "161803398874989484820458683436563811772030917980576286213544862270526046281890" +
      "244970720720418939113748475408807538689175212663386222353693179318006076672635" +
      "443338908659593958290563832266131992829026788067520876689250171169620703222104" +
      "321626954862629631361443814975870122034080588795445474924618569536486444924104" +
      "432077134494704956584678850987433944221254487706647809158846074998871240076521" +
      "705751797883416625624940758906970400028121042762177111777805315317141011704666" +
      "599146697987317613560067087480710131795236894275219484353056783002287856997829" +
      "778347845878228911097625003026961561700250464338243776486102838312683303724292" +
      "675263116533924731671112115881863851331620384005222165791286675294654906811317" +
      "159934323597349498509040947621322298101726107059611645629909816290555208524790" +
      "352406020172799747175342777592778625619432082750513121815628551222480939471234" +
      "145170223735805772786160086883829523045926478780178899219902707769038953219681" +
      "986151437803149974110692608867429622675756052317277752035361393621076738937645" +
      "560606059216589466759551900400555908950229530942312482355212212415444006470340" +
      "565734797663972394949946584578873039623090375033993856210242369025138680414577" +
      "995698122445747178034173126453220416397232134044449487302315417676893752103068" +
      "737880344170093954409627955898678723209512426893557309704509595684401755519881" +
      "921802064052905518934947592600734852282101088194644544222318891319294689622002" +
      "301443770269923007803085261180754519288770502109684249362713592518760777884665" +
      "836150238913493333122310533923213624319263728910670503399282265263556209029798" +
      "642472759772565508615487543574826471814145127000602389016207773224499435308899" +
      "909501680328112194320481964387675863314798571911397815397807476150772211750826" +
      "945863932045652098969855567814106968372884058746103378105444390943683583581381" +
      "131168993855576975484149144534150912954070050194775486163075422641729394680367" +
      "319805861833918328599130396072014455950449779212076124785645916160837059498786",
      buffer.toString()
    )
  }

  @Test
  fun writeBigIntegerOrNull_NotNull() {
    writer.writeBigIntegerOrNull(BigInteger.ONE)
    assertEquals("1", buffer.toString())
  }

  @Test
  fun writeBigIntegerOrNull_Null() {
    writer.writeBigIntegerOrNull(null)
    assertEquals("null", buffer.toString())
  }

  // endregion BigInteger

  // region Float

  @Test
  fun writeFloat() {
    writer.writeFloat(1.3333f)
    assertEquals(1.3333, buffer.toString().toDouble(), 0.0001)
  }

  @Test
  fun writeFloatOrNull_NotNull() {
    writer.writeFloatOrNull(44.44f)
    assertEquals(44.44, buffer.toString().toDouble(), 0.01)
  }

  @Test
  fun writeFloatOrNull_Null() {
    writer.writeFloatOrNull(null)
    assertEquals("null", buffer.toString())
  }

  @Test
  fun writeFloatArray() {
    writer.writeFloatArray(floatArrayOf(1.1f, 2.2f))
    assertEquals("""["[f]",1,2,1.1,2.2]""", buffer.toString())
  }

  @Test
  fun writeFloatArrayOrNull_NotNull() {
    writer.writeFloatArrayOrNull(floatArrayOf(1.1f, 2.2f))
    assertEquals("""["[f]",1,2,1.1,2.2]""", buffer.toString())
  }

  @Test
  fun writeFloatArrayOrNull_Null() {
    writer.writeFloatArrayOrNull(null)
    assertEquals("null", buffer.toString())
  }

  @Test
  fun writeFloatList() {
    writer.writeFloatList(listOf(2.2f))
    assertEquals("""["<f>",1,1,2.2]""", buffer.toString())
  }

  @Test
  fun writeFloatListOrNull_NotNull() {
    writer.writeFloatListOrNull(listOf(2.2f))
    assertEquals("""["<f>",1,1,2.2]""", buffer.toString())
  }

  @Test
  fun writeFloatListOrNull_Null() {
    writer.writeFloatListOrNull(null)
    assertEquals("null", buffer.toString())
  }

  // endregion Float

  // region Double

  @Test
  fun writeDouble() {
    writer.writeDouble(1.3333)
    assertEquals(1.3333, buffer.toString().toDouble(), 0.0001)
  }

  @Test
  fun writeDoubleOrNull_NotNull() {
    writer.writeDoubleOrNull(44.44)
    assertEquals(44.44, buffer.toString().toDouble(), 0.01)
  }

  @Test
  fun writeDoubleOrNull_Null() {
    writer.writeDoubleOrNull(null)
    assertEquals("null", buffer.toString())
  }

  @Test
  fun writeDoubleArray() {
    writer.writeDoubleArray(doubleArrayOf(1.1, 2.2))
    assertEquals("""["[d]",1,2,1.1,2.2]""", buffer.toString())
  }

  @Test
  fun writeDoubleArrayOrNull_NotNull() {
    writer.writeDoubleArrayOrNull(doubleArrayOf(1.1, 2.2))
    assertEquals("""["[d]",1,2,1.1,2.2]""", buffer.toString())
  }

  @Test
  fun writeDoubleArrayOrNull_Null() {
    writer.writeDoubleArrayOrNull(null)
    assertEquals("null", buffer.toString())
  }

  @Test
  fun writeDoubleList() {
    writer.writeDoubleList(listOf(2.2))
    assertEquals("""["<d>",1,1,2.2]""", buffer.toString())
  }

  @Test
  fun writeDoubleListOrNull_NotNull() {
    writer.writeDoubleListOrNull(listOf(2.2))
    assertEquals("""["<d>",1,1,2.2]""", buffer.toString())
  }

  @Test
  fun writeDoubleListOrNull_Null() {
    writer.writeDoubleListOrNull(null)
    assertEquals("null", buffer.toString())
  }

  // endregion Double

  // region BigDecimal

  @Test
  fun writeBigDecimal() {
    writer.writeBigDecimal(BigDecimal(
      "1.1803398874989484820458683436563811772030917980576286213544862270526046281890" +
      "244970720720418939113748475408807538689175212663386222353693179318006076672635" +
      "443338908659593958290563832266131992829026788067520876689250171169620703222104" +
      "321626954862629631361443814975870122034080588795445474924618569536486444924104" +
      "432077134494704956584678850987433944221254487706647809158846074998871240076521" +
      "705751797883416625624940758906970400028121042762177111777805315317141011704666" +
      "599146697987317613560067087480710131795236894275219484353056783002287856997829" +
      "778347845878228911097625003026961561700250464338243776486102838312683303724292" +
      "675263116533924731671112115881863851331620384005222165791286675294654906811317" +
      "159934323597349498509040947621322298101726107059611645629909816290555208524790" +
      "352406020172799747175342777592778625619432082750513121815628551222480939471234" +
      "145170223735805772786160086883829523045926478780178899219902707769038953219681" +
      "986151437803149974110692608867429622675756052317277752035361393621076738937645" +
      "560606059216589466759551900400555908950229530942312482355212212415444006470340" +
      "565734797663972394949946584578873039623090375033993856210242369025138680414577" +
      "995698122445747178034173126453220416397232134044449487302315417676893752103068" +
      "737880344170093954409627955898678723209512426893557309704509595684401755519881" +
      "921802064052905518934947592600734852282101088194644544222318891319294689622002" +
      "301443770269923007803085261180754519288770502109684249362713592518760777884665" +
      "836150238913493333122310533923213624319263728910670503399282265263556209029798" +
      "642472759772565508615487543574826471814145127000602389016207773224499435308899" +
      "909501680328112194320481964387675863314798571911397815397807476150772211750826" +
      "945863932045652098969855567814106968372884058746103378105444390943683583581381" +
      "131168993855576975484149144534150912954070050194775486163075422641729394680367" +
      "319805861833918328599130396072014455950449779212076124785645916160837059498786"
    ))
    assertEquals(
      "1.1803398874989484820458683436563811772030917980576286213544862270526046281890" +
      "244970720720418939113748475408807538689175212663386222353693179318006076672635" +
      "443338908659593958290563832266131992829026788067520876689250171169620703222104" +
      "321626954862629631361443814975870122034080588795445474924618569536486444924104" +
      "432077134494704956584678850987433944221254487706647809158846074998871240076521" +
      "705751797883416625624940758906970400028121042762177111777805315317141011704666" +
      "599146697987317613560067087480710131795236894275219484353056783002287856997829" +
      "778347845878228911097625003026961561700250464338243776486102838312683303724292" +
      "675263116533924731671112115881863851331620384005222165791286675294654906811317" +
      "159934323597349498509040947621322298101726107059611645629909816290555208524790" +
      "352406020172799747175342777592778625619432082750513121815628551222480939471234" +
      "145170223735805772786160086883829523045926478780178899219902707769038953219681" +
      "986151437803149974110692608867429622675756052317277752035361393621076738937645" +
      "560606059216589466759551900400555908950229530942312482355212212415444006470340" +
      "565734797663972394949946584578873039623090375033993856210242369025138680414577" +
      "995698122445747178034173126453220416397232134044449487302315417676893752103068" +
      "737880344170093954409627955898678723209512426893557309704509595684401755519881" +
      "921802064052905518934947592600734852282101088194644544222318891319294689622002" +
      "301443770269923007803085261180754519288770502109684249362713592518760777884665" +
      "836150238913493333122310533923213624319263728910670503399282265263556209029798" +
      "642472759772565508615487543574826471814145127000602389016207773224499435308899" +
      "909501680328112194320481964387675863314798571911397815397807476150772211750826" +
      "945863932045652098969855567814106968372884058746103378105444390943683583581381" +
      "131168993855576975484149144534150912954070050194775486163075422641729394680367" +
      "319805861833918328599130396072014455950449779212076124785645916160837059498786",
      buffer.toString()
    )
  }

  @Test
  fun writeBigDecimalOrNull_NotNull() {
    writer.writeBigDecimalOrNull(BigDecimal.ONE)
    assertEquals("1", buffer.toString())
  }

  @Test
  fun writeBigDecimalOrNull_Null() {
    writer.writeBigDecimalOrNull(null)
    assertEquals("null", buffer.toString())
  }

  // endregion BigDecimal

  // region Short

  @Test
  fun writeString() {
    writer.writeString("hello")
    assertEquals(""""hello"""", buffer.toString())
  }

  @Test
  fun writeStringOrNull_NotNull() {
    writer.writeStringOrNull("goodbye")
    assertEquals(""""goodbye"""", buffer.toString())
  }

  @Test
  fun writeStringOrNull_Null() {
    writer.writeStringOrNull(null)
    assertEquals("null", buffer.toString())
  }

  @Test
  fun writeStringArray() {
    writer.writeStringArray(arrayOf("he's", "so", "nipped", "up"))
    assertEquals("""["[S]",1,4,"he's","so","nipped","up"]""", buffer.toString())
  }

  @Test
  fun writeStringArrayOrNull_NotNull() {
    writer.writeStringArrayOrNull(arrayOf())
    assertEquals("""["[S]",1,0]""", buffer.toString())
  }

  @Test
  fun writeStringArrayOrNull_Null() {
    writer.writeStringArrayOrNull(null)
    assertEquals("null", buffer.toString())
  }

  @Test
  fun writeStringList() {
    writer.writeStringList(listOf("blaze", "it"))
    assertEquals("""["<S>",1,2,"blaze","it"]""", buffer.toString())
  }

  @Test
  fun writeStringListOrNull_NotNull() {
    writer.writeStringListOrNull(listOf("yep"))
    assertEquals("""["<S>",1,1,"yep"]""", buffer.toString())
  }

  @Test
  fun writeStringListOrNull_Null() {
    writer.writeStringListOrNull(null)
    assertEquals("null", buffer.toString())
  }

  // endregion String

  @Test
  fun writeNull() {
    writer.writeNull()
    assertEquals("null", buffer.toString())
  }

  // region Complex

  @Test
  fun writeComplex_Primitives() {
    val tests = arrayOf(
      true to """["B",1,true]""",

      (-1).toByte() to """["b",1,-1]""",
      27.toShort() to """["s",1,27]""",
      666 to """["i",1,666]""",
      420L to """["l",1,420]""",

      1.1f to """["f",1,1.1]""",
      2.2 to """["d",1,2.2]""",

      '@' to """["c",1,"@"]"""
    )

    for ((input, expect) in tests) {
      writer.writeComplex(input)
      assertEquals(expect, buffer.toString())
      setup()
    }
  }

  @Test
  fun writeComplex_PersistentDataContainer() {
    val input = CraftPersistentDataContainer(CraftPersistentDataTypeRegistry()).also {
      it.set(NamespacedKey.minecraft("butts"), PersistentDataType.STRING, "hello")
      it.set(NamespacedKey.minecraft("stink"), PersistentDataType.TAG_CONTAINER, CraftPersistentDataContainer(CraftPersistentDataTypeRegistry()).also {
        it.set(NamespacedKey.minecraft("sub"), PersistentDataType.BYTE_ARRAY, "goodbye".toByteArray())
      })
    }

    writer.writeComplex(input)
    assertEquals("""["pdc",1,"CgAPbWluZWNyYWZ0OnN0aW5rBwANbWluZWNyYWZ0OnN1YgAAAAdnb29kYnllAAgAD21pbmVjcmFmdDpidXR0cwAFaGVsbG8A"]""", buffer.toString())
  }

  // endregion Complex
}

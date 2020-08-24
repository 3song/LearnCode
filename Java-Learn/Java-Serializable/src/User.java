import java.io.Serializable;

public class User implements Serializable {
    //可以使用字节码技术对类的基本信息做操作，新增属性和方法、类、修改属性和方法、删除属性和方法 lombok 注解生成GetSet实体类方法
    private int anInt;
    private byte aByte;
    private float aFloat;
    private char aChar;
    private boolean aBoolean;
    private double aDouble;
    private long aLong;
    private short aShort;
    private String name;
    private transient Integer age;
    private Double aDoubles=null;
    private Boolean aBooleans=null;
    private Short aShorts=null;
    private Long aLongs=null;
    private Float aFloats=null;
    private Byte aBytes=null;
    private Character aCharacter=null;
    private Number number;

    public int getAnInt() {
        return anInt;
    }

    public void setAnInt(int anInt) {
        this.anInt = anInt;
    }

    public byte getaByte() {
        return aByte;
    }

    public void setaByte(byte aByte) {
        this.aByte = aByte;
    }

    public float getaFloat() {
        return aFloat;
    }

    public void setaFloat(float aFloat) {
        this.aFloat = aFloat;
    }

    public char getaChar() {
        return aChar;
    }

    public void setaChar(char aChar) {
        this.aChar = aChar;
    }

    public boolean isaBoolean() {
        return aBoolean;
    }

    public void setaBoolean(boolean aBoolean) {
        this.aBoolean = aBoolean;
    }

    public double getaDouble() {
        return aDouble;
    }

    public void setaDouble(double aDouble) {
        this.aDouble = aDouble;
    }

    public long getaLong() {
        return aLong;
    }

    public void setaLong(long aLong) {
        this.aLong = aLong;
    }

    public short getaShort() {
        return aShort;
    }

    public void setaShort(short aShort) {
        this.aShort = aShort;
    }

    public Double getaDoubles() {
        return aDoubles;
    }

    public void setaDoubles(Double aDoubles) {
        this.aDoubles = aDoubles;
    }

    public Boolean getaBooleans() {
        return aBooleans;
    }

    public void setaBooleans(Boolean aBooleans) {
        this.aBooleans = aBooleans;
    }

    public Short getaShorts() {
        return aShorts;
    }

    public void setaShorts(Short aShorts) {
        this.aShorts = aShorts;
    }

    public Long getaLongs() {
        return aLongs;
    }

    public void setaLongs(Long aLongs) {
        this.aLongs = aLongs;
    }

    public Float getaFloats() {
        return aFloats;
    }

    public void setaFloats(Float aFloats) {
        this.aFloats = aFloats;
    }

    public Byte getaBytes() {
        return aBytes;
    }

    public void setaBytes(Byte aBytes) {
        this.aBytes = aBytes;
    }

    public Character getaCharacter() {
        return aCharacter;
    }

    public void setaCharacter(Character aCharacter) {
        this.aCharacter = aCharacter;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Number getNumber() {
        return number;
    }

    public void setNumber(Number number) {
        this.number = number;
    }
}

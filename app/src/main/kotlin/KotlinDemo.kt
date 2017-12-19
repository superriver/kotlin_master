
import kotlin.properties.Delegates
import kotlin.reflect.KProperty

/**
 * Created by Administrator on 2017/5/18.
 */

//有返回值
fun add(a: Int, b: Int): Int {
    return a + b
}

//无返回值（Unit也可以省略不写）
fun minus(a: Int, b: Int): Unit {
    println(a - b)
}

//使用条件表达式
fun max(a: Int, b: Int): Int {
    if (a > b)
        return a
    else
        return b
}

fun loop(type: Any): String =
        when (type) {
            1 -> "it is 1"
            "one" -> "it is one"
            is Long -> "Long"
            !is String -> "Not is string"
            else -> "String"
        }


fun hasPrefix(x: String) = when (x) {
    is String -> x.startsWith("prefix", true)
    else -> false
}

//自动转换类型
fun getStringFun(obj: Any): Int? {
    if (obj is String) {
        return obj.length
    }
    return null
}

//
fun collection() {
    val list = listOf("a", "b", "c")
    list.filter { it.startsWith("a") }
            .sortedBy { it }
            .map { it.toUpperCase() }
            .forEach { print(it) }
}

fun test1() {
    val i = "a"//类型推导，可以不用声明类型
    val str: String
    str = "String"
    var a = 1//可变变量
    a += 1
    println(i)
    println(str)
    println(a)
    println("a+b=" + add(1, 2))
    minus(3, 2)
    max(2, 3)
    //字符串数组
    val arr = arrayOf("a", "b", "c")
    for (ar in arr) {
        print(ar)

    }
    println()
    //字符数组
    val arr1: CharArray = charArrayOf('a', 'b')
    loop(1)
    print(hasPrefix("prefix"))
    println()
    collection()
}

/**
 * 另一种的学、写法
 * fun max(a:Int,b:Int)=if(a>b) a else b
 */

//使用nullable值检测空（null）值

fun test2() {
    val a: Int = 100
    val boxedA: Int? = a
    val boxedB: Int? = a
    val c: Long? = a.toLong()
    print(boxedA === boxedB)
    print(boxedA == boxedB)
}

fun test3() {
    //转义字符
    val text2="\nhello,world"
    //原生字符
    val text1 = """
    |srwsr
|aaf
|wtwl
""".trimMargin()
    print(text1)
    print(text2)
}
fun test4(){
    loop@ for(i in 1..10){
        for(j in 1..10){
            print(j)
            if(j==9) break@loop
        }
    }
    println()
    val ints= intArrayOf(1,2,3,4,5,6)
    ints.forEach {
        if(it==4)
           return
        print(it)
    }
}

class Address{
    var name:String?=null
    var street:String="tina"
    var city:String="xiameng"
    var state:String="hao"
    var zip:String="liu"

}
fun copyAddress(address:Address):Address{
    val result=Address()
    result.name=address.name
    result.state=address.state
    result.city=address.city
    result.street=address.street
    result.zip=address.zip

    return result
}

/**
 * 接口
 */
interface MyInterfaceA{
    val prop:Int
    fun bar()
    fun foo(){

    }
}
interface A{
    val prop:Int
    val propertyWithImplementation: String
        get() = "foo"
    fun bar()
    fun foo(){
        print("A")
    }
}
interface B{
    fun bar(){
        print("B")
    }
    fun foo(){
        print("B")
    }
}
class Child :MyInterfaceA{
    override val prop:Int=20
    override fun bar() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    class ChildA(override val prop: Int) :A,B{
        override fun bar() {
            super<B>.bar()
        }

        override fun foo() {
            super<A>.foo()
            super<B>.foo()
        }

    }
}
/**
 * 可扩展函数
 */

fun MutableList<Int>.swap(index1:Int,index2:Int){
    val tmp=this[index1]
    this[index1]=this[index2]
    this[index2]=tmp
}
fun demo(){
    val list= mutableListOf(1,2,3)
    list.swap(0,2)
    for (item in list){
        print(item)
    }
}

open class C
class D:C()
fun D.foo()="d"
fun C.foo()="c"
fun printFoo(c:D){
    print(c.foo())
}
class E{
    fun foo(){
        print("member")
    }

}
fun E.foo(i:Int){
    print("extension")
}

/**
 * Data Class
 */
data class User(val name:String="",val age:Int=0)

/**
 * 密封类
 */

sealed class Expr{
    class Const(val number:Double):Expr()
    class Sum(val e1:Expr,val e2:Expr):Expr()
    object NotANumber:Expr()
}
fun eval(e:Expr):Double=when(e){
    is Expr.Const->e.number
    is Expr.Sum->eval(e.e1)+eval(e.e2)
    Expr.NotANumber-> Double.NaN
}

//泛型
abstract class Source<out T>{
    abstract fun nextT():T
}
fun demo(strs:Source<String>){
    val objects:Source<Any> = strs
}
abstract class Compareable<in T>{
    abstract fun compareTo(other:T):Int
}
fun demo(x:Compareable<Number>){
    x.compareTo(1.0)
    val y:Compareable<Double> = x
}

//泛型函数
fun <T> singtonList(item:T):List<T>{
        return listOf()
}
fun <T> T.basicToString():String{
        return ""
}

//泛型约束
fun <T:Compareable<T>> sort(list:List<T>){

}
//fun <T> cloneWhenGreater(list:List<T>,threshold:T): List<T>
//            where T:Comparable,
//                  T:Cloneable{
//    return list.filter { it > threshold }.map { it.clone() }
//}
class Array<T> (val size:Int){
//    fun get(index:Int):T{
//
//    }
    fun set(index:Int,vakue:T){

    }
}
fun copy(from:kotlin.Array<out Any>,to:kotlin.Array<Any>){
    assert(from.size==to.size)
    for(i in from.indices){
        to[i]=from[i]
    }
}

//嵌套类和枚举类
enum class RGB{RED,BLACK,WHITE}
inline fun<reified T:Enum<T>> printAllValues(){
    println(enumValues<T>().joinToString { it.name })
}

//匿名内部类
interface Factory<T>{
    fun create():T
}
class MyClass{
    companion object :Factory<MyClass>{
        override fun create(): MyClass=MyClass()
    }
}
class C1{
    private fun foo()=object {
        val x:String="x"
    }
    fun publicFoo()=object {
        val x:String="x"
    }
    fun bar(){
        val x1=foo().x
        val x2=publicFoo()
    }

}

//委托类
interface Base{
   fun print()
}
class BaseImpl(val x:Any):Base{
    override fun print() {
            println(x)
    }
}

class Derived(b:Base):Base by b//委托类

//委托属性
class Example{
    var p:String by Delegate()
}
class Delegate{
    operator fun getValue(thisRef:Any?,property:KProperty<*>):String{
        return "$thisRef, thank you for delegating '${property.name}' to me!"
    }
    operator fun setValue(thisRef:Any?,property: KProperty<*>,value:String){
       println("$value has been assigned to '${property.name} in $thisRef.'")
    }
}
//标准委托
//延迟加载
val LazyValue :String by lazy{
    println("Hello")
    "world"
}

class Users{
    var name:String by Delegates.observable("<no name>"){
        prop,old,new->{ println("$old->$new") }

    }
}

class  User_Val(val map:Map<String,Any?>){
    val name:String by map
    val age:Int by map
}
class  User_Val1(val map:MutableMap<String,Any?>){
    var name:String by map
    var age:Int by map
}

//函数
fun demo(b:Array<Byte>,i:Int=0,len:Int=b.size){

}
//可变参数
fun <T> asList(vararg t:T):List<T>{
    var ts=ArrayList<T>()
    for(t in ts){
        ts.add(t)
    }
    return ts
}
//尾递归函数
tailrec fun findFixPoint(x:Double):Double=
    if(x==Math.cos(x)) x else findFixPoint(x)

class HTML{
    fun body(){}
}
fun html(init:HTML.()->Unit):HTML{
    val html=HTML()
    html.init()
    return html

}
fun <T> max(collection:Collection<T>,less:(T,T)->Boolean):T?{
    var max:T?=null
    for(it in collection)
        if(max==null||less(max,it))
            max=it
    return max
}

fun<T,R> List<T>.map(transform:(T)->R):List<R>{
    var result= arrayListOf<R>()
    for (item in this)
        result.add(transform(item))

    return result
}




//fun main(args: Array<String>) {
//    //demo()
//    //printFoo(D())
//    //E().foo()
//   // E().foo(1)
//    val river=U3dser("river",1)
//    val river2 = river.copy(age = 2)
//    println(river.toString())
//    println(river2.toString())
//    //解构声明
//    val (name,age)=river
//    println("I'm $name is $age")
//    val ints:kotlin.Array<Int> = arrayOf(1,2,3)
//    val any=kotlin.Array<Any>(3){""}
//    copy(ints,any)
//    printAllValues<RGB>()
//}
fun main(args:Array<String>){
    printAllValues<RGB>()
    val b=BaseImpl(10)
    Derived(b).print()
    val e = Example()
    println(e.p)
    println(LazyValue)
    println(LazyValue)
    val user=User_Val(mapOf(
            "name" to "river",
            "age" to 1
    ))
    print(user.name)
    asList(1,2,3)
    val a= arrayOf(1,2,3)
    asList(1,2,*a,4)

    var sum=0
    val ints:IntArray= intArrayOf(1,2,3,4)
    ints.filter { it>0 }.forEach { sum+=it }
    print(sum)
    html{body()}
}
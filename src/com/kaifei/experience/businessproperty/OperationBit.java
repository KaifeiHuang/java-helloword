package com.kaifei.experience.businessproperty;

import com.kaifei.util.logger.LoggerUtil;
import org.junit.Test;

/**
 * 采用int类型的bit位来表示业务属性， 可以表示64种类型的业务属性，能够处理大多数的一个模块的大多的数业务场景
 *
 * 以nio的SelectionKey为例
 *
 * 第 0 个属性为来表示 OP_READ  读事件
 * 第 1 个属性为来表示 OP_WRITE  写事件
 * 第 2 个属性为来表示 OP_CONNECT  连接事件
 * 第 3 个属性为来表示 OP_ACCEPT  监听事件
 */
public class OperationBit {
    /**
     * 操作失败的状态码
     *
     * 使用这个来代替在代码里使用魔鬼数字-1，便于维护
     */
    public static int OPERATION_FAILED = -1;

    /**
     * 最小的比特位
     * 0
     */
    public static int MIN_BIT_INDEX = 0;

    /**
     * 最大的比特位
     * 62
     *
     * 1<< 62 => 1073741824
     * 1<< 63 => -2147483648
     * 1<< 64 => 1
     */
    public static int MAX_BIT_INDEX = 62;



    // 业务属性的命名
    // -- Operation bits and bit-testing convenience methods --

    /**
     * Operation-set bit for read operations.
     *
     * <p> Suppose that a selection key's interest set contains
     * <tt>OP_READ</tt> at the start of a <a
     * href="Selector.html#selop">selection operation</a>.  If the selector
     * detects that the corresponding channel is ready for reading, has reached
     * end-of-stream, has been remotely shut down for further reading, or has
     * an error pending, then it will add <tt>OP_READ</tt> to the key's
     * ready-operation set and add the key to its selected-key&nbsp;set.  </p>
     */
    public static final int OP_READ = 1 << 0;

    /**
     * Operation-set bit for write operations.
     *
     * <p> Suppose that a selection key's interest set contains
     * <tt>OP_WRITE</tt> at the start of a <a
     * href="Selector.html#selop">selection operation</a>.  If the selector
     * detects that the corresponding channel is ready for writing, has been
     * remotely shut down for further writing, or has an error pending, then it
     * will add <tt>OP_WRITE</tt> to the key's ready set and add the key to its
     * selected-key&nbsp;set.  </p>
     */
    public static final int OP_WRITE = 1 << 2;

    /**
     * Operation-set bit for socket-connect operations.
     *
     * <p> Suppose that a selection key's interest set contains
     * <tt>OP_CONNECT</tt> at the start of a <a
     * href="Selector.html#selop">selection operation</a>.  If the selector
     * detects that the corresponding socket channel is ready to complete its
     * connection sequence, or has an error pending, then it will add
     * <tt>OP_CONNECT</tt> to the key's ready set and add the key to its
     * selected-key&nbsp;set.  </p>
     */
    public static final int OP_CONNECT = 1 << 3;

    /**
     * Operation-set bit for socket-accept operations.
     *
     * <p> Suppose that a selection key's interest set contains
     * <tt>OP_ACCEPT</tt> at the start of a <a
     * href="Selector.html#selop">selection operation</a>.  If the selector
     * detects that the corresponding server-socket channel is ready to accept
     * another connection, or has an error pending, then it will add
     * <tt>OP_ACCEPT</tt> to the key's ready set and add the key to its
     * selected-key&nbsp;set.  </p>
     */
    public static final int OP_ACCEPT = 1 << 4;


    /**
     * set businessProperty by bitIndex.
     *
     * 使用 | 运算是为了在已有的数据上操作，并且不覆盖之前的数据
     * 比如：原始property是 1000， 现在 | 操作后是 11000
     *
     * @param baseProperty the original property value
     * @param bitIndex the bit index
     * @return the property value
     */
    public static int setBitProperty(int baseProperty, int bitIndex){
        if (bitIndex < MIN_BIT_INDEX || bitIndex > MAX_BIT_INDEX){
            LoggerUtil.error("bitIndex " + bitIndex + "is invalid.");
            return OPERATION_FAILED;
        }

        int tmpProperty = 1 << bitIndex;
        return tmpProperty | baseProperty;
    }

    /**
     * unset bitProperty
     *
     * @param baseProperty original property value
     * @param bitIndex input bit index
     * @return the unset value
     */
    public static int unsetBitProperty(int baseProperty, int bitIndex){
        if (bitIndex < MIN_BIT_INDEX || bitIndex > MAX_BIT_INDEX){
            LoggerUtil.error("bitIndex " + bitIndex + "is invalid.");
            return OPERATION_FAILED;
        }

        int tmpProperty = 1 << bitIndex;
        if ((baseProperty & tmpProperty) == 0) {
            return tmpProperty;
        }

        return baseProperty ^ tmpProperty;
    }

    /**
     * check whether the bitProperty is set or not
     *
     * @return check result
     */
    public static boolean checkBitProperty(int property, int bitIndex){
        if (bitIndex < MIN_BIT_INDEX || bitIndex > MAX_BIT_INDEX){
            LoggerUtil.error("bitIndex " + bitIndex + "is invalid.");
            return false;
        }

        int tmpProperty = 1 << bitIndex;
        return (property & tmpProperty) == tmpProperty;
    }


    /**
     * 数据库里过滤指定属性位的记录
     *
     * @param bitIndex 属性位
     */
    public static void getSpecificProperty(int bitIndex){
        // 比如数据库表TABEL_USERINFO, 有字段businessProperty
        String sql = "select * from TABEL_USERINFO where 1=1 " +
                "and (businessProperty & (1 << bitIndex))==0;";

    }

    @Test
    public void test(){
        System.out.println("-----------int bit-----------");
        System.out.println(1<<62);
        System.out.println(1<<63);
        System.out.println(1<<64);

        System.out.println("-----------long bit-----------");
        System.out.println((long) 1<<62);
        System.out.println((long) 1<<63);
        System.out.println((long) 1<<64);
    }




}

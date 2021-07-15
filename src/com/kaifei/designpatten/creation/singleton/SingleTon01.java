package com.kaifei.designpatten.creation.singleton;

public class SingleTon01 {
//    private static SingleTon01 instance; getInstance02和getInstance01使用

    private volatile static SingleTon01 instance;
    private SingleTon01(){}

    // lazy init, has multi-thread issue
    public SingleTon01 getInstance01(){
        if (instance == null) {
            return new SingleTon01();
        }

        return instance;
    }

    /**
     * 双重检查
     * 第一次检查： 检查是否已创建
     * 第二次检查： 保证该类不会被其他线程所持有
     *
     * 缺点：
     * 主要在于instance = new Singleton()这句，这并非是一个原子操作，事实上在 JVM 中这句话大概做了下面 3 件事情。
     *
     * 1. 给 instance 分配内存
     * 2. 调用 Singleton 的构造函数来初始化成员变量
     * 3. 将instance对象指向分配的内存空间（执行完这步 instance 就为非 null 了）
     *    是在 JVM 的即时编译器中存在指令重排序的优化。
     *    也就是说上面的第二步和第三步的顺序是不能保证的，
     *    最终的执行顺序可能是 1-2-3 也可能是 1-3-2。
     *    如果是后者，则在 3 执行完毕、2 未执行之前，被线程二抢占了，
     *    这时 instance 已经是非 null 了（但却没有初始化），
     *    所以线程二会直接返回 instance，然后使用，然后顺理成章地报错。
     * @return
     */
    public SingleTon01 getInstance02(){
        if (instance == null) { // 第一次检查： 检查是否已创建
             synchronized (SingleTon01.class) { // 检查是否有其他线程正在创建
                 if (instance == null) {
                     return new SingleTon01();
                 }
             }
        }

        return instance;
    }


    /**
     * 双重检查 + volatile
     * 第一次检查： 检查是否已创建
     * 第二次检查： 保证该类不会被其他线程所持有
     * @return
     */
    public SingleTon01 getInstance03(){
        if (instance == null) { // 第一次检查： 检查是否已创建
            synchronized (SingleTon01.class) { // 检查是否有其他线程正在创建
                if (instance == null) {
                    return new SingleTon01();
                }
            }
        }

        return instance;
    }

}

package test;

public class OuterClass {
    private String outerField = "I am from OuterClass";

    // 外部类的方法，用于创建内部类的实例
    public InnerClass createInnerClassInstance() {
        InnerClass inner = new InnerClass(); // 创建内部类实例
        inner.displayFields();               // 调用内部类的方法
        inner.innerMethod();                 // 调用内部类的另一个方法
        return inner;
    }

    // 非静态内部类
    public class InnerClass {
        private final String innerField = "I am from InnerClass";

        // 内部类可以访问外部类的成员
        public void displayFields() {
            System.out.println("Outer Field: " + outerField);
            System.out.println("Inner Field: " + innerField);
        }

        // 内部类可以定义自己的方法和成员
        public void innerMethod() {
            System.out.println("This is a method inside InnerClass.");
        }

        // 内部类可以定义自己的方法和成员
        public String getInnerField() {
            return innerField;
        }
    }

    Object aa;
}

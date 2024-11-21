
class GeometricObject {
    private String color="white";
    private boolean filled=false;

    public GeometricObject() {
    }
    public GeometricObject(String color, boolean filled) {
        this.color=color;
        this.filled=filled;
    }

    public String getColor() {
        return color;
    }
    public boolean isFilled() {
        return filled;
    }
    public void setColor(String color) {
        this.color=color;
    }
    public void setFilled(boolean filled) {
        this.filled=filled;
    }
}

class Triangle extends GeometricObject {
    //1. 三个 `double` 数据字段，分别命名为 `side1`、`side2` 和 `side3`，默认值为 1.0，表示三角形的三条边。
    private double side1, side2, side3;
    //2. 一个无参构造函数，用于创建一个默认的三角形。
    public Triangle() throws IllegalSideException {
        this(1.0, 1.0, 1.0);
    }
    //3. 一个构造函数，用于根据指定的 `side1`、`side2` 和 `side3` 创建三角形。
    public Triangle(double side1, double side2, double side3) throws IllegalSideException {
        if(side1 <= 0 || side2 <= 0 || side3 <= 0) {
            throw new IllegalSideException(side1, side2, side3);
        }

        this.side1 = side1;
        this.side2 = side2;
        this.side3 = side3;
    }
    //4. 一个名为 `getArea()` 的方法，返回该三角形的面积。
    public double getArea() {
        double s = (side1 + side2 + side3) / 2;
        return Math.sqrt(s * (s - side1) * (s - side2) * (s - side3));
    }
    //5. 一个名为 `getPerimeter()` 的方法，返回该三角形的周长。
    public double getPerimeter() {
        return side1 + side2 + side3;
    }
    //6. 一个名为 `toString()` 的方法，返回该三角形的字符串表示。
    public String toString() {
        return "Triangle: side1=" + side1 + ", side2=" + side2 + ", side3=" + side3;
    }
}

class IllegalSideException extends Exception {
    private double side1;
    private double side2;
    private double side3;

    // 无参构造函数
    public IllegalSideException() {
        super("Illegal Sides");
    }

    // 带参数构造函数
    public IllegalSideException(double side1, double side2, double side3) {
        super("Illegal Sides: " + side1 + ", " + side2 + ", " + side3);
        this.side1 = side1;
        this.side2 = side2;
        this.side3 = side3;
    }

    // 重写 getMessage() 方法
    @Override
    public String getMessage() {
        return "Illegal Sides: " + side1 + ", " + side2 + ", " + side3;
    }
}

//编写一个测试程序，创建两个 `Triangle` 对象。一个三角形的边长为 1、1.5、1，设置颜色为黄色，填充状态为 true，并显示边长（使用 `toString()`）、面积、周长、颜色以及是否填充。另一个三角形的边长为 -1、0、1，这将触发 `IllegalSideException` 异常并打印出异常消息。
public class Main {
    public static void main(String[] args) {
        Triangle t1 = null;
        try {
            t1 = new Triangle(1, 1.5, 1);
        }catch (IllegalSideException e) {
            System.out.println(e.getMessage());
            return;
        }
        t1.setColor("yellow");
        t1.setFilled(true); // 设置填充状态为 true
        System.out.println(t1.toString()); // 输出三角形的字符串表示
        System.out.println("Area: " + t1.getArea()); // 输出三角形的面积
        System.out.println("Perimeter: " + t1.getPerimeter()); // 输出三角形的周长
        System.out.println("Color: " + t1.getColor()); // 输出三角形的颜色
        System.out.println("Filled: " + t1.isFilled()); // 输出三角形的填充状态

        Triangle t2 = null;
        try {
            t2 = new Triangle(-1, 0, 1); // 触发异常
        }catch (IllegalSideException e) {
            System.out.println(e.getMessage());
            return;
        }
        System.out.println(t2.toString()); // 输出异常信息
    }
}
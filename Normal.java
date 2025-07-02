package com.ms.pojo.base.drill;

import com.ms.common.MathUtil;
import com.ms.pojo.Interface.Drill;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class Normal implements Drill {
    // 孔的标志编号
    private Integer T;
    // 孔的直径
    private BigDecimal C;
    // 普通孔的X坐标
    private BigDecimal X;
    // 普通孔的Y坐标
    private BigDecimal Y;
    // 关联的标志对象
    private Sample linkSample;
    // 定义一个私有布尔变量 isend，判断是否为尾孔，默认值为 false
    private Boolean isend = false;
    // 定义一个私有布尔变量 isLuo，判断是否为锣刀，默认值为 false
    private Boolean isLuo = false;

    /**
     * 获取 T 的值
     *
     * @return 返回 T 的值
     */
    public Integer getT() {
        return T;
    }

    public Boolean getLuo() {
        return isLuo;
    }

    public void setLuo(Boolean luo) {
        isLuo = luo;
        this.linkSample.setLuo(true);
    }

    /**
     * 设置 T 的值
     *
     * @param t 要设置的 T 的值
     */
    public void setT(Integer t) {
        T = t;
    }

    /**
     * 获取 C 的值
     *
     * @return 返回 C 的值
     */
    public BigDecimal getC() {
        return C;
    }

    /**
     * 设置 C 的值，并将其舍入到 3 位小数
     *
     * @param c 要设置的 C 的值
     */
    public void setC(BigDecimal c) {
        if (this.isLuo == false) {
            C = MathUtil.Tailimprove(c);
            C = C.setScale(3, RoundingMode.HALF_UP);
        } else {
            C = c;
        }
    }

    public void setC(BigDecimal c, Boolean improve) {
        if (improve == true) C = MathUtil.Tailimprove(c);
        else C = c;
        C = C.setScale(3, RoundingMode.HALF_UP);
    }

    /**
     * 获取 X 的值
     *
     * @return 返回 X 的值
     */
    public BigDecimal getX() {
        return X;
    }

    /**
     * 设置 X 的值，并将其舍入到 3 位小数
     *
     * @param x 要设置的 X 的值
     */
    public void setX(BigDecimal x) {
        X = x.setScale(3, RoundingMode.HALF_UP);
    }

    /**
     * 获取 Y 的值
     *
     * @return 返回 Y 的值
     */
    public BigDecimal getY() {
        return Y;
    }

    /**
     * 设置 Y 的值，并将其舍入到 3 位小数
     *
     * @param y 要设置的 Y 的值
     */
    public void setY(BigDecimal y) {
        Y = y.setScale(3, RoundingMode.HALF_UP);
    }

    /**
     * 构造一个新的 Normal 对象，初始值为 t、x 和 y
     *
     * @param t T 的初始值
     * @param x X 的初始值
     * @param y Y 的初始值
     */
    public Normal(Integer t, BigDecimal x, BigDecimal y) {
        T = t;
        X = x.setScale(3, RoundingMode.HALF_UP);
        Y = y.setScale(3, RoundingMode.HALF_UP);
    }

    /**
     * 获取 isend 的值
     *
     * @return 返回 isend 的值
     */
    public Boolean getIsend() {
        return isend;
    }

    /**
     * 设置 isend 的值
     *
     * @param isend 判断是否是尾孔
     */
    public void setIsend(Boolean isend) {
        this.isend = isend;
    }

    @Override
    public String toString() {
        return "Normal{" + "T=" + T + ", C=" + C + ", X=" + X + ", Y=" + Y + ", linkSample=" + linkSample + ", isend=" + isend + '}';
    }

    /**
     * 默认构造函数
     */
    public Normal() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Normal normal = (Normal) o;
        return Objects.equals(T, normal.T) && Objects.equals(C, normal.C) && Objects.equals(X, normal.X) && Objects.equals(Y, normal.Y) && Objects.equals(linkSample, normal.linkSample) && Objects.equals(isend, normal.isend);
    }

    @Override
    public int hashCode() {
        return Objects.hash(T, C, X, Y, linkSample, isend);
    }

    public Sample getLinkSample() {
        return linkSample;
    }

    /**
     * 设置关联的Sample对象并自动更新相关属性
     * 该方法会将传入Sample对象的C值（经过舍入处理）和T值赋给当前对象
     *
     * @param linkSample 要关联的Sample对象，不可为null
     */
    public void setLinkSample(Sample linkSample) {
        this.linkSample = linkSample;
        setC(linkSample.getC());
        this.T = linkSample.getT();
    }

    public void setLinkSample(Sample linkSample, Boolean improve) {
        this.linkSample = linkSample;
        setC(linkSample.getC(), improve);
        this.setT(linkSample.getT());
    }
}

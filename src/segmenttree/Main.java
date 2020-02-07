package segmenttree;

/**
 * Project：Agorthm     @author 源伟
 * DateTime：2019/11/13 14:26
 * Description：TODO
 */
public class Main {

    public static void main(String[] args) {
        int[] nums = {-2, 0, 3, -5, 2, -1};
        NumArray2 numArray2 = new NumArray2(nums);

        System.out.println(numArray2.sumRange(0, 2));
        System.out.println(numArray2.sumRange(2, 5));
        numArray2.update(3, 10);
        System.out.println(numArray2.sumRange(2, 5));

    }
}

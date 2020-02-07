package segmenttree;

/**
 * Project：Agorthm     @author 源伟
 * DateTime：2019/11/13 15:25
 * Description：TODO
 */
public class NumArray2 {
    private int[] sum;

    public NumArray2(int[] nums) {
        sum = new int[nums.length];
        sum[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            sum[i] = sum[i - 1] + nums[i];
        }
    }

    public void update(int index, int val) {
        int moreSize = sum[index] - sum[index - 1] - val;

        for (int i = index; i < sum.length; i++) {

            sum[i] -= moreSize;
        }

    }

    public int sumRange(int i, int j) {
        if (i == 0){
            return sum[j];
        }
        return sum[j] - sum[i - 1];
    }
}

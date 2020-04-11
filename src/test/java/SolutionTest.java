import org.junit.Test;

public class SolutionTest {
    Solution solution = new Solution();

    @Test
    public void lengthOfLongestSubstringTest() {
        int m = solution.lengthOfLongestSubstring("anviaj");
        System.out.println(m);
    }

    @Test
    public void findMedianSortedArraysTest() {
        double m = solution.findMedianSortedArrays(new int[]{1}, new int[]{2, 3, 4, 5, 6});
        System.out.println(m);
    }

    @Test
    public void getKthTest() {
        int[] nums1 = new int[]{1, 3, 5, 7, 9};
        int[] nums2 = new int[]{2, 4, 6, 8, 10};

        int id = solution.getKth(nums1, 0, nums2, 0, 7);
        System.out.println(id);
    }

    @Test
    public void symmetryPalindromeTest() {
        //System.out.println(solution.longestPalindrome("babad"));
        //System.out.println(solution.longestPalindrome("cbbd"));
        System.out.println(solution.longestPalindrome("ccc"));
    }

    @Test
    public void convertTest() {
        System.out.println(solution.convert("PAYPALISHIRING", 3));
        //System.out.println(solution.convert("PAYPALISHIRING", 4));
    }

    @Test
    public void reverseTest() {
        System.out.println(solution.reverse(123));
        System.out.println(solution.reverse(-123));
        System.out.println(solution.reverse(120));
        System.out.println(solution.reverse(1534236469));
    }

    @Test
    public void myAtoiTest() {
        System.out.println(solution.myAtoi("123"));
        System.out.println(solution.myAtoi("   -123"));
        System.out.println(solution.myAtoi("2147483646"));
        System.out.println(solution.atoi("2147483646"));
    }

    @Test
    public void isPalindromeTest() {
        System.out.println(solution.isPalindrome(121));
        System.out.println(solution.isPalindrome(-121));
        System.out.println(solution.isPalindrome(10));
    }

    @Test
    public void isMatchTest() {
        System.out.println(solution.isMatch("aaa", "aaa"));
        System.out.println(solution.isMatch("abc", "a.c"));
        System.out.println(solution.isMatch("abc", "..."));
        System.out.println(solution.isMatch("abbccc", "a.bc*"));
        System.out.println(solution.isMatch("aab", "c*a*b"));
    }

    @Test
    public void maxAreaTest() {
        System.out.println(solution.maxArea(new int[]{1,8,6,2,5,4,8,3,7}));
    }

    @Test
    public void intToRomanTest() {
        System.out.println(solution.intToRoman(3));
        System.out.println(solution.intToRoman(4));
        System.out.println(solution.intToRoman(9));
        System.out.println(solution.intToRoman(58));
        System.out.println(solution.intToRoman(1994));
    }

    @Test
    public void romanToIntTest() {
        System.out.println(solution.romanToInt("III"));
        System.out.println(solution.romanToInt("IV"));
        System.out.println(solution.romanToInt("IX"));
        System.out.println(solution.romanToInt("LVIII"));
        System.out.println(solution.romanToInt("MCMXCIV"));
    }

    @Test
    public void longestCommonPrefixTest() {
        System.out.println(solution.longestCommonPrefix(new String[]{"flower","flow","flight"}));
        System.out.println(solution.longestCommonPrefix(new String[]{"dog","racecar","car"}));
    }

    @Test
    public void threeSumClosestTest() {
        System.out.println(solution.threeSumClosest(new int[]{-1, 2, 1, -4}, 1));
        System.out.println(solution.threeSumClosest(new int[]{0, 2, 1, -3}, 1));
    }

    @Test
    public void letterCombinationsTest() {
        System.out.println(solution.letterCombinations("23"));
        System.out.println(solution.letterCombinations(""));
    }

    @Test
    public void fourSumTest() {
        System.out.println(solution.fourSum(new int[]{1, 0, -1, 0, -2, 2}, 0));
    }

    @Test
    public void removeNthFromEndTest() {
        Solution.ListNode n1 = new Solution.ListNode(1);
        Solution.ListNode n2 = new Solution.ListNode(2);
        Solution.ListNode n3 = new Solution.ListNode(3);
        Solution.ListNode n4 = new Solution.ListNode(4);
        Solution.ListNode n5 = new Solution.ListNode(5);

        Solution.ListNode head = n1;
        n1.next = n2; n2.next = n3; n3.next = n4; n4.next = n5;
        //System.out.println(solution.removeNthFromEnd(head, 1));
        System.out.println(solution.removeNthFromEnd(new Solution.ListNode(1), 1));
    }

    @Test
    public void isValidTest() {
        System.out.println(solution.isValid("()"));
        System.out.println(solution.isValid("()[]{}"));
        System.out.println(solution.isValid("(]"));
        System.out.println(solution.isValid("([)]"));
        System.out.println(solution.isValid("{[]}"));
    }

    @Test
    public void mergeTwoListsTest() {
        Solution.ListNode n1 = new Solution.ListNode(1);
        Solution.ListNode n2 = new Solution.ListNode(2);
        Solution.ListNode n3 = new Solution.ListNode(4);
        n1.next = n2; n2.next = n3;

        Solution.ListNode n4 = new Solution.ListNode(1);
        Solution.ListNode n5 = new Solution.ListNode(3);
        Solution.ListNode n6 = new Solution.ListNode(4);
        n4.next = n5; n5.next = n6;

        System.out.println(solution.mergeTwoLists(n1, n4));
    }

    @Test
    public void generateParenthesisTest() {
        solution.generateParenthesis(3).forEach(e->System.out.println(e));
    }

    @Test
    public void mergeKListsTest() {
        Solution.ListNode n1 = new Solution.ListNode(1);
        Solution.ListNode n2 = new Solution.ListNode(4);
        Solution.ListNode n3 = new Solution.ListNode(5);
        n1.next = n2; n2.next = n3;

        Solution.ListNode n4 = new Solution.ListNode(1);
        Solution.ListNode n5 = new Solution.ListNode(3);
        Solution.ListNode n6 = new Solution.ListNode(4);
        n4.next = n5; n5.next = n6;

        Solution.ListNode n7 = new Solution.ListNode(2);
        Solution.ListNode n8 = new Solution.ListNode(6);
        n7.next = n8;

        System.out.println(solution.mergeKLists(new Solution.ListNode[]{n1, n4, n7}));
    }

    @Test
    public void swapPairsTest() {
        Solution.ListNode n1 = new Solution.ListNode(1);
        Solution.ListNode n2 = new Solution.ListNode(2);
        Solution.ListNode n3 = new Solution.ListNode(3);
        Solution.ListNode n4 = new Solution.ListNode(4);
        n1.next = n2; n2.next = n3; n3.next = n4;

        System.out.println(solution.swapPairs(n1));
    }

    @Test
    public void reverseKGroupTest() {
        Solution.ListNode n1 = new Solution.ListNode(1);
        Solution.ListNode n2 = new Solution.ListNode(2);
        Solution.ListNode n3 = new Solution.ListNode(3);
        Solution.ListNode n4 = new Solution.ListNode(4);
        Solution.ListNode n5 = new Solution.ListNode(5);
        n1.next = n2; n2.next = n3; n3.next = n4; n4.next = n5;

        System.out.println(solution.reverseKGroup(n1, 3));
    }

    @Test
    public void removeDuplicatesTest() {
        System.out.println(solution.removeDuplicates(new int[]{1,1,2}));
        System.out.println(solution.removeDuplicates(new int[]{0,0,1,1,1,2,2,3,3,4}));
    }

    @Test
    public void removeElementTest() {
        System.out.println(solution.removeElement(new int[]{3,2,2,3}, 3));
        System.out.println(solution.removeElement(new int[]{0,1,2,2,3,0,4,2}, 2));
    }

    @Test
    public void divideTest() {
       //System.out.println(solution.divide(10, 3));
       // System.out.println(solution.divide(7, -3));
        System.out.println(solution.divide(-2147483648, -1));
    }
}

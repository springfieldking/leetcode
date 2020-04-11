import java.util.*;

public class Solution {
    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public int lengthOfLongestSubstring(String s) {
        int asciiTable[] = new int[128];
        Arrays.fill(asciiTable, 0);
        char[] charArr = s.toCharArray();

        int max = 0;
        int head = 0;
        int tail = 0;
        while (head < charArr.length) {
            asciiTable[(int)charArr[head]] = 1;
            for(tail = head + 1; tail < charArr.length; tail ++) {
                if(asciiTable[(int)charArr[tail]] > 0)
                    break;
                asciiTable[(int)charArr[tail]] = 1;
            }

            int size = tail - head;
            max = size>max?size:max;

            Arrays.fill(asciiTable, 0);
            head++;
        }

        return max;
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        int l = (m + n + 1) / 2;
        int r = (m + n + 2) / 2;
        return (getKth(nums1, 0, nums2, 0,  l) + getKth(nums1, 0, nums2, 0, r)) / 2.0;
    }

    int getKth(int[] A, int aStart, int[] B, int bStart, int k) {
        if(aStart >= A.length)
            return B[bStart + k - 1];
        if(bStart >= B.length)
            return A[aStart + k - 1];
        if (k == 1)
            return Math.min(A[aStart], B[bStart]);

        int halfK = k/2;
        int aMid = Integer.MAX_VALUE, bMid = Integer.MAX_VALUE;
        if(aStart + halfK <= A.length) aMid = A[aStart + halfK - 1];
        if(bStart + halfK <= B.length) bMid = B[bStart + halfK - 1];

        if(aMid < bMid)
            return getKth(A, aStart + halfK, B, bStart, k - halfK);
        else
            return getKth(A, aStart, B, bStart + halfK, k - halfK);
    }


    public String longestPalindrome(String s) {
        int start = 0;
        int maxlen = 0;
        for(int i = 0; i < s.length(); i ++) {
            int l1 = symmetryPalindrome(s, i, i);           // odd
            int l2 = symmetryPalindrome(s, i, i + 1);    // even

            int m = Math.max(l1, l2);
            if(m > maxlen) {
                maxlen = m;
                start = i - (maxlen - 1)/2;
            }
        }
        return s.substring(start, start + maxlen);
    }

    int symmetryPalindrome(String s, int i, int j) {
        while(i >= 0 && j < s.length() && s.charAt(i) == s.charAt(j)) {
            i--;
            j++;
        }
        i++;
        j--;
        return j - i + 1;
    }

    public String convert(String s, int numRows) {
        StringBuilder[] sbs = new StringBuilder[numRows];
        for (int i = 0; i < sbs.length; i++)
            sbs[i] = new StringBuilder();

        int len = s.length();
        int i = 0;
        while (i < len) {
            for(int j = 0; j < numRows && i < len; j++)
                sbs[j].append(s.charAt(i++));
            for(int k = numRows - 2; k >= 1 && i < len; k--)
                sbs[k].append(s.charAt(i++));
        }

        StringBuilder result = new StringBuilder();
        for(StringBuilder sb : sbs) {
            result.append(sb);
        }
        return result.toString();
    }

    public int reverse(int x) {
        long result = 0;
        while(x != 0) {
            int last = x % 10;
            result = result * 10 + last;
            if(Integer.MIN_VALUE <= result && result <= Integer.MAX_VALUE)
                x /= 10;
            else
                return 0;
        }
        return (int)result;
    }

    public int myAtoi(String str) {
        int pos = 0;
        while (pos < str.length() && str.charAt(pos) == ' ')
            pos ++;

        int sign = 1;
        if(pos < str.length() && (str.charAt(pos) == '-' || str.charAt(pos) == '+')) {
            sign = str.charAt(pos) == '-' ? - 1 : 1;
            pos ++;
        }

        int result = 0;
        while (pos < str.length() && '0' <= str.charAt(pos) && str.charAt(pos) <= '9') {
            int digit = str.charAt(pos) - '0';
            if(Integer.MAX_VALUE/10 < result || Integer.MAX_VALUE/10 == result && Integer.MAX_VALUE %10 < digit)
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            result = result * 10 + digit;
            pos++;
        }

        return result * sign;
    }

    public int atoi(String str) {
        int index = 0, sign = 1, total = 0;
        //1. Empty string
        if(str.length() == 0) return 0;

        //2. Remove Spaces
        while(str.charAt(index) == ' ' && index < str.length())
            index ++;

        //3. Handle signs
        if(str.charAt(index) == '+' || str.charAt(index) == '-'){
            sign = str.charAt(index) == '+' ? 1 : -1;
            index ++;
        }

        //4. Convert number and avoid overflow
        while(index < str.length()){
            int digit = str.charAt(index) - '0';
            if(digit < 0 || digit > 9) break;

            //check if total will be overflow after 10 times and add digit
            if(Integer.MAX_VALUE/10 < total || Integer.MAX_VALUE/10 == total && Integer.MAX_VALUE %10 < digit)
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;

            total = 10 * total + digit;
            index ++;
        }
        return total * sign;
    }

    public boolean isPalindrome(int x) {
        if(x < 0)
            return false;

        List<Integer> nums = new ArrayList<Integer>();
        while (x != 0) {
            int tail = x % 10;
            nums.add(tail);
            x /= 10;
        }

        int len = nums.size();
        int l = (len - 1) / 2;
        int r = len / 2;
        while(l >= 0 && r < len) {
            if(nums.get(l) == nums.get(r)) {
                l--;
                r++;
            } else {
                return false;
            }
        }
        return true;
    }

    public boolean isMatch(String s, String p) {
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        dp[0][0] = true;
        for(int j = 0; j < p.length(); j++) {
            if(p.charAt(j) == '*') dp[0][j+1] = dp[0][j-1];
        }
        for(int i = 0; i < s.length(); i++) {
            for(int j = 0; j < p.length(); j++) {
                if(p.charAt(j) == s.charAt(i)) {
                    dp[i+1][j+1] = dp[i][j];
                }
                if(p.charAt(j) == '.') {
                    dp[i+1][j+1] = dp[i][j];
                }
                if(p.charAt(j) == '*') {
                    if(p.charAt(j - 1) != s.charAt(i) && p.charAt(j -1 ) != '.') {
                        dp[i+1][j+1] = dp[i+1][j-1];
                    } else {
                        dp[i+1][j+1] = dp[i][j+1] || dp[i+1][j] || dp[i+1][j-1];
                    }
                }
            }
        }
        return dp[s.length()][p.length()];
    }

    public int maxArea(int[] height) {
        int i = 0, j = height.length - 1, water = 0;
        while (i < j) {
            int h = Math.min(height[i], height[j]);
            int w = j - i;
            water = Math.max(water, h * w);

            while (height[i] <= h && i < j) i++;
            while (height[j] <= h && i < j) j--;
        }
        return water;
    }

    public String intToRoman(int num) {
        String M[] = {"", "M", "MM", "MMM"};
        String C[] = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
        String X[] = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        String I[] = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};

        return M[num/1000] + C[(num%1000)/100] + X[(num%100)/10] + I[num%10];
    }

    public int romanToInt(String s) {
        int sum = 0, currcode = 0, lastcode = 1000;
        for(char c : s.toCharArray()){
            if(c == 'I') currcode= 1;
            if(c == 'V') currcode= 5;
            if(c == 'X') currcode= 10;
            if(c == 'L') currcode= 50;
            if(c == 'C') currcode= 100;
            if(c == 'D') currcode= 500;
            if(c == 'M') currcode= 1000;;

            if(lastcode >= currcode)
                sum += currcode;
            else
                sum += (currcode - lastcode - lastcode);
            lastcode = currcode;
        }
        return sum;
    }

    public String longestCommonPrefix(String[] strs) {
        if(strs.length == 0) return "";

        StringBuilder sb = new StringBuilder();
        String firstStr = strs[0];
        int index = 0;

        while (index < firstStr.length()) {
            boolean match = true;
            for(int i = 1; i < strs.length; i++ ) {
                match = (index < strs[i].length()) && (strs[i].charAt(index) == firstStr.charAt(index));
                if(!match) break;
            }
            if(match) {
                sb.append(firstStr.charAt(index));
                index ++;
            } else
                break;
        }
        return sb.toString();
    }

    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int closest = Integer.MAX_VALUE;
        for(int i = 0; i < nums.length - 2; i ++ ) {
            int j = i + 1, k = nums.length -1, t = target - nums[i];
            while (j < k) {
                int dis = nums[i] + nums[j] + nums[k] - target;
                if(Math.abs(dis) < Math.abs(closest)) {
                    closest = dis;
                }
                if(dis < 0) j++; else k--;
            }
        }
        return closest + target;
    }

    public List<String> letterCombinations(String digits) {
        if(digits.length() == 0) return new ArrayList<String>();

        String[] digitToLetter = new String[]{
          "",       "abc",  "def",
          "ghi",    "jkl",  "mno",
          "pqrs",   "tuv",  "wxyz",
        };

        LinkedList<String> fifo = new LinkedList();
        fifo.add("");
        for(int i = 0; i < digits.length(); i++) {
            int index = digits.toCharArray()[i] - '1';
            String letters = digitToLetter[index];
            while (fifo.peek().length() == i){
                String first = fifo.removeFirst();
                for (char c : letters.toCharArray()) {
                    fifo.add(first + c);
                }
            }
        }
        return fifo;
    }

    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> results = new ArrayList<List<Integer>>();
        if(nums.length < 4) return results;

        Arrays.sort(nums);
        for(int i = 0; i < nums.length - 3; i ++) {
            if(i > 0 && nums[i] == nums[i-1]) continue; // skip same i
            for(int j = i + 1; j < nums.length - 2; j++) {
                if(j > i + 1 && nums[j] == nums[j-1]) continue; // skip same j
                int k = j + 1, l = nums.length-1;
                while (k < l) {
                    int sum = nums[i] + nums[j] + nums[k] + nums[l];
                    if(sum == target) {
                        results.add(Arrays.asList(nums[i], nums[j], nums[k], nums[l]));
                        while (k < l && nums[k] == nums[k+1]) k++;  // skip same k
                        while (k < l && nums[l] == nums[l-1]) l--;  // skip same l
                        k++;
                        l--;
                    }else if(sum < target)
                        k++;
                    else
                        l--;
                }
            }
        }

        return results;
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode fast = dummy, slow = dummy;
        while (n-- >= 0)
            fast = fast.next;

        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }

        slow.next = slow.next.next;
        return dummy.next;
    }

    public boolean isValid(String s) {
        Stack<Character> cs = new Stack<Character>();
        for(char c : s.toCharArray()) {
            if(c=='(' || c == '{' || c == '[')   cs.push(c);
            else if(!cs.isEmpty() && cs.peek() == '(' && c ==')') cs.pop();
            else if(!cs.isEmpty() && cs.peek() == '{' && c =='}') cs.pop();
            else if(!cs.isEmpty() && cs.peek() == '[' && c ==']') cs.pop();
            else return false;
        }
        return cs.isEmpty();
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0), last = head;
        while (l1 != null && l2 != null) {
            ListNode ln;
            if(l1.val < l2.val) {
                ln = l1;
                l1 = l1.next;
            } else {
                ln = l2;
                l2 = l2.next;
            }

            last.next = ln;
            ln.next = null;
            last = ln;
        }
        if(l1 != null) last.next = l1;
        if(l2 != null) last.next = l2;
        return head.next;
    }

    public List<String> generateParenthesis(int n) {
        List<String> results = new ArrayList<String>();
        catalanNumberProblem(results, "", 0, 0, n);
        return results;
    }

    void catalanNumberProblem(List<String> results, String combination, int count, int sum, final int max) {
        if(count == max) {
            while (sum-->0) combination += ')';
            results.add(combination);
            return;
        }

        catalanNumberProblem(results, combination + '(', count + 1, sum + 1, max);
        if(sum - 1 >= 0)
            catalanNumberProblem(results, combination + ')', count, sum - 1, max);
    }

    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> queue = new PriorityQueue<>((o1, o2) -> {
            if(o1.val <  o2.val) return -1;
            else if(o1.val > o2.val) return 1;
            else return 0;
        });

        for(ListNode node : lists) {
            if(node != null) queue.add(node);
        }

        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        while (!queue.isEmpty()) {
            ListNode node = queue.poll();
            tail.next = node;
            tail = node;
            if(node.next != null)
                queue.add(node.next);
        }

        return dummy.next;
    }

    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(0); dummy.next = head;
        ListNode l = dummy, r = dummy;

        int count = 0;
        while (r.next != null && ++count > 0) {
            r = r.next;
            if(count % 2 == 0) {
                // swap
                l.next.next = r.next;
                r.next = l.next;
                l.next = r;
                // again
                r = r.next;
                l = r;
            }
        }

        return dummy.next;
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(0); dummy.next = head;
        ListNode l = dummy, r = dummy;

        int count = 0;
        while (r.next != null && ++count > 0) {
            r = r.next;
            if(count % k == 0) {
                // mark h
                ListNode h = l.next;
                while (l.next != r) {
                    ListNode t = l.next;
                    l.next = t.next;
                    t.next = r.next;
                    r.next = t;
                }
                l = h; r = h;
            }
        }

        return dummy.next;
    }

    public int removeDuplicates(int[] nums) {
        int l = 0, r = l + 1;
        while (r < nums.length) {
            if(nums[r-1] < nums[r]) nums[++l] = nums[r];
            r++;
        }
        return l + 1;
    }

    public int removeElement(int[] nums, int val) {
        int l = 0;
        for(int r = 0; r < nums.length; r ++) {
            if(nums[r] != val) nums[l++] = nums[r];
        }
        return l;
    }

    public int strStr(String haystack, String needle) {
        for (int i = 0; ; i++) {
            for (int j = 0; ; j++) {
                if (j == needle.length()) return i;
                if (i + j == haystack.length()) return -1;
                if (needle.charAt(j) != haystack.charAt(i + j)) break;
            }
        }
    }

    public int divide(int dividend, int divisor) {
        int sign = (dividend > 0 ? 1 : -1) * (divisor > 0 ? 1 : -1);
        long ldividend = Math.abs((long)dividend);
        long ldivisor = Math.abs((long)divisor);
        long d = 0;
        while ((ldividend -= ldivisor) >= 0) d++;

        int di = (int) d;
        if(di < 0) return Integer.MAX_VALUE;
        else return di* sign;
    }
}

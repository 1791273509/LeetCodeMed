
package medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;


import easy.TreeNode;
@SuppressWarnings("all")
public class Med {

	
	
	//20��44��
	public int canCompleteCircuit(int[] gas, int[] cost) {
        int gas1 [] = new int[gas.length * 2];
        int cost1[] = new int[cost.length * 2];
        //�����������չ�������鷳
        for (int i = 0; i < gas1.length; i++) {
			gas1[i] = gas[i % gas.length];
		}
        
        for (int i = 0; i < gas1.length; i++) {
			cost1[i] = cost[i % gas.length];
		}
        int len = gas.length;
        int index = 0;
        int shengyu = 0;
        for (int i = 0; i < len; i++) {
			shengyu = 0;
			int end = i + len;
			boolean flag = true;
			for (int j = i; j < end; j++) {
				if(shengyu + gas1[j] - cost1[j] >= 0){
					shengyu = shengyu + gas1[j] - cost1[j]; 
					continue;
				}
				flag = false;
			}
			if(flag){
				return i;
			}
		}
        return -1;
        
        
        
    }
	//22��02��
	public int magicalString(int n) {
        if(n == 0){
        	return 0;
        }
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(2);
        int indexA = 2;
        int k = 1;
        while (list.size() < n) {
        	if(list.get(indexA) == 2){
        		list.add(k);
        		list.add(k);
        	}
        	if(list.get(indexA) == 1){
        		list.add(k);
        	}

        	k = k == 1 ? 2 : 1;
        	indexA ++;
		}
        int sum = 0;
        for (int i = 0; i < n; i++) {
        	if(list.get(i) == 1){
				sum += list.get(i);
			}
			
		}
        return sum;
    }
	
	public int sumNumbers(TreeNode root) {
        int sum = 0;
        if(root == null){
        	return sum;
        }
        List<Integer> tem = new ArrayList<>();
        
        get(root,0,tem);
        
        for (Integer integer : tem) {
			sum += integer;
		}
        return sum;
    }

	public static void get(TreeNode root,int sum,List<Integer>tem){
		if(root.left == null && root.right == null){
			sum = sum * 10 + root.val;
			tem.add(sum);
		}
		sum = sum * 10 + root.val;
		if(root.left != null){
		
			get(root.left, sum, tem);
		}
		
		if(root.right != null){
			get(root.right, sum, tem);
		}
		
	}
	public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        if(nums.length <= 3){
        	return result;
        }
        Set<List<Integer>> set = new HashSet<>();
        
        for (int i = 0; i < nums.length - 3; i++) {
			for (int j = i + 1; j < nums.length - 2; j++) {
				int left = j + 1;
				int right = nums.length - 1;
				while (left < right) {
					if(nums[i] + nums[j] + nums[left] + nums[right] == target){
						List<Integer> tem = new ArrayList<>();
						tem.add(nums[i]);
						tem.add(nums[j]);
						tem.add(nums[left]);
						tem.add(nums[right]);
						int size = set.size();
						set.add(tem);
						if(size > set.size()){
							result.add(tem);
						}
						left ++;
						right --;
					}else if (nums[i] + nums[j] + nums[left] + nums[right] < target) {
						left ++;
					}else {
						right --;
					}
					
				}
			}
		}
        return result;
    }
	
	 public static int minEatingSpeed(int[] piles, int H) {
		 //�����жϵ���H�Ĵ�������
		 int sum = 0;
		 int max = piles[0];
		 for (int i : piles) {
			sum += i;
			max = i > max ? i : max;
		}
		 if(H == sum){
			 return 1;
		 }
		 if(H == piles.length){
			 return max;
		 }
		 
		 int left = 1;
		 int right = max;
		 while (left < right) {
			int mid = left + (right - left) >> 1;
		 	System.out.println(mid);
		 	if(isEatAll(piles, mid, H)){
		 		right = mid;
		 	}else {
				left = mid + 1;
			}
		}
		 return left;
	 }
	 public static boolean isEatAll(int []piles,int mid,int H){
		 //�ж���mid�ٶ��Ƿ���Գ���һ��
		 int need = 0;
		 for (int i : piles) {
			need += i / mid;
			if(i % mid != 0){
				need ++;
			}
		}
		 
		 return need <= H;
	 }
	 
	 
	//21��53��
	public int shipWithinDays(int[] weights, int D) {
		long sum = 0;
		long left = weights[0];
		for (int i : weights) {
			sum += i;
			left = left > i ? left : i;
			
		}
		if(D == 1){
			return (int) sum;
		}
		if(D == weights.length){
			return (int) left;
		}
		long right = sum;
		while (left != right) {
			long mid = (left + right) >> 1;
			long cur = 0;
			int need = 1;
			for (int i : weights) {
				if(cur + i > mid){
					cur = 0;
					need ++;
				}
				cur += i;
				
			}
			if(need > D){
				left = mid + 1;
			}else {
				right = mid;
			}
			
		}
		return (int) left;
        
    }
	
	
	public int removeStones(int[][] stones) {
		//ʹ�ò��鼯
		if(stones == null || stones.length <=1){
			return 0;
		}
		Map<Integer, Integer> map = new HashMap<>();
		Set<Integer> set = new HashSet<>();
		for (int i = 0; i < stones.length; i++) {
			for (int j = i + 1; j < stones.length; j++) {
				if(map.get(i) == null){
					map.put(i, i);
				}
				if(map.get(j) == null){
					map.put(j, j);
				}
				if(stones[j][0] == stones[i][0] || stones[i][1] == stones[j][1]){
					System.out.println(stones[i]);
					Union(i,j, map);//i��value��key��j
				}
				
			}
		}
		for (int i = 0; i < stones.length; i++) {
			set.add(Find(i, map));
		}
		System.out.println(map.toString());
		return stones.length - set.size();
		
		
    }
	public int Find(int i,Map<Integer, Integer> map){
		return map.get(i) == i? i:Find(map.get(i), map);
	}
	public void Union(int i,int j,Map<Integer, Integer> map){
		map.put(Find(j, map), Find(i, map));
	}
	
	public int numberOfArithmeticSlices(int[] A) {
		int dp = 0;
		int sum = 0;
		if(A == null || A.length <= 2){
			return sum;
		}
		for (int i = 2; i < A.length; i++) {
			if(A[i] - A[i-1] == A[i-1] - A[i - 2]){
				dp++;
			}else {
				sum += dp;
				dp = 0;
			}
		}
		return sum;
    }
	
	
	
	
	
	public int[] nextGreaterElements(int[] nums) {
		if(nums == null || nums.length == 0){
			return new int[]{};
		}
		
		int result[] = new int[nums.length];
		
		int tem[] = new int[nums.length * 2];
		int max = nums[0];
		for (int i = 0; i < tem.length; i++) {
			tem[i] = nums[i % nums.length];
			max = tem[i] > max ? tem[i]:max;
		}
		System.out.println(Arrays.toString(tem));
		//stack��ŵ�Ҳ���±꣬map�����ŵ�key���±꣬value�Ƕ�Ӧ��ֵ
		Stack<Integer> stack = new Stack<>();
		int len = tem.length;
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < tem.length; i++) {
			 while (!stack.isEmpty() && tem[stack.peek()] < tem[i]) {
				 map.put(stack.pop(), tem[i]);
			}
			 stack.push(i % len);
		}
		System.out.println(map.toString());
		int i = 0;
		for (int j = 0; j < result.length; j++) {
			result[j] = map.getOrDefault(j, -1);
		}
		return result;
    }
	
	
	int nums = 0;
	int time = 0;
	public int[] findFrequentTreeSum(TreeNode root) {
		//���Ǳ��˵�˼·����������ģ�µ�
		//ʹ�õ��Ǻ������Ȼ�����map�У�list�е�ǰnum��ֵ���Ǵ������ļ���
		List<Integer> temlist = new ArrayList<>();
		Map<Integer, Integer> map = new HashMap<>();
		
		getfind(root, temlist, map);
		int result [] = new int[nums];
		for (int i = 0; i < nums; i++) {
			result[i] = temlist.get(i);
		}
		
		return result;
    }

	public int getfind(TreeNode root,List<Integer> tem,Map<Integer, Integer> map){
		
		if(root == null){
			return 0;
		}
		int left = getfind(root.left, tem, map);
		int right = getfind(root.right, tem, map);
		int sum = root.val + left + right;
		map.put(sum, map.getOrDefault(sum, 0) + 1);
		int times = map.get(sum);
		if(times >= time){
			if(times > time){
				nums = 0;
			}
			nums++;
			tem.add(0,sum);
			time = times;
		}
		return sum;
	}
	//��������
	public int findMaxLength(int[] nums) {
        int sum = 0;
        int result = 0;
        if(nums == null || nums.length == 0 || nums.length == 1){
        	return 0;
        }
        Map<Integer, Integer> temmap = new HashMap<>();
        temmap.put(0, -1);
        for (int i = 0; i < nums.length; i++) {
			if(nums[i] == 0){
				sum--;
			}else {
				sum++;
			}
			if(temmap.get(sum) != null){
				temmap.put(sum, i);
			}else {
				result = Math.max(result, i - temmap.get(sum));
				
			}
		}
        
        return result;
    }
	public List<Integer> findDuplicates(int[] nums) {
		List<Integer> result = new ArrayList<>();
		if(nums == null || nums.length == 0 || nums.length == 1){
			return result;
		}
		//�����±����ȡ��
		
		for (int i = 0; i < nums.length; i++) {
			int num = Math.abs(nums[i]);
			if(nums[num - 1] > 0){
				nums[num - 1] *=-1;
			}else {
				result.add(num);
			}
		}
		
		
		return result;
		
		
	}
	public int minSubArrayLen(int s, int[] nums) {
		 int min = nums.length;
		 int sum = 0;
		 for (int i : nums) {
			sum += i;
		}
		 if(sum < s){
			 return 0;
		 }
		 if(sum == s){
			 return nums.length;
		 }
		 sum = 0;
		 int l = 0;
		 int start = 0;
		 for (int r = 0; r < nums.length; r++) {
			sum += nums[r];
			while (sum - nums[l] >= s && l <= r) {
				sum -= nums[l++];
				
			}
			if(sum >= s){
				min = Math.min(min, r - l + 1);
			}
			
		}
		 
		 
		 return min;
		 
		 
	 }
	
	public int findLength(int[] A, int[] B) {
		int lenA = A.length + 1;
		int lenB = B.length + 1;
        int dp[][] = new int[lenA][lenB];
        int max = 0;
        for (int i = 0; i < A.length; i++) {
			for (int j = 0; j < B.length; j++) {
				if(A[i] == B[j]){
					dp[i+1][j+1] = dp[i][j] + 1;
					max = Math.max(max, dp[i+1][j+1]);
				}else {
					dp[i+1][j+1] = 0;
				}
			}
		}
        return max;
    }
	
	int countArrangement = 0;
	public static void main(String[] args) {
		
		List<Integer> tem = new ArrayList<>();
		tem.add(1);
		tem.add(2);
		System.out.println(tem.toString());
		tem.remove(tem.size() - 1);
		System.out.println(tem.toString());
		
		
	}
	//00��12��
	public void sortColors(int[] nums) {
        //0�� 1 �� 2 �ֱ��ʾ��ɫ����ɫ����ɫ
		
		int indexstart = 0;
		int indexend = nums.length - 1;
		  for (int i = 0; i <= indexend; i++) {
			if(nums[i] == 0){
				//�ƶ�����ͷ
				int tem = nums[indexstart];
				nums[indexstart] = nums[i];
				nums[i] = tem;
				indexstart ++;
			}else if (nums[i] == 2) {
				int tem = nums[indexend];
				nums[indexend] = nums[i];
				nums[i] = tem;
				indexend --;
			}
		}
			
	    
	
	
	}
	
	List<List<Integer>> result = new ArrayList<>();
	//23��00��
	public List<List<Integer>> combine(int n, int k) {
        

        int dire[] = new int[n];
        for (int i = 0; i < dire.length; i++) {
			dire[i] = i + 1;
		}
        List<Integer> tem = new ArrayList<>();
        getsub(result, tem, dire, k, 0);
        return result;
	}
	
	
	 public boolean stoneGame(int[] piles) {
	      int n = piles.length;
			
			//dp[i][j]��ʾ��piles�����i��jѡ��ʱ���ֱȺ��ֶ��ʯ�ӵ�����
			//��ôdp[i][j] = Max()
	        int dp[][] = new int[n][n];
	        for (int i = 0; i < dp.length; i++) {
				dp[i][i] = piles[i];
			}
	      for (int l = 2; l <= n; l++) {
	            for (int i = 0; i <= n - l; i++) {
	                int j = i + l - 1;
	                dp[i][j] = Math.max(piles[i] - dp[i + 1][j], piles[j] - dp[i][j - 1]);
	            }
	        }
	        
	        return dp[0][n - 1] > 0? true :false;
	    }
	
	
	public void getsub(List<List<Integer>> result, List<Integer> tem,int []n,int k,int dangqian){
        
		if(tem.size() == k){
            System.out.println(tem.toString());
			List<Integer> news = new ArrayList<>(tem);
			
			result.add(news);
			return ;
		}
		for (int i = dangqian; i < n.length; i++) {
			tem.add(n[i]);
			getsub(result, tem, n, k, i + 1);
			tem.remove(tem.size() - 1);
		}
		
		
	}
	//22��31��
	public List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k) {
		
		List<int[]> result = new ArrayList<>();
		
		if(nums1.length == 0 || nums2.length == 0){
			return result;
		}
		
		int nums1index = 0;
		int nums2index = 0;
		int jishu = 0;
		while (jishu < k) {
			if(nums1index == nums1.length - 1&& nums1index == nums2.length - 1){
				break;
			}
			int tem[] = new int[2];
			tem[0] = nums1[nums1index];
			tem[1] = nums2[nums2index];
			result.add(tem);
			if(nums1index < nums1.length - 1 && nums2index < nums2.length - 1){
				if(nums1[nums1index + 1] + nums2[nums2index] < nums1[nums1index] + nums2[nums2index + 1]){
					nums1index++;
				}else {
					nums2index++;
				}
			}else if (nums1index == nums1.length - 1 && nums2index == nums2.length - 1) {
				return result;
			}else if (nums1index == nums1.length - 1) {
				nums2index++;
			}else {
				nums1index++;
			}
			jishu ++;
		}
		return result;
    }
	//22��14��
	public int totalHammingDistance(int[] nums) {
        //ʹ����һ���㷨ʵ��
		int result = 0;
		int len = nums.length;
		for (int i = 0; i < 32; i++) {
			int tem = 0;
			for (int j = 0; j < nums.length; j++) {
				tem += (nums[j] >> 1) & 1;
			}
			result += tem * (len - tem);
		}
		
		
		return result;
    }
	//23��06��
	public String customSortString(String S, String T) {
		
		//��S�ֱ�����Ȼ���T�н��в鿴�Ƿ���
		char[] temS = S.toCharArray();
		char[] temT = T.toCharArray();
		int Tindex = 0;
		for (int i = 0; i < temS.length; i++) {
			int index = new String(temT).indexOf(temS[i]);
			
			if(index != -1){
				int lastindex = new String(temT).lastIndexOf(temS[i]);
				if(index == lastindex){
					char tem = temT[i];
					temT[i] = temT[index];
					temT[index] = tem;
				}else {
					
				}
				 
			}
		}
		System.out.println(Arrays.toString(temT));
		return new String(temT);
		
    }
	 int get = 0;
	//22��27��
	public int rangeSumBST(TreeNode root, int L, int R) {
        //�����������
			re(root, L, R);
		return get;
		
    }
	public void re(TreeNode root,int L,int R){
		if(root == null){
			return;
		}
		re(root.left, L, R);
		if(root.val >= L && root.val <= R){
			get+=root.val;
		}
        if(root.val > R)
			return;
		re(root.right, L, R);
	}
	//11��03��
	public int divide(int dividend, int divisor) {
		int result = 0;
		if(dividend == 0){
			return result;
		}
		if(dividend == Integer.MIN_VALUE && divisor == -1){
			return Integer.MAX_VALUE;
		}
		long a = Math.abs((long)dividend);
		long b = Math.abs((long)divisor);
		int shift = 0;
		while (a >= b) {
			while (a >= (b<<shift)) {
				shift ++;
			}
			
			a -= b<<(shift - 1);
			result +=1 <<( shift - 1);
			
			shift = 0;
			
		}
		if(dividend >  0 && divisor > 0 ||(dividend <  0 && divisor < 0)){
			return result;
		}
		
		
		return -result;
		
		
		
    }
	
	
	//22��26��
	public List<String> letterCombinations(String digits) {
		String [] tem = {"","","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
        List<String> result = new ArrayList<>();
        if(digits.length() == 0 || digits == null){
        	return result;
        }
        get(result, 0,"", tem,digits);
        return result;
        
    }
	
	public void get(List<String> tem,int start,String get,String [] tems,String digits){
		if(start >= digits.length()){
			tem.add(get);
			return;
		}
		int index = Integer.parseInt(digits.charAt(start) + "");
		
		for (int i = 0; i < tems[index].length(); i++) {
			get(tem,start+1, get + tems[index].charAt(i),tems,digits);
		}
		
	}
	//22��06��
	public TreeNode constructMaximumBinaryTree(int[] nums) {
        return get(0, nums.length - 1, nums);
    }
	public TreeNode get(int start, int end, int [] nums){
		if(start > end){
			return null;
		}
		int max = getMax(nums, start, end);
		TreeNode root = new TreeNode(nums[max]);
		root.left = get(start, max, nums);
		root.right = get(max, end, nums);
		return root;
	}
	public int getMax(int [] nums,int start,int end){
		int result = start;
		for (int i = start + 1; i <= end; i++) {
			if(nums[result] < nums[i]){
				result = i;
			}
		}
		return result;
	}
	
	
	//08��53��
	public int findCircleNum(int[][] M) {
		//���õ����ǵ���ʽ������������˼·
		Map<Integer, Integer> tem = new HashMap<>();
		for (int i = 0; i < M.length; i++) {
			for (int j = 0; j < M[i].length; j++) {
				if(M[i][j] == 1){
					if(!tem.containsKey(i)){
						tem.put(i, i);
					}
					if(!tem.containsKey(j)){
						tem.put(j, j);
					}
					unio(i, j, tem);
				}
			}
		}
		Set<Integer> res = new HashSet<>();
		for (int i = 0; i < M.length; i++) {
				res.add(find(i, tem));
		}
		return res.size();
    }
	public int find(int i,Map<Integer, Integer> tem){
		if(tem.get(i) != i){
			return find(tem.get(i), tem);
		}else {
			return i;
		}
		
	
	}
	public void unio(int i,int j,Map<Integer, Integer> tem){
		tem.put(find(i, tem), find(j, tem));
	}
	//16��21��
	public static int openLock(String[] deadends, String target) {
	    //���Ա�ʾ���������
		int direc[][] = {{1,0,0,0},
						 {-1,0,0,0},
						 {0,1,0,0},
						 {0,-1,0,0},
						 {0,0,1,0},
						 {0,0,-1,0},
						 {0,0,0,1},
						 {0,0,0,-1}};
		List<String> dead = Arrays.asList(deadends);
		String tem = "0000";//��0000��ʼ
		Queue<String> duilie = new LinkedList<>();
		Queue<String> duilie2 = new LinkedList<>();
		duilie2.offer(target);
		if(dead.contains(tem)){
			return -1;
		}
		if("0000".equals(target)){
			return 0;
		}
		duilie.offer(tem);
		int result = 0;
		
		//����isvisit����
		boolean isvisit[] = new boolean[10000];
		isvisit[0]  = true;
		while (!duilie.isEmpty() && !duilie2.isEmpty()) {
			//duilie�ǳ��ȶ̵��Ǹ���duilie2�ǳ��ȳ����Ǹ�
			if(duilie.size() > duilie2.size()){
				Queue<String> tems = duilie;
				duilie = duilie2;
				duilie2 = tems;
			}
			int size = duilie.size();
			for (int i = 0; i < size; i++) {
				String s1 = duilie.poll();
				//�˸����򶼽��б���
				for (int di[] : direc) {
					int i1 = (Integer.parseInt(s1.charAt(0)+"") + di[0] + 10) % 10;
					int i2 = (Integer.parseInt(s1.charAt(1)+"") + di[1] + 10) % 10;
					int i3 = (Integer.parseInt(s1.charAt(2)+"") + di[2] + 10) % 10;
					int i4 = (Integer.parseInt(s1.charAt(3)+"") + di[3] + 10) % 10;
					//9999
					String getString = i1 + "" + i2 +"" + i3 + "" + i4;
					if(duilie2.contains(getString)){
						return result + 1;
					}
					if(!dead.contains(getString)){
						//��һ����Ϊ�˷�ֹ��ѭ��
						if(!isvisit[i1 * 1000 + i2 * 100 + i3 * 10 + i4]){
							isvisit[i1 * 1000 + i2 * 100 + i3 * 10 + i4] = true;
							duilie.offer(getString);
						}
					}
				}
			}
			result ++;
		}
		return -1;
	 
	}
	
	//15��45��
	public int[] dailyTemperatures(int[] T) {
		for (int i = 0; i < T.length; i++) {
			int tem = 0;
			int j = i+1;
			boolean flag = true;
			for (; j < T.length; j++) {
				if(T[j] > T[i]){
					tem ++;
					flag = false;
					break;
				}else {
					tem++;
				}
			}
			if(!flag){
				T[i] = tem;
			}
			if(i == T.length - 1 ){
				T[i] = 0;
			}
		}
		
		
		return T;
        
    }	
	
	
	//21��49��
	public int[] deckRevealedIncreasing(int[] deck) {
		  Arrays.sort(deck);
	        int index = 0;
	        Deque<Integer> tem = new LinkedList<>();
	        for (int i = deck.length - 1; i >= 0 ; i--) {
	        	int size = tem.size();
	        	if (size > 1) {
					tem.offerFirst(tem.pollLast());
				}
	        	tem.offerFirst(deck[i]);
				
			}
	        int [] result = new int[deck.length];
	        int x = 0;
	        for (int i : tem) {
				result[x] = i;
				x++;
			}
	        return result;
        
    }
	
	//09��42��
	public List<Integer> grayCode(int n) {
       /* List<Integer> result = new ArrayList<>();
        int tem = (int) Math.pow(2, n);
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= tem; i++) {
			list.add(i);
		}
        if(n == 0 ){
        	result.add(0);
        	return result;
        }
        int max = 0;
        //����1 ������Ҫ���ӵ�max
        result.add(0);
        while (max < n) {
			max++;
			for (Integer integer : list) {
				if(Integer.bitCount(integer) - Integer.bitCount(result.get(result.size() - 1)) == 1){
					result.add(integer);
					list.remove(list.indexOf(integer));
					break;
				}
			}
		}
        max --;
        while (max >= 1) {
        	for (Integer integer : list) {
				if(Integer.bitCount(result.get(result.size() - 1)) - Integer.bitCount(integer)  == 1){
					result.add(integer);
					list.remove(list.indexOf(integer));
					break;
				}
			}
        	
        	
        	max--;
			
		}
       
        return result;*/
		List<Integer> result = new ArrayList<>();
		int max = (int) (Math.pow(2, n) - 1);
		for (int i = 0; i <= max; i++) {
			result.add(i ^ (i/2));
		}
		
		
		
		return result;
    }
	//18��57��
	public String iantToRoman(int num) {
        String result = null;
        return result;
    }
	//12��41��
	public int singleNumber(int[] nums) {
      /*
       * ͨ�õĽⷨ������Ч�ʵ�
       *   HashMap<Integer, Integer> map = new HashMap<>();
        for (int i : nums) {
			map.put(i, map.getOrDefault(i, 0) + 1);
		}
        for (Map.Entry<Integer, Integer> s : map.entrySet()) {
			if(s.getValue() == 1){
				return s.getKey();
			}
		}
        return 0;*/
		int tem[] = new int[32];
		int result = 0;
		for (int i = 0; i < nums.length; i++) {
			int tems = nums[i];
			for (int j = 0; j < 32; j++) {
				tem[j] += tems % 2 == 0 ? 0:1;
				tems = tems >> 1;
			if(tems == 0){
				break;
			}
			}
		}
		for (int i = 31; i >= 0; i--) {
			tem[i] %=3;
			result = result * 2 + tem[i];
		}
		
		return result;
		
		
		
		
    }
	//18��49��
	public int[] singleNumberI(int[] nums) {
        Map<Integer, Integer> temMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
			temMap.put(nums[i], temMap.getOrDefault(nums[i], 0) + 1);
		}
        int result[] = new int[2];
        int index = 0;
        for (Map.Entry<Integer, Integer>i : temMap.entrySet()) {
			
        	if(temMap.get(i.getKey()) == 1){
        		result[index] = i.getValue();
        		index ++;
        	}
		}
        return result;
    }
	
	//10��10��
	public int[] constructArray(int n, int k) {
		
		        int[] ret = new int[n];
		        if(n<1)
		            return ret;
		        int cnt = 0;
		        ret[cnt++]=1;
		        for(int i=k; i>0; i--){
		            if(cnt%2==1){
		                ret[cnt] = ret[cnt-1]+i;
		             }
		            else{
		                ret[cnt] = ret[cnt-1]-i;
		            }
		            cnt++;
		        }
		        if(cnt<n){
		           ret[cnt++]=2+k;
		        }
		        while(cnt<n){
		            ret[cnt] = ret[cnt-1]+1;
		            cnt++;
		        }
		        return ret;
		}
        
    
	
	
	//08��58��
	public int countArrangement(int N) {
        //N<=15
		
		//��һ����ʱ������������е�ѭ�������0��ʾδʹ�ã�1��ʾʹ����
		int tem[] = new int [N + 1];
	
		temArrange(1, tem, N);
		return countArrangement;
		
		
		
		
    }
	public void temArrange(int pose,int tem[],int N){
		if(pose > N){
			countArrangement++;
			return ;
		}
		for (int i = 1; i <= N; i++) {
			if(tem[i] == 0){
				if(i % pose == 0 || pose % i == 0){
					tem[i] = 1;
					temArrange(pose + 1, tem, N);
					tem[i] = 0;
				}
			}
		}
		
	}
	
	//21��34��
	public int countBattleships(char[][] board) {
		int count = 0;
		//��
        int row = board.length;
        //��
        int column = board[0].length;
        for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				if(board[i][j] == 'X'){
					//�ұߺ��±߶�û��
					if(i == row - 1 || board[i + 1][j] == '.'){
						if(j == column - 1 || board[i][j + 1] == '.'){
							count ++;
						}
					}
				}
				
			}
		}
        return count;
    }
	public int[] countBits(int num) {
		int []result = new int[num + 2];
		for (int i = 0; i <= num; i++) {
			result[i] = Integer.bitCount(i);
		}
     
		return result;
    }
	//14��44��
	
	//23��35��
	public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        
        gen(0, 0, result, n, "");
        
        
        return result;
    }
	
	public void gen(int left,int right,List<String> result,int count,String tem){
		if(left > count || right > count || right > left){
			return ;
		}
		if(left == count && right == count)
			result.add(tem);
		gen(left + 1, right, result , count, tem+"(");
		gen(left, right + 1, result , count, tem+")");
		
		
	}
	//20��05��
	public static  int sumSubarrayMins(int[] A) {
		int gt = 100000000 + 7;
		int sum = 0;
		//��ʼ��һ��ʼ�ǵ�һ������
		for (int i = 1; i <= A.length; i++) {
			for (int j = 0; j < A.length; j++) {
				//��ΧΪ j + i
				int min = A[j];
				int index = 1;
				while (index < i && index + j < A.length ) {
					if(A[index + j] < min){
						min = A[index + j];
					}
						index ++;
				}
				if(index == i){
					min = min % gt;
					sum = (sum % gt + min) % gt;
					//sum += min;
				}
				
			}
		}
		
		System.out.println(sum);
		
		
		return sum;
    }
	
	
	
	public List<Integer> partitionLabels(String S) {
        List<Integer> result = new ArrayList<Integer>();
        //�����һ�������һ����ĸһ����ôֱ�ӷ���һ������
        if(S.charAt(0) == S.charAt(S.length() - 1)){
        	result.add(S.length());
        	return result;
        }
        int tem[] = new int[26];
        for (int i = 0; i < S.length(); i++) {
        	//���ȼ�¼ÿ����ĸ��������������λ�ã�������tem������
			tem[S.charAt(i) - 97] = i;
		}
        //������ǽ��б���
        for (int i = 0; i < S.length();) {
        	//������ֵ��±�λ�ô���
			//��ȡ������ĸ���ֵ����һ���±��λ�ã�Ȼ��Ը��ֶ���ĸ�������ղ鿴
        	int index = tem[S.charAt(i) - 97];
        	System.out.println(index);
        	for (int j = i + 1;  j <= index; j++) {
				if(tem[S.charAt(j) - 97] > index){
					index = tem[S.charAt(j) - 97];
				}
			}
        	result.add(index - i + 1);
			i = index + 1;
		}
        return result;
    }
	//13��03��
	public int findPoisonedDuration(int[] timeSeries, int duration) {
		//�ٶ�ÿ������������ʱ�����ô������ô��ʱ��
		if(timeSeries.length == 0 || timeSeries == null || duration == 0){
			return 0;
		}
		int result = timeSeries[timeSeries.length - 1] + duration - 1;
        
		//����ʵ���Ͽ϶��м���û���ж���ʱ��
		for (int i = 1; i < timeSeries.length; i++) {
			if(timeSeries[i] - timeSeries[i - 1] > duration){
				result = result - (timeSeries[i] - timeSeries[i-1] - duration ); 
			}
		}
		
        return result - timeSeries[0] + 1;
    }
	//11��10��
	public static  int maxProduct(String[] words) {
    //����˼·�����˼·��֮ǰ���Ǹ�Ҫ��һ��
		int []num = new int[words.length];
		int result = 0;
		for (int i = 0; i < words.length; i++) {
			for (int j = 0; j < words[i].length(); j++) {
				num[i]|= (1 << words[i].charAt(j) - 97);
			}
		}
		
		for (int i = 0; i < words.length - 1; i++) {
			for (int j = i + 1; j < words.length; j++) {
				if((num[i] & num[j]) == 0){
					result = Math.max(words[i].length() * words[j].length(), result);
				}
			}
		}
		
		return result;
		
		
		/*//��������ֱ����һ����ά��������Ŷ�Ӧ��ֵ
		int [][] tem = new int[words.length][26];
		//��ʼ��tem����
		for (int i = 0; i < words.length; i++) {
			//��ÿһ��words������
			for (int j = 0; j < words[i].length(); j++) {
				tem[i][words[i].charAt(j) - 97] ++;
			}
		}
		int result = 0;
		
		for (int i = 0; i < words.length - 1; i++) {
			for (int j = i + 1; j < tem.length; j++) {
				boolean flag = false;
				for (int k = 0; k < 26; k++) {
					if(tem[i][k] * tem[j][k] != 0){
						//�������ʾ�������ظ�Ԫ��
						flag = true;
						break;
					}
				}
				if(!flag){
					result = Math.max(words[i].length() * words[j].length(), result);
				}
			}
		}
		return result;*/
    }
	
	
	//ʱ��16��57��
	public List<List<Integer>> subsets(int[] nums) {
		List<List<Integer>> result = new ArrayList<>();
		List<Integer> tem = new ArrayList<>();
		result.add(tem);
		for (int i = 0; i < nums.length; i++) {
			int size = result.size();
			while (size > 0) {
				size --;
				List<Integer> tem1  = new ArrayList<>(result.get(size));
				tem1.add(nums[i]);
				result.add(tem1);
			//	System.out.println(result.toString());
			}
			
		}
		return result;
        
    }
	//11��42��
	public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        //desiredTotal��ʾ�����������ܺ�
		//maxChoosableIntegerΪ��������
		
		//�����ѡ�����ִ���Ŀ�����֣���ôһ�����ص���true
		if(desiredTotal <= maxChoosableInteger){
			return true;
		}
		//���ڲ���ʹ���ظ������֣���ô�ж��ܺ��Ƿ����Ŀ���������С����ô˭��Ӯ����
		int sum = 0;
		for (int i = 1; i <= maxChoosableInteger; i++) {
			sum+=i;
		}
		//���ڵĻ�Ҳ�ǲ��еģ���Ϊ���������ô
		if(sum < desiredTotal){
			return false;
		}
		//������
		if(sum == desiredTotal && (maxChoosableInteger & 1) == 1)
			return true;
		//��ż��
		if(sum == desiredTotal && (maxChoosableInteger & 1) == 0)
			return false;
		Map<Integer, Boolean> map = new HashMap<>();//��ʾ����ѡ��ǰ�����ֶ�Ӧ�Ľ��
		int visit = 0;//��ʼ��Ϊ�㣬��ʾ����һ����û���ʹ�

		return getisWin(maxChoosableInteger, desiredTotal, map, visit);
		
    }
	boolean getisWin(int max,int dest,Map<Integer, Boolean> map,int visit){
		if(map.containsKey(visit)){
			return map.get(visit);
		}
		for (int i = 1; i <= max; i++) {
			int mast = (1 << i);
			//û�з��ʹ�������û��
			if((visit & mast) == 0 && (i >= dest || !getisWin(max, dest - i, map, visit | mast)) ){
				map.put(visit, true);
				return true;
			}
		}
		map.put(visit, false);
		return false;
		
		
		
	}
	 public boolean isOnDif(String s,String  t){
			int dif = 0;
			for (int i = 0; i < s.length(); i++) {
				if(s.charAt(i) != t.charAt(i)){
					dif++;
				}
				if(dif > 1 ){
					return false;
				}
			}
			return dif == 1;
		}
	 public int ladderLength(String beginWord, String endWord, List<String> wordList) {
			if(!wordList.contains(endWord)){
				return 0;
			}
			
			Set<String> beginSet = new HashSet<>(Arrays.asList(beginWord));
			Set<String> endSet = new HashSet<>(Arrays.asList(endWord));
			Set<String> dic = new HashSet<>(wordList);
			int step = 1;
			while (!beginSet.isEmpty()) {
				step++;
				Set<String> temSet = new HashSet<>();
				for (String string : beginSet) {
					//���ֵ���ɾ��beginset�а�����
					dic.remove(string);
				}
				for (String string : beginSet) {
					//���һ����������dic��
					for (String strings : dic) {
						if(isOnDif(string, strings)){
							temSet.add(strings);
							if(endSet.contains(strings)){
								return step;
							}
						}
					}
				}
				if(temSet.size() < endSet.size()){
					beginSet = temSet;
				}else {
					beginSet = endSet;
					endSet = temSet;
				}
			}
			
			return 0;
			
			
		/*
		 * Ч�ʲ��Ǻܸߵ�BFS
		 * 
		 * //�����������ô�ͷ���0
	        if(!wordList.contains(endWord)){
	        	return 0;
	        }
	        int deep = 1;
	        Queue<String> temQueue = new LinkedList<>();
	        Set<String> temSet = new HashSet<>();
	        temSet.add(beginWord);
	        temQueue.offer(beginWord);
	        while (!temQueue.isEmpty()) {
				int size = temQueue.size();
				deep ++;
				while (size > 0) {
					size--;
					String temString = temQueue.poll();
					//ȡ����һ�㣬Ȼ�����ǿ�ʼѰ����ֻ֮��һ����ĸ������
					for (String string : wordList) {
						if(isOnDif(temString, string) && !temSet.contains(string)){
							if(string.equals(endWord)){
								return deep;
							}
							temSet.add(string);
							temQueue.offer(string);
						}
						
					}
				}
			}
	        
	        return 0;*/
	    }
	public boolean exist(char[][] board, String word) {
       	
		int tem [][] =  new int[board.length][board[0].length];
		//�����ҵ���һ�����ϵ�Ȼ����л���
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if(isget(i, j,board, 0, word,tem))
					return true;
			}
		}
		return false;
    }
	public boolean isget(int x,int y,char[][] word,int index,String words,int [][]tem){
		if(index == words.length()){
			return true;
		}
		if(x < 0 || x >= word.length || y < 0 || y >= word[x].length || word[x][y] != words.charAt(index)){
			return false;
		}
		if(tem[x][y] == 0){
			tem[x][y] = 1;
			if(isget(x+1, y, word, index+1, words,tem)||
					isget(x-1, y, word, index+1, words,tem)||
					isget(x, y+1, word, index+1, words,tem)||
					isget(x, y-1, word, index+1, words,tem))
				return true;
			tem[x][y] = 0;
		}
	
		return false;

	
	
	}
	//18��01��
	public List<String> wordSubsets(String[] A, String[] B) {
		List<String> result = new ArrayList<>();
		int [][] temA = new int[A.length][26];
		int [][] temB = new int[B.length][26];
		int table[] = new int[26];
		//��B�еĵ���ȫ������temB��
		for (int i = 0; i < B.length; i++) {
			for (int j = 0; j < B[i].length(); j++) {
				temB[i][B[i].charAt(j) - 'a'] ++;
			}
			for (int j = 0; j < table.length; j++) {
				if(table[j]<temB[i][j]){
					table[j] = temB[i][j];
				}
			}
		}
		
		for (int i = 0; i < A.length; i++) {
			for (int j = 0; j < A[i].length(); j++) {
				temB[i][A[i].charAt(j) - 'a']++;
			}
		}
		for (int i = 0; i < A.length; i++) {
			boolean flag = true;
			for (int j = 0; j < 26; j++) {
				if(table[j] != 0 && table[j] > temA[i][j]){
					flag = false;
					break;
				}
			}
			if(flag){
				result.add(A[i]);
			}
			
		}
		
		
		return result;
        
    }
	
	//17��16��
	public int smallestRangeII(int[] A, int K) {
        if(A.length == 1){
        	return 0;
        }
        Arrays.sort(A);
        int len = A.length;
        int result = A[len - 1] - A[0];
        for (int i = 1; i < A.length - 1; i++) {
			int min = Math.min(A[0] + K, A[i] + K);
			int max = Math.max(A[len - 1] - K, A[i + 1] + K);
			result = Math.min(max - min , result);
		}

        return result;
        
        
    }
	
	
	public int combinationSum4(int[] nums, int target) {
		int []dp = new int[target + 1];
		dp[0] = 1;
		for (int i = 1; i <= target; i++) {
			for (int j : nums) {
				if(i >= j)
					dp[i]+=dp[i - j];
			}
			
		}
		
		
		return dp[target];
	 }
	
	
	public List<List<Integer>> combinationSum2(int[] candidates, int target) {
//		 List<List<Integer>> result = new ArrayList<>();
		 Set<List<Integer>> result = new HashSet<>();
		 List<Integer> tem = new ArrayList<>();
		 Arrays.sort(candidates);
		 getcombinationSum2(candidates, 0, target, result, tem);
		 List list = new ArrayList(result);
		// Set set = new HashSet(list);
		 return list;
	 }
	public static void getcombinationSum2(int []nums, int index, int target,Set<List<Integer>> result,List<Integer> tem){
		if(target == 0){
			result.add(new ArrayList<>(tem));
		}
		if(target < 0){
			return;
		}
		for (int i = index; i < nums.length; i++) {
			tem.add(nums[i]);
			getcombinationSum2(nums, index + 1, target - nums[i], result, tem);
			tem.remove(tem.size() - 1);
		}
	}
	 
	 
	
	public List<List<Integer>> combinationSum3(int k, int n) {
		
		//k�����֣���Ϊn
		List<List<Integer>> result = new ArrayList<>();
		int[] nums = {1,2,3,4,5,6,7,8,9};
		List<Integer> tem = new ArrayList<>();
		
		getcombinatisonSum3(nums, 0, k, result, tem, n);
		return result;
		
        
    }
	public void getcombinatisonSum3(int nums[],int start,int k ,List<List<Integer>> result, List<Integer> tem,int target){
		if(k == tem.size() && target == 0){
			result.add(tem);
			return ;
		}
		
		
		
		for (int i = start; i < 9; i++) {
			tem.add(nums[i]);
			getcombinatisonSum3(nums, i + 1, k, result, tem, target - nums[i]);
			tem.remove(tem.size() - 1);
		}
		
	}
	public List<List<Integer>> combinationSum(int[] candidates, int target) {
		  Arrays.sort(candidates);
		        List<Integer> list = new ArrayList<>();//�½���ջ�����ж�
		        List<List<Integer>> res = new ArrayList<>();//�����
		        if (candidates == null || candidates.length == 0)
		            return res;
		        combin(candidates, 0, target, list, res);
		        return res;
		    }
		    //������Ԫ�أ������򣩽�������ж��Լ���������
     private void combin(int[] candidates, int start, int target,List<Integer> list, List<List<Integer>> res) {
		        //�պ������򽫽����������
		        if (target == 0) {
		            res.add(new ArrayList<>(list));
		            return;
		        }
		        for (int i = start; i < candidates.length; i ++) {
		            if (candidates[i] <= target) { //�ж��Ƿ��Ѿ�����target
		                list.add(candidates[i]);//����һ��Ԫ�ش���         
		                combin(candidates, i, target -candidates[i] , list, res);//�����жϽ�ջԪ��
		                list.remove(list.size() - 1);//�����������һ��Ԫ���Ƴ�����ջ��Ԫ���ж�
		            }
		        }      
		    }
	
	
	
	public int maxIncreaseKeepingSkyline(int[][] grid) {
	 	//���е�����߷���row��
        List<Integer> row = new ArrayList<>();
        //���е�����߷���column��
        List<Integer> column =  new ArrayList<>();
        int result = 0;
        //һ�б���
        for (int i = 0; i < grid.length; i++) {
        	//һ��һ�б���
        	int max = grid[i][0];
			for (int j = 1; j < grid[i].length; j++) {
				max = Math.max(max, grid[i][j]);
			}
			//�õ��е������
			row.add(max);
		}
        System.out.println(row.toString());
        //�õ��������
        for (int i = 0; i < grid[0].length; i++) {
              int max = grid[0][i];
			for ( int j = 1; j < grid.length; j++) {
				max = Math.max(max, grid[j][i]);
			}
			column.add(max);
		}
        //�����е�����ߺ��е�����߶��õ���
        System.out.println(column.toString()); 
        //���������Ǽ���ÿ���������ܼӵ��������
        for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				//���ڵ��ǵ�i�к͵�j��
				//�õ���Сֵ
				int min = Math.min(row.get(i), column.get(j));
				result =  result + min - grid[i][j];
			}
		}
        return result;
    }
	
	
	
	 public static int maxArea(int[] height) {
	        int length = height.length;
	        int max = 0;
	        if(height[0] == height[length - 1]){
	        	max =  (length-1) * height[0];
	        }
	        int start = 0;
	        int end = length - 1;
	        
	       while(end > start) {
	        	int tem = height[start] < height[end]? height[start]: height[end];
				max = Math.max( tem * (end - start ), max);
				
				if(height[start] < height[end]){
					start ++;
				}else if (height[start] > height[end]) {
					end--;
				}else {
					start ++;
					end --;
				}
			}
	        
	        return max;
	    
	 }
	
	public static int lengthOfLongestSubstring(String s) {
		if(s.length() == 0){
			return 0;
		}
		int max = 1;
		//һ��ʼ�ҵ�˼·�������ģ������ַ���s�����������������ģ�abbc�������Ϊ����,�ڵڶ���b����ʱ���ַ���Ϊabb,��ʱ����Ҫ�ж�b��ab��ʲôλ�ã�
		//�Ӷ�ȷ�����ַ���������λ�ÿ�ʼ
		StringBuilder sbtem = new StringBuilder();
		for (int i = 0; i < s.length(); i++) {
			//�õ��Ƿ����ظ���
			int index = sbtem.indexOf(s.charAt(i) + "");
			
			if(index == -1){
				sbtem.append(s.charAt(i));
			}else {
				//���ظ�����ô��Ҫ��֮ǰ��ȫ�������Ƴ������Ҽ��ϵ�ǰ��
				max = Math.max(max, sbtem.length());
				sbtem.delete(0, index + 1);
				sbtem.append(s.charAt(i));
			}
			
		}
		
		
		
		
        return Math.max(max, sbtem.length());
    }
	public static  List<List<Integer>> threeSum(int[] nums) {
		Set<List<Integer>> tem1 = new HashSet<>();
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		//��һ��˼·���������鲢������ǰ��ָ��
		
		
		//�Ƚ�������һ��
		Arrays.sort(nums);
		
		for (int i = 0; i < nums.length; i++) {
			int j = nums[i];
			int goal = -j;
			int start = i+1;
			int end = nums.length -1;
			while (start < end) {
				if(nums[start] + nums[end] > goal){
					end --;
				}else if (nums[start] + nums[end] < goal) {
					start ++;
				}else {
					List<Integer> tem = new ArrayList<>();
					
					//�����Ѿ�������ˣ���˿���ֱ�ӽ����жϣ�������Ҫһ��һ���жϣ���֪j< start < end
					tem.add(j);
					tem.add(nums[start]);
					tem.add(nums[end]);
					tem1.add(tem);
					int t = nums[start];
					while(nums[start] == t){
						start ++;
						if(start >= end){
							break;
						}
					}
					t = nums[end];
					while(nums[end] == t){
						end --;
						if(start >= end){
							break;
						}
					}
					
					
				}
				
			}
			
		}
		
		
		for (List<Integer> list : tem1) {
			result.add(list);
		}
		return result;
		
	/*	
	 * 
	 * ��ʱ�Ĵ��룬���˳�ʱû����������
	 * List<List<Integer>> result = new ArrayList<List<Integer>>();
		Arrays.sort(nums);
		for (int i = 0; i < nums.length; i++) {
			int x1 = nums[i];
			for (int j = i + 1; j < nums.length; j++) {
				int x2 = nums[j];
				for (int k = j + 1; k < nums.length; k++) {
					int x3 = nums[k];
					List<Integer> tem = new ArrayList<>();
					if(x1 + x3 + x2 == 0){
						System.out.println("����֮��" + " "+x1 + " "+x2 + " "+x3);
						tem.add(x1);
						tem.add(x2);
						tem.add(x3);
						//����鿴�������Ƿ��Ѿ������ˣ�Ĭ���ǲ����ڵ�
						boolean flag = false;
						for (List<Integer> integers : result) {
							List<Integer> integer = new ArrayList<>();
							integer.add(integers.get(0));
							integer.add(integers.get(1));
							integer.add(integers.get(2));
							if(integer.contains(x1)){
								integer.remove(integer.indexOf(x1));
								 if(integer.contains(x2)){
									 integer.remove(integer.indexOf(x2));
									 if(integer.contains(x3)){
										 flag = true;
											break;
									 }
								}
								
							}else {
								continue;
							}
						}
						//�������������ô����
						if(!flag){
							result.add(tem);
						}
						
						
					}
				}
				
			}
			
		}
		
		
		
		return result;
		*/
        
    }
	
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    
	/*���룺(2 -> 4 -> 3) + (5 -> 6 -> 4)
        �����7 -> 0 -> 8
        ԭ��342 + 465 = 807
        
        ��l1��Ϊ����ֵ���������еļӷ����뵽
		*/
		int jinwei = 0;
		int size1 = 0;
		int size2 = 0;
		ListNode tem1 = l1;
		ListNode tem2 = l2;
		
		//�ֱ���������ĳ��ȣ�������������������Ϊ���صĽ��
		while (tem1!=null) {
			tem1 = tem1.next;
			size1++;
		}
		while (tem2!=null) {
			tem2 = tem2.next;
			size2++;
		}
		//tem ������Ǹ������ͷ
		ListNode tem = size1 >= size2? l1 : l2;
		
		if(size1 >= size2){
			
			//l1���ȴ���l2����ô��Ҫ��l1��Ϊ�����ֵ
			while (l2!=null) {
				int t = l1.val + l2.val + jinwei;
				l1.val = t % 10;
				jinwei = t / 10;
				//�����������һ�£������н�λ
				if(l1.next == null && l2.next == null && jinwei ==1){
					ListNode teListNode = new ListNode(1);
					teListNode.next = null;
					l1.next = teListNode;					
					return tem;
					
				}
				l1 = l1.next;
				l2 = l2.next;
				
				
				
			}
			
			while (l1!=null) {
				int t = l1.val + jinwei;
				l1.val = t % 10;
				jinwei = t / 10;
				//������һλ�н�λ
				if(l1.next == null && jinwei ==1){
					ListNode teListNode = new ListNode(1);
					teListNode.next = null;
					l1.next = teListNode;					
					return tem;
					
				}
				l1 = l1.next;
				
			}
			
			
		}else {
			//l2���ȴ���l1����ô��Ҫ��l2��Ϊ�����ֵ
			while (l1 != null) {
				int t = l1.val + l2.val + jinwei;
				l2.val = t % 10;
				jinwei = t / 10;
				l1 = l1.next;
				l2 = l2.next;
			}
			while (l2!=null) {
				int t = l2.val + jinwei;
				l2.val = t % 10;
				jinwei = t / 10;
				if(l2.next == null  && jinwei ==1){
					ListNode teListNode = new ListNode(1);
					teListNode.next = null;
					
					l2.next = teListNode;					
					return tem;
					
				}
				l2 = l2.next;
			}
			
			
		}
		
		
		
		return tem;
		
		
    }

	
	
	
	public static int myAtoi(String str) {

		if(str.trim().length() == 0){
			return 0;
		}
       char [] tem = str.toCharArray();
		int i = 0 ;
		while (tem[i] == ' ') {
			i++;
		}
       if(!((tem[i] >= '0'  &&  tem[i] <= '9')|| tem[i] == '-'|| tem[i] == '+')){
           return 0;
        }
        int result = 0;
        int st = result;
        boolean isminus = false;
        if(tem[i] == '-'||tem[i] == '+'  ){
        	if(tem[i] == '-'){
                isminus = true;
                i++;
            }else
                i++;
        }
        while (i < str.length() && tem[i] >= '0' && tem[i]<='9') {
        	result = result * 10 + (tem[i] - 48);
			if(result > Integer.MAX_VALUE / 10 ||(result == Integer.MAX_VALUE / 10 && tem[i] - '0' > 7)){
				if(isminus){
					return Integer.MIN_VALUE;
				}else {
					return Integer.MAX_VALUE;
				}
			}
			i++;
		}
        if(isminus){
        	return 0 - result;
        }else {
			return result;
		} 
    }
	
	
	public int threeSumClosest(int[] nums, int target) {
		//���ȱ����ƽ�
		int result = Integer.MAX_VALUE;
		for (int i = 0; i < nums.length; i++) {
			int tem1 = nums[i];
			for (int j = i + 1; j < nums.length; j++) {
				int tem2 = nums[j];
				for (int k = j + 1; k < nums.length; k++) {
					int tem3 = nums[k];
					if(Math.abs(tem1 + tem2 + tem3 - target) < Math.abs(result - target)){
						result = tem1 + tem2 + tem3;
					
					}
					if(result == target){
						return target;
					}
					
				}
				
			}
			
		}
        return result;
    }
}

package match;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class WeeklyContest109 {

	
	public int knightDialer(int N) {
		if(N == 1){
			return 10;
		}
		//���ȳ�ʼ���������ӣ���һ����ά��������ʾ���������ʾ����������λ�ã����Ӧ��������λ�ã����磺
		//1����λ����6��8��5û������λ�ã�2��Ӧ����7��9
		int [][]nums = {
				{4,6},
				{6,8},
				{7,9},
				{4,8},
				{3,9,0},
				{},
				{1,7,0},
				{2,6},
				{1,3},
				{2,4}
		};
		//dp���鶨��������ǳ�ʼ��һ�£���ʼֵ��ʾ����N=1 ��ʾ��0������0-9����1������ʼΪ1
		int x = 1000000000 + 7;
		int dp[] = {1,1,1,1,1,1,1,1,1,1};
		//ѭ������ΪN-1
		while (N-- > 0) {
			int []tem = new int[10];
			for (int i = 0; i < 10; i++) {
				//����������0��ʼ���У����磺0��һ�ο�������4��6����ôtem�����4��6�±�ͼ���1
				for (int j = 0; j < nums[i].length; j++) {
					//ע�����tem������±��ʾѭ������ǰ����ʱ������ǰλ�õ��ܵĸ���
					tem[nums[i][j]] = (tem[nums[i][j]] % x + dp[i] % x);
				}
			}
			//����dp����
			dp = tem.clone();
			System.out.println(Arrays.toString(dp));
		}
		int sum = 0;
		for (int i = 0; i < dp.length; i++) {
			sum = (sum + dp[i] % x) % x;
		}
		return sum;
        
    }
	
	//��̵���
	public int shortestBridge(int[][] A) {
        int result = 0;
        Queue<int[]> tem = new LinkedList<int[]>();
        
        //����Ҫ�ҵ���һ�����죬Ϊ��֮�������һ���͵ڶ����������ҵ���ֱ�ӽ�����2����������
        boolean found = false;
        for (int i = 0; i < A.length; i++) {
			for (int j = 0; j < A[i].length; j++) {
				if(!found && A[i][j] == 1){
					dfs(A, i, j);
					found = true;
				}
				if(found && A[i][j] == 1){
					//�����Ӧ���±��������н��б���
					int temq[] = new int[2];
					temq[0] = i;
					temq[1] = j;
					tem.offer(temq);
				}
			}
		}
        
        
        //Ϊ�˷�����������ﶨ��һ���������飬���ڲ���
        //����Ϊ��������
        int direc[][] = {
        		{0,1},
        		{0,-1},
        		{-1,0},
        		{1,0}
        	
        };
        //���������ڵڶ���������й�ȱ���bfs�����Ӷ�����ȡ�����꣬�����������ҽ�����䣬ֱ������һ����2����������ֳ�һ��ʼ�ѵ�һ���������ó�2
        //�ĺô��ˣ�
        while (!tem.isEmpty()) {
			int size = tem.size();
			for (int i = 0; i < size; i++) {
				int temshu[] = tem.poll();
				//���ĸ����������䣨�����ţ�
				for (int[] is : direc) {
					//x����
					int x = temshu[0] + is[0];
					//y����
					int y = temshu[1] + is[1];
					if(x < 0 || x >= A[0].length || y >= A.length || y < 0 || A[x][y] == 1 )
						continue;
					if(A[x][y] == 2){
						return result;
					}
					if(A[x][y] == 0){
						A[x][y] = 1;
						
						//�����Ӧ���±��������н��б���
						int temq[] = new int[2];
						temq[0] = x;
						temq[1] = y;
						tem.offer(temq);
					
					}
				}
			}
			result ++;
		}
        
        return 1;
    }
	public void dfs(int [][]A ,int x ,int y){
		if(x < 0 || y < 0 || x >= A.length || y >= A[0].length || A[x][y] == 0 || A[x][y] == 2 )
			return;
		A[x][y] = 2;
		dfs(A, x - 1, y);	
		dfs(A, x + 1, y);	
		dfs(A, x, y - 1);	
		dfs(A, x, y + 1);
	}

}

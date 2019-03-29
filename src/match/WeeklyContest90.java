package match;

import java.util.Stack;

public class WeeklyContest90 {

	public static void main(String[] args) {
		System.out.println(mirrorReflection(5, 1));
	}
	
	public static int mirrorReflection(int p, int q) {
		//���ݻ�ͼ����֪��ÿ���ߵľ����ǹ̶��ģ�ֻ������һ���ߵ���q���ڶ����ߵ��� 2 * q����ͼ���Ժ����Կ�����
		if(2 * q == p){
			return 2;
		}
		if(p == q)
			return 1;
		int time1 = 1;
		int time2 = 1;
		int time0 = 1;
		//�ж�����2����С����
		while ( (time2 * 2 * q + p ) % (2 * p) != 0) {
			time2 ++;
			if(time2 >= 10000){
				break;
			}
		}
		int tem2 = time2 * 2 * q;
		System.out.println("2�ľ���Ϊ��   "+  tem2);
		while ((time1 * 2 * q + q + p) % (  2 * p) != 0 ){
			time1++;
			if(time1 >= 10000){
				break;
			}
			if(time1 > time2){
				break;
			}
		}
		int tem1 = time1 * 2 * q + q;
		System.out.println("1�ľ���Ϊ��   "+  tem1);

		//ֻ��������������Ū�ģ����඼��Ҫת
		while ((time0 * 2 * q + q) % ( 2 * p ) != 0) {
			time0 ++;
			if(time0 > time1 || time0 > time2){
				break;
			}
			if(time0 >= 10000){
				break;
			}
		}
		int tem0 = time0 * 2 * q + q;
		System.out.println("0�ľ���Ϊ��   "+  tem0);

		if(tem0 < tem1 && tem0 < tem2){
			return 0;
		}else if (tem1 < tem0 && tem1 < tem2) {
			return 1;
		}else {
			return 2;
		}
        
    }
	
	public static int scoreOfParentheses(String S) {
		if(S.length() == 0)
			return 0;
		//Ϊ�˷����������������0��ʾ�������ű�ʾ��-1
		Stack<Integer> tem = new Stack<>();
		for (int i = 0; i < S.length(); i++) {
			//�����������ֱ����ջ
			if(S.charAt(i) == '('){
				tem.push(0);
			}else {
				//�������ţ���Ҫע��,�ж�ջ���ǲ�������
				
				//���ջ����������
				if(tem.peek() == 0){
					tem.pop();
					int tems = 1;
					//���ջ�������֣�Ҫ�����ۼ�,������ÿ��
					if ( !tem.isEmpty() && tem.peek() != 0) {
						
						tems += tem.pop();
						
					}
					tem.push(tems);
					
				}else {
					//���ջ�������֣���ô��Ҫ����
					int tems = tem.pop();
					tems *= 2;
					tem.pop();
					//���ջ�������֣�Ҫ�����ۼ�
					if ( !tem.isEmpty() && tem.peek() != 0) {
						tems += tem.pop();
						
					}
					tem.push(tems);
				}
			}
		}
		
        return tem.peek();
    }
	
	public static  boolean buddyStrings(String A, String B) {
		if(A.length() != B.length()){
			return false;
		}
		int tems[] = new int[26];
		int math = 0;
		int s = 0;
        for (int i = 0; i < A.length(); i++) {
        	tems[A.charAt(i) - 97]++;
			if(A.charAt(i) != B.charAt(i)){
				if(math == 1){
					char tem[] = A.toCharArray();
					tem[s] = B.charAt(s);
					tem[i] = B.charAt(i);
					if(new String(tem).equals(B)){
						return true;
					}else {
						return false;
					}
				}else {
					//��¼��λ��
					s = i;
					math ++;
				}
			}
		}
        for (int i = 0; i < tems.length; i++) {
			if(tems[i] >= 2){
				return true;
			}
		}
        return false;
    }
}

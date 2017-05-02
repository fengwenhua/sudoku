package ����;

import java.util.Scanner;

public class Sudoku2 {
	int a[][][]=new int[3][9][9];//a�����a[0]�����洢ÿһ�еĿ�������a[1]�����洢ÿһ�еĿ�������a[2]�����洢ÿһС����Ŀ�������
	int data[][]=new int[9][9];
	int flag[][]=new int[9][9];
	long  count=0;
	public Sudoku2() {
		Scanner scanner=new Scanner(System.in);
		for (int i = 0; i<9; i++)
		{
			for (int j = 0; j<9; j++)
			{
				data[i][j] = scanner.nextInt();
				if (data[i][j]==0) 
				{ 
					flag[i][j] = 1; //��λ��Ϊ0������������
					data[i][j] = -1;
				}
				else
				{
					flag[i][j] = 0; 
					data[i][j]--;//��ʼ��ʱ��Ϊ������㣬������Ĭ������Ϊ0-8�������������ʱ�ټ���1����  
					a[0][i][data[i][j]] = 1;
					a[1][j][data[i][j]] = 1;
					a[2][get_index(i, j)][data[i][j]] = 1;//�����Щ���������  
				}
			}
		}
	}
	void output()//��ӡ�Ź��Ŀ��н�  
	{
		for (int i = 0; i<9; i++)
		{
			for (int j = 0; j<9; j++)
				System.out.format("%d", data[i][j] + 1);
			System.out.println();
		}
		System.out.println();
	}

	int get_index(int y, int x)
	{
		return 3 * (y / 3) + (x / 3);//����������ȡС�Ź���λ��  
	}
	
	int get_next(int y, int x, int start)//�Ե�ǰ����ĵ�ǰֵΪ��׼��Ѱ����һ��������  
	{
		for (int i = start; i<9; i++)
			if (a[0][y][i] == 0 && a[1][x][i] == 0 && a[2][get_index(y, x)][i] == 0)return i;
		return -1;
	}
	void solve(int y, int x)
	{
		if (x == 9) { y++; x = 0; }
		while (flag[y][x]==0 && y <= 8) { x++; if (x == 9) { y++; x = 0; } }//��ȡ��һ��������������  
		if (y>8) { output(); count++; return; }//��ǰ�������Ѿ�����������Χ��Ĭ���ѵó�һ�����н�  
		while ((data[y][x] = get_next(y, x, data[y][x] + 1)) != -1)//�Ե�ǰ����ĵ�ǰֵΪ��׼��Ѱ����һ��������  
		{
			a[0][y][data[y][x]] = 1;
			a[1][x][data[y][x]] = 1;
			a[2][get_index(y, x)][data[y][x]] = 1;//������֮��Ҫ�Ѷ�Ӧ�Ĺ�������޸ģ�֤����ǰ���Ѿ�ʹ����  
			solve(y, x + 1);//��ֻ�����һ�����н���˳����ɼ�������룺if(count)break;  
			a[0][y][data[y][x]] = 0;
			a[1][x][data[y][x]] = 0;
			a[2][get_index(y, x)][data[y][x]] = 0;//��һ�������޷���ȡ������֮��Ҫ�Ѷ�Ӧ�Ĺ���ԭ  
		}
	}
	public static void main(String[] args) {
		Sudoku2 shudu=new Sudoku2();
		shudu.solve(0, 0);//��(0,0)λ�ÿ�ʼ����  
		System.out.format("count=%lld\n", shudu.count);//��count>=1��֤�������ɽ⣬�ɽ���Ϊcount,0Ϊ�޽�
	}
}

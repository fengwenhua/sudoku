package 数独;

import java.util.Scanner;

public class Sudoku2 {
	int a[][][]=new int[3][9][9];//a数组的a[0]用来存储每一行的可填数，a[1]用来存储每一列的可填数，a[2]用来存储每一小宫格的可填数。
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
					flag[i][j] = 1; //该位置为0，可以填数字
					data[i][j] = -1;
				}
				else
				{
					flag[i][j] = 0; 
					data[i][j]--;//初始化时，为方便计算，我们先默认数字为0-8，填完数在输出时再加上1即可  
					a[0][i][data[i][j]] = 1;
					a[1][j][data[i][j]] = 1;
					a[2][get_index(i, j)][data[i][j]] = 1;//标记哪些数是填过的  
				}
			}
		}
	}
	void output()//打印九宫的可行解  
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
		return 3 * (y / 3) + (x / 3);//这是用来获取小九宫的位置  
	}
	
	int get_next(int y, int x, int start)//以当前坐标的当前值为基准，寻找下一个可填数  
	{
		for (int i = start; i<9; i++)
			if (a[0][y][i] == 0 && a[1][x][i] == 0 && a[2][get_index(y, x)][i] == 0)return i;
		return -1;
	}
	void solve(int y, int x)
	{
		if (x == 9) { y++; x = 0; }
		while (flag[y][x]==0 && y <= 8) { x++; if (x == 9) { y++; x = 0; } }//获取下一个可填数的坐标  
		if (y>8) { output(); count++; return; }//当前的坐标已经超出数独范围，默认已得出一个可行解  
		while ((data[y][x] = get_next(y, x, data[y][x] + 1)) != -1)//以当前坐标的当前值为基准，寻找下一个可填数  
		{
			a[0][y][data[y][x]] = 1;
			a[1][x][data[y][x]] = 1;
			a[2][get_index(y, x)][data[y][x]] = 1;//填了数之后，要把对应的规则进行修改，证明当前数已经使用了  
			solve(y, x + 1);//若只想求出一个可行解就退出，可加上这代码：if(count)break;  
			a[0][y][data[y][x]] = 0;
			a[1][x][data[y][x]] = 0;
			a[2][get_index(y, x)][data[y][x]] = 0;//下一个格子无法抽取可填数之后，要把对应的规则还原  
		}
	}
	public static void main(String[] args) {
		Sudoku2 shudu=new Sudoku2();
		shudu.solve(0, 0);//从(0,0)位置开始填数  
		System.out.format("count=%lld\n", shudu.count);//若count>=1，证明数独可解，可解数为count,0为无解
	}
}

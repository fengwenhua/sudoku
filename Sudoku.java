package 数独;

import java.util.Scanner;

public class Sudoku {
	static int data[][];
	private long answers=0;
	int i,j;
	public Sudoku(){
		Scanner scanner=new Scanner(System.in);
		System.out.println("从第一个行第一列开始，输入数组");
		data=new int[9][9];
		for (i = 0; i < data.length; i++) {
			for (j = 0; j < data[i].length; j++) {
				data[i][j]=scanner.nextInt();
			}
		}
		
		//用循环判定如果原来的数组没有零这种情况
		for (i = 0; i < data.length; i++) {
			for (j = 0; j < data[i].length; j++) {
				if(data[i][j]==0)
					break;
			}

		}
		if(i==9&&j==9){
			System.out.println("该数独题目有问题");
			System.exit(0);
		}
		
	}
	int rule(int x, int y, int i, int a[][])
	{
		// 检查列
		int c, r;                            
		for (c = 0; c < 9; c++)                
		{
			if (c != y&&a[x][c] == i)        
				return 0;
		}
		// 检查行
		for (r = 0; r < 9; r++)                
		{
			if (r != x&&a[r][y] == i)        
				return 0;
		}
		// 检测一个方格是否不相同
		for (r = x / 3 * 3; r< x / 3 * 3 + 3; r++)
		{
			for (c = y / 3 * 3; c < y / 3 * 3 + 3; c++)
			{
				if (a[r][c] == i && r != x&&c != y)
					return 0;
			}
		}
		return 1;                         
	}
	
	int find(int num, int a[][])          
	{
		int x, y;                         
										 
		x = num / 9;                     
		y = num % 9;                      
		if (num >= 81){
			System.out.println("*******************");
			output(data);
			answers++;
			return 0;
		}

		if (a[x][y] != 0)                  
			return find(num + 1, a);       
		else                               
		{
			for (int i = 1; i <= 9; i++)
			{
				if (rule(x, y, i, a)==1)     
				{
					a[x][y] = i;          
					find(num + 1, a);					
				}
				a[x][y] = 0;	
						   
			}
			
			return 0;                      
		}
	}

	public long action(){
		find(0,data);
		return answers;
	}
	public void output(int a[][])
	{
		int i, j;
		for (i = 0; i < 9; i++)
		{
			for (j = 0; j < 9; j++)
			{
				System.out.format("%d   ", a[i][j]);
				if (j % 3 == 2)
					System.out.print("\t");
			}
			System.out.println();
			if (i % 3 == 2)
				System.out.println();
		}
	}
	public static void main(String[] args) {
		Sudoku shudu=new Sudoku();
		long answers=shudu.action();
		if(answers>0){
			System.out.println("**********************");
			System.out.println("该数独有"+answers+"个解");
		}else{
			System.out.println("该数独无解");
		}
	}

}
public class CardDp {

	public static void main(String[] args) {
		
		int value[] = {1000,2000,4000};	//购物车那个问题 只需要一个价值就行了，重量都都没有。
		
		int w = 5000;
		int n = 3;
		int dp[][] = new int[n+1][w+1];		//n表示是物品，w表示重量,初始化全是0
		
		for(int i = 1; i<= n; i++){	//每次加的物品
			for(int cw = 1 ; cw <= w ; cw ++){		//分割的背包
				if(value[i - 1] <= cw){		//表示本次这个物品可以装进去
					dp[i][cw] = Math.max(
							value[i-1] + dp[i-1][cw-value[i-1]],
							dp[i-1][cw]
							);
				}else{
					dp[i][cw] = dp[i-1][cw];	//不能装
				}
			}
		}
		System.out.println(dp[n][w]);
		
	}
}

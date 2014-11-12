import java.util.ArrayList;

public class BuildUpBlock {

	public static void main(String[] args){
		
		int[][] iBlockInfo;
		ArrayList<String>[] aryResult;
		ArrayList<String>[] wkArray;
		int num;
		int maxValue;
		int maxIndex;
		
		num = Integer.parseInt(args[0]);
		iBlockInfo = new int[num + 1][6];	//添え字0の行は未使用
		aryResult = new ArrayList[101];	//添え字0は未使用
		wkArray = new ArrayList[6];
		maxValue = 0;
		maxIndex = 0;
		
		//初期化
		for(int i = 1; i <= 100; i++){
			aryResult[i] = new ArrayList<String>(0);
		}
		
		for(int i = 1; i <= 5; i++){
			wkArray[i] = new ArrayList<String>(0);
		}
		
		//コマンドライン引数の格納
		for(int i = 1; i <= num; i++){
			for(int j = 0; j <= 5; j++){
				iBlockInfo[i][j] = Integer.parseInt(args[(i - 1) * 6 + j + 1]);
			}
		}
		
		for(int i = num; i > 0; i--){
			for(int j = 0; j <= 5; j++){
				//その面を上にして積むことのできる組み合わせを取得
				if (j % 2 == 1) {
					//奇数の対面は添え字-1
					wkArray[j] = (ArrayList<String>) aryResult[iBlockInfo[i][j - 1]].clone();
				}
				else{
					//偶数の対面は添え字+1
					wkArray[j] = (ArrayList<String>) aryResult[iBlockInfo[i][j + 1]].clone();
				}
			}
			
			for(int j = 0; j <= 5; j++){
				if (wkArray[j].size() >= aryResult[iBlockInfo[i][j]].size()) {
					//現在の組み合わせより高くなるようであれば入れ替える
					wkArray[j].add(String.valueOf(i) + " " + String.valueOf(j));
					aryResult[iBlockInfo[i][j]] = (ArrayList<String>) wkArray[j].clone();
				}
			}
		}
		
		//最も高い組み合わせを検索
		for(int i = 1; i <= 100; i++){
			if (aryResult[i].size() > maxValue){
				maxValue = aryResult[i].size();
				maxIndex = i;
			}
		}
		
		//出力
		System.out.printf("%d%n", maxValue);
		for(int i = maxValue - 1; i >= 0; i--){
			System.out.printf(Left(aryResult[maxIndex].get(i), aryResult[maxIndex].get(i).length() - 1));
			System.out.printf(GetSideString(Right(aryResult[maxIndex].get(i), 1)));
			System.out.printf("%n");
		}
	}
	
	public static String GetSideString(String str){
		String wkStr = "";
		
		switch(str) {
		case "0":
			wkStr = "front";
			break;
		case "1":
			wkStr = "back";
			break;
		case "2":
			wkStr = "left";
			break;
		case "3":
			wkStr = "right";
			break;
		case "4":
			wkStr = "top";
			break;
		case "5":
			wkStr = "bottom";
			break;
		}
		
		return wkStr;
	}
	
	public static String Left(String str, int iLength){
		String wkStr;
		
		wkStr = str.substring(0, iLength);
		
		return wkStr;
	}
	
	public static String Right(String str, int iLength){
		String wkStr;
		
		wkStr = str.substring(str.length() - iLength, str.length());
		
		return wkStr;
	}
}

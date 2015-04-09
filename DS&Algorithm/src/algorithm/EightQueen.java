package algorithm;

public class EightQueen {

	private static int Count = 0;

	public static void main(String[] args) {
		int chess[][] = new int[8][8];
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				chess[i][j] = 0;
			}
		}
		System.out.println("*********八皇后问题*********");
		Queen(0, chess);
		System.out.println("*********结束*********");
	}

	/**
	 * 
	 * @param row
	 *            起始行
	 * @param n
	 *            列数
	 * @param chess
	 *            棋盘
	 */
	public static void Queen(int row, int[][] chess) {

		// 临时棋盘
		int chess2[][] = new int[8][8];
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				chess2[i][j] = chess[i][j];
			}
		}
		if (8 == row) {
			System.out.println("第" + (Count + 1) + "种摆法:");
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					System.out.print(chess2[i][j] + " ");
				}
				System.out.println();
			}
			Count++;
		} else {
			// 判断这个位置是否有危险
			// 如果没有危险继续
			for (int j = 0; j < 8; j++) {
				if (NotDanger(row, j, chess)) {
					for (int i = 0; i < 8; i++) {
						chess2[row][i] = 0;
					}
					chess2[row][j] = 1;

					Queen(row + 1, chess2);
				}
			}

		}
	}

	/**
	 * 判断是否危险
	 * 
	 * @param row
	 * @param j
	 * @param chess
	 * @return
	 */
	private static boolean NotDanger(int row, int j, int[][] chess) {

		for (int i = 0; i < 8; i++) {
			// 列与行方向
			if (chess[i][j] == 1 || chess[row][i] == 1) {
				return false;
			}
			// 斜线方向
			// 1\ 3/
			// 4/ 2\
			if (((row - i >= 0 && j - i >= 0) && chess[row - i][j - i] == 1)
					|| ((row + i < 8 && j + i < 8) && chess[row + i][j + i] == 1)
					|| ((row - i >= 0 && j + i < 8) && chess[row - i][j + i] == 1)
					|| ((row + i < 8 && j - i >= 0) && chess[row + i][j - i] == 1)) {
				return false;
			}

		}
		return true;
	}
}
